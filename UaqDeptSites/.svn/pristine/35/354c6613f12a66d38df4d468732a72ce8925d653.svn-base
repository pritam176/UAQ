package com.uaq.service;

import static com.uaq.common.ApplicationConstants.COMMA;
import static com.uaq.common.ApplicationConstants.LANG_ENGLISH;
import static com.uaq.common.ApplicationConstants.REQUEST_PARAM_PAGE_LABLE;
import static com.uaq.common.ApplicationConstants.REQUEST_PARAM_REQUEST_NO;
import static com.uaq.common.ApplicationConstants.REQUEST_PARAM_SERVICE_ID;
import static com.uaq.common.ApplicationConstants.REQUEST_PARAM_STATUS_ID;
import static com.uaq.common.ApplicationConstants.REQUEST_PARAM_TYPE_OF_USER;
import static com.uaq.common.ApplicationConstants.UNDERSCORE;
import static com.uaq.common.ServiceNameConstant.ADD_LAND_REQUEST;
import static com.uaq.common.ServiceNameConstant.EXTENTION_OF_GRANT_LAND_REQUEST;
import static com.uaq.common.ServiceNameConstant.GRANT_LAND_REQUEST;
import static com.uaq.common.ServiceNameConstant.ISSUE_NEW_PRO_REQUEST;
import static com.uaq.common.ServiceNameConstant.ISSUE_SITE_PLAN_DOCUMENT_REQUEST;
import static com.uaq.common.ServiceNameConstant.ISSUE_TO_WHOME_IT_MAY_CERTIFICATE;
import static com.uaq.common.ServiceNameConstant.LAND_DEMACRATION_REQUEST;
import static com.uaq.common.ServiceNameConstant.LAND_PROPERTY_VALUTION_REQUEST;
import static com.uaq.common.ServiceNameConstant.LOST_DOCUMENT;
import static com.uaq.common.ServiceNameConstant.NEW_REAL_ESTATE;
import static com.uaq.common.ServiceNameConstant.NEW_SUPPLIER_REGISTRATION;
import static com.uaq.common.ServiceNameConstant.RENEW_PRO_REQUEST;
import static com.uaq.common.ServiceNameConstant.RENEW_REAL_ESTATE;
import static com.uaq.common.ServiceNameConstant.GRANT_LAND_EXCEPTION_REQUEST;
import static com.uaq.common.ServiceNameConstant.RENEW_SUPPLIER_REGISTRATION;
import static com.uaq.common.StatusNameConstant.PROCEED_TO_APPLICATION_FEE_PAYMENT;
import static com.uaq.common.StatusNameConstant.PROCEED_TO_SERVICE_FEE_PAYMENT;
import static com.uaq.common.WebServiceConstant.SOAP_FEETYPE_ARGUMENT;
import static com.uaq.common.WebServiceConstant.SOAP_LANDTYPE_ARGUMENT;
import static com.uaq.common.WebServiceConstant.SOAP_REQUESTNO_ARGUMENT;
import static com.uaq.common.WebServiceConstant.SOAP_SERVICEID_ARGUMENT;
import static com.uaq.common.WebServiceConstant.SOAP_USERTYPE_ARGUMENT;


import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uaq.db.si.model.common.LpValuationViewSDO;
import uaq.pymt.si.model.common.EserviceFeeMatrixViewSDO;

import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.util.StringUtil;
import com.uaq.vo.LPtoWhomeConcernVO;
import com.uaq.vo.PSResubmissonOutputVO;
import com.uaq.vo.ReSubmiisionInputVO;

@Service(value= "feeIdService")
public class FeeIdService {
	
	protected static UAQLogger logger = new UAQLogger(FeeIdService.class);
	

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private LPFindRequestService lFindRequestService;

	@Autowired
	private PSFindRequestService pSFindRequestService;
	
