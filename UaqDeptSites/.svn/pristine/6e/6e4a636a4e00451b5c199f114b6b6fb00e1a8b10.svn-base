package com.uaq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.uaq.util.DateUtil;
import com.uaq.vo.EventsVO;

/**
 * Maps SQL resultset to InquiryPaymentResponse object.
 * 
 * @author mraheem
 * 
 */
public class EventsMapper implements RowMapper<EventsVO> {

	@Override
	public EventsVO mapRow(ResultSet resultset, int rowNum) throws SQLException {


		EventsVO eventVO = new EventsVO();

		// eventVO.setDate(sdf.format(resultset.getTimestamp("_date")));

		eventVO.setAssetId(resultset.getString("ID"));
		eventVO.setName(resultset.getString("NAME"));
		eventVO.setTitle(resultset.getString("TITLE"));
		eventVO.setTeaserTitle(resultset.getString("TEASERTITLE"));
		eventVO.setBody(resultset.getString("BODY"));
		eventVO.setTeaserText(resultset.getString("TEASERTEXT"));
		eventVO.setImage(resultset.getString("IMAGE"));
		eventVO.setLanguage(resultset.getString("LANG"));
		eventVO.setEventName(resultset.getString("EVENTNAME"));
		eventVO.setAddressLine1(resultset.getString("ADDRESSLINE1"));
		eventVO.setAddressLine2(resultset.getString("ADDRESSLINE2"));
		eventVO.setAddressLine3(resultset.getString("ADDRESSLINE3"));
		eventVO.setLatitude(resultset.getString("LATITUDE"));
		eventVO.setLongitude(resultset.getString("LONGITUDE"));
		eventVO.setWebsite(resultset.getString("WEBSITE"));
		if (null != resultset.getTimestamp("OPENINGDATE")) {
			eventVO.setOpeningDate(DateUtil.getUAQFormattedDate(resultset.getTimestamp("OPENINGDATE"), "MMMM dd, yyyy", resultset.getString("LANG")));
			eventVO.setSiteOpeningDate(DateUtil.getUAQFormattedDate(resultset.getTimestamp("OPENINGDATE"), "MMMM dd", resultset.getString("LANG")));
		}
		if (null != resultset.getTimestamp("ENDINGDATE")) {
			eventVO.setEndingDate(DateUtil.getUAQFormattedDate(resultset.getTimestamp("ENDINGDATE"), "MMMM dd, yyyy", resultset.getString("LANG")));
			eventVO.setSiteEndingDate(DateUtil.getUAQFormattedDate(resultset.getTimestamp("ENDINGDATE"), "MMMM dd", resultset.getString("LANG")));
		}
		eventVO.setProcedures(resultset.getString("PROCEDURES"));

		eventVO.setCount(resultset.getLong("cnt")); // total rows count
		eventVO.setRowNum(resultset.getLong("rn")); // row number from total
													// number of rows as stated
													// above

		return eventVO;
	}
}
