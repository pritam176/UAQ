/**
 * 
 */
package com.uaq.controller.mapper;

import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.BaseVO;
import com.uaq.vo.FormFieldVO;

/**
 * Maps result from sites asset to formField VO.
 * 
 * @author mraheem
 * 
 */
@Component("formFieldVOMapper")
public class FormFieldVOMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(FormFieldVOMapper.class);

	@Override
	public BaseVO mapAssetToVO(AssetBean assetBean) {

		logger.enter("mapAssetToVO");

		FormFieldVO formFieldVO = new FormFieldVO();
		formFieldVO.setAssetId(assetBean.getId().split(":")[1]);
		formFieldVO.setName(assetBean.getName());

		for (Attribute attribute : assetBean.getAttributes()) {
			if (attribute.getName().equals("Question")) {
				formFieldVO.setText(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("AnswerType")) { // radio
																	// button,
																	// checkbox,
																	// dropdown
																	// select or
																	// descriptive
				formFieldVO.setFormFieldType(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("PollAnwers")) {
				formFieldVO.setValues(attribute.getData().getStringLists());
			}
		}

		logger.exit("mapAssetToVO");

		return formFieldVO;
	}

}
