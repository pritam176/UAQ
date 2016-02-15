package com.uaq.flexfliter;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Handles the database operation
 * 
 * @author nsharma
 * 
 */
public class ConnectionManager {

	/**
	 * 
	 * 
	 * @return
	 * @throws VBSystemException
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 */
	public static final Connection getInstance() throws Exception {
		Connection connection = getConnection();
		return connection;
	}

	/**
	 * Opens the DB connection using the DataSource
	 * 
	 * @return
	 * @throws VBSystemException
	 */
	public static Connection getConnection() throws Exception {

		// Obtain our environment naming context
		Context initCtx;
		Connection conn = null;
		initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		Object ud = (Object) envCtx.lookup("csDataSource");
		System.out.println("ud : " + ud);
		System.out.println("ud.getClass() : " + ud.getClass());
		DataSource ds = (DataSource) envCtx.lookup("csDataSource");
		conn = ds.getConnection();

		return conn;

	}

	/**
	 * Closes the DB connection
	 * 
	 * @param con
	 * @throws VBSystemException
	 * @throws SQLException
	 */
	public static void closeConnection(Connection con) throws SQLException {
		if (!con.isClosed()) {
			con.close();
		}
	}
}
