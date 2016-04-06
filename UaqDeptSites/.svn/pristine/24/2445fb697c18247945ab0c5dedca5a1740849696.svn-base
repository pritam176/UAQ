package com.uaq.service;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import uaq.service.pws.model.UAQPWSServices;
import uaq.service.pws.model.UAQPWSServicesPortBindingStub;
import uaq.service.pws.model.UAQPWSServicesService;
import uaq.service.pws.model.UAQPWSServicesServiceLocator;
import WasteContainerRequest.AttachmentRecPayload;

import com.uaq.common.ApplicationConstants;
import com.uaq.common.ServiceNameConstant;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.UserDeatilVO;
import com.uaq.vo.WasteContainerRequestInputVO;
import com.uaq.vo.WasteContainerRequestOutputVO;

/**
 * Service class for Submit Waste Containor Detail
 * 
 * 
 * 
 * Jar Name-PWSDepartmentSubmit.jar
 * WSDL-http://94.57.252.234:7001/UAQPWSMiddlewareService
 * /UAQPWSServicesPort?WSDL
 * 
 * @author Pritam
 * 
 */

@Service
@Qualifier("pWSRequestService")
public class PWSRequestService {

	protected static UAQLogger logger = new UAQLogger(PWSRequestService.class);

	

	@Autowired
	private UCMCenterURLService uCMCenterURLService;

	private uaq.service.pws.model.UserContext uc = null;
	private UAQPWSServicesService service = null;
	private UAQPWSServices port = null;
	private UAQPWSServicesPortBindingStub stub = null;

	DateFormat df = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT);
	Calendar calender = null;

	public void createStub() {
		uc = new uaq.service.pws.model.UserContext();
		uc.setUsername(ApplicationConstants.WS_USERNAME);
		uc.setPassword(ApplicationConstants.WS_PASSWORD);

		try {
			service = (UAQPWSServicesService) new UAQPWSServicesServiceLocator();
			port = service.getUAQPWSServicesPort();
			stub = (UAQPWSServicesPortBindingStub) port;

		} catch (ServiceException e) {
			logger.error("WebService Error  |" + e.getMessage());
		}

	}

	public WasteContainerRequestOutputVO submitContainer(WasteContainerRequestInputVO input, UserDeatilVO userDeatilVO) {

		createStub();

		WasteContainerRequestOutputVO outputVO = new WasteContainerRequestOutputVO();
		WasteContainerRequest.Inputpayload payload = new WasteContainerRequest.Inputpayload();

		WasteContainerRequest.UserDetailsPayload userdDetails = new WasteContainerRequest.UserDetailsPayload();
		userdDetails.setUsername(userDeatilVO.getLoginUserName());
		userdDetails.setTypeOfUser(userDeatilVO.getTypeOfUser());
		userdDetails.setAccountid(userDeatilVO.getAccountid());
		userdDetails.setMobileNo(userDeatilVO.getMobileNo());
		userdDetails.setEmailID(userDeatilVO.getEmailID());
		userdDetails.setNationality(userDeatilVO.getNationality());
		userdDetails.setEmirate(String.valueOf(userDeatilVO.getEmiratesCode()));
		userdDetails.setFirstName(userDeatilVO.getFirstName());
		userdDetails.setEmiratesId(userDeatilVO.getEmiratesId());
		
		if(userDeatilVO.getTypeOfUser().equals("1")){
			userdDetails.setApplicanttypeid(userDeatilVO.getApplicantTypeId());	
		}else{
			userdDetails.setApplicanttypeid("0");
		}


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
			attachmentRecPayload.setFileExpiryDate("01/01/1800");
			attachmentRecPayload.setFilename(name);
			attachmentRecPayload.setIsMandatory("1");
			attachmentRecPayload.setFileType("5");
			fileArry[i] = attachmentRecPayload;

		}
		
		payload.setAddress1(input.getAddress());
		payload.setAddress2(input.getAddress());
		payload.setDeliveredAT(input.getSubmissionTime());
		payload.setDeliveredBY(userDeatilVO.getAccountid());
		payload.setLanguageId(input.getLanguageId());
		payload.setLatitude(input.getLatitude());
		payload.setLongitude(input.getLongitude());
		payload.setPostbox(userDeatilVO.getPOBOX());
		payload.setReplacementReason(input.getReplacement());
		payload.setRequestID("");
		payload.setRequestNo("");
		payload.setServiceid(ServiceNameConstant.NEW_WASTE_CONTAINER);
		payload.setUserAccountid(userDeatilVO.getAccountid());
		payload.setServiceName(ServiceNameConstant.NEW_WASTE_CONTAINER_NAME);
		payload.setServiceType(input.getServiceType());
		payload.setSourceType("1");
		payload.setStatus("1");
		payload.setSubmittedByUserId(userDeatilVO.getUsername());
		payload.setUserComment("");
		payload.setWasteContainerReqId("1");
		payload.setWorkflowId("1");
		payload.setAttachmentList(fileArry);
		payload.setUserDetails(userdDetails);
		// payload.setUserAccountid(userAccountid);
		payload.setWasteContainerReqId(userDeatilVO.getAccountid());

		WasteContainerRequest.OutputPayload output = null;
	
		try {
			output = stub.wasteContainerRequest(payload, uc);
		} catch (RemoteException e) {
			outputVO.setStatus("Failed");
			logger.error("WebService Input Error  |" + e.getMessage());
		}
		if (output != null) {
			logger.info(output.getStatus_EN() + " |" + output.getStausFlag());
			outputVO.setMessage_EN(output.getStatus_EN());
			outputVO.setMessage_AR(output.getStatus_AR());
			outputVO.setStatus(output.getStausFlag());
			outputVO.setStatusId(output.getInputFields().getStatus());
			outputVO.setRequestNo(output.getInputFields().getRequestNo());
		} else {
			outputVO.setStatus("Failed");
			logger.info("stub.createWasteContainer(payload, uc); return NULL  | FAiled");
		}

		return outputVO;
	}

	/* file upload for waste Container */

	/*
	 * private AttachmentRecPayload wasteContainergetURLofFIle(MultipartFile
	 * file, String id) { FileOutputVO files =
	 * fileUploadService.upLoadFile(file); String ucmUrl =
	 * uCMCenterURLService.getWebCenterURLofFile(files.getDid());
	 * AttachmentRecPayload fileArray = new AttachmentRecPayload();
	 * fileArray.setContentid(id); fileArray.setUrl(ucmUrl);
	 * fileArray.setFilename(file.getOriginalFilename());
	 * fileArray.setIsMandatory("1"); fileArray.setFileExpiryDate("10/20/2017");
	 * return fileArray; }
	 */

}
