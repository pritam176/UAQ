package com.pkm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.pkm.vo.UserDetailVO;

@Repository
public class UserDetailDao {
	
	
	
	
	
	public long saveUser(UserDetailVO userDetailVO,Connection con) throws ClassNotFoundException, SQLException{
		
		long rowid=0;
		
		String sql = "INSERT INTO  userdetails ( NAME ,  MOBILENO , EMAIL , ADDRESS_ID ) VALUES (?,?,?,?)";

		
		 
		
		java.sql.PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		
		ps.setString(1, userDetailVO.getNAME());
		ps.setString(2, userDetailVO.getMOBILENO());
		//ps.setString(3, userDetailVO.getCOLUMN1());
		ps.setString(3, userDetailVO.getEMAIL());
		ps.setString(4, userDetailVO.getADDRESS_ID());
		
		
		ps.executeUpdate();
		
		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			rowid = generatedKeys.getLong(1);
		} else {
			throw new SQLException("Creating user failed, no ID obtained.");
		}
		
		return rowid;
		
	}

}
