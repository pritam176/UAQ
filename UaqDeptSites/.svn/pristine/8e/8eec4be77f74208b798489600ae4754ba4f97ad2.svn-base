package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.FuneralCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.FuneralService;
import com.uaq.util.MetaDataUtil;

/**
 * This controller is used for funeral page.
 * 
 * @author mraheem
 * 
 */
@Controller
public class FuneralController extends BaseController {

	private static transient UAQLogger logger = new UAQLogger(FuneralController.class);

	@Autowired
	@Qualifier("funeralService")
	private FuneralService service;

	@Autowired
	private MessageSource messageSource;

	/**
	 * Initializes the funeral form
	 * 
	 * @param model
	 *            implicit object.
	 * @return view name.
	 */
	@RequestMapping(value = ViewPath.FUNERAL, method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request, ModelMap model) {

		logger.enter("handle Request");

		String view = "";
		
		super.handleRequest(request, model);
		
		String languageCode = request.getParameter("languageCode");
		
		Boolean logStatus_session = (Boolean) request.getSession().getAttribute("logStatus");
		String userName_session = (String) request.getSession().getAttribute("userName");
		if (null != logStatus_session && logStatus_session && null != userName_session) {
			view = "funeral";
		} else {
			view = "redirect:" + PropertiesUtil.getProperty("globalUrl") + "/" + languageCode + "/funeralLogin.html";
		}
		
		model.addAttribute("pageMetadata", MetaDataUtil.getPageMetaData("funeral", languageCode, messageSource));
		model.addAttribute("funeralCommand", new FuneralCommand());
		model.addAttribute("ignore", "true");
		
		return view;
	}	

	/**
	 * Processes the funeral form submitted by the user.
	 * 
	 * @param funeralCommand
	 *            object for holding the funeral form data.
	 * @param result
	 *            object holding errors.
	 * @param request
	 *            implicit http request object.
	 * @return view name.
	 */
	@RequestMapping(value = ViewPath.FUNERAL, method = RequestMethod.POST)
	public String processForm(@ModelAttribute("funeralCommand") FuneralCommand funeralCommand, BindingResult result, HttpServletRequest request, ModelMap model) {

		logger.debug("Inside funeral Controller | Entry");

		String view = "";
		
		view = "funeral";
		super.handleRequest(request, model);
		 
		String languageCode = request.getParameter("languageCode");
		String ticket = (String) request.getSession().getAttribute(SESSION_TICKET);		
			
		funeralCommand.setSessionId(ticket);
		funeralCommand.setSite("uaq");
		
		funeralCommand.setName(funeralCommand.getDeparteeName());

		try {
			funeralCommand.setLanguageCode("ar");
			service.execute(funeralCommand); // arabic asset
			
			funeralCommand.setLanguageCode("en");
			service.execute(funeralCommand); // english clone
			
			view = "funeralConfirmation";
		} catch (UAQException e) {
			logger.error(e.getMessage());
		}			

		String pageName = "funeral";
		model.addAttribute("pageMetadata", MetaDataUtil.getPageMetaData(pageName, languageCode, messageSource));
		model.addAttribute("funeralCommand", funeralCommand);
		model.addAttribute("ignore", "true");
		
		return view;
	}		
}