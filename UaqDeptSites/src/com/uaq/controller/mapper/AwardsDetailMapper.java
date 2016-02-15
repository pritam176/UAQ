package com.uaq.controller.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.uaq.common.AssetUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.AwardsVO;
import com.uaq.vo.BaseVO;
import com.uaq.vo.ImageVO;

@Component("awardsDetailMapper")
public class AwardsDetailMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(AwardsDetailMapper.class);

	@Override
	public BaseVO mapAssetToVO(AssetBean assetBean) {

		logger.debug("Inside Award Detail Mapper");
		
		Locale locale = new Locale(assetBean.getDimensions().get(0).getName());

		AwardsVO award = new AwardsVO();
		award.setAssetId(assetBean.getId().split(":")[1]);
		award.setName(assetBean.getName());
		List<ImageVO> images = new ArrayList<ImageVO>();
		SimpleDateFormat sdfin = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		SimpleDateFormat sdfout = new SimpleDateFormat("EEEE, dd MMMMMMMMMM yyyy HH:mm", locale);
		SimpleDateFormat sdfoutForLanding = new SimpleDateFormat("dd MMMM yyyy", locale);
		ImageVO imageVO = null;

		for (Attribute attribute : assetBean.getAttributes()) {
			if (attribute.getName().equalsIgnoreCase("Title") && attribute.getData() != null) {
				award.setTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserTitle") && attribute.getData() != null) {
				award.setTeaserTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Body") && attribute.getData() != null) {
				award.setBody(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserText") && attribute.getData() != null) {
				award.setTeaserText(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("DateAndTime") && attribute.getData() != null && attribute.getData().getDateValue() != null) {
				try {
					award.setDatevalue(sdfout.format(sdfin.parse(attribute.getData().getDateValue().toString())));
					award.setLandingPageDate(sdfoutForLanding.format(sdfin.parse(attribute.getData().getDateValue().toString())));
					award.setAwardsDate(attribute.getData().getDateValue());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (attribute.getName().equalsIgnoreCase("ExternalLink") && attribute.getData() != null) {
				award.setExternalLink(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Author") && attribute.getData() != null) {
				award.setAuthor(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Images") && null != attribute.getData() && null != attribute.getData().getStringLists()) {
				for (String image : attribute.getData().getStringLists()) {
					imageVO = new ImageVO();
					imageVO.setAssetId(image.split(":")[1]);
					imageVO.setAssetType(image.split(":")[0]);
					images.add(imageVO);
				}

			}
		}
		award.setImages(images);
		award.setUrl(AssetUtil.getModuleURL(assetBean.getName(), assetBean.getSubtype()));
		return award;
	}
}
