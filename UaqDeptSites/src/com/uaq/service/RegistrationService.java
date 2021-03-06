package com.uaq.service;

import static com.uaq.common.ApplicationConstants.EMPTY_STRING;
import static com.uaq.common.ApplicationConstants.SERVICE_FAILED;
import static com.uaq.common.ApplicationConstants.SERVICE_SUCCESS;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.proxies.validateotpbpel_client_ep.types.InputPayload;
import com.uaq.proxies.validateotpbpel_client_ep.types.OutputPayload;
import com.uaq.soap.warpper.WebServiceWarpper;
import com.uaq.util.StringUtil;
import com.uaq.vo.AccountDetailInputVO;
import com.uaq.vo.AccountDetailOutputVO;
import com.uaq.vo.ActiveAccountInputVO;
import com.uaq.vo.ActiveAccountOutputVO;
import com.uaq.vo.ForgetPasswordInputVO;
import com.uaq.vo.ForgetPasswordOutputVO;
import com.uaq.vo.ForgetUserNameInputVO;
import com.uaq.vo.ForgetUserNameOutputVO;
import com.uaq.vo.ValidateOTPInputVO;
import com.uaq.vo.ValidateOTPOutput;

/**
 * Service class for Create Account on WSDL Calling.
 * 
 * 
 * Jar Name-RegistrationService.jar WSDL-http://94.57.252.234:7001
 * /UAQServiceMiddleLayer/RegistrationServicePort?WSDL
 * 
 * @author Pritam
 * 
 */

@Service(value = "registrationService")
public class RegistrationService {

	protected static UAQLogger logger = new UAQLogger(RegistrationService.class);

	
	

	

	public ActiveAccountOutputVO activateAccount(ActiveAccountInputVO input) {

		
		ActiveAccountOutputVO output = new ActiveAccountOutputVO();

		com.uaq.proxies.activateuseraccountbpel_client_ep.types.InputPayload payload = new com.uaq.proxies.activateuseraccountbpel_client_ep.types.InputPayload();
		payload.setAccountId(input.getAccountId());
		payload.setTypeOfUser(input.getTypeOfUser());
		payload.setLoginusername(input.getLoginusername().toLowerCase());
		payload.setPassword(input.getPassword());
		payload.setMobileno(input.getMobileno());

		com.uaq.proxies.activateuseraccountbpel_client_ep.types.EstablishmentPayload establishmentPayload = new com.uaq.proxies.activateuseraccountbpel_client_ep.types.EstablishmentPayload();
		establishmentPayload.setAddress1(StringUtil.isEmpty(input.getAddress()) ? EMPTY_STRING : input.getAddress());
		establishmentPayload.setAddress2(EMPTY_STRING);
		establishmentPayload.setAddress3(EMPTY_STRING);
		establishmentPayload.setEmailAddress(StringUtil.isEmpty(input.getEmail()) ? EMPTY_STRING : input.getEmail());
		establishmentPayload.setOfficephone(StringUtil.isEmpty(input.getLandLine()) ? EMPTY_STRING : input.getLandLine());
		establishmentPayload.setPostbox(StringUtil.isEmpty(input.getPostbox()) ? EMPTY_STRING : input.getPostbox());

		com.uaq.proxies.activateuseraccountbpel_client_ep.types.IndividualPayload individualPayload = new com.uaq.proxies.activateuseraccountbpel_client_ep.types.IndividualPayload();
		individualPayload.setMobileNo1(StringUtil.isEmpty(input.getMobileno()) ? EMPTY_STRING : input.getMobileno());
		individualPayload.setLandline(StringUtil.isEmpty(input.getLandLine()) ? EMPTY_STRING : input.getLandLine());
		individualPayload.setEmailAddress(StringUtil.isEmpty(input.getEmail()) ? EMPTY_STRING : input.getEmail());

		payload.setIndividualRec(individualPayload);
		payload.setEstablishmentRec(establishmentPayload);

		com.uaq.proxies.activateuseraccountbpel_client_ep.types.OutputPayload outputPayload = null;
		try {
			outputPayload = new WebServiceWarpper().activateUserAccount(payload, PropertiesUtil.getProperty("SOA_URL_ACTIVATE_ACCOUNT"), PropertiesUtil.getProperty("SOA_USERNAME"), PropertiesUtil.getProperty("SOA_PASSWORD"));
			if (outputPayload != null) {
				logger.info(outputPayload.getStatus() + " | " + outputPayload.getMessageEN());
				output.setStatus(outputPayload.getStatus());
				output.setMessage_EN(outputPayload.getMessageEN());
				output.setMessage_AR(outputPayload.getMessageAR());
			} else {
				logger.info("Failure  | stub.activateAccount(payload, uc); return NUll");
				output.setStatus("Failure");
			}

		} catch (Exception e) {
			output.setStatus("Failure");
			logger.error("Failure  |" + e.getMessage());

		}
		return output;
	}

	

	

