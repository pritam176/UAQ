package com.uaq.vo;

import java.math.BigDecimal;
import java.util.Calendar;

import GetAllRequestDetails.ServiceAttachments;


public class PSResubmissonOutputVO {
	
	String status;
	
	 
    private BigDecimal id;
     
    private String requestNo;
     
    private BigDecimal serviceId;
     
    private String sourcetype;
      
    private Calendar modifieddate;
     
    private Calendar createddate;
     
    private String createdby;
     
    private String modifiedby;
     
    private BigDecimal statusid;
   
    private  String  sitePlanNo;
   
    private  String  sitePlanDate;
    
    private  String  secSectorNo;
    
    private  String  sectorBlock;
   
    private  String  subSectorNo;
   
    private  String  secPlotNo;
    
    private  String  areaNo;
   
    private  String  areaBlock;
   
    private  String  areaSubArea;
   
    private  String  areaPlotNo;
    
    private String landUsage;
   
    private  BigDecimal  siteplanDocId;
   
    private  BigDecimal  requestNocLetterid;
   
    private  BigDecimal  submittednocid;
   
    private  String  ownername;
   
    private  String  ownerId;
   
    private  BigDecimal  ownerNationalityid;
   
    private  String  assignedtousername;
  
    private  String  landValue;
   
    private  String  commiteeRemarks;
   
    private  BigDecimal  trueSitePlanDocumentid;
   
    private  String  surveyorReport;
    
    private  BigDecimal  surveReportDocid;
    
    private String grandExpiryDate;
    
    private String granIssueDate;
    
    private ServiceAttachments[] addLandAttachments;
    private GetLandDemarcationReqDetails.ServiceAttachments[] LandDemarcationAttachments;
    private String landDemarcationModifiedDate;
    private GetIssueSitePlnRequestDetails.ServiceAttachments[] issueSitePlnAttachments;
    private GetAllRequestDetails.RequestNOCRecPayload [] nocAttachment;
    private GetExtensionOfGrantLndRequestDetails.ServiceAttachments[] extentionAttachmentList;
    
    /**
	 * @return the issueSitePlnAttachments
	 */
	public GetIssueSitePlnRequestDetails.ServiceAttachments[] getIssueSitePlnAttachments() {
		return issueSitePlnAttachments;
	}

	/**
	 * @param serviceAttachments the issueSitePlnAttachments to set
	 */
	public void setIssueSitePlnAttachments(GetIssueSitePlnRequestDetails.ServiceAttachments[] serviceAttachments) {
		this.issueSitePlnAttachments = serviceAttachments;
	}

	/**
	 * @return the landDemarcationModifiedDate
	 */
	public String getLandDemarcationModifiedDate() {
		return landDemarcationModifiedDate;
	}

	/**
	 * @param landDemarcationModifiedDate the landDemarcationModifiedDate to set
	 */
	public void setLandDemarcationModifiedDate(String landDemarcationModifiedDate) {
		this.landDemarcationModifiedDate = landDemarcationModifiedDate;
	}

	/**
	 * @return the landDemarcationAttachments
	 */
	public GetLandDemarcationReqDetails.ServiceAttachments[] getLandDemarcationAttachments() {
		return LandDemarcationAttachments;
	}

	/**
	 * @param landDemarcationAttachments the landDemarcationAttachments to set
	 */
	public void setLandDemarcationAttachments(GetLandDemarcationReqDetails.ServiceAttachments[] landDemarcationAttachments) {
		LandDemarcationAttachments = landDemarcationAttachments;
	}

	private String landDemarcationCreatedDate;
    
    /**
	 * @return the landDemarcationCreatedDate
	 */
	public String getLandDemarcationCreatedDate() {
		return landDemarcationCreatedDate;
	}

	/**
	 * @param landDemarcationCreatedDate the landDemarcationCreatedDate to set
	 */
	public void setLandDemarcationCreatedDate(String landDemarcationCreatedDate) {
		this.landDemarcationCreatedDate = landDemarcationCreatedDate;
	}

	/**
	 * @return the addLandAttachments
	 */
	public GetAllRequestDetails.ServiceAttachments[] getAddLandAttachments() {
		return addLandAttachments;
	}

	/**
	 * @param addLandAttachments the addLandAttachments to set
	 */
	public void setAddLandAttachments(GetAllRequestDetails.ServiceAttachments[] addLandAttachments) {
		this.addLandAttachments = addLandAttachments;
	}

	private String OptionalComments;
    
   
	/**
	 * @return the serviceAttachments
	 */
	public ServiceAttachments[] getServiceAttachments() {
		return addLandAttachments;
	}

	/**
	 * @param serviceAttachments2 the serviceAttachments to set
	 */
	public void setServiceAttachments(ServiceAttachments[] serviceAttachments) {
		this.addLandAttachments = serviceAttachments;
	}

	/**
	 * @return the optionalComments
	 */
	public String getOptionalComments() {
		return OptionalComments;
	}

	/**
	 * @param optionalComments the optionalComments to set
	 */
	public void setOptionalComments(String optionalComments) {
		OptionalComments = optionalComments;
	}

	/**
	 * @return the modifieddate
	 */
	public Calendar getModifieddate() {
		return modifieddate;
	}

	/**
	 * @param modifieddate the modifieddate to set
	 */
	public void setModifieddate(Calendar modifieddate) {
		this.modifieddate = modifieddate;
	}

	/**
	 * @return the createddate
	 */
	public Calendar getCreateddate() {
		return createddate;
	}

