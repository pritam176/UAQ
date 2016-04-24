package com.pkm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.pkm.config.PKMLogger;
import com.pkm.vo.IssuDetailVO;

@Repository
public class IssueDetailDao {
	
	protected PKMLogger logger =new PKMLogger(IssueDetailDao.class);
	
	public long saveIssue(IssuDetailVO issuDetailVO,Connection con) throws SQLException, ClassNotFoundException{
		
		logger.enter("saveIssue");
		
		long rowid=0;
		String sql = "INSERT INTO issue_detail (PROUDUCTTYPE,PROUDUCTSUBTYPE,DESCRIPTION,MODELNO,"+ 
				"USER_ID,ADRESS_ID,IMAGE_ID,issuestatus )"+ 
				"VALUES(?,?,?,?,?,?,?,? )";
		
		 
		
		java.sql.PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		
		
		ps.setString(1, issuDetailVO.getPROUDUCTTYPE());
		ps.setString(2, issuDetailVO.getPROUDUCTSUBTYPE());
		ps.setString(3, issuDetailVO.getDESCRIPTION());
		ps.setString(4, issuDetailVO.getMODELNO());
		ps.setString(5, issuDetailVO.getUSER_ID());
		ps.setString(6, issuDetailVO.getADRESS_ID());
		ps.setString(7, issuDetailVO.getIMAGE_ID());
		ps.setString(8, "NEW");
		
		
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
