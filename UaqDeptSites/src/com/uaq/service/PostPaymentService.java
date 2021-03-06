/*package com.uaq.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.springframework.stereotype.Service;

import uaq.middleware.pymt.service.PostPaymentServicePortBindingStub;
import uaq.middleware.pymt.service.PostPaymentService_PortType;
import uaq.middleware.pymt.service.PostPaymentService_Service;
import uaq.middleware.pymt.service.PostPaymentService_ServiceLocator;
import uaq.middleware.pymt.service.UserContext;

import com.uaq.common.ApplicationConstants;
import com.uaq.logger.UAQLogger;

@Service("postPaymentService")
public class PostPaymentService {

	protected static UAQLogger logger = new UAQLogger(PostPaymentService.class);

	private PostPaymentService_PortType type = null;
	private PostPaymentService_Service service = null;
	private PostPaymentServicePortBindingStub stub = null;
	private uaq.middleware.pymt.service.UserContext uc = null;

	private void createStub() {
		uc = new UserContext();
		uc.setUsername(ApplicationConstants.WS_USERNAME);
		uc.setPassword(ApplicationConstants.WS_PASSWORD);

		try {
			service = (PostPaymentService_Service) new PostPaymentService_ServiceLocator();
			type = service.getPostPaymentServicePort();
			stub = (PostPaymentServicePortBindingStub) type;

		} catch (ServiceException e) {
			logger.error(e.getMessage());

		}
	}

	public String  excutePostPayment(String userName) {
		logger.enter("excutePostPayment");
		createStub();
		String result="";
		try {
			result = stub.postPaymentServiceBrokenTransactions(userName, uc);
			logger.debug("PostPayment Status-"+result);
		} catch (RemoteException e) {
			logger.error(e.getMessage());
		}
		
		logger.exit("excutePostPayment");
		return result;
	}
}
*/