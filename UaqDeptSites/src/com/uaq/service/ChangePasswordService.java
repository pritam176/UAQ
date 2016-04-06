package com.uaq.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaq.command.ChangePasswordCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.dao.ChangePasswordDAO;
import com.uaq.logger.UAQLogger;
import com.uaq.soap.warpper.WebServiceWarpper;

@Service("changePasswordService")
public class ChangePasswordService {

	protected static UAQLogger logger = new UAQLogger(ChangePasswordService.class);
	
	@Autowired
	private ChangePasswordDAO changePasswordDAO;

	
	public Map<String, String> changePassword(ChangePasswordCommand command) throws SQLException {
		
		
		Map<String, String> statusMessage = new HashMap<String, String>();
		com.uaq.proxies.changepasswordbpel_client_ep.types.InputPayload inputPayload = new com.uaq.proxies.changepasswordbpel_client_ep.types.InputPayload();

		inputPayload.setPassword(command.getPassword());
		inputPayload.setUsername(command.getUserName());

		try {
			com.uaq.proxies.changepasswordbpel_client_ep.types.OutputPayload outputPayload = new WebServiceWarpper().changePassword(inputPayload, PropertiesUtil.getProperty("SOA_URL_CHANGE_PASSWORD"), PropertiesUtil.getProperty("SOA_USERNAME"), PropertiesUtil.getProperty("SOA_PASSWORD"));

			if (outputPayload != null) {
				if("Success".equals(outputPayload.getStatus())){
					changePasswordDAO.updateISActive(command);
					statusMessage.put("EN", outputPayload.getMessageEN());
					statusMessage.put("AR", outputPayload.getMessageAR());
					logger.info("reponse from changepassword service is :: " + outputPayload.getMessageEN());
				}
				
				
			}
		} catch (Exception e) {
			logger.error("Exception from changepassword service :: " + e);
		}
		return statusMessage;

	}

}
