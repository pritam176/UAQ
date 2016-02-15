package com.uaq.flexfliter;

public class FlexConstants {
	
	
	/* For UAT Enviornment*/
	public static final String PUSHNOTIFICATION_ENDPOINT = "http://83.111.136.2/uaqws/pushnotification/sendNotification/";
	
	public static final String TOMCAT_TEMP_PATH = "/U01/tomcat/temp/temp";
	
	
	/* For Dev Enviornment*/
	
	/*public static final String PUSHNOTIFICATION_ENDPOINT = "http://94.57.252.237:8080/uaqws/pushnotification/sendNotification/";
	
	public static final String TOMCAT_TEMP_PATH = "/tomcat/temp/temp"; */

	public static enum ACTION {
		ADD, DELETE, UPDATE
	}

	public static final String NEWS_TYPE = "2";

	public static final String EVENTS_TYPE = "3";

	public static final String FUNERAL_TYPE = "4";

	public static final String FILTER_INPUT_ATTRIBUTE = "Input attribute name";

	public static final String FILTER_OUTPUT_ATTRIBUTE_1 = "Attribute Name for Preview Image";

	public static final String FILTER_OUTPUT_ATTRIBUTE_SIZE_1 = "520x390";

	public static final String FILTER_OUTPUT_ATTRIBUTE_2 = "Attribute Name for Generic Content Image";

	public static final String FILTER_OUTPUT_ATTRIBUTE_SIZE_2 = "400x300";

	public static final String FILTER_OUTPUT_ATTRIBUTE_3 = "Attribute Name for Mobile Image";

	public static final String FILTER_OUTPUT_ATTRIBUTE_SIZE_3 = "320x240";

	public static final String FILTER_OUTPUT_ATTRIBUTE_4 = "Attribute Name for Teaser Image";

	public static final String FILTER_OUTPUT_ATTRIBUTE_SIZE_4 = "160x120";

	public static final String FILTER_OUTPUT_ATTRIBUTE_5 = "Attribute Name for Gallery Teaser Image";

	public static final String FILTER_OUTPUT_ATTRIBUTE_SIZE_5 = "140x105";

	public static final String IMAGE_SPLITTER = "x";

	public static final String DIMENSION = "Dimension";
}
