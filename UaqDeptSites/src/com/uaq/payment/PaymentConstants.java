/**
 * 
 */
package com.uaq.payment;

/**
 * This interface holds the constants used in payment processes.
 * 
 * @author raheem
 * 
 */
public interface PaymentConstants {
	
	public static final String PAYMENT_METHOD_TYPE_VISA = "1-1";
	public static final String PAYMENT_METHOD_TYPE_MASTERCARD = "1-2";
	public static final String PAYMENT_METHOD_TYPE_GII_CO_BRANDED_MAG_STRIPE_CARD = "1-9"; // blue
	public static final String PAYMENT_METHOD_TYPE_Corporate_GII_Co_Branded_Chip_Card = "1-10"; // not available yet
	public static final String PAYMENT_METHOD_TYPE_Personal_GII_Co_Branded_Chip_Card = "1-11"; // not available yet
	public static final String PAYMENT_METHOD_TYPE_GII_PLC_Mag_Stripe_Card = "1-12"; // red
	public static final String PAYMENT_METHOD_TYPE_GII_PLC_CHIP_CARD = "1-13"; // gold
		
	public static final String PAYMENT_REQUEST_TYPE_INQUIRY = "Inquiry Request";
	public static final String PAYMENT_REQUEST_TYPE_PAYWEB = "PayWeb Request";
	public static final String PAYMENT_REQUEST_TYPE_PREAUTH = "PreAuth Request";
	public static final String PAYMENT_REQUEST_TYPE_AUTOUPDATE = "AutoUpdate Request";
	public static final String PAYMENT_RESPONSE_TYPE_INQUIRY = "Inquiry Response";
	public static final String PAYMENT_RESPONSE_TYPE_PAYWEB = "PayWeb Response";
	public static final String PAYMENT_RESPONSE_TYPE_PREAUTH = "PreAuth Response";
	public static final String PAYMENT_RESPONSE_TYPE_AUTOUPDATE = "AutoUpdate Response";

	public static final String RESPONSE_STATUS_SUCCESS = "0000";
	
	public static final String PAYMENT_ACTION_INQUIRY = "28";
	public static final String PAYMENT_ACTION_PAYWEB = "0";
	public static final String PAYMENT_ACTION_PREAUTH = "32";
	public static final String PAYMENT_ACTION_AUTOUPDATE = "13";
	public static final String PAYMENT_ACTION_COMPLETION = "33";
	
	public static final String PAYMENT_RESPONSE_STATUS_CODE_UPDATED_SUCCESSFUL = "0000";
	public static final String PAYMENT_RESPONSE_STATUS_CODE_PAYMENT_METHOD_SELECTED = "6511";
	public static final String PAYMENT_RESPONSE_STATUS_CODE_UPDATED_FAILED = "6512";
	public static final String PAYMENT_RESPONSE_STATUS_CODE_ALREADY_UPDATED = "6514";
	public static final String PAYMENT_RESPONSE_STATUS_CODE_ORIGINAL_NOT_CONFIRMED = "6516";
	public static final String PAYMENT_RESPONSE_STATUS_CODE_TXN_STATUS_FAILED = "6517";	
	public static final String PAYMENT_RESPONSE_STATUS_CODE_TXN_NOT_FOUND = "2012";
	
	public static final String UAE_CURRENCY_ISO = "784";
	
	public static final String GENERAL_FEE_ID = "0025031";
	public static final String EGD_DEPT_ID = "002";

}
