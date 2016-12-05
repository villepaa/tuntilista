/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wad.controller;

import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String createTask(@ModelAttribute Task task){
        taskRepository.save(task);
        
        return "redirect:/tasks";
    }
    
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public String showTask(){
        
        return "editTask";
    }
    
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    public String editTask(){
        
        return "redirect:/tasks";
    }
    
     @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    public String deleteTask(@PathVariable Long id){
        taskRepository.delete(id);
        return "redirect:/tasks";
    }
}
