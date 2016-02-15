/**
 * 
 */
package com.uaq.payment;

import org.apache.commons.codec.digest.DigestUtils;

import com.uaq.vo.PurchaseServiceVO;

/**
 * /** This class represents the inquiry payment response sub type of Payment
 * Response
 * 
 * @author raheem
 * 
 */
public class AutoUpdatePaymentResponse extends PaymentResponse {

	private String paywebTransactionId;
	
	private String originalTransactionStatus;
	
	private String originalTransactionStatusMessage;
			
	private String extractStatus;
	
	private String OriginalTransactionAction;
	
	private PurchaseServiceVO service;
	
	/**
	 * overriding the parent class method to provide specific secure hash for
	 * inquiry payment request
	 */
	@Override
	public String calculateSecureHash() {

		StringBuffer fields = new StringBuffer(merchantAccount.getSecretKey());
		fields.append(merchantAccount.getMerchantID());
		fields.append(transactionId);
		fields.append(transactionResponseDate);		

		String hashSecureEncoded = DigestUtils.sha256Hex(fields.toString());

		return hashSecureEncoded;
	}

	/**
	 * @return the paywebTransactionId
	 */
	public String getPaywebTransactionId() {
		return paywebTransactionId;
	}

	/**
	 * @param paywebTransactionId the paywebTransactionId to set
	 */
	public void setPaywebTransactionId(String paywebTransactionId) {
		this.paywebTransactionId = paywebTransactionId;
	}

	/**
	 * @return the originalTransactionStatus
	 */
	public String getOriginalTransactionStatus() {
		return originalTransactionStatus;
	}

	/**
	 * @param originalTransactionStatus the originalTransactionStatus to set
	 */
	public void setOriginalTransactionStatus(String originalTransactionStatus) {
		this.originalTransactionStatus = originalTransactionStatus;
	}

	/**
	 * @return the originalTransactionStatusMessage
	 */
	public String getOriginalTransactionStatusMessage() {
		return originalTransactionStatusMessage;
	}

	/**
	 * @param originalTransactionStatusMessage the originalTransactionStatusMessage to set
	 */
	public void setOriginalTransactionStatusMessage(String originalTransactionStatusMessage) {
		this.originalTransactionStatusMessage = originalTransactionStatusMessage;
	}
	
	/**
	 * @return the extractStatus
	 */
	public String getExtractStatus() {
		return extractStatus;
	}

	/**
	 * @param extractStatus the extractStatus to set
	 */
	public void setExtractStatus(String extractStatus) {
		this.extractStatus = extractStatus;
	}

	/**
	 * @return the originalTransactionAction
	 */
	public String getOriginalTransactionAction() {
		return OriginalTransactionAction;
	}

	/**
	 * @param originalTransactionAction the originalTransactionAction to set
	 */
	public void setOriginalTransactionAction(String originalTransactionAction) {
		OriginalTransactionAction = originalTransactionAction;
	}
	
	/**
	 * @return the service
	 */
	public PurchaseServiceVO getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(PurchaseServiceVO service) {
		this.service = service;
	}

	
	@Override
	public String toString() {
		return "AutoUpdatePaymentResponse [paywebTransactionId=" + paywebTransactionId + ", originalTransactionStatus=" + originalTransactionStatus
				+ ", originalTransactionStatusMessage=" + originalTransactionStatusMessage + ", extractStatus=" + extractStatus
				+ ", OriginalTransactionAction=" + OriginalTransactionAction + ", service=" + service + ", action=" + action + ", secureHash="
				+ secureHash + ", status=" + status + ", statusMessage=" + statusMessage + ", transactionId=" + transactionId
				+ ", retrievalRefNumber=" + retrievalRefNumber + ", eDirhamFees=" + eDirhamFees + ", eDirhamFeesDecimal=" + eDirhamFeesDecimal
				+ ", collectionCentreFees=" + collectionCentreFees + ", collectionCentreFeesDecimal=" + collectionCentreFeesDecimal
				+ ", transactionAmount=" + transactionAmount + ", transactionAmountDecimal=" + transactionAmountDecimal + ", amount=" + amount
				+ ", amountDecimal=" + amountDecimal + ", transactionResponseDate=" + transactionResponseDate + ", referenceId=" + referenceId
				+ ", otherInfo=" + otherInfo + ", currency=" + currency + ", paymentMethodType=" + paymentMethodType + ", merchantModuleSessionID="
				+ merchantModuleSessionID + ", purchaseServices=" + purchaseServices + "]";
	}
		
	
}
