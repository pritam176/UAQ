package com.uaq.vo;

import java.util.List;

public class AccountDetailTokenOutputVO {

	private java.lang.String userName;

	private java.lang.String accountId;

	private java.lang.String password;

	private java.lang.String mobileNo;

	private java.lang.String emailAddress;

	private java.lang.String sourceId;

	private java.lang.String accountStatusId;

	private java.lang.String accountStatus_EN;

	private java.lang.String accountStatus_AR;

	private java.lang.String retryCount;

	private java.lang.String languageId;

	private java.lang.String pushNotificationEnabled;

	private java.lang.String emailNotificationEnabled;

	private java.lang.String smsNotificationEnabled;

	private java.lang.String termsConditionsFlag;

	private java.util.Calendar createdDate;

	private java.lang.String profileImageId;

	private java.util.Calendar modifiedDate;

	private java.lang.String createdby;

	private java.lang.String modifiedby;

	private java.lang.String loginusername;

	private java.lang.String id;

	private java.lang.String emiratesId;

	private java.lang.String tradeLienceNo;

	private java.lang.String firstName;

	private java.lang.String middleName;

	private java.lang.String lastName;

	private java.util.Calendar dob;

	private java.lang.String homePhone;

	private java.lang.String addressline1;

	private java.lang.String addressline2;

	private java.lang.String emirate;

	private java.lang.String postbox;

	private java.lang.String nationalityId;

	private java.lang.String nationality_EN;

	private java.lang.String nationality_AR;

	private java.lang.String emiratesCode;

	private java.lang.String emirates_EN;

	private java.lang.String emirates_AR;

	private java.lang.String typeOfUser;

	private java.lang.String eidaExpiryDate;

	private java.lang.String familyBookNum;

	private java.lang.String passportNo;

	private java.lang.String passportExpiryDate;

	private java.lang.String residenceNo;

	private java.lang.String residenseExpiryDate;

	private java.lang.String tradeLienceExpiryDate;

	private java.lang.String applicanttypeid;

	private java.lang.String applicanttype_EN;

	private java.lang.String applicanttype_AR;

	private java.lang.String subcribetonewsletterflag;

	private java.lang.String countryidofcitizenshipid;

	private java.lang.String countryidofcitizenship_EN;

	private java.lang.String countryidofcitizenship_AR;

	private java.lang.String countryidofresidencyid;

	private java.lang.String countryidofresidency_EN;

	private java.lang.String countryidofresidency_AR;

	private java.lang.String hasfamilybookno;

	private java.lang.String eidaexpirydate;

	private java.lang.String townname;

	private java.lang.String townnumber;

	private java.lang.String familynumber;

	private java.lang.String tribename;

	private java.lang.String clannumber;

	private java.lang.String issuancedate;

	private java.lang.String mothername;

	private java.lang.String mothersfathername;

	private java.lang.String tradelicensetypeid;

	private java.lang.String tradelicensetype_EN;

	private java.lang.String tradelicensetype_AR;

	private java.lang.String mobileno2;

	private java.lang.String website;
	
	private String message_EN;
	
	private String message_AR;
	
	private String executionStatus;
	
	private List<UserAttachmentsVO> userAttachmentsList;
	
	
	/**
	 * @return the userAttachmentsList
	 */
	public List<UserAttachmentsVO> getUserAttachmentsList() {
		return userAttachmentsList;
	}

	/**
	 * @param userAttachmentsList the userAttachmentsList to set
	 */
	public void setUserAttachmentsList(List<UserAttachmentsVO> userAttachmentsList) {
		this.userAttachmentsList = userAttachmentsList;
	}

	

