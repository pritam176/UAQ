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
import uaq.db.si.model.common.GrantLandReqViewSDO;
import uaq.db.si.model.common.LpRealestateOfficeReqViewSDO;
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
	
	private static RequestIdData insertApplicantData(Map<String, Object> inputParams, String wsdlUrl, UserDetailsViewSDO loginInfo) throws Exception {
		List<Param> applicantsParams = new ArrayList<Param>();
		List<Param> applicantData = new ArrayList<Param>();
		applicantData.add(new Param("requestId", "1"));
		applicantData.add(new Param("requestNo", null));
		applicantData.add(new Param("statusId", inputParams.get("statusId")==null?"33":(String)inputParams.get("statusId")));
		applicantData.add(new Param("source", "1"));
		applicantData.add(new Param("userName", loginInfo.getUserName()));
		applicantData.add(new Param("serviceId", inputParams.get("serviceId").toString()));
		applicantData.add(new Param("createdDate", dateFormatter.format(new Date())));
		applicantData.add(new Param("modifiedDate", dateFormatter.format(new Date())));
		applicantData.add(new Param("createdby", loginInfo.getUserName()));
		List<Param> applicantParams = new ArrayList<Param>();
		applicantParams.add(new Param("ApplicantRequest", null, applicantData));
		applicantsParams.add(new Param("serviceDept", inputParams.get("serviceDept").toString()));
		applicantsParams.add(new Param("ApplicantRequestCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertApplicantRequestService", applicantParams));

		boolean hasResponse = true;
		SOAPBody result = new DynamicSoapClient().consume(wsdlUrl, "start", applicantsParams, true, hasResponse);
		String requestId = result.getElementsByTagName("requestId").item(0).getTextContent();
		String requestNumber = result.getElementsByTagName("requestNo").item(0).getTextContent();
		return new RequestIdData(requestId, requestNumber);
	}

	public static SendBackInfo getSendBackInfo(String requestId) throws Exception {
		return getSendBackInfo(requestId, null);
	}

	public static SendBackInfo getSendBackInfo(String requestId, String docTypeId) throws Exception {
		String operationName = "getSentBackInfo";
		SendBackInfo sendBackInfo = new SendBackInfo();
		List<Param> params = new ArrayList<Param>();
		params.add(new Param("requestId", requestId));
		params.add(new Param("docTypeId", docTypeId));
		boolean hasResponse = true;
		SOAPBody result = new DynamicSoapClient().consume(wsdlUrl, operationName, params, true, hasResponse);
		// Get the Reviewer response
		Map<String, ServiceAttachment> reviewAttachments = new HashMap<String, ServiceAttachment>();
		NodeList reviewResponseNodes = result.getElementsByTagName("GetReviewerResponseServiceOutput");
		for (int i = 0; i < reviewResponseNodes.getLength(); i++) {
			Node reviewResponseNode = reviewResponseNodes.item(i);
			NodeList reviewAttachmentChildNodes = reviewResponseNode.getChildNodes();
			ServiceAttachment reviewAttachment = new ServiceAttachment();
			for (int j = 0; j < reviewAttachmentChildNodes.getLength(); j++) {
				Node serviceAttachmentChildNode = reviewAttachmentChildNodes.item(j);
				if (serviceAttachmentChildNode.getNodeName().equals("USER_COMMENTS"))
					sendBackInfo.setReviewComment(serviceAttachmentChildNode.getTextContent());
				else if (serviceAttachmentChildNode.getNodeType() == Node.ELEMENT_NODE)
					reviewAttachment.setField(serviceAttachmentChildNode.getNodeName(), serviceAttachmentChildNode.getTextContent());
			}
			reviewAttachments.put(reviewAttachment.getDocId(), reviewAttachment);
		}
		sendBackInfo.setReviewAttachments(reviewAttachments);
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

	public static void sendSmsAndEMail(Map<String, Object> inputParams) throws Exception {
		String operationName = "sendApplicantNotifications";
		List<Param> params = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		params.add(new Param("applicantEmailAddress", accountDetails.getEmailAddress()));
		params.add(new Param("applicantMobileNumber", accountDetails.getMobileNo()));
		params.add(new Param("requestNumber", inputParams.get("requestNumber").toString()));
		params.add(new Param("username", accountDetails.getUserDetailsView().get(0).getUserName()));
		params.add(new Param("requestId", inputParams.get("requestId").toString()));
		// Asmaa changed according to the new sendNotification
		// (WorkflowHistory to be dynamic)
		params.add(new Param("stepAction", (String) inputParams.get("stepAction")));
		params.add(new Param("stepName", (String) inputParams.get("stepName")));
		params.add(new Param("amount", (String) inputParams.get("amount")));
		params.add(new Param("applicantId", accountDetails.getId()));
		params.add(new Param("applicantName", accountDetails.getFirstName()));
		params.add(new Param("transactionId", (String) inputParams.get("transactionId")));
		params.add(new Param("userLoginName", accountDetails.getUserDetailsView().get(0).getLoginusername().getValue()));
		params.add(new Param("status", (String) inputParams.get("status")));
		
		boolean hasResponse = false;
		new DynamicSoapClient().consume(wsdlUrl, operationName, params, true, hasResponse);
	}

	public static String uploadAttachmentToDatabase(Map<String, String> attachmentInputParams) throws Exception {
		String operationName = "attachmentStart";
		List<Param> attachmentsParams = new ArrayList<Param>();
		List<Param> attachmentData = new ArrayList<Param>();
		attachmentData.add(new Param("attachmentId", "1"));
		attachmentData.add(new Param("requestId", attachmentInputParams.get("requestId")));
		attachmentData.add(new Param("isDeleted", "0"));
		attachmentData.add(new Param("serviceId", attachmentInputParams.get("serviceId")));
		attachmentData.add(new Param("attDocId", attachmentInputParams.get("attDocId")));
		attachmentData.add(new Param("fileName", attachmentInputParams.get("fileName")));
		attachmentData.add(new Param("downloadurl", attachmentInputParams.get("downloadurl")));
		attachmentData.add(new Param("viewurl", attachmentInputParams.get("viewurl")));
		attachmentData.add(new Param("ucmDid", attachmentInputParams.get("ucmDid")));
		List<Param> attachmentParams = new ArrayList<Param>();
		attachmentParams.add(new Param("ServiceAttachments", null, attachmentData));
		attachmentsParams.add(new Param("ServiceAttachmentsCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertAttachementService", attachmentParams));
		boolean hasResponse = true;
		SOAPBody result = new DynamicSoapClient().consume(wsdlUrl, operationName, attachmentsParams, false, hasResponse);
		String attachmentId = result.getElementsByTagName("argument1").item(0).getTextContent();
		return attachmentId;
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
		new DynamicSoapClient().consume(wsdlUrl, operationName, params, true, hasResponse);
	}

	public static void proceedWithLpServiceAfterPayment(String wsdl, String operationName, String requestNoParamName, String paramValue) throws Exception {
		List<Param> params = new ArrayList<Param>();
		params.add(new Param(requestNoParamName, paramValue));
		boolean hasResponse = false;
		new DynamicSoapClient().consume(wsdl, operationName, params, true, hasResponse);
		System.out.println("Process proceeded");
	}

	// ////////////////////PRO CARD PROCESS

	public static RequestIdData saveCardRequest(Map<String, Object> inputParams) throws Exception {
		String operationName = "insertLPProCardRequest";
		List<Param> detailsParams = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);

		// Asmaa changed according to the new insertApplicantData
		RequestIdData requestData = insertApplicantData(inputParams, wsdlUrl, loginInfo);
		String requestId = requestData.getRequestId();

		List<Param> detailData = getLpProCardRequestData(inputParams, accountDetails, loginInfo, requestId);
		List<Param> detailParams = new ArrayList<Param>();
		detailParams.add(new Param("LpProCardReqDetails", null, detailData));
		detailsParams.add(new Param("LpProCardReqDetailsCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertOrUpdateLPCardRequest", detailParams));

		boolean hasResponse = true;
		new DynamicSoapClient().consume(wsdlUrl, operationName, detailsParams, true, hasResponse);
		return requestData;
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

	public static void issueNewCardProcess(Map<String, Object> inputParams) throws Exception {
		String wsdlUrl = PropertiesUtil.getProperty("SOA_URL_ISSUE_PROCARD");
		issueServiceProcess(wsdlUrl, inputParams, null);
	}

	public static void renewCardProcess(Map<String, Object> inputParams) throws Exception {
		String wsdlUrl = PropertiesUtil.getProperty("SOA_URL_ISSUE_NEW_PROCARD");
		issueServiceProcess(wsdlUrl, inputParams, null);
	}

	// ////////////////////LAND AND PROPERTY VALUATION

	public static RequestIdData saveValuationRequest(Map<String, Object> inputParams) throws Exception {
		String operationName = "insertLPValuationRequest";
		List<Param> detailsParams = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);

		// Asmaa changed according to the new insertApplicantData
		RequestIdData requestData = insertApplicantData(inputParams, wsdlUrl, loginInfo);
		String requestId = requestData.getRequestId();

		List<Param> detailData = getLPValuationRequestData(inputParams, loginInfo, requestId);
		List<Param> detailParams = new ArrayList<Param>();
		detailParams.add(new Param("LpValuation", null, detailData));
		detailsParams.add(new Param("LpValuationCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertOrUpdateLPValuationRequest", detailParams));

		boolean hasResponse = true;
		new DynamicSoapClient().consume(wsdlUrl, operationName, detailsParams, true, hasResponse);
		return requestData;
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
			landCategory = inputParams.get("heirLandCategory").toString();
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

	public static void issueLandAndPropertyValuationProcess(Map<String, Object> inputParams) throws Exception {
		String wsdlUrl = PropertiesUtil.getProperty("SOA_URL_LAND&PROPERTY");
		String applicantType = inputParams.get("applicantType").toString();
		List<Param> params = new ArrayList<Param>();
		params.add(new Param("landType", inputParams.get("landCategory").toString()));
		params.add(new Param("applicantType", applicantType.equals("1") ? "Owner" : "Heir"));
		issueServiceProcess(wsdlUrl, inputParams, params);
	}

	// ////////////////////LOST DOCUMENT PROCESS

	public static RequestIdData saveLpLostDocumentRequest(Map<String, Object> inputParams) throws Exception {
		String operationName = "insertLpLostDocumentRequest";
		List<Param> detailsParams = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);

		// Asmaa changed according to the new insertApplicantData
		RequestIdData requestData = insertApplicantData(inputParams, wsdlUrl, loginInfo);

		List<Param> detailData = getLpLostDocumentRequestData(inputParams, loginInfo, requestData);
		List<Param> detailParams = new ArrayList<Param>();
		detailParams.add(new Param("LpLostDocReplacement", null, detailData));
		detailsParams.add(new Param("LpLostDocReplacementCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertOrUpdateLpLostDocRequest", detailParams));

		boolean hasResponse = false;
		new DynamicSoapClient().consume(wsdlUrl, operationName, detailsParams, true, hasResponse);
		return requestData;
	}

	public static void updateLpLostDocumentRequest(Map<String, Object> inputParams) throws Exception {
		String operationName = "updateLpLostDocRequestDetails";
		List<Param> detailsParams = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);
		RequestIdData requestData = new RequestIdData(inputParams.get("requestId").toString(), inputParams.get("requestNumber").toString());

		List<Param> detailData = getLpLostDocumentRequestData(inputParams, loginInfo, requestData);

		List<Param> detailParams = new ArrayList<Param>();
		detailsParams.add(new Param("stepAction", (String) inputParams.get("stepAction")));
		detailParams.add(new Param("LpLostDocReplacement", null, detailData));
		detailsParams.add(new Param("LpLostDocReplacementCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertOrUpdateLpLostDocRequest", detailParams));

		boolean hasResponse = false;
		new DynamicSoapClient().consume(wsdlUrl, operationName, detailsParams, true, hasResponse);
	}

	private static List<Param> getLpLostDocumentRequestData(Map<String, Object> inputParams, UserDetailsViewSDO loginInfo, RequestIdData requestData) {
		String newspaperAddSubmitted = null;
		String phase = (String) inputParams.get("phase");
		if (inputParams.get("newspaperAddSubmitted") != null) {
			newspaperAddSubmitted = inputParams.get("newspaperAddSubmitted").toString();
		}
		List<Param> detailData = new ArrayList<Param>();
		detailData.add(new Param("lostDocReqId", "1"));
		detailData.add(new Param("reqId", requestData.getRequestId()));
		detailData.add(new Param("accountId", loginInfo.getAccountId()));
		detailData.add(new Param("serviceId", inputParams.get("serviceId").toString()));
		detailData.add(new Param("lostDocId", inputParams.get("documenType").toString()));
		detailData.add(new Param("createdby", loginInfo.getLoginusername().getValue().toString()));
		detailData.add(new Param("createdDate", dateFormatter.format(new Date())));
		detailData.add(new Param("status", (phase == null || phase.equals("")) ? "1" : (phase.equals("Resubmit") ? "15" : "45")));
		detailData.add(new Param("newspaperAddSubmitted", newspaperAddSubmitted));
		detailData.add(new Param("requestNo", requestData.getRequestNumber()));
		return detailData;
	}

	public static void issueLpLostDocumentProcess(Map<String, Object> inputParams) throws Exception {
		String operationName = "startLostDocumentReplacmentProcess";
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);

		List<Param> params = new ArrayList<Param>();
		params.add(new Param("requestNumber", inputParams.get("requestNumber").toString()));
		params.add(new Param("applicantId", loginInfo.getAccountId()));
		params.add(new Param("applicantUserName", loginInfo.getLoginusername().getValue().toString()));
		params.add(new Param("DocTypeCode", inputParams.get("lostDocId").toString()));

		boolean hasResponse = false;
		new DynamicSoapClient().consume(PropertiesUtil.getProperty("SOA_URL_LP_LOSTDOC"), operationName, params, true, hasResponse);
	}

	// ////////////////////REAL ESTATE PROCESS

	public static RequestIdData saveRealEstateRequest(Map<String, Object> inputParams) throws Exception {
		String operationName = "insertRealEstateRequest";
		List<Param> detailsParams = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);

		// Asmaa changed according to the new insertApplicantData
		RequestIdData requestData = insertApplicantData(inputParams, wsdlUrl, loginInfo);

		List<Param> detailData = getRealEstateRequestData(inputParams, loginInfo, requestData);
		List<Param> detailParams = new ArrayList<Param>();
		detailParams.add(new Param("LpRealestateOfficeReq", null, detailData));
		detailsParams.add(new Param("LpRealestateOfficeReqCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertOrUpdateRealEstateRequest", detailParams));

		boolean hasResponse = false;
		new DynamicSoapClient().consume(wsdlUrl, operationName, detailsParams, true, hasResponse);
		return requestData;
	}

	public static void updateRealEstateRequest(Map<String, Object> inputParams) throws Exception {
		String operationName = "updateRealEstateRequest";
		List<Param> detailsParams = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);
		RequestIdData requestData = new RequestIdData(inputParams.get("requestId").toString(), inputParams.get("requestNumber").toString());

		List<Param> detailData = getRealEstateRequestData(inputParams, loginInfo, requestData);

		List<Param> detailParams = new ArrayList<Param>();
		detailsParams.add(new Param("stepAction", "SUBMITTED"));
		detailParams.add(new Param("LpRealestateOfficeReq", null, detailData));
		detailsParams.add(new Param("LpRealestateOfficeReqCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertOrUpdateRealEstateRequest", detailParams));

		boolean hasResponse = false;
		new DynamicSoapClient().consume(wsdlUrl, operationName, detailsParams, true, hasResponse);
	}

	private static List<Param> getRealEstateRequestData(Map<String, Object> inputParams, UserDetailsViewSDO loginInfo, RequestIdData requestData) {
		List<Param> detailData = new ArrayList<Param>();
		String phase = (String) inputParams.get("phase");
		detailData.add(new Param("realOffReqId", "1"));
		detailData.add(new Param("accountId", loginInfo.getAccountId()));
		detailData.add(new Param("reqId", requestData.getRequestId()));
		detailData.add(new Param("serviceId", inputParams.get("serviceId").toString()));
		detailData.add(new Param("mdName", inputParams.get("managingDirectorName").toString()));
		detailData.add(new Param("mdAddress1", inputParams.get("address").toString()));
		detailData.add(new Param("status", (phase == null || phase.equals("")) ? "33" : (phase.equals("Resubmit") ? "39" : "54")));
		detailData.add(new Param("createdBy", loginInfo.getLoginusername().getValue().toString()));
		detailData.add(new Param("createdDate", dateFormatter.format(new Date())));
		detailData.add(new Param("requestNo", requestData.getRequestNumber()));
		return detailData;
	}

	public static void issueNewRealEstateProcess(Map<String, Object> inputParams) throws Exception {
		String operationName = "mobileStartNewRealEstateRequest ";
		issueRealEstateProcess(inputParams, PropertiesUtil.getProperty("SOA_URL_ISSUE_REALESTATE"), operationName);
	}

	public static void issueRenewRealEstateProcess(Map<String, Object> inputParams) throws Exception {
		String operationName = "mobileStartReNewRealEstateRequest";
		issueRealEstateProcess(inputParams, PropertiesUtil.getProperty("SOA_URL_RENEW_REALESTATE"), operationName);
	}

	public static void issueRealEstateProcess(Map<String, Object> inputParams, String wsdl, String operation) throws Exception {
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);
		LpRealestateOfficeReqViewSDO realEstateOffice = (LpRealestateOfficeReqViewSDO) inputParams.get("realEstateOffice");

		List<Param> params = new ArrayList<Param>();
		params.add(new Param("ManageDirectorName", realEstateOffice.getMdName().getValue()));
		params.add(new Param("Address", realEstateOffice.getMdAddress1().getValue()));
		params.add(new Param("RequestNo", inputParams.get("requestNumber").toString()));
		params.add(new Param("ApplicantUserName", loginInfo.getLoginusername().getValue().toString()));
		params.add(new Param("ApplicantId", loginInfo.getAccountId()));

		boolean hasResponse = false;
		new DynamicSoapClient().consume(wsdl, operation, params, true, hasResponse);
	}

	// ////////////////////GRANT LAND REQUEST PROCESS

	public static RequestIdData saveGrantLandRequest(Map<String, Object> inputParams) throws Exception {
		String operationName = "insertGrantLandRequest";
		List<Param> detailsParams = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);

		RequestIdData requestData = insertApplicantData(inputParams, wsdlUrl, loginInfo);

		List<Param> detailData = getGrantLandRequestData(inputParams, accountDetails, requestData);
		List<Param> detailParams = new ArrayList<Param>();
		detailParams.add(new Param("GrantLandReq", null, detailData));
		detailsParams.add(new Param("GrantLandReqCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertOrUpdateGrandLandRequestService", detailParams));

		boolean hasResponse = false;
		new DynamicSoapClient().consume(wsdlUrl, operationName, detailsParams, true, hasResponse);
		return requestData;
	}

	public static void updateGrantLandRequest(Map<String, Object> inputParams) throws Exception {
		updateGrantLandRequestWithAttachments(inputParams, false);
	}

	public static void updateGrantLandRequestWithAttachments(Map<String, Object> inputParams) throws Exception {
		updateGrantLandRequestWithAttachments(inputParams, true);
	}

	public static void updateGrantLandRequestWithAttachments(Map<String, Object> inputParams, boolean inputHasAttachmentData) throws Exception {
		String operationName = "updateGrantLandRequest";
		List<Param> detailsParams = new ArrayList<Param>();
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		RequestIdData requestData = new RequestIdData(inputParams.get("requestId").toString(), inputParams.get("requestNumber").toString());

		List<Param> detailData = getGrantLandRequestData(inputParams, accountDetails, requestData);
		if (inputHasAttachmentData)
			detailData.addAll(getGrantLandAttachmentData(inputParams));

		List<Param> detailParams = new ArrayList<Param>();
		String phase = (String) inputParams.get("phase");
		String stepAction = null;
		if (phase != null && !phase.isEmpty()) {
			stepAction = "Resubmited";
		}
		detailsParams.add(new Param("stepAction", stepAction));
		detailsParams.add(new Param("requestId", inputParams.get("requestId").toString()));
		detailParams.add(new Param("GrantLandReq", null, detailData));
		detailsParams.add(new Param("GrantLandReqCollection", "http://xmlns.oracle.com/pcbpel/adapter/db/top/InsertOrUpdateGrandLandRequestService", detailParams));

		boolean hasResponse = false;
		new DynamicSoapClient().consume(wsdlUrl, operationName, detailsParams, true, hasResponse);
	}

	private static List<Param> getGrantLandRequestData(Map<String, Object> inputParams, AccountDetailsViewSDO accountDetails, RequestIdData requestData) {
		List<Param> detailData = new ArrayList<Param>();
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);
		String phase = (String) inputParams.get("phase");
		detailData.add(new Param("id", "1"));
		detailData.add(new Param("requestNo", requestData.getRequestNumber()));
		detailData.add(new Param("serviceId", inputParams.get("serviceId").toString()));
		detailData.add(new Param("createddate", dateFormatter.format(new Date())));
		detailData.add(new Param("createdby", loginInfo.getLoginusername().getValue().toString()));
		detailData.add(new Param("statusid", (phase == null || phase.equals("")) ? "33" : "39"));
		detailData.add(new Param("familyMembersNo", inputParams.get("familyMembersNo").toString()));
		detailData.add(new Param("employer", inputParams.get("employer").toString()));
		detailData.add(new Param("monthlySalary", inputParams.get("monthlySalary").toString()));
		detailData.add(new Param("currentaddress", inputParams.get("currentaddress").toString()));
		detailData.add(new Param("maritalStatus", inputParams.get("maritalStatus").toString()));
		detailData.add(new Param("ownername", loginInfo.getLoginusername().getValue()));
		detailData.add(new Param("ownerId", loginInfo.getAccountId()));
		detailData.add(new Param("parentReqNo", inputParams.get("fromRequestNo")==null?null:inputParams.get("fromRequestNo").toString()));
		detailData.add(new Param("ownerNationalityId", accountDetails.getNationalityId()==null?null:accountDetails.getNationalityId().toString()));
		return detailData;
	}

	
	
	private static List<Param> getGrantLandAttachmentData(Map<String, Object> inputParams) {
		List<Param> detailData = new ArrayList<Param>();
		if (inputParams.get("familyBookId") != null)
			detailData.add(new Param("familyBookId", (String) inputParams.get("familyBookId")));
		if (inputParams.get("spouseEmirateId") != null)
			detailData.add(new Param("spousesEmiratesId", (String) inputParams.get("spouseEmirateId")));
		if (inputParams.get("propertyAccountingId") != null)
			detailData.add(new Param("proprtyAccountingId", (String) inputParams.get("propertyAccountingId")));
		if (inputParams.get("salaryCertificateId") != null)
			detailData.add(new Param("salaryCertificateId", (String) inputParams.get("salaryCertificateId")));
		if (inputParams.get("familyBookDocId") != null)
			detailData.add(new Param("familyBookDocId", (String) inputParams.get("familyBookDocId")));
		if (inputParams.get("propertyAccountingDocId") != null)
			detailData.add(new Param("propertyAccountingDocId", (String) inputParams.get("propertyAccountingDocId")));
		if (inputParams.get("spouseEmirateDocId") != null)
			detailData.add(new Param("spouseEmirateDocId", (String) inputParams.get("spouseEmirateDocId")));
		if (inputParams.get("salaryCertificateDocId") != null)
			detailData.add(new Param("salaryCertificateDocId", (String) inputParams.get("salaryCertificateDocId")));
		if (inputParams.get("rulersCourtAcceptanceDocId") != null)
			detailData.add(new Param("rulersCourtAcceptanceDocId", (String) inputParams.get("rulersCourtAcceptanceDocId")));
		return detailData;
	}

	public static void issueGrantLandRequestProcess(Map<String, Object> inputParams) throws Exception {
		String operationName = "startProcess";
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);

		List<Param> detailsParams = new ArrayList<Param>();
		detailsParams.add(new Param("GLRDataInput", "glr", getGLRDataInput(inputParams, loginInfo),false));
		detailsParams.add(new Param("ApplicantDataInput", "appl", getGLApplicantDataInput(accountDetails),false));

		boolean hasResponse = false;
		new DynamicSoapClient().consume(PropertiesUtil.getProperty("SOA_URL_GRANTLAND_REQ"), operationName, detailsParams, true, hasResponse);
	}

	private static List<Param> getGLRDataInput(Map<String, Object> inputParams, UserDetailsViewSDO loginInfo) {
		List<Param> detailData = new ArrayList<Param>();
		GrantLandReqViewSDO grantLandReq = (GrantLandReqViewSDO) inputParams.get("grantLandReq");

		detailData.add(new Param("GrantReqReference", inputParams.get("requestNumber").toString()));
		detailData.add(new Param("FamilyMembers", grantLandReq.getFamilyMembersNo().getValue()));
		detailData.add(new Param("MonthlySalary", grantLandReq.getMonthlySalary().getValue()));
		detailData.add(new Param("EmployerName", grantLandReq.getEmployer().getValue()));
		detailData.add(new Param("Address", grantLandReq.getCurrentaddress().getValue()));
		detailData.add(new Param("ApplicantId", grantLandReq.getOwnerId().getValue()));
		detailData.add(new Param("MaritalStatus", grantLandReq.getMaritalStatus().getValue()));
		detailData.add(new Param("GrantRequestId", grantLandReq.getId().getValue().toString()));
		detailData.add(new Param("FamilyBookId", grantLandReq.getFamilyBookId().getValue()));
		detailData.add(new Param("SpouseEmirateId", grantLandReq.getSpousesEmiratesId().getValue()));
		detailData.add(new Param("PropertyAccountingId", grantLandReq.getProprtyAccountingId().getValue()));
		detailData.add(new Param("SalaryCertificate", grantLandReq.getSalaryCertificateId().getValue()));
		detailData.add(new Param("FamilyBookFileName", null));
		detailData.add(new Param("SpouseEmirateFileName", null));
		detailData.add(new Param("PropertyAccountingFileName", null));
		detailData.add(new Param("SalaryCertificateFileName", null));
		detailData.add(new Param("RequestId", grantLandReq.getId().getValue().toString()));
		detailData.add(new Param("ApplicantRequestId", inputParams.get("requestId").toString()));
		detailData.add(new Param("StepNo", null));
		detailData.add(new Param("SpouseEmirateIdField", null));
		detailData.add(new Param("UserComment", null));
		detailData.add(new Param("DocumentUrl", null));
		detailData.add(new Param("AttachContent", null));
		detailData.add(new Param("FileName", null));
		detailData.add(new Param("MimeType", null));
		detailData.add(new Param("LastUser", loginInfo.getUserName()));
		detailData.add(new Param("GrantLandDocId", null));
		detailData.add(new Param("RulersCourtAcceptanceDocId", null));
		detailData.add(new Param("RulersCourtAcceptanceRemark", null));
		detailData.add(new Param("SourceType", "1"));

		return detailData;
	}

	private static List<Param> getGLApplicantDataInput(AccountDetailsViewSDO accountDetails) {
		List<Param> detailData = new ArrayList<Param>();
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);
		String applicantType = null;
		if (accountDetails.getApplicanttypeid().getValue() != null)
			applicantType = accountDetails.getApplicanttypeid().getValue().toString();

		detailData.add(new Param("ApplicantName", loginInfo.getLoginusername().getValue()));
		detailData.add(new Param("ApplicantMobileNumber", loginInfo.getMobileNo()));
		detailData.add(new Param("ApplicantAddress", accountDetails.getAddressline1()));
		detailData.add(new Param("NationalityId", accountDetails.getNationalityId()==null?null:accountDetails.getNationalityId().toString()));
		detailData.add(new Param("NationalityName", null));
		detailData.add(new Param("TypeOfUser", accountDetails.getTypeOfUser()));
		detailData.add(new Param("AccountId", loginInfo.getAccountId()));
		detailData.add(new Param("EmiratesId", accountDetails.getEmiratesId().getValue()));
		detailData.add(new Param("TradeLicenseNo", accountDetails.getTradeLienceNo().getValue()));
		detailData.add(new Param("EmirateName", accountDetails.getEmirate()));
		detailData.add(new Param("PostBox", accountDetails.getPostbox()==null?null:accountDetails.getPostbox().toString()));
		detailData.add(new Param("ApplicantType", applicantType));
		detailData.add(new Param("EmailAddress", loginInfo.getEmailAddress()));
		detailData.add(new Param("UserName", loginInfo.getUserName()));

		return detailData;
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
	
	public static void issueApplicantNotifiction(Map<String, Object> inputParams) throws Exception {
		String operationName = "start";
		
		List<Param> mobileDetailsParams = new ArrayList<Param>();
		mobileDetailsParams.add(new Param("mobileNumber",inputParams.get("mobileNumber").toString()));
		mobileDetailsParams.add(new Param("mobileMessage",null));
		
		
		List<Param> emailDetailsParams = new ArrayList<Param>();
		emailDetailsParams.add(new Param("applicantEmail",inputParams.get("applicantEmail").toString()));
		emailDetailsParams.add(new Param("emailSubject",null));
		emailDetailsParams.add(new Param("emailBody",null));
		emailDetailsParams.add(new Param("attachmentFileName",null));
		emailDetailsParams.add(new Param("attachmentContent",null));
		emailDetailsParams.add(new Param("attachmentMimeType",null));
		
		
		List<Param> pushDetailsParams = new ArrayList<Param>();
		pushDetailsParams.add(new Param("requestNumber",inputParams.get("requestNumber").toString()));
		pushDetailsParams.add(new Param("applicantUserName",inputParams.get("applicantUserName").toString()));
		pushDetailsParams.add(new Param("pnMessage",null));
		
		List<Param> templateParams = new ArrayList<Param>();
		templateParams.add(new Param("applicantAccountId",inputParams.get("applicantAccountId").toString()));
		templateParams.add(new Param("stepName",inputParams.get("stepName").toString()));
		templateParams.add(new Param("requestNo",inputParams.get("requestNumber").toString()));
		templateParams.add(new Param("applicantName",inputParams.get("applicantName").toString()));
		templateParams.add(new Param("transactionId",inputParams.get("transactionId").toString()));
		templateParams.add(new Param("amount",inputParams.get("amount").toString()));
		templateParams.add(new Param("serviceId",null));
		templateParams.add(new Param("date",null));
		templateParams.add(new Param("param1",null));
		templateParams.add(new Param("param2",null));
		templateParams.add(new Param("param3",null));
		templateParams.add(new Param("param4",null));
		templateParams.add(new Param("param5",null));
		templateParams.add(new Param("param6",null));
		templateParams.add(new Param("param7",null));
		templateParams.add(new Param("param8",null));
		templateParams.add(new Param("param9",null));
		templateParams.add(new Param("param10",null));
		
		
		List<Param> detailsParams = new ArrayList<Param>();
		detailsParams.add(new Param("mobile", null,mobileDetailsParams));
		detailsParams.add(new Param("email", null,emailDetailsParams));
		detailsParams.add(new Param("PN", null,pushDetailsParams));
		detailsParams.add(new Param("TemplateDetails", null,templateParams));
		
		List<Param> notificationParams = new ArrayList<Param>();
		notificationParams.add(new Param("notification", "http://www.tacme.com" ,detailsParams));

		boolean hasResponse = false;
		new DynamicSoapClient().consume(PropertiesUtil.getProperty("SOA_URL_APPLICANT_NOTIFICATION"), operationName, notificationParams, true, hasResponse);
	}
	
	public static void copyRequestLatestAttachments(Map<String, Object> inputParams) throws Exception {
		String operationName = "copyRequestAttachments";
		List<Param> params = new ArrayList<Param>();
		params.add(new Param("fromRequestId", (String)inputParams.get("fromRequestId")));
		params.add(new Param("toRequestId", (String)inputParams.get("toRequestId")));
		params.add(new Param("serviceId", (String)inputParams.get("serviceId").toString()));
		
		boolean hasResponse = false;
		new DynamicSoapClient().consume(wsdlUrl, operationName, params, true, hasResponse);		
	}
	public static void issueGrantLandRequestExceptionProcess(Map<String, Object> inputParams) throws Exception {
		String operationName = "startGLRException";		
		
		List<Param> detailsParams = new ArrayList<Param>();		
		detailsParams.add(new Param("GLRData", "http://www.glr.com", getGLRData(inputParams),false));
		detailsParams.add(new Param("ISPDInput", "http://IssueSitePlanDoc", getISPDInput(inputParams)));
		
		boolean hasResponse = false;
		new DynamicSoapClient().consume(PropertiesUtil.getProperty("SOA_URL_GRANTLAND_EXCEP_REQ"), operationName, detailsParams, true, hasResponse);
	}

	private static List<Param> getGLRData(Map<String, Object> inputParams) {
		List<Param> detailParams = new ArrayList<Param>();
		detailParams.add(new Param("requestId", (String)inputParams.get("requestId")));
		detailParams.add(new Param("requestNo", (String)inputParams.get("requestNumber")));
		detailParams.add(new Param("requestDate", (String)inputParams.get("requestDate")));
		detailParams.add(new Param("serviceFees", "150"));
		detailParams.add(new Param("stepNumber", "1"));
		detailParams.add(new Param("instanceId", null));
		detailParams.add(new Param("comment", null));
		detailParams.add(new Param("sourceType", "1"));
		return detailParams;
	}
	
	private static List<Param> getISPDInput(Map<String, Object> inputParams) {
		AccountDetailsViewSDO accountDetails = (AccountDetailsViewSDO) inputParams.get("accountDetails");
		UserDetailsViewSDO loginInfo = accountDetails.getUserDetailsView().get(0);
		
		List<Param> detailParams = new ArrayList<Param>();
		detailParams.add(new Param("UserDetails", null, getUserDetails(inputParams, accountDetails, loginInfo)));
		detailParams.add(new Param("ServiceType", "NEW"));
		detailParams.add(new Param("SourceType", "1"));
		detailParams.add(new Param("SubmittedByUserId",loginInfo.getAccountId()));
		detailParams.add(new Param("Status", "1"));
		detailParams.add(new Param("ServiceName", "Grant Land Request Exception"));
		detailParams.add(new Param("Serviceid", (String)inputParams.get("serviceId")));//Site_Plan_No
		detailParams.add(new Param("Site_Plan_No", (String)inputParams.get("sitePlanNo")));
		int landLocation = Integer.parseInt((String)inputParams.get("landLocation"));
		if(landLocation == 1) {
			// Area
			detailParams.add(new Param("Area_Name", (String)inputParams.get("area")));
			detailParams.add(new Param("Area_Block", (String)inputParams.get("areaBlock")));
			detailParams.add(new Param("Area_Sub_Area", (String)inputParams.get("subArea")));
			detailParams.add(new Param("Area_Plot_No", (String)inputParams.get("areaPlotNumber")));
		} else {
			// Sector
			detailParams.add(new Param("Sec_Sector_Name", (String)inputParams.get("sector")));
			detailParams.add(new Param("Sector_block", (String)inputParams.get("sectorBlock")));
			detailParams.add(new Param("Sub_Sector_No", (String)inputParams.get("subSector")));
			detailParams.add(new Param("Sec_Plot_No", (String)inputParams.get("sectorPlotNumber")));
		}
		detailParams.add(new Param("AttachmentList", null, getAttachmentList(inputParams)));
		detailParams.add(new Param("Land_Usage", (String)inputParams.get("landUsage")));
		return detailParams;
	}
	
	private static List<Param> getUserDetails(Map<String, Object> inputParams, AccountDetailsViewSDO accountDetails, UserDetailsViewSDO loginInfo) {
		List<Param> detailParams = new ArrayList<Param>();
		detailParams.add(new Param("Username", loginInfo.getLoginusername().getValue().toString()));
		detailParams.add(new Param("TypeOfUser", accountDetails.getTypeOfUser()));
		detailParams.add(new Param("nationality", accountDetails.getNationalityId()==null?null:accountDetails.getNationalityId().toString()));
		return detailParams;
	}
	
	private static List<Param> getAttachmentList(Map<String, Object> inputParams) {
		ServiceAttachment sitePlanAttachment = (ServiceAttachment)inputParams.get("sitePlanAttachment");		
		List<Param> attachDetailParams = new ArrayList<Param>();
		attachDetailParams.add(new Param("Contentid", sitePlanAttachment.getDID()));
		attachDetailParams.add(new Param("Filename", sitePlanAttachment.getFileName()));
		attachDetailParams.add(new Param("IsMandatory", "0"));
		attachDetailParams.add(new Param("Url", sitePlanAttachment.getViewUrl()));
		attachDetailParams.add(new Param("DocType", sitePlanAttachment.getDocId()));
		
		List<Param> detailParams = new ArrayList<Param>();
		detailParams.add(new Param("AttachmentRec", null, attachDetailParams));
		return detailParams;
	}
	
}
