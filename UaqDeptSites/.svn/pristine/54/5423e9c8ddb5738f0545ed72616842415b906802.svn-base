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

public abstract class  GrantLandRequestServiceHandler extends ServiceHandler {

	protected enum AttachmentTypes {
		FamilyBook("familyBook", "20", "GLRDocType"),
		PropertyAccounting("propertyAccounting", "31", "GLRDocType"),
		SalaryCertificate("salaryCertificate", "30", "GLRDocType"),
		SpouseEmirate("spouseEmirate", "21", "GLRDocType"),
		RulersCourtAcceptance("rulersCourtAcceptance", "50", "GLRDocType"),
		SiteDocumentPlan("sitePlanDoc", "10", "GLRDocType");
		
		private String fieldName;
		private String docTypeId;
		private String docTypeName;
		
		private AttachmentTypes(String fieldName, String docTypeId, String docTypeName) {
			this.fieldName = fieldName;
			this.docTypeId = docTypeId;
			this.docTypeName = docTypeName;
		}
		
		public String getFieldName() {
			return fieldName;
		}
		
		public String getDocTypeId() {
			return docTypeId;
		}
		
		public String getDocTypeName () {
			return docTypeName;
		}
	}
	@Override
	public List<ServiceField> getServicePreparationFields(String phase, LPServiceLookUp lookupServiceEN_AR, String languageCode, Map<String, Object> initialParams) throws Exception {
		List<ServiceField> serviceFields = new ArrayList<ServiceField>();
		GrantLandReqViewSDO grantLandReq = null;
		SendBackInfo sendBackInfo = null;
		phase = (phase == null || phase.isEmpty()) ? null : phase;
		boolean isExcepProcess = (this instanceof GrantLandRequestExceptionServiceHandler);
		if (phase != null && (phase.equals("Resubmit") || phase.equals("Step1"))) {
			if (initialParams != null && initialParams.get("requestNumber") != null) {
				String requestNumber = initialParams.get("requestNumber").toString();
				grantLandReq = lookupServiceEN_AR.getGrantLandRequestByRequestNumber(requestNumber);
				sendBackInfo = WebServiceInvoker.getSendBackInfo(lookupServiceEN_AR.getApplicantRequestByRequestNumber(requestNumber).getRequestId().toString());
			}
		}else if (initialParams != null) {			
			if(isExcepProcess && initialParams.get("grantRequestNo") == null) {
				Map<String, String> params = new HashMap<String, String>();
				String accountId = ((AccountDetailsViewSDO) initialParams.get("accountDetails")).getUserDetailsView().get(0).getAccountId();
				params.put("accountId", accountId);
				
				ServiceField f1 = new ServiceField("grantRequestNo", "grantLand.reopen.requestNumber", FieldTypeEnum.Select, true);
				ServiceField f2 = new ServiceField(null, null, FieldTypeEnum.Text, false);
				f1.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.RejectedGrantLandRequests, languageCode, params));
				f2.setNextPhaseField(true);
				
				serviceFields.add(f1);
				serviceFields.add(f2);
				return serviceFields;
			} else if (isExcepProcess) {
				String requestNumber = initialParams.get("grantRequestNo").toString();
				grantLandReq = lookupServiceEN_AR.getGrantLandRequestByRequestNumber(requestNumber);
				sendBackInfo = WebServiceInvoker.getSendBackInfo(lookupServiceEN_AR.getApplicantRequestByRequestNumber(requestNumber).getRequestId().toString());
			}
		}
		if (initialParams == null) // just prevent the nullPpointerException
			initialParams = new HashMap<String, Object>();
		ServiceField f1 = new ServiceField("familyMembersNo", "grantLand.familyMembersNo", FieldTypeEnum.Number, !isExcepProcess);
		ServiceField f2 = new ServiceField("employer", "grantLand.employer", FieldTypeEnum.Text, !isExcepProcess);
		ServiceField f3 = new ServiceField("monthlySalary", "grantLand.monthlySalary", FieldTypeEnum.Number, !isExcepProcess);
		ServiceField f4 = new ServiceField("currentaddress", "grantLand.currentaddress", FieldTypeEnum.Text, !isExcepProcess);
		ServiceField f5 = new ServiceField("maritalStatus", "grantLand.maritalStatus", FieldTypeEnum.Select, !isExcepProcess);
		ServiceField f6 = new ServiceField(isExcepProcess? "" : AttachmentTypes.FamilyBook.fieldName, "grantLand.familyBook", FieldTypeEnum.File, !isExcepProcess && phase == null);
		ServiceField f7 = new ServiceField(isExcepProcess? "" : AttachmentTypes.PropertyAccounting.fieldName, "grantLand.propertyAccounting", FieldTypeEnum.File, !isExcepProcess && phase == null);
		ServiceField f8 = new ServiceField(isExcepProcess? "" : AttachmentTypes.SalaryCertificate.fieldName, "grantLand.salaryCertificate", FieldTypeEnum.File, !isExcepProcess && phase == null);
		ServiceField f9 = new ServiceField(isExcepProcess? "" : AttachmentTypes.SpouseEmirate.fieldName, "grantLand.spouseEmirateId", FieldTypeEnum.File, false);
		ServiceField f10 = new ServiceField(AttachmentTypes.RulersCourtAcceptance.fieldName, "grantLand.rulersCourtAcceptance", FieldTypeEnum.File, true);
		ServiceField f11 = new ServiceField("fromRequestNo", "requestno", grantLandReq != null? grantLandReq.getRequestNo().getValue().toString() : null, true);
		ServiceField f12 = new ServiceField("landLocation", "lpv.landLocation", FieldTypeEnum.Radio, true);
		ServiceField f13 = new ServiceField("sitePlanNo", "label.ps.siteplanNo", FieldTypeEnum.Text, false);
		ServiceField f14 = new ServiceField("sector", "label.ps.sector", FieldTypeEnum.Select, false);
		ServiceField f15 = new ServiceField("sectorBlock", "label.ps.block", FieldTypeEnum.Text, false);
		ServiceField f16 = new ServiceField("subSector", "label.ps.subSector", FieldTypeEnum.Text, false);
		ServiceField f17 = new ServiceField("sectorPlotNumber", "label.ps.plotNo", FieldTypeEnum.Text, false);
		ServiceField f18 = new ServiceField("area", "label.ps.area", FieldTypeEnum.Select, false);
		ServiceField f19 = new ServiceField("areaBlock", "label.ps.block", FieldTypeEnum.Text, false);
		ServiceField f20 = new ServiceField("subArea", "label.ps.subArea", FieldTypeEnum.Text, false);
		ServiceField f21 = new ServiceField("areaPlotNumber", "label.ps.plotNo", FieldTypeEnum.Number, false);
		ServiceField f22 = new ServiceField("landUsage", "label.ps.landUsage", FieldTypeEnum.Select, false);
		ServiceField f23 = new ServiceField(AttachmentTypes.SiteDocumentPlan.fieldName, "label.ps.sitePlanDoc", FieldTypeEnum.File, true);
		f1.setFieldValue(grantLandReq == null ? (String) initialParams.get(f1.getFieldName()) : grantLandReq.getFamilyMembersNo().getValue().toString());
		f2.setFieldValue(grantLandReq == null ? (String) initialParams.get(f2.getFieldName()) : grantLandReq.getEmployer().getValue().toString());
		f3.setFieldValue(grantLandReq == null ? (String) initialParams.get(f3.getFieldName()) : grantLandReq.getMonthlySalary().getValue().toString());
		f4.setFieldValue(grantLandReq == null ? (String) initialParams.get(f4.getFieldName()) : grantLandReq.getCurrentaddress().getValue().toString());
		
		// In case of readOnly/ disabled I need to display lookup String value
		// not Id value
		Map<String, String> maritalStatus = lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.MaritalStatus, languageCode, null);
		f5.setFieldLkValues(maritalStatus);
		f5.setFieldLkNeedLocalization(true);
		f6.setPanelHeader("service.label.attachments");
		f6.setDocType(AttachmentTypes.FamilyBook.docTypeId, AttachmentTypes.FamilyBook.docTypeName);		//f6.setDocType("20", "Family_book");
		f7.setDocType(AttachmentTypes.PropertyAccounting.docTypeId, AttachmentTypes.PropertyAccounting.docTypeName);
		f8.setDocType(AttachmentTypes.SalaryCertificate.docTypeId, AttachmentTypes.SalaryCertificate.docTypeName);
		f9.setDocType(AttachmentTypes.SpouseEmirate.docTypeId, AttachmentTypes.SpouseEmirate.docTypeName);		//f9.setDocType("21", "Spouse_Emirates_ID");		
		f10.setPanelHeader("label.lp.actions");
		f10.setDocType(AttachmentTypes.RulersCourtAcceptance.docTypeId, AttachmentTypes.RulersCourtAcceptance.docTypeName);		//TODO correct document type
		f12.setPanelHeader("grantLand.sitePlanDataHeader");
		f12.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.LandLocation, languageCode, null));
		f12.setFieldLkNeedLocalization(true);
		f12.setNotifierField(true);			
		f12.setFieldValue("2");
		f14.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.Sector, languageCode, null));
		f14.setNextFieldInSameRow(true);
		f15.setInSameRow(true);
		f16.setNextFieldInSameRow(true);
		f17.setInSameRow(true);
		f18.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.Area, languageCode, null));
		f18.setNextFieldInSameRow(true);
		f19.setInSameRow(true);
		f20.setNextFieldInSameRow(true);
		f21.setInSameRow(true);
		f22.setFieldLkValues(lookUpDataDropDown(lookupServiceEN_AR, LookupTypeEnum.LandUsage, languageCode, null));
		f23.setDocType(AttachmentTypes.SiteDocumentPlan.docTypeId, AttachmentTypes.SiteDocumentPlan.docTypeName);
		
		f14.setRequiredCondition("landLocation", "2");
		f15.setRequiredCondition("landLocation", "2");
		f16.setRequiredCondition("landLocation", "2");
		f17.setRequiredCondition("landLocation", "2");
		f18.setRequiredCondition("landLocation", "1");
		f19.setRequiredCondition("landLocation", "1");
		f20.setRequiredCondition("landLocation", "1");
		f21.setRequiredCondition("landLocation", "1");
		
		if(!isExcepProcess) {
		f5.setFieldValue(grantLandReq == null ? (String) initialParams.get(f5.getFieldName()) : grantLandReq.getMaritalStatus().getValue());
		f5.setNotifierField(true);
		f9.setRequiredCondition("maritalStatus", "2");
		} else {
			f1.setPanelHeader("label.lp.requestDetails");
			f1.setDisabled(true);			
			f2.setDisabled(true);
			f3.setDisabled(true);
			f4.setDisabled(true);
//			f5.setFieldValue(grantLandReq == null ? (String) initialParams.get(f5.getFieldName()) : maritalStatus.get(grantLandReq.getMaritalStatus().getValue()));
			f5.setFieldValue(grantLandReq == null ? (String) initialParams.get(f5.getFieldName()) : grantLandReq.getMaritalStatus().getValue());
			f5.setDisabled(true);
		}

		if(isExcepProcess) {
			serviceFields.add(f11);
		}
		serviceFields.add(f1);
		serviceFields.add(f2);
		serviceFields.add(f3);
		serviceFields.add(f4);
		serviceFields.add(f5);
		serviceFields.add(f6);
		serviceFields.add(f7);
		serviceFields.add(f8);
		if(!(isExcepProcess && grantLandReq!=null && !grantLandReq.getMaritalStatus().getValue().equals("2")))
			serviceFields.add(f9);
		if(isExcepProcess) {			
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
			serviceFields.add(f22);
			serviceFields.add(f23);
			serviceFields.add(f10);
		}
		if (sendBackInfo != null) {
			f6.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f6.getDocTypeId()));
			f7.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f7.getDocTypeId()));
			f8.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f8.getDocTypeId()));
			f9.setAttachmentValue(sendBackInfo.getLatestApplicantAttachment().get(f9.getDocTypeId()));
			if(!isExcepProcess)
			getReviewerResponse(sendBackInfo, serviceFields);
		}
		
		return serviceFields;
	}

	
	@Override
	public void issueServiceRequest(AccountDetailsViewSDO accountDetails, LPServiceLookUp lookupServiceEN_AR, String languageCode, Map<String, String> params) throws Exception {
		Map<String, Object> inputParams = new HashMap<String, Object>();
		String requestNumber = params.get("requestNumber");
		GrantLandReqViewSDO grantLandReq = lookupServiceEN_AR.getGrantLandRequestByRequestNumber(requestNumber);
		inputParams.putAll(params);
		inputParams.put("requestId", params.get("requestId"));
		inputParams.put("requestNumber", requestNumber);
		inputParams.put("serviceId", params.get("serviceId"));
		inputParams.put("accountDetails", accountDetails);
		inputParams.put("grantLandReq", grantLandReq);
		issueServiceRequest(inputParams);
		System.out.println("Process initiated");
	}

	

	protected abstract void issueServiceRequest(Map<String, Object> inputParams) throws Exception;

	
}