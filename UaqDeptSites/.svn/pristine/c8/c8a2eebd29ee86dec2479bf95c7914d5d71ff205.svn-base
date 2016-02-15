package com.uaq.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uaq.command.SearchCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.service.SmileyService;

/**
 * @author raheem
 * 
 *         Controller for News Page
 */
@Controller
public class SmileyController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(SmileyController.class);

	@Autowired
	@Qualifier("smileyService")
	private BaseService<SearchCommand, String> smileyService;

	/**
	 * Get the News Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = { ViewPath.SMILEY }, method = RequestMethod.GET)
	public @ResponseBody 
	String handleRequest(@PathVariable("site") String site, HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Smiley | handle Request");
		
		JSONObject jsonResult = new JSONObject();		
		
		boolean isDuplicate = Boolean.FALSE;
		SearchCommand searchCommand = new SearchCommand();
		searchCommand.setTicketId(request.getSession().getId());
		searchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		
		try{
			
			isDuplicate = ((SmileyService)smileyService).isDuplicate(searchCommand);
			
		} catch (Exception e) {
			logger.error(e.getMessage());			
		}
		
		jsonResult.element("status", isDuplicate == false ? "success" : "fail");
		
		logger.exit("Search| handle Request");

		return jsonResult.toString();
	}

	@RequestMapping(value = { ViewPath.SMILEY }, method = RequestMethod.POST)
	public @ResponseBody
	String saveSmileyAjax(@PathVariable("site") String site, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {

		logger.enter("Smiley | handle Request");

		SearchCommand searchCommand = new SearchCommand();
		String status = "fail";
		Locale locale = new Locale(request.getParameter("languageCode"));
		String message =  messageSource.getMessage("smiley.success", null, locale);
		JSONObject jsonResult = new JSONObject();
		
		String languageCode = request.getParameter("languageCode");
		searchCommand.setTicketId(request.getSession().getId());
		searchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		searchCommand.setLanguage(languageCode);
		searchCommand.setKeyword(request.getParameter("question"));
		searchCommand.setOther(request.getParameter("answer"));
		
		String result = null;

		try {

			result = smileyService.execute(searchCommand);
			
			if(result != null && !result.equals("") && result.contains("unique constraint")){
				message =  messageSource.getMessage("duplicate.session", null, locale);
			} else {
				status = "success";
			}

		} catch (UAQException e) {
			logger.error(e.getMessage());			
			message =  messageSource.getMessage("smiley.fail", null, locale);
		} catch (Exception e) {
			logger.error(e.getMessage());			
			message =  messageSource.getMessage("smiley.fail", null, locale);
		}

		jsonResult.element("status", status);
		jsonResult.element("message", message);

		logger.exit("Search| handle Request");

		return jsonResult.toString();

	}

}
