package com.uaq.service;

import org.springframework.stereotype.Service;

import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.soap.warpper.WebServiceWarpper;
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

	

	
	
	public PWSReSubmissonOuputVO findWasteContainerRequestView(ReSubmiisionInputVO inputVO) {
		
		


		com.uaq.proxies.getwastecontainerreqdetails_client_ep.types.OutputPayload outputPayload = null;
		com.uaq.proxies.getwastecontainerreqdetails_client_ep.types.InputPayload inputPayLoad = new com.uaq.proxies.getwastecontainerreqdetails_client_ep.types.InputPayload();
		inputPayLoad.setRequestId(inputVO.getAttributeValue().split("-")[3]);
		
		inputPayLoad.setRequestNo(inputVO.getAttributeValue());

		PWSReSubmissonOuputVO pwsReSubmissonOuputVO = new PWSReSubmissonOuputVO();
		try {
			
			outputPayload = new WebServiceWarpper().getWasteContainorDetail(inputPayLoad, PropertiesUtil.getProperty("SOA_URL_PWS_GETDETAIL"), PropertiesUtil.getProperty("SOA_USERNAME"),
					PropertiesUtil.getProperty("SOA_PASSWORD"));
		
		
		if(outputPayload!=null){
			
			pwsReSubmissonOuputVO.setStatus(outputPayload.getStatus());
			pwsReSubmissonOuputVO.setAddress2(outputPayload.getWasteContainerRec().getAddress2().getValue());
			pwsReSubmissonOuputVO.setAddress1(outputPayload.getWasteContainerRec().getAddress1().getValue());
			pwsReSubmissonOuputVO.setWasteContainerattachmentRecPayload(outputPayload.getAttachmentList()) ;
			pwsReSubmissonOuputVO.setUserComments(outputPayload.getWasteContainerRec().getOptionalcomment().getValue());
			pwsReSubmissonOuputVO.setSourceType(outputPayload.getWasteContainerRec().getSourceType());
			pwsReSubmissonOuputVO.setReplacementReason(outputPayload.getWasteContainerRec().getReplacementReason());
			pwsReSubmissonOuputVO.setRequestNo(outputPayload.getWasteContainerRec().getRequestNo());
			pwsReSubmissonOuputVO.setSubmittedByUserName(outputPayload.getWasteContainerRec().getSubmittedByUserName());
			pwsReSubmissonOuputVO.setLatitude(outputPayload.getWasteContainerRec().getLatitude().getValue());
			pwsReSubmissonOuputVO.setLongitude(outputPayload.getWasteContainerRec().getLongitude().getValue());
			//PostBox and ServiceId should not be bigdecimal, it should be int.
			//TODO change datatype to int from serviceside.
			pwsReSubmissonOuputVO.setPostbox(outputPayload.getWasteContainerRec().getPostbox().getValue());
			pwsReSubmissonOuputVO.setServiceId(outputPayload.getWasteContainerRec().getServiceId());
			pwsReSubmissonOuputVO.setId(outputPayload.getWasteContainerRec().getId());
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pwsReSubmissonOuputVO;
	}

}
