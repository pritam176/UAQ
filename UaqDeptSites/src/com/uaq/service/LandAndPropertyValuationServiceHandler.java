package com.uaq.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uaq.db.si.model.common.AccountDetailsViewSDO;
import uaq.db.si.model.common.LpValuationViewSDO;

import com.uaq.command.ServiceParamsCommand.FieldTypeEnum;
import com.uaq.command.ServiceParamsCommand.ServiceField;
import com.uaq.vo.SendBackInfo;
import com.uaq.service.WebServiceInvoker.RequestIdData;
import com.uaq.util.UCMUploader;
import com.uaq.util.UCMUploader.AttachmentInfo;

public class LandAndPropertyValuationServiceHandler extends ServiceHandler {

	@Override
	public List<ServiceField> getServicePreparationFields(String phase, LPServiceLookUp lookupServiceEN_AR, String languageCode, Map<String, Object> initialParams) throws Exception {
		LpValuationViewSDO lpValuation = null;
		SendBackInfo sendBackInfo = null;
		String applicantPosition = null, landLocation = null, subArea = null, subSector = null, ownerName = null, ownerNationality = null, ownerId = null, familyBookNumber = null;
		phase = (phase == null || phase.isEmpty()) ? null : phase;
		if (phase != null && phase.equals("Resubmit")) {
			if (initialParams != null && initialParams.get("requestNumber") != null) {
				String requestNumber = initialParams.get("requestNumber").toString();
				lpValuation = lookupServiceEN_AR.getLPValudationRequestByRequestNumber(requestNumber);
				sendBackInfo = WebServiceInvoker.getSendBackInfo(lpValuation.getReqId().toString());
			}
		}
		if (initialParams == null) // just prevent the nullPpointerException
			initialParams = new HashMap<String, Object>();

		List<ServiceField> serviceFields = new ArrayList<ServiceField>();
		ServiceField f1 = new ServiceField("applicantPosition", "lpv.applicantPosition", FieldTypeEnum.Radio, true);
		ServiceField f2 = new ServiceField("landStatus", "lpv.landStatus", FieldTypeEnum.Select, true);
		ServiceField f3 = new ServiceField("landType", "lpv.landType", FieldTypeEnum.Select, true);
		ServiceField f4 = new ServiceField("ownerLandCategory", "lpv.landCategory", FieldTypeEnum.Select, false);
		ServiceField f5 = new ServiceField("hierLandCategory", "lpv.landCategory", FieldTypeEnum.Select, false);
		ServiceField f6 = new ServiceField("sitePlanNo", "lpv.sitePlanNo", FieldTypeEnum.Number, true);
		ServiceField f7 = new ServiceField("landLocation", "lpv.landLocation", FieldTypeEnum.Radio, true);
		ServiceField f8 = new ServiceField("sector", "lpv.sector", FieldTypeEnum.Select, false);
		ServiceField f9 = new ServiceField("subSector", "lpv.subSector", FieldTypeEnum.Text, false);
		ServiceField f10 = new ServiceField("sectorBlock", "lpv.sectorBlock", FieldTypeEnum.Text, false);
		ServiceField f11 = new ServiceField("sectorPlotNumber", "lpv.sectorPlotNumber", FieldTypeEnum.Number, false);
		ServiceField f12 = new ServiceField("area", "lpv.area", FieldTypeEnum.Select, false);
		ServiceField f13 = new ServiceField("subArea", "lpv.subArea", FieldTypeEnum.Text, false);
		ServiceField f14 = new ServiceField("areaBlock", "lpv.areaBlock", FieldTypeEnum.Text, false);
		ServiceField f15 = new ServiceField("areaPlotNumber", "lpv.areaPlotNumber", FieldTypeEnum.Number, false);
		ServiceField f16 = new ServiceField("ownerName", "lpv.ownerName", FieldTypeEnum.Text, false);
		ServiceField f17 = new ServiceField("ownerNationality", "lpv.ownerNationality", FieldTypeEnum.Select, false);
		ServiceField f18 = new ServiceField("ownerIdNumber", "lpv.ownerIdNumber", FieldTypeEnum.Text, false);
		ServiceField f19 = new ServiceField("familyBookNumber", "lpv.familyBookNumber", FieldTypeEnum.Text, false);

		ServiceField f20 = new ServiceField("ownershipCertificate", "lvp.ownershipCertificate", FieldTypeEnum.File, phase == null);
		ServiceField f21 = new ServiceField("sitePlanDocument", "lvp.sitePlanDocument", FieldTypeEnum.File, phase == null);
		ServiceField f22 = new ServiceField("identity", "lvp.identity", FieldTypeEnum.File, false);
		ServiceField f23 = new ServiceField("familyBook", "lvp.familyBook", FieldTypeEnum.File, false);
		ServiceField f24 = new ServiceField("ownerDeathCertificate", "lvp.ownerDeathCertificate", FieldTypeEnum.File, false);
		ServiceField f25 = new ServiceField("inheritanceInventory", "lvp.inheritanceInventory", FieldTypeEnum.File, false);
		ServiceField f26 = new ServiceField("heirAuthorization", "lvp.heirAuthorization", FieldTypeEnum.File, false);
		ServiceField f27 = new ServiceField("courtEvaluationRequestLe", "lvp.courtEvaluationRequestLetter", FieldTypeEnum.File, false);

		applicantPosition = lpValuation == null ? (String) initialParams.get(f1.getFieldName()) : lpValuation.getUserPositionId().getValue().toString();
		if (applicantPosition != null) {
			if (applicantPosition.equals("2")) {
				ownerName = lpValuation == null ? (String) initialParams.get(f16.getFieldName()) : lpValuation.getOwnerName().getValue().toString();
				ownerNationality = lpValuation == null ? (String) initialParams.get(f17.getFieldName()) : lpValuation.getOwnerNationality().getValue().toString();
				ownerId = lpValuation == null ? (String) initialParams.get(f18.getFieldName()) : lpValuation.getOwnerId().getValue().toString();
				familyBookNumber = lpValuation == null ? (String) initialParams.get(f19.getFieldName()) : lpValuation.getFamilyBookId().getValue().toString();
			}
		} else {
			applicantPosition = "1";
		}
		landLocation = lpValuation == null ? (String) initialParams.get(f7.getFieldName()) : lpValuation.getLocationType().getValue().toString();
		if (landLocation != null) {
			if (landLocation.equals("1")) {
				subArea = lpValuation == null ? (String) initialParams.get(f13.getFieldName()) : lpValuation.getSubArea().getValue().toString();
			} else {
				subSector = lpValuation == null ? (String) initialParams.get(f9.getFieldName()) : lpValuation.getSubSector().getValue().toString();
			}
		} else {
			landLocation = "1";
		}

		f1.setNotifierField(true);
		f1.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.ApplicantPosition, languageCode, null));
		f1.setFieldValue(applicantPosition);
		f1.setFieldLkNeedLocalization(true);
		f1.setDisabled(phase != null);
		f2.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.LandStatus, languageCode, null));
		f2.setFieldValue(lpValuation == null ? (String) initialParams.get(f2.getFieldName()) : lpValuation.getLandStatusId().getValue().toString());
		f2.setNextFieldInSameRow(true);
		f3.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.LandType, languageCode, null));
		f3.setFieldValue(lpValuation == null ? (String) initialParams.get(f3.getFieldName()) : lpValuation.getLandTypeId().getValue().toString());
		f3.setInSameRow(true);
		f4.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.LandCategory, languageCode, null));
		f4.setFieldValue(lpValuation == null ? (String) initialParams.get(f4.getFieldName()) : lpValuation.getLandCategory().getValue().toString());
		f5.setDisabled(true);
		f5.setFieldIdValue("7");
		f5.setFieldValue("lpv.landCategory.lk.valuation");
		f5.setFieldLkNeedLocalization(true);
		f6.setInfoMessage("lpv.sitePlanNo.info");
		f6.setFieldValue(lpValuation == null ? (String) initialParams.get(f6.getFieldName()) : lpValuation.getSiteplanId().getValue().toString());
		f7.setNotifierField(true);
		f7.setPanelHeader("lpv.label.location");
		f7.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.LandLocation, languageCode, null));
		f7.setFieldValue(landLocation);
		f7.setFieldLkNeedLocalization(true);
		f8.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.Sector, languageCode, null));
		f8.setFieldValue(lpValuation == null ? (String) initialParams.get(f8.getFieldName()) : lpValuation.getSector().getValue().toString());
		f8.setNextFieldInSameRow(true);
		f9.setFieldValue(subSector);
		f9.setInSameRow(true);
		f10.setFieldValue(lpValuation == null ? (String) initialParams.get(f10.getFieldName()) : lpValuation.getBlock().getValue().toString());
		f10.setNextFieldInSameRow(true);
		f11.setFieldValue(lpValuation == null ? (String) initialParams.get(f11.getFieldName()) : lpValuation.getPlotNumber().getValue().toString());
		f11.setInSameRow(true);
		f12.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.Area, languageCode, null));
		f12.setFieldValue(lpValuation == null ? (String) initialParams.get(f12.getFieldName()) : lpValuation.getArea().getValue().toString());
		f12.setNextFieldInSameRow(true);
		f13.setFieldValue(subArea);
		f13.setInSameRow(true);
		f14.setFieldValue(lpValuation == null ? (String) initialParams.get(f14.getFieldName()) : lpValuation.getBlock().getValue().toString());
		f14.setNextFieldInSameRow(true);
		f15.setFieldValue(lpValuation == null ? (String) initialParams.get(f15.getFieldName()) : lpValuation.getPlotNumber().getValue().toString());
		f15.setInSameRow(true);
		f16.setPanelHeader("lpv.label.owner");
		f16.setFieldValue(ownerName);
		f16.setNextFieldInSameRow(true);
		f17.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.Nationality, languageCode, null));
		f17.setFieldValue(ownerNationality);
		f17.setInSameRow(true);
		f18.setFieldValue(ownerId);
		f18.setNextFieldInSameRow(true);
		f19.setFieldValue(familyBookNumber);
		f19.setInSameRow(true);

		f20.setPanelHeader("service.label.attachments");
		f20.setDocType("101", "Ownership_Certificate");
		f21.setDocType("10", "Site_Plan_Document");
		f22.setDocType("102", "Identity");
		f23.setDocType("20", "Family_book");
		f24.setDocType("103", "Owner_Death_Certificate");
		f25.setDocType("104", "Inheritance_Inventory");
		f26.setDocType("105", "Heir_authorization");
		f27.setDocType("106", "Court_Evaluation");

		f4.setRequiredCondition("applicantPosition", "1");
		f5.setRequiredCondition("applicantPosition", "2");
		f8.setRequiredCondition("landLocation", "2");
		f9.setRequiredCondition("landLocation", "2");
		f10.setRequiredCondition("landLocation", "2");
		f11.setRequiredCondition("landLocation", "2");
		f12.setRequiredCondition("landLocation", "1");
		f13.setRequiredCondition("landLocation", "1");
		f14.setRequiredCondition("landLocation", "1");
		f15.setRequiredCondition("landLocation", "1");
		f16.setRequiredCondition("applicantPosition", "2");
		f17.setRequiredCondition("applicantPosition", "2");
		f18.setRequiredCondition("applicantPosition", "2");
		f19.setRequiredCondition("applicantPosition", "2");
