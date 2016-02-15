package com.uaq.service;

import java.util.ArrayList;
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
import com.uaq.vo.DetailPageVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.PageMetadataVO;

/**
 * Service class for login which validates the password provided by the user and
 * the one returned form the database.
 * 
 * @author arjun
 * 
 */

@Service(value = "detailPageService")
public class DetailPageService implements BaseService<DetailPageVO, DetailPageVO> {
	protected static UAQLogger logger = new UAQLogger(DetailPageService.class);

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> webReferneceDAO;

	@Autowired
	@Qualifier("imageMapper")
	BaseVOMapper imageMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.uaq.service.BaseService#execute(java.lang.Object)
	 */
	@Override
	public DetailPageVO execute(DetailPageVO inputObject) throws UAQException {

		DetailPageVO detailpageVO = null;
		AssetBean detailPageAsset;

		NavigationVO query = new NavigationVO();
		query.setName(inputObject.getName());
		query.setLanguage(inputObject.getLanguage());
		query.setSite(inputObject.getSite());

		NavigationVO navigationVO = webReferneceDAO.findByPrimaryKey(query);
		
		if(navigationVO!=null){
			inputObject.setAssetId(navigationVO.getAssetId());

			try {
				detailPageAsset = AssetUtil.getAssetDetail(inputObject.getSite(), "Page", inputObject.getAssetId(), inputObject.getTicketId());
				detailpageVO = getDetailPageVO(detailPageAsset, inputObject.getTicketId(), inputObject.getSite());
			} catch (SSOException e) {
				logger.error("Error getting Detail Page Details" + e.getMessage());
				throw new UAQException(e);
			}
		}
		
		return detailpageVO;
	}