	@Autowired
	private LPServiceLookUp lPServiceLookUp;
	
	
	PurchaseService purchaseService = new PurchaseService();
	
	
	public Map<String,String> getServiceFee(String requestNo,String serviceId, String userType, String applicantType, String status, Connection con)throws Exception{
		Map<String,String> serviceFeeMap = new HashMap<String,String>();
		String feeTypeCode = "A001";
		if("18".equals(status))
				feeTypeCode = "S001";
		ReSubmiisionInputVO inputVO = new ReSubmiisionInputVO();
		switch (serviceId) {
		

		case ISSUE_TO_WHOME_IT_MAY_CERTIFICATE:
			
			
			inputVO.setAttributeName(SOAP_REQUESTNO_ARGUMENT);
			inputVO.setAttributeValue(requestNo);
			LPtoWhomeConcernVO lptoWhomeConcernVO = lFindRequestService.findLpToWhomConcernView1(inputVO, LANG_ENGLISH);
			String letter = "Other";
			int letterid = lptoWhomeConcernVO.getAddressedtoId().intValue();
			if (letterid == 4 || letterid == 6 || letterid == 7 || letterid == 0 || letterid == 8 || letterid == 9 || letterid == 12 || letterid == 13 || letterid == 14) {
				letter = "Charity";
			} else if (letterid == 2 || letterid == 5) {
				letter = "Social Affairs";
			}
			serviceFeeMap = purchaseService.getServiceFee(serviceId, feeTypeCode, userType, applicantType, letter, null, con);
			break;
		
		
		case LAND_PROPERTY_VALUTION_REQUEST:
			String landType = null;
			if(requestNo!=null){
				LpValuationViewSDO lpValuation = lPServiceLookUp.getLPValudationRequestByRequestNumber(requestNo);
				 landType = String.valueOf(lpValuation.getLandCategory().getValue());
			}
			
				serviceFeeMap = purchaseService.getServiceFee(serviceId, feeTypeCode, userType, applicantType, null, landType, con);
			break;
		case EXTENTION_OF_GRANT_LAND_REQUEST:
			serviceFeeMap = purchaseService.getServiceFee(serviceId, feeTypeCode, userType, applicantType, null, null, con);

			inputVO = new ReSubmiisionInputVO();
			inputVO.setAttributeName(SOAP_REQUESTNO_ARGUMENT);
			inputVO.setAttributeValue(requestNo);
			PSResubmissonOutputVO resubmitVO = pSFindRequestService.findExtentionGrandLandRequest(inputVO);
			serviceFeeMap.put("calculatedAmountFromService", resubmitVO.getCreatedby());
			break;
		

		default:
			serviceFeeMap = purchaseService.getServiceFee(serviceId, feeTypeCode, userType, applicantType, null, null, con);

		}
		return serviceFeeMap;
	}
	public Map<String, String> getSearchCriteriaMap(String serviceId, Map<String, String> queryParamsMap) throws Exception {
		Map<String, String> searchCriteriaMap = null;

		switch (serviceId) {
		case ISSUE_SITE_PLAN_DOCUMENT_REQUEST:
			searchCriteriaMap = new HashMap<String, String>();
			String typeOfUser = queryParamsMap.get(REQUEST_PARAM_TYPE_OF_USER);
			String serviceIdtypeOfUser = serviceId + UNDERSCORE + typeOfUser;
			String IssueSitePlanSearchCriteria = PropertiesUtil.getProperty(serviceIdtypeOfUser);
			String[] keywords = IssueSitePlanSearchCriteria.split(COMMA);
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, ISSUE_SITE_PLAN_DOCUMENT_REQUEST);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, keywords[0]);
			searchCriteriaMap.put(SOAP_USERTYPE_ARGUMENT, keywords[1]);
			break;

