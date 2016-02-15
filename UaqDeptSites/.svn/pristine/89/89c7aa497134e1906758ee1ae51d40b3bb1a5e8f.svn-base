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
import com.uaq.controller.mapper.CertificatesDetailMapper;
import com.uaq.dao.BaseDAO;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.PaginationUtil;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.Asset;
import com.uaq.vo.CertificatesPageVO;
import com.uaq.vo.CertificatesVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.SearchResponseVO;

/**
 * This class is used to manage departments related functionality
 * 
 * @author mraheem
 * 
 */
@Service(value = "certificatesService")
public class CertificateService implements BaseService<CertificatesVO, List<CertificatesVO>> {

	@Autowired
	@Qualifier("certificatesDetailMapper")
	private BaseVOMapper mapper;

	@Autowired
	@Qualifier("imageMapper")
	private BaseVOMapper imageMapper;

	@Autowired
	@Qualifier("getAssetDetailsDAO")
	BaseDAO<Asset, List<Asset>> getAssetDetailsDAO;
	
	@Autowired
	@Qualifier("certificatesPageVOMapper")
	private BaseVOMapper certificatesPageVOMapper;

	protected static UAQLogger logger = new UAQLogger(CertificateService.class);

	/**
	 * 
	 * @param certificatesSearchCommand
	 * @param sector
	 * @param assetobj
	 * @return
	 * @throws UAQException
	 * @throws SSOException
	 */
	public SearchResponseVO getCertificatesList(SearchCommand certificatesSearchCommand, String sector, Asset assetobj) throws UAQException, SSOException {

		List<Asset> objAsset = null;
		AssetBean assetBean = new AssetBean();
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		CertificatesVO certificatesVO = new CertificatesVO();
		CertificatesDetailMapper certificatesMapper = new CertificatesDetailMapper();

		int pageSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.pagesize"));
		int paginationSetSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.setsize"));
		certificatesSearchCommand.setStartRow((certificatesSearchCommand.getCurrentPage() - 1) * pageSize);

		List<CertificatesVO> certificatesList = new ArrayList<CertificatesVO>();
		List<CertificatesVO> certificateFinalList= new ArrayList<CertificatesVO>();

		certificatesSearchCommand.setPageSize(pageSize);
		

		objAsset = getAssetDetailsDAO.execute(assetobj);

		for (Asset eachAsset : objAsset) {
			List<ImageVO>ImageListVO= new ArrayList<ImageVO>();
			assetBean = AssetUtil.getAssetDetail(sector, "Content_C", eachAsset.getAssetId(), certificatesSearchCommand.getTicketId());

			certificatesVO = (CertificatesVO) certificatesMapper.mapAssetToVO(assetBean);

			if (certificatesVO.getImages().size() > 0) {
				for (ImageVO imageVO : certificatesVO.getImages()) {

					assetBean = AssetUtil.getAssetDetail(sector, imageVO.getAssetType(), imageVO.getAssetId(), certificatesSearchCommand.getTicketId());
					imageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
					ImageListVO.add(imageVO);
					certificatesVO.setImages(ImageListVO);
				}

			}

			certificatesVO.setUrl(UrlTransliterationUtil.getTransliteratedString(certificatesVO.getName()));

			certificatesList.add(certificatesVO);

		}
		
		int startRecord = certificatesSearchCommand.getStartRow();		
		int endRecord = (certificatesSearchCommand.getPageSize()*certificatesSearchCommand.getCurrentPage() - 1);
		if(certificatesList != null && certificatesList.size() > 0 && endRecord >= certificatesList.size())
			endRecord = certificatesList.size()-1;			
		if(endRecord < 0 ){
			endRecord = 0;
		}
	
		if (certificatesList != null && certificatesList.size() > 0) {
			
			for(int i=startRecord;i <= endRecord; i++){			
				certificateFinalList.add(certificatesList.get(i));
			}
			searchResponseVO.setSearchCertificateResults(certificateFinalList);
			searchResponseVO.setTotalNumberOfrows(certificatesList.size());
			searchResponseVO.setCurrentPage(certificatesSearchCommand.getCurrentPage());
			PaginationUtil.updateSearcvhResponseWithPaginationLimits(searchResponseVO, pageSize, paginationSetSize);
		}
		return searchResponseVO;
	}

	/**
	 * This method is not used
	 */
	@Override
	public List<CertificatesVO> execute(CertificatesVO certificateVO) throws UAQException {
		List<CertificatesVO> certificatesList = new ArrayList<CertificatesVO>();
		AssetsBean assetsBean;
		try {
			assetsBean = AssetUtil.searchAssetbyDefinition("Certificate", certificateVO.getSite(), "DateAndTime", "desc", certificateVO.getTicketId());
			certificatesList = getCertificatesVOList(assetsBean, certificateVO);
		} catch (SSOException e) {
			logger.error("Error get Certificates List");
		}
		return certificatesList;
	}

	/**
	 * This method is used to convert the assetsBean to List of CertificatesVO
	 * 
	 * @param assetsBean
	 * @return
	 * @throws SSOException
	 */
	private List<CertificatesVO> getCertificatesVOList(AssetsBean assetsBean, CertificatesVO certificateVO) throws SSOException {

		List<CertificatesVO> certificates = new ArrayList<CertificatesVO>();
		for (AssetInfo assetBean : assetsBean.getAssetinfos()) {
			CertificatesVO certificate = new CertificatesVO();
			certificate.setAssetId(assetBean.getId().split(":")[1]);
			certificate.setTicketId(certificateVO.getTicketId());
			certificate.setSite(certificateVO.getSite());
			certificate = getCertificateDetails(certificate);
			certificates.add(certificate);
		}
		return certificates;
	}

	/**
	 * This method is used to get details for the given certificate id
	 * 
	 * @param CertificatesVO
	 * @return
	 * @throws SSOException
	 */

	private CertificatesVO getCertificateDetails(CertificatesVO certificatesVO) throws SSOException {
		CertificatesVO certificatesVOReturn;
		List<ImageVO> images = new ArrayList<ImageVO>();
		AssetBean assetBean = AssetUtil.getAssetDetail(certificatesVO.getSite(), "Content_C", certificatesVO.getAssetId(), certificatesVO.getTicketId());
		certificatesVOReturn = (CertificatesVO) mapper.mapAssetToVO(assetBean);
		if (certificatesVOReturn.getImages().size() > 0) {
			for (ImageVO imageVO : certificatesVOReturn.getImages()) {
				assetBean = AssetUtil.getAssetDetail(certificatesVO.getSite(), imageVO.getAssetType(), imageVO.getAssetId(), certificatesVO.getTicketId());
				imageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
				images.add(imageVO);
			}

		}
		certificatesVOReturn.setImages(images);
		return certificatesVOReturn;
	}

	@Override
	public List<CertificatesVO> executeSites(CertificatesVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	public CertificatesPageVO getPageVO(NavigationVO navigationVO) {
		logger.enter("certificatesService");
		CertificatesPageVO certificatesPageVO = null;
		
		try {
			AssetBean  assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Page", navigationVO.getAssetId(), navigationVO.getTicketId());
			certificatesPageVO = (CertificatesPageVO) certificatesPageVOMapper.mapAssetToVO(assetBean);
		} catch (SSOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return certificatesPageVO;
	}

}
