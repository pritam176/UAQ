package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.wem.sso.SSOException;
import com.uaq.command.Logincommand;
import com.uaq.command.SearchCommand;
import com.uaq.common.ApplicationConstants;
import com.uaq.common.AssetUtil;
import com.uaq.common.PropertiesUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.service.NavigationService;
import com.uaq.service.PSRequestService;
import com.uaq.service.PortalUtil;
import com.uaq.util.StringUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.DepartmentVO;
import com.uaq.vo.DetailPageVO;
import com.uaq.vo.HomeVO;
import com.uaq.vo.LandingPageVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.ServicesVO;

/**
 * This class is used as a base controller for all other controllers. This class
 * holds all common tasks for other controllers.
 * 
 * @author mraheem
 * 
 */
public class BaseController {

	public static transient UAQLogger logger = new UAQLogger(BaseController.class);

	@Autowired
	@Qualifier("navigationService")
	NavigationService navigationService;

	@Autowired
	@Qualifier("homeService")
	private BaseService<HomeVO, HomeVO> homeService;

	@Autowired
	public MessageSource messageSource;

	String multiticket;

	@Autowired
	@Qualifier("landingPageService")
	BaseService<LandingPageVO, LandingPageVO> landingPageService;

	@Autowired
	@Qualifier("detailPageService")
	BaseService<DetailPageVO, DetailPageVO> detailPageService;

