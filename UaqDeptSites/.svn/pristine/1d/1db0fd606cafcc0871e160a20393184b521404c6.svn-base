package com.uaq.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import uaq.db.si.model.common.AccountDetailsViewSDO;

import com.tacme.uaq.TaskQueryServicePortClient;
import com.tacme.uaq.TaskServicePortClient;
import com.uaq.command.ServiceParamsCommand.FieldTypeEnum;
import com.uaq.command.ServiceParamsCommand.ServiceField;
import com.uaq.common.ApplicationConstants;
import com.uaq.util.UCMUploader.AttachmentInfo;
import com.uaq.vo.SendBackInfo;

public abstract class ServiceHandler {

	private Logger logger = Logger.getLogger(ServiceHandler.class);
	protected SimpleDateFormat displayDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static enum LookupTypeEnum {
		Nationality,
		Renewable_Cards,
		ApplicantPosition,
		LandStatus,
		LandType,
		LandCategory,
		LandLocation,
		Sector,
		Area, 
	}

	protected Map<String, String> lookUpDataDropDown(LPServiceLookUp lPService, LookupTypeEnum lookupType, String languageCode, Map<String, String> params) throws Exception {
		if (lPService == null)
			return new HashMap<String, String>();
		Map<String, Map<String, String>> lookupList;
		try {
			switch (lookupType) {
			case Nationality:
				lookupList = lPService.getNationalityListAR_EN();
				break;
			case Renewable_Cards:
				lookupList = lPService.getRenewableCardRequestListAR_EN(params.get("accountId"));
				break;
			case LandStatus:
				lookupList = lPService.getLandStatusListAR_EN();
				break;
			case LandType:
				lookupList = lPService.getLandTypeListAR_EN();
				break;
			case LandCategory:
				lookupList = lPService.getOwnerLandCatergoryListAR_EN();
				break;
			case Sector:
				lookupList = lPService.getSectorListAR_EN();
				break;
			case Area:
				lookupList = lPService.getAreaListAR_EN();
				break;
			case ApplicantPosition:
				lookupList = new HashMap<String, Map<String, String>>();
				Map<String, String> lookupValuesMap = new HashMap<String, String>();
				lookupValuesMap.put("1", "lpv.applicantPosition.lk.owner");
				lookupValuesMap.put("2", "lpv.applicantPosition.lk.hier");
				lookupList.put("All", lookupValuesMap);
				break;
			case LandLocation:
				lookupList = new HashMap<String, Map<String, String>>();
				lookupValuesMap = new HashMap<String, String>();
				lookupValuesMap.put("1", "lpv.landLocation.lk.area");
				lookupValuesMap.put("2", "lpv.landLocation.lk.sector");
				lookupList.put("All", lookupValuesMap);
				break;
			default:
				throw new Exception("Lookup type \"" + lookupType + "\" not handled !!!");
			}
			Map<String, String> lookupListMap;
			if (lookupList.size() > 1)
				lookupListMap = lookupList.get(languageCode);
			else
				lookupListMap = lookupList.values().iterator().next();
			return lookupListMap;
		} catch (Exception e) {
			logger.error("Error getting Value from Look Up EService " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	protected String lookUpDataValue(LPServiceLookUp lPService, LookupTypeEnum lookupType, String languageCode, String lookupValueCode) throws Exception {
		if (lPService == null || lookupValueCode == null || lookupValueCode.isEmpty())
			return "";
		try {
			switch (lookupType) {
			case Nationality:
				return lPService.getNationalityValueAR_EN(languageCode, lookupValueCode);
			case LandStatus:
				return lPService.getLandStatusValueAR_EN(languageCode, lookupValueCode);
			case LandType:
				return lPService.getLandTypeValueAR_EN(languageCode, lookupValueCode);
			case Sector:
				return lPService.getSectorValueAR_EN(languageCode, lookupValueCode);
			case Area:
				return lPService.getAreaValueAR_EN(languageCode, lookupValueCode);
			case ApplicantPosition:
				if (lookupValueCode.equals("1"))
					return "lpv.applicantPosition.lk.owner";
				else
					return "lpv.applicantPosition.lk.hier";
			case LandLocation:
				if (lookupValueCode.equals("1"))
					return "lpv.landLocation.lk.area";
				else
					return "lpv.landLocation.lk.sector";
			default:
				throw new Exception("Lookup type \"" + lookupType + "\" value not handled !!!");
			}
		} catch (Exception e) {
			logger.error("Error getting Value from Look Up EService " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	protected void getReviewerResponse(SendBackInfo sendBackInfo, List<ServiceField> serviceFields) {
		if (sendBackInfo != null) {
			ServiceField f7 = new ServiceField("", "service.resubmit.ReviewerComment", FieldTypeEnum.Text, false);
			ServiceField f8 = new ServiceField("", "service.resubmit.ReviewerAttachment", FieldTypeEnum.File, false);
			f7.setPanelHeader("service.resubmit.reviewerResponse");
			f7.setDisabled(true);
			f7.setFieldValue(sendBackInfo.getReviewComment());
			f8.setDisabled(true);
			f8.setAttachmentValue(sendBackInfo.getReviewAttachment());
			serviceFields.add(f7);
			serviceFields.add(f8);
		}
	}

	public void resubmitService(Map<String, Object> inputParams) {
		String requestNumber = inputParams.get("requestNumber").toString();
		// String loggedInUser =
		// ((AccountDetailsViewSDO)inputParams.get("accountDetails")).getUserDetailsView().get(0).getLoginusername().getValue().toString();
		String loggedInUser = getResubmissionUser();
		Map<String, String> resubmittionTasks = new TaskQueryServicePortClient().getUserTasks(ApplicationConstants.WS_USERNAME, ApplicationConstants.WS_PASSWORD, loggedInUser, getResubmitActivityName());
		String taskId = resubmittionTasks.get(requestNumber);
		new TaskServicePortClient().updateTaskOutcome(ApplicationConstants.WS_USERNAME, ApplicationConstants.WS_PASSWORD, loggedInUser, taskId);
	}

	public abstract List<ServiceField> getServicePreparationFields(String phase, LPServiceLookUp lPServiceLookUp, String languageCode, Map<String, Object> initialParams) throws Exception;

	public abstract String saveOrSubmitServiceRequestData(String phase, AccountDetailsViewSDO accountDetails, LPServiceLookUp lPServiceLookUp, Map<String, String> params, List<AttachmentInfo> attachmentInfos) throws Exception;

	public abstract void issueServiceRequest(AccountDetailsViewSDO accountDetails, LPServiceLookUp lPServiceLookUp, String languageCode, Map<String, String> params) throws Exception;

	public abstract void proceedWithServiceAfterPayment(Map<String, String> params) throws Exception;

	public abstract String getResubmitActivityName();

	public abstract String getResubmissionUser();
}
