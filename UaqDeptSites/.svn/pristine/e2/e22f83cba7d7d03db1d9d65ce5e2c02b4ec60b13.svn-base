/**
 * 
 */
package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.PARAM_LANGUAGE_CODE;
import static com.uaq.common.ApplicationConstants.USER_UPDATE_STATUS;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.common.ViewPath;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.PageMetadataVO;

/**
 * @author TACME
 *
 */
@Controller
public class ServiceErrorPageController extends BaseController{
	
	public static transient UAQLogger logger = new UAQLogger(ServiceErrorPageController.class);
	
	@RequestMapping(value=ViewPath.SERVICES_ERROR_PAGE,method = RequestMethod.GET)
	public String handleThankYouPage(HttpServletRequest request, ModelMap model){
		super.handleRequest(request, model);
		
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.eservice.error.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.eservice.error.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.eservice.error.pagekeyword", null, locale));
		
		String isMobile = request.getParameter("isMobile");
		logger.debug("isMobile="+isMobile);
		
		
		
		
		model.addAttribute(USER_UPDATE_STATUS,request.getParameter(USER_UPDATE_STATUS));
		
		
		String view ="";
		if(isMobile.equalsIgnoreCase("true")){
			 view = "service.errorpage.mobile";
		}else{
			view = "service.errorpage.portal";
		}
		model.addAttribute("pageMetadata", pageMetadataVO);
		return view;
	}

}
