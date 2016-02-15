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
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.ReportsVO;

/**
 * This class is used to manage departments related functionality
 * 
 * @author anair
 * 
 */
@Service(value = "reportsDetailService")
public class ReportDetailService implements BaseService<NavigationVO, ReportsVO> {

	@Autowired
	@Qualifier("reportsDetailMapper")
	private BaseVOMapper mapper;

	@Autowired
	@Qualifier("imageMapper")
	private BaseVOMapper imageMapper;

	@Autowired
	@Qualifier("contentReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> contentReferneceDAO;

	protected static UAQLogger logger = new UAQLogger(ReportDetailService.class);

	/**
	 * This method is not used
	 */
	@Override
	public ReportsVO execute(NavigationVO navigationVO) throws UAQException {
		ReportsVO reportsVOResult = null;
		try {

			NavigationVO navigation = contentReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());
			reportsVOResult = getReportDetails(navigationVO);
		} catch (SSOException e) {
			logger.error("Error get Reports List");
		}
		return reportsVOResult;

	}

	/**
	 * This method is used to get details for the given report id
	 * 
	 * @param ReportsVO
	 * @return
	 * @throws SSOException
	 */

	private ReportsVO getReportDetails(NavigationVO navigationVO) throws SSOException {
		ReportsVO reportsVOReturn;
		AssetBean assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_C", navigationVO.getAssetId(), navigationVO.getTicketId());
		List<ImageVO> images = new ArrayList<ImageVO>();
		reportsVOReturn = (ReportsVO) mapper.mapAssetToVO(assetBean);
		if (reportsVOReturn.getImages().size() > 0) {
			for (ImageVO imageVO : reportsVOReturn.getImages()) {
				assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), imageVO.getAssetType(), imageVO.getAssetId(), navigationVO.getTicketId());
				imageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
				images.add(imageVO);
			}

		}
		reportsVOReturn.setImages(images);
		return reportsVOReturn;
	}

	@Override
	public ReportsVO executeSites(NavigationVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
