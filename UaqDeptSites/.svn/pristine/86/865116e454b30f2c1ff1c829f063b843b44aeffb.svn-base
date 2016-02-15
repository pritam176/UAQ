package com.uaq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.rest.beans.Association;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.Attribute.Data;
import com.fatwire.wem.sso.SSOException;
import com.uaq.command.SearchCommand;
import com.uaq.common.AssetUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.CategoryVO;
import com.uaq.vo.ImageSearchResponseVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;

/**
 * Service class for Image Gallery
 * 
 * @author mraheem
 * 
 */
@Service(value = "imageService")
public class ImageGalleryService implements
		BaseService<SearchCommand, ImageSearchResponseVO> {

	protected static UAQLogger logger = new UAQLogger(ImageGalleryService.class);

	@Autowired
	@Qualifier("imageGalleryPageVOMapper")
	private BaseVOMapper imageGalleryPageVOMapper;

	@Override
	public ImageSearchResponseVO execute(SearchCommand inputObject)
			throws UAQException {
		List<ImageVO> imageList = new ArrayList<ImageVO>();
		ImageSearchResponseVO imageSearchResponseVO = new ImageSearchResponseVO();

		imageSearchResponseVO.setSearchResults(imageList);

		return imageSearchResponseVO;
	}

	public List<CategoryVO> getCategories(String sector, NavigationVO navigationVO)
			throws UAQException {

		AssetsBean categoriesAssets;
		List<CategoryVO> categoriesList;

		try {

			categoriesAssets = AssetUtil.searchAssetbyDefinition("Content_P",
					"Category", sector, navigationVO.getTicketId());
			categoriesList = getCategoriesList(categoriesAssets, navigationVO);

		} catch (SSOException e) {
			logger.error("Error getting Image Page Details " + e.getMessage());
			throw new UAQException(e);
		}

		return categoriesList;
	}

	/**
	 * This method is used to convert the assetsBean to List of CategoryVO
	 * 
	 * @param assetsBean
	 * @param navigationVO 
	 * @return
	 */
	private List<CategoryVO> getCategoriesList(AssetsBean assetsBean, NavigationVO navigationVO) {

		List<CategoryVO> categoriesList = new ArrayList<CategoryVO>();

		List<AssetInfo> assetInfoList = assetsBean.getAssetinfos();
				
		AssetBean categoryAssestBean=null;
		
		for (AssetInfo assetBean : assetInfoList) {

			CategoryVO category = new CategoryVO();
			category.setAssetId(assetBean.getId().split(":")[1]);
			
			Data data = null;
			
			try {
				categoryAssestBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_P", assetBean.getId().split(":")[1], navigationVO.getTicketId());
				category.setName(categoryAssestBean.getName());
			} catch (SSOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (null != categoryAssestBean) {
				for (Attribute attributes : categoryAssestBean.getAttributes()) {
					data = attributes.getData();
					if (attributes.getName().equalsIgnoreCase(
							"CategoryNameArabic")) {
						category.setCategoryNameArabic(data.getStringValue());
					}
					if (attributes.getName().equalsIgnoreCase(
							"CategoryNameEnglish")) {
						category.setCategoryNameEnglish(data.getStringValue());
					}
				}
			}
			categoriesList.add(category);
		}

		return categoriesList;
	}

	private ImageVO getAssetDetail(AssetBean assetBean) {
		ImageVO image = new ImageVO();
		image.setAssetId(assetBean.getId().split(":")[1]);
		image.setName(assetBean.getName());

		for (Attribute attribute : assetBean.getAttributes()) {

			if (attribute.getName().equalsIgnoreCase("TeaserTitle")
					&& attribute.getData() != null) {
				image.setTeaserTitle(attribute.getData().getStringValue());
			}
			
			else if (attribute.getName().equalsIgnoreCase("CategoryNameEnglish")
					&& attribute.getData() != null) {
				image.setCategoryNameEN(attribute.getData().getStringValue());
			}
			
			else if (attribute.getName().equalsIgnoreCase("CategoryNameArabic")
					&& attribute.getData() != null) {
				image.setCategoryNameAR(attribute.getData().getStringValue());
			}

			else if (attribute.getName().equalsIgnoreCase("Image")
					&& attribute.getData() != null) {
				image.setImage(AssetUtil.getBolbURL(attribute.getData()
						.getBlobValue().getHref(), "image%2Fjpeg"));
			}

			else if (attribute.getName().equalsIgnoreCase("TeaserImage")
					&& attribute.getData() != null
					&& attribute.getData().getBlobValue() != null) {
				image.setTeaserImage(AssetUtil.getBolbURL(attribute.getData()
						.getBlobValue().getHref(), "image%2Fjpeg"));
			}

			else if (attribute.getName().equalsIgnoreCase("GalleryTeaserImage")
					&& attribute.getData() != null
					&& attribute.getData().getBlobValue() != null) {
				image.setGalleryTeaserImage(AssetUtil.getBolbURL(attribute
						.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			}

			else if (attribute.getName().equalsIgnoreCase("PreviewImage")
					&& attribute.getData() != null) {
				image.setPreviewImage(AssetUtil.getBolbURL(attribute.getData()
						.getBlobValue().getHref(), "image%2Fjpeg"));
			}

			else if (attribute.getName().equalsIgnoreCase("CreatedDate")
					&& attribute.getData() != null) {
				image.setDate(attribute.getData().getDateValue().toString());
			}

		}

		return image;
	}

	@Override
	public ImageSearchResponseVO executeSites(SearchCommand inputObject)
			throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	public ImageVO getPageVO(NavigationVO navigationVO) {

		logger.enter("CareerService");
		ImageVO imageVO = null;

		try {
			AssetBean assetBean = AssetUtil.getAssetDetail(
					navigationVO.getSite(), "Page", navigationVO.getAssetId(),
					navigationVO.getTicketId());
			imageVO = (ImageVO) imageGalleryPageVOMapper.mapAssetToVO(assetBean);
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return imageVO;
	}

	public List<ImageVO> getGalleryImages(NavigationVO navigationVO, SearchCommand imageSearchCommand, String requestType) {
		List<ImageVO> images = null;
		AssetBean imageGalleryAsset = null;
		try {
			imageGalleryAsset = AssetUtil.getAssetDetail(
					navigationVO.getSite(), "Page", navigationVO.getAssetId(), navigationVO.getTicketId());
			if(requestType.equals("POST"))
			{
				images = getImageGalleryPostRequest(imageGalleryAsset,
					navigationVO.getTicketId(),navigationVO.getSite(), imageSearchCommand, navigationVO.getLanguage());
			}
			if(requestType.equals("GET"))
			{
				images = getImageGalleryGetRequest(imageGalleryAsset,
						navigationVO.getTicketId(),navigationVO.getSite());
			}

		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return images;
	}

	private List<ImageVO> getImageGalleryPostRequest(AssetBean imageGalleryAsset,
			String multiticket,String site, SearchCommand imageSearchCommand, String language) {

		List<ImageVO> images = new ArrayList<ImageVO>();
		AssetBean associateImageAsset = null;
		ImageVO imageVO = null;
		for (Association association : imageGalleryAsset.getAssociations()
				.getAssociations()) {
			if (association.getName().equals("ImageList")
					&& !association.getAssociatedAssets().isEmpty()) {
				images = new ArrayList<ImageVO>();
				for (String id : association.getAssociatedAssets()) {
					try {
						associateImageAsset = AssetUtil.getAssetDetail(site,
								"Content_C", id.split(":")[1], multiticket);
						if (null != associateImageAsset) {
							imageVO = getAssetDetail(associateImageAsset);
							if(language.equals("en") && null!=imageVO.getCategoryNameEN() && (imageSearchCommand.getCategory().equals(imageVO.getCategoryNameEN())) || imageSearchCommand.getCategory().equalsIgnoreCase("0"))
								images.add(imageVO);
							else if (language.equals("ar") && null!=imageVO.getCategoryNameAR() && (imageSearchCommand.getCategory().equals(imageVO.getCategoryNameAR())) || imageSearchCommand.getCategory().equalsIgnoreCase("0"))
								images.add(imageVO);
						}
					} catch (SSOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				// TODO Auto-generated method stub
			}

		}
		return images;
	}
	
	private List<ImageVO> getImageGalleryGetRequest(AssetBean imageGalleryAsset,
			String multiticket,String site) {

		List<ImageVO> images = new ArrayList<ImageVO>();
		AssetBean associateImageAsset = null;
		ImageVO imageVO = null;
		for (Association association : imageGalleryAsset.getAssociations()
				.getAssociations()) {
			if (association.getName().equals("ImageList")
					&& !association.getAssociatedAssets().isEmpty()) {
				images = new ArrayList<ImageVO>();
				for (String id : association.getAssociatedAssets()) {
					try {
						associateImageAsset = AssetUtil.getAssetDetail(site,
								"Content_C", id.split(":")[1], multiticket);
						if (null != associateImageAsset) {
							imageVO = getAssetDetail(associateImageAsset);
							images.add(imageVO);
						}
					} catch (SSOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				// TODO Auto-generated method stub
			}

		}
		return images;
	}
	
}
