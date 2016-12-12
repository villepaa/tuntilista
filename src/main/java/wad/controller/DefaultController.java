package wad.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    
    private static final Logger log = Logger.getLogger(DefaultController.class);
    
    @RequestMapping("*")
    public String handleDefault() {
        
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("handleDefault() / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        
        return "home";
    }
    
    @RequestMapping("/403")
    public String showAccesDenied(){
        
        if(SecurityContextHolder.getContext().getAuthentication() != null){
            log.info("AccessDenied() / user: "+ SecurityContextHolder.getContext().getAuthentication().getName());
        }
        
        return "denied";
    }
}