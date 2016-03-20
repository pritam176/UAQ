package com.uaq.pos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.uaq.pos.exception.DAOException;
import com.uaq.pos.exception.UAQException;
import com.uaq.pos.pojo.PaymentReportFilterVO;
import com.uaq.pos.pojo.PaymentRequestECR;
import com.uaq.pos.pojo.PaymentResponseECR;
import com.uaq.pos.pojo.PaymentStatus;
import com.uaq.pos.pojo.PaymentVO;
import com.uaq.pos.util.ConfUtils;
import com.uaq.pos.util.DateUtil;
import com.uaq.pos.util.UAQLogger;

/**
 * This class manipulates data in PURCHASE, PAYMENT_TRANSACTION table.
 * 
 * @author mraheem
 * 
 */
public class PaymentDAO {

	private static transient UAQLogger logger = new UAQLogger(PaymentDAO.class);

	private static final String PURCHASE_STATUS_APPROVED = "Approved";
	
	private static final String DB_DRIVER = ConfUtils.getValue("soa.jdbc.driverClassName");
	private static final String DB_CONNECTION = ConfUtils.getValue("soa.jdbc.url");
	private static final String DB_USER = ConfUtils.getValue("soa.jdbc.username");
	private static final String DB_PASSWORD = ConfUtils.getValue("soa.jdbc.password");
	
	private static final String DB_CONNECTION_ERP = ConfUtils.getValue("erp.jdbc.url");
	private static final String DB_USER_ERP = ConfUtils.getValue("erp.jdbc.username");
	private static final String DB_PASSWORD_ERP = ConfUtils.getValue("erp.jdbc.password");
	
	
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
		
        /*javax.naming.Context initialContext;

        try {
            initialContext = new javax.naming.InitialContext();
            javax.sql.DataSource dataSource = 
                        (javax.sql.DataSource)initialContext.lookup("jdbc/LocalUAQDB");
            con = dataSource.getConnection();            
        } catch (Exception e) {
            e.printStackTrace();
        }*/
		
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
			
