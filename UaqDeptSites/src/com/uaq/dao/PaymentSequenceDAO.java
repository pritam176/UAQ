package com.uaq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;

public class PaymentSequenceDAO {

	private static transient UAQLogger logger = new UAQLogger(PaymentSequenceDAO.class);

	
	public String getSequenceByDepartment(String sequenceName,Connection con) throws SQLException {

		logger.enter("getSequenceByDepartment");

		String query = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
		logger.debug("SQL - " + sequenceName);

		String nextValue = "";
		PreparedStatement ps = null;
			ps = con.prepareStatement(query);
			ResultSet rs  = ps.executeQuery();
			while (rs.next()) {
				nextValue =rs.getInt(1)+"";
			
			}

		
		logger.exit("getSequenceByDepartment  -" + nextValue);

		return nextValue;
	}

}
