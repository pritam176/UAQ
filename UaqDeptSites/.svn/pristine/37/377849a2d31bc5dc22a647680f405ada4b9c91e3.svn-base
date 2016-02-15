package com.uaq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.Blob;
import com.fatwire.wem.sso.SSOException;
import com.uaq.common.AssetUtil;
import com.uaq.dao.LocalizationDAO;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.HomeVO;
import com.uaq.vo.PageMetadataVO;

/**
 * Service class for login which validates the password provided by the user and
 * the one returned form the database.
 * 
 * @author nsharma
 * 
 */
@Service(value = "homeService")
public class HomeService implements BaseService<HomeVO, HomeVO> {

	protected static UAQLogger logger = new UAQLogger(HomeService.class);

	@Autowired
	@Qualifier("localizationDAO")
	private LocalizationDAO localizationDAO;

	@Override
	public HomeVO execute(HomeVO inputObject) throws UAQException {
		AssetBean homeAsset;

		if (inputObject.getLanguage().equals("en")) {
			String translatedAssetId = localizationDAO.getTranslatedAssetIdPage(inputObject.getAssetId());
			inputObject.setAssetId(translatedAssetId);
		}

		try {
			homeAsset = AssetUtil.getAssetDetail("uaq", "Page", inputObject.getAssetId(), inputObject.getTicketId());
			inputObject = getHomeVO(homeAsset);
		} catch (SSOException e) {
			logger.error("Error getting Home Page Details " + e.getMessage());
			throw new UAQException(e);
		}
		return inputObject;
	}

	@Override
	public HomeVO executeSites(HomeVO inputObject) throws UAQException {
		AssetBean homeAsset;

		if (inputObject.getLanguage().equals("en")) {
			String translatedAssetId = localizationDAO.getTranslatedAssetIdPage(inputObject.getAssetId());
			inputObject.setAssetId(translatedAssetId);
		}

		try {
			homeAsset = AssetUtil.getAssetDetail(inputObject.getSite(), "Page", inputObject.getAssetId(), inputObject.getTicketId());
			inputObject = getHomeVO(homeAsset);
		} catch (SSOException e) {
			logger.error("Error getting Home Page Details " + e.getMessage());
			throw new UAQException(e);
		}
		return inputObject;
	}

	/**
	 * @param assetBean
	 * @return
	 */
	private HomeVO getHomeVO(AssetBean assetBean) {
		HomeVO homeVO = new HomeVO();
		homeVO.setName(assetBean.getName());
		homeVO.setAssetId(assetBean.getId().split(":")[1]);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		List<String> carouselImages = new ArrayList<String>();

		for (Attribute attribute : assetBean.getAttributes()) {
			if (attribute.getName().equals("PageTitle")) {
				pageMetadataVO.setPageTitle(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("PageKeywords")) {
				pageMetadataVO.setPageKeywords(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("PageDescription")) {
				pageMetadataVO.setPageDescription(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("CopyrightText")) {
				homeVO.setCopyrightText(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("WebsiteUpdateText")) {
				homeVO.setWebsiteUpdateText(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("BrowserSupportText")) {
				homeVO.setBrowserSupportText(attribute.getData().getStringValue());
			} else if (attribute.getName().equals("BackgroundImage")) {
				homeVO.setBackgroundImage(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "image%2Fjpeg"));
			} else if (attribute.getName().equals("CarouselImages") && null != attribute.getData() && null != attribute.getData().getBlobLists()) {
				for (Blob blob : attribute.getData().getBlobLists()) {
					carouselImages.add(AssetUtil.getBolbURL(blob.getHref(), "image%2Fjpeg"));
				}
			} else if (attribute.getName().equals("CarouselVideos") && null != attribute.getData() && null != attribute.getData().getBlobValue()) {
				homeVO.setCarouselVideos(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "Video%2Fmp4"));
			}
		}
		homeVO.setCarouselImages(carouselImages);
		homeVO.setPageMetadataVO(pageMetadataVO);
		return homeVO;
	}

}
