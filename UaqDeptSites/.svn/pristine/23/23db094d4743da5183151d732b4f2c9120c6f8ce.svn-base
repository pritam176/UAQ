package com.uaq.controller.mapper;

import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.uaq.common.AssetUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.BaseVO;
import com.uaq.vo.CareersPageVO;
import com.uaq.vo.PageMetadataVO;

@Component("careersPageVOMapper")
public class CareersPageVOMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(CareersPageVOMapper.class);
	
	@Override
	public BaseVO mapAssetToVO(AssetBean assetBean) {
		
		logger.debug("Inside Careers Mapper");

		CareersPageVO careersPageVO = new CareersPageVO();
		careersPageVO.setAssetId(assetBean.getId().split(":")[1]);
		careersPageVO.setName(assetBean.getName());
		
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		
		for (Attribute attribute : assetBean.getAttributes()) {
			  if (attribute.getName().equalsIgnoreCase("Title") && attribute.getData() != null) {
				  careersPageVO.setTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TitleIcon") && null!= attribute.getData()  && attribute.getData().getBlobValue() != null) {
				careersPageVO.setTitleIcon(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("PageTitle") && attribute.getData() != null) {
				pageMetadataVO.setPageTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("PageKeywords") && attribute.getData() != null) {
				pageMetadataVO.setPageKeywords(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("PageDescription") && attribute.getData() != null) {
				pageMetadataVO.setPageDescription(attribute.getData().getStringValue());
			}
		}
		careersPageVO.setPageMetadataVO(pageMetadataVO);
		return careersPageVO;
	}
	
}
