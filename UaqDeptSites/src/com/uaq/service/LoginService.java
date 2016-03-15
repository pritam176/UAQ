package com.uaq.service;

import static com.uaq.common.ApplicationConstants.SERVICE_FAILED;
import static com.uaq.common.ApplicationConstants.SERVICE_SUCCESS;
import static com.uaq.common.ApplicationConstants.WS_PASSWORD;
import static com.uaq.common.ApplicationConstants.WS_USERNAME;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import uaq.middleware.service.RegistrationServicePortBindingStub;
import uaq.middleware.service.RegistrationService_PortType;
import uaq.middleware.service.RegistrationService_Service;
import uaq.middleware.service.RegistrationService_ServiceLocator;
import uaq.middleware.service.RegistrationTokenServicePortBindingStub;
import uaq.middleware.service.RegistrationTokenService_PortType;
import uaq.middleware.service.RegistrationTokenService_Service;
import uaq.middleware.service.RegistrationTokenService_ServiceLocator;
import uaq.middleware.service.UserContext;
import GetAccountDetailsFrmAuthToken.AccountDetails;
import GetAccountDetailsFrmAuthToken.InputPayload;
import GetAccountDetailsFrmAuthToken.OutputPayload;
import GetAccountDetailsFrmAuthToken.UserDetails;
import UserLogin.AuthenticationTokenPayload;
import UserLogout.Inputpayload;

import com.uaq.common.ApplicationConstants;
import com.uaq.common.PropertiesUtil;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.AccountDetailTokenInput;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LoginInputVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.LogoutInputVO;
import com.uaq.vo.LogoutOutputVO;
import com.uaq.vo.UserAttachmentsVO;


/**
 * Service Class For Login & Logout Service
 * 
 * @author Pritam
 * 
 */
@Service(value = "loginService")
@Lazy
public class LoginService {
	

	protected static UAQLogger logger = new UAQLogger(LoginService.class);

	private RegistrationService_PortType type;
	private RegistrationService_Service service;
	private RegistrationServicePortBindingStub stub;
	
	@Autowired
	private LookupServiceEN_AR lookupServiceEN_AR;

	UserContext uc =null;

	private void createStub() {
		uc= new UserContext();
		uc.setUsername(ApplicationConstants.WS_USERNAME);
		uc.setPassword(ApplicationConstants.WS_PASSWORD);

		try {
			service = (RegistrationService_Service) new RegistrationService_ServiceLocator();
			type = service.getRegistrationServicePort();
			stub = (RegistrationServicePortBindingStub) type;

		} catch (ServiceException e) {
			logger.error("WebService Error  |" + e.getMessage());
			e.printStackTrace();

		}

	}

	
	/**
	 * Service Methods For Login 
	 * @param LoginInputVO
	 * @return LoginOutputVO
	 * 
	 * @author Pritam
	 * 
	 */
	public LoginOutputVO loginServiceRequest(LoginInputVO input) {
		logger.info("In Login Service");
		
		createStub();
		
		LoginOutputVO outpuVO = new LoginOutputVO();
		UserLogin.InputPayload inputPayload = new UserLogin.InputPayload();
		inputPayload.setLoginUsername(input.getLoginUserName().toLowerCase());
		inputPayload.setPassword(input.getLoginPassword());
		UserLogin.OutputPayload output = null;
		try {
			output = stub.login(inputPayload, uc);
			if (output != null) {
				logger.info("Succes  | " + output.getMessage_EN());
				if (output.getAuthenticationTokenData() != null) {
					AuthenticationTokenPayload authenticationTokenPayload = output.getAuthenticationTokenData();
					outpuVO.setAcountId(authenticationTokenPayload.getAcountId());
					outpuVO.setStatus(authenticationTokenPayload.getStatus());
					outpuVO.setUsername(authenticationTokenPayload.getUsername());
					outpuVO.setTypeOfUser(authenticationTokenPayload.getTypeOfUser());
					outpuVO.setTokenCode(authenticationTokenPayload.getTokenCode());
					outpuVO.setLastUpdatedDate(authenticationTokenPayload.getLastUpdatedDate());
					outpuVO.setCreatedDate(authenticationTokenPayload.getCreatedDate());
				}
				outpuVO.setExecutionStatus(output.getStatus());
				outpuVO.setMessage_AR(output.getMessage_AR());
				outpuVO.setMessage_EN(output.getMessage_EN());

			} else {
				outpuVO.setExecutionStatus(SERVICE_FAILED);
				logger.info("Failure  | stub.login(inputPayload, uc); return null");
			}

		} catch (RemoteException e) {
			logger.error("Failure | " + e.getMessage());
			outpuVO.setExecutionStatus(SERVICE_FAILED);

		}
		return outpuVO;

	}
	/**
	 * Service Methods For Logout 
	 * @param LogoutInputVO
	 * @return LogoutOutputVO
	 * 
	 * @author Pritam
	 * 
	 */