	/**
	 * @param detailPageAsset
	 * @param multiticket
	 * @param site 
	 * @return
	 * @throws UAQException
	 * @throws SSOException
	 */
	private DetailPageVO getDetailPageVO(AssetBean detailPageAsset, String multiticket, String site) throws UAQException, SSOException {

		Map<String, Map<String, AssociatedItemsVO>> rhsList = null;
		Map<String, Map<String, AssociatedItemsVO>> bodyList = null;

		Map<String, AssociatedItemsVO> serviceIDVOList = null;
		Map<String, AssociatedItemsVO> awardsIDVOList = null;
		Map<String, AssociatedItemsVO> formIDVOList = null;
		Map<String, AssociatedItemsVO> faqIDVOList = null;
		Map<String, AssociatedItemsVO> imagesIDVOList = null;
		Map<String, AssociatedItemsVO> article = null;

		DetailPageVO detailpageVO = new DetailPageVO();
		detailpageVO.setName(detailPageAsset.getName());
		detailpageVO.setAssetId(detailPageAsset.getId().split(":")[1]);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		
		ArrayList<AssociatedItemsVO> laws = new ArrayList<AssociatedItemsVO>();
		
		for (Attribute attribute : detailPageAsset.getAttributes()) {
			if (attribute.getName().equalsIgnoreCase("PageTitle") && attribute.getData() != null) {
				pageMetadataVO.setPageTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("PageKeywords") && attribute.getData() != null) {
				pageMetadataVO.setPageKeywords(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("PageDescription") && attribute.getData() != null) {
				pageMetadataVO.setPageDescription(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("title") && attribute.getData() != null) {
				detailpageVO.setTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("heading") && attribute.getData() != null) {
				detailpageVO.setHeading(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TitleIcon") && attribute.getData().getBlobValue() != null) {
				detailpageVO.setTitleIcon(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("UploadImage") && attribute.getData().getBlobValue() != null) {
				detailpageVO.setImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("ShowImageInBody")) {
				detailpageVO.setShowImageInBody(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserImage") && attribute.getData().getBlobValue() != null) {
				detailpageVO.setTeaserImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("imageAlignment") && attribute.getData() != null) {
				detailpageVO.setImageAlignment(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Body") && attribute.getData() != null) {
				detailpageVO.setBody(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("BannerText") && attribute.getData() != null) {
				detailpageVO.setBannerText(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("DsplayTypeHome") && attribute.getData() != null) {
				detailpageVO.setDisplayTypeHome(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserText") && attribute.getData() != null) {
				detailpageVO.setTeaserText(attribute.getData().getStringValue());
			}
		}

		/*************************************************************************
		 * ASSOCIATIONS FOR RHS LIST ITEMS.
		 **************************************************************************/

		for (Association association : detailPageAsset.getAssociations().getAssociations()) {

			// Association for RHS section List Items.
			if (association.getName().equals("RHSList") && !association.getAssociatedAssets().isEmpty()) {
				rhsList = new HashMap<String, Map<String, AssociatedItemsVO>>();
				List<String> rhsAssociatedIds = association.getAssociatedAssets();

				for (String id : rhsAssociatedIds) {
					AssetBean rhsMainAssociatedAsset = AssetUtil.getAssetDetail(site, "Content_C", id.split(":")[1], multiticket);
					String associatedAssetSubType = rhsMainAssociatedAsset.getSubtype();

					if (associatedAssetSubType.equals("ServiceList")) {
						for (Association innerAssociation : rhsMainAssociatedAsset.getAssociations().getAssociations()) {

							if (innerAssociation.getName().equals("RelatedServices") && !innerAssociation.getAssociatedAssets().isEmpty()) {
								List<String> innerAsssociatedIDs = innerAssociation.getAssociatedAssets();
								serviceIDVOList = new LinkedHashMap<String, AssociatedItemsVO>();

								for (String innerid : innerAsssociatedIDs) {
									AssetBean InnerAssociatedAsset = AssetUtil.getAssetDetail(site, "Content_C", innerid.split(":")[1], multiticket);
									AssociatedItemsVO associatedItemsVO = new AssociatedItemsVO();

									for (Attribute innerAssetAttribute : InnerAssociatedAsset.getAttributes()) {
										if (innerAssetAttribute.getName().equals("Title") && innerAssetAttribute.getData() != null) {
											associatedItemsVO.setTitle(innerAssetAttribute.getData().getStringValue());

										}
									}
									serviceIDVOList.put(innerid.split(":")[1], associatedItemsVO);
									associatedItemsVO = null;
								}
								rhsList.put(associatedAssetSubType, serviceIDVOList);
							}
						}
					}

					else if (associatedAssetSubType.equals("AwardsList")) {
						for (Association innerAssociation : rhsMainAssociatedAsset.getAssociations().getAssociations()) {

							if (innerAssociation.getName().equals("RelatedAwards") && !innerAssociation.getAssociatedAssets().isEmpty()) {
								List<String> innerAsssociatedIDs = innerAssociation.getAssociatedAssets();
								awardsIDVOList = new LinkedHashMap<String, AssociatedItemsVO>();

								for (String innerid : innerAsssociatedIDs) {
									AssetBean InnerAssociatedAsset = AssetUtil.getAssetDetail(site, "Content_C", innerid.split(":")[1], multiticket);
									AssociatedItemsVO associatedItemsVO = new AssociatedItemsVO();

									for (Attribute innerAssetAttribute : InnerAssociatedAsset.getAttributes()) {
										if (innerAssetAttribute.getName().equals("Images") && innerAssetAttribute.getData() != null) {
											List<ImageVO> imageVOList = new ArrayList<ImageVO>();
											String imageAssetID = innerAssetAttribute.getData().getStringLists().get(0).split(":")[1];
											AssetBean assetBean = AssetUtil.getAssetDetail(site, "Content_C", imageAssetID, multiticket);
											ImageVO imageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
											imageVOList.add(imageVO);
											associatedItemsVO.setImages(imageVOList);
										}

										else if (innerAssetAttribute.getName().equals("TeaserText") && innerAssetAttribute.getData() != null) {
											associatedItemsVO.setTeaserText(innerAssetAttribute.getData().getStringValue());
										}
									}
									awardsIDVOList.put(innerid.split(":")[1], associatedItemsVO);
									associatedItemsVO = null;
								}
								rhsList.put(associatedAssetSubType, awardsIDVOList);
							}
						}
					}

					else if (associatedAssetSubType.equals("ImageList")) {
						for (Association innerAssociation : rhsMainAssociatedAsset.getAssociations().getAssociations()) {

							if (innerAssociation.getName().equals("RelatedImages") && !innerAssociation.getAssociatedAssets().isEmpty()) {
								List<String> innerAsssociatedIDs = innerAssociation.getAssociatedAssets();
								imagesIDVOList = new LinkedHashMap<String, AssociatedItemsVO>();

								for (String innerid : innerAsssociatedIDs) {
									AssetBean InnerAssociatedAsset = AssetUtil.getAssetDetail(site, "Content_C", innerid.split(":")[1], multiticket);
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

					else if (associatedAssetSubType.equals("Article")) {
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
					}
				}
			}

			/*************************************************************************
			 * ASSOCIATIONS FOR BODY LIST ITEMS.
			 **************************************************************************/

			else if (association.getName().equals("BodyList") && !association.getAssociatedAssets().isEmpty()) {
				List<String> bodyAssociatedIds = association.getAssociatedAssets();
				bodyList = new HashMap<String, Map<String, AssociatedItemsVO>>();

				for (String bodyID : bodyAssociatedIds) {
					AssetBean bodyMainAssociatedAsset = AssetUtil.getAssetDetail(site, "Content_C", bodyID.split(":")[1], multiticket);
					String associatedAssetSubType = bodyMainAssociatedAsset.getSubtype();

					if (associatedAssetSubType.equals("FAQList")) {
						for (Association innerAssociation : bodyMainAssociatedAsset.getAssociations().getAssociations()) {

							if (innerAssociation.getName().equals("RelatedFAQs") && !innerAssociation.getAssociatedAssets().isEmpty()) {
								List<String> innerAsssociatedIDs = innerAssociation.getAssociatedAssets();
								faqIDVOList = new LinkedHashMap<String, AssociatedItemsVO>();

								for (String innerid : innerAsssociatedIDs) {
									AssetBean InnerAssociatedAsset = AssetUtil.getAssetDetail(site, "Content_C", innerid.split(":")[1], multiticket);
									AssociatedItemsVO associatedItemsVO = new AssociatedItemsVO();

									for (Attribute innerAssetAttribute : InnerAssociatedAsset.getAttributes()) {
										if (innerAssetAttribute.getName().equals("QuestionText") && innerAssetAttribute.getData() != null) {
											associatedItemsVO.setQuestionText(innerAssetAttribute.getData().getStringValue());
										}

										else if (innerAssetAttribute.getName().equals("AnswerText") && innerAssetAttribute.getData() != null) {
											associatedItemsVO.setAnswerText(innerAssetAttribute.getData().getStringValue());
										}
									}
									faqIDVOList.put(innerid.split(":")[1], associatedItemsVO);
									associatedItemsVO = null;
								}
								bodyList.put(associatedAssetSubType, faqIDVOList);
							}
						}
					}

					else if (associatedAssetSubType.equals("Form")) {

						formIDVOList = new LinkedHashMap<String, AssociatedItemsVO>();

						// To understand the Form Type Submission - which can be
						// either Poll/Survey.

						for (Attribute bodyMainAttribute : bodyMainAssociatedAsset.getAttributes()) {
							// Set the Form Submission Type(Poll/Survey) in the
							// detailPageVO.
							if (bodyMainAttribute.getName().equals("FormSubmissionType") && bodyMainAttribute.getData() != null) {
								detailpageVO.setFormType(bodyMainAttribute.getData().getStringValue());
							}
						}

						for (Association innerAssociation : bodyMainAssociatedAsset.getAssociations().getAssociations()) {

							if (innerAssociation.getName().equals("RelatedFormField") && !innerAssociation.getAssociatedAssets().isEmpty()) {
								List<String> innerAsssociatedIDs = innerAssociation.getAssociatedAssets();

								for (String innerid : innerAsssociatedIDs) {
									AssetBean InnerAssociatedAsset = AssetUtil.getAssetDetail(site, "Content_C", innerid.split(":")[1], multiticket);
									AssociatedItemsVO associatedItemsVO = new AssociatedItemsVO();

									for (Attribute innerAssetAttribute : InnerAssociatedAsset.getAttributes()) {
										if (innerAssetAttribute.getName().equals("Question") && innerAssetAttribute.getData() != null) {
											associatedItemsVO.setQuestion(innerAssetAttribute.getData().getStringValue());
										}

										else if (innerAssetAttribute.getName().equals("AnswerType") && innerAssetAttribute.getData() != null) {
											associatedItemsVO.setAnswerType(innerAssetAttribute.getData().getStringValue());
										}

										else if (innerAssetAttribute.getName().equals("AvailableAnswers") && innerAssetAttribute.getData() != null) {
											associatedItemsVO.setAvailableAnswers(innerAssetAttribute.getData().getStringValue());
										}
									}
									formIDVOList.put(innerid.split(":")[1], associatedItemsVO);
									associatedItemsVO = null;
								}
								bodyList.put(associatedAssetSubType, formIDVOList);
							}
						}
					}
					
					else if (associatedAssetSubType.equals("Law")) {
						AssociatedItemsVO associatedItemsVO = new AssociatedItemsVO();
						
						for (Attribute attribute : bodyMainAssociatedAsset.getAttributes()) {
							if (attribute.getName().equals("Title") && attribute.getData() != null) {
								associatedItemsVO.setTitle(attribute.getData().getStringValue());
							} else if (attribute.getName().equals("TeaserTitle") && attribute.getData() != null) {
								associatedItemsVO.setTeaserTitle(attribute.getData().getStringValue());
							} else if (attribute.getName().equals("SelectFile") && attribute.getData().getBlobValue() != null) {
								associatedItemsVO.setFile(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "application%2Fpdf"));
							}
						}
						laws.add(associatedItemsVO);
						associatedItemsVO = null;
					}
				}
			}
		}
		if(null!=pageMetadataVO){
		detailpageVO.setPageMetadataVO(pageMetadataVO); // Add Page Meta data to View Object.
		}
		
		if(null!=rhsList){
		detailpageVO.setRhsIDViewObjectMap(rhsList); // Add RHS Association to View Object.
		}
		
		if(null!=bodyList){
		detailpageVO.setBodyIDViewObjectMap(bodyList); // Add Body Association to View Object.
		}
		
		if(null!=laws){
		detailpageVO.setLawsList(laws);  // Add Laws to View Object.
		}
		
		return detailpageVO;
	}

	@Override
	public DetailPageVO executeSites(DetailPageVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}
}