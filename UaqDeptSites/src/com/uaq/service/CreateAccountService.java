package com.uaq.service;

import static com.uaq.common.AttachmentDocTypeConstant.EMIRATES_ID_BACK;
import static com.uaq.common.AttachmentDocTypeConstant.EMIRATES_ID_FRONT;
import static com.uaq.common.AttachmentDocTypeConstant.FAMILY_BOOK;
import static com.uaq.common.AttachmentDocTypeConstant.PASSPORT_FRONT;
import static com.uaq.common.AttachmentDocTypeConstant.PASSPORT_RESIDENCY;
import static com.uaq.common.AttachmentDocTypeConstant.SIGNATORY_ATTSTAUION;
import static com.uaq.common.AttachmentDocTypeConstant.TRADE_LICENSE;
import static com.uaq.common.AttachmentDocTypeConstant.VISA_PAGE;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.proxies.creatonlineaccountbpel_client_ep.types.OutputPayload;
import com.uaq.soap.warpper.WebServiceWarpper;
import com.uaq.util.DateUtil;
import com.uaq.util.StringUtil;
import com.uaq.vo.CreateAccountInputVO;
import com.uaq.vo.CreateAccountOutputVO;

@Service(value = "createAccountService")
public class CreateAccountService {

	protected static UAQLogger logger = new UAQLogger(CreateAccountService.class);

	private WebServiceWarpper webServiceWarpper = new WebServiceWarpper();

	@Autowired
	private UCMCenterURLService uCMCenterURLService;

	

