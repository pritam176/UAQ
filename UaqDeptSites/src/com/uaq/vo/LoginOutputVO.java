package com.uaq.vo;

import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

public class LoginOutputVO {

	private String tokenCode;

	private String username;

	private String acountId;

	private String status;

	private String typeOfUser;

	private XMLGregorianCalendar createdDate;

	private XMLGregorianCalendar lastUpdatedDate;

	private String executionStatus;

	private String message_EN;

	private String message_AR;

	public String getTokenCode() {
		return tokenCode;
	}

	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAcountId() {
		return acountId;
	}

	public void setAcountId(String acountId) {
		this.acountId = acountId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(String typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	public XMLGregorianCalendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(XMLGregorianCalendar createdDate) {
		this.createdDate = createdDate;
	}

	public XMLGregorianCalendar getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(XMLGregorianCalendar lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getExecutionStatus() {
		return executionStatus;
	}

	public void setExecutionStatus(String executionStatus) {
		this.executionStatus = executionStatus;
	}

	public String getMessage_EN() {
		return message_EN;
	}

	public void setMessage_EN(String message_EN) {
		this.message_EN = message_EN;
	}

	public String getMessage_AR() {
		return message_AR;
	}

	public void setMessage_AR(String message_AR) {
		this.message_AR = message_AR;
	}

	@Override
	public String toString() {
		return "LoginOutputVO [tokenCode=" + tokenCode + ", username=" + username + ", acountId=" + acountId + "]";
	}

	

}