			/*javax.naming.Context initialContext;

	        try {
	            initialContext = new javax.naming.InitialContext();
	            javax.sql.DataSource dataSource = 
	                        (javax.sql.DataSource)initialContext.lookup("jdbc/LocalUAQDB");
	            con = dataSource.getConnection();            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/
			
			return con;
		}
	
	

	/**
	 * This method is used to update the payment status of the payment
	 * record.
	 * 
	 * @param paymentCommand
	 * @return true
	 * @throws SQLException 
	 */
	public boolean updatePaymentStatus(String paymentId, String status){
		
		logger.enter("updatePaymentStatus paymentId = " + paymentId + " : status = " + status);
		
		boolean result = false;
		PreparedStatement ps = null;
		Connection con = null;
		
		String sql = "update POS_PAYMENT set PAYMENT_STATUS=?, LAST_MODIFIED_DATE=SYSDATE where PAYMENT_ID=?";		
		logger.debug("sql query = " + sql);
		
		try {
			
			con = getConnection();
			ps = con.prepareStatement(sql); 
			
			ps.setString( 1, status);
			ps.setString( 2, paymentId);
			Integer updateCount = ps.executeUpdate();
			
			System.out.println("Rows Updated = " + updateCount);
			
			result = updateCount > 0 ? true : false;
			
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
		
		logger.exit("updatePaymentStatus result = " + result);
		
		return result;
	}

	/**
	 * This method is used to block a payment to mark it as payment in
	 * progress.
	 * 
	 * @param paymentId
	 * @return true
	 * @throws SQLException 
	 */
	public boolean updatePaymentInProgress(String paymentId, boolean status){

		logger.enter("updatePaymentInProgress paymentId = " + paymentId + " : status = " + status);

		boolean result = false;
		PreparedStatement ps = null;
		Connection con = null;
		
		String sql = "update POS_PAYMENT set payment_in_progress=?, LAST_MODIFIED_DATE=SYSDATE where PAYMENT_ID=?";
		logger.debug("sql query = " + sql);
		
		try {
                    con = getConnection();			
                    ps = con.prepareStatement(sql); 
                    
                    ps.setBoolean( 1, status);
                    ps.setString( 2, paymentId);
                    
                    Integer updateCount = ps.executeUpdate();
                    
                    System.out.println("Rows Updated = " + updateCount);
                    
                    result = updateCount > 0 ? true : false;
			
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

		logger.exit("updatePaymentInProgress result = " + result);

		return result;
	}	
	
	/**
	 * This method is used to get payment id against given transaction id
	 * 
	 * @param transactionId
	 * @return paymentId
	 * @throws SQLException 
	 */
	public String getPaymentIdForTransactionId(String transactionId){

		logger.enter("getPaymentIdForTransactionId : transactionId = " + transactionId);
		
		String paymentId = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		String sql = "select payment_id from POS_PAYMENT_TRANSACTION where transaction_id = ?";
		logger.debug("sql query = " + sql);
		
		try {
			
			con = getConnection();		
			ps = con.prepareStatement(sql);
			
			ps.setString( 1, transactionId);
			
			ResultSet resultset = ps.executeQuery();
			
			while (resultset.next()) {				
				paymentId = resultset.getString(1);
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
		
		logger.exit("getPaymentIdForTransactionId : paymentId = " + paymentId);

		return paymentId;
	}
		
	/**
	 * Insert payment request into response table
	 * 
	 * @param paymentRequestECR
	 * @return
	 * @throws UAQException
	 * @throws SQLException 
	 */
	public boolean savePaymentRequest(PaymentRequestECR paymentRequestECR){

		logger.enter("savePaymentRequest");
		
		String query = "";
		int intResult = 0;
		boolean result = false;
		Connection con = null;
		
		PreparedStatement pStmt = null;
		
		query = "INSERT INTO POS_PAYMENT_TRANSACTION (transaction_id, transaction_type, payment_id, terminal_no, merchant_id) values (?, ?, ?, ?, ?)";
		logger.debug("sql query = " + query);

		try {
			
			con = getConnection();
			pStmt = con.prepareStatement(query); 

			pStmt.setString( 1, paymentRequestECR.getEcrIdNo());
			pStmt.setString( 2, paymentRequestECR.getTransactionType());
			pStmt.setString( 3, paymentRequestECR.getPaymentId());		
			pStmt.setString( 4, paymentRequestECR.getTerminalNo());
			pStmt.setString( 5, paymentRequestECR.getMerchantId());
			
			intResult = pStmt.executeUpdate();
			
			result = intResult > 0 ? true : false;
		
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

		logger.exit("savePaymentRequest result = " + result);

		return result;
	}

	/**
	 * update response table for payweb payment response
	 * 
	 * @param paymentResponseECR
	 * @return
	 * @throws UAQException
	 * @throws SQLException 
	 */
	public boolean savePaymentResponse(PaymentResponseECR paymentResponseECR){

		logger.enter("savePaymentResponse");

		int intResult = 0;
		boolean result = false;
		PreparedStatement ps = null;
		Connection con = null;
			
		String query = "update POS_PAYMENT_TRANSACTION set response_code=?, response_message=?, pos_transaction_id=?, " +
						"retrieval_ref_no=?, approval_code=?, services_count=?, service1_amount=?, service1_fee1_amount=?, " + 
						"service1_fee1_name=?, dynamic_fee_count=?, dynamic_fee1_amount=?, dynamic_fee1_text=?, " +
						"dynamic_Fee2_amount=?, dynamic_fee2_text=?, dynamic_fee3_amount=?, dynamic_fee3_text=?, " + 
						"invoice_no=?, transaction_date=?, AUTOUPDATE_STATUS=?, AUTOUPDATE_STATUS_MSG=?, other=?,  " +
						"LAST_MODIFIED_DATE=sysdate where transaction_id=?";
		logger.debug("sql query = " + query);
		
		try {
			
			con = getConnection();
			ps = con.prepareStatement(query);  

			ps.setString(1, paymentResponseECR.getResponseCode());
			ps.setString(2, paymentResponseECR.getResponseMessage());
			ps.setString(3, paymentResponseECR.getTransactionId());
			ps.setString(4, paymentResponseECR.getRetrievalRefNo());
			ps.setString(5, paymentResponseECR.getApprovalCode());
			ps.setInt(6, paymentResponseECR.getServicesCount()); 
			ps.setDouble(7, paymentResponseECR.getService1Amount());
			ps.setDouble(8, paymentResponseECR.getService1Fee1Amount());
			ps.setString(9, paymentResponseECR.getService1Fee1Name()); 
			ps.setInt(10, paymentResponseECR.getDynamicFeeCount());
			ps.setDouble(11, paymentResponseECR.getDynamicFee1Amount()); 
			ps.setString(12, paymentResponseECR.getDynamicFee1Text()); 
			ps.setDouble(13, paymentResponseECR.getDynamicFee2Amount());
			ps.setString(14, paymentResponseECR.getDynamicFee2Text()); 
			ps.setDouble(15, paymentResponseECR.getDynamicFee3Amount()); 
			ps.setString(16, paymentResponseECR.getDynamicFee3Text()); 
			ps.setString(17, paymentResponseECR.getInvoiceNo()); 
						
			java.sql.Timestamp transactionDate = null;
			if(paymentResponseECR.getTransactionResponseDate() != null){
				logger.debug("before conversion transactionDate = " + paymentResponseECR.getTransactionResponseDate());
				transactionDate = new java.sql.Timestamp( DateUtil.getSqlDateFromStringDate(paymentResponseECR.getTransactionResponseDate()).getTime());
				logger.debug("after conversion transactionDate = " + transactionDate);			
			}
			ps.setTimestamp(18, transactionDate);						
			ps.setString(19, paymentResponseECR.getAutoUpdateStatus());
			ps.setString(20, paymentResponseECR.getAutoUpdateStatusMessage());
			ps.setString(21, paymentResponseECR.getOther());
			ps.setString(22, paymentResponseECR.getEcrIdNo());

			intResult = ps.executeUpdate();
			
			result = intResult > 0 ? true : false;
			
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

		logger.exit("savePaymentResponse result = " + result);

		return result;
	}	
			
	/**
	 * This method is used to get payment record with a given paymentId
	 * @throws SQLException 
	 * 
	 */	
	public PaymentVO execute(String paymentId){
		
		logger.enter("execute paymentId = " + paymentId);		
		
		PaymentVO paymentCommandResult =  null;		
		PreparedStatement ps = null;
		Connection con = null;
		
		String query = "select * from POS_Payment p where p.payment_id=?";
		logger.debug("sql query = " + query);
		
		try {
                    con = getConnection();
                    ps = con.prepareStatement(query); 
                    ps.setString( 1, paymentId);
                    
                    ResultSet resultset = ps.executeQuery();				
                    
                    while (resultset.next()) {
                         // its unique result
                        paymentCommandResult = new PaymentVO();
                        paymentCommandResult.setPaymentId(resultset.getString("PAYMENT_ID"));		
                        paymentCommandResult.setPaymentStatus(resultset.getString("PAYMENT_STATUS"));		
                        paymentCommandResult.setPaymentInProgress(resultset.getBoolean("PAYMENT_IN_PROGRESS"));
                        paymentCommandResult.setServiceId(resultset.getString("service_id"));
                        paymentCommandResult.setDepartmentId(resultset.getString("department_id"));
                        paymentCommandResult.setCustomerId(resultset.getString("customer_id"));
                        paymentCommandResult.setCustomerName(resultset.getString("customer_name"));	
                        paymentCommandResult.setServiceCode(resultset.getString("SERVICE_CODE"));	
                        paymentCommandResult.setAmount(resultset.getDouble("AMOUNT"));
                        paymentCommandResult.setFeeId(resultset.getString("FEE_ID"));
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
		
		logger.exit("execute");
		
		return paymentCommandResult;
	}	
			
	/**
	 * This method is used to save the payment record.
	 * @param paymentCommand
	 * @return true
	 * @throws DAOException 
	 * @throws SQLException 
	 */	
	public boolean savePayment(PaymentVO paymentCommand){
		System.out.println("------------------- savePayment --------------------");
		logger.enter("savePayment");
		
		boolean result = false;	int rowCount = 0;
		
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO POS_Payment(PAYMENT_ID, PAYMENT_STATUS, SERVICE_CODE, AMOUNT, PAYMENT_IN_PROGRESS, " +
				"SERVICE_ID, DEPARTMENT_ID, FEE_ID, CUSTOMER_ID, CUSTOMER_NAME, CREATED_DATE, LAST_MODIFIED_DATE) " +
				"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE,SYSDATE)";	
		logger.debug("sql query = " + sql);
		
		try{
			
			con = getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, paymentCommand.getPaymentId());
//Oraby: Here we set the PAYMENT_STATUS = Appoved and we didn't get from the paymentCommand object with value approved as expected!!
			ps.setString(2, PURCHASE_STATUS_APPROVED);
			ps.setString(3, paymentCommand.getServiceCode());	
			ps.setDouble(4, paymentCommand.getAmount());
//Oraby: PAYMENT_IN_PROGRESS is Char(1) in the DB and it's values is 0/1 and we built queries to get records with PAYMENT_IN_PROGRESS=1!!
			ps.setBoolean(5, false);
			ps.setString(6, paymentCommand.getServiceId());
			ps.setString(7, paymentCommand.getDepartmentId());
			ps.setString(8, paymentCommand.getFeeId());
			ps.setString(9, paymentCommand.getCustomerId());
			ps.setString(10, paymentCommand.getCustomerName());
			
			rowCount = ps.executeUpdate();
			
			result = rowCount > 0 ? true : false;
			
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
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException e) {
                            logger.error(e.getMessage());
                        }
                    }
		} 	
		
		logger.exit("savePayment result = " + result);
		
		return result;
	}
	
	/**
	 * This method is used to get payment including its services
	 * 
	 * @param paymentId
	 * @return
	 * @throws UAQException
	 * @throws SQLException 
	 */
	public PaymentVO fetchPayment(String paymentId){

		logger.enter("fetchPayment paymentId = " + paymentId);

		PaymentVO paymentCommandResult = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		List<PaymentVO> payments = new ArrayList<PaymentVO>();
		
		String sql = "select * from POS_PAYMENT p "											
						    + " where p.PAYMENT_ID=? "
							+ " order by p.PAYMENT_ID desc";
		logger.debug("sql query = " + sql);
		
		try{

			con = getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, paymentId);
			
			ResultSet resultset = ps.executeQuery();
			
			while (resultset.next()) {
				
				PaymentVO vo = new PaymentVO();
				
				vo.setPaymentId(resultset.getString("PAYMENT_ID"));		
				vo.setPaymentStatus(resultset.getString("PAYMENT_STATUS"));		
				vo.setPaymentInProgress(resultset.getBoolean("PAYMENT_IN_PROGRESS"));				
								
				payments.add(vo);
			}
			
			if (!payments.isEmpty()) {
				PaymentVO paymentObject = payments.get(0);
				paymentCommandResult = new PaymentVO();			
				
				if (paymentObject.getPaymentId() != null && !paymentObject.getPaymentId().isEmpty()) {
					paymentCommandResult.setPaymentId(paymentObject.getPaymentId());			
					paymentCommandResult.setPaymentStatus(paymentObject.getPaymentStatus());					
					paymentCommandResult.setPaymentInProgress(paymentObject.getPaymentInProgress());				
				}					
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
		
		logger.exit("fetchPayment");

		return paymentCommandResult;
	}	
	
	/**
	 * This method is used to get the last failed transaction id
	 * @param paymentId
	 * @param terminalNo TODO
	 * @param merchantID TODO
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */	
	public PaymentVO getLastPaymentTransaction(String paymentId, String terminalNo, String merchantID){
		
		logger.enter("getLastPaymentTransactionId paymentId = " + paymentId + " : terminalNo = " + terminalNo + " : merchantID = " + merchantID);
				
		PreparedStatement ps = null;
		Connection con = null;
		
		PaymentVO paymentVO = new PaymentVO();
		
		StringBuilder queryString = new StringBuilder("Select Pt.transaction_id, Pt.payment_id from POS_PAYMENT_TRANSACTION Pt " +  
				 " Join POS_PAYMENT Pur on Pt.payment_id = Pur.payment_id and Pur.payment_in_progress = 1");				
		queryString.append(" and Pt.terminal_no=? and Pt.merchant_id=?" );
		queryString.append(paymentId != null && !paymentId.isEmpty() ? " And Pt.payment_id=?" : "");
		
		logger.debug("sql query = " + queryString);
		
		if(terminalNo != null && !terminalNo.isEmpty() && merchantID != null && !merchantID.isEmpty()){
                    try{
                        con = getConnection();
                        ps = con.prepareStatement(queryString.toString());		
                        
                        ps.setString(1, terminalNo);
                        ps.setString(2, merchantID);
                        if(paymentId != null && !paymentId.isEmpty()){
                            ps.setString(3, paymentId);
                        }
                        
                        ResultSet resultset = ps.executeQuery();
                        // there could be max one broken transaction on POS
//Oraby: if there is no proken transaction for this POS the paymentVO will be new object which this method returns and it's not handeled
                        while(resultset.next()){					
                            paymentVO.setTransactionID(resultset.getString("transaction_id"));
                            paymentVO.setPaymentId(resultset.getString("payment_id"));
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
				
		logger.exit("getLastPaymentTransactionId result = " + paymentVO);
		
		return paymentVO;
	}

	/**
	 * This method is used to get all broken transactions need to be auto updated through day end batch service.
	 * @param paymentReportFilterCommand
	 * @return
	 * @throws DAOException
	 * @throws SQLException
	 */	
	public List<PaymentResponseECR> getBrokenPaymentTransactions(PaymentReportFilterVO paymentReportFilterCommand){

		logger.enter("getPaymentBrokenTransactions");

		List<PaymentResponseECR> transactions = new ArrayList<PaymentResponseECR>();
		PreparedStatement ps = null;
		
		String queryString = "select pt.transaction_id, pt.transaction_date, pt.response_code, pt.AUTOUPDATE_STATUS, " +
				" pt.transaction_type, payment.payment_status, payment.payment_in_progress " +
				" from POS_PAYMENT_TRANSACTION pt " +
				" join POS_PAYMENT on pt.payment_id = payment.payment_id and payment.payment_status = 'Approved' " +
				" and payment.payment_in_progress = '1' " +
				" where (pt.transaction_type ='01' or pt.transaction_type ='TS') and (pt.transaction_date >= to_date(?, 'mm/dd/yyyy HH24:mi:ss') and pt.transaction_date <= to_date(?, 'mm/dd/yyyy HH24:mi:ss') ) " +
				" Order By Pt.transaction_date Desc";		
		logger.debug("query : " + queryString);
		
		try{
			
			con = getConnection();
			ps = con.prepareStatement(queryString);
			
			ps.setString(1, paymentReportFilterCommand.getStartDate() + " 00:00:00");
			ps.setString(2, paymentReportFilterCommand.getEndDate() + " 23:59:59");
						
			ResultSet resultset = ps.executeQuery();
			
			while (resultset.next()) {
				PaymentResponseECR transaction = new PaymentResponseECR();
				transaction.setTransactionId(resultset.getString("transaction_id"));
				transactions.add(transaction);
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

		logger.exit("getPaymentBrokenTransactions");

		return transactions;
	}
	
	/**
	 * This method is used to update the SOA db table. Its not a regular payment table and operation.
	 * @param paymentId
	 * @param typeOfService
	 * * @param serviceId
	 * @return
	 * @throws SQLException
	 */	
	public PaymentStatus updateApplicantRequestTableStatus(String paymentId){
		
		logger.enter("updateApplicantRequestTableStatus paymentId = " + paymentId);

		PreparedStatement ps = null;
		Connection con = null;
		PaymentStatus paymentStatus = null;
		
		String requestId = paymentId.split("_")[0]; //discarding the  _feeId part
		
		String sql = "update APPLICANT_REQUEST set status_id=? where request_id=?";		
		logger.debug("sql = " + sql);
		
		try {
			
			con = getConnection();
			ps = con.prepareStatement(sql);
			
			paymentStatus = getPaymentStatus(paymentId); // method call to get payment status information such as statusId and requestNo
			
			ps.setString( 1, paymentStatus.getStatusId()); 
			ps.setString( 2, requestId); 
			
			Integer updateCount = ps.executeUpdate();
			
			logger.debug("Rows Updated = " + updateCount);
			
			paymentStatus.setRequestId(requestId); // including requestId in payment status information		
			
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
		
		logger.exit("updateApplicantRequestTableStatus paymentStatus = " + paymentStatus.toString());
		
		return paymentStatus;
	}	
	
	/**
	 * This method is used to update the ERP system. Its not a regular table and operation.
	 * @return
	 * @throws SQLException
	 */	
	public boolean updateERPtable(Double amount, String deptCode, String transactionId, String feeId, String custId, 
			String custName, String transactionDate, String edirhamRefNo){
		
		boolean result = false;
		PreparedStatement ps = null;
		Connection con = null;
		
		logger.enter("updateERPtable");
		
		String sql = "INSERT INTO " + ConfUtils.getValue("erp.table.name") + " (CURRENCY_CODE, AMOUNT, DEPARTMENT_CODE, RECEIPT_METHOD_TYPE, " +
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
			ps.setString(5, transactionId.substring(transactionId.indexOf('E'), transactionId.length())); // removing the leading zeros for ERP
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
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error(e.getMessage());
			}
		} catch (ClassNotFoundException e) {			
			logger.error(e.getMessage());
		} catch (ParseException e) {
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
		
		logger.exit("updateERPtable result = " + result);
		
		return result;
	}

	/**
	 * This method is used to get payment status information such as statusId, serviceId and RequestNo for a given paymentId
	 * @param paymentId
	 * @return
	 */	
	public PaymentStatus getPaymentStatus(String paymentId){
		
		logger.enter("getPaymentStatus : paymentId = " + paymentId);
		
		PaymentStatus paymentStatus = new PaymentStatus();
		PreparedStatement ps = null;
		Connection con = null;
		
		String requestId = paymentId.split("_")[0]; //discarding the _feeId part
		
		String sql = "select psl.STATE_ID_AFTER_SUCCESS, p.service_id from PaymentStatus_Lookups psl " +
					 "join POS_PAYMENT p on psl.fee_id = p.fee_id and p.payment_id = ?";
		logger.debug("sql query = " + sql);
		
		try {
			
			con = getConnection();			
			ps = con.prepareStatement(sql); 
			
			ps.setString( 1, paymentId );
			
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
	private String getRequestNoForRequestId(String requestId){		
		
		logger.enter("getRequestNoForRequestId : requestId = " + requestId);
				
		PreparedStatement ps = null;
		Connection con = null;
		String requestNo = null;
		
		String sql = "select REQUEST_NO from APPLICANT_REQUEST " +
					 " where REQUEST_ID = ?";
		logger.debug("sql query = " + sql);
		
		try {
			
			con = getConnection();							
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
	public PaymentVO getPaymentServiceCode(String serviceId){

		logger.enter("getPaymentServiceCode : serviceId = " + serviceId);

		PaymentVO paymentServiceCode = new PaymentVO();
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
                        /*paymentServiceCode.setMerchantId(resultset.getString("merchant_id"));
                        paymentServiceCode.setTerminalId(resultset.getString("terminal_id"));*/				
                        break; // single record is returned by the query
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

		logger.exit("getPaymentServiceCode");

		return paymentServiceCode;
	}
	
	/**
	 * This method is used to get the general fee which belong to the super entity eGD
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */	
	/*public Double getGeneralFee()  throws UAQException, SQLException{
		
		logger.enter("getGeneralFee");
		
		Double feeAmount = null;
		PreparedStatement ps = null;
		Connection con = null;
		
		String sql = "select amount from ESERVICE_FEE_MATRIX where service_id = ?";
		
		try {
			
			con = getConnection();
			
			logger.debug("sql query = " + sql);
			
			ps = con.prepareStatement(sql); 
			
			ps.setString(1, "99999");
						
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
	}*/
	
	/**
	 * This method is used to get the amount for a given feeId
	 * @param feeId
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */	
	public PaymentVO getFeeDetail(String feeId){
		
		logger.enter("getFeeDetail feeId = " + feeId);
				
		PreparedStatement ps = null;
		Connection con = null;		
		
		PaymentVO paymentServiceCode = new PaymentVO();
//Oraby: this query might return with multi records as fee_id is not unique	
		String sql = "select DEPARTMENT_CODE, service_id, amount from ESERVICE_FEE_MATRIX where fee_id = ?";
		logger.debug("sql query = " + sql);
		
		try {
                    con = getConnection();			
                    ps = con.prepareStatement(sql); 
                    ps.setString(1, feeId);
                                            
                    ResultSet resultset = ps.executeQuery();
                    
                    while (resultset.next()) {
                        paymentServiceCode.setAmount(resultset.getDouble("amount"));
                        paymentServiceCode.setServiceId(resultset.getString("service_id"));
                        paymentServiceCode.setDepartmentId(resultset.getString("DEPARTMENT_CODE"));
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
		
		logger.exit("getFeeDetail");

		return paymentServiceCode;
	}
	
	/**
	 * unique transaction id based on a sequence for each service belong to a department
	 * @param sequenceName
	 * @return
	 */
	public String getSequenceByDepartment(String sequenceName) {

		logger.enter("getSequenceByDepartment");

		String query = "SELECT " + sequenceName + ".NEXTVAL FROM DUAL";
		logger.debug("SQL - " + sequenceName);

		String nextValue = "";
		PreparedStatement ps = null;
		Connection con = null;

		try {

			con = getConnection();
			ps = con.prepareStatement(query);
			ResultSet rs  = ps.executeQuery();
			while (rs.next()) {
				nextValue =rs.getInt(1)+"";
			
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

		logger.exit("getSequenceByDepartment  -" + nextValue);

		return nextValue;
	}
}
