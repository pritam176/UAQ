package com.uaq.command;

import com.uaq.proxies.getwastecontainerreqdetails_client_ep.types.AttachmentListPayload;


public class WasteContainerCommand {
	private String findLocation;
	private String address;
	private String latitude;
	private String longitude;
	private String serviceType;
	private String replacement;
	private String ignore ;
	
	private String wasteCOntainoerId;
	private String requestNo;
	private String serviceId;
	private String sorceType;
	private String status;
	
	private AttachmentListPayload wasteContainerattachmentRecPayload;
	/**
	 * @return the wasteContainerattachmentRecPayload
	 */
	public AttachmentListPayload getWasteContainerattachmentRecPayload() {
		return wasteContainerattachmentRecPayload;
	}

	/**
	 * @param wasteContainerattachmentRecPayload the wasteContainerattachmentRecPayload to set
	 */
	public void setWasteContainerattachmentRecPayload(AttachmentListPayload wasteContainerattachmentRecPayload) {
		this.wasteContainerattachmentRecPayload = wasteContainerattachmentRecPayload;
	}

	/**
	 * @return the userComments
	 */
	public String getUserComments() {
		return userComments;
	}

	/**
	 * @param userComments the userComments to set
	 */
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	private String userComments;
	
	
	String wase_container_file_0_name;
	
	String wase_container_file_1_name;
	
	String wase_container_file_2_name;
	
	String wase_container_file_3_name;

	public String getFindLocation() {
		return findLocation;
	}

	public void setFindLocation(String findLocation) {
		this.findLocation = findLocation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getReplacement() {
		return replacement;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}

	public String getWase_container_file_0_name() {
		return wase_container_file_0_name;
	}

	public void setWase_container_file_0_name(String wase_container_file_0_name) {
		this.wase_container_file_0_name = wase_container_file_0_name;
	}

	public String getWase_container_file_1_name() {
		return wase_container_file_1_name;
	}

	public void setWase_container_file_1_name(String wase_container_file_1_name) {
		this.wase_container_file_1_name = wase_container_file_1_name;
	}

	public String getWase_container_file_2_name() {
		return wase_container_file_2_name;
	}

	public void setWase_container_file_2_name(String wase_container_file_2_name) {
		this.wase_container_file_2_name = wase_container_file_2_name;
	}

	public String getWase_container_file_3_name() {
		return wase_container_file_3_name;
	}

	public void setWase_container_file_3_name(String wase_container_file_3_name) {
		this.wase_container_file_3_name = wase_container_file_3_name;
	}

	public String getIgnore() {
		return ignore;
	}

	public void setIgnore(String ignore) {
		this.ignore = ignore;
	}

	public String getWasteCOntainoerId() {
		return wasteCOntainoerId;
	}

	public void setWasteCOntainoerId(String wasteCOntainoerId) {
		this.wasteCOntainoerId = wasteCOntainoerId;
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

	public String getSorceType() {
		return sorceType;
	}

	public void setSorceType(String sorceType) {
		this.sorceType = sorceType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	

}
