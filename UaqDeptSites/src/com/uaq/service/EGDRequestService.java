package com.uaq.service;

import static com.uaq.common.AttachmentDocTypeConstant.CERTIFICATE;
import static com.uaq.common.AttachmentDocTypeConstant.CHAMBER_OF_COMMERCE;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.ws.Holder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.uaq.common.PropertiesUtil;
import com.uaq.common.ServiceNameConstant;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.dao.EGDServiceDAO;
import com.uaq.logger.UAQLogger;
import com.uaq.proxies.supp_registration_bpel_client_ep.types.RegistrationTypeListType;
import com.uaq.proxies.supp_registration_bpel_client_ep.types.SuppRegisterRenewReqType;
import com.uaq.soap.warpper.WebServiceWarpper;
import com.uaq.util.DateUtil;
import com.uaq.util.StringUtil;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.NewSupplierRegistrationVO;
import com.uaq.vo.UserDeatilVO;

/**
 * 
 * @author G Sekhar
 * 
 */
@Service
@Qualifier("eGDRequestService")
public class EGDRequestService {

	protected static UAQLogger logger = new UAQLogger(EGDRequestService.class);

	@Autowired
	private UCMCenterURLService uCMCenterURLService;

	@Autowired
	private EGDServiceDAO eGDServiceDAO;

	private WebServiceWarpper webServiceWarpper = new WebServiceWarpper();

