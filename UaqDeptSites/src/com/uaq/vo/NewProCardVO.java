package com.uaq.vo;

import org.springframework.web.multipart.MultipartFile;

/**
 * VO for New Pro card Request
 * 
 * @author GSekhar
 * 
 */
public class NewProCardVO {

	private String proName;
	private String proIdNumber;
	private String proIdExpiryDate;
	private String proNationality;
	private MultipartFile proIdProof;
	private MultipartFile proPhotograph;

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProIdNumber() {
		return proIdNumber;
	}

	public void setProIdNumber(String proIdNumber) {
		this.proIdNumber = proIdNumber;
	}

	public String getProIdExpiryDate() {
		return proIdExpiryDate;
	}

	public void setProIdExpiryDate(String proIdExpiryDate) {
		this.proIdExpiryDate = proIdExpiryDate;
	}

	public String getProNationality() {
		return proNationality;
	}

	public void setProNationality(String proNationality) {
		this.proNationality = proNationality;
	}

	public MultipartFile getProIdProof() {
		return proIdProof;
	}

	public void setProIdProof(MultipartFile proIdProof) {
		this.proIdProof = proIdProof;
	}

	public MultipartFile getProPhotograph() {
		return proPhotograph;
	}

	public void setProPhotograph(MultipartFile proPhotograph) {
		this.proPhotograph = proPhotograph;
	}

}
