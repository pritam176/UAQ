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
import com.uaq.controller.mapper.ReportsDetailMapper;
import com.uaq.dao.BaseDAO;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.PaginationUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.Asset;
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.ReportsPageVO;
import com.uaq.vo.ReportsVO;
import com.uaq.vo.SearchResponseVO;

/**
 * This class is used to manage departments related functionality
 * 
 * @author mraheem
 * 
 */
@Service(value = "reportsService")
public class ReportService implements BaseService<ReportsVO, List<ReportsVO>> {

	@Autowired
	@Qualifier("reportsDetailMapper")
	private BaseVOMapper mapper;

	@Autowired
	@Qualifier("imageMapper")
	private BaseVOMapper imageMapper;

	@Autowired
	@Qualifier("getAssetDetailsDAO")
	BaseDAO<Asset, List<Asset>> getAssetDetailsDAO;
	
	@Autowired
	@Qualifier("reportsPageVOMapper")
	private BaseVOMapper reportsPageVOMapper;

	protected static UAQLogger logger = new UAQLogger(ReportService.class);

	/**
	 * 
	 * @param reportsSearchCommand
	 * @param sector
	 * @param assetobj
	 * @return
	 * @throws UAQException
	 * @throws SSOException
	 */
	public SearchResponseVO getReportsList(SearchCommand reportsSearchCommand, String sector, Asset assetobj) throws UAQException, SSOException {

		List<Asset> objAsset = null;
		AssetBean assetBean = new AssetBean();
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		ReportsVO reportsVO = new ReportsVO();
		ReportsDetailMapper reportsMapper = new ReportsDetailMapper();

		int pageSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.pagesize"));
		int paginationSetSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.setsize"));
		reportsSearchCommand.setStartRow((reportsSearchCommand.getCurrentPage() - 1) * pageSize);

		List<ReportsVO> reportsList = new ArrayList<ReportsVO>();
		List<ReportsVO> reportFinalList= new ArrayList<ReportsVO>();

		reportsSearchCommand.setPageSize(pageSize);
		

		objAsset = getAssetDetailsDAO.execute(assetobj);

		for (Asset eachAsset : objAsset) {
			List<ImageVO>ImageListVO= new ArrayList<ImageVO>();
			assetBean = AssetUtil.getAssetDetail(sector, "Content_C", eachAsset.getAssetId(), reportsSearchCommand.getTicketId());

			reportsVO = (ReportsVO) reportsMapper.mapAssetToVO(assetBean);

			if (reportsVO.getImages().size() > 0) {
				for (ImageVO imageVO : reportsVO.getImages()) {

					assetBean = AssetUtil.getAssetDetail(sector, imageVO.getAssetType(), imageVO.getAssetId(), reportsSearchCommand.getTicketId());
					imageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
					ImageListVO.add(imageVO);
					reportsVO.setImages(ImageListVO);
				}

			}

			reportsVO.setUrl(UrlTransliterationUtil.getTransliteratedString(reportsVO.getName()));

			reportsList.add(reportsVO);

		}
		
		int startRecord = reportsSearchCommand.getStartRow();		
		int endRecord = (reportsSearchCommand.getPageSize()*reportsSearchCommand.getCurrentPage() - 1);
		if(reportsList != null && reportsList.size() > 0 && endRecord >= reportsList.size())
			endRecord = reportsList.size()-1;			
		if(endRecord < 0 ){
			endRecord = 0;
		}
	
		if (reportsList != null && reportsList.size() > 0) {
			
			for(int i=startRecord;i <= endRecord; i++){			
				reportFinalList.add(reportsList.get(i));
			}
			searchResponseVO.setSearchReportResults(reportFinalList);
			searchResponseVO.setTotalNumberOfrows(reportsList.size());
			searchResponseVO.setCurrentPage(reportsSearchCommand.getCurrentPage());
			PaginationUtil.updateSearcvhResponseWithPaginationLimits(searchResponseVO, pageSize, paginationSetSize);
		}
		return searchResponseVO;
	}

	/**
	 * This method is not used
	 */
	@Override
	public List<ReportsVO> execute(ReportsVO reportVO) throws UAQException {
		List<ReportsVO> reportsList = new ArrayList<ReportsVO>();
		AssetsBean assetsBean;
		try {
			assetsBean = AssetUtil.searchAssetbyDefinition("Report", reportVO.getSite(), "DateAndTime", "desc", reportVO.getTicketId());
			reportsList = getReportsVOList(assetsBean, reportVO);
		} catch (SSOException e) {
			logger.error("Error get Reports List");
		}
		return reportsList;
	}

	/**
	 * This method is used to convert the assetsBean to List of ReportsVO
	 * 
	 * @param assetsBean
	 * @return
	 * @throws SSOException
	 */
	private List<ReportsVO> getReportsVOList(AssetsBean assetsBean, ReportsVO reportVO) throws SSOException {

		List<ReportsVO> reports = new ArrayList<ReportsVO>();
		for (AssetInfo assetBean : assetsBean.getAssetinfos()) {
			ReportsVO report = new ReportsVO();
			report.setAssetId(assetBean.getId().split(":")[1]);
			report.setTicketId(reportVO.getTicketId());
			report.setSite(reportVO.getSite());
			report = getReportDetails(report);
			reports.add(report);
		}
		return reports;
	}

	/**
	 * This method is used to get details for the given report id
	 * 
	 * @param ReportsVO
	 * @return
	 * @throws SSOException
	 */

	private ReportsVO getReportDetails(ReportsVO reportsVO) throws SSOException {
		ReportsVO reportsVOReturn;
		List<ImageVO> images = new ArrayList<ImageVO>();
		AssetBean assetBean = AssetUtil.getAssetDetail(reportsVO.getSite(), "Content_C", reportsVO.getAssetId(), reportsVO.getTicketId());
		reportsVOReturn = (ReportsVO) mapper.mapAssetToVO(assetBean);
		if (reportsVOReturn.getImages().size() > 0) {
			for (ImageVO imageVO : reportsVOReturn.getImages()) {
				assetBean = AssetUtil.getAssetDetail(reportsVO.getSite(), imageVO.getAssetType(), imageVO.getAssetId(), reportsVO.getTicketId());
				imageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
				images.add(imageVO);
			}

		}
		reportsVOReturn.setImages(images);
		return reportsVOReturn;
	}

	@Override
	public List<ReportsVO> executeSites(ReportsVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	public ReportsPageVO getPageVO(NavigationVO navigationVO) {
		logger.enter("reportsService");
		ReportsPageVO reportsPageVO = null;
		
		try {
			AssetBean  assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Page", navigationVO.getAssetId(), navigationVO.getTicketId());
			reportsPageVO = (ReportsPageVO) reportsPageVOMapper.mapAssetToVO(assetBean);
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return reportsPageVO;
	}

}
