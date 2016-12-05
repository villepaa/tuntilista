package wad.controller;

import java.time.LocalTime;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wad.domain.Task;
import wad.repository.EmployeeRepository;
import wad.repository.TaskRepository;

@Controller
public class DefaultController {
    
//      @Autowired
//    private EmployeeRepository employeeRepository;
//    
//     @Autowired
//    private TaskRepository taskRepository;
//    
//     @PostConstruct
//    public void init() {
//        Task t = new Task();
//        Task ta = new Task();
//        t.setEndTime(LocalTime.MIN);
//    }
    
    @RequestMapping("*")
    public String handleDefault() {
        return "home";
    }
}