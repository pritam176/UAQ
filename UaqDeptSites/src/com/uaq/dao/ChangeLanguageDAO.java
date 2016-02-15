package com.uaq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uaq.common.PropertiesUtil;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.NavigationVO;

@Repository(value = "changeLanguageDAO")
public class ChangeLanguageDAO implements GenericDao<NavigationVO, NavigationVO> {

	protected static UAQLogger logger = new UAQLogger(ChangeLanguageDAO.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public NavigationVO findByPrimaryKey(NavigationVO navigationVO) throws UAQException {
		String siteName = PropertiesUtil.getProperty(navigationVO.getSite() + "_csSiteName");
		logger.debug("Getting URL Details for" + navigationVO.getName() + " for language " + navigationVO.getLanguage() + "  for Site " + siteName);

		StringBuffer query = new StringBuffer();
		query.append("select p.id,p.name from Content_C p ");
		query.append("join Content_C_dim pd ");
		query.append("on p.id =pd.cs_ownerid ");
		query.append("JOIN Dimension dim ");
		query.append("on dim.id=pd.cs_dimensionid ");
		query.append("JOIN assetpublication ap ");
		query.append("ON ap.assetid= p.id ");
		query.append("JOIN Publication pn ");
		query.append("ON pn.id=ap.pubid ");
		query.append("WHERE dim.name=? ");
		query.append("AND lower(p.name)=? ");
		query.append("AND pn.name=?");

		List<NavigationVO> navigationList = jdbcTemplate.query(query.toString(), new Object[] { navigationVO.getLanguage(), navigationVO.getName(), siteName }, new RowMapper<NavigationVO>() {
			public NavigationVO mapRow(ResultSet resultset, int rowNum) throws SQLException {
				NavigationVO navigationVO = new NavigationVO();
				navigationVO.setAssetId(resultset.getString("ID"));
				navigationVO.setName(resultset.getString("NAME"));
				return navigationVO;
			}
		});

		if (null != navigationList && !navigationList.isEmpty())
			return navigationList.get(0);
		else
			return null;
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
	public void delete(NavigationVO instance) throws UAQException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDepartmentUrl(String name, String subType) {
		// TODO Auto-generated method stub
		return null;
	}

}
