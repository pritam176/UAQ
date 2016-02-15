package com.uaq.vo;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.datatype.XMLGregorianCalendar;

import com.oracle.xmlns.UAQ_LP_Twimc_App.UAQ_LP_Twimc_Prj.UAQ_LP_Twimc_BPEL.AttachmentRecPayload;

public class LPtoWhomeConcernVO {

	private BigInteger towhomeReqId;

	private BigInteger reqId;

	private BigInteger servId;

	private BigInteger accountId;

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

	private BigDecimal addressedtoId;

	private String executionSTatus;
	
	private  AttachmentRecPayload[] attachmentRecPayload;
	
	private String comments;
	

	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}

	/**
	 * @return the attachmentRecPayload
	 */
	public AttachmentRecPayload[] getAttachmentRecPayload() {
		return attachmentRecPayload;
	}

	/**
	 * @param attachmentRecPayload the attachmentRecPayload to set
	 */
	public void setAttachmentRecPayload(AttachmentRecPayload[] attachmentRecPayload) {
		this.attachmentRecPayload = attachmentRecPayload;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigInteger getTowhomeReqId() {
		return towhomeReqId;
	}

	public void setTowhomeReqId(BigInteger towhomeReqId) {
		this.towhomeReqId = towhomeReqId;
	}

	public BigInteger getReqId() {
		return reqId;
	}

	public void setReqId(BigInteger reqId) {
		this.reqId = reqId;
	}

	public BigInteger getServId() {
		return servId;
	}

	public void setServId(BigInteger servId) {
		this.servId = servId;
	}

	public BigInteger getAccountId() {
		return accountId;
	}

	public void setAccountId(BigInteger accountId) {
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

	public BigDecimal getAddressedtoId() {
		return addressedtoId;
	}

	public void setAddressedtoId(BigDecimal addressedtoId) {
		this.addressedtoId = addressedtoId;
	}

	public String getExecutionSTatus() {
		return executionSTatus;
	}

	public void setExecutionSTatus(String executionSTatus) {
		this.executionSTatus = executionSTatus;
	}

}
