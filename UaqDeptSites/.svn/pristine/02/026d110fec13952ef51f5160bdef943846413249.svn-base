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
public class NewsMapper implements RowMapper<NewsVO> {

	@Override
	public NewsVO mapRow(ResultSet resultset, int rowNum) throws SQLException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");

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
		newsVO.setPostedDate(sdf.format(resultset.getTimestamp("POSTEDDATE")));
		// newsVO.setTeaserImage(resultset.getString("TEASERIMAGE"));
		newsVO.setImage(resultset.getString("IMAGE"));
		newsVO.setLanguage(resultset.getString("LANG"));

		newsVO.setCount(resultset.getLong("cnt")); // total rows count
		newsVO.setRowNum(resultset.getLong("rn")); // row number from total
													// number of rows as stated
													// above

		return newsVO;
	}
}
