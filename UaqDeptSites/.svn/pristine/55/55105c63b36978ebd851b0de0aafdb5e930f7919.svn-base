package com.uaq.controller.mapper;

import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.uaq.common.AssetUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.BaseVO;
import com.uaq.vo.ImageVO;

@Component("imageMapper")
public class ImageMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(ImageMapper.class);

	@Override
	public BaseVO mapAssetToVO(AssetBean assetBean) {

		//logger.debug("Inside Image Detail Mapper");
		ImageVO imageVO = new ImageVO();
		imageVO.setAssetId(assetBean.getId().split(":")[1]);
		imageVO.setName(assetBean.getName());

		for (Attribute attribute : assetBean.getAttributes()) {
			if (attribute.getName().equalsIgnoreCase("GenericContentImage") && attribute.getData() != null && attribute.getData().getBlobValue() != null) {
				imageVO.setGenericContentImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("PreviewImage") && attribute.getData().getBlobValue() != null) {
				imageVO.setPreviewImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("TeaserImage") && attribute.getData().getBlobValue() != null) {
				imageVO.setTeaserImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("GalleryTeaserImage") && attribute.getData().getBlobValue() != null) {
				imageVO.setGalleryTeaserImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("MobileImage") && attribute.getData().getBlobValue() != null && attribute.getData().getDateValue() != null) {
				imageVO.setMobileImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("Image") && attribute.getData().getBlobValue() != null) {
				imageVO.setImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("TeaserText") && attribute.getData() != null) {
				imageVO.setTeaserText(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserTitle") && attribute.getData() != null) {
				imageVO.setTeaserTitle(attribute.getData().getStringValue());
			}
		}
		return imageVO;
	}
}
