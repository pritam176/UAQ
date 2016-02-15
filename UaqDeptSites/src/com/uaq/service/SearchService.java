package com.uaq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.wem.sso.SSOException;
import com.uaq.command.SearchCommand;
import com.uaq.common.AssetUtil;
import com.uaq.common.PropertiesUtil;
import com.uaq.controller.mapper.ImageMapper;
import com.uaq.dao.BaseDAO;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.PaginationUtil;
import com.uaq.util.UrlTransliterationUtil;
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
@Service(value = "searchService")
public class SearchService implements BaseService<SearchCommand, SearchResponseVO> {

	protected static UAQLogger logger = new UAQLogger(SearchService.class);

	@Autowired
	@Qualifier("navigationDAO")
	GenericDao<NavigationVO, NavigationVO> navigationDAO;

	@Autowired
	@Qualifier("urlDAO")
	GenericDao<NavigationVO, String> urlDAO;

	@Autowired
	@Qualifier("searchDAO")
	BaseDAO<SearchCommand, SearchResponseVO> searchDAO;

	public SearchResponseVO execute(SearchCommand searchCommand) throws UAQException {

		SearchResponseVO searchResponseVO = null;
		int pageSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.pagesize"));
		int paginationSetSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.setsize"));
		searchCommand.setStartRow((searchCommand.getCurrentPage() - 1) * pageSize);
		searchCommand.setPageSize(pageSize);

		searchResponseVO = searchDAO.execute(searchCommand);

		if (searchResponseVO != null && searchResponseVO.getSearchResult() != null && searchResponseVO.getSearchResult().size() > 0) {
			searchResponseVO.setCurrentPage(searchCommand.getCurrentPage());
			PaginationUtil.updateSearcvhResponseWithPaginationLimits(searchResponseVO, pageSize, paginationSetSize);
			// fill in the image using its id
			for (NewsVO newsVO : searchResponseVO.getSearchResult()) {
				// create image url

				if (newsVO.getImage() != null) {
					AssetBean assetBean;
					try {
						assetBean = AssetUtil.getAssetDetail(searchCommand.getSite(), "Content_C", newsVO.getImage(), searchCommand.getTicketId());
					} catch (SSOException e) {
						throw new UAQException(e);
					}
					ImageMapper imageMapper = new ImageMapper();
					ImageVO imageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);

					newsVO.setImage(imageVO.getTeaserImage());
				}

				// create url
				newsVO.setUrl(getUrl(searchCommand.getLanguage(), newsVO.getAssetId(), newsVO.getName(), newsVO.getAssetType(), newsVO.getAssetSubType(), searchCommand.getSite(),
						searchCommand.getTicketId()));
			}
		}
		return searchResponseVO;
	}

	/**
	 * @param id
	 * @param name
	 * @param subType
	 * @param site
	 * @param ticket
	 * @return
	 * @throws UAQException
	 */
	private String getUrl(String language, String id, String name, String assetType, String subType, String site, String ticket) throws UAQException {
		NavigationVO navigationVO = new NavigationVO();
		navigationVO.setAssetId(id);
		navigationVO.setName(UrlTransliterationUtil.getString(name));
		navigationVO.setTicketId(ticket);
		navigationVO.setSite(site);
		navigationVO.setLanguage(language);
		String url = null;

		if (id != null && name != null && subType != null) {

			if (assetType.equalsIgnoreCase("Page")) {
				url = urlDAO.findByPrimaryKey(navigationVO);
			} 
			else if(subType.equalsIgnoreCase("Department")){
				url = urlDAO.getDepartmentUrl(name, subType);
			}
			else {
				if (site.equals("uaq")) {
					url = AssetUtil.getModuleURL(name, subType);
				} else {
					url = AssetUtil.getModuleURLSite(name, subType);
				}
			}
		}

		return url;
	}

	@Override
	public SearchResponseVO executeSites(SearchCommand inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
