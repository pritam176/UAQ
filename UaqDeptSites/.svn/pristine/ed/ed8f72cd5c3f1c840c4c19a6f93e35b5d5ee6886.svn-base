package com.uaq.controller.mapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.ServicesVO;

@Component("ServicesDetailMapper")
public class ServicesDetailMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(ServicesDetailMapper.class);

	@Override
	public ServicesVO mapAssetToVO(AssetBean assetBean) {

		logger.debug("Inside Service Detail Mapper");
		String url = PropertiesUtil.getProperty("uaq.url");
				
		ServicesVO service = new ServicesVO();
		service.setAssetId(assetBean.getId().split(":")[1]);
		service.setName(assetBean.getName());
		Pattern pattern = Pattern.compile("(.*?)\\d\\.(.*?)");
		logger.debug("Inside Service Detail Mapper" + service.getAssetId());
		String locale=assetBean.getDimensions().get(0).getName();
		for (Attribute attribute : assetBean.getAttributes()) {
			if (attribute.getName().equalsIgnoreCase("Title") && attribute.getData() != null) {
				service.setTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("SERVICEDESCRIPTION") && attribute.getData() != null) {
				service.setserviceDescription(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Timings") && attribute.getData() != null) {
				service.setTimings(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Beneficiary") && attribute.getData() != null) {
				service.setBeneficiary(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("ServiceCategory") && attribute.getData() != null) {
				service.setServiceCategory(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("RequiredDocuments") && attribute.getData() != null && null != attribute.getData().getStringValue()
					&& !StringUtils.isEmpty(attribute.getData().getStringValue())) {
				//logger.debug("RequiredDocuments" + attribute.getData().getStringValue());
				service.setReqDocs(attribute.getData().getStringValue());
				String stringValue= attribute.getData().getStringValue().replaceAll("\\r?\\n", "");
				Matcher matcher = pattern.matcher(stringValue);
				if (matcher.matches()) {
					String[] list = stringValue.split("\\d\\.");
					service.setRequiredDocsList(list);
				}

			} else if (attribute.getName().equalsIgnoreCase("Procedures") && attribute.getData() != null && null != attribute.getData().getStringValue()
					&& !StringUtils.isEmpty(attribute.getData().getStringValue())) {
				//logger.debug("Procedures" + attribute.getData().getStringValue());
				String stringValue= attribute.getData().getStringValue().replaceAll("\\r?\\n", "");
				Matcher matcher = pattern.matcher(stringValue);
				service.setProcedures(attribute.getData().getStringValue());
				if (matcher.matches()) {
					String[] list = stringValue.split("\\d\\.");
					service.setProceduresList(list);
				}

			} else if (attribute.getName().equalsIgnoreCase("EmailID") && attribute.getData() != null) {
				service.setEmail(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Fax") && attribute.getData() != null) {
				service.setFax(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TelephoneNumber") && attribute.getData() != null) {
				service.setTelephone(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("ServiceEndUser") && attribute.getData() != null) {
				service.setServiceEndUser(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("ServiceFees") && attribute.getData() != null) {
				service.setServiceFees(attribute.getData().getStringLists());
			} else if (attribute.getName().equalsIgnoreCase("ExternalLink") && attribute.getData() != null) {
				service.setExternalLink(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("ServiceEnabled") && attribute.getData() != null) {
				service.setServiceEnabled(attribute.getData().getStringValue());
			} 
			if(locale.equals("en")){
			if (attribute.getName().equalsIgnoreCase("DepartmentNameEN") && attribute.getData() != null) {
				service.setDepartmentName(attribute.getData().getStringValue());
			}
			}else {if (attribute.getName().equalsIgnoreCase("DepartmentNameAR") && attribute.getData() != null) {
				service.setDepartmentName(attribute.getData().getStringValue());
			}}
		}
		
		service.setUrl(url);
		
		return service;
	}

}