	@Autowired
	@Qualifier("contentReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> contentReferneceDAO;

	@Autowired
	@Qualifier("departmentDetailService")
	private BaseService<DepartmentVO, DepartmentVO> departmentDetailService;

	@Autowired
	@Qualifier("departmentDetailMapper")
	private BaseVOMapper departmentDetailMapper;
	
	@Autowired
	private PSRequestService pSRequestService;
	
	@Autowired
	private PortalUtil portalUtil;

	/**
	 * This method handles the incoming requests, in need of common tasks, for
	 * all other controllers
	 * 
	 * @param request
	 * @param model
	 * @return view which is null
	 * 
	 */
	public String handleRequest(HttpServletRequest request, ModelMap model) {
		multiticket = (String) request.getSession().getAttribute(SESSION_TICKET);
		Map<String, List<NavigationVO>> navigationMAP = null;
		String parentPageName = null;
		String sourceURL = null;
		String source = StringUtil.getString(request.getServletPath());
		String paramList="";
		
		//Added for request parameter
		 Enumeration<String> parameterNames = request.getParameterNames();
		 while(parameterNames.hasMoreElements()){
			 String paramName = (String)parameterNames.nextElement();
			 //for My request Action on Button language change
			 if(paramName.equals("serviceId") || paramName.equals("requestNo") || paramName.equals("statusId") || paramName.equals("typeOfUser") 
					 || paramName.equals("letter") || paramName.equals("id") || paramName.equals("feeId") || paramName.equals("customerId")
					 || paramName.equals("customerName") || paramName.equals("languageCode") || paramName.equals("returnUrl") || paramName.equals("isMobile")){
			 paramList+="&"+paramName+"="+request.getParameter(paramName);
			 }
			}

		try {
			navigationService.setSite("uaq");
			navigationService.setTicketId(multiticket);
			navigationMAP = navigationService.getPortalNavigationDetails("uaq", multiticket);

			sourceURL = PropertiesUtil.getProperty("uaq" + ".url") + "/" + request.getParameter("languageCode") + "/" + source;

			if (source.contains("/")) {
				parentPageName = source.split("/")[0];
				if (source.split("/").length == 3 || source.split("/").length == 4) {
					List<NavigationVO> navigations = navigationMAP.get(request.getParameter("languageCode"));
					String landingPage = source.split("/")[1];
					DetailPageVO detailPageVO = new DetailPageVO();
					detailPageVO = getThirdLevelNavigations(request, navigations, detailPageVO, landingPage);
					if (detailPageVO != null) {
						model.addAttribute("pageMetadata", detailPageVO.getPageMetadataVO());
					}
					model.addAttribute("detailPageVO", detailPageVO);
				}
			}

			if (null != parentPageName && !parentPageName.equals("content") && !parentPageName.equals("news") && !parentPageName.equals("award")) {
				LandingPageVO landingPageVO = new LandingPageVO();
				landingPageVO.setName(UrlTransliterationUtil.getString(parentPageName));
				landingPageVO.setSite("uaq");
				landingPageVO.setLanguage(request.getParameter("languageCode"));

				landingPageVO = landingPageService.execute(landingPageVO);

				for (NavigationVO navigationVO : navigationMAP.get(request.getParameter("languageCode"))) {
					if (landingPageVO.getAssetId().equals(navigationVO.getAssetId())) {
						model.addAttribute("parentLandingPage", navigationVO);
						logger.debug("Matched asset Id is " + navigationVO.getAssetId() + " Name of the asset is :" + navigationVO.getName());
						break;
					}
				}
			}
			model.addAttribute("department", getDepartment(request.getParameter("languageCode"), multiticket));
		} catch (SSOException e) {
			logger.error("Error while getting the navigation details" + e.getMessage());
		} catch (UAQException e) {
			logger.error("Error while getting the navigation details" + e.getMessage());
		}
		LoginOutputVO logininfo = (LoginOutputVO) request.getSession().getAttribute(ApplicationConstants.SESSION_LOGIN_INFO_PORTAL);
		if (logininfo != null && !logininfo.getAcountId().isEmpty()) {
			logger.info("loginInfo="+logininfo.toString());
			AccountDetailTokenOutputVO accountDetailfromToken =portalUtil.getAccountDetailForMobile(logininfo);
			if(accountDetailfromToken!=null && accountDetailfromToken.getAccountId()!= null ){
				model.addAttribute("loginstatus", logininfo.getExecutionStatus());
				String userName=accountDetailfromToken.getFirstName().trim().split(" ")[0];
				model.addAttribute("UserName", userName);
			}
			
		} else {
			logger.info("loginInfo is Null.Session is Invalided SOme where");
			model.addAttribute("loginstatus", "Failure");
		}

		Logincommand logincommand = new Logincommand();

		model.addAttribute("sourceURL", sourceURL);
		model.addAttribute("navigationList", navigationMAP);
		model.addAttribute("source", source);
		model.addAttribute("searchCommand", new SearchCommand());
		model.addAttribute("logincommand", logincommand);
		model.addAttribute("paramList", paramList);

		return navigationService.getHomeId();
	}

	/**
	 * This method handles the incoming requests, in need of common tasks, for
	 * all other controllers
	 * 
	 * @param request
	 * @param model
	 * @return view which is null
	 * 
	 */
	public String handleDepartmentRequest(HttpServletRequest request, ModelMap model, String site) {
		Map<String, List<NavigationVO>> navigationMAP = null;
		String parentPageName = null;
		String source = StringUtil.getString(request.getServletPath());
		multiticket = (String) request.getSession().getAttribute(SESSION_TICKET);
		navigationService.setSite(site);
		navigationService.setTicketId(multiticket);

		String sourceURL = PropertiesUtil.getProperty(site + ".url") + "/" + request.getParameter("languageCode") + "/" + source;

		Map<String, String> socialLinks = null;

		socialLinks = getSocialLinks(site, multiticket, request.getParameter("languageCode"));

		try {
			model.addAttribute("sourceURL", sourceURL);
			navigationMAP = navigationService.getSitesNavigationDetails(site, multiticket);
			
			//TODO:will revisit this
			List<NavigationVO> navigationList = navigationMAP.get(request.getParameter("languageCode"));
			for(NavigationVO navigationVO : navigationList){
				if(navigationVO.getName().equals("Archaeological Sites")){
					for(NavigationVO association : navigationVO.getAssociations()){
						association.setUrl(association.getUrl().replace("archsites_d", "archaeology"));
					}
				}
				if(navigationVO.getName().equals("Museums")){
					for(NavigationVO association : navigationVO.getAssociations()){
						association.setUrl(association.getUrl().replace("museum_d", "museums"));
					}
				}
			}
			
			model.addAttribute("navigations", navigationMAP);
			if (source.contains("/")) {
				parentPageName = source.split("/")[0];
			}

			if (null != parentPageName && !parentPageName.equals("content")) {
				LandingPageVO landingPageVO = new LandingPageVO();
				landingPageVO.setName(UrlTransliterationUtil.getString(parentPageName));
				landingPageVO.setSite(site);
				landingPageVO.setLanguage(request.getParameter("languageCode"));

				landingPageVO = landingPageService.execute(landingPageVO);

				for (NavigationVO navigationVO : navigationMAP.get(request.getParameter("languageCode") + "-siteplan")) {
					if (landingPageVO.getAssetId().equals(navigationVO.getAssetId())) {
						model.addAttribute("parentLandingPage", navigationVO);
						logger.debug("Matched asset Id is " + navigationVO.getAssetId() + " Name of the asset is :" + navigationVO.getName());
						break;
					}
				}

			}

		} catch (Exception e) {
			logger.error("Error while getting the parent Object" + e.getMessage());
		}

		if (null != socialLinks && !socialLinks.isEmpty())
			model.addAttribute("socialLinks", socialLinks);
		model.addAttribute("source", source);
		model.addAttribute("searchCommand", new SearchCommand());
		model.addAttribute("site", site);

		HomeVO homeVO = new HomeVO();
		homeVO.setAssetId(navigationService.getHomeSiteId());
		homeVO.setSite(site);
		homeVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		homeVO.setLanguage(request.getParameter("languageCode"));
		try {
			homeVO = homeService.executeSites(homeVO);
			model.addAttribute("homeVO", homeVO);
		} catch (UAQException e) {
			logger.error("Error while getting the Home Page for UAQ" + e.getMessage());
		}

		return navigationService.getHomeSiteId();
	}

	private Map<String, String> getSocialLinks(String site, String multiticket2, String language) {

		Map<String, String> socialLinks = new HashMap<String, String>();
		DepartmentVO deptVO = new DepartmentVO();
		try {
			String departmentAssetId = null;
			AssetsBean departmentAssets = AssetUtil.searchAssetbyDefinition("Content_C", "Department", site, multiticket2);
			AssetBean assetBean = new AssetBean();

			for (AssetInfo departmentAssetInfo : departmentAssets.getAssetinfos()) {
				deptVO = new DepartmentVO();
				departmentAssetId = departmentAssetInfo.getId().split(":")[1];

				deptVO.setAssetId(departmentAssetId);
				deptVO.setTicketId(multiticket2);

				assetBean = AssetUtil.getAssetDetail(site, "Content_C", departmentAssetId, multiticket2);
				deptVO = (DepartmentVO) departmentDetailMapper.mapAssetToVO(assetBean);
				
			}
			
			if(null!=deptVO.getDepartmentNameEN() && language.equals("en")){
				socialLinks.put("deptName", deptVO.getDepartmentNameEN());
				}
				
				if(null!=deptVO.getDepartmentNameAR() && language.equals("ar")){
				socialLinks.put("deptName", deptVO.getDepartmentNameAR());
				}

		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		if ((site.equals("egd") && deptVO.getName().equalsIgnoreCase("E Government Department")) || (site.equals("mun") && deptVO.getName().equalsIgnoreCase("Municipality of Umm Al Quwain"))
				|| (site.equals("ded") && deptVO.getName().equalsIgnoreCase("Department of Economic Development"))
				|| (site.equals("lap") && deptVO.getName().equalsIgnoreCase("Land & Properties"))
				|| (site.equals("pas") && deptVO.getName().equalsIgnoreCase("Department of Planning & Survey"))
				|| (site.equals("pws") && deptVO.getName().equalsIgnoreCase("Public Works & Services Department"))
				|| (site.equals("aah") && deptVO.getName().equalsIgnoreCase("Department of Archaeology & Heritage"))
				|| (site.equals("dfm") && deptVO.getName().equalsIgnoreCase("Falaj Municipality"))
				|| (site.equals("ica") && deptVO.getName().equalsIgnoreCase("Industrial City Authority"))
				|| (site.equals("pcf") && deptVO.getName().equalsIgnoreCase("Ports & Customs FreeZone"))
				|| (site.equals("dec") && deptVO.getName().equalsIgnoreCase("Executive Council"))
				|| (site.equals("faa") && deptVO.getName().equalsIgnoreCase("Department of Finance & Administration"))
				|| (site.equals("dfa") && deptVO.getName().equalsIgnoreCase("Financial Auditing"))) {

			socialLinks.put("youtubeContact", deptVO.getYoutubeContact());
			socialLinks.put("twitterContact", deptVO.getTwitterContact());
			socialLinks.put("linkedInContact", deptVO.getLinkedInContact());
			socialLinks.put("facebookContact", deptVO.getFacebookContact());
			socialLinks.put("instagramContact", deptVO.getInstagramContact());
		}
		return socialLinks;
	}

	/**
	 * @return the messageSource
	 */
	public MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * DetailPageVo for Portal events
	 * 
	 * @param request
	 * @param modelMap
	 * @param detailPageVO
	 * @return
	 */
	private DetailPageVO getThirdLevelNavigations(HttpServletRequest request, List<NavigationVO> navigations, DetailPageVO detailPageVO, String landingPage) {

		detailPageVO.setName(UrlTransliterationUtil.getString(landingPage));
		detailPageVO.setSite("uaq");
		detailPageVO.setTicketId(multiticket);
		detailPageVO.setLanguage(request.getParameter("languageCode"));

		try {
			detailPageVO = detailPageService.execute(detailPageVO);

			if (detailPageVO != null) {
				for (NavigationVO navigationVO : navigations) {
					for (NavigationVO thirdLeveLNavigationVO : navigationVO.getNavigations()) {
						if (detailPageVO.getAssetId().equals(thirdLeveLNavigationVO.getAssetId())) {
							detailPageVO.setNavigations(thirdLeveLNavigationVO.getNavigations());
						}

					}
				}
			}
		} catch (UAQException e) {
			logger.error("Error while getting Detail Page Details" + e.getMessage());
		}
		return detailPageVO;
	}

	DepartmentVO getDepartment(String languageCode, String ticketId) {

		String site = "uaq";
		DepartmentVO departmentVO = new DepartmentVO();
		SearchCommand searchCommand = new SearchCommand();
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId(ticketId);
		navigationVO.setName(UrlTransliterationUtil.getString("UmmAlQuwain"));
		navigationVO.setLanguage(languageCode);

		NavigationVO navigation;

		try {
			navigation = contentReferneceDAO.findByPrimaryKey(navigationVO);

			departmentVO.setAssetId(navigation.getAssetId());
			departmentVO.setSite(site);
			departmentVO.setTicketId(ticketId);

			searchCommand.setLanguage(languageCode);
			departmentVO.setLanguage(languageCode);
			departmentVO = departmentDetailService.execute(departmentVO);
		} catch (UAQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return departmentVO;
	}
	
	public String getDepartmentServicesJSON(String locale){
		JSONObject departmentServicesJSON =null;
		try {
			Map<String, List<ServicesVO>> departmentServicesMap =	pSRequestService.getServices(locale);
			 departmentServicesJSON = JSONObject.fromObject(departmentServicesMap);
		} catch (UAQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return departmentServicesJSON.toString();
	}

}