	/**
	 * @param createddate the createddate to set
	 */
	public void setCreateddate(Calendar createddate) {
		this.createddate = createddate;
	}

	/**
	 * @return the sitePlanDate
	 */
	public String getSitePlanDate() {
		return sitePlanDate;
	}

	/**
	 * @param sitePlanDate the sitePlanDate to set
	 */
	public void setSitePlanDate(String sitePlanDate) {
		this.sitePlanDate = sitePlanDate;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public BigDecimal getServiceId() {
		return serviceId;
	}

	public void setServiceId(BigDecimal serviceId) {
		this.serviceId = serviceId;
	}

	public String getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}


	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public BigDecimal getStatusid() {
		return statusid;
	}

	public void setStatusid(BigDecimal statusid) {
		this.statusid = statusid;
	}

	public String getSitePlanNo() {
		return sitePlanNo;
	}

	public void setSitePlanNo(String sitePlanNo) {
		this.sitePlanNo = sitePlanNo;
	}

	public String getSecSectorNo() {
		return secSectorNo;
	}

	public void setSecSectorNo(String secSectorNo) {
		this.secSectorNo = secSectorNo;
	}

	public String getSectorBlock() {
		return sectorBlock;
	}

	public void setSectorBlock(String sectorBlock) {
		this.sectorBlock = sectorBlock;
	}

	public String getSubSectorNo() {
		return subSectorNo;
	}

	public void setSubSectorNo(String subSectorNo) {
		this.subSectorNo = subSectorNo;
	}

	public String getSecPlotNo() {
		return secPlotNo;
	}

	public void setSecPlotNo(String secPlotNo) {
		this.secPlotNo = secPlotNo;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public String getAreaBlock() {
		return areaBlock;
	}

	public void setAreaBlock(String areaBlock) {
		this.areaBlock = areaBlock;
	}

	public String getAreaSubArea() {
		return areaSubArea;
	}

	public void setAreaSubArea(String areaSubArea) {
		this.areaSubArea = areaSubArea;
	}

	public String getAreaPlotNo() {
		return areaPlotNo;
	}

	public void setAreaPlotNo(String areaPlotNo) {
		this.areaPlotNo = areaPlotNo;
	}

	public String getLandUsage() {
		return landUsage;
	}

	public void setLandUsage(String landUsage) {
		this.landUsage = landUsage;
	}

	public BigDecimal getSiteplanDocId() {
		return siteplanDocId;
	}

	public void setSiteplanDocId(BigDecimal siteplanDocId) {
		this.siteplanDocId = siteplanDocId;
	}

	public BigDecimal getRequestNocLetterid() {
		return requestNocLetterid;
	}

	public void setRequestNocLetterid(BigDecimal requestNocLetterid) {
		this.requestNocLetterid = requestNocLetterid;
	}

	public BigDecimal getSubmittednocid() {
		return submittednocid;
	}

	public void setSubmittednocid(BigDecimal submittednocid) {
		this.submittednocid = submittednocid;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public BigDecimal getOwnerNationalityid() {
		return ownerNationalityid;
	}

	public void setOwnerNationalityid(BigDecimal ownerNationalityid) {
		this.ownerNationalityid = ownerNationalityid;
	}

	public String getAssignedtousername() {
		return assignedtousername;
	}

	public void setAssignedtousername(String assignedtousername) {
		this.assignedtousername = assignedtousername;
	}

	public String getLandValue() {
		return landValue;
	}

	public void setLandValue(String landValue) {
		this.landValue = landValue;
	}

	public String getCommiteeRemarks() {
		return commiteeRemarks;
	}

	public void setCommiteeRemarks(String commiteeRemarks) {
		this.commiteeRemarks = commiteeRemarks;
	}

	public BigDecimal getTrueSitePlanDocumentid() {
		return trueSitePlanDocumentid;
	}

	public void setTrueSitePlanDocumentid(BigDecimal trueSitePlanDocumentid) {
		this.trueSitePlanDocumentid = trueSitePlanDocumentid;
	}

	public String getSurveyorReport() {
		return surveyorReport;
	}

	public void setSurveyorReport(String surveyorReport) {
		this.surveyorReport = surveyorReport;
	}

	public BigDecimal getSurveReportDocid() {
		return surveReportDocid;
	}

	public void setSurveReportDocid(BigDecimal surveReportDocid) {
		this.surveReportDocid = surveReportDocid;
	}

	public GetAllRequestDetails.RequestNOCRecPayload[] getNocAttachment() {
		return nocAttachment;
	}

	public void setNocAttachment(GetAllRequestDetails.RequestNOCRecPayload[] nocAttachment) {
		this.nocAttachment = nocAttachment;
	}

	public GetExtensionOfGrantLndRequestDetails.ServiceAttachments[] getExtentionAttachmentList() {
		return extentionAttachmentList;
	}

	public void setExtentionAttachmentList(GetExtensionOfGrantLndRequestDetails.ServiceAttachments[] extentionAttachmentList) {
		this.extentionAttachmentList = extentionAttachmentList;
	}

	

	

	public String getGrandExpiryDate() {
		return grandExpiryDate;
	}

	public void setGrandExpiryDate(String grandExpiryDate) {
		this.grandExpiryDate = grandExpiryDate;
	}

	public String getGranIssueDate() {
		return granIssueDate;
	}

	public void setGranIssueDate(String granIssueDate) {
		this.granIssueDate = granIssueDate;
	}

}
