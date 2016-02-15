/**
 * 
 */
package com.uaq.payment;

import java.util.List;

import com.uaq.logger.UAQLogger;
import com.uaq.vo.PurchaseServiceVO;

/**
 * This class represents the payweb payment response sub type of Payment
 * Response
 * 
 * @author raheem
 * 
 */
public class PayWebPaymentResponse extends PaymentResponse {
	
	private static transient UAQLogger logger = new UAQLogger(PayWebPaymentResponse.class);
	
	private String confirmationID;
	
	private PurchaseServiceVO service; // currently, this is to support single service per request
	
	private List<AutoUpdatePaymentResponse> autoUpdateTransactions;
	
	private String eServiceData;

	/**
	 * overriding the parent class method to provide specific secure hash for
	 * payweb payment response
	 */
	@Override
	
	public String calculateSecureHash() {
		
		logger.enter("PayWebPaymentResponse : calculateSecureHash");
			
		StringBuffer fields = new StringBuffer(merchantAccount.getSecretKey());		//secret key
		fields.append(amount); //Amount
		fields.append(merchantAccount.getBankID()); //BankId
		fields.append(collectionCentreFees); //CollectionCenterFees
		fields.append(confirmationID);		// confirmation id
		fields.append(currency); //Currency
		fields.append(eDirhamFees); //edirham fees		
		fields.append(eServiceData); //eService Data
		fields.append(merchantAccount.getMerchantID()); //MerchantId
		fields.append(merchantModuleSessionID); //MerchantModuleSessionId	
		fields.append(transactionId); //Pun
		fields.append(paymentMethodType); // paymentMethodType or card type		
		//fields.append(retrievalRefNumber); //ReceiptId    , empty value in response
		fields.append(status); //status
		fields.append(statusMessage); //status message				
		fields.append(merchantAccount.getTerminalID()); //Terminal Id
		fields.append(transactionAmount); //transactionAmount
		fields.append(transactionResponseDate); // TransactionResponseDate
		
		logger.debug("fields : " + fields.toString()); // only for testing. comment out on production. it contains serious security info.
		
		String hashSecureEncoded = PaymentUtil.generateHMACSHA256Hash(fields.toString(), merchantAccount.getSecretKey());

		logger.exit("PayWebPaymentResponse : calculateSecureHash hashSecureEncoded = " + hashSecureEncoded);
		
		return hashSecureEncoded;
	}
	
	/*public static void main(String[] args){
		
		StringBuffer fields = new StringBuffer("4d93e8788197c0773123862949f611fe");		//secret key
		fields.append("1400"); //Amount
		fields.append("MOFPGSTGB"); //BankId
		fields.append("000"); //CollectionCenterFees
		fields.append("150000275021");		// confirmation id
		fields.append("784"); //Currency
		fields.append("300"); //edirham fees		
		fields.append("[{\"ownerFees\":\"0\",\"mainSubCode\":\"000000-0008\",\"price\":\"1100\",\"amountWithFees\":\"1100\",\"quantity\":\"1\",\"amountWithoutFees\":\"1100\"}]"); //eService Data
		fields.append("811482000"); //MerchantId
		fields.append("A0DFFFCCBC8623D7483456A29EB58AE5.node1"); //MerchantModuleSessionId	
		fields.append("1448517323103"); //Pun or transaction id
		fields.append("9"); // paymentMethodType or card type		
		//fields.append(retrievalRefNumber); //ReceiptId    , empty value in response
		fields.append("0000"); //status
		fields.append("Transaction+was+processed+successfully."); //status message				
		fields.append("81148"); //Terminal Id
		fields.append("1400"); //transactionAmount
		fields.append("26112015095611"); // TransactionResponseDate
		
		//logger.debug("fields : " + fields.toString()); // only for testing. comment out on production. it contains serious security info.
		
		String hashSecureEncoded = PaymentUtil.generateHMACSHA256Hash("4d93e8788197c0773123862949f611fe1400MOFPGSTGB000150000275021784300[{\"ownerFees\":\"0\",\"mainSubCode\":\"000000-0008\",\"price\":\"1100\",\"amountWithFees\":\"1100\",\"quantity\":\"1\",\"amountWithoutFees\":\"1100\"}]811482000A0DFFFCCBC8623D7483456A29EB58AE5.node1144851732310390000Transaction was processed successfully.81148140026112015095611", "4d93e8788197c0773123862949f611fe");

		System.out.println(" hashSecureEncoded = " + hashSecureEncoded);
		//logger.exit("PayWebPaymentResponse : calculateSecureHash hashSecureEncoded = " + hashSecureEncoded);
	}*/
	
	/**
	 * @return the confirmationID
	 */
	public String getConfirmationID() {
		return confirmationID;
	}

	/**
	 * @param confirmationID
	 *            the confirmationID to set
	 */
	public void setConfirmationID(String confirmationID) {
		this.confirmationID = confirmationID;
	}

	/**
	 * @return the autoUpdateTransactions
	 */
	public List<AutoUpdatePaymentResponse> getAutoUpdateTransactions() {
		return autoUpdateTransactions;
	}

	/**
	 * @param autoUpdateTransactions the autoUpdateTransactions to set
	 */
	public void setAutoUpdateTransactions(List<AutoUpdatePaymentResponse> autoUpdateTransactions) {
		this.autoUpdateTransactions = autoUpdateTransactions;
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

	/**
	 * @return the eServiceData
	 */
	public String geteServiceData() {
		return eServiceData;
	}

	/**
	 * @param eServiceData the eServiceData to set
	 */
	public void seteServiceData(String eServiceData) {
		this.eServiceData = eServiceData;
	}
		
	
	@Override
	public String toString() {
		return "PayWebPaymentResponse [confirmationID=" + confirmationID + ", service=" + service + ", autoUpdateTransactions="
				+ autoUpdateTransactions + ", action=" + action + ", secureHash=" + secureHash + ", status=" + status + ", statusMessage="
				+ statusMessage + ", transactionId=" + transactionId + ", retrievalRefNumber=" + retrievalRefNumber + ", eDirhamFees=" + eDirhamFees
				+ ", eDirhamFeesDecimal=" + eDirhamFeesDecimal + ", collectionCentreFees=" + collectionCentreFees + ", collectionCentreFeesDecimal="
				+ collectionCentreFeesDecimal + ", transactionAmount=" + transactionAmount + ", transactionAmountDecimal=" + transactionAmountDecimal
				+ ", amount=" + amount + ", amountDecimal=" + amountDecimal + ", transactionResponseDate=" + transactionResponseDate
				+ ", referenceId=" + referenceId + ", otherInfo=" + otherInfo + ", currency=" + currency + ", paymentMethodType=" + paymentMethodType
				+ ", eServiceData=" + eServiceData +", merchantModuleSessionID=" + merchantModuleSessionID + "]";
	}
	
}