//		if (phase == null) {
			f22.setRequiredCondition("applicantPosition", "2");
			f23.setRequiredCondition("applicantPosition", "2");
			f24.setRequiredCondition("applicantPosition", "2");
			f25.setRequiredCondition("applicantPosition", "2");
			f26.setRequiredCondition("applicantPosition", "2");
			f27.setRequiredCondition("applicantPosition", "2");
//		}
		serviceFields.add(f1);
		serviceFields.add(f2);
		serviceFields.add(f3);
		serviceFields.add(f4);
		serviceFields.add(f5);
		serviceFields.add(f6);
		serviceFields.add(f7);
		serviceFields.add(f8);
		serviceFields.add(f9);
		serviceFields.add(f10);
		serviceFields.add(f11);
		serviceFields.add(f12);
		serviceFields.add(f13);
		serviceFields.add(f14);
		serviceFields.add(f15);
		serviceFields.add(f16);
		serviceFields.add(f17);
		serviceFields.add(f18);
		serviceFields.add(f19);
		serviceFields.add(f20);
		serviceFields.add(f21);
//		if (phase != null && applicantPosition.equals("2")) {
			serviceFields.add(f22);
			serviceFields.add(f23);
			serviceFields.add(f24);
			serviceFields.add(f25);
			serviceFields.add(f26);
			serviceFields.add(f27);
