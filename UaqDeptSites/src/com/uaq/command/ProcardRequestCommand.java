package com.uaq.command;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

/**
 * Command class for New Procard Request
 * 
 * @author Gsekhar
 * 
 */
public class ProcardRequestCommand implements Serializable {

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
