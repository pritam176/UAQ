package com.uaq.payment;

/**
 * This class represents the Merchant Account on the payment service provider.
 * It is used in Payment Request objects to pass through.
 * 
 * @author raheem
 * 
 */

public class MerchantAccount {

	private String merchantAccountID;

	private String bankID;

	private String merchantID;

	private String terminalID;

	private String secretKey;

	private String serviceProvider;

	private String backToBackURL;

	private String redirectURL;

	/**
	 * @return the merchantAccountID
	 */
	public String getMerchantAccountID() {
		return merchantAccountID;
	}

	/**
	 * @param merchantAccountID
	 *            the merchantAccountID to set
	 */
	public void setMerchantAccountID(String merchantAccountID) {
		this.merchantAccountID = merchantAccountID;
	}

	/**
	 * @return the bankID
	 */
	public String getBankID() {
		return bankID;
	}

	/**
	 * @param bankID
	 *            the bankID to set
	 */
	public void setBankID(String bankID) {
		this.bankID = bankID;
	}

	/**
	 * @return the merchantID
	 */
	public String getMerchantID() {
		return merchantID;
	}

	/**
	 * @param merchantID
	 *            the merchantID to set
	 */
	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	/**
	 * @return the terminalID
	 */
	public String getTerminalID() {
		return terminalID;
	}

	/**
	 * @param terminalID
	 *            the terminalID to set
	 */
	public void setTerminalID(String terminalID) {
		this.terminalID = terminalID;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey
	 *            the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * @return the serviceProvider
	 */
	public String getServiceProvider() {
		return serviceProvider;
	}

	/**
	 * @param serviceProvider
	 *            the serviceProvider to set
	 */
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	/**
	 * @return the backToBackURL
	 */
	public String getBackToBackURL() {
		return backToBackURL;
	}

	/**
	 * @param backToBackURL
	 *            the backToBackURL to set
	 */
	public void setBackToBackURL(String backToBackURL) {
		this.backToBackURL = backToBackURL;
	}

	/**
	 * @return the redirectURL
	 */
	public String getRedirectURL() {
		return redirectURL;
	}

	/**
	 * @param redirectURL
	 *            the redirectURL to set
	 */
	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	/* 
	 * For testing purpose only. remove while going to production. 
	 * don't leak secret information to logs
	 */
	@Override
	public String toString() {
		return "MerchantAccount [merchantAccountID=" + merchantAccountID + ", bankID=" + bankID + ", merchantID=" + merchantID + ", terminalID="
				+ terminalID + ", secretKey=" + secretKey + ", serviceProvider=" + serviceProvider + ", backToBackURL=" + backToBackURL
				+ ", redirectURL=" + redirectURL + "]";
	}
	
	

}
