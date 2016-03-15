package com.uaq.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.uaq.payment.PayWebPaymentResponse;


/**
 * Command Class for PurchaseCommand.
 * 
 * @author mraheem
 * 
 */
public class PurchaseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String purchaseId;	
	
	private String purchaseStatus;
	
	private List<PurchaseServiceVO> purchaseServiceCodes;
	
	private String paymentMethodType; // card type such as GII, Visa, Master	
	
	private Boolean paymentInProgress;

	PayWebPaymentResponse paymentTransaction;
	
	private String departmentId;
	
	private String feeId;	
		
	private String serviceId;
	
	private String customerId;
	
	private String customerName;
		
	private Date createdDate;
	
	private Date lastModifiedDate;
	
	private long requestId;
	private String receiptNumber;

	

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the purchaseId
	 */
	public String getPurchaseId() {
		return purchaseId;
	}

	/**
	 * @param purchaseId the purchaseId to set
	 */
	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	/**
	 * @return the purchaseStatus
	 */
	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	/**
	 * @param purchaseStatus the purchaseStatus to set
	 */
	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	/**
	 * @return the purchaseServiceCodes
	 */
	public List<PurchaseServiceVO> getPurchaseServiceCodes() {
		return purchaseServiceCodes;
	}

	/**
	 * @param purchaseServiceCodes the purchaseServiceCodes to set
	 */
	public void setPurchaseServiceCodes(List<PurchaseServiceVO> purchaseServiceCodes) {
		this.purchaseServiceCodes = purchaseServiceCodes;
	}

	/**
	 * @return the paymentMethodType
	 */
	public String getPaymentMethodType() {
		return paymentMethodType;
	}

	/**
	 * @param paymentMethodType the paymentMethodType to set
	 */
	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}

	/**
	 * @return the paymentInProgress
	 */
	public Boolean getPaymentInProgress() {
		return paymentInProgress;
	}

	/**
	 * @param paymentInProgress the paymentInProgress to set
	 */
	public void setPaymentInProgress(Boolean paymentInProgress) {
		this.paymentInProgress = paymentInProgress;
	}

	/**
	 * @return the paymentTransaction
	 */
	public PayWebPaymentResponse getPaymentTransaction() {
		return paymentTransaction;
	}

	/**
	 * @param paymentTransaction the paymentTransaction to set
	 */
	public void setPaymentTransaction(PayWebPaymentResponse paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the feeId
	 */
	public String getFeeId() {
		return feeId;
	}

	/**
	 * @param feeId the feeId to set
	 */
	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}
	
	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	
}
