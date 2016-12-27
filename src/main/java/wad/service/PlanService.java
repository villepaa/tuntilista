/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.service;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.domain.Employee;
import wad.domain.Plan;
import wad.domain.PlannedEmployee;
import wad.domain.PlannedTask;
import wad.domain.Task;
import wad.repository.EmployeeRepository;
import wad.repository.PlanRepository;
import wad.repository.TaskRepository;
import wad.service.validator.PlanForm;

@Service
public class PlanService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaskRepository taskRepository;
     
    @Autowired
    private PlanRepository planRepository;
    
    @Transactional
    public Plan createPlan(PlanForm planForm){
        
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
        return planRepository.save(p);
    }
    
    @Transactional
    public void updatePlan(Long employeeId, Long ptaskId, String taskName, Long id){
        
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
    }
}
