package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import java.util.HashMap;
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

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.wem.sso.SSOException;
import com.uaq.common.AssetUtil;
import com.uaq.common.ViewPath;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.service.GenericPageService;
import com.uaq.service.ServicesCatalogPageService;
import com.uaq.service.ServicesService;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.DetailPageVO;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.ServicesCatalogPageVO;
import com.uaq.vo.ServicesVO;

@Controller
public class ServicesController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(ServicesController.class);

	@Autowired
	@Qualifier("ServicesDetailMapper")
	BaseVOMapper mapper;

	@Autowired
	@Qualifier("servicesService")
	private ServicesService servicesService;

	@Autowired
	@Qualifier("serviceDetailService")
	private BaseService<NavigationVO, ServicesVO> serviceDetailService;

	@Autowired
	@Qualifier("departmentDetailMapper")
	private BaseVOMapper departmentDetailMapper;

	@Autowired
	@Qualifier("contentReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> contentReferneceDAO;

	@Autowired
	@Qualifier("detailPageService")
	BaseService<DetailPageVO, DetailPageVO> detailPageService;

	@Autowired
	@Qualifier("servicesCatalogPageService")
	BaseService<NavigationVO, ServicesCatalogPageVO> servicesCatalogPageService;

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> pageReferneceDAO;
	
	@Autowired
	@Qualifier("genericPageService")
	BaseService<NavigationVO, GenericPageVO> genericPageService;
	
	@RequestMapping(value = ViewPath.SERVICES_DETAIL, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site, @PathVariable("name") String name, @PathVariable("sector") String sector, HttpServletRequest request, ModelMap model)
			throws UAQException {

		String view = null;
		String pageName = "servicecatalog";
		
		String multiticket = (String) request.getSession().getAttribute("ticketid");
		logger.debug("Multiticket is : " + multiticket);

		//String dept = getSite(sector);
		
		if (site.equals("uaq")) {
			view = "services.detail";
			logger.enter("Get Services Detail | handle Request");
			super.handleRequest(request, model);

			NavigationVO navigationVO = new NavigationVO();
			navigationVO.setSite(sector);
			navigationVO.setLanguage(request.getParameter("languageCode"));
			navigationVO.setName(UrlTransliterationUtil.getString(name));
			navigationVO.setTicketId(multiticket);
			GenericPageVO genericPageVO = null;
			
			NavigationVO navigation = contentReferneceDAO.findByPrimaryKey(navigationVO);
			try {
				AssetBean assetBean = AssetUtil.getAssetDetail(sector, "Content_C", navigation.getAssetId(), multiticket);
				ServicesVO serviceVO = (ServicesVO) this.mapper.mapAssetToVO(assetBean);
				
				navigationVO = null;
				navigationVO = new NavigationVO();
				navigationVO.setSite(site);
				navigationVO.setLanguage(request.getParameter("languageCode"));
				navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
				navigationVO.setName(UrlTransliterationUtil.getString(pageName));
				
				genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);
				
				model.addAttribute("serviceVO", serviceVO);
				model.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
			} catch (SSOException e) {
				logger.error("Error getting session " + e.getMessage());
			}
		}

		return view;
	}

	@RequestMapping(value = ViewPath.SITES_SERVICES_DETAIL, method = RequestMethod.GET)
	public String handleDetailRequest(@PathVariable("site") String site, @PathVariable("parent") String parent, @PathVariable("name") String name, HttpServletRequest request, ModelMap model)
			throws SSOException {

		String view = "sites.services.detail";
		logger.enter("Get Sites Services Detail | handle Request");

		super.handleDepartmentRequest(request, model, site);
		ServicesVO serviceVO = null;
		String pageName = "Services";
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(name));
		navigationVO.setLanguage(request.getParameter("languageCode"));
		GenericPageVO genericPageVO = null;
		
		try {

			serviceVO = serviceDetailService.execute(navigationVO);
			
			navigationVO = null;
			navigationVO = new NavigationVO();
			navigationVO.setSite(site);
			navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
			navigationVO.setName(UrlTransliterationUtil.getString(pageName));
			navigationVO.setLanguage(request.getParameter("languageCode"));
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);
			
			model.addAttribute("serviceVO", serviceVO);
			model.addAttribute("landing", parent);
			model.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
		} catch (UAQException e) {
			logger.error("Error getting session " + e.getMessage());
		}

		//model.addAttribute("pageMetadata", serviceVO.getPageMetadataVO());

		return view;
	}

	@RequestMapping(value = { ViewPath.SERVICES_CATALOG, ViewPath.SERVICES_SITES }, method = RequestMethod.GET)
	public String handleRequestServices(@PathVariable("site") String site, HttpServletRequest request, ModelMap model) throws SSOException {

		String languageCode = request.getParameter("languageCode");
		String multiticket = (String) request.getSession().getAttribute("ticketid");

		String pageName = "";
		String parent = "";
		String source = (String) request.getServletPath();
		if (source.contains("/")) {
			pageName = source.split("/")[3];
			parent = source.split("/")[2];
			pageName = (pageName.split(".html")[0]);
		}
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setLanguage(request.getParameter("languageCode"));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setTicketId(multiticket);

		logger.enter("Call Service for" + ViewPath.SERVICES_CATALOG);

		ServicesVO servicesVO = null;

		ServicesCatalogPageVO servicesCatalogPageVO = null;

		NavigationVO navigation;
		try {
			navigation = pageReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());
			servicesCatalogPageVO = ((ServicesCatalogPageService) servicesCatalogPageService).getPageVO(navigationVO);

		} catch (UAQException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String view = null;
		if (site.equals("uaq")) {

			view = "services.list";
			super.handleRequest(request, model);
			Map<String, List<ServicesVO>> serviceMap = null;
			try {
				serviceMap = servicesService.getServices(languageCode);
			} catch (UAQException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("pageMetadata", servicesCatalogPageVO.getPageMetadataVO());
			model.addAttribute("serviceMap", serviceMap);
			model.addAttribute("languageCode", languageCode);
			model.addAttribute("servicesCatalogPageVO", servicesCatalogPageVO);
			}

		else {
			view = "sites.services.list";
			Map<String, List<ServicesVO>> serviceMap = new HashMap<String, List<ServicesVO>>();
			servicesVO = new ServicesVO();
			servicesVO.setSite(site);
			servicesVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
			servicesVO.setLanguage(languageCode);
			try {
				serviceMap = servicesService.executeSites(servicesVO);
				super.handleDepartmentRequest(request, model, site);
			} catch (UAQException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("serviceMap", serviceMap);
			model.addAttribute("parent", parent);
		}

		model.addAttribute("pageMetadata", servicesCatalogPageVO.getPageMetadataVO());

		logger.debug("Multiticket is : " + multiticket);
		return view;
	}

	@RequestMapping(value = { ViewPath.SERVICES_CATALOG_PORTAL }, method = RequestMethod.GET)
	public String handleRequestPortalServices(HttpServletRequest request, ModelMap model) throws SSOException {

		String languageCode = request.getParameter("languageCode");
		String multiticket = (String) request.getSession().getAttribute("ticketid");

		String site = "uaq";
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

		logger.enter("Call Service for" + ViewPath.SERVICES_CATALOG);

		ServicesCatalogPageVO servicesCatalogPageVO = null;

		NavigationVO navigation;
		try {
			navigation = pageReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());
			servicesCatalogPageVO = ((ServicesCatalogPageService) servicesCatalogPageService).getPageVO(navigationVO);

		} catch (UAQException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String view = "services.list";
		super.handleRequest(request, model);
		
		Map<String, List<ServicesVO>> serviceMap = null;
		try {
			serviceMap = servicesService.getServices(languageCode);
		} catch (UAQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("servicesCatalogPageVO", servicesCatalogPageVO);
		model.addAttribute("serviceMap", serviceMap);
		model.addAttribute("languageCode", languageCode);
		model.addAttribute("pageMetadata", servicesCatalogPageVO.getPageMetadataVO());

		return view;
	}

}
