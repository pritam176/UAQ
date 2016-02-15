package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.uaq.service.ProjectsService;
import com.uaq.util.SessionUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.Asset;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.ProjectsVO;
import com.uaq.vo.SearchResponseVO;

@Controller
public class ProjectsController extends BaseController {
	public static transient UAQLogger logger = new UAQLogger(ProjectsController.class);

	@Autowired
	@Qualifier("projectsService")
	private BaseService<ProjectsVO, List<ProjectsVO>> projectsService;

	@Autowired
	@Qualifier("projectsDetailService")
	private BaseService<NavigationVO, ProjectsVO> projectsDetailService;
	
	@Autowired
	@Qualifier("genericPageService")
	BaseService<NavigationVO, GenericPageVO> genericPageService;

	/**
	 * @param site
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = ViewPath.PROJECTS_LISTING, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, @ModelAttribute("projectSearchCommand") SearchCommand projectSearchCommand, HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get Project Detail | handle Request");

		String view = "sites.projects.list";
		String pageName = "projects";
		super.handleDepartmentRequest(request, modelMap, site);
		Integer currentPage = 1;

		ProjectsVO projectsVO = new ProjectsVO();
		SearchResponseVO searchResponseVO = new SearchResponseVO();

		String languageCode = request.getParameter("languageCode");

		projectsVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		projectsVO.setSite(site);
		projectSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		projectSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		projectSearchCommand.setLanguage(languageCode);

		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
			SessionUtil.updateSearchCommandWithFilters(request, projectSearchCommand);
		} else {
			SessionUtil.removeSearchFilters(request);
		}
		projectSearchCommand.setCurrentPage(currentPage);

		Asset asset = new Asset();
		asset.setAssetType("Project");
		asset.setLanguage(projectSearchCommand.getLanguage());
		asset.setSite(projectSearchCommand.getSite());
		
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setLanguage(languageCode);
		GenericPageVO genericPageVO = null;
		
		

		try {

			searchResponseVO = ((ProjectsService) projectsService).getProjectsList(projectSearchCommand, site, asset);
			
			// load page metaData
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);

		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("searchCommand", projectSearchCommand);
		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
		// modelMap.addAttribute("landing","");

		logger.exit("Landing Page | handle Request");

		return view;
	}

	/**
	 * @param site
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = ViewPath.PROJECTS_LISTING_SITES, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, @PathVariable("landing") String landing, @ModelAttribute("projectSearchCommand") SearchCommand projectSearchCommand,
			HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get Project Detail | handle Request");

		String view = "sites.projects.list";
		String pageName = "projects";
		super.handleDepartmentRequest(request, modelMap, site);
		Integer currentPage = 1;

		ProjectsVO projectsVO = new ProjectsVO();
		SearchResponseVO searchResponseVO = new SearchResponseVO();

		String languageCode = request.getParameter("languageCode");

		projectsVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		projectsVO.setSite(site);
		projectSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		projectSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		projectSearchCommand.setLanguage(languageCode);

		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
			SessionUtil.updateSearchCommandWithFilters(request, projectSearchCommand);
		} else {
			SessionUtil.removeSearchFilters(request);
		}
		projectSearchCommand.setCurrentPage(currentPage);

		Asset asset = new Asset();
		asset.setAssetType("Project");
		asset.setLanguage(projectSearchCommand.getLanguage());
		asset.setSite(projectSearchCommand.getSite());
		
		// for page metadata
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setLanguage(languageCode);
		GenericPageVO genericPageVO = null;		
		
		try {

			searchResponseVO = ((ProjectsService) projectsService).getProjectsList(projectSearchCommand, site, asset);
			
			// load page metaData
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);

		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("searchCommand", projectSearchCommand);
		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
		modelMap.addAttribute("landing", "/" + landing);

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
	@RequestMapping(value = ViewPath.PROJECTS_DETAIL, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, @PathVariable("name") String name, HttpServletRequest request, ModelMap model) {
		logger.enter("Inside Projects Detail page");
		String landing = null;
		String activeNav = null;
		String view = "sites.projects.detail";
		String pageName = "projects";
		super.handleDepartmentRequest(request, model, site);
		ProjectsVO projectsVO = null;
		GenericPageVO genericPageVO = null;
		
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(name));
		navigationVO.setLanguage(request.getParameter("languageCode"));

		logger.debug("Multiticket is : " + multiticket);
		try {
			String source = (String) model.get("source");
			if (source.contains("/")) {
				landing = source.split("/")[0];
				activeNav = source.split("/")[0] + "/" + source.split("/")[1] + ".html";
			}
			projectsVO = projectsDetailService.execute(navigationVO);
			
			// for page metadata
			navigationVO = null; // to avoid cache load
			navigationVO = new NavigationVO();
			navigationVO.setSite(site);
			navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
			navigationVO.setName(UrlTransliterationUtil.getString(pageName));
			navigationVO.setLanguage(request.getParameter("languageCode"));
						
			// load page metaData
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);
			
		} catch (UAQException e) {
			logger.error("Error getting session " + e.getMessage());
		}
		
		model.addAttribute("projectsVO", projectsVO);
		model.addAttribute("landing", landing);
		model.addAttribute("activeNav", activeNav);
		model.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());

		return view;

	}

	/**
	 * @param site
	 * @param name
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = ViewPath.PROJECTS_DETAIL_SITES, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, @PathVariable("landing") String landing, @PathVariable("name") String name, HttpServletRequest request, ModelMap model) {
		logger.enter("Inside Projects Detail page");
		String view = "sites.projects.detail";
		String pageName = "projects";
		String activeNav = null;
		GenericPageVO genericPageVO = null;
		
		super.handleDepartmentRequest(request, model, site);
		
		ProjectsVO projectsVO = null;
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(name));
		navigationVO.setLanguage(request.getParameter("languageCode"));

		logger.debug("Multiticket is : " + multiticket);
		try {
			String source = (String) model.get("source");
			if (source.contains("/")) {
				landing = source.split("/")[0];
				activeNav = source.split("/")[0] + "/" + source.split("/")[1] + "s.html";
			}

			projectsVO = projectsDetailService.execute(navigationVO);
			
			// for page metadata
			navigationVO = null; // to avoid cache load
			navigationVO = new NavigationVO();
			navigationVO.setSite(site);
			navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
			navigationVO.setName(UrlTransliterationUtil.getString(pageName));
			navigationVO.setLanguage(request.getParameter("languageCode"));
						
			// load page metaData
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);		
			
		} catch (UAQException e) {
			logger.error("Error getting session " + e.getMessage());
		}
		
		model.addAttribute("projectsVO", projectsVO);
		model.addAttribute("landing", "/" + landing);
		model.addAttribute("activeNav", activeNav);
		model.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());

		return view;

	}

}
