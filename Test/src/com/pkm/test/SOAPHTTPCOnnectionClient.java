package com.pkm.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SOAPHTTPCOnnectionClient {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		URL oURL = new URL("http://94.57.252.234:8001/soa-infra/services/PWS_Department/UAQ_PWS_WasteContainer/newwastecontainerreqbpel_client_ep?WSDL");
		HttpURLConnection httpConn = (HttpURLConnection) oURL.openConnection();

		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		String responseString = "";
		String outputString = "";

		String xmlInput = getRequest();

		System.out.println("SOAP Request");
		// System.out.println(xmlInput);

		byte[] buffer = new byte[xmlInput.length()];
		buffer = xmlInput.getBytes();
		bout.write(buffer);
		byte[] b = bout.toByteArray();

		String SOAPAction = "http://94.57.252.234:8001/soa-infra/services/PWS_Department/UAQ_PWS_WasteContainer/newwastecontainerreqbpel_client_ep";
		// Set the appropriate HTTP parameters.
		httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));

		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setRequestProperty("SOAPAction", SOAPAction);
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		OutputStream out = httpConn.getOutputStream();
		// Write the content of the request to the outputstream of the HTTP
		// Connection.
		out.write(b);
		out.close();
		// Ready with sending the request.

		System.out.println(httpConn.getResponseCode());
		// System.out.println(httpConn.getErrorStream());
		// Read the response.
		InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
		BufferedReader in = new BufferedReader(isr);

		// Write the SOAP message response to a String.
		while ((responseString = in.readLine()) != null) {
			outputString = outputString + responseString;
		}
		System.out.println(outputString);
		// Parse the String output to a org.w3c.dom.Document and be able to
		// reach every node with the org.w3c.dom API.
		Document document = parseXmlFile(outputString);
		NodeList nodeLst = document.getElementsByTagName("Status_EN");
		String Status_EN = nodeLst.item(0).getTextContent();
		System.out.println("Status_EN: " + Status_EN);

		// Write the SOAP message formatted to the console.
		String formattedSOAPResponse = formatXML(outputString);
		System.out.println(formattedSOAPResponse);

	}

	// format the XML in your String
	public static String formatXML(String unformattedXml) {
		try {
			Document document = parseXmlFile(unformattedXml);
			OutputFormat format = new OutputFormat(document);
			format.setIndenting(true);
			format.setIndent(3);
			format.setOmitXMLDeclaration(true);
			Writer out = new StringWriter();
			XMLSerializer serializer = new XMLSerializer(out, format);
			serializer.serialize(document);
			return out.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static Document parseXmlFile(String in) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(in));
			return db.parse(is);
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String getSequrityHeader() {
		String sequrity_header = " <wsse:Security soapenv:mustUnderstand=\"1\" " + "xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" "
				+ " xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"> \n"
				+ "<wsse:UsernameToken wsu:Id=\"UsernameToken-3B8FAAAB8B87A4DE2714561240207701\"> \n" + "<wsse:Username>uaqdev</wsse:Username> \n"
				+ "     <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">welcome1</wsse:Password> \n"
				+ "     <wsse:Nonce EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">ZEVey6LE8FD/3jncl2+5fA==</wsse:Nonce> \n" +

				"   <wsu:Created>2016-02-22T06:53:40.768Z</wsu:Created> \n" + "   </wsse:UsernameToken> \n" +

				"  </wsse:Security> \n";
		return sequrity_header;

	}

	private static String getRequest() {

		String requestXML = " <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:was=\"http://WasteContainerRequest\">\n" + " <soapenv:Header>\n"

		+ getSequrityHeader() +

		" </soapenv:Header>\n"

		+ " <soapenv:Body>\n" + " <was:Input>\n" + "   <was:UserDetails>\n" + "     <was:Username>pritam176</was:Username>\n" + "     <was:TypeOfUser>1</was:TypeOfUser> \n" +

		"      <was:Accountid>60652</was:Accountid> \n" +

		"<was:MobileNo>0567396576</was:MobileNo>\n" +

		"	<was:EmailID>pkumar@tacme.net</was:EmailID>\n" +

		"    <was:EmiratesId>?11111</was:EmiratesId> \n" +

		"    <was:TradeLienceNo>123</was:TradeLienceNo> \n" +

		"     <was:FamilyBookNo>0</was:FamilyBookNo> \n" + "     <was:nationality>1</was:nationality> \n" +

		"     <was:Emirate>1</was:Emirate>  \n" +

		"     <was:FirstName>pritam</was:FirstName> \n" +

		"    <was:Address1>gndf</was:Address1> \n" + "     <was:POBOX>76967</was:POBOX> \n" +

		"      <was:DOB>2002-05-30T09:00:00</was:DOB> \n" +

		"     <was:applicanttypeid>1</was:applicanttypeid> \n" + "      </was:UserDetails> \n" + "      <was:Serviceid>101</was:Serviceid> \n" + "      <was:Address1>fdh</was:Address1> \n" +

		"       <was:Address2>?</was:Address2> \n" + "      <was:Postbox>4366</was:Postbox> \n" + "        <was:Latitude>43</was:Latitude> \n" + "       <was:Longitude>36</was:Longitude> \n"
				+ "       <was:ServiceType>1</was:ServiceType> \n" +

				"      <was:userComment>dfh</was:userComment> \n" + "      <was:ReplacementReason>32552</was:ReplacementReason> \n" + "      <was:SourceType>1</was:SourceType> \n" +

				"      <was:RequestNo/> \n" + "       <was:SubmittedByUserId>2d333834323538353031323730343533</was:SubmittedByUserId> \n" + "       <was:UserAccountid>60652</was:UserAccountid> \n"
				+ "        <was:Status>1</was:Status> \n" +

				"        <was:ServiceName>New Waste Conatiner</was:ServiceName> \n" +

				"       <was:RequestID>1</was:RequestID> \n" +

				"        <was:WorkflowId>1</was:WorkflowId> \n" + "       <was:WasteContainerReqId>1</was:WasteContainerReqId> \n" + "      <was:LanguageId>1</was:LanguageId> \n" +

				"        <was:takenPictureId>1</was:takenPictureId> \n" +

				"        <was:InspectionReport>1</was:InspectionReport> \n" +

				"       <was:InspSupportiveAttachemntID>1</was:InspSupportiveAttachemntID> \n" +

				"       <was:ReviewerComment>1</was:ReviewerComment> \n" +

				"        <was:ReviewerSupportiveAttachmentid>1</was:ReviewerSupportiveAttachmentid> \n" +

				"        <was:AttachmentList> \n" +

				"          <was:AttachmentRec> \n" + "             <was:Contentid>123</was:Contentid> \n" + "            <was:Filename>abx.text</was:Filename> \n"
				+ "            <was:FileExpiryDate>2002-05-30T09:00:00</was:FileExpiryDate> \n" + "            <was:IsMandatory>1</was:IsMandatory> \n"
				+ "            <was:Url>www.google.com</was:Url> \n" + "           <was:FileType>5</was:FileType> \n" + "        </was:AttachmentRec> \n" + "     </was:AttachmentList> \n" +

				"     <was:Inspectorusername>sg</was:Inspectorusername> \n" +

				"      <was:InspSupervisorComment>sfv</was:InspSupervisorComment> \n" +

				"    <was:operatorcomment>sf</was:operatorcomment> \n" +

				"    <was:markasdelivered>1</was:markasdelivered> \n" +

				"     <was:SubmittedDate>2002-05-30T09:00:00</was:SubmittedDate> \n" +

				"     <was:receiverIdentitydocId>1</was:receiverIdentitydocId> \n" + "  </was:Input> \n" +

				" </soapenv:Body>\n" + " </soapenv:Envelope>";
		return requestXML;

	}

}
