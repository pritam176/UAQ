package com.uaq.ws.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EIDAVO")
public class EIDAVO {
	private String fullName;
	private String emiratesID;
	private String emiratesIDExpiryDate;
	private String arabicFullname;
	String dob;
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmiratesIDExpiryDate() {
		return emiratesIDExpiryDate;
	}
	public void setEmiratesIDExpiryDate(String emiratesIDExpiryDate) {
		this.emiratesIDExpiryDate = emiratesIDExpiryDate;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmiratesID() {
		return emiratesID;
	}
	public void setEmiratesID(String emiratesID) {
		this.emiratesID = emiratesID;
	}
	public String getArabicFullname() {
		return arabicFullname;
	}
	public void setArabicFullname(String arabicFullname) {
		this.arabicFullname = arabicFullname;
	}
	
}
