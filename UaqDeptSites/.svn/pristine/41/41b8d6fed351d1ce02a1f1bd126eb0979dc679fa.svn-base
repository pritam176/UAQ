package com.uaq.common;

import javax.servlet.http.Cookie;

import com.uaq.logger.UAQLogger;

/**
 * CookieUtil - util for all the cookie operations in the application
 * 
 * @author nsharma
 * 
 */
public class CookieUtil {
	private static transient UAQLogger logger = new UAQLogger(CookieUtil.class);

	/**
	 * On the Key present in the browser cookie - return the cookie value else
	 * an empty string
	 * 
	 * @param cookies
	 * @param cookieName
	 * @param defaultValue
	 * @return
	 */
	public static String getCookieValue(Cookie[] cookies, String cookieName) {
		logger.enter("getCookieValue");
		String cookieValue = "";
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (null != cookie && null != cookie.getName() && cookieName.equals(cookie.getName()))
					if (null != cookie.getValue()) {
						cookieValue = cookie.getValue();
						break;
					}
			}

		}

		logger.exit("getCookieValue");
		return cookieValue;
	}

	/**
	 * Sets the cookie value to the cookie key
	 * 
	 * @param cookieKey
	 * @param cookieValue
	 * @param cookieAge
	 * @return
	 */
	public static Cookie setCookieValue(String cookieKey, String cookieValue, int cookieAge) {
		logger.enter("setCookieValue");
		Cookie userCookie = new Cookie(cookieKey, cookieValue);
		// Set cookie age for 2 days
		userCookie.setMaxAge(cookieAge);
		userCookie.setPath("/");
		logger.exit("setCookieValue");
		return userCookie;

	}
}
