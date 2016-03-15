package com.uaq.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uaq.db.si.model.common.AccountDetailsViewSDO;
import uaq.db.si.model.common.LpProCardReqDetailsViewSDO;

import com.uaq.command.ServiceParamsCommand.FieldTypeEnum;
import com.uaq.command.ServiceParamsCommand.ServiceField;
import com.uaq.common.PropertiesUtil;
import com.uaq.service.ServiceHandler.LookupTypeEnum;
import com.uaq.vo.SendBackInfo;

public class ProCardRenewerServiceHandler extends ProCardServiceHandler {

	@Override
	public List<ServiceField> getServicePreparationFields(String phase, LPServiceLookUp lookupServiceEN_AR, String languageCode, Map<String, Object> initialParams) throws Exception {
		List<ServiceField> serviceFields = new ArrayList<ServiceField>();
		LpProCardReqDetailsViewSDO cardDetails = null;
		Date proIdExpDate = null;
		String nationalityId = null, nationality = null;
		SendBackInfo sendBackInfo = null;
		phase = (phase == null || phase.isEmpty()) ? null : phase;

		if (phase != null && phase.equals("Resubmit")) {
			if (initialParams != null && initialParams.get("requestNumber") != null) {
				String requestNumber = initialParams.get("requestNumber").toString();
				cardDetails = lookupServiceEN_AR.getProCardByRequestNumber(requestNumber);
				sendBackInfo = WebServiceInvoker.getSendBackInfo(cardDetails.getReqId().toString());
			}
		} else if (initialParams != null && initialParams.get("proIdNo") == null) {
			Map<String, String> params = new HashMap<String, String>();
			String accountId = ((AccountDetailsViewSDO) initialParams.get("accountDetails")).getUserDetailsView().get(0).getAccountId();
			params.put("accountId", accountId);
			ServiceField f1 = new ServiceField("proIdNo", "proCard.renew.proCardId", FieldTypeEnum.Text, true);
			ServiceField f2 = new ServiceField(null, null, FieldTypeEnum.Text, false);
			f2.setNextPhaseField(true);
			serviceFields.add(f1);
			serviceFields.add(f2);
			return serviceFields;
		} else if (initialParams != null) {
			// this is the second phase of the service
			String proIdNo = initialParams.get("proIdNo").toString();
			cardDetails = lookupServiceEN_AR.getProCardByProNumber(proIdNo);
			if (cardDetails == null)
				throw new Exception("error.proCardId.doesnotExist");
		}

		if (cardDetails != null) {
			proIdExpDate = cardDetails.getProIdExpDate().getValue().toGregorianCalendar().getTime();
			nationalityId = cardDetails.getProNationality().getValue().toString();
			nationality = lookUpDataValue(lookupServiceEN_AR, LookupTypeEnum.Nationality, languageCode, nationalityId);
		}
 
		ServiceField f1 = new ServiceField("nameOfPro", "proCard.NameOfPro", cardDetails != null ? cardDetails.getProName().getValue() : null, true);
		ServiceField f2 = new ServiceField("proIdNo", "proCard.ProIdNo", cardDetails != null ? cardDetails.getProIdNum().getValue() : null, true,20);
		ServiceField f3 = new ServiceField("proIdExpiryDate", "proCard.ProIdExpiryDate", FieldTypeEnum.Text, true);
		ServiceField f4 = new ServiceField("proNationality", "proCard.ProNationality", cardDetails != null ? nationality : null, true);
		ServiceField f5 = new ServiceField("identityOfPro", "proCard.IdentityOfPro", FieldTypeEnum.File, phase == null);
		ServiceField f6 = new ServiceField("proPhotograph", "proCard.ProPhotograph", FieldTypeEnum.File, phase == null);
		f3.setFieldValue(proIdExpDate != null ? displayDateFormat.format(proIdExpDate) : null);
		f4.setFieldType(FieldTypeEnum.Select);
		f4.setFieldValue(cardDetails != null ? nationalityId : null);
		f4.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.Nationality, languageCode, null));
		f5.setPanelHeader("service.label.attachments");
		f5.setDocType("16", "Identity_of_the_PRO");
		f6.setDocType("17", "PRO_Photograph");

		serviceFields.add(f1);
		serviceFields.add(f2);
		serviceFields.add(f3);
		serviceFields.add(f4);
		serviceFields.add(f5);
		serviceFields.add(f6);
		if (sendBackInfo != null) {
			f5.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f5.getDocTypeId()));
			f6.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f6.getDocTypeId()));
			getReviewerResponse(sendBackInfo, serviceFields);
		}
		return serviceFields;
	}

	@Override
	public void issueServiceRequest(Map<String, Object> inputParams) throws Exception {
		WebServiceInvoker.renewCardProcess(inputParams);
	}

	
	@Override
	public void proceedWithServiceAfterPayment(Map<String, String> params) throws Exception {
		WebServiceInvoker.proceedWithLpServiceAfterPayment(PropertiesUtil.getProperty("SOA_URL_ISSUE_NEW_PROCARD"), "serviceFeesMessage", "requestId", params.get("requestId"));
	}

	@Override
	public String getPhaseActivityName(String phase) {
		return "Resubmit Request Renew PRO Card";
	}
}
