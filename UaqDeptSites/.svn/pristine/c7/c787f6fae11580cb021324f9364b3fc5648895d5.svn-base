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
import com.uaq.vo.CertificatesVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;

/**
 * This class is used to manage departments related functionality
 * 
 * @author anair
 * 
 */
@Service(value = "certificatesDetailService")
public class CertificateDetailService implements BaseService<NavigationVO, CertificatesVO> {

	@Autowired
	@Qualifier("certificatesDetailMapper")
	private BaseVOMapper mapper;

	@Autowired
	@Qualifier("imageMapper")
	private BaseVOMapper imageMapper;

	@Autowired
	@Qualifier("contentReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> contentReferneceDAO;

	protected static UAQLogger logger = new UAQLogger(CertificateDetailService.class);

	/**
	 * This method is not used
	 */
	@Override
	public CertificatesVO execute(NavigationVO navigationVO) throws UAQException {
		CertificatesVO certificatesVOResult = null;
		try {

			NavigationVO navigation = contentReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());
			certificatesVOResult = getCertificateDetails(navigationVO);
		} catch (SSOException e) {
			logger.error("Error get Certificates List");
		}
		return certificatesVOResult;

	}

	/**
	 * This method is used to get details for the given certificate id
	 * 
	 * @param CertificatesVO
	 * @return
	 * @throws SSOException
	 */

	private CertificatesVO getCertificateDetails(NavigationVO navigationVO) throws SSOException {
		CertificatesVO certificatesVOReturn;
		AssetBean assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_C", navigationVO.getAssetId(), navigationVO.getTicketId());
		List<ImageVO> images = new ArrayList<ImageVO>();
		certificatesVOReturn = (CertificatesVO) mapper.mapAssetToVO(assetBean);
		if (certificatesVOReturn.getImages().size() > 0) {
			for (ImageVO imageVO : certificatesVOReturn.getImages()) {
				assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), imageVO.getAssetType(), imageVO.getAssetId(), navigationVO.getTicketId());
				imageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
				images.add(imageVO);
			}

		}
		certificatesVOReturn.setImages(images);
		return certificatesVOReturn;
	}

	@Override
	public CertificatesVO executeSites(NavigationVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