	public CreateAccountOutputVO createAccount(CreateAccountInputVO input) throws DatatypeConfigurationException, ParseException {
		// createStub();

		CreateAccountOutputVO outputVO = new CreateAccountOutputVO();

		com.uaq.proxies.creatonlineaccountbpel_client_ep.types.InputPayload payload = new com.uaq.proxies.creatonlineaccountbpel_client_ep.types.InputPayload();

		com.uaq.proxies.creatonlineaccountbpel_client_ep.types.ApplicantDetailsPayload app = new com.uaq.proxies.creatonlineaccountbpel_client_ep.types.ApplicantDetailsPayload();

		com.uaq.proxies.creatonlineaccountbpel_client_ep.types.EmiratesIDPayload em = new com.uaq.proxies.creatonlineaccountbpel_client_ep.types.EmiratesIDPayload();
		em.setFullName(StringUtil.isEmpty(input.getFullname()) ? "" : input.getFullname());
		em.setEmiratesID(StringUtil.isEmpty(input.getEmirateId()) ? "" : input.getEmirateId().replaceAll("-", ""));
		em.setEmiratesCode(StringUtil.isEmpty(input.getEmirate()) ? "" : input.getEmirate());

		em.setExpiryDate(DateUtil.convertStringToXMLGregorianCalendar(StringUtil.isEmpty(input.getEmirateIdExpiryDate()) ? "01/01/1800" : input.getEmirateIdExpiryDate()));

		em.setDOB(DateUtil.convertStringToXMLGregorianCalendar(StringUtil.isEmpty(input.getDob()) ? "01/01/1800" : input.getDob()));

		// Should Be Changed
		em.setResidenceNo(input.getFamilyNumber() == null ? "0" : input.getFamilyNumber());

		// need to confirm which date to provide.No Residence date is there.
		em.setResidenceExpiryDate(DateUtil.convertStringToXMLGregorianCalendar("01/01/1800"));

		com.uaq.proxies.creatonlineaccountbpel_client_ep.types.PassportPayload pass = new com.uaq.proxies.creatonlineaccountbpel_client_ep.types.PassportPayload();
		pass.setFullName(StringUtil.isEmpty(input.getFullname()) ? "" : input.getFullname());
		pass.setPassportNumber(StringUtil.isEmpty(input.getPassportNumber()) ? "" : input.getPassportNumber());

		// need to confirm which date to provide.No Passportt expiry date date
		// is there.
		pass.setPassportExpiryDate(DateUtil.convertStringToXMLGregorianCalendar("01/01/1800"));

		com.uaq.proxies.creatonlineaccountbpel_client_ep.types.FamilyBookPayload family = new com.uaq.proxies.creatonlineaccountbpel_client_ep.types.FamilyBookPayload();
		family.setFullname(StringUtil.isEmpty(input.getFamilybook_fullName()) ? "0" : input.getFamilybook_fullName());
		family.setEmirateCode(StringUtil.isEmpty(input.getFamilybook_emirates()) ? "0" : input.getFamilybook_emirates());
		family.setTownName(StringUtil.isEmpty(input.getTownName()) ? "0" : input.getTownName());
		family.setTownNumber(StringUtil.isEmpty(input.getTownNumber()) ? "0" : input.getTownNumber());
		family.setFamilyNumber(StringUtil.isEmpty(input.getFamilyNumber()) ? "0" : input.getFamilyNumber());
		family.setTribeName(StringUtil.isEmpty(input.getTownNumber()) ? "0" : input.getTownNumber());
		family.setClanNumber(StringUtil.isEmpty(input.getClanNumber()) ? "0" : input.getClanNumber());

		family.setIssuanceDate(DateUtil.convertStringToXMLGregorianCalendar(StringUtil.isEmpty(input.getIssuanceDate()) ? "01/01/1800" : input.getIssuanceDate()));

		family.setMotherName(StringUtil.isEmpty(input.getMotherName()) ? "0" : input.getMotherName());
		family.setMothersFatherName(StringUtil.isEmpty(input.getMothersFatherName()) ? "0" : input.getMothersFatherName());

		app.setApplicantTypeId(input.getApplicantTypeId());
		app.setFirstName(StringUtil.isEmpty(input.getFirstName()) ? "" : input.getFirstName());
		app.setMiddleName(StringUtil.isEmpty(input.getMidleName()) ? "" : input.getMidleName());
		app.setLastName(StringUtil.isEmpty(input.getLastName()) ? "" : input.getLastName());
		app.setAddress1(StringUtil.isEmpty(input.getAddress()) ? "" : input.getAddress());
		app.setAddress2("");
		app.setAddress3("");

		app.setMobileNo1(input.getMobile1());
		app.setMobileNo2(input.getMobile2());
		app.setLandLine(input.getLandLine());
		app.setCountryIdOfCitizenship(input.getCountryCitizen().equals("") ? "" : input.getCountryCitizen());
		app.setCountryIdOfResidency(input.getCountryResidency().equals("") ? "" : input.getCountryResidency());
		app.setEmailID(input.getEmailAddress());

		// Sholud be replace
		app.setPostbox(StringUtil.isEmpty(input.getPostboxNo()) ? "" : input.getPostboxNo());
		app.setHasFamilyBookNo(input.isHasFamilyBook() ? "1" : "0");
		app.setEmiratesIDRec(em);
		app.setPassportRec(pass);
		app.setFamilyBookRec(family);

		com.uaq.proxies.creatonlineaccountbpel_client_ep.types.UserDetailsPayload user = new com.uaq.proxies.creatonlineaccountbpel_client_ep.types.UserDetailsPayload();
		user.setApplicantUserDetails(app);

		/** for Establishment registration **/
		com.uaq.proxies.creatonlineaccountbpel_client_ep.types.EstablishmentDetailsPayload establishmentDetailsPayload = new com.uaq.proxies.creatonlineaccountbpel_client_ep.types.EstablishmentDetailsPayload();
		establishmentDetailsPayload.setFullName(StringUtil.isEmpty(input.getEstablishmentName()) ? "" : input.getEstablishmentName());
		establishmentDetailsPayload.setMobileNumber(input.getMobile1());
		establishmentDetailsPayload.setOfficePhone(input.getLandLine());
		establishmentDetailsPayload.setEmailAddress(input.getEmailAddress());
		establishmentDetailsPayload.setAddress1(StringUtil.isEmpty(input.getAddress()) ? "" : input.getAddress());
		establishmentDetailsPayload.setAddress2("");
		establishmentDetailsPayload.setAddress3("");
		establishmentDetailsPayload.setWebSite(StringUtil.isEmpty(input.getWebsite()) ? "" : input.getWebsite());
		establishmentDetailsPayload.setPostBox(StringUtil.isEmpty(input.getPostboxNo()) ? "" : input.getPostboxNo());
		establishmentDetailsPayload.setTradeLicenseNo(StringUtil.isEmpty(input.getTradeLicenseNumber()) ? "" : input.getTradeLicenseNumber());
		establishmentDetailsPayload.setEmiratesCode(StringUtil.isEmpty(input.getEmirate()) ? "" : input.getEmirate());

		try {
			establishmentDetailsPayload.setTradeLicenseExpiryDate(DateUtil.convertStringToXMLGregorianCalendar(input.getTradeLicenseExpDate()));
		} catch (ParseException e1) {
			logger.error(e1.getMessage());
		}

		establishmentDetailsPayload.setTradeLicenseTypeid(StringUtil.isEmpty(input.getTradeLicenseType()) ? "" : input.getTradeLicenseType());
		establishmentDetailsPayload.setEmiratesId(StringUtil.isEmpty(input.getEmirateId()) ? "" : input.getEmirateId());
		user.setEstablishmentUserDetails(establishmentDetailsPayload);

		payload.setUserDetails(user);
		payload.setTypeOfUser(input.getTypeofUser());
		payload.setLoginUserName(input.getUsername().toLowerCase());
		payload.setPassword(input.getPassword());

		com.uaq.proxies.creatonlineaccountbpel_client_ep.types.GenericDetailsPayload ge = new com.uaq.proxies.creatonlineaccountbpel_client_ep.types.GenericDetailsPayload();
		ge.setWorkFlowId("1");
		ge.setLanguageID(input.getLanguageId());
		ge.setProfileImageId("1");
		ge.setSubcribeForNewsLetterFlag(input.isNewsLetter() ? "1" : "0");
		ge.setUsername(input.getUsername().toLowerCase());
		ge.setAccountid("1");
		ge.setRequestId("1");
		ge.setRequestNo("1");

		payload.setGenericDetails(ge);

		/**
		 * Adding the file did to the FileArray
		 * 
		 * 
		 * @author Pritam
		 * 
		 */

		Map<String, String> fileMapName = new HashMap<String, String>();

		if (!StringUtil.isEmpty(input.getEmerateIdFront())) {
			fileMapName.put(EMIRATES_ID_FRONT, input.getEmerateIdFront());
		}

		if (!StringUtil.isEmpty(input.getEmerateIdBack())) {
			fileMapName.put(EMIRATES_ID_BACK, input.getEmerateIdBack());
		}

		if (!StringUtil.isEmpty(input.getFamilyBook())) {
			fileMapName.put(FAMILY_BOOK, input.getFamilyBook());
		}

		if (!StringUtil.isEmpty(input.getPassportFront())) {
			fileMapName.put(PASSPORT_FRONT, input.getPassportFront());
		}
		if (!StringUtil.isEmpty(input.getTradeLicenseAttach())) {
			fileMapName.put(TRADE_LICENSE, input.getTradeLicenseAttach());
		}

		if (!StringUtil.isEmpty(input.getSignatoriesAttestation())) {
			fileMapName.put(SIGNATORY_ATTSTAUION, input.getSignatoriesAttestation());
		}
		if (!StringUtil.isEmpty(input.getPassportResidencyPage())) {
			fileMapName.put(PASSPORT_RESIDENCY, input.getPassportResidencyPage());
		}

		if (!StringUtil.isEmpty(input.getVisaPage())) {
			fileMapName.put(VISA_PAGE, input.getVisaPage());
		}

		com.uaq.proxies.creatonlineaccountbpel_client_ep.types.AttachmentListPayload fileList = new com.uaq.proxies.creatonlineaccountbpel_client_ep.types.AttachmentListPayload();
		
		/*
		 * for (int i = 0; i < fileMapName.size(); i++) { //fileArry[i] =
		 * getURLofFIle(fileListName.get(i), i + ""); }
		 */

		Iterator<Entry<String, String>> entries = fileMapName.entrySet().iterator();
		while (entries.hasNext()) {
			Entry thisEntry = (Entry) entries.next();
			fileList.getAttachmentRec().add(getURLofFIle(thisEntry.getKey() + "", thisEntry.getValue() + ""));

		}

		payload.setAttachmentList(fileList);

		OutputPayload output = null;
		try {
			output = webServiceWarpper.createAccountOnline(payload, PropertiesUtil.getProperty("SOA_URL_CRETAE_ACCOUNT_ONLINE"), PropertiesUtil.getProperty("SOA_USERNAME"),
					PropertiesUtil.getProperty("SOA_PASSWORD"));

			if (output != null) {
				logger.info(output.getMessageEN() + " |" + output.getStatus());
				if (!(output.getStatus().equals("Failure"))) {
					com.uaq.proxies.creatonlineaccountbpel_client_ep.types.GenericDetailsPayload genericDetailsPayload = output.getGenericDetails();
					outputVO.setAccountId(genericDetailsPayload.getAccountid());
					outputVO.setRequestId(genericDetailsPayload.getRequestId());
					outputVO.setRequestNo(genericDetailsPayload.getRequestNo());
					outputVO.setUserName(genericDetailsPayload.getUsername());
					outputVO.setWorkFlowId(genericDetailsPayload.getWorkFlowId());
				}
				outputVO.setMessage_EN(output.getMessageEN());
				outputVO.setMessage_AR(output.getMessageAR());
				outputVO.setStatus(output.getStatus());
			} else {
				outputVO.setStatus("Failed");
				logger.info("stub.registerAccount(payload, uc) return NULL  | FAiled");
			}
		} catch (Exception e) {
			logger.error("WebService Input Error  |" + e.getMessage());
			outputVO.setStatus("Failed");
		}
		return outputVO;
	}

	private com.uaq.proxies.creatonlineaccountbpel_client_ep.types.AttachmentRecPayload getURLofFIle(String docType, String files) {

		String did = files.split("-")[0];
		String name = files.split("-")[1];
		String ucmUrl = uCMCenterURLService.getWebCenterURLofFile(did);
		com.uaq.proxies.creatonlineaccountbpel_client_ep.types.AttachmentRecPayload fileArray = new com.uaq.proxies.creatonlineaccountbpel_client_ep.types.AttachmentRecPayload();
		fileArray.setContentID(did);
		fileArray.setURL(ucmUrl);
		fileArray.setAttachemntType(docType);
		fileArray.setAttachemntName(name);
		return fileArray;
	}

}
