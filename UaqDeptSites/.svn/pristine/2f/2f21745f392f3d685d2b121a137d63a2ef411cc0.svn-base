/**
 * 
 */
package com.uaq.payment;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.uaq.vo.PurchaseServiceVO;

/**
 * /** This class represents the inquiry payment response sub type of Payment
 * Response
 * 
 * @author raheem
 * 
 */
public class InquiryPaymentResponse extends PaymentResponse {

	private List<PurchaseServiceVO> PurchaseCommandServices;

	private Integer eServicesCount;

	/**
	 * overriding the parent class method to provide specific secure hash for
	 * inquiry payment response
	 */
	@Override
	public String calculateSecureHash() {

		StringBuffer fields = new StringBuffer(merchantAccount.getSecretKey());
		fields.append(merchantAccount.getMerchantID());
		fields.append(transactionResponseDate);
		fields.append(transactionId);

		String hashSecureEncoded = DigestUtils.sha256Hex(fields.toString());

		return hashSecureEncoded;
	}

	/**
	 * @return the PurchaseCommandServices
	 */
	public List<PurchaseServiceVO> getPurchaseCommandServices() {
		return PurchaseCommandServices;
	}

	/**
	 * @param PurchaseCommandServices
	 *            the PurchaseCommandServices to set
	 */
	public void setPurchaseCommandServices(List<PurchaseServiceVO> PurchaseCommandServices) {
		this.PurchaseCommandServices = PurchaseCommandServices;
	}

	/**
	 * @return the eServicesCount
	 */
	public Integer geteServicesCount() {
		return eServicesCount;
	}

	/**
	 * @param eServicesCount
	 *            the eServicesCount to set
	 */
	public void seteServicesCount(Integer eServicesCount) {
		this.eServicesCount = eServicesCount;
	}

	@Override
	public String toString() {
		return "InquiryPaymentResponse [PurchaseCommandServices=" + PurchaseCommandServices + ", secureHash=" + secureHash + ", status="
				+ status + ", statusMessage=" + statusMessage + ", transactionId=" + transactionId + ", retrievalRefNumber=" + retrievalRefNumber
				+ ", eDirhamFees=" + eDirhamFees + ", collectionCentreFees=" + collectionCentreFees + ", transactionAmount=" + transactionAmount
				+ ", eServicesCount=" + eServicesCount + ", transactionResponseDate=" + transactionResponseDate + ", referenceId="
				+ referenceId + "]";
	}

}