	public AccountDetailOutputVO getAccountDetail(AccountDetailInputVO input) {
		
		AccountDetailOutputVO outputVo = new AccountDetailOutputVO();
		com.uaq.proxies.getaccountdetailsbpel_client_ep.types.InputPayload payload = new com.uaq.proxies.getaccountdetailsbpel_client_ep.types.InputPayload();
		payload.setTypeOfUser(input.getTypeofUser());

		com.uaq.proxies.getaccountdetailsbpel_client_ep.types.IndividualPayload ip = new com.uaq.proxies.getaccountdetailsbpel_client_ep.types.IndividualPayload();
		ip.setMobileNo(StringUtil.isEmpty(input.getMobileNo()) ? EMPTY_STRING : input.getMobileNo());
		// ip.setEmiratesID((input.getEmiratesID())==null?"":input.getEmiratesID());

		ip.setEmiratesID(StringUtils.isBlank(input.getEmiratesID()) ? EMPTY_STRING : input.getEmiratesID());
		ip.setPassportID(StringUtils.isBlank(input.getPassportID()) ? EMPTY_STRING : input.getPassportID());

		com.uaq.proxies.getaccountdetailsbpel_client_ep.types.EstablishmentPayload establishmentREC = new com.uaq.proxies.getaccountdetailsbpel_client_ep.types.EstablishmentPayload();
		establishmentREC.setMobileNo(StringUtil.isEmpty(input.getMobileNo()) ? EMPTY_STRING : input.getMobileNo());
		establishmentREC.setEmiratesCode(StringUtil.isEmpty(input.getEmirate()) ? EMPTY_STRING : input.getEmirate());
		establishmentREC.setTradeLicenseNo(StringUtil.isEmpty(input.getTraedLicence()) ? EMPTY_STRING : input.getTraedLicence());
		payload.setIndividualREC(ip);
		payload.setEstablishmentREC(establishmentREC);

		try {
			com.uaq.proxies.getaccountdetailsbpel_client_ep.types.OutputPayload outputPayload = new WebServiceWarpper().getAccountDetails(payload, PropertiesUtil.getProperty("SOA_URL_GET_ACCOUNT_DETAILS"), PropertiesUtil.getProperty("SOA_USERNAME"), PropertiesUtil.getProperty("SOA_PASSWORD"));
			if (outputPayload != null) {
				logger.info(outputPayload.getStatus() + " | " + outputPayload.getMessageEN());
				if (outputPayload.getStatus().equals(SERVICE_SUCCESS)) {
					com.uaq.proxies.getaccountdetailsbpel_client_ep.types.UserDetails user = outputPayload.getUserDetailsREC();
					com.uaq.proxies.getaccountdetailsbpel_client_ep.types.AccountDetails accountDetail = outputPayload.getAccountDetailsREC();

					outputVo.setFullName(accountDetail.getFirstName());
					outputVo.setCitizenship(String.valueOf(accountDetail.getCountryidofcitizenshipid()));
					outputVo.setResidence(String.valueOf(accountDetail.getCountryidofresidencyid()));
					outputVo.setUserType(accountDetail.getTypeOfUser());
					outputVo.setTradelicensetypeid(accountDetail.getTradelicensetypeid().getValue() == null ? new BigDecimal(0) : accountDetail.getTradelicensetypeid().getValue());
					outputVo.setEmirateID((accountDetail.getEmiratesId()) == null ? EMPTY_STRING : accountDetail.getEmiratesId());
					outputVo.setNationalityId((accountDetail.getNationalityId()) == null ? new BigDecimal(0) : accountDetail.getNationalityId());
					outputVo.setMobileNo(accountDetail.getMobileNo());
					outputVo.setMessage_EN(StringUtil.isEmpty(user.getAccountStatusEN()) ? EMPTY_STRING : user.getAccountStatusEN());
					outputVo.setMessage_AR(StringUtil.isEmpty(user.getAccountStatusAR()) ? EMPTY_STRING : user.getAccountStatusAR());
					outputVo.setSourceId((user.getSourceId()) == null ? EMPTY_STRING : user.getSourceId().toString());
					outputVo.setAccountStatusId((user.getAccountStatusId()) == null ? EMPTY_STRING : user.getAccountStatusId().toString());
					outputVo.setAccountId(user.getAccountId());
					outputVo.setEmirate(accountDetail.getEmirate());
					outputVo.setEmailAddress(user.getEmailAddress());
					outputVo.setMobileno2(accountDetail.getMobileno2().getValue());
					outputVo.setTreadLicenceNo(accountDetail.getTradeLienceNo());
					outputVo.setWebsite(accountDetail.getWebsite().getValue());
					outputVo.setAddress(accountDetail.getAddressline1());
//					outputVo.setTreadLicenceExprDate(accountDetail.getTradeLienceExpiryDate());
					outputVo.setLandLine(accountDetail.getHomePhone().getValue());
					outputVo.setPostbox(accountDetail.getPostbox() == null ? EMPTY_STRING : accountDetail.getPostbox().toString());

				} else if (outputPayload.getStatus().equals("Failure")) {
					outputVo.setMessage_EN(outputPayload.getMessageEN());
					outputVo.setMessage_AR(outputPayload.getMessageAR());
				}
				outputVo.setStatus(outputPayload.getStatus());

			} else {
				logger.info("Failed  | stub.getAccountDetails(payload, uc) return null ");
				outputVo.setStatus(SERVICE_FAILED);

			}

		} catch (Exception e) {
			logger.error("Failed  |" + e.getMessage());
			outputVo.setStatus(SERVICE_FAILED);
		}
		return outputVo;
	}

