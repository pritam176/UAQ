package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fatwire.wem.sso.SSOException;
import com.uaq.command.SearchCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.service.GenericPageService;
import com.uaq.service.NewsService;
import com.uaq.util.SessionUtil;
import com.uaq.util.StringUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.util.ValidationUtil;
import com.uaq.vo.CategoryVO;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.LandingPageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.NewsVO;
import com.uaq.vo.SearchResponseVO;

/**
 * @author raheem
 * 
 *         Controller for News Page
 */
@Controller
public class NewsController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(NewsController.class);

	@Autowired
	@Qualifier("newsService")
	private BaseService<NavigationVO, SearchResponseVO> newsService;

	@Autowired
	@Qualifier("landingPageService")
	BaseService<LandingPageVO, LandingPageVO> landingPageService;
	
	@Autowired
	@Qualifier("genericPageService")
	BaseService<NavigationVO, GenericPageVO> genericPageService;

	/**
	 * Get the News Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = { ViewPath.NEWS_LISTING_SITES ,ViewPath.NEWS_AND_ANNOUNCEMENT_LISTING_SITES}, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site,@PathVariable("landing")String landing, @ModelAttribute("newsSearchCommand") SearchCommand newsSearchCommand,
			HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get News Detail | handle Request");
		
		
		String htmlName= StringUtil.getString(request.getServletPath());
		htmlName=htmlName.split("/")[1];
		String pageName="";

		String view = "news.list";
		if (!site.equals("uaq")) {
			view = "sites.news.list";
			pageName="News & Announcements";
			super.handleDepartmentRequest(request, modelMap, site);

		} else {
			pageName="News";
			super.handleRequest(request, modelMap);
		}

		try {
			
			List<CategoryVO> categories = null;
			Map<String, String> categoriesMap = null;
			((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
			Integer currentPage = 1;

			NewsVO newsVO = new NewsVO();
			SearchResponseVO searchResponseVO = new SearchResponseVO();

			String languageCode = request.getParameter("languageCode");

			newsVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
			newsVO.setSite(site);
			newsSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
			newsSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
			newsSearchCommand.setLanguage(languageCode);

			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
				currentPage = Integer.valueOf(request.getParameter("currentPage"));
				SessionUtil.updateSearchCommandWithFilters(request, newsSearchCommand);
			} else {
				SessionUtil.removeSearchFilters(request);
			}
			newsSearchCommand.setCurrentPage(currentPage);
			
			NavigationVO navigationVO = new NavigationVO();
			navigationVO.setSite(site);
			navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
			navigationVO.setName(UrlTransliterationUtil.getString(pageName));
			navigationVO.setLanguage(languageCode);
			GenericPageVO genericPageVO = null;
			
			// load page metaData
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);

			searchResponseVO = ((NewsService) newsService).getNewsList(newsSearchCommand, site);

			categories = ((NewsService) newsService).getNewsCategories(newsVO);
			categoriesMap = getMapFromCategoryList(categories, languageCode);

			modelMap.addAttribute("searchCommand", newsSearchCommand);
			modelMap.addAttribute("searchResponse", searchResponseVO);
			modelMap.addAttribute("categoriesMap", categoriesMap);
			modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
			modelMap.addAttribute("pageName", pageName.replaceAll("\\s",""));

		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		logger.exit("Landing Page | handle Request");

		return view;
	}

	@RequestMapping(value = { ViewPath.NEWS_LISTING }, method = RequestMethod.GET)
	public String handleNewsList(@PathVariable("site") String site, @ModelAttribute("newsSearchCommand") SearchCommand newsSearchCommand, HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get News Detail | handle Request");
		
		String pageName = "News";

		String view = "news.list";
		if (!site.equals("uaq")) {
			view = "sites.news.list";
			super.handleDepartmentRequest(request, modelMap, site);

		} else {
			super.handleRequest(request, modelMap);
		}

		try {

			List<CategoryVO> categories = null;
			Map<String, String> categoriesMap = null;
			Integer currentPage = 1;

			NewsVO newsVO = new NewsVO();
			SearchResponseVO searchResponseVO = new SearchResponseVO();

			String languageCode = request.getParameter("languageCode");

			newsVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
			newsVO.setSite(site);
			newsSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
			newsSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
			newsSearchCommand.setLanguage(languageCode);
			
			NavigationVO navigationVO = new NavigationVO();
			navigationVO.setSite(site);
			navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
			navigationVO.setName(UrlTransliterationUtil.getString(pageName));
			navigationVO.setLanguage(languageCode);
			GenericPageVO genericPageVO = null;
			
			// load page metaData
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);

			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
				currentPage = Integer.valueOf(request.getParameter("currentPage"));
				SessionUtil.updateSearchCommandWithFilters(request, newsSearchCommand);
			} else {
				SessionUtil.removeSearchFilters(request);
			}
			newsSearchCommand.setCurrentPage(currentPage);

			searchResponseVO = ((NewsService) newsService).getNewsList(newsSearchCommand, site);

			categories = ((NewsService) newsService).getNewsCategories(newsVO);
			categoriesMap = getMapFromCategoryList(categories, languageCode);

			modelMap.addAttribute("searchCommand", newsSearchCommand);
			modelMap.addAttribute("searchResponse", searchResponseVO);
			modelMap.addAttribute("categoriesMap", categoriesMap);
			modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
			modelMap.addAttribute("pageName", pageName.replaceAll("\\s",""));

		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		logger.exit("Landing Page | handle Request");

		return view;
	}

	/**
	 * To fetch the category name based on the language selected.
	 * 
	 */

	private Map<String, String> getMapFromCategoryList(List<CategoryVO> categoryList, String languageCode) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (categoryList != null && categoryList.size() > 0) {
			for (CategoryVO category : categoryList) {
				if (languageCode.equals("en")) {
					resultMap.put(category.getCategoryNameEnglish(), category.getCategoryNameEnglish());
				} else {
					resultMap.put(category.getCategoryNameArabic(), category.getCategoryNameArabic());
				}
			}
		}
		return resultMap;
	}

	@RequestMapping(value = { ViewPath.NEWS_LISTING, ViewPath.NEWS_LISTING_SITES,ViewPath.NEWS_AND_ANNOUNCEMENT_LISTING_SITES }, method = RequestMethod.POST)
	public String processSearch(@PathVariable("site") String site, @ModelAttribute("newsSearchCommand") SearchCommand newsSearchCommand, BindingResult result,
			ModelMap modelMap, HttpServletRequest request) {

		logger.enter("News Listing processSearch()");
		String landing="";
		String pageName = "";
		String source = StringUtil.getString(request.getServletPath());
		
		if (source.contains("/")) {
			landing = source.split("/")[0];
		}
		String view = "news.list";
		if (!site.equals("uaq")) {
			view = "sites.news.list";
			pageName = "News & Announcements";
			super.handleDepartmentRequest(request, modelMap, site);
		} else {
			pageName = "News";
			super.handleRequest(request, modelMap);
		}
		List<CategoryVO> categories = null;
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		NewsVO newsVO = new NewsVO();

		Integer currentPage = 1;

		Map<String, String> categoriesMap = null;

		validateNews(newsSearchCommand, result);

		String languageCode = request.getParameter("languageCode");
		newsSearchCommand.setLanguage(languageCode);
		newsSearchCommand.setStartRow(0);
		newsSearchCommand.setCurrentPage(currentPage);

		newsVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		newsVO.setSite(site);
		newsSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		newsSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setLanguage(languageCode);
		GenericPageVO genericPageVO = null;

		try {
			if (!result.hasErrors()) {
				SessionUtil.removeSearchFilters(request);
				SessionUtil.saveSearchFilters(request, newsSearchCommand);
				searchResponseVO = ((NewsService) newsService).getNewsList(newsSearchCommand, site);
			}
			categories = ((NewsService) newsService).getNewsCategories(newsVO);
			categoriesMap = getMapFromCategoryList(categories, languageCode);
			
			// load page metaData
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);
						
		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("categories", categories);
		// modelMap.addAttribute("pageMetadata",

		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("searchCommand", newsSearchCommand);
		modelMap.addAttribute("categoriesMap", categoriesMap);
		modelMap.addAttribute("landing",landing);
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
		modelMap.addAttribute("pageName", pageName.replaceAll("\\s",""));

		logger.exit("News Listing processSearch()");

		return view;
	}

	@RequestMapping(value = { ViewPath.NEWS_DETAIL, ViewPath.NEWS_DETAIL_SITES }, method = RequestMethod.GET)
	public String newsDetail(@PathVariable("site") String site, @PathVariable("name") String name, HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get News Detail | handle Request");

		String landing="";
		String pageName = "";
		String source = StringUtil.getString(request.getServletPath());
		
		if (source.contains("/")) {
			landing = source.split("/")[0];
		}
		
		String view = "news.detail";
		if (!site.equals("uaq")) {
			view = "sites.news.detail";
			pageName = "News & Announcements";
			super.handleDepartmentRequest(request, modelMap, site);
		} else {
			super.handleRequest(request, modelMap);
			pageName = "News";
		}

		NewsVO newsVO = null;
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(name));
		navigationVO.setLanguage(request.getParameter("languageCode"));
		GenericPageVO genericPageVO = null;

		try {
			newsVO = ((NewsService) newsService).getNewsDetails(navigationVO);
			
			// load page metaData
			// we have to do create navigationVO again to avoid cache
			navigationVO = null;
			navigationVO = new NavigationVO();
			navigationVO.setSite(site);
			navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
			navigationVO.setName(UrlTransliterationUtil.getString(pageName));
			navigationVO.setLanguage(request.getParameter("languageCode"));
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);
						
		} catch (SSOException e) {
			logger.error("Error while getting News Details Page" + e.getMessage());
		} catch (UAQException e) {
			logger.error("Error while getting News Details Page" + e.getMessage());
		}

		modelMap.addAttribute("newsVO", newsVO);
		modelMap.addAttribute("landing",landing);
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
		modelMap.addAttribute("pageName", pageName.replaceAll("\\s",""));

		logger.exit("Get News Detail | handle Request");

		return view;
	}

	/**
	 * validates filters
	 * 
	 * @param newsSearchCommand
	 * @param errors
	 */
	private void validateNews(SearchCommand newsSearchCommand, Errors errors) {

		try {
			if (newsSearchCommand.getKeyword().length() > 0 && !ValidationUtil.validateArabicAndEnglish(newsSearchCommand.getKeyword())) {
				errors.rejectValue("keyword", "keyword.incorrect", "Invalid Keyword.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

}
