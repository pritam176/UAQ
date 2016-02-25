package com.uaq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uaq.db.si.model.common.AccountDetailsViewSDO;
import uaq.db.si.model.common.LpRealestateOfficeReqViewSDO;

import com.uaq.command.ServiceParamsCommand.FieldTypeEnum;
import com.uaq.command.ServiceParamsCommand.ServiceField;
import com.uaq.vo.SendBackInfo;
import com.uaq.service.WebServiceInvoker.RequestIdData;
import com.uaq.util.UCMUploader;
import com.uaq.util.UCMUploader.AttachmentInfo;

public abstract class RealEstateOfficeServiceHandler extends ServiceHandler {

	private static final int renewProcessServiceId = 407;

	@Override
	public List<ServiceField> getServicePreparationFields(String phase, LPServiceLookUp lookupServiceEN_AR, String languageCode, Map<String, Object> initialParams) throws Exception {
		List<ServiceField> serviceFields = new ArrayList<ServiceField>();
		LpRealestateOfficeReqViewSDO realEstateOffice = null;
		SendBackInfo sendBackInfo = null;
		int serviceId = 0;
		phase = (phase == null || phase.isEmpty()) ? null : phase;

		if (phase != null && (phase.equals("Resubmit") || phase.equals("Step1"))) {
			if (initialParams != null && initialParams.get("requestNumber") != null) {
				String requestNumber = initialParams.get("requestNumber").toString();
				String docTypeId = null;
				if (phase.equals("Step1")) {
					docTypeId = "155";
				}
				realEstateOffice = lookupServiceEN_AR.getRealEstateOfficeByRequestNumber(requestNumber);
				sendBackInfo = WebServiceInvoker.getSendBackInfo(realEstateOffice.getReqId().toString(), docTypeId);
			}
		}
		if (initialParams == null) // just prevent the nullPpointerException
			initialParams = new HashMap<String, Object>();
		else {
			serviceId = Integer.parseInt(initialParams.get("serviceId").toString());
		}
		ServiceField f1 = new ServiceField("managingDirectorName", "realEstate.managingDirectorName", FieldTypeEnum.Text, true);
		ServiceField f2 = new ServiceField("address", "realEstate.address", FieldTypeEnum.Text, true);
		ServiceField f3 = new ServiceField("policeClearanceCertificate", "realEstate.policeClearanceCertificate", FieldTypeEnum.File, phase == null);
		ServiceField f4 = new ServiceField("personalPicture", "realEstate.personalPicture", FieldTypeEnum.File, phase == null);
		ServiceField f5 = new ServiceField("realStateTradeLicense", "realEstate.realStateTradeLicense", FieldTypeEnum.File, phase == null);
		f1.setFieldValue(realEstateOffice == null ? (String) initialParams.get(f1.getFieldName()) : realEstateOffice.getMdName().getValue().toString());
		f2.setFieldValue(realEstateOffice == null ? (String) initialParams.get(f2.getFieldName()) : realEstateOffice.getMdAddress1().getValue().toString());
		f3.setPanelHeader("service.label.attachments");
		f3.setDocType("157", "policeClearanceCertificate");
		f4.setDocType("158", "personalImage");
		f5.setDocType("19", "tradeLicense");

		serviceFields.add(f1);
		serviceFields.add(f2);
		if (sendBackInfo != null) {
			f3.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f3.getDocTypeId()));
			f4.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f4.getDocTypeId()));
			if (phase != null && phase.equals("Step1")) {
				f1.setDisabled(true);
				f2.setDisabled(true);
				f5.setRequired(true);
				ServiceField f6 = new ServiceField("remarks", "realEstate.remarks", FieldTypeEnum.Text, false);
				f6.setPanelHeader("service.label.attachments");
				serviceFields.add(f6);
				serviceFields.add(f5);
			} else { // Resubmit
				serviceFields.add(f3);
				serviceFields.add(f4);
				f5.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f5.getDocTypeId()));
				if (serviceId == renewProcessServiceId) {
					serviceFields.add(f5);
				}
			}
			String attachDisplayKey = null;
			String requiredAttachDocId = null;
			if (phase.equals("Step1")) {
				attachDisplayKey = "realEstate.approvalDocument";
				requiredAttachDocId = "155";
			}
			getReviewerResponse(sendBackInfo, attachDisplayKey, requiredAttachDocId, serviceFields);
		} else {
			serviceFields.add(f3);
			serviceFields.add(f4);
			if (serviceId == renewProcessServiceId) {
				serviceFields.add(f5);
			}
		}
		return serviceFields;
	}

	@Override
	public String saveOrSubmitServiceRequestData(String phase, AccountDetailsViewSDO accountDetails, LPServiceLookUp lookupServiceEN_AR, Map<String, String> params, List<AttachmentInfo> attachmentInfos) {
		Map<String, Object> inputParams = new HashMap<String, Object>();

		if (attachmentInfos.size() > 0 && !new UCMUploader().genericUploadAttachment(attachmentInfos))
			return null;
		try {
			inputParams.put("serviceId", params.get("serviceId"));
			inputParams.put("serviceDept", params.get("serviceDept"));
			inputParams.put("phase", phase);
			inputParams.put("accountDetails", accountDetails);
			inputParams.put("managingDirectorName", params.get("managingDirectorName"));
			inputParams.put("address", params.get("address"));

			RequestIdData requestData;
			if (phase != null && (phase.equals("Resubmit") || phase.equals("Step1"))) {
				String requestNumber = params.get("requestNumber");
				String requestId = lookupServiceEN_AR.getRealEstateOfficeByRequestNumber(requestNumber).getReqId().toString();
				inputParams.put("requestNumber", requestNumber);
				inputParams.put("requestId", requestId);
				WebServiceInvoker.updateRealEstateRequest(inputParams);
				requestData = new RequestIdData(requestId, requestNumber);
			} else {
				requestData = WebServiceInvoker.saveRealEstateRequest(inputParams);
				inputParams.put("requestNumber", requestData.getRequestNumber());
				inputParams.put("requestId", requestData.getRequestId());
				inputParams.put("stepAction", "SUBMITTED");
				inputParams.put("stepName", null);
				inputParams.put("applicantId", accountDetails.getId());
				inputParams.put("applicantName", accountDetails.getFirstName());
				inputParams.put("amount", params.get("feeAmount"));
				WebServiceInvoker.sendSmsAndEMail(inputParams);
			}
			System.out.println("--------->  Request Id: " + requestData.getRequestId());
			for (AttachmentInfo attachmentInfo : attachmentInfos) {
				attachmentInfo.setRequestId(requestData.getRequestId());
				attachmentInfo.setServiceId(inputParams.get("serviceId").toString());
			}
			if (!new UCMUploader().genericSaveAttachmentToDB(attachmentInfos))
				return null;
			if (phase != null && (phase.equals("Resubmit") || phase.equals("Step1"))) {
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
		params.put("managingDirectorName", (String) inputParams.get("managingDirectorName"));
		params.put("Address", (String) inputParams.get("address"));
		return params;
	}

	@Override
	public String getResubmissionUser() {
		return "lpuser";
	}

	@Override
	public void issueServiceRequest(AccountDetailsViewSDO accountDetails, LPServiceLookUp lookupServiceEN_AR, String languageCode, Map<String, String> params) throws Exception {
		Map<String, Object> inputParams = new HashMap<String, Object>();
		String requestNumber = params.get("requestNumber");
		LpRealestateOfficeReqViewSDO realEstateOffice = lookupServiceEN_AR.getRealEstateOfficeByRequestNumber(requestNumber);
		inputParams.put("requestId", params.get("requestId"));
		inputParams.put("requestNumber", requestNumber);
		inputParams.put("serviceId", params.get("serviceId"));
		inputParams.put("accountDetails", accountDetails);
		inputParams.put("realEstateOffice", realEstateOffice);

		issueServiceRequest(inputParams);
		System.out.println("Process initiated");
	}

	public abstract void issueServiceRequest(Map<String, Object> inputParams) throws Exception;
}
