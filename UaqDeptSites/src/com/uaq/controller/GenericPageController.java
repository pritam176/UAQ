package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import javax.servlet.http.HttpServletRequest;

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
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.NavigationVO;

@Controller
public class GenericPageController extends BaseController {

	@Autowired
	@Qualifier("genericPageService")
	BaseService<NavigationVO, GenericPageVO> genericPageService;

	public static transient UAQLogger logger = new UAQLogger(GenericPageController.class);

	@RequestMapping(value = {ViewPath.GENERIC_CONTENT_PAGE}, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, @PathVariable("pageName") String pageName, HttpServletRequest request, ModelMap model) {
		logger.enter("Generic Page | handle Request");

		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setLanguage(request.getParameter("languageCode"));
		GenericPageVO genericPageVO = null;

		String view = null;
		if (site.equals("uaq")) {
			view = "generic.page";
			super.handleRequest(request, model);
			try {
				genericPageVO = genericPageService.execute(navigationVO);
			} catch (UAQException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
			model.addAttribute("genericPageVO", genericPageVO);
		} else {
			view = "sites.generic.page";
			super.handleDepartmentRequest(request, model, site);
			try {
				genericPageVO = genericPageService.execute(navigationVO);
			} catch (UAQException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("genericPageVO", genericPageVO);
			model.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
		}
		return view;
	}
	
	@RequestMapping(value = ViewPath.CONTACT_US, method = RequestMethod.GET)
	public String handleRequestSpecial(@PathVariable("site") String site, HttpServletRequest request, ModelMap model) {
		return handleRequest(site, "ContactUs", request, model);
	}
}
