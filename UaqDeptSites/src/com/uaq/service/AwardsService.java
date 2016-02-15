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
import com.uaq.controller.mapper.AwardsDetailMapper;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.dao.BaseDAO;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.PaginationUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.Asset;
import com.uaq.vo.AwardsPageVO;
import com.uaq.vo.AwardsVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.SearchResponseVO;

/**
 * This class is used to manage departments related functionality
 * 
 * @author mraheem
 * 
 */
@Service(value = "awardsService")
public class AwardsService implements BaseService<AwardsVO, List<AwardsVO>> {

	@Autowired
	@Qualifier("awardsDetailMapper")
	private BaseVOMapper mapper;

	@Autowired
	@Qualifier("imageMapper")
	private BaseVOMapper imageMapper;

	@Autowired
	@Qualifier("getAssetDetailsDAO")
	BaseDAO<Asset, List<Asset>> getAssetDetailsDAO;
	
	@Autowired
	@Qualifier("awardsPageVOMapper")
	private BaseVOMapper awardsPageVOMapper;

	protected static UAQLogger logger = new UAQLogger(AwardsService.class);

	/**
	 * 
	 * @param awardsSearchCommand
	 * @param sector
	 * @param assetobj
	 * @return
	 * @throws UAQException
	 * @throws SSOException
	 */
	public SearchResponseVO getAwardsList(SearchCommand awardsSearchCommand, String sector, Asset assetobj) throws UAQException, SSOException {

		List<Asset> objAsset = null;
		AssetBean assetBean = new AssetBean();
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		AwardsVO awardsVO = new AwardsVO();
		AwardsDetailMapper awardsMapper = new AwardsDetailMapper();

		int pageSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.pagesize"));
		int paginationSetSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.setsize"));
		awardsSearchCommand.setStartRow((awardsSearchCommand.getCurrentPage() - 1) * pageSize);

		List<AwardsVO> awardsList = new ArrayList<AwardsVO>();
		List<AwardsVO> awardFinalList= new ArrayList<AwardsVO>();

		awardsSearchCommand.setPageSize(pageSize);
		

		objAsset = getAssetDetailsDAO.execute(assetobj);

		for (Asset eachAsset : objAsset) {
			List<ImageVO>ImageListVO= new ArrayList<ImageVO>();
			assetBean = AssetUtil.getAssetDetail(sector, "Content_C", eachAsset.getAssetId(), awardsSearchCommand.getTicketId());

			awardsVO = (AwardsVO) awardsMapper.mapAssetToVO(assetBean);

			if (awardsVO.getImages().size() > 0) {
				for (ImageVO imageVO : awardsVO.getImages()) {

					assetBean = AssetUtil.getAssetDetail(sector, imageVO.getAssetType(), imageVO.getAssetId(), awardsSearchCommand.getTicketId());
					imageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
					ImageListVO.add(imageVO);
					awardsVO.setImages(ImageListVO);
				}

			}

			awardsVO.setUrl(UrlTransliterationUtil.getTransliteratedString(awardsVO.getName()));

			awardsList.add(awardsVO);

		}
		
		int startRecord = awardsSearchCommand.getStartRow();		
		int endRecord = (awardsSearchCommand.getPageSize()*awardsSearchCommand.getCurrentPage() - 1);
		if(awardsList != null && awardsList.size() > 0 && endRecord >= awardsList.size())
			endRecord = awardsList.size()-1;			
		if(endRecord < 0 ){
			endRecord = 0;
		}
	
		if (awardsList != null && awardsList.size() > 0) {
			
			for(int i=startRecord;i <= endRecord; i++){			
				awardFinalList.add(awardsList.get(i));
			}
			searchResponseVO.setSearchAwardResults(awardFinalList);
			searchResponseVO.setTotalNumberOfrows(awardsList.size());
			searchResponseVO.setCurrentPage(awardsSearchCommand.getCurrentPage());
			PaginationUtil.updateSearcvhResponseWithPaginationLimits(searchResponseVO, pageSize, paginationSetSize);
		}
		return searchResponseVO;
	}

	/**
	 * This method is not used
	 */
	@Override
	public List<AwardsVO> execute(AwardsVO awardVO) throws UAQException {
		List<AwardsVO> awardsList = new ArrayList<AwardsVO>();
		AssetsBean assetsBean;
		try {
			assetsBean = AssetUtil.searchAssetbyDefinition("Award", awardVO.getSite(), "DateAndTime", "desc", awardVO.getTicketId());
			awardsList = getAwardsVOList(assetsBean, awardVO);
		} catch (SSOException e) {
			logger.error("Error get Awards List");
		}
		return awardsList;
	}

	/**
	 * This method is used to convert the assetsBean to List of AwardsVO
	 * 
	 * @param assetsBean
	 * @return
	 * @throws SSOException
	 */
	private List<AwardsVO> getAwardsVOList(AssetsBean assetsBean, AwardsVO awardVO) throws SSOException {

		List<AwardsVO> awards = new ArrayList<AwardsVO>();
		for (AssetInfo assetBean : assetsBean.getAssetinfos()) {
			AwardsVO award = new AwardsVO();
			award.setAssetId(assetBean.getId().split(":")[1]);
			award.setTicketId(awardVO.getTicketId());
			award.setSite(awardVO.getSite());
			award = getAwardDetails(award);
			awards.add(award);
		}
		return awards;
	}

	/**
	 * This method is used to get details for the given award id
	 * 
	 * @param AwardsVO
	 * @return
	 * @throws SSOException
	 */

	private AwardsVO getAwardDetails(AwardsVO awardsVO) throws SSOException {
		AwardsVO awardsVOReturn;
		List<ImageVO> images = new ArrayList<ImageVO>();
		AssetBean assetBean = AssetUtil.getAssetDetail(awardsVO.getSite(), "Content_C", awardsVO.getAssetId(), awardsVO.getTicketId());
		awardsVOReturn = (AwardsVO) mapper.mapAssetToVO(assetBean);
		if (awardsVOReturn.getImages().size() > 0) {
			for (ImageVO imageVO : awardsVOReturn.getImages()) {
				assetBean = AssetUtil.getAssetDetail(awardsVO.getSite(), imageVO.getAssetType(), imageVO.getAssetId(), awardsVO.getTicketId());
				imageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
				images.add(imageVO);
			}

		}
		awardsVOReturn.setImages(images);
		return awardsVOReturn;
	}

	@Override
	public List<AwardsVO> executeSites(AwardsVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	public AwardsPageVO getPageVO(NavigationVO navigationVO) {
		logger.enter("AwardsService");
		AwardsPageVO awardsPageVO = null;
		
		try {
			AssetBean  assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Page", navigationVO.getAssetId(), navigationVO.getTicketId());
			awardsPageVO = (AwardsPageVO) awardsPageVOMapper.mapAssetToVO(assetBean);
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return awardsPageVO;
	}

}
