package com.uaq.ws.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PosInfo")
public class PosInfo {

	private String merchantID;
	
	private String terminalID;

	/**
	 * @return the merchantID
	 */
	public String getMerchantID() {
		return merchantID;
	}

	/**
	 * @param merchantID the merchantID to set
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
	 * @param terminalID the terminalID to set
	 */
	public void setTerminalID(String terminalID) {
		this.terminalID = terminalID;
	}

	
}
