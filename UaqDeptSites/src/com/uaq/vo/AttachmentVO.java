package com.uaq.vo;

public class AttachmentVO {
	
	private String contentID; 
	
	private String requestId; 
	
	private String attachmentName; 
	
	private String attachmentType; 
	
	private String attachmentType_EN; 
	
	private String attachmentType_AR; 
	
	private String downloadURL; 
	
	private String viewURL;

	public String getContentID() {
		return contentID;
	}

	public void setContentID(String contentID) {
		this.contentID = contentID;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public String getAttachmentType_EN() {
		return attachmentType_EN;
	}

	public void setAttachmentType_EN(String attachmentTypeName_EN) {
		this.attachmentType_EN = attachmentTypeName_EN;
	}

	public String getAttachmentType_AR() {
		return attachmentType_AR;
	}

	public void setAttachmentType_AR(String attachmentType_AR) {
		this.attachmentType_AR = attachmentType_AR;
	}

	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	public String getViewURL() {
		return viewURL;
	}

	public void setViewURL(String viewURL) {
		this.viewURL = viewURL;
	} 

}
