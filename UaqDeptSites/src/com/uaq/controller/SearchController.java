package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.SearchCommand;
import com.uaq.common.ViewPath;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.util.MetaDataUtil;
import com.uaq.util.SessionUtil;
import com.uaq.util.ValidationUtil;
import com.uaq.vo.SearchResponseVO;

/**
 * @author raheem
 * 
 *         Controller for News Page
 */
@Controller
public class SearchController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(SearchController.class);

	@Autowired
	@Qualifier("searchService")
	private BaseService<SearchCommand, SearchResponseVO> searchService;

	/**
	 * Get the News Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = ViewPath.SEARCH, method = RequestMethod.GET)
	public String handleRequest(@ModelAttribute("site") String site, HttpServletRequest request, BindingResult result, ModelMap modelMap) {

		logger.enter("Search | handle Request");

		String view = "";
		if (site.equals("uaq")) {
			view = "search";
			super.handleRequest(request, modelMap);
		} else {
			view = "sites.search";
			super.handleDepartmentRequest(request, modelMap, site);
		}

		SearchCommand searchCommand = new SearchCommand();
		SearchResponseVO searchResponseVO = new SearchResponseVO();

		String languageCode = request.getParameter("languageCode");
		Integer currentPage = 1;

		searchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		searchCommand.setSite(site);
		searchCommand.setLanguage(languageCode);

		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
			SessionUtil.updateSearchCommandWithFilters(request, searchCommand);
		} else {
			SessionUtil.removeSearchFilters(request);
		}
		searchCommand.setCurrentPage(currentPage);
		validateSearch(searchCommand, result);

		try {

			if (!result.hasErrors()) {
				searchResponseVO = searchService.execute(searchCommand);
			}

		} catch (UAQException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("searchCommand", searchCommand);
		modelMap.addAttribute("searchResponse", searchResponseVO);

		logger.exit("Search| handle Request");

		return view;
	}

	@RequestMapping(value = ViewPath.SEARCH, method = RequestMethod.POST)
	public String handleRequest(@ModelAttribute("site") String site, @ModelAttribute("searchCommand") SearchCommand searchCommand, HttpServletRequest request, BindingResult result, ModelMap modelMap) {

		logger.enter("Search | handle Request");

		String view = "";
		if (site.equals("uaq")) {
			view = "search";
			super.handleRequest(request, modelMap);
		} else {
			view = "sites.search";
			super.handleDepartmentRequest(request, modelMap, site);
		}
		Integer currentPage = 1;

		SearchResponseVO searchResponseVO = new SearchResponseVO();

		String languageCode = request.getParameter("languageCode");

		searchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		searchCommand.setSite(site);

		validateSearch(searchCommand, result);
		searchCommand.setLanguage(languageCode);
		searchCommand.setStartRow(0);
		searchCommand.setCurrentPage(currentPage);

		try {
			if (!result.hasErrors()) {
				SessionUtil.removeSearchFilters(request);
				SessionUtil.saveSearchFilters(request, searchCommand);
				searchResponseVO = searchService.execute(searchCommand);
			}

		} catch (UAQException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("pageMetadata", MetaDataUtil.getPageMetaData(view, languageCode, messageSource));
		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("searchCommand", searchCommand);

		logger.exit("Search| handle Request");

		return view;
	}

	/**
	 * validates the search form
	 * 
	 * @param searchCommand
	 * @param errors
	 */
	private void validateSearch(SearchCommand searchCommand, Errors errors) {

		try {
			if (null != searchCommand.getKeyword() && searchCommand.getKeyword().length() > 0 && !ValidationUtil.validateArabicAndEnglish(searchCommand.getKeyword())) {
				errors.rejectValue("keyword", "keyword.incorrect", "Invalid Keyword.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

}
