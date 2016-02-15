package com.uaq.service;

import java.text.ParseException;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;

import com.uaq.common.ServiceNameConstant;
import com.uaq.controller.mapper.PSDataMapper;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;
import com.uaq.service.mapper.ReviewerServiceBrokenTranasactionDataMapper;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LandInputVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.NewSupplierRegistrationVO;
import com.uaq.vo.PSResubmissonOutputVO;
import com.uaq.vo.PaymentTransactionDetailVO;
import com.uaq.vo.ReSubmiisionInputVO;
import com.uaq.vo.UserDeatilVO;
import com.uaq.vo.WhomItmayConcernVO;

import static com.uaq.common.ApplicationConstants.SERVICE_FAILED;
import static com.uaq.common.ServiceNameConstant.*;
import static com.uaq.common.StatusNameConstant.*;
import static com.uaq.common.WebServiceConstant.*;

public class ReviewerServiceBrokenTracaction {

	private static transient UAQLogger logger = new UAQLogger(ReviewerServiceBrokenTracaction.class);

	private PSReSubmissionRequestService pSReSubmissionRequestService;

	private PSAfterServicePayment pSAfterServicePayment;

	private LPReSubmissionRequestService lPReSubmissionRequestService;

	private EGDAfterServicePayment eGDAfterServicePayment;
	
	
	private PSFindRequestService pSFindRequestService;

