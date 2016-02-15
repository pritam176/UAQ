package com.uaq.util;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.uaq.service.WebServiceInvoker;
import com.tacme.uaq.services.utils.ucm.UCMUtilities;

public class UCMUploader {

	private UCMUtilities ucm = new UCMUtilities();

	public UCMUploader() {
		ucm.login("weblogic");
	}

	public boolean genericUploadAttachment(List<AttachmentInfo> attachmentList) {
		if (attachmentList != null && attachmentList.size() > 0) {
			for (AttachmentInfo attachment : attachmentList) {
				String contentId = "uaq".concat("-").concat(((Long) new Date().getTime()).toString());
				try {
					String fileName = attachment.getFilename();
					Map<String, String> docInfo = ucm.uploadReturnDocInfo(contentId, attachment.getAttachmentType().replace(" ", "_"), fileName, attachment.getInputStream(), null);
					attachment.setDocInfo(docInfo);
				} catch (Exception e) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean genericSaveAttachmentToDB(List<AttachmentInfo> attachmentList) {
		if (attachmentList != null && attachmentList.size() > 0) {
			for (AttachmentInfo attachment : attachmentList) {
				try {
					String fileName = attachment.getFilename();
					Map<String, String> attachmentParams = new HashMap<String, String>();
					attachmentParams.put("requestId", attachment.getRequestId());
					attachmentParams.put("attDocId", attachment.getDocTypeId());
					attachmentParams.put("serviceId", attachment.getServiceId());
					attachmentParams.put("fileName", fileName);
					attachmentParams.put("ucmDid", attachment.getDocInfo().get("DID"));
					attachmentParams.put("viewurl", attachment.getDocInfo().get("DocUrl"));
					WebServiceInvoker.uploadAttachmentToDatabase(attachmentParams);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public static class AttachmentInfo {

		private String fileName;
		private String attachmentType;
		private String docTypeId;
		private InputStream inputStream;
		private String serviceId;
		private String requestId;
		private Map<String, String> docInfo;

		public String getFilename() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		public String getAttachmentType() {
			return attachmentType;
		}

		public void setAttachmentType(String attachmentType) {
			this.attachmentType = attachmentType;
		}

		public String getDocTypeId() {
			return docTypeId;
		}

		public void setDocTypeId(String docTypeId) {
			this.docTypeId = docTypeId;
		}

		public String getServiceId() {
			return serviceId;
		}

		public void setServiceId(String serviceId) {
			this.serviceId = serviceId;
		}

		public String getRequestId() {
			return requestId;
		}

		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}

		public Map<String, String> getDocInfo() {
			return docInfo;
		}
		
		public void setDocInfo(Map<String, String> docInfo) {
			this.docInfo = docInfo;
		}
	}

}
