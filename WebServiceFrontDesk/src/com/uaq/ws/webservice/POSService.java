package com.uaq.ws.webservice;

import com.uaq.ws.pojo.PosInfo;
import com.uaq.ws.pos.PaymentRequestECR;
import com.uaq.ws.pos.PaymentResponseECR;
import com.uaq.ws.pos.SerialCommUtil;
import com.uaq.ws.util.UAQLogger;
import com.uaq.ws.webservice.interfac.IPOSService;

public class POSService implements IPOSService{
	
	private static transient UAQLogger logger = new UAQLogger(POSService.class);
	
	@Override
	public PaymentResponseECR doTransaction(PaymentRequestECR paymentRequestECR) {
		
		logger.enter("sendMessage(message, userId)");
		
		SerialCommUtil communicator = new SerialCommUtil();
		PaymentResponseECR paymentResponseECR = new PaymentResponseECR(); 

		try{
			
			paymentResponseECR = communicator.doTransaction(paymentRequestECR);
    	
		} catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("paymentResponseECR : " + paymentResponseECR.toString());
		
		return paymentResponseECR;
	}

	@Override
	public PosInfo getPosInfo() {
	
		PosInfo posInfo = new PosInfo();
		
		String merchantID = System.getProperty("mid"),
			   terminalID = System.getProperty("tid");		
		
		posInfo.setMerchantID(merchantID);
		posInfo.setTerminalID(terminalID);
		
		return posInfo;
	}
	
}
