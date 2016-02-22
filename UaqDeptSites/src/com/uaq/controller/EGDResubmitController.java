package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.TilesViewConstant.*;
import static com.uaq.common.UAQURLConstant.*;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.NewSupplierRegistrationCommand;

import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.controller.mapper.EGDDataMapper;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;
import com.uaq.service.EGDResubmitRequestService;
import com.uaq.service.PortalUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;

import com.uaq.vo.LandOutputVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.NewSupplierRegistrationVO;
import com.uaq.vo.PageMetadataVO;
import com.uaq.vo.UserDeatilVO;

@Controller
public class EGDResubmitController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(EGDResubmitController.class);

	@Autowired
	private PortalUtil portalUtil;

	@Autowired
	EGDResubmitRequestService eGDResubmitRequestService;

	String defaultpage = "";

	@RequestMapping(value = ViewPath.RESUBMIT_NEW_SUPPLIER_REGISTRATION, method = RequestMethod.POST)
	public String handleResubmitNewRegistartion(@ModelAttribute("newSupplierRegistrationCommand") NewSupplierRegistrationCommand newSupplierRegistrationCommand, HttpServletResponse responce,
			HttpServletRequest request, ModelMap model) {
		super.handleRequest(request, model);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.egd.resubmitnewSupplierRegistrationPage.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.egd.resubmitnewSupplierRegistrationPage.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.egd.resubmitnewSupplierRegistrationPage.pagekeyword", null, locale));

		String viewname = "";
		// EGDResubmissionVO eGDResubmissionVO = (EGDResubmissionVO)
		// request.getSession().getAttribute(SESSION_RESUBMIT_EGD);
		defaultpage = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + MY_REQUEST_URL;
		viewname = defaultpage;
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, responce)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail from session- Login Info Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						NewSupplierRegistrationVO supplierDetails = EGDDataMapper.getSupplierDataForResubmit(accountDetailfromToken, newSupplierRegistrationCommand);
						supplierDetails.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST_MOBILE;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), newSupplierRegistrationCommand.getRequestNo(), newSupplierRegistrationCommand.getStatusId())) {
							
							LandOutputVO output = eGDResubmitRequestService.reSubmitSupplierRequest(userDetails, supplierDetails);
							if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
								model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
								model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
							}
						}
					}
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Failure    |  User Not Loged In   ");

			}

		} else {
			model.addAttribute(ISMOBILE, "false");
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				logger.debug("Portal User Detail from session- Login Info Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						NewSupplierRegistrationVO supplierDetails = EGDDataMapper.getSupplierDataForResubmit(accountDetailfromToken, newSupplierRegistrationCommand);
						supplierDetails.setServiceId("502");
						supplierDetails.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), newSupplierRegistrationCommand.getRequestNo(), newSupplierRegistrationCommand.getStatusId())) {
							
							LandOutputVO output = eGDResubmitRequestService.reSubmitSupplierRequest(userDetails, supplierDetails);
							if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
								model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
								model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
							}
						}
					}
				}
			}
		}
		

		if (viewname.equals(defaultpage)) {
			request.getSession().invalidate();
			logger.info("Failure    |  User Not Loged In   ");
		}
		model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		model.addAttribute(PAGE_LABEL, "label.egd.newSupplier");
		model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		model.addAttribute("myRequestUrl", PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL);

		return viewname;

	}

	@RequestMapping(value = ViewPath.RESUBMIT_RE_NEW_SUPPLIER_REGISTRATION, method = RequestMethod.POST)
	public String handleResubmitreNewRegistartion(@ModelAttribute("newSupplierRegistrationCommand") NewSupplierRegistrationCommand newSupplierRegistrationCommand, HttpServletRequest request,
			HttpServletResponse responce, ModelMap model) {
		super.handleRequest(request, model);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.egd.resubmitreNewSupplierRegistrationPage.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.egd.resubmitreNewSupplierRegistrationPage.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.egd.resubmitreNewSupplierRegistrationPage.pagekeyword", null, locale));

		String viewname = "";
		// EGDResubmissionVO eGDResubmissionVO = (EGDResubmissionVO)
		// request.getSession().getAttribute(SESSION_RESUBMIT_EGD);
		defaultpage = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + MY_REQUEST_URL;
		viewname = defaultpage;
		LoginOutputVO logininfo = null;

		if (portalUtil.isMobile(request, responce)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail from session- Login Info Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						NewSupplierRegistrationVO supplierDetails = EGDDataMapper.getSupplierDataForResubmit(accountDetailfromToken, newSupplierRegistrationCommand);
						supplierDetails.setServiceId("502");
						supplierDetails.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST_MOBILE;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), newSupplierRegistrationCommand.getRequestNo(), newSupplierRegistrationCommand.getStatusId())) {
							
							LandOutputVO output = eGDResubmitRequestService.reSubmitSupplierRequest(userDetails, supplierDetails);
							if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
								model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
								model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
								model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
								model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
							}
						}
					}
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Failure    |  User Not Loged In   ");

			}

		} else {
			model.addAttribute(ISMOBILE, "false");
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				logger.debug("Portal User Detail from session- Login Info Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						NewSupplierRegistrationVO supplierDetails = EGDDataMapper.getSupplierDataForResubmit(accountDetailfromToken, newSupplierRegistrationCommand);
						supplierDetails.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), newSupplierRegistrationCommand.getRequestNo(), newSupplierRegistrationCommand.getStatusId())) {
							
							LandOutputVO output = eGDResubmitRequestService.reSubmitSupplierRequest(userDetails, supplierDetails);
							if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
								model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
								model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
							}
						}
					}
				}
			}
		}

		if (viewname.equals(defaultpage)) {
			request.getSession().invalidate();
			logger.info("Failure    |  User Not Loged In   ");
		}
		model.addAttribute(PAGE_LABEL, "label.egd.renewSupplier");
		model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		model.addAttribute("myRequestUrl", PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL);
		return viewname;

	}

}
