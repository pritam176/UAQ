package com.uaq.payment;


/**
 * This is the base class for different kinds of payment requests such as
 * inquiry and payweb. It contains common attributes for these request types
 * 
 * @author raheem
 * 
 */
public abstract class PaymentRequest {

	protected Integer requestID;

	protected String currency; // ISO formatted code for currency

	protected String secureHash;

	protected String version; // optional field which defaults to 1.0 if not
								// sent

	protected String referenceID; // photoPurchaseID reference leads to the
									// photo purchaser user

	protected Integer payWebRequestID; // equal to above requestID, for grouping
										// each inquiry request with payWeb
										// request. will be null for payWeb
										// rquest action

	protected MerchantAccount merchantAccount;

	protected String action; // differs for each kind of request

	protected String transactionId; // its merchant app generated unique id.
									// called PUN in payWeb request.

	protected String paymentMethodType;

	protected String paymentMethodTypeDesc;
	
	protected String transactionRequestDate;
	
	protected String otherInfo;
	
	protected String queryString;

	// abstract method to calcuate secure hash for each kind of request in their
	// respective concrete classes
	public abstract String calculateSecureHash();

	/**
	 * @return the requestID
	 */
	public Integer getRequestID() {
		return requestID;
	}

	/**
	 * @param requestID
	 *            the requestID to set
	 */
	public void setRequestID(Integer requestID) {
		this.requestID = requestID;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
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
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the referenceID
	 */
	public String getReferenceID() {
		return referenceID;
	}

	/**
	 * @param referenceID
	 *            the referenceID to set
	 */
	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}

	/**
	 * @return the payWebRequestID
	 */
	public Integer getPayWebRequestID() {
		return payWebRequestID;
	}

	/**
	 * @param payWebRequestID
	 *            the payWebRequestID to set
	 */
	public void setPayWebRequestID(Integer payWebRequestID) {
		this.payWebRequestID = payWebRequestID;
	}

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
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
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
	 * @return the paymentMethodType
	 */
	public String getPaymentMethodType() {
		return paymentMethodType;
	}

	/**
	 * @param paymentMethodType
	 *            the paymentMethodType to set
	 */
	public void setPaymentMethodType(String paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}
	
	/**
	 * @return the paymentMethodTypeDesc
	 */
	public String getPaymentMethodTypeDesc() {
		return paymentMethodTypeDesc;
	}

	/**
	 * @param paymentMethodTypeDesc
	 *            the paymentMethodTypeDesc to set
	 */
	public void setPaymentMethodTypeDesc(String paymentMethodTypeDesc) {
		this.paymentMethodTypeDesc = paymentMethodTypeDesc;
	}

	/**
	 * @return the transactionRequestDate
	 */
	public String getTransactionRequestDate() {
		return transactionRequestDate;
	}

	/**
	 * @param transactionRequestDate the transactionRequestDate to set
	 */
	public void setTransactionRequestDate(String transactionRequestDate) {
		this.transactionRequestDate = transactionRequestDate;
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
	 * @return the queryString
	 */
	public String getQueryString() {
		return queryString;
	}

	/**
	 * @param queryString the queryString to set
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	
	@Override
	public String toString() {
		return "PaymentRequest [requestID=" + requestID + ", currency=" + currency + ", secureHash=" + secureHash + ", version=" + version
				+ ", referenceID=" + referenceID + ", payWebRequestID=" + payWebRequestID + ", action=" + action + ", transactionId=" + transactionId
				+ ", paymentMethodType=" + paymentMethodType + ", paymentMethodTypeDesc=" + paymentMethodTypeDesc + ", transactionRequestDate="
				+ transactionRequestDate + ", otherInfo=" + otherInfo + ", queryString=" + queryString + "]";
	}
	
}
