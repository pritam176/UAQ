package com.pkm.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
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

public class SOAPCALLAhmedSurrorStyle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String url = "http://94.57.252.234:8001/soa-infra/services/PWS_Department/UAQ_PWS_WasteContainer/newwastecontainerreqbpel_client_ep?WSDL";
		String wsdlString = getText(url);
		Document wsdlDocument = convertToXML(wsdlString);

		// System.out.println(getStringFromDocument(wsdlDocument));

		String targetNameSpace = null;
		String serviceNameString = null;
		String portNameString = null;
		String endPointUrl = null;

		NamedNodeMap targetNameSpaceAttrs = wsdlDocument.getChildNodes().item(0).getAttributes();
		for (int i = 0; i < targetNameSpaceAttrs.getLength(); i++) {
			if (targetNameSpaceAttrs.item(i).getNodeName().equals("targetNamespace")) {
				targetNameSpace = targetNameSpaceAttrs.item(i).getNodeValue();
				break;
			}
		}

		Node serviceNameNode = null;
		NodeList wsdlChildren = wsdlDocument.getChildNodes().item(0).getChildNodes();
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
				System.out.println("serviceNameString==="+serviceNameString);
				if (serviceNameString.contains("."))
					serviceNameString = serviceNameString.substring(0, serviceNameString.indexOf("."));
				break;
			}
		}

		Node portNameNode = getChildNode(serviceNameNode, "wsdl:port");
		NamedNodeMap portNameAttrs = portNameNode.getAttributes();
		for (int i = 0; i < portNameAttrs.getLength(); i++) {
			if (portNameAttrs.item(i).getNodeName().equals("name")) {
				portNameString = portNameAttrs.item(i).getNodeValue();
				break;
			}
		}

		Node endPointNode = getChildNode(portNameNode, "soap:address");
		NamedNodeMap endPointAttrs = endPointNode.getAttributes();
		for (int i = 0; i < endPointAttrs.getLength(); i++) {
			if (endPointAttrs.item(i).getNodeName().equals("location")) {
				endPointUrl = endPointAttrs.item(i).getNodeValue();
				break;
			}
		}
		
		QName serviceName = new QName(targetNameSpace, "process");
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
		
		List<Param> detailsParams = new ArrayList<Param>();
		
		List<Param> applicantData = new ArrayList<Param>();
		applicantData.add(new Param("requestId", "1"));
		applicantData.add(new Param("requestNo", null));
		applicantData.add(new Param("statusId", "1"));
		applicantData.add(new Param("source", "1"));
		applicantData.add(new Param("userName", "38313438303431373031333032363639"));
		applicantData.add(new Param("serviceId", "101"));
		applicantData.add(new Param("createdDate", dateFormatter.format(new Date())));
		applicantData.add(new Param("modifiedDate", dateFormatter.format(new Date())));
		applicantData.add(new Param("createdby", "pritam176"));
		
		
		String msgContent = hashMap2Xml("process", targetNameSpace, applicantData);
		System.out.println("targetNameSpace |"+msgContent);
		String soapMessage = constructSoapMessage(msgContent);
		SOAPMessage message = messageFactory.createMessage(headers, new ByteArrayInputStream(soapMessage.trim().getBytes("UTF8")));

		printSOAPMessage(message);
		
		
		System.out.println("targetNameSpace |"+targetNameSpace);
		System.out.println("serviceNameString | "+serviceNameString);
		System.out.println("portNameString | "+portNameString);
		System.out.println("endPointUrl | "+endPointUrl);

		


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

	private static Document convertToXML(String documentAsText) throws Exception {
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(documentAsText));
		Document doc = db.parse(is);
		return doc;
	}

	public static String getStringFromDocument(Document doc) {
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private static Node getChildNode(Node serviceNameNode, String nodeString) {
		Node child = null;
		NodeList children = serviceNameNode.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			child = children.item(i);

			/*if (child.getNodeType() != Node.TEXT_NODE) {
				System.out.println(child.getNodeName());
				break;
			}*/
			 if (child.getNodeName().equals(nodeString))
				 break; 
		}
	return child;
	}
	private static String constructSoapMessage(String payload) {
		String soapMessage = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
				+ "  <soap:Header/>\n"
				+ "  <soap:Body>\n" + payload + "\n" + "  </soap:Body>\n"
				+ "</soap:Envelope>";
		return soapMessage;
	}
	private static String hashMap2Xml(String serviceName, String targetNameSpace, List<Param> params) throws Exception {
		String xmlString = "<ns1:" + serviceName + " xmlns:ns1=\"" + targetNameSpace + "\">\n";
		xmlString = hashMap2XmlNode(params, xmlString, "", 2);
		xmlString += "</ns1:" + serviceName + ">";
		return xmlString.toString();
	}
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
	private static String hashMap2XmlNode(List<Param> params, String xmlString, String nsAlias, int nsCount) {
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
	
	private static void printSOAPMessage(SOAPMessage soapResponse) throws Exception {
		
		
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		System.out.print("\nSOAP Message = ");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		StreamResult result = new StreamResult(System.out);
		transformer.transform(sourceContent, result);
		System.out.println("end");
	}

}
