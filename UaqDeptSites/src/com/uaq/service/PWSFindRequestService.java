package com.uaq.service;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.springframework.stereotype.Service;

import uaq.service.pws.model.UAQPWSResubmitServicePortBindingStub;
import uaq.service.pws.model.UAQPWSResubmitService_PortType;
import uaq.service.pws.model.UAQPWSResubmitService_Service;
import uaq.service.pws.model.UAQPWSResubmitService_ServiceLocator;
import uaq.service.pws.model.UserContext;

import com.uaq.common.ApplicationConstants;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.PWSReSubmissonOuputVO;
import com.uaq.vo.ReSubmiisionInputVO;



/**
 * Service Class For PWSReSubmissionService
 * 
 * 
 * @author Pritam
 * 
 */

@Service("pWSFindRequestService")
public class PWSFindRequestService {

	protected static UAQLogger logger = new UAQLogger(PWSFindRequestService.class);

	private UAQPWSResubmitService_Service service = null;
	private UAQPWSResubmitService_PortType port = null;
	private UAQPWSResubmitServicePortBindingStub stub = null;
	UserContext userContext = null;

	/**
	 * Service Method findWasteContainerRequestView
	 * 
	 * @param ReSubmiisionInputVO
	 * @return PWSReSubmissonOuputVO
	 * @author Pritam
	 * 
	 */
	private void createStub() {
		userContext = new UserContext();
		userContext.setUsername(ApplicationConstants.WS_USERNAME);
		userContext.setPassword(ApplicationConstants.WS_PASSWORD);

		try {
			service = (UAQPWSResubmitService_Service) new UAQPWSResubmitService_ServiceLocator();
			port = service.getUAQPWSResubmitServicePort();
			stub = (UAQPWSResubmitServicePortBindingStub) port;
		} catch (ServiceException e) {
			logger.error("WebService Error  |" + e.getMessage());
			e.printStackTrace();

		}
	}
	
	public PWSReSubmissonOuputVO findWasteContainerRequestView(ReSubmiisionInputVO inputVO) {
		
		createStub();


		GetWasteContainerReqDetails.OutputPayload outputPayload = null;
		GetWasteContainerReqDetails.InputPayload inputPayLoad = new GetWasteContainerReqDetails.InputPayload();
		inputPayLoad.setRequestId(inputVO.getAttributeValue().split("-")[3]);
		
		inputPayLoad.setRequestNo(inputVO.getAttributeValue());

		PWSReSubmissonOuputVO pwsReSubmissonOuputVO = new PWSReSubmissonOuputVO();
		try {
			
			outputPayload = stub.getAllWasteContainerDetails(inputPayLoad, userContext);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if(outputPayload!=null){
			
			pwsReSubmissonOuputVO.setStatus(outputPayload.getStatus());
			pwsReSubmissonOuputVO.setAddress2(outputPayload.getWasteContainerRec().getAddress2());
			pwsReSubmissonOuputVO.setAddress1(outputPayload.getWasteContainerRec().getAddress1());
			pwsReSubmissonOuputVO.setWasteContainerattachmentRecPayload(outputPayload.getAttachmentList()) ;
			pwsReSubmissonOuputVO.setUserComments(outputPayload.getWasteContainerRec().getOptionalcomment());
			pwsReSubmissonOuputVO.setSourceType(outputPayload.getWasteContainerRec().getSourceType());
			pwsReSubmissonOuputVO.setReplacementReason(outputPayload.getWasteContainerRec().getReplacementReason());
			pwsReSubmissonOuputVO.setRequestNo(outputPayload.getWasteContainerRec().getRequestNo());
			pwsReSubmissonOuputVO.setSubmittedByUserName(outputPayload.getWasteContainerRec().getSubmittedByUserName());
			pwsReSubmissonOuputVO.setLatitude(outputPayload.getWasteContainerRec().getLatitude());
			pwsReSubmissonOuputVO.setLongitude(outputPayload.getWasteContainerRec().getLongitude());
			//PostBox and ServiceId should not be bigdecimal, it should be int.
			//TODO change datatype to int from serviceside.
			pwsReSubmissonOuputVO.setPostbox(outputPayload.getWasteContainerRec().getPostbox());
			pwsReSubmissonOuputVO.setServiceId(outputPayload.getWasteContainerRec().getServiceId());
			pwsReSubmissonOuputVO.setId(outputPayload.getWasteContainerRec().getId());
		}
		return pwsReSubmissonOuputVO;
	}

}
