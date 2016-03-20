package com.uaq.pos.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.uaq.pos.pojo.PaymentRequestECR;
import com.uaq.pos.pojo.PaymentVO;

public class PosUtil {
	
	private static transient UAQLogger logger = new UAQLogger(PosUtil.class);
	
	private static final String EMPTY_STRING = "";

	/**
	 * This method is used to convert decimal amount to ISO format, to be sent
	 * to payment service provider
	 * 
	 * @param decimalAmount
	 * @return
	 */

	public static String convertAmountDecimalToISOFormat(String decimalAmount) {
		String isoFormattedAmount = "00";
		if (decimalAmount != null && !decimalAmount.equals(EMPTY_STRING)) {
			try {
				isoFormattedAmount = String.format("%.0f", (new Double(decimalAmount)) * 100);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return isoFormattedAmount;
	}

	/**
	 * This method is used to convert ISO formatted amount to decimal format, to
	 * be shown on the screens
	 * 
	 * @param isoAmount
	 * @return
	 */

	public static Double convertAmountISOToDecimalFormat(String isoAmount) {
		Double dValue = 0.0;
		if (isoAmount != null && !isoAmount.equals(dValue)) {
			try {
				dValue = Double.valueOf(isoAmount) / 100;
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		return dValue;
	}
	
	/*public static String callRestWebservice(PaymentRequestECR paymentRequestECR, String host, String port, String method, String username, String password){
		
		String response = "";
		
		HttpClient client = new HttpClient();      
        PostMethod postMethod = new PostMethod("http://" + host + ":" + port + method);   
        client.getParams().setAuthenticationPreemptive(true); 
        
        try{        
	        
	        StringRequestEntity requestEntity = new StringRequestEntity(new Gson().toJson(paymentRequestECR), "application/json", "UTF-8"); 	        
	        postMethod.setRequestEntity(requestEntity);
	        
	        Header contentType = new Header("Content-Type", "application/json");	   	 
	        postMethod.addRequestHeader(contentType);
	        
	        Credentials defaultcreds = new UsernamePasswordCredentials(username, password);     
	        client.getState().setCredentials(new AuthScope(host, Integer.parseInt(port), AuthScope.ANY_REALM), defaultcreds);     
	                           
	        client.executeMethod(postMethod);     
	      
	        byte[] responseBody = postMethod.getResponseBody(); 
	        
	        response = new String(responseBody);           
           
	    } catch(UnsupportedEncodingException e) {     
	        e.printStackTrace();     
	    } catch(HttpException e) {     
	        e.printStackTrace();     
	    } catch(IOException e) {     
	        e.printStackTrace();      
	    }          
		
		return response;
	}*/
	
	public static PaymentRequestECR fillRequest(PaymentVO paymentVO, String transactionType, String transactionIDseq){
		
		PaymentRequestECR paymentRequestECR = new PaymentRequestECR();
		
		if(transactionType.equalsIgnoreCase("Sales")){
			paymentRequestECR.setTransactionType("01");
			paymentRequestECR.setPaymentId(paymentVO.getPaymentId());
			paymentRequestECR.setServiceCode(paymentVO.getServiceCode());
			paymentRequestECR.setAmount(paymentVO.getAmount().toString());   
			paymentRequestECR.setEcrIdNo(StringUtils.leftPad(transactionIDseq, 16, "0")); // max length 16, small is left padded with 0s
			paymentRequestECR.setTerminalNo(paymentVO.getTerminalId());
			paymentRequestECR.setMerchantId(paymentVO.getMerchantId());
		} else if(transactionType.equalsIgnoreCase("LastTransactionStatus")){
			paymentRequestECR.setTransactionType("TS");
			paymentRequestECR.setEcrIdNo(paymentVO.getTransactionID());
		}	
				
		return paymentRequestECR;
		
	}
		
}
