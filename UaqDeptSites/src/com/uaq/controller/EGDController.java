package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.TilesViewConstant.*;
import static com.uaq.common.UAQURLConstant.*;
import static com.uaq.common.ServiceNameConstant.*;

import java.util.Locale;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.xmlns.adf.svc.errors.ServiceException;
import com.uaq.command.NewSupplierRegistrationCommand;

import com.uaq.common.PropertiesUtil;
import com.uaq.common.ServiceNameConstant;
import com.uaq.common.ViewPath;
import com.uaq.controller.mapper.EGDDataMapper;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.EGDRequestService;

import com.uaq.service.PortalUtil;
import com.uaq.service.ReportsService;
import com.uaq.util.StringUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.NewSupplierRegistrationVO;
import com.uaq.vo.PageMetadataVO;
import com.uaq.vo.UserDeatilVO;

@Controller
public class EGDController extends BaseController {
	@Autowired
	private PortalUtil portalUtil;

	@Autowired
	private EGDRequestService eGDRequestService;

	@Autowired
	private MessageSource messageSource;

	public static transient UAQLogger logger = new UAQLogger(EGDController.class);

	/***
	 * Handler Method New Supplier Registration
	 * URL-/uaq/newSupplierRegistrationPage.html
	 ***/

	@RequestMapping(value = ViewPath.EGD_NEW_SUPPLIER_REGISTRATION_PAGE, method = RequestMethod.GET)
	public String handleNewSupplier(HttpServletRequest request, HttpServletResponse responce, ModelMap model) {
		logger.enter("handle New Supplier Registration");
		super.handleRequest(request, model);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		String viewname = "";
		LoginOutputVO logininfo = null;
		// For E-Service RHS
		String departmentServicesJSON = super.getDepartmentServicesJSON(languageCode);
		model.addAttribute("departmentServicesJSON", departmentServicesJSON);
		Locale locale = new Locale(languageCode);

		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.egd.newsupplierregistrationpage.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.egd.newsupplierregistrationpage.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.egd.newsupplierregistrationpage.pagekeyword", null, locale));

		try {
			portalUtil.emiratesDropDown(model, languageCode);
		} catch (ServiceException e) {
			logger.error("emiratesDropDown service error" + e.getMessage());

		} catch (UAQException e) {
			logger.error("emiratesDropDown service error" + e.getMessage());
		}

		String typeOfUser = "";

		if (portalUtil.isMobile(request, responce)) {
			logger.enter("Mobile App requested");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			logger.debug("Mobile User Detail- Login Info" + logininfo.toString());
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					typeOfUser = logininfo.getTypeOfUser();
					model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, typeOfUser);
					if (typeOfUser.equals(ESTABLISMENT_USER)) {
						request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
						AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
						logger.debug("Account Detail" + accountDetailfromToken.toString());
						logger.info("Mobile    |    Token Validated  | New Supplier.newreq");
						if (eGDRequestService.isValidSupplier(NEW_SUPPLIER_REGISTRATION, logininfo.getAcountId())) {
							portalUtil.lookUpDataDropDownforEGDSuppCategoryEN_AR(model, languageCode);
							portalUtil.lookUpDataDropDownforEgdSuppRegTypesEN_AR(model, languageCode);
							model.addAttribute("newSupplierRegistrationCommand", EGDDataMapper.setuserDataToNewSupplierCommand(accountDetailfromToken));
							model.addAttribute("userAttachmentsList", accountDetailfromToken.getUserAttachmentsList());
							viewname = "egd.newSupplier.mobile";
						} else {
							viewname = MOBILE_THANKU_VIEW;
							model.addAttribute("message", messageSource.getMessage("egd.supplier.status", null, locale));
						}

					}
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				if (StringUtil.isEmpty(typeOfUser)) {
					request.getSession().invalidate();
					logger.info("Mobile  | Failure    |  User Not Loged In   ");
				}
			}

		} else {
			logger.enter("Portal SIte Requested");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					typeOfUser = logininfo.getTypeOfUser();
					model.addAttribute("typeOfUser", typeOfUser);
					if (typeOfUser.equals("2")) {
						AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
						logger.debug("Account Detail" + accountDetailfromToken.toString());
						logger.info("Desktop    |    Token Validated  | New Supplier.newreq");
						if (eGDRequestService.isValidSupplier(NEW_SUPPLIER_REGISTRATION, logininfo.getAcountId())) {
							portalUtil.lookUpDataDropDownforEGDSuppCategoryEN_AR(model, languageCode);
							portalUtil.lookUpDataDropDownforEgdSuppRegTypesEN_AR(model, languageCode);
							model.addAttribute("newSupplierRegistrationCommand", EGDDataMapper.setuserDataToNewSupplierCommand(accountDetailfromToken));
							model.addAttribute("userAttachmentsList", accountDetailfromToken.getUserAttachmentsList());
							viewname = "egd.newSupplier";
						} else {
							viewname = "thankyou.egd";
							model.addAttribute("message", messageSource.getMessage("egd.supplier.status", null, locale));
						}
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				if (StringUtil.isEmpty(typeOfUser)) {
					logger.info("Desktop  |  Failure    |    User Not Loged In ");
					request.getSession().invalidate();
				}

			}
			model.addAttribute(PAGE_LABEL, "label.egd.newSupplier");
			request.setAttribute(PAGE_LABEL, "label.egd.newSupplier");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
			model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		}

		return viewname;

	}

	/***
	 * Handler Method New Supplier Registration POST
	 * URL-/uaq/newSupplierRegistrationPage.html
	 ***/

	@RequestMapping(value = ViewPath.EGD_NEW_SUPPLIER_REGISTRATION_PAGE, method = RequestMethod.POST)
	public String handleNewSupplier(@ModelAttribute("newSupplierRegistrationCommand") NewSupplierRegistrationCommand newSupplierRegistrationCommand, ModelMap model, HttpServletResponse responce,
			HttpServletRequest request) {
		logger.info("New Supplier Registration| handle POST Request");
		super.handleRequest(request, model);
		String viewname = "";

		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.egd.newsupplierregistrationpage.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.egd.newsupplierregistrationpage.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.egd.newsupplierregistrationpage.pagekeyword", null, locale));

		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, responce)) {
			model.addAttribute(ISMOBILE, "true");
			logger.enter("Mobile APp requested");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail from session- Account Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						logger.debug("Account Detail" + accountDetailfromToken.toString());
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						NewSupplierRegistrationVO supplierDetails = EGDDataMapper.getSupplierData(accountDetailfromToken, newSupplierRegistrationCommand);
						supplierDetails.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output = eGDRequestService.submitSupplierRequest(userDetails, supplierDetails);
						if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
							logger.info("Status Id | " + output.getSatausId());
							logger.info("request No | " + output.getRequestNo());
							model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
							model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
							model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
							model.addAttribute(PAYMENT_URL_EXPRESSON, MOBILE_PAYMENT_URL);
							model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
							model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, userDetails.getTypeOfUser());
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							new ReportsService().generateRequestReport(output.getServiceId(), output.getRequestNo().split("-")[3], output.getRequestNo(), userDetails.getUsername(), userDetails.getAccountid(),supplierDetails.getServiceName_En());
						}

					}
				}

			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("mobile | Failure    |  User Not Loged In   ");

			}
		} else {
			model.addAttribute(ISMOBILE, "false");
			logger.info("Portal SIte Requested");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						logger.debug("Account Detail" + accountDetailfromToken.toString());
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						NewSupplierRegistrationVO supplierDetails = EGDDataMapper.getSupplierData(accountDetailfromToken, newSupplierRegistrationCommand);
						supplierDetails.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output = eGDRequestService.submitSupplierRequest(userDetails, supplierDetails);
						if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
							logger.info("Status Id | " + output.getSatausId());
							logger.info("request No | " + output.getRequestNo());
							model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
							model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
							model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
							model.addAttribute(PAYMENT_URL_EXPRESSON, PORTAL_PAYMENT_URL);
							model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, userDetails.getTypeOfUser());
							model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							new ReportsService().generateRequestReport(output.getServiceId(), output.getRequestNo().split("-")[3], output.getRequestNo(), userDetails.getUsername(), userDetails.getAccountid(),supplierDetails.getServiceName_En());
						}
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Failure    |    User is Not Loged In ");
				request.getSession().invalidate();

			}
			model.addAttribute(PAGE_LABEL, "label.egd.newSupplier");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
			model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		}

		return viewname;
	}

	@RequestMapping(value = ViewPath.EGD_RENEW_SUPPLIER_REGISTRATION_PAGE, method = RequestMethod.GET)
	public String handleReNewSupplier(HttpServletRequest request, ModelMap model, HttpServletResponse responce) {
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		super.handleRequest(request, model);
		String viewname = EMPTY_STRING;
		LoginOutputVO logininfo = null;
		String typeOfUser = EMPTY_STRING;
		// For E-Service RHS
		String departmentServicesJSON = super.getDepartmentServicesJSON(languageCode);
		model.addAttribute("departmentServicesJSON", departmentServicesJSON);

		try {
			portalUtil.emiratesDropDown(model, languageCode);
		} catch (ServiceException e) {
			logger.error("emiratesDropDown service error" + e.getMessage());

		} catch (UAQException e) {
			logger.error("emiratesDropDown service error" + e.getMessage());
		}

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.egd.renewsupplierregistrationpage.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.egd.renewsupplierregistrationpage.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.egd.renewsupplierregistrationpage.pagekeyword", null, locale));

		if (portalUtil.isMobile(request, responce)) {
			// mobile Site
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Account Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					typeOfUser = logininfo.getTypeOfUser();
					model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, typeOfUser);
					if (typeOfUser.equals(ESTABLISMENT_USER)) {
						AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
						logger.debug("Account Detail" + accountDetailfromToken.toString());
						request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
						logger.info("Mobile    |    Token Validated  | re New Supplier.newreq");
						if (eGDRequestService.isValidSupplier(RENEW_SUPPLIER_REGISTRATION, logininfo.getAcountId())) {
							portalUtil.lookUpDataDropDownforEGDSuppCategoryEN_AR(model, languageCode);
							portalUtil.lookUpDataDropDownforEgdSuppRegTypesEN_AR(model, languageCode);
							model.addAttribute("newSupplierRegistrationCommand", EGDDataMapper.setuserDataToReNewSupplierCommand(accountDetailfromToken));
							model.addAttribute("userAttachmentsList", accountDetailfromToken.getUserAttachmentsList());
							viewname = "egd.reNewSupplier.mobile";
						} else {
							viewname = MOBILE_THANKU_VIEW;
							model.addAttribute("message", messageSource.getMessage("egd.renewsupplier.status", null, locale));
						}
					}
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				if (StringUtil.isEmpty(typeOfUser)) {
					request.getSession().invalidate();
					logger.info("Mobile  | Failure    |  User Not Loged In   ");
				}

			}

		} else {
			// Desktop Site
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					typeOfUser = logininfo.getTypeOfUser();
					model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, typeOfUser);
					if (typeOfUser.equals(ESTABLISMENT_USER)) {
						AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
						logger.debug("Account Detail" + accountDetailfromToken.toString());
						logger.info("Desktop    |    Token Validated  | New Supplier.newreq");
						if (eGDRequestService.isValidSupplier(RENEW_SUPPLIER_REGISTRATION, logininfo.getAcountId())) {
							portalUtil.lookUpDataDropDownforEGDSuppCategoryEN_AR(model, languageCode);
							portalUtil.lookUpDataDropDownforEgdSuppRegTypesEN_AR(model, languageCode);
							model.addAttribute("newSupplierRegistrationCommand", EGDDataMapper.setuserDataToReNewSupplierCommand(accountDetailfromToken));
							model.addAttribute("userAttachmentsList", accountDetailfromToken.getUserAttachmentsList());
							model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
							viewname = "egd.reNewSupplier";
						} else {
							viewname = "thankyou.egd";
							model.addAttribute("message", messageSource.getMessage("egd.renewsupplier.status", null, locale));
						}
					}

				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				if (StringUtil.isEmpty(typeOfUser)) {
					logger.info("Desktop  |  Failure    |    User Not Loged In ");
					request.getSession().invalidate();
				}

			}
			request.setAttribute(PAGE_LABEL, "label.egd.newSupplier");
			model.addAttribute(PAGE_LABEL, "label.egd.renewSupplier");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		}
		return viewname;
	}

	@RequestMapping(value = ViewPath.EGD_RENEW_SUPPLIER_REGISTRATION_PAGE, method = RequestMethod.POST)
	public String handleRenewSupplierReq(@ModelAttribute("newSupplierRegistrationCommand") NewSupplierRegistrationCommand newSupplierRegistrationCommand, ModelMap model, HttpServletResponse responce,
			HttpServletRequest request) {
		logger.info("ReNew Supplier Registration| handle POST Request");
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		super.handleRequest(request, model);
		LoginOutputVO logininfo = null;
		String viewname = EMPTY_STRING;

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.egd.renewsupplierregistrationpage.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.egd.renewsupplierregistrationpage.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.egd.renewsupplierregistrationpage.pagekeyword", null, locale));
		if (portalUtil.isMobile(request, responce)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail from session- loginInfo" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						logger.debug("Account Detail" + accountDetailfromToken.toString());
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						NewSupplierRegistrationVO supplierDetails = EGDDataMapper.getSupplierData(accountDetailfromToken, newSupplierRegistrationCommand);
						supplierDetails.setServiceId(ServiceNameConstant.RENEW_SUPPLIER_REGISTRATION);
						supplierDetails.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output = eGDRequestService.submitSupplierRequest(userDetails, supplierDetails);
						if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
							logger.info("Status Id | " + output.getSatausId());
							logger.info("request No | " + output.getRequestNo());
							model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
							model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
							model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
							new ReportsService().generateRequestReport(output.getServiceId(), output.getRequestNo().split("-")[3], output.getRequestNo(), userDetails.getUsername(), userDetails.getAccountid(),"Re New Supplier Registration");
						}
					}
				}

			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("mobile | Failure    |  User Not Loged In   ");

			}
		} else {
			model.addAttribute(ISMOBILE, "false");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						logger.debug("Account Detail" + accountDetailfromToken.toString());
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						NewSupplierRegistrationVO supplierDetails = EGDDataMapper.getSupplierData(accountDetailfromToken, newSupplierRegistrationCommand);
						supplierDetails.setServiceId(ServiceNameConstant.RENEW_SUPPLIER_REGISTRATION);
						supplierDetails.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output = eGDRequestService.submitSupplierRequest(userDetails, supplierDetails);
						if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
							logger.info("Status Id | " + output.getSatausId());
							logger.info("request No | " + output.getRequestNo());
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
							model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
							model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
							model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
							new ReportsService().generateRequestReport(output.getServiceId(), output.getRequestNo().split("-")[3], output.getRequestNo(), userDetails.getUsername(), userDetails.getAccountid(),"Re New Supplier Registration");
						}
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Failure    |    User is Not Loged In ");
				request.getSession().invalidate();

			}
			model.addAttribute(PAGE_LABEL, "label.egd.renewSupplier");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
			model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		}

		return viewname;
	}

}