	// moved to generateOTP service
	/*
	 * public String generateOTP(GenerateOTPInputVO input) { createStub();
	 * String otp = EMPTY_STRING; GenerateOTP.InputPayload payload = new
	 * GenerateOTP.InputPayload(); payload.setTypeOfUser(input.getTypeOfUser());
	 * payload.setAccountId(input.getAccountId());
	 * 
	 * GenerateOTP.IndividualPayload individualPayload = new
	 * GenerateOTP.IndividualPayload();
	 * individualPayload.setEmiratesID(input.getEmirateId());
	 * individualPayload.setMobileNo(input.getMobile());
	 * individualPayload.setPassportID(input.getPassportId());
	 * payload.setIndividualREC(individualPayload); EstablishmentPayload
	 * establishmentPayLoad = new EstablishmentPayload();
	 * establishmentPayLoad.setMobileNo(input.getMobile());
	 * establishmentPayLoad.setTradeLicenseNo(input.getTraedLicenceNumber());
	 * establishmentPayLoad.setEmiratesCode(input.getEmirate());
	 * payload.setEstablishmentREC(establishmentPayLoad);
	 * GenerateOTP.OutputPayload output = null; //In dev is not Deployed Yet try
	 * { output = stub.generateOTP(payload,uc); } catch (RemoteException e) {
	 * logger.error("Failure  |" + e.getMessage());
	 * 
	 * } if (output != null) { otp = output.getStatus(); logger.info("Sucess  |"
	 * + output.getMessage_EN()); } else {
	 * logger.error("Failure | output.getOTPValue() return Null "); }
	 * 
	 * return otp; }
	 */

