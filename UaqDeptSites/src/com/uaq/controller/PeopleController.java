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
import com.uaq.service.PeopleService;
import com.uaq.service.ProjectsService;
import com.uaq.util.SessionUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.Asset;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.PeopleVO;
import com.uaq.vo.ProjectsVO;
import com.uaq.vo.SearchResponseVO;

@Controller
public class PeopleController extends BaseController {
	public static transient UAQLogger logger = new UAQLogger(PeopleController.class);

	@Autowired
	@Qualifier("peopleService")
	private BaseService<PeopleVO, List<PeopleVO>> peopleService;
	
	
	@Autowired
	@Qualifier("genericPageService")
	BaseService<NavigationVO, GenericPageVO> genericPageService;
	
	
	/**
	 * @param site
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = ViewPath.PEOPLE_LISTING, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, @PathVariable("landing") String landing, @ModelAttribute("peopleSearchCommand") SearchCommand peopleSearchCommand,
			HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get People List | handle Request");

		String view = "sites.people.list";
		String pageName = "our-people";
		super.handleDepartmentRequest(request, modelMap, site);
		Integer currentPage = 1;

		PeopleVO peopleVO = new PeopleVO();
		SearchResponseVO searchResponseVO = new SearchResponseVO();

		String languageCode = request.getParameter("languageCode");

		peopleVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		peopleVO.setSite(site);
		peopleSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		peopleSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		peopleSearchCommand.setLanguage(languageCode);

		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
			SessionUtil.updateSearchCommandWithFilters(request, peopleSearchCommand);
		} else {
			SessionUtil.removeSearchFilters(request);
		}
		peopleSearchCommand.setCurrentPage(currentPage);

		Asset asset = new Asset();
		asset.setAssetType("People");
		asset.setLanguage(peopleSearchCommand.getLanguage());
		asset.setSite(peopleSearchCommand.getSite());
		
		// for page metadata
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setLanguage(languageCode);
		GenericPageVO genericPageVO = null;		
		
		try {
			searchResponseVO = ((PeopleService) peopleService).getPeopleList(peopleSearchCommand, site, asset);

			// load page metaData
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);

		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("searchCommand", peopleSearchCommand);
		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
		modelMap.addAttribute("landing", "/" + landing);

		logger.exit("People Controller | handle Request");

		return view;
	}

	
	/**
	 * @param site
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = ViewPath.PEOPLE_LISTING, method = RequestMethod.POST)
	public String processSearch(@PathVariable("site") String site, @PathVariable("landing") String landing, @ModelAttribute("peopleSearchCommand") SearchCommand peopleSearchCommand,
			HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get People List | handle Request");

		String view = "sites.people.list";
		String pageName = "our-people";
		super.handleDepartmentRequest(request, modelMap, site);
		Integer currentPage = 1;

		PeopleVO peopleVO = new PeopleVO();
		SearchResponseVO searchResponseVO = new SearchResponseVO();

		String languageCode = request.getParameter("languageCode");

		peopleVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		peopleVO.setSite(site);
		peopleSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		peopleSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		peopleSearchCommand.setLanguage(languageCode);

		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
			SessionUtil.updateSearchCommandWithFilters(request, peopleSearchCommand);
		} else {
			SessionUtil.removeSearchFilters(request);
		}
		peopleSearchCommand.setCurrentPage(currentPage);

		Asset asset = new Asset();
		asset.setAssetType("People");
		asset.setLanguage(peopleSearchCommand.getLanguage());
		asset.setSite(peopleSearchCommand.getSite());
		
		// for page metadata
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setLanguage(languageCode);
		GenericPageVO genericPageVO = null;		
		
		try {
			searchResponseVO = ((PeopleService) peopleService).getPeopleList(peopleSearchCommand, site, asset);

			// load page metaData
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);

		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("searchCommand", peopleSearchCommand);
		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
		modelMap.addAttribute("landing", "/" + landing);

		logger.exit("People Controller | handle Request");

		return view;
	}

}
