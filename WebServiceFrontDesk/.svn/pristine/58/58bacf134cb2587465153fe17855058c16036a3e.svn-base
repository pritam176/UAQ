package com.uaq.ws.pos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PaymentResponseECR")
public class PaymentResponseECR {
	
	private String transactionType;

	private String transactionId;
	
	private Integer servicesCount;
	
	private Double service1Amount;
	
	private Double service1Fee1Amount;
	
	private String service1Fee1Name;
	
	private Integer dynamicFeeCount;
	
	private Double dynamicFee1Amount;
	
	private String dynamicFee1Text;
	
	private Double dynamicFee2Amount;
	
	private String dynamicFee2Text;
	
	private Double dynamicFee3Amount;
	
	private String dynamicFee3Text;
	
	private String approvalCode;
	
	private String ecrIdNo;
	
	private String retrievalRefNo;
	
	private String invoiceNo;
	
	private String posDate;
	
	private String posTime;
	
	private String lrc;
	
	private String customLRC;
	
	private String responseCode;	
	
	private String responseMessage;	
	
	private String autoUpdateStatus;
	
	private String autoUpdateStatusMessage;
	
	private String other;
		
	public Double getTotalAmount(){
		
		double total = 0.0;
		
		if(service1Amount != null && service1Amount > 0){
			total += service1Amount;
		}
		if(service1Fee1Amount != null && service1Fee1Amount > 0){
			total += service1Fee1Amount;
		}
		if(dynamicFee1Amount != null && dynamicFee1Amount > 0){
			total += dynamicFee1Amount;
		}
		if(dynamicFee2Amount != null && dynamicFee2Amount > 0){
			total += dynamicFee2Amount;
		}
		if(dynamicFee3Amount != null && dynamicFee3Amount > 0){
			total += dynamicFee3Amount;
		}
		
		return total;
	}

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
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the servicesCount
	 */
	public Integer getServicesCount() {
		return servicesCount;
	}

	/**
	 * @param servicesCount the servicesCount to set
	 */
	public void setServicesCount(Integer servicesCount) {
		this.servicesCount = servicesCount;
	}

	/**
	 * @return the service1Amount
	 */
	public Double getService1Amount() {
		return service1Amount;
	}

	/**
	 * @param service1Amount the service1Amount to set
	 */
	public void setService1Amount(Double service1Amount) {
		this.service1Amount = service1Amount;
	}

	/**
	 * @return the service1Fee1Amount
	 */
	public Double getService1Fee1Amount() {
		return service1Fee1Amount;
	}

	/**
	 * @param service1Fee1Amount the service1Fee1Amount to set
	 */
	public void setService1Fee1Amount(Double service1Fee1Amount) {
		this.service1Fee1Amount = service1Fee1Amount;
	}

	/**
	 * @return the service1Fee1Name
	 */
	public String getService1Fee1Name() {
		return service1Fee1Name;
	}

	/**
	 * @param service1Fee1Name the service1Fee1Name to set
	 */
	public void setService1Fee1Name(String service1Fee1Name) {
		this.service1Fee1Name = service1Fee1Name;
	}

	/**
	 * @return the dynamicFeeCount
	 */
	public Integer getDynamicFeeCount() {
		return dynamicFeeCount;
	}

	/**
	 * @param dynamicFeeCount the dynamicFeeCount to set
	 */
	public void setDynamicFeeCount(Integer dynamicFeeCount) {
		this.dynamicFeeCount = dynamicFeeCount;
	}

	/**
	 * @return the dynamicFee1Amount
	 */
	public Double getDynamicFee1Amount() {
		return dynamicFee1Amount;
	}

	/**
	 * @param dynamicFee1Amount the dynamicFee1Amount to set
	 */
	public void setDynamicFee1Amount(Double dynamicFee1Amount) {
		this.dynamicFee1Amount = dynamicFee1Amount;
	}

	/**
	 * @return the dynamicFee1Text
	 */
	public String getDynamicFee1Text() {
		return dynamicFee1Text;
	}

	/**
	 * @param dynamicFee1Text the dynamicFee1Text to set
	 */
	public void setDynamicFee1Text(String dynamicFee1Text) {
		this.dynamicFee1Text = dynamicFee1Text;
	}

	/**
	 * @return the dynamicFee2Amount
	 */
	public Double getDynamicFee2Amount() {
		return dynamicFee2Amount;
	}

	/**
	 * @param dynamicFee2Amount the dynamicFee2Amount to set
	 */
	public void setDynamicFee2Amount(Double dynamicFee2Amount) {
		this.dynamicFee2Amount = dynamicFee2Amount;
	}

	/**
	 * @return the dynamicFee2Text
	 */
	public String getDynamicFee2Text() {
		return dynamicFee2Text;
	}

	/**
	 * @param dynamicFee2Text the dynamicFee2Text to set
	 */
	public void setDynamicFee2Text(String dynamicFee2Text) {
		this.dynamicFee2Text = dynamicFee2Text;
	}

