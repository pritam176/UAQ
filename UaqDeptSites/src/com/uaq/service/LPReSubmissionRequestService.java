/**
 * 
 */
package com.uaq.service;

import static com.uaq.common.ApplicationConstants.SERVICE_FAILED;

import java.math.BigInteger;

import javax.xml.ws.Holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.AttachmentListPayload;
import com.uaq.soap.warpper.WebServiceWarpper;
import com.uaq.util.StringUtil;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.UserDeatilVO;
import com.uaq.vo.WhomItmayConcernVO;

/**
 * @author TACME
 * 
 */
@Service
@Qualifier("lpReSubmissionRequestService")
public class LPReSubmissionRequestService {

	protected static UAQLogger logger = new UAQLogger(LPReSubmissionRequestService.class);

	@Autowired
	private UCMCenterURLService uCMCenterURLService;

	/**
	 * This Method is responsible for after Service Payment the request &
	 * Resubmit
	 * 
	 * http://u01vusoa01.uaqgov.ae:8001/soa-infra/services/LP_Department/
	 * UAQ_LP_Twimc_Prj/uaq_lp_towhom_reviewerforout_client_ep?WSDL JAR-UAQProxy
	 * 
	 * @author Pritam
	 * 
	 */

	public LandOutputVO afterPaymentWhomItMayConcern(UserDeatilVO userDeatilVO, WhomItmayConcernVO whomItmayConcern) {

		LandOutputVO outputVO = new LandOutputVO();

		com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AddressToDetailsType address = new com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AddressToDetailsType();
		address.setEmiratesId(userDeatilVO.getEmiratesId());
		address.setAddressTo(whomItmayConcern.getAddressTo());
		address.setFamilyBookNo(whomItmayConcern.getFamilyBookNo());
		address.setSpauseId(whomItmayConcern.getSpouseIdNo());
		address.setSource(whomItmayConcern.getSource() == null ? new BigInteger("1") : new BigInteger(whomItmayConcern.getSource()));
		address.setServiceId((whomItmayConcern.getServiceId() == null || whomItmayConcern.getServiceId().equalsIgnoreCase("null")) ? new BigInteger("0") : new BigInteger(whomItmayConcern
				.getServiceId()));
		address.setUserCommnets(whomItmayConcern.getUserCommnets());

		address.setAttachment1(whomItmayConcern.getScanFamilyBook() == null ? "" : whomItmayConcern.getScanFamilyBook().replace("-", "|"));
		address.setAttachment2(whomItmayConcern.getSposeEmiratesId() == null ? "" : whomItmayConcern.getSposeEmiratesId().replace("-", "|"));
		address.setAttachment3("");
		// address.setAttachment4(whomItmayConcern.getOther());
		address.setUserCommnets("");

		String did = "";
		String filenme = "";
		String ucmUrl = "";

		com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AttachmentListPayload attachmentList = new com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AttachmentListPayload();

		if (!StringUtil.isEmpty(whomItmayConcern.getScanFamilyBook())) {
			did = whomItmayConcern.getScanFamilyBook().split("-")[0];
			filenme = whomItmayConcern.getScanFamilyBook().split("-")[1];
			logger.debug("File filenme=" + filenme);
			logger.debug("File did=" + did);

			ucmUrl = uCMCenterURLService.getWebCenterURLofFile(did);
			logger.debug("From WebCenter URL=" + ucmUrl);

			com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AttachmentRecPayload attachmentRec = new com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AttachmentRecPayload();
			attachmentRec.setContentid(did);
			attachmentRec.setFilename(filenme);
			attachmentRec.setUrl(ucmUrl);
			attachmentRec.setIsMandatory("1");
			attachmentRec.setDocTypeId("20");
			attachmentList.getAttachmentRec().add(attachmentRec);
		}

		if (!StringUtil.isEmpty(whomItmayConcern.getSposeEmiratesId())) {
			did = whomItmayConcern.getSposeEmiratesId().split("-")[0];
			filenme = whomItmayConcern.getSposeEmiratesId().split("-")[1];
			logger.debug("File filenme=" + filenme);
			logger.debug("File did=" + did);

			ucmUrl = uCMCenterURLService.getWebCenterURLofFile(did);
			logger.debug("From WebCenter URL=" + ucmUrl);
			com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AttachmentRecPayload attachmentRec = new com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AttachmentRecPayload();
			attachmentRec.setContentid(did);
			attachmentRec.setFilename(filenme);
			attachmentRec.setUrl(ucmUrl);
			attachmentRec.setIsMandatory("1");
			attachmentRec.setDocTypeId("21");
			attachmentList.getAttachmentRec().add(attachmentRec);
		}

		com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.CommonRequestBPMType commonBpm = new com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.CommonRequestBPMType();

		commonBpm.setRequestId(whomItmayConcern.getRequestId() == null ? new BigInteger("0") : new BigInteger(whomItmayConcern.getRequestId().toString()));
		commonBpm.setRequestNo(whomItmayConcern.getRequestNo());
		commonBpm.setWorkListId(whomItmayConcern.getWorkListId() == null ? new BigInteger("0") : new BigInteger(whomItmayConcern.getWorkListId().toString()));
		commonBpm.setStateId(whomItmayConcern.getStateId());
		commonBpm.setToWhomItmayId(whomItmayConcern.getToWhomItmayId() == null ? new BigInteger("0") : new BigInteger(whomItmayConcern.getToWhomItmayId().toString()));
		commonBpm.setTempToWhomItmayId(whomItmayConcern.getTempToWhomItmayId() == null ? new BigInteger("0") : new BigInteger(whomItmayConcern.getTempToWhomItmayId().toString()));
		commonBpm.setServiceNameEn(whomItmayConcern.getServiceName_En());
		commonBpm.setServiceNameAr(whomItmayConcern.getServiceName_Ar());

		com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.PaymentRequestType paymentDetails = new com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.PaymentRequestType();

		paymentDetails.setPaymentId(whomItmayConcern.getPaymentId());
		paymentDetails.setPaymentStatus(whomItmayConcern.getPaymentStatus());
		paymentDetails.setServiceFee(whomItmayConcern.getServiceFee());
		paymentDetails.setApplicationFee(whomItmayConcern.getApplicationFee());
		paymentDetails.setDeptID(whomItmayConcern.getDeptID());
		paymentDetails.setServiceFeeDisc(whomItmayConcern.getServiceFeeDisc());
		paymentDetails.setAppFeeDisc(whomItmayConcern.getAppFeeDisc());
		paymentDetails.setEdirhamServCode(whomItmayConcern.getEdirhamServCode());
		paymentDetails.setIsPaymentRequired(whomItmayConcern.getIsPaymentRequired());

		com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.UserDetailsPayload userDetails = new com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.UserDetailsPayload();

		userDetails.setUsername(userDeatilVO.getUsername());
		userDetails.setTypeOfUser(userDeatilVO.getTypeOfUser());
		userDetails.setAccountid(userDeatilVO.getAccountid());
		userDetails.setMobileNo(userDeatilVO.getMobileNo());
		userDetails.setEmailID(userDeatilVO.getEmailID());
		userDetails.setEmiratesId(userDeatilVO.getEmiratesId());
		userDetails.setTradeLienceNo(userDeatilVO.getTradeLienceNo());
		userDetails.setFamilyBookNo(whomItmayConcern.getFamilyBookNo());
		// BigInteger nationalId=new
		// BigInteger(StringUtil.isEmpty(userDeatilVO.getPOBOX())?"0":userDeatilVO.getPOBOX());
		userDetails.setNationalityID(StringUtil.isEmpty(userDeatilVO.getNationality()) ? new BigInteger("0") : new BigInteger(userDeatilVO.getNationality()));
		userDetails.setEmirate(userDeatilVO.getEmirate());
		userDetails.setFirstName(userDeatilVO.getFirstName());
		userDetails.setLastName(userDeatilVO.getLastName());
		userDetails.setAddress1(userDeatilVO.getAddress1());
		userDetails.setAddress2(whomItmayConcern.getOther());
		BigInteger postbox = new BigInteger(StringUtil.isEmpty(userDeatilVO.getPOBOX()) ? "0" : userDeatilVO.getPOBOX());
		userDetails.setPOBOX(postbox);
		userDetails.setDOB(null);
		userDetails.setHomePhone(userDeatilVO.getHomePhone());
		userDetails.setLanguageID(whomItmayConcern.getLanguageId() == null ? new BigInteger("1") : new BigInteger(whomItmayConcern.getLanguageId()));
		userDetails.setEiratesCode(StringUtil.isEmpty(userDeatilVO.getEmiratesCode()) ? new BigInteger("0") : new BigInteger(userDeatilVO.getEmiratesCode()));
		/*
		 * if (userDeatilVO.getEdiaExpirtyDate() != null) { try {
		 * userDetails.setEDIA_Expirty_Date
		 * (DateUtil.getDateFromString(userDeatilVO.getEdiaExpirtyDate())); }
		 * catch (ParseException e) { logger.error(e.getMessage()); } }
		 */

		Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AddressToDetailsType> addressToDetailsH = new Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AddressToDetailsType>(
				address);
		Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.UserDetailsPayload> userDetailsH = new Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.UserDetailsPayload>(
				userDetails);
		Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AttachmentListPayload> attachmentListH = new Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AttachmentListPayload>(
				attachmentList);
		Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.CommonRequestBPMType> commonRequestBPM = new Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.CommonRequestBPMType>(
				commonBpm);

		com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.CommentsListPayload comments = new com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.CommentsListPayload();
		com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.RegistrationAttachmentsListType registrationAtt = new com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.RegistrationAttachmentsListType();
		Holder<String> statusEN = new Holder<String>();
		Holder<String> statusAR = new Holder<String>();
		Holder<String> status = new Holder<String>();
		Holder<String> reviewerComments = new Holder<String>();
		Holder<String> loginUserName = new Holder<String>();
		try {
			new WebServiceWarpper().afterSevicePayment(addressToDetailsH, userDetailsH, attachmentListH, commonRequestBPM, paymentDetails, comments, registrationAtt, statusEN, statusAR, status,
					reviewerComments, loginUserName, PropertiesUtil.getProperty("SOA_URL_LP_TWME_FOROUT"), PropertiesUtil.getProperty("SOA_USERNAME"), PropertiesUtil.getProperty("SOA_PASSWORD"));
		} catch (Exception e) {
			outputVO.setStatus(SERVICE_FAILED);
			logger.error("Failed :" + e.getMessage());
		}

		outputVO.setStatus_AR(statusAR.value);
		outputVO.setStatus_EN(statusEN.value);
		logger.debug("LP review Msg is :: " + status.value);
		logger.debug("LP review Msg is :: " + statusEN.value);
		outputVO.setStatus(StringUtil.isEmpty(status.value) ? SERVICE_FAILED : status.value);

		return outputVO;

	}

}
