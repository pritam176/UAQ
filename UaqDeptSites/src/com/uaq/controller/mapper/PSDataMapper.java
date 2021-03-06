package com.uaq.controller.mapper;

import static com.uaq.common.ApplicationConstants.DATE_FORMAT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.uaq.command.AddLandRequestCommand;
import com.uaq.command.ExtensionOfGrandCommand;
import com.uaq.command.GrantLandRequestCommand;
import com.uaq.command.IssueSitePlanDocumentCommand;
import com.uaq.command.LandDemarcationRequestCommand;
import com.uaq.common.ServiceNameConstant;
import com.uaq.util.StringUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LandInputVO;
import com.uaq.vo.PSGrandLandRequestVO;
import com.uaq.vo.PSResubmissonOutputVO;

public class PSDataMapper {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	
	

	/**
	 * This will set the Data to LandInputVO for Resubmisson
	 * 
	 * @param AccountDetailTokenOutputVO
	 * @param PSResubmissonOutputVO
	 * @return LandInputVO
	 */
	public static LandInputVO setReviewDataToAddLandVO(PSResubmissonOutputVO resubmissonVO, AccountDetailTokenOutputVO accountDetailfromToken) {
		LandInputVO landInputVO = new LandInputVO();
		landInputVO.setServiceType("New");
		landInputVO.setUserComment("");
		landInputVO.setSourceType("1");
		landInputVO.setSubmittedByUserId(accountDetailfromToken.getUserName());
		landInputVO.setStatus("");
		landInputVO.setServiceName(ServiceNameConstant.ADD_LAND_REQUEST_NAME);
		landInputVO.setServiceid(ServiceNameConstant.ADD_LAND_REQUEST);
		landInputVO.setRequestNo(resubmissonVO.getRequestNo());
		landInputVO.setRequestID(resubmissonVO.getRequestNo().split("-")[3]);
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

	/**
	 * This will set the Data to LandInputVO for Resubmisson Land Demacration
	 * 
	 * @param AccountDetailTokenOutputVO
	 * @param PSResubmissonOutputVO
	 * @return LandInputVO
	 */
	public static LandInputVO settingServiceToDemarcationVO(PSResubmissonOutputVO resubmissonVO, AccountDetailTokenOutputVO accountDetailfromToken) {
		LandInputVO landDemacreationVO = new LandInputVO();
		landDemacreationVO.setServiceType("1");
		landDemacreationVO.setUserComment("");
		landDemacreationVO.setSubmittedByUserId(accountDetailfromToken.getUserName());
		landDemacreationVO.setSourceType("1");
		landDemacreationVO.setStatus("1");
		landDemacreationVO.setServiceName(ServiceNameConstant.LAND_DEMACRATION_REQUEST_NAME);
		landDemacreationVO.setServiceid(ServiceNameConstant.LAND_DEMACRATION_REQUEST);
		landDemacreationVO.setRequestID(resubmissonVO.getRequestNo().split("-")[3]);
		landDemacreationVO.setRequestNo(resubmissonVO.getRequestNo());
		landDemacreationVO.setWorkflowId("1");
		landDemacreationVO.setLandDemarcationId("1");
		landDemacreationVO.setLanguageId("1");
		landDemacreationVO.setSite_Plan_No(resubmissonVO.getSitePlanNo() == null ? "" : resubmissonVO.getSitePlanNo());
		landDemacreationVO.setSitePlanDate("2015-10-10");
		landDemacreationVO.setSecPlotNo(resubmissonVO.getSecPlotNo() == null ? "" : resubmissonVO.getSecPlotNo());
		landDemacreationVO.setSectorBlock(resubmissonVO.getSectorBlock() == null ? "" : resubmissonVO.getSectorBlock());
		landDemacreationVO.setSecSectorName(resubmissonVO.getSecSectorNo() == null ? "" : resubmissonVO.getSecSectorNo());
		landDemacreationVO.setSubSectorNo(resubmissonVO.getSubSectorNo() == null ? "" : resubmissonVO.getSubSectorNo());
		landDemacreationVO.setAreaName(resubmissonVO.getAreaNo() == null ? "" : resubmissonVO.getAreaNo());
		landDemacreationVO.setAreaBlock(resubmissonVO.getAreaBlock() == null ? "" : resubmissonVO.getAreaBlock());
		landDemacreationVO.setAreaPlotNo(resubmissonVO.getAreaPlotNo() == null ? "" : resubmissonVO.getAreaPlotNo());
		landDemacreationVO.setAreaSubArea(resubmissonVO.getAreaSubArea() == null ? "" : resubmissonVO.getAreaSubArea());
		landDemacreationVO.setLandUsage(resubmissonVO.getLandUsage());
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

	/**
	 * This will set the Data to LandInputVO for Resubmisson Issus site Plan
	 * 
	 * @param AccountDetailTokenOutputVO
	 * @param PSResubmissonOutputVO
	 * @return LandInputVO
	 */
	public static LandInputVO settingServiceToToIssueSitePlanDocVO(PSResubmissonOutputVO resubmissonVO, AccountDetailTokenOutputVO accountDetailfromToken) {
		LandInputVO issueSitePlanDocVO = new LandInputVO();
		issueSitePlanDocVO.setServiceType("New");
		issueSitePlanDocVO.setUserComment("");
		issueSitePlanDocVO.setSubmittedByUserId(accountDetailfromToken.getUserName());
		issueSitePlanDocVO.setSourceType("1");
		issueSitePlanDocVO.setStatus("");
		issueSitePlanDocVO.setServiceName(ServiceNameConstant.ISSUE_SITE_PLAN_DOCUMENT_REQUEST_NAME);
		issueSitePlanDocVO.setServiceid(ServiceNameConstant.ISSUE_SITE_PLAN_DOCUMENT_REQUEST);
		issueSitePlanDocVO.setRequestID(resubmissonVO.getRequestNo().split("-")[3]);
		issueSitePlanDocVO.setRequestNo(resubmissonVO.getRequestNo());
		issueSitePlanDocVO.setWorkflowId("");
		issueSitePlanDocVO.setLandDemarcationId("");
		// landDemacreationVO.setLanguageId("1");Should be 1 for en and 2 for ar
		issueSitePlanDocVO.setSite_Plan_No(resubmissonVO.getSitePlanNo());
		issueSitePlanDocVO.setSitePlanDate("2015-10-10");
		issueSitePlanDocVO.setSecPlotNo(resubmissonVO.getSecPlotNo() == null ? "" : resubmissonVO.getSecPlotNo());
		issueSitePlanDocVO.setSectorBlock(resubmissonVO.getSectorBlock() == null ? "" : resubmissonVO.getSectorBlock());
		issueSitePlanDocVO.setSecSectorName(resubmissonVO.getSecSectorNo() == null ? "" : resubmissonVO.getSecSectorNo());
		issueSitePlanDocVO.setSubSectorNo(resubmissonVO.getSubSectorNo() == null ? "" : resubmissonVO.getSubSectorNo());
		issueSitePlanDocVO.setAreaName(resubmissonVO.getAreaNo() == null ? "" : resubmissonVO.getAreaNo());
		issueSitePlanDocVO.setAreaBlock(resubmissonVO.getAreaBlock() == null ? "" : resubmissonVO.getAreaBlock());
		issueSitePlanDocVO.setAreaPlotNo(resubmissonVO.getAreaPlotNo() == null ? "" : resubmissonVO.getAreaPlotNo());
		issueSitePlanDocVO.setAreaSubArea(resubmissonVO.getAreaSubArea() == null ? "" : resubmissonVO.getAreaSubArea());
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

	public static LandInputVO setReviewDataToExtentionGrandLandVO(PSResubmissonOutputVO resubmissonVO, AccountDetailTokenOutputVO accountDetailfromToken) {
		LandInputVO landDemacreationVO = new LandInputVO();
		landDemacreationVO.setServiceType("1");
		landDemacreationVO.setUserComment("");
		landDemacreationVO.setSubmittedByUserId(accountDetailfromToken.getUserName());
		landDemacreationVO.setSourceType("1");
		landDemacreationVO.setStatus("1");
		landDemacreationVO.setServiceName(ServiceNameConstant.EXTENTION_OF_GRANT_LAND_REQUEST_NAME);
		landDemacreationVO.setServiceid(ServiceNameConstant.EXTENTION_OF_GRANT_LAND_REQUEST);
		landDemacreationVO.setRequestID(resubmissonVO.getRequestNo().split("-")[3]);
		landDemacreationVO.setRequestNo(resubmissonVO.getRequestNo());
		landDemacreationVO.setWorkflowId("1");
		landDemacreationVO.setLandDemarcationId("1");
		landDemacreationVO.setLanguageId("1");
		landDemacreationVO.setSite_Plan_No(resubmissonVO.getSitePlanNo());
		landDemacreationVO.setSitePlanDate("2015-10-10");
		landDemacreationVO.setSecPlotNo(resubmissonVO.getSecPlotNo() == null ? "" : resubmissonVO.getSecPlotNo());
		landDemacreationVO.setSectorBlock(resubmissonVO.getSectorBlock() == null ? "" : resubmissonVO.getSectorBlock());
		landDemacreationVO.setSecSectorName(resubmissonVO.getSecSectorNo() == null ? "" : resubmissonVO.getSecSectorNo());
		landDemacreationVO.setSubSectorNo(resubmissonVO.getSubSectorNo() == null ? "" : resubmissonVO.getSubSectorNo());
		landDemacreationVO.setAreaName(resubmissonVO.getAreaNo() == null ? "" : resubmissonVO.getAreaNo());
		landDemacreationVO.setAreaBlock(resubmissonVO.getAreaBlock() == null ? "" : resubmissonVO.getAreaBlock());
		landDemacreationVO.setAreaPlotNo(resubmissonVO.getAreaPlotNo() == null ? "" : resubmissonVO.getAreaPlotNo());
		landDemacreationVO.setAreaSubArea(resubmissonVO.getAreaSubArea() == null ? "" : resubmissonVO.getAreaSubArea());
		landDemacreationVO.setLandUsage(resubmissonVO.getLandUsage());
		landDemacreationVO.setGrantIssuanceDate("2015-10-10");
		landDemacreationVO.setAssignedToUserName(accountDetailfromToken.getUserName());
		landDemacreationVO.setSurveyo_Report("");
		landDemacreationVO.setOwnerName(accountDetailfromToken.getUserName());
		landDemacreationVO.setSurveReportDocId("1");
		landDemacreationVO.setOwnerID(accountDetailfromToken.getAccountId());
		landDemacreationVO.setOwnerNationalityID("1");
		landDemacreationVO.setSupportiveAttachmentid("1");
		landDemacreationVO.setExtendedDate("2015-10-10");
		landDemacreationVO.setLandDemarcationId(resubmissonVO.getId().toString());
		landDemacreationVO.setCreatedBy(accountDetailfromToken.getUserName());
		landDemacreationVO.setModifiedBy(accountDetailfromToken.getUserName());
		landDemacreationVO.setExtensionOfGrantLandId(resubmissonVO.getId().toString());
		
		return landDemacreationVO;

	}

	public static LandInputVO setReviewDataToGrandLand(PSGrandLandRequestVO resubmissonVO, AccountDetailTokenOutputVO accountDetailfromToken) {

		LandInputVO grandLandVO = new LandInputVO();
		grandLandVO.setServiceType("1");
		grandLandVO.setUserComment("abc");
		grandLandVO.setSubmittedByUserId(accountDetailfromToken.getUserName());
		grandLandVO.setSourceType("1");
		grandLandVO.setStatus("1");
		grandLandVO.setServiceName(ServiceNameConstant.GRAND_LAND_REQUEST_NAME);
		grandLandVO.setServiceid(ServiceNameConstant.GRAND_LAND_REQUEST);
		grandLandVO.setRequestID("1");
		grandLandVO.setRequestNo("1");
		grandLandVO.setWorkflowId("1");
		grandLandVO.setLandDemarcationId("1");
		grandLandVO.setLanguageId("1");

		// landDemacreationVO.setSite_Plan_No(resubmissonVO.getSitePlanNo());
		/*
		 * landDemacreationVO.setSitePlanDate("2015-10-10");
		 * landDemacreationVO.setSecPlotNo
		 * (resubmissonVO.getSecPlotNo()==null?"":resubmissonVO.getSecPlotNo());
		 * landDemacreationVO
		 * .setSectorBlock(resubmissonVO.getSectorBlock()==null
		 * ?"":resubmissonVO.getSectorBlock());
		 * landDemacreationVO.setSecSectorName
		 * (resubmissonVO.getSecSectorNo()==null
		 * ?"":resubmissonVO.getSecSectorNo());
		 * landDemacreationVO.setSubSectorNo
		 * (resubmissonVO.getSubSectorNo()==null
		 * ?"":resubmissonVO.getSubSectorNo());
		 * landDemacreationVO.setAreaName(resubmissonVO
		 * .getAreaNo()==null?"":resubmissonVO.getAreaNo());
		 * landDemacreationVO.setAreaBlock
		 * (resubmissonVO.getAreaBlock()==null?"":resubmissonVO.getAreaBlock());
		 * landDemacreationVO
		 * .setAreaPlotNo(resubmissonVO.getAreaPlotNo()==null?""
		 * :resubmissonVO.getAreaPlotNo());
		 * landDemacreationVO.setAreaSubArea(resubmissonVO
		 * .getAreaSubArea()==null?"":resubmissonVO.getAreaSubArea());
		 * landDemacreationVO.setLandUsage(resubmissonVO.getLandUsage());
		 */
		grandLandVO.setGrantIssuanceDate("2015-10-10");
		grandLandVO.setAssignedToUserName(accountDetailfromToken.getUserName());
		grandLandVO.setSurveyo_Report("test");
		grandLandVO.setOwnerName(accountDetailfromToken.getUserName());
		grandLandVO.setSurveReportDocId("1");
		grandLandVO.setOwnerID(accountDetailfromToken.getAccountId());
		grandLandVO.setOwnerNationalityID("1");
		grandLandVO.setSupportiveAttachmentid("1");
		grandLandVO.setExtendedDate("2015-10-10");
		// landDemacreationVO.setSitePlanDocument(resubmissonVO.getSitePlanNo());
		grandLandVO.setCreatedBy(accountDetailfromToken.getUserName());
		grandLandVO.setModifiedBy(accountDetailfromToken.getUserName());
		return grandLandVO;

	}

	/**
	 * Setting the Form Command Data to the pSRequestService.addLand input VO
	 * for Both Mobile & Portal
	 * 
	 * 
	 * @param AddLandRequestCommand
	 * @param AccountDetailTokenOutputVO
	 * @return LandInputVO
	 */
	public static LandInputVO addLandDataToService(AddLandRequestCommand addLandRequestCommand, AccountDetailTokenOutputVO accountDetailfromToken) {
		LandInputVO landInputVO = new LandInputVO();
		landInputVO.setServiceType("1");
		landInputVO.setUserComment("");
		landInputVO.setSourceType("1");
		landInputVO.setSubmittedByUserId(accountDetailfromToken.getUserName());
		landInputVO.setStatus("1");
		landInputVO.setServiceName(ServiceNameConstant.ADD_LAND_REQUEST_NAME);
		landInputVO.setServiceid(ServiceNameConstant.ADD_LAND_REQUEST);
		landInputVO.setRequestNo("1");
		landInputVO.setRequestID("1");
		landInputVO.setWorkflowId("1");
		landInputVO.setAddLandReqID("1");
		// landInputVO.setLanguageId("1");
		landInputVO.setSite_Plan_No(addLandRequestCommand.getSiteNumber());
		landInputVO.setSitePlanDate("");
		landInputVO.setSecSectorName(addLandRequestCommand.getSector());
		landInputVO.setSectorBlock(addLandRequestCommand.getBlock());
		landInputVO.setSubSectorNo(addLandRequestCommand.getSubsector());
		landInputVO.setSecPlotNo(addLandRequestCommand.getPlotNumber());
		landInputVO.setAreaBlock(addLandRequestCommand.getAreablock());
		landInputVO.setAreaSubArea(addLandRequestCommand.getSubarea());
		landInputVO.setAreaPlotNo(addLandRequestCommand.getAreaPlotNumber());
		landInputVO.setLandUsage(addLandRequestCommand.getLandUsage());
		landInputVO.setSitePlanDocument(addLandRequestCommand.getSitePlanDocument_name());
		landInputVO.setGrantExpiryDate("");
		landInputVO.setAssignedToUserName(accountDetailfromToken.getUserName());
		landInputVO.setSurveyo_Report("");
		landInputVO.setOwnerName(accountDetailfromToken.getFirstName());
		landInputVO.setOwnerID(accountDetailfromToken.getEmiratesId());
		landInputVO.setOwnerNationalityID((accountDetailfromToken.getNationalityId()) == null ? "" : accountDetailfromToken.getNationalityId().toString());
		landInputVO.setSurveReportDocId("1");
		landInputVO.setExtendedDate("");
		landInputVO.setSupportiveAttachmentid("1");
		landInputVO.setSubmitedNocID("1");
		landInputVO.setNocLetterId("1");
		landInputVO.setSitePlanDocId("1");
		landInputVO.setLandValue("0");
		landInputVO.setCommiteeRemarks("");
		landInputVO.setTrueSitePlanDocId("1");
		landInputVO.setAreaName(addLandRequestCommand.getArea());
		landInputVO.setGrantIssuanceDate("");
		landInputVO.setCreatedBy(accountDetailfromToken.getUserName());
		landInputVO.setModifiedBy(accountDetailfromToken.getUserName());
		return landInputVO;
	}

	/**
	 * Setting the Form Command Data to the
	 * pSRequestService.issueSitePalnDocument input VO for Both Mobile & Portal
	 * 
	 * @param IssueSitePlanDocumentCommand
	 * @param AccountDetailTokenOutputVO
	 * @return LandInputVO
	 */
	public static LandInputVO issueSitePlanDataToService(IssueSitePlanDocumentCommand issueSitePlanDocumentCommand, AccountDetailTokenOutputVO accountDetailfromToken) {
		LandInputVO landInputVO = new LandInputVO();
		landInputVO.setServiceType("New");
		landInputVO.setUserComment("");
		landInputVO.setSourceType("1");
		landInputVO.setSubmittedByUserId(accountDetailfromToken.getUserName());
		landInputVO.setStatus("");
		landInputVO.setServiceName(ServiceNameConstant.ISSUE_SITE_PLAN_DOCUMENT_REQUEST_NAME);
		landInputVO.setServiceid(ServiceNameConstant.ISSUE_SITE_PLAN_DOCUMENT_REQUEST);
		landInputVO.setRequestNo("");
		landInputVO.setRequestID("");
		landInputVO.setWorkflowId("");
		landInputVO.setAddLandReqID("");
		// landInputVO.setLanguageId("1");
		landInputVO.setSite_Plan_No(issueSitePlanDocumentCommand.getSitePlanNumber());
		landInputVO.setSitePlanDate("2015-10-10");
		landInputVO.setSecSectorName(issueSitePlanDocumentCommand.getSector());
		landInputVO.setSectorBlock(issueSitePlanDocumentCommand.getBlock());
		landInputVO.setSubSectorNo(issueSitePlanDocumentCommand.getSubsector());
		landInputVO.setSecPlotNo(issueSitePlanDocumentCommand.getSectorPlotNumber());
		landInputVO.setAreaBlock(issueSitePlanDocumentCommand.getAreaBlock());
		landInputVO.setAreaSubArea(issueSitePlanDocumentCommand.getSubArea());
		landInputVO.setAreaPlotNo(issueSitePlanDocumentCommand.getAreaPlotNumber());
		landInputVO.setLandUsage(issueSitePlanDocumentCommand.getLandUsage());
		landInputVO.setSitePlanDocument(issueSitePlanDocumentCommand.getSitePlanDocument_name());
		landInputVO.setGrantExpiryDate("1800-01-01");
		landInputVO.setAssignedToUserName(accountDetailfromToken.getUserName());
		landInputVO.setSurveyo_Report("");
		landInputVO.setOwnerName(accountDetailfromToken.getFirstName());
		landInputVO.setOwnerID(accountDetailfromToken.getAccountId());
		landInputVO.setOwnerNationalityID((accountDetailfromToken.getLanguageId()) == null ? "" : accountDetailfromToken.getLanguageId().toString());
		landInputVO.setSurveReportDocId("");
		landInputVO.setExtendedDate("1800-01-01");
		landInputVO.setSupportiveAttachmentid("1");
		landInputVO.setSubmitedNocID("");
		landInputVO.setNocLetterId("");
		landInputVO.setSitePlanDocId("");
		landInputVO.setLandValue("0");
		landInputVO.setCommiteeRemarks("");
		landInputVO.setTrueSitePlanDocId("");
		landInputVO.setCommiteeRemarks("");
		landInputVO.setTrueSitePlanDocId("");
		landInputVO.setDocDeliveredFlag("");
		landInputVO.setAtelierAttachId("1");
		landInputVO.setIssueSitePlanDocReqId("");
		landInputVO.setApplicantFees("");
		landInputVO.setServiceFees("");
		landInputVO.setCreatedBy(accountDetailfromToken.getUserName());
		landInputVO.setModifiedBy(accountDetailfromToken.getUserName());
		landInputVO.setAreaName(issueSitePlanDocumentCommand.getArea());
		landInputVO.setGrantIssuanceDate("1800-01-01");
		return landInputVO;
	}

	/**
	 * Setting the Form Command Data to the
	 * pSRequestService.landDemarcationRequest input VO for Both Mobile & Portal
	 * 
	 * @param LandDemarcationRequestCommand
	 * @param AccountDetailTokenOutputVO
	 * @return LandInputVO
	 */
	public static LandInputVO landDemacrationDataToService(LandDemarcationRequestCommand command, AccountDetailTokenOutputVO accountDetailfromToken) {
		LandInputVO landDemacreationVO = new LandInputVO();
		landDemacreationVO.setServiceType("1");
		landDemacreationVO.setUserComment("");
		landDemacreationVO.setSubmittedByUserId(accountDetailfromToken.getUserName());
		landDemacreationVO.setSourceType("1");
		landDemacreationVO.setStatus("1");
		landDemacreationVO.setServiceName(ServiceNameConstant.LAND_DEMACRATION_REQUEST_NAME);
		landDemacreationVO.setServiceid(ServiceNameConstant.LAND_DEMACRATION_REQUEST);
		landDemacreationVO.setRequestID("1");
		landDemacreationVO.setRequestNo("1");
		landDemacreationVO.setWorkflowId("1");
		landDemacreationVO.setLandDemarcationId("1");
		// landDemacreationVO.setLanguageId("1");
		landDemacreationVO.setSite_Plan_No(command.getSitePlanNumber());
		landDemacreationVO.setSitePlanDate("2015-10-10");
		landDemacreationVO.setSecPlotNo(command.getSectorPlotNumber());
		landDemacreationVO.setSectorBlock(command.getBlock());
		landDemacreationVO.setSecSectorName((command.getSector() == null) ? "" : command.getSector());
		landDemacreationVO.setSubSectorNo(command.getSubsector());
		landDemacreationVO.setAreaName((command.getArea() == null) ? "" : command.getArea());
		landDemacreationVO.setAreaBlock(command.getAreablock());
		landDemacreationVO.setAreaPlotNo(command.getAreaPlotNumber());
		landDemacreationVO.setAreaSubArea(command.getSubarea());
		landDemacreationVO.setLandUsage(command.getLandUsage());
		landDemacreationVO.setGrantIssuanceDate("2015-10-10");
		landDemacreationVO.setAssignedToUserName(accountDetailfromToken.getUserName());
		landDemacreationVO.setSurveyo_Report("");
		landDemacreationVO.setOwnerName(accountDetailfromToken.getFirstName());
		landDemacreationVO.setSurveReportDocId("1");
		landDemacreationVO.setOwnerID(accountDetailfromToken.getAccountId());
		landDemacreationVO.setOwnerNationalityID((accountDetailfromToken.getLanguageId()) == null ? "" : accountDetailfromToken.getLanguageId().toString());
		landDemacreationVO.setSupportiveAttachmentid("1");
		landDemacreationVO.setExtendedDate("2015-10-10");
		landDemacreationVO.setSitePlanDocument(command.getSitePlanDocument_name());
		landDemacreationVO.setCreatedBy(accountDetailfromToken.getUserName());
		landDemacreationVO.setModifiedBy(accountDetailfromToken.getUserName());
		return landDemacreationVO;
	}

	/**
	 * Setting the Form Command Data to the pSRequestService.extention oF Grand
	 * Land input VO for Both Mobile & Portal
	 * 
	 * @param ExtensionOfGrandCommand
	 * @param AccountDetailTokenOutputVO
	 * @return LandInputVO
	 */
	public static LandInputVO extensionOfGrandDataToService(ExtensionOfGrandCommand extensionOfGrandCommand, AccountDetailTokenOutputVO accountDetailfromToken) {
		LandInputVO landInputVO = new LandInputVO();
		landInputVO.setSite_Plan_No(extensionOfGrandCommand.getSitePlanNumber());
		landInputVO.setSecSectorName((extensionOfGrandCommand.getSector()) == null ? "" : extensionOfGrandCommand.getSector());
		landInputVO.setSectorBlock(extensionOfGrandCommand.getBlock());
		landInputVO.setSubSectorNo(extensionOfGrandCommand.getSubsector());
		landInputVO.setSecPlotNo(extensionOfGrandCommand.getSectorPlotnumber());
		landInputVO.setAreaName((extensionOfGrandCommand.getArea()) == null ? "" : extensionOfGrandCommand.getArea());
		landInputVO.setAreaBlock(extensionOfGrandCommand.getAreaBlock());
		landInputVO.setAreaSubArea(extensionOfGrandCommand.getSubArea());
		landInputVO.setAreaPlotNo(extensionOfGrandCommand.getAreaPlotnumber());
		landInputVO.setLandUsage(extensionOfGrandCommand.getLandUsage());
		landInputVO.setGrantIssuanceDate(extensionOfGrandCommand.getGrandIssueDate());
		landInputVO.setGrantExpiryDate(extensionOfGrandCommand.getGrandExpDate());
		landInputVO.setSitePlanDocument(extensionOfGrandCommand.getSitePlanDocument_name());
		// to be set later
		landInputVO.setUserComment("");
		landInputVO.setSourceType("1");
		landInputVO.setSubmittedByUserId(accountDetailfromToken.getUserName());
		landInputVO.setStatus("1");
		landInputVO.setServiceName(ServiceNameConstant.EXTENTION_OF_GRANT_LAND_REQUEST_NAME);
		landInputVO.setServiceType("1");
		landInputVO.setServiceid(ServiceNameConstant.EXTENTION_OF_GRANT_LAND_REQUEST);
		landInputVO.setRequestID("1");
		landInputVO.setRequestNo("1");
		landInputVO.setWorkflowId("1");
		landInputVO.setExtensionOfGrantLandId("1");
		//landInputVO.setLanguageId((accountDetailfromToken.getLanguageId()) == null ? "" : accountDetailfromToken.getLanguageId().toString());
		landInputVO.setAssignedToUserName(accountDetailfromToken.getUserName());
		landInputVO.setOwnerName(accountDetailfromToken.getLoginusername());
		// input.setSurve_Report_DocId("1");
		landInputVO.setOwnerID(accountDetailfromToken.getEmiratesId());
		landInputVO.setOwnerNationalityID((accountDetailfromToken.getNationalityId()) == null ? "" : accountDetailfromToken.getNationalityId().toString());
		landInputVO.setSupportiveAttachmentid("1");
		landInputVO.setExtendedDate(dateFormat.format(new Date()));
		landInputVO.setSitePlanDate(dateFormat.format(new Date()));
		if(StringUtil.isEmpty(extensionOfGrandCommand.getArea())){
			landInputVO.setUserDecesion("sector");
		}else{
			landInputVO.setUserDecesion("area");
		}
		return landInputVO;
	}

	public static LandInputVO grantLandRequestDataToService(GrantLandRequestCommand grantLandRequestCommand, AccountDetailTokenOutputVO accountDetailfromToken) {
		LandInputVO inputVo = new LandInputVO();
		inputVo.setFamilyBook(grantLandRequestCommand.getFamilyBook());
		inputVo.setSpouceEmirateId(grantLandRequestCommand.getSpousesEmiratesIdAttch());
		inputVo.setPropertyAccountDoc(grantLandRequestCommand.getPropertyAccDetailsAttach());
		inputVo.setSalaryCertificateDoc(grantLandRequestCommand.getSalaryCertificate());
		inputVo.setSourceType((accountDetailfromToken.getSourceId()) == null ? "0" : accountDetailfromToken.getSourceId().toString());
		inputVo.setStatus("1");
		inputVo.setRequestNo("1");
		inputVo.setRequestID("1");
		inputVo.setServiceid(ServiceNameConstant.GRAND_LAND_REQUEST);
		inputVo.setCreatedDate(dateFormat.format(new Date()));
		inputVo.setModifiedDate(dateFormat.format(new Date()));
		inputVo.setCreatedBy(accountDetailfromToken.getUserName());
		inputVo.setModifiedBy(accountDetailfromToken.getUserName());
		inputVo.setStatusId(1);

		// Need To confirm
		inputVo.setOwnerName(accountDetailfromToken.getUserName());
		inputVo.setOwnerID(accountDetailfromToken.getAccountId());
		inputVo.setOwnerNationalityID((accountDetailfromToken.getNationalityId()) == null ? "1" : accountDetailfromToken.getNationalityId().toString());
		inputVo.setRulersCourtAcceptanceDocId(1);
		inputVo.setRulersCourtAcceptanceRemark("test");
		inputVo.setSubmittedByUserId(accountDetailfromToken.getUserName());
		return inputVo;
	}

	public static IssueSitePlanDocumentCommand settingIssueSitePlanDocCommand(PSResubmissonOutputVO resubmissonVO) {
		IssueSitePlanDocumentCommand command = new IssueSitePlanDocumentCommand();
		command.setArea("-1".equals(resubmissonVO.getAreaNo()) ? "" : resubmissonVO.getAreaNo());
		command.setAreaBlock("-1".equals(resubmissonVO.getAreaBlock()) ? "" : resubmissonVO.getAreaBlock());
		command.setAreaPlotNumber("-1".equals(resubmissonVO.getAreaPlotNo()) ? "" : resubmissonVO.getAreaPlotNo());
		command.setBlock("-1".equals(resubmissonVO.getSectorBlock()) ? "" : resubmissonVO.getSectorBlock());
		command.setLandUsage(resubmissonVO.getLandUsage());
		// command.setLocations(resubmissonVO.get);
		command.setSectorPlotNumber("-1".equals(resubmissonVO.getSecPlotNo()) ? "" : resubmissonVO.getSecPlotNo());
		command.setSector("-1".equals(resubmissonVO.getSecSectorNo()) ? "" : resubmissonVO.getSecSectorNo());
		command.setSitePlanNumber(resubmissonVO.getSitePlanNo());
		command.setSubArea("-1".equals(resubmissonVO.getAreaSubArea()) ? "" : resubmissonVO.getAreaSubArea());
		command.setSubsector("-1".equals(resubmissonVO.getSubSectorNo()) ? "" : resubmissonVO.getSubSectorNo());
		command.setIssueSiteplanAttachments(resubmissonVO.getIssueSitePlnAttachments());
		command.setOptionalComments(resubmissonVO.getOptionalComments());
		if (resubmissonVO.getSubSectorNo() != null && resubmissonVO.getSecPlotNo() != null && resubmissonVO.getSecSectorNo() != null && !resubmissonVO.getSubSectorNo().equals("-1")
				&& !resubmissonVO.getSecPlotNo().equals("-1") && !resubmissonVO.getSecSectorNo().equals("-1")) {
			command.setLocations("sector");
		} else if (resubmissonVO.getAreaNo() != null && resubmissonVO.getAreaBlock() != null && resubmissonVO.getAreaSubArea() != null && !resubmissonVO.getAreaNo().equals("-1")
				&& !resubmissonVO.getAreaBlock().equals("-1") && !resubmissonVO.getAreaSubArea().equals("-1")) {
			command.setLocations("area");
		}
		// Related To Resubmit Request Detail which is earler passes through
		// session
		// this will fill as Hidden Value in JSP
		command.setSorceType(resubmissonVO.getSourcetype());
		command.setStausId(String.valueOf(resubmissonVO.getStatusid()));
		command.setRequestNo(resubmissonVO.getRequestNo());
		command.setOwnerName(resubmissonVO.getOwnername());
		command.setOwnerId(resubmissonVO.getOwnerId());
		command.setSubmittednocid(String.valueOf(resubmissonVO.getSubmittednocid()));
		command.setSitePalnDOcId(String.valueOf(resubmissonVO.getSiteplanDocId()));
		command.setLandValue(resubmissonVO.getLandValue());
		command.setCommeteRemark(resubmissonVO.getCommiteeRemarks());
		command.setTrueSitePlanDocId(String.valueOf(resubmissonVO.getTrueSitePlanDocumentid()));
		command.setCreatedBy(resubmissonVO.getCreatedby());
		return command;

	}

	public static ExtensionOfGrandCommand settingExtentionGrandLandCommand(PSResubmissonOutputVO resubmissonOutputVO) throws ParseException {
		ExtensionOfGrandCommand command = new ExtensionOfGrandCommand();
		command.setArea("-1".equals(resubmissonOutputVO.getAreaNo()) ? "" : resubmissonOutputVO.getAreaNo());
		command.setAreaBlock("-1".equals(resubmissonOutputVO.getAreaBlock()) ? "" : resubmissonOutputVO.getAreaBlock());
		command.setAreaPlotnumber("-1".equals(resubmissonOutputVO.getAreaPlotNo()) ? "" : resubmissonOutputVO.getAreaPlotNo());
		command.setBlock("-1".equals(resubmissonOutputVO.getSectorBlock()) ? "" : resubmissonOutputVO.getSectorBlock());
		command.setLandUsage(resubmissonOutputVO.getLandUsage());
		
		command.setSectorPlotnumber("-1".equals(resubmissonOutputVO.getSecPlotNo()) ? "" : resubmissonOutputVO.getSecPlotNo());
		command.setSector("-1".equals(resubmissonOutputVO.getSecSectorNo()) ? "" : resubmissonOutputVO.getSecSectorNo());
		command.setSitePlanNumber(resubmissonOutputVO.getSitePlanNo());
		command.setSubArea("-1".equals(resubmissonOutputVO.getAreaSubArea()) ? "" : resubmissonOutputVO.getAreaSubArea());
		command.setSubsector("-1".equals(resubmissonOutputVO.getSubSectorNo()) ? "" : resubmissonOutputVO.getSubSectorNo());
	
		//command.setGrandExpDate(new SimpleDateFormat("MM/dd/yyyy").format(dateFormat.parse(resubmissonOutputVO.getGrandExpiryDate())));
		//command.setGrandIssueDate(new SimpleDateFormat("MM/dd/yyyy").format(dateFormat.parse(resubmissonOutputVO.getGranIssueDate())));
		
		command.setGrandExpDate(resubmissonOutputVO.getGrandExpiryDate());
		command.setGrandIssueDate(resubmissonOutputVO.getGranIssueDate());

		if (resubmissonOutputVO.getSubSectorNo() != null && resubmissonOutputVO.getSecPlotNo() != null && resubmissonOutputVO.getSecSectorNo() != null && !resubmissonOutputVO.getSubSectorNo().equals("-1")
				&& !resubmissonOutputVO.getSecPlotNo().equals("-1") && !resubmissonOutputVO.getSecSectorNo().equals("-1")) {
			command.setLocations("sector");
		} else if (resubmissonOutputVO.getAreaNo() != null && resubmissonOutputVO.getAreaBlock() != null && resubmissonOutputVO.getAreaSubArea() != null && !resubmissonOutputVO.getAreaNo().equals("-1")
				&& !resubmissonOutputVO.getAreaBlock().equals("-1") && !resubmissonOutputVO.getAreaSubArea().equals("-1")) {
			command.setLocations("area");
		}
		
		command.setSorceType(resubmissonOutputVO.getSourcetype());
		command.setStausId(String.valueOf(resubmissonOutputVO.getStatusid()));
		command.setRequestNo(resubmissonOutputVO.getRequestNo());
		command.setOwnerName(resubmissonOutputVO.getOwnername());
		command.setOwnerId(resubmissonOutputVO.getOwnerId());
		command.setServiceId(String.valueOf(resubmissonOutputVO.getServiceId()));
		command.setExtGrandLandId(resubmissonOutputVO.getId().toString());
		
	
		
		return command;

	}

	public static LandDemarcationRequestCommand settingCommanLandDemarcationCommand(PSResubmissonOutputVO resubmissonVO) {
		LandDemarcationRequestCommand command = new LandDemarcationRequestCommand();
		command.setArea("-1".equals(resubmissonVO.getAreaNo()) ? "" : resubmissonVO.getAreaNo());
		command.setAreablock("-1".equals(resubmissonVO.getAreaBlock()) ? "" : resubmissonVO.getAreaBlock());
		command.setAreaPlotNumber("-1".equals(resubmissonVO.getAreaPlotNo()) ? "" : resubmissonVO.getAreaPlotNo());
		command.setBlock("-1".equals(resubmissonVO.getSectorBlock()) ? "" : resubmissonVO.getSectorBlock());
		command.setLandUsage(resubmissonVO.getLandUsage());
		// command.setLocations(resubmissonVO.get);
		command.setSectorPlotNumber("-1".equals(resubmissonVO.getSecPlotNo()) ? "" : resubmissonVO.getSecPlotNo());
		command.setSector("-1".equals(resubmissonVO.getSecSectorNo()) ? "" : resubmissonVO.getSecSectorNo());
		command.setSitePlanNumber(resubmissonVO.getSitePlanNo());
		command.setSubarea("-1".equals(resubmissonVO.getAreaSubArea()) ? "" : resubmissonVO.getAreaSubArea());
		command.setSubsector("-1".equals(resubmissonVO.getSubSectorNo()) ? "" : resubmissonVO.getSubSectorNo());
		command.setServiceAttachments(resubmissonVO.getLandDemarcationAttachments());
		command.setOptionalComments(resubmissonVO.getOptionalComments());

		if (resubmissonVO.getSubSectorNo() != null && resubmissonVO.getSecPlotNo() != null && resubmissonVO.getSecSectorNo() != null && !resubmissonVO.getSubSectorNo().equals("-1")
				&& !resubmissonVO.getSecPlotNo().equals("-1") && !resubmissonVO.getSecSectorNo().equals("-1")) {
			command.setLocations("sector");
		} else if (resubmissonVO.getAreaNo() != null && resubmissonVO.getAreaBlock() != null && resubmissonVO.getAreaSubArea() != null && !resubmissonVO.getAreaNo().equals("-1")
				&& !resubmissonVO.getAreaBlock().equals("-1") && !resubmissonVO.getAreaSubArea().equals("-1")) {
			command.setLocations("area");
		}
		command.setSourcetype(resubmissonVO.getSourcetype());
		command.setStatusid(String.valueOf(resubmissonVO.getStatusid()));
		command.setRequestNo(resubmissonVO.getRequestNo());
		command.setOwnername(resubmissonVO.getOwnername());
		command.setOwnerId(resubmissonVO.getOwnerId());
		command.setServiceId(String.valueOf(resubmissonVO.getServiceId()));
		command.setLandValue(resubmissonVO.getLandValue());
		command.setId(String.valueOf(resubmissonVO.getId()));
		command.setCreatedby(resubmissonVO.getCreatedby());

		return command;

	}

	public static AddLandRequestCommand settingCommandAddLandCommand(PSResubmissonOutputVO resubmissonVO) {
		AddLandRequestCommand command = new AddLandRequestCommand();
		command.setArea("-1".equals(resubmissonVO.getAreaNo()) ? "" : resubmissonVO.getAreaNo());
		command.setAreablock("-1".equals(resubmissonVO.getAreaBlock()) ? "" : resubmissonVO.getAreaBlock());
		command.setAreaPlotNumber("-1".equals(resubmissonVO.getAreaPlotNo()) ? "" : resubmissonVO.getAreaPlotNo());
		command.setBlock("-1".equals(resubmissonVO.getSectorBlock()) ? "" : resubmissonVO.getSectorBlock());
		command.setLandUsage(resubmissonVO.getLandUsage());
		// command.setLocations(resubmissonVO.get);
		command.setPlotNumber("-1".equals(resubmissonVO.getSecPlotNo()) ? "" : resubmissonVO.getSecPlotNo());
		command.setSector("-1".equals(resubmissonVO.getSecSectorNo()) ? "" : resubmissonVO.getSecSectorNo());
		command.setSiteNumber(resubmissonVO.getSitePlanNo());
		command.setSubarea("-1".equals(resubmissonVO.getAreaSubArea()) ? "" : resubmissonVO.getAreaSubArea());
		command.setSubsector("-1".equals(resubmissonVO.getSubSectorNo()) ? "" : resubmissonVO.getSubSectorNo());
		command.setOptionalComments(resubmissonVO.getOptionalComments());
		command.setServiceAttachments(resubmissonVO.getLandDemarcationAttachments());
		if (resubmissonVO.getSubSectorNo() != null && resubmissonVO.getSecPlotNo() != null && resubmissonVO.getSecSectorNo() != null && !resubmissonVO.getSubSectorNo().equals("-1")
				&& !resubmissonVO.getSecPlotNo().equals("-1") && !resubmissonVO.getSecSectorNo().equals("-1")) {
			command.setLocations("sector");
		} else if (resubmissonVO.getAreaNo() != null && resubmissonVO.getAreaBlock() != null && resubmissonVO.getAreaSubArea() != null && !resubmissonVO.getAreaNo().equals("-1")
				&& !resubmissonVO.getAreaBlock().equals("-1") && !resubmissonVO.getAreaSubArea().equals("-1")) {
			command.setLocations("area");
		}

		// Related To Resubmit Request Detail which is earler passes through
		// session
		// this will fill as Hidden Value in JSP
		command.setSorceType(resubmissonVO.getSourcetype());
		command.setStausId(String.valueOf(resubmissonVO.getStatusid()));
		command.setRequestNo(resubmissonVO.getRequestNo());
		command.setOwnerName(resubmissonVO.getOwnername());
		command.setOwnerId(resubmissonVO.getOwnerId());
		command.setSubmittednocid(String.valueOf(resubmissonVO.getSubmittednocid()));
		command.setSitePalnDOcId(String.valueOf(resubmissonVO.getSiteplanDocId()));
		command.setLandValue(resubmissonVO.getLandValue());
		command.setCommeteRemark(resubmissonVO.getCommiteeRemarks());
		command.setTrueSitePlanDocId(String.valueOf(resubmissonVO.getTrueSitePlanDocumentid()));
		command.setCreatedBy(resubmissonVO.getCreatedby());
		command.setServiceId(String.valueOf(resubmissonVO.getServiceId()));

		return command;

	}

	public static GrantLandRequestCommand settingGrantLandCommand(PSGrandLandRequestVO pSGrandLandRequestVO) {
		GrantLandRequestCommand command = new GrantLandRequestCommand();
		command.setCurrentAddress(pSGrandLandRequestVO.getCurrentaddress());
		command.setEmployer(pSGrandLandRequestVO.getEmployer());
		// command.setFamilyBook(pSGrandLandRequestVO.getFamilyBookId());
		// command.setFamilyMembers(pSGrandLandRequestVO.getFamilyMembersNo());
		command.setMaritalStatus(pSGrandLandRequestVO.getMaritalStatus());
		command.setMonthlySalary(pSGrandLandRequestVO.getMonthlySalary());
		command.setSpousesEmiratesId(pSGrandLandRequestVO.getSpousesEmiratesId());
		return command;

	}

}