	public LandOutputVO invokeRevierIneceator(AccountDetailTokenOutputVO accountDetailfromToken, PaymentTransactionDetailVO paymentTransactionDetailVO) throws DatatypeConfigurationException, ParseException {

		LandOutputVO landOutputVO = new LandOutputVO();

		pSReSubmissionRequestService = new PSReSubmissionRequestService();
		pSAfterServicePayment = new PSAfterServicePayment();
		lPReSubmissionRequestService = new LPReSubmissionRequestService();
		eGDAfterServicePayment = new EGDAfterServicePayment();
		pSFindRequestService =new PSFindRequestService();

		String requestNo = paymentTransactionDetailVO.getRequestNo();
		String serviceId = paymentTransactionDetailVO.getServiceId();
		String languageCode = accountDetailfromToken.getLanguageId();
		String status = paymentTransactionDetailVO.getStatusId();
		
		logger.debug("Request NO-"+requestNo+" | serviceId - "+serviceId+" | languageCode -"+languageCode + "  | status"+status);

		ReSubmiisionInputVO inputVO = new ReSubmiisionInputVO();
		inputVO.setAttributeName(SOAP_REQUESTNO_ARGUMENT);
		inputVO.setAttributeValue(requestNo);

		if (serviceId.equals(ADD_LAND_REQUEST)) {
			logger.enter(ADD_LAND_REQUEST);
			UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
			LandInputVO addLandRequest = ReviewerServiceBrokenTranasactionDataMapper.setReviewDataToAddLandVO(accountDetailfromToken, requestNo);
			addLandRequest.setLanguageId(languageCode);
			addLandRequest.setTransactionId(paymentTransactionDetailVO.getTransactionId());
			addLandRequest.setTransationStatus(paymentTransactionDetailVO.getTransactionStatus());
			addLandRequest.setTransationAmount(paymentTransactionDetailVO.getTransactionAmount());
			if (status.equals(PROCEED_TO_REVEWER)) {
				landOutputVO = pSReSubmissionRequestService.reSubmitAddLand(user, addLandRequest);
			} else if (status.equals(TO_ISSUE_SITE_PLAN_INICEATE)) {
				landOutputVO = pSAfterServicePayment.servicePaymentAddlandRequest(user, addLandRequest);
			}
			logger.exit(ADD_LAND_REQUEST);
		} else if (serviceId.equals(ISSUE_SITE_PLAN_DOCUMENT_REQUEST)) {
			logger.enter(ISSUE_SITE_PLAN_DOCUMENT_REQUEST);
			UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
			LandInputVO inputVo = ReviewerServiceBrokenTranasactionDataMapper.settingServiceToToIssueSitePlanDocVO(accountDetailfromToken, requestNo);
			inputVo.setStatus("2");
			inputVo.setLanguageId(languageCode);
			inputVo.setTransactionId(paymentTransactionDetailVO.getTransactionId());
			inputVo.setTransationStatus(paymentTransactionDetailVO.getTransactionStatus());
			inputVo.setTransationAmount(paymentTransactionDetailVO.getTransactionAmount());
			landOutputVO = pSReSubmissionRequestService.reSubmitissueSitePlanDocument(user, inputVo);
			logger.exit(ISSUE_SITE_PLAN_DOCUMENT_REQUEST);
		} else if (serviceId.equals(LAND_DEMACRATION_REQUEST)) {
			logger.enter(LAND_DEMACRATION_REQUEST);
			UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
			LandInputVO inputVo = ReviewerServiceBrokenTranasactionDataMapper.settingServiceToDemarcationVO(accountDetailfromToken, requestNo);
			inputVo.setLanguageId(languageCode);
			inputVo.setTransactionId(paymentTransactionDetailVO.getTransactionId());
			inputVo.setTransationStatus(paymentTransactionDetailVO.getTransactionStatus());
			inputVo.setTransationAmount(paymentTransactionDetailVO.getTransactionAmount());
			landOutputVO = pSReSubmissionRequestService.reSubmitLandDemarcationPalnDocument(user, inputVo);
			logger.exit(LAND_DEMACRATION_REQUEST);
		}else if (serviceId.equals(NEW_SUPPLIER_REGISTRATION)) {
			logger.enter(NEW_SUPPLIER_REGISTRATION);
			UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
			NewSupplierRegistrationVO supplierDetails = ReviewerServiceBrokenTranasactionDataMapper.setSupplierDataForAfterPayment(accountDetailfromToken, requestNo);
			supplierDetails.setLanguageId(languageCode);
			// Tranasction ID
			supplierDetails.setEdirhamServCode(paymentTransactionDetailVO.getTransactionId());
			supplierDetails.setServiceFee(paymentTransactionDetailVO.getTransactionAmount());
			landOutputVO = eGDAfterServicePayment.submitAfterPayment(user, supplierDetails);
			logger.exit(NEW_SUPPLIER_REGISTRATION);
		} else if (serviceId.equals(RENEW_SUPPLIER_REGISTRATION)) {
			logger.enter(RENEW_SUPPLIER_REGISTRATION);
			UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
			NewSupplierRegistrationVO supplierDetails = ReviewerServiceBrokenTranasactionDataMapper.setSupplierDataForAfterPayment(accountDetailfromToken, requestNo);
			supplierDetails.setLanguageId(languageCode);
			// Tranasction ID
			supplierDetails.setEdirhamServCode(paymentTransactionDetailVO.getTransactionId());
			supplierDetails.setServiceFee(paymentTransactionDetailVO.getTransactionAmount());
			landOutputVO = eGDAfterServicePayment.submitAfterPayment(user, supplierDetails);
			logger.exit(RENEW_SUPPLIER_REGISTRATION);
		} else if(serviceId.equals(ServiceNameConstant.ISSUE_TO_WHOME_IT_MAY_CERTIFICATE)){
			logger.enter(ISSUE_TO_WHOME_IT_MAY_CERTIFICATE);
			UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
			WhomItmayConcernVO whomItmayConcernVO = ReviewerServiceBrokenTranasactionDataMapper.getWhomItmayConcernVOForProceedToOperator(accountDetailfromToken,requestNo);
			whomItmayConcernVO.setLanguageId(languageCode);
			whomItmayConcernVO.setEdirhamServCode(paymentTransactionDetailVO.getTransactionId());
			whomItmayConcernVO.setServiceFee(paymentTransactionDetailVO.getTransactionAmount());
			landOutputVO = lPReSubmissionRequestService.reSubmitWhomItMayConcern(user, whomItmayConcernVO);
			logger.exit(ISSUE_TO_WHOME_IT_MAY_CERTIFICATE);
		}else if (serviceId.equals(EXTENTION_OF_GRANT_LAND_REQUEST)) {

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
