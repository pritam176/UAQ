package com.pkm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
	
	@RequestMapping("/about.html")  
    public String getAboutPage(Model model) {  
        String message = "Hello World, Spring MVC @ Pritam";  
        model.addAttribute("message", message);
        return "about";  
    }  
	
	
	@RequestMapping("/services.html")  
    public String getServicesPage(Model model) {  
        String message = "Hello World, Spring MVC @ Pritam";  
        model.addAttribute("message", message);
        return "home";  
    } 
	
	@RequestMapping("/portfolio.html")  
    public String getPortfolioPage(Model model) {  
        String message = "Hello World, Spring MVC @ Pritam";  
        model.addAttribute("message", message);
        return "home";  
    } 
	
	@RequestMapping("/gallery.html")  
    public String getGalleryPage(Model model) {  
        String message = "Hello World, Spring MVC @ Pritam";  
        model.addAttribute("message", message);
        return "home";  
    } 
	
	@RequestMapping("/contact.html")  
    public String getContactPage(Model model) {  
        String message = "Hello World, Spring MVC @ Pritam";  
        model.addAttribute("message", message);
        return "home";  
    } 
	
	
	

}
