package com.uaq.service;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import uaq.db.si.model.common.AccountDetailsViewSDO;
import uaq.db.si.model.common.ApplicantRequestViewSDO;

import static com.uaq.common.ServiceNameConstant.*;
import static com.uaq.common.StatusNameConstant.*;
import static com.uaq.common.WebServiceConstant.*;
import com.uaq.controller.mapper.EGDDataMapper;
import com.uaq.controller.mapper.LPDataMapper;
import com.uaq.controller.mapper.PSDataMapper;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.EGDResubmissionVO;
import com.uaq.vo.LPtoWhomeConcernVO;
import com.uaq.vo.LandInputVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.NewSupplierRegistrationVO;
import com.uaq.vo.PSResubmissonOutputVO;
import com.uaq.vo.PaymentTransactionDetailVO;
import com.uaq.vo.ReSubmiisionInputVO;
import com.uaq.vo.UserDeatilVO;
import com.uaq.vo.WhomItmayConcernVO;
import static com.uaq.common.ApplicationConstants.*;

@Service
public class ReviewerService {

	private static transient UAQLogger logger = new UAQLogger(ReviewerService.class);

	@Autowired
	private LPFindRequestService lpFindRequestService;

	@Autowired
	private PSFindRequestService pSFindRequestService;

	@Autowired
	private PSReSubmissionRequestService pSReSubmissionRequestService;

	@Autowired
	private LPReSubmissionRequestService lPReSubmissionRequestService;

	@Autowired
	private EGDFindRequestService eGDFindRequestService;

	@Autowired
	private EGDAfterServicePayment eGDAfterServicePayment;

	@Autowired
	private PSAfterServicePayment pSAfterServicePayment;

	@Autowired
	private LPServiceLookUp lPServiceLookUp;

	@Autowired
	private MessageSource messageSource;

