package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.SearchCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.service.GenericPageService;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.DepartmentVO;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.NavigationVO;

/**
 * @author raheem
 * 
 *         Controller for Department Page
 */
@Controller
public class DepartmentsController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(DepartmentsController.class);

	@Autowired
	@Qualifier("departmentsService")
	private BaseService<DepartmentVO, List<DepartmentVO>> departmentsService;

	@Autowired
	@Qualifier("departmentDetailService")
	private BaseService<DepartmentVO, DepartmentVO> departmentDetailService;

	@Autowired
	@Qualifier("contentReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> contentReferneceDAO;
	
	@Autowired
	@Qualifier("genericPageService")
	BaseService<NavigationVO, GenericPageVO> genericPageService;
	
	@Autowired
	private MessageSource messageSource;

	/**
	 * Get the Department Details Page
	 * 
	 * @return view the name of the JSP page
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = ViewPath.DEPARTMENTS_LISTING, method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request, ModelMap modelMap) {

		logger.enter("Get Department Detail | handle Request");

		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter("languageCode");
		String view = "departments.list";
		String sector = "uaq";
		List<DepartmentVO> departments = null;
		
		String pageName = "";
		String source = (String)request.getServletPath();
		if (source.contains("/")) {
			pageName = source.split("/")[3];
			pageName = (pageName.split(".html")[0]);
		}

		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(sector);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setLanguage(request.getParameter("languageCode"));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		GenericPageVO genericPageVO = null;

		DepartmentVO departmentsVO = new DepartmentVO();
		departmentsVO.setSite(PropertiesUtil.getProperty(sector + "_" + "csSiteName"));
		departmentsVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		departmentsVO.setLanguage(languageCode);

		Map<String, List<NavigationVO>> navigationMAP = (Map<String, List<NavigationVO>>) modelMap.get("navigationList");

		List<NavigationVO> navigationList = navigationMAP.get(languageCode);
		NavigationVO departmentNavigationListVO = navigationList.get(0);

		try {
			departments = departmentsService.execute(departmentsVO);
			
			// load page metaData
			genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);

		} catch (UAQException e) {
			logger.error(e.getMessage());
		}

		modelMap.addAttribute("departments", departments);
		modelMap.addAttribute("departmentNavigationListVO", departmentNavigationListVO);
		modelMap.addAttribute("departmentsJSON", getJSONObject(departments, languageCode));
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());

		logger.exit("Landing Page | handle Request");

		return view;
	}

	private Map<String, String> getMapFromDepartmentList(List<DepartmentVO> departments, String languageCode) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (departments != null && departments.size() > 0) {
			for (DepartmentVO dept : departments) {				
				
				if (languageCode.equals("en")) {
					resultMap.put(dept.getName(), dept.getDepartmentNameEN());
				} else {
					resultMap.put(dept.getName(), dept.getDepartmentNameAR());
				}
			}
		}
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = ViewPath.DEPARTMENT_DETAIL, method = { RequestMethod.GET, RequestMethod.POST })
	public String departmentsDetail(@ModelAttribute("searchCommand") SearchCommand searchCommand, @PathVariable("name") String name, HttpServletRequest request, ModelMap modelMap) throws UAQException {

		logger.enter("Get Department Detail | handle Request");

		super.handleRequest(request, modelMap);
		String view = "department.detail";
		String site = "uaq";
		String pageName="department";
		String languageCode = request.getParameter("languageCode");
		Locale locale = new Locale(languageCode);
		DepartmentVO departmentVO = new DepartmentVO();
		List<DepartmentVO> departments = null;
		
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(name));
		navigationVO.setLanguage(request.getParameter("languageCode"));

		NavigationVO navigation = contentReferneceDAO.findByPrimaryKey(navigationVO);
		departmentVO.setAssetId(navigation.getAssetId());
		departmentVO.setSite(site);
		departmentVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		Map<String, String> departmentMap = null;

		searchCommand.setLanguage(languageCode);
		departmentVO.setLanguage(languageCode);
		departmentVO = departmentDetailService.execute(departmentVO);
		departmentVO.setLanguage(languageCode);
		departmentVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		departmentVO.setName(messageSource.getMessage(departmentVO.getSite(), null, locale));
		departments = departmentsService.execute(departmentVO);
		departmentMap = getMapFromDepartmentList(departments, languageCode);
		
		// load page metaData
		GenericPageVO genericPageVO = null;
		navigationVO = null; // to avoid cache load
		navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setLanguage(request.getParameter("languageCode"));
		genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);

		Map<String, List<NavigationVO>> navigationMAP = (Map<String, List<NavigationVO>>) modelMap.get("navigationList");
		List<NavigationVO> navigationList = navigationMAP.get(languageCode);
		NavigationVO departmentNavigationListVO = navigationList.get(0);

		modelMap.addAttribute("departmentVO", departmentVO);
		modelMap.addAttribute("searchCommand", searchCommand);
		modelMap.addAttribute("departmentMap", departmentMap);
		modelMap.addAttribute("departmentNavigationListVO", departmentNavigationListVO);
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());

		logger.exit("Get Department Detail | handle Request");

		return view;
	}

	private String getJSONObject(List<DepartmentVO> departments, String language) {
		Locale locale = new Locale(language);
		JSONObject jsonObjectOuter = new JSONObject();
		JSONObject innerObject;
		StringBuffer description;
		int i = 0;
		if (null != departments && departments.size() > 0) {
			// Centering the map location to first result of the Search Results
			for (DepartmentVO departmentVO : departments) {
				description = new StringBuffer();
				i = i + 1;
				innerObject = new JSONObject();
				innerObject.put("title", (language.equals("en")) ? departmentVO.getDepartmentNameEN() : departmentVO.getDepartmentNameAR());
				JSONObject mapObject = new JSONObject();
				mapObject.put("lat", departmentVO.getLatitude());
				mapObject.put("lon", departmentVO.getLongitude());
				innerObject.put("coordinates", mapObject);
				description.append(departmentVO.getDepartmentNameEN());
				innerObject.put("description", description.toString());
				JSONObject linkObject = new JSONObject();
				linkObject.put("url", departmentVO.getUrl());
				linkObject.put("title", messageSource.getMessage("map.view.more", null, locale));
				linkObject.put("caption", departmentVO.getDepartmentNameEN());
				innerObject.put("link", linkObject);
				jsonObjectOuter.put("id_" + i, innerObject);
			}

		}
		return jsonObjectOuter.toString();
	}
}
