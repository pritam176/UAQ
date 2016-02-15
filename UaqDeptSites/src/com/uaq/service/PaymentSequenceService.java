package com.uaq.service;

import org.springframework.stereotype.Service;

import com.uaq.dao.PaymentSequenceDAO;
import com.uaq.logger.UAQLogger;


@Service
public class PaymentSequenceService {
	
	private static transient UAQLogger logger = new UAQLogger(PaymentSequenceService.class);
	
	public  PaymentSequenceDAO paymentSequenceDAO =new PaymentSequenceDAO();
	
	public String getSequnceNextValue(String sequenceName){
		logger.debug("sequenceName-"+sequenceName);
		return paymentSequenceDAO.getSequenceByDepartment(sequenceName);
	}

}
