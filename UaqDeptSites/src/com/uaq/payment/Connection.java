package com.uaq.payment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import com.uaq.logger.UAQLogger;

/**
 * This class is used to make actual connection to the service provider, send
 * the request and get the response
 * 
 * @author raheem
 * 
 */

public class Connection {

	public static transient UAQLogger logger = new UAQLogger(Connection.class);

	/**
	 * This method performs the payment transaction on the service provider
	 * 
	 * @param paymentRequestDataGram
	 * @return
	 * @throws Exception
	 */

	protected String doTransaction(PaymentRequest paymentRequestDataGram) throws Exception {

		logger.enter("doTransaction");

		StringBuffer output = null;
		String response = null;

		URL url = new URL(paymentRequestDataGram.getMerchantAccount().getBackToBackURL());
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

		// switch off for production, contains sesitive data. switch on only for
		// debugging on local or test environment
		logger.debug("Payment Request Datagram : " +
				paymentRequestDataGram.getQueryString());

		writer.write(paymentRequestDataGram.getQueryString());
		writer.flush();
		// Get the response
		output = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String line;

		while ((line = reader.readLine()) != null) {
			output.append(line);
		}

		response = output.toString();

		// switch off for production, contains sensitive data. switch on only for
		// debugging on local or test environment
		logger.debug("Payment Response Datagram : " + response); 

		logger.exit("doTransaction");

		return response;
	}

}
