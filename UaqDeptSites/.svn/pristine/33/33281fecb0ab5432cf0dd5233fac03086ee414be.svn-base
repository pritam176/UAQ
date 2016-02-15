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
import com.uaq.vo.DepartmentVO;
import com.uaq.vo.ImageVO;

/**
 * This class is used to manage departments related functionality
 * 
 * @author mraheem
 * 
 */
@Service(value = "departmentsService")
public class DepartmentsService implements BaseService<DepartmentVO, List<DepartmentVO>> {

	@Autowired
	@Qualifier("departmentDetailMapper")
	private BaseVOMapper mapper;

	@Autowired
	@Qualifier("imageMapper")
	BaseVOMapper imageMapper;

	protected static UAQLogger logger = new UAQLogger(DepartmentsService.class);

	/**
	 * This method is not used
	 */
	@Override
	public List<DepartmentVO> execute(DepartmentVO departmentVO) throws UAQException {
		AssetsBean departmentsAssets;
		List<DepartmentVO> departmentsList;
		List<ImageVO> departmentImageVO = null;
		try {

			departmentsAssets = AssetUtil.searchAssetbyDefinition("Content_C", "Department", "uaq", departmentVO.getTicketId());
			departmentsList = getDepartmentsList(departmentsAssets, departmentVO);

			for (DepartmentVO department : departmentsList) {
				if (department.getImage() != null) {
					departmentImageVO = new ArrayList<ImageVO>();
					AssetBean assetBean = AssetUtil.getAssetDetail("uaq", "Content_C", department.getImage(), departmentVO.getTicketId());
					ImageVO images = getImageAsset(assetBean);
					departmentImageVO.add(images);
					department.setImages(departmentImageVO);
					/*
					 * eventsImageVo.add(images);
					 * eventsVO.setImages(eventsImageVo);
					 */
				}

				// department.setUrl(UrlTransliterationUtil.getTransliteratedString(department.getName()));
			}

		} catch (SSOException e) {
			logger.error("Error getting Departments" + e.getMessage());
			throw new UAQException(e);
		}
		return departmentsList;

	}

	// TODO: Generalize this function
	private ImageVO getImageAsset(AssetBean assetBean) {
		ImageVO newsImageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
		return newsImageVO;
	}

	/**
	 * This method is used to convert the assetsBean to List of DepartmentVO
	 * 
	 * @param assetsBean
	 * @return
	 * @throws SSOException
	 */
	private List<DepartmentVO> getDepartmentsList(AssetsBean assetsBean, DepartmentVO departmentVO) throws SSOException {

		List<DepartmentVO> departmentsList = new ArrayList<DepartmentVO>();

		List<AssetInfo> assetInfoList = assetsBean.getAssetinfos();

		for (AssetInfo assetBean : assetInfoList) {

			DepartmentVO department = new DepartmentVO();
			department.setAssetId(assetBean.getId().split(":")[1]);
			department.setTicketId(departmentVO.getTicketId());

			department = getDepartmentDetails(department);
				
			if(null!=department && department.getLanguage().equalsIgnoreCase(departmentVO.getLanguage())){
				departmentsList.add(department);
			}
			
		}

		return departmentsList;
	}

	/**
	 * This method is used to get details for the given department id
	 * 
	 * @param departmentsVO
	 * @return
	 * @throws SSOException
	 */

	private DepartmentVO getDepartmentDetails(DepartmentVO departmentsVO) throws SSOException {

		AssetBean assetBean = AssetUtil.getAssetDetail("uaq", "Content_C", departmentsVO.getAssetId(), departmentsVO.getTicketId());

		departmentsVO = (DepartmentVO) mapper.mapAssetToVO(assetBean);
		departmentsVO.setLanguage(assetBean.getDimensions().get(0).getName());
		
		if(!(departmentsVO.getName().equalsIgnoreCase("UmmAlQuwain"))){
		return departmentsVO;
		}
		else
		return null;
	}

	@Override
	public List<DepartmentVO> executeSites(DepartmentVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
