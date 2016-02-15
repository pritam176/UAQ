package com.uaq.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.common.ViewPath;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.service.SiteMapService;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.SiteMapVO;

/**
 * @author anair
 * 
 *         Controller for SiteMap Page for
 * 
 *         a) Portal
 * 
 *         b) Department
 */
@Controller
public class SiteMapController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(SiteMapController.class);
	
	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> pageReferneceDAO;
	
	@Autowired
	@Qualifier("siteMapService")
	private BaseService<NavigationVO, SiteMapVO> siteMapService;
	
	/**
	 * Get the SiteMap Page Details
	 * 
	 * @return view the name of the JSP page
	 */
	@RequestMapping(value = ViewPath.SITEMAP, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site,HttpServletRequest request, ModelMap model) {
		
		logger.enter("SiteMap Page for " + site + "| handle Request");
		
		String view="";
		String pageName = "";
		String source = (String)request.getServletPath();
		if (source.contains("/")) {
			pageName = source.split("/")[3];
			pageName = (pageName.split(".html")[0]);
		}
		
		if(site.equals("uaq")){
			view="portal.sitemap";
			super.handleRequest(request, model);						
		}else{
			view="department.sitemap";
			super.handleDepartmentRequest(request, model, site);
		}
		
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setLanguage(request.getParameter("languageCode"));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setTicketId(multiticket);
		SiteMapVO sitemapVO = null;
		
		try{
			NavigationVO navigation = pageReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());
			
			sitemapVO =   ((SiteMapService) siteMapService).getPageVO(navigationVO);
		}catch (UAQException e) {
			logger.error(e.getMessage());
		}
		
		model.addAttribute("sitemapVO", sitemapVO);
		model.addAttribute("pageMetadata", sitemapVO.getPageMetadataVO());
		
		logger.exit("SiteMap Page for " + site + "| handle Request");
		
		return view;
	}

}
