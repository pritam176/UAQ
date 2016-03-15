package com.uaq.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.uaq.dao.PaymentSequenceDAO;
import com.uaq.logger.UAQLogger;


@Service
public class PaymentSequenceService {
	
	private static transient UAQLogger logger = new UAQLogger(PaymentSequenceService.class);
	
	public  PaymentSequenceDAO paymentSequenceDAO =new PaymentSequenceDAO();
	
	public String getSequnceNextValue(String sequenceName,Connection con) throws SQLException{
		logger.debug("sequenceName-"+sequenceName);
		return paymentSequenceDAO.getSequenceByDepartment(sequenceName,con);
	}

}
