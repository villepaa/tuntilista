package wad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
    

    
    @RequestMapping("*")
    public String handleDefault() {
        return "home";
    }
    
    @RequestMapping("/403")
    public String showAccesDenied(){
        
        return "denied";
    }
}