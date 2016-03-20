package com.uaq.vo;

import java.util.Map;

import com.uaq.common.PropertiesUtil;

public class SendBackInfo {
	private String reviewComment;
	private Map<String, ServiceAttachment> reviewAttachments;
	private Map<String, ServiceAttachment> latestApplicantAttachment;

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public ServiceAttachment getReviewAttachment() {
		if (reviewAttachments != null && reviewAttachments.size() > 0)
			return reviewAttachments.values().iterator().next();
		return null;
	}

	public Map<String, ServiceAttachment> getReviewAttachments() {
		return reviewAttachments;
	}

	public void setReviewAttachments(Map<String, ServiceAttachment> reviewAttachment) {
		this.reviewAttachments = reviewAttachment;
	}

	public Map<String, ServiceAttachment> getLatestApplicantAttachment() {
		return latestApplicantAttachment;
	}

	public void setLatestApplicantAttachment(Map<String, ServiceAttachment> latestApplicantAttachment) {
		this.latestApplicantAttachment = latestApplicantAttachment;
	}

	public static class ServiceAttachment {
		private String docId;
		private String fileName;
		private String DID;
		private String viewUrl;
		private String attachmentId;

		public String getDocId() {
			return docId;
		}

		public void setDocId(String docId) {
			this.docId = docId;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getDID() {
			return DID;
		}

		public void setDID(String dID) {
			DID = dID;
		}
		
		public String getAttachmentId() {
			return attachmentId;
		}

		public void setAttachmentId(String attachmentId) {
			this.attachmentId = attachmentId;
		}

		public String getViewUrl() {
			String ucmIP = PropertiesUtil.getProperty("ucm.ip");
			viewUrl = viewUrl.replace("http://webcenter", "http://"+ucmIP);
			return viewUrl;
		}

		public void setViewUrl(String viewUrl) {
			this.viewUrl = viewUrl;
		}

		private enum ServiceAttachmentEnum {

			ATT_DOC_ID("docId"),
			FILE_NAME("fileName"),
			VIEWURL("viewUrl"),
			UCM_DID("DID"),
			ATTACHMENT_ID("attachmentId");
			private String memberName;

			private ServiceAttachmentEnum(String memberName) {
				this.memberName = memberName;
			}
		}

		public void setField(String nodeName, String textContent) {
			try {
				ServiceAttachmentEnum serviceAttachmentField = ServiceAttachmentEnum.valueOf(nodeName);
				getClass().getDeclaredField(serviceAttachmentField.memberName).set(this, textContent);
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}
}