	public ValidateOTPOutput validateOTP(ValidateOTPInputVO input) {

		ValidateOTPOutput validateOTPOutput = new ValidateOTPOutput();

		InputPayload inputPayload = new InputPayload();
		inputPayload.setAccountId(input.getAccountId());
		inputPayload.setOTPvalue(input.getOtp());

		OutputPayload output = null;
		try {
			output = new WebServiceWarpper().validateOTP(inputPayload, PropertiesUtil.getProperty("SOA_URL_VALIDATE_OTP"), PropertiesUtil.getProperty("SOA_USERNAME"),
					PropertiesUtil.getProperty("SOA_PASSWORD"));
			if (output != null) {
				validateOTPOutput.setStataus(output.getStatus());
				validateOTPOutput.setMessage_EN(output.getMessageEN());
				validateOTPOutput.setMessage_AR(output.getMessageAR());
				validateOTPOutput.setExceucutionStatus(SERVICE_SUCCESS);
				logger.info("Success  |" + output.getMessageEN());
			} else {
				validateOTPOutput.setStataus(SERVICE_FAILED);
				logger.error("Failure | stub.validateOTP(inputPayload, uc); return Null ");
			}

		} catch (Exception e) {
			validateOTPOutput.setStataus(SERVICE_FAILED);
			logger.error("Error Invoking validateOTP |" + e.getMessage());

		}

		return validateOTPOutput;
	}

	public ForgetUserNameOutputVO forgetUserName(ForgetUserNameInputVO inputVO) {

		ForgetUserNameOutputVO outputVO = new ForgetUserNameOutputVO();
		com.uaq.proxies.forgetusernamebpel_client_ep.types.InputPayload input = new com.uaq.proxies.forgetusernamebpel_client_ep.types.InputPayload();
		com.uaq.proxies.forgetusernamebpel_client_ep.types.ApplicantDetailsPayload applicantDetailsPayload = new com.uaq.proxies.forgetusernamebpel_client_ep.types.ApplicantDetailsPayload();
		applicantDetailsPayload.setEmailAddress(StringUtil.isEmpty(inputVO.getEmailAddress()) ? EMPTY_STRING : inputVO.getEmailAddress());
		applicantDetailsPayload.setEmiritesID(StringUtil.isEmpty(inputVO.getEmiritesID()) ? EMPTY_STRING : inputVO.getEmiritesID());
		applicantDetailsPayload.setMobileNo(StringUtil.isEmpty(inputVO.getMobileNo()) ? EMPTY_STRING : inputVO.getMobileNo());
		applicantDetailsPayload.setPassportNo(StringUtil.isEmpty(inputVO.getPassportNo()) ? EMPTY_STRING : inputVO.getPassportNo());

		com.uaq.proxies.forgetusernamebpel_client_ep.types.EstablishmentDetailsPayload establishmentDetailsPayload = new com.uaq.proxies.forgetusernamebpel_client_ep.types.EstablishmentDetailsPayload();

		establishmentDetailsPayload.setTradeLicenseNo(StringUtil.isEmpty(inputVO.getTradeLicenseNo()) ? EMPTY_STRING : inputVO.getTradeLicenseNo());
		establishmentDetailsPayload.setMobileNo(StringUtil.isEmpty(inputVO.getMobileNoEstablishment()) ? EMPTY_STRING : inputVO.getMobileNoEstablishment());
		establishmentDetailsPayload.setEmailAddress(StringUtil.isEmpty(inputVO.getEmailAddressEstablishment()) ? EMPTY_STRING : inputVO.getEmailAddressEstablishment());

		com.uaq.proxies.forgetusernamebpel_client_ep.types.UserDetailsPayload userDetailsPayload = new com.uaq.proxies.forgetusernamebpel_client_ep.types.UserDetailsPayload();
		userDetailsPayload.setApplicantUserDetails(applicantDetailsPayload);
		userDetailsPayload.setEstablishmentUserDetails(establishmentDetailsPayload);

		input.setUserDetails(userDetailsPayload);
		input.setTypeOfUser(inputVO.getUserType());

		try {
			com.uaq.proxies.forgetusernamebpel_client_ep.types.OutputPayload outputPayload = new WebServiceWarpper().forgotUsername(input, PropertiesUtil.getProperty("SOA_URL_FORGOT_USERNAME"),
					PropertiesUtil.getProperty("SOA_USERNAME"), PropertiesUtil.getProperty("SOA_PASSWORD"));
			if (outputPayload != null) {
				outputVO.setMesage_EN(outputPayload.getMessageEN());
				outputVO.setMessage_AR(outputPayload.getMessageAR());
				outputVO.setStatus(outputPayload.getStatus());
				logger.info("Succcess  | " + outputPayload.getMessageEN());
			} else {
				outputVO.setStatus(SERVICE_FAILED);
				logger.error("Failed | stub.forgotUsername(input, uc); return NUll ");
			}
		} catch (Exception e) {
			outputVO.setStatus(SERVICE_FAILED);
			logger.error("Failed | " + e.getMessage());

		}
		return outputVO;
	}

