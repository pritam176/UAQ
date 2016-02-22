package com.uaq.ws.pos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PaymentRequestECR")
public class PaymentRequestECR {
	
	private String transactionType;

	private String serviceCode;
	
	private String amount;	
		
	private String ecrIdNo; // unique transactionId from application. not to be confused with response transactionId. 
							// timestamp miliseconds. max 16 char
	
	private String terminalNo;
	
	private String merchantId;

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @return the serviceCode
	 */
	public String getServiceCode() {
		return serviceCode;
	}

	/**
	 * @param serviceCode the serviceCode to set
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the ecrIdNo
	 */
	public String getEcrIdNo() {
		return ecrIdNo;
	}

	/**
	 * @param ecrIdNo the ecrIdNo to set
	 */
	public void setEcrIdNo(String ecrIdNo) {
		this.ecrIdNo = ecrIdNo;
	}
	
	/**
	 * @return the terminalNo
	 */
	public String getTerminalNo() {
		return terminalNo;
	}

	/**
	 * @param terminalNo the terminalNo to set
	 */
	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	/**
	 * @return the merchantId
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * @param merchantId the merchantId to set
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentRequestECR [transactionType=" + transactionType + ", serviceCode=" + serviceCode + ", amount=" + amount + ", ecrIdNo="
				+ ecrIdNo + ", terminalNo=" + terminalNo + ", merchantId=" + merchantId + "]";
	}

	
			
}
