/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    
    @RequestMapping(value = "/plans", method = RequestMethod.POST)
    public String createPlan(@RequestParam String startDate, @RequestParam String endDate,
                             @RequestParam List<String> valitut   ){
        Plan p = new Plan();
        List<PlannedEmployee> emps = new ArrayList<>();
        List<PlannedTask> tasks = new ArrayList<>();
        for(String name: valitut){
            Employee e = employeeRepository.findBySurname(name);
            PlannedEmployee plEmp = new PlannedEmployee();
            plEmp.setEmployee(e);
               
        }
        
        p.setStartDate(LocalDate.parse(startDate));
        p.setEndDate(LocalDate.parse(endDate));
        LocalDate d = p.getStartDate();
        
        while(d.isBefore(p.getEndDate().plusDays(1))){
            Task t = taskRepository.findByName("VP");
            PlannedTask pt = new PlannedTask();
            pt.setDateof(d);
            pt.setTask(t);
            tasks.add(pt);
            d = d.plusDays(1);
        }
        
        for(PlannedEmployee e : emps){
           e.setTasks(tasks);
        }
        p.setEmployees(emps);
        p.setName(startDate + endDate);
        planRepository.save(p);
        return "redirect:/plans";
    }
    
    @RequestMapping(value = "/plans/{id}", method = RequestMethod.GET)
    public String showPlan(Model model,@PathVariable Long id){
        Plan plan = planRepository.findOne(id);
        model.addAttribute("plan",plan);
        model.addAttribute("employees",plan.getEmployees());
        return "plan";
    }
}
