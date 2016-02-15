package com.uaq.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.common.ViewPath;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.service.SurveyService;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.DetailPageVO;
import com.uaq.vo.LandingPageVO;
import com.uaq.vo.NavigationVO;

/**
 * @author mraheem
 * 
 *         Controller for Portal Detail Page
 */

@Controller
public class DetailPageController extends BaseController {

	@Autowired
	@Qualifier("detailPageService")
	BaseService<DetailPageVO, DetailPageVO> detailPageService;

	@Autowired
	@Qualifier("landingPageService")
	BaseService<LandingPageVO, LandingPageVO> landingPageService;

	@Autowired
	@Qualifier("surveyService")
	SurveyService surveyService;

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> pageReferneceDAO;

	public static transient UAQLogger logger = new UAQLogger(DetailPageController.class);

	@RequestMapping(value = ViewPath.DETAIL_PAGE, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("landing") String landing, @PathVariable("pageName") String pageName, HttpServletRequest request, ModelMap model) {
		logger.enter("Portal Detail Page | handle Request");

		super.handleRequest(request, model);

		@SuppressWarnings("unchecked")
		Map<String, List<NavigationVO>> navigationList = (Map<String, List<NavigationVO>>) model.get("navigationList");

		DetailPageVO detailPageVO = new DetailPageVO();
		detailPageVO.setName(UrlTransliterationUtil.getString(pageName));
		detailPageVO.setTicketId(multiticket);
		detailPageVO.setSite("uaq");
		detailPageVO.setLanguage(request.getParameter("languageCode"));
		try {
			detailPageVO = detailPageService.execute(detailPageVO);

			for (NavigationVO navigationVO : navigationList.get(request.getParameter("languageCode"))) {
				for (NavigationVO secondLeveLNavigationVO : navigationVO.getNavigations()) {
					if (detailPageVO.getAssetId().equals(secondLeveLNavigationVO.getAssetId())) {
						detailPageVO.setNavigations(secondLeveLNavigationVO.getNavigations());
					}

				}
			}

		} catch (UAQException e) {
			logger.error("Error while getting Detail Page Details" + e.getMessage());
		}

		model.addAttribute("pageMetadata", detailPageVO.getPageMetadataVO());
		model.addAttribute("detailPageVO", detailPageVO);

		return "detail.page";
	}

	@RequestMapping(value = ViewPath.DETAIL_DETAIL_PAGE, method = RequestMethod.GET)
	public String handleThrirdLevelRequest(@PathVariable("parentPage") String parentPage, @PathVariable("landing") String landing, @PathVariable("pageName") String pageName,
			HttpServletRequest request, ModelMap model) {
		logger.enter(ViewPath.DETAIL_DETAIL_PAGE + "| handle Request");

		super.handleRequest(request, model);


		DetailPageVO detailPageVO = new DetailPageVO();
		detailPageVO.setName(UrlTransliterationUtil.getString(pageName));
		detailPageVO.setTicketId(multiticket);
		detailPageVO.setSite("uaq");
		detailPageVO.setLanguage(request.getParameter("languageCode"));
		try {
			detailPageVO = detailPageService.execute(detailPageVO);

		} catch (UAQException e) {
			logger.error("Error while getting Detail Page Details" + e.getMessage());
		}

		model.addAttribute("pageMetadata", detailPageVO.getPageMetadataVO());
		model.addAttribute("thirdLevelDetailPageVO", detailPageVO);

		return "third.detail.page";
	}

	@RequestMapping(value = {ViewPath.SITES_DETAIL_PAGE,ViewPath.SITES_DETAIL_PAGE_TMP}, method = RequestMethod.GET)
	public String handleRequestSites(@PathVariable("sites") String site, @PathVariable("landing") String landing, @PathVariable("pageName") String pageName, HttpServletRequest request, ModelMap model) {
		logger.enter("Portal Detail Page | handle Request");

		super.handleDepartmentRequest(request, model, site);

		DetailPageVO detailPageVO = new DetailPageVO();

		detailPageVO.setName(UrlTransliterationUtil.getString(pageName));
		detailPageVO.setTicketId(multiticket);
		detailPageVO.setSite(site);
		detailPageVO.setLanguage(request.getParameter("languageCode"));
		
		try {
			detailPageVO = detailPageService.execute(detailPageVO);
			
			if (detailPageVO.getDisplayTypeHome() != null && detailPageVO.getDisplayTypeHome().toString().equalsIgnoreCase("Image")){
				detailPageVO.setImageCSSClass("imagecssclass");
			}

		} catch (UAQException e) {
			logger.error("Error while getting Detail Page Details" + e.getMessage());
		}

		model.addAttribute("pageMetadata", detailPageVO.getPageMetadataVO());
		model.addAttribute("detailPageVO", detailPageVO);
		
		return "sites.detail.page";
	}
}
