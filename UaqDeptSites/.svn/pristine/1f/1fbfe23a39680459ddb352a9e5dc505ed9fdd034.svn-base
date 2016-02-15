package com.uaq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.uaq.vo.JobVO;

/**
 * Mapper class for JobVO
 * 
 * @author ajain
 * 
 */

public class JobMapper implements RowMapper<JobVO> {
	@Override
	public JobVO mapRow(ResultSet resultset, int rowNum) throws SQLException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		JobVO jobVO = new JobVO();

		jobVO.setAssetId(resultset.getString("ID"));

		jobVO.setName(resultset.getString("NAME"));

		jobVO.setJobTitle(resultset.getString("JOB_TITLE"));

		jobVO.setJobDescription(resultset.getString("JOB_DESCRIPTION"));

		jobVO.setJobResponsibility(resultset.getString("JOB_RESPONSIBILITY"));

		jobVO.setPostedDate(sdf.format(resultset.getTimestamp("POSTED_DATE")).toString().split(" ")[0]);

		jobVO.setEndDate(sdf.format(resultset.getTimestamp("END_DATE")));

		jobVO.setDepartmentNameEN(resultset.getString("DEPARTMENTNAME_EN"));

		jobVO.setDepartmentNameAR(resultset.getString("DEPARTMENTNAME_AR"));

		jobVO.setJobReferenceNumber(resultset.getString("JOB_REFERENCE_NUMBER"));

		jobVO.setTeaserText(resultset.getString("TEASER_TEXT"));

		jobVO.setCount(resultset.getLong("cnt")); // total rows count

		jobVO.setRowNum(resultset.getLong("rn")); // row number from total
													// number of rows as stated
													// above

		jobVO.setMailTo(resultset.getString("MAIL_TO"));
		
		return jobVO;
	}
}
