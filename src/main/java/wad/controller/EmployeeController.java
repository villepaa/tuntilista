
package wad.controller;
import java.util.ArrayList;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Employee;
import wad.repository.EmployeeRepository;
import wad.repository.TaskRepository;
import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;



@Controller
public class EmployeeController {
    
    private static final Logger log = Logger.getLogger(EmployeeController.class);
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaskRepository taskRepository;
     
    @Autowired
    private PasswordEncoder encoder; 
    
     
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String listEmployees(Model model){
        
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("listEmployees() / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        
        model.addAttribute("employees", employeeRepository.findAll());
        return "employees";
    }
    
    
    @RequestMapping(value = "/employees/new", method = RequestMethod.GET)
    public String showAddForm(Model model){
       
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("showAddForm() / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        
        Employee employee = new Employee();
        employee.setQualifications(new ArrayList<>());
        employee.setUserRoles(new ArrayList<>());
        model.addAttribute("employee",employee);
        model.addAttribute("tasks",taskRepository.findAll());


        return "addEmployee";
       
    }
    
    
    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public String createEmployee(Model model,@Valid @ModelAttribute Employee emp, BindingResult bindingResult){
        Employee employee = employeeRepository.findByUsername(emp.getUsername());
        if(employee != null){
            bindingResult.addError(new FieldError("Employee","username","Käyttäjänimi jo käytössä!"));
        }
        
        if(emp.getPassword().isEmpty()){
            bindingResult.addError(new FieldError("Employee","password","Salasana ei saa olla tyhjä!"));
        }
        
        if(bindingResult.hasErrors()) {
            model.addAttribute("tasks",taskRepository.findAll());
            return "addEmployee";
        }
        
        
        String encoded = encoder.encode(emp.getPassword());
        emp.setPassword(encoded);
        employeeRepository.save(emp);
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("createEmployee() " + emp.getUsername() + "  / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return "redirect:/employees";
        
       
    }
    
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public String showEmployee(Model model, @PathVariable Long id){
        
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("showEmployee: " + id  + " / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        
        model.addAttribute("employee",employeeRepository.findOne(id));
        model.addAttribute("tasks",taskRepository.findAll());
        
        return "editEmployee";
    }
    
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public String editEmployee(Model model,@ModelAttribute Employee emp, @PathVariable Long id, BindingResult bindingResult){
        
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("editEmployee: " + id  + " user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        
        if(bindingResult.hasErrors()) {
            model.addAttribute("tasks",taskRepository.findAll());
            return "addEmployee";
        }
        
        Employee updated = employeeRepository.findOne(id);
        updated.setForename(emp.getForename());
        updated.setSurname(emp.getSurname());
        updated.setEmail(emp.getEmail());
        updated.setAddress(emp.getAddress());
        updated.setPhoneNumber(emp.getPhoneNumber());
        updated.setUserRoles(emp.getUserRoles());
        updated.setQualifications(emp.getQualifications());

        employeeRepository.save(updated);
        return "redirect:/employees";
            
           
    }
   
     @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable Long id){
        
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("deleteEmployee: " + id  + " / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
           
        employeeRepository.delete(id);
        return "redirect:/employees";
        
    }
    
    
}
