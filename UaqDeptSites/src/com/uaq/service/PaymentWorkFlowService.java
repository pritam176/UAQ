package com.uaq.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.uaq.dao.PaymentWorkFLowDAO;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.PaymentWorkFlowVO;

@Service
public class PaymentWorkFlowService {
	
	private static transient UAQLogger logger = new UAQLogger(PaymentWorkFlowService.class);
	
	
	PaymentWorkFLowDAO paymentWorkFLowDAO = new PaymentWorkFLowDAO();
	
	
	public boolean savePaymentWorkFlow(PaymentWorkFlowVO paymentWorkFlowVO,Connection con) throws SQLException{
		logger.debug("RequestNo-"+paymentWorkFlowVO.getRequestNo()+": Request Status-"+paymentWorkFlowVO.getStatusId());
		return paymentWorkFLowDAO.savePaymentWorkFlow(paymentWorkFlowVO,con);
	}
	
	public boolean updatePaymentWorkFLow(PaymentWorkFlowVO paymentWorkFlowVO,Connection con) throws SQLException{
		logger.debug("RequestNo-"+paymentWorkFlowVO.getRequestNo()+": Request Status-"+paymentWorkFlowVO.getStatusId());
		return paymentWorkFLowDAO.updatePaymentWorkFLow(paymentWorkFlowVO,con);
	}
	
	public boolean deletePaymentWorkFLow(PaymentWorkFlowVO paymentWorkFlowVO,Connection con) throws SQLException{
		logger.debug("RequestNo-"+paymentWorkFlowVO.getRequestNo()+": Request Status-"+paymentWorkFlowVO.getStatusId());
		return paymentWorkFLowDAO.deletePaymentWorkFLow(paymentWorkFlowVO,con);
	}
	
	public boolean isExistInPaymentWorkFlow(PaymentWorkFlowVO paymentWorkFlowVO,Connection con) throws SQLException{
		logger.debug("RequestNo-"+paymentWorkFlowVO.getRequestNo()+": Request Status-"+paymentWorkFlowVO.getStatusId());
		return paymentWorkFLowDAO.isExistInPaymentWorkFlow(paymentWorkFlowVO,con);
	}
	public List<PaymentWorkFlowVO> getPaymentWorkFlowVOList(Connection con) throws SQLException{
		return paymentWorkFLowDAO.getPaymentWorkFlowVOList(con);
	}
}
