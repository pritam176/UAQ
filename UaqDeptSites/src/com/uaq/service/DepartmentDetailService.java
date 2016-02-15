package com.uaq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.wem.sso.SSOException;
import com.uaq.common.AssetUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.DepartmentVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.ServicesVO;

/**
 * This class is used to manage departments related functionality
 * 
 * @author mraheem
 * 
 */
@Service(value = "departmentDetailService")
public class DepartmentDetailService implements BaseService<DepartmentVO, DepartmentVO> {

	@Autowired
	@Qualifier("departmentDetailMapper")
	private BaseVOMapper mapper;

	@Autowired
	@Qualifier("ServicesDetailMapper")
	private BaseVOMapper servicesDetailMapper;

	@Autowired
	@Qualifier("imageMapper")
	BaseVOMapper imageMapper;

	protected static UAQLogger logger = new UAQLogger(DepartmentDetailService.class);

	/**
	 * This method is not used
	 */
	@Override
	public DepartmentVO execute(DepartmentVO departmentVO) throws UAQException {
		String multiticket = departmentVO.getTicketId();
		String languageCode = departmentVO.getLanguage();
		AssetBean assetBean = null;
		List<ServicesVO> relatedServices = new ArrayList<ServicesVO>();
		ServicesVO servicesVO;
		List<ImageVO> departmentImageVO = null;
		try {
			assetBean = AssetUtil.getAssetDetail("uaq", "Content_C", departmentVO.getAssetId(), multiticket);

			departmentVO = (DepartmentVO) mapper.mapAssetToVO(assetBean);

			if (departmentVO.getImage() != null) {
				departmentImageVO = new ArrayList<ImageVO>();
				AssetBean imageAssetBean = AssetUtil.getAssetDetail("uaq", "Content_C", departmentVO.getImage(), multiticket);
				ImageVO images = getImageAsset(imageAssetBean);
				departmentImageVO.add(images);
				departmentVO.setImages(departmentImageVO);
				/*
				 * eventsImageVo.add(images); eventsVO.setImages(eventsImageVo);
				 */
			}

			AssetsBean assetsBean = AssetUtil.searchAssetbyDefinition("Content_C", "Service", departmentVO.getSite(), multiticket);
			List<AssetInfo> assetInfos = assetsBean.getAssetinfos();
			servicesVO = new ServicesVO();
			for (AssetInfo assetInfo : assetInfos) {
				servicesVO.setAssetId(assetInfo.getId().split(":")[1]);
				assetBean = AssetUtil.getAssetDetail(departmentVO.getSite(), "Content_C", assetInfo.getId().split(":")[1], multiticket);
				
				String locale="ar";
				
				if(assetBean.getDimensions().size()>0){
					locale = assetBean.getDimensions().get(0).getName();
				}
				if(languageCode.equalsIgnoreCase(locale)){
					servicesVO = (ServicesVO) servicesDetailMapper.mapAssetToVO(assetBean);
					servicesVO.setSite(departmentVO.getSite());
					servicesVO.setUrl(UrlTransliterationUtil.getString(servicesVO.getName()));
					relatedServices.add(servicesVO);
				}
			}

		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		departmentVO.setServices(relatedServices);
		return departmentVO;
	}

	// TODO: Generalize this function
	private ImageVO getImageAsset(AssetBean assetBean) {
		ImageVO newsImageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
		return newsImageVO;
	}

	@Override
	public DepartmentVO executeSites(DepartmentVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