	public LogoutOutputVO logoutServiceRequest(LogoutInputVO inputVO) {
		
		createStub();
		
		UserLogout.Inputpayload input = new Inputpayload();

		UserLogout.AuthenticationTokenPayload authenticationTokenPayload = new UserLogout.AuthenticationTokenPayload();
		authenticationTokenPayload.setAcountId(inputVO.getAcountId());
		authenticationTokenPayload.setCreatedDate(inputVO.getCreatedDate());
		authenticationTokenPayload.setLastUpdatedDate(inputVO.getLastUpdatedDate());
		authenticationTokenPayload.setStatus(inputVO.getStatus());
		authenticationTokenPayload.setTokenCode(inputVO.getTokenCode());
		authenticationTokenPayload.setTypeOfUser(inputVO.getTypeOfUser());
		authenticationTokenPayload.setUsername(inputVO.getUsername().toLowerCase());
		input.setAuthenticationTokenData(authenticationTokenPayload);

		LogoutOutputVO outputVO = new LogoutOutputVO();
		UserLogout.OutputPayload output = null;
		try {
			output = stub.logout(input, uc);
			if (output != null) {
				logger.info("Success | " + output.getMessage_EN());
				outputVO.setStatus(output.getStatus());
				outputVO.setMessage_AR(output.getMessage_AR());
				outputVO.setMessage_EN(output.getMessage_EN());
			} else {
				logger.info("Failure  | stub.logout(input, uc); return null");
				outputVO.setStatus(SERVICE_FAILED);
			}

		} catch (RemoteException e) {
			logger.info("Failure  |" + e.getMessage());
			outputVO.setStatus(SERVICE_FAILED);
		}
		return outputVO;
	}
	
