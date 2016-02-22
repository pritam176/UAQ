/**
 * 
 */
package com.uaq.controller.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.uaq.logger.UAQLogger;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.ArchaeologyHeritageVO;
import com.uaq.vo.BaseVO;
import com.uaq.vo.ImageVO;

/**
 * @author TACME
 *
 */

@Component(value="archaeologyHeritageMapper")
public class ArchaeologyHeritageMapper implements BaseVOMapper {

	public static transient UAQLogger logger = new UAQLogger(ArchaeologyHeritageMapper.class);
	
	@Override
	public BaseVO mapAssetToVO(AssetBean assetBean) {
		
		ArchaeologyHeritageVO archaeologyHeritageVO = new ArchaeologyHeritageVO();
		archaeologyHeritageVO.setAssetId(assetBean.getId().split(":")[1]);
		archaeologyHeritageVO.setName(assetBean.getName());
		List<ImageVO> images = new ArrayList<ImageVO>();
		
		ImageVO imageVO = null;
		
		for (Attribute attribute : assetBean.getAttributes()) {
			if (attribute.getName().equalsIgnoreCase("Title") && attribute.getData() != null) {
				archaeologyHeritageVO.setTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserTitle") && attribute.getData() != null) {
				archaeologyHeritageVO.setTeaserTitle(attribute.getData().getStringValue());
			}  else if (attribute.getName().equalsIgnoreCase("TeaserText") && attribute.getData() != null) {
				archaeologyHeritageVO.setTeaserText(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Images") && null != attribute.getData() && null != attribute.getData().getStringLists()) {
				for (String image : attribute.getData().getStringLists()) {
					imageVO = new ImageVO();
					imageVO.setAssetId(image.split(":")[1]);
					imageVO.setAssetType(image.split(":")[0]);
					images.add(imageVO);
				}
		}else if (attribute.getName().equalsIgnoreCase("ExternalLink") && attribute.getData() != null) {
			archaeologyHeritageVO.setExternalLink(attribute.getData().getStringValue());
		} else if (attribute.getName().equalsIgnoreCase("Author") && attribute.getData() != null) {
			archaeologyHeritageVO.setAuthor(attribute.getData().getStringValue());
		} else if (attribute.getName().equalsIgnoreCase("Body") && attribute.getData() != null) {
			archaeologyHeritageVO.setBody(attribute.getData().getStringValue());
		} 

	}
		archaeologyHeritageVO.setImages(images);
		archaeologyHeritageVO.setUrl(UrlTransliterationUtil.getTransliteratedString(archaeologyHeritageVO.getName()));

		return archaeologyHeritageVO;
	}
}
