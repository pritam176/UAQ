package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.uaq.service.VideoGalleryService;
import com.uaq.util.SessionUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.CategoryVO;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.VideoSearchResponseVO;
import com.uaq.vo.VideoVO;

/**
 * @author raheem
 * 
 *         Controller for News Page
 */
@Controller
public class VideoGalleryController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(
			VideoGalleryController.class);

	private static final String SEARCH_FILTERS_OBJECT = "search_filters_object";
	
	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> pageReferneceDAO;

	@Autowired
	@Qualifier("videoService")
	private BaseService<SearchCommand, VideoSearchResponseVO> videoService;
	
	@Autowired
	@Qualifier("genericPageService")
	BaseService<NavigationVO, GenericPageVO> genericPageService;

	/**
	 * Get the News Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = { ViewPath.VIDEO_GALLERY,
			ViewPath.VIDEO_GALLERY_SITES }, method = RequestMethod.GET)
	public String handleRequest(
			@PathVariable("site") String site,
			@PathVariable("landing") String landing,
			@ModelAttribute("videoSearchCommand") SearchCommand videoSearchCommand,
			HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get Video Gallery | handle Request");
		List<VideoVO> videos = null;
		VideoSearchResponseVO videoSearchResponseVO = new VideoSearchResponseVO();
		List<CategoryVO> categories = null;
		Map<String, String> categoriesMap = null;
		String languageCode = request.getParameter("languageCode");
		String category = request.getParameter("category");
		VideoVO videoVO = new VideoVO();
		videoVO.setTicketId((String) request.getSession().getAttribute(
				SESSION_TICKET));
		videoSearchCommand.setTicketId((String) request.getSession()
				.getAttribute(SESSION_TICKET));

		String pageName = "";
		String source = (String) request.getServletPath();
		String view = null;
		GenericPageVO genericPageVO = null;
		String requestType = "GET";
		
		Integer currentPage = 1;
		if (request.getParameter("currentPage") != null
				&& !request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
			SessionUtil.updateSearchCommandWithFilters(request,
					videoSearchCommand);
		} else {
			SessionUtil.removeSearchFilters(request);
		}
		videoSearchCommand.setCurrentPage(currentPage);

		if (site.equals("uaq")) {
			view = "video.gallery";
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
			VideoVO videoGalleryPageVO = null;

			try {
				NavigationVO navigation = pageReferneceDAO
						.findByPrimaryKey(navigationVO);
				navigationVO.setAssetId(navigation.getAssetId());

				videoGalleryPageVO = ((VideoGalleryService) videoService)
						.getPageVO(navigationVO);
	
				if (category != null && !category.isEmpty()) {
					videoSearchCommand.setCategory(category);
				}
				videoSearchCommand.setLanguage(languageCode);
				videoSearchCommand.setSite(site);

				videoSearchResponseVO = videoService
						.execute(videoSearchCommand);
				videos = ((VideoGalleryService) videoService)
						.getGalleryVideos(navigationVO, videoSearchCommand, requestType);

				categories = ((VideoGalleryService) videoService)
						.getCategories(site, navigationVO);
				getCategories(videoSearchResponseVO, languageCode);
				categoriesMap = getMapFromCategoryList(categories, languageCode);

			} catch (UAQException e) {
				logger.error(e.getMessage());
			}

			modelMap.addAttribute("videoSearchResponseVO",
					videoSearchResponseVO.getSearchResults());
			modelMap.addAttribute("videoSearchCommand", new SearchCommand());
			modelMap.addAttribute("videos", videos);
			modelMap.addAttribute("categoriesMap", categoriesMap);
			modelMap.addAttribute("videoGalleryPageVO", videoGalleryPageVO);
			modelMap.addAttribute("pageMetadata",
					videoGalleryPageVO.getPageMetadataVO());

			logger.exit("Video Gallery | handle Request");

		} else {
			if (source.contains("/")) {
				pageName = source.split("/")[3];
				pageName = (pageName.split(".html")[0]);
			}
			view = "sites.video.gallery";
			super.handleDepartmentRequest(request, modelMap, site);

			try {

				NavigationVO navigationVO = new NavigationVO();
				navigationVO.setSite(site);
				navigationVO.setLanguage(request.getParameter("languageCode"));
				navigationVO
						.setName(UrlTransliterationUtil.getString(pageName));
				navigationVO.setTicketId(multiticket);

				NavigationVO navigation = pageReferneceDAO
						.findByPrimaryKey(navigationVO);
				navigationVO.setAssetId(navigation.getAssetId());
				
				if (category != null && !category.isEmpty()) {
					videoSearchCommand.setCategory(category);
				}
				videoSearchCommand.setLanguage(languageCode);
				videoSearchCommand.setSite(site);
				videoSearchCommand.setPageSize(12);
				videoSearchResponseVO = videoService
						.execute(videoSearchCommand);
				videos = ((VideoGalleryService) videoService)
						.getGalleryVideos(navigationVO, videoSearchCommand, requestType);
				categories = ((VideoGalleryService) videoService)
						.getCategories(site, navigationVO);
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

			modelMap.addAttribute("videoSearchResponseVO",
					videoSearchResponseVO.getSearchResults());
			modelMap.addAttribute("videoSearchCommand", new SearchCommand());
			modelMap.addAttribute("categoriesMap", categoriesMap);
			modelMap.addAttribute("videos", videos);
			modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
			
			logger.exit("Video Gallery | handle Request");
		}
		return view;
	}

	private void getCategories(VideoSearchResponseVO videoSearchResponseVO,
			String languageCode) {
		Iterator<VideoVO> itr = videoSearchResponseVO.getSearchResults()
				.iterator();
		while (itr.hasNext()) {
			VideoVO video = itr.next();
			if (!video.getLanguage().equals(languageCode)) {
				itr.remove();
			}
		}
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
	
	@RequestMapping(value = ViewPath.VIDEO_GALLERY, method = RequestMethod.POST)
	public String processSearch(
			@PathVariable("site") String site,
			@ModelAttribute("videoSearchCommand") SearchCommand videoSearchCommand,
			BindingResult result, ModelMap modelMap, HttpServletRequest request) {

		logger.enter("Video Listing processSearch()");
		String requestType = "POST";
		VideoSearchResponseVO videoSearchResponseVO = new VideoSearchResponseVO();
		
		List<VideoVO> videos = null;
		
		String source = (String) request.getServletPath();
		String pageName = "";
		if (source.contains("/")) {
			pageName = source.split("/")[4];
			pageName = (pageName.split(".html")[0]);
		}
		
		String view = "video.gallery";
		if (!site.equals("uaq")) {
			view = "sites.video.gallery";
			super.handleDepartmentRequest(request, modelMap, site);
		} else {
			super.handleRequest(request, modelMap);
		}
		List<CategoryVO> categories = null;
		VideoVO videoVO = new VideoVO();

		Map<String, String> categoriesMap = null;

		String languageCode = request.getParameter("languageCode");
		videoSearchCommand.setLanguage(languageCode);

		videoVO.setTicketId((String) request.getSession().getAttribute(
				SESSION_TICKET));
		videoSearchCommand.setTicketId((String) request.getSession()
				.getAttribute(SESSION_TICKET));
		videoSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
	
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
				saveSearchFilters(request, videoSearchCommand);

				videoSearchResponseVO = videoService
						.execute(videoSearchCommand);
				categories = ((VideoGalleryService) videoService)
						.getCategories(site, navigationVO);
				categoriesMap = getMapFromCategoryList(categories, languageCode);
				videos = ((VideoGalleryService) videoService)
						.getGalleryVideos(navigationVO, videoSearchCommand, requestType);
			}

		} catch (UAQException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("videoSearchResponseVO",
				videoSearchResponseVO.getSearchResults());
		modelMap.addAttribute("categoriesMap", categoriesMap);
		modelMap.addAttribute("surveyCommand", new SurveyCommand());
		modelMap.addAttribute("videos", videos);
		
		logger.debug("Video Listing processSearch()");

		return view;
	}
	
	@RequestMapping(value = ViewPath.VIDEO_GALLERY_SITES, method = RequestMethod.POST)
	public String processSitesSearch(
			@PathVariable("site") String site,
			@PathVariable("landing") String landing,
			@ModelAttribute("videoSearchCommand") SearchCommand videoSearchCommand,
			BindingResult result, ModelMap modelMap, HttpServletRequest request) {

		logger.enter("Video Listing processSitesSearch()");
		String requestType = "POST";
		VideoSearchResponseVO videoSearchResponseVO = new VideoSearchResponseVO();
		
		List<VideoVO> videos = null;
		
		String source = (String) request.getServletPath();
		String pageName = "";
		if (source.contains("/")) {
			pageName = source.split("/")[3];
			pageName = (pageName.split(".html")[0]);
		}
		
		String view = "video.gallery";
		if (!site.equals("uaq")) {
			view = "sites.video.gallery";
			super.handleDepartmentRequest(request, modelMap, site);
		} else {
			super.handleRequest(request, modelMap);
		}
		List<CategoryVO> categories = null;
		VideoVO videoVO = new VideoVO();

		Map<String, String> categoriesMap = null;

		String languageCode = request.getParameter("languageCode");
		videoSearchCommand.setLanguage(languageCode);

		videoVO.setTicketId((String) request.getSession().getAttribute(
				SESSION_TICKET));
		videoSearchCommand.setTicketId((String) request.getSession()
				.getAttribute(SESSION_TICKET));
		videoSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
	
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
				saveSearchFilters(request, videoSearchCommand);

				videoSearchResponseVO = videoService
						.execute(videoSearchCommand);
				categories = ((VideoGalleryService) videoService)
						.getCategories(site, navigationVO);
				categoriesMap = getMapFromCategoryList(categories, languageCode);
				videos = ((VideoGalleryService) videoService)
						.getGalleryVideos(navigationVO, videoSearchCommand, requestType);
			}

		} catch (UAQException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("videoSearchResponseVO",
				videoSearchResponseVO.getSearchResults());
		modelMap.addAttribute("categoriesMap", categoriesMap);
		modelMap.addAttribute("surveyCommand", new SurveyCommand());
		modelMap.addAttribute("videos", videos);
		
		logger.debug("Video Listing processSearch()");

		return view;
	}
	
	
	/**
	 * preserve filters
	 * 
	 * @param request
	 * @param videoSearchCommand
	 */
	private void saveSearchFilters(HttpServletRequest request,
			SearchCommand videoSearchCommand) {
		request.getSession().setAttribute(SEARCH_FILTERS_OBJECT,
				videoSearchCommand);
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