	public AccountDetailTokenOutputVO getDetailFromToken(AccountDetailTokenInput input){
		
		createStub();
		
		AccountDetailTokenOutputVO outputVO =new AccountDetailTokenOutputVO();
		InputPayload authenticationTokenData=new GetAccountDetailsFrmAuthToken.InputPayload();
		GetAccountDetailsFrmAuthToken.AuthenticationTokenPayload tokenInput=new GetAccountDetailsFrmAuthToken.AuthenticationTokenPayload();
		tokenInput.setAcountId(input.getAcountId());
		tokenInput.setCreatedDate(input.getCreatedDate());
		tokenInput.setLastUpdatedDate(input.getLastUpdatedDate());
		tokenInput.setStatus(input.getStatus());
		tokenInput.setTokenCode(input.getTokenCode());
		tokenInput.setTypeOfUser(input.getTypeOfUser());
		tokenInput.setUsername(input.getUsername().toLowerCase());
		authenticationTokenData.setAuthenticationTokenData(tokenInput);
		
		List<uaq.db.si.model.common.AccountattachmentsViewSDO> userAttachmentsList	= lookupServiceEN_AR.getUserAttachments(input.getAcountId());
		
		try {
			OutputPayload output=stub.getAccountDetailsFromToken(authenticationTokenData, uc);
			//accountattachments--find
			if(output !=null){
				if(output.getAccountDetailsREC()!=null){
					AccountDetails accountRec=output.getAccountDetailsREC();
					UserDetails userrec=output.getUserDetailsREC();
					outputVO.setAccountId(userrec.getAccountId());
					outputVO.setAccountStatus_AR(userrec.getAccountStatus_AR());
					outputVO.setAccountStatus_EN(userrec.getAccountStatus_EN());
					outputVO.setAccountStatusId(userrec.getAccountStatusId()==null?"":userrec.getAccountStatusId().toString());
					outputVO.setAddressline1(accountRec.getAddressline1());
					outputVO.setAddressline2(accountRec.getAddressline2());
					outputVO.setApplicanttype_AR(accountRec.getApplicanttype_AR());
					outputVO.setApplicanttype_EN(accountRec.getApplicanttype_EN());
					outputVO.setApplicanttypeid(accountRec.getApplicanttypeid()==null?"1":accountRec.getApplicanttypeid().toString());
					outputVO.setClannumber(accountRec.getClannumber());
					outputVO.setCountryidofcitizenship_AR(accountRec.getCountryidofcitizenship_AR());
					outputVO.setCountryidofcitizenship_EN(accountRec.getCountryidofcitizenship_EN());
					outputVO.setCountryidofcitizenshipid(accountRec.getCountryidofcitizenshipid()==null?"":accountRec.getCountryidofcitizenshipid().toString());
					outputVO.setCountryidofresidency_AR(accountRec.getCountryidofresidency_AR());
					outputVO.setCountryidofresidency_EN(accountRec.getCountryidofresidency_EN());
					outputVO.setCountryidofresidencyid(accountRec.getCountryidofresidencyid()==null?"":accountRec.getCountryidofresidencyid().toString());
					outputVO.setCreatedby(accountRec.getCreatedby());
					outputVO.setCreatedDate(accountRec.getCreatedDate());
					outputVO.setDob(accountRec.getDob());
					outputVO.setEidaexpirydate(accountRec.getEidaexpirydate());
					outputVO.setEidaExpiryDate(accountRec.getEidaExpiryDate());
					outputVO.setEmailAddress(accountRec.getEmailAddress());
					outputVO.setEmailNotificationEnabled(userrec.getEmailNotificationEnabled()==null?"0":userrec.getEmailNotificationEnabled().toString());
					outputVO.setEmirate(accountRec.getEmiratesCode()==null?"":accountRec.getEmiratesCode().toString());
					outputVO.setEmirates_AR(accountRec.getEmirates_AR());
					outputVO.setEmirates_EN(accountRec.getEmirates_EN());
					outputVO.setEmiratesCode(accountRec.getEmiratesCode()==null?"":accountRec.getEmiratesCode().toString());
					outputVO.setEmiratesId(accountRec.getEmiratesId());
					outputVO.setFamilyBookNum(accountRec.getFamilyBookNum());
					outputVO.setFamilynumber(accountRec.getFamilynumber());
					outputVO.setFirstName(accountRec.getFirstName());
					outputVO.setHasfamilybookno(accountRec.getHasfamilybookno()==null?"0":accountRec.getHasfamilybookno().toString());
					outputVO.setHomePhone(accountRec.getHomePhone());
					outputVO.setId(accountRec.getId());
					outputVO.setIssuancedate(accountRec.getIssuancedate());
					outputVO.setLanguageId(accountRec.getLanguageId()==null?"1":accountRec.getLanguageId().toString());
					outputVO.setLastName(accountRec.getLastName());
					outputVO.setLoginusername(userrec.getLoginusername());
					outputVO.setMiddleName(accountRec.getMiddleName());
					outputVO.setMobileNo(accountRec.getMobileNo());
					outputVO.setMobileno2(accountRec.getMobileno2());
					outputVO.setModifiedby(accountRec.getModifiedby());
					outputVO.setModifiedDate(accountRec.getModifiedDate());
					outputVO.setMothername(accountRec.getMothername());
					outputVO.setMothersfathername(accountRec.getMothersfathername());
					outputVO.setNationality_AR(accountRec.getNationality_AR());
					outputVO.setNationality_EN(accountRec.getNationality_EN());
					outputVO.setNationalityId(accountRec.getNationalityId()==null?"":accountRec.getNationalityId().toString());
					outputVO.setPassportExpiryDate(accountRec.getPassportExpiryDate());
					outputVO.setPassportNo(accountRec.getPassportNo());
					outputVO.setPassword(userrec.getPassword());
					outputVO.setPostbox(accountRec.getPostbox()==null?"":accountRec.getPostbox().toString());
					outputVO.setProfileImageId(userrec.getProfileImageId()==null?"0":userrec.getProfileImageId().toString());
					outputVO.setPushNotificationEnabled(userrec.getPushNotificationEnabled()==null?"0":userrec.getPushNotificationEnabled().toString());
					outputVO.setResidenceNo(accountRec.getResidenceNo());
					outputVO.setResidenseExpiryDate(accountRec.getResidenseExpiryDate());
					outputVO.setRetryCount(userrec.getRetryCount()==null?"0":userrec.getRetryCount().toString());
					outputVO.setSmsNotificationEnabled(userrec.getSmsNotificationEnabled()==null?"0":userrec.getSmsNotificationEnabled().toString());
					outputVO.setSourceId(userrec.getSmsNotificationEnabled()==null?"0":userrec.getSmsNotificationEnabled().toString());
					outputVO.setSubcribetonewsletterflag(accountRec.getSubcribetonewsletterflag()==null?"0":accountRec.getSubcribetonewsletterflag().toString());
					outputVO.setTermsConditionsFlag(userrec.getTermsConditionsFlag()==null?"0":userrec.getTermsConditionsFlag().toString());
					outputVO.setTownname(accountRec.getTownname());
					outputVO.setTownnumber(accountRec.getTownnumber());
					outputVO.setTradelicensetype_AR(accountRec.getTradelicensetype_AR());
					outputVO.setTradelicensetype_EN(accountRec.getTradelicensetype_EN());
					outputVO.setTradelicensetypeid(accountRec.getTradelicensetypeid()==null?"":accountRec.getTradelicensetypeid().toString());
					outputVO.setTradeLienceExpiryDate(accountRec.getTradeLienceExpiryDate());
					outputVO.setTradeLienceNo(accountRec.getTradeLienceNo());
					outputVO.setTribename(accountRec.getTribename());
					outputVO.setTypeOfUser(accountRec.getTypeOfUser());
					outputVO.setUserName(userrec.getUserName());
					outputVO.setWebsite(accountRec.getWebsite());
					
					//UserAttachments
					outputVO.setUserAttachmentsList(getUserAttachmentsList(userAttachmentsList));
				}
				outputVO.setExecutionStatus(output.getStatus());
				outputVO.setMessage_EN(output.getMessage_EN());
				outputVO.setMessage_AR(output.getMessage_AR());
				logger.info(output.getStatus()+"  |  "+output.getMessage_EN());
			}
			else{
				outputVO.setExecutionStatus(SERVICE_FAILED);
				logger.error("Failure  |  stub.getAccountDetailsFromToken(authenticationTokenData, uc); return null");
			}
		} catch (RemoteException e) {
			outputVO.setExecutionStatus(SERVICE_FAILED);
			logger.error("Failure  |"+ e.getMessage());
		}

		return outputVO;
	}
	
	
	private List<UserAttachmentsVO> getUserAttachmentsList(List<uaq.db.si.model.common.AccountattachmentsViewSDO> userAttachmentsList){
		
		List<UserAttachmentsVO>userAttachmentVoList = new ArrayList<UserAttachmentsVO>();
		UserAttachmentsVO  userAttachmentsVO ;
		
		for(uaq.db.si.model.common.AccountattachmentsViewSDO attachment : userAttachmentsList){
			userAttachmentsVO = new UserAttachmentsVO();
			//userAttachmentsVO.setAttachmentName(attachment.getAttachemntname().getValue());
			userAttachmentsVO.setAttachmentName(PropertiesUtil.getProperty(attachment.getAttachementtype().getValue()));
			userAttachmentsVO.setAttachmentType(attachment.getAttachementtype().getValue());
			userAttachmentsVO.setAttachmentUrl(attachment.getUrl().getValue());
			userAttachmentsVO.setAccountattachmentId(String.valueOf(attachment.getAccountattachmentid()));
			userAttachmentsVO.setAccountId(attachment.getAccountid().getValue());
			userAttachmentsVO.setContentId(attachment.getContentid().getValue());
			userAttachmentVoList.add(userAttachmentsVO);
		}
		
		return userAttachmentVoList;
	}
	public boolean validateToken(AccountDetailTokenInput accountDetailTokenInput){
		
		boolean valid=false;
		
		RegistrationTokenService_PortType type;
		 RegistrationTokenService_Service service;
		 RegistrationTokenServicePortBindingStub stub;
		 	service=(RegistrationTokenService_Service) new RegistrationTokenService_ServiceLocator();
		 	uc = new UserContext();
		 	uc.setUsername(WS_USERNAME);
			uc.setPassword(WS_PASSWORD);
		 
		 ValidateAuthenticationToken.InputPayload arg0 =new ValidateAuthenticationToken.InputPayload();
		 
		 arg0.setServiceID("");
		 ValidateAuthenticationToken.AuthenticationTokenPayload authenticationTokenPayload =new ValidateAuthenticationToken.AuthenticationTokenPayload();
		 authenticationTokenPayload.setAcountId(accountDetailTokenInput.getAcountId());
		 authenticationTokenPayload.setTokenCode(accountDetailTokenInput.getTokenCode());
		 authenticationTokenPayload.setTypeOfUser(accountDetailTokenInput.getTypeOfUser());
		 authenticationTokenPayload.setUsername(accountDetailTokenInput.getUsername().toLowerCase());
		 authenticationTokenPayload.setStatus(accountDetailTokenInput.getStatus());
		 authenticationTokenPayload.setCreatedDate(accountDetailTokenInput.getCreatedDate());
		 authenticationTokenPayload.setLastUpdatedDate(accountDetailTokenInput.getLastUpdatedDate());
		 arg0.setAuthenticationTokenData(authenticationTokenPayload);
		
		   try {
			type=service.getRegistrationTokenServicePort();
			stub=(RegistrationTokenServicePortBindingStub)type;
			ValidateAuthenticationToken.OutputPayload ouput=stub.validateToken(arg0, uc);
			if(ouput !=null){
				if(ouput.getStatus().equals(SERVICE_SUCCESS)){
					valid = true;
					logger.debug("Responce From  validate Token Service="+ouput.getStatus());
				}
			}
		} catch (ServiceException e) {
			logger.error("Failure   | "+e.getMessage());
			
		} catch (RemoteException e) {
			
			logger.error("Failure   | "+e.getMessage());
		}
		   
		return valid;
		
	}
}
