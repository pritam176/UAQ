package com.uaq.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Association;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.wem.sso.SSOException;
import com.uaq.common.AssetUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.AssociatedItemsVO;
import com.uaq.vo.GenericPageVO;
import com.uaq.vo.NavigationVO;

@Service(value = "genericPageService")
public class GenericPageService implements BaseService<NavigationVO, GenericPageVO> {

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> webReferneceDAO;

	@Autowired
	@Qualifier("genericPageVOMapper")
	private BaseVOMapper mapper;

	protected static UAQLogger logger = new UAQLogger(GenericPageService.class);

	@Override
	public GenericPageVO execute(NavigationVO navigationVO) throws UAQException {

		GenericPageVO genericPageVO = new GenericPageVO();
		try {

			NavigationVO navigation = webReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());

			AssetBean assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Page", navigationVO.getAssetId(), navigationVO.getTicketId());
			genericPageVO = (GenericPageVO) mapper.mapAssetToVO(assetBean);

			genericPageVO = populateAllAssociations(genericPageVO, assetBean, navigationVO.getSite(), navigationVO.getTicketId());

			return genericPageVO;

		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return genericPageVO;
	}

	private GenericPageVO populateAllAssociations(GenericPageVO genericPageVO, AssetBean genericPageAsset, String site, String multiticket) throws SSOException {

		if (null != genericPageAsset.getAssociations().getAssociations()) {
			Map<String, Map<String, AssociatedItemsVO>> rhsList = null;
			Map<String, Map<String, AssociatedItemsVO>> bodyList = null;
			for (Association association : genericPageAsset.getAssociations().getAssociations()) {

				// Association for RHS section List Items.

				if (association.getName().equals("RHSList") && !association.getAssociatedAssets().isEmpty()) {
					rhsList = new HashMap<String, Map<String, AssociatedItemsVO>>();
					Map<String, AssociatedItemsVO> article = null;
					Map<String, AssociatedItemsVO> imagesIDVOList = null;

					List<String> rhsAssociatedIds = association.getAssociatedAssets();

					for (String id : rhsAssociatedIds) {
						AssetBean rhsMainAssociatedAsset = AssetUtil.getAssetDetail(site, "Content_C", id.split(":")[1], multiticket);
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
							rhsList.put(associatedAssetSubType, article);
						} else if (associatedAssetSubType.equals("ImageList")) {
							for (Association innerAssociation : rhsMainAssociatedAsset.getAssociations().getAssociations()) {

								if (innerAssociation.getName().equals("RelatedImages") && !innerAssociation.getAssociatedAssets().isEmpty()) {
									List<String> innerAsssociatedIDs = innerAssociation.getAssociatedAssets();
									imagesIDVOList = new LinkedHashMap<String, AssociatedItemsVO>();

									for (String innerid : innerAsssociatedIDs) {
										AssetBean InnerAssociatedAsset = AssetUtil.getAssetDetail("uaq", "Content_C", innerid.split(":")[1], multiticket);
										AssociatedItemsVO associatedItemsVO = new AssociatedItemsVO();

										for (Attribute innerAssetAttribute : InnerAssociatedAsset.getAttributes()) {
											if (innerAssetAttribute.getName().equals("GalleryTeaserImage") && innerAssetAttribute.getData().getBlobValue() != null) {
												associatedItemsVO.setGalleryTeaserImage(AssetUtil.getBolbURL(innerAssetAttribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
											} else if (innerAssetAttribute.getName().equals("Image") && innerAssetAttribute.getData().getBlobValue() != null) {
												associatedItemsVO.setOriginalImage(AssetUtil.getBolbURL(innerAssetAttribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
											}
										}
										imagesIDVOList.put(innerid.split(":")[1], associatedItemsVO);
										associatedItemsVO = null;
									}
									rhsList.put(associatedAssetSubType, imagesIDVOList);
								}
							}
						}

					}
				}

				// Association for Body section List Items.

				else if (association.getName().equals("BodyList") && !association.getAssociatedAssets().isEmpty()) {
					List<String> bodyAssociatedIds = association.getAssociatedAssets();
					bodyList = new HashMap<String, Map<String, AssociatedItemsVO>>();
					Map<String, AssociatedItemsVO> departmentContact = null;

					for (String bodyID : bodyAssociatedIds) {
						AssetBean bodyMainAssociatedAsset = AssetUtil.getAssetDetail(site, "Content_C", bodyID.split(":")[1], multiticket);
						String associatedAssetSubType = bodyMainAssociatedAsset.getSubtype();

						if (associatedAssetSubType.equals("Department")) {
							AssociatedItemsVO associatedItemsVO = new AssociatedItemsVO();
							departmentContact = new LinkedHashMap<String, AssociatedItemsVO>();
							for (Attribute attribute : bodyMainAssociatedAsset.getAttributes()) {
								if (attribute.getName().equals("Website") && attribute.getData() != null) {
									associatedItemsVO.setWebsite(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("DepartmentNameEN") && attribute.getData() != null) {
									associatedItemsVO.setDepartmentNameEN(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("DepartmentNameAR") && attribute.getData() != null) {
									associatedItemsVO.setDepartmentNameAR(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("TelephoneNumber") && attribute.getData() != null) {
									associatedItemsVO.setTelephoneNumber(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("Fax") && attribute.getData() != null) {
									associatedItemsVO.setFax(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("Latitude") && attribute.getData() != null) {
									associatedItemsVO.setLatitude(attribute.getData().getDoubleValue().toString());
								} else if (attribute.getName().equals("Longitude") && attribute.getData() != null) {
									associatedItemsVO.setLongitude(attribute.getData().getDoubleValue().toString());
								} else if (attribute.getName().equals("AddressLine1") && attribute.getData() != null) {
									associatedItemsVO.setAddressLine1(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("AddressLine2") && attribute.getData() != null) {
									associatedItemsVO.setAddressLine2(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("AddressLine3") && attribute.getData() != null) {
									associatedItemsVO.setAddressLine3(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("FacebookContact") && attribute.getData() != null) {
									associatedItemsVO.setFacebookContact(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("TwitterContact") && attribute.getData() != null) {
									associatedItemsVO.setTwitterContact(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("GooglePlusContact") && attribute.getData() != null) {
									associatedItemsVO.setGooglePlusContact(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("YoutubeContact") && attribute.getData() != null) {
									associatedItemsVO.setYoutubeContact(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("InstagramContact") && attribute.getData() != null) {
									associatedItemsVO.setInstagramContact(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("RssContact") && attribute.getData() != null) {
									associatedItemsVO.setRssContact(attribute.getData().getStringValue());
								} else if (attribute.getName().equals("EmailID") && attribute.getData() != null) {
									associatedItemsVO.setEmailID(attribute.getData().getStringValue());
								}
							}
							departmentContact.put(associatedAssetSubType, associatedItemsVO);
							associatedItemsVO = null;
							bodyList.put(associatedAssetSubType, departmentContact);
						}
					}
				}

			}

			genericPageVO.setRhsIDViewObjectMap(rhsList); // Add RHS Association
															// to View Object.
			genericPageVO.setBodyIDViewObjectMap(bodyList); // Add Body
															// Association to
															// View Object.
		}
		return genericPageVO;
	}

	@Override
	public GenericPageVO executeSites(NavigationVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This method is used to get page meta data for a given page name. it will not load associations
	 * @param navigationVO
	 * @return
	 * @throws UAQException
	 */
	
	public GenericPageVO getPageMetaData(NavigationVO navigationVO) throws UAQException {

		GenericPageVO genericPageVO = new GenericPageVO();
		try {

			NavigationVO navigation = webReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());

			AssetBean assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Page", navigationVO.getAssetId(), navigationVO.getTicketId());
			genericPageVO = (GenericPageVO) mapper.mapAssetToVO(assetBean);

			return genericPageVO;

		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return genericPageVO;
	}

}
