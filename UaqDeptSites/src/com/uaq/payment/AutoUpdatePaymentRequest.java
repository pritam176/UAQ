/**
 * 
 */
package com.uaq.payment;

import com.uaq.logger.UAQLogger;

/**
 * This class represents the inquiry payment request sub type of Payment Request
 * Request
 * 
 * @author raheem
 * 
 */
public class AutoUpdatePaymentRequest extends PaymentRequest {
		
	// This class don't need any specific fields
	
	private static transient UAQLogger logger = new UAQLogger(PayWebPaymentRequest.class);

	/**
	 * overriding the parent class method to provide specific secure hash for
	 * AutoUpdatePaymentRequest
	 */
	@Override
	public String calculateSecureHash() {
		
		logger.enter("AutoUpdatePaymentRequest : calculateSecureHash");	

		StringBuffer fields = new StringBuffer(merchantAccount.getSecretKey());		
		fields.append(action);
		fields.append(merchantAccount.getBankID());
		fields.append(merchantAccount.getMerchantID());		
		fields.append(transactionId);
		fields.append(transactionRequestDate);		

		// logger.debug("fields : " + fields.toString()); // for testing purpose only. never log as it contains secret info

		String hashSecureEncoded = PaymentUtil.generateHMACSHA256Hash(fields.toString(), merchantAccount.getSecretKey());

		logger.exit("AutoUpdatePaymentRequest : calculateSecureHash hashSecureEncoded = " + hashSecureEncoded);			

		return hashSecureEncoded;
	}
	
	@Override
	public String toString() {
		return "AutoUpdatePaymentRequest [toString()=" + super.toString() + "]";
	}	
	
}
