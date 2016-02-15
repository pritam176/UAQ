package com.uaq.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.SearchCommand;
import com.uaq.common.ViewPath;
import com.uaq.logger.UAQLogger;

@Controller
public class PageNotFoundController extends BaseController {
	public static transient UAQLogger logger = new UAQLogger(PageNotFoundController.class);

	/**
	 * Get the Awards Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = ViewPath.PAGE_NOT_FOUND, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, @ModelAttribute("awardsSearchCommand") SearchCommand awardsSearchCommand, HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get Awards Detail | handle Request");

		String view = "page.not.found";
		if (!site.equals("uaq")) {
			view = "sites.page.not.found";
			super.handleDepartmentRequest(request, modelMap, site);
		} else {
			super.handleRequest(request, modelMap);
		}

		logger.exit("Landing Page | handle Request");

		return view;
	}

}
