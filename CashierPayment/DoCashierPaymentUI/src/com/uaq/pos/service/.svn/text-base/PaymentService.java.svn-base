package com.uaq.pos.service;

import java.sql.SQLException;
import java.util.List;

import com.uaq.pos.dao.PaymentDAO;
import com.uaq.pos.exception.UAQException;
import com.uaq.pos.pojo.PaymentReportFilterVO;
import com.uaq.pos.pojo.PaymentRequestECR;
import com.uaq.pos.pojo.PaymentResponseECR;
import com.uaq.pos.pojo.PaymentStatus;
import com.uaq.pos.pojo.PaymentVO;

/**
 * Service class for Payment process. It calls the DAO layer for database
 * access
 * 
 * @author mraheem
 * 
 */

public class PaymentService {
	
	PaymentDAO paymentDAO = null;
			
	public PaymentService(){
		try{
			paymentDAO = new PaymentDAO();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//private static transient UAQLogger logger = new UAQLogger(PaymentService.class);
	
	/**
	 * This method is used to get full payment records and perform autoupdate if its broken transaction.
	 * abstract class method.
	 * @throws SQLException 
	 */
	
	public PaymentVO execute(String paymentId){
		
		return paymentDAO.execute(paymentId);

	}
	
	/**
	 * This method is used to get payment record, without performing autoupdate.
	 * @param paymentId
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */	
	public PaymentVO getPayment(String paymentId){
		
		PaymentVO paymentVO = paymentDAO.execute(paymentId);				
			
		return paymentVO;
	}
	
	/**
	 * This method is used to get pos payment record based on given filter criteria 
	 * @param paymentReportFilterVO
	 * @return
	 */
	public List<PaymentResponseECR> execute(PaymentReportFilterVO paymentReportFilterVO){

		return paymentDAO.getBrokenPaymentTransactions(paymentReportFilterVO);

	}
	
	/**
	 * This method is used to get the last payment transaction id for the given payment id
	 * @param paymentId
	 * @return transactionId  last payment transaction id
	 * @throws UAQException
	 * @throws SQLException
	 */	
	public PaymentVO getLastPaymentTransaction(String paymentId, String terminalNo, String merchantID){
		
		return paymentDAO.getLastPaymentTransaction(paymentId, terminalNo, merchantID);
	}
        
	/**
	 * This method is used to fetch Payment record
	 * @param paymentId
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */	
	public PaymentVO fetchPayment(String paymentId){
		
		return paymentDAO.fetchPayment(paymentId);			
	}

	/**
	 * This method is used to update the  payment status to Complete after
	 * receiving successful payment response from the payment service provider
	 * 
	 * @param paymentVO
	 * @return
	 * @throws UAQException
	 * @throws SQLException 
	 */
	public boolean updatePaymentStatus(String paymentId, String status){
		return paymentDAO.updatePaymentStatus(paymentId, status);
	}

	/**
	 * This method is used to block a  payment to mark it as payment in
	 * progress or not in progress
	 * 
	 * @param paymentId
	 * @param status
	 * @return success or fail
	 * @throws UAQException
	 * @throws SQLException 
	 */
	public boolean updatePaymentInProgress(String paymentId, boolean status){
		return paymentDAO.updatePaymentInProgress(paymentId, status);
	}
	
	/**
	 * This method is used to find the payment id against given transaction id
	 * of the payweb request which was saved before sending payweb payment
	 * request to service provider
	 * 
	 * @param transactionId
	 * @return
	 * @throws UAQException
	 * @throws SQLException 
	 */
	public String getPaymentIdForTransactionId(String transactionId){
		return paymentDAO.getPaymentIdForTransactionId(transactionId);
	}
	
	/**
	 * This method is used to save payweb request
	 * @param paywebPaymentRequest
	 * @return
	 * @throws UAQException
	 * @throws SQLException 
	 */
	public boolean savePaymentRequest(PaymentRequestECR paymentRequestECR){
		return paymentDAO.savePaymentRequest(paymentRequestECR);
	}

	/**
	 * This method is used to save payweb response
	 * @param payWebPaymentResponse
	 * @return
	 * @throws UAQException
	 * @throws SQLException 
	 */
	public boolean savePaymentResponse(PaymentResponseECR paymentResponseECR){
		return paymentDAO.savePaymentResponse(paymentResponseECR);
	}
		
	/**
	 * This method is used to save the invoice
	 * @param paymentVO
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */
	public boolean savePayment(PaymentVO paymentVO){
		return paymentDAO.savePayment(paymentVO);
	}	
	
	/**
	 * This method is used to update ERP integration table
	 * @param amount
	 * @param deptCode
	 * @param transactionId
	 * @param feeId
	 * @param custId
	 * @param custName
	 * @param transactionDate
	 * @param edirhamRefNo
	 * @return status
	 * @throws SQLException
	 */
	public boolean updateERPtable(Double amount, String deptCode, String transactionId, String feeId, String custId, 
			String custName, String transactionDate, String edirhamRefNo){
		
		return paymentDAO.updateERPtable(amount, deptCode, transactionId, feeId, custId, 
				custName, transactionDate, edirhamRefNo);
	}
	
	/**
	 * This method is used to update the next step in business flow
	 * @param paymentId
	 * @return PaymentStatus ojb
	 * @throws SQLException
	 */
	public PaymentStatus updateApplicantRequestTableStatus(String paymentId){
		
		return paymentDAO.updateApplicantRequestTableStatus(paymentId);
	}	
	
	/**
	 * This method is used to get service code information
	 * @param serviceId
	 * @return PaymentVO
	 * @throws UAQException
	 * @throws SQLException
	 */
	public PaymentVO getPaymentServiceCode(String serviceId){
	
		return paymentDAO.getPaymentServiceCode(serviceId);	
	}	
	
	/**
	 * This method is used to get general fee
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */
	/*public Double getGeneralFee() throws UAQException, SQLException{
		return paymentDAO.getGeneralFee();
	}*/
	
	/**
	 * This method is used to get fee detail
	 * @param feeId
	 * @return PaymentVO
	 * @throws UAQException
	 * @throws SQLException
	 */
	public PaymentVO getFeeDetail(String feeId){
		return paymentDAO.getFeeDetail(feeId);
	}	
	
	/**
	 * unique transaction id based on a sequence for each service belong to a department
	 * @param sequenceName
	 * @return
	 */
	public String getSequnceNextValue(String sequenceName){
		
		return paymentDAO.getSequenceByDepartment(sequenceName);
	}
	
}
