package com.uaq.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Its a stored procedure creation class which is used to record popular news
 * 
 * @author nsharma
 * 
 */
public class GetLocalizedAssetStoredProcedure extends StoredProcedure {

	private static final String storeProcedureName = "getlocalized_asset";

	public GetLocalizedAssetStoredProcedure(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, storeProcedureName);
		declareParameter(new SqlParameter("asset_id", Types.NUMERIC));
		declareParameter(new SqlOutParameter("translated_id", Types.NUMERIC));
		compile();
	}

	/**
	 * @param news
	 * @return
	 */
	public String getLocalizedAsset(Long assetId) {

		String outPutAssetid = null;
		Map<String, Object> inParameters = new HashMap<String, Object>();
		Map<String, Object> out = null;
		inParameters.put("asset_id", assetId);
		try {
			out = execute(inParameters);
		} catch (Exception e) {
		}
		if (null != out) {
			outPutAssetid = String.valueOf(out.get("translated_id"));
		}
		return outPutAssetid;
	}
}
