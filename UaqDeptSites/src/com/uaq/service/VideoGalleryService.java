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
import com.uaq.vo.NavigationVO;
import com.uaq.vo.VideoSearchResponseVO;
import com.uaq.vo.VideoVO;

/**
 * Service class for Video Gallery
 * 
 * @author mraheem
 * 
 */
@Service(value = "videoService")
public class VideoGalleryService implements
		BaseService<SearchCommand, VideoSearchResponseVO> {

	protected static UAQLogger logger = new UAQLogger(VideoGalleryService.class);

	@Autowired
	@Qualifier("videoGalleryPageVOMapper")
	private BaseVOMapper videoGalleryPageVOMapper;

	@Override
	public VideoSearchResponseVO execute(SearchCommand inputObject)
			throws UAQException {
		List<VideoVO> videoList = new ArrayList<VideoVO>();
		VideoSearchResponseVO videoSearchResponseVO = new VideoSearchResponseVO();

		videoSearchResponseVO.setSearchResults(videoList);

		return videoSearchResponseVO;
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
			logger.error("Error getting Video Page Details " + e.getMessage());
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

	private VideoVO getAssetDetail(AssetBean assetBean) {
		VideoVO video = new VideoVO();
		video.setAssetId(assetBean.getId().split(":")[1]);
		video.setName(assetBean.getName());

		for (Attribute attribute : assetBean.getAttributes()) {

			if (attribute.getName().equalsIgnoreCase("TeaserTitle")
					&& attribute.getData() != null) {
				video.setTeaserTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Title")
					&& attribute.getData() != null) {
				video.setTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Video")
					&& attribute.getData() != null
					&& attribute.getData().getBlobValue() != null) {
				video.setVideo(AssetUtil.getBolbURL(attribute.getData()
						.getBlobValue().getHref(), "Video%2Fmp4"));
			} else if (attribute.getName().equalsIgnoreCase("Image")
					&& attribute.getData() != null
					&& attribute.getData().getBlobValue() != null) {
				video.setImage(AssetUtil.getBolbURL(attribute.getData()
						.getBlobValue().getHref(), "Image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("TeaserImage")
					&& attribute.getData() != null
					&& attribute.getData().getBlobValue() != null) {
				video.setTeaserImage(AssetUtil.getBolbURL(attribute.getData()
						.getBlobValue().getHref(), "Image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("CreatedDate")
					&& attribute.getData() != null) {
				video.setDate(attribute.getData().getDateValue().toString());
			}
			else if (attribute.getName().equalsIgnoreCase("CategoryNameEnglish")
					&& attribute.getData() != null) {
				video.setCategoryNameEN(attribute.getData().getStringValue());
			}
			else if (attribute.getName().equalsIgnoreCase("CategoryNameArabic")
					&& attribute.getData() != null) {
				video.setCategoryNameAR(attribute.getData().getStringValue());
			}
		}

		return video;
	}

	@Override
	public VideoSearchResponseVO executeSites(SearchCommand inputObject)
			throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	public VideoVO getPageVO(NavigationVO navigationVO) {
		logger.enter("CareerService");
		VideoVO videoVO = null;

		try {
			AssetBean assetBean = AssetUtil.getAssetDetail(
					navigationVO.getSite(), "Page", navigationVO.getAssetId(),
					navigationVO.getTicketId());
			videoVO = (VideoVO) videoGalleryPageVOMapper
					.mapAssetToVO(assetBean);
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return videoVO;
	}

	public List<VideoVO> getGalleryVideos(NavigationVO navigationVO, SearchCommand videoSearchCommand, String requestType) {
		List<VideoVO> videos = null;
		AssetBean videoGalleryAsset = null;
		try {
			videoGalleryAsset = AssetUtil.getAssetDetail(
					navigationVO.getSite(), "Page", navigationVO.getAssetId(),
					navigationVO.getTicketId());
			if(requestType.equals("POST"))
			{
			videos = getVideoGalleryPostRequest(videoGalleryAsset, navigationVO.getTicketId(),navigationVO.getSite(), videoSearchCommand, navigationVO.getLanguage());
			}
			if(requestType.equals("GET"))
			{
				videos = getVideoGalleryGetRequest(videoGalleryAsset, navigationVO.getTicketId(),navigationVO.getSite(), navigationVO.getLanguage());
			}
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return videos;
	}

	private List<VideoVO> getVideoGalleryPostRequest(AssetBean videoGalleryAsset,
			String multiticket,String site, SearchCommand videoSearchCommand, String language) {

		List<VideoVO> videos = new ArrayList<VideoVO>();
		AssetBean associateVideoAsset = null;
		VideoVO videoVO = null;
		for (Association association : videoGalleryAsset.getAssociations()
				.getAssociations()) {
			if (association.getName().equals("VideoList")
					&& !association.getAssociatedAssets().isEmpty()) {
				videos = new ArrayList<VideoVO>();
				for (String id : association.getAssociatedAssets()) {
					try {
						associateVideoAsset = AssetUtil.getAssetDetail(site,
								"Content_C", id.split(":")[1], multiticket);
						if (null != associateVideoAsset) {
							videoVO = getAssetDetail(associateVideoAsset);
							if(language.equals("en") && null!=videoVO.getCategoryNameEN() && (videoSearchCommand.getCategory().equals(videoVO.getCategoryNameEN())) || videoSearchCommand.getCategory().equalsIgnoreCase("0"))
								videos.add(videoVO);
							else if (language.equals("ar") && null!=videoVO.getCategoryNameAR() && (videoSearchCommand.getCategory().equals(videoVO.getCategoryNameAR())) || videoSearchCommand.getCategory().equalsIgnoreCase("0"))
								videos.add(videoVO);
						}
					} catch (SSOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// TODO Auto-generated method stub
			}

		}
		return videos;
	}

	
	private List<VideoVO> getVideoGalleryGetRequest(AssetBean videoGalleryAsset,
			String multiticket,String site, String language) {

		List<VideoVO> videos = new ArrayList<VideoVO>();
		AssetBean associateVideoAsset = null;
		VideoVO videoVO = null;
		if(null!=videoGalleryAsset.getAssociations().getAssociations()){
		for (Association association : videoGalleryAsset.getAssociations().getAssociations()) {
			if (association.getName().equals("VideoList")
					&& !association.getAssociatedAssets().isEmpty()) {
				videos = new ArrayList<VideoVO>();
				for (String id : association.getAssociatedAssets()) {
					try {
						associateVideoAsset = AssetUtil.getAssetDetail(site,
								"Content_C", id.split(":")[1], multiticket);
						if (null != associateVideoAsset && language.equals(associateVideoAsset.getDimensions().get(0).getName().toString())) {
							videoVO = getAssetDetail(associateVideoAsset);
							videos.add(videoVO);
						}
					} catch (SSOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// TODO Auto-generated method stub
			}

		}
		}
		return videos;
	}
	
}
