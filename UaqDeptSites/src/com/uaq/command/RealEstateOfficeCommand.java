package com.uaq.command;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;
/**
 * command class for real estate
 * @author G Sekhar
 *
 */
public class RealEstateOfficeCommand implements Serializable {

	private String directorName;
	private String address;

	private MultipartFile familyBook;
	private MultipartFile spouseEmiratesId;

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public MultipartFile getFamilyBook() {
		return familyBook;
	}

	public void setFamilyBook(MultipartFile familyBook) {
		this.familyBook = familyBook;
	}

	public MultipartFile getSpouseEmiratesId() {
		return spouseEmiratesId;
	}

	public void setSpouseEmiratesId(MultipartFile spouseEmiratesId) {
		this.spouseEmiratesId = spouseEmiratesId;
	}

}
