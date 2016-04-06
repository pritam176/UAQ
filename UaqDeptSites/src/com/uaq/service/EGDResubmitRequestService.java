/*package com.uaq.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.Holder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaq.common.PropertiesUtil;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;
import com.uaq.proxies.approverforoutbpel_client_ep.types.RegistrationTypeRecType;
import com.uaq.soap.warpper.WebServiceWarpper;
import com.uaq.util.StringUtil;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.NewSupplierRegistrationVO;
import com.uaq.vo.UserDeatilVO;

import static com.uaq.common.AttachmentDocTypeConstant.CERTIFICATE;
import static com.uaq.common.AttachmentDocTypeConstant.CHAMBER_OF_COMMERCE;

@Service("eGDResubmitRequestService")
public class EGDResubmitRequestService {

	protected static UAQLogger logger = new UAQLogger(EGDResubmitRequestService.class);

	@Autowired
	private UCMCenterURLService uCMCenterURLService;

	private WebServiceWarpper webServiceWarpper = new WebServiceWarpper();

	public LandOutputVO reSubmitSupplierRequest(UserDeatilVO userDeatilVO, NewSupplierRegistrationVO supplierDetails) throws DatatypeConfigurationException, ParseException {

		LandOutputVO outputVO = new LandOutputVO();

		com.uaq.proxies.approverforoutbpel_client_ep.types.SuppRegisterRenewReqType suppDetails = new com.uaq.proxies.approverforoutbpel_client_ep.types.SuppRegisterRenewReqType();

		suppDetails.setEstablishmentName(supplierDetails.getEstablishmentName());
		// suppDetails.setTradeLicenceNo(supplierDetails.getTradeLicenceNumber());
		// once abdul did the exception.we need to remove

		suppDetails.setTradeLicenceNo(userDeatilVO.getTradeLienceNo());

		suppDetails.setTradExpDateo(PortalDataMapper.convertStringToXMLGregorianCalendar(userDeatilVO.getTradeLicenceExpDate()));

		suppDetails.setRequestType(supplierDetails.getRequestType());
		suppDetails.setSourceType(supplierDetails.getSourceType());
		// suppDetails.setUserName(supplierDetails.getUserName());
		suppDetails.setServiceId(supplierDetails.getServiceId());
		suppDetails.setSuppRegId("1");
		suppDetails.setSuppCatgId(supplierDetails.getSuppCatgId());
		suppDetails.setUserCommnets(supplierDetails.getUserCommnets());
		suppDetails.setOffTelNum(supplierDetails.getOfficeNumber());
		suppDetails.setSupplierCategory(supplierDetails.getSupplierCategory());

		Arrays.sort(supplierDetails.getRegistrationsType());
		String[] regType = Arrays.copyOf(supplierDetails.getRegistrationsType(), supplierDetails.getRegistrationsType().length, String[].class);
		suppDetails.setRegistrationType(StringUtils.join(regType, ","));

		// String[] regType = supplierDetails.getRegistrationsType();
		logger.info("Registration Type-" + regType.length);
		com.uaq.proxies.approverforoutbpel_client_ep.types.RegistrationTypeListType registrationTypeList = new com.uaq.proxies.approverforoutbpel_client_ep.types.RegistrationTypeListType();
		for (int i = 0; i < regType.length; i++) {
			com.uaq.proxies.approverforoutbpel_client_ep.types.RegistrationTypeRecType typeRec = new RegistrationTypeRecType();
			typeRec.setRegistrationTypeId(regType[i]);
			typeRec.setRegistrationType(regType[i]);
			registrationTypeList.getRegistrationTypeIdRec().add(typeRec);
		}
		suppDetails.setRegistrationTypeList(registrationTypeList);

		com.uaq.proxies.approverforoutbpel_client_ep.types.CommonRequestBPMType commonRequestBPM = new com.uaq.proxies.approverforoutbpel_client_ep.types.CommonRequestBPMType();
		commonRequestBPM.setRequestId(supplierDetails.getRequestId());
		commonRequestBPM.setRequestNo(supplierDetails.getRequestNo());
		commonRequestBPM.setWorkListId(supplierDetails.getWorkListId());
		commonRequestBPM.setSupprReqId(supplierDetails.getSupprReqId());
		commonRequestBPM.setStateId(supplierDetails.getStateId());
		commonRequestBPM.setTempSupprReqId(supplierDetails.getTempSupprReqId());
		commonRequestBPM.setServiceNameEn(supplierDetails.getServiceName_En());
		commonRequestBPM.setServiceNameAr(supplierDetails.getServiceName_Ar());

		com.uaq.proxies.approverforoutbpel_client_ep.types.UserDetailsPayload userDetails = new com.uaq.proxies.approverforoutbpel_client_ep.types.UserDetailsPayload();

		userDetails.setUsername(supplierDetails.getUserName());
		userDetails.setTypeOfUser(userDeatilVO.getTypeOfUser());
		userDetails.setAccountid(userDeatilVO.getAccountid());
		userDetails.setMobileNo(userDeatilVO.getMobileNo());
		userDetails.setEmailID(userDeatilVO.getEmailID());
		userDetails.setEmiratesId(userDeatilVO.getEmiratesId());
		userDetails.setTradeLienceNo(userDeatilVO.getTradeLienceNo());
		userDetails.setFamilyBookNo(userDeatilVO.getFamilyBookNo());
		userDetails.setNationalityID(StringUtil.isEmpty(userDeatilVO.getNationality()) ? new BigInteger("0") : new BigInteger(userDeatilVO.getNationality()));
		userDetails.setEmirate(userDeatilVO.getEmirate());
		userDetails.setFirstName(userDeatilVO.getFirstName());
		userDetails.setLastName(userDeatilVO.getLastName());
		userDetails.setAddress1(supplierDetails.getAddress());
		userDetails.setAddress2(userDeatilVO.getAddress2());
		userDetails.setPOBOX(new BigInteger(supplierDetails.getPostBox()));
		userDetails.setDOB(PortalDataMapper.convertDateToXMLGregorianCalendar(userDeatilVO.getDOB()));
		userDetails.setHomePhone(userDeatilVO.getHomePhone());
		userDetails.setLanguage(supplierDetails.getLanguageId());
		// userDetails.setEirates_Code(new BigInteger("0"));
		userDetails.setNationality("");
		userDetails.setLanguage("");

		// userDetails.setHome_Phone("");
		// userDetails.setLanguage_ID(new BigInteger("0"));
		userDetails.setEiratesCode(supplierDetails.getEmirates());
		// userDetails.setNationality("");
		// userDetails.setLanguage("");

		com.uaq.proxies.approverforoutbpel_client_ep.types.PaymentRequestType paymentDetails = new com.uaq.proxies.approverforoutbpel_client_ep.types.PaymentRequestType();

		paymentDetails.setPaymentId(supplierDetails.getPaymentId());
		paymentDetails.setPaymentStatus(supplierDetails.getPaymentStatus());
		paymentDetails.setServiceFee(supplierDetails.getServiceFee());
		paymentDetails.setApplicationFee(supplierDetails.getApplicationFee());
		paymentDetails.setDeptID(supplierDetails.getDeptID());
		paymentDetails.setServiceFeeDisc(supplierDetails.getServiceFeeDisc());
		paymentDetails.setEdirhamServCode(supplierDetails.getEdirhamServCode());
		paymentDetails.setIsPaymentRequired(supplierDetails.getIsPaymentRequired());
		paymentDetails.setDepartment("");

		Map<String, String> fileList = supplierDetails.getFileList();
		com.uaq.proxies.approverforoutbpel_client_ep.types.AttachmentListPayload attachmentList = new com.uaq.proxies.approverforoutbpel_client_ep.types.AttachmentListPayload();

		Iterator it = supplierDetails.getFileList().entrySet().iterator();
		int index = 0;
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			String filename = (String) pair.getValue();
			logger.debug("File Name from Controller   |  " + filename);
			String did = filename.split("-")[0];
			String name = filename.split("-")[1];
			String ucmUrl = uCMCenterURLService.getWebCenterURLofFile(did);
			logger.debug("From WebCenter URL=" + ucmUrl);
			com.uaq.proxies.approverforoutbpel_client_ep.types.AttachmentRecPayload attachmentRecPayload = new com.uaq.proxies.approverforoutbpel_client_ep.types.AttachmentRecPayload();
			attachmentRecPayload.setContentid(did);
			attachmentRecPayload.setUrl(ucmUrl);
			attachmentRecPayload.setFileExpiryDate("10/20/2017");
			attachmentRecPayload.setFilename(did + " | " + name);
			attachmentRecPayload.setFileExpiryDate("01/01/2018");
			attachmentRecPayload.setIsMandatory("1");
			if (CHAMBER_OF_COMMERCE.equals(pair.getKey())) {
				// now trade license and signature are not uploading from
				// portal.
				attachmentRecPayload.setDocTypeId((String) pair.getKey());

			} else {
				attachmentRecPayload.setDocTypeId(CERTIFICATE);
			}
			attachmentList.getAttachmentRec().add(attachmentRecPayload);
			index++;
		}

		suppDetails.setTradeLicenseAttachmentid(supplierDetails.getTradeLicenceName());
		suppDetails.setSignetoryAttestationAttachid(supplierDetails.getSignatureAttestation());
		suppDetails.setCOCMemAttachmentid(supplierDetails.getChamberOfCommerce() == null ? "" : supplierDetails.getChamberOfCommerce());
		suppDetails.setCertificateAttachemntid(supplierDetails.getCertificates());

		Holder<String> statusEN = new Holder<String>();
		Holder<String> statusAR = new Holder<String>();
		Holder<String> requestNo = new Holder<String>();

		Holder<String> statusId = new Holder<String>();

		Holder<com.uaq.proxies.approverforoutbpel_client_ep.types.AttachmentListPayload> attachmentListH = new Holder<com.uaq.proxies.approverforoutbpel_client_ep.types.AttachmentListPayload>(
				attachmentList);

		Holder<com.uaq.proxies.approverforoutbpel_client_ep.types.UserDetailsPayload> userDetailsH = new Holder<com.uaq.proxies.approverforoutbpel_client_ep.types.UserDetailsPayload>(userDetails);
		Holder<com.uaq.proxies.approverforoutbpel_client_ep.types.PaymentRequestType> paymentDetailsH = new Holder<com.uaq.proxies.approverforoutbpel_client_ep.types.PaymentRequestType>(
				paymentDetails);

		try {

			webServiceWarpper.reSubmitSypplier(suppDetails, attachmentListH, commonRequestBPM, userDetailsH, paymentDetailsH, null, null, statusEN, statusAR, requestNo, null, null, null, null, null,
					null, null, null, null, null, null, null, null, statusId, null, PropertiesUtil.getProperty("SOA_URL_EGD_SUBMIT"), PropertiesUtil.getProperty("SOA_USERNAME"),
					PropertiesUtil.getProperty("SOA_PASSWORD"));

			outputVO.setSatausId(statusId.value);
			outputVO.setStatus_EN(statusEN.value);
			outputVO.setStatus_AR(statusAR.value);
			outputVO.setRequestNo(requestNo.value);
			outputVO.setStatus("Sucess");

		} catch (Exception e) {
			logger.error("Failure  | " + e.getMessage());
			outputVO.setStatus("Failed");
		}

		return outputVO;

	}

}
*/