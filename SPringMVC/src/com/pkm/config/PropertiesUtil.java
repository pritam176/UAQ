package com.pkm.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertiesUtil extends PropertyPlaceholderConfigurer {
	private static Map<String, String> properties = new HashMap<String, String>();

	@Override
	protected void loadProperties(final Properties props) throws IOException {
		super.loadProperties(props);
		for (final Object key : props.keySet()) {
			properties.put((String) key, props.getProperty((String) key));
		}
	}

	/**
	 * Return a property loaded by the place holder.
	 * 
	 * @param name
	 *            the property name.
	 * @return the property value.
	 */
	public static String getProperty(final String name) {
		return properties.get(name);
	}
}