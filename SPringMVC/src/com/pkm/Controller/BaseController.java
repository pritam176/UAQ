package com.pkm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
	
	@RequestMapping("/About")  
    public String helloWorld(Model model) {  
        String message = "Hello World, Spring MVC @ Pritam";  
        model.addAttribute("message", message);
        return "home";  
    }  

}
