package com.uaq.command;

public class Logincommand {

	private String loginUsername;

	private String loginPassword;
	
	private String loginStatus;

	private Boolean isLoggedInSucessfull;

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public void setIsLoggedInSucessfull(Boolean isLoggedInSucessfull) {
		this.isLoggedInSucessfull = isLoggedInSucessfull;
	}
	
	public Boolean getIsLoggedInSucessfull() {
		return isLoggedInSucessfull;
	}
}
