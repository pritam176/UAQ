package com.pkm.test;

import java.text.SimpleDateFormat;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

public class SOAPSAAJClient {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();

		String url = "http://94.57.252.234:8001/soa-infra/services/PWS_Department/UAQ_PWS_WasteContainer/newwastecontainerreqbpel_client_ep?WSDL";
		SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(),url);
		 
		 System.out.print("Response SOAP Message:");
		 soapResponse.writeTo(System.out);
		 
		 printSOAPMessage(soapResponse);
		 
		 soapConnection.close();
		 

	}

	private static SOAPMessage createSOAPRequest() throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();

		String serverURI = "http://WasteContainerRequest";

		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("was", serverURI);

		/*
		 * Constructed SOAP Request Message: <SOAP-ENV:Envelope
		 * xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
		 * xmlns:example="http://ws.cdyne.com/"> <SOAP-ENV:Header/>
		 * <SOAP-ENV:Body> <example:VerifyEmail>
		 * <example:email>mutantninja@gmail.com</example:email>
		 * <example:LicenseKey>123</example:LicenseKey> </example:VerifyEmail>
		 * </SOAP-ENV:Body> </SOAP-ENV:Envelope>
		 */

		// SOAP Body
		SOAPBody soapBody = envelope.getBody();
		
		SOAPElement Input = soapBody.addChildElement("Input", "was");
		SOAPElement UserDetails = Input.addChildElement("UserDetails", "was");
		
		
		SOAPElement Username = UserDetails.addChildElement("Username", "was");
		Username.addTextNode("pritam176");
		SOAPElement TypeOfUser = UserDetails.addChildElement("TypeOfUser", "was");
		TypeOfUser.addTextNode("1");
		
		SOAPElement Accountid = UserDetails.addChildElement("Accountid", "was");
		Accountid.addTextNode("60652");
		
		SOAPElement MobileNo = UserDetails.addChildElement("MobileNo", "was");
		MobileNo.addTextNode("0567396576");
		
		SOAPElement EmailID = UserDetails.addChildElement("EmailID", "was");
		EmailID.addTextNode("pkumar@tacme.net");
		
		SOAPElement DOB = UserDetails.addChildElement("DOB", "was");
		DOB.addTextNode("2002-05-30T09:00:00");
		
		SOAPElement nationality = UserDetails.addChildElement("nationality", "was");
		nationality.addTextNode("1");
		
		SOAPElement Emirate = UserDetails.addChildElement("Emirate", "was");
		Emirate.addTextNode("1");
		
		SOAPElement Serviceid = Input.addChildElement("Serviceid", "was");
		Serviceid.addTextNode("101");
		
		SOAPElement Address1 = Input.addChildElement("Address1", "was");
		Address1.addTextNode("101");
		
		SOAPElement Latitude = Input.addChildElement("Latitude", "was");
		Latitude.addTextNode("101");
		
		SOAPElement Longitude = Input.addChildElement("Longitude", "was");
		Longitude.addTextNode("101");
		
		SOAPElement ServiceType = Input.addChildElement("ServiceType", "was");
		ServiceType.addTextNode("1");
		
		SOAPElement userComment = Input.addChildElement("userComment", "was");
		userComment.addTextNode("101");
		
		SOAPElement ReplacementReason = Input.addChildElement("ReplacementReason", "was");
		ReplacementReason.addTextNode("101");
		
		SOAPElement SourceType = Input.addChildElement("SourceType", "was");
		SourceType.addTextNode("1");
		
		SOAPElement SubmittedByUserId = Input.addChildElement("SubmittedByUserId", "was");
		SubmittedByUserId.addTextNode("2d333834323538353031323730343533");
		
		SOAPElement UserAccountid = Input.addChildElement("UserAccountid", "was");
		UserAccountid.addTextNode("60652");
		
		SOAPElement Status = Input.addChildElement("Status", "was");
		Status.addTextNode("1");
		
		SOAPElement ServiceName = Input.addChildElement("ServiceName", "was");
		ServiceName.addTextNode("New Waste Conatiner");
		
		SOAPElement LanguageId = Input.addChildElement("LanguageId", "was");
		LanguageId.addTextNode("1");
		
		/*SOAPElement Status = Input.addChildElement("Status", "was");
		Status.addTextNode("1");
		
		SOAPElement Status = Input.addChildElement("Status", "was");
		Status.addTextNode("1");*/

		SOAPHeader headers = soapMessage.getSOAPHeader();
		
		
		
		SOAPElement security = headers.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
		SOAPElement usernameToken = security.addChildElement("UsernameToken", "wsse");
		usernameToken.addAttribute(new QName("xmlns:wsu"), "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
		SOAPElement usernameEle = usernameToken.addChildElement("Username", "wsse");
		usernameEle.addTextNode("uaqdev");
		SOAPElement passwordEle = usernameToken.addChildElement("Password", "wsse");
		passwordEle.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
		passwordEle.addTextNode("welcome1");
		

		soapMessage.saveChanges();

		/* Print the request message */
		System.out.print("Request SOAP Message:");
		printSOAPMessage(soapMessage);
		System.out.println();

		return soapMessage;
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
