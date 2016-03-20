package com.uaq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.uaq.command.ChangePasswordCommand;
import com.uaq.logger.UAQLogger;

@Repository(value = "changePasswordDAO")
public class ChangePasswordDAO {
	
	protected static UAQLogger logger = new UAQLogger(ChangePasswordDAO.class);
	
	public Map<String,String> getByPrimaryKey(String key) throws SQLException{
		Map<String,String> data =new HashMap<String, String>();
		DAOManager daoManager = new DAOManager();
		Connection con = daoManager.getConnection();
		
		String SQL = " SELECT * FROM FORGETPASSWORD_VERIFICATION WHERE ID = ? ";
		
		logger.debug("SQL - "+SQL);
		
		PreparedStatement ps = con.prepareStatement(SQL);
		
		ps.setString( 1, key);
		
		ResultSet resultset = ps.executeQuery();
		
		while (resultset.next()) {
			data.put("USERNAME", resultset.getString("USERNAME"));
			data.put("ISACTIVE", resultset.getString("ISACTIVE"));
			
		}
		ps.close();
		daoManager.closeConnection();
		return data;
	}
	
	public boolean updateISActive(ChangePasswordCommand comand) throws SQLException{
		boolean upadte =false;
		DAOManager daoManager = new DAOManager();
		Connection con = daoManager.getConnection();
		
		String SQL = " UPDATE FORGETPASSWORD_VERIFICATION SET ISACTIVE='2', MODIFIEDDATE= SYSDATE ,MODIFIEDBY = ? WHERE ID=? ";
		
		logger.debug("sql = " + SQL);
		
		PreparedStatement ps = con.prepareStatement(SQL);
		
		ps.setString( 1, comand.getUserName()); 
		ps.setString( 2, comand.getKey()); 
		
		Integer updateCount = ps.executeUpdate();
		
		logger.debug("Rows Updated = " + updateCount);
		
		if(updateCount >0){
			upadte = true;
		}
		daoManager.commit();
		daoManager.closeConnection();
		return upadte;
		
	}

}
