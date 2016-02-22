package com.uaq.ws.util;

import org.springframework.util.StringUtils;

public class CommonUtil {

	public static void main(String[] args) {
		String tNo = "04 226 2127, 055 785 4494,055 785 4494,055 785 4494";
		System.out.println(formatTelephoneNumber(tNo));
		tNo = "055 785 4494,";
		System.out.println(formatTelephoneNumber(tNo));
		tNo = "02 6182829";
		System.out.println(formatTelephoneNumber(tNo));
		tNo = "042262127,055 785 4494";
		System.out.println(formatTelephoneNumber(tNo));
	}

	/**
	 * Formats the Telephone Number with comma as well as without comma.
	 * 
	 * @param telephoneNumber
	 *            to be formatted
	 * @return formatted telephone number
	 */
	public static String formatTelephoneNumber(String telephoneNumber) {
		String formattedTelephoneNUmber = new String();

		if (null != telephoneNumber && telephoneNumber.contains(",")) {
			String[] telephoneArray = telephoneNumber.split(",");
			for (int i = 0; i < telephoneArray.length; i++) {
				formattedTelephoneNUmber = formattedTelephoneNUmber
						+ getFormattedTelephoneNumber(telephoneArray[i]);
				if (i < telephoneArray.length-1) {
					formattedTelephoneNUmber = formattedTelephoneNUmber + ",";
				}
			}
		} else if (null != telephoneNumber && !telephoneNumber.contains(",")) {
			formattedTelephoneNUmber = getFormattedTelephoneNumber(telephoneNumber);
		}

		return formattedTelephoneNUmber.toString();
	}

	/**
	 * Formats the telephone number
	 * 
	 * @param telephoneNumber
	 * @return
	 */
	public static String getFormattedTelephoneNumber(String telephoneNumber) {
		telephoneNumber = StringUtils.trimAllWhitespace(telephoneNumber);
		StringBuffer formattedTelephoneNUmber = new StringBuffer();
		int length = telephoneNumber.length();
		formattedTelephoneNUmber.append(ApplicationConstants.COUNTRY_CODE);
		formattedTelephoneNUmber.append(ApplicationConstants.EMPTY_SPACE);
		formattedTelephoneNUmber.append(telephoneNumber
				.substring(1, length - 7));
		formattedTelephoneNUmber.append(ApplicationConstants.EMPTY_SPACE);
		formattedTelephoneNUmber.append(telephoneNumber.substring(length - 7,
				length));
		return formattedTelephoneNUmber.toString();
	}

}
