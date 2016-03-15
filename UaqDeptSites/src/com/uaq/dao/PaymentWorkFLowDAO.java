package com.uaq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.PaymentWorkFlowVO;

public class PaymentWorkFLowDAO {

	private static transient UAQLogger logger = new UAQLogger(PaymentWorkFLowDAO.class);

	

	public boolean savePaymentWorkFlow(PaymentWorkFlowVO paymentWorkFlowVO,Connection con) throws SQLException {

		logger.enter("savePaymentWorkFlow");
		boolean result = false;
		String query = "";
		int intResult = 0;
		PreparedStatement pStmt = null;
		
		query = "INSERT INTO PAYMENT_WF_TRANSACTION (REQUEST_ID, REQUEST_NO, SERVICE_ID, STATUS_ID, REQUEST_TYPE, CREATED_DATE,"
				+ " TRANSACTION_ID, TRANSACTION_STATUS, CREATEDBY, DEPARTMENT_CODE,USER_NAME,FLAG,FEE_TYPE_CODE,EDIRAMFEES)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";

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

		

		if (intResult > 0)
			result = true;

		logger.exit("savePaymentWorkFlow result-" + result);

		return result;

	}

	public boolean updatePaymentWorkFLow(PaymentWorkFlowVO paymentWorkFlowVO,Connection con) throws SQLException {

		logger.enter("updatePaymentWorkFLow");
		boolean result = false;
		int intResult = 0;
		PreparedStatement ps = null;
		
		String query = "UPDATE PAYMENT_WF_TRANSACTION SET MODIFIED_DATE = ?,  MODIFIEDBY = ?,STATUS_ID = ? , TRANSACTION_STATUS = ? WHERE REQUEST_ID = ?";

		ps = con.prepareStatement(query);

			java.sql.Timestamp modifiedDate = new java.sql.Timestamp(new Date().getTime());

			ps.setTimestamp(1, modifiedDate);
			ps.setString(2, paymentWorkFlowVO.getModifiedBy());
			ps.setString(3, paymentWorkFlowVO.getStatusId());
			ps.setString(4, "WORK_FLOW_FAILED");

			ps.setString(5, paymentWorkFlowVO.getRequestId());

			intResult = ps.executeUpdate();

		
		if (intResult > 0)
			result = true;

		logger.exit("updatePaymentWorkFLow result-" + result);

		return result;

	}

	public boolean deletePaymentWorkFLow(PaymentWorkFlowVO paymentWorkFlowVO,Connection con) throws SQLException {

		logger.enter("deletePaymentWorkFLow");
		boolean result = false;
		int intResult = 0;
		PreparedStatement ps = null;
		
		String query = "DELETE FROM PAYMENT_WF_TRANSACTION WHERE REQUEST_ID = ?";

		ps = con.prepareStatement(query);

			ps.setString(1, paymentWorkFlowVO.getRequestId());

			intResult = ps.executeUpdate();

		
		if (intResult > 0)
			result = true;

		logger.exit("deletePaymentWorkFLow result -" + result);

		return result;

	}

	public boolean isExistInPaymentWorkFlow(PaymentWorkFlowVO paymentWorkFlowVO,Connection con) throws SQLException {
		boolean isExist = false;

		logger.enter("isExistInPaymentWorkFlow");
		int intResult = 0;
		PreparedStatement ps = null;
		
		String query = "SELECT REQUEST_ID FROM PAYMENT_WF_TRANSACTION WHERE REQUEST_ID = ?";

		ps = con.prepareStatement(query);

			ps.setString(1, paymentWorkFlowVO.getRequestId());

			intResult = ps.executeUpdate();

		

		if (intResult > 0)
			isExist = true;

		logger.exit("isExistInPaymentWorkFlow result -" + isExist);

		return isExist;
	}
	
	public List<PaymentWorkFlowVO> getPaymentWorkFlowVOList(Connection con) throws SQLException{
		String query="select \r\n" + 
				"REQUEST_ID , \r\n" + 
				"	REQUEST_NO , \r\n" + 
				"	SERVICE_ID , \r\n" + 
				"	STATUS_ID , \r\n" + 
				"	REQUEST_TYPE , \r\n" + 
				"	CREATED_DATE , \r\n" + 
				"	MODIFIED_DATE , \r\n" + 
				"	TRANSACTION_ID , \r\n" + 
				"	TRANSACTION_STATUS , \r\n" + 
				"	CREATEDBY , \r\n" + 
				"	MODIFIEDBY , \r\n" + 
				"	DEPARTMENT_CODE , \r\n" + 
				"	USER_NAME , \r\n" + 
				"	FLAG , \r\n" + 
				"	FEE_TYPE_CODE , \r\n" + 
				"	EDIRAMFEES \r\n" + 
				"from payment_wf_transaction\r\n" ;
		
		int intResult = 0;
		PreparedStatement ps = null;
		List<PaymentWorkFlowVO> paymentWorkFlowVOList = new ArrayList<PaymentWorkFlowVO>();
		ps = con.prepareStatement(query);

		ResultSet resultset = ps.executeQuery();
		while (resultset.next()) {
			PaymentWorkFlowVO vo = new PaymentWorkFlowVO();
			vo.setRequestId(resultset.getString("REQUEST_ID"));
			vo.setRequestNo(resultset.getString("REQUEST_NO"));
			vo.setServiceId(resultset.getString("SERVICE_ID"));
			vo.setStatusId(resultset.getString("STATUS_ID"));
			vo.setRequestType(resultset.getString("REQUEST_TYPE"));
			vo.setTransactionId(resultset.getString("TRANSACTION_ID"));
			vo.setTransactionStatus(resultset.getString("TRANSACTION_STATUS"));
			vo.setCreatedBy(resultset.getString("CREATEDBY"));
			vo.setModifiedBy(resultset.getString("MODIFIEDBY"));
			vo.setDepartmentCode(resultset.getString("DEPARTMENT_CODE"));
			vo.setUserName(resultset.getString("USER_NAME"));
			vo.setEdiramFees(resultset.getString("EDIRAMFEES"));
			
			paymentWorkFlowVOList.add(vo);
		}
		return paymentWorkFlowVOList;
	}

}
