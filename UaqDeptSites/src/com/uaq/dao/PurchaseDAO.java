package com.uaq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

public class PurchaseDAO {
	
	private static transient UAQLogger logger = new UAQLogger(PurchaseDAO.class);

	private static final String PURCHASE_STATUS_APPROVED = "Approved";
	
	private static final String DB_DRIVER = PropertiesUtil.getProperty("soa.jdbc.driverClassName");
	private static final String DB_CONNECTION = PropertiesUtil.getProperty("soa.jdbc.url");
	private static final String DB_USER = PropertiesUtil.getProperty("soa.jdbc.username");
	private static final String DB_PASSWORD = PropertiesUtil.getProperty("soa.jdbc.password");
	
	private static final String DB_CONNECTION_ERP = PropertiesUtil.getProperty("erp.jdbc.url");
	private static final String DB_USER_ERP = PropertiesUtil.getProperty("erp.jdbc.username");
	private static final String DB_PASSWORD_ERP = PropertiesUtil.getProperty("erp.jdbc.password");
	
	
	private Connection con; 
	
	//used to establish connection with SOA database 
	private Connection getConnection() throws ClassNotFoundException, SQLException 
	{ 
				 
		try { 
			Class.forName(DB_DRIVER); 
		} catch (ClassNotFoundException e) { 
			logger.error(e.getMessage()); 
		}
 
		try { 
			con = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD); 
		} catch (SQLException e) { 
			logger.error(e.getMessage()); 
		}	
		
