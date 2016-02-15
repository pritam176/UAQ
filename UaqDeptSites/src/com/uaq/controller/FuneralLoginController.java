package com.uaq.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.Logincommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.util.MetaDataUtil;

/**
 * This controller is used for funeral page.
 * 
 * @author mraheem
 * 
 */
@Controller
public class FuneralLoginController extends BaseController {

	private static transient UAQLogger logger = new UAQLogger(FuneralLoginController.class);
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	@Qualifier("funeralLoginService")
	private BaseService<Logincommand, Logincommand> service;

	/**
	 * This method is used to handle funeral login request
	 */
	@RequestMapping(value = ViewPath.FUNERAL_LOGIN, method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request, ModelMap model) {

		logger.enter("handle Request");

		String view = "funeralLogin";		
		
		super.handleRequest(request, model);
		
		String languageCode = request.getParameter("languageCode");		
		
		model.addAttribute("pageMetadata", MetaDataUtil.getPageMetaData("funeral", languageCode, messageSource));
		model.addAttribute("loginCommand", new Logincommand());
		model.addAttribute("ignore", "true");
		
		return view;
	}	

	/**
	 * 	This method is used to process the login form
	 * @param loginCommand
	 * @param result funeral screen in case of success or login screen if invalid credentials
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = ViewPath.FUNERAL_LOGIN, method = RequestMethod.POST)
	public String processForm(@ModelAttribute("loginCommand") Logincommand loginCommand, BindingResult result, HttpServletRequest request,
			ModelMap model) {

		logger.enter("processForm");

		String view = "";
		
		view = "funeralLogin";
		super.handleRequest(request, model);
		 
		String languageCode = request.getParameter("languageCode");
		Boolean isLoggedInSuccessfull = Boolean.FALSE;
		Logincommand loginCommandResult;		

		if (loginCommand.getLoginUsername() != null && !loginCommand.getLoginUsername().isEmpty() && 
				loginCommand.getLoginPassword() != null && !loginCommand.getLoginPassword().isEmpty()) {
			try {
				loginCommandResult = service.execute(loginCommand);
				if (null != loginCommandResult && loginCommandResult.getIsLoggedInSucessfull()) {
					request.getSession().setAttribute("logStatus", loginCommandResult.getIsLoggedInSucessfull());
					request.getSession().setAttribute("userName", loginCommandResult.getLoginUsername());
					request.setAttribute("logStatus", loginCommandResult.getLoginUsername());
					request.setAttribute("userName", (String) request.getSession().getAttribute("userName"));
					model.addAttribute("loginCommand", loginCommandResult);
					isLoggedInSuccessfull = Boolean.TRUE;
				}			
			} catch (Exception e) {
				logger.debug("Error processing the request" + e.getMessage());
			}
		}

		if(isLoggedInSuccessfull){
			view = "redirect:" + PropertiesUtil.getProperty("globalUrl") + "/" + languageCode + "/funeral.html";
		} else {
			model.addAttribute("errorMessage", messageSource.getMessage("login.invalid.credentials", null, new Locale(languageCode)));
		}

		String pageName = "funeral";
		model.addAttribute("pageMetadata", MetaDataUtil.getPageMetaData(pageName, languageCode, messageSource));
		model.addAttribute("loginCommand", loginCommand);
		model.addAttribute("ignore", "true");
		
		logger.exit("processForm");
		
		return view;
	}
	
	/**
	 * Handles the logout process.
	 * 
	 * @return the name of the JSP page	 
	 */
	@RequestMapping(value = ViewPath.FUNERAL_LOGOUT, method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {

		logger.debug("logout");

		String view = "redirect:" + PropertiesUtil.getProperty("globalUrl") + "/" + request.getParameter("languageCode") + "/home.html";
		
		request.getSession().removeAttribute("logStatus");
		request.getSession().removeAttribute("userName");
		response.setHeader("Cache-Control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		request.getSession().invalidate();

		Logincommand loginCommand = new Logincommand();
		model.addAttribute(loginCommand);

		return view;
	}
	
}