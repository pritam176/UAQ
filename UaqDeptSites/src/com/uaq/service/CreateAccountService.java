package com.uaq.service;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uaq.services.regi.middleware.RegistrationServicePortBindingStub;
import uaq.services.regi.middleware.RegistrationService_PortType;
import uaq.services.regi.middleware.RegistrationService_Service;
import uaq.services.regi.middleware.RegistrationService_ServiceLocator;
import uaq.services.regi.middleware.UserContext;
import CreateOnlineAccount.EmiratesIDPayload;
import CreateOnlineAccount.FamilyBookPayload;
import CreateOnlineAccount.GenericDetailsPayload;
import CreateOnlineAccount.InputPayload;
import CreateOnlineAccount.PassportPayload;
import CreateOnlineAccount.UserDetailsPayload;

import com.uaq.common.ApplicationConstants;
import com.uaq.logger.UAQLogger;
import com.uaq.util.StringUtil;
import com.uaq.vo.CreateAccountInputVO;
import com.uaq.vo.CreateAccountOutputVO;
import static com.uaq.common.AttachmentDocTypeConstant.*;


@Service(value = "createAccountService")
public class CreateAccountService {
	
	protected static UAQLogger logger = new UAQLogger(CreateAccountService.class);
	
	private RegistrationService_PortType type = null;
	private RegistrationService_Service service = null;
	private RegistrationServicePortBindingStub stub = null;
	private UserContext uc = null;
	DateFormat df = new SimpleDateFormat(ApplicationConstants.MM_DD_YYYY);
	Calendar calender = null;

	@Autowired
	private UCMCenterURLService uCMCenterURLService;

	private void createStub() {

		uc = new UserContext();
		uc.setUsername(ApplicationConstants.WS_USERNAME);
		uc.setPassword(ApplicationConstants.WS_PASSWORD);

		try {
			service = (RegistrationService_Service) new RegistrationService_ServiceLocator();
			type = service.getRegistrationServicePort();
			stub = (RegistrationServicePortBindingStub) type;

		} catch (ServiceException e) {
			logger.error("WebService Error  |" + e.getMessage());

		}

	}
	
	public CreateAccountOutputVO createAccount(CreateAccountInputVO input) {
		createStub();

		CreateAccountOutputVO outputVO = new CreateAccountOutputVO();

		InputPayload payload = new InputPayload();

		

		CreateOnlineAccount.ApplicantDetailsPayload app = new CreateOnlineAccount.ApplicantDetailsPayload();

		EmiratesIDPayload em = new EmiratesIDPayload();
		em.setFullName(StringUtil.isEmpty(input.getFullname()) ? "" : input
				.getFullname());
		em.setEmiratesID(StringUtil.isEmpty(input.getEmirateId()) ? "" : input
				.getEmirateId().replaceAll("-", ""));
		em.setEmiratesCode(StringUtil.isEmpty(input.getEmirate()) ? "" : input
				.getEmirate());

		em.setExpiryDate(stringToCalendar(StringUtil.isEmpty(input
				.getEmirateIdExpiryDate()) ? "01/01/1800" : input
				.getEmirateIdExpiryDate()));

		em.setDOB(stringToCalendar(StringUtil.isEmpty(input.getDob()) ? "01/01/1800"
				: input.getDob()));

		// Should Be Changed
		em.setResidenceNo(input.getFamilyNumber() == null ? "0" : input
				.getFamilyNumber());

		// need to confirm which date to provide.No Residence date is there.
		em.setResidenceExpiryDate(stringToCalendar("01/01/1800"));

		PassportPayload pass = new PassportPayload();
		pass.setFullName(StringUtil.isEmpty(input.getFullname()) ? "" : input
				.getFullname());
		pass.setPassportNumber(StringUtil.isEmpty(input.getPassportNumber()) ? ""
				: input.getPassportNumber());

		// need to confirm which date to provide.No Passportt expiry date date
		// is there.
		pass.setPassportExpiryDate(stringToCalendar("01/01/1800"));

		FamilyBookPayload family = new FamilyBookPayload();
		family.setFullname(StringUtil.isEmpty(input.getFamilybook_fullName()) ? "0"
				: input.getFamilybook_fullName());
		family.setEmirateCode(StringUtil.isEmpty(input.getFamilybook_emirates()) ? "0"
				: input.getFamilybook_emirates());
		family.setTownName(StringUtil.isEmpty(input.getTownName()) ? "0"
				: input.getTownName());
		family.setTownNumber(StringUtil.isEmpty(input.getTownNumber()) ? "0"
				: input.getTownNumber());
		family.setFamilyNumber(StringUtil.isEmpty(input.getFamilyNumber()) ? "0"
				: input.getFamilyNumber());
		family.setTribeName(StringUtil.isEmpty(input.getTownNumber()) ? "0"
				: input.getTownNumber());
		family.setClanNumber(StringUtil.isEmpty(input.getClanNumber()) ? "0"
				: input.getClanNumber());

		family.setIssuanceDate(stringToCalendar(StringUtil.isEmpty(input
				.getIssuanceDate()) ? "01/01/1800" : input.getIssuanceDate()));

		family.setMotherName(StringUtil.isEmpty(input.getMotherName()) ? "0"
				: input.getMotherName());
		family.setMothersFatherName(StringUtil.isEmpty(input
				.getMothersFatherName()) ? "0" : input.getMothersFatherName());

		app.setApplicantTypeId(input.getApplicantTypeId());
		app.setFirstName(StringUtil.isEmpty(input.getFirstName()) ? "" : input
				.getFirstName());
		app.setMiddleName(StringUtil.isEmpty(input.getMidleName()) ? "" : input
				.getMidleName());
		app.setLastName(StringUtil.isEmpty(input.getLastName()) ? "" : input
				.getLastName());
		app.setAddress1(StringUtil.isEmpty(input.getAddress()) ? "" : input
				.getAddress());
		app.setAddress2("");
		app.setAddress3("");

		app.setMobileNo1(input.getMobile1());
		app.setMobileNo2(input.getMobile2());
		app.setLandLine(input.getLandLine());
		app.setCountryIdOfCitizenship(input.getCountryCitizen().equals("") ? ""
				: input.getCountryCitizen());
		app.setCountryIdOfResidency(input.getCountryResidency().equals("") ? ""
				: input.getCountryResidency());
		app.setEmailID(input.getEmailAddress());

		// Sholud be replace
		app.setPostbox(StringUtil.isEmpty(input.getPostboxNo()) ? "" : input
				.getPostboxNo());
		app.setHasFamilyBookNo(input.isHasFamilyBook() ? "1" : "0");
		app.setEmiratesIDRec(em);
		app.setPassportRec(pass);
		app.setFamilyBookRec(family);

		UserDetailsPayload user = new UserDetailsPayload();
		user.setApplicantUserDetails(app);

		/** for Establishment registration **/
		CreateOnlineAccount.EstablishmentDetailsPayload establishmentDetailsPayload = new CreateOnlineAccount.EstablishmentDetailsPayload();
		establishmentDetailsPayload.setFullName(StringUtil.isEmpty(input
				.getEstablishmentName()) ? "" : input.getEstablishmentName());
		establishmentDetailsPayload.setMobileNumber(input.getMobile1());
		establishmentDetailsPayload.setOfficePhone(input.getLandLine());
		establishmentDetailsPayload.setEmailAddress(input.getEmailAddress());
		establishmentDetailsPayload.setAddress1(StringUtil.isEmpty(input
				.getAddress()) ? "" : input.getAddress());
		establishmentDetailsPayload.setAddress2("");
		establishmentDetailsPayload.setAddress3("");
		establishmentDetailsPayload.setWebSite(StringUtil.isEmpty(input
				.getWebsite()) ? "" : input.getWebsite());
		establishmentDetailsPayload.setPostBox(StringUtil.isEmpty(input
				.getPostboxNo()) ? "" : input.getPostboxNo());
		establishmentDetailsPayload.setTradeLicenseNo(StringUtil.isEmpty(input
				.getTradeLicenseNumber()) ? "" : input.getTradeLicenseNumber());
		establishmentDetailsPayload.setEmiratesCode(StringUtil.isEmpty(input
				.getEmirate()) ? "" : input.getEmirate());

		establishmentDetailsPayload
				.setTradeLicenseExpiryDate(stringToCalendar(StringUtil
						.isEmpty(input.getTradeLicenseExpDate()) ? "01/01/1800"
						: input.getTradeLicenseExpDate()));

		establishmentDetailsPayload.setTradeLicenseTypeid(StringUtil
				.isEmpty(input.getTradeLicenseType()) ? "" : input
				.getTradeLicenseType());
		establishmentDetailsPayload.setEmiratesId(StringUtil.isEmpty(input
				.getEmirateId()) ? "" : input.getEmirateId());
		user.setEstablishmentUserDetails(establishmentDetailsPayload);

		payload.setUserDetails(user);
		payload.setTypeOfUser(input.getTypeofUser());
		payload.setLoginUserName(input.getUsername().toLowerCase());
		payload.setPassword(input.getPassword());

		CreateOnlineAccount.GenericDetailsPayload ge = new CreateOnlineAccount.GenericDetailsPayload();
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
			fileMapName.put(SIGNATORY_ATTSTAUION,
					input.getSignatoriesAttestation());
		}
		if (!StringUtil.isEmpty(input.getPassportResidencyPage())) {
			fileMapName.put(PASSPORT_RESIDENCY,
					input.getPassportResidencyPage());
		}

