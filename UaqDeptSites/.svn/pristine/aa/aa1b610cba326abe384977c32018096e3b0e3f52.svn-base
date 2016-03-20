package com.uaq.payment;

import static com.uaq.payment.PaymentConstants.PAYMENT_ACTION_AUTOUPDATE;
import static com.uaq.payment.PaymentConstants.PAYMENT_ACTION_PAYWEB;
import static com.uaq.payment.PaymentConstants.PAYMENT_ACTION_PREAUTH;
import static com.uaq.payment.PaymentConstants.PAYMENT_METHOD_TYPE_VISA;
import static com.uaq.payment.PaymentConstants.PAYMENT_RESPONSE_STATUS_CODE_ALREADY_UPDATED;
import static com.uaq.payment.PaymentConstants.PAYMENT_RESPONSE_STATUS_CODE_PAYMENT_METHOD_SELECTED;
import static com.uaq.payment.PaymentConstants.PAYMENT_RESPONSE_STATUS_CODE_TXN_NOT_FOUND;
import static com.uaq.payment.PaymentConstants.PAYMENT_RESPONSE_STATUS_CODE_UPDATED_FAILED;
import static com.uaq.payment.PaymentConstants.RESPONSE_STATUS_SUCCESS;
import static com.uaq.payment.PaymentConstants.UAE_CURRENCY_ISO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.uaq.common.PropertiesUtil;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.PaymentServiceManager;
import com.uaq.vo.PurchaseServiceVO;

public class PaymentUtil {
	
	private static transient UAQLogger logger = new UAQLogger(PaymentUtil.class);
	private static final String EMPTY_STRING = "";
	private static final String URL_SEPARATER = "/";
	private static final String MERCHANT_REDIRECT_ACTION = "paymentConfirmation.html";
	private static final String ENCODING_UTF8 = "UTF-8";
	private static final String EMPERSAND_SIGN = "&";
	private static final String EQUALS_SIGN = "=";
	
	private static SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");