	@Override
	public String toString() {
		return "AccountDetailTokenOutputVO [userName=" + userName + ", accountId=" + accountId + ", mobileNo=" + mobileNo + "]";
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getAccountId() {
		return accountId;
	}

	public void setAccountId(java.lang.String accountId) {
		this.accountId = accountId;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(java.lang.String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public java.lang.String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(java.lang.String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public java.lang.String getSourceId() {
		return sourceId;
	}

	public void setSourceId(java.lang.String sourceId) {
		this.sourceId = sourceId;
	}

	public java.lang.String getAccountStatusId() {
		return accountStatusId;
	}

	public void setAccountStatusId(java.lang.String accountStatusId) {
		this.accountStatusId = accountStatusId;
	}

	public java.lang.String getAccountStatus_EN() {
		return accountStatus_EN;
	}

	public void setAccountStatus_EN(java.lang.String accountStatus_EN) {
		this.accountStatus_EN = accountStatus_EN;
	}

	public java.lang.String getAccountStatus_AR() {
		return accountStatus_AR;
	}

	public void setAccountStatus_AR(java.lang.String accountStatus_AR) {
		this.accountStatus_AR = accountStatus_AR;
	}

	public java.lang.String getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(java.lang.String retryCount) {
		this.retryCount = retryCount;
	}

	public java.lang.String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(java.lang.String languageId) {
		this.languageId = languageId;
	}

	public java.lang.String getPushNotificationEnabled() {
		return pushNotificationEnabled;
	}

	public void setPushNotificationEnabled(java.lang.String pushNotificationEnabled) {
		this.pushNotificationEnabled = pushNotificationEnabled;
	}

	public java.lang.String getEmailNotificationEnabled() {
		return emailNotificationEnabled;
	}

	public void setEmailNotificationEnabled(java.lang.String emailNotificationEnabled) {
		this.emailNotificationEnabled = emailNotificationEnabled;
	}

	public java.lang.String getSmsNotificationEnabled() {
		return smsNotificationEnabled;
	}

	public void setSmsNotificationEnabled(java.lang.String smsNotificationEnabled) {
		this.smsNotificationEnabled = smsNotificationEnabled;
	}

	public java.lang.String getTermsConditionsFlag() {
		return termsConditionsFlag;
	}

	public void setTermsConditionsFlag(java.lang.String termsConditionsFlag) {
		this.termsConditionsFlag = termsConditionsFlag;
	}

	public java.util.Calendar getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(java.util.Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public java.lang.String getProfileImageId() {
		return profileImageId;
	}

	public void setProfileImageId(java.lang.String profileImageId) {
		this.profileImageId = profileImageId;
	}

	public java.util.Calendar getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(java.util.Calendar modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public java.lang.String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(java.lang.String createdby) {
		this.createdby = createdby;
	}

	public java.lang.String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(java.lang.String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public java.lang.String getLoginusername() {
		return loginusername;
	}

	public void setLoginusername(java.lang.String loginusername) {
		this.loginusername = loginusername;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getEmiratesId() {
		return emiratesId;
	}

	public void setEmiratesId(java.lang.String emiratesId) {
		this.emiratesId = emiratesId;
	}

	public java.lang.String getTradeLienceNo() {
		return tradeLienceNo;
	}

	public void setTradeLienceNo(java.lang.String tradeLienceNo) {
		this.tradeLienceNo = tradeLienceNo;
	}

	public java.lang.String getFirstName() {
		return firstName;
	}

	public void setFirstName(java.lang.String firstName) {
		this.firstName = firstName;
	}

	public java.lang.String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(java.lang.String middleName) {
		this.middleName = middleName;
	}

	public java.lang.String getLastName() {
		return lastName;
	}

	public void setLastName(java.lang.String lastName) {
		this.lastName = lastName;
	}

	public java.util.Calendar getDob() {
		return dob;
	}

	public void setDob(java.util.Calendar dob) {
		this.dob = dob;
	}

	public java.lang.String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(java.lang.String homePhone) {
		this.homePhone = homePhone;
	}

	public java.lang.String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(java.lang.String addressline1) {
		this.addressline1 = addressline1;
	}

	public java.lang.String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(java.lang.String addressline2) {
		this.addressline2 = addressline2;
	}

	public java.lang.String getEmirate() {
		return emirate;
	}

	public void setEmirate(java.lang.String emirate) {
		this.emirate = emirate;
	}

	public java.lang.String getPostbox() {
		return postbox;
	}

	public void setPostbox(java.lang.String postbox) {
		this.postbox = postbox;
	}

	public java.lang.String getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(java.lang.String nationalityId) {
		this.nationalityId = nationalityId;
	}

	public java.lang.String getNationality_EN() {
		return nationality_EN;
	}

	public void setNationality_EN(java.lang.String nationality_EN) {
		this.nationality_EN = nationality_EN;
	}

	public java.lang.String getNationality_AR() {
		return nationality_AR;
	}

	public void setNationality_AR(java.lang.String nationality_AR) {
		this.nationality_AR = nationality_AR;
	}

	public java.lang.String getEmiratesCode() {
		return emiratesCode;
	}

	public void setEmiratesCode(java.lang.String emiratesCode) {
		this.emiratesCode = emiratesCode;
	}

	public java.lang.String getEmirates_EN() {
		return emirates_EN;
	}

	public void setEmirates_EN(java.lang.String emirates_EN) {
		this.emirates_EN = emirates_EN;
	}

	public java.lang.String getEmirates_AR() {
		return emirates_AR;
	}

	public void setEmirates_AR(java.lang.String emirates_AR) {
		this.emirates_AR = emirates_AR;
	}

	public java.lang.String getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(java.lang.String typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	public java.lang.String getEidaExpiryDate() {
		return eidaExpiryDate;
	}

	public void setEidaExpiryDate(java.lang.String eidaExpiryDate) {
		this.eidaExpiryDate = eidaExpiryDate;
	}

	public java.lang.String getFamilyBookNum() {
		return familyBookNum;
	}

	public void setFamilyBookNum(java.lang.String familyBookNum) {
		this.familyBookNum = familyBookNum;
	}

	public java.lang.String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(java.lang.String passportNo) {
		this.passportNo = passportNo;
	}

	public java.lang.String getPassportExpiryDate() {
		return passportExpiryDate;
	}

	public void setPassportExpiryDate(java.lang.String passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}

	public java.lang.String getResidenceNo() {
		return residenceNo;
	}

	public void setResidenceNo(java.lang.String residenceNo) {
		this.residenceNo = residenceNo;
	}

	public java.lang.String getResidenseExpiryDate() {
		return residenseExpiryDate;
	}

	public void setResidenseExpiryDate(java.lang.String residenseExpiryDate) {
		this.residenseExpiryDate = residenseExpiryDate;
	}

	public java.lang.String getTradeLienceExpiryDate() {
		return tradeLienceExpiryDate;
	}

	public void setTradeLienceExpiryDate(java.lang.String tradeLienceExpiryDate) {
		this.tradeLienceExpiryDate = tradeLienceExpiryDate;
	}

	public java.lang.String getApplicanttypeid() {
		return applicanttypeid;
	}

	public void setApplicanttypeid(java.lang.String applicanttypeid) {
		this.applicanttypeid = applicanttypeid;
	}

	public java.lang.String getApplicanttype_EN() {
		return applicanttype_EN;
	}

	public void setApplicanttype_EN(java.lang.String applicanttype_EN) {
		this.applicanttype_EN = applicanttype_EN;
	}

	public java.lang.String getApplicanttype_AR() {
		return applicanttype_AR;
	}

	public void setApplicanttype_AR(java.lang.String applicanttype_AR) {
		this.applicanttype_AR = applicanttype_AR;
	}

	public java.lang.String getSubcribetonewsletterflag() {
		return subcribetonewsletterflag;
	}

	public void setSubcribetonewsletterflag(java.lang.String subcribetonewsletterflag) {
		this.subcribetonewsletterflag = subcribetonewsletterflag;
	}

	public java.lang.String getCountryidofcitizenshipid() {
		return countryidofcitizenshipid;
	}

	public void setCountryidofcitizenshipid(java.lang.String countryidofcitizenshipid) {
		this.countryidofcitizenshipid = countryidofcitizenshipid;
	}

	public java.lang.String getCountryidofcitizenship_EN() {
		return countryidofcitizenship_EN;
	}

	public void setCountryidofcitizenship_EN(java.lang.String countryidofcitizenship_EN) {
		this.countryidofcitizenship_EN = countryidofcitizenship_EN;
	}

	public java.lang.String getCountryidofcitizenship_AR() {
		return countryidofcitizenship_AR;
	}

	public void setCountryidofcitizenship_AR(java.lang.String countryidofcitizenship_AR) {
		this.countryidofcitizenship_AR = countryidofcitizenship_AR;
	}

	public java.lang.String getCountryidofresidencyid() {
		return countryidofresidencyid;
	}

	public void setCountryidofresidencyid(java.lang.String countryidofresidencyid) {
		this.countryidofresidencyid = countryidofresidencyid;
	}

	public java.lang.String getCountryidofresidency_EN() {
		return countryidofresidency_EN;
	}

	public void setCountryidofresidency_EN(java.lang.String countryidofresidency_EN) {
		this.countryidofresidency_EN = countryidofresidency_EN;
	}

	public java.lang.String getCountryidofresidency_AR() {
		return countryidofresidency_AR;
	}

	public void setCountryidofresidency_AR(java.lang.String countryidofresidency_AR) {
		this.countryidofresidency_AR = countryidofresidency_AR;
	}

	public java.lang.String getHasfamilybookno() {
		return hasfamilybookno;
	}

	public void setHasfamilybookno(java.lang.String hasfamilybookno) {
		this.hasfamilybookno = hasfamilybookno;
	}

	public java.lang.String getEidaexpirydate() {
		return eidaexpirydate;
	}

	public void setEidaexpirydate(java.lang.String eidaexpirydate) {
		this.eidaexpirydate = eidaexpirydate;
	}

	public java.lang.String getTownname() {
		return townname;
	}

	public void setTownname(java.lang.String townname) {
		this.townname = townname;
	}

	public java.lang.String getTownnumber() {
		return townnumber;
	}

	public void setTownnumber(java.lang.String townnumber) {
		this.townnumber = townnumber;
	}

	public java.lang.String getFamilynumber() {
		return familynumber;
	}

	public void setFamilynumber(java.lang.String familynumber) {
		this.familynumber = familynumber;
	}

	public java.lang.String getTribename() {
		return tribename;
	}

	public void setTribename(java.lang.String tribename) {
		this.tribename = tribename;
	}

	public java.lang.String getClannumber() {
		return clannumber;
	}

	public void setClannumber(java.lang.String clannumber) {
		this.clannumber = clannumber;
	}

	public java.lang.String getIssuancedate() {
		return issuancedate;
	}

	public void setIssuancedate(java.lang.String issuancedate) {
		this.issuancedate = issuancedate;
	}

	public java.lang.String getMothername() {
		return mothername;
	}

	public void setMothername(java.lang.String mothername) {
		this.mothername = mothername;
	}

	public java.lang.String getMothersfathername() {
		return mothersfathername;
	}

	public void setMothersfathername(java.lang.String mothersfathername) {
		this.mothersfathername = mothersfathername;
	}

	public java.lang.String getTradelicensetypeid() {
		return tradelicensetypeid;
	}

	public void setTradelicensetypeid(java.lang.String tradelicensetypeid) {
		this.tradelicensetypeid = tradelicensetypeid;
	}

	public java.lang.String getTradelicensetype_EN() {
		return tradelicensetype_EN;
	}

	public void setTradelicensetype_EN(java.lang.String tradelicensetype_EN) {
		this.tradelicensetype_EN = tradelicensetype_EN;
	}

	public java.lang.String getTradelicensetype_AR() {
		return tradelicensetype_AR;
	}

	public void setTradelicensetype_AR(java.lang.String tradelicensetype_AR) {
		this.tradelicensetype_AR = tradelicensetype_AR;
	}

	public java.lang.String getMobileno2() {
		return mobileno2;
	}

	public void setMobileno2(java.lang.String mobileno2) {
		this.mobileno2 = mobileno2;
	}

	public java.lang.String getWebsite() {
		return website;
	}

	public void setWebsite(java.lang.String website) {
		this.website = website;
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

	public String getExecutionStatus() {
		return executionStatus;
	}

	public void setExecutionStatus(String executionStatus) {
		this.executionStatus = executionStatus;
	}

	

}
