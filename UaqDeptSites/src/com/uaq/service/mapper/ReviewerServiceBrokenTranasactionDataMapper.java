package com.uaq.service.mapper;

import com.uaq.common.ServiceNameConstant;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.EGDResubmissionVO;
import com.uaq.vo.LPtoWhomeConcernVO;
import com.uaq.vo.LandInputVO;
import com.uaq.vo.NewSupplierRegistrationVO;
import com.uaq.vo.PSResubmissonOutputVO;
import com.uaq.vo.WhomItmayConcernVO;

public class ReviewerServiceBrokenTranasactionDataMapper {

	public static LandInputVO setReviewDataToAddLandVO(AccountDetailTokenOutputVO accountDetailfromToken, String requestNo) {
		LandInputVO landInputVO = new LandInputVO();
		landInputVO.setServiceType("New");
		landInputVO.setUserComment("");
		landInputVO.setSourceType("1");
		landInputVO.setSubmittedByUserId(accountDetailfromToken.getUserName());
		landInputVO.setStatus("");
		landInputVO.setServiceName(ServiceNameConstant.ADD_LAND_REQUEST_NAME);
		landInputVO.setServiceid(ServiceNameConstant.ADD_LAND_REQUEST);
		landInputVO.setRequestNo(requestNo);
		landInputVO.setRequestID(requestNo.split("-")[3]);
		landInputVO.setWorkflowId("1");
		landInputVO.setAddLandReqID("1");
		// landInputVO.setLanguageId("");
		landInputVO.setSite_Plan_No("");
		landInputVO.setSitePlanDate("2015-10-10");
		landInputVO.setSecPlotNo("");
		landInputVO.setSectorBlock("");
		landInputVO.setSecSectorName("");
		landInputVO.setSubSectorNo("");
		landInputVO.setAreaName("");
		landInputVO.setAreaBlock("");
		landInputVO.setAreaPlotNo("");
		landInputVO.setAreaSubArea("");
		landInputVO.setLandUsage("NoLandUsage");
		// landInputVO.setSitePlanDocument(resubmissonVO.getSiteplanDocId());
		landInputVO.setGrantExpiryDate("2015-10-10");
		landInputVO.setAssignedToUserName("");
		landInputVO.setSurveyo_Report("");
		landInputVO.setOwnerName("");
		landInputVO.setOwnerID("");
		landInputVO.setOwnerNationalityID("");
		landInputVO.setSurveReportDocId("");
		landInputVO.setExtendedDate("2015-10-10");
		landInputVO.setSupportiveAttachmentid("");
		landInputVO.setSubmitedNocID("");
		landInputVO.setNocLetterId("");
		landInputVO.setSitePlanDocId("");
		landInputVO.setLandValue("0");
		landInputVO.setCommiteeRemarks("");
		landInputVO.setTrueSitePlanDocId("");
		landInputVO.setAreaName("");
		landInputVO.setGrantIssuanceDate("2015-10-10");
		landInputVO.setCreatedBy(accountDetailfromToken.getUserName());
		landInputVO.setModifiedBy(accountDetailfromToken.getUserName());
		return landInputVO;
	}

