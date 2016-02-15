package com.uaq.command;

import GetAllRequestDetails.ServiceAttachments;

public class AddLandRequestCommand {
	private String siteNumber;
	private String locations;
	private String sector;
	private String block;
	private String subsector;
	private String plotNumber;
	private String area;
	private String areablock;
	private String subarea;
	private String areaPlotNumber;
	private String landUsage;
	private String sitePlanDocument_name;

	// Related To Resubmit Request Detail which is earler passes through session
	private String requestNo;
	private String stausId;
	private String serviceId;

	private String sorceType;
	private String ownerName;
	private String ownerId;

	private String submittednocid;

	private String sitePalnDOcId;
	private String landValue;
	private String commeteRemark;
	private String trueSitePlanDocId;
	private String createdBy;

	/**
	 * @return the optionalComments
	 */
	public String getOptionalComments() {
		return optionalComments;
	}

	/**
	 * @param optionalComments
	 *            the optionalComments to set
	 */
	public void setOptionalComments(String optionalComments) {
		this.optionalComments = optionalComments;
	}

	/**
	 * @return the serviceAttachments
	 */
	public ServiceAttachments[] getServiceAttachments() {
		return serviceAttachments;
	}

	/**
	 * @param serviceAttachments
	 *            the serviceAttachments to set
	 */
	public void setServiceAttachments(ServiceAttachments[] serviceAttachments) {
		this.serviceAttachments = serviceAttachments;
	}

	private String optionalComments;
	private ServiceAttachments[] serviceAttachments;

	public String getSiteNumber() {
		return siteNumber;
	}

	public void setSiteNumber(String siteNumber) {

		this.siteNumber = siteNumber;
	}

	public String getLocations() {
		return locations;
	}

	public void setLocations(String locations) {
		this.locations = locations;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getSubsector() {
		return subsector;
	}

	public void setSubsector(String subsector) {
		this.subsector = subsector;
	}

	public String getPlotNumber() {
		return plotNumber;
	}

	public void setPlotNumber(String plotNumber) {
		this.plotNumber = plotNumber;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreablock() {
		return areablock;
	}

	public void setAreablock(String areablock) {
		this.areablock = areablock;
	}

	public String getSubarea() {
		return subarea;
	}

	public void setSubarea(String subarea) {
		this.subarea = subarea;
	}

	public String getAreaPlotNumber() {
		return areaPlotNumber;
	}

	public void setAreaPlotNumber(String areaPlotNumber) {
		this.areaPlotNumber = areaPlotNumber;
	}

	public String getLandUsage() {
		return landUsage;
	}

	public void setLandUsage(String landUsage) {
		this.landUsage = landUsage;
	}

	public String getSitePlanDocument_name() {
		return sitePlanDocument_name;
	}

	public void setSitePlanDocument_name(String sitePlanDocument_name) {
		this.sitePlanDocument_name = sitePlanDocument_name;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getStausId() {
		return stausId;
	}

	public void setStausId(String stausId) {
		this.stausId = stausId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getSorceType() {
		return sorceType;
	}

	public void setSorceType(String sorceType) {
		this.sorceType = sorceType;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getSubmittednocid() {
		return submittednocid;
	}

	public void setSubmittednocid(String submittednocid) {
		this.submittednocid = submittednocid;
	}

	public String getSitePalnDOcId() {
		return sitePalnDOcId;
	}

	public void setSitePalnDOcId(String sitePalnDOcId) {
		this.sitePalnDOcId = sitePalnDOcId;
	}

	public String getLandValue() {
		return landValue;
	}

	public void setLandValue(String landValue) {
		this.landValue = landValue;
	}

	public String getCommeteRemark() {
		return commeteRemark;
	}

	public void setCommeteRemark(String commeteRemark) {
		this.commeteRemark = commeteRemark;
	}

	public String getTrueSitePlanDocId() {
		return trueSitePlanDocId;
	}

	public void setTrueSitePlanDocId(String trueSitePlanDocId) {
		this.trueSitePlanDocId = trueSitePlanDocId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
