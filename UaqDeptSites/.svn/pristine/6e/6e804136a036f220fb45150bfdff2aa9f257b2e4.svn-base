package com.uaq.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uaq.logger.UAQLogger;

@Repository(value = "localizationDAO")
public class LocalizationDAO {

	protected static UAQLogger logger = new UAQLogger(NavigationDAO.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String getTranslatedAssetIdPage(String assetId) {
		String localisedAssetId;
		GetLocalizedAssetStoredProcedure assetStoredProcedure = new GetLocalizedAssetStoredProcedure(jdbcTemplate);
		localisedAssetId = assetStoredProcedure.getLocalizedAsset(Long.valueOf(assetId));
		return localisedAssetId;

	}
	
	public String getTranslatedAssetIdContent(String assetId) {
		String localisedAssetId;
		GetLocalizedAssetStoredProcedure assetStoredProcedure = new GetLocalizedAssetStoredProcedure(jdbcTemplate);
		localisedAssetId = assetStoredProcedure.getLocalizedAsset(Long.valueOf(assetId));
		return localisedAssetId;

	}

}
