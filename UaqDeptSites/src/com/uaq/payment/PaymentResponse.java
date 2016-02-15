package com.uaq.payment;

import java.util.List;

import com.uaq.vo.PurchaseServiceVO;

/**
 * This is the base class for different kinds of payment responses such as
 * inquiry and payweb. It contains common attributes for these response types
 * 
 * @author raheem
 * 
 */
public abstract class PaymentResponse {

	protected MerchantAccount merchantAccount;
	
	protected String action;

	protected String secureHash;

	protected String status;

	protected String statusMessage;

	protected String transactionId; // its merchant app generated unique id.
									// called PUN in payWeb request.

	protected String retrievalRefNumber; // given by eDirham payment service
											// provider. confirmation id

	protected String eDirhamFees;

	protected Double eDirhamFeesDecimal;

	protected String collectionCentreFees;

	protected Double collectionCentreFeesDecimal;

	protected String transactionAmount; // sum of all groups
										// EServiceToalAmount_n fields. amount
										// to be paid. found in Inquiry payment
										// response sub class

	protected Double transactionAmountDecimal;
	
	protected String amount;
	
	protected Double amountDecimal;

	// real total amount sum which user pays is eDirhamFees +
	// collectionCentreFees + transactionAmount

	protected String transactionResponseDate;

	protected String referenceId;
	
	protected String otherInfo;
	
	protected String currency;
	
	protected String paymentMethodType;
	
	protected String merchantModuleSessionID;
	
	protected List<PurchaseServiceVO> purchaseServices;

	
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	// abstract method to calcuate secure hash for each kind of request in their
	// respective concrete classes
	public abstract String calculateSecureHash();

	/**
	 * @return the merchantAccount
	 */
	public MerchantAccount getMerchantAccount() {
		return merchantAccount;
	}

	/**
	 * @param merchantAccount
	 *            the merchantAccount to set
	 */
	public void setMerchantAccount(MerchantAccount merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	/**
	 * @return the secureHash
	 */
	public String getSecureHash() {
		return secureHash;
	}

	/**
	 * @param secureHash
	 *            the secureHash to set
	 */
	public void setSecureHash(String secureHash) {
		this.secureHash = secureHash;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage
	 *            the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId
	 *            the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the retrievalRefNumber
	 */
	public String getRetrievalRefNumber() {
		return retrievalRefNumber;
	}

	/**
	 * @param retrievalRefNumber
	 *            the retrievalRefNumber to set
	 */
	public void setRetrievalRefNumber(String retrievalRefNumber) {
		this.retrievalRefNumber = retrievalRefNumber;
	}

	/**
	 * @return the eDirhamFees
	 */
	public String geteDirhamFees() {
		return eDirhamFees;
	}

	/**
	 * @param eDirhamFees
	 *            the eDirhamFees to set
	 */
	public void seteDirhamFees(String eDirhamFees) {
		this.eDirhamFees = eDirhamFees;
	}

	/**
	 * @return the collectionCentreFees
	 */
	public String getCollectionCentreFees() {
		return collectionCentreFees;
	}

	/**
	 * @param collectionCentreFees
	 *            the collectionCentreFees to set
	 */
	public void setCollectionCentreFees(String collectionCentreFees) {
		this.collectionCentreFees = collectionCentreFees;
	}

	/**
	 * @return the transactionAmount
	 */
	public String getTransactionAmount() {
		return transactionAmount;
	}

	/**
	 * @param transactionAmount
	 *            the transactionAmount to set
	 */
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	/**
	 * @return the transactionResponseDate
	 */
	public String getTransactionResponseDate() {
		return transactionResponseDate;
	}

	/**
	 * @param transactionResponseDate
	 *            the transactionResponseDate to set
	 */
	public void setTransactionResponseDate(String transactionResponseDate) {
		this.transactionResponseDate = transactionResponseDate;
	}

	/**
	 * @return the referenceId
	 */
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 * @param referenceId
	 *            the referenceId to set
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * @return the eDirhamFeesDecimal
	 */
	public Double geteDirhamFeesDecimal() {
		return PaymentUtil.convertAmountISOToDecimalFormat(eDirhamFees);
	}

	/**
	 * @return the collectionCentreFeesDecimal
	 */
	public Double getCollectionCentreFeesDecimal() {
		return PaymentUtil.convertAmountISOToDecimalFormat(collectionCentreFees);
	}

	/**
	 * @return the transactionAmountDecimal
	 */
	public Double getTransactionAmountDecimal() {
		return PaymentUtil.convertAmountISOToDecimalFormat(transactionAmount);
	}	
	
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the otherInfo
	 */
	public String getOtherInfo() {
		return otherInfo;
	}

	/**
	 * @param otherInfo the otherInfo to set
	 */
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
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
	 * @return the amountDecimal
	 */
	public Double getAmountDecimal() {
		return PaymentUtil.convertAmountISOToDecimalFormat(amount);		
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
	 * @return the merchantModuleSessionID
	 */
	public String getMerchantModuleSessionID() {
		return merchantModuleSessionID;
	}

	/**
	 * @param merchantModuleSessionID the merchantModuleSessionID to set
	 */
	public void setMerchantModuleSessionID(String merchantModuleSessionID) {
		this.merchantModuleSessionID = merchantModuleSessionID;
	}

	/**
	 * @return the purchaseCommandServices
	 */
	public List<PurchaseServiceVO> getPurchaseServices() {
		return purchaseServices;
	}

	/**
	 * @param purchaseCommandServices the purchaseCommandServices to set
	 */
	public void setPurchaseServices(List<PurchaseServiceVO> purchaseServices) {
		this.purchaseServices = purchaseServices;
	}

	
	@Override
	public String toString() {
		return "PaymentResponse [action=" + action + ", secureHash=" + secureHash + ", status=" + status + ", statusMessage=" + statusMessage
				+ ", transactionId=" + transactionId + ", retrievalRefNumber=" + retrievalRefNumber + ", eDirhamFees=" + eDirhamFees
				+ ", eDirhamFeesDecimal=" + eDirhamFeesDecimal + ", collectionCentreFees=" + collectionCentreFees + ", collectionCentreFeesDecimal="
				+ collectionCentreFeesDecimal + ", transactionAmount=" + transactionAmount + ", transactionAmountDecimal=" + transactionAmountDecimal
				+ ", amount=" + amount + ", amountDecimal=" + amountDecimal + ", transactionResponseDate=" + transactionResponseDate
				+ ", referenceId=" + referenceId + ", otherInfo=" + otherInfo + ", currency=" + currency + ", paymentMethodType=" + paymentMethodType
				+ ", merchantModuleSessionID=" + merchantModuleSessionID + ", purchaseServices=" + purchaseServices + "]";
	}
		
}
