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
import com.uaq.service.CertificateService;
import com.uaq.util.SessionUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.Asset;
import com.uaq.vo.CertificatesPageVO;
import com.uaq.vo.CertificatesVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.SearchResponseVO;

@Controller
public class CertificateController extends BaseController {
	public static transient UAQLogger logger = new UAQLogger(CertificateController.class);

	@Autowired
	@Qualifier("certificatesService")
	private BaseService<CertificatesVO, List<CertificatesVO>> certificatesService;

	@Autowired
	@Qualifier("certificatesDetailService")
	private BaseService<NavigationVO, CertificatesVO> certificatesDetailService;

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> pageReferneceDAO;

	

	/**
	 * Get the Certificates Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = ViewPath.CERTIFICATES_SITES_LISTING, method = RequestMethod.GET)
	public String handleDepartmentRequest(@PathVariable("site") String site, HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get Certificates Detail | handle Request");

		String view = "sites.certificates.list";
			super.handleDepartmentRequest(request, modelMap, site);
		

		Integer currentPage = 1;
		SearchCommand certificatesSearchCommand = new SearchCommand();

		String pageName = "";
		String source = (String) request.getServletPath();
		if (source.contains("/")) {
			pageName = source.split("/")[2];
			pageName = (pageName.split(".html")[0]);
		}

		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setLanguage(request.getParameter("languageCode"));
		navigationVO.setName(UrlTransliterationUtil.getString("Certificates"));
		navigationVO.setTicketId(multiticket);

		CertificatesVO certificatesVO = new CertificatesVO();
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		CertificatesPageVO certificatesPageVO = null;

		String languageCode = request.getParameter("languageCode");

		certificatesVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		certificatesVO.setSite(site);
		certificatesSearchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		certificatesSearchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		certificatesSearchCommand.setLanguage(languageCode);

		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
			SessionUtil.updateSearchCommandWithFilters(request, certificatesSearchCommand);
		} else {
			SessionUtil.removeSearchFilters(request);
		}
		certificatesSearchCommand.setCurrentPage(currentPage);

		Asset asset = new Asset();
		asset.setAssetType("Certificate");
		asset.setLanguage(certificatesSearchCommand.getLanguage());
		asset.setSite(certificatesSearchCommand.getSite());

		try {
			NavigationVO navigation = pageReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());

			certificatesPageVO = (CertificatesPageVO) ((CertificateService) certificatesService).getPageVO(navigationVO);

			searchResponseVO = (SearchResponseVO) ((CertificateService) certificatesService).getCertificatesList(certificatesSearchCommand, site, asset);

		} catch (UAQException e) {
			logger.error(e.getMessage());
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("searchCommand", certificatesSearchCommand);
		modelMap.addAttribute("searchResponse", searchResponseVO);
		modelMap.addAttribute("certificatesPageVO", certificatesPageVO);
		modelMap.addAttribute("pageMetadata", certificatesPageVO.getPageMetadataVO());
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
	@RequestMapping(value = ViewPath.CERTIFICATES_DETAIL_SITES, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, @PathVariable("name") String name, HttpServletRequest request, ModelMap model) {
		logger.enter("Inside Certificates Detail page");
		String view = "certificates.detail";
		String landing = null;
		String activeNav = null;
		if (site.equals("uaq")) {
			super.handleRequest(request, model);
			view = "certificates.detail";
		} else {
			super.handleDepartmentRequest(request, model, site);
			view = "sites.certificates.detail";
		}

		CertificatesVO certificatesVO = null;
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(name));
		navigationVO.setLanguage(request.getParameter("languageCode"));
		String source = (String) model.get("source");
		if (source.contains("/")) {
			landing = source.split("/")[0];
			activeNav = source.split("/")[0] + "/" + "certificates.html";
		}


		try {
			certificatesVO = certificatesDetailService.execute(navigationVO);
			model.addAttribute("certificateVO", certificatesVO);
			model.addAttribute("landing", landing);
			model.addAttribute("activeNav", activeNav);
		} catch (UAQException e) {
			logger.error("Error getting session " + e.getMessage());
		}
		return view;
	}
}
