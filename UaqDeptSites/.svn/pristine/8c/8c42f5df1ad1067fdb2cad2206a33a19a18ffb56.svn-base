package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fatwire.wem.sso.SSOException;
import com.uaq.command.SearchCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.AwardsService;
import com.uaq.service.BaseService;
import com.uaq.service.GenericPageService;
import com.uaq.util.SessionUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.Asset;
import com.uaq.vo.AwardsPageVO;
import com.uaq.vo.AwardsVO;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.SearchResponseVO;

@Controller
public class AwardsController extends BaseController {
	public static transient UAQLogger logger = new UAQLogger(AwardsController.class);

	@Autowired
	@Qualifier("awardsService")
	private BaseService<AwardsVO, List<AwardsVO>> awardsService;

	@Autowired
	@Qualifier("awardsDetailService")
	private BaseService<NavigationVO, AwardsVO> awardsDetailService;

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> pageReferneceDAO;
	
	@Autowired
	@Qualifier("genericPageService")
	BaseService<NavigationVO, GenericPageVO> genericPageService;

	/**
	 * Get the Awards Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = ViewPath.AWARDS_LISTING, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, @PathVariable("landing") String landing, @PathVariable("parent") String parent, HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get Awards Detail | handle Request");

		String view = "awards.list";
		if (!site.equals("uaq")) {
			view = "sites.awards.list";
			super.handleDepartmentRequest(request, modelMap, site);
		} else {
			view = "awards.list";
			super.handleRequest(request, modelMap);
		}

		Integer currentPage = 1;
		SearchCommand awardsSearchCommand = new SearchCommand();

		String pageName = "";
		String source = (String) request.getServletPath();
		if (source.contains("/")) {
			pageName = source.split("/")[2];
			pageName = (pageName.split(".html")[0]);
		}

		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setLanguage(request.getParameter("languageCode"));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setTicketId(multiticket);

		AwardsVO awardsVO = new AwardsVO();
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		AwardsPageVO awardsPageVO = null;

		String languageCode = request.getParameter("languageCode");

		awardsVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		awardsVO.setSite(site);
		awardsSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		awardsSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		awardsSearchCommand.setLanguage(languageCode);

		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
			SessionUtil.updateSearchCommandWithFilters(request, awardsSearchCommand);
		} else {
			SessionUtil.removeSearchFilters(request);
		}
		awardsSearchCommand.setCurrentPage(currentPage);

		Asset asset = new Asset();
		asset.setAssetType("Award");
		asset.setLanguage(awardsSearchCommand.getLanguage());
		asset.setSite(awardsSearchCommand.getSite());

		try {
			NavigationVO navigation = pageReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());

			awardsPageVO = (AwardsPageVO) ((AwardsService) awardsService).getPageVO(navigationVO);

			searchResponseVO = (SearchResponseVO) ((AwardsService) awardsService).getAwardsList(awardsSearchCommand, site, asset);

		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("searchCommand", awardsSearchCommand);
		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("awardsPageVO", awardsPageVO);
		modelMap.addAttribute("pageMetadata", awardsPageVO.getPageMetadataVO());

		logger.exit("Landing Page | handle Request");
		return view;
	}

	/**
	 * Get the Awards Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = ViewPath.AWARDS_SITES_LISTING, method = RequestMethod.GET)
	public String handleDepartmentRequest(@PathVariable("site") String site, HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get Awards Detail | handle Request");

		String view = "awards.list";
		if (!site.equals("uaq")) {
			view = "sites.awards.list";
			super.handleDepartmentRequest(request, modelMap, site);
		} else {
			view = "awards.list";
			super.handleRequest(request, modelMap);
		}

		Integer currentPage = 1;
		SearchCommand awardsSearchCommand = new SearchCommand();

		String pageName = "";
		String source = (String) request.getServletPath();
		if (source.contains("/")) {
			pageName = source.split("/")[2];
			pageName = (pageName.split(".html")[0]);
		}

		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setLanguage(request.getParameter("languageCode"));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setTicketId(multiticket);

		AwardsVO awardsVO = new AwardsVO();
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		AwardsPageVO awardsPageVO = null;

		String languageCode = request.getParameter("languageCode");

		awardsVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		awardsVO.setSite(site);
		awardsSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		awardsSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		awardsSearchCommand.setLanguage(languageCode);

		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
			SessionUtil.updateSearchCommandWithFilters(request, awardsSearchCommand);
		} else {
			SessionUtil.removeSearchFilters(request);
		}
		awardsSearchCommand.setCurrentPage(currentPage);

		Asset asset = new Asset();
		asset.setAssetType("Award");
		asset.setLanguage(awardsSearchCommand.getLanguage());
		asset.setSite(awardsSearchCommand.getSite());

		try {
			NavigationVO navigation = pageReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());

			awardsPageVO = (AwardsPageVO) ((AwardsService) awardsService).getPageVO(navigationVO);

			searchResponseVO = (SearchResponseVO) ((AwardsService) awardsService).getAwardsList(awardsSearchCommand, site, asset);

		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("searchCommand", awardsSearchCommand);
		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("awardsPageVO", awardsPageVO);
		modelMap.addAttribute("pageMetadata", awardsPageVO.getPageMetadataVO());
		modelMap.addAttribute("pageName", pageName);
		logger.exit("Landing Page | handle Request");
		return view;
	}

	/**
	 * @param site
	 * @param name
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { ViewPath.AWARDS_DETAIL, ViewPath.AWARDS_DETAIL_SITES }, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, @PathVariable("name") String name, HttpServletRequest request, ModelMap model) {
		logger.enter("Inside Awards Detail page");
		String view = "awards.detail";
		String landing = null;
		String activeNav = null;
		String pageName = null;
		if (site.equals("uaq")) {
			super.handleRequest(request, model);
			view = "awards.detail";
			pageName = "awards";
		} else {
			super.handleDepartmentRequest(request, model, site);
			view = "sites.awards.detail";
			pageName = "awards-and-achievements";
		}

		AwardsVO awardsVO = null;
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(name));
		navigationVO.setLanguage(request.getParameter("languageCode"));
		GenericPageVO genericPageVO = null;
		
		String source = (String) model.get("source");
		if (source.contains("/")) {
			landing = source.split("/")[0];
			activeNav = source.split("/")[0] + "/" + "awards-and-achievements.html";
		}

		try {
			
			awardsVO = awardsDetailService.execute(navigationVO);
			
			navigationVO = null;
			navigationVO = new NavigationVO();
			navigationVO.setSite(site);
			navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
			navigationVO.setName(UrlTransliterationUtil.getString(pageName));
			navigationVO.setLanguage(request.getParameter("languageCode"));
			
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);			
			
		} catch (UAQException e) {
			logger.error("Error getting session " + e.getMessage());
		}

		model.addAttribute("awardVO", awardsVO);
		model.addAttribute("landing", landing);
		model.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
		model.addAttribute("activeNav", activeNav);
		return view;
	}
}
