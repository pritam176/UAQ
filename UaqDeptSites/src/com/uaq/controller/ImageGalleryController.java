package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.SearchCommand;
import com.uaq.command.SurveyCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.service.GenericPageService;
import com.uaq.service.ImageGalleryService;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.CategoryVO;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.ImageSearchResponseVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;

/**
 * @author raheem
 * 
 *         Controller for News Page
 */
@Controller
public class ImageGalleryController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(
			ImageGalleryController.class);

	private static final String SEARCH_FILTERS_OBJECT = "search_filters_object";

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> pageReferneceDAO;

	@Autowired
	@Qualifier("imageService")
	private BaseService<SearchCommand, ImageSearchResponseVO> imageService;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	@Qualifier("genericPageService")
	BaseService<NavigationVO, GenericPageVO> genericPageService;

	/**
	 * Get the News Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = { ViewPath.IMAGE_GALLERY,
			ViewPath.IMAGE_GALLERY_SITES }, method = RequestMethod.GET)
	public String handleRequest(
			@ModelAttribute("sites") String site,
			@ModelAttribute("imageSearchCommand") SearchCommand imageSearchCommand,
			HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get Image Gallery | handle Request");
		String requestType = "GET";
		ImageSearchResponseVO imageSearchResponseVO = new ImageSearchResponseVO();
		List<CategoryVO> categories = null;
		Map<String, String> categoriesMap = null;
		String category = request.getParameter("category");
		String languageCode = request.getParameter("languageCode");
		String view = null;
		ImageVO imageVO = new ImageVO();
		imageVO.setTicketId((String) request.getSession().getAttribute(
				SESSION_TICKET));
		imageSearchCommand.setTicketId((String) request.getSession()
				.getAttribute(SESSION_TICKET));
		String pageName = "";
		String source = (String) request.getServletPath();
		List<ImageVO> images = null;
		GenericPageVO genericPageVO = null;
		if (site.equals("uaq")) {
			view = "image.gallery";
			super.handleRequest(request, modelMap);

			if (source.contains("/")) {
				pageName = source.split("/")[4];
				pageName = (pageName.split(".html")[0]);
			}

			NavigationVO navigationVO = new NavigationVO();
			navigationVO.setSite(site);
			navigationVO.setLanguage(request.getParameter("languageCode"));
			navigationVO.setName(UrlTransliterationUtil.getString(pageName));
			navigationVO.setTicketId(multiticket);
			ImageVO imageGalleryPageVO = null;

			try {
				NavigationVO navigation = pageReferneceDAO
						.findByPrimaryKey(navigationVO);
				navigationVO.setAssetId(navigation.getAssetId());

				imageGalleryPageVO = ((ImageGalleryService) imageService)
						.getPageVO(navigationVO);
				images = ((ImageGalleryService) imageService)
						.getGalleryImages(navigationVO, imageSearchCommand, requestType);

				if (category != null && !category.isEmpty()) {
					imageSearchCommand.setCategory(category);
				}
				imageSearchCommand.setLanguage(languageCode);
				imageSearchCommand.setSite(site);

				imageSearchResponseVO = imageService
						.execute(imageSearchCommand);
				categories = ((ImageGalleryService) imageService)
						.getCategories(site, navigationVO);
				categoriesMap = getMapFromCategoryList(categories, languageCode);

			} catch (UAQException e) {
				logger.error(e.getMessage());
			}

			// modelMap.addAttribute("pageMetadata",
			// MetaDataUtil.getPageMetaData(view, languageCode, messageSource));

			modelMap.addAttribute("categoriesMap", categoriesMap);
			modelMap.addAttribute("images", images);
			modelMap.addAttribute("imageSearchCommand", new SearchCommand());
			modelMap.addAttribute("imageGalleryPageVO", imageGalleryPageVO);
			modelMap.addAttribute("pageMetadata",
					imageGalleryPageVO.getPageMetadataVO());

			logger.debug("Image Gallery | handle Request");
		} else {
			view = "sites.image.gallery";
			super.handleDepartmentRequest(request, modelMap, site);

			if (source.contains("/")) {
				pageName = source.split("/")[3];
				pageName = (pageName.split(".html")[0]);
			}

			NavigationVO navigationVO = new NavigationVO();
			navigationVO.setSite(site);
			navigationVO.setLanguage(request.getParameter("languageCode"));
			navigationVO.setName(UrlTransliterationUtil.getString(pageName));
			navigationVO.setTicketId(multiticket);
			try {
				NavigationVO navigation = pageReferneceDAO
						.findByPrimaryKey(navigationVO);
				navigationVO.setAssetId(navigation.getAssetId());
				
				if (category != null && !category.isEmpty()) {
					imageSearchCommand.setCategory(category);
				}
				imageSearchCommand.setLanguage(languageCode);
				imageSearchCommand.setSite(site);

				imageSearchResponseVO = imageService
						.execute(imageSearchCommand);
				categories = ((ImageGalleryService) imageService)
						.getCategories(site, navigationVO);
				images = ((ImageGalleryService) imageService)
						.getGalleryImages(navigationVO, imageSearchCommand, requestType);
				categoriesMap = getMapFromCategoryList(categories, languageCode);
				
				navigationVO = null;
				navigationVO = new NavigationVO();
				navigationVO.setSite(site);
				navigationVO.setLanguage(request.getParameter("languageCode"));
				navigationVO.setName(UrlTransliterationUtil.getString(pageName));
				navigationVO.setTicketId(multiticket);
				
				genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);
				
			} catch (UAQException e) {
				logger.error(e.getMessage());
			}

			// modelMap.addAttribute("pageMetadata",
			// MetaDataUtil.getPageMetaData(view, languageCode, messageSource));
			modelMap.addAttribute("imageSearchResponseVO",
					imageSearchResponseVO.getSearchResults());
			modelMap.addAttribute("categoriesMap", categoriesMap);
			modelMap.addAttribute("imageSearchCommand", new SearchCommand());
			modelMap.addAttribute("images", images);
			modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
		}
		return view;
	}

	/**
	 * Retrieving the category list
	 * 
	 * @param categoryList
	 * @param languageCode
	 * @return
	 */
	private Map<String, String> getMapFromCategoryList(
			List<CategoryVO> categoryList, String languageCode) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (categoryList.size() > 0 && categoryList != null) {
			for (CategoryVO categorie : categoryList) {
				if (languageCode.equals("en")) {
					resultMap.put(categorie.getCategoryNameEnglish(), categorie.getCategoryNameEnglish());
				}
				if (languageCode.equals("ar")) {
					resultMap.put(categorie.getCategoryNameArabic(), categorie.getCategoryNameArabic());
				}
			}
		}
		return resultMap;
	}

	@RequestMapping(value = ViewPath.IMAGE_GALLERY, method = RequestMethod.POST)
	public String processSearch(
			@PathVariable("sites") String site,
			@ModelAttribute("imageSearchCommand") SearchCommand imageSearchCommand,
			BindingResult result, ModelMap modelMap, HttpServletRequest request) {

		logger.enter("Image Listing processSearch()");
		String requestType = "POST";
		ImageSearchResponseVO imageSearchResponseVO = new ImageSearchResponseVO();
		List<ImageVO> images = null;
		
		String source = (String) request.getServletPath();
		String pageName = "";
		if (source.contains("/")) {
			pageName = source.split("/")[4];
			pageName = (pageName.split(".html")[0]);
		}
		
		String view = "image.gallery";
		if (!site.equals("uaq")) {
			view = "sites.image.gallery";
			super.handleDepartmentRequest(request, modelMap, site);
		} else {
			super.handleRequest(request, modelMap);
		}
		List<CategoryVO> categories = null;
		ImageVO imageVO = new ImageVO();

		Map<String, String> categoriesMap = null;

		String languageCode = request.getParameter("languageCode");
		imageSearchCommand.setLanguage(languageCode);

		imageVO.setTicketId((String) request.getSession().getAttribute(
				SESSION_TICKET));
		imageSearchCommand.setTicketId((String) request.getSession()
				.getAttribute(SESSION_TICKET));
		imageSearchCommand.setSite(PropertiesUtil.getProperty(site + "_"
				+ "csSiteName"));
	
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setLanguage(request.getParameter("languageCode"));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setTicketId(multiticket);
		
		ImageVO imageGalleryPageVO = null;
		
		try {
			NavigationVO navigation = pageReferneceDAO
					.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());
			imageGalleryPageVO = ((ImageGalleryService) imageService)
					.getPageVO(navigationVO);
			
			if (!result.hasErrors()) {
				removeSearchFilters(request);
				saveSearchFilters(request, imageSearchCommand);

				imageSearchResponseVO = imageService
						.execute(imageSearchCommand);
				categories = ((ImageGalleryService) imageService)
						.getCategories(site, navigationVO);
				categoriesMap = getMapFromCategoryList(categories, languageCode);
				images = ((ImageGalleryService) imageService)
						.getGalleryImages(navigationVO, imageSearchCommand, requestType);
			}

		} catch (UAQException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("imageSearchResponseVO",
				imageSearchResponseVO.getSearchResults());
		modelMap.addAttribute("categoriesMap", categoriesMap);
		modelMap.addAttribute("surveyCommand", new SurveyCommand());
		modelMap.addAttribute("images", images);
		modelMap.addAttribute("imageGalleryPageVO", imageGalleryPageVO);
		
		logger.debug("Image Listing processSearch()");

		return view;
	}
	
	
	@RequestMapping(value = ViewPath.IMAGE_GALLERY_SITES, method = RequestMethod.POST)
	public String processSitesSearch(
			@PathVariable("sites") String site,
			@ModelAttribute("imageSearchCommand") SearchCommand imageSearchCommand,
			BindingResult result, ModelMap modelMap, HttpServletRequest request) {

		logger.enter("Image Listing processSitesSearch()");
		String requestType = "POST";
		ImageSearchResponseVO imageSearchResponseVO = new ImageSearchResponseVO();
		List<ImageVO> images = null;
		
		String source = (String) request.getServletPath();
		String pageName = "";
		if (source.contains("/")) {
			pageName = source.split("/")[3];
			pageName = (pageName.split(".html")[0]);
		}
		
		String view = "image.gallery";
		if (!site.equals("uaq")) {
			view = "sites.image.gallery";
			super.handleDepartmentRequest(request, modelMap, site);
		} 
		
		List<CategoryVO> categories = null;
		ImageVO imageVO = new ImageVO();

		Map<String, String> categoriesMap = null;

		String languageCode = request.getParameter("languageCode");
		imageSearchCommand.setLanguage(languageCode);

		imageVO.setTicketId((String) request.getSession().getAttribute(
				SESSION_TICKET));
		imageSearchCommand.setTicketId((String) request.getSession()
				.getAttribute(SESSION_TICKET));
		imageSearchCommand.setSite(PropertiesUtil.getProperty(site + "_"
				+ "csSiteName"));
	
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setLanguage(request.getParameter("languageCode"));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setTicketId(multiticket);
		
		try {
			NavigationVO navigation = pageReferneceDAO
					.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());
			
			if (!result.hasErrors()) {
				removeSearchFilters(request);
				saveSearchFilters(request, imageSearchCommand);

				imageSearchResponseVO = imageService
						.execute(imageSearchCommand);
				categories = ((ImageGalleryService) imageService)
						.getCategories(site, navigationVO);
				categoriesMap = getMapFromCategoryList(categories, languageCode);
				images = ((ImageGalleryService) imageService)
						.getGalleryImages(navigationVO, imageSearchCommand, requestType);
			}

		} catch (UAQException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("imageSearchResponseVO",
				imageSearchResponseVO.getSearchResults());
		modelMap.addAttribute("categoriesMap", categoriesMap);
		modelMap.addAttribute("surveyCommand", new SurveyCommand());
		modelMap.addAttribute("images", images);
		
		logger.debug("Image Listing processSearch()");

		return view;
	}
	

	/**
	 * preserve filters
	 * 
	 * @param request
	 * @param imageSearchCommand
	 */
	private void saveSearchFilters(HttpServletRequest request,
			SearchCommand imageSearchCommand) {
		request.getSession().setAttribute(SEARCH_FILTERS_OBJECT,
				imageSearchCommand);

	}

	/**
	 * reset filters
	 * 
	 * @param request
	 */
	private void removeSearchFilters(HttpServletRequest request) {
		request.getSession().removeAttribute(SEARCH_FILTERS_OBJECT);

	}

}
