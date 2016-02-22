/**
 * 
 */
package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.SearchCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.exception.UAQException;
import com.uaq.service.ArchaeologyHeritageService;
import com.uaq.service.BaseService;
import com.uaq.service.GenericPageService;
import com.uaq.util.SessionUtil;
import com.uaq.util.StringUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.ArchaeologyHeritageVO;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.SearchResponseVO;

/**
 * @author TACME
 *
 */

@Controller
public class ArchaeologyHeritageController extends BaseController {
	
	@Autowired
	@Qualifier("archaeologyHeritageService")
	BaseService<ArchaeologyHeritageVO, SearchResponseVO>archaeologyHeritageService;
	
	
	@Autowired
	@Qualifier("genericPageService")
	BaseService<NavigationVO, GenericPageVO> genericPageService;
	
	@RequestMapping(value = { ViewPath.ARCHAEOLOGICAL_LISTING_SITES,ViewPath.MUSEUMS_LISTING_SITES }, method = RequestMethod.GET)
	public String handleRequest(@PathVariable("site") String site,@PathVariable("landing")String landing,HttpServletRequest request, ModelMap modelMap,@ModelAttribute("searchCommand") SearchCommand searchCommand) throws UAQException {

		logger.enter("Get Archaeological Detail | handle Request");
		super.handleDepartmentRequest(request, modelMap, site);
		String languageCode = request.getParameter("languageCode");
		String SITE = "aah";
		
		String source = StringUtil.getString(request.getServletPath());
		String pageName="";
		if (source.contains("/")) {
			landing = source.split("/")[0];
			pageName = source.split("/")[1];
		}
		
		
		
		String VIEWNAME="sites.archaeological.list";
		Integer currentPage = 1;
		ArchaeologyHeritageVO archaeologyHeritageVO = new ArchaeologyHeritageVO();
		archaeologyHeritageVO.setSite(SITE);
		archaeologyHeritageVO.setTicketId(multiticket);
		archaeologyHeritageVO.setAssetType("ArchSites_D");
		archaeologyHeritageVO.setLanguage(languageCode);
		
		if(pageName.equals("museums.html")){
			archaeologyHeritageVO.setAssetType("Museum_D");
		}
		
		SearchResponseVO archaeologyHeritageResponseVO 	=	archaeologyHeritageService.execute(archaeologyHeritageVO);

		
		
		searchCommand.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		searchCommand.setSite(PropertiesUtil.getProperty(site + "_" + "csSiteName"));
		searchCommand.setLanguage(languageCode);
		
		if (request.getParameter("currentPage") != null && !request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.valueOf(request.getParameter("currentPage"));
			SessionUtil.updateSearchCommandWithFilters(request, searchCommand);
		} else {
			SessionUtil.removeSearchFilters(request);
		}
		searchCommand.setCurrentPage(currentPage);
		
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setTicketId((String) request.getSession().getAttribute(SESSION_TICKET));
		navigationVO.setName(UrlTransliterationUtil.getString("Archaeological Sites") );
		navigationVO.setLanguage(languageCode);
		GenericPageVO genericPageVO = null;
		
		if(pageName.equals("museums.html")){
			navigationVO.setName(UrlTransliterationUtil.getString("Museums") );
		}
		
		// load page metaData
		genericPageVO = ((GenericPageService)genericPageService).getPageMetaData(navigationVO);
		
		//Pagination
		archaeologyHeritageResponseVO =((ArchaeologyHeritageService) archaeologyHeritageService).setPaginagetion(archaeologyHeritageResponseVO, searchCommand);

		modelMap.addAttribute("searchResponse", archaeologyHeritageResponseVO);
		modelMap.addAttribute("pageMetadata", genericPageVO.getPageMetadataVO());
		
		if(pageName.equals("museums.html")){
			modelMap.addAttribute("pageName","Museums");
			modelMap.addAttribute("parent","museums");
			modelMap.addAttribute("url",pageName);
		}else{
			modelMap.addAttribute("pageName","ArchaeologicalSites");
			modelMap.addAttribute("parent","archaeology");
			modelMap.addAttribute("url",pageName);
		}
		
		
		return VIEWNAME;
		
	}

}