	public LandOutputVO submitSupplierRequest(UserDeatilVO userDeatilVO, NewSupplierRegistrationVO supplierDetails) throws DatatypeConfigurationException {

		LandOutputVO outputVO = new LandOutputVO();
		com.uaq.proxies.supp_registration_bpel_client_ep.types.ProcessType processType = new com.uaq.proxies.supp_registration_bpel_client_ep.types.ProcessType();
		com.uaq.proxies.supp_registration_bpel_client_ep.types.SuppRegisterRenewReqType suppDetails = new SuppRegisterRenewReqType();
		// com.uaq.proxies.supp_registration_bpel_client_ep.types.Supp_RegisterRenewReqType
		// suppDetails = new
		// com.uaq.proxies.supp_registration_bpel_client_ep.types.Supp_RegisterRenewReqType();
		Holder<String> establishmentName = new Holder<String>(supplierDetails.getEstablishmentName());
		// suppDetails.setEstablishmentName(supplierDetails.getEstablishmentName());
		// suppDetails.setTradeLicenceNo(supplierDetails.getTradeLicenceNumber());
		// once abdul did the exception.we need to remove

		// suppDetails.setTradeLicenceNo(userDeatilVO.getTradeLienceNo());
		Holder<String> tradeLicenceNo = new Holder<String>(userDeatilVO.getTradeLienceNo());
		suppDetails.setTradeLicenceNo(userDeatilVO.getTradeLienceNo());
		suppDetails.setEstablishmentName(supplierDetails.getEstablishmentName());
		
		try {
			// suppDetails.setTradExpDateo(DateUtil.getDateFromString(supplierDetails.getExpiryDate()));
			//expiryDate = new Holder<XMLGregorianCalendar>(PortalDataMapper.convertStringToXMLGregorianCalendar(userDeatilVO.getTradeLicenceExpDate()));
			suppDetails.setTradExpDateo(DateUtil.convertStringToXMLGregorianCalendar(userDeatilVO.getTradeLicenceExpDate()));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

		RegistrationTypeListType regtypleList = new RegistrationTypeListType();
		com.uaq.proxies.supp_registration_bpel_client_ep.types.RegistrationTypeRecType registrationTypeRec = new com.uaq.proxies.supp_registration_bpel_client_ep.types.RegistrationTypeRecType();
		for (int i = 0; i < regType.length; i++) {

			registrationTypeRec.setRegistrationTypeId(regType[i]);
			registrationTypeRec.setRegistrationType(regType[i]);
			regtypleList.getRegistrationTypeIdRec().add(registrationTypeRec);
		}

		suppDetails.setRegistrationTypeList(regtypleList);

		com.uaq.proxies.supp_registration_bpel_client_ep.types.CommonRequestBPMType commonRequestBPM = new com.uaq.proxies.supp_registration_bpel_client_ep.types.CommonRequestBPMType();

		commonRequestBPM.setStateId(supplierDetails.getStateId());
		commonRequestBPM.setRequestId(supplierDetails.getRequestId());
		commonRequestBPM.setRequestNo(supplierDetails.getRequestNo());
		commonRequestBPM.setServiceNameAr("");
		commonRequestBPM.setServiceNameEn("");
		commonRequestBPM.setSupprReqId(new Integer(0));
		commonRequestBPM.setTempSupprReqId(new Integer(0));
		commonRequestBPM.setWorkListId(new Integer(0));

		com.uaq.proxies.supp_registration_bpel_client_ep.types.UserDetailsPayload userDetails = new com.uaq.proxies.supp_registration_bpel_client_ep.types.UserDetailsPayload();

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
		userDetails.setDOB(DateUtil.convertDateToXMLGregorianCalendar(userDeatilVO.getDOB()));
		userDetails.setHomePhone(userDeatilVO.getHomePhone());
		userDetails.setLanguage(supplierDetails.getLanguageId());
		userDetails.setEiratesCode(supplierDetails.getEmirates());
		userDetails.setNationality(userDeatilVO.getNationality());
		
		

		com.uaq.proxies.supp_registration_bpel_client_ep.types.PaymentRequestType paymentDetails = new com.uaq.proxies.supp_registration_bpel_client_ep.types.PaymentRequestType();

		paymentDetails.setPaymentId(supplierDetails.getPaymentId());
		paymentDetails.setPaymentStatus(supplierDetails.getPaymentStatus());
		paymentDetails.setServiceFee(supplierDetails.getServiceFee());
		paymentDetails.setApplicationFee(supplierDetails.getApplicationFee());
		paymentDetails.setDeptID(supplierDetails.getDeptID());
		paymentDetails.setServiceFeeDisc(supplierDetails.getServiceFeeDisc());
		paymentDetails.setEdirhamServCode(supplierDetails.getEdirhamServCode());
		paymentDetails.setIsPaymentRequired(supplierDetails.getIsPaymentRequired());
		paymentDetails.setDepartment("");

		com.uaq.proxies.supp_registration_bpel_client_ep.types.AttachmentListPayload attachmentList = new com.uaq.proxies.supp_registration_bpel_client_ep.types.AttachmentListPayload();

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
			com.uaq.proxies.supp_registration_bpel_client_ep.types.AttachmentRecPayload attachmentRecPayload = new com.uaq.proxies.supp_registration_bpel_client_ep.types.AttachmentRecPayload();
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
			// attachmentList[index] = attachmentRecPayload;
			attachmentList.getAttachmentRec().add(attachmentRecPayload);
			index++;
		}

		// Ae per SOA no need to pass empty string as SupportiveAttachmentid.
		suppDetails.setTradeLicenseAttachmentid(supplierDetails.getTradeLicenceName());
		suppDetails.setSignetoryAttestationAttachid(supplierDetails.getSignatureAttestation());
		suppDetails.setCOCMemAttachmentid(supplierDetails.getChamberOfCommerce());
		suppDetails.setCertificateAttachemntid(supplierDetails.getCertificates());
		suppDetails.setSupportiveAttachmentid("");

		

		Holder<String> statusEN = new Holder<String>();
		Holder<String> statusAR = new Holder<String>();

		try {
			Holder<com.uaq.proxies.supp_registration_bpel_client_ep.types.AttachmentListPayload> attachmentListH = new Holder<com.uaq.proxies.supp_registration_bpel_client_ep.types.AttachmentListPayload>(
					attachmentList);

			Holder<com.uaq.proxies.supp_registration_bpel_client_ep.types.UserDetailsPayload> userDetailsH = new Holder<com.uaq.proxies.supp_registration_bpel_client_ep.types.UserDetailsPayload>(
					userDetails);
			Holder<com.uaq.proxies.supp_registration_bpel_client_ep.types.PaymentRequestType> paymentDetailsH = new Holder<com.uaq.proxies.supp_registration_bpel_client_ep.types.PaymentRequestType>(
					paymentDetails);
			webServiceWarpper.submutSupplier(suppDetails, attachmentListH, commonRequestBPM, userDetailsH, paymentDetailsH, null, null, statusEN, statusAR, null, null, establishmentName,
					tradeLicenceNo, null, null, null, null, null, null, null, null, null, null, null, null, PropertiesUtil.getProperty("SOA_URL_EGD_SUBMIT"),
					PropertiesUtil.getProperty("SOA_USERNAME"), PropertiesUtil.getProperty("SOA_PASSWORD"));

			outputVO.setSatausId(paymentDetailsH.value.getIsPaymentRequired());
			outputVO.setStatus_EN(statusEN.value);
			outputVO.setStatus_AR(statusAR.value);
			outputVO.setStatus("Sucess");

		} catch (Exception e) {
			logger.error("Failure  | " + e.getMessage());
			outputVO.setStatus("Failed");
		}

		return outputVO;

	}

	public boolean isValidSupplier(String serviceId, String accountId) {
		logger.enter("isValidSupplier");
		boolean isValid = false;

		if (ServiceNameConstant.RENEW_SUPPLIER_REGISTRATION.equals(serviceId)) {

			isValid = eGDServiceDAO.isExpired(accountId);
			// isValid = (eGDServiceDAO.isCreated(accountId));

		}

		if (ServiceNameConstant.NEW_SUPPLIER_REGISTRATION.equals(serviceId)) {
			isValid = !(eGDServiceDAO.isCreated(accountId));
		}
		logger.debug("validSuppliterrequested | " + isValid);
		logger.exit("isValidSupplier");
		return isValid;
	}

}
