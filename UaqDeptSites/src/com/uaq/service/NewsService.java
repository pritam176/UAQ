package com.uaq.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.rest.beans.Association;
import com.fatwire.rest.beans.Associations;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.Attribute.Data;
import com.fatwire.wem.sso.SSOException;
import com.uaq.command.SearchCommand;
import com.uaq.common.AssetUtil;
import com.uaq.common.PropertiesUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.dao.BaseDAO;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.PaginationUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.CategoryVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.NewsVO;
import com.uaq.vo.SearchResponseVO;

/**
 * Service class for login which validates the password provided by the user and
 * the one returned form the database.
 * 
 * @author mraheem
 * 
 */
@Service(value = "newsService")
public class NewsService implements BaseService<NavigationVO, SearchResponseVO> {

	protected static UAQLogger logger = new UAQLogger(NewsService.class);

	@Autowired
	@Qualifier("newsDAO")
	BaseDAO<SearchCommand, SearchResponseVO> newsDAO;

	@Autowired
	@Qualifier("contentReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> contentReferneceDAO;

	@Autowired
	@Qualifier("imageMapper")
	BaseVOMapper imageMapper;

	public SearchResponseVO getNewsList(SearchCommand newsSearchCommand, String sector) throws UAQException, SSOException {

		List<ImageVO> newsImageVo = null;
		SearchResponseVO searchResponseVO = null;
		int pageSize = newsSearchCommand.getPageSize() > 0 ? newsSearchCommand.getPageSize() : Integer.valueOf(PropertiesUtil.getProperty("pagination.pagesize"));
		int paginationSetSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.setsize"));
		newsSearchCommand.setStartRow((newsSearchCommand.getCurrentPage() - 1) * pageSize);
		newsSearchCommand.setPageSize(pageSize);

		searchResponseVO = newsDAO.execute(newsSearchCommand);

		// fill in the image using its id
		if (searchResponseVO != null && searchResponseVO.getSearchResult() != null && searchResponseVO.getSearchResult().size() > 0) {
			searchResponseVO.setCurrentPage(newsSearchCommand.getCurrentPage());
			PaginationUtil.updateSearcvhResponseWithPaginationLimits(searchResponseVO, pageSize, paginationSetSize);
			for (NewsVO newsVO : searchResponseVO.getSearchResult()) {
				if (newsVO.getImage() != null) {
					newsImageVo = new ArrayList<ImageVO>();
					AssetBean assetBean = AssetUtil.getAssetDetail(sector, "Content_C", newsVO.getImage(), newsSearchCommand.getTicketId());
					ImageVO images = getImageAsset(assetBean);
					newsImageVo.add(images);
					newsVO.setImages(newsImageVo);
				}
				newsVO.setUrl(UrlTransliterationUtil.getTransliteratedString(newsVO.getName()));
			}
		}

		return searchResponseVO;
	}

	// TODO: Generalize this function
	private ImageVO getImageAsset(AssetBean assetBean) {
		ImageVO newsImageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
		return newsImageVO;
	}

	public List<CategoryVO> getNewsCategories(NewsVO newsVO) throws UAQException {

		AssetsBean categoriesAssets;
		List<CategoryVO> categoriesList;

		try {

			categoriesAssets = AssetUtil.searchAssetbyDefinition("Content_P", "Category", newsVO.getSite(), newsVO.getTicketId());
			categoriesList = getCategoriesList(categoriesAssets,newsVO);

		} catch (SSOException e) {
			logger.error("Error getting News Page Details " + e.getMessage());
			throw new UAQException(e);
		}

		return categoriesList;
	}

	/**
	 * This method is used to convert the assetsBean to List of CategoryVO
	 * 
	 * @param assetsBean
	 * @return
	 */
	private List<CategoryVO> getCategoriesList(AssetsBean assetsBean, NewsVO objVO) throws SSOException {

		List<CategoryVO> categoriesList = new ArrayList<CategoryVO>();

		List<AssetInfo> assetInfoList = assetsBean.getAssetinfos();

		AssetBean categoryAssestBean;

		for (AssetInfo assetBean : assetInfoList) {

			CategoryVO category = new CategoryVO();
			category.setAssetId(assetBean.getId().split(":")[1]);
			Data data = null;

			categoryAssestBean = AssetUtil.getAssetDetail(objVO.getSite(), "Content_P", assetBean.getId().split(":")[1], objVO.getTicketId());
			category.setName(categoryAssestBean.getName());
			for (Attribute attributes : categoryAssestBean.getAttributes()) {
				data = attributes.getData();
				if (attributes.getName().equalsIgnoreCase("CategoryNameArabic")) {
					category.setCategoryNameArabic(data.getStringValue());
				}
				if (attributes.getName().equalsIgnoreCase("CategoryNameEnglish")) {
					category.setCategoryNameEnglish(data.getStringValue());
				}
			}
			/*
			 * for (FieldInfo attribute : assetBean.getFieldinfos()) {
			 * 
			 * if (attribute.getFieldname().equalsIgnoreCase("name")) {
			 * category.setName(attribute.getData()); } }
			 */
			categoriesList.add(category);
		}

		return categoriesList;
	}

	public NewsVO getNewsDetails(NavigationVO navigationVO) throws SSOException {

		List<ImageVO> newsImageVo = null;
		NewsVO newsVO = null;
		AssetBean assetBean = null;
		try {
			NavigationVO navigation = contentReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());
			assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_C", navigationVO.getAssetId(), navigationVO.getTicketId());
			newsVO = getAssetDetail(assetBean);
		} catch (UAQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		newsImageVo = new ArrayList<ImageVO>();
		
		if (newsVO.getImage() != null) {
			AssetBean imagesAssetbean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_C", newsVO.getImage(), navigationVO.getTicketId());
			ImageVO images = getImageAsset(imagesAssetbean);
			newsImageVo.add(images);
			newsVO.setImages(newsImageVo);
		}

		Map<String, NewsVO> associatedNewsMap = new HashMap<String, NewsVO>();
		Map<String, Map<String, NewsVO>> relatedNewsMap = new HashMap<String, Map<String, NewsVO>>();

		associatedNewsMap = getAssociatedNew(navigationVO.getSite(), navigationVO.getTicketId(), assetBean);
		if (!associatedNewsMap.isEmpty()) {
			relatedNewsMap.put(newsVO.getName(), associatedNewsMap);
			newsVO.setRelatedNews(relatedNewsMap);
		}

		return newsVO;
	}

	private NewsVO getAssetDetail(AssetBean assetBean) throws SSOException {
		NewsVO newsVO = new NewsVO();
		newsVO.setAssetId(assetBean.getId().split(":")[1]);
		newsVO.setName(assetBean.getName());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Attribute attribute : assetBean.getAttributes()) {

			if (attribute.getName().equalsIgnoreCase("Title") && attribute.getData() != null) {
				newsVO.setTitle(attribute.getData().getStringValue());
			}

			else if (attribute.getName().equalsIgnoreCase("TeaserTitle") && attribute.getData() != null) {
				newsVO.setTeaserTitle(attribute.getData().getStringValue());
			}

			else if (attribute.getName().equalsIgnoreCase("Image") && attribute.getData() != null) {
				newsVO.setImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("Images") && attribute.getData() != null) {
				if (!attribute.getData().getStringLists().isEmpty()) {
					newsVO.setImage(attribute.getData().getStringLists().get(0).split(":")[1]);
				}

			} else if (attribute.getName().equalsIgnoreCase("TeaserImage") && attribute.getData() != null && attribute.getData().getBlobValue() != null) {
				newsVO.setTeaserImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			}

			else if (attribute.getName().equalsIgnoreCase("Body") && attribute.getData() != null) {
				newsVO.setBody(attribute.getData().getStringValue());
			}

			else if (attribute.getName().equalsIgnoreCase("TeaserText") && attribute.getData() != null) {
				newsVO.setTeaserText(attribute.getData().getStringValue());
			}

			else if (attribute.getName().equalsIgnoreCase("CreatedDate") && attribute.getData() != null) {
				newsVO.setDate(attribute.getData().getDateValue().toString());
			}

			else if (attribute.getName().equalsIgnoreCase("Author") && attribute.getData() != null) {
				newsVO.setAuthor(attribute.getData().getStringValue());
			}else if (attribute.getName().equalsIgnoreCase("PostedDate") && attribute.getData() != null) {
				newsVO.setPostedDate(sdf.format(attribute.getData().getDateValue()));
			}
			
			

		}
		return newsVO;
	}

