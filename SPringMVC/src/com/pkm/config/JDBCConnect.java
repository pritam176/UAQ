package com.pkm.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.pkm.Controller.HomeController;
import com.pkm.config.PropertiesUtil;

public class JDBCConnect {
	
	protected PKMLogger logger =new PKMLogger(HomeController.class);

	private static final String DB_DRIVER = PropertiesUtil.getProperty("jdbc.driverClassName");
	private static final String DB_CONNECTION = PropertiesUtil.getProperty("jdbc.url");
	private static final String DB_USER = PropertiesUtil.getProperty("jdbc.username");
	private static final String DB_PASSWORD = PropertiesUtil.getProperty("jdbc.password");
	
	private Connection con;

	
	
	public Connection getConnection() throws SQLException {

		if (this.con == null || this.con.isClosed()) {
			try {
				Class.forName(DB_DRIVER);
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
			}

			try {
				con = DriverManager.getConnection(DB_CONNECTION, DB_USER,
						DB_PASSWORD);
				con.setAutoCommit(false);
			} catch (SQLException e) {
				throw e;
			}
		}
		return con;
	}
	
	public void closeConnection() {
		try {
			if (this.con != null && !this.con.isClosed())
				this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void commit() throws SQLException{
		try {
			if (this.con != null && !this.con.isClosed())
				con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public void rollback(){
		try {
			if (this.con != null && !this.con.isClosed())
				con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
