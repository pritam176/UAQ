package com.uaq.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.uaq.common.ApplicationConstants;

public final class DynamicSoapClient {

	private String username = ApplicationConstants.WS_USERNAME;
	private String password = ApplicationConstants.WS_PASSWORD;

	public static class Param {
		String name;
		String value;
		String namespace;
		List<Param> values;
		boolean hasPrefix=true;

		public Param(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public Param(String name, String namespace, List<Param> values) {
			this.name = name;
			this.namespace = namespace;
			this.values = values;
		}
		public Param(String name, String namespace, List<Param> values, boolean hasPrefix) {
			this.name = name;
			this.namespace = namespace;
			this.values = values;
			this.hasPrefix = hasPrefix;
		}

		public boolean isHasPrefix() {
			return hasPrefix;
		}

		public void setHasPrefix(boolean hasPrefix) {
			this.hasPrefix = hasPrefix;
		}

		public String getName() {
			return name;
		}

		public String getValue() {
			return value;
		}

		public String getNamespace() {
			return namespace;
		}

		public List<Param> getValues() {
			return values;
		}
	}

	public SOAPBody consume(String wsdlUrl, String operationName, List<Param> params, boolean applySecurityHeaders, boolean hasResponse) throws Exception {
		String targetNameSpace = null;
		String serviceNameString = null;
		String portNameString = null;
		String endPointUrl = null;
		String wsdlString = getText(wsdlUrl);
		Document wsdl = convertToXML(wsdlString);

		NamedNodeMap targetNameSpaceAttrs = wsdl.getChildNodes().item(0).getAttributes();
		for (int i = 0; i < targetNameSpaceAttrs.getLength(); i++) {
			if (targetNameSpaceAttrs.item(i).getNodeName().equals("targetNamespace")) {
				targetNameSpace = targetNameSpaceAttrs.item(i).getNodeValue();
				break;
			}
		}

		Node serviceNameNode = null;
		NodeList wsdlChildren = wsdl.getChildNodes().item(0).getChildNodes();
		for (int i = 0; i < wsdlChildren.getLength(); i++) {
			serviceNameNode = wsdlChildren.item(i);
			if (serviceNameNode.getNodeName().equals("wsdl:service")) {
				break;
			}
		}
		NamedNodeMap serviceNameAttrs = serviceNameNode.getAttributes();
		for (int i = 0; i < serviceNameAttrs.getLength(); i++) {
			if (serviceNameAttrs.item(i).getNodeName().equals("name")) {
				serviceNameString = serviceNameAttrs.item(i).getNodeValue();
				if (serviceNameString.contains("."))
					serviceNameString = serviceNameString.substring(0, serviceNameString.indexOf("."));
				break;
			}
		}

		Node portNameNode = getChildNode(serviceNameNode);
		NamedNodeMap portNameAttrs = portNameNode.getAttributes();
		for (int i = 0; i < portNameAttrs.getLength(); i++) {
			if (portNameAttrs.item(i).getNodeName().equals("name")) {
				portNameString = portNameAttrs.item(i).getNodeValue();
				break;
			}
		}

		Node endPointNode = getChildNode(portNameNode);
		NamedNodeMap endPointAttrs = endPointNode.getAttributes();
		for (int i = 0; i < endPointAttrs.getLength(); i++) {
			if (endPointAttrs.item(i).getNodeName().equals("location")) {
				endPointUrl = endPointAttrs.item(i).getNodeValue();
				break;
			}
		}

		// QName serviceName = new QName(targetNameSpace, serviceNameString);
		QName serviceName = new QName(targetNameSpace, operationName);
		QName portName = new QName(targetNameSpace, portNameString);

		/** Create a service and add at least one port to it. **/
		Service soapService = Service.create(serviceName);
		soapService.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endPointUrl);

		/** Create a Dispatch instance from a service. **/
		Dispatch<SOAPMessage> dispatch = soapService.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);
		BindingProvider bp = (BindingProvider) dispatch;
		bp.getRequestContext().put("jaxws.response.throwExceptionIfSOAPFault", Boolean.FALSE);

