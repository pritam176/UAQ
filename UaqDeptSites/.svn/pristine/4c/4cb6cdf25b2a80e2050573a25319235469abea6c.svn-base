package com.uaq.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.springframework.stereotype.Service;

import uaq.service.ucm.common.model.UCMWCMServicePortBindingStub;
import uaq.service.ucm.common.model.UCMWCMService_PortType;
import uaq.service.ucm.common.model.UCMWCMService_Service;
import uaq.service.ucm.common.model.UCMWCMService_ServiceLocator;
import uaq.service.ucm.common.model.UcmDocument;

import com.uaq.logger.UAQLogger;


/**
 * Service class for UCM Portal Get URl From did
 * 
 * JAR Name-UCMPortal.jar
 * WSDL-http://94.57.252.234:7001/UAQUCMCommonService/UCMWCMServicePort?WSDL
 * 
 * @author Pritam
 * 
 */

@Service("uCMCenterURLService")
public class UCMCenterURLService {

	protected static UAQLogger logger = new UAQLogger(UCMCenterURLService.class);
	
	private  UCMWCMService_Service service =null;
	private  UCMWCMServicePortBindingStub stub=null;
	private  UCMWCMService_PortType port= null;
	
	public String getWebCenterURLofFile(String did){
		try {
			service = (UCMWCMService_Service)new UCMWCMService_ServiceLocator();
			port = service.getUCMWCMServicePort();
			stub = (UCMWCMServicePortBindingStub)port;

		} catch (ServiceException e) {
			logger.error("WebService Error  |" + e.getMessage());
		}
		String url="";
		try {
			UcmDocument output=stub.getWebURL(did);
			if(output!=null){
				logger.info("Sucess  |" + output.getWeblocation());
				url=output.getWeblocation();
			}else{
				logger.info("Failure  | stub.getWebURL(did); return error" );
			}
		} catch (RemoteException e) {
			logger.error("WebService Error  |" + e.getMessage());
		}
		
		return url;
	}
}