	public static String convertAmountDecimalToISOFormat(String decimalAmount) {
		
		String isoFormattedAmount = "00";
		
		if ((decimalAmount != null) && (!decimalAmount.equals(EMPTY_STRING))) {
			try {
				isoFormattedAmount = String.format("%.0f", new Object[] { Double.valueOf(new Double(decimalAmount).doubleValue() * 100.0D) });
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return isoFormattedAmount;
	}

	public static Double convertAmountISOToDecimalFormat(String isoAmount) {
		
		Double dValue = Double.valueOf(0.0D);
		
		if ((isoAmount != null) && (!isoAmount.equals(dValue))) {
			try {
				dValue = Double.valueOf(Double.valueOf(isoAmount).doubleValue() / 100.0D);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		return dValue;
	}

	public static AutoUpdatePaymentRequest fillAutoUpdatePaymentRequest(String paywebTransactionId, MerchantAccount merchantAccount) {
		
		AutoUpdatePaymentRequest autoUpdatePaymentRequest = new AutoUpdatePaymentRequest();

		autoUpdatePaymentRequest.setAction(PAYMENT_ACTION_AUTOUPDATE);
		autoUpdatePaymentRequest.setMerchantAccount(merchantAccount);
		autoUpdatePaymentRequest.setTransactionRequestDate(df.format(Calendar.getInstance().getTime()));
		autoUpdatePaymentRequest.setTransactionId(paywebTransactionId);
		autoUpdatePaymentRequest.setSecureHash(autoUpdatePaymentRequest.calculateSecureHash());

		return autoUpdatePaymentRequest;
	}

	public static PayWebPaymentRequest fillWebPaymentRequest(PurchaseServiceVO service, String paymentDescription, String merchantModuleSessionID,
			String referenceID, String lang, String paymentType, MerchantAccount merchantAccount,String transactionId) {
		
		logger.enter("fillWebPaymentRequest");

		PayWebPaymentRequest webPaymentRequest = new PayWebPaymentRequest();

		webPaymentRequest.setMerchantAccount(merchantAccount);
		webPaymentRequest.setAction((paymentType != null) && (!paymentType.equals(EMPTY_STRING)) && (paymentType.equalsIgnoreCase("Pre-Auth")) ? PAYMENT_ACTION_PREAUTH : PAYMENT_ACTION_PAYWEB);
		webPaymentRequest.setCurrency(UAE_CURRENCY_ISO);

		webPaymentRequest.setTransactionId(transactionId);
		try {
			webPaymentRequest.setPaymentDescription(URLEncoder.encode(paymentDescription, ENCODING_UTF8));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			webPaymentRequest.setPaymentDescription("No Description");
		}
		webPaymentRequest.setMerchantModuleSessionID(merchantModuleSessionID);
		webPaymentRequest.setTransactionRequestDate(df.format(Calendar.getInstance().getTime()));
		webPaymentRequest.setPaymentMethodType(PAYMENT_METHOD_TYPE_VISA);
		webPaymentRequest.setExtraFields_f16(PAYMENT_METHOD_TYPE_VISA);

		webPaymentRequest.setExtraFields_f18(merchantAccount.getTerminalID());

		webPaymentRequest.setExtraFields_f14(PropertiesUtil.getProperty("globalUrl") + URL_SEPARATER + lang + URL_SEPARATER + MERCHANT_REDIRECT_ACTION
				+ "?languageCode=" + lang);
		webPaymentRequest.setLang(lang);
		webPaymentRequest.setReferenceID(referenceID);
		webPaymentRequest.setIntendedEDirhamService((paymentType != null) && (!paymentType.equals(EMPTY_STRING)) && (paymentType.equalsIgnoreCase("Pre-Auth")) ? PAYMENT_ACTION_PREAUTH
				: PAYMENT_ACTION_PAYWEB);
		webPaymentRequest.setService(service);

		webPaymentRequest.setSecureHash(webPaymentRequest.calculateSecureHash());

		logger.exit("fillWebPaymentRequest");

		return webPaymentRequest;
	}

	public static PayWebPaymentResponse fillWebPaymentResponse(HttpServletRequest request, MerchantAccount merchantAccount) {
		
		PayWebPaymentResponse paywebPaymentResponse = new PayWebPaymentResponse();

		paywebPaymentResponse.setStatus(request.getParameter("Response.Status"));
		paywebPaymentResponse.setStatusMessage(request.getParameter("Response.StatusMessage"));
		paywebPaymentResponse.setPaymentMethodType(request.getParameter("Response.PaymentMethodType"));
		paywebPaymentResponse.setConfirmationID(request.getParameter("Response.ConfirmationID"));
		paywebPaymentResponse.setTransactionId(request.getParameter("Response.PUN"));
		paywebPaymentResponse.setAmount(request.getParameter("Response.Amount"));
		paywebPaymentResponse.setTransactionAmount(request.getParameter("Response.TransactionAmount"));
		paywebPaymentResponse.setTransactionResponseDate(request.getParameter("Response.TransactionResponseDate"));
		paywebPaymentResponse.seteDirhamFees(request.getParameter("Response.EDirhamFees"));
		paywebPaymentResponse.setCollectionCentreFees(request.getParameter("Response.CollectionCenterFees"));
		paywebPaymentResponse.setMerchantModuleSessionID(request.getParameter("Response.MerchantModuleSessionID"));
		paywebPaymentResponse.setRetrievalRefNumber(request.getParameter("Response.ReceiptID"));
		paywebPaymentResponse.setCurrency(request.getParameter("Response.Currency"));
		paywebPaymentResponse.seteServiceData(request.getParameter("Response.EServiceData"));

		paywebPaymentResponse.setService((PurchaseServiceVO) getPurchaseServicesFromJson(request.getParameter("Response.EServiceData")).get(0));

		paywebPaymentResponse.setSecureHash(request.getParameter("Response.SecureHash"));

		paywebPaymentResponse.setMerchantAccount(merchantAccount);

		paywebPaymentResponse.setReferenceId((String) request.getAttribute("purchaseId"));

		return paywebPaymentResponse;
	}

	private static List<PurchaseServiceVO> getPurchaseServicesFromJson(String jsonStr) {
		
		logger.enter("getPurchaseServicesFromJson");
		logger.debug("jsonStr = " + jsonStr);

		List<PurchaseServiceVO> purchaseServices = new ArrayList<PurchaseServiceVO>();
		if ((jsonStr != null) && (!jsonStr.isEmpty())) {
			JsonElement jelement = new JsonParser().parse(jsonStr);
			JsonArray jarray = jelement.getAsJsonArray();
			for (int i = 0; i < jarray.size(); i++) {
				PurchaseServiceVO service = new PurchaseServiceVO();

				JsonObject jobject = jarray.get(i).getAsJsonObject();
				String serviceCode = jobject.get("mainSubCode").toString().replace("\"", EMPTY_STRING);
				String price = jobject.get("price").toString().replace("\"", EMPTY_STRING);
				String quantity = jobject.get("quantity").toString().replace("\"", EMPTY_STRING);
				String ownerFees = jobject.get("ownerFees").toString().replace("\"", EMPTY_STRING);
				String amountWithoutFees = jobject.get("amountWithoutFees").toString().replace("\"", EMPTY_STRING);
				String amountWithFees = jobject.get("amountWithFees").toString().replace("\"", EMPTY_STRING);

				service.setServiceCode(serviceCode);
				service.setPrice(price);
				service.setQuantity(quantity);
				service.setOwnerFees(ownerFees);
				service.setAmountWithFees(amountWithFees);
				service.setAmountWithoutFees(amountWithoutFees);

				purchaseServices.add(service);
			}
		}
		
		logger.debug("purchaseServices = " + purchaseServices.toString());
		logger.exit("getPurchaseServicesFromJson");

		return purchaseServices;
	}

	public static String buildQueryStringForAutoUpdate(AutoUpdatePaymentRequest autoUpdatePaymentRequest) {
		
		StringBuffer requestQuery = new StringBuffer();

		requestQuery.append("Action").append(EQUALS_SIGN).append(PAYMENT_ACTION_AUTOUPDATE).append(EMPERSAND_SIGN).append("MerchantID").append(EQUALS_SIGN)
				.append(autoUpdatePaymentRequest.getMerchantAccount().getMerchantID()).append(EMPERSAND_SIGN).append("BankID").append(EQUALS_SIGN)
				.append(autoUpdatePaymentRequest.getMerchantAccount().getBankID()).append(EMPERSAND_SIGN).append("PUN").append(EQUALS_SIGN)
				.append(autoUpdatePaymentRequest.getTransactionId()).append(EMPERSAND_SIGN).append("TransactionRequestDate").append(EQUALS_SIGN)
				.append(autoUpdatePaymentRequest.getTransactionRequestDate()).append(EMPERSAND_SIGN).append("SecureHash").append(EQUALS_SIGN)
				.append(autoUpdatePaymentRequest.getSecureHash());

		return requestQuery.toString();
	}

	public static AutoUpdatePaymentResponse fillAutoUpdatePaymentResponse(String responseText,String transactionId) {
		
		logger.enter("fillAutoUpdatePaymentResponse");

		AutoUpdatePaymentResponse autoUpdatePaymentResponse = new AutoUpdatePaymentResponse();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		if ((responseText != null) && (!responseText.isEmpty())) {
			try {
				Map<String, String> responseMap = getResponseMap(responseText);

				String responseStatus = extractValue(responseMap, "Response.Status");
				autoUpdatePaymentResponse.setAction(PAYMENT_ACTION_AUTOUPDATE);
				autoUpdatePaymentResponse.setStatus(responseStatus);
				autoUpdatePaymentResponse.setStatusMessage(extractValue(responseMap, "Response.StatusMessage"));
				
				
				
//				autoUpdatePaymentResponse.setTransactionId(String.valueOf(Calendar.getInstance().getTimeInMillis()));
				autoUpdatePaymentResponse.setTransactionId(transactionId);
				if ((responseStatus.equals(RESPONSE_STATUS_SUCCESS)) || 
						(responseStatus.equals(PAYMENT_RESPONSE_STATUS_CODE_ALREADY_UPDATED)) || 
						(responseStatus.equals(PAYMENT_RESPONSE_STATUS_CODE_UPDATED_FAILED))) {
					autoUpdatePaymentResponse.setOriginalTransactionAction(extractValue(responseMap, "Response.Action"));
					autoUpdatePaymentResponse.setRetrievalRefNumber(extractValue(responseMap, "Response.ConfirmationID"));
					autoUpdatePaymentResponse.setPaywebTransactionId(extractValue(responseMap, "Response.PUN"));
					autoUpdatePaymentResponse.setOriginalTransactionStatus(extractValue(responseMap, "Response.OriginalTransactionStatus"));

					autoUpdatePaymentResponse.setOriginalTransactionStatusMessage(extractValue(responseMap,
							"Response.OriginalTransactionStatusMessage"));

					autoUpdatePaymentResponse.setTransactionAmount(extractValue(responseMap, "Response.TransactionAmount"));
					autoUpdatePaymentResponse.setExtractStatus(extractValue(responseMap, "Response.ExtractStatus"));
					autoUpdatePaymentResponse.setTransactionResponseDate(df.format(sdf.parse(extractValue(responseMap,
							"Response.TransactionResponseDate"))));

					autoUpdatePaymentResponse.setCollectionCentreFees(extractValue(responseMap, "Response.CollectionCenterFees"));
					autoUpdatePaymentResponse.seteDirhamFees(extractValue(responseMap, "Response.EDirhamFees"));
					autoUpdatePaymentResponse.setService((PurchaseServiceVO) getPurchaseServicesFromJson(
							extractValue(responseMap, "Response.EServiceData")).get(0));
				} else if (responseStatus.equals(PAYMENT_RESPONSE_STATUS_CODE_PAYMENT_METHOD_SELECTED)) {
					autoUpdatePaymentResponse.setPaywebTransactionId(extractValue(responseMap, "Response.PUN"));
					autoUpdatePaymentResponse.setTransactionResponseDate(df.format(sdf.parse(extractValue(responseMap,
							"Response.TransactionResponseDate"))));

					autoUpdatePaymentResponse.setOriginalTransactionStatus(extractValue(responseMap, "Response.OriginalTransactionStatus"));

					autoUpdatePaymentResponse.setOriginalTransactionStatusMessage(extractValue(responseMap,
							"Response.OriginalTransactionStatusMessage"));

					autoUpdatePaymentResponse.setTransactionAmount(extractValue(responseMap, "Response.TransactionAmount"));
				} else if (responseStatus.equals(PAYMENT_RESPONSE_STATUS_CODE_TXN_NOT_FOUND)) {
					autoUpdatePaymentResponse.setPaywebTransactionId(extractValue(responseMap, "Response.PUN"));
					autoUpdatePaymentResponse.setTransactionResponseDate(df.format(sdf.parse(extractValue(responseMap,
							"Response.TransactionResponseDate"))));
				} else {
					autoUpdatePaymentResponse.setTransactionResponseDate(df.format(Calendar.getInstance().getTime()));
				}
			} catch (ParseException e) {
				logger.error(e.getMessage());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		
		logger.exit("fillAutoUpdatePaymentResponse");

		return autoUpdatePaymentResponse;
	}

	public static AutoUpdatePaymentResponse autoUpdatePaymentTransaction(String transactionId, MerchantAccount merchantAccount,String newTransactionId) throws UAQException {
		
		logger.enter("autoUpdatePaymentTransaction");

		PaymentServiceManager paymentServiceManager = new PaymentServiceManager();
		AutoUpdatePaymentResponse autoUpdatePaymentResponse = null;
		try {
			autoUpdatePaymentResponse = paymentServiceManager.doAutoUpdateTransaction(fillAutoUpdatePaymentRequest(transactionId, merchantAccount),newTransactionId);
		} catch (Exception e) {
			logger.error(e.getMessage()); 
			throw new UAQException(e.getMessage());
		}
		
		logger.exit("autoUpdatePaymentTransaction");

		return autoUpdatePaymentResponse;
	}

	private static String extractValue(Map<String, String> responseTable, String nodeName) {
		String result = EMPTY_STRING;
		if (responseTable.containsKey(nodeName)) {
			result = (String) responseTable.get(nodeName);
		}
		return result;
	}

	private static String getRandomUUID() {
		
		String randomUUIDString = null;
		
		try {
			UUID uuid = UUID.randomUUID();
			randomUUIDString = uuid.toString().toUpperCase();

			randomUUIDString = randomUUIDString.substring(0, 8)
					+ randomUUIDString.substring(randomUUIDString.length() - 12, randomUUIDString.length());
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return randomUUIDString;
	}

	private static Map<String, String> getResponseMap(String dataGram) {
		
		logger.enter("getResponseMap");

		Map<String, String> responseTable = new HashMap<String, String>();
		
		String[] pairs = dataGram.split(EMPERSAND_SIGN);
		boolean handleAndSign = true;
		if ((dataGram != null) && (dataGram.trim().length() > 0)) {
			String key = null;
			String value = null;
			for (String nameValue : pairs) {
				if (nameValue.indexOf(EQUALS_SIGN) == -1) {
					value = (String) responseTable.get(key) + EMPERSAND_SIGN + nameValue;
				} else {
					key = nameValue.substring(0, nameValue.indexOf(EQUALS_SIGN));
					value = nameValue.substring(nameValue.indexOf(EQUALS_SIGN) + 1);
				}
				if ((handleAndSign) && (value.indexOf("//&") != -1)) {
					value = value.replaceAll("//&", EMPERSAND_SIGN);
				}
				responseTable.put(key, value);
			}
		}
		
		logger.exit("getResponseMap");

		return responseTable;
	}

	public static String generateHMACSHA256Hash(String data, String secretKey) {
		
		logger.enter("generateHMACSHA256Hash : " + data);

		String result = EMPTY_STRING;
		try {
			String HMAC_SHA256_ALGORITHM = "HmacSHA256";

			SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256_ALGORITHM);

			Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
			mac.init(signingKey);

			byte[] rawHmac = mac.doFinal(data.getBytes());

			result = Hex.encodeHexString(rawHmac);

			result = new String(Base64.encodeBase64(result.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		logger.exit("generateHMACSHA256Hash result : " + result);

		return result;
	}
}