		MimeHeaders headers = new MimeHeaders();
		headers.addHeader("Content-Type", "text/xml; charset=UTF-8");

		/** Create SOAPMessage request. **/
		// compose a request message
		MessageFactory messageFactory = MessageFactory.newInstance();
		String msgContent = hashMap2Xml(operationName, targetNameSpace, params);
		String soapMessage = constructSoapMessage(msgContent);
		SOAPMessage message = messageFactory.createMessage(headers, new ByteArrayInputStream(soapMessage.trim().getBytes("UTF8")));

		if (applySecurityHeaders) {
			SOAPHeader header = message.getSOAPHeader();
			SOAPElement security = header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
			SOAPElement usernameToken = security.addChildElement("UsernameToken", "wsse");
			usernameToken.addAttribute(new QName("xmlns:wsu"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
			SOAPElement usernameEle = usernameToken.addChildElement("Username", "wsse");
			usernameEle.addTextNode(username);
			SOAPElement passwordEle = usernameToken.addChildElement("Password", "wsse");
			passwordEle.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
			passwordEle.addTextNode(password);
			message.saveChanges();
		}

		//printSOAPMessage(message);

		/** Invoke the service endpoint. **/
		if (hasResponse) {
			SOAPMessage response = dispatch.invoke(message);
			printSOAPMessage(response);
			return response.getSOAPBody();
		} else {
			dispatch.invokeOneWay(message);
			return null;
		}
	}

	private Document convertToXML(String documentAsText) throws Exception {
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(documentAsText));
		Document doc = db.parse(is);
		return doc;
	}

	private static String getText(String url) throws Exception {
		URL website = new URL(url);
		URLConnection connection = website.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder response = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);
		in.close();
		return response.toString();
	}

	private Node getChildNode(Node serviceNameNode) {
		Node child = null;
		NodeList children = serviceNameNode.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			child = children.item(i);
			if (child.getNodeType() != Node.TEXT_NODE)
				break;
		}
		return child;
	}

	private String constructSoapMessage(String payload) {
		String soapMessage = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
				+ "  <soap:Header/>\n"
				+ "  <soap:Body>\n" + payload + "\n" + "  </soap:Body>\n"
				+ "</soap:Envelope>";
		return soapMessage;
	}

	private String hashMap2Xml(String serviceName, String targetNameSpace, List<Param> params) throws Exception {
		String xmlString = "<ns1:" + serviceName + " xmlns:ns1=\"" + targetNameSpace + "\">\n";
		xmlString = hashMap2XmlNode(params, xmlString, "", 2);
		xmlString += "</ns1:" + serviceName + ">";
		return xmlString.toString();
	}
	
	private String hashMap2XmlNode(List<Param> params, String xmlString, String nsAlias, int nsCount) {
		for (Param param : params) {
			if (param.getNamespace() != null) {
				nsAlias = "ns" + nsCount;
				if(param.isHasPrefix())
					xmlString += "\n\t<" + nsAlias + ":" + param.getName() + " xmlns:" + nsAlias + "=\"" + param.getNamespace() + "\">";
				else
					xmlString += "\n\t<" + param.getName() + " xmlns:" + nsAlias + "=\"" + param.getNamespace() + "\">";
				nsCount++;
			} else {
				xmlString += "\t<" + nsAlias + ":" + param.getName() + ">";
			}
			if (param.getValues() != null)
				xmlString = hashMap2XmlNode(param.getValues(), xmlString, nsAlias, nsCount);
			else
				xmlString += param.getValue() == null ? "" : param.getValue();
			if(param.isHasPrefix())
				xmlString += "</" + nsAlias + ":" + param.getName() + ">\n";
			else
				xmlString += "</" + param.getName() + ">\n";
		}
		return xmlString;
	}

	private void printSOAPMessage(SOAPMessage soapResponse) throws Exception {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		System.out.print("\nSOAP Message = ");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		StreamResult result = new StreamResult(System.out);
		transformer.transform(sourceContent, result);
		System.out.println();
	}
}
