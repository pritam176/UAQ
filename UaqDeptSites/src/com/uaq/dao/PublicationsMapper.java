package com.uaq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.uaq.vo.ImageVO;
import com.uaq.vo.PublicationsVO;

/**
 * Maps SQL resultset to InquiryPaymentResponse object.
 * 
 * @author anair
 * 
 */
public class PublicationsMapper implements RowMapper<PublicationsVO> {

	@Override
	public PublicationsVO mapRow(ResultSet resultset, int rowNum) throws SQLException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfoutForLanding = new SimpleDateFormat("dd MMM yyyy");
		List<ImageVO> images = new ArrayList<ImageVO>();
		ImageVO imageVO = null;

		PublicationsVO publicationsVO = new PublicationsVO();
		publicationsVO.setAssetId(resultset.getString("ID"));
		publicationsVO.setName(resultset.getString("NAME"));
		publicationsVO.setTitle(resultset.getString("TITLE"));
		publicationsVO.setTeaserTitle(resultset.getString("TEASERTITLE"));
		publicationsVO.setBody(resultset.getString("BODY"));
		publicationsVO.setTeaserText(resultset.getString("TEASERTEXT"));
		if (resultset.getTimestamp("CREATEDDATE") != null) {
			publicationsVO.setDate(sdf.format(resultset.getTimestamp("CREATEDDATE")));
			publicationsVO.setLandingPageDate(sdfoutForLanding.format(resultset.getTimestamp("CREATEDDATE")));
		}
		if (resultset.getString("IMAGE") != null) {

			publicationsVO.setImageAssetId(resultset.getString("IMAGE"));
		}
		publicationsVO.setLanguage(resultset.getString("LANG"));

		return publicationsVO;
	}
}
