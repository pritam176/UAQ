/**
 * 
 */
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
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.PaginationUtil;
import com.uaq.vo.ArchaeologyHeritageVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.SearchResponseVO;


/**
 * @author TACME
 *
 */

@Service(value="archaeologyHeritageService")
public class ArchaeologyHeritageService implements BaseService<ArchaeologyHeritageVO, SearchResponseVO>  {
	
	@Autowired
	@Qualifier("archaeologyHeritageMapper")
	BaseVOMapper archaeologyHeritageMapper;
	
	@Autowired
	@Qualifier("imageMapper")
	BaseVOMapper imageMapper;
	
	protected static UAQLogger logger = new UAQLogger(ArchaeologyHeritageService.class);

	@Override
	public SearchResponseVO execute(ArchaeologyHeritageVO archaeologyHeritageVO) throws UAQException {
		
		
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		
		String site = archaeologyHeritageVO.getSite();
		String multiticket = archaeologyHeritageVO.getTicketId();
		String assetType = archaeologyHeritageVO.getAssetType();
			List<ArchaeologyHeritageVO>archaeologyAssetInfo = new ArrayList<ArchaeologyHeritageVO>(); 
			
		try {
			AssetsBean assetsBean	= AssetUtil.searchAssetbyDefinition("Content_C", assetType,site , multiticket);
			
			for(AssetInfo assetInfo : assetsBean.getAssetinfos()){
				ArchaeologyHeritageVO archaeologyHeritageAssetVO = new ArchaeologyHeritageVO();
				archaeologyHeritageAssetVO.setAssetId(assetInfo.getId().split(":")[1]);
				archaeologyHeritageAssetVO.setTicketId(multiticket);
				archaeologyHeritageAssetVO.setSite(site);
				archaeologyHeritageAssetVO.setAssetType(assetType);
				archaeologyHeritageAssetVO =	getArchaeologyHeritageAssetDetails(archaeologyHeritageAssetVO,site,multiticket);
				if(archaeologyHeritageAssetVO!=null && archaeologyHeritageVO.getLanguage().equalsIgnoreCase(archaeologyHeritageAssetVO.getLanguage())){
					archaeologyAssetInfo.add(archaeologyHeritageAssetVO);
				}
				
			}
		} catch (SSOException e) {
			logger.error(e.getMessage());
		}
		searchResponseVO.setSearchArchaeologyHeritageResults(archaeologyAssetInfo);
		searchResponseVO.setTotalNumberOfrows(archaeologyAssetInfo.size());
		return searchResponseVO;
	}

	@Override
	public SearchResponseVO executeSites(ArchaeologyHeritageVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public SearchResponseVO setPaginagetion(SearchResponseVO searchResponseVO,SearchCommand searchCommand){
		int pageSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.pagesize"));
		int paginationSetSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.setsize"));
		searchCommand.setStartRow((searchCommand.getCurrentPage() - 1) * pageSize);
		searchCommand.setPageSize(pageSize);
		List<ArchaeologyHeritageVO>archaeologyAssetInfoListFinal = new ArrayList<ArchaeologyHeritageVO>(); 
		List<ArchaeologyHeritageVO>archaeologyAssetInfoList = new ArrayList<ArchaeologyHeritageVO>(); 
		archaeologyAssetInfoList.addAll(searchResponseVO.getSearchArchaeologyHeritageResults());
		
		int startRecord = searchCommand.getStartRow();		
		int endRecord = (searchCommand.getPageSize()*searchCommand.getCurrentPage() - 1);
		if(archaeologyAssetInfoList != null && archaeologyAssetInfoList.size() > 0 && endRecord >= archaeologyAssetInfoList.size())
			endRecord = archaeologyAssetInfoList.size()-1;			
		if(endRecord < 0 ){
			endRecord = 0;
		}
		
		
	
		if (archaeologyAssetInfoList != null && archaeologyAssetInfoList.size() > 0) {
			
			for(int i=startRecord;i <= endRecord; i++){			
				archaeologyAssetInfoListFinal.add(archaeologyAssetInfoList.get(i));
			}
			searchResponseVO.setSearchArchaeologyHeritageResults(archaeologyAssetInfoListFinal);
			searchResponseVO.setTotalNumberOfrows(archaeologyAssetInfoList.size());
			searchResponseVO.setCurrentPage(searchCommand.getCurrentPage());
			PaginationUtil.updateSearcvhResponseWithPaginationLimits(searchResponseVO, pageSize, paginationSetSize);
		}
		return searchResponseVO;
		
		/*if (searchResponseVO != null && searchResponseVO.getSearchArchaeologyHeritageResults() != null && searchResponseVO.getSearchArchaeologyHeritageResults().size() > 0) {
			searchResponseVO.setCurrentPage(searchCommand.getCurrentPage());
			PaginationUtil.updateSearcvhResponseWithPaginationLimits(searchResponseVO, pageSize, paginationSetSize);
		}
		*/
		//return searchResponseVO;
		
	}
	
	
	private ArchaeologyHeritageVO getArchaeologyHeritageAssetDetails (ArchaeologyHeritageVO archaeologyHeritageAssetVO,String site,String ticketId) throws SSOException{
		
		AssetBean assetBean = AssetUtil.getAssetDetail(archaeologyHeritageAssetVO.getSite(), "Content_C", archaeologyHeritageAssetVO.getAssetId(), archaeologyHeritageAssetVO.getTicketId());
		
		archaeologyHeritageAssetVO  = (ArchaeologyHeritageVO) archaeologyHeritageMapper.mapAssetToVO(assetBean);
		archaeologyHeritageAssetVO.setLanguage(assetBean.getDimensions().get(0).getName());
		if(archaeologyHeritageAssetVO.getImages()!=null){
			archaeologyHeritageAssetVO.setImages(getImageAsset( archaeologyHeritageAssetVO.getImages(), site, ticketId));
		}

		return archaeologyHeritageAssetVO;
		
	}
	

	private List<ImageVO> getImageAsset(List<ImageVO> imageList,String site,String ticketID) throws SSOException{
		List<ImageVO> archaeologyHeritageImageVO =  new ArrayList<ImageVO>();
		
		for (ImageVO imageVO : imageList) {

			AssetBean imageAssetBean = AssetUtil.getAssetDetail(site, "Content_C", imageVO.getAssetId(), ticketID);
			ImageVO images = getImageAsset(imageAssetBean);
			archaeologyHeritageImageVO.add(images);
			
		}
		return archaeologyHeritageImageVO;
	}
	
	// TODO: Generalize this function
		private ImageVO getImageAsset(AssetBean assetBean) {
			ImageVO newsImageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
			return newsImageVO;
		}
	
}