	public ForgetPasswordOutputVO forgetPassword(ForgetPasswordInputVO inputVO) {
		

		ForgetPasswordOutputVO outputVO = new ForgetPasswordOutputVO();
		com.uaq.proxies.checkforgetpasswordaccountvaliditybpel_client_ep.types.InputPayload input = new com.uaq.proxies.checkforgetpasswordaccountvaliditybpel_client_ep.types.InputPayload();
		com.uaq.proxies.checkforgetpasswordaccountvaliditybpel_client_ep.types.ApplicantDetailsPayload applicantDetailsPayload = new com.uaq.proxies.checkforgetpasswordaccountvaliditybpel_client_ep.types.ApplicantDetailsPayload();

		applicantDetailsPayload.setMobileNo(inputVO.getMobile());
		applicantDetailsPayload.setPassportNo(inputVO.getPassportNo());
		applicantDetailsPayload.setUsername(inputVO.getUserName().toLowerCase());
		applicantDetailsPayload.setEmiritesID(inputVO.getEmiritesID());

		com.uaq.proxies.checkforgetpasswordaccountvaliditybpel_client_ep.types.EstablishmentDetailsPayload establishmentDetailsPayload = new com.uaq.proxies.checkforgetpasswordaccountvaliditybpel_client_ep.types.EstablishmentDetailsPayload();

		establishmentDetailsPayload.setTradeLicenseNo(inputVO.getTradeLicence());
		establishmentDetailsPayload.setMobileNo(inputVO.getMobileNumberEstablish());
		establishmentDetailsPayload.setUsername(inputVO.getEstablishUsername().toLowerCase());

		com.uaq.proxies.checkforgetpasswordaccountvaliditybpel_client_ep.types.UserDetailsPayload userDetailsPayload = new com.uaq.proxies.checkforgetpasswordaccountvaliditybpel_client_ep.types.UserDetailsPayload();
		userDetailsPayload.setApplicantUserDetails(applicantDetailsPayload);
		userDetailsPayload.setEstablishmentUserDetails(establishmentDetailsPayload);

		input.setUserDetails(userDetailsPayload);
		input.setTypeOfUser(inputVO.getUserType());

		com.uaq.proxies.checkforgetpasswordaccountvaliditybpel_client_ep.types.OutputPayload outputPayload = null;

		try {
			outputPayload = new WebServiceWarpper().checkForgetPasswordAccountValidity(input, PropertiesUtil.getProperty("SOA_URL_FORGOT_PASSWORD_VALIDITY"),
					PropertiesUtil.getProperty("SOA_USERNAME"), PropertiesUtil.getProperty("SOA_PASSWORD"));
			if (outputPayload != null) {
				logger.info("Success  | " + outputPayload.getMessageEN());
				outputVO.setStatus(outputPayload.getStatus());
				outputVO.setMessage_EN(outputPayload.getMessageEN());
				outputVO.setMessage_AR(outputPayload.getMessageAR());

			} else {
				outputVO.setStatus("Failure");
				logger.error("Failure | stub.forgotPassword(input, uc);  return NULL");
			}

		} catch (Exception e) {
			logger.error("Failure | " + e.getMessage());
			outputVO.setStatus("Failed");

		}

		return outputVO;

	}
}
