package com.uaq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.Asset;

/**
 * This class retrieves records from the table for the given user name.
 * 
 * @author nsharma
 * 
 */
@Repository("getAssetDetailsDAO")
public class GetAssetDetailsDAO implements BaseDAO<Asset, List<Asset>> {

	private static transient UAQLogger logger = new UAQLogger(GetAssetDetailsDAO.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.edaleel.dao.BaseDAO#execute(java.lang.Object)
	 */
	public List<Asset> execute(Asset asset) throws UAQException {
		logger.debug("Inside GetAssetDetailsDAO");
		StringBuffer query = new StringBuffer();
		query.append("select cc.id,ccd.name from content_c cc ");
		query.append("JOIN content_cd ccd  ON ccd.id=cc.flextemplateid ");
		query.append("JOIN Content_c_dim cdim   ON cdim.cs_ownerid=cc.id ");
		query.append("JOIN dimension dim   ON cdim.cs_dimensionid=dim.id ");
		query.append("JOIN assetpublication ap   ON cc.id=ap.assetid ");
		query.append("JOIN Publication pp   ON pp.id=pubid ");
		query.append(" WHERE ccd.name=?  AND cc.status !='VO'  AND dim.name=?  AND pp.name=? ");
		query.append("ORDER BY cc.updateddate desc");

		List<Asset> newsList = jdbcTemplate.query(query.toString(), new Object[] { asset.getAssetType(), asset.getLanguage(), asset.getSite() }, new RowMapper<Asset>() {
			public Asset mapRow(ResultSet resultset, int rowNum) throws SQLException {
				Asset news = new Asset();
				news.setAssetId(resultset.getString("ID"));
				news.setAssetType(resultset.getString("NAME"));
				return news;
			}
		});
		return newsList;

	}
}
