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
public class NewsDetailMapper implements RowMapper<NewsVO> {

	@Override
	public NewsVO mapRow(ResultSet resultset, int rowNum) throws SQLException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		NewsVO newsVO = new NewsVO();
		newsVO.setAssetId(resultset.getString("ID"));
		newsVO.setName(resultset.getString("NAME"));
		newsVO.setTitle(resultset.getString("TITLE"));
		newsVO.setTeaserTitle(resultset.getString("TEASERTITLE"));
		newsVO.setBody(resultset.getString("BODY"));
		newsVO.setTeaserText(resultset.getString("TEASERTEXT"));
		if (resultset.getTimestamp("CREATEDDATE") != null) {
			newsVO.setDate(sdf.format(resultset.getTimestamp("CREATEDDATE")));
		}
		// newsVO.setTeaserImage(resultset.getString("TEASERIMAGE"));
		newsVO.setImage(resultset.getString("IMAGE"));
		newsVO.setLanguage(resultset.getString("LANG"));

		return newsVO;
	}
}
