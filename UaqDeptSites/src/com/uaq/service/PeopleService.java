package com.uaq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.wem.sso.SSOException;
import com.uaq.command.SearchCommand;
import com.uaq.common.AssetUtil;
import com.uaq.common.PropertiesUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.controller.mapper.PeopleMapper;
import com.uaq.dao.BaseDAO;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.PaginationUtil;
import com.uaq.vo.Asset;
import com.uaq.vo.ImageVO;
import com.uaq.vo.PeopleVO;
import com.uaq.vo.SearchResponseVO;

/**
 * This class is used to manage departments related functionality
 * 
 * @author ajain
 * 
 */
@Service(value = "peopleService")
public class PeopleService implements BaseService<PeopleVO, List<PeopleVO>> {

	@Autowired
	@Qualifier("peopleMapper")
	private BaseVOMapper mapper;

	@Autowired
	@Qualifier("imageMapper")
	private BaseVOMapper imageMapper;

	@Autowired
	@Qualifier("getAssetDetailsDAO")
	BaseDAO<Asset, List<Asset>> getAssetDetailsDAO;

	protected static UAQLogger logger = new UAQLogger(PeopleService.class);

	/**
	 * This method is not used
	 */
	@Override
	public List<PeopleVO> execute(PeopleVO peopleVO) throws UAQException {
		List<PeopleVO> peopleList = new ArrayList<PeopleVO>();
		AssetsBean assetsBean;
		try {
			assetsBean = AssetUtil.searchAssetbyDefinition("People", peopleVO.getSite(), "DateAndTime", "desc", peopleVO.getTicketId());
			peopleList = getPeopleVOList(assetsBean, peopleVO);
		} catch (SSOException e) {
			logger.error("Error get People List");
		}
		return peopleList;
	}

	public SearchResponseVO getPeopleList(SearchCommand peopleSearchCommand, String sector, Asset assetobj) throws UAQException, SSOException {

		List<Asset> objAsset = null;
		AssetBean assetBean = new AssetBean();
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		PeopleVO peopleVO = new PeopleVO();
		PeopleMapper peopleMapper = new PeopleMapper();

		int pageSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.pagesize"));
		int paginationSetSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.setsize"));
		peopleSearchCommand.setStartRow((peopleSearchCommand.getCurrentPage() - 1) * pageSize);

		List<PeopleVO> peopleList = new ArrayList<PeopleVO>();
		List<PeopleVO> peopleFinalList= new ArrayList<PeopleVO>();

		peopleSearchCommand.setPageSize(pageSize);

		objAsset = getAssetDetailsDAO.execute(assetobj);

		for (Asset eachAsset : objAsset) {
			assetBean = AssetUtil.getAssetDetail(sector, "Content_C", eachAsset.getAssetId(), peopleSearchCommand.getTicketId());

			peopleVO = (PeopleVO) peopleMapper.mapAssetToVO(assetBean);

			if (peopleVO.getImages().size() > 0) {
				for (ImageVO imageVO : peopleVO.getImages()) {

					assetBean = AssetUtil.getAssetDetail(sector, imageVO.getAssetType(), imageVO.getAssetId(), peopleSearchCommand.getTicketId());
					imageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);

					peopleVO.setImage(imageVO.getTeaserImage());
				}

			}
			peopleList.add(peopleVO);

		}
		
		int startRecord = peopleSearchCommand.getStartRow();		
		int endRecord = (peopleSearchCommand.getPageSize()*peopleSearchCommand.getCurrentPage() - 1);
		if(peopleList != null && peopleList.size() > 0 && endRecord >= peopleList.size())
			endRecord = peopleList.size()-1;
		if(endRecord < 0 ){
			endRecord = 0;
		}

		if (peopleList != null && peopleList.size() > 0) {
			for(int i=startRecord;i <= endRecord; i++){			
				peopleFinalList.add(peopleList.get(i));
			}
			searchResponseVO.setSearchPeopleResults(peopleFinalList);
			searchResponseVO.setTotalNumberOfrows(peopleList.size());
			searchResponseVO.setCurrentPage(peopleSearchCommand.getCurrentPage());
			PaginationUtil.updateSearcvhResponseWithPaginationLimits(searchResponseVO, pageSize, paginationSetSize);
		}

		return searchResponseVO;
	}

	/**
	 * This method is used to convert the assetsBean to List of ProjectsVO
	 * 
	 * @param assetsBean
	 * @return
	 * @throws SSOException
	 */
	private List<PeopleVO> getPeopleVOList(AssetsBean assetsBean, PeopleVO peopleVO) throws SSOException {

		List<PeopleVO> peopleList = new ArrayList<PeopleVO>();
		for (AssetInfo assetBean : assetsBean.getAssetinfos()) {
			PeopleVO people = new PeopleVO();
			people.setAssetId(assetBean.getId().split(":")[1]);
			people.setTicketId(peopleVO.getTicketId());
			people.setSite(peopleVO.getSite());
			people = getPeopleDetails(people);
			peopleList.add(people);
		}

		return peopleList;
	}

	/**
	 * This method is used to get details for the given people id
	 * 
	 * @param PeopleVO
	 * @return
	 * @throws SSOException
	 */

	private PeopleVO getPeopleDetails(PeopleVO peopleVO) throws SSOException {
		PeopleVO peopleVOReturn;
		AssetBean assetBean = AssetUtil.getAssetDetail(peopleVO.getSite(), "Content_C", peopleVO.getAssetId(), peopleVO.getTicketId());

		peopleVOReturn = (PeopleVO) mapper.mapAssetToVO(assetBean);

		return peopleVOReturn;
	}

	@Override
	public List<PeopleVO> executeSites(PeopleVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}
}
