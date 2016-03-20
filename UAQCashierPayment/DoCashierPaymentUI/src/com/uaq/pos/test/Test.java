package com.uaq.pos.test;

import com.uaq.pos.model.IPOSService;
import com.uaq.pos.model.POSServiceImpl;
import com.uaq.pos.pojo.PaymentRequestECR;
import com.uaq.pos.pojo.PaymentResponseECR;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		IPOSService pos = new POSServiceImpl();
		
		String paymentID = "", feeID = "0103031", terminalNo = "00051427", merchantID = "936696000",
				customerId = "47102", customerName = "Raheem";
		
		PaymentRequestECR paymentRequestECR = pos.preTransaction(paymentID, feeID, customerId, customerName, terminalNo, merchantID);		
		System.out.println("PaymentRequestECR = "+ paymentRequestECR);
		
		//call pos service 
		
		//after getting response
		/*String jsonResponse = "{\"approvalCode\":218823,\"customLRC\":\"W\",\"dynamicFee1Amount\":0,\"dynamicFee1Text\":\"COLLECTION CNTR COMM\",\"dynamicFee2Amount\":3,\"dynamicFee2Text\":\"E-DIRHAM COMM\",\"dynamicFee3Amount\":7,\"dynamicFee3Text\":\"uaq egov fees\",\"dynamicFeeCount\":3,\"ecrIdNo\":\"0001451218626939\",\"lrc\":\"W\",\"posDate\":271216,\"posTime\":\"0513\",\"responseCode\":\"00\",\"retrievalRefNo\":150000287844,\"service1Amount\":2,\"service1Fee1Amount\":0,\"service1Fee1Name\":\"E-SERVICE OWNER COMM\",\"servicesCount\":1,\"transactionId\":\"0000150000287844\",\"transactionType\":\"01\"}";
		PaymentResponseECR paymentResponseECR = pos.postTransaction(jsonResponse);		
		System.out.println("result  = "+ paymentResponseECR);*/		
	
	}

}
