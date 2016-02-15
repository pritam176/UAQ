package com.uaq.controller.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.uaq.common.AssetUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.BaseVO;
import com.uaq.vo.CertificatesVO;
import com.uaq.vo.ImageVO;

@Component("certificatesDetailMapper")
public class CertificatesDetailMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(CertificatesDetailMapper.class);

	@Override
	public BaseVO mapAssetToVO(AssetBean assetBean) {

		logger.debug("Inside Certificate Detail Mapper");

		CertificatesVO certificate = new CertificatesVO();
		certificate.setAssetId(assetBean.getId().split(":")[1]);
		certificate.setName(assetBean.getName());
		List<ImageVO> images = new ArrayList<ImageVO>();
		SimpleDateFormat sdfin = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		SimpleDateFormat sdfout = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm");
		SimpleDateFormat sdfoutForLanding = new SimpleDateFormat("dd MMMM yyyy"); 
		ImageVO imageVO = null;

		for (Attribute attribute : assetBean.getAttributes()) {
			if (attribute.getName().equalsIgnoreCase("Title") && attribute.getData() != null) {
				certificate.setTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserTitle") && attribute.getData() != null) {
				certificate.setTeaserTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Body") && attribute.getData() != null) {
				certificate.setBody(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserText") && attribute.getData() != null) {
				certificate.setTeaserText(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("DateAndTime") && attribute.getData() != null && attribute.getData().getDateValue() != null) {
				try {
					certificate.setDatevalue(sdfout.format(sdfin.parse(attribute.getData().getDateValue().toString())));
					certificate.setLandingPageDate(sdfoutForLanding.format(sdfin.parse(attribute.getData().getDateValue().toString())));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (attribute.getName().equalsIgnoreCase("ExternalLink") && attribute.getData() != null) {
				certificate.setExternalLink(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Author") && attribute.getData() != null) {
				certificate.setAuthor(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Images") && null != attribute.getData() && null != attribute.getData().getStringLists()) {
				for (String image : attribute.getData().getStringLists()) {
					imageVO = new ImageVO();
					imageVO.setAssetId(image.split(":")[1]);
					imageVO.setAssetType(image.split(":")[0]);
					images.add(imageVO);
				}

			}
		}
		certificate.setImages(images);
		certificate.setUrl(AssetUtil.getModuleURL(assetBean.getName(), assetBean.getSubtype()));
		return certificate;
	}
}
