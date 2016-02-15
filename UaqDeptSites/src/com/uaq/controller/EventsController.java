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

import com.fatwire.wem.sso.SSOException;
import com.uaq.command.SearchCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.service.EventsService;
import com.uaq.service.GenericPageService;
import com.uaq.util.SessionUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.util.ValidationUtil;
import com.uaq.vo.CategoryVO;
import com.uaq.vo.DetailPageVO;
import com.uaq.vo.EventsSearchResponseVO;
import com.uaq.vo.EventsVO;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.SearchResponseVO;

/**
 * @author raheem
 * 
 *         Controller for Events Page
 */
@Controller
public class EventsController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(EventsController.class);

	@Autowired
	@Qualifier("eventsService")
	private BaseService<EventsVO, EventsVO> eventsService;
	
	@Autowired
	@Qualifier("detailPageService")
	BaseService<DetailPageVO, DetailPageVO> detailPageService;
	
	@Autowired
	@Qualifier("genericPageService")
	BaseService<NavigationVO, GenericPageVO> genericPageService;

	/**
	 * Get the Events Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = { ViewPath.EVENTS_LISTING, ViewPath.EVENTS_MEDIAROOM ,ViewPath.EVENTS_SITES_LISTING}, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site,@ModelAttribute("eventsSearchCommand") SearchCommand eventsSearchCommand, HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get Events Detail | handle Request");
		String view = null;
		String landing = null;
		String parent = null;
		String pageName = "Events";
		
		if (site.equals("uaq")) {
			view = "events.list";
			super.handleRequest(request, modelMap);
		} else {
			view = "sites.events.list";
			super.handleDepartmentRequest(request, modelMap, site);
		}
		
		String source = (String) modelMap.get("source");
		if (source.contains("/")) {
			landing = source.split("/")[1];
			landing = landing.split("\\.")[0];
			parent = source.split("/")[0];
		}

		List<CategoryVO> categories = null;
		Integer currentPage = 1;

		EventsVO eventsVO = new EventsVO();
		SearchResponseVO searchResponseVO = new SearchResponseVO();

		eventsVO.setSite(site);
		String languageCode = request.getParameter("languageCode");
		eventsSearchCommand.setLanguage(languageCode);
		eventsVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		eventsVO.setLanguage(languageCode);
		eventsSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		eventsSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		Map<String, String> categoryMap = null;

		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
			SessionUtil.updateSearchCommandWithFilters(request, eventsSearchCommand);
		} else {
			SessionUtil.removeSearchFilters(request);
		}
		eventsSearchCommand.setCurrentPage(currentPage);
		
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setLanguage(languageCode);
		GenericPageVO genericPageVO = null;

		try {

			searchResponseVO = ((EventsService) eventsService).getEventsList(eventsSearchCommand, site);

			categories = ((EventsService) eventsService).getEventsCategories(eventsVO);

			categoryMap = getCategoryMap(categories, languageCode);
			
			// load page metaData
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);

		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("languageCode", languageCode);
		modelMap.addAttribute("searchCommand", eventsSearchCommand);
		modelMap.addAttribute("categoryMap", categoryMap);
		
		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("landing", landing);
		modelMap.addAttribute("parent", parent);
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());

		logger.exit("Landing Page | handle Request");

		return view;
	}
	
	/**
	 * 
	 * @param categories
	 * @param languageCode
	 * @return
	 */
	private Map<String, String> getCategoryMap(List<CategoryVO> categories, String languageCode) {
		Map<String, String> categoryMap = new HashMap<String, String>();
		if (categories.size() > 0 && categories != null) {
			for (CategoryVO categorie : categories) {
				if (languageCode.equals("en")) {
					categoryMap.put(categorie.getCategoryNameEnglish(), categorie.getCategoryNameEnglish());
				}
				if (languageCode.equals("ar")) {
					categoryMap.put(categorie.getCategoryNameArabic(), categorie.getCategoryNameArabic());
				}
			}
		}
		return categoryMap;
	}
	
	/**
	 * 
	 * @param site
	 * @param eventsSearchCommand
	 * @param result
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { ViewPath.EVENTS_LISTING, ViewPath.EVENTS_MEDIAROOM,ViewPath.EVENTS_SITES_LISTING}, method = RequestMethod.POST)
	public String processSearch(@PathVariable("site") String site, @ModelAttribute("eventsSearchCommand") SearchCommand eventsSearchCommand, BindingResult result, ModelMap modelMap,
			HttpServletRequest request) {

		logger.enter("Events Listing processSearch()");

		String view = "";
		String landing=null;
		String landingForPortal=null;
		String parent = null;
		String pageName = "Events";

		Integer currentPage = 1;
		List<CategoryVO> categories = null;
		EventsVO eventsVO = new EventsVO();
		List<EventsVO> eventsList = null;
		Map<String, String> categoryMap = null;
		SearchResponseVO searchResponseVO = new EventsSearchResponseVO();

		if (site.equals("uaq")) {
			view = "events.list";
			super.handleRequest(request, modelMap);
		} else {
			view = "sites.events.list";
			super.handleDepartmentRequest(request, modelMap, site);
		}
		
		String source = (String) modelMap.get("source");
		if (source.contains("/")) {
			landing = source.split("/")[1];
			landing = landing.split("\\.")[0];
			parent = source.split("/")[0];
			landingForPortal=source.split("/")[1];
		}
		
		if(site.equals("uaq")){
			landing=landingForPortal;
		}
		
		validateEvents(eventsSearchCommand, result);

		String languageCode = request.getParameter("languageCode");
		eventsSearchCommand.setLanguage(languageCode);
		eventsVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		eventsVO.setSite(site);
		eventsSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		eventsSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));

		eventsSearchCommand.setStartRow(0);
		eventsSearchCommand.setCurrentPage(currentPage);
		
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setLanguage(languageCode);
		GenericPageVO genericPageVO = null;
		
		try {
			if (!result.hasErrors()) {
				SessionUtil.removeSearchFilters(request);
				SessionUtil.saveSearchFilters(request, eventsSearchCommand);
				searchResponseVO = ((EventsService) eventsService).getEventsList(eventsSearchCommand, site);
			}
			categories = ((EventsService) eventsService).getEventsCategories(eventsVO);
			categoryMap = getCategoryMap(categories, languageCode);
			
			// load page metaData
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);
			
		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}
		modelMap.addAttribute("languageCode", languageCode);
		modelMap.addAttribute("categories", categories);
		modelMap.addAttribute("categoryMap", categoryMap);
		modelMap.addAttribute("eventsList", eventsList);
		modelMap.addAttribute("searchCommand", eventsSearchCommand);
		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("landing", landing);
		modelMap.addAttribute("parent", parent);	
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());

		logger.exit("Events Listing processSearch()");

		return view;
	}
	
	/**
	 * Event Detail Page for Portal and Department sites
	 * @param site
	 * @param name
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = {ViewPath.EVENTS_DETAIL, ViewPath.EVENTS_DETAIL_SITES,ViewPath.EVENTS_MEDIAROOM_DETAILS}, method = { RequestMethod.GET })
	public String eventsDetail(@PathVariable("site") String site, @PathVariable("name") String name, HttpServletRequest request, ModelMap modelMap) {
		
		String sourceURL = request.getParameter("parentPage");
		if(sourceURL!=null && sourceURL.trim().length()>0){
			modelMap.put("sourceURL", sourceURL);
		}

		logger.enter("Get Events Detail | handle Request");
		String view = null;
		String landing=null;
		String activeNav=null;
		String pageName = "Events";

		if (site.equals("uaq")) {
			view = "event.detail";
			super.handleRequest(request, modelMap);
		} else {
			view = "sites.events.detail";
			super.handleDepartmentRequest(request, modelMap, site);
		}		

		String source = (String) modelMap.get("source");
		if (source.contains("/")) {
			landing = source.split("/")[0];
			activeNav=source.split("/")[0]+"/"+source.split("/")[1]+".html";
		}

		EventsVO eventVO = null;
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(name));
		navigationVO.setLanguage(request.getParameter("languageCode"));
		GenericPageVO genericPageVO = null;

		try {
			eventVO = ((EventsService) eventsService).getEventsDetails(navigationVO);
			
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
			logger.error("Error while getting Events Details Page" + e.getMessage());
		} catch (UAQException e) {
			logger.error("Error while getting Events Details Page" + e.getMessage());
		}

		modelMap.addAttribute("eventVO", eventVO);
		modelMap.addAttribute("landing", landing);
		modelMap.addAttribute("activeNav",activeNav);
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());

		logger.exit("Get Events Detail | handle Request");

		return view;
	}

	/**
	 * validates filters
	 * 
	 * @param eventsSearchCommand
	 * @param errors
	 */
	private void validateEvents(SearchCommand eventsSearchCommand, Errors errors) {

		try {
			if (eventsSearchCommand.getKeyword().length() > 0 && !ValidationUtil.validateArabicAndEnglish(eventsSearchCommand.getKeyword())) {
				errors.rejectValue("keyword", "keyword.incorrect", "Invalid Keyword.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}
