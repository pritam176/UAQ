package com.uaq.vo;

import java.math.BigDecimal;

import javax.xml.datatype.XMLGregorianCalendar;

public class MyRequestOutputVO {

	private String requestNo;
	private BigDecimal StatusId;
	private BigDecimal ServiceId;
	private XMLGregorianCalendar CreatedDate;
	private XMLGregorianCalendar ModifiedDate;

	private String statusName_EN;
	private String servicename_EN;
	private String statusName_AR;
	private String servicename_AR;
	private String sorceType;
	private String userName;
	private String serviceExecution;
	

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public BigDecimal getStatusId() {
		return StatusId;
	}

	public void setStatusId(BigDecimal statusId) {
		StatusId = statusId;
	}

	public BigDecimal getServiceId() {
		return ServiceId;
	}

	public void setServiceId(BigDecimal serviceId) {
		ServiceId = serviceId;
	}

	public XMLGregorianCalendar getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(XMLGregorianCalendar createdDate) {
		CreatedDate = createdDate;
	}

	public XMLGregorianCalendar getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(XMLGregorianCalendar modifiedDate) {
		ModifiedDate = modifiedDate;
	}

	public String getStatusName_EN() {
		return statusName_EN;
	}

	public void setStatusName_EN(String statusName_EN) {
		this.statusName_EN = statusName_EN;
	}

	public String getServicename_EN() {
		return servicename_EN;
	}

	public void setServicename_EN(String servicename_EN) {
		this.servicename_EN = servicename_EN;
	}

	public String getStatusName_AR() {
		return statusName_AR;
	}

	public void setStatusName_AR(String statusName_AR) {
		this.statusName_AR = statusName_AR;
	}

	public String getServicename_AR() {
		return servicename_AR;
	}

	public void setServicename_AR(String servicename_AR) {
		this.servicename_AR = servicename_AR;
	}

	public String getSorceType() {
		return sorceType;
	}

	public void setSorceType(String sorceType) {
		this.sorceType = sorceType;
	}

	public String getServiceExecution() {
		return serviceExecution;
	}

	public void setServiceExecution(String serviceExecution) {
		this.serviceExecution = serviceExecution;
	}

}
