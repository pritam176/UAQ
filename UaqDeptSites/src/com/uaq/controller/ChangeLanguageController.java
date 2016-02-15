/**
 * 
 */
package com.uaq.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fatwire.wem.sso.SSOException;
import com.uaq.common.PropertiesUtil;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.NavigationVO;

/**
 * This controller is the first controller to be invoked when user accesses the
 * base url.
 */
@Controller
@RequestMapping(value = "/{site}/changeLanguage.html")
public class ChangeLanguageController {

	@Autowired
	@Qualifier("changeLanguageService")
	BaseService<NavigationVO, NavigationVO> changeLanguageService;

	public static transient UAQLogger logger = new UAQLogger(ChangeLanguageController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String processForm(@PathVariable("site") String site, HttpServletRequest request, ModelMap model) throws SSOException {
		logger.debug("Inside Login Controller | Entry");
		String sourceURL = request.getParameter("fromPage");
		String language = request.getParameter("languageCode");
		String destinationURL = null;
		String sitesURL = "";
		
		//TODO:will revisit this later
		if(sourceURL.equalsIgnoreCase("paymentReview.html")){
			 sitesURL = PropertiesUtil.getProperty("globalUrl");
		}else{
			sitesURL = PropertiesUtil.getProperty(site + ".url");
		}
		 
		
		String  paramList="?";
		 Enumeration<String> parameterNames = request.getParameterNames();
		 while(parameterNames.hasMoreElements()){
			 String paramName = (String)parameterNames.nextElement();
			 //for My request Action on Button language change
			 if(paramName.equals("serviceId") || paramName.equals("requestNo") || paramName.equals("statusId") || paramName.equals("typeOfUser") 
					 || paramName.equals("letter") || paramName.equals("id") || paramName.equals("feeId") || paramName.equals("customerId")
					 || paramName.equals("customerName") || paramName.equals("languageCode") || paramName.equals("returnUrl") || paramName.equals("isMobile")){
			 paramList+="&"+paramName+"="+request.getParameter(paramName);
			 }
			}
		

		if (language.equals("en")) {
			language = "ar";

		} else if (language.equals("ar")) {
			language = "en";

		}

		if (null != request.getParameter("ignore") && request.getParameter("ignore").equals("true")) {
			destinationURL = sitesURL + "/" + language + "/" + sourceURL;

		} else {
			NavigationVO query = new NavigationVO();
			NavigationVO response = null;
			query.setName(getPageName(sourceURL));
			query.setSite(site);
			if (sourceURL.contains("/") && sourceURL.split("/")[0].equals("services")) {
				String siteTemp = sourceURL.split("/")[1];
				if (siteTemp.equals("egd") || siteTemp.equals("lap") || siteTemp.equals("ded") || siteTemp.equals("dec") || siteTemp.equals("aah") || siteTemp.equals("mun") || siteTemp.equals("pas")
						|| siteTemp.equals("pws") || siteTemp.equals("dfm") || siteTemp.equals("ica") || siteTemp.equals("dfa") || siteTemp.equals("pcf") || siteTemp.equals("dfa")) {
					query.setSite(sourceURL.split("/")[1]);
				}

			}

			query.setLanguage(language);
			try {
				response = changeLanguageService.execute(query);
				if (null != response) {
					destinationURL = sitesURL + "/" + language + "/" + sourceURL;
				} else {
					destinationURL = sitesURL + "/" + language + "/" + "home.html";
				}

			} catch (UAQException e) {
				logger.error("Error while change Language " + e.getMessage());
			}
		}
		return "redirect:" + destinationURL+paramList;
	}

	private String getPageName(String pageName) {
		String transliteratedPageName = null;
		if (pageName.contains("/")) {
			String[] pages = pageName.split("/");
			String name = pages[pages.length - 1];
			logger.debug("Page Name" + name);
			if (name.contains(".")) {

				pageName = name.split("\\.")[0];
				transliteratedPageName = UrlTransliterationUtil.getString(pageName);

			}
		} else {
			if (pageName.contains(".")) {
				pageName = pageName.split("\\.")[0];
				transliteratedPageName = UrlTransliterationUtil.getString(pageName);

			}

		}
		return transliteratedPageName;
	}

}
