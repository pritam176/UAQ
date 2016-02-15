package com.uaq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.uaq.vo.OptionResultVO;

/**
 * Maps SQL resultset to OptionResultVO object.
 * 
 * @author mraheem
 * 
 */
public class OptionResultMapper implements RowMapper<OptionResultVO> {

	@Override
	public OptionResultVO mapRow(ResultSet resultset, int rowNum) throws SQLException {

		OptionResultVO optionResultVO = new OptionResultVO();

		optionResultVO.setFormId(resultset.getString("form_id"));
		optionResultVO.setFormFieldId(resultset.getString("form_field_id"));
		optionResultVO.setOptionId(resultset.getString("answer"));
		String perc = resultset.getString("percentage").replace("%", "");
		Double percentage = Double.valueOf(perc);
		optionResultVO.setPercentage(String.format("%.2f", percentage) + "%");

		return optionResultVO;
	}
}
