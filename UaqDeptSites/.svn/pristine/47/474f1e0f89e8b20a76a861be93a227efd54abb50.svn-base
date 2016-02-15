package com.uaq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.wem.sso.SSOException;
import com.uaq.common.AssetUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.SiteMapVO;

/**
 * 
 * @author ajain
 * 
 *         Service Class for the Careers page.
 */

@Service(value = "siteMapService")
public class SiteMapService implements BaseService<NavigationVO, SiteMapVO>	{

	protected static UAQLogger logger = new UAQLogger(SiteMapService.class);
	
	@Autowired
	@Qualifier("siteMapPageVOMapper")
	private BaseVOMapper siteMapPageVOMapper;
	
	@Override
	public SiteMapVO execute(NavigationVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SiteMapVO executeSites(NavigationVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	public SiteMapVO getPageVO(NavigationVO navigationVO) {
		
		logger.enter("SiteMapService");
		SiteMapVO siteMapVO = null;
		
		try {
			AssetBean assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Page", navigationVO.getAssetId(), navigationVO.getTicketId());
			siteMapVO = (SiteMapVO) siteMapPageVOMapper.mapAssetToVO(assetBean);
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return siteMapVO;
	}
	
}