	public Map<String, NewsVO> getAssociatedNew(String sector, String ticketId, AssetBean assetBean) throws SSOException {
		NewsVO relatedNewsVO = null;
		new NewsVO();

		List<ImageVO> newsImageVo = null;
		Map<String, NewsVO> assocoatedNewsMap = new HashMap<String, NewsVO>();
		Associations association = assetBean.getAssociations();
		for (Association associationAssets : association.getAssociations()) {
			List<String> associatedAssets = associationAssets.getAssociatedAssets();
			if (associatedAssets.size() > 0) {

				for (String news : associatedAssets) {
					newsImageVo = new ArrayList<ImageVO>();
					relatedNewsVO = new NewsVO();
					String assetID = news.split(":")[1];
					AssetBean associateNewAssetBean = AssetUtil.getAssetDetail(sector, "Content_C", assetID, ticketId);
					relatedNewsVO.setAssetId(associateNewAssetBean.getId().split(":")[1]);
					relatedNewsVO.setName(associateNewAssetBean.getName());
					relatedNewsVO = getAssetDetail(associateNewAssetBean);
					if (relatedNewsVO.getImage() != null) {
						AssetBean associateNewsImageBean = AssetUtil.getAssetDetail(sector, "Content_C", relatedNewsVO.getImage(), ticketId);
						ImageVO images = getImageAsset(associateNewsImageBean);
						newsImageVo.add(images);
						relatedNewsVO.setImages(newsImageVo);
					}
					relatedNewsVO.setUrl(UrlTransliterationUtil.getTransliteratedString(relatedNewsVO.getName()));
					assocoatedNewsMap.put(relatedNewsVO.getName(), relatedNewsVO);
				}

			}
		}

		return assocoatedNewsMap;
	}

	@Override
	public SearchResponseVO execute(NavigationVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchResponseVO executeSites(NavigationVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}
}
