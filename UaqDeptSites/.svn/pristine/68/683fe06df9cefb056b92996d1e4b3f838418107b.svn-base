/**
 * 
 */
package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.SESSION_TICKET;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.common.ViewPath;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.BaseService;
import com.uaq.service.ServicesCatalogPageService;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.FaqVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.ServicesCatalogPageVO;

/**
 * @author Akhil
 * 
 */
@Controller
public class FAQController extends BaseController {

	public static transient UAQLogger uaqLogger = new UAQLogger(FAQController.class);

	@Autowired
	@Qualifier("faqService")
	BaseService<FaqVO, List<FaqVO>> faqService;
	
	@Autowired
	@Qualifier("servicesCatalogPageService")
	BaseService<NavigationVO, ServicesCatalogPageVO> servicesCatalogPageService;
	
	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> pageReferneceDAO;
	
	@RequestMapping(value = ViewPath.FAQ_LISTING, method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request, ModelMap model) {
		
		List<FaqVO> faqList = new ArrayList<FaqVO>();
		ServicesCatalogPageVO servicesCatalogPageVO = null;
		NavigationVO navigation = null;
		String pageName = "FAQ";
		String site = "uaq";
		
		String view = "faq.list";
		String ticketId = (String) request.getSession().getAttribute(SESSION_TICKET);
		super.handleRequest(request, model);
		FaqVO faqVO = new FaqVO();
		faqVO.setTicketId(ticketId);
		
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setSite(site);
		navigationVO.setLanguage(request.getParameter("languageCode"));
		navigationVO.setName(UrlTransliterationUtil.getString(pageName));
		navigationVO.setTicketId(multiticket);
		
		try {
			faqList = faqService.execute(faqVO);
			filterOnLanguage(faqList, request.getParameter("languageCode"));
			
			// This is to fetch help article from the page
			navigation = pageReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());
			servicesCatalogPageVO = ((ServicesCatalogPageService)servicesCatalogPageService).getPageVO(navigationVO);
						
		} catch (UAQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("faqList", faqList);
		model.addAttribute("servicesCatalogPageVO", servicesCatalogPageVO);
		model.addAttribute("pageMetadata", servicesCatalogPageVO.getPageMetadataVO());
		
		return view;

	}

	private void filterOnLanguage(List<FaqVO> faqList, String languageCode) {
		
		Iterator<FaqVO> itr = faqList.iterator();
		while(itr.hasNext()){
			FaqVO faq = itr.next();
			if(!faq.getLanguage().equals(languageCode)){
				itr.remove();
			}
		}
	}

}