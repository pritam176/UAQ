package com.uaq.service;

import static com.uaq.common.ApplicationConstants.SERVICE_FAILED;

import java.math.BigInteger;
import java.util.List;

import javax.xml.ws.Holder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uaq.common.PropertiesUtil;
import com.uaq.dao.ServiceAttachmentDAO;
import com.uaq.logger.UAQLogger;
import com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.CommentsListPayload;
import com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.RegistrationAttachmentsListType;
import com.uaq.soap.warpper.WebServiceWarpper;
import com.uaq.vo.AttachmentVO;
import com.uaq.vo.LPtoWhomeConcernVO;
import com.uaq.vo.ReSubmiisionInputVO;

@Service
@Qualifier("lpFindRequestService")
public class LPFindRequestService {

	protected static UAQLogger logger = new UAQLogger(LPFindRequestService.class);
	
	@Autowired
	private ServiceAttachmentDAO serviceAttachmentDAO;

	/**
	 * This Method is responsible for findToWhomConcernRequest request Resubmit
	 * the request
	 * 
	 * http://u01vusoa01.uaqgov.ae:8001/soa-infra/services/LP_Department/UAQ_LP_Twimc_Prj/uaq_lp_towhom_reviewerforout_client_ep?WSDL
	 *  JAR-UAQProxy
	 * 
	 * @author Pritam
	 * 
	 */
	public LPtoWhomeConcernVO getToWhomConcernRequest(ReSubmiisionInputVO input, String languageCode) {

		

		com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.CommonRequestBPMType commonBpm = new com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.CommonRequestBPMType();

		commonBpm.setRequestId(new BigInteger(input.getAttributeValue().split("-")[3]));
		commonBpm.setRequestNo(input.getAttributeValue());
		commonBpm.setWorkListId(new BigInteger("0"));
		commonBpm.setStateId(3);// for Get Detail
		commonBpm.setToWhomItmayId(new BigInteger("0"));
		commonBpm.setTempToWhomItmayId(new BigInteger("0"));
		commonBpm.setServiceNameEn("");
		commonBpm.setServiceNameAr("");

		com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.PaymentRequestType paymentDetails = new com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.PaymentRequestType();

		paymentDetails.setPaymentId(new Integer(0));
		paymentDetails.setPaymentStatus("");
		paymentDetails.setServiceFee("");
		paymentDetails.setApplicationFee("");
		paymentDetails.setDeptID("");
		paymentDetails.setServiceFeeDisc("");
		paymentDetails.setAppFeeDisc("");
		paymentDetails.setEdirhamServCode("");
		paymentDetails.setIsPaymentRequired("");
		
		/*

		com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.UserDetailsPayload userDetails = new com.uaq.proxies.uaq_lp_twimc_bpel_client_ep.types.UserDetailsPayload();

		userDetails.setUsername("");
		userDetails.setTypeOfUser("");
		userDetails.setAccountid("");
		userDetails.setMobileNo("");
		userDetails.setEmailID("");
		userDetails.setEmiratesId("");
		userDetails.setTradeLienceNo("");
		userDetails.setFamilyBookNo("");
		userDetails.setNationalityID(new BigInteger("0"));
		userDetails.setEmirate("");
		userDetails.setFirstName("");
		userDetails.setLastName("");
		userDetails.setAddress1("");
		userDetails.setAddress2("");
		userDetails.setPOBOX(new BigInteger("0"));
		userDetails.setDOB(null);
		userDetails.setHomePhone("");
		userDetails.setLanguageID(new BigInteger(PortalDataMapper.getLanguageId(languageCode)));
		userDetails.setEiratesCode(new BigInteger("0"));

		userDetails.setEDIAExpirtyDate(null);*/

		Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AddressToDetailsType> addressToDetailsH = new Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AddressToDetailsType>();
		Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.UserDetailsPayload> userDetailsH = new Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.UserDetailsPayload>();
		Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AttachmentListPayload> attachmentList = new Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.AttachmentListPayload>();
		Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.CommonRequestBPMType> commonRequestBPM = new Holder<com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.CommonRequestBPMType>(commonBpm);

		com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.CommentsListPayload comments = new CommentsListPayload();
		com.uaq.proxies.uaq_lp_towhom_reviewerforout_client_ep.types.RegistrationAttachmentsListType registrationAtt = new RegistrationAttachmentsListType();
		Holder<String> statusEN = new Holder<String>();
		Holder<String> statusAR = new Holder<String>();
		Holder<String> status = new Holder<String>();
		Holder<String> reviewerComments = new Holder<String>();
		Holder<String> loginUserName = new Holder<String>();

		LPtoWhomeConcernVO outputVO = new LPtoWhomeConcernVO();

		try {
			new WebServiceWarpper().afterSevicePayment(addressToDetailsH, userDetailsH, attachmentList, commonRequestBPM, paymentDetails, comments, registrationAtt, statusEN, statusAR, status,
					reviewerComments, loginUserName, PropertiesUtil.getProperty("SOA_URL_LP_TWME_FOROUT"), PropertiesUtil.getProperty("SOA_USERNAME"), PropertiesUtil.getProperty("SOA_PASSWORD"));
			outputVO.setAddressedtoId(addressToDetailsH.value.getAddressTo());
			outputVO.setFamilyBookNum(addressToDetailsH.value.getFamilyBookNo());
			outputVO.setSpouseId(addressToDetailsH.value.getSpauseId());
			//outputVO.setAttachmentRecPayload(attachmentList.value);
			outputVO.setComments(reviewerComments.value);
			outputVO.setReqId(commonRequestBPM.value.getRequestId());
			outputVO.setRequestNo(commonRequestBPM.value.getRequestNo());
			outputVO.setTowhomeReqId(commonRequestBPM.value.getToWhomItmayId());
			outputVO.setOther(userDetailsH.value.getAddress2());
			outputVO.setStatus(status.value);
			
			
			List<AttachmentVO> attachList=serviceAttachmentDAO.getAllattachmentByRequestId(input.getAttributeValue().split("-")[3]);
			outputVO.setAttachmentList(attachList);
		
		} catch (Exception e) {
			logger.error("Failed" + e.getMessage());
			outputVO.setStatus(SERVICE_FAILED);
		}

		return outputVO;

	}

}
