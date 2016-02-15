package com.uaq.controller.mapper;

import org.springframework.stereotype.Component;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.uaq.common.AssetUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.BaseVO;
import com.uaq.vo.CertificatesPageVO;
import com.uaq.vo.PageMetadataVO;

@Component("certificatesPageVOMapper")
public class CertificatesPageVOMapper implements BaseVOMapper{

	public static transient UAQLogger logger = new UAQLogger(CertificatesPageVOMapper.class);
	
	@Override
	public BaseVO mapAssetToVO(AssetBean assetBean) {
		
		logger.debug("Inside AwasrdsPageVO Mapper");

		CertificatesPageVO certificatesPageVO = new CertificatesPageVO();
		certificatesPageVO.setAssetId(assetBean.getId().split(":")[1]);
		certificatesPageVO.setName(assetBean.getName()); 
		
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		
		for (Attribute attribute : assetBean.getAttributes()) {
			  if (attribute.getName().equalsIgnoreCase("Title") && attribute.getData() != null) {
				  certificatesPageVO.setTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TitleIcon") && null!= attribute.getData()  && attribute.getData().getBlobValue() != null) {
				  certificatesPageVO.setTitleIcon(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("PageTitle") && attribute.getData() != null) {
				  pageMetadataVO.setPageTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("PageKeywords") && attribute.getData() != null) {
				  pageMetadataVO.setPageKeywords(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("PageDescription") && attribute.getData() != null) {
				  pageMetadataVO.setPageDescription(attribute.getData().getStringValue());
			}
		}
		//TODO Auto-generated method stub
		certificatesPageVO.setPageMetadataVO(pageMetadataVO);
		return certificatesPageVO;
	}

}
