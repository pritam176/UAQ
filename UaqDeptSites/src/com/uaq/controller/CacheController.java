package com.uaq.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.Logincommand;
import com.uaq.command.SearchCommand;
import com.uaq.common.ViewPath;
import com.uaq.dao.GenericDao;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.NavigationVO;

@Controller
public class CacheController {
	public static transient UAQLogger logger = new UAQLogger(CacheController.class);

	@Autowired
	@Qualifier("navigationDAO")
	GenericDao<NavigationVO, NavigationVO> navigationDAO;

	@Autowired
	@Qualifier("localizedNavigationDAO")
	GenericDao<NavigationVO, NavigationVO> localizedNavigationDAO;

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> webReferneceDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.ParameterizableViewController#
	 * handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@RequestMapping(value = ViewPath.FLUSH_CACHE, method = RequestMethod.GET)
	protected String handleRequestInternal(HttpServletRequest request, ModelMap modelMap) throws Exception {
		modelMap.addAttribute("searchCommand", new SearchCommand());
		modelMap.addAttribute("logincommand", new Logincommand());
		navigationDAO.delete(new NavigationVO());
		webReferneceDAO.delete(new NavigationVO());
		localizedNavigationDAO.delete(new NavigationVO());
		return "cachecleared";
	}

}
