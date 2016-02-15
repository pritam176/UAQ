package com.uaq.controller.mapper;

import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.uaq.common.AssetUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.BaseVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.PageMetadataVO;

@Component("imageGalleryPageVOMapper")
public class ImageGalleryPageVOMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(ImageGalleryPageVOMapper.class);
	
	@Override
	public BaseVO mapAssetToVO(AssetBean assetBean) {
		
		logger.debug("Inside Image Gallery Mapper");

		ImageVO imageVO = new ImageVO();
		imageVO.setAssetId(assetBean.getId().split(":")[1]);
		imageVO.setName(assetBean.getName());
		
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		
		for (Attribute attribute : assetBean.getAttributes()) {
			  if (attribute.getName().equalsIgnoreCase("Title") && attribute.getData() != null) {
				  imageVO.setTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TitleIcon") && attribute.getData().getBlobValue() != null) {
				imageVO.setTitleIcon(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("PageTitle") && attribute.getData() != null) {
				pageMetadataVO.setPageTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("PageKeywords") && attribute.getData() != null) {
				pageMetadataVO.setPageKeywords(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("PageDescription") && attribute.getData() != null) {
				pageMetadataVO.setPageDescription(attribute.getData().getStringValue());
			}
		}
		imageVO.setPageMetadataVO(pageMetadataVO);
		return imageVO;
	}
}
