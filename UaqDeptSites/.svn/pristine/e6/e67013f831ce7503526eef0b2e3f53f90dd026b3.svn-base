package com.uaq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.wem.sso.SSOException;
import com.uaq.common.AssetUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.ServicesVO;

@Service(value = "serviceDetailService")
public class ServicesDetailService implements BaseService<NavigationVO, ServicesVO> {

	@Autowired
	@Qualifier("ServicesDetailMapper")
	private BaseVOMapper mapper;

	@Autowired
	@Qualifier("contentReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> contentReferneceDAO;

	protected static UAQLogger logger = new UAQLogger(ServicesDetailService.class);

	@Override
	public ServicesVO execute(NavigationVO navigationVO) throws UAQException {
		ServicesVO serviceVOResult = null;
		NavigationVO navigation = contentReferneceDAO.findByPrimaryKey(navigationVO);
		navigationVO.setAssetId(navigation.getAssetId());
		serviceVOResult = getServiceDetails(navigationVO);
		return serviceVOResult;
	}

	private ServicesVO getServiceDetails(NavigationVO navigationVO) {

		ServicesVO servicesVOReturn = null;
		try {
			AssetBean assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_C", navigationVO.getAssetId(), navigationVO.getTicketId());
			servicesVOReturn = (ServicesVO) mapper.mapAssetToVO(assetBean);
			servicesVOReturn.setSite(navigationVO.getSite());
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stuba
		return servicesVOReturn;
	}

	@Override
	public ServicesVO executeSites(NavigationVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
