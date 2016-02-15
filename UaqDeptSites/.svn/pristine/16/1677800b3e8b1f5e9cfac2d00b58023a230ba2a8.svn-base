package com.uaq.service;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.soap.SOAPBody;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uaq.db.si.model.common.AccountDetailsViewSDO;
import uaq.db.si.model.common.UserDetailsViewSDO;

import com.uaq.vo.SendBackInfo;
import com.uaq.vo.SendBackInfo.ServiceAttachment;
import com.uaq.common.PropertiesUtil;

import com.uaq.util.DynamicSoapClient;
import com.uaq.util.DynamicSoapClient.Param;

public class WebServiceInvoker {

	// private static final String wsdlUrl =
	// "http://soavm2:8001/soa-infra/services/default/CardDatabaseQueriesProject/CardDatabaseQueriesProcess.service?WSDL";
	private static final String wsdlUrl ;// "http://soavm2:8001/soa-infra/services/default/CardDatabaseQueriesProject!2.0*soa_1eee0485-1641-4981-b8a0-1fb256866518/CardDatabaseQueriesProcess.service?WSDL";
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	static{
		wsdlUrl =PropertiesUtil.getProperty("SOA_URL_GETCARD_DETAIL");
	}
	

	private  static Logger logger = Logger.getLogger(WebServiceInvoker.class);
	
	public static SendBackInfo getSendBackInfo(String requestId) throws Exception {
		String operationName = "getSentBackInfo";
		SendBackInfo sendBackInfo = new SendBackInfo();
		List<Param> params = new ArrayList<Param>();
		params.add(new Param("requestId", requestId));
		boolean hasResponse = true;
		logger.debug("WSDLURL | "+wsdlUrl);
		SOAPBody result = new DynamicSoapClient().consume(wsdlUrl, operationName, params, true, hasResponse);
		// Get the Reviewer response
		Node reviewResponseNode = result.getElementsByTagName("GetReviewerResponseServiceOutput").item(0);
		ServiceAttachment reviewAttachment = new ServiceAttachment();
		NodeList reviewAttachmentChildNodes = reviewResponseNode.getChildNodes();
		for (int j = 0; j < reviewAttachmentChildNodes.getLength(); j++) {
			Node serviceAttachmentChildNode = reviewAttachmentChildNodes.item(j);
			if (serviceAttachmentChildNode.getNodeName().equals("USER_COMMENTS"))
				sendBackInfo.setReviewComment(serviceAttachmentChildNode.getTextContent());
			else if (serviceAttachmentChildNode.getNodeType() == Node.ELEMENT_NODE)
				reviewAttachment.setField(serviceAttachmentChildNode.getNodeName(), serviceAttachmentChildNode.getTextContent());
		}
		sendBackInfo.setReviewAttachment(reviewAttachment);
		// Get the Reviewer response
		Map<String, ServiceAttachment> latestApplicantAttachments = new HashMap<String, ServiceAttachment>();
		NodeList latestApplicantAttachmentNodes = result.getElementsByTagName("GetLatestApplicantAttachmentsOutput");
		for (int i = 0; i < latestApplicantAttachmentNodes.getLength(); i++) {
			Node latestApplicantAttachmentNode = latestApplicantAttachmentNodes.item(i);
			ServiceAttachment serviceAttachment = new ServiceAttachment();
			NodeList serviceAttachmentChildNodes = latestApplicantAttachmentNode.getChildNodes();
			for (int j = 0; j < serviceAttachmentChildNodes.getLength(); j++) {
				Node serviceAttachmentChildNode = serviceAttachmentChildNodes.item(j);
				if (serviceAttachmentChildNode.getNodeType() == Node.ELEMENT_NODE)
					serviceAttachment.setField(serviceAttachmentChildNode.getNodeName(), serviceAttachmentChildNode.getTextContent());
			}
			latestApplicantAttachments.put(serviceAttachment.getDocId(), serviceAttachment);
		}
		sendBackInfo.setLatestApplicantAttachment(latestApplicantAttachments);
		return sendBackInfo;
	}

