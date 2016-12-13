
package wad.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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


@Controller
public class PlanController {
    
    private static final Logger log = Logger.getLogger(PlanController.class);
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaskRepository taskRepository;
     
    @Autowired
    private PlanRepository planRepository;
    
    @RequestMapping(value = "/plans", method = RequestMethod.GET)
    public String showPlans(Model model){
        
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("showPlans() / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        PlanForm form = new PlanForm();
        
        LocalDate latest = planRepository.findOneByLatestEndDate();
        if(latest != null){
            form.setStartDate(latest.plusDays(1));
            
        }
        model.addAttribute("plans",planRepository.findAll());
        model.addAttribute("employees",employeeRepository.findAll());
        model.addAttribute("planForm",form);
        
        return "plans";
    }
   
    @RequestMapping(value = "/plans/{id}", method = RequestMethod.DELETE)
    public String showPlan(@PathVariable Long id){
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("showPlan() / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        planRepository.delete(id);
        return "redirect:/plans";
    }
    
    
    @Transactional
    @RequestMapping(value = "/plans", method = RequestMethod.POST)
    public String createPlan(@Valid @ModelAttribute PlanForm planForm, BindingResult result, Model model){
        if(planForm.getEndDate().isBefore(planForm.getStartDate())){
             result.addError(new FieldError("PlanForm","endDate","Loppupäivämäärä ennen alkupäivämäärää!"));
        }     
        
        if(result.hasErrors()){
            if(SecurityContextHolder.getContext().getAuthentication() != null){
                log.info("FAIL:createPlan() / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
            }
            model.addAttribute("plans",planRepository.findAll());
            model.addAttribute("employees",employeeRepository.findAll());
            return "plans";
        }
        Plan p = new Plan();
               
        p.setStartDate(planForm.getStartDate());
        p.setEndDate(planForm.getEndDate());
        p.setName(planForm.getStartDate().toString() + " - " + planForm.getEndDate().toString());
        
        Set <PlannedEmployee> emps = new TreeSet<>();

        LocalDate dStart = planForm.getStartDate();
        LocalDate dEnd = planForm.getEndDate().plusDays(1);
        LocalDate d;
        Task t = taskRepository.findByName("VP");
        for(Long id: planForm.getEmployeeIds()){
                d = dStart;
                Employee e = employeeRepository.findOne(id);
                PlannedEmployee pEmp = new PlannedEmployee();
                pEmp.setPlan(p);
                pEmp.setEmployee(e);
                
                while(d.isBefore(dEnd)){
                    PlannedTask pt = new PlannedTask();
                    pt.setDateof(d);
                    pt.setEmployee(pEmp);
                    pt.setTask(t);
                    pEmp.addTask(pt);
                    d = d.plusDays(1);
                }
                emps.add(pEmp);
        }      
        
        
        
        p.setEmployees(emps);
        
        p = planRepository.save(p);
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("createPlan: " + p.getId() + " / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return "redirect:/plans";
    }
    
    
    @RequestMapping(value = "/plans/{id}", method = RequestMethod.GET)
    public String showPlan(Model model,@PathVariable Long id){
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("showPlan: " + id  + " /user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        Plan plan = planRepository.findOne(id);
        ArrayList<PlannedTask> tasks = new ArrayList<>(plan.getEmployees().iterator().next().getTasks());
        Collections.sort(tasks);
        model.addAttribute("plan",plan);
        model.addAttribute("dates",tasks);
        return "plan";
    }
    
    @RequestMapping(value = "/plans/{id}", method = RequestMethod.PUT)
    public String updatePlan(Model model,@RequestParam Long employeeId,@RequestParam Long ptaskId, 
                             @RequestParam String taskName, @PathVariable Long id){
        
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("updatePlan: " + id  + " /user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        Plan plan = planRepository.findOne(id);
        Set <PlannedEmployee> emps = plan.getEmployees();
        PlannedEmployee newEmp = new PlannedEmployee();
        PlannedTask newT = new PlannedTask();
        int index = 0;
        for(PlannedEmployee pEmp : emps){
            if(pEmp.getId().equals(employeeId)){
                newEmp = pEmp;
                for(int i= 0;i<newEmp.getTasks().size();i++){
                    PlannedTask t = newEmp.getTasks().get(i);
                    if(t.getId().equals(ptaskId)){
                        newT = t;
                        newT.setTask(taskRepository.findByName(taskName));
                        index = i;
                }
        }
            }
        }
        newEmp.getTasks().remove(index);
        newEmp.addTask(newT);
        plan.addEmployee(newEmp);
        planRepository.save(plan);
        
        return "redirect:/plans/" + plan.getId();
    }
}
