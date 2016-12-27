/*
 Tämä kontrolleri huolehtii työvuorojen perustietojen luonnista ja ylläpidosta
 */
package wad.controller;


import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import wad.domain.Task;
import wad.repository.TaskRepository;

@Controller
public class TaskController {
    
    private static final Logger log = Logger.getLogger(PlanController.class);
    
    @Autowired
    private TaskRepository taskRepository;
    
    // Listataan työvuorot
    
    @PreAuthorize("hasAnyAuthority('READER','PLANNER','ADMIN')")
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String listTasks(Model model){
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("listTasks: user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        
        model.addAttribute("tasks",taskRepository.findAll());
        return "tasks";
    } 
    
    // avataan työvuoron luonti näkymä
    
    @PreAuthorize("hasAnyAuthority('PLANNER','ADMIN')")
    @RequestMapping(value = "/tasks/new", method = RequestMethod.GET)
    public String showAddForm(Model m){
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("showAddTask: user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        m.addAttribute("task", new Task());
        return "addTask";
    }
    
    // luodaan ja validoidaan työvuoro-olio
    
    @PreAuthorize("hasAnyAuthority('PLANNER','ADMIN')")
    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public String createTask(Model m ,@Valid @ModelAttribute Task task,BindingResult bindingResult){
        Task t = taskRepository.findByName(task.getName());
        if(t != null){
            bindingResult.addError(new FieldError("Task","name","Antamallasi nimellä löytyy jo työvuoro!"));
        }
        
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("createTask: " + task.getName() + " / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        if(bindingResult.hasErrors()){
            return "addTask";
        }
        taskRepository.save(task);
        
        
        return "redirect:/tasks";
    }
    
    // haetaan työvuoron tiedot 
    
    @PreAuthorize("hasAnyAuthority('READER','PLANNER','ADMIN')")
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public String showTask(Model model, @PathVariable Long id){
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("showTask: " + id  +  "user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        model.addAttribute("task",taskRepository.findOne(id));
        return "editTask";
    }
    
    @PreAuthorize("hasAnyAuthority('PLANNER','ADMIN')")
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    public String editTask(@Valid  @ModelAttribute Task task,@PathVariable Long id, BindingResult bindingResult){
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("editTask: " + id  + " user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        if(bindingResult.hasErrors()){
            return "editTask";
        }
        Task updated = taskRepository.findOne(id);
        updated.setName(task.getName());
        updated.setStartTime(task.getStartTime());
        updated.setEndTime(task.getEndTime());
        updated.setInformation(task.getInformation());
        taskRepository.save(updated);
        return "redirect:/tasks";
    }
    
    @PreAuthorize("hasAnyAuthority('PLANNER','ADMIN')")
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    public String deleteTask(@PathVariable Long id){
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("deleteTask: " + id  +  " /user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        Task t = taskRepository.findOne(id);
        if(!t.getName().equals("VP")){
            taskRepository.delete(id);
        }
            
        return "redirect:/tasks";
    }
}
