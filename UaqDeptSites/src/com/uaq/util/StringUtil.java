package com.uaq.util;

import java.util.Arrays;
import java.util.List;

/**
 * This string utility is used for String conversion to and from other types.
 * 
 * @author mraheem
 * 
 */

public final class StringUtil {
	
	private StringUtil(){
		
	}
	
	
	public static boolean  isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

	/**
	 * This method is used to comvert the given string array to a command
	 * separated string.
	 * 
	 * @param arrayValues
	 * @return
	 */
	public static String getCommaSeparatedFromArray(String[] arrayValues) {

		if (arrayValues != null && arrayValues.length > 0) {
			StringBuilder nameBuilder = new StringBuilder();

			for (String n : arrayValues) {
				nameBuilder.append("'").append(n.replaceAll("'", "\\\\'")).append("',");
				// can also do the following
				// nameBuilder.append("'").append(n.replaceAll("'",
				// "''")).append("',");
			}
			nameBuilder.deleteCharAt(nameBuilder.length() - 1);
			return nameBuilder.toString();
		} else {
			return "";
		}
	}

	/**
	 * This method is used to comvert the given comma separated string to a list
	 * of strings.
	 * 
	 * @param arrayValues
	 * @return
	 */
	public static List<String> getListFromArray(String commanSeparatedString) {

		List<String> valuesList = null;

		if (commanSeparatedString != null && commanSeparatedString.length() > 0) {
			valuesList = Arrays.asList(commanSeparatedString.split(","));
		}

		return valuesList;
	}

	public static String getString(String url) {
		String urlString = url;
		String site = urlString.split("/")[1];
		urlString = urlString.substring(new String("/" + site + "/").length(), urlString.length());

		return urlString;
	}

}
