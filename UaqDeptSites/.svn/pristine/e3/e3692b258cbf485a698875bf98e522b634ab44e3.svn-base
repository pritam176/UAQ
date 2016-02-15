package com.uaq.service;

import org.springframework.stereotype.Service;

import com.uaq.dao.PaymentWorkFLowDAO;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.PaymentWorkFlowVO;

@Service
public class PaymentWorkFlowService {
	
	private static transient UAQLogger logger = new UAQLogger(PaymentWorkFlowService.class);
	
	
	PaymentWorkFLowDAO paymentWorkFLowDAO = new PaymentWorkFLowDAO();
	
	
	public boolean savePaymentWorkFlow(PaymentWorkFlowVO paymentWorkFlowVO){
		logger.debug("RequestNo-"+paymentWorkFlowVO.getRequestNo()+": Request Status-"+paymentWorkFlowVO.getStatusId());
		return paymentWorkFLowDAO.savePaymentWorkFlow(paymentWorkFlowVO);
	}
	
	public boolean updatePaymentWorkFLow(PaymentWorkFlowVO paymentWorkFlowVO){
		logger.debug("RequestNo-"+paymentWorkFlowVO.getRequestNo()+": Request Status-"+paymentWorkFlowVO.getStatusId());
		return paymentWorkFLowDAO.updatePaymentWorkFLow(paymentWorkFlowVO);
	}
	
	public boolean deletePaymentWorkFLow(PaymentWorkFlowVO paymentWorkFlowVO){
		logger.debug("RequestNo-"+paymentWorkFlowVO.getRequestNo()+": Request Status-"+paymentWorkFlowVO.getStatusId());
		return paymentWorkFLowDAO.deletePaymentWorkFLow(paymentWorkFlowVO);
	}
	
	public boolean isExistInPaymentWorkFlow(PaymentWorkFlowVO paymentWorkFlowVO){
		logger.debug("RequestNo-"+paymentWorkFlowVO.getRequestNo()+": Request Status-"+paymentWorkFlowVO.getStatusId());
		return paymentWorkFLowDAO.isExistInPaymentWorkFlow(paymentWorkFlowVO);
	}

}
