package com.uaq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.fatwire.rest.beans.AssetsBean;
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
import com.uaq.util.DateUtil;
import com.uaq.util.PaginationUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.CategoryVO;
import com.uaq.vo.EventsSearchResponseVO;
import com.uaq.vo.EventsVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.SearchResponseVO;

/**
 * Service class for login which validates the password provided by the user and
 * the one returned form the database.
 * 
 * @author mraheem
 * 
 */
@Service(value = "eventsService")
public class EventsService implements BaseService<EventsVO, EventsVO> {

	protected static UAQLogger logger = new UAQLogger(EventsService.class);

	@Autowired
	@Qualifier("eventsDAO")
	BaseDAO<SearchCommand, SearchResponseVO> eventsDAO;

	@Autowired
	@Qualifier("contentReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> contentReferneceDAO;

	@Autowired
	@Qualifier("imageMapper")
	BaseVOMapper imageMapper;

	/**
	 * @param eventsSearchCommand
	 * @return
	 * @throws UAQException
	 * @throws SSOException
	 */
	public SearchResponseVO getEventsList(SearchCommand eventsSearchCommand, String sector) throws UAQException, SSOException {

		List<ImageVO> eventsImageVo = null;
		SearchResponseVO searchResponseVO = null;
		int pageSize = eventsSearchCommand.getPageSize() > 0 ? eventsSearchCommand.getPageSize() : Integer.valueOf(PropertiesUtil.getProperty("pagination.pagesize"));
		int paginationSetSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.setsize"));
		eventsSearchCommand.setStartRow((eventsSearchCommand.getCurrentPage() - 1) * pageSize);
		eventsSearchCommand.setPageSize(pageSize);

		searchResponseVO = eventsDAO.execute(eventsSearchCommand);

		// fill in the image using its id
		if (searchResponseVO != null && ((EventsSearchResponseVO) searchResponseVO).getSearchResults() != null && ((EventsSearchResponseVO) searchResponseVO).getSearchResults().size() > 0) {
			searchResponseVO.setCurrentPage(eventsSearchCommand.getCurrentPage());
			PaginationUtil.updateSearcvhResponseWithPaginationLimits(searchResponseVO, pageSize, paginationSetSize);
			for (EventsVO eventsVO : ((EventsSearchResponseVO) searchResponseVO).getSearchResults()) {
				if (eventsVO.getImage() != null) {
					eventsImageVo = new ArrayList<ImageVO>();
					AssetBean assetBean = AssetUtil.getAssetDetail(sector, "Content_C", eventsVO.getImage(), eventsSearchCommand.getTicketId());
					ImageVO images = getImageAsset(assetBean);
					eventsImageVo.add(images);
					eventsVO.setImages(eventsImageVo);
				}

				eventsVO.setUrl(UrlTransliterationUtil.getTransliteratedString(eventsVO.getName()));
			}
		}

		return searchResponseVO;
	}

	// TODO: Generalize this function
	private ImageVO getImageAsset(AssetBean assetBean) {
		ImageVO newsImageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
		return newsImageVO;
	}

	/**
	 * @param eventsVO
	 * @return
	 * @throws UAQException
	 */
	public List<CategoryVO> getEventsCategories(EventsVO eventsVO) throws UAQException {

		AssetsBean categoriesAssets;
		List<CategoryVO> categoriesList;

		try {

			categoriesAssets = AssetUtil.searchAssetbyDefinition("Content_P", "Category", eventsVO.getSite(), eventsVO.getTicketId());
			categoriesList = getCategoriesList(categoriesAssets, eventsVO);

		} catch (SSOException e) {
			logger.error("Error getting Events Page Details " + e.getMessage());
			throw new UAQException(e);
		}

		return categoriesList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.uaq.service.BaseService#execute(java.lang.Object)
	 */
	@Override
	public EventsVO execute(EventsVO inputObject) throws UAQException {
		EventsVO eventsVO = new EventsVO();
		/*
		 * NavigationVO navigationVO =
		 * WebReferneceDAO.findByPrimaryKey(inputObject.getUrl());
		 * eventsVO.setAssetId(navigationVO.getAssetId());
		 * logger.debug("Id of the landing Page is" + eventsVO.getAssetId());
		 */
		return eventsVO;
	}

	/**
	 * This method is used to convert the assetsBean to List of CategoryVO
	 * 
	 * @param assetsBean
	 * @return
	 * @throws SSOException
	 */
	private List<CategoryVO> getCategoriesList(AssetsBean assetsBean, EventsVO objVO) throws SSOException {

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

	public EventsVO getEventsDetails(NavigationVO navigationVO) throws SSOException {

		List<ImageVO> eventsImageVo = null;
		EventsVO eventsVO = null;

		try {
			NavigationVO navigation = contentReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());
			eventsImageVo = new ArrayList<ImageVO>();
			AssetBean assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_C", navigationVO.getAssetId(), navigationVO.getTicketId());
			eventsVO = getAssetDetail(assetBean,navigationVO.getLanguage());

		} catch (UAQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (eventsVO.getImage() != null) {
			AssetBean imagesAssetbean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_C", eventsVO.getImage(), navigationVO.getTicketId());
			ImageVO images = getImageAsset(imagesAssetbean);
			eventsImageVo.add(images);
			eventsVO.setImages(eventsImageVo);
		}
		return eventsVO;
	}

	private EventsVO getAssetDetail(AssetBean assetBean,String locale) {
		EventsVO eventsVO = new EventsVO();
		eventsVO.setAssetId(assetBean.getId().split(":")[1]);
		eventsVO.setName(assetBean.getName());
		String dateFormat = "MMMM dd, yyyy";

		for (Attribute attribute : assetBean.getAttributes()) {

			if (attribute.getName().equalsIgnoreCase("Title") && attribute.getData() != null) {
				eventsVO.setTitle(attribute.getData().getStringValue());
			}else if (attribute.getName().equalsIgnoreCase("TeaserTitle") && attribute.getData() != null) {
				eventsVO.setTeaserTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Latitude") && attribute.getData() != null) {
				eventsVO.setLatitude(attribute.getData().getDoubleValue().toString());
			} else if (attribute.getName().equalsIgnoreCase("Longitude") && attribute.getData() != null) {
				eventsVO.setLongitude(attribute.getData().getDoubleValue().toString());
			} else if (attribute.getName().equalsIgnoreCase("Heading") && attribute.getData() != null) {
				eventsVO.setHeading(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("OpeningDate") && attribute.getData() != null && attribute.getData().getDateValue() != null) {
				/*eventsVO.setOpeningDate(sdf.format(attribute.getData().getDateValue()));*/
				eventsVO.setOpeningDate(DateUtil.getUAQFormattedDate(attribute.getData().getDateValue(), dateFormat, locale));
			} else if (attribute.getName().equalsIgnoreCase("EndingDate") && attribute.getData() != null && attribute.getData().getDateValue() != null) {
				/*eventsVO.setEndingDate(sdf.format(attribute.getData().getDateValue()));*/
				eventsVO.setEndingDate(DateUtil.getUAQFormattedDate(attribute.getData().getDateValue(), dateFormat, locale));
			} else if (attribute.getName().equalsIgnoreCase("AddressLine1") && attribute.getData() != null) {
				eventsVO.setAddressLine1(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("addressLine2") && attribute.getData() != null) {
				eventsVO.setAddressLine2(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("AddressLine3") && attribute.getData() != null) {
				eventsVO.setAddressLine3(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Website") && attribute.getData() != null) {
				eventsVO.setWebsite(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("EventName") && attribute.getData() != null) {
				eventsVO.setEventName(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("Procedures") && attribute.getData() != null) {
				eventsVO.setProcedures(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("ExternalLink") && attribute.getData() != null) {
				eventsVO.setExternalLink(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserImage") && attribute.getData() != null && attribute.getData().getBlobValue() != null) {
				eventsVO.setTeaserImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("Image") && attribute.getData() != null && attribute.getData().getBlobValue() != null) {
				eventsVO.setImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equalsIgnoreCase("Images") && attribute.getData() != null) {
				if (!attribute.getData().getStringLists().isEmpty()) {
					eventsVO.setImage(attribute.getData().getStringLists().get(0).split(":")[1]);
				}
			} else if (attribute.getName().equalsIgnoreCase("Body") && attribute.getData() != null) {
				eventsVO.setBody(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("TeaserText") && attribute.getData() != null) {
				eventsVO.setTeaserText(attribute.getData().getStringValue());
			} else if (attribute.getName().equalsIgnoreCase("CreatedDate") && attribute.getData() != null && attribute.getData().getDateValue() != null) {
				eventsVO.setDate(attribute.getData().getDateValue().toString());
			}
		}
		return eventsVO;
	}

	@Override
	public EventsVO executeSites(EventsVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
