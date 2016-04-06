package com.uaq.vo;

import java.math.BigInteger;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

public class LPtoWhomeConcernVO {

	private BigInteger towhomeReqId;

	private BigInteger reqId;

	private String servId;

	private String accountId;

	private String familyBookNum;

	private String spouseId;

	private String mobile;

	private String status;

	private String ceratedBy;
	
	private String other;

	private XMLGregorianCalendar createdDate;

	private String modifiedBy;

	private XMLGregorianCalendar modifiedDate;

	private String attchmentId;

	private String requestNo;

	private String addressedtoId;

	private String executionSTatus;
	
	private  List<AttachmentVO> attachmentList;
	
	private String comments;

	public BigInteger getTowhomeReqId() {
		return towhomeReqId;
	}

	public void setTowhomeReqId(BigInteger bigInteger) {
		this.towhomeReqId = bigInteger;
	}

	public BigInteger getReqId() {
		return reqId;
	}

	public void setReqId(BigInteger bigInteger) {
		this.reqId = bigInteger;
	}

	public String getServId() {
		return servId;
	}

	public void setServId(String servId) {
		this.servId = servId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getFamilyBookNum() {
		return familyBookNum;
	}

	public void setFamilyBookNum(String familyBookNum) {
		this.familyBookNum = familyBookNum;
	}

	public String getSpouseId() {
		return spouseId;
	}

	public void setSpouseId(String spouseId) {
		this.spouseId = spouseId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCeratedBy() {
		return ceratedBy;
	}

	public void setCeratedBy(String ceratedBy) {
		this.ceratedBy = ceratedBy;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public XMLGregorianCalendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(XMLGregorianCalendar createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public XMLGregorianCalendar getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(XMLGregorianCalendar modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getAttchmentId() {
		return attchmentId;
	}

	public void setAttchmentId(String attchmentId) {
		this.attchmentId = attchmentId;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getAddressedtoId() {
		return addressedtoId;
	}

	public void setAddressedtoId(String string) {
		this.addressedtoId = string;
	}

	public String getExecutionSTatus() {
		return executionSTatus;
	}

	public void setExecutionSTatus(String executionSTatus) {
		this.executionSTatus = executionSTatus;
	}

	

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<AttachmentVO> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<AttachmentVO> attachmentList) {
		this.attachmentList = attachmentList;
	}
	

	
}
