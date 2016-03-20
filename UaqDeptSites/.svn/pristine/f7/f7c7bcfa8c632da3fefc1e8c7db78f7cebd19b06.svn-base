package com.uaq.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;

import com.uaq.vo.UserDeatilVO;

public class SOAPClient {

	public static SOAPMessage callSOAPService(SOAPMessage request, String url) throws Exception {

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		SOAPHeader headers = request.getSOAPHeader();

		SOAPElement security = headers.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
		SOAPElement usernameToken = security.addChildElement("UsernameToken", "wsse");
		usernameToken.addAttribute(new QName("xmlns:wsu"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
		SOAPElement usernameEle = usernameToken.addChildElement("Username", "wsse");
		usernameEle.addTextNode("uaqdev");
		SOAPElement passwordEle = usernameToken.addChildElement("Password", "wsse");
		passwordEle.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
		passwordEle.addTextNode("welcome1");

		request.saveChanges();

		System.out.println("After adding Header");
		printSOAPMessage(request);

		SOAPMessage soapResponse = soapConnection.call(request, url);

		return soapResponse;

	}

	public static SOAPEnvelope getEnvlope() throws SOAPException {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		SOAPEnvelope envelope = soapPart.getEnvelope();
		return envelope;
	}

	public static void printSOAPMessage(SOAPMessage soapResponse) throws Exception {
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

	public static SOAPElement createElement(SOAPElement parentElemnt, Map<String, String> childEleemt, String nameSpacePrefix) throws SOAPException {
		SOAPElement child = null;
		for (Entry<String, String> entry : childEleemt.entrySet()) {
			child = parentElemnt.addChildElement(entry.getKey(), nameSpacePrefix);
			child.addTextNode(entry.getValue());

		}
		return child;
	}

	public static Map<String, String> parseResponceMessage(SOAPMessage soapResponse) throws Exception, SOAPException {
		printSOAPMessage(soapResponse);
		Map<String, String> responceMap = new HashMap<String, String>();
		SOAPBody soapBody = soapResponse.getSOAPBody();

		java.util.Iterator iterator = soapBody.getChildElements();
		SOAPBodyElement UploadOutput = (SOAPBodyElement) iterator.next();
		Iterator uploadOutputIterator = UploadOutput.getChildElements();
		while (uploadOutputIterator.hasNext()) {
			SOAPBodyElement bodyElement = (SOAPBodyElement) uploadOutputIterator.next();
			responceMap.put(bodyElement.getLocalName(), bodyElement.getValue());
			//if(bodyElement instanceof bodyElement. )
		}
		return responceMap;

	}

}
