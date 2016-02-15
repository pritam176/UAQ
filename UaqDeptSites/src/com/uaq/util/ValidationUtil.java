package com.uaq.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a validation utility class which validates common fields such as
 * name, phone, mobile, email etc
 * 
 * @author mraheem
 * 
 */

public class ValidationUtil {

	public static boolean isEmpty(String var) {
		if (var == null || var.equals("")) {
			return (true);
		} else {
			return (false);
		}
	}

	public static boolean validateName(String name) {
		if (name.equals("")) {
			return false;
		}

		String restrictedChars = "0123456789";

		for (int i = 0; i < name.length(); i++) {
			if (restrictedChars.indexOf(name.charAt(i)) > -1) {
				return false;
			}
		}

		return true;
	}

	public static boolean checkLetter(String var) {
		if (var == null || var.equals("")) {
			return (true);
		}
		char[] characters = var.toCharArray();
		for (int f = 0; f < characters.length; f++) {
			if (!Character.isLetter(characters[f])) {
				return (false);
			}
		}
		return (true);
	}

	public static boolean isValidMobileNumer(String number) {

		String allowedChars = "0123456789()-";

		for (int i = 0; i < number.length(); i++) {
			if (allowedChars.indexOf(number.charAt(i)) == -1) {
				return false;
			}
		}

		return true;
	}

	public static boolean validateEmail(String emailAddress) {
		if (emailAddress.equals("")) {
			return true; // allowed
		}
		String regex = "^\\w(\\.?[\\w-])*@\\w(\\.?[-\\w])*\\.([a-z]{3}(\\.[a-z]{2})?|[a-z]{2}(\\.[a-z]{2})?)$";
		return emailAddress.matches(regex); // get result from regular
											// expression checker
	}

	public static boolean checkAlphaNumeric(String var) {
		if (var == null || var.equals("")) {
			return (true);
		}
		char[] characters = var.toCharArray();
		for (int f = 0; f < characters.length; f++) {
			if (!Character.isLetterOrDigit(characters[f])) {
				return (true);
			}
		}
		return (false);
	}

	public static boolean checkNumeric(String var) {
		if (var == null || var.equals("")) { // check null or empty
			return (true); // allowed
		}
		char[] characters = var.toCharArray();
		for (int f = 0; f < characters.length; f++) { // chars iteration
			if (!Character.isDigit(characters[f])) {
				return (false);
			}
		}
		return (true);
	}

	/**
	 * This method is used to validate and allows alpha, numeric and space for
	 * both english and arabic
	 * 
	 * @author mraheem
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */

	public static boolean validateArabicAndEnglish(String text) throws UnsupportedEncodingException {

		Pattern pattern;
		Matcher matcher;

		// \u0600-\u06FF this is code point for Arabic UTF-8 characters set
		// range. Please see wikipedia for full list
		String LETTER_PATTERN = "[\"a-zA-Z0-9\u0600-\u06FF ]*$";
		pattern = Pattern.compile(LETTER_PATTERN);
		matcher = pattern.matcher(text);
		return matcher.matches();
	}

	/**
	 * This method is used to validate and allows alpha, numeric values
	 * 
	 * @author mraheem
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */

	public static boolean validateAlphaNumeric(String text) {

		Pattern pattern;
		Matcher matcher;

		String LETTER_PATTERN = "[a-zA-Z0-9]*$";
		pattern = Pattern.compile(LETTER_PATTERN);
		matcher = pattern.matcher(text);
		return matcher.matches();
	}

	/**
	 * This method allows only enlglish letters with space but no numbers and
	 * all arabic letters including numbers
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */

	public static boolean validateArabicAndEnglishLetters(String text) throws UnsupportedEncodingException {

		Pattern pattern;
		Matcher matcher;

		// \u0600-\u06FF this is code point for Arabic UTF-8 characters set
		// range. Please see wikipedia for full list
		String LETTER_PATTERN = "[a-zA-Z\u0600-\u06FF ]*$";
		pattern = Pattern.compile(LETTER_PATTERN);
		matcher = pattern.matcher(text);
		return matcher.matches();
	}

}
