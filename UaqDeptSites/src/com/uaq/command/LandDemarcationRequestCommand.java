package com.uaq.command;

import GetLandDemarcationReqDetails.ServiceAttachments;

public class LandDemarcationRequestCommand {
	private String sitePlanNumber;
	private String locations;
	private String sector;
	private String block;
	private String subsector;
	private String sectorPlotNumber;
	private String area;
	private String areablock;

	private String subarea;
	private String areaPlotNumber;
	private String landUsage;
	private String sitePlanDocument_name;

	private ServiceAttachments[] serviceAttachments;

	private String optionalComments;

	// Request Detail in Hidden for Resubmit.will populate from MyequestResubmit
	// COntroller
	private String id;

	private String requestNo;

	private String serviceId;

	private String sourcetype;

	private String createdby;

	private String statusid;

	private String ownername;

	private String ownerId;

	private String landValue;

	public String getSitePlanNumber() {
		return sitePlanNumber;
	}

	public void setSitePlanNumber(String sitePlanNumber) {
		this.sitePlanNumber = sitePlanNumber;
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

	public String getSectorPlotNumber() {
		return sectorPlotNumber;
	}

	public void setSectorPlotNumber(String sectorPlotNumber) {
		this.sectorPlotNumber = sectorPlotNumber;
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

	public ServiceAttachments[] getServiceAttachments() {
		return serviceAttachments;
	}

	public void setServiceAttachments(ServiceAttachments[] serviceAttachments) {
		this.serviceAttachments = serviceAttachments;
	}

	public String getOptionalComments() {
		return optionalComments;
	}

	public void setOptionalComments(String optionalComments) {
		this.optionalComments = optionalComments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
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

	public String getStatusid() {
		return statusid;
	}

	public void setStatusid(String statusid) {
		this.statusid = statusid;
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

	public String getLandValue() {
		return landValue;
	}

	public void setLandValue(String landValue) {
		this.landValue = landValue;
	}

}