	public LandOutputVO invokeRevierIneceator(AccountDetailTokenOutputVO accountDetailfromToken, PaymentTransactionDetailVO paymentTransactionDetailVO) throws Exception {

		LandOutputVO landOutputVO = new LandOutputVO();

		String requestNo = paymentTransactionDetailVO.getRequestNo();
		String requestId = paymentTransactionDetailVO.getRequestId();
		String serviceId = paymentTransactionDetailVO.getServiceId();
		String languageCode = PortalDataMapper.getLanguageId(paymentTransactionDetailVO.getLanguageCode());
		String status = paymentTransactionDetailVO.getStatusId();

		ReSubmiisionInputVO inputVO = new ReSubmiisionInputVO();
		inputVO.setAttributeName(SOAP_REQUESTNO_ARGUMENT);
		inputVO.setAttributeValue(requestNo);
		AccountDetailsViewSDO accountDetails = lPServiceLookUp.getAccountDetails(accountDetailfromToken.getAccountId());
		Map<String, Object> notificationParams = new HashMap<String, Object>();
		notificationParams.put("accountDetails", accountDetails);
		notificationParams.put("requestNumber", requestNo);
		notificationParams.put("requestId", requestId);
		notificationParams.put("applicantId", accountDetailfromToken.getAccountId());
		notificationParams.put("applicantName", accountDetailfromToken.getFirstName());
		notificationParams.put("transactionId", paymentTransactionDetailVO.getTransactionId());
		notificationParams.put("amount", paymentTransactionDetailVO.getTransactionAmount());
		notificationParams.put("status", "29");
		
		if (serviceId.equals(ADD_LAND_REQUEST)) {

			PSResubmissonOutputVO resubmitdata = pSFindRequestService.findAddLandRequest(inputVO);
			if (!SERVICE_FAILED.equals(resubmitdata.getStatus())) {
				UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
				LandInputVO addLandRequest = PSDataMapper.setReviewDataToAddLandVO(resubmitdata, accountDetailfromToken);
				addLandRequest.setLanguageId(languageCode);
				addLandRequest.setTransactionId(paymentTransactionDetailVO.getTransactionId());
				addLandRequest.setTransationStatus(paymentTransactionDetailVO.getTransactionStatus());
				addLandRequest.setTransationAmount(paymentTransactionDetailVO.getTransactionAmount());
				if (status.equals(PROCEED_TO_REVEWER)) {
					landOutputVO = pSReSubmissionRequestService.reSubmitAddLand(user, addLandRequest);
				} else if (status.equals(TO_ISSUE_SITE_PLAN_INICEATE)) {
					landOutputVO = pSAfterServicePayment.servicePaymentAddlandRequest(user, addLandRequest);
				}
			} else {
				logger.debug("findAddLandRequest return null");
			}

		} else if (serviceId.equals(ISSUE_SITE_PLAN_DOCUMENT_REQUEST)) {

			PSResubmissonOutputVO resubmitdata = pSFindRequestService.findIssueSitePlanDocRequest(inputVO);
			if (!SERVICE_FAILED.equals(resubmitdata.getStatus())) {
				UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
				LandInputVO inputVo = PSDataMapper.settingServiceToToIssueSitePlanDocVO(resubmitdata, accountDetailfromToken);
				inputVo.setStatus("2");
				inputVo.setLanguageId(languageCode);
				inputVo.setTransactionId(paymentTransactionDetailVO.getTransactionId());
				inputVo.setTransationStatus(paymentTransactionDetailVO.getTransactionStatus());
				inputVo.setTransationAmount(paymentTransactionDetailVO.getTransactionAmount());
				landOutputVO = pSReSubmissionRequestService.reSubmitissueSitePlanDocument(user, inputVo);
			} else {
				logger.debug("findIssueSitePlanDocRequest return null");
			}

		}

		else if (serviceId.equals(LAND_DEMACRATION_REQUEST)) {

			PSResubmissonOutputVO resubmitdata = pSFindRequestService.findLandDemacration(inputVO);
			if (!SERVICE_FAILED.equals(resubmitdata.getStatus())) {
				UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
				LandInputVO inputVo = PSDataMapper.settingServiceToDemarcationVO(resubmitdata, accountDetailfromToken);
				inputVo.setLanguageId(languageCode);
				inputVo.setTransactionId(paymentTransactionDetailVO.getTransactionId());
				inputVo.setTransationStatus(paymentTransactionDetailVO.getTransactionStatus());
				inputVo.setTransationAmount(paymentTransactionDetailVO.getTransactionAmount());
				landOutputVO = pSReSubmissionRequestService.reSubmitLandDemarcationPalnDocument(user, inputVo);
			} else {
				logger.error("findLandDemacration return null");
			}

		}

		else if (serviceId.equals(ISSUE_TO_WHOME_IT_MAY_CERTIFICATE)) {

			LPtoWhomeConcernVO resubmitdata = null;
			resubmitdata = lpFindRequestService.findLpToWhomConcernView1(inputVO, languageCode);
			if (!SERVICE_FAILED.equals(resubmitdata.getStatus())) {
				UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
				WhomItmayConcernVO whomItmayConcernVO = LPDataMapper.getWhomItmayConcernVOForProceedToOpertor(resubmitdata);
				whomItmayConcernVO.setLanguageId(languageCode);
				whomItmayConcernVO.setEdirhamServCode(paymentTransactionDetailVO.getTransactionId());
				whomItmayConcernVO.setServiceFee(paymentTransactionDetailVO.getTransactionAmount());
				landOutputVO = lPReSubmissionRequestService.reSubmitWhomItMayConcern(user, whomItmayConcernVO);
			} else {
				logger.exit("findLpToWhomConcernView1 return Null");
			}
		} else if (serviceId.equals(NEW_SUPPLIER_REGISTRATION)) {

			EGDResubmissionVO eGDResubmissionVO = eGDFindRequestService.findNewSupplierRequest(inputVO, languageCode);
			if (!SERVICE_FAILED.equals(eGDResubmissionVO.getStatus())) {
				UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
				NewSupplierRegistrationVO supplierDetails = EGDDataMapper.setSupplierDataForAfterPayment(accountDetailfromToken, eGDResubmissionVO);
				supplierDetails.setLanguageId(languageCode);
				// Tranasction ID
				supplierDetails.setEdirhamServCode(paymentTransactionDetailVO.getTransactionId());
				supplierDetails.setServiceFee(paymentTransactionDetailVO.getTransactionAmount());
				landOutputVO = eGDAfterServicePayment.submitAfterPayment(user, supplierDetails);
			} else {
				logger.exit("eGDResubmissionVO return Null");
			}

		} else if (serviceId.equals(RENEW_SUPPLIER_REGISTRATION)) {
			EGDResubmissionVO eGDResubmissionVO = eGDFindRequestService.findNewSupplierRequest(inputVO, languageCode);
			if (!SERVICE_FAILED.equals(eGDResubmissionVO.getStatus())) {
				UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
				NewSupplierRegistrationVO supplierDetails = EGDDataMapper.setSupplierDataForAfterPayment(accountDetailfromToken, eGDResubmissionVO);
				supplierDetails.setServiceId("502");
				supplierDetails.setLanguageId(languageCode);
				// Tranasction ID
				supplierDetails.setEdirhamServCode(paymentTransactionDetailVO.getTransactionId());
				supplierDetails.setServiceFee(paymentTransactionDetailVO.getTransactionAmount());
				landOutputVO = eGDAfterServicePayment.submitAfterPayment(user, supplierDetails);
			} else {
				logger.exit("eGDResubmissionVO return Null");
			}

		} else if (serviceId.equals(ISSUE_NEW_PRO_REQUEST)) {
//			ApplicantRequestViewSDO applicantRequest = lPServiceLookUp.getApplicantRequestByRequestNumber(requestNo);
//			AccountDetailsViewSDO accountDetails = lPServiceLookUp.getAccountDetails(accountDetailfromToken.getAccountId());
//			String requestId = applicantRequest.getRequestId().toString();
			// int serviceIdIssUNewPro =
			// applicantRequest.getServiceId().getValue().intValue();
			Map<String, String> params = new HashMap<String, String>();
			params.put("requestId", requestId);
			params.put("requestNumber", requestNo);
			params.put("serviceId", "" + ISSUE_NEW_PRO_REQUEST);

			if (status.equals(PROCEED_TO_REVEWER)) {
				notificationParams.put("stepAction", "Applicant Fee Paymant");
				notificationParams.put("stepName", "AFTER_APP_FEES");
				notificationParams.put("status", "29");
				new ProCardIssuerServiceHandler().issueServiceRequest(accountDetails, lPServiceLookUp, languageCode, params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);
			}
			if (status.equals(PROCEED_TO_OPERATOR)) {
				notificationParams.put("stepAction", "Service Fee Paymant");
				notificationParams.put("stepName", "AFTER_SERVICE_FEES");
				notificationParams.put("status", "30");
				new ProCardIssuerServiceHandler().proceedWithServiceAfterPayment(params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);

			}
			WebServiceInvoker.sendSmsAndEMail(notificationParams);

		} else if (serviceId.equals(RENEW_PRO_REQUEST)) {
//			ApplicantRequestViewSDO applicantRequest = lPServiceLookUp.getApplicantRequestByRequestNumber(requestNo);
//			AccountDetailsViewSDO accountDetails = lPServiceLookUp.getAccountDetails(accountDetailfromToken.getAccountId());
//			String requestId = applicantRequest.getRequestId().toString();
			// int serviceIdIssUNewPro =
			// applicantRequest.getServiceId().getValue().intValue();
			Map<String, String> params = new HashMap<String, String>();
			params.put("requestId", requestId);
			params.put("requestNumber", requestNo);
			params.put("serviceId", "" + ISSUE_NEW_PRO_REQUEST);

			if (status.equals(PROCEED_TO_REVEWER)) {
				notificationParams.put("stepAction", "Applicant Fee Paymant");
				notificationParams.put("stepName", "AFTER_APP_FEES");
				notificationParams.put("status", "29");
				new ProCardRenewerServiceHandler().issueServiceRequest(accountDetails, lPServiceLookUp, languageCode, params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);
			}
			if (status.equals(PROCEED_TO_OPERATOR)) {
				notificationParams.put("stepAction", "Service Fee Paymant");
				notificationParams.put("stepName", "AFTER_SERVICE_FEES");
				notificationParams.put("status", "30");
				new ProCardRenewerServiceHandler().proceedWithServiceAfterPayment(params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);

			}
			WebServiceInvoker.sendSmsAndEMail(notificationParams);

		} else if (serviceId.equals(LAND_PROPERTY_VALUTION_REQUEST)) {
//			ApplicantRequestViewSDO applicantRequest = lPServiceLookUp.getApplicantRequestByRequestNumber(requestNo);
//			AccountDetailsViewSDO accountDetails = lPServiceLookUp.getAccountDetails(accountDetailfromToken.getAccountId());
//			String requestId = applicantRequest.getRequestId().toString();
			// int serviceIdIssUNewPro =
			// applicantRequest.getServiceId().getValue().intValue();
			Map<String, String> params = new HashMap<String, String>();
			params.put("requestId", requestId);
			params.put("requestNumber", requestNo);
			params.put("serviceId", "" + LAND_PROPERTY_VALUTION_REQUEST);

			if (status.equals(PROCEED_TO_REVEWER)) {
				notificationParams.put("stepAction", "Applicant Fee Paymant");
				notificationParams.put("stepName", "AFTER_APP_FEES");
				notificationParams.put("status", "29");
				new LandAndPropertyValuationServiceHandler().issueServiceRequest(accountDetails, lPServiceLookUp, languageCode, params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);
			}
			if (status.equals(PROCEED_TO_OPERATOR)) {
				notificationParams.put("stepAction", "Service Fee Paymant");
				notificationParams.put("stepName", "AFTER_SERVICE_FEES");
				notificationParams.put("status", "30");
				new LandAndPropertyValuationServiceHandler().proceedWithServiceAfterPayment(params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);

			}
			WebServiceInvoker.sendSmsAndEMail(notificationParams);

		} else if (serviceId.equals(NEW_REAL_ESTATE)) {
//			ApplicantRequestViewSDO applicantRequest = lPServiceLookUp.getApplicantRequestByRequestNumber(requestNo);
//			AccountDetailsViewSDO accountDetails = lPServiceLookUp.getAccountDetails(accountDetailfromToken.getAccountId());
//			String requestId = applicantRequest.getRequestId().toString();
			Map<String, String> params = new HashMap<String, String>();
			params.put("requestId", requestId);
			params.put("requestNumber", requestNo);
			params.put("serviceId", "" + NEW_REAL_ESTATE);

			if (status.equals(PROCEED_TO_REVEWER)) {
				notificationParams.put("stepAction", "Applicant Fee Paymant");
				notificationParams.put("stepName", "AFTER_APP_FEES");
				notificationParams.put("status", "29");
				new RealEstateOfficeIssuerServiceHandler().issueServiceRequest(accountDetails, lPServiceLookUp, languageCode, params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);
			}
			if (status.equals(PROCEED_TO_OPERATOR)) {
				notificationParams.put("stepAction", "Service Fee Paymant");
				notificationParams.put("stepName", "AFTER_SERVICE_FEES");
				notificationParams.put("status", "30");
				new RealEstateOfficeIssuerServiceHandler().proceedWithServiceAfterPayment(params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);

			}
			WebServiceInvoker.sendSmsAndEMail(notificationParams);

		} else if (serviceId.equals(RENEW_REAL_ESTATE)) {
			
//			ApplicantRequestViewSDO applicantRequest = lPServiceLookUp.getApplicantRequestByRequestNumber(requestNo);
//			AccountDetailsViewSDO accountDetails = lPServiceLookUp.getAccountDetails(accountDetailfromToken.getAccountId());
//			String requestId = applicantRequest.getRequestId().toString();
			Map<String, String> params = new HashMap<String, String>();
			params.put("requestId", requestId);
			params.put("requestNumber", requestNo);
			params.put("serviceId", "" + RENEW_REAL_ESTATE);

			if (status.equals(PROCEED_TO_REVEWER)) {
				notificationParams.put("stepAction", "Applicant Fee Paymant");
				notificationParams.put("stepName", "AFTER_APP_FEES");
				notificationParams.put("status", "29");
				new RealEstateOfficeRenewerServiceHandler().issueServiceRequest(accountDetails, lPServiceLookUp, languageCode, params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);
			}
			if (status.equals(PROCEED_TO_OPERATOR)) {
				notificationParams.put("stepAction", "Service Fee Paymant");
				notificationParams.put("stepName", "AFTER_SERVICE_FEES");
				notificationParams.put("status", "30");
				new RealEstateOfficeRenewerServiceHandler().proceedWithServiceAfterPayment(params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);

			}
			WebServiceInvoker.sendSmsAndEMail(notificationParams);

		} else if (serviceId.equals(GRANT_LAND_REQUEST)) {
//			ApplicantRequestViewSDO applicantRequest = lPServiceLookUp.getApplicantRequestByRequestNumber(requestNo);
//			AccountDetailsViewSDO accountDetails = lPServiceLookUp.getAccountDetails(accountDetailfromToken.getAccountId());
//			String requestId = applicantRequest.getRequestId().toString();
			Map<String, String> params = new HashMap<String, String>();
			params.put("requestId", requestId);
			params.put("requestNumber", requestNo);
			params.put("serviceId", "" + GRANT_LAND_REQUEST);

			if (status.equals(PROCEED_TO_REVEWER)) {
				notificationParams.put("stepAction", "Applicant Fee Paymant");
				notificationParams.put("stepName", "AFTER_APP_FEES");
				notificationParams.put("status", "29");
				new GrantLandRequestIssuerServiceHandler().issueServiceRequest(accountDetails, lPServiceLookUp, languageCode, params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);
			}
			if (status.equals(SERVICE_PAYMENT_SUCCESS)) {
				notificationParams.put("stepAction", "Service Fee Paymant");
				notificationParams.put("stepName", "AFTER_SERVICE_FEES");
				notificationParams.put("status", "30");
				new GrantLandRequestIssuerServiceHandler().proceedWithServiceAfterPayment(params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);

			}
			WebServiceInvoker.sendSmsAndEMail(notificationParams);

		} else if (serviceId.equals(GRANT_LAND_EXCEPTION_REQUEST)) {
//			ApplicantRequestViewSDO applicantRequest = lPServiceLookUp.getApplicantRequestByRequestNumber(requestNo);
//			AccountDetailsViewSDO accountDetails = lPServiceLookUp.getAccountDetails(accountDetailfromToken.getAccountId());
//			String requestId = applicantRequest.getRequestId().toString();
			Map<String, String> params = new HashMap<String, String>();
			params.put("requestId", requestId);
			params.put("requestNumber", requestNo);
			params.put("serviceId", "" + GRANT_LAND_REQUEST);

//			if (status.equals(PROCEED_TO_REVEWER)) {
//				notificationParams.put("stepAction", "Applicant Fee Paymant");
//				notificationParams.put("stepName", "AFTER_APP_FEES");
//				notificationParams.put("status", "29");
//				new GrantLandRequestIssuerServiceHandler().issueServiceRequest(accountDetails, lPServiceLookUp, languageCode, params);
//				landOutputVO.setStatus(SERVICE_SUCCESS);
//				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
//				landOutputVO.setStatus_EN(message);
//				landOutputVO.setStatus_AR(message);
//			}
			if (status.equals(SERVICE_PAYMENT_SUCCESS)) {
				notificationParams.put("stepAction", "Service Fee Paymant");
				notificationParams.put("stepName", "AFTER_SERVICE_FEES");
				notificationParams.put("status", "30");
				new GrantLandRequestExceptionServiceHandler().proceedWithServiceAfterPayment(params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);

			}
			WebServiceInvoker.sendSmsAndEMail(notificationParams);

		} else if (serviceId.equals(LOST_DOCUMENT)) {
//			ApplicantRequestViewSDO applicantRequest = lPServiceLookUp.getApplicantRequestByRequestNumber(requestNo);
//			AccountDetailsViewSDO accountDetails = lPServiceLookUp.getAccountDetails(accountDetailfromToken.getAccountId());
//			String requestId = applicantRequest.getRequestId().toString();
			Map<String, String> params = new HashMap<String, String>();
			params.put("requestId", requestId);
			params.put("requestNumber", requestNo);
			params.put("serviceId", "" + LOST_DOCUMENT);

			if (status.equals(PROCEED_TO_REVEWER)) {
				notificationParams.put("stepAction", "Applicant Fee Paymant");
				notificationParams.put("stepName", "AFTER_APP_FEES");
				notificationParams.put("status", "29");
				new LostDocumentServiceHandler().issueServiceRequest(accountDetails, lPServiceLookUp, languageCode, params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);
			}
			if (status.equals(SERVICE_PAYMENT_SUCCESS)) {
				notificationParams.put("stepAction", "Service Fee Paymant");
				notificationParams.put("stepName", "AFTER_SERVICE_FEES");
				notificationParams.put("status", "30");
				new LostDocumentServiceHandler().proceedWithServiceAfterPayment(params);
				landOutputVO.setStatus(SERVICE_SUCCESS);
				String message = messageSource.getMessage("label.paymentCompletedSuccessfully", new String[] { requestNo }, new Locale(languageCode));
				landOutputVO.setStatus_EN(message);
				landOutputVO.setStatus_AR(message);

			}
			WebServiceInvoker.sendSmsAndEMail(notificationParams);

		} else if (serviceId.equals(EXTENTION_OF_GRANT_LAND_REQUEST)) {

			PSResubmissonOutputVO resubmitdata = pSFindRequestService.findExtentionGrandLandRequest(inputVO);

			if (!SERVICE_FAILED.equals(resubmitdata.getStatus())) {
				UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
				LandInputVO addLandRequest = PSDataMapper.setReviewDataToExtentionGrandLandVO(resubmitdata, accountDetailfromToken);
				addLandRequest.setLanguageId(languageCode);
				addLandRequest.setTransactionId(paymentTransactionDetailVO.getTransactionId());
				addLandRequest.setTransationStatus(paymentTransactionDetailVO.getTransactionStatus());
				addLandRequest.setTransationAmount(paymentTransactionDetailVO.getTransactionAmount());
				landOutputVO = pSAfterServicePayment.servicePaymentExtensionGrandLandRequest(user, addLandRequest);
			} else {
				logger.exit("findExtentionGrandLandRequest return Null");
			}
		} else {
			logger.debug("Invalid Status");

		}

		return landOutputVO;

	}
}
