package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.TilesViewConstant.*;
import static com.uaq.common.UAQURLConstant.*;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oracle.xmlns.adf.svc.errors.ServiceException;
import com.uaq.command.ActivateFormCommand;
import com.uaq.command.ActivationCommand;
import com.uaq.command.ForgetPasswordCommand;
import com.uaq.command.ForgetUsernameCommand;
import com.uaq.command.RegistrationEstablishmentCommand;
import com.uaq.command.RegistrationGccCitizenCommand;
import com.uaq.command.RegistrationGccResidentCommand;
import com.uaq.command.RegistrationIndividualUAE;
import com.uaq.command.RegistrationIndividualVisitorCommand;
import com.uaq.command.RegistrationUaeResidentCommand;
import com.uaq.command.ValidateOTPCommand;

import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.controller.mapper.RegistrationDataMapper;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.CreateAccountService;
import com.uaq.service.GenerateOTPService;
import com.uaq.service.PortalUtil;
import com.uaq.service.RegistrationService;
import com.uaq.util.StringUtil;
import com.uaq.vo.AccountDetailInputVO;
import com.uaq.vo.AccountDetailOutputVO;
import com.uaq.vo.ActiveAccountInputVO;
import com.uaq.vo.ActiveAccountOutputVO;
import com.uaq.vo.CreateAccountInputVO;
import com.uaq.vo.CreateAccountOutputVO;
import com.uaq.vo.ForgetPasswordInputVO;
import com.uaq.vo.ForgetPasswordOutputVO;
import com.uaq.vo.ForgetUserNameInputVO;
import com.uaq.vo.ForgetUserNameOutputVO;
import com.uaq.vo.GenerateOTPInputVO;
import com.uaq.vo.PageMetadataVO;
import com.uaq.vo.ValidateOTPInputVO;
import com.uaq.vo.ValidateOTPOutput;

/**
 * Controller Class For Registration UI handle
 * 
 * @author Pritam
 * 
 */

@Controller
public class RegistrationController extends BaseController {

	protected static UAQLogger logger = new UAQLogger(RegistrationController.class);

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PortalUtil portalUtil;

	@Autowired
	private CreateAccountService createAccountService;

	@Autowired
	private GenerateOTPService generateOTPService;

