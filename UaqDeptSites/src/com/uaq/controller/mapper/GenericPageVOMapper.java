package com.uaq.controller.mapper;

import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.uaq.common.AssetUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.BaseVO;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.PageMetadataVO;

@Component("genericPageVOMapper")
public class GenericPageVOMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(GenericPageVOMapper.class);

	@Override
	public BaseVO mapAssetToVO(AssetBean assetBean) {

		logger.debug("Inside Projects Detail Mapper");

		GenericPageVO genericPageVO = new GenericPageVO();
		genericPageVO.setAssetId(assetBean.getId().split(":")[1]);
		genericPageVO.setName(assetBean.getName());

		PageMetadataVO pageMetadataVO = new PageMetadataVO();

		for (Attribute attribute : assetBean.getAttributes()) {
			if (attribute.getName().equalsIgnoreCase("Body") && attribute.getData() != null) {
				genericPageVO.setBody(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Title") && attribute.getData() != null) {
				genericPageVO.setTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TitleIcon") && attribute.getData().getBlobValue() != null) {
				genericPageVO.setTitleIcon(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("PageTitle") && attribute.getData() != null) {
				pageMetadataVO.setPageTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("PageKeywords") && attribute.getData() != null) {
				pageMetadataVO.setPageKeywords(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("PageDescription") && attribute.getData() != null) {
				pageMetadataVO.setPageDescription(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserText") && attribute.getData() != null) {
				genericPageVO.setTeaserText(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserImage") && attribute.getData().getBlobValue() != null) {
				genericPageVO.setTeaserImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			}
		}

		genericPageVO.setPageMetadataVO(pageMetadataVO);
		
		// TODO Auto-generated method stub
		return genericPageVO;
	}

}
