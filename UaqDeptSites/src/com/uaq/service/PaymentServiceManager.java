package com.uaq.service;

import com.uaq.logger.UAQLogger;
import com.uaq.payment.AutoUpdatePaymentRequest;
import com.uaq.payment.AutoUpdatePaymentResponse;
import com.uaq.payment.Connection;
import com.uaq.payment.PaymentUtil;

/**
 * This class coordinates the performing and receiving of payment request from
 * application with the payment service provider and process the response.
 * 
 * @author raheem
 * 
 */

public class PaymentServiceManager extends Connection {

	public static transient UAQLogger logger = new UAQLogger(PaymentServiceManager.class);
	
	/**
	 * This method is used to perform auto update transaction
	 * 
	 * @param paymentRequest
	 * @return
	 * @throws Exception
	 */

	public AutoUpdatePaymentResponse doAutoUpdateTransaction(AutoUpdatePaymentRequest paymentRequest) throws Exception {

		logger.enter("doAutoUpdateTransaction");

		paymentRequest.setQueryString(PaymentUtil.buildQueryStringForAutoUpdate(paymentRequest));

		logger.exit("doAutoUpdateTransaction");

		return processAutoUpdateTransaction(doTransaction(paymentRequest));
	}

	/**
	 * This method is used to process the auto update response
	 * 
	 * @param response
	 * @return
	 */

	private AutoUpdatePaymentResponse processAutoUpdateTransaction(String response) {

		logger.enter("processAutoUpdateTransaction");

		AutoUpdatePaymentResponse autoUpdatePaymentResponse = PaymentUtil.fillAutoUpdatePaymentResponse(response);

		logger.exit("processAutoUpdateTransaction");

		return autoUpdatePaymentResponse;
	}

}
