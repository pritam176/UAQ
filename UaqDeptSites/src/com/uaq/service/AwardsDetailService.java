package com.uaq.service;

import java.util.ArrayList;
import java.util.List;

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
import com.uaq.vo.AwardsVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;

/**
 * This class is used to manage departments related functionality
 * 
 * @author mraheem
 * 
 */
@Service(value = "awardsDetailService")
public class AwardsDetailService implements BaseService<NavigationVO, AwardsVO> {

	@Autowired
	@Qualifier("awardsDetailMapper")
	private BaseVOMapper mapper;

	@Autowired
	@Qualifier("imageMapper")
	private BaseVOMapper imageMapper;

	@Autowired
	@Qualifier("contentReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> contentReferneceDAO;

	protected static UAQLogger logger = new UAQLogger(AwardsDetailService.class);

	/**
	 * This method is not used
	 */
	@Override
	public AwardsVO execute(NavigationVO navigationVO) throws UAQException {
		AwardsVO awardsVOResult = null;
		try {

			NavigationVO navigation = contentReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());
			awardsVOResult = getAwardDetails(navigationVO);
		} catch (SSOException e) {
			logger.error("Error get Awards List");
		}
		return awardsVOResult;

	}

	/**
	 * This method is used to get details for the given award id
	 * 
	 * @param AwardsVO
	 * @return
	 * @throws SSOException
	 */

	private AwardsVO getAwardDetails(NavigationVO navigationVO) throws SSOException {
		AwardsVO awardsVOReturn;
		AssetBean assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_C", navigationVO.getAssetId(), navigationVO.getTicketId());
		List<ImageVO> images = new ArrayList<ImageVO>();
		awardsVOReturn = (AwardsVO) mapper.mapAssetToVO(assetBean);
		if (awardsVOReturn.getImages().size() > 0) {
			for (ImageVO imageVO : awardsVOReturn.getImages()) {
				assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), imageVO.getAssetType(), imageVO.getAssetId(), navigationVO.getTicketId());
				imageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
				images.add(imageVO);
			}

		}
		awardsVOReturn.setImages(images);
		return awardsVOReturn;
	}

	@Override
	public AwardsVO executeSites(NavigationVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
