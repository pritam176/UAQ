package com.uaq.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uaq.db.si.model.common.AccountDetailsViewSDO;

import com.uaq.service.WebServiceInvoker.RequestIdData;
import com.uaq.util.UCMUploader;
import com.uaq.util.UCMUploader.AttachmentInfo;

public abstract class ProCardServiceHandler extends ServiceHandler {

	@Override
	public String saveOrSubmitServiceRequestData(String phase, AccountDetailsViewSDO accountDetails, LPServiceLookUp lookupServiceEN_AR, Map<String, String> params, List<AttachmentInfo> attachmentInfos) {
		Map<String, Object> inputParams = new HashMap<String, Object>();

		if (attachmentInfos.size() > 0 && !new UCMUploader().genericUploadAttachment(attachmentInfos))
			return null;
		try {
			inputParams.put("serviceId", params.get("serviceId"));
			inputParams.put("accountDetails", accountDetails);
			inputParams.put("nameOfPro", params.get("nameOfPro"));
			inputParams.put("proIdNo", params.get("proIdNo"));
			inputParams.put("proNationality", params.get("proNationality"));
			Date proIdExpiryDate = new SimpleDateFormat("yyyy-MM-dd").parse(params.get("proIdExpiryDate"));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			inputParams.put("proIdExpiryDate", formatter.format(proIdExpiryDate));

			RequestIdData requestData;
			if (phase != null && phase.equals("Resubmit")) {
				String requestNumber = params.get("requestNumber");
				String requestId = lookupServiceEN_AR.getProCardByRequestNumber(requestNumber).getReqId().toString();
				inputParams.put("requestNumber", requestNumber);
				inputParams.put("requestId", requestId);
				WebServiceInvoker.updateCardRequest(inputParams);
				requestData = new RequestIdData(requestId, requestNumber);
			} else {
				requestData = WebServiceInvoker.saveCardRequest(inputParams);
				inputParams.put("requestNumber", requestData.getRequestNumber());
				inputParams.put("requestId", requestData.getRequestId());
				WebServiceInvoker.sendSmsAndEMail(inputParams);
			}
			System.out.println("--------->  Request Id: " + requestData.getRequestId());
			for (AttachmentInfo attachmentInfo : attachmentInfos) {
				attachmentInfo.setRequestId(requestData.getRequestId());
				attachmentInfo.setServiceId(inputParams.get("serviceId").toString());
			}
			if (!new UCMUploader().genericSaveAttachmentToDB(attachmentInfos))
				return null;
			if (phase.equals("Resubmit")) {
				resubmitService(inputParams);
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

	@Override
	public void proceedWithServiceAfterPayment(Map<String, String> params) throws Exception {
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put("requestId", params.get("requestId"));
		String serviceId = params.get("serviceId").toString();
		if(serviceId.equals("403"))
			WebServiceInvoker.issueNewProCardServiceFee(inputParams);
		else if(serviceId.equals("404"))
			WebServiceInvoker.issueNewProCardServiceFee(inputParams);
		System.out.println("Process proceeded");
	}
}
