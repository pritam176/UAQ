package com.uaq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.NavigationVO;

/**
 * @author nsharma
 * 
 */
@Service(value = "changeLanguageService")
public class ChangeLanguageService implements BaseService<NavigationVO, NavigationVO> {

	public static transient UAQLogger logger = new UAQLogger(ChangeLanguageService.class);

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> pageReferneceDAO;

	@Autowired
	@Qualifier("changeLanguageDAO")
	GenericDao<NavigationVO, NavigationVO> changeLanguageDAO;

	@Override
	public NavigationVO execute(NavigationVO inputObject) throws UAQException {
		NavigationVO response = null;
		response = pageReferneceDAO.findByPrimaryKey(inputObject);
		if (null == response) {
			response = changeLanguageDAO.findByPrimaryKey(inputObject);
		}
		return response;

	}

	@Override
	public NavigationVO executeSites(NavigationVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
