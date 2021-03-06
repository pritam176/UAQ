package com.uaq.vo;

import java.math.BigDecimal;
import java.util.Calendar;

import com.uaq.proxies.getallrequestdetails_client_ep.types.RequestNOCPayload;
import com.uaq.proxies.getlanddemarcationreqdetails_client_ep.types.AttachmentListPayload;


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
    
    private String OptionalComments;
    
    private String landDemarcationModifiedDate;
    
    private String landDemarcationCreatedDate;
    
    private com.uaq.proxies.getallrequestdetails_client_ep.types.AttachmentListPayload addLandAttachments;
    private AttachmentListPayload landDemarcationAttachments;
   
    private com.uaq.proxies.getissuesiteplanreqdetails_client_ep.types.AttachmentListPayload issueSitePlnAttachments;
    private RequestNOCPayload nocAttachment;
    private com.uaq.proxies.getextensionofgrantlandbpelprocess_client_ep.types.AttachmentListPayload extentionAttachmentList;
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
	public Calendar getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(Calendar modifieddate) {
		this.modifieddate = modifieddate;
	}
	public Calendar getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Calendar createddate) {
		this.createddate = createddate;
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
	public String getSitePlanDate() {
		return sitePlanDate;
	}
	public void setSitePlanDate(String sitePlanDate) {
		this.sitePlanDate = sitePlanDate;
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
	public String getOptionalComments() {
		return OptionalComments;
	}
	public void setOptionalComments(String optionalComments) {
		OptionalComments = optionalComments;
	}
	public String getLandDemarcationModifiedDate() {
		return landDemarcationModifiedDate;
	}
	public void setLandDemarcationModifiedDate(String landDemarcationModifiedDate) {
		this.landDemarcationModifiedDate = landDemarcationModifiedDate;
	}
	public String getLandDemarcationCreatedDate() {
		return landDemarcationCreatedDate;
	}
	public void setLandDemarcationCreatedDate(String landDemarcationCreatedDate) {
		this.landDemarcationCreatedDate = landDemarcationCreatedDate;
	}
	public com.uaq.proxies.getallrequestdetails_client_ep.types.AttachmentListPayload getAddLandAttachments() {
		return addLandAttachments;
	}
	public void setAddLandAttachments(com.uaq.proxies.getallrequestdetails_client_ep.types.AttachmentListPayload addLandAttachments) {
		this.addLandAttachments = addLandAttachments;
	}
	public AttachmentListPayload getLandDemarcationAttachments() {
		return landDemarcationAttachments;
	}
	public void setLandDemarcationAttachments(AttachmentListPayload landDemarcationAttachments) {
		this.landDemarcationAttachments = landDemarcationAttachments;
	}
	public com.uaq.proxies.getissuesiteplanreqdetails_client_ep.types.AttachmentListPayload getIssueSitePlnAttachments() {
		return issueSitePlnAttachments;
	}
	public void setIssueSitePlnAttachments(com.uaq.proxies.getissuesiteplanreqdetails_client_ep.types.AttachmentListPayload issueSitePlnAttachments) {
		this.issueSitePlnAttachments = issueSitePlnAttachments;
	}
	public RequestNOCPayload getNocAttachment() {
		return nocAttachment;
	}
	public void setNocAttachment(RequestNOCPayload nocAttachment) {
		this.nocAttachment = nocAttachment;
	}
	public com.uaq.proxies.getextensionofgrantlandbpelprocess_client_ep.types.AttachmentListPayload getExtentionAttachmentList() {
		return extentionAttachmentList;
	}
	public void setExtentionAttachmentList(com.uaq.proxies.getextensionofgrantlandbpelprocess_client_ep.types.AttachmentListPayload extentionAttachmentList) {
		this.extentionAttachmentList = extentionAttachmentList;
	}
    
    

	

	
}