	public static LandInputVO settingServiceToToIssueSitePlanDocVO(AccountDetailTokenOutputVO accountDetailfromToken, String requestNo) {
		LandInputVO issueSitePlanDocVO = new LandInputVO();
		issueSitePlanDocVO.setServiceType("New");
		issueSitePlanDocVO.setUserComment("");
		issueSitePlanDocVO.setSubmittedByUserId(accountDetailfromToken.getUserName());
		issueSitePlanDocVO.setSourceType("1");
		issueSitePlanDocVO.setStatus("");
		issueSitePlanDocVO.setServiceName(ServiceNameConstant.ISSUE_SITE_PLAN_DOCUMENT_REQUEST_NAME);
		issueSitePlanDocVO.setServiceid(ServiceNameConstant.ISSUE_SITE_PLAN_DOCUMENT_REQUEST);
		issueSitePlanDocVO.setRequestID(requestNo.split("-")[3]);
		issueSitePlanDocVO.setRequestNo(requestNo);
		issueSitePlanDocVO.setWorkflowId("");
		issueSitePlanDocVO.setLandDemarcationId("");
		// landDemacreationVO.setLanguageId("1");Should be 1 for en and 2 for ar
		issueSitePlanDocVO.setSite_Plan_No("");
		issueSitePlanDocVO.setSitePlanDate("2015-10-10");
		issueSitePlanDocVO.setSecPlotNo("");
		issueSitePlanDocVO.setSectorBlock("");
		issueSitePlanDocVO.setSecSectorName("");
		issueSitePlanDocVO.setSubSectorNo("");
		issueSitePlanDocVO.setAreaName("");
		issueSitePlanDocVO.setAreaBlock("");
		issueSitePlanDocVO.setAreaPlotNo("");
		issueSitePlanDocVO.setAreaSubArea("");
		issueSitePlanDocVO.setLandUsage("NoLandUsage");
		issueSitePlanDocVO.setGrantIssuanceDate("2015-10-10");
		issueSitePlanDocVO.setAssignedToUserName(accountDetailfromToken.getUserName());
		issueSitePlanDocVO.setSurveyo_Report("");
		issueSitePlanDocVO.setOwnerName(accountDetailfromToken.getFirstName());
		issueSitePlanDocVO.setSurveReportDocId("1");
		issueSitePlanDocVO.setOwnerID(accountDetailfromToken.getAccountId());
		issueSitePlanDocVO.setOwnerNationalityID((accountDetailfromToken.getLanguageId()) == null ? "" : accountDetailfromToken.getLanguageId().toString());
		issueSitePlanDocVO.setSupportiveAttachmentid("1");
		issueSitePlanDocVO.setExtendedDate("2015-10-10");
		// landDemacreationVO.setSitePlanDocument(resubmissonVO.getSitePlanNo());
		issueSitePlanDocVO.setCreatedBy(accountDetailfromToken.getUserName());
		issueSitePlanDocVO.setModifiedBy(accountDetailfromToken.getUserName());
		return issueSitePlanDocVO;

	}

	public static LandInputVO settingServiceToDemarcationVO(AccountDetailTokenOutputVO accountDetailfromToken, String requestNo) {
		LandInputVO landDemacreationVO = new LandInputVO();
		landDemacreationVO.setServiceType("1");
		landDemacreationVO.setUserComment("");
		landDemacreationVO.setSubmittedByUserId(accountDetailfromToken.getUserName());
		landDemacreationVO.setSourceType("1");
		landDemacreationVO.setStatus("1");
		landDemacreationVO.setServiceName(ServiceNameConstant.LAND_DEMACRATION_REQUEST_NAME);
		landDemacreationVO.setServiceid(ServiceNameConstant.LAND_DEMACRATION_REQUEST);
		landDemacreationVO.setRequestID(requestNo.split("-")[3]);
		landDemacreationVO.setRequestNo(requestNo);
		landDemacreationVO.setWorkflowId("1");
		landDemacreationVO.setLandDemarcationId("1");
		landDemacreationVO.setLanguageId("1");
		landDemacreationVO.setSite_Plan_No("");
		landDemacreationVO.setSitePlanDate("2015-10-10");
		landDemacreationVO.setSecPlotNo("");
		landDemacreationVO.setSectorBlock("");
		landDemacreationVO.setSecSectorName("");
		landDemacreationVO.setSubSectorNo("");
		landDemacreationVO.setAreaName("");
		landDemacreationVO.setAreaBlock("");
		landDemacreationVO.setAreaPlotNo("");
		landDemacreationVO.setAreaSubArea("");
		landDemacreationVO.setLandUsage("NoLandUsage");
		landDemacreationVO.setGrantIssuanceDate("2015-10-10");
		landDemacreationVO.setAssignedToUserName(accountDetailfromToken.getUserName());
		landDemacreationVO.setSurveyo_Report("");
		landDemacreationVO.setOwnerName(accountDetailfromToken.getUserName());
		landDemacreationVO.setSurveReportDocId("1");
		landDemacreationVO.setOwnerID(accountDetailfromToken.getAccountId());
		landDemacreationVO.setOwnerNationalityID(accountDetailfromToken.getNationalityId().toString());
		landDemacreationVO.setSupportiveAttachmentid("1");
		landDemacreationVO.setExtendedDate("2015-10-10");
		// landDemacreationVO.setSitePlanDocument(resubmissonVO.getSitePlanNo());
		landDemacreationVO.setCreatedBy(accountDetailfromToken.getUserName());
		landDemacreationVO.setModifiedBy(accountDetailfromToken.getUserName());
		return landDemacreationVO;

	}

