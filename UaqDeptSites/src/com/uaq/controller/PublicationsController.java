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
import com.uaq.service.GenericPageService;
import com.uaq.service.PublicationsService;
import com.uaq.util.SessionUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.util.ValidationUtil;
import com.uaq.vo.CategoryVO;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.PublicationsVO;
import com.uaq.vo.SearchResponseVO;

/**
 * @author anair
 * 
 *         Controller for Publications Page
 */
@Controller
public class PublicationsController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(PublicationsController.class);

	private static final String SEARCH_FILTERS_OBJECT = "search_filters_object";

	@Autowired
	@Qualifier("publicationsService")
	private BaseService<PublicationsVO, PublicationsVO> publicationsService;
	
	@Autowired
	@Qualifier("genericPageService")
	BaseService<NavigationVO, GenericPageVO> genericPageService;

	/**
	 * Get the publications Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = ViewPath.PUBLICATION_LISTING_SITES , method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, @PathVariable("landing") String landing,@ModelAttribute("publicationsSearchCommand") SearchCommand publicationsSearchCommand, HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get Publicaion | handle Request");

		String view = "sites.publications.list";
		List<CategoryVO> categories = null;
		Map<String, String> categoriesMap = null;
		Integer currentPage = 1;
		String languageCode = request.getParameter("languageCode");
		PublicationsVO publicationsVO = new PublicationsVO();
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		publicationsVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		publicationsSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		publicationsSearchCommand.setSite(site);
		
		String pageName="Publications";
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setLanguage(request.getParameter("languageCode"));
		GenericPageVO genericPageVO = null;
		
		try {
			
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);
			
		} catch (UAQException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		super.handleDepartmentRequest(request, modelMap, site);		
		
		try {
			
			if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
				currentPage = Integer.valueOf(request.getParameter("currentPage"));
				SessionUtil.updateSearchCommandWithFilters(request, publicationsSearchCommand);
			} else {
				SessionUtil.removeSearchFilters(request);
			}			
			publicationsSearchCommand.setCurrentPage(currentPage);

			removeSearchFilters(request);

			publicationsSearchCommand.setLanguage(languageCode);

			searchResponseVO = ((PublicationsService) publicationsService).getPublicationsList(publicationsSearchCommand);

			categories = ((PublicationsService) publicationsService).getPublicationsCategories(publicationsVO);
			categoriesMap = getMapFromCategoryList(categories, languageCode);

		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("languageCode", languageCode);
		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("searchCommand", publicationsSearchCommand);
		modelMap.addAttribute("categoriesMap", categoriesMap);
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());

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
					resultMap.put(category.getAssetId(), category.getName());// category.getCategoryNameEnglish
																				// needs
																				// to
																				// be
																				// added
				} else {
					resultMap.put(category.getAssetId(), category.getName());// category.getCategoryNameArabic
																				// needs
																				// to
																				// be
																				// added
				}
			}
		}
		return resultMap;
	}

	/**
	 * Get the Publications List from the category search
	 * 
	 * @param site
	 * @param publicationsSearchCommand
	 * @param result
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = ViewPath.PUBLICATION_LISTING_SITES, method = RequestMethod.POST)
	public String processSearch(@PathVariable("site") String site, @PathVariable("landing") String landing, @ModelAttribute("publicationsSearchCommand") SearchCommand publicationsSearchCommand, BindingResult result, ModelMap modelMap,
			HttpServletRequest request) {

		logger.enter("Publications Listing processSearch()");

		String view = "sites.publications.list";
		List<CategoryVO> categories = null;
		PublicationsVO publicationVo = new PublicationsVO();
		Integer currentPage = 1;
		Map<String, String> categoriesMap = null;
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		
		super.handleDepartmentRequest(request, modelMap, site);	
		
		validatePublications(publicationsSearchCommand, result);

		String languageCode = request.getParameter("languageCode");
		publicationsSearchCommand.setLanguage(languageCode);

		publicationVo.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		publicationVo.setSite(site);
		publicationsSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		publicationsSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));

		try {
			if (!result.hasErrors()) {
				SessionUtil.removeSearchFilters(request);
				SessionUtil.saveSearchFilters(request, publicationsSearchCommand);
				searchResponseVO = ((PublicationsService) publicationsService).getPublicationsList(publicationsSearchCommand);

			}
			publicationsSearchCommand.setCurrentPage(currentPage);
			super.handleDepartmentRequest(request, modelMap, site);
			categories = ((PublicationsService) publicationsService).getPublicationsCategories(publicationVo);
			categoriesMap = getMapFromCategoryList(categories, languageCode);
		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("languageCode", languageCode);
		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("searchCommand", publicationsSearchCommand);
		modelMap.addAttribute("categoriesMap", categoriesMap);

		logger.exit("Publications Listing processSearch()");

		return view;
	}

	@RequestMapping(value = ViewPath.PUBLICATION_DETAIL, method = RequestMethod.GET)
	public String publicationsDetail(@PathVariable("site") String site, @PathVariable("name") String name, HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get Publications Detail | handle Request");
		super.handleDepartmentRequest(request, modelMap, site);
		
		String landing = null;
		String activeNav = null;
		String source =(String) modelMap.get("source");
		if (source.contains("/")) {
			landing = source.split("/")[0];
			activeNav = source.split("/")[0] + "/" + source.split("/")[1] + "s.html";
		}

		String view = "sites.publications.detail";

		PublicationsVO publicationsVO = null;

		String languageCode = request.getParameter("languageCode");

		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(name));
		navigationVO.setLanguage(languageCode);
		GenericPageVO genericPageVO = null;
		
		try {
			publicationsVO = ((PublicationsService) publicationsService).getPublicationsDetails(navigationVO);
			
			String pageName="Publications";
			navigationVO = null;
			navigationVO = new NavigationVO();
			navigationVO.setSite(site);
			navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
			navigationVO.setName(UrlTransliterationUtil.getString(pageName));
			navigationVO.setLanguage(request.getParameter("languageCode"));
			
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);
			
			super.handleDepartmentRequest(request, modelMap, site);
		} catch (SSOException e) {
			logger.error("Error while getting Publications Details Page" + e.getMessage());
		} catch (UAQException e) {
			// TODO Auto-generated catch block
			logger.debug("Error getting session " + e.getMessage());
		}

		modelMap.addAttribute("publicationsVO", publicationsVO);
		modelMap.addAttribute("landing", landing);
		modelMap.addAttribute("activeNav", activeNav);
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());

		logger.exit("Get Publications Detail | handle Request");

		return view;
	}

	/**
	 * reset filters
	 * 
	 * @param request
	 */
	private void removeSearchFilters(HttpServletRequest request) {
		request.getSession().removeAttribute(SEARCH_FILTERS_OBJECT);

	}

	/**
	 * validates filters
	 * 
	 * @param newsSearchCommand
	 * @param errors
	 */
	private void validatePublications(SearchCommand newsSearchCommand, Errors errors) {

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