	/**
	 * @return the dynamicFee3Amount
	 */
	public Double getDynamicFee3Amount() {
		return dynamicFee3Amount;
	}

	/**
	 * @param dynamicFee3Amount the dynamicFee3Amount to set
	 */
	public void setDynamicFee3Amount(Double dynamicFee3Amount) {
		this.dynamicFee3Amount = dynamicFee3Amount;
	}

	/**
	 * @return the dynamicFee3Text
	 */
	public String getDynamicFee3Text() {
		return dynamicFee3Text;
	}

	/**
	 * @param dynamicFee3Text the dynamicFee3Text to set
	 */
	public void setDynamicFee3Text(String dynamicFee3Text) {
		this.dynamicFee3Text = dynamicFee3Text;
	}

	/**
	 * @return the approvalCode
	 */
	public String getApprovalCode() {
		return approvalCode;
	}

	/**
	 * @param approvalCode the approvalCode to set
	 */
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
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
	 * @return the retrievalRefNo
	 */
	public String getRetrievalRefNo() {
		return retrievalRefNo;
	}

	/**
	 * @param retrievalRefNo the retrievalRefNo to set
	 */
	public void setRetrievalRefNo(String retrievalRefNo) {
		this.retrievalRefNo = retrievalRefNo;
	}

	/**
	 * @return the invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * @param invoiceNo the invoiceNo to set
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * @return the posDate
	 */
	public String getPosDate() {
		return posDate;
	}

	/**
	 * @param posDate the posDate to set
	 */
	public void setPosDate(String posDate) {
		this.posDate = posDate;
	}

	/**
	 * @return the posTime
	 */
	public String getPosTime() {
		return posTime;
	}

	/**
	 * @param posTime the posTime to set
	 */
	public void setPosTime(String posTime) {
		this.posTime = posTime;
	}

	/**
	 * @return the lrc
	 */
	public String getLrc() {
		return lrc;
	}

	/**
	 * @param lrc the lrc to set
	 */
	public void setLrc(String lrc) {
		this.lrc = lrc;
	}

	/**
	 * @return the customLRC
	 */
	public String getCustomLRC() {
		return customLRC;
	}

	/**
	 * @param customLRC the customLRC to set
	 */
	public void setCustomLRC(String customLRC) {
		this.customLRC = customLRC;
	}
	
	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @param responseMessage the responseMessage to set
	 */
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	/**
	 * @return the autoUpdateStatus
	 */
	public String getAutoUpdateStatus() {
		return autoUpdateStatus;
	}

	/**
	 * @param autoUpdateStatus the autoUpdateStatus to set
	 */
	public void setAutoUpdateStatus(String autoUpdateStatus) {
		this.autoUpdateStatus = autoUpdateStatus;
	}

	/**
	 * @return the autoUpdateStatusMessage
	 */
	public String getAutoUpdateStatusMessage() {
		return autoUpdateStatusMessage;
	}

	/**
	 * @param autoUpdateStatusMessage the autoUpdateStatusMessage to set
	 */
	public void setAutoUpdateStatusMessage(String autoUpdateStatusMessage) {
		this.autoUpdateStatusMessage = autoUpdateStatusMessage;
	}

	/**
	 * @return the other
	 */
	public String getOther() {
		return other;
	}

	/**
	 * @param other the other to set
	 */
	public void setOther(String other) {
		this.other = other;
	}


	@Override
	public String toString() {
		return "PaymentResponseECR [transactionType=" + transactionType + ", transactionId=" + transactionId + ", servicesCount=" + servicesCount
				+ ", service1Amount=" + service1Amount + ", service1Fee1Amount=" + service1Fee1Amount + ", service1Fee1Name=" + service1Fee1Name
				+ ", dynamicFeeCount=" + dynamicFeeCount + ", dynamicFee1Amount=" + dynamicFee1Amount + ", dynamicFee1Text=" + dynamicFee1Text
				+ ", dynamicFee2Amount=" + dynamicFee2Amount + ", dynamicFee2Text=" + dynamicFee2Text + ", dynamicFee3Amount=" + dynamicFee3Amount
				+ ", dynamicFee3Text=" + dynamicFee3Text + ", approvalCode=" + approvalCode + ", ecrIdNo=" + ecrIdNo + ", retrievalRefNo="
				+ retrievalRefNo + ", invoiceNo=" + invoiceNo + ", posDate=" + posDate + ", posTime=" + posTime + ", lrc=" + lrc + ", customLRC="
				+ customLRC + ", responseCode=" + responseCode + ", responseMessage=" + responseMessage + ", autoUpdateStatus=" + autoUpdateStatus
				+ ", autoUpdateStatusMessage=" + autoUpdateStatusMessage + ", other=" + other + ", getTotalAmount()=" + getTotalAmount() + "]";
	}
	
}
