package com.uaq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uaq.db.si.model.common.AccountDetailsViewSDO;
import uaq.db.si.model.common.GrantLandReqViewSDO;

import com.uaq.common.PropertiesUtil;
import com.uaq.service.WebServiceInvoker.RequestIdData;
import com.uaq.util.UCMUploader;
import com.uaq.util.UCMUploader.AttachmentInfo;

public class GrantLandRequestIssuerServiceHandler extends GrantLandRequestServiceHandler {
	
	@Override
	public String saveOrSubmitServiceRequestData(String phase, AccountDetailsViewSDO accountDetails, LPServiceLookUp lookupServiceEN_AR, Map<String, String> params, List<AttachmentInfo> attachmentInfos) throws Exception {
		Map<String, Object> inputParams = new HashMap<String, Object>();

		if (attachmentInfos.size() > 0 && !new UCMUploader().genericUploadAttachment(attachmentInfos))
			return null;
		try {
			inputParams.putAll(params);
			inputParams.put("serviceId", params.get("serviceId"));
			inputParams.put("serviceDept", params.get("serviceDept"));
			inputParams.put("phase", phase);
			inputParams.put("accountDetails", accountDetails);

			RequestIdData requestData;
			if (phase != null && phase.equals("Resubmit")) {
				String requestNumber = params.get("requestNumber");
				String requestId = lookupServiceEN_AR.getApplicantRequestByRequestNumber(requestNumber).getRequestId().toString();
				inputParams.put("requestNumber", requestNumber);
				inputParams.put("requestId", requestId);
				WebServiceInvoker.updateGrantLandRequest(inputParams);
				requestData = new RequestIdData(requestId, requestNumber);
			} else {
				requestData = WebServiceInvoker.saveGrantLandRequest(inputParams);
				inputParams.put("requestNumber", requestData.getRequestNumber());
				inputParams.put("requestId", requestData.getRequestId());
				inputParams.put("stepAction", "SUBMITTED");
				inputParams.put("stepName", "BEFORE_APP_FEES");
				inputParams.put("applicantId", accountDetails.getId());
				inputParams.put("applicantName", accountDetails.getFirstName());
				inputParams.put("amount", params.get("feeAmount"));
				inputParams.put("status", "1");
				WebServiceInvoker.sendSmsAndEMail(inputParams);
				String serviceName = "Grant Land Request";
				new ReportsService().generateRequestReport(params.get("serviceId"), requestData.getRequestId(), requestData.getRequestNumber(), accountDetails.getUserDetailsView().get(0).getUserName(), accountDetails.getId(),serviceName);
			}
			System.out.println("--------->  Request Id: " + requestData.getRequestId());
			for (AttachmentInfo attachmentInfo : attachmentInfos) {
				attachmentInfo.setRequestId(requestData.getRequestId());
				attachmentInfo.setServiceId(inputParams.get("serviceId").toString());
			}
			if (!new UCMUploader().genericSaveAttachmentToDB(attachmentInfos))
				return null;
			else {				
				for (AttachmentInfo attachmentInfo : attachmentInfos) {
					inputParams.put(attachmentInfo.getFieldName() + "Id", attachmentInfo.getDocInfo().get("DID"));
					inputParams.put(attachmentInfo.getFieldName() + "DocId", attachmentInfo.getAttachmentId());
				}
				WebServiceInvoker.updateGrantLandRequestWithAttachments(inputParams);
			}
			
			if (phase != null && phase.equals("Resubmit")) {
				inputParams.put("phase", phase);
				inputParams.put("grantLandReq", lookupServiceEN_AR.getGrantLandRequestByRequestNumber(params.get("requestNumber")));
				Map<String, String> payloadParams = getPayloadParams(inputParams);
				resubmitService(inputParams, payloadParams);
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
	
	private Map<String, String> getPayloadParams(Map<String, Object> inputParams) {
		Map<String, String> params = new HashMap<String, String>();
		GrantLandReqViewSDO grantLandReq = (GrantLandReqViewSDO)inputParams.get("grantLandReq");
		
		params.put("FamilyMembers", (String)inputParams.get("familyMembersNo"));
		params.put("MaritalStatus", (String)inputParams.get("maritalStatus"));
		params.put("MonthlySalary", (String)inputParams.get("monthlySalary"));
		params.put("EmployerName", (String)inputParams.get("employer"));
		params.put("Address", (String)inputParams.get("currentaddress"));
		params.put("FamilyBookId", grantLandReq.getFamilyBookId().getValue());
		params.put("SpouseEmirateId", grantLandReq.getSpousesEmiratesId().getValue());
		params.put("PropertyAccountingId", grantLandReq.getProprtyAccountingId().getValue());
		params.put("SalaryCertificate", grantLandReq.getSalaryCertificateId().getValue());
		return params;
	}

	@Override
	protected void issueServiceRequest(Map<String, Object> inputParams) throws Exception {
		WebServiceInvoker.issueGrantLandRequestProcess(inputParams);
	}

	@Override
	public void proceedWithServiceAfterPayment(Map<String, String> params) throws Exception {
		WebServiceInvoker.proceedWithLpServiceAfterPayment(PropertiesUtil.getProperty("SOA_URL_GRANTLAND_REQ"),
		 "catchPaymentEvent", "requestNo", params.get("requestNumber"));
	}

	@Override
	public String getResubmissionUser() {
		return "psuser";
	}

	@Override
	public String getResubmissionAction() {
		return "RESUBMIT";
	}

	@Override
	public String getPhaseActivityName(String phase) {
		return "Resubmit Request";
	}
}
