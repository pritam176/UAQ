/**
 * 
 */
package com.uaq.controller.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Association;
import com.fatwire.rest.beans.Attribute;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.BaseVO;
import com.uaq.vo.LandingPageVO;
import com.uaq.vo.PageMetadataVO;

/**
 * Maps result from sites asset to survey VO.
 * 
 * @author mraheem
 * 
 */
@Component("landingPageVOMapper")
public class LandingPageVOMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(LandingPageVOMapper.class);

	@Override
	public BaseVO mapAssetToVO(AssetBean assetBean) {

		logger.enter("mapAssetToVO");

		LandingPageVO landingPageVO = new LandingPageVO();
		PageMetadataVO pageMetadataVO = new PageMetadataVO();

		landingPageVO.setAssetId(assetBean.getId().split(":")[1]);
		landingPageVO.setName(assetBean.getName());

		for (Attribute attribute : assetBean.getAttributes()) {
			if (attribute.getName().equals("PageTitle")) {
				pageMetadataVO.setPageTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("PageKeywords")) {
				pageMetadataVO.setPageKeywords(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("PageDescription")) {
				pageMetadataVO.setPageDescription(attribute.getData().getStringValue());
			}
		}

		for (Association association : assetBean.getAssociations().getAssociations()) {

			if (association.getName().equals("BodyList") && !association.getAssociatedAssets().isEmpty()) {

				List<String> bodyAssociatedIds = association.getAssociatedAssets();
				landingPageVO.setBodyAssociatedIds(bodyAssociatedIds);

				break;
			}
		}

		landingPageVO.setPageMetadataVO(pageMetadataVO);

		logger.debug("mapAssetToVO");

		return landingPageVO;
	}

}
