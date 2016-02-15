package com.uaq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.uaq.command.Logincommand;

/**
 * Maps SQL resultset to login command object.
 * 
 * @author mraheem
 * 
 */
public class FuneralLoginMapper implements RowMapper<Logincommand> {

	@Override
	public Logincommand mapRow(ResultSet resultset, int rowNum)
			throws SQLException {
		
		Logincommand command = new Logincommand();
		command.setLoginUsername(resultset.getString("USER_NAME"));		
		command.setLoginPassword(resultset.getString("PASSWORD"));			
		
		return command;
	}

}