	public static NewSupplierRegistrationVO setSupplierDataForAfterPayment(AccountDetailTokenOutputVO accountDetailfromToken, String requestNo) {

		NewSupplierRegistrationVO supplierVo = new NewSupplierRegistrationVO();
		supplierVo.setEstablishmentName("");
		supplierVo.setTradeLicenceNumber("");
		// supplierVo.setExpiryDate(eGDResubmissionVO.getTrdExpDate());
		supplierVo.setRequestType("1");
		supplierVo.setSourceType("1");
		supplierVo.setUserName(accountDetailfromToken.getUserName());
		supplierVo.setServiceId("501");
		supplierVo.setSuppRegId("1");
		supplierVo.setSuppCatgId("");
		supplierVo.setUserCommnets("");
		supplierVo.setSupplierCategory("");
		supplierVo.setRequestId(Integer.parseInt(requestNo.split("-")[3]));
		supplierVo.setRequestNo(requestNo);
		supplierVo.setWorkListId(1);
		supplierVo.setSupprReqId(1);
		supplierVo.setStateId(1);
		supplierVo.setTempSupprReqId(1);
		supplierVo.setServiceName_En("Supplier Registration");
		supplierVo.setServiceName_Ar("Supplier Registration");
		// supplierVo.setRegistrationsType(eGDResubmissionVO.getRegistrationsType());
		supplierVo.setRegistrationType("");
		supplierVo.setPaymentId(7);
		supplierVo.setPaymentStatus("7");
		supplierVo.setServiceFee("400");
		supplierVo.setApplicationFee("244");
		supplierVo.setDeptID("123");
		supplierVo.setServiceFeeDisc("1");
		supplierVo.setEdirhamServCode("1");
		supplierVo.setIsPaymentRequired("1");
		supplierVo.setOfficeNumber("1234");
		// supplierVo.setTradingLicence();
		// supplierVo.setSignatureAttestation(eGDResubmissionVO.getSignatureAttestation());
		// supplierVo.setChamberOfCommerce(eGDResubmissionVO.getChamberOfCommerce());
		// supplierVo.setCertificates(eGDResubmissionVO.getCertificates());

		return supplierVo;
	}

	public static WhomItmayConcernVO getWhomItmayConcernVOForProceedToOperator(AccountDetailTokenOutputVO accountDetailfromToken, String requestNo) {
		WhomItmayConcernVO whomItmayConcernVO = new WhomItmayConcernVO();
		whomItmayConcernVO.setAddressTo("");
		whomItmayConcernVO.setFamilyBookNo("");
		whomItmayConcernVO.setSpouseIdNo("");
		whomItmayConcernVO.setSource("1");
		whomItmayConcernVO.setServiceId(ServiceNameConstant.ISSUE_TO_WHOME_IT_MAY_CERTIFICATE);
		whomItmayConcernVO.setUserCommnets("");
		whomItmayConcernVO.setOther("");
		whomItmayConcernVO.setRequestId(Integer.parseInt(requestNo.split("-")[3]));
		whomItmayConcernVO.setRequestNo(requestNo);
		whomItmayConcernVO.setWorkListId(1);
		// For Proceed to reviwer the status should be 41 for Operartor
		whomItmayConcernVO.setStateId(48);
		whomItmayConcernVO.setToWhomItmayId(new Integer(0));
		whomItmayConcernVO.setTempToWhomItmayId(1);
		whomItmayConcernVO.setServiceName_En(ServiceNameConstant.ISSUE_TO_WHOME_IT_MAY_CERTIFICATE_NAME);
		whomItmayConcernVO.setServiceName_Ar(ServiceNameConstant.ISSUE_TO_WHOME_IT_MAY_CERTIFICATE_NAME);

		return whomItmayConcernVO;
	}

}
