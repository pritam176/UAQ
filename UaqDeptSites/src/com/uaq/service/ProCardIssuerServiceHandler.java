package com.uaq.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uaq.db.si.model.common.LpProCardReqDetailsViewSDO;

import com.uaq.command.ServiceParamsCommand.FieldTypeEnum;
import com.uaq.command.ServiceParamsCommand.ServiceField;
import com.uaq.common.PropertiesUtil;
import com.uaq.vo.SendBackInfo;

/**
 * Phases: <br/>
 * 1- Resubmit :
 * \showServicePhase.html?serviceId=403&requestNumber=LP-003-16-000118
 * &servicePhase=Resubmit
 * 
 *
 */
public class ProCardIssuerServiceHandler extends ProCardServiceHandler {

	@Override
	public List<ServiceField> getServicePreparationFields(String phase, LPServiceLookUp lookupServiceEN_AR, String languageCode, Map<String, Object> initialParams) throws Exception {
		List<ServiceField> serviceFields = new ArrayList<ServiceField>();
		LpProCardReqDetailsViewSDO cardDetails = null;
		String nationality = null;
		Date proIdExpDate = null;
		SendBackInfo sendBackInfo = null;
		phase = (phase == null || phase.isEmpty()) ? null : phase;

		if (phase != null && phase.equals("Resubmit")) {
			if (initialParams != null && initialParams.get("requestNumber") != null) {
				String requestNumber = initialParams.get("requestNumber").toString();
				cardDetails = lookupServiceEN_AR.getProCardByRequestNumber(requestNumber);
				proIdExpDate = cardDetails.getProIdExpDate().getValue().toGregorianCalendar().getTime();
				nationality = cardDetails.getProNationality().getValue().toString();
				sendBackInfo = WebServiceInvoker.getSendBackInfo(cardDetails.getReqId().toString());
			}
		}
		if (initialParams == null) // just prevent the nullPpointerException
			initialParams = new HashMap<String, Object>();
		ServiceField f1 = new ServiceField("nameOfPro", "proCard.NameOfPro", FieldTypeEnum.Text, true);
		ServiceField f2 = new ServiceField("proIdNo", "proCard.ProIdNo", FieldTypeEnum.Text, true);
		ServiceField f3 = new ServiceField("proIdExpiryDate", "proCard.ProIdExpiryDate", FieldTypeEnum.Text, true);
		ServiceField f4 = new ServiceField("proNationality", "proCard.ProNationality", FieldTypeEnum.Select, true);
		ServiceField f5 = new ServiceField("identityOfPro", "proCard.IdentityOfPro", FieldTypeEnum.File, phase == null);
		ServiceField f6 = new ServiceField("proPhotograph", "proCard.ProPhotograph", FieldTypeEnum.File, phase == null);
		f1.setFieldValue(cardDetails == null ? (String) initialParams.get(f1.getFieldName()) : cardDetails.getProName().getValue().toString());
		f2.setFieldValue(cardDetails == null ? (String) initialParams.get(f2.getFieldName()) : cardDetails.getProIdNum().getValue().toString());
		f3.setFieldValue(cardDetails == null ? (String) initialParams.get(f3.getFieldName()) : displayDateFormat.format(proIdExpDate));
		f4.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.Nationality, languageCode, null));
		f4.setFieldValue(cardDetails == null ? (String) initialParams.get(f4.getFieldName()) : nationality);

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
		WebServiceInvoker.issueNewCardProcess(inputParams);
	}

	
	
	@Override
	public void proceedWithServiceAfterPayment(Map<String, String> params) throws Exception {
		WebServiceInvoker.proceedWithLpServiceAfterPayment(PropertiesUtil.getProperty("SOA_URL_ISSUE_PROCARD"), "serviceFeesMessage", "requestId", params.get("requestId"));
	}

	@Override
	public String getPhaseActivityName(String phase) {
		return "Resubmit Request Issue New PRO Card";
	}
}
