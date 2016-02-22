package com.uaq.ws.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.uaq.ws.exception.UAQException;

/**
 * <pre>
 * Utility method to read the configuration details.
 * All the properties file keys can be read from this utility
 * Assumption:
 * No key will be same in the application
 * All the properties will be loaded from the properties file &amp;
 * will be cached.
 * </pre>
 * 
 * @author nsharma
 */
public final class ConfUtils {

	private static transient Logger logger = Logger.getLogger(ConfUtils.class);

	private static String[] propertyFileList = {"Application.properties"};

	private static ConfUtils confUtils = null;

	private static Properties confProperties = null;

	public ConfUtils() throws UAQException {
		init();
	}

	/**
	 * Implementing the ConfUtils as singleton
	 * 
	 * @return
	 */
	public static ConfUtils getInstance() throws UAQException {
		if (null == confUtils) {
			synchronized (ConfUtils.class) {
				if (null == confUtils) {
					confUtils = new ConfUtils();
				}
			}
		}
		return confUtils;
	}

	public static String getValue(String key) {
		String value = null;
		try {
			value = getInstance().getString(key);
		} catch (UAQException e) {
			logger.error("Error while instantiating the class");
		}
		if (value == null) {
			logger.error("Property value not found for the key " + key);
		}
		return value;
	}

	public static List<String> getListValues(String key) {
		List<String> values = new ArrayList<String>();
		String value = null;
		value = ConfUtils.getValue(key);
		if (value != null) {
			String[] csvs = value.split(",");
			for (String definition : csvs) {
				values.add(definition);
			}
		}
		return values;
	}

	public String getString(String key) {

		return confProperties.getProperty(key, "");
	}

	/**
	 * loading properties object
	 * 
	 * @param dir
	 */
	public Properties loadFromClassPath(String propertiesFileName) {

		Properties property = new Properties();

		try {
			InputStream inputStreamReader = ConfUtils.class.getClassLoader()
					.getResourceAsStream(propertiesFileName);
			property.load(inputStreamReader);
		} catch (Exception exception) {
			logger.error("Error while loading the property file :"
					+ propertiesFileName, exception);
		}
		return property;
	}

	/**
	 * loading properties file with locale
	 * 
	 * @param propertiesFileName
	 * @param locale
	 * @return property
	 */
	public Properties loadFromClassPath(String propertiesFileName, String locale) {

		Properties property = new Properties();

		try {
			InputStream inputStreamReader = ConfUtils.class.getClassLoader()
					.getResourceAsStream(
							propertiesFileName + "_" + locale + ".properties");
			property.load(inputStreamReader);
		} catch (Exception exception) {
			logger.error("Error while loading the property file :"
					+ propertiesFileName + "_" + locale, exception);
		}
		return property;
	}

	/**
	 * returns value for the property file
	 * 
	 * @param propertiesFileName
	 * @param locale
	 *            , key
	 * @return value
	 */
	public String loadFromClassPath(String propertiesFileName, String locale,
			String key) {

		Properties property = null;
		String value = null;

		if (null != propertiesFileName && null != locale) {

			property = new Properties();
			property = loadFromClassPath(propertiesFileName, locale);

		}
		if (null != property && null != key) {

			value = property.getProperty(key);
		}
		return value;
	}

	public Properties loadFromFile(File file) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(file));
		} catch (Exception e) {
			logger.error("Could not load the file :" + file.getName());
		}
		return prop;
	}

	private void init() throws UAQException {
		confProperties = new Properties();
		if (System.getProperty("CONFHOME") == null) {
			loadAllFromClassPath();
		} else {
			loadAllFromConfHome();
		}
	}

	private void loadAllFromConfHome() throws UAQException {
		String confHome = System.getProperty("CONFHOME");
		if (confHome != null) {
			File file = new File(confHome);
			if (file.exists() && file.isDirectory()) {
				File[] fileList = file.listFiles();
				for (File tmpFile : fileList) {
					confProperties.putAll(loadFromFile(tmpFile));
				}
			}
		}
	}

	private void loadAllFromClassPath() throws UAQException {

		for (int index = 0; index < propertyFileList.length; index++) {
			logger.debug("Loading ..." + propertyFileList[index]);
			confProperties.putAll(loadFromClassPath(propertyFileList[index]));
		}

	}

}
