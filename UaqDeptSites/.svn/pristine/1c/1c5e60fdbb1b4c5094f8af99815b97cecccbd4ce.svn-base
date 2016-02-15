/**
 * 
 */
package com.uaq.util;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author tacme03
 *
 */
public class TranslationUtil {
	
	ResourceBundle resourceBundle = null;
	
	public TranslationUtil(String languageCode){
		resourceBundle = ResourceBundle.getBundle("messages", new Locale(languageCode));
	}
		
	public String getTranslation(String key) throws UnsupportedEncodingException{
		String value = null;
		try{
			value = new String(resourceBundle.getString(key).getBytes("ISO-8859-1"), "UTF-8");
		} catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		return value;
	}
	
	/**
	 * @param resourceBundle the resourceBundle to set
	 */
	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}	
		
}
