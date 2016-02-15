/**
 * 
 */
package com.uaq.payment;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.uaq.vo.PurchaseServiceVO;

/**
 * This class represents the inquiry payment request sub type of Payment Request
 * Request
 * 
 * @author raheem
 * 
 */
public class InquiryPaymentRequest extends PaymentRequest {	

	private String applicationNumber;

	private String extraFields_intendedEDirhamService;

	private List<PurchaseServiceVO> purchaseCommandServices;
	
	private String transactionAmount;

	/**
	 * overriding the parent class method to provide specific secure hash for
	 * inquiry payment request
	 */
	@Override
	public String calculateSecureHash() {

		StringBuffer fields = new StringBuffer(merchantAccount.getSecretKey());
		fields.append(merchantAccount.getMerchantID());
		fields.append(transactionRequestDate);
		fields.append(transactionId);
		
		//logger.debug("calculateSecureHash fields = " + fields.toString()); //only for testing environment

		String hashSecureEncoded = DigestUtils.sha256Hex(fields.toString());

		//logger.debug("calculateSecureHash = " + hashSecureEncoded);  //only for testing environment
		
		return hashSecureEncoded;
	}

	/**
	 * @return the applicationNumber
	 */
	public String getApplicationNumber() {
		return applicationNumber;
	}

	/**
	 * @param applicationNumber
	 *            the applicationNumber to set
	 */
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	/**
	 * @return the extraFields_intendedEDirhamService
	 */
	public String getExtraFields_intendedEDirhamService() {
		return extraFields_intendedEDirhamService;
	}

	/**
	 * @param extraFields_intendedEDirhamService
	 *            the extraFields_intendedEDirhamService to set
	 */
	public void setExtraFields_intendedEDirhamService(String extraFields_intendedEDirhamService) {
		this.extraFields_intendedEDirhamService = extraFields_intendedEDirhamService;
	}

	/**
	 * @return the purchaseCommandServices
	 */
	public List<PurchaseServiceVO> getPurchaseCommandServices() {
		return purchaseCommandServices;
	}

	/**
	 * @param purchaseCommandServices
	 *            the purchaseCommandServices to set
	 */
	public void setPurchaseCommandServices(List<PurchaseServiceVO> purchaseCommandServices) {
		this.purchaseCommandServices = purchaseCommandServices;
	}

	
	/**
	 * @return the transactionAmount
	 */
	public String getTransactionAmount() {
		return transactionAmount;
	}

	/**
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	
	@Override
	public String toString() {
		return "InquiryPaymentRequest [applicationNumber=" + applicationNumber + ", extraFields_intendedEDirhamService="
				+ extraFields_intendedEDirhamService + ", purchaseCommandServices=" + purchaseCommandServices + ", transactionAmount="
				+ transactionAmount + ", requestID=" + requestID + ", currency=" + currency + ", secureHash=" + secureHash + ", version=" + version
				+ ", referenceID=" + referenceID + ", payWebRequestID=" + payWebRequestID + ", action=" + action + ", transactionId=" + transactionId
				+ ", paymentMethodType=" + paymentMethodType + ", paymentMethodTypeDesc=" + paymentMethodTypeDesc + ", transactionRequestDate="
				+ transactionRequestDate + ", otherInfo=" + otherInfo + ", queryString=" + queryString + "]";
	}

	
}