	public static RequestIdData saveCardRequest(Map<String, Object> inputParams) throws Exception {
		String operationName = "insertLPProCardRequest";
		List<Param> detailsParams = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);
		logger.debug("WSDLURL | "+wsdlUrl);
		String requestId = insertApplicantData(inputParams, wsdlUrl, loginInfo);

		List<Param> detailData = getLpProCardRequestData(inputParams, accountDetails, loginInfo, requestId);
		List<Param> detailParams = new ArrayList<Param>();
		detailParams.add(new Param("LpProCardReqDetails", null, detailData));
		detailsParams.add(new Param("LpProCardReqDetailsCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertOrUpdateLPCardRequest", detailParams));

		boolean hasResponse = true;
		logger.debug("WSDLURL | "+wsdlUrl);
		SOAPBody result = new DynamicSoapClient().consume(wsdlUrl, operationName, detailsParams, true, hasResponse);
		String requestNumber = result.getElementsByTagName("requestNumber").item(0).getTextContent();
		return new RequestIdData(requestId, requestNumber);
	}

	public static void updateCardRequest(Map<String, Object> inputParams) throws Exception {
		String operationName = "updateCardRequestDetails";
		List<Param> detailsParams = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);

		List<Param> detailData = getLpProCardRequestData(inputParams, accountDetails, loginInfo, inputParams.get("requestId").toString());
		List<Param> detailParams = new ArrayList<Param>();
		detailParams.add(new Param("LpProCardReqDetails", null, detailData));
		detailsParams.add(new Param("LpProCardReqDetailsCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertOrUpdateLPCardRequest", detailParams));

		boolean hasResponse = false;
		logger.debug("WSDLURL | "+wsdlUrl);
		new DynamicSoapClient().consume(wsdlUrl, operationName, detailsParams, true, hasResponse);
	}

	private static List<Param> getLpProCardRequestData(Map<String, Object> inputParams, AccountDetailsViewSDO accountDetails, UserDetailsViewSDO loginInfo, String requestId) {
		Date proEmiIdExpDate = null;
		if (accountDetails.getEidaExpiryDate().getValue() != null) {
			proEmiIdExpDate = accountDetails.getEidaExpiryDate().getValue().toGregorianCalendar().getTime();
		}
		List<Param> detailData = new ArrayList<Param>();
		detailData.add(new Param("proCardReqId", "1"));
		detailData.add(new Param("reqId", requestId));
		detailData.add(new Param("servId", inputParams.get("serviceId").toString()));
		detailData.add(new Param("accountId", loginInfo.getAccountId()));
		detailData.add(new Param("proName", inputParams.get("nameOfPro").toString()));
		detailData.add(new Param("proIdNum", inputParams.get("proIdNo").toString()));
		detailData.add(new Param("proIdExpDate", inputParams.get("proIdExpiryDate").toString()));
		detailData.add(new Param("proEmiratesId", accountDetails.getEmiratesId().getValue()));
		detailData.add(new Param("proEmiIdExpDate", proEmiIdExpDate != null ? dateFormatter.format(proEmiIdExpDate) : null));
		detailData.add(new Param("proNationality", inputParams.get("proNationality").toString()));
		detailData.add(new Param("userName", loginInfo.getUserName()));
		return detailData;
	}

	public static void sendSmsAndEMail(Map<String, Object> inputParams) throws Exception {
		String operationName = "sendNotifications";
		List<Param> params = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		params.add(new Param("applicantEmailAddress", accountDetails.getEmailAddress()));
		params.add(new Param("applicantMobileNumber", accountDetails.getMobileNo()));
		params.add(new Param("requestNumber", inputParams.get("requestNumber").toString()));
		params.add(new Param("username", accountDetails.getUserDetailsView().get(0).getLoginusername().getValue()));
		params.add(new Param("requestId", inputParams.get("requestId").toString()));
		boolean hasResponse = false;
		logger.debug("WSDLURL | "+wsdlUrl);
		new DynamicSoapClient().consume(wsdlUrl, operationName, params, true, hasResponse);
	}

	public static void uploadAttachmentToDatabase(Map<String, String> attachmentInputParams) throws Exception {
		String operationName = "attachmentStart";
		List<Param> attachmentsParams = new ArrayList<Param>();
		List<Param> attachmentData = new ArrayList<Param>();
		attachmentData.add(new Param("attachmentId", "1"));
		attachmentData.add(new Param("requestId", attachmentInputParams.get("requestId")));
		attachmentData.add(new Param("serviceId", attachmentInputParams.get("serviceId")));
		attachmentData.add(new Param("attDocId", attachmentInputParams.get("attDocId")));
		attachmentData.add(new Param("fileName", attachmentInputParams.get("fileName")));
		attachmentData.add(new Param("viewurl", attachmentInputParams.get("viewurl")));
		attachmentData.add(new Param("ucmDid", attachmentInputParams.get("ucmDid")));
		List<Param> attachmentParams = new ArrayList<Param>();
		attachmentParams.add(new Param("ServiceAttachments", null, attachmentData));
		attachmentsParams.add(new Param("ServiceAttachmentsCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertAttachementService", attachmentParams));
		boolean hasResponse = false;
		logger.debug("WSDLURL | "+wsdlUrl);
		new DynamicSoapClient().consume(wsdlUrl, operationName, attachmentsParams, true, hasResponse);
	}

	public static void issueNewCardProcess(Map<String, Object> inputParams) throws Exception {
		String wsdlUrl = PropertiesUtil.getProperty("SOA_URL_ISSUE_PROCARD");
		logger.debug("WSDLURL | "+wsdlUrl);
		issueServiceProcess(wsdlUrl, inputParams, null);
	}

	public static void renewCardProcess(Map<String, Object> inputParams) throws Exception {
		String wsdlUrl = PropertiesUtil.getProperty("SOA_URL_ISSUE_NEW_PROCARD");
		logger.debug("WSDLURL | "+wsdlUrl);
		issueServiceProcess(wsdlUrl, inputParams, null);
	}

	public static void issueLandAndPropertyValuationProcess(Map<String, Object> inputParams) throws Exception {
		String wsdlUrl = PropertiesUtil.getProperty("SOA_URL_LAND&PROPERTY");//"http://soavm2:8001/soa-infra/services/LP/LandAndPropertyValuationProject/LandAndPropertyValuationProcess.service?WSDL";
		logger.debug("WSDLURL | "+wsdlUrl);
		String applicantType = inputParams.get("applicantType").toString();
		List<Param> params = new ArrayList<Param>();
		params.add(new Param("landType", inputParams.get("landCategory").toString()));
		params.add(new Param("applicantType", applicantType.equals("1") ? "Owner" : "Hier"));
		issueServiceProcess(wsdlUrl, inputParams, params);
	}
	
	public static void issueLandAndPropertyValuationProcessServiceFee(Map<String, Object> inputParams) throws Exception {
		String wsdlUrl = PropertiesUtil.getProperty("SOA_URL_LAND&PROPERTY");//"http://soavm2:8001/soa-infra/services/LP/LandAndPropertyValuationProject/LandAndPropertyValuationProcess.service?WSDL";
		logger.debug("WSDLURL | "+wsdlUrl);
		
		List<Param> params = new ArrayList<Param>();
		params.add(new Param("requestId", inputParams.get("requestId").toString()));
		String operationName = "serviceFeesMessage";
		
		boolean hasResponse = false;
		logger.debug("WSDLURL | "+wsdlUrl);
		new DynamicSoapClient().consume(wsdlUrl, operationName, params, true, hasResponse);
	}
	public static void issueNewProCardServiceFee(Map<String, Object> inputParams) throws Exception {
		String wsdlUrl = PropertiesUtil.getProperty("SOA_URL_ISSUE_PROCARD");
		logger.debug("WSDLURL | "+wsdlUrl);
		
		List<Param> params = new ArrayList<Param>();
		params.add(new Param("requestId", inputParams.get("requestId").toString()));
		String operationName = "serviceFeesMessage";
		
		boolean hasResponse = false;
		logger.debug("WSDLURL | "+wsdlUrl);
		new DynamicSoapClient().consume(wsdlUrl, operationName, params, true, hasResponse);
	}
	public static void renewProCardServiceFee(Map<String, Object> inputParams) throws Exception {
		String wsdlUrl = PropertiesUtil.getProperty("SOA_URL_ISSUE_NEW_PROCARD");
		logger.debug("WSDLURL | "+wsdlUrl);
		
		List<Param> params = new ArrayList<Param>();
		params.add(new Param("requestId", inputParams.get("requestId").toString()));
		String operationName = "serviceFeesMessage";
		
		boolean hasResponse = false;
		logger.debug("WSDLURL | "+wsdlUrl);
		new DynamicSoapClient().consume(wsdlUrl, operationName, params, true, hasResponse);
	}

	public static void issueServiceProcess(String wsdlUrl, Map<String, Object> inputParams, List<Param> additionalParams) throws Exception {
		String operationName = "mobileWebService";
		List<Param> params = new ArrayList<Param>();

		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);

		params.add(new Param("creator", loginInfo.getLoginusername().getValue().toString()));
		params.add(new Param("requestId", inputParams.get("requestId").toString()));
		params.add(new Param("requestNumber", inputParams.get("requestNumber").toString()));
		if (additionalParams != null)
			params.addAll(additionalParams);
		boolean hasResponse = false;
		logger.debug("WSDLURL | "+wsdlUrl);
		new DynamicSoapClient().consume(wsdlUrl, operationName, params, true, hasResponse);
	}

