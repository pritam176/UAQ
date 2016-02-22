package com.uaq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uaq.db.si.model.common.AccountDetailsViewSDO;
import uaq.db.si.model.common.GrantLandReqViewSDO;

import com.uaq.command.ServiceParamsCommand.FieldTypeEnum;
import com.uaq.command.ServiceParamsCommand.ServiceField;
import com.uaq.common.PropertiesUtil;
import com.uaq.vo.SendBackInfo;
import com.uaq.service.WebServiceInvoker.RequestIdData;
import com.uaq.util.UCMUploader;
import com.uaq.util.UCMUploader.AttachmentInfo;

public class GrantLandRequestServiceHandler extends ServiceHandler {

	@Override
	public List<ServiceField> getServicePreparationFields(String phase, LPServiceLookUp lookupServiceEN_AR, String languageCode, Map<String, Object> initialParams) throws Exception {
		List<ServiceField> serviceFields = new ArrayList<ServiceField>();
		GrantLandReqViewSDO grantLandReq = null;
		SendBackInfo sendBackInfo = null;
		phase = (phase == null || phase.isEmpty()) ? null : phase;

		if (phase != null && (phase.equals("Resubmit") || phase.equals("Step1"))) {
			if (initialParams != null && initialParams.get("requestNumber") != null) {
				String requestNumber = initialParams.get("requestNumber").toString();
				grantLandReq = lookupServiceEN_AR.getGrantLandRequestByRequestNumber(requestNumber);
				sendBackInfo = WebServiceInvoker.getSendBackInfo(lookupServiceEN_AR.getApplicantRequestByRequestNumber(requestNumber).getRequestId().toString());
			}
		}
		if (initialParams == null) // just prevent the nullPpointerException
			initialParams = new HashMap<String, Object>();
		ServiceField f1 = new ServiceField("familyMembersNo", "grantLand.familyMembersNo", FieldTypeEnum.Number, true);
		ServiceField f2 = new ServiceField("employer", "grantLand.employer", FieldTypeEnum.Text, true);
		ServiceField f3 = new ServiceField("monthlySalary", "grantLand.monthlySalary", FieldTypeEnum.Number, true);
		ServiceField f4 = new ServiceField("currentaddress", "grantLand.currentaddress", FieldTypeEnum.Text, true);
		ServiceField f5 = new ServiceField("maritalStatus", "grantLand.maritalStatus", FieldTypeEnum.Select, true);
		ServiceField f6 = new ServiceField("familyBook", "grantLand.familyBook", FieldTypeEnum.File, phase == null);
		ServiceField f7 = new ServiceField("propertyAccounting", "grantLand.propertyAccounting", FieldTypeEnum.File, phase == null);
		ServiceField f8 = new ServiceField("salaryCertificate", "grantLand.salaryCertificate", FieldTypeEnum.File, phase == null);
		ServiceField f9 = new ServiceField("spouseEmirate", "grantLand.spouseEmirateId", FieldTypeEnum.File, false);
		f1.setFieldValue(grantLandReq == null ? (String) initialParams.get(f1.getFieldName()) : grantLandReq.getFamilyMembersNo().getValue().toString());
		f2.setFieldValue(grantLandReq == null ? (String) initialParams.get(f2.getFieldName()) : grantLandReq.getEmployer().getValue().toString());
		f3.setFieldValue(grantLandReq == null ? (String) initialParams.get(f3.getFieldName()) : grantLandReq.getMonthlySalary().getValue().toString());
		f4.setFieldValue(grantLandReq == null ? (String) initialParams.get(f4.getFieldName()) : grantLandReq.getCurrentaddress().getValue().toString());
		f5.setFieldValue(grantLandReq == null ? (String) initialParams.get(f5.getFieldName()) : grantLandReq.getMaritalStatus().getValue());
		f5.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.MaritalStatus, languageCode, null));
		f5.setFieldLkNeedLocalization(true);
		f5.setNotifierField(true);

		f6.setPanelHeader("service.label.attachments");
		f6.setDocType("20", "GLRDocType");
		f7.setDocType("30", "GLRDocType");
		f8.setDocType("31", "GLRDocType");
		f9.setRequiredCondition("maritalStatus", "2");
		f9.setDocType("21", "GLRDocType");

		serviceFields.add(f1);
		serviceFields.add(f2);
		serviceFields.add(f3);
		serviceFields.add(f4);
		serviceFields.add(f5);
		serviceFields.add(f6);
		serviceFields.add(f7);
		serviceFields.add(f8);
		serviceFields.add(f9);
		if (sendBackInfo != null) {
			f6.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f6.getDocTypeId()));
			f7.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f7.getDocTypeId()));
			f8.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f8.getDocTypeId()));
			f9.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f9.getDocTypeId()));
			getReviewerResponse(sendBackInfo, serviceFields);
		}
		return serviceFields;
	}

	@Override
	public String saveOrSubmitServiceRequestData(String phase, AccountDetailsViewSDO accountDetails, LPServiceLookUp lookupServiceEN_AR, Map<String, String> params, List<AttachmentInfo> attachmentInfos) {
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
				inputParams.put("stepName", null);
				WebServiceInvoker.sendSmsAndEMail(inputParams);
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
				Map<String, String> payloadParams = getPayloadParams(inputParams);
				resubmitService(inputParams, payloadParams);
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
		params.put("FamilyMembers", (String) inputParams.get("familyMembersCnt"));
		params.put("MaritalStatus", (String) inputParams.get("maritalStatus"));
		params.put("MonthlySalary", (String) inputParams.get("salary"));
		params.put("EmployerName", (String) inputParams.get("employer"));
		params.put("Address", (String) inputParams.get("curResAddress"));
		params.put("FamilyBookId", (String) inputParams.get("familyBook"));
		params.put("SpouseEmirateId", (String) inputParams.get("spouseEmiratesIdAttch"));
		params.put("PropertyAccountingId", (String) inputParams.get("propertyAccDoc"));
		params.put("SalaryCertificate", (String) inputParams.get("salaryCertificate"));
		return params;
	}

	@Override
	public void issueServiceRequest(AccountDetailsViewSDO accountDetails, LPServiceLookUp lookupServiceEN_AR, String languageCode, Map<String, String> params) throws Exception {
		Map<String, Object> inputParams = new HashMap<String, Object>();
		String requestNumber = params.get("requestNumber");
		GrantLandReqViewSDO grantLandReq = lookupServiceEN_AR.getGrantLandRequestByRequestNumber(requestNumber);
		inputParams.put("requestId", params.get("requestId"));
		inputParams.put("requestNumber", requestNumber);
		inputParams.put("serviceId", params.get("serviceId"));
		inputParams.put("accountDetails", accountDetails);
		inputParams.put("grantLandReq", grantLandReq);
		issueServiceRequest(inputParams);
		System.out.println("Process initiated");
	}

	private void issueServiceRequest(Map<String, Object> inputParams) throws Exception {
		WebServiceInvoker.issueGrantLandRequestProcess(inputParams);
	}

	@Override
	public void proceedWithServiceAfterPayment(Map<String, String> params) throws Exception {
		// TODO Check with Eid
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
