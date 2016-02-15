/**
 * 
 */
package com.uaq.controller.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Association;
import com.fatwire.rest.beans.Attribute;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.BaseVO;
import com.uaq.vo.FormFieldVO;
import com.uaq.vo.FormVO;
import com.uaq.vo.PageMetadataVO;

/**
 * Maps result from sites asset to form VO.
 * 
 * @author mraheem
 * 
 */
@Component("formVOMapper")
public class FormVOMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(FormVOMapper.class);

	@Override
	public BaseVO mapAssetToVO(AssetBean assetBean) {

		logger.enter("FormVOMapper");

		FormVO formVO = new FormVO();
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		List<FormFieldVO> formFields = new ArrayList<FormFieldVO>();

		formVO.setAssetId(assetBean.getId().split(":")[1]);
		formVO.setName(assetBean.getName());

		for (Attribute attribute : assetBean.getAttributes()) {
			if (attribute.getName().equals("PageTitle")) {
				pageMetadataVO.setPageTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("PageKeywords")) {
				pageMetadataVO.setPageKeywords(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("PageDescription")) {
				pageMetadataVO.setPageDescription(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("FormTitle")) {
				formVO.setText(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("FormSubmissionType")) { // poll,
																			// survey
				formVO.setFormSubmissionType(attribute.getData().getStringValue());
			}
		}

		for (Association association : assetBean.getAssociations().getAssociations()) {
			if (association.getName().equals("RelatedFormField")) { // questions
																	// and their
																	// answers
				for (String associatedAsset : association.getAssociatedAssets()) {
					FormFieldVO formField = new FormFieldVO();
					formField.setAssetId(associatedAsset.split(":")[1]);
					formFields.add(formField);
				}
				formVO.setFormFields(formFields);
			}
		}

		formVO.setPageMetadataVO(pageMetadataVO);

		logger.exit("FormVOMapper");

		return formVO;
	}

}
