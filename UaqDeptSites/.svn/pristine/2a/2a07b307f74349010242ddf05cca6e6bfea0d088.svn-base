package com.uaq.service;

import static com.uaq.common.ApplicationConstants.LANG_ARABIC;
import static com.uaq.common.ApplicationConstants.LANG_ENGLISH;
import static com.uaq.common.ApplicationConstants.WS_PASSWORD;
import static com.uaq.common.ApplicationConstants.WS_USERNAME;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uaq.services.changepassword.model.UAQChangePasswordServicePortBindingStub;
import uaq.services.changepassword.model.UAQChangePasswordService_PortType;
import uaq.services.changepassword.model.UAQChangePasswordService_Service;
import uaq.services.changepassword.model.UAQChangePasswordService_ServiceLocator;

import com.uaq.command.ChangePasswordCommand;
import com.uaq.dao.ChangePasswordDAO;
import com.uaq.logger.UAQLogger;

@Service("changePasswordService")
public class ChangePasswordService {

	protected static UAQLogger logger = new UAQLogger(ChangePasswordService.class);
	
	@Autowired
	private ChangePasswordDAO changePasswordDAO;

	private uaq.services.changepassword.model.UserContext uc = null;
	private UAQChangePasswordService_Service service = null;
	private UAQChangePasswordService_PortType port = null;
	private UAQChangePasswordServicePortBindingStub stub = null;

	private void createStub() {
		uc = new uaq.services.changepassword.model.UserContext();
		uc.setUsername(WS_USERNAME);
		uc.setPassword(WS_PASSWORD);

		try {
			service = (UAQChangePasswordService_Service) new UAQChangePasswordService_ServiceLocator();
			port = service.getUAQChangePasswordServicePort();
			stub = (UAQChangePasswordServicePortBindingStub) port;

		} catch (ServiceException e) {
			logger.error("WebService Error  |" + e.getMessage());
		}

	}

	public Map<String, String> changePassword(ChangePasswordCommand command) throws SQLException {
		
		createStub();
		
		Map<String, String> statusMessage = new HashMap<String, String>();
		ChangePassword.InputPayload inputPayload = new ChangePassword.InputPayload();

		inputPayload.setPassword(command.getPassword());
		inputPayload.setUsername(command.getUserName());

		try {
			ChangePassword.OutputPayload outputPayload = stub.changePassword(inputPayload, uc);

			if (outputPayload != null) {
				if("Success".equals(outputPayload.getStatus())){
					changePasswordDAO.updateISActive(command);
					statusMessage.put("EN", outputPayload.getMessage_EN());
					statusMessage.put("AR", outputPayload.getMessage_AR());
					logger.info("reponse from changepassword service is :: " + outputPayload.getMessage_EN());
				}
				
				
			}
		} catch (RemoteException e) {
			logger.error("Exception from changepassword service :: " + e);
		}
		return statusMessage;

	}

}
