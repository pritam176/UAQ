package com.uaq.controller.mapper;

import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.uaq.common.AssetUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.DepartmentVO;

@Component("departmentDetailMapper")
public class DepartmentDetailMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(DepartmentDetailMapper.class);

	@Override
	public DepartmentVO mapAssetToVO(AssetBean assetBean) {
		DepartmentVO departmentsVO = new DepartmentVO();
		departmentsVO.setAssetId(assetBean.getId().split(":")[1]);
		departmentsVO.setName(assetBean.getName());

		for (Attribute attribute : assetBean.getAttributes()) {

			if (attribute.getName().equalsIgnoreCase("Image") && attribute.getData() != null) {
				departmentsVO.setImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("TeaserImage") && attribute.getData() != null && attribute.getData().getBlobValue() != null) {
				departmentsVO.setTeaserImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("Body") && attribute.getData() != null) {
				departmentsVO.setBody(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("DepartmentNameEN") && attribute.getData() != null) {
				departmentsVO.setDepartmentNameEN(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("DepartmentNameAR") && attribute.getData() != null) {
				departmentsVO.setDepartmentNameAR(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TelephoneNumber") && attribute.getData() != null) {
				departmentsVO.setTelephoneNumber(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Fax") && attribute.getData() != null) {
				departmentsVO.setFax(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Latitude") && attribute.getData() != null) {
				departmentsVO.setLatitude(attribute.getData().getDoubleValue().toString());
			} else if (attribute.getName().equalsIgnoreCase("Longitude") && attribute.getData() != null) {
				departmentsVO.setLongitude(attribute.getData().getDoubleValue().toString());
			} else if (attribute.getName().equalsIgnoreCase("Timings") && attribute.getData() != null) {
				departmentsVO.setTimings(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("RamadanTimings") && attribute.getData() != null) {
				departmentsVO.setRamadanTimings(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("FacebookContact") && attribute.getData() != null) {
				departmentsVO.setFacebookContact(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("YoutubeContact") && attribute.getData() != null) {
				departmentsVO.setYoutubeContact(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TwitterContact") && attribute.getData() != null) {
				departmentsVO.setTwitterContact(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("LinkedInContact") && attribute.getData() != null) {
				departmentsVO.setLinkedInContact(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("InstagramContact") && attribute.getData() != null) {
				departmentsVO.setInstagramContact(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("GooglePlusContact") && attribute.getData() != null) {
				departmentsVO.setGooglePlusContact(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Website") && attribute.getData() != null) {
				departmentsVO.setWebsite(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("EmailID") && attribute.getData() != null) {
				departmentsVO.setEmailID(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("CreatedDate") && attribute.getData() != null) {
				departmentsVO.setDate(attribute.getData().getDateValue().toString());
			} else if (attribute.getName().equalsIgnoreCase("Site") && attribute.getData() != null) {
				departmentsVO.setSite(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("AddressLine1") && attribute.getData() != null) {
				departmentsVO.setAddressLine1(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("AddressLine2") && attribute.getData() != null) {
				departmentsVO.setAddressLine2(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("AddressLine3") && attribute.getData() != null) {
				departmentsVO.setAddressLine3(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Images") && attribute.getData() != null) {
				if (!attribute.getData().getStringLists().isEmpty()) {
					departmentsVO.setImage(attribute.getData().getStringLists().get(0).split(":")[1]);
				}

			}
		}
		departmentsVO.setUrl(UrlTransliterationUtil.getTransliteratedString(departmentsVO.getName()));
		return departmentsVO;
	}

}