	@RequestMapping(value = ViewPath.REGISTRATION_UAE_CITIZEN, method = RequestMethod.GET)
	public String handleRegistrationRequest(HttpServletRequest request, ModelMap modelMap) {
		RegistrationIndividualUAE registrationIndividualUAE = new RegistrationIndividualUAE();

		modelMap.addAttribute("registrationIndividualforUAEBean", registrationIndividualUAE);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		super.handleRequest(request, modelMap);
		Locale locale = new Locale(languageCode);
		String view = INDIVIDUAL_UAE_CITIZEN;
		try {
			portalUtil.emiratesDropDown(modelMap, languageCode);
			portalUtil.nationalDropDown(modelMap, languageCode);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;
		} catch (UAQException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;
		}

		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("individualCitizenLbl", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("individualCitizenLbl", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("individualCitizenLbl", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);

		modelMap.addAttribute(PAGE_LABEL_REG, "individualCitizenLbl");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	@RequestMapping(value = ViewPath.REGISTRATION_UAE_CITIZEN, method = RequestMethod.POST)
	public String submitRegistrationRequest(@ModelAttribute("registrationIndividualforUAEBean") RegistrationIndividualUAE bean, ModelMap modelMap, HttpServletRequest request, BindingResult result) {
		logger.info("In Resgistration UAE Citizen");
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		super.handleRequest(request, modelMap);

		String view = "uae.citizen.registration";
		CreateAccountInputVO input = RegistrationDataMapper.bindingDataTOIndividualUAE(bean);
		input.setTypeofUser(INDIVIDUAL_USER);
		input.setApplicantTypeId(UAE_CITIZEN);
		input.setLanguageId(PortalDataMapper.getLanguageId(languageCode));

		Boolean isValidResult = portalUtil.validateCaptcha(request.getSession().getId(), bean.getCaptchaText(), result);

		if (isValidResult) {
			CreateAccountOutputVO output = createAccountService.createAccount(input);
			if (!(output.getStatus().equals(SERVICE_FAILED))) {
				modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getMessage_EN() : output.getMessage_AR());
				modelMap.addAttribute(MODEL_ATTR_ACCOUNTID, output.getAccountId());
				modelMap.addAttribute(MODEL_ATTR_REQUESTNO, output.getRequestNo());
				view = REGISTRATION_SUCCESS;
			} else {
				logger.error("Execution Failed |registrationIndividualforUAE");
				view = REGISTRATION_SUCCESS;
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			}
		} else {
			try {
				portalUtil.emiratesDropDown(modelMap, languageCode);
				portalUtil.nationalDropDown(modelMap, languageCode);
			} catch (ServiceException e) {
				logger.error(e.getMessage());
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;
			} catch (UAQException e) {
				logger.error(e.getMessage());
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;
			}
		}
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("individualCitizenLbl", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("individualCitizenLbl", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("individualCitizenLbl", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute("registrationIndividualforUAEBean", bean);
		modelMap.addAttribute(PAGE_LABEL_REG, "individualCitizenLbl");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;
	}

	/*
	 * @RequestMapping(value = ViewPath.ACTIVATE_FORM, method =
	 * RequestMethod.GET) public String
	 * handleActivationRequest(@ModelAttribute("activateFormCommand")
	 * ActivateFormCommand activateFormCommand, ModelMap modelMap,
	 * HttpServletRequest request) { logger.info("Activate  FORM Processing");
	 * super.handleRequest(request, modelMap); String languageCode =
	 * request.getParameter("languageCode");
	 * 
	 * modelMap.addAttribute("activateFormCommand", activateFormCommand);
	 * emiratesDropDown(modelMap, languageCode); nationalDropDown(modelMap,
	 * languageCode); return "activate.form"; }
	 */
	// for walkin user
	@RequestMapping(value = ViewPath.ACTIVATE_FORM, method = RequestMethod.POST)
	public String submitActivationRequest(@ModelAttribute("activateFormCommand") ActivateFormCommand activateFormCommand, ModelMap modelMap, HttpServletRequest request,
			RedirectAttributes redirectAttributes, BindingResult result) throws ServiceException, UAQException {
		String view = "";
		logger.info("Activate Form FOR Walkin User");
		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);

		Boolean isValidResult = portalUtil.validateCaptcha(request.getSession().getId(), activateFormCommand.getCaptchaText(), result);

		if (isValidResult) {
			view = "activate.success";
			AccountDetailOutputVO accountInfo = (AccountDetailOutputVO) request.getSession().getAttribute(SESSION_ACCOUNT_INFO);

			ActiveAccountInputVO inputVO = RegistrationDataMapper.setindDataToActivateAccountService(activateFormCommand, accountInfo);
			logger.info("Input Vo after setting from Command=" + inputVO.toString());

			ActiveAccountOutputVO output = registrationService.activateAccount(inputVO);
			if (SERVICE_SUCCESS.equals(output.getStatus())) {
				logger.info("From acyivate account Service=" + output.toString());
				modelMap.addAttribute("validateOTPCommand", new ValidateOTPCommand());
				view = OTP_FORM;
			}
			modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getMessage_EN() : output.getMessage_AR());
		} else {
			modelMap.addAttribute("activateFormCommand", activateFormCommand);
			portalUtil.emiratesDropDown(modelMap, languageCode);
			portalUtil.nationalDropDown(modelMap, languageCode);
			view = ACTIVE_ACCOUNT_FORM_SUBMIT;
		}
		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("activateAcounttxt", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("activateAcounttxt", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("activateAcounttxt", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);

		modelMap.addAttribute(PAGE_LABEL, "activateAcounttxt");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;
	}

	@RequestMapping(value = ViewPath.ACCOUNT_ACTIVATION, method = RequestMethod.GET)
	public String handleAccountActivation(ModelMap modelMap, HttpServletRequest request) {
		logger.info("Activate Activation Form Processing");
		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		String view = ACTIVE_ACCOUNT_FORM;
		try {
			portalUtil.emiratesDropDown(modelMap, languageCode);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;
			modelMap.addAttribute(PAGE_LABEL_REG, "activateAcounttxt");
		} catch (UAQException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;
			modelMap.addAttribute(PAGE_LABEL_REG, "activateAcounttxt");
		}

		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("activateAcounttxt", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("activateAcounttxt", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("activateAcounttxt", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);

		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		modelMap.addAttribute("activation", new ActivationCommand());
		return view;
	}

	@RequestMapping(value = ViewPath.ACCOUNT_ACTIVATION, method = RequestMethod.POST)
	public String handleAccountActivationSubmit(@ModelAttribute("activateAccountCommand") ActivationCommand accountCommand, ModelMap modelMap, HttpServletRequest request) {

		logger.info("Activate Activation POST Request");
		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		String view = EMPTY_STRING;
		AccountDetailInputVO inputVO = new AccountDetailInputVO();
		inputVO.setEmiratesID(StringUtils.isBlank(accountCommand.getEmirateId()) ? EMPTY_STRING : accountCommand.getEmirateId().replaceAll("-", ""));
		inputVO.setPassportID(StringUtils.isBlank(accountCommand.getPassportId()) ? EMPTY_STRING : accountCommand.getPassportId());
		inputVO.setMobileNo(StringUtils.isBlank(accountCommand.getMobileNumber()) ? EMPTY_STRING : accountCommand.getMobileNumber().replaceAll("-", ""));
		inputVO.setTypeofUser(accountCommand.getUserType());
		inputVO.setTraedLicence(StringUtils.isBlank(accountCommand.getTradelicence()) ? EMPTY_STRING : accountCommand.getTradelicence());
		inputVO.setEmirate(accountCommand.getEmirate());

		logger.info("From ACCOUNT ACTIVATION Command=" + inputVO.toString());

		Locale locale = new Locale(languageCode);

		AccountDetailOutputVO accountInfo = registrationService.getAccountDetail(inputVO);
		if (!accountInfo.getStatus().equals(SERVICE_FAILED)) {
			if (accountInfo.getStatus().equals(SERVICE_SUCCESS)) {
				request.getSession().setAttribute(SESSION_ACCOUNT_INFO, accountInfo);
				// for Online User
				if (accountInfo.getSourceId().equals(PORTAL_USER)) {
					logger.info(accountInfo.getStatus() + "  | accountStatusId= " + accountInfo.getAccountStatusId() + "SourceId=" + accountInfo.getSourceId());
					// Not Verified
					if (accountInfo.getAccountStatusId().equals(ACCOUNT_CREATED)) {
						logger.info("Thank you Page Render .Message Got from Service" + accountInfo.getMessage_EN());
						modelMap.addAttribute(RESPONCE_KEY, (messageSource.getMessage("yourAccountIsNotVerified", null, locale)));
						view = ACTIVE_ACCOUNT_SUCESS;
					}// verified OTP send To User
					else if (accountInfo.getAccountStatusId().equals(OTP_SENT)) {
						logger.info("OTP page Render .Message Got from Service" + accountInfo.getMessage_EN());
						modelMap.addAttribute("validateOTPCommand", new ValidateOTPCommand());
						view = OTP_FORM;
					}
					// Email send To User
					else if (accountInfo.getAccountStatusId().equals(EMAIL_SENT)) {
						logger.info("Thank you page Render .Message Got from Service" + accountInfo.getMessage_EN());
						modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? accountInfo.getMessage_EN() : accountInfo.getMessage_AR());
						view = ACTIVE_ACCOUNT_SUCESS;
					}
					// account active
					else if (accountInfo.getAccountStatusId().equals(ACCOUNT_ACTIVATED)) {
						logger.info("Thank you page Render .Message Got from Service" + accountInfo.getMessage_EN());
						modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? accountInfo.getMessage_EN() : accountInfo.getMessage_AR());
						view = ACTIVE_ACCOUNT_SUCESS;
					}

				} else if (accountInfo.getSourceId().equals(WALKIN_USER)) {
					// walkin User
					if (accountInfo.getAccountStatusId().equals(ACCOUNT_CREATED)) {
						logger.info(" Walk In User | activate form render|  accountInfo from Session=" + accountInfo.toString());
						modelMap.addAttribute("activateFormCommand", RegistrationDataMapper.setAccountInfoToActivateFormCommand(accountInfo));
						modelMap.addAttribute("typeOfuser", accountInfo.getUserType());
						String accountFOrmView = ACTIVE_ACCOUNT_FORM_SUBMIT;
						try {
							portalUtil.emiratesDropDown(modelMap, languageCode);
							portalUtil.nationalDropDown(modelMap, languageCode);
						} catch (ServiceException e) {
							logger.error(e.getMessage());
							modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
							accountFOrmView = ACTIVE_ACCOUNT_SUCESS;
						} catch (UAQException e) {
							logger.error(e.getMessage());
							modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
							accountFOrmView = ACTIVE_ACCOUNT_SUCESS;
						}
						view = accountFOrmView;
					} else if (accountInfo.getAccountStatusId().equals(OTP_SENT)) {
						logger.info("OTP page Render .Message Got from Service" + accountInfo.getMessage_EN());
						modelMap.addAttribute("validateOTPCommand", new ValidateOTPCommand());
						view = OTP_FORM;
					}
					// Email send To User
					else if (accountInfo.getAccountStatusId().equals(EMAIL_SENT)) {
						logger.info("Thank you page Render .Message Got from Service" + accountInfo.getMessage_EN());
						modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? accountInfo.getMessage_EN() : accountInfo.getMessage_AR());
						view = ACTIVE_ACCOUNT_SUCESS;
					}
					// account active
					else if (accountInfo.getAccountStatusId().equals(ACCOUNT_ACTIVATED)) {
						logger.info("Thank you page Render .Message Got from Service" + accountInfo.getMessage_EN());
						modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? accountInfo.getMessage_EN() : accountInfo.getMessage_AR());
						view = ACTIVE_ACCOUNT_SUCESS;
					}

				} else {
					logger.info("Other Status  | " + accountInfo.getSourceId());
					modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? accountInfo.getMessage_EN() : accountInfo.getMessage_AR());
					view = ACTIVE_ACCOUNT_SUCESS;
				}
			} else {
				view = ACTIVE_ACCOUNT_SUCESS;
				try {
					portalUtil.emiratesDropDown(modelMap, languageCode);
				} catch (ServiceException e) {
					view = ACTIVE_ACCOUNT_SUCESS;
					logger.error(e.getMessage());
					modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				} catch (UAQException e) {
					view = ACTIVE_ACCOUNT_SUCESS;
					logger.error(e.getMessage());
					modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				}
				modelMap.addAttribute("activation", accountCommand);
				logger.debug("Failure  No value is there | " + accountInfo.getMessage_EN());
				modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? accountInfo.getMessage_EN() : accountInfo.getMessage_AR());
			}
		} else {
			logger.info("Failed  | Service Error");
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = ACTIVE_ACCOUNT_SUCESS;
		}

		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("activateAcounttxt", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("activateAcounttxt", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("activateAcounttxt", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(PAGE_LABEL, "activateAcounttxt");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	/*
	 * @RequestMapping(value = ViewPath.VALIDATE_OTP_PAGE, method =
	 * RequestMethod.GET) public String handleOTPrequest(ModelMap modelMap,
	 * HttpServletRequest request) {
	 * 
	 * super.handleRequest(request, modelMap);
	 * 
	 * logger.info("Validate OTP Form Processing"); ValidateOTPCommand
	 * submitOTPCommand = new ValidateOTPCommand();
	 * modelMap.addAttribute("validateOTPCommand", submitOTPCommand); return
	 * "otp.page";
	 * 
	 * }
	 */

	@RequestMapping(value = ViewPath.VALIDATE_OTP_PAGE, method = RequestMethod.POST)
	public String handleValidateOTP(@ModelAttribute("validateOTPCommand") ValidateOTPCommand validateOTPCommand, ModelMap modelMap, HttpServletRequest request) {
		logger.info("Vallidate OTP Form Submit");
		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		String view = OTP_FORM;
		AccountDetailOutputVO accountInfo = (AccountDetailOutputVO) request.getSession().getAttribute(SESSION_ACCOUNT_INFO);
		if (accountInfo.getAccountId() != null) {
			logger.info("accountInfo from Session=" + accountInfo.toString());
			ValidateOTPInputVO inputVo = new ValidateOTPInputVO();
			inputVo.setAccountId(accountInfo.getAccountId());
			inputVo.setOtp(validateOTPCommand.getOtp());
			ValidateOTPOutput outputmessage = registrationService.validateOTP(inputVo);
			if (outputmessage.getStataus().equals("Failure")) {
				modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputmessage.getMessage_EN() : outputmessage.getMessage_AR());
			} else if (outputmessage.getStataus().equals(SERVICE_FAILED)) {
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			} else {
				view = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + request.getParameter(PARAM_LANGUAGE_CODE) + ACTIVATE_SUCCESS;
				modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputmessage.getMessage_EN() : outputmessage.getMessage_AR());
			}
		}
		// modelMap.addAttribute(RESPONCE_KEY,
		// messageSource.getMessage("userdata.invalid", null, locale));
		modelMap.addAttribute(PAGE_LABEL, "otpvalidation");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");

		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("otpvalidation", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("otpvalidation", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("otpvalidation", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);

		modelMap.addAttribute("validateOTPCommand", new ValidateOTPCommand());
		return view;
	}

	@RequestMapping(value = ViewPath.ACCOUNT_ACTIVATION_SUCCESS, method = RequestMethod.GET)
	public String handleActivateSuccesPage(HttpServletRequest request, ModelMap modelMap) {
		super.handleRequest(request, modelMap);
		String pagable = request.getParameter(PAGE_LABEL);
		String msg = request.getParameter(RESPONCE_KEY);
		modelMap.addAttribute(PAGE_LABEL, pagable);
		modelMap.addAttribute(RESPONCE_KEY, msg);
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage(pagable, null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage(pagable, null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage(pagable, null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);

		return ACTIVE_ACCOUNT_SUCESS;
	}

	@RequestMapping(value = ViewPath.GENERATE_OTP, method = RequestMethod.POST)
	@ResponseBody
	public String handleGenerateOTP(HttpServletRequest request) {

		logger.info("AJAX Request for Generate OTP");

		String otp = null;

		AccountDetailOutputVO accountInfo = (AccountDetailOutputVO) request.getSession().getAttribute(SESSION_ACCOUNT_INFO);

		if (accountInfo != null) {

			logger.info("accountInfo from Session=" + accountInfo.toString());
			GenerateOTPInputVO input = new GenerateOTPInputVO();
			input.setOtp(request.getParameter("otp"));
			input.setAccountId(accountInfo.getAccountId());
			input.setMobile(accountInfo.getMobileNo());
			input.setEmirateId(StringUtil.isEmpty(accountInfo.getEmirateID()) ? EMPTY_STRING : accountInfo.getEmirateID());
			input.setPassportId(StringUtil.isEmpty(accountInfo.getPassportNo()) ? EMPTY_STRING : accountInfo.getPassportNo());
			input.setTypeOfUser(accountInfo.getUserType());
			input.setTraedLicenceNumber((accountInfo.getTreadLicenceNo()) == null ? EMPTY_STRING : accountInfo.getTreadLicenceNo().toString());
			input.setEmirate(accountInfo.getEmirate());

			try {
				otp = generateOTPService.generateOTP(input);
			} catch (Exception e) {
				logger.error("Failed " + e.getMessage());
			}
		}

		return otp;
	}

	@RequestMapping(value = ViewPath.FORGET_USERNAME, method = RequestMethod.GET)
	public String handleForgetUserNamePage(ModelMap modelMap, HttpServletRequest request) {
		logger.info("Forget UserNAme Form Processing");
		super.handleRequest(request, modelMap);
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		modelMap.addAttribute("forgetUsernameCommand", new ForgetUsernameCommand());
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);

		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("header.forget.username", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("header.forget.username", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("header.forget.username", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		return FORGET_USERNAME;

	}

	@RequestMapping(value = ViewPath.FORGET_USERNAME, method = RequestMethod.POST)
	public String handleForgetUserNameSubmit(@ModelAttribute("forgetUsernameCommand") ForgetUsernameCommand forgetUsernameCommand, ModelMap modelMap, HttpServletRequest request) {
		logger.info("Forget Username Form Submit Processing");
		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);

		String view = ACTIVE_ACCOUNT_SUCESS;
		ForgetUserNameInputVO input = new ForgetUserNameInputVO();
		AccountDetailInputVO accountDetailInputVO = new AccountDetailInputVO();
		accountDetailInputVO.setTypeofUser(forgetUsernameCommand.getUserType());
		accountDetailInputVO.setEmiratesID(StringUtils.isBlank(forgetUsernameCommand.getEmiratesId()) ? EMPTY_STRING : forgetUsernameCommand.getEmiratesId().replaceAll("-", ""));
		accountDetailInputVO.setPassportID(StringUtils.isBlank(forgetUsernameCommand.getPassportNo()) ? EMPTY_STRING : forgetUsernameCommand.getPassportNo());
		// accountDetailInputVO.setPassportID(forgetUsernameCommand.getPassportNo());
		accountDetailInputVO.setTraedLicence((forgetUsernameCommand.getTradeLicenceNumber() == null ? EMPTY_STRING : forgetUsernameCommand.getTradeLicenceNumber()));

		if (forgetUsernameCommand.getUserType().equals(ESTABLISMENT_USER)) {
			accountDetailInputVO.setMobileNo(forgetUsernameCommand.getMobileNumberEstablismentCode() + forgetUsernameCommand.getMobileNumberEstablish());
		} else {
			accountDetailInputVO.setMobileNo(forgetUsernameCommand.getMobileNumberIndividualCode() + forgetUsernameCommand.getMobileNumberIndividual());
		}

		AccountDetailOutputVO accountDetailOutputVO = registrationService.getAccountDetail(accountDetailInputVO);
		if (!accountDetailOutputVO.getStatus().equals(SERVICE_FAILED)) {
			if (!StringUtil.isEmpty(accountDetailOutputVO.getAccountStatusId())) {
				if (StringUtil.isEmpty(forgetUsernameCommand.getEmiratesId())) {
					input.setEmiritesID(forgetUsernameCommand.getPassportNo());
				} else {
					input.setEmiritesID(forgetUsernameCommand.getEmiratesId().replaceAll("-", EMPTY_STRING));
				}
				// input.setEmiritesID(forgetUsernameCommand.getEmiratesId());
				input.setMobileNo(forgetUsernameCommand.getMobileNumberIndividualCode() + forgetUsernameCommand.getMobileNumberIndividual());
				input.setPassportNo("");
				input.setUserType(forgetUsernameCommand.getUserType());
				input.setEmailAddress(forgetUsernameCommand.getEmailAddress());
				input.setMobileNoEstablishment(forgetUsernameCommand.getMobileNumberEstablismentCode() + forgetUsernameCommand.getMobileNumberEstablish());
				input.setEmailAddressEstablishment(forgetUsernameCommand.getEmailAddressEstablish());
				input.setTradeLicenseNo(forgetUsernameCommand.getTradeLicenceNumber());

				ForgetUserNameOutputVO outputVO = registrationService.forgetUserName(input);
				if (outputVO.getStatus().equals(SERVICE_FAILED)) {
					modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				} else {
					modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputVO.getMesage_EN() : outputVO.getMessage_AR());
					view = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + ACTIVATE_SUCCESS;
				}

			} else {
				logger.error("Faileure | enter detail are no Account");
				modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? accountDetailOutputVO.getMessage_EN() : accountDetailOutputVO.getMessage_AR());
			}

		} else {
			logger.error("Execution Failed | forget.username");
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
		}

		modelMap.addAttribute(PAGE_LABEL, "header.forget.username");
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("header.forget.username", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("header.forget.username", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("header.forget.username", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	@RequestMapping(value = ViewPath.FORGET_PASSWORD, method = RequestMethod.GET)
	public String handleForgetUserPasswordPage(ModelMap modelMap, HttpServletRequest request) {

		logger.info("Forget Password Form Processing");
		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		modelMap.addAttribute("forgetPasswordCommand", new ForgetPasswordCommand());
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("header.forget.forgotpassword", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("header.forget.forgotpassword", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("header.forget.forgotpassword", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		return FORGET_PASSWORD;

	}

	@RequestMapping(value = ViewPath.FORGET_PASSWORD, method = RequestMethod.POST)
	public String handleForgetUserPasswordSubmit(@ModelAttribute("forgetPasswordCommand") ForgetPasswordCommand forgetPasswordCommand, ModelMap modelMap, HttpServletRequest request) {
		logger.info("ForgetPassword Form Submitting");
		super.handleRequest(request, modelMap);

		String view = ACTIVE_ACCOUNT_SUCESS;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		ForgetPasswordInputVO input = new ForgetPasswordInputVO();
		AccountDetailInputVO accountDetailInputVO = new AccountDetailInputVO();
		accountDetailInputVO.setTypeofUser(forgetPasswordCommand.getUserType());
		accountDetailInputVO.setEmiratesID(StringUtils.isBlank(forgetPasswordCommand.getEmiritesID()) ? EMPTY_STRING : forgetPasswordCommand.getEmiritesID().replaceAll("-", ""));
		accountDetailInputVO.setPassportID(StringUtils.isBlank(forgetPasswordCommand.getPassportNo()) ? EMPTY_STRING : forgetPasswordCommand.getPassportNo());
		accountDetailInputVO.setTraedLicence(forgetPasswordCommand.getTradeLicenseNo());

		if (forgetPasswordCommand.getUserType().equals(INDIVIDUAL_USER)) {
			accountDetailInputVO.setMobileNo(forgetPasswordCommand.getMobileCodeIndividual() + forgetPasswordCommand.getMobileNumberIndividual());
		} else {
			accountDetailInputVO.setMobileNo(forgetPasswordCommand.getMobileCodeEstablish() + forgetPasswordCommand.getMobileNumberEstablish());
		}
		AccountDetailOutputVO accountDetailOutputVO = registrationService.getAccountDetail(accountDetailInputVO);
		if (!accountDetailOutputVO.getStatus().equals("Failed")) {
			if (!StringUtil.isEmpty(accountDetailOutputVO.getAccountStatusId())) {
				if (accountDetailOutputVO.getAccountStatusId().equals(ACCOUNT_ACTIVATED) || accountDetailOutputVO.getAccountStatusId().equals(ACCOUNT_LOCKED)) {
					input.setMobile(forgetPasswordCommand.getMobileNumberIndividual());
					input.setUserType(forgetPasswordCommand.getUserType());
					input.setUserName(forgetPasswordCommand.getUsername());
					input.setPassportNo(EMPTY_STRING);
					input.setMobile(forgetPasswordCommand.getMobileCodeIndividual() + forgetPasswordCommand.getMobileNumberIndividual());
					input.setMobileNumberEstablish(forgetPasswordCommand.getMobileCodeEstablish() + forgetPasswordCommand.getMobileNumberEstablish());
					input.setEstablishUsername(forgetPasswordCommand.getEstablishUsername());
					input.setTradeLicence(forgetPasswordCommand.getTradeLicenseNo());
					// input.setEmiritesID(forgetPasswordCommand.getEmiritesID());
					if (StringUtil.isEmpty(forgetPasswordCommand.getEmiritesID())) {
						input.setEmiritesID(forgetPasswordCommand.getPassportNo());
					} else {
						input.setEmiritesID(forgetPasswordCommand.getEmiritesID().replaceAll("-", EMPTY_STRING));
					}
					ForgetPasswordOutputVO outputVO = registrationService.forgetPassword(input);
					/* Checking the forgetPassword execution */
					if (outputVO.getStatus().equals(SERVICE_FAILED)) {
						logger.error("Excution Failed   ");
						modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
						view = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + request.getParameter(PARAM_LANGUAGE_CODE) + ACTIVATE_SUCCESS;
					} else {
						logger.debug("Excetuion Success  | " + outputVO.getMessage_EN());
						modelMap.addAttribute(RESPONCE_KEY, languageCode.equals(LANG_ENGLISH) ? outputVO.getMessage_EN() : outputVO.getMessage_AR());
					}
					/*
					 * else condition for account status 4 or 5 .so it can be
					 * change password
					 */
				} else {
					// custom message for status 1.2 3
					logger.debug("Excetuion Success  | Status=" + accountDetailOutputVO.getAccountStatusId());
					modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage("lable.account.not.active", null, locale));

				}

				/* else condition for account exist or not */
			} else {
				logger.debug("Excetuion Success  | Status=" + accountDetailOutputVO.getMessage_EN());
				modelMap.addAttribute(RESPONCE_KEY, languageCode.equals(LANG_ENGLISH) ? accountDetailOutputVO.getMessage_EN() : accountDetailOutputVO.getMessage_AR());
			}
		} else {
			logger.error("Execution Failed | forgotpassword");
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
		}
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("header.forget.forgotpassword", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("header.forget.forgotpassword", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("header.forget.forgotpassword", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(PAGE_LABEL, "header.forget.forgotpassword");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	@RequestMapping(value = ViewPath.REGISTRATION_ESTABLISHMENT, method = RequestMethod.GET)
	public String handleEstablishmentRegistrationRequest(HttpServletRequest request, ModelMap modelMap) {
		modelMap.addAttribute("registrationEstablishmentCommand", new RegistrationEstablishmentCommand());
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		super.handleRequest(request, modelMap);
		String view = "establishment.registration";
		Locale locale = new Locale(languageCode);
		try {
			portalUtil.emiratesDropDown(modelMap, languageCode);
			portalUtil.tradeLicenseDropDown(modelMap, languageCode);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;
		} catch (UAQException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;
		}
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("establishUserTxt", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("establishUserTxt", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("establishUserTxt", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(PAGE_LABEL_REG, "establishmentReg");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	@RequestMapping(value = ViewPath.REGISTRATION_ESTABLISHMENT, method = RequestMethod.POST)
	public String submitEstablishmentRegistrationRequest(@ModelAttribute("registrationEstablishmentCommand") RegistrationEstablishmentCommand estCommand, HttpServletRequest request,
			BindingResult result, ModelMap modelMap) {
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		super.handleRequest(request, modelMap);
		CreateAccountInputVO inputvo = RegistrationDataMapper.bindingDataToEstablisment(estCommand);
		inputvo.setLanguageId(PortalDataMapper.getLanguageId(languageCode));

		String view = "establishment.registration";

		Boolean isValidResult = portalUtil.validateCaptcha(request.getSession().getId(), estCommand.getCaptchaText(), result);
		if (isValidResult) {
			CreateAccountOutputVO output = createAccountService.createAccount(inputvo);
			if (!(output.getStatus().equals("Failed"))) {
				logger.info("Return Success | Forrword Request to registrationsucces.jsp");
				modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getMessage_EN() : output.getMessage_AR());
				modelMap.addAttribute(MODEL_ATTR_ACCOUNTID, output.getAccountId());
				modelMap.addAttribute(MODEL_ATTR_REQUESTNO, output.getRequestNo());
				view = REGISTRATION_SUCCESS;
			} else {
				logger.error("Execution Failed | establishmentReg");
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;
			}
		} else {
			modelMap.addAttribute("registrationEstablishmentCommand", estCommand);
			try {
				portalUtil.emiratesDropDown(modelMap, languageCode);
				portalUtil.tradeLicenseDropDown(modelMap, languageCode);
			} catch (ServiceException e) {
				logger.error(e.getMessage());
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;
			} catch (UAQException e) {
				logger.error(e.getMessage());
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;
			}
		}
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("establishUserTxt", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("establishUserTxt", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("establishUserTxt", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(PAGE_LABEL_REG, "establishmentReg");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	@RequestMapping(value = ViewPath.REGISTRATION_GCC_CITIZEN, method = RequestMethod.GET)
	public String handleGccCitizenRegistrationRequest(HttpServletRequest request, ModelMap modelMap) {
		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		modelMap.addAttribute("registrationGccCitizenCommand", new RegistrationGccCitizenCommand());
		String view = "gcc.citizen.registration";
		try {
			portalUtil.gccDropDown(modelMap, languageCode);
			portalUtil.emiratesDropDown(modelMap, languageCode);
			portalUtil.nationalDropDown(modelMap, languageCode);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;

		} catch (UAQException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;

		}
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("individualGccCitizen", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("individualGccCitizen", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("individualGccCitizen", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(PAGE_LABEL_REG, "individualGccCitizen");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	@RequestMapping(value = ViewPath.REGISTRATION_GCC_CITIZEN, method = RequestMethod.POST)
	public String submitGccCitizenRegistrationRequest(@ModelAttribute("registrationGccCitizenCommand") RegistrationGccCitizenCommand gccCitizenCommand, HttpServletRequest request,
			BindingResult result, ModelMap modelMap) {
		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		super.handleRequest(request, modelMap);
		CreateAccountInputVO inputvo = RegistrationDataMapper.BindingDataTogccCitizen(gccCitizenCommand);
		inputvo.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
		String view = "gcc.citizen.registration";
		Boolean isValidResult = portalUtil.validateCaptcha(request.getSession().getId(), gccCitizenCommand.getCaptchaText(), result);
		if (isValidResult) {
			CreateAccountOutputVO output = createAccountService.createAccount(inputvo);
			if (!(output.getStatus().equals("Failed"))) {
				logger.info("Return Success | Forrword Request to registrationsucces.jsp");
				modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getMessage_EN() : output.getMessage_AR());
				modelMap.addAttribute(MODEL_ATTR_ACCOUNTID, output.getAccountId());
				modelMap.addAttribute(MODEL_ATTR_REQUESTNO, output.getRequestNo());
				view = REGISTRATION_SUCCESS;
			} else {
				logger.error("Execution Failed | individualGccCitizen");
				view = REGISTRATION_SUCCESS;
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			}
		} else {
			modelMap.addAttribute("registrationGccCitizenCommand", gccCitizenCommand);
			try {
				portalUtil.gccDropDown(modelMap, languageCode);
				portalUtil.emiratesDropDown(modelMap, languageCode);
				portalUtil.nationalDropDown(modelMap, languageCode);
			} catch (ServiceException e) {
				logger.error(e.getMessage());
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;

			} catch (UAQException e) {
				logger.error(e.getMessage());
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;

			}
		}
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("individualGccCitizen", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("individualGccCitizen", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("individualGccCitizen", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(PAGE_LABEL_REG, "individualGccCitizen");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	@RequestMapping(value = ViewPath.REGISTRATION_GCC_RESIDENT, method = RequestMethod.GET)
	public String handleGccResidentRegistrationRequest(HttpServletRequest request, ModelMap modelMap) {
		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		modelMap.addAttribute("registrationGccResidentCommand", new RegistrationGccResidentCommand());
		String view = "gcc.resident.registration";
		try {
			portalUtil.nationalDropDown(modelMap, languageCode);
			portalUtil.gccDropDown(modelMap, languageCode);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;
		} catch (UAQException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;
		}
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("individualGccResident", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("individualGccResident", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("individualGccResident", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(PAGE_LABEL_REG, "individualGccResident");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	@RequestMapping(value = ViewPath.REGISTRATION_GCC_RESIDENT, method = RequestMethod.POST)
	public String submitGccResidentRegistrationRequest(@ModelAttribute("registrationGccResidentCommand") RegistrationGccResidentCommand gccResidentCommand, BindingResult result,
			HttpServletRequest request, ModelMap modelMap) {
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		super.handleRequest(request, modelMap);
		CreateAccountInputVO inputvo = RegistrationDataMapper.bindingDataTogccResident(gccResidentCommand);
		inputvo.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
		String view = "gcc.resident.registration";
		Boolean isValidResult = portalUtil.validateCaptcha(request.getSession().getId(), gccResidentCommand.getCaptchaText(), result);
		if (isValidResult) {
			CreateAccountOutputVO output = createAccountService.createAccount(inputvo);
			if (!(output.getStatus().equals("Failed"))) {
				logger.info("Return Success | Forrword Request to registrationsucces.jsp");
				modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getMessage_EN() : output.getMessage_AR());
				modelMap.addAttribute(MODEL_ATTR_ACCOUNTID, output.getAccountId());
				modelMap.addAttribute(MODEL_ATTR_REQUESTNO, output.getRequestNo());
				view = REGISTRATION_SUCCESS;
			} else {
				logger.error("Execution Failed | individualGccResident");
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;
			}
		} else {
			modelMap.addAttribute("registrationGccResidentCommand", gccResidentCommand);
			try {
				portalUtil.nationalDropDown(modelMap, languageCode);
				portalUtil.gccDropDown(modelMap, languageCode);
			} catch (ServiceException e) {
				logger.error(e.getMessage());
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;
			} catch (UAQException e) {
				logger.error(e.getMessage());
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;
			}
		}
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("individualGccResident", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("individualGccResident", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("individualGccResident", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(PAGE_LABEL_REG, "individualGccResident");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	@RequestMapping(value = ViewPath.REGISTRATION_UAE_RESIDENT, method = RequestMethod.GET)
	public String handleUAEResidentRegistrationRequest(HttpServletRequest request, ModelMap modelMap) {
		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		modelMap.addAttribute("registrationUaeResidentCommand", new RegistrationUaeResidentCommand());
		String view = "uae.resident.registration";
		try {
			portalUtil.nationalDropDown(modelMap, languageCode);
			portalUtil.emiratesDropDown(modelMap, languageCode);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;
		} catch (UAQException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;
		}

		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("individualResidentLbl", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("individualResidentLbl", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("individualResidentLbl", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(PAGE_LABEL_REG, "individualResidentLbl");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	@RequestMapping(value = ViewPath.REGISTRATION_UAE_RESIDENT, method = RequestMethod.POST)
	public String submitUAEResidentRegistrationRequest(@ModelAttribute("registrationUaeResidentCommand") RegistrationUaeResidentCommand uaeResidentCommand, BindingResult result,
			HttpServletRequest request, ModelMap modelMap) {
		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		CreateAccountInputVO inputvo = RegistrationDataMapper.bindingDataTouaeResident(uaeResidentCommand);
		inputvo.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
		String view = "uae.resident.registration";
		Boolean isValidResult = portalUtil.validateCaptcha(request.getSession().getId(), uaeResidentCommand.getCaptchaText(), result);
		if (isValidResult) {
			CreateAccountOutputVO output = createAccountService.createAccount(inputvo);

			if (!(output.getStatus().equals("Failed"))) {
				logger.info("Return Success | Forrword Request to registrationsucces.jsp");
				modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getMessage_EN() : output.getMessage_AR());
				modelMap.addAttribute(MODEL_ATTR_ACCOUNTID, output.getAccountId());
				modelMap.addAttribute(MODEL_ATTR_REQUESTNO, output.getRequestNo());
				view = REGISTRATION_SUCCESS;
			} else {
				logger.error("Execution Failed | individualResident");
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;
			}
		} else {
			modelMap.addAttribute("registrationUaeResidentCommand", uaeResidentCommand);
			try {
				portalUtil.nationalDropDown(modelMap, languageCode);
				portalUtil.emiratesDropDown(modelMap, languageCode);
			} catch (ServiceException e) {
				logger.error(e.getMessage());
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;
			} catch (UAQException e) {
				logger.error(e.getMessage());
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;
			}
		}
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("individualResidentLbl", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("individualResidentLbl", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("individualResidentLbl", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(PAGE_LABEL_REG, "individualResidentLbl");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	@RequestMapping(value = ViewPath.REGISTRATION_INDIVIDUAL_VISITOR, method = RequestMethod.GET)
	public String handleVisitorRegistrationRequest(HttpServletRequest request, ModelMap modelMap) {
		super.handleRequest(request, modelMap);
		String view = "visitor.registration";
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		modelMap.addAttribute("registrationIndividualVisitorCommand", new RegistrationIndividualVisitorCommand());

		try {
			portalUtil.nationalDropDown(modelMap, languageCode);
			portalUtil.emiratesDropDown(modelMap, languageCode);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;
		} catch (UAQException e) {
			logger.error(e.getMessage());
			modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
			view = REGISTRATION_SUCCESS;
		}

		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("indivlVisitor", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("indivlVisitor", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("indivlVisitor", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(PAGE_LABEL_REG, "indivlVisitor");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	@RequestMapping(value = ViewPath.REGISTRATION_INDIVIDUAL_VISITOR, method = RequestMethod.POST)
	public String submitVisitorRegistrationRequest(@ModelAttribute("registrationIndividualVisitorCommand") RegistrationIndividualVisitorCommand uaeVisitorCommand, BindingResult result,
			HttpServletRequest request, ModelMap modelMap) {
		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		CreateAccountInputVO inputvo = RegistrationDataMapper.bindingTouaeVisitor(uaeVisitorCommand);
		inputvo.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
		String view = "visitor.registration";
		Boolean isValidResult = portalUtil.validateCaptcha(request.getSession().getId(), uaeVisitorCommand.getCaptchaText(), result);
		if (isValidResult) {
			CreateAccountOutputVO output = createAccountService.createAccount(inputvo);

			if (!(output.getStatus().equals("Failed"))) {
				logger.info("Return Success | Forward Request to registrationsucces.jsp");
				modelMap.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getMessage_EN() : output.getMessage_AR());
				modelMap.addAttribute(MODEL_ATTR_ACCOUNTID, output.getAccountId());
				modelMap.addAttribute(MODEL_ATTR_REQUESTNO, output.getRequestNo());
				view = REGISTRATION_SUCCESS;
			} else {
				modelMap.addAttribute("message", messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				logger.error("Execution Failed | indivlVisitor");
				view = REGISTRATION_SUCCESS;
			}
		} else {
			modelMap.addAttribute("registrationIndividualVisitorCommand", uaeVisitorCommand);
			try {
				portalUtil.nationalDropDown(modelMap, languageCode);
				portalUtil.emiratesDropDown(modelMap, languageCode);
			} catch (ServiceException e) {
				logger.error(e.getMessage());
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;
			} catch (UAQException e) {
				logger.error(e.getMessage());
				modelMap.addAttribute(RESPONCE_KEY, messageSource.getMessage(SERVICE_ERROR_MESG_KEY, null, locale));
				view = REGISTRATION_SUCCESS;
			}
		}
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("indivlVisitor", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("indivlVisitor", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("indivlVisitor", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(PAGE_LABEL_REG, "indivlVisitor");
		modelMap.addAttribute(PAGE_LABEL_REG, "indivlVisitor");
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return view;

	}

	@RequestMapping(value = ViewPath.REGISTRATION_LANDING, method = RequestMethod.GET)
	public String handleRegistrationLanding(HttpServletRequest request, ModelMap modelMap) {
		super.handleRequest(request, modelMap);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("registration", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("registration", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("registration", null, locale));
		modelMap.addAttribute(PAGE_META_DATA, pageMetadataVO);
		modelMap.addAttribute(PAGE_LABEL_REG, "indivlVisitor");
		return "registration.landing";
	}

	@RequestMapping(value = ViewPath.REGISTRATION_TC, method = RequestMethod.GET)
	public String handleTermsCondition(HttpServletRequest request, ModelMap modelMap) {
		super.handleRequest(request, modelMap);
		modelMap.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return "registration.tearms";
	}
}