		case NEW_SUPPLIER_REGISTRATION:
			searchCriteriaMap = new HashMap<String, String>();
			String newSupplierID = queryParamsMap.get(REQUEST_PARAM_SERVICE_ID);
			String newSupplierSerachCrieteria = PropertiesUtil.getProperty(newSupplierID);
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, NEW_SUPPLIER_REGISTRATION);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, newSupplierSerachCrieteria);
			break;

		case RENEW_SUPPLIER_REGISTRATION:
			searchCriteriaMap = new HashMap<String, String>();
			String reNewSupplierId = queryParamsMap.get(REQUEST_PARAM_SERVICE_ID);
			String reNewSupplierSerachCrieteria = PropertiesUtil.getProperty(reNewSupplierId);
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, RENEW_SUPPLIER_REGISTRATION);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, reNewSupplierSerachCrieteria);
			break;
		case ADD_LAND_REQUEST:
			searchCriteriaMap = new HashMap<String, String>();
			String addLandTypeofUser = queryParamsMap.get(REQUEST_PARAM_TYPE_OF_USER);
			String addLandRequestSTatus = queryParamsMap.get(REQUEST_PARAM_STATUS_ID);
			String propertyFileKey = serviceId + UNDERSCORE + addLandRequestSTatus + UNDERSCORE + addLandTypeofUser;
			String addLandIdSerachCrieteria = PropertiesUtil.getProperty(propertyFileKey);
			keywords = addLandIdSerachCrieteria.split(COMMA);
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, ADD_LAND_REQUEST);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, keywords[0]);
			searchCriteriaMap.put(SOAP_USERTYPE_ARGUMENT, keywords[1]);
			break;

		case LAND_DEMACRATION_REQUEST:
			searchCriteriaMap = new HashMap<String, String>();
			String landDemacrationTypeofUser = queryParamsMap.get(REQUEST_PARAM_TYPE_OF_USER);
			String landDemacrationrequestSTatus = queryParamsMap.get(REQUEST_PARAM_STATUS_ID);
			String landDemacrationrequestpropertyFileKey = serviceId + UNDERSCORE + landDemacrationrequestSTatus + UNDERSCORE + landDemacrationTypeofUser;
			String landDemacrationSerachCrieteria = PropertiesUtil.getProperty(landDemacrationrequestpropertyFileKey);
			keywords = landDemacrationSerachCrieteria.split(COMMA);
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, LAND_DEMACRATION_REQUEST);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, keywords[0]);
			searchCriteriaMap.put(SOAP_USERTYPE_ARGUMENT, keywords[1]);
			break;
		case ISSUE_TO_WHOME_IT_MAY_CERTIFICATE:
			// TowhomeEverMayConcern
			searchCriteriaMap = new HashMap<String, String>();
			ReSubmiisionInputVO inputVO = new ReSubmiisionInputVO();
			inputVO.setAttributeName(SOAP_REQUESTNO_ARGUMENT);
			inputVO.setAttributeValue(queryParamsMap.get(REQUEST_PARAM_REQUEST_NO));
			LPtoWhomeConcernVO lptoWhomeConcernVO = lFindRequestService.findLpToWhomConcernView1(inputVO, LANG_ENGLISH);
			String letter = "3";
			int letterid = lptoWhomeConcernVO.getAddressedtoId().intValue();
			if (letterid == 4 || letterid == 6 || letterid == 7 || letterid == 0 || letterid == 8 || letterid == 9 || letterid == 12 || letterid == 13 || letterid == 14) {
				letter = "2";
			} else if (letterid == 2 || letterid == 5) {
				letter = "1";
			}

			String towepropertyFileKey = serviceId + "_" + letter;
			String toweSerachCrieteria = PropertiesUtil.getProperty(towepropertyFileKey);
			keywords = toweSerachCrieteria.split(",");
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, serviceId);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, keywords[0]);
			searchCriteriaMap.put("Letter", keywords[1]);
			break;
		case ISSUE_NEW_PRO_REQUEST:
			searchCriteriaMap = new HashMap<String, String>();
			String procardTypeofUser = queryParamsMap.get(REQUEST_PARAM_TYPE_OF_USER);
			String procardrequestStatus = queryParamsMap.get(REQUEST_PARAM_STATUS_ID);
			String procardpropertyFileKey = serviceId + UNDERSCORE + procardrequestStatus+ UNDERSCORE + procardTypeofUser;
			String procardSerachCrieteria = PropertiesUtil.getProperty(procardpropertyFileKey);
			keywords = procardSerachCrieteria.split(COMMA);
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, ISSUE_NEW_PRO_REQUEST);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, keywords[0]);
			 searchCriteriaMap.put(SOAP_USERTYPE_ARGUMENT, keywords[1]);
			break;

		case RENEW_PRO_REQUEST:
			searchCriteriaMap = new HashMap<String, String>();
			String renewprocardTypeofUser = queryParamsMap.get(REQUEST_PARAM_TYPE_OF_USER);
			String renewprocardrequestStatus = queryParamsMap.get(REQUEST_PARAM_STATUS_ID);
			String renewprocardFileKey = serviceId + UNDERSCORE + renewprocardrequestStatus+ UNDERSCORE + renewprocardTypeofUser;
			String renewprocardFileCrieteria = PropertiesUtil.getProperty(renewprocardFileKey);
			keywords = renewprocardFileCrieteria.split(COMMA);
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, RENEW_PRO_REQUEST);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, keywords[0]);
			 searchCriteriaMap.put(SOAP_USERTYPE_ARGUMENT, keywords[1]);
			break;
		case LAND_PROPERTY_VALUTION_REQUEST:
			searchCriteriaMap = new HashMap<String, String>();
			String landPropertyStatus = queryParamsMap.get(REQUEST_PARAM_STATUS_ID);
			String landPropertyTypeofUser = queryParamsMap.get(REQUEST_PARAM_TYPE_OF_USER);
			// Get The OwnerType
			
			
			String landPropertyFileKey = "";
			String landPropertyFileCrieteria = "";
			if (PROCEED_TO_APPLICATION_FEE_PAYMENT.equals(landPropertyStatus)) {
				landPropertyFileKey = serviceId + UNDERSCORE + landPropertyStatus;
				landPropertyFileCrieteria = PropertiesUtil.getProperty(landPropertyFileKey);
//				keywords = landPropertyFileCrieteria.split(COMMA);
				searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, LAND_PROPERTY_VALUTION_REQUEST);
				searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, landPropertyFileCrieteria);
