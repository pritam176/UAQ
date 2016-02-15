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
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.LandingPageVO;
import com.uaq.vo.NavigationVO;

/**
 * @author nsharma
 * 
 *         Controller for Landing Page
 */
@Controller
public class LandingPageController extends BaseController {

	@Autowired
	@Qualifier("landingPageService")
	BaseService<LandingPageVO, LandingPageVO> landingPageService;

	public static transient UAQLogger logger = new UAQLogger(LandingPageController.class);

	/**
	 * @param pageName
	 * @param request
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = ViewPath.LANDING_PAGE, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("pageName") String pageName, HttpServletRequest request, ModelMap model) {
		logger.enter("Landing Page | handle Request");
		super.handleRequest(request, model);
		Map<String, List<NavigationVO>> navigationList = (Map<String, List<NavigationVO>>) model.get("navigationList");
		LandingPageVO landingPageVO = new LandingPageVO();
		landingPageVO.setName(UrlTransliterationUtil.getString(pageName));
		landingPageVO.setSite("uaq");
		landingPageVO.setLanguage(request.getParameter("languageCode"));
		try {
			landingPageVO = landingPageService.execute(landingPageVO);

			for (NavigationVO navigationVO : navigationList.get(request.getParameter("languageCode"))) {
				if (landingPageVO.getAssetId().equals(navigationVO.getAssetId())) {
					model.addAttribute("currentPage", navigationVO);
					model.addAttribute("pageMetadata", navigationVO.getPageMetadataVO());
					logger.debug("Matched asset Id is " + navigationVO.getAssetId() + " Name of the asset is :" + navigationVO.getName());
					break;
				}
			}
		} catch (UAQException e) {
			logger.error("Error while getting Landing Page Details" + e.getMessage());
		}
		logger.exit("Landing Page | handle Request");
		return "landing.page";
	}

	/**
	 * @param site
	 * @param pageName
	 * @param request
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = ViewPath.SITES_LANDING_PAGE, method = RequestMethod.GET)
//	public String handleRequest(@PathVariable("sites") String site, @PathVariable("pageName") String pageName, HttpServletRequest request, ModelMap model) {
//		logger.enter("Department Landing Page | handle Request");
//		super.handleDepartmentRequest(request, model, site);
//		String pageURl = pageName + ".html";
//		String ticket = (String) request.getSession().getAttribute(SESSION_TICKET);
//		@SuppressWarnings("unchecked")
//		List<NavigationVO> navigations = (List<NavigationVO>) model.get("navigations");
//		for (NavigationVO navigationVO : navigations) {
//			if (navigationVO.getUrl().equals(pageURl)) {
//				navigationVO.setIsCurrent(Boolean.TRUE);
//				if (null != navigationVO.getNavigations() && navigationVO.getNavigations().size() > 0) {
//					navigationVO.getNavigations().get(0).getAssetId();
//					try {
//						AssetBean assetBean = AssetUtil.getAssetDetail(site, "Content_C", navigationVO.getNavigations().get(0).getAssetId(), ticket);
//						logger.debug("Matched asset Name is " + assetBean.getName());
//						logger.debug("Matched asset Subtype is " + assetBean.getSubtype());
//						logger.debug("Matched asset Type is " + assetBean.getId().split(":")[0]);
//						logger.debug("Matched asset Id is " + assetBean.getId().split(":")[1]);
//					} catch (SSOException e) {
//						logger.error("Error while getting Landing Page Details" + e.getMessage());
//					}
//				}
//
//			}
//		}
//
//		logger.exit("Department Landing Page | handle Request");
//		return "department.landing.page";
//	}

}