		return con;
	}
	
	//used to establish connection with ERP database 
		private Connection getConnectionERP() throws ClassNotFoundException, SQLException 
		{ 
					 
			try { 
				Class.forName(DB_DRIVER); 
			} catch (ClassNotFoundException e) { 
				logger.error(e.getMessage()); 
			}
	 
			try { 
				con = DriverManager.getConnection(DB_CONNECTION_ERP, DB_USER_ERP,DB_PASSWORD_ERP); 
			} catch (SQLException e) { 
				logger.error(e.getMessage()); 
			}	
			
			return con;
		}
	
	

	/**
	 * This method is used to update the purchase status of the purchase
	 * record.
	 * 
	 * @param purchaseCommand
	 * @return true
	 * @throws SQLException 
	 */

	public boolean updatePurchaseStatus(String purchaseId, String status) throws SQLException {
		
		boolean result = false;
		PreparedStatement ps = null;
		Connection con = null;
		
		String sql = "update PURCHASE set PURCHASE_STATUS=?, LAST_MODIFIED_DATE=SYSDATE where PURCHASE_ID=?";
		
		try {
			
			con = getConnection();
			ps = con.prepareStatement(sql); 
			ps.setString( 1, status);
			ps.setString( 2, purchaseId);
			Integer updateCount = ps.executeUpdate();
			
			System.out.println("Rows Updated = " + updateCount);
			
			result = true;
			
		} catch (SQLException e) {

			logger.error(e.getMessage());
			con.rollback();
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} 
		finally {

			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return result;
	}

	/**
	 * This method is used to block a purchase to mark it as payment in
	 * progress.
	 * 
	 * @param purchaseId
	 * @return true
	 * @throws SQLException 
	 */
	public boolean purchasePaymentInProgress(String purchaseId, boolean status) throws SQLException {

		logger.enter("purchasePaymentInProgress");

		boolean result = false;
		PreparedStatement ps = null;
		Connection con = null;
		
		String sql = "update PURCHASE set payment_in_progress=?, LAST_MODIFIED_DATE=SYSDATE where PURCHASE_ID=?";
		
		try {
			
			con = getConnection();			
			ps = con.prepareStatement(sql); 
			ps.setBoolean( 1, status);
			ps.setString( 2, purchaseId);
			
			Integer updateCount = ps.executeUpdate();
			
			System.out.println("Rows Updated = " + updateCount);
			
			result = true;
			
		} catch (SQLException e) {

			logger.error(e.getMessage());
			con.rollback();
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} 
		finally {

			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}

		logger.exit("purchasePaymentInProgress");

		return result;
	}

	/**
	 * This method is used to block a purchase to mark the txn as auto
	 * updated or not progress.
	 * 
	 * @param purchaseId
	 * @return true
	 * @throws SQLException 
	 */
	public boolean transactionAutoUpdated(String transactionId, boolean status) throws SQLException {

		logger.enter("transactionAutoUpdated");

		boolean result = false;
		PreparedStatement ps = null;
		Connection con = null;
		
		String sql = "update PAYMENT_TRANSACTION set auto_updated=? where TRANSACTION_ID=?";
		
		try {
			
			con = getConnection();			
			ps = con.prepareStatement(sql);
			
			ps.setBoolean( 1, status);
			ps.setString( 2, transactionId);
			
			Integer updateCount = ps.executeUpdate();
			
			System.out.println("Rows Updated = " + updateCount);
			
			result = true;
		} catch (SQLException e) {

			logger.error(e.getMessage());
			con.rollback();
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} 
		finally {

			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}

		logger.exit("transactionAutoUpdated");

		return result;
	}
	
	/**
	 * This method is used to get purchase id against given transaction id
	 * 
	 * @param transactionId
	 * @return purchaseId
	 * @throws SQLException 
	 */
	public String getPurchaseIdForTransactionId(String transactionId) throws UAQException, SQLException {

		logger.enter("getPurchaseIdForTransactionId : transactionId = " + transactionId);
		
		String purchaseId = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		String sql = "select reference_id from PAYMENT_TRANSACTION where transaction_id = ?";
		
		try {
			
			con = getConnection();
			
			logger.debug("sql query = " + sql);
			
			ps = con.prepareStatement(sql); 
			ps.setString( 1, transactionId);
			
			ResultSet resultset = ps.executeQuery();
			
			while (resultset.next()) {
				
				purchaseId = resultset.getString(1);
			}

		} catch (SQLException e) {
			
			logger.error(e.getMessage());
			con.rollback();
			
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} 
		finally {

			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
		
		logger.exit("getPurchaseIdForTransactionId : purchaseId = " + purchaseId);

		return purchaseId;
	}
	
	/**
	 * This method is used to insert the autoupdate transaction request before sending to service provider
	 * 
	 * @param autoUpdatePaymentResponse
	 * @return result
	 * @throws UAQException
	 * @throws SQLException 
	 */

	public boolean saveAutoUpdatePaymentTransaction(AutoUpdatePaymentResponse autoUpdatePaymentResponse){

		logger.enter("saveAutoUpdatePaymentTransaction");

		String query = "";
		int intResult = 0;
		PreparedStatement pStmt = null;
		Connection con = null;
		
		query = "INSERT INTO PAYMENT_TRANSACTION (transaction_id, action, status, status_message, confirmation_id, " +
				"transaction_amount, transaction_date, reference_id, autoupdated_payweb_txn_status, autoupdated_payweb_status_msg, " + 
				"edirham_fees, collection_centre_fees, payment_method_type, other) " + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			
			con = getConnection();
			pStmt = con.prepareStatement(query); 
			
			pStmt.setString( 1, autoUpdatePaymentResponse.getTransactionId());
			pStmt.setString( 2, "13"); // autoupdate
			pStmt.setString( 3, autoUpdatePaymentResponse.getStatus());
			pStmt.setString( 4, autoUpdatePaymentResponse.getStatusMessage());
			pStmt.setString( 5, autoUpdatePaymentResponse.getRetrievalRefNumber());
			pStmt.setString( 6, autoUpdatePaymentResponse.getTransactionAmount());
			
			java.sql.Timestamp transactionDate = new java.sql.Timestamp( DateUtil.getSqlDateFromStringDate(autoUpdatePaymentResponse.getTransactionResponseDate()).getTime());
			logger.debug("transactionDate = " + transactionDate);
			
			pStmt.setTimestamp( 7, transactionDate);
			pStmt.setString( 8, autoUpdatePaymentResponse.getReferenceId());
			pStmt.setString( 9, autoUpdatePaymentResponse.getOriginalTransactionStatus());
			pStmt.setString( 10, autoUpdatePaymentResponse.getOriginalTransactionStatusMessage());
			pStmt.setString( 11, autoUpdatePaymentResponse.geteDirhamFees());
			pStmt.setString( 12, autoUpdatePaymentResponse.getCollectionCentreFees());
			pStmt.setString( 13, autoUpdatePaymentResponse.getPaymentMethodType());
			pStmt.setString( 14, autoUpdatePaymentResponse.getPaywebTransactionId());
			
			intResult = pStmt.executeUpdate();	
			
			if(intResult > 0 && autoUpdatePaymentResponse.getService() != null){
				batchUpdateServices(autoUpdatePaymentResponse.getService(), autoUpdatePaymentResponse.getTransactionId());
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error(e.getMessage());
			}
		} catch (ParseException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} catch (UAQException e) {
			logger.error(e.getMessage());
		} 
		finally {

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

		logger.exit("saveAutoUpdatePaymentTransaction");

		if (intResult > 0)
			return true;
		else
			return false;
	}

	/**
	 * This method is used to update the payment transaction table for autoupdate transaction response received from service provider
	 * @param autoUpdatePaymentResponse
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */
	
	public boolean updatePaymentTransaction(AutoUpdatePaymentResponse autoUpdatePaymentResponse) throws UAQException, SQLException {

		logger.enter("updatePaymentTransaction");

		String query = "";
		int intResult = 0;
		PreparedStatement pStmt = null;
		Connection con = null;
		
			query = "update PAYMENT_TRANSACTION set autoupdated_payweb_txn_status=?, autoupdated_payweb_status_msg=?, confirmation_id=?, transaction_amount=?, " +
					"edirham_fees=?, collection_centre_fees=? where TRANSACTION_ID=?";

			try{
				
				con = getConnection();
				pStmt = con.prepareStatement(query); 
	
				pStmt.setString( 1, autoUpdatePaymentResponse.getOriginalTransactionStatus());
				pStmt.setString( 2, autoUpdatePaymentResponse.getOriginalTransactionStatusMessage());
				pStmt.setString( 3, autoUpdatePaymentResponse.getRetrievalRefNumber());
				pStmt.setString( 4, autoUpdatePaymentResponse.getTransactionAmount());
				pStmt.setString( 5, autoUpdatePaymentResponse.geteDirhamFees());
				pStmt.setString( 6, autoUpdatePaymentResponse.getCollectionCentreFees());
				pStmt.setString( 7, autoUpdatePaymentResponse.getPaywebTransactionId());	
				
				intResult = pStmt.executeUpdate();			
			
			} catch (SQLException e) {
				logger.error(e.getMessage());
				con.rollback();
			} catch (ClassNotFoundException e) {				
				logger.error(e.getMessage());
			} 
			finally {

				if (pStmt != null) {
					pStmt.close();
				}
				if (con != null) {
					con.close();
				}
			}
			logger.exit("updatePaymentTransaction");
	
			if (intResult > 0)
				return true;
			else
				return false;
	}

	/**
	 * Insert payweb payment request into response table
	 * 
	 * @param payWebPaymentResponse
	 * @return
	 * @throws UAQException
	 * @throws SQLException 
	 */

	public boolean savePaywebRequestTransaction(PayWebPaymentRequest payWebPaymentRequest) throws UAQException, SQLException {

		logger.enter("savePaywebRequestTransaction");
		String query = "";
		int intResult = 0;
		Connection con = null;
		
		PreparedStatement pStmt = null;
		
		query = "INSERT INTO PAYMENT_TRANSACTION (" + "transaction_id," + "action, " + "transaction_amount, " + "transaction_date, "
				+ "reference_id) " + "values(?, ?, ?, ?, ?)";

		try {
			
			con = getConnection();
			pStmt = con.prepareStatement(query); 

			pStmt.setString( 1, payWebPaymentRequest.getTransactionId());
			pStmt.setString( 2, "0");
			pStmt.setString( 3, payWebPaymentRequest.getAmount());
			
			java.sql.Timestamp transactionDate = new java.sql.Timestamp( DateUtil.getSqlDateFromStringDate(payWebPaymentRequest.getTransactionRequestDate()).getTime());
			logger.debug("transactionDate = " + transactionDate);
			
			pStmt.setTimestamp( 4, transactionDate );
			pStmt.setString( 5, payWebPaymentRequest.getReferenceID());					

			intResult = pStmt.executeUpdate();
		
		} catch (SQLException e) {
			logger.error(e.getMessage());
			con.rollback();
		} catch (ParseException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} 
		finally {

			if (pStmt != null) {
				pStmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

		logger.exit("savePaywebRequestTransaction");

		if (intResult > 0)
			return true;
		else
			return false;
	}

	/**
	 * update response table for payweb payment response
	 * 
	 * @param payWebPaymentResponse
	 * @return
	 * @throws UAQException
	 * @throws SQLException 
	 */

	public boolean savePaywebPaymentTransaction(PayWebPaymentResponse payWebPaymentResponse) throws UAQException, SQLException {

		logger.enter("savePaywebPaymentTransaction");

		int intResult = 0;
		PreparedStatement ps = null;
		Connection con = null;
		
		String query = "update PAYMENT_TRANSACTION set " + "status=?," + "status_message=?," + "transaction_id=?," + 
							"transaction_date=?, " + "confirmation_id=?, " + "amount=?, " + 
							"collection_centre_fees=?, " + "edirham_fees=?, " + "transaction_amount=?, " + "other=?, " + 
							"payment_method_type=?, " + "reference_id=? " + " where transaction_id=?";
		
		try {
			
			con = getConnection();
			ps = con.prepareStatement(query); 

			ps.setString( 1, payWebPaymentResponse.getStatus());
			ps.setString( 2, payWebPaymentResponse.getStatusMessage());
			ps.setString( 3, payWebPaymentResponse.getTransactionId());
			
			java.sql.Timestamp transactionDate = new java.sql.Timestamp( DateUtil.getSqlDateFromStringDate(payWebPaymentResponse.getTransactionResponseDate()).getTime());
			logger.debug("transactionDate = " + transactionDate);
			
			ps.setTimestamp( 4, transactionDate );
			ps.setString( 5, payWebPaymentResponse.getConfirmationID());
			ps.setString( 6, payWebPaymentResponse.getAmount()); 
			ps.setString( 7, payWebPaymentResponse.getCollectionCentreFees());
			ps.setString( 8, payWebPaymentResponse.geteDirhamFees());
			ps.setString( 9, payWebPaymentResponse.getTransactionAmount()); 
			ps.setString( 10, payWebPaymentResponse.getOtherInfo());
			ps.setString( 11, payWebPaymentResponse.getPaymentMethodType()); 
			ps.setString( 12, payWebPaymentResponse.getReferenceId()); 
			ps.setString( 13, payWebPaymentResponse.getTransactionId()); 

			intResult = ps.executeUpdate();
			if(intResult > 0){
				//batchUpdateServices(payWebPaymentResponse.getPurchaseServices(), payWebPaymentResponse.getTransactionId()); // when multiple services per request
				batchUpdateServices(payWebPaymentResponse.getService(), payWebPaymentResponse.getTransactionId());
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			con.rollback();
		} catch (ParseException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} 
		finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}	

		logger.exit("savePaywebPaymentTransaction");

		if (intResult > 0)
			return true;
		else
			return false;

	}
	
	/**
	 * bulk insert of services for savePaymentCharges
	 * Use this method when multiple services per request
	 * @param purchaseServices
	 * @param transactionId
	 * @throws DAOException
	 * @throws SQLException 
	 */
	/*private void batchUpdateServices(final List<PurchaseServiceVO> purchaseServices, final String transactionId) throws DAOException,
			SQLException {

		PreparedStatement ps = null;
		Connection con = null;

		String sql = "INSERT INTO PAYMENT_TRANSACTION_CHARGES "
				+ "(transaction_id, eservice_main_code_sub_code, price, quantity, owner_fees, "
				+ "amount_without_fees, amount_with_fees) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			
			con = getConnection();
			ps = con.prepareStatement(sql);			
			
			for (PurchaseServiceVO purchaseServiceVo : purchaseServices) {
				ps.setString(1, transactionId);
				ps.setString(2, purchaseServiceVo.getServiceCode());
				ps.setString(3, purchaseServiceVo.getPrice());
				ps.setString(4, purchaseServiceVo.getQuantity());
				ps.setString(5, purchaseServiceVo.getOwnerFees());
				ps.setString(6, purchaseServiceVo.getAmountWithoutFees());
				ps.setString(7, purchaseServiceVo.getAmountWithFees());				
				
				ps.addBatch();
			}
			ps.executeBatch();
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			con.rollback();
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}*/
	
	/**
	 * Single service per request
	 * @param purchaseService
	 * @param transactionId
	 * @throws DAOException
	 * @throws SQLException
	 */
	private void batchUpdateServices(final PurchaseServiceVO purchaseService, final String transactionId) throws DAOException,
	SQLException {

		logger.enter("batchUpdateServices");
		
		PreparedStatement ps = null;
		Connection con = null;
		
		int result = 0;
		
		if(purchaseService != null && transactionId != null){
			
			String sql = "INSERT INTO PAYMENT_TRANSACTION_CHARGES "
					+ "(transaction_id, eservice_main_code_sub_code, price, quantity, owner_fees, "
					+ "amount_without_fees, amount_with_fees) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			logger.debug("sql query = " + sql);
			
			try {
				
				con = getConnection();
				ps = con.prepareStatement(sql);					
				
				ps.setString(1, transactionId);
				ps.setString(2, purchaseService.getServiceCode());
				ps.setString(3, purchaseService.getPrice());
				ps.setString(4, purchaseService.getQuantity());
				ps.setString(5, purchaseService.getOwnerFees());
				ps.setString(6, purchaseService.getAmountWithoutFees());
				ps.setString(7, purchaseService.getAmountWithFees());				
					
				result = ps.executeUpdate();				
				
			} catch (SQLException e) {
				logger.error(e.getMessage());
				con.rollback();
			} catch (ClassNotFoundException e) {			
				logger.error(e.getMessage());
			} finally {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			}
		}
		
		if(result > 0){
			logger.debug("result = " + "successfully inserted");
		} else {
			logger.debug("result = " + "insert failed");
		}
		
		logger.exit("batchUpdateServices");
	}

	/**
	 * This method is used to get purchase record with a given purchaseId
	 * @throws SQLException 
	 * 
	 */
	
	public PurchaseVO execute(String purchaseId) throws UAQException, SQLException {
		
		logger.enter("Inside execute");		
		
		PurchaseVO purchaseCommandResult =  null;		
		PreparedStatement ps = null;
		Connection con = null;
		
		String query = "select * from Purchase p where p.purchase_id=?";
		
		try {
			
			con = getConnection();
			ps = con.prepareStatement(query); 
	
			ps.setString( 1, purchaseId);
			
			ResultSet resultset = ps.executeQuery();				
			
			while (resultset.next()) {
				 // its unique result
				purchaseCommandResult = new PurchaseVO();
				purchaseCommandResult.setPurchaseId(resultset.getString("PURCHASE_ID"));		
				purchaseCommandResult.setPurchaseStatus(resultset.getString("PURCHASE_STATUS"));		
				purchaseCommandResult.setPaymentInProgress(resultset.getBoolean("PAYMENT_IN_PROGRESS"));
				purchaseCommandResult.setServiceId(resultset.getString("service_id"));
				purchaseCommandResult.setDepartmentId(resultset.getString("department_id"));
				purchaseCommandResult.setCustomerId(resultset.getString("customer_id"));
				purchaseCommandResult.setCustomerName(resultset.getString("customer_name"));
				purchaseCommandResult.setFeeId(resultset.getString("fee_id"));
				// CREATED_DATE for PURCHASE table
				purchaseCommandResult.setCreatedDate(resultset.getDate("CREATED_DATE"));
				// LAST_UPDATED_DATE for PURCHASE table
				purchaseCommandResult.setLastModifiedDate(resultset.getDate("LAST_MODIFIED_DATE"));
 
			}			
		
		} catch (SQLException e) {
			logger.error(e.getMessage());
			con.rollback();
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
		
		logger.exit("exiting execute");
		
		return purchaseCommandResult;
	}	
	
	/**
	 * This method is used to get payment details for a given confirmation id 
	 * @param confirmationId
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */
	
	public InquiryPaymentResponse getPaymentDetails(String confirmationId) throws UAQException, SQLException {

		logger.enter("getPaymentDetails : confirmationId = " + confirmationId);

		InquiryPaymentResponse inquiryPaymentResponse = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		String sql = "select * from PAYMENT_TRANSACTION pt "				
				+ "where pt.action = '0' and pt.confirmation_id=?";

		logger.debug("sql query = " + sql);
		
		try {
			
			con = getConnection();
			ps = con.prepareStatement(sql);	

			ps.setString(1, confirmationId);
			
			ResultSet resultset = ps.executeQuery();
			
			List<InquiryPaymentResponse> inquiryObjects = new ArrayList<InquiryPaymentResponse>();
			
			while (resultset.next()) {				
				
				InquiryPaymentResponse ipr = new InquiryPaymentResponse();

				ipr.setReferenceId(resultset.getString("reference_id"));
				ipr.setTransactionId(resultset.getString("transaction_id"));
				ipr.setRetrievalRefNumber(resultset.getString("confirmation_id"));

				String eDirhamFees = resultset.getString("edirham_fees");
				if (eDirhamFees != null)
					ipr.seteDirhamFees(eDirhamFees);

				String collectionCentreFees = resultset.getString("collection_centre_fees");
				if (collectionCentreFees != null)
					ipr.setCollectionCentreFees(collectionCentreFees);

				ipr.setTransactionAmount(resultset.getString("transaction_amount"));
				ipr.setAmount(resultset.getString("amount"));
				ipr.setPaymentMethodType(resultset.getString("payment_method_type"));
				ipr.setTransactionResponseDate(sdf.format(resultset.getTimestamp("transaction_date")));
								
				inquiryObjects.add(ipr); 
			}			
			
			if (inquiryObjects != null && !inquiryObjects.isEmpty()) {
				inquiryPaymentResponse = inquiryObjects.get(0);
				inquiryPaymentResponse.setPurchaseCommandServices(fetchPaymentCharges(inquiryPaymentResponse.getTransactionId()));
				inquiryPaymentResponse.seteServicesCount(inquiryPaymentResponse.getPurchaseCommandServices().size());
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			con.rollback();
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}

		logger.exit("getPaymentDetails");

		return inquiryPaymentResponse;

	}
	
	/**
	 * This method is used to get charges information for a given transaction
	 * @param transaction_id
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */

	private List<PurchaseServiceVO> fetchPaymentCharges(String transaction_id) throws UAQException, SQLException {

		logger.enter("fetchPaymentCharges : transaction_id = " + transaction_id);
		
		PreparedStatement ps = null;
		Connection con = null;
		
		List<PurchaseServiceVO> charges = new ArrayList<PurchaseServiceVO>();
		
		String sql = "select * from PAYMENT_TRANSACTION_CHARGES ptc where ptc.transaction_id = ? ";
		
		logger.debug("sql query = " + sql);
		
		try{
			
			con = getConnection();
			ps = con.prepareStatement(sql);		
			
			ps.setString(1, transaction_id);
			
			ResultSet resultset = ps.executeQuery();
			
			while(resultset.next()){
				
				PurchaseServiceVO paymentCharge = new PurchaseServiceVO();

				paymentCharge.setServiceCode(resultset.getString("eservice_main_code_sub_code"));
				paymentCharge.setPrice(resultset.getString("price"));
				paymentCharge.setQuantity(resultset.getString("quantity"));
				paymentCharge.setOwnerFees(resultset.getString("owner_fees"));
				paymentCharge.setAmountWithFees(resultset.getString("amount_with_fees"));
				paymentCharge.setAmountWithoutFees(resultset.getString("amount_without_fees"));
				
				charges.add(paymentCharge);
			}			

		} catch (SQLException e) {
			logger.error(e.getMessage());
			con.rollback();
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}

		logger.exit("fetchPaymentCharges");

		return charges;
	}
	
	
	/**
	 * This method is used to update the purchase status of the purchase record.
	 * @param purchaseCommand
	 * @return true
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	
	public boolean savePurchaseTransaction(PurchaseVO purchaseCommand) throws DAOException, SQLException{
		
		logger.enter("savePurchaseTransaction");
		
		boolean result = false;			
				
		savePurchase(purchaseCommand);
		batchUpdatePurchaseServices(purchaseCommand.getPurchaseServiceCodes());		
					
		result = true;	
		
		logger.exit("savePurchaseTransaction");
		
		return result;
	}
	
	/**
	 * This method is used to save the purchase
	 * @param purchaseCommand
	 * @throws DAOException
	 * @throws SQLException 
	 */
	
	private void savePurchase(final PurchaseVO purchaseCommand) throws DAOException, SQLException{
		
		logger.enter("savePurchase");
		
		int rowCount = 0;
		
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO PURCHASE (PURCHASE_ID, PURCHASE_STATUS, PAYMENT_IN_PROGRESS, department_id, service_id, fee_id, customer_id, customer_name, CREATED_DATE, LAST_MODIFIED_DATE) " +
				"values (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE,SYSDATE)";		
		try{
			
			con = getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, purchaseCommand.getPurchaseId());
			ps.setString(2, PURCHASE_STATUS_APPROVED);
			ps.setBoolean(3, false);	
			ps.setString(4, purchaseCommand.getDepartmentId());
			ps.setString(5, purchaseCommand.getServiceId());
			ps.setString(6, purchaseCommand.getFeeId());
			ps.setString(7, purchaseCommand.getCustomerId());
			ps.setString(8, purchaseCommand.getCustomerName());
			
			rowCount = ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
			con.rollback();
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} finally {			
			if (con != null) {
				con.close();
			}
		} 
		
		logger.exit("savePurchase : inserted rowCount=" + rowCount);
	}
	
	private void batchUpdatePurchaseServices(final List<PurchaseServiceVO> purchaseServices) throws DAOException, SQLException{
		
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO PURCHASE_SERVICE " +
				"(service_code, units, unit_price, purchase_id) VALUES (?, ?, ?, ?)";
	 		
		try{
			
			con = getConnection();
			ps = con.prepareStatement(sql);
			
			for (PurchaseServiceVO purchaseServiceCommand : purchaseServices) {				
				ps.setString(1, purchaseServiceCommand.getServiceCode());
				ps.setInt(2, new Integer(purchaseServiceCommand.getQuantity()));
				ps.setDouble(3, new Double(purchaseServiceCommand.getPrice()));
				ps.setString(4, purchaseServiceCommand.getPurchaseId() );

				ps.addBatch();
			}
			
			ps.executeBatch();				
				
		} catch (SQLException e) {
			logger.error(e.getMessage());
			con.rollback();
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} 
	}
	
	/**
	 * This method is used to get purchase including its services
	 * 
	 * @param purchaseId
	 * @return
	 * @throws UAQException
	 * @throws SQLException 
	 */
	public PurchaseVO fetchPurchase(String purchaseId) throws UAQException, SQLException {

		logger.enter("fetchPurchase");

		PurchaseVO purchaseCommandResult = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		List<PurchaseVO> purchases = new ArrayList<PurchaseVO>();
		
		String sql = "select * from PURCHASE p "											
						    + " where p.PURCHASE_ID=? "
							+ " order by p.PURCHASE_ID desc";
		
		try{

			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, purchaseId);
			
			ResultSet resultset = ps.executeQuery();
			
			while (resultset.next()) {
				PurchaseVO vo = new PurchaseVO();
				
				vo.setPurchaseId(resultset.getString("PURCHASE_ID"));		
				vo.setPurchaseStatus(resultset.getString("PURCHASE_STATUS"));		
				vo.setPaymentInProgress(resultset.getBoolean("PAYMENT_IN_PROGRESS"));				
				// CREATED_DATE for PURCHASE table
				vo.setCreatedDate(resultset.getDate("CREATED_DATE"));
				// LAST_UPDATED_DATE for PURCHASE table
				vo.setLastModifiedDate(resultset.getDate("LAST_MODIFIED_DATE"));		
				
				purchases.add(vo);
			}
			
			if (!purchases.isEmpty()) {
				PurchaseVO purchaseObject = purchases.get(0);
				purchaseCommandResult = new PurchaseVO();			
				
				if (purchaseObject.getPurchaseId() != null && !purchaseObject.getPurchaseId().isEmpty()) {
					purchaseCommandResult.setPurchaseId(purchaseObject.getPurchaseId());			
					purchaseCommandResult.setPurchaseStatus(purchaseObject.getPurchaseStatus());				
					purchaseCommandResult.setCreatedDate(purchaseObject.getCreatedDate());
					purchaseCommandResult.setLastModifiedDate(purchaseObject.getLastModifiedDate());
					purchaseCommandResult.setPaymentInProgress(purchaseObject.getPaymentInProgress());				
					
					purchaseCommandResult.setPurchaseServiceCodes(fetchPurchaseServiceCodes(purchaseId, con));
				}		
				
			}		
		
		} catch (SQLException e) {

			logger.error(e.getMessage());
			con.rollback();

		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
		logger.exit("fetchPurchase");

		return purchaseCommandResult;
	}

	/**
	 * This method is used to retrieve all photo purchase service codes from the
	 * db
	 * 
	 * @param purchaseId
	 * @return List<PhotoPurchaseServiceCommand>
	 * @throws SQLException 
	 */

	private List<PurchaseServiceVO> fetchPurchaseServiceCodes(String purchaseId, Connection con) throws SQLException {

		logger.enter("PurchaseDAO");
		
		PreparedStatement ps = null;
				
		List<PurchaseServiceVO> serviceObjects = new ArrayList<PurchaseServiceVO>();
		List<PurchaseServiceVO> purchases = new ArrayList<PurchaseServiceVO>();
		
		String sql = "select * from PURCHASE_SERVICE ps where ps.PURCHASE_ID=? order by ps.PURCHASE_ID";
		
		try{
			
			ps = con.prepareStatement(sql);
			ps.setString(1, purchaseId);
			
			ResultSet resultset = ps.executeQuery();
			
			while (resultset.next()) {
				
				PurchaseServiceVO vo = new PurchaseServiceVO();

				vo.setServiceCode(resultset.getString("service_code"));
				vo.setQuantity(resultset.getString("units"));
				vo.setPurchaseId(resultset.getString("purchase_id"));
				vo.setPrice(resultset.getString("unit_price"));
				
				serviceObjects.add(vo);
			}
						
			if (!serviceObjects.isEmpty()) {
				for (PurchaseServiceVO serviceObject : serviceObjects) {
					if (serviceObject.getPurchaseId() != null && !serviceObject.getPurchaseId().isEmpty()) {
						purchases.add(serviceObject);
					}
				}
			}
		
		} catch (SQLException e) {
			logger.error(e.getMessage());
			con.rollback();
		} 
		
		logger.exit("fetchPurchaseServiceCodes()");

		return purchases;
	}
	
	/**
	 * This method is used to get the last failed transaction id, show the status to user
	 * @param purchaseId
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */
	
	public String getLastPaymentTransactionId(String purchaseId) throws UAQException, SQLException {
		
		logger.enter("getLastPaymentTransactionId purchaseId = " + purchaseId);
		
		String transactionId = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		/*String queryString = "Select Pt.Transaction_Id Transaction_Id from Payment_Transaction Pt " +  
				 " Join Purchase Pur on Pt.Reference_Id=Pur.Purchase_id and Pur.Purchase_Status <> 'Completed' " +
				 " where (Pt.Status is null OR Pt.Status <> '0000')  " +
				 " And (Pt.Autoupdated_Payweb_Txn_Status <> '0000' Or Pt.Autoupdated_Payweb_Txn_Status Is Null)  " +
				 " And (Pt.Action ='0' or Pt.Action ='32') " +
				 " And Pt.Reference_Id = ?" ;*/
		
		String queryString = "SELECT * FROM (Select Pt.Transaction_Id Transaction_Id from Payment_Transaction Pt 	" +
				" Join Purchase Pur on Pt.Reference_Id=Pur.Purchase_id and Pur.Purchase_Status <> 'Completed' " +
				" where (Pt.Status is null OR Pt.Status <> '0000')  " +
				" And (Pt.Action ='0') " +
				"  And Pt.Reference_Id = ? " +
				"  order by pt.transaction_date desc)" +
				"  WHERE  ROWNUM <= 1 " ;
		
		
		if(purchaseId != null && !purchaseId.isEmpty()){
			
			try{
				
				con = getConnection();
				ps = con.prepareStatement(queryString);		
				
				ps.setString(1, purchaseId);
				
				ResultSet resultset = ps.executeQuery();
				
				while(resultset.next()){					
					transactionId = resultset.getString("Transaction_Id");
				}
			} catch (SQLException e) {
				logger.error(e.getMessage());
				con.rollback();
			} catch (ClassNotFoundException e) {			
				logger.error(e.getMessage());
			} finally {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			}				 
		}
				
		logger.exit("getLastPaymentTransactionId transactionId = " + transactionId);
		
		return transactionId;
	}
	
	/**
	 * This method is used to get the successful transaction information for given purchase id
	 * @param purchaseId
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */
	
	public InquiryPaymentResponse getSuccessfulPaymentTransaction(String purchaseId){
		
		logger.enter("getSuccessfulPaymentTransaction purchaseId = " + purchaseId);

		PreparedStatement ps = null;
		Connection con = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		InquiryPaymentResponse ipr = new InquiryPaymentResponse();
		
		String queryString = "Select Pt.action, Pt.transaction_Id, Pt.reference_id, Pt.confirmation_id, Pt.edirham_fees, Pt.collection_centre_fees, " +
				 " Pt.transaction_amount, Pt.amount, Pt.payment_method_type, Pt.transaction_date, Pt.other from Payment_Transaction Pt " +  
				 " Join Purchase Pur on Pt.Reference_Id=Pur.Purchase_id and Pur.Purchase_Status = 'Completed' " +
				 " where (Pt.Status='0000')  " +				 
				 " And (Pt.Action ='0' OR Pt.Action ='13') " +
				 " And Pt.Reference_Id = ?" ;		
		
		logger.debug("sql query = " + queryString);
		
		if(purchaseId != null && !purchaseId.isEmpty()){
			
			try{
				
				con = getConnection();
				ps = con.prepareStatement(queryString);		
				
				ps.setString(1, purchaseId);
				
				ResultSet resultset = ps.executeQuery();			
				
				while (resultset.next()) {					
					ipr.setAction(resultset.getString("action"));
					ipr.setReferenceId(resultset.getString("reference_id"));
					ipr.setTransactionId(resultset.getString("transaction_id"));
					ipr.setRetrievalRefNumber(resultset.getString("confirmation_id"));
					ipr.setOtherInfo(resultset.getString("other")); // in case of autoupdate transaction, it contains its parent transaction id which is payweb

					String eDirhamFees = resultset.getString("edirham_fees");
					if (eDirhamFees != null)
						ipr.seteDirhamFees(eDirhamFees);

					String collectionCentreFees = resultset.getString("collection_centre_fees");
					if (collectionCentreFees != null)
						ipr.setCollectionCentreFees(collectionCentreFees);

					ipr.setTransactionAmount(resultset.getString("transaction_amount"));
					ipr.setAmount(resultset.getString("amount"));
					ipr.setPaymentMethodType(resultset.getString("payment_method_type"));
					ipr.setTransactionResponseDate(sdf.format(resultset.getTimestamp("transaction_date")));
				
				}			
				
				if (ipr != null) {					
					ipr.setPurchaseCommandServices(fetchPaymentCharges(ipr.getTransactionId()));
					ipr.seteServicesCount(ipr.getPurchaseCommandServices().size());
				}
				
				/**
				 * Important Note to the Developer :
				 * Currently, ipr contains transaction id regardless of whether its payweb or autoupdate. in case of autoupdate, this transaction id doesn't make
				 * much sense. we can set transaction id in ipr if action is 0(payweb) and otherInfo(contains transaction id of the parent payweb) 
				 * if action is 13(autoupdate).
				 */
				if(ipr.getAction().equals("13")){ // autoupdate
					ipr.setTransactionId(ipr.getOtherInfo()); // using parent payweb transaction id as transaction id
				}
				
			} catch (SQLException e) {
				logger.error(e.getMessage());				
				try {
					con.rollback();
				} catch (SQLException e1) {
					logger.error(e1.getMessage());
				}
				 
			} catch (ClassNotFoundException e) {			
				logger.error(e.getMessage());
			} catch (UAQException e) {
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
		}
				
		logger.exit("getSuccessfulPaymentTransaction transactionId = " + ipr.getTransactionId());
		
		return ipr;
	}

	/**
	 * This method is used to get all broken transactions need to be auto updated through day end batch service.
	 * @param paymentReportFilterCommand
	 * @return
	 * @throws DAOException
	 * @throws SQLException
	 */
	
	public List<PaymentReportVO> getPaymentBrokenTransactions(PaymentReportFilterVO paymentReportFilterCommand) throws DAOException, SQLException {

		logger.enter("getPaymentBrokenTransactions");

		List<PaymentReportVO> transactions = new ArrayList<PaymentReportVO>();
		PreparedStatement ps = null;
		
		String queryString = "select pt.transaction_id, pt.transaction_date, pt.status, pt.autoupdated_payweb_txn_status, pt.action, pt.auto_updated,purchase.purchase_status, purchase.payment_in_progress " +
				" from PAYMENT_TRANSACTION pt " +
				" join purchase on pt.reference_id = purchase.purchase_id and purchase.purchase_status = 'Approved' " +
				" and purchase.payment_in_progress = '1' " +
				" where (pt.action ='0' or pt.action ='32') and (pt.transaction_date >= to_date(?, 'mm/dd/yyyy HH24:mi:ss') and pt.transaction_date <= to_date(?, 'mm/dd/yyyy HH24:mi:ss') ) " +
				" Order By Pt.Transaction_Date Desc";
		
		logger.debug("query : " + queryString);
		
		try{
			
			con = getConnection();
			ps = con.prepareStatement(queryString);
			
			ps.setString(1, paymentReportFilterCommand.getStartDate() + " 00:00:00");
			ps.setString(2, paymentReportFilterCommand.getEndDate() + " 23:59:59");
						
			ResultSet resultset = ps.executeQuery();
			
			while (resultset.next()) {
				PaymentReportVO transaction = new PaymentReportVO();
				transaction.setTransactinId(resultset.getString("transaction_id"));
				transactions.add(transaction);
			}			
		
		} catch (SQLException e) {
			logger.error(e.getMessage());
			con.rollback();
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}

		logger.exit("getPaymentBrokenTransactions");

		return transactions;
	}
	
	/**
	 * This method is used to update the SOA db table. Its not a regular payment table and operation.
	 * @param purchaseId
	 * @param typeOfService
	 * * @param serviceId
	 * @return
	 * @throws SQLException
	 */
	
	public PaymentStatus updateApplicantRequestTableStatus(String purchaseId) throws SQLException {
		
		logger.enter("updateApplicantRequestTableStatus purchaseId = " + purchaseId);

		PreparedStatement ps = null;
		Connection con = null;
		PaymentStatus paymentStatus = null;
		
		String requestId = purchaseId.split("_")[0]; //discarding the  _feeId part
		
		String sql = "update APPLICANT_REQUEST set status_id=? where request_id=?";
		
		logger.debug("sql = " + sql);
		
		try {
			
			con = getConnection();
			ps = con.prepareStatement(sql);
			
			paymentStatus = getPaymentStatus(purchaseId); // method call to get payment status information such as statusId and requestNo
			
			ps.setString( 1, paymentStatus.getStatusId()); 
			ps.setString( 2, requestId); 
			
			Integer updateCount = ps.executeUpdate();
			
			logger.debug("Rows Updated = " + updateCount);
			
			paymentStatus.setRequestId(requestId); // including requestId in payment status information		
			
		} catch (SQLException e) {

			logger.error(e.getMessage());
			con.rollback();
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} 
		finally {

			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
		
		logger.exit("updateApplicantRequestTableStatus paymentStatus = " + paymentStatus.toString());
		
		return paymentStatus;
	}	
	
	/**
	 * This method is used to get payment information such as requestId, statusId, serviceId for a given purchaseId
	 * @param purchaseId
	 * @return PaymentStatus
	 * @throws SQLException
	 */
	
	public PaymentStatus getPaymentInfo(String purchaseId) throws SQLException {
		
		logger.enter("getPaymentInfo purchaseId = " + purchaseId);

		PaymentStatus paymentStatus = null;
		
		String requestId = purchaseId.split("_")[0]; //discarding the  _feeId part					
			
		paymentStatus = getPaymentStatus(purchaseId); // method call to get payment status information such as statusId and requestNo			
		
		paymentStatus.setRequestId(requestId); // including requestId in payment status information			
		
		logger.exit("getPaymentInfo paymentStatus = " + paymentStatus.toString());
		
		return paymentStatus;
	}	
	
	/**
	 * This method is used to update the ERP system. Its not a regular table and operation.
	 * @return
	 * @throws SQLException
	 */
	
	public boolean updateERPtable(Double amount, String deptCode, String transactionId, String feeId, String custId, 
			String custName, String transactionDate, String edirhamRefNo) throws SQLException {
		
		boolean result = false;
		PreparedStatement ps = null;
		Connection con = null;
		
		logger.enter("updateERPtable");
		
		String sql = "INSERT INTO " + PropertiesUtil.getProperty("erp.table.name") + " (CURRENCY_CODE, AMOUNT, DEPARTMENT_CODE, RECEIPT_METHOD_TYPE, " +
				"SERVICE_TRANSACTION_ID, FEES_ID, CUSTOMER_ID, CUSTOMER_NAME, TRANSACTION_DATE, EDIRHAM_REFERENCE_NUMBER) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		logger.debug("sql = " + sql);
		
		try {
			
			con = getConnectionERP(); // ERP specific database connection
			ps = con.prepareStatement(sql); 			
			
			java.sql.Timestamp dateTime = new java.sql.Timestamp( DateUtil.getSqlDateFromStringDate(transactionDate).getTime());
						
			ps.setString(1, "AED");
			ps.setDouble(2, amount);
			ps.setString(3, deptCode);
			ps.setString(4, "CARD");			
			ps.setString(5, transactionId);
			ps.setString(6, feeId);
			ps.setString(7, custId);
			ps.setString(8, custName);
			ps.setTimestamp(9, dateTime);			
			ps.setString(10, edirhamRefNo);
			
			Integer updateCount = ps.executeUpdate();
			
			logger.debug("Rows Updated = " + updateCount);
			
			result = true;
			
		} catch (SQLException e) {

			logger.error(e.getMessage());
			con.rollback();
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		} 
		finally {

			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
		
		logger.exit("updateERPtable result = " + result);
		
		return result;
	}

	/**
	 * This method is used to get payment status information such as statusId, serviceId and RequestNo for a given purchaseId
	 * @param purchaseId
	 * @return
	 */
	
	public PaymentStatus getPaymentStatus(String purchaseId){
		
		logger.enter("getPaymentStatus : purchaseId = " + purchaseId);
		
		PaymentStatus paymentStatus = new PaymentStatus();
		PreparedStatement ps = null;
		Connection con = null;
		
		String requestId = purchaseId.split("_")[0]; //discarding the _feeId part
		
		String sql = "select psl.STATE_ID_AFTER_SUCCESS, p.service_id from PaymentStatus_Lookups psl " +
					 "join PURCHASE p on psl.fee_id = p.fee_id and p.purchase_id = ?";
		
		try {
			
			con = getConnection();
			
			logger.debug("sql query = " + sql);
			
			ps = con.prepareStatement(sql); 
			ps.setString( 1, purchaseId );
			
			ResultSet resultset = ps.executeQuery();
			
			while (resultset.next()) {				
				paymentStatus.setStatusId(resultset.getString(1));
				paymentStatus.setServiceId(resultset.getString(2));
			}		
			
			paymentStatus.setRequestNo(getRequestNoForRequestId(requestId)); // method call to get requestNo for given requestId
				
		} catch (SQLException e) {
			
			logger.error(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error(e.getMessage());
			}
			
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} 
		finally {

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
		
		logger.exit("getPaymentStatus status = " + paymentStatus.getStatusId());

		return paymentStatus;
	}
	
	/**
	 * This method is used to get request no for given request id
	 * @param requestId
	 * @return
	 */
	
	public String getRequestNoForRequestId(String requestId){		
		
		logger.enter("getRequestNoForRequestId : requestId = " + requestId);
				
		PreparedStatement ps = null;
		Connection con = null;
		String requestNo = null;
		
		String sql = "select REQUEST_NO from APPLICANT_REQUEST " +
					 " where REQUEST_ID = ?";
		
		try {
			
			con = getConnection();
			
			logger.debug("sql query = " + sql);
			
			ps = con.prepareStatement(sql); 
			ps.setString( 1, requestId );
			
			ResultSet resultset = ps.executeQuery();
			
			while (resultset.next()) {
				
				requestNo = resultset.getString(1);											
			}		
				
		} catch (SQLException e) {
			
			logger.error(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error(e.getMessage());
			}
			
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} 
		finally {

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
		
		logger.exit("getRequestNoForRequestId requestNo = " + requestNo);
		
		return requestNo;
	}
	
	/**
	 * This method is used to get payment service code for given serviceId and FeeType
	 * @param serviceId
	 * @param feeType
	 * @return PaymentServiceCode
	 * @throws UAQException
	 * @throws SQLException
	 */
	
	public PaymentServiceCode getPaymentServiceCode(String serviceId) throws UAQException, SQLException {

		logger.enter("getPaymentServiceCode : serviceId = " + serviceId);

		PaymentServiceCode paymentServiceCode = new PaymentServiceCode();
		PreparedStatement ps = null;
		Connection con = null;

		StringBuilder sql = new StringBuilder("select * from PAYMENT_SERVICE_CODE ");				
		sql.append(" where service_id=?");
		

		logger.debug("sql query = " + sql.toString());
		
		try {
			
			con = getConnection();
			ps = con.prepareStatement(sql.toString());	

			ps.setString(1, serviceId);
			
			ResultSet resultset = ps.executeQuery();
			
			while (resultset.next()) {			
				
				paymentServiceCode.setServiceCode(resultset.getString("service_code"));
				paymentServiceCode.setServiceId(serviceId);								
				// create merchant account object and put it in the paymentServiceCode obj				
				MerchantAccount merchantAccount = new MerchantAccount();
				merchantAccount.setMerchantID(resultset.getString("merchant_id"));
				merchantAccount.setTerminalID(resultset.getString("terminal_id"));
				merchantAccount.setBankID(resultset.getString("bank_id"));
				merchantAccount.setSecretKey(resultset.getString("secure_key"));
				merchantAccount.setBackToBackURL(resultset.getString("back_to_back_url"));
				merchantAccount.setRedirectURL(resultset.getString("redirect_url"));
				
				paymentServiceCode.setMerchantAccount(merchantAccount);
				
				break; // single record is returned by the query
			}			

		} catch (SQLException e) {
			logger.error(e.getMessage());
			con.rollback();
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}

		logger.exit("getPaymentServiceCode");

		return paymentServiceCode;
	}
	
	/**
	 * This method is used to get the general fee which belong to the super entity eGD
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */
	
	public Double getGeneralFee()  throws UAQException, SQLException{
		
		logger.enter("getGeneralFee");
		
		Double feeAmount = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		String sql = "select amount from ESERVICE_FEE_MATRIX where fee_id = ?";
		
		try {
			
			con = getConnection();
			
			logger.debug("sql query = " + sql);
			
			ps = con.prepareStatement(sql); 
			
			ps.setString(1, PaymentConstants.GENERAL_FEE_ID);
						
			ResultSet resultset = ps.executeQuery();
			
			while (resultset.next()) {
				
				feeAmount = resultset.getDouble(1);
			}

		} catch (SQLException e) {
			
			logger.error(e.getMessage());
			con.rollback();
			
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} 
		finally {

			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
		
		logger.exit("getGeneralFee feeAmount = " + feeAmount);

		return feeAmount;
	}
	
	/**
	 * This method is used to get the amount for a given feeId
	 * @param feeId
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */
	
	public PaymentServiceCode getFeeDetail(String feeId)  throws UAQException, SQLException{
		
		logger.enter("getFeeDetail");
				
		PreparedStatement ps = null;
		Connection con = null;		
		
		PaymentServiceCode paymentServiceCode = new PaymentServiceCode();
		
		String sql = "select DEPARTMENT_CODE, service_id, amount from ESERVICE_FEE_MATRIX where fee_id = ?";
		
		try {
			
			con = getConnection();
			
			logger.debug("sql query = " + sql);
			
			ps = con.prepareStatement(sql); 
			
			ps.setString(1, feeId);
						
			ResultSet resultset = ps.executeQuery();
			
			while (resultset.next()) {
				
				paymentServiceCode.setServiceFee(resultset.getDouble("amount"));
				paymentServiceCode.setServiceId(resultset.getString("service_id"));
				paymentServiceCode.setDepartmentId(resultset.getString("DEPARTMENT_CODE"));
			}

		} catch (SQLException e) {
			
			logger.error(e.getMessage());
			con.rollback();
			
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} 
		finally {

			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
		
		logger.exit("getFeeDetail");

		return paymentServiceCode;
	}
}