//	public static void proceedWithServiceAfterPayment(Map<String, String> inputParams) throws Exception {
//		String requestUrl = PropertiesUtil.getProperty("SOA_URL_AFTERPAYMENT");//"http://soavm2:8001/InvokeBPMWsdlApplication-InvokeBPMWsdlProject-context-root/invokebpmservlet?paymentType=service&requestId={0}&serviceId={1}";
//		logger.debug("WSDLURL | "+wsdlUrl);
//		requestUrl = MessageFormat.format(requestUrl, inputParams.get("requestId"), inputParams.get("serviceId"));
//		URL obj = new URL(requestUrl);
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//		int responseCode = con.getResponseCode();
//		System.out.println("\nSending 'GET' request to URL : " + requestUrl);
//		System.out.println("Response Code : " + responseCode);
//
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
//		in.close();
//		// print result
//		System.out.println(response.toString());
//	}

	public static RequestIdData saveValuationRequest(Map<String, Object> inputParams) throws Exception {
		String operationName = "insertLPValuationRequest";
		List<Param> detailsParams = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);

		String requestId = insertApplicantData(inputParams, wsdlUrl, loginInfo);

		List<Param> detailData = getLPValuationRequestData(inputParams, loginInfo, requestId);
		List<Param> detailParams = new ArrayList<Param>();
		detailParams.add(new Param("LpValuation", null, detailData));
		detailsParams.add(new Param("LpValuationCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertOrUpdateLPValuationRequest", detailParams));

		boolean hasResponse = true;
		SOAPBody result = new DynamicSoapClient().consume(wsdlUrl, operationName, detailsParams, true, hasResponse);
		String requestNumber = result.getElementsByTagName("requestNumber").item(0).getTextContent();
		return new RequestIdData(requestId, requestNumber);
	}

	public static void updateValuationRequest(Map<String, Object> inputParams) throws Exception {
		String operationName = "updateValuationDetails";
		List<Param> detailsParams = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);

		List<Param> detailData = getLPValuationRequestData(inputParams, loginInfo, inputParams.get("requestId").toString());
		List<Param> detailParams = new ArrayList<Param>();
		detailParams.add(new Param("LpValuation", null, detailData));
		detailsParams.add(new Param("LpValuationCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertOrUpdateLPValuationRequest", detailParams));

		boolean hasResponse = false;
		new DynamicSoapClient().consume(wsdlUrl, operationName, detailsParams, true, hasResponse);
	}

	private static List<Param> getLPValuationRequestData(Map<String, Object> inputParams, UserDetailsViewSDO loginInfo, String requestId) {
		String landCategory, block, plotNumber;
		if (inputParams.get("applicantPosition") != null && inputParams.get("applicantPosition").toString() != null && inputParams.get("applicantPosition").toString().equals("1")) {
			landCategory = inputParams.get("ownerLandCategory").toString();
		} else {
			landCategory = inputParams.get("hierLandCategory").toString();
		}
		if (inputParams.get("landLocation") != null && inputParams.get("landLocation").toString() != null && inputParams.get("landLocation").toString().equals("1")) {
			block = inputParams.get("areaBlock").toString();
			plotNumber = inputParams.get("areaPlotNumber").toString();
		} else {
			block = inputParams.get("sectorBlock").toString();
			plotNumber = inputParams.get("sectorPlotNumber").toString();
		}
		List<Param> detailData = new ArrayList<Param>();
		detailData.add(new Param("valuationReqId", "1"));
		detailData.add(new Param("reqId", requestId));
		detailData.add(new Param("accountId", loginInfo.getAccountId()));
		detailData.add(new Param("serviceId", inputParams.get("serviceId").toString()));
		detailData.add(new Param("landStatusId", inputParams.get("landStatus").toString()));
		detailData.add(new Param("landTypeId", inputParams.get("landType").toString()));
		detailData.add(new Param("siteplanId", inputParams.get("sitePlanNo").toString()));
		detailData.add(new Param("sector", inputParams.get("sector").toString()));
		detailData.add(new Param("block", block));
		detailData.add(new Param("area", inputParams.get("area").toString()));
		detailData.add(new Param("subSector", inputParams.get("subSector").toString()));
		detailData.add(new Param("subArea", inputParams.get("subArea").toString()));
		detailData.add(new Param("ownerId", inputParams.get("ownerIdNumber").toString()));
		detailData.add(new Param("ownerName", inputParams.get("ownerName").toString()));
		detailData.add(new Param("ownerNationality", inputParams.get("ownerNationality").toString()));
		detailData.add(new Param("familyBookId", inputParams.get("familyBookNumber").toString()));
		detailData.add(new Param("createdDate", dateFormatter.format(new Date())));
		detailData.add(new Param("userPositionId", inputParams.get("applicantPosition").toString()));
		detailData.add(new Param("userName", loginInfo.getUserName()));
		detailData.add(new Param("locationType", inputParams.get("landLocation").toString()));
		detailData.add(new Param("plotNumber", plotNumber));
		detailData.add(new Param("landCategory", landCategory));
		return detailData;
	}

	private static String insertApplicantData(Map<String, Object> inputParams, String wsdlUrl, UserDetailsViewSDO loginInfo) throws Exception {
		List<Param> applicantsParams = new ArrayList<Param>();
		List<Param> applicantData = new ArrayList<Param>();
		applicantData.add(new Param("requestId", "1"));
		applicantData.add(new Param("statusId", "1"));
		applicantData.add(new Param("source", "1"));
		applicantData.add(new Param("userName", loginInfo.getUserName()));
		applicantData.add(new Param("serviceId", inputParams.get("serviceId").toString()));
		applicantData.add(new Param("createdDate", dateFormatter.format(new Date())));
		applicantData.add(new Param("modifiedDate", dateFormatter.format(new Date())));
		applicantData.add(new Param("createdby", loginInfo.getUserName()));
		List<Param> applicantParams = new ArrayList<Param>();
		applicantParams.add(new Param("ApplicantRequest", null, applicantData));
		applicantsParams.add(new Param("ApplicantRequestCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertApplicantRequestService", applicantParams));

		boolean hasResponse = true;
		SOAPBody result = new DynamicSoapClient().consume(wsdlUrl, "start", applicantsParams, true, hasResponse);
		String requestId = result.getElementsByTagName("requestId").item(0).getTextContent();
		return requestId;
	}

	static class RequestIdData {

		private String requestId;
		private String requestNumber;

		public RequestIdData(String requestId, String requestNumber) {
			this.requestId = requestId;
			this.requestNumber = requestNumber;
		}

		public String getRequestId() {
			return requestId;
		}

		public String getRequestNumber() {
			return requestNumber;
		}

	}

}
