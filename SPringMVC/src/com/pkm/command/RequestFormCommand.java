package com.pkm.command;

import org.springframework.web.multipart.MultipartFile;

public class RequestFormCommand {
	
	private String name;
	private String mobileno;
	private String modelno;
	private String prouducttype;
	private String prouductSubtype;
	private String description;
	private String email;
	private MultipartFile descriptionFile;
	private AddressDetail address;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	
	public String getProuducttype() {
		return prouducttype;
	}
	public void setProuducttype(String prouducttype) {
		this.prouducttype = prouducttype;
	}
	public String getProuductSubtype() {
		return prouductSubtype;
	}
	public void setProuductSubtype(String prouductSubtype) {
		this.prouductSubtype = prouductSubtype;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AddressDetail getAddress() {
		return address;
	}
	public void setAddress(AddressDetail address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public MultipartFile getDescriptionFile() {
		return descriptionFile;
	}
	public void setDescriptionFile(MultipartFile descriptionFile) {
		this.descriptionFile = descriptionFile;
	}
	public String getModelno() {
		return modelno;
	}
	public void setModelno(String modelno) {
		this.modelno = modelno;
	}
	

}
