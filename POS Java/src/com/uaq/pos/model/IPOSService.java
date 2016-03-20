package com.uaq.pos.model;

import com.uaq.pos.pojo.PaymentRequestECR;
import com.uaq.pos.pojo.PaymentResponseECR;

/**
 * This is integration interface to handle pre and post transaction activities and interact with the database
 * @author raheem
 *
 */

public interface IPOSService {

	/**
	 * This method is called by integration system to perform pre transaction activity of pos payment.
	 * @param paymentID
	 * @param feeID
	 * @param customerId
	 * @param customerName
	 * @return
	 */
	public PaymentRequestECR preTransaction(String paymentID, String feeID, String customerId, String customerName, String terminalNo, String merchantID);
	
	/**
	 * This method is used to handle post transaction activities of pos payment
	 * @param jsonResponse
	 * @return
	 */
	public PaymentResponseECR postTransaction(String jsonResponse);
	
}
