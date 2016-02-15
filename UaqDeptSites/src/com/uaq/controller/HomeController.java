package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.common.ViewPath;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.vo.HomeVO;

/**
 * @author nsharma
 * 
 *         Controller for Home Page for
 * 
 *         a) Portal
 * 
 *         b) Department Home Page
 */
@Controller
public class HomeController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(HomeController.class);

	@Autowired
	@Qualifier("homeService")
	private BaseService<HomeVO, HomeVO> homeService;

	/**
	 * Get the Home Page Details
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = ViewPath.PORTAL_HOME, method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request, ModelMap model) {
		logger.enter("Home Page for UAQ Portal | handle Request");
		String homeId = super.handleRequest(request, model);
		HomeVO homeVO = new HomeVO();
		homeVO.setAssetId(homeId);
		homeVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		homeVO.setLanguage(request.getParameter("languageCode"));
		logger.debug("Multiticket is : " + homeVO.getTicketId());

		try {
			homeVO = homeService.execute(homeVO);
		} catch (UAQException e) {
			logger.error("Error while getting the Home Page for UAQ" + e.getMessage());
		}

		// This is get footer disclaimer in all pages.This should handle in
		// BaseController
		HttpSession session = request.getSession();
		session.setAttribute("homeVO", homeVO);

		model.addAttribute("homeVO", homeVO);
		model.addAttribute("pageMetadata", homeVO.getPageMetadataVO());
		logger.exit(ViewPath.PORTAL_HOME + " | handle Request");
		return "global.home";
	}

	/**
	 * Get the News Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = ViewPath.SITES_HOME_PAGE, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("sites") String sites, HttpServletRequest request, ModelMap model) {
		logger.enter("Home Page for " + sites + "| handle Request");
		super.handleDepartmentRequest(request, model, sites);
		HomeVO homeVO = (HomeVO) model.get("homeVO");
		model.addAttribute("pageMetadata", homeVO.getPageMetadataVO());
		logger.exit("Home Page for " + sites + "| handle Request");
		return "department.home";
	}

}
