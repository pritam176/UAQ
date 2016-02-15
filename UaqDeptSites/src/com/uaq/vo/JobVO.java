package com.uaq.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Career")
/**
 * 
 * @author ajain
 * View Object for the Job Asset Type.
 */
public class JobVO extends BaseVO {

	private String jobTitle;
	private String jobDescription;
	private String jobResponsibility;
	private String createdDate;
	private String postedDate;
	private String endDate;
	private String departmentNameEN;
	private String departmentNameAR;
	private String jobReferenceNumber;
	private String mailTo;
	private String mobileImage;
	private String teaserText;
	private long rowNum;
	private long count;

	public String getTeaserText() {
		return teaserText;
	}

	public void setTeaserText(String teaserText) {
		this.teaserText = teaserText;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getJobResponsibility() {
		return jobResponsibility;
	}

	public void setJobResponsibility(String jobResponsibility) {
		this.jobResponsibility = jobResponsibility;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDepartmentNameEN() {
		return departmentNameEN;
	}

	public void setDepartmentNameEN(String departmentNameEN) {
		this.departmentNameEN = departmentNameEN;
	}

	public String getDepartmentNameAR() {
		return departmentNameAR;
	}

	public void setDepartmentNameAR(String departmentNameAR) {
		this.departmentNameAR = departmentNameAR;
	}

	public String getJobReferenceNumber() {
		return jobReferenceNumber;
	}

	public void setJobReferenceNumber(String jobReferenceNumber) {
		this.jobReferenceNumber = jobReferenceNumber;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMobileImage() {
		return mobileImage;
	}

	public void setMobileImage(String mobileImage) {
		this.mobileImage = mobileImage;
	}

	public long getRowNum() {
		return rowNum;
	}

	public void setRowNum(long rowNum) {
		this.rowNum = rowNum;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
}
