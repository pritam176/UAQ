package com.uaq.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.NavigationVO;

@Repository(value = "localizedNavigationDAO")
public class LocalizedNavigationDAO implements GenericDao<NavigationVO, NavigationVO> {

	protected static UAQLogger logger = new UAQLogger(LocalizedNavigationDAO.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Autowired
	@Qualifier("navigationDAO")
	GenericDao<NavigationVO, NavigationVO> navigationDAO;

	@Override
	@Cacheable(value = "localizednavigations", key = "#queryVO.assetId")
	public NavigationVO findByPrimaryKey(NavigationVO queryVO) throws UAQException {
		//logger.debug("Service | Get the asset details of the asset" + queryVO.getAssetId());
		NavigationVO navigationVO = null;

		GetLocalizedAssetStoredProcedure assetStoredProcedure = new GetLocalizedAssetStoredProcedure(jdbcTemplate);
		String translatedAssetId = assetStoredProcedure.getLocalizedAsset(Long.valueOf(queryVO.getAssetId()));

		if (null != translatedAssetId) {
			queryVO.setAssetId(translatedAssetId);

			try {
				navigationVO = navigationDAO.findByPrimaryKey(queryVO);
			} catch (UAQException e) {
				logger.error("Error Gettong Details");
			}
		}
		return navigationVO;
	}

	@Override
	public List<NavigationVO> listAll() throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(NavigationVO instance) throws UAQException {
		// TODO Auto-generated method stub

	}

	@Override
	@CacheEvict(value = { "localizednavigations" }, allEntries = true)
	public void delete(NavigationVO instance) throws UAQException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDepartmentUrl(String name, String subType) {
		// TODO Auto-generated method stub
		return null;
	}

}
