package com.uaq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uaq.common.PropertiesUtil;
import com.uaq.exception.DAOException;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.payment.AutoUpdatePaymentResponse;
import com.uaq.payment.InquiryPaymentResponse;
import com.uaq.payment.MerchantAccount;
import com.uaq.payment.PayWebPaymentRequest;
import com.uaq.payment.PayWebPaymentResponse;
import com.uaq.payment.PaymentServiceCode;
import com.uaq.payment.PaymentStatus;
import com.uaq.util.DateUtil;
import com.uaq.vo.PaymentReportFilterVO;
import com.uaq.vo.PaymentReportVO;
import com.uaq.vo.PurchaseServiceVO;
import com.uaq.vo.PurchaseVO;

import com.uaq.payment.PaymentConstants;

/**
 * This class manipulates data in PURCHASE, PAYMENT_TRANSACTION table.
 * 
 * @author mraheem
 * 
 */

public class RequestDAO {
	
	private static transient UAQLogger logger = new UAQLogger(RequestDAO.class);

	

	/**
	 * This method is used to update the purchase status of the purchase
	 * record.
	 * 
	 * @param purchaseCommand
	 * @return true
	 * @throws SQLException 
	 */

	public String getSubmitWorkFlowHistoryId(String requestId,Connection con) throws SQLException {
		logger.enter("getSubmitWorkFlowHistoryId : requestId = " + requestId);
		PreparedStatement ps = null;
		String id=null;
		String sql = "select min(id) id from WORKFLOW_HISTORY where request_id=?";
		
		ps = con.prepareStatement(sql); 
			ps.setString( 1, requestId);
			
ResultSet resultset = ps.executeQuery();
			
			while (resultset.next()) {
				
				id = resultset.getString(1);
			}

		
		logger.exit("getSubmitWorkFlowHistoryId : requestId = " + requestId);

		return id;
			
	}
}
