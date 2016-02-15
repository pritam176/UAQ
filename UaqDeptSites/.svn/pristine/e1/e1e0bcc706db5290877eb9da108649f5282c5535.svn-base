package com.uaq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.uaq.vo.NewsVO;

/**
 * Maps SQL resultset to InquiryPaymentResponse object.
 * 
 * @author mraheem
 * 
 */
public class SearchMapper implements RowMapper<NewsVO> {

	@Override
	public NewsVO mapRow(ResultSet resultset, int rowNum) throws SQLException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		NewsVO searchVO = new NewsVO();

		searchVO.setAssetId(resultset.getString("ID"));
		searchVO.setName(resultset.getString("NAME"));
		searchVO.setAssetType(resultset.getString("ASSETTYPE"));
		searchVO.setAssetSubType(resultset.getString("SUBTYPE"));
		// searchVO.setTeaserTitle(resultset.getString("TEASERTITLE"));
		// searchVO.setBody(resultset.getString("BODY"));
		searchVO.setTeaserTitle(resultset.getString("TITLE"));
		searchVO.setTeaserText(resultset.getString("TEASERTEXT"));
		searchVO.setDate(sdf.format(resultset.getTimestamp("CREATEDDATE")));
		// searchVO.(resultset.getString("AUTHOR"));
		// searchVO.(resultset.getString("EXTERNALLINK"));
		// searchVO.setImage(resultset.getString("IMAGE"));
		searchVO.setTeaserImage(resultset.getString("TEASERIMAGE"));
		// searchVO.(resultset.getString("MOBILEIMAGE"));
		// searchVO.setLanguage(resultset.getString("LANG"));
		// searchVO.(resultset.getString("WEBREFERENCEURL"));
		// searchVO.(resultset.getString("SITE"));

		searchVO.setCount(resultset.getLong("cnt")); // total rows count
		searchVO.setRowNum(resultset.getLong("rn")); // row number from total
														// number of rows as
														// stated above

		return searchVO;
	}
}
