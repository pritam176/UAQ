package com.pkm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.pkm.command.AddressDetail;

@Repository
public class AddressDao {

	public long saveAddress(AddressDetail addressDetail, Connection con) throws SQLException, ClassNotFoundException {

		long rowid=0;
		
		String sql = "INSERT INTO address \r\n" + "(ADRESS1,ADRESS2,STREETNO,LANDMARK,POSTBOX,LATITUDE,LONGITUDE)" +

		"VALUES" + "(?,?,?,?,?,?,?)";

		//System.out.println(sql);

		java.sql.PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, addressDetail.getAdress1());
		ps.setString(2, addressDetail.getAdress2());
		ps.setString(3, addressDetail.getStreetNo());
		ps.setString(4, addressDetail.getLandMark());
		ps.setString(5, addressDetail.getPostbox());
		ps.setString(6, addressDetail.getLatitude());
		ps.setString(7, addressDetail.getLongitude());

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
