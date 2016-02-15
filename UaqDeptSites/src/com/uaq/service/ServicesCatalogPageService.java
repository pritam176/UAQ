package com.uaq.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Association;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.wem.sso.SSOException;
import com.uaq.common.AssetUtil;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.AssociatedItemsVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.PageMetadataVO;
import com.uaq.vo.ServicesCatalogPageVO;

/**
 * 
 * @author ajain
 * 
 *         Service Class for the Careers page.
 */

@Service(value = "servicesCatalogPageService")
public class ServicesCatalogPageService implements BaseService<NavigationVO, ServicesCatalogPageVO> {
	
	public static transient UAQLogger logger = new UAQLogger(ServicesCatalogPageService.class);

	@Override
	public ServicesCatalogPageVO execute(NavigationVO inputObject)
			throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServicesCatalogPageVO executeSites(NavigationVO inputObject)
			throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ServicesCatalogPageVO getPageVO(NavigationVO navigationVO) {
		
		logger.enter("ServicesControllerPageService");
		ServicesCatalogPageVO servicesControllerPageVO = null;
		
		try {
			
			AssetBean  assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Page", navigationVO.getAssetId(), navigationVO.getTicketId());
			servicesControllerPageVO = mapAssetToVO(assetBean, navigationVO.getTicketId());
			
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return servicesControllerPageVO;
	}

	private ServicesCatalogPageVO mapAssetToVO(AssetBean assetBean,
			String multiticket) throws SSOException {
		
		ServicesCatalogPageVO servicesCatalogPageVO = new ServicesCatalogPageVO();
		Map<String, Map<String, AssociatedItemsVO>> rhsList = null;
		Map<String, AssociatedItemsVO> article = null;
		
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		
		for (Attribute attribute : assetBean.getAttributes()) {
			  if (attribute.getName().equalsIgnoreCase("Title") && attribute.getData() != null) {
				servicesCatalogPageVO.setTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TitleIcon") && null!= attribute.getData()  && attribute.getData().getBlobValue() != null) {
				servicesCatalogPageVO.setTitleIcon(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("PageTitle") && attribute.getData() != null) {
				pageMetadataVO.setPageTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("PageKeywords") && attribute.getData() != null) {
				pageMetadataVO.setPageKeywords(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("PageDescription") && attribute.getData() != null) {
				pageMetadataVO.setPageDescription(attribute.getData().getStringValue());
			}
		}
		
		for (Association association : assetBean.getAssociations().getAssociations()) {
			// Association for RHS section List Items.
						if (association.getName().equals("RHSList") && !association.getAssociatedAssets().isEmpty()) {
							rhsList = new HashMap<String, Map<String, AssociatedItemsVO>>();
							List<String> rhsAssociatedIds = association.getAssociatedAssets();
							
							for (String id : rhsAssociatedIds) {
								AssetBean rhsMainAssociatedAsset = AssetUtil.getAssetDetail("uaq", "Content_C", id.split(":")[1], multiticket);
								String associatedAssetSubType = rhsMainAssociatedAsset.getSubtype();
								
								if (associatedAssetSubType.equals("Article")) {
									AssociatedItemsVO associatedItemsVO = new AssociatedItemsVO();
									article = new LinkedHashMap<String, AssociatedItemsVO>();
									for (Attribute attribute : rhsMainAssociatedAsset.getAttributes()) {
										if (attribute.getName().equals("Title") && attribute.getData() != null) {
											associatedItemsVO.setTitle(attribute.getData().getStringValue());
										} else if (attribute.getName().equals("HelpText1") && attribute.getData() != null) {
											associatedItemsVO.setHelpText1(attribute.getData().getStringValue());
										} else if (attribute.getName().equals("HelpText2") && attribute.getData() != null) {
											associatedItemsVO.setHelpText2(attribute.getData().getStringValue());
										} else if (attribute.getName().equals("TelephoneNumber") && attribute.getData() != null) {
											associatedItemsVO.setTelephoneNumber(attribute.getData().getStringValue());
										} else if (attribute.getName().equals("Image") && attribute.getData().getBlobValue() != null) {
											associatedItemsVO.setImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
										} else if (attribute.getName().equals("URLLink") && attribute.getData() != null) {
											associatedItemsVO.setUrlLink(attribute.getData().getStringValue());
										} else if (attribute.getName().equals("URLText") && attribute.getData() != null) {
											associatedItemsVO.setUrlText(attribute.getData().getStringValue());
										}
									}
									article.put(associatedAssetSubType, associatedItemsVO);
									associatedItemsVO = null;
							}
								rhsList.put(associatedAssetSubType, article);
						}
							
					}
					servicesCatalogPageVO.setPageMetadataVO(pageMetadataVO);
					servicesCatalogPageVO.setRhsIDViewObjectMap(rhsList);
		}
		return servicesCatalogPageVO;
}
}
