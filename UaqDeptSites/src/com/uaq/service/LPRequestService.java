package com.uaq.service;

import java.math.BigInteger;
import java.text.ParseException;

import javax.xml.ws.Holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.AttachmentListPayload;
import com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.AttachmentRecPayload;
import com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.CommentsListPayload;
import com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.RegistrationAttachmentsListType;
import com.uaq.soap.warpper.WebServiceWarpper;
import com.uaq.util.StringUtil;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.UserDeatilVO;
import com.uaq.vo.WhomItmayConcernVO;

@Service("lPRequestService")
public class LPRequestService {
	protected static UAQLogger logger = new UAQLogger(LPRequestService.class);

	@Autowired
	private UCMCenterURLService uCMCenterURLService;

	public LandOutputVO submitWhomItMayConcern(UserDeatilVO userDeatilVO, WhomItmayConcernVO whomItmayConcern) throws ParseException {

		LandOutputVO outputVO = new LandOutputVO();

		com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.AddressToDetailsType address = new com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.AddressToDetailsType();
		address.setEmiratesId(userDeatilVO.getEmiratesId());
		address.setAddressTo(whomItmayConcern.getAddressTo());
		address.setFamilyBookNo(whomItmayConcern.getFamilyBookNo());
		address.setSpauseId(whomItmayConcern.getSpouseIdNo());
		address.setSource(whomItmayConcern.getSource() == null ? new BigInteger("0") : new BigInteger(whomItmayConcern.getSource()));
		address.setServiceId(whomItmayConcern.getServiceId() == null ? new BigInteger("0") : new BigInteger(whomItmayConcern.getServiceId()));
		address.setUserCommnets(whomItmayConcern.getUserCommnets());

		
		
		address.setAttachment1(whomItmayConcern.getScanFamilyBook().replace("-", "|"));
		address.setAttachment2(whomItmayConcern.getSposeEmiratesId().replace("-", "|"));
		address.setAttachment3("");
		// address.setAttachment4(whomItmayConcern.getOther());

		com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.CommonRequestBPMType commonBpm = new com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.CommonRequestBPMType();

		commonBpm.setRequestId(whomItmayConcern.getRequestId() == null ? new BigInteger("0") : new BigInteger(whomItmayConcern.getRequestId().toString()));
		commonBpm.setRequestNo(whomItmayConcern.getRequestNo());
		commonBpm.setWorkListId(whomItmayConcern.getWorkListId() == null ? new BigInteger("0") : new BigInteger(whomItmayConcern.getWorkListId().toString()));
		commonBpm.setStateId(whomItmayConcern.getStateId());// Submit
		commonBpm.setToWhomItmayId(whomItmayConcern.getToWhomItmayId() == null ? new BigInteger("0") : new BigInteger(whomItmayConcern.getToWhomItmayId().toString()));
		commonBpm.setTempToWhomItmayId(whomItmayConcern.getTempToWhomItmayId() == null ? new BigInteger("0") : new BigInteger(whomItmayConcern.getTempToWhomItmayId().toString()));
		commonBpm.setServiceNameEn(whomItmayConcern.getServiceName_En());
		commonBpm.setServiceNameAr(whomItmayConcern.getServiceName_Ar());

		com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.PaymentRequestType paymentDetails = new com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.PaymentRequestType();

		paymentDetails.setPaymentId(whomItmayConcern.getPaymentId());
		paymentDetails.setPaymentStatus(whomItmayConcern.getPaymentStatus());
		paymentDetails.setServiceFee(whomItmayConcern.getServiceFee());
		paymentDetails.setApplicationFee(whomItmayConcern.getApplicationFee());
		paymentDetails.setDeptID(whomItmayConcern.getDeptID());
		paymentDetails.setServiceFeeDisc(whomItmayConcern.getServiceFeeDisc());
		paymentDetails.setAppFeeDisc(whomItmayConcern.getAppFeeDisc());
		paymentDetails.setEdirhamServCode(whomItmayConcern.getEdirhamServCode());
		paymentDetails.setIsPaymentRequired(whomItmayConcern.getIsPaymentRequired());

		com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.UserDetailsPayload userDetails = new com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.UserDetailsPayload();

		userDetails.setUsername(userDeatilVO.getUsername());
		userDetails.setTypeOfUser(userDeatilVO.getTypeOfUser());
		userDetails.setAccountid(userDeatilVO.getAccountid());
		userDetails.setMobileNo(userDeatilVO.getMobileNo());
		userDetails.setEmailID(userDeatilVO.getEmailID());
		userDetails.setEmiratesId(userDeatilVO.getEmiratesId());
		userDetails.setTradeLienceNo(userDeatilVO.getTradeLienceNo());
		userDetails.setFamilyBookNo(whomItmayConcern.getFamilyBookNo());
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
		userDetails.setLanguageID(new BigInteger(whomItmayConcern.getLanguageId()));
		// userDetails.setEirates_Code((userDeatilVO.getEmiratesCode().toBigInteger()));
		userDetails.setEiratesCode(StringUtil.isEmpty(userDeatilVO.getEmiratesCode()) ? new BigInteger("1") : new BigInteger(userDeatilVO.getEmiratesCode()));
		userDetails.setAddress2(whomItmayConcern.getOther());
		if (userDeatilVO.getEdiaExpirtyDate() != null) {
			userDetails.setEDIAExpirtyDate(null);
		}

		String did = "";
		String filenme = "";
		String ucmUrl = "";

		com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.AttachmentListPayload attachmentList = new AttachmentListPayload();

		if (!StringUtil.isEmpty(whomItmayConcern.getScanFamilyBook())) {
			did = whomItmayConcern.getScanFamilyBook().split("-")[0];
			filenme = whomItmayConcern.getScanFamilyBook().split("-")[1];
			logger.debug("File filenme=" + filenme);
			logger.debug("File did=" + did);

			ucmUrl = uCMCenterURLService.getWebCenterURLofFile(did);
			logger.debug("From WebCenter URL=" + ucmUrl);

			com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.AttachmentRecPayload atachRec = new AttachmentRecPayload();
			atachRec.setContentid(did);
			atachRec.setFilename(filenme);
			atachRec.setUrl(ucmUrl);
			atachRec.setIsMandatory("1");
			atachRec.setDocTypeId("20");
			attachmentList.getAttachmentRec().add(atachRec);
		}

		if (!StringUtil.isEmpty(whomItmayConcern.getSposeEmiratesId())) {
			did = whomItmayConcern.getSposeEmiratesId().split("-")[0];
			filenme = whomItmayConcern.getSposeEmiratesId().split("-")[1];
			logger.debug("File filenme=" + filenme);
			logger.debug("File did=" + did);

			ucmUrl = uCMCenterURLService.getWebCenterURLofFile(did);
			logger.debug("From WebCenter URL=" + ucmUrl);
			com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.AttachmentRecPayload atachRec = new AttachmentRecPayload();
			atachRec.setContentid(did);
			atachRec.setFilename(filenme);
			atachRec.setUrl(ucmUrl);
			atachRec.setIsMandatory("1");
			atachRec.setDocTypeId("21");
			attachmentList.getAttachmentRec().add(atachRec);
		}

		Holder<com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.AddressToDetailsType> addressToDetailsH = new Holder<com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.AddressToDetailsType>(address);
		Holder<com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.UserDetailsPayload> userDetailsH = new Holder<com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.UserDetailsPayload>(userDetails);
		Holder<com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.AttachmentListPayload> attachmentListH = new Holder<com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.AttachmentListPayload>(
				attachmentList);
		Holder<com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.CommonRequestBPMType> commonRequestBPM = new Holder<com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.CommonRequestBPMType>(commonBpm);

		com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.CommentsListPayload comments = new CommentsListPayload();
		com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.RegistrationAttachmentsListType registrationAtt = new RegistrationAttachmentsListType();
		Holder<String> statusEN = new Holder<String>();
		Holder<String> statusAR = new Holder<String>();
		Holder<String> status = new Holder<String>();
		Holder<String> reviewerComments = new Holder<String>();
		Holder<String> loginUserName = new Holder<String>();

		try {
			new WebServiceWarpper().submitTowhomeEver(addressToDetailsH, userDetailsH, attachmentListH, commonRequestBPM, paymentDetails, comments, registrationAtt, statusEN, statusAR, status,
					reviewerComments, loginUserName, PropertiesUtil.getProperty("SOA_URL_LP_TWME"), PropertiesUtil.getProperty("SOA_USERNAME"), PropertiesUtil.getProperty("SOA_PASSWORD"));

			outputVO.setSatausId(status.value);
			outputVO.setRequestNo(commonRequestBPM.value.getRequestNo());
			// Service ID is not in the response. for now setting service ID
			// from Inputpayload
			outputVO.setServiceId(address.getServiceId().toString());
			outputVO.setStatus_AR(statusAR.value);
			outputVO.setStatus_EN(statusEN.value);
			outputVO.setStatus(status.value);

		} catch (Exception e) {
			outputVO.setStatus("Failed");
			logger.error("Service Exception for service toWhomeEverSubmitRequest ::" + e.getMessage());
		}

		return outputVO;

	}

}
