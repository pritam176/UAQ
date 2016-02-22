package com.uaq.ws.util;


public class PosUtil {
	
	private static transient UAQLogger logger = new UAQLogger(PosUtil.class);
	
	private static final String EMPTY_STRING = "";

	/**
	 * This method is used to convert decimal amount to ISO format, to be sent
	 * to payment service provider
	 * 
	 * @param decimalAmount
	 * @return
	 */

	public static String convertAmountDecimalToISOFormat(String decimalAmount) {
		String isoFormattedAmount = "00";
		if (decimalAmount != null && !decimalAmount.equals(EMPTY_STRING)) {
			try {
				isoFormattedAmount = String.format("%.0f", (new Double(decimalAmount)) * 100);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return isoFormattedAmount;
	}

	/**
	 * This method is used to convert ISO formatted amount to decimal format, to
	 * be shown on the screens
	 * 
	 * @param isoAmount
	 * @return
	 */

	public static Double convertAmountISOToDecimalFormat(String isoAmount) {
		Double dValue = 0.0;
		if (isoAmount != null && !isoAmount.equals(dValue)) {
			try {
				dValue = Double.valueOf(isoAmount) / 100;
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		return dValue;
	}
}
