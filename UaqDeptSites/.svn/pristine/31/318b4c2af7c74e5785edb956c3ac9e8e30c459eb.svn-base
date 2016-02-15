package com.uaq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.PaymentWorkFlowVO;

public class PaymentWorkFLowDAO {

	private static transient UAQLogger logger = new UAQLogger(PaymentWorkFLowDAO.class);

	private static final String DB_DRIVER = PropertiesUtil.getProperty("soa.jdbc.driverClassName");
	private static final String DB_CONNECTION = PropertiesUtil.getProperty("soa.jdbc.url");
	private static final String DB_USER = PropertiesUtil.getProperty("soa.jdbc.username");
	private static final String DB_PASSWORD = PropertiesUtil.getProperty("soa.jdbc.password");

	private Connection con;

	// used to establish connection with SOA database
	private Connection getConnection() throws ClassNotFoundException, SQLException {

		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return con;
	}

	public boolean savePaymentWorkFlow(PaymentWorkFlowVO paymentWorkFlowVO) {

		logger.enter("savePaymentWorkFlow");
		boolean result = false;
		String query = "";
		int intResult = 0;
		PreparedStatement pStmt = null;
		Connection con = null;

		query = "INSERT INTO PAYMENT_WF_TRANSACTION (REQUEST_ID, REQUEST_NO, SERVICE_ID, STATUS_ID, REQUEST_TYPE, CREATED_DATE,"
				+ " TRANSACTION_ID, TRANSACTION_STATUS, CREATEDBY, DEPARTMENT_CODE,USER_NAME,FLAG,FEE_TYPE_CODE,EDIRAMFEES)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";

		try {

			con = getConnection();
			pStmt = con.prepareStatement(query);

			pStmt.setString(1, paymentWorkFlowVO.getRequestId());
			pStmt.setString(2, paymentWorkFlowVO.getRequestNo());
			pStmt.setString(3, paymentWorkFlowVO.getServiceId());
			pStmt.setString(4, paymentWorkFlowVO.getStatusId());
			pStmt.setString(5, paymentWorkFlowVO.getRequestType());
			java.sql.Timestamp creatdDate = new java.sql.Timestamp(new Date().getTime());
			logger.debug("transactionDate = " + creatdDate);
			pStmt.setTimestamp(6, creatdDate);
			// pStmt.setTimestamp(7, modifiedDate);
			pStmt.setString(7, paymentWorkFlowVO.getTransactionId());
			pStmt.setString(8, paymentWorkFlowVO.getTransactionStatus());
			pStmt.setString(9, paymentWorkFlowVO.getCreatedBy());
			// pStmt.setString(11, paymentWorkFlowVO.getModifiedBy());
			pStmt.setString(10, paymentWorkFlowVO.getDepartmentCode());
			pStmt.setString(11, paymentWorkFlowVO.getUserName());
			pStmt.setString(12, "Y");
			pStmt.setString(13, paymentWorkFlowVO.getFeeId());
			pStmt.setString(14, paymentWorkFlowVO.getEdiramFees());

			intResult = pStmt.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error(e.getMessage());
			}
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} finally {

			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}

		if (intResult > 0)
			result = true;

		logger.exit("savePaymentWorkFlow result-" + result);

		return result;

	}

	public boolean updatePaymentWorkFLow(PaymentWorkFlowVO paymentWorkFlowVO) {

		logger.enter("updatePaymentWorkFLow");
		boolean result = false;
		int intResult = 0;
		PreparedStatement ps = null;
		Connection con = null;

		String query = "UPDATE PAYMENT_WF_TRANSACTION SET MODIFIED_DATE = ?,  MODIFIEDBY = ?,STATUS_ID = ? , TRANSACTION_STATUS = ? WHERE REQUEST_ID = ?";

		try {

			con = getConnection();
			ps = con.prepareStatement(query);

			java.sql.Timestamp modifiedDate = new java.sql.Timestamp(new Date().getTime());

			ps.setTimestamp(1, modifiedDate);
			ps.setString(2, paymentWorkFlowVO.getModifiedBy());
			ps.setString(3, paymentWorkFlowVO.getStatusId());
			ps.setString(4, "WORK_FLOW_FAILED");

			ps.setString(5, paymentWorkFlowVO.getRequestId());

			intResult = ps.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error(e.getMessage());
			}
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}

		if (intResult > 0)
			result = true;

		logger.exit("updatePaymentWorkFLow result-" + result);

		return result;

	}

	public boolean deletePaymentWorkFLow(PaymentWorkFlowVO paymentWorkFlowVO) {

		logger.enter("deletePaymentWorkFLow");
		boolean result = false;
		int intResult = 0;
		PreparedStatement ps = null;
		Connection con = null;

		String query = "DELETE FROM PAYMENT_WF_TRANSACTION WHERE REQUEST_ID = ?";

		try {

			con = getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, paymentWorkFlowVO.getRequestId());

			intResult = ps.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error(e.getMessage());
			}
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}

		if (intResult > 0)
			result = true;

		logger.exit("deletePaymentWorkFLow result -" + result);

		return result;

	}

	public boolean isExistInPaymentWorkFlow(PaymentWorkFlowVO paymentWorkFlowVO) {
		boolean isExist = false;

		logger.enter("isExistInPaymentWorkFlow");
		int intResult = 0;
		PreparedStatement ps = null;
		Connection con = null;

		String query = "SELECT REQUEST_ID FROM PAYMENT_WF_TRANSACTION WHERE REQUEST_ID = ?";

		try {

			con = getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, paymentWorkFlowVO.getRequestId());

			intResult = ps.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error(e.getMessage());
			}
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}

		if (intResult > 0)
			isExist = true;

		logger.exit("isExistInPaymentWorkFlow result -" + isExist);

		return isExist;
	}

}
