/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.time.LocalTime;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Employee;
import wad.domain.Task;
import wad.repository.TaskRepository;

@Controller
public class TaskController {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String listTasks(Model model){
        model.addAttribute("tasks",taskRepository.findAll());
        return "tasks";
    } 
    
    @RequestMapping(value = "/tasks/new", method = RequestMethod.GET)
    public String showAddForm(Model m){
        m.addAttribute("task", new Task());
        return "addTask";
    }
    
    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public String createTask(@Valid @ModelAttribute Task task,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addTask";
        }
        taskRepository.save(task);
        
        
        return "redirect:/tasks";
    }
    
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public String showTask(Model model, @PathVariable Long id){
        model.addAttribute("task",taskRepository.findOne(id));
        return "editTask";
    }
    
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    public String editTask(@Valid  @ModelAttribute Task task,@PathVariable Long id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "editTask";
        }
        Task updated = taskRepository.getOne(id);
        updated.setName(task.getName());
        updated.setStartTime(task.getStartTime());
        updated.setEndTime(task.getEndTime());
        updated.setInformation(task.getInformation());
        taskRepository.save(updated);
        return "redirect:/tasks";
    }
    
     @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    public String deleteTask(@PathVariable Long id){
        taskRepository.delete(id);
        return "redirect:/tasks";
    }
}
