package com.uaq.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author nsharma
 * 
 */
public class UrlTransliterationUtil {

	public static void main(String[] args) {

		String test = "mandate";
		System.out.println(getString(test));
	}

	/**
	 * @param toBeTransliteratedString
	 * @return
	 */
	public static String getTransliteratedString(String toBeTransliteratedString) {
		String transliteratedString = null;
		String tempString = null;
		String CONST_SPACE = " ";
		String CONST_HYPHEN = "-";
		String CONST_AMP = "&";
		String CONST_AND = "and";
		String CONST_DOUBLE_SPACE_REGEX = "(['  ']+)";

		transliteratedString = toBeTransliteratedString.replaceAll(CONST_AMP, CONST_AND);
		Pattern pattern = null;
		Matcher matcher = null;
		pattern = Pattern.compile("[^a-zA-Z0-9]");
		matcher = pattern.matcher(transliteratedString);
		tempString = matcher.replaceAll(" ");
		transliteratedString = tempString.replaceAll(CONST_DOUBLE_SPACE_REGEX, CONST_SPACE);
		transliteratedString = transliteratedString.replaceAll(CONST_SPACE, CONST_HYPHEN);
		transliteratedString = transliteratedString.toLowerCase();
		return transliteratedString;
	}

	/**
	 * @param toBeTransliteratedString
	 * @return
	 */
	public static String getString(String toBeTransliteratedString) {
		String transliteratedString = toBeTransliteratedString;
		String CONST_SPACE = " ";
		String CONST_HYPHEN = "-";
		String CONST_AMP = "&";
		String CONST_AND = "and";
		if (toBeTransliteratedString.contains("-"+ CONST_AND + "-")) {
			transliteratedString = toBeTransliteratedString.replaceAll(CONST_AND, CONST_AMP);
		}
		transliteratedString = transliteratedString.replaceAll(CONST_HYPHEN, CONST_SPACE);
		transliteratedString = transliteratedString.toLowerCase();
		return transliteratedString;
	}
}
