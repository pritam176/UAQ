package com.uaq.service;

import static com.uaq.common.ApplicationConstants.SPRING_REDIRECT;
import static com.uaq.common.ApplicationConstants.UAQ_URL;
import static com.uaq.common.ApplicationConstants.URL_SEPARATOR;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uaq.db.si.model.common.AccountDetailsViewSDO;
import uaq.db.si.model.common.LpProCardReqDetailsViewSDO;

import com.uaq.common.PropertiesUtil;
import com.uaq.common.UAQURLConstant;
import com.uaq.service.WebServiceInvoker.RequestIdData;
import com.uaq.util.StringUtil;
import com.uaq.util.UCMUploader;
import com.uaq.util.UCMUploader.AttachmentInfo;

public abstract class ProCardServiceHandler extends ServiceHandler {
 
	@Override
	public String saveOrSubmitServiceRequestData(String phase, AccountDetailsViewSDO accountDetails, LPServiceLookUp lookupServiceEN_AR, Map<String, String> params, List<AttachmentInfo> attachmentInfos) throws Exception {
		Map<String, Object> inputParams = new HashMap<String, Object>();

		if(StringUtil.isEmpty(phase)&& params.get("serviceId").equals("403")){
			LpProCardReqDetailsViewSDO cardDetails = lookupServiceEN_AR.getProCardByProNumber(params.get("proIdNo"));
			if(cardDetails!=null){
				throw new Exception("error.proCardId.exist");
			}
		}
		
		if (attachmentInfos.size() > 0 && !new UCMUploader().genericUploadAttachment(attachmentInfos))
			return null;
		
		try {
			inputParams.put("serviceId", params.get("serviceId"));
			inputParams.put("serviceDept", params.get("serviceDept"));
			inputParams.put("accountDetails", accountDetails);
			inputParams.put("nameOfPro", params.get("nameOfPro"));
			inputParams.put("proIdNo", params.get("proIdNo"));
			inputParams.put("proNationality", params.get("proNationality"));
			Date proIdExpiryDate = new SimpleDateFormat("MM/dd/yyyy").parse(params.get("proIdExpiryDate"));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			inputParams.put("proIdExpiryDate", formatter.format(proIdExpiryDate));

			RequestIdData requestData;
			String requestNumber = params.get("requestNumber");
			if (phase != null && phase.equals("Resubmit")) {
				
				String requestId = lookupServiceEN_AR.getProCardByRequestNumber(requestNumber).getReqId().toString();
				inputParams.put("requestNumber", requestNumber);
				inputParams.put("requestId", requestId);
				WebServiceInvoker.updateCardRequest(inputParams);
				requestData = new RequestIdData(requestId, requestNumber);
			} else {
				
				requestData = WebServiceInvoker.saveCardRequest(inputParams);
				inputParams.put("requestNumber", requestData.getRequestNumber());
				inputParams.put("requestId", requestData.getRequestId());
				inputParams.put("stepAction", "SUBMITTED");
				inputParams.put("stepName", "BEFORE_APP_FEES");
				inputParams.put("applicantId", accountDetails.getId());
				inputParams.put("applicantName", accountDetails.getFirstName());
				inputParams.put("amount", params.get("feeAmount"));
				inputParams.put("status", "1");
				WebServiceInvoker.sendSmsAndEMail(inputParams);
				String serviceName = "Issue New Card PRO Request";
				if("404".equals(params.get("serviceId")))
					serviceName = "Renew Card PRO Request";
//				new ReportsService().generateRequestReport(params.get("serviceId"), requestData.getRequestId(), requestData.getRequestNumber(), accountDetails.getUserDetailsView().get(0).getUserName(), accountDetails.getId(),serviceName);
			}
			System.out.println("--------->  Request Id: " + requestData.getRequestId());
			for (AttachmentInfo attachmentInfo : attachmentInfos) {
				attachmentInfo.setRequestId(requestData.getRequestId());
				attachmentInfo.setServiceId(inputParams.get("serviceId").toString());
			}
			if (!new UCMUploader().genericSaveAttachmentToDB(attachmentInfos))
				return null;
			if (phase.equals("Resubmit")) {
				inputParams.put("phase", phase);
				resubmitService(inputParams);
				inputParams.put("requestNumber", requestData.getRequestNumber());
				inputParams.put("requestId", requestData.getRequestId());
				inputParams.put("stepAction", "RESUBMITTED");
				inputParams.put("stepName", "RESUBMITED");
				inputParams.put("applicantId", accountDetails.getId());
				inputParams.put("applicantName", accountDetails.getFirstName());
				inputParams.put("amount", params.get("feeAmount"));
				inputParams.put("status", "15");
				WebServiceInvoker.sendSmsAndEMail(inputParams);
			}
			System.out.println("Request Saved");
			return requestData.getRequestNumber();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getResubmissionUser() {
		return "lpuser";
	}

	@Override
	public void issueServiceRequest(AccountDetailsViewSDO accountDetails, LPServiceLookUp lookupServiceEN_AR, String languageCode, Map<String, String> params) throws Exception {
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put("requestId", params.get("requestId"));
		inputParams.put("requestNumber", params.get("requestNumber"));
		inputParams.put("serviceId", params.get("serviceId"));
		inputParams.put("accountDetails", accountDetails);
		issueServiceRequest(inputParams);
		System.out.println("Process initiated");
	}

	public abstract void issueServiceRequest(Map<String, Object> inputParams) throws Exception;

//	@Override
//	public void proceedWithServiceAfterPayment(Map<String, String> params) throws Exception {
//		Map<String, Object> inputParams = new HashMap<String, Object>();
//		inputParams.put("requestId", params.get("requestId"));
//		String serviceId = params.get("serviceId").toString();
//		if(serviceId.equals("403"))
//			WebServiceInvoker.issueNewProCardServiceFee(inputParams);
//		else if(serviceId.equals("404"))
//			WebServiceInvoker.issueNewProCardServiceFee(inputParams);
//		System.out.println("Process proceeded");
//	}
}