//				searchCriteriaMap.put(SOAP_USERTYPE_ARGUMENT, keywords[1]);
			} else if (PROCEED_TO_SERVICE_FEE_PAYMENT.equals(landPropertyStatus)) {
				LpValuationViewSDO lpValuation = lPServiceLookUp.getLPValudationRequestByRequestNumber(queryParamsMap.get(REQUEST_PARAM_REQUEST_NO));
				String ownerType = String.valueOf(lpValuation.getLandCategory().getValue());
				landPropertyFileKey = serviceId + UNDERSCORE + landPropertyStatus + UNDERSCORE + ownerType;
				landPropertyFileCrieteria = PropertiesUtil.getProperty(landPropertyFileKey);
				keywords = landPropertyFileCrieteria.split(COMMA);
				searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, LAND_PROPERTY_VALUTION_REQUEST);
				searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, keywords[0]);
				searchCriteriaMap.put(SOAP_LANDTYPE_ARGUMENT, keywords[1]);
			}

			// searchCriteriaMap.put(SOAP_USERTYPE_ARGUMENT, keywords[1]);
			break;
		case EXTENTION_OF_GRANT_LAND_REQUEST:
			searchCriteriaMap = new HashMap<String, String>();
			typeOfUser = queryParamsMap.get(REQUEST_PARAM_TYPE_OF_USER);
			serviceIdtypeOfUser = serviceId + UNDERSCORE + typeOfUser;
			String extentionGrandLandCritera = PropertiesUtil.getProperty(serviceIdtypeOfUser);
			keywords = extentionGrandLandCritera.split(COMMA);
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, EXTENTION_OF_GRANT_LAND_REQUEST);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, keywords[0]);
			searchCriteriaMap.put(SOAP_USERTYPE_ARGUMENT, keywords[1]);

			inputVO = new ReSubmiisionInputVO();
			inputVO.setAttributeName(SOAP_REQUESTNO_ARGUMENT);
			inputVO.setAttributeValue(queryParamsMap.get(REQUEST_PARAM_REQUEST_NO));
			PSResubmissonOutputVO resubmitVO = pSFindRequestService.findExtentionGrandLandRequest(inputVO);
			searchCriteriaMap.put("calculatedAmountFromService", resubmitVO.getCreatedby());
			break;
		case NEW_REAL_ESTATE:
			searchCriteriaMap = new HashMap<String, String>();
			String realStateStatus = queryParamsMap.get(REQUEST_PARAM_STATUS_ID);
			String realStateTypeofUser = queryParamsMap.get(REQUEST_PARAM_TYPE_OF_USER);
			String realStatePropertyFileKey = serviceId + UNDERSCORE + realStateStatus+ UNDERSCORE + realStateTypeofUser;
			String realStateCritera = PropertiesUtil.getProperty(realStatePropertyFileKey);
			keywords = realStateCritera.split(COMMA);
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, NEW_REAL_ESTATE);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, keywords[0]);
			searchCriteriaMap.put(SOAP_USERTYPE_ARGUMENT, keywords[1]);
			break;
		case RENEW_REAL_ESTATE:
			searchCriteriaMap = new HashMap<String, String>();
			String renewRealStateStatus = queryParamsMap.get(REQUEST_PARAM_STATUS_ID);
			String renewRealStateTypeofUser = queryParamsMap.get(REQUEST_PARAM_TYPE_OF_USER);
			String renewRealStatePropertyFileKey = serviceId + UNDERSCORE + renewRealStateStatus+ UNDERSCORE + renewRealStateTypeofUser;
			String renewRealStateCritera = PropertiesUtil.getProperty(renewRealStatePropertyFileKey);
			keywords = renewRealStateCritera.split(COMMA);
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, RENEW_REAL_ESTATE);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, keywords[0]);
			searchCriteriaMap.put(SOAP_USERTYPE_ARGUMENT, keywords[1]);
			break;
		case LOST_DOCUMENT:
			searchCriteriaMap = new HashMap<String, String>();
			String lostDocumentStatus = queryParamsMap.get(REQUEST_PARAM_STATUS_ID);
			String lostDocumentTypeofUser = queryParamsMap.get(REQUEST_PARAM_TYPE_OF_USER);
			String lostDocumentPropertyFileKey = serviceId + UNDERSCORE + lostDocumentStatus+ UNDERSCORE + lostDocumentTypeofUser;
			String lostDocumentCritera = PropertiesUtil.getProperty(lostDocumentPropertyFileKey);
			keywords = lostDocumentCritera.split(COMMA);
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, LOST_DOCUMENT);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, keywords[0]);
			searchCriteriaMap.put(SOAP_USERTYPE_ARGUMENT, keywords[1]);
			break;
		case GRANT_LAND_REQUEST:
			searchCriteriaMap = new HashMap<String, String>();
			String grantLandTypeofUser = queryParamsMap.get(REQUEST_PARAM_TYPE_OF_USER);
			String grantLandrequestSTatus = queryParamsMap.get(REQUEST_PARAM_STATUS_ID);
			String grantLandrequestpropertyFileKey = serviceId + UNDERSCORE + grantLandrequestSTatus + UNDERSCORE + grantLandTypeofUser;
			String grantLandSerachCrieteria = PropertiesUtil.getProperty(grantLandrequestpropertyFileKey);
			keywords = grantLandSerachCrieteria.split(COMMA);
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, GRANT_LAND_REQUEST);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, keywords[0]);
			searchCriteriaMap.put(SOAP_USERTYPE_ARGUMENT, keywords[1]);
			break;
		case GRANT_LAND_EXCEPTION_REQUEST:
			searchCriteriaMap = new HashMap<String, String>();
			String grantLandExceptionTypeofUser = queryParamsMap.get(REQUEST_PARAM_TYPE_OF_USER);
			String grantLandExceptionrequestSTatus = queryParamsMap.get(REQUEST_PARAM_STATUS_ID);
			String grantLandExceptionrequestpropertyFileKey = serviceId + UNDERSCORE + grantLandExceptionrequestSTatus + UNDERSCORE + grantLandExceptionTypeofUser;
			String grantLandExceptionSerachCrieteria = PropertiesUtil.getProperty(grantLandExceptionrequestpropertyFileKey);
			keywords = grantLandExceptionSerachCrieteria.split(COMMA);
			searchCriteriaMap.put(SOAP_SERVICEID_ARGUMENT, GRANT_LAND_EXCEPTION_REQUEST);
			searchCriteriaMap.put(SOAP_FEETYPE_ARGUMENT, keywords[0]);
			searchCriteriaMap.put(SOAP_USERTYPE_ARGUMENT, keywords[1]);
			break;

		default:
			searchCriteriaMap = new HashMap<String, String>();

		}

		return searchCriteriaMap;
	}
}

