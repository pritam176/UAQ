package com.uaq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.NavigationVO;

@Repository(value = "urlDAO")
public class URLDAO implements GenericDao<NavigationVO, String> {

	protected static UAQLogger logger = new UAQLogger(URLDAO.class);

	@Autowired
	@Qualifier("webReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> webReferneceDAO;

	@Autowired
	@Qualifier("localizedNavigationDAO")
	GenericDao<NavigationVO, NavigationVO> localizedNavigationDAO;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String findByPrimaryKey(NavigationVO navigationVO) throws UAQException {

		NavigationVO navigationVODAO;

		if (navigationVO.getLanguage().equals("en")) {
			navigationVODAO = webReferneceDAO.findByPrimaryKey(navigationVO);
			if (null != navigationVODAO) {
				navigationVO.setAssetId(navigationVODAO.getAssetId());
				navigationVODAO = localizedNavigationDAO.findByPrimaryKey(navigationVO);
			}

		} else {
			navigationVODAO = webReferneceDAO.findByPrimaryKey(navigationVO);
		}

		StringBuffer url = new StringBuffer();
		if (navigationVODAO != null) { // temp solution by Raheem. we need to
										// revisit this issue
			logger.debug("Getting URL Name for" + navigationVO.getName() + " for ID " + navigationVODAO.getAssetId() + "  for Site " + navigationVO.getSite());
			NavigationVO navigationParentVOResponse = null;

			navigationParentVOResponse = getParent(navigationVODAO.getAssetId());
			// Handle for First Level
			if (null == navigationParentVOResponse) {
				if (navigationVO.getName().equalsIgnoreCase("servicecatalog")) {
					url.append("services");
				} else {
					url.append("content");
				}
				url.append("/");
				url.append(UrlTransliterationUtil.getTransliteratedString(navigationVO.getName()));
				url.append(".html");

			} else if (isParentARoot(navigationParentVOResponse)) {
				url.append(UrlTransliterationUtil.getTransliteratedString(navigationVO.getName()));
				url.append(".html");

			} else {
				NavigationVO navigationThirdVOParentResponse = getParent(navigationParentVOResponse.getAssetId());
				if (null != navigationThirdVOParentResponse && null != navigationParentVOResponse) {
					if (!isParentARoot(navigationThirdVOParentResponse)) {
						url.append(UrlTransliterationUtil.getTransliteratedString(navigationThirdVOParentResponse.getName()));
						url.append("/");
					}
					url.append(UrlTransliterationUtil.getTransliteratedString(navigationParentVOResponse.getName()));
					url.append("/");
					url.append(UrlTransliterationUtil.getTransliteratedString(navigationVO.getName()));
					url.append(".html");
				}
			}
		}
		return url.toString();
	}
	
	public String getDepartmentUrl(String name, String assetSubType){
		StringBuffer url = new StringBuffer();
		url = url.append("umm-al-quwain");
		url.append("/");
		url = url.append(assetSubType.toLowerCase());
		url.append("/");
		url.append(UrlTransliterationUtil.getTransliteratedString(name));
		url.append(".html");
		return url.toString();
	}

	/**
	 * @param pageId
	 * @return
	 */
	private NavigationVO getParent(String pageId) {

		NavigationVO response = null;
		logger.debug("Getting Parent Page Name for" + pageId);
		try {

			response = jdbcTemplate.query("select id,name from Page where id in (select oid from siteplantree where nid in (select nparentid from siteplantree where oid=?))", new Object[] { pageId },
					new RowMapper<NavigationVO>() {
						@Override
						public NavigationVO mapRow(ResultSet resultset, int arg1) throws SQLException {
							NavigationVO navigationVO = new NavigationVO();
							navigationVO.setAssetId(resultset.getString("ID"));
							navigationVO.setName(resultset.getString("NAME"));
							return navigationVO;
						}

					}).get(0);
		} catch (Exception e) {
			logger.error("Did not find page with page id :" + pageId);
		}

		return response;

	}

	@Override
	public List<String> listAll() throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(String instance) throws UAQException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String instance) throws UAQException {
		// TODO Auto-generated method stub

	}

	private Boolean isParentARoot(NavigationVO navigationVO) {
		Boolean isRoot = Boolean.FALSE;

		if (null != navigationVO) {
			String parentPageName = navigationVO.getName();
			isRoot = null != parentPageName
					&& (parentPageName.equals("UAQPortal") || parentPageName.equals("EGovernmentHome") || parentPageName.equals("Municipality") || parentPageName.equals("Economic Development")
							|| parentPageName.equals("Lands Properties") || parentPageName.equals("Planning Survey Department") || parentPageName.equals("Public Works Service")
							|| parentPageName.equals("Archaeology & Heritage") || parentPageName.equals("Falaj Municipality") || parentPageName.equals("Industrial City Authority")
							|| parentPageName.equals("Ports Customs") || parentPageName.equals("Executive Council") || parentPageName.equals("Finance Administration") || parentPageName
								.equals("Financial Auditing"));
		}
		return isRoot;
	}
}
