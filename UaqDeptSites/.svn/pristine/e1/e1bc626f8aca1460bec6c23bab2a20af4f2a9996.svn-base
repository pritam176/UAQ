/**
 * 
 */
package com.uaq.payment;

import static com.uaq.payment.PaymentConstants.PAYMENT_ACTION_PAYWEB;
import static com.uaq.payment.PaymentConstants.PAYMENT_METHOD_TYPE_VISA;
import static com.uaq.payment.PaymentConstants.UAE_CURRENCY_ISO;

import com.uaq.logger.UAQLogger;
import com.uaq.vo.PurchaseServiceVO;

/**
 * This class represents the payweb payment request sub type of Payment Request
 * @author raheem
 * 
 */
public class PayWebPaymentRequest extends PaymentRequest {
	
	private static transient UAQLogger logger = new UAQLogger(PayWebPaymentRequest.class);

	private String amount;

	private String paymentDescription;

	private String merchantModuleSessionID;

	private String transactionRequestDate;

	private String extraFields_f16; // payment method type

	private String extraFields_f18; // terminal id of merchant. already exists
									// in abstract class PaymentRequest inside
									// MerchantAccount. therefore it not
									// required
									// but need to be putted into the request
									// going to eDirham.

	private String extraFields_f14; // optional but we will use it. Merchant
									// site response page url that will receive
									// the response from eDirham.

	private String lang; // eDirham ui interface language. default is Ar. For
						// English its En.
	
	private String intendedEDirhamService;
	
	private PurchaseServiceVO service; // currently, this is to support single service per request
		
	//protected List<PurchaseServiceVO> purchaseCommandServices; // for future use only to support multiple services per request

	@Override
	public String calculateSecureHash() {
		
		logger.enter("PayWebPaymentRequest : calculateSecureHash");

		StringBuffer fields = new StringBuffer(merchantAccount.getSecretKey());
		fields.append(PAYMENT_ACTION_PAYWEB); //Action
		fields.append(referenceID); //ApplicationNumber
		fields.append(merchantAccount.getBankID()); //BankId
		fields.append(UAE_CURRENCY_ISO); //Currency		
		/** start, put this set in loop if multiple services per request required **/		
		fields.append(service.getServiceCode()); //MainCodeSubCode
		if(service.getPrice() != null && !service.getPrice().equals("0")){
			fields.append(service.getPrice()); //Price		
		}
		fields.append(service.getQuantity()); //Quantity
		/** end put this set in loop if mutiple services per request required **/;		
		fields.append(extraFields_f14); //ExtraFields_f14
		fields.append(PAYMENT_METHOD_TYPE_VISA);//ExtraFields_f16 , Payment method type. this is hard coded, default. user will select himself on edirham site
		fields.append(merchantAccount.getTerminalID());//ExtraFields_f18, Terminal Id
		fields.append(PAYMENT_ACTION_PAYWEB); //ExtraFields_intendedEDirhamService			
		fields.append(lang);//Lang
		fields.append(merchantAccount.getMerchantID());//MerchantId
		fields.append(merchantModuleSessionID);//MerchantModuleSessionId
		fields.append(transactionId);//Pun
		fields.append(paymentDescription);//PaymentDescription				
		fields.append(transactionRequestDate);//TransactionRequestDate	
		
		// logger.debug("fields : " + fields.toString()); // for testing purpose only. never log as it contains secret info

		String hashSecureEncoded = PaymentUtil.generateHMACSHA256Hash(fields.toString(), merchantAccount.getSecretKey());

		logger.exit("PayWebPaymentRequest : calculateSecureHash hashSecureEncoded = " + hashSecureEncoded);
		
		return hashSecureEncoded;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * @return the paymentDescription
	 */
	public String getPaymentDescription() {
		return paymentDescription;
	}

	/**
	 * @param paymentDescription
	 *            the paymentDescription to set
	 */
	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}

	/**
	 * @return the merchantModuleSessionID
	 */
	public String getMerchantModuleSessionID() {
		return merchantModuleSessionID;
	}

	/**
	 * @param merchantModuleSessionID
	 *            the merchantModuleSessionID to set
	 */
	public void setMerchantModuleSessionID(String merchantModuleSessionID) {
		this.merchantModuleSessionID = merchantModuleSessionID;
	}

	/**
	 * @return the transactionRequestDate
	 */
	public String getTransactionRequestDate() {
		return transactionRequestDate;
	}

	/**
	 * @param transactionRequestDate
	 *            the transactionRequestDate to set
	 */
	public void setTransactionRequestDate(String transactionRequestDate) {
		this.transactionRequestDate = transactionRequestDate;
	}

	/**
	 * @return the extraFields_f16
	 */
	public String getExtraFields_f16() {
		return extraFields_f16;
	}

	/**
	 * @param extraFields_f16
	 *            the extraFields_f16 to set
	 */
	public void setExtraFields_f16(String extraFields_f16) {
		this.extraFields_f16 = extraFields_f16;
	}

	
	/**
	 * @return the extraFields_f18
	 */
	public String getExtraFields_f18() {
		return extraFields_f18;
	}

	/**
	 * @param extraFields_f18
	 *            the extraFields_f18 to set
	 */
	public void setExtraFields_f18(String extraFields_f18) {
		this.extraFields_f18 = extraFields_f18;
	}

	/**
	 * @return the extraFields_f14
	 */
	public String getExtraFields_f14() {
		return extraFields_f14;
	}

	/**
	 * @param extraFields_f14
	 *            the extraFields_f14 to set
	 */
	public void setExtraFields_f14(String extraFields_f14) {
		this.extraFields_f14 = extraFields_f14;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
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
	 * @return the intendedEDirhamService
	 */
	public String getIntendedEDirhamService() {
		return intendedEDirhamService;
	}

	/**
	 * @param intendedEDirhamService the intendedEDirhamService to set
	 */
	public void setIntendedEDirhamService(String intendedEDirhamService) {
		this.intendedEDirhamService = intendedEDirhamService;
	}

	@Override
	public String toString() {
		return "PayWebPaymentRequest [amount=" + amount + ", paymentDescription=" + paymentDescription + ", merchantModuleSessionID="
				+ merchantModuleSessionID + ", transactionRequestDate=" + transactionRequestDate + ", extraFields_f16=" + extraFields_f16
				+ ", extraFields_f18=" + extraFields_f18 + ", extraFields_f14=" + extraFields_f14 + ", lang=" + lang + ", service=" + service
				+ ", requestID=" + requestID + ", currency=" + currency + ", secureHash=" + secureHash
				+ ", version=" + version + ", referenceID=" + referenceID + ", payWebRequestID=" + payWebRequestID + ", action=" + action
				+ ", transactionId=" + transactionId + ", paymentMethodType=" + paymentMethodType + ", paymentMethodTypeDesc="
				+ paymentMethodTypeDesc + ", otherInfo=" + otherInfo + "]";
	}
	
}