		if (!StringUtil.isEmpty(input.getVisaPage())) {
			fileMapName.put(VISA_PAGE, input.getVisaPage());
		}

		CreateOnlineAccount.AttachmentRecPayload fileArry[] = new CreateOnlineAccount.AttachmentRecPayload[fileMapName
				.size()];

		/*
		 * for (int i = 0; i < fileMapName.size(); i++) { //fileArry[i] =
		 * getURLofFIle(fileListName.get(i), i + ""); }
		 */

		int imageCounter = 0;
		Iterator<Entry<String, String>> entries = fileMapName.entrySet()
				.iterator();
		while (entries.hasNext()) {
			Entry thisEntry = (Entry) entries.next();
			fileArry[imageCounter] = getURLofFIle(thisEntry.getKey() + "",
					thisEntry.getValue() + "");
			imageCounter++;
		}

		payload.setAttachmentList(fileArry);

		CreateOnlineAccount.OutputPayload output = null;
		try {
			output = stub.registerAccount(payload, uc);
		} catch (RemoteException e) {
			logger.error("WebService Input Error  |" + e.getMessage());
			outputVO.setStatus("Failed");
		}
		if (output != null) {
			logger.info(output.getMessage_EN() + " |" + output.getStatus());
			if (!(output.getStatus().equals("Failure"))) {
				GenericDetailsPayload genericDetailsPayload = output
						.getGenericDetails();
				outputVO.setAccountId(genericDetailsPayload.getAccountid());
				outputVO.setRequestId(genericDetailsPayload.getRequestId());
				outputVO.setRequestNo(genericDetailsPayload.getRequestNo());
				outputVO.setUserName(genericDetailsPayload.getUsername());
				outputVO.setWorkFlowId(genericDetailsPayload.getWorkFlowId());
			}
			outputVO.setMessage_EN(output.getMessage_EN());
			outputVO.setMessage_AR(output.getMessage_AR());
			outputVO.setStatus(output.getStatus());
		} else {
			outputVO.setStatus("Failed");
			logger.info("stub.registerAccount(payload, uc) return NULL  | FAiled");
		}
		return outputVO;
	}
	private CreateOnlineAccount.AttachmentRecPayload getURLofFIle(String docType, String files) {

		String did = files.split("-")[0];
		String name = files.split("-")[1];
		String ucmUrl = uCMCenterURLService.getWebCenterURLofFile(did);
		CreateOnlineAccount.AttachmentRecPayload fileArray = new CreateOnlineAccount.AttachmentRecPayload();
		fileArray.setContentID(did);
		fileArray.setURL(ucmUrl);
		fileArray.setAttachemntType(docType);
		fileArray.setAttachemntName(name);
		return fileArray;
	}
	private Calendar stringToCalendar(String dateString) {

		// Date date = null;
		Calendar cal = Calendar.getInstance();
		try {

			cal.setTime(df.parse(dateString));
		} catch (ParseException e) {
			logger.error("Error in parsing Date  |" + e.getMessage());

		}

		return cal;
	}

}
