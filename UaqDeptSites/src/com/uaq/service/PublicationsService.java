package com.uaq.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.FieldInfo;
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
import com.uaq.vo.PublicationsVO;
import com.uaq.vo.SearchResponseVO;

/**
 * Service class for login which validates the password provided by the user and
 * the one returned form the database.
 * 
 * @author anair
 * 
 */
@Service(value = "publicationsService")
public class PublicationsService implements BaseService<PublicationsVO, PublicationsVO> {

	protected static UAQLogger logger = new UAQLogger(PublicationsService.class);

	@Autowired
	@Qualifier("publicationsDAO")
	BaseDAO<SearchCommand, List<PublicationsVO>> publicationsDAO;

	@Autowired
	@Qualifier("contentReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> contentReferneceDAO;

	@Autowired
	@Qualifier("imageMapper")
	private BaseVOMapper imageMapper;

	public SearchResponseVO getPublicationsList(SearchCommand publicationsSearchCommand) throws UAQException, SSOException {

		List<PublicationsVO> publicationsList = null;
		List<PublicationsVO> publicationsFinalList = new ArrayList<PublicationsVO>();

		ImageVO imageVO = null;
		SearchResponseVO searchResponseVO = new SearchResponseVO();

		int pageSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.pagesize"));
		int paginationSetSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.setsize"));
		publicationsSearchCommand.setStartRow((publicationsSearchCommand.getCurrentPage() - 1) * pageSize);
		publicationsSearchCommand.setPageSize(pageSize);

		publicationsList = publicationsDAO.execute(publicationsSearchCommand);
		// fill in the image using its id
		for (PublicationsVO publicationsVO : publicationsList) {
			if (publicationsVO.getAssetId() != null) {
				AssetBean assetBean = AssetUtil.getAssetDetail(publicationsSearchCommand.getSite(), "Content_C", publicationsVO.getAssetId(), publicationsSearchCommand.getTicketId());
				
				for (Attribute attribute : assetBean.getAttributes()) {
					if (attribute.getName().equals("SelectFile") && attribute.getData().getBlobValue() != null) {
						publicationsVO.setFile(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "application%2Fpdf"));
					}
				}
				
				// publicationsVO.setImage("/cs/Satellite?blobkey=id&blobwhere="
				// + publicationsVO.getImage() + "&blobheader=" + "image%2Fjpeg"
				// + "&blobcol=urldata&blobtable=MungoBlobs");
				// publicationsVO.setTeaserImage("/cs/Satellite?blobkey=id&blobwhere="
				// + publicationsVO.getTeaserImage() + "&blobheader=" +
				// "image%2Fjpeg" + "&blobcol=urldata&blobtable=MungoBlobs");
			}
		}

		int startRecord = publicationsSearchCommand.getStartRow();
		int endRecord = (publicationsSearchCommand.getPageSize() * publicationsSearchCommand.getCurrentPage() - 1);
		if (publicationsList != null && publicationsList.size() > 0 && endRecord >= publicationsList.size())
			endRecord = publicationsList.size() - 1;
		if (endRecord < 0) {
			endRecord = 0;
		}

		if (publicationsList != null && publicationsList.size() > 0) {

			for (int i = startRecord; i <= endRecord; i++) {
				publicationsFinalList.add(publicationsList.get(i));
			}
			searchResponseVO.setSearchPublicationResults(publicationsFinalList);
			searchResponseVO.setTotalNumberOfrows(publicationsList.size());
			searchResponseVO.setCurrentPage(publicationsSearchCommand.getCurrentPage());
			PaginationUtil.updateSearcvhResponseWithPaginationLimits(searchResponseVO, pageSize, paginationSetSize);
		}

		return searchResponseVO;
	}

	public List<CategoryVO> getPublicationsCategories(PublicationsVO publicationsVO) throws UAQException {

		AssetsBean categoriesAssets;
		List<CategoryVO> categoriesList;

		try {

			categoriesAssets = AssetUtil.searchAssetbyDefinition("Content_P", "Category", "egd", publicationsVO.getTicketId());
			categoriesList = getCategoriesList(categoriesAssets);

		} catch (SSOException e) {
			logger.error("Error getting Publications Page Details " + e.getMessage());
			throw new UAQException(e);
		}

		return categoriesList;
	}

	@Override
	public PublicationsVO execute(PublicationsVO inputObject) throws UAQException {
		PublicationsVO publicationsVO = new PublicationsVO();
		/*
		 * NavigationVO navigationVO =
		 * WebReferneceDAO.findByPrimaryKey(inputObject.getUrl());
		 * publicationsVO.setAssetId(navigationVO.getAssetId());
		 * logger.debug("Id of the landing Page is" +
		 * publicationsVO.getAssetId());
		 */
		return publicationsVO;
	}

	/**
	 * This method is used to convert the assetsBean to List of CategoryVO
	 * 
	 * @param assetsBean
	 * @return
	 */
	private List<CategoryVO> getCategoriesList(AssetsBean assetsBean) {

		List<CategoryVO> categoriesList = new ArrayList<CategoryVO>();

		List<AssetInfo> assetInfoList = assetsBean.getAssetinfos();

		for (AssetInfo assetBean : assetInfoList) {

			CategoryVO category = new CategoryVO();
			category.setAssetId(assetBean.getId().split(":")[1]);

			for (FieldInfo attribute : assetBean.getFieldinfos()) {

				if (attribute.getFieldname().equalsIgnoreCase("name")) {
					category.setName(attribute.getData());
				}
			}
			categoriesList.add(category);
		}

		return categoriesList;
	}

	public PublicationsVO getPublicationsDetails(NavigationVO navigationVO) throws SSOException {
		PublicationsVO publicationsVO = null;
		try {
			NavigationVO navigation = contentReferneceDAO.findByPrimaryKey(navigationVO);
			AssetBean assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_C", navigation.getAssetId(), navigationVO.getTicketId());
			publicationsVO = getAssetDetail(assetBean);
		} catch (UAQException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return publicationsVO;
	}

	private PublicationsVO getAssetDetail(AssetBean assetBean) {
		PublicationsVO publicationsVO = new PublicationsVO();
		publicationsVO.setAssetId(assetBean.getId().split(":")[1]);
		publicationsVO.setName(assetBean.getName());
		SimpleDateFormat sdfout = new SimpleDateFormat("dd-MM-yyyy");

		for (Attribute attribute : assetBean.getAttributes()) {

			if (attribute.getName().equalsIgnoreCase("Title") && attribute.getData() != null) {
				publicationsVO.setTitle(attribute.getData().getStringValue());
			}

			else if (attribute.getName().equalsIgnoreCase("TeaserTitle") && attribute.getData() != null) {
				publicationsVO.setTeaserTitle(attribute.getData().getStringValue());
			}

			else if (attribute.getName().equals("SelectFile") && attribute.getData().getBlobValue() != null) {
				publicationsVO.setFile(AssetUtil.getBolbURL(attribute.getData().getBlobValue().getHref(), "application%2Fpdf"));
			}

			

		}
		return publicationsVO;
	}

	@Override
	public PublicationsVO executeSites(PublicationsVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
