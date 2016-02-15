package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

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
import com.uaq.service.BaseService;
import com.uaq.service.CareerService;
import com.uaq.util.SessionUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.CareersPageVO;
import com.uaq.vo.JobVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.SearchResponseVO;

/**
 * 
 * @author ajain
 * 
 *         Controller for Careers page
 */
@Controller
public class CareersController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(CareersController.class);

	@Autowired
	@Qualifier("careerService")
	private BaseService<JobVO, JobVO> careerService;
	
	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> pageReferneceDAO;
	/**
	 * 
	 * @param modelMap
	 * @param request
	 * @return view the name of the JSP page
	 * @throws SSOException
	 * @throws UAQException
	 */
	@RequestMapping(value = ViewPath.CAREER_LISTING, method = RequestMethod.GET)
	public String handelRequest(@PathVariable("site") String site, ModelMap modelMap, HttpServletRequest request) {

		String careerEmailTo = PropertiesUtil.getProperty("careers.email");

		super.handleRequest(request, modelMap);

		String view = "careers.list";
		String sector = "uaq";
		Integer currentPage = 1;
		String languageCode = request.getParameter("languageCode");
		CareersPageVO careersPageVO = null;
				
		String pageName = "";
		String source =  (String)request.getServletPath();
		if (source.contains("/")) {
			pageName = source.split("/")[2];
			pageName = (pageName.split(".html")[0]);
		}

		SearchCommand careerSearchCommand = new SearchCommand();
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		JobVO jobVO = new JobVO();
		
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setLanguage(request.getParameter("languageCode"));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setTicketId(multiticket);

		jobVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));

		careerSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		careerSearchCommand.setSite(PropertiesUtil.getProperty(sector + "_" + "csSiteName"));

		careerSearchCommand.setLanguage(languageCode);
		careerSearchCommand.setStartRow(0);

		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
			SessionUtil.updateSearchCommandWithFilters(request, careerSearchCommand);
		} else {
			SessionUtil.removeSearchFilters(request);
		}

		careerSearchCommand.setCurrentPage(currentPage);

		try {
			NavigationVO navigation = pageReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());
			
			careersPageVO = (CareersPageVO) ((CareerService) careerService).getPageVO(navigationVO);
			
			searchResponseVO = ((CareerService) careerService).getJobsList(careerSearchCommand);
		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("searchCommand", careerSearchCommand);
		modelMap.addAttribute("careerEmailTo", careerEmailTo);
		modelMap.addAttribute("careersPageVO", careersPageVO);
		modelMap.addAttribute("pageMetadata", careersPageVO.getPageMetadataVO());

		logger.debug("Exiting Careers Listing");

		return view;
	}

	/**
	 * 
	 * @param modelMap
	 * @param request
	 * @return view name of the jsp.
	 */

	@RequestMapping(value = ViewPath.CAREER_DETAILS, method = RequestMethod.GET)
	public String careerDetails(@PathVariable("site") String site, @PathVariable("name") String name, ModelMap modelMap, HttpServletRequest request) {

		String view = "career.detail";
		logger.enter("Get Career Details | Career Details");

		String careerEmailTo = PropertiesUtil.getProperty("careers.email");

		super.handleRequest(request, modelMap);
		JobVO jobVO = null;

		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(name));
		navigationVO.setLanguage(request.getParameter("languageCode"));

		try {
			jobVO = ((CareerService) careerService).getJobDetails(navigationVO);
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelMap.addAttribute("jobVO", jobVO);
		modelMap.addAttribute("careerEmailTo", careerEmailTo);
		logger.debug("Landing Page | handle Request");
		return view;

	}

}
