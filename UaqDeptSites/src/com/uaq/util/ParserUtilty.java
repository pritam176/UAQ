package com.uaq.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * @author nsharma
 * 
 */
public class ParserUtilty {

	private static Document xmlDocument;

	private static XPath xPath;

	/**
	 * @param xmlFileName
	 */
	public static void initializeXML(File xmlTobeparsed) {

		try {
			xmlDocument = null;
			xPath = null;
			FileInputStream file = new FileInputStream(xmlTobeparsed);
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			xmlDocument = builder.parse(file);
			xPath = XPathFactory.newInstance().newXPath();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

	}

	public static String getValueOfNode(String attributeName, String attributeType) {
		String attributeValue = null;
		try {

			String expression = "/document/asset/attribute[@name='" + attributeName + "']/" + attributeType + "/@value";
			attributeValue = xPath.compile(expression).evaluate(xmlDocument);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return attributeValue;

	}

	public static String getAssetId() {
		String assetId = null;
		try {

			String expression = "/document/asset/@id";
			assetId = xPath.compile(expression).evaluate(xmlDocument);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return assetId;

	}

	public static String getImage(String attributeName) {
		String attributeValue = null;
		try {

			String expression = "/document/asset/attribute[@name='" + attributeName + "']/file";
			attributeValue = xPath.compile(expression).evaluate(xmlDocument);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return attributeValue;

	}

	public static String getNameofNode(String attributeName, String attributeType) {
		String attributeValue = null;
		try {

			String expression = "/document/asset/attribute[@name='" + attributeName + "']/" + attributeType + "/@name";
			attributeValue = xPath.compile(expression).evaluate(xmlDocument);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return attributeValue;

	}

	public static String getXPathValue(String expression) {
		String attributeValue = null;
		try {
			attributeValue = xPath.compile(expression).evaluate(xmlDocument);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return attributeValue;

	}

	public static Node getXPathNode(String expression) {
		Node node = null;
		try {
			node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return node;

	}

	public static Node getXPathNode(String expression, XPath xPath, Document xmlDocument) {
		Node node = null;
		try {
			node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return node;

	}

}
