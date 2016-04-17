package com.pkm.Controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pkm.config.PropertiesUtil;

@Qualifier("homeController")
@Controller
public class HomeController {
	
	
	
	 @RequestMapping("/home.html")  
	    public String helloWorld(Model model) {  
	        String message = "Hello World, Spring MVC @ Pritam"+PropertiesUtil.getProperty("test");  
	       model.addAttribute("message", message);
	        return "home";  
	    }  

}
