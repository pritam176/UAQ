package com.uaq.controller.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.uaq.common.AssetUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.BaseVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.PeopleVO;

@Component("peopleMapper")
public class PeopleMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(PeopleMapper.class);

	@Override
	public BaseVO mapAssetToVO(AssetBean assetBean) {

		logger.debug("Inside People Mapper");

		PeopleVO people = new PeopleVO();
		people.setAssetId(assetBean.getId().split(":")[1]);
		people.setName(assetBean.getName());
		
		List<ImageVO> images = new ArrayList<ImageVO>();
		ImageVO imageVO = null;

		for (Attribute attribute : assetBean.getAttributes()) {

			if (attribute.getName().equalsIgnoreCase("EmployeeName") && attribute.getData() != null) {
				people.setEmployeeName(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Position") && attribute.getData() != null) {
				people.setPosition(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Images") && attribute.getData() != null && null != attribute.getData().getStringLists()) {
				for (String image : attribute.getData().getStringLists()) {
					imageVO = new ImageVO();
					imageVO.setAssetId(image.split(":")[1]);
					imageVO.setAssetType(image.split(":")[0]);
					images.add(imageVO);
				}
			}
		}
		people.setImages(images);
		people.setUrl(AssetUtil.getModuleURL(assetBean.getName(), assetBean.getSubtype()));
		return people;
	}
}
