
package wad.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Collections;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Employee;
import wad.domain.Plan;
import wad.domain.PlannedEmployee;
import wad.domain.PlannedTask;
import wad.domain.Task;
import wad.repository.EmployeeRepository;
import wad.repository.PlanRepository;
import wad.repository.TaskRepository;


@Controller
public class PlanController {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaskRepository taskRepository;
     
    @Autowired
    private PlanRepository planRepository;
    
    @RequestMapping(value = "/plans", method = RequestMethod.GET)
    public String showPlans(Model model){
        model.addAttribute("plans",planRepository.findAll());
        model.addAttribute("employees",employeeRepository.findAll());
        model.addAttribute("plan", new Plan());
        return "plans";
    }
    
    @RequestMapping(value = "/plans/{id}", method = RequestMethod.DELETE)
    public String showPlan(@PathVariable Long id){
        planRepository.delete(id);
        return "redirect:/plans";
    }
    
    @Transactional
    @RequestMapping(value = "/plans", method = RequestMethod.POST)
    public String createPlan(@RequestParam String startDate, @RequestParam String endDate,
                             @RequestParam List<Long> chosen){
        Plan p = new Plan();
               
        p.setStartDate(LocalDate.parse(startDate));
        p.setEndDate(LocalDate.parse(endDate));
        p.setName(startDate + " - " + endDate);
        
        Set <PlannedEmployee> emps = new TreeSet<>();

        LocalDate dStart = LocalDate.parse(startDate);
        LocalDate dEnd = LocalDate.parse(endDate).plusDays(1);
        LocalDate d;
        Task t = taskRepository.findByName("VP");
        for(Long id: chosen){
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
        
        planRepository.save(p);
        return "redirect:/plans";
    }
    
    @RequestMapping(value = "/plans/{id}", method = RequestMethod.GET)
    public String showPlan(Model model,@PathVariable Long id){
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
