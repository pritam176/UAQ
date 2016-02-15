package com.uaq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.LandingPageVO;
import com.uaq.vo.NavigationVO;

/**
 * Service class for login which validates the password provided by the user and
 * the one returned form the database.
 * 
 * @author nsharma
 * 
 */
@Service(value = "landingPageService")
public class LandingPageService implements BaseService<LandingPageVO, LandingPageVO> {

	protected static UAQLogger logger = new UAQLogger(LandingPageService.class);

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> webReferneceDAO;

	@Override
	public LandingPageVO execute(LandingPageVO inputObject) throws UAQException {
		LandingPageVO landingPageVO = new LandingPageVO();
		NavigationVO query = new NavigationVO();
		query.setName(inputObject.getName());
		query.setLanguage(inputObject.getLanguage());
		query.setSite(inputObject.getSite());

		NavigationVO navigationVO = webReferneceDAO.findByPrimaryKey(query);
		landingPageVO.setAssetId(navigationVO.getAssetId());
		logger.debug("Id of the landing Page is" + landingPageVO.getAssetId());
		return landingPageVO;
	}

	@Override
	public LandingPageVO executeSites(LandingPageVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
