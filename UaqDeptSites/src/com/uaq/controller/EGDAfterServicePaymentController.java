package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.TilesViewConstant.*;
import static com.uaq.common.UAQURLConstant.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.uaq.common.PropertiesUtil;
import com.uaq.common.ServiceNameConstant;
import com.uaq.common.ViewPath;
import com.uaq.controller.mapper.EGDDataMapper;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;
import com.uaq.service.EGDAfterServicePayment;
import com.uaq.service.EGDFindRequestService;
import com.uaq.service.PortalUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.EGDResubmissionVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.NewSupplierRegistrationVO;
import com.uaq.vo.ReSubmiisionInputVO;
import com.uaq.vo.UserDeatilVO;

@Controller
public class EGDAfterServicePaymentController extends BaseController {
	
	
	protected static UAQLogger logger = new UAQLogger(EGDAfterServicePaymentController.class);
	
	
	@Autowired
	private EGDAfterServicePayment eGDAfterServicePayment;
	
	@Autowired
	private PortalUtil portalUtil;
	
	@Autowired
	private EGDFindRequestService eGDFindRequestService;
	
	
	@RequestMapping(value=ViewPath.INITIATE_ACTIVATE_ACCOUNT,method=RequestMethod.GET)
	public String servicePaymentIssueSitePlan(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		String requestNo = portalUtil.decryptParameter(request.getParameter(REQUEST_PARAM_REQUEST_NO));
		String serviceId = portalUtil.decryptParameter(request.getParameter(REQUEST_PARAM_SERVICE_ID));
		ReSubmiisionInputVO resubmossioninputVO = new ReSubmiisionInputVO();
		resubmossioninputVO.setAttributeName("RequestNo");
		resubmossioninputVO.setAttributeValue(requestNo);
		String viewname="";
		
		if (serviceId.equals(ServiceNameConstant.NEW_SUPPLIER_REGISTRATION)) {
			EGDResubmissionVO resubmitdata = eGDFindRequestService.findNewSupplierRequest(resubmossioninputVO,languageCode);
			viewname = servicePaymentNewSupplierRequest(resubmitdata, request,response, model);
			logger.info("ReSubmission New Supplier Loaded");

		} else if (serviceId.equals(ServiceNameConstant.RENEW_SUPPLIER_REGISTRATION)) {
			EGDResubmissionVO resubmitdata =eGDFindRequestService.findNewSupplierRequest(resubmossioninputVO,languageCode);
			viewname = servicePaymentRenewSupplier(resubmitdata, request,response, model);
			logger.info("ReSubmission Re New Page Loaded");
		}
		return viewname;
		
	}
	
	private String servicePaymentNewSupplierRequest(EGDResubmissionVO resubmitdata,HttpServletRequest request,HttpServletResponse responce,ModelMap model){
		
		super.handleRequest(request, model);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		String viewname = "";
		
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request,responce)) {
			// mobile Site
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			logger.debug("Mobile User Detail-"+logininfo.getAcountId()   + logininfo.getTokenCode()  + logininfo .getUsername());
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Mobile    |    Token Validated  | New Supplier");
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
					NewSupplierRegistrationVO supplierDetails = EGDDataMapper.setSupplierDataForAfterPayment(accountDetailfromToken,resubmitdata);
					LandOutputVO outputVo=eGDAfterServicePayment.submitAfterPayment(user, supplierDetails);
					model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputVo.getStatus_EN() : outputVo.getStatus_AR());
					//viewname =MOBILE_THANKU_VIEW;
					model.addAttribute("isMobile", "true");
					viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url")+ URL_SEPARATOR + languageCode + THANKYOU_PAGE;
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Mobile  | Failure    |  User Not Loged In   ");
			}
		} else {
			// Desktop Site
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | New Supplier");
					AccountDetailTokenOutputVO accountDetailfromToken = (AccountDetailTokenOutputVO) request.getSession().getAttribute(SESSION_ACCOUNT_DETAIL_FROM_TOKEN);
					UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
					NewSupplierRegistrationVO supplierDetails = EGDDataMapper.setSupplierDataForAfterPayment(accountDetailfromToken,resubmitdata);
					LandOutputVO outputVo=eGDAfterServicePayment.submitAfterPayment(user, supplierDetails);
					//viewname =PORTAL_THANKU_VIEW;
					model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputVo.getStatus_EN() : outputVo.getStatus_AR());
					model.addAttribute("isMobile", "false");
					viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url")+ URL_SEPARATOR + languageCode + THANKYOU_PAGE;
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop  |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.egd.newSupplier");
		}
		return viewname;
		
		
		
	}
	
	//for ReNew
	private String servicePaymentRenewSupplier(EGDResubmissionVO resubmitdata,HttpServletRequest request,HttpServletResponse response,ModelMap model){
		super.handleRequest(request, model);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		String viewname = "";
		//String serviceId = portalUtil.decryptParameter(request.getParameter(REQUEST_PARAM_SERVICE_ID));
		
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request,response)) {
			// mobile Site
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			logger.debug("Mobile User Detail-"+logininfo.getAcountId()   + logininfo.getTokenCode()  + logininfo .getUsername());
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Mobile    |    Token Validated  | Re New Supplier");
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
					NewSupplierRegistrationVO supplierDetails = EGDDataMapper.setSupplierDataForAfterPayment(accountDetailfromToken,resubmitdata);
					LandOutputVO outputVo=eGDAfterServicePayment.submitAfterPayment(user, supplierDetails);
					model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputVo.getStatus_EN() : outputVo.getStatus_AR());
					//viewname =MOBILE_THANKU_VIEW;
					model.addAttribute("isMobile", "true");
					viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url")+ URL_SEPARATOR + languageCode + THANKYOU_PAGE;
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Mobile  | Failure    |  User Not Loged In   ");
			}
		} else {
			// Desktop Site
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | Re New Supplier");
					AccountDetailTokenOutputVO accountDetailfromToken = (AccountDetailTokenOutputVO) request.getSession().getAttribute(SESSION_ACCOUNT_DETAIL_FROM_TOKEN);
					UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
					NewSupplierRegistrationVO supplierDetails = EGDDataMapper.setSupplierDataForAfterPayment(accountDetailfromToken,resubmitdata);
					LandOutputVO outputVo=eGDAfterServicePayment.submitAfterPayment(user, supplierDetails);
					//viewname =PORTAL_THANKU_VIEW;
					model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputVo.getStatus_EN() : outputVo.getStatus_AR());
					model.addAttribute("isMobile", "false");
					viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url")+ URL_SEPARATOR + languageCode + THANKYOU_PAGE;
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop  |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.egd.renewSupplier");
		}
		return viewname;
		
		
	}

}