//		}

		if (sendBackInfo != null) {
			f20.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f20.getDocTypeId()));
			f21.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f21.getDocTypeId()));
			f22.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f22.getDocTypeId()));
			f23.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f23.getDocTypeId()));
			f24.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f24.getDocTypeId()));
			f25.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f25.getDocTypeId()));
			f26.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f26.getDocTypeId()));
			f27.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f27.getDocTypeId()));
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
			inputParams.put("serviceId", params.get("serviceId"));
			inputParams.put("accountDetails", accountDetails);
			inputParams.putAll(params);
			SimpleDateFormat wsDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			inputParams.put("createdDate", wsDateFormatter.format(new Date()));

			RequestIdData requestData;
			if (phase != null && phase.equals("Resubmit")) {
				String requestNumber = params.get("requestNumber");
				String requestId = lookupServiceEN_AR.getLPValudationRequestByRequestNumber(requestNumber).getReqId().toString();
				inputParams.put("requestId", requestId);
				WebServiceInvoker.updateValuationRequest(inputParams);
				requestData = new RequestIdData(requestId, requestNumber);
			} else {
				requestData = WebServiceInvoker.saveValuationRequest(inputParams);
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
	public void issueServiceRequest(AccountDetailsViewSDO accountDetails, LPServiceLookUp lookupServiceEN_AR, String languageCode, Map<String, String> params) {
		Map<String, Object> inputParams = new HashMap<String, Object>();
		try {
			LpValuationViewSDO lpValuation = lookupServiceEN_AR.getLPValudationRequestByRequestNumber(params.get("requestNumber"));
			String applicantTpye, landCategory;
			applicantTpye = lpValuation.getUserPositionId().getValue().toString();
			landCategory = lpValuation.getLandCategory().getValue().toString();

			inputParams.put("accountDetails", accountDetails);
			inputParams.put("requestId", params.get("requestId"));
			inputParams.put("requestNumber", params.get("requestNumber"));
			inputParams.put("serviceId", 401);
			inputParams.put("applicantType", applicantTpye);
			inputParams.put("landCategory", landCategory);
			WebServiceInvoker.issueLandAndPropertyValuationProcess(inputParams);
			System.out.println("Process initiated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void proceedWithServiceAfterPayment(Map<String, String> params) throws Exception {
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put("requestId", params.get("requestId"));
		WebServiceInvoker.issueLandAndPropertyValuationProcessServiceFee(inputParams);
		System.out.println("Process proceeded");
	}

	@Override
	public String getResubmitActivityName() {
		return "Resubmit Request Land and Property Valuation";
	}

	@Override
	public String getResubmissionUser() {
		return "lpuser";
	}
}
