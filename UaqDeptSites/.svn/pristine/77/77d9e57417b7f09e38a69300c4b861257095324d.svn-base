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
import com.uaq.service.BaseService;
import com.uaq.service.ReportService;
import com.uaq.util.SessionUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.Asset;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.ReportsPageVO;
import com.uaq.vo.ReportsVO;
import com.uaq.vo.SearchResponseVO;

@Controller
public class ReportController extends BaseController {
	public static transient UAQLogger logger = new UAQLogger(ReportController.class);

	@Autowired
	@Qualifier("reportsService")
	private BaseService<ReportsVO, List<ReportsVO>> reportsService;

	@Autowired
	@Qualifier("reportsDetailService")
	private BaseService<NavigationVO, ReportsVO> reportsDetailService;

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> pageReferneceDAO;

	

	/**
	 * Get the Reports Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = ViewPath.REPORTS_SITES_LISTING, method = RequestMethod.GET)
	public String handleDepartmentRequest(@PathVariable("site") String site, HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get Reports Detail | handle Request");

		String view = "sites.reports.list";
			super.handleDepartmentRequest(request, modelMap, site);
		

		Integer currentPage = 1;
		SearchCommand reportsSearchCommand = new SearchCommand();

		String pageName = "";
		String source = (String) request.getServletPath();
		if (source.contains("/")) {
			pageName = source.split("/")[2];
			pageName = (pageName.split(".html")[0]);
		}

		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setLanguage(request.getParameter("languageCode"));
		navigationVO.setName(UrlTransliterationUtil.getString("Reports"));
		navigationVO.setTicketId(multiticket);

		ReportsVO reportsVO = new ReportsVO();
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		ReportsPageVO reportsPageVO = null;

		String languageCode = request.getParameter("languageCode");

		reportsVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		reportsVO.setSite(site);
		reportsSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		reportsSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		reportsSearchCommand.setLanguage(languageCode);

		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
			SessionUtil.updateSearchCommandWithFilters(request, reportsSearchCommand);
		} else {
			SessionUtil.removeSearchFilters(request);
		}
		reportsSearchCommand.setCurrentPage(currentPage);

		Asset asset = new Asset();
		asset.setAssetType("Report");
		asset.setLanguage(reportsSearchCommand.getLanguage());
		asset.setSite(reportsSearchCommand.getSite());

		try {
			NavigationVO navigation = pageReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());

			reportsPageVO = (ReportsPageVO) ((ReportService) reportsService).getPageVO(navigationVO);

			searchResponseVO = (SearchResponseVO) ((ReportService) reportsService).getReportsList(reportsSearchCommand, site, asset);

		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("searchCommand", reportsSearchCommand);
		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("reportsPageVO", reportsPageVO);
		modelMap.addAttribute("pageMetadata", reportsPageVO.getPageMetadataVO());
		modelMap.addAttribute("pageName",pageName);
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
	@RequestMapping(value = ViewPath.REPORTS_DETAIL_SITES, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, @PathVariable("name") String name, HttpServletRequest request, ModelMap model) {
		logger.enter("Inside Reports Detail page");
		String view = "reports.detail";
		String landing = null;
		String activeNav = null;
		if (site.equals("uaq")) {
			super.handleRequest(request, model);
			view = "reports.detail";
		} else {
			super.handleDepartmentRequest(request, model, site);
			view = "sites.reports.detail";
		}

		ReportsVO reportsVO = null;
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(name));
		navigationVO.setLanguage(request.getParameter("languageCode"));
		String source = (String) model.get("source");
		if (source.contains("/")) {
			landing = source.split("/")[0];
			activeNav = source.split("/")[0] + "/" + source.split("/")[1] + "s.html";
		}
		try {
			reportsVO = reportsDetailService.execute(navigationVO);
			model.addAttribute("reportVO", reportsVO);
			model.addAttribute("landing", landing);
			model.addAttribute("activeNav", activeNav);
		} catch (UAQException e) {
			logger.error("Error getting session " + e.getMessage());
		}
		return view;
	}
}
