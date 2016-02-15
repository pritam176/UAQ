/**
 * 
 */
package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.TilesViewConstant.*;
import static com.uaq.common.UAQURLConstant.*;

import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uaq.services.changepassword.model.UAQChangePasswordServicePortBindingStub;
import uaq.services.changepassword.model.UAQChangePasswordService_PortType;
import uaq.services.changepassword.model.UAQChangePasswordService_Service;
import uaq.services.changepassword.model.UAQChangePasswordService_ServiceLocator;

import com.uaq.command.ChangePasswordCommand;

import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.logger.UAQLogger;

/**
 * @author akhil
 *
 */

@Controller
public class ChangePasswordController extends BaseController {
	
	public static transient UAQLogger logger = new UAQLogger(ChangePasswordController.class);
	

	private  uaq.services.changepassword.model.UserContext uc = null;
	private UAQChangePasswordService_Service service = null;
	private UAQChangePasswordService_PortType port = null;
	private UAQChangePasswordServicePortBindingStub stub = null;
	String userName = null;
	
	@RequestMapping(value=ViewPath.CHANGE_PASSWORD,method = RequestMethod.GET)
	public String handleChangePassword(HttpServletRequest request, ModelMap model){
		super.handleRequest(request, model);

		String view ="changePassword.page";
		model.addAttribute("changePasswordCommand", new ChangePasswordCommand());
		return view;
	}
	
	@RequestMapping(value=ViewPath.CHANGE_PASSWORD,method = RequestMethod.POST)
	public String handleChangePasswordSubmit(@ModelAttribute("changePasswordCommand")ChangePasswordCommand changePasswordCommand, HttpServletRequest request, ModelMap model){
		super.handleRequest(request, model);
		
				
		String password = changePasswordCommand.getPassword();
		String userName = changePasswordCommand.getUserName();
		String languageCode = request.getParameter("languageCode");
		
		
		
		createStub();
		
		ChangePassword.InputPayload inputPayload = new ChangePassword.InputPayload();
		
		
		inputPayload.setPassword(password);
		inputPayload.setUsername(userName);
		
		try {
			ChangePassword.OutputPayload  outputPayload = stub.changePassword(inputPayload, uc);
			
			if(outputPayload!=null){
				
				if(languageCode.equals(LANG_ENGLISH)){
					model.addAttribute("message", outputPayload.getMessage_EN());
				}else if(languageCode.equals(LANG_ARABIC)){
					model.addAttribute("message", outputPayload.getMessage_AR());
				}
				logger.info("reponse from changepassword service is :: " + outputPayload.getMessage_EN());
			}else{
				model.addAttribute("message", "Something went wrong. Please try again later");
				logger.info("outputPayload is :: " + outputPayload);
			}
		} catch (RemoteException e) {
			model.addAttribute("message", "Something went wrong. Please try again later");
			e.printStackTrace();
			logger.error("Exception from changepassword service :: "+e);
		}
		model.addAttribute(PAGE_LABEL, "label.change.password");	
		model.addAttribute(ISMOBILE, "false");	
		model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "false");
		return "redirect:" + PropertiesUtil.getProperty("uaq.url")+ URL_SEPARATOR + languageCode + THANKYOU_PAGE;
	}
	
	private  void createStub() {
		uc = new uaq.services.changepassword.model.UserContext();
		uc.setUsername(WS_USERNAME);
		uc.setPassword(WS_PASSWORD);

		try {
			service = (UAQChangePasswordService_Service) new UAQChangePasswordService_ServiceLocator();
			port = service.getUAQChangePasswordServicePort();
			stub = (UAQChangePasswordServicePortBindingStub) port;

		} catch (ServiceException e) {
			logger.error("WebService Error  |" + e.getMessage());
		}

	}

}
