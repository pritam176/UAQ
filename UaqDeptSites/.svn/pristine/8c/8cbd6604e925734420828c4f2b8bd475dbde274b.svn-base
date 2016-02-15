package com.uaq.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Association;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.wem.sso.SSOException;
import com.uaq.common.AssetUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.PageMetadataVO;

@Repository(value = "navigationDAO")
public class NavigationDAO implements GenericDao<NavigationVO, NavigationVO> {

	protected static UAQLogger logger = new UAQLogger(NavigationDAO.class);

	@Autowired
	@Qualifier("imageMapper")
	BaseVOMapper imageMapper;

	@Override
	@Cacheable(value = "navigations", key = "#queryVO.assetId")
	public NavigationVO findByPrimaryKey(NavigationVO queryVO) throws UAQException {
		//logger.debug("NavigationDAO | Get the asset details of the asset" + queryVO.getAssetId());
		NavigationVO navigationVO = null;
		List<NavigationVO> associations = null;
		AssetBean assetBean = new AssetBean();
		try {
			assetBean = AssetUtil.getAssetDetail(queryVO.getSite(), "Page", queryVO.getAssetId(), queryVO.getTicketId());
			navigationVO = getAttributeDetails(assetBean, queryVO);
			if (assetBean.getAssociations().getAssociations().size() > 0) {
				for (Association association : assetBean.getAssociations().getAssociations()) {
					if (association.getName().equals("RelateHoverItems")) {

						associations = new ArrayList<NavigationVO>();
						for (String associatedAsset : association.getAssociatedAssets()) {
							//logger.debug("Asset ID in the association of the  Page Asset " + associatedAsset.split(":")[1] + "of type " + associatedAsset.split(":")[0]);

							NavigationVO AssocNavigationVO = new NavigationVO();
							assetBean = AssetUtil.getAssetDetail(queryVO.getSite(), associatedAsset.split(":")[0], associatedAsset.split(":")[1], queryVO.getTicketId());
							AssocNavigationVO = getAttributeDetails(assetBean, queryVO);
							associations.add(AssocNavigationVO);
						}
						navigationVO.setAssociations(associations);
					}
				}
			}

		} catch (SSOException e) {
			logger.error("Error getting the asset Details" + e.getMessage());
		}
		return navigationVO;
	}

	@Override
	public List<NavigationVO> listAll() throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(NavigationVO instance) throws UAQException {
		// TODO Auto-generated method stub

	}

	@Override
	@CacheEvict(value = { "navigations" }, allEntries = true)
	public void delete(NavigationVO instance) throws UAQException {
		// TODO Auto-generated method stub

	}

	/**
	 * Returns Navigation VO by reading from the attributes
	 * 
	 * @param assetBean
	 *            bean coming from CMS
	 * @return Navigation VO
	 */
	private NavigationVO getAttributeDetails(AssetBean assetBean, NavigationVO queryVO) {
		NavigationVO navigationVO;
		PageMetadataVO pageMetadataVO;
		navigationVO = new NavigationVO();
		pageMetadataVO = new PageMetadataVO();
		navigationVO.setName(assetBean.getName());
		navigationVO.setAssetId(assetBean.getId().split(":")[1]);
		navigationVO.setAssetType(assetBean.getId().split(":")[0]);
		navigationVO.setAssetSubType(assetBean.getSubtype());
		for (Attribute attribute : assetBean.getAttributes()) {
			if (attribute.getName().equals("Title") && null != attribute.getData() && null != attribute.getData().getStringValue()) {
				navigationVO.setTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("TeaserTitle") && null != attribute.getData() && null != attribute.getData().getStringValue()) {
				navigationVO.setTeaserTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("Heading") && null != attribute.getData() && null != attribute.getData().getStringValue()) {
				navigationVO.setHeading(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("TeaserText") && null != attribute.getData() && null != attribute.getData().getStringValue()) {
				navigationVO.setTeaserText(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("UploadImage") && null != attribute.getData() && null != attribute.getData().getBlobValue()) {
				navigationVO.setImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equals("Image") && null != attribute.getData() && null != attribute.getData().getBlobValue()) {
				navigationVO.setImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equals("Video") && null != attribute.getData() && null != attribute.getData().getBlobValue()) {
				navigationVO.setVideo(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equals("TeaserImage") && null != attribute.getData() && null != attribute.getData().getBlobValue()) {
				navigationVO.setTeaserImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equals("TitleIcon") && null != attribute.getData() && null != attribute.getData().getBlobValue()) {
				navigationVO.setTitleIcon(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equals("ShowInFooter") && null != attribute.getData() && null != attribute.getData().getStringValue()) {
				navigationVO.setShowInFooter(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("BannerImage") && null != attribute.getData() && null != attribute.getData().getBlobValue()) {
				navigationVO.setBannerImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equals("BannerText") && null != attribute.getData() && null != attribute.getData().getStringValue()) {
				navigationVO.setBannerText(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("PageTitle") && null != attribute.getData() && null != attribute.getData().getStringValue()) {
				pageMetadataVO.setPageTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("PageDescription") && null != attribute.getData() && null != attribute.getData().getStringValue()) {
				pageMetadataVO.setPageDescription(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("PageKeywords") && null != attribute.getData() && null != attribute.getData().getStringValue()) {
				pageMetadataVO.setPageKeywords(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("DsplayTypeHome") && null != attribute.getData() && null != attribute.getData().getStringValue()) {
				navigationVO.setDisplayTypeHome(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("Images") && null != attribute.getData() && null != attribute.getData().getStringLists()) {
				if (attribute.getData().getStringLists().size() > 0) {
					AssetBean imageAsset;
					try {
						imageAsset = AssetUtil.getAssetDetail(queryVO.getSite(), "Content_C", attribute.getData().getStringLists().get(0).split(":")[1], queryVO.getTicketId());
						ImageVO newsImageVO = (ImageVO) imageMapper.mapAssetToVO(imageAsset);
						navigationVO.setTeaserImage(newsImageVO.getGalleryTeaserImage());
					} catch (SSOException e) {
						logger.error("Error while getting the Image" + e.getMessage());
					}

				}
			}
		}
		navigationVO.setPageMetadataVO(pageMetadataVO);

		if (assetBean.getDimensions().size() == 0) {
			logger.error("Locale not mentioned for " + assetBean.getId());
		}

		if (!assetBean.getId().split(":")[0].equals("Page")) {
			navigationVO.setUrl(AssetUtil.getModuleURL(assetBean.getName(), assetBean.getSubtype()));
		}
		return navigationVO;
	}

	@Override
	public String getDepartmentUrl(String name, String subType) {
		// TODO Auto-generated method stub
		return null;
	}

}
