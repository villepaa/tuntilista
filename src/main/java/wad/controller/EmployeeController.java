/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Employee;
import wad.domain.Task;
import wad.repository.EmployeeRepository;
import wad.repository.TaskRepository;

@Controller
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
     @Autowired
    private TaskRepository taskRepository;
    
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String listEmployees(Model model){
        model.addAttribute("employees", employeeRepository.findAll());
        return "employees";
    } 
    
    @RequestMapping(value = "/employees/new", method = RequestMethod.GET)
    public String showAddForm(Model model){
        model.addAttribute("employee",new Employee());
        model.addAttribute("tasks",taskRepository.findAll());
       
        return "addEmployee";
    }
    
    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public String createEmployee(@ModelAttribute Employee emp){
        employeeRepository.save(emp);
        return "redirect:/employees";
    }
    
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public String showEmployee(Model model, @PathVariable Long id){
        model.addAttribute("employee",employeeRepository.findOne(id));
        model.addAttribute("tasks",taskRepository.findAll());
        
        return "editEmployee";
    }
    
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public String editEmployee(@ModelAttribute Employee emp, @PathVariable Long id){
        Employee updated = employeeRepository.getOne(id);
        updated.setForename(emp.getForename());
        updated.setSurname(emp.getSurname());
        updated.setEmail(emp.getEmail());
        updated.setAddress(emp.getAddress());
        updated.setPhoneNumber(emp.getPhoneNumber());
        
        updated.setQualifications(emp.getQualifications());
        employeeRepository.save(updated);
        return "redirect:/employees";
    }
    
     @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable Long id){
        employeeRepository.delete(id);
        return "redirect:/employees";
    }
    
    
}
