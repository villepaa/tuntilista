
package wad.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Employee;
import wad.domain.Plan;
import wad.domain.PlannedEmployee;
import wad.domain.PlannedTask;
import wad.domain.Task;
import wad.service.validator.PlanForm;
import wad.repository.EmployeeRepository;
import wad.repository.PlanRepository;
import wad.repository.TaskRepository;
import wad.service.PlanService;


@Controller
public class PlanController {
    
    private static final Logger log = Logger.getLogger(PlanController.class);
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaskRepository taskRepository;
     
    @Autowired
    private PlanRepository planRepository;
    
    @Autowired
    private PlanService planService;
    
    @PreAuthorize("hasAnyAuthority('READER','PLANNER','ADMIN')")
    @RequestMapping(value = "/plans", method = RequestMethod.GET)
    public String showPlans(Model model){
        
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("showPlans() / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        
        // haetaan viimeisimmän loppupäivämäärän mukaan suunnitelma-olio
        
        Pageable pageable = new PageRequest(0, 1, Sort.Direction.DESC, "endDate");
        Page<Plan> page = planRepository.findAll(pageable);
        
        List<Plan> allPlans = planRepository.findAll();
        PlanForm form = new PlanForm();
        LocalDate latest = LocalDate.MIN;
        
        if(!allPlans.isEmpty()){
            
            // Jos suunnitelmia oli olemassa, niin asetetaan viimeisen päivämäärä lomake-olioon
            
            latest = page.getContent().get(0).getEndDate().plusDays(1); 
            form.setStartDate(latest);
        }
        model.addAttribute("plans",allPlans);
        model.addAttribute("employees",employeeRepository.findAll());
        model.addAttribute("planForm",form);
        
        return "plans";
    }
    
    @PreAuthorize("hasAnyAuthority('PLANNER','ADMIN')")
    @RequestMapping(value = "/plans/{id}", method = RequestMethod.DELETE)
    public String showPlan(@PathVariable Long id){
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("showPlan() / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        planRepository.delete(id);
        return "redirect:/plans";
    }
    
    
    @PreAuthorize("hasAnyAuthority('PLANNER','ADMIN')")
    @Transactional
    @RequestMapping(value = "/plans", method = RequestMethod.POST)
    public String createPlan(@Valid @ModelAttribute PlanForm planForm, BindingResult result, Model model){
                
        if(result.hasErrors()){
            if(SecurityContextHolder.getContext().getAuthentication() != null){
                log.info("FAIL:createPlan() / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
            }
            model.addAttribute("plans",planRepository.findAll());
            model.addAttribute("employees",employeeRepository.findAll());
            return "plans";
        }
        if(planForm.getEndDate().isBefore(planForm.getStartDate())){
             result.addError(new FieldError("PlanForm","endDate","Loppupäivämäärä ennen alkupäivämäärää!"));
             model.addAttribute("plans",planRepository.findAll());
            model.addAttribute("employees",employeeRepository.findAll());
            return "plans";
        }     
        
        
        
        Plan p = planService.createPlan(planForm);
        
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("createPlan: " + p.getId() + " / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        
        return "redirect:/plans";
    }
    
    @PreAuthorize("hasAnyAuthority('READER','PLANNER','ADMIN')")
    @RequestMapping(value = "/plans/{id}", method = RequestMethod.GET)
    public String showPlan(Model model,@PathVariable Long id){
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("showPlan: " + id  + " /user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        
        // Näytetään suunnitelma, päivämäärät haetaan jonkin työntekijän listasta (vähän purkkaratkaisu...)
        
        Plan plan = planRepository.findOne(id);
        ArrayList<PlannedTask> tasks = new ArrayList<>(plan.getEmployees().iterator().next().getTasks());
        Collections.sort(tasks);
        model.addAttribute("plan",plan);
        model.addAttribute("dates",tasks);
        return "plan";
    }
    
    @PreAuthorize("hasAnyAuthority('PLANNER','ADMIN')")
    @RequestMapping(value = "/plans/{id}", method = RequestMethod.PUT)
    public String updatePlan(Model model,@RequestParam Long employeeId,@RequestParam Long ptaskId, 
                             @RequestParam String taskName, @PathVariable Long id){
        
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("updatePlan: " + id  + " /user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        planService.updatePlan(employeeId,ptaskId,taskName,id);
        return "redirect:/plans/" + id;
    }
}
