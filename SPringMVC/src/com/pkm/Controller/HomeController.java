package com.pkm.Controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pkm.command.RequestFormCommand;
import com.pkm.config.PKMLogger;
import com.pkm.config.PropertiesUtil;
import com.pkm.service.IssueRequestService;
import com.pkm.service.ProductService;

@Qualifier("homeController")
@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private IssueRequestService issueRequestService;
	
	protected PKMLogger logger =new PKMLogger(HomeController.class);
	
	
	
	 @RequestMapping("/home.html")  
	    public String getHomePage(Model model) {  
	        String message = "Hello World, Spring MVC @ Pritam"+PropertiesUtil.getProperty("test");  
	       model.addAttribute("message", message);
	        return "home";  
	    }
	 @RequestMapping("/requestForm.html")
	 public String requestFormPage(ModelMap model){
		 
		 model.addAttribute("productList", productService.getProuducttypeList());
		 //model.addAttribute("productSubTypeList", productService.getProuducttypeList());
		 
		 model.addAttribute("requestFormCommand", new RequestFormCommand());
		return "requestform";
		 
	 }
	 @RequestMapping(value ="/submitRequestForm.html",method = RequestMethod.POST)
	 public String requestFormPost(@ModelAttribute("requestFormCommand")RequestFormCommand requestFormCommand,ModelMap model,HttpServletRequest request){
		//System.out.print(request.getParameter("name"));
		//System.out.print(requestFormCommand.getAddress().getAdress1());
		//System.out.print(requestFormCommand.getAddress().getLongitude());
		MultipartFile multipartFile = requestFormCommand.getDescriptionFile();
		System.out.print(multipartFile.getOriginalFilename());
		issueRequestService.saveAndSendRequest(requestFormCommand);
		
		 model.addAttribute("message", "Mr   "+requestFormCommand.getName()+"   your request is submited succesfully.we will get back to u soon");
		 return "thankyou";
		 
	 }
	 @ResponseBody
	 @RequestMapping("/getSubType.html")
	 public String processSubTypeList(@RequestParam(value = "key", required = false) String key,HttpServletRequest request){
		
		 logger.debug("key Request= "+key);
			
		 Map<String , String> datamap=productService.getProuductSubtypeList(key);
		 if(datamap!=null)
			 return new JSONObject(datamap).toString();
		 return "";
		 
	 }
	 @RequestMapping("/getIssueDetail.html")
	 public String getIssueDetail(@RequestParam(value = "issueId", required = false) String issueId){
		 logger.enter("getIssueDetail | Issue Id "+issueId);
		 
		 
		return null;
		 
	 }

}
