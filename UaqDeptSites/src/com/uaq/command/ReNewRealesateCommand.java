package com.uaq.command;

import org.springframework.web.multipart.MultipartFile;

public class ReNewRealesateCommand {
	private MultipartFile policeClearance;
	private MultipartFile personalPhotograph;

	public MultipartFile getPoliceClearance() {
		return policeClearance;
	}

	public void setPoliceClearance(MultipartFile policeClearance) {
		this.policeClearance = policeClearance;
	}

	public MultipartFile getPersonalPhotograph() {
		return personalPhotograph;
	}

	public void setPersonalPhotograph(MultipartFile personalPhotograph) {
		this.personalPhotograph = personalPhotograph;
	}

}
