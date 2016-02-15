package com.uaq.vo;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author G Sekhar Vo class for New Supplier Registration
 */
public class NewSupplierRegistrationVO {

	public String establishmentName;
	public String tradeLicenceNumber;
	public String expiryDate;
	private String mobileNumber;
	private String email;
	private String address;
	private String officeNumber;
	private String postBox;
	private String supplierCategory;
	private String registrationType;
	private String emirates;
	private String languageId;
	
	
	
	/**
	 * @return the emirates
	 */
	public String getEmirates() {
		return emirates;
	}

	/**
	 * @param emirates the emirates to set
	 */
	public void setEmirates(String emirates) {
		this.emirates = emirates;
	}

	private List<UserAttachmentsVO> userAttacmentListVO;
	
	/**
	 * @return the userAttacmentListVO
	 */
	public List<UserAttachmentsVO> getUserAttacmentListVO() {
		return userAttacmentListVO;
	}

	/**
	 * @param userAttacmentListVO the userAttacmentListVO to set
	 */
	public void setUserAttacmentListVO(List<UserAttachmentsVO> userAttacmentListVO) {
		this.userAttacmentListVO = userAttacmentListVO;
	}

	private String [] registrationsType;
	
	private Map<String,String> fileList;
	
	private String tradeLicenceName;
	private String signatureAttestation;
	private String chamberOfCommerce;
	private String certificates;
	
	
	private String requestType;
	private String sourceType;
	private String userName;
	private String serviceId;
	private String suppRegId;
	private String suppCatgId;
	private String userCommnets;
	
	private Integer requestId;
	private String requestNo;
	private Integer workListId;
	private Integer supprReqId;
	private Integer stateId;
	private Integer tempSupprReqId;
	private String serviceName_En;
	private String serviceName_Ar;
	
	private Integer paymentId;
	private String paymentStatus;
	private String serviceFee;
	private String applicationFee;
	private String deptID;
	private String  serviceFeeDisc;
	private String EdirhamServCode;
	private String  isPaymentRequired;

	
	

	/**
	 * @return the registrationsType
	 */
	public String[] getRegistrationsType() {
		return registrationsType;
	}

	/**
	 * @param registrationsType the registrationsType to set
	 */
	public void setRegistrationsType(String[] registrationsType) {
		this.registrationsType = registrationsType;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getApplicationFee() {
		return applicationFee;
	}

	public void setApplicationFee(String applicationFee) {
		this.applicationFee = applicationFee;
	}

	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public String getServiceFeeDisc() {
		return serviceFeeDisc;
	}

	public void setServiceFeeDisc(String serviceFeeDisc) {
		this.serviceFeeDisc = serviceFeeDisc;
	}

	public String getEdirhamServCode() {
		return EdirhamServCode;
	}

	public void setEdirhamServCode(String edirhamServCode) {
		EdirhamServCode = edirhamServCode;
	}



	public String getIsPaymentRequired() {
		return isPaymentRequired;
	}

	public void setIsPaymentRequired(String isPaymentRequired) {
		this.isPaymentRequired = isPaymentRequired;
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public Integer getWorkListId() {
		return workListId;
	}

	public void setWorkListId(Integer workListId) {
		this.workListId = workListId;
	}

	public Integer getSupprReqId() {
		return supprReqId;
	}

	public void setSupprReqId(Integer supprReqId) {
		this.supprReqId = supprReqId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getTempSupprReqId() {
		return tempSupprReqId;
	}

	public void setTempSupprReqId(Integer tempSupprReqId) {
		this.tempSupprReqId = tempSupprReqId;
	}

	public String getServiceName_En() {
		return serviceName_En;
	}

	public void setServiceName_En(String serviceName_En) {
		this.serviceName_En = serviceName_En;
	}

	public String getServiceName_Ar() {
		return serviceName_Ar;
	}

	public void setServiceName_Ar(String serviceName_Ar) {
		this.serviceName_Ar = serviceName_Ar;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getSuppRegId() {
		return suppRegId;
	}

	public void setSuppRegId(String suppRegId) {
		this.suppRegId = suppRegId;
	}

	public String getSuppCatgId() {
		return suppCatgId;
	}

	public void setSuppCatgId(String suppCatgId) {
		this.suppCatgId = suppCatgId;
	}

	public String getUserCommnets() {
		return userCommnets;
	}

	public void setUserCommnets(String userCommnets) {
		this.userCommnets = userCommnets;
	}

	public String getEstablishmentName() {
		return establishmentName;
	}

	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}

	public String getTradeLicenceNumber() {
		return tradeLicenceNumber;
	}

	public void setTradeLicenceNumber(String tradeLicenceNumber) {
		this.tradeLicenceNumber = tradeLicenceNumber;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getPostBox() {
		return postBox;
	}

	public void setPostBox(String postBox) {
		this.postBox = postBox;
	}

	public String getSupplierCategory() {
		return supplierCategory;
	}

	public void setSupplierCategory(String supplierCategory) {
		this.supplierCategory = supplierCategory;
	}

	public String getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}

	

	public String getTradeLicenceName() {
		return tradeLicenceName;
	}

	public void setTradeLicenceName(String tradeLicenceName) {
		this.tradeLicenceName = tradeLicenceName;
	}

	public String getSignatureAttestation() {
		return signatureAttestation;
	}

	public void setSignatureAttestation(String signatureAttestation) {
		this.signatureAttestation = signatureAttestation;
	}

	public String getChamberOfCommerce() {
		return chamberOfCommerce;
	}

	public void setChamberOfCommerce(String chamberOfCommerce) {
		this.chamberOfCommerce = chamberOfCommerce;
	}

	public String getCertificates() {
		return certificates;
	}

	public void setCertificates(String certificates) {
		this.certificates = certificates;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public Map<String, String> getFileList() {
		return fileList;
	}

	public void setFileList(Map<String, String> fileList) {
		this.fileList = fileList;
	}

	

}
