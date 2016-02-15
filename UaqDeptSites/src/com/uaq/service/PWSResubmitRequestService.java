package com.uaq.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import uaq.service.pws.model.UAQPWSResubmitServicePortBindingStub;
import uaq.service.pws.model.UAQPWSResubmitService_PortType;
import uaq.service.pws.model.UAQPWSResubmitService_Service;
import uaq.service.pws.model.UAQPWSResubmitService_ServiceLocator;
import uaq.service.pws.model.UserContext;
import WasteContainerRequest.AttachmentRecPayload;
import WasteContainerRequest.Inputpayload;
import WasteContainerRequest.OutputPayload;
import WasteContainerRequest.UserDetailsPayload;

import com.uaq.command.WasteContainerCommand;
import com.uaq.common.ApplicationConstants;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.UserDeatilVO;
import com.uaq.vo.WasteContainerRequestInputVO;
import com.uaq.vo.WasteContainerRequestOutputVO;

/**
 * Service class for ReSubmit Waste Containor Detail
 * 
 * 
 * 
 * Jar Name-PWSDepartmentReSubmit.jar
 * WSDL-http://94.57.252.234:7001/UAQPWSMiddlewareService/UAQPWSServicesPort?WSDL
 * 
 * @author Pritam
 * 
 */
@Service
@Qualifier("pWSResubmitRequestService")
public class PWSResubmitRequestService {
	
	protected static UAQLogger logger = new UAQLogger(PWSResubmitRequestService.class);
	
	
	
	
	@Autowired
	private UCMCenterURLService uCMCenterURLService;
	
	private UAQPWSResubmitService_Service service=null;
	private UAQPWSResubmitService_PortType port=null;
	private UAQPWSResubmitServicePortBindingStub stub=null;
	private UserContext userContext =null;
	
	
	
	
	private void createStub(){
		userContext=new UserContext();
		userContext.setUsername(ApplicationConstants.WS_USERNAME);
		userContext.setPassword(ApplicationConstants.WS_PASSWORD);

		try {
			service = (UAQPWSResubmitService_Service)new UAQPWSResubmitService_ServiceLocator();
			port = service.getUAQPWSResubmitServicePort();
			stub = (UAQPWSResubmitServicePortBindingStub)port;

		} catch (ServiceException e) {
			logger.error("WebService Error  |" + e.getMessage());
			e.printStackTrace();

		}
	}
	
	public WasteContainerRequestOutputVO reSubmitWasteContainor(UserDeatilVO user,WasteContainerRequestInputVO input,WasteContainerCommand wasteContainerCommand){
		
		createStub();
		
		WasteContainerRequestOutputVO outputVO=new WasteContainerRequestOutputVO();
		Inputpayload inputpayload=new Inputpayload();
		UserDetailsPayload userDetail =new UserDetailsPayload();
		
		userDetail.setUsername(user.getLoginUserName());
		userDetail.setTypeOfUser(user.getTypeOfUser());
		userDetail.setNationality(user.getNationality());
		userDetail.setAccountid(user.getAccountid());
		userDetail.setMobileNo(user.getMobileNo());
		userDetail.setEmailID(user.getEmailID());
		userDetail.setEmiratesId(user.getEmiratesId());
		userDetail.setTradeLienceNo(user.getTradeLienceNo());
		userDetail.setFamilyBookNo(user.getFamilyBookNo());
		userDetail.setFirstName(user.getFirstName());
		userDetail.setMiddleName(user.getMiddleName());
		userDetail.setLastName(user.getLastName());
		userDetail.setAddress1(user.getAddress1());
		userDetail.setAddress2(user.getAddress2());
		userDetail.setAddress3(user.getAddress3());
		userDetail.setPOBOX(user.getPOBOX());
		userDetail.setDOB(user.getDOB());
		userDetail.setEmirate(String.valueOf(user.getEmiratesCode()));
		
		
		if(userDetail.getTypeOfUser().equals("1")){
			userDetail.setApplicanttypeid(user.getApplicantTypeId());	
		}else{
			userDetail.setApplicanttypeid("0");
		}

		
		inputpayload.setUserDetails(userDetail);
		
		inputpayload.setAddress1(input.getAddress());
		inputpayload.setAddress2(input.getAddress());
		inputpayload.setDeliveredAT(input.getSubmissionTime());
		inputpayload.setDeliveredBY(user.getAccountid());
		inputpayload.setLanguageId(input.getLanguageId());
		inputpayload.setLatitude(input.getLatitude());
		inputpayload.setLongitude(input.getLongitude());
		inputpayload.setPostbox(user.getPOBOX());
		inputpayload.setReplacementReason(input.getReplacement());
		inputpayload.setWasteContainerReqId(wasteContainerCommand.getWasteCOntainoerId());
		inputpayload.setRequestID(wasteContainerCommand.getRequestNo().split("-")[3]);
		inputpayload.setRequestNo(wasteContainerCommand.getRequestNo());
		inputpayload.setServiceid(wasteContainerCommand.getServiceId());
		inputpayload.setUserAccountid(user.getAccountid());
		inputpayload.setServiceName("New Waste Conatiner");
		inputpayload.setServiceType(input.getServiceType());
		inputpayload.setSourceType(wasteContainerCommand.getSorceType());
		inputpayload.setStatus(wasteContainerCommand.getStatus());
		inputpayload.setSubmittedByUserId(user.getUsername());
		inputpayload.setUserComment("");
		inputpayload.setWorkflowId("1");
		
		AttachmentRecPayload fileArry[] = new AttachmentRecPayload[input.getFiles().size()];
		for (int i = 0; i < input.getFiles().size(); i++) {
			
			String filename = input.getFiles().get(i);
			logger.debug("File Name from Controller   |  " + filename);
			String did = filename.split("-")[0];
			String name = filename.split("-")[1];
			String ucmUrl = uCMCenterURLService.getWebCenterURLofFile(did);
			logger.debug("From WebCenter URL=" + ucmUrl);
			AttachmentRecPayload attachmentRecPayload = new AttachmentRecPayload();
			attachmentRecPayload.setContentid(did);
			attachmentRecPayload.setUrl(ucmUrl);
			attachmentRecPayload.setFileExpiryDate("10/20/2017");
			attachmentRecPayload.setFilename(name);
			//TODO: need to check with saourav what should be the correct IsMandatory value for resubmit.
			attachmentRecPayload.setIsMandatory("1");
			attachmentRecPayload.setFileType("5");
			fileArry[i] = attachmentRecPayload;

		}
		
		inputpayload.setAttachmentList(fileArry);
		
		try {
			OutputPayload ouput=stub.wasteContainerResubmit(inputpayload, userContext);
			
			if(ouput!=null){
				outputVO.setStatus(ouput.getStausFlag());
				outputVO.setMessage_EN(ouput.getStatus_EN());
				outputVO.setMessage_AR(ouput.getStatus_AR());
				logger.debug("Success  |  "+ouput.getStatus_EN()); 
			}else{
				outputVO.setStatus("Failed");
				logger.error("Failed  | stub.wasteContainerResubmit(inputpayload, userContext); return null"); 
			}
		} catch (RemoteException e) {
			outputVO.setStatus("Failed");
			logger.error("Failed  |" + e.getMessage());
			
		}
		
		return outputVO;
		
	}
	

}
