/**
 * 
 *//*
package com.uaq.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import uaq.mobile.wrapper.PaymentHistory;
import uaq.mobile.wrapper.PaymentHistoryPortBindingStub;
import uaq.mobile.wrapper.PaymentHistoryServiceLocator;
import com.oracle.xmlns.Mobile_PaymentHistory.Mobile_PaymentHistory_pjr.Mobile_PaymentHistory_BPEL.ProcessResponseListType;

import com.uaq.common.ApplicationConstants;
import com.uaq.logger.UAQLogger;
import com.uaq.proxies.mobile_paymenthistory_bpel_client_ep.types.ProcessResponseType;
import com.uaq.service.mapper.PaymentHistoryMapper;
import com.uaq.vo.PaymentHistoryVO;

*//**
 * @author akhil
 *
 *//*
@Service(value="paymentHistoryService")
public class PaymentHistoryService {

	
	protected static UAQLogger logger = new UAQLogger(PaymentHistoryService.class);
	
	@Autowired
	@Qualifier("paymentHistoryMapper")
	PaymentHistoryMapper paymentHistoryMapper;

	
	
	
	
	
	public List<PaymentHistoryVO>getPaymentHistoryList (String accountId){
		createStub() ;
		List<PaymentHistoryVO>paymentHistoryList = new ArrayList<PaymentHistoryVO>();
		try {
			ProcessResponseType [] processResponseListType	= stub.getPaymentHistory(accountId, uc);
//			paymentHistoryList =paymentHistoryMapper.mapPaymentReponseToVO(processResponseListType);
		} catch (RemoteException e) {
			logger.error("WebService Error From getPaymentHistory |" + e.getMessage());
		}
		return paymentHistoryList;
	}
	
	
	private void createStub() {
		uc = new uaq.mobile.wrapper.UserContext();
		uc.setUsername(ApplicationConstants.WS_USERNAME);
		uc.setPassword(ApplicationConstants.WS_PASSWORD);
		try {
			paymentHistoryService = (PaymentHistoryServiceLocator) new PaymentHistoryServiceLocator();
			port = paymentHistoryService.getPaymentHistoryPort();
			stub = (PaymentHistoryPortBindingStub) port;
		} catch (ServiceException e) {
			logger.error("WebService Error  createStub() in PaymentHistoryService |" + e.getMessage());
		}
	}		
}
*/