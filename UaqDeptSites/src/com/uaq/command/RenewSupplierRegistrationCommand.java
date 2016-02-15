package com.uaq.command;

import org.springframework.web.multipart.MultipartFile;

public class RenewSupplierRegistrationCommand {
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
	private MultipartFile tradingLicence;
	private MultipartFile signatureAttestation;
	private MultipartFile chamberOfCommerce;
	private MultipartFile certificates;

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

	public MultipartFile getTradingLicence() {
		return tradingLicence;
	}

	public void setTradingLicence(MultipartFile tradingLicence) {
		this.tradingLicence = tradingLicence;
	}

	public MultipartFile getSignatureAttestation() {
		return signatureAttestation;
	}

	public void setSignatureAttestation(MultipartFile signatureAttestation) {
		this.signatureAttestation = signatureAttestation;
	}

	public MultipartFile getChamberOfCommerce() {
		return chamberOfCommerce;
	}

	public void setChamberOfCommerce(MultipartFile chamberOfCommerce) {
		this.chamberOfCommerce = chamberOfCommerce;
	}

	public MultipartFile getCertificates() {
		return certificates;
	}

	public void setCertificates(MultipartFile certificates) {
		this.certificates = certificates;
	}

}
