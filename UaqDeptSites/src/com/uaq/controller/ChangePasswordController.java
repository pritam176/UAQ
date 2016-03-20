/**
 * 
 */
package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.TilesViewConstant.*;
import static com.uaq.common.UAQURLConstant.*;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import net.sf.cglib.core.Local;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.uaq.dao.ChangePasswordDAO;
import com.uaq.logger.UAQLogger;
import com.uaq.service.ChangePasswordService;

/**
 * @author akhil
 * 
 */

@Controller
public class ChangePasswordController extends BaseController {

	@Autowired
	private ChangePasswordDAO changePasswordDAO;

	@Autowired
	private ChangePasswordService changePasswordService;

	public static transient UAQLogger logger = new UAQLogger(ChangePasswordController.class);

	String userName = null;

	@RequestMapping(value = ViewPath.CHANGE_PASSWORD, method = RequestMethod.GET)
	public String handleChangePassword(HttpServletRequest request, ModelMap model) {
		super.handleRequest(request, model);
		String languageCode = request.getParameter("languageCode");
		String view = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
		String key = request.getParameter("userName");
		
		Locale locale = new Locale(languageCode);
		model.addAttribute(ISMOBILE, "false");
		try {
			Map<String, String> userdata = changePasswordDAO.getByPrimaryKey(key);
			if ("1".equals(userdata.get("ISACTIVE"))) {
				view = "changePassword.page";
				ChangePasswordCommand comand = new ChangePasswordCommand();
				comand.setUserName(userdata.get("USERNAME"));
				comand.setKey(key);
				model.addAttribute("changePasswordCommand", comand);
			}
			if ("2".equals(userdata.get("ISACTIVE"))) {
				model.addAttribute("message", messageSource.getMessage("change.password.link.invalid", null, locale));
				view = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
			}

		} catch (SQLException e) {
			
			logger.error(e.getMessage());
		}
		model.addAttribute(PAGE_LABEL, "label.change.password");
		model.addAttribute(ISMOBILE, "false");
		model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "false");
		return view;
	}

	@RequestMapping(value = ViewPath.CHANGE_PASSWORD, method = RequestMethod.POST)
	public String handleChangePasswordSubmit(@ModelAttribute("changePasswordCommand") ChangePasswordCommand changePasswordCommand, HttpServletRequest request, ModelMap model) {
		super.handleRequest(request, model);
		String languageCode = request.getParameter("languageCode");
		String view = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;

		try {
			Map<String, String> statusMessage = changePasswordService.changePassword(changePasswordCommand);

			if (statusMessage.containsKey("EN")) {
				view = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
				model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? statusMessage.get("EN") : statusMessage.get("AR"));
			}
		} catch (Exception e) {
			logger.error("Failed | " + e.getMessage());
		}

		model.addAttribute(PAGE_LABEL, "label.change.password");
		model.addAttribute(ISMOBILE, "false");
		model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "false");
		return view;
	}

}
