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
import com.uaq.vo.ImageVO;
import com.uaq.vo.ProjectsVO;

@Component("projectsDetailMapper")
public class ProjectsDetailMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(ProjectsDetailMapper.class);

	@Override
	public BaseVO mapAssetToVO(AssetBean assetBean) {

		logger.debug("Inside Projects Detail Mapper");

		ProjectsVO projects = new ProjectsVO();
		projects.setAssetId(assetBean.getId().split(":")[1]);
		projects.setName(assetBean.getName());
		SimpleDateFormat sdfin = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		SimpleDateFormat sdfout = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm");
		SimpleDateFormat sdfLandingout = new SimpleDateFormat("dd MMM yyyy");
		
		List<ImageVO> images = new ArrayList<ImageVO>();
		ImageVO imageVO = null;

		for (Attribute attribute : assetBean.getAttributes()) {

			if (attribute.getName().equalsIgnoreCase("Title") && attribute.getData() != null) {
				projects.setTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserTitle") && attribute.getData() != null) {
				projects.setTeaserTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Body") && attribute.getData() != null) {
				projects.setBody(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserText") && attribute.getData() != null) {
				projects.setTeaserText(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("DateAndTime") && attribute.getData() != null && attribute.getData().getDateValue() != null) {
				try {
					projects.setDateAndTime(sdfout.format(sdfin.parse(attribute.getData().getDateValue().toString())));
					projects.setLandingPageDate(sdfLandingout.format(sdfin.parse(attribute.getData().getDateValue().toString())));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (attribute.getName().equalsIgnoreCase("MobileImage") && attribute.getData() != null) {
				projects.setMobileImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("Images") && attribute.getData() != null && null != attribute.getData().getStringLists()) {
				for (String image : attribute.getData().getStringLists()) {
					imageVO = new ImageVO();
					imageVO.setAssetId(image.split(":")[1]);
					imageVO.setAssetType(image.split(":")[0]);
					images.add(imageVO);
				}
			}
		}
		projects.setImages(images);
		projects.setUrl(AssetUtil.getModuleURL(assetBean.getName(), assetBean.getSubtype()));
		return projects;
	}
}
