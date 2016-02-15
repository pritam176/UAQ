package com.uaq.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.common.ViewPath;
import com.uaq.logger.UAQLogger;

@Controller
public class ComingSoonController extends BaseController {
	public static transient UAQLogger logger = new UAQLogger(ComingSoonController.class);

	/**
	 * Get the Awards Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = ViewPath.COMING_SOON, method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Coming Soon | handle Request");

		String view = "comingsoon";
		super.handleRequest(request, modelMap);

		logger.exit("Coming Soon | handle Request");
		return view;
	}

}
