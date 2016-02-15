package com.uaq.vo;

import java.util.Map;

public class SendBackInfo {
	private String reviewComment;
	private ServiceAttachment reviewAttachment;
	private Map<String, ServiceAttachment> latestApplicantAttachment;

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public ServiceAttachment getReviewAttachment() {
		return reviewAttachment;
	}

	public void setReviewAttachment(ServiceAttachment reviewAttachment) {
		this.reviewAttachment = reviewAttachment;
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

		public String getViewUrl() {
			return viewUrl;
		}

		public void setViewUrl(String viewUrl) {
			this.viewUrl = viewUrl;
		}

		private enum ServiceAttachmentEnum {

			ATT_DOC_ID("docId"),
			FILE_NAME("fileName"),
			VIEWURL("viewUrl"),
			UCM_DID("DID");

			private String memberName;

			private ServiceAttachmentEnum(String memberName) {
				this.memberName = memberName;
			}
		}

		public void setField(String nodeName, String textContent) {
			try {
				ServiceAttachmentEnum serviceAttachmentField = ServiceAttachmentEnum.valueOf(nodeName);
				getClass().getDeclaredField(serviceAttachmentField.memberName).set(this, textContent);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
//				e.printStackTrace();
			}
		}
	}
}
