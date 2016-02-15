package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.*;

import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.AddLandRequestCommand;
import com.uaq.command.ExtensionOfGrandCommand;
import com.uaq.command.GrantLandRequestCommand;
import com.uaq.command.IssueSitePlanDocumentCommand;
import com.uaq.command.LandDemarcationRequestCommand;

import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.controller.mapper.PSDataMapper;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;
import com.uaq.service.PSRequestService;
import com.uaq.service.PortalUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LandInputVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.PageMetadataVO;
import com.uaq.vo.UserDeatilVO;
import com.uaq.vo.UserFamilyDetailsVO;

import static com.uaq.common.TilesViewConstant.*;
import static com.uaq.common.UAQURLConstant.*;


/**
 * Controller for Planning and Survey Department
 * 
 * @author Gsekhar
 * 
 * 
 */

@Controller
public class PSController extends BaseController {

	@Autowired
	private PSRequestService pSRequestService;

	@Autowired
	private PortalUtil portalUtil;

	public static transient UAQLogger logger = new UAQLogger(PSController.class);

	/*** Handler Method Grand Land Request ***/

	@RequestMapping(value = ViewPath.NEW_GRANTLANDREQ, method = RequestMethod.GET)
	public String grandLandReq(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		super.handleRequest(request, model);

		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		// For E-Service RHS
		String departmentServicesJSON = super.getDepartmentServicesJSON(languageCode);
		model.addAttribute("departmentServicesJSON", departmentServicesJSON);

		String viewname = EMPTY_STRING;
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			// mobile Site
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Account Id =" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
					logger.info("Mobile    |    Token Validated  | grantland.newreq");
					model.addAttribute("grantLandRequestCommand", new GrantLandRequestCommand());
					model.addAttribute( LANGUAGE_TRANSFORMATION_IGNORE, "true");
					viewname = MOBILE_GRANDLAND;
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
					logger.info("Desktop    |    Token Validated  | grantland.newreq");
					model.addAttribute("grantLandRequestCommand", new GrantLandRequestCommand());
					model.addAttribute( LANGUAGE_TRANSFORMATION_IGNORE, "true");
					viewname = PORTAL_GRANDLAND;
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop  |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.ps.grantlandreq");
		}
		return viewname;

	}

	/*** Handler Method Grand Land Request POST url=/uaq/grantlandrequest.html ***/

	@RequestMapping(value = ViewPath.NEW_GRANTLANDREQ, method = RequestMethod.POST)
	public String grandLandReq(@ModelAttribute("grantLandRequestCommand") GrantLandRequestCommand grantLandRequestCommand, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Grant Land Request| handle POST Request");
		super.handleRequest(request, model);
		String viewname = EMPTY_STRING;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			// mobile site handle
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User  Detail from session- Account Id =" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {

					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						UserFamilyDetailsVO familyVo = new UserFamilyDetailsVO();
						familyVo.setFamilyMembers(Integer.parseInt(grantLandRequestCommand.getFamilyMembers()));
						familyVo.setEmployer(grantLandRequestCommand.getEmployer());
						familyVo.setMonthlySalary(Integer.parseInt(grantLandRequestCommand.getMonthlySalary()));
						familyVo.setCurrentAddress(grantLandRequestCommand.getCurrentAddress());
						familyVo.setMaritalStatus(grantLandRequestCommand.getMaritalStatus());
						familyVo.setSpouceEmirateId(grantLandRequestCommand.getSpousesEmiratesId());
						familyVo.setFamilyBookId("1");
						familyVo.setSpouceEmirateId(grantLandRequestCommand.getSpousesEmiratesId());
						familyVo.setPropertyAccountingId("1");
						familyVo.setSalaryCertificateId("1");
						familyVo.setTypeOfUser(accountDetailfromToken.getTypeOfUser());
						familyVo.setUserName(accountDetailfromToken.getUserName());
						familyVo.setNationality((accountDetailfromToken.getNationalityId()) == null ? "" : accountDetailfromToken.getNationalityId().toString());
						familyVo.setTradeLienceNo(StringUtils.isBlank(accountDetailfromToken.getTradeLienceNo()) ? "" : accountDetailfromToken.getTradeLienceNo());

						LandInputVO inputVo = PSDataMapper.grantLandRequestDataToService(grantLandRequestCommand, accountDetailfromToken);

						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);

						LandOutputVO output = pSRequestService.grandLandRequest(userDetails, familyVo, inputVo);
						if (output != null) {
							// Proceed to Application Fee Payment
							model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
							model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
							model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
							model.addAttribute(PAYMENT_URL_EXPRESSON, MOBILE_PAYMENT_URL);
							model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, userDetails.getTypeOfUser());
							model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
							/*
							 * viewname = MOBILE_THANKU_VIEW;
							 * model.addAttribute(RESPONCE_KEY,
							 * (languageCode.equals(LANG_ENGLISH)) ?
							 * output.getStatus_EN() : output.getStatus_AR());
							 */
							model.addAttribute( RESPONCE_KEY, (languageCode.equals( LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
							model.addAttribute( ISMOBILE, "true");
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty( UAQ_URL) +  URL_SEPARATOR + languageCode +  THANKYOU_PAGE;
							logger.info("Success | Render the Thankyou page for MOBILE");
						}

					}
				}

			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("mobile | Failure    |  User Not Loged In   ");

			}
		} else {
			// Desktop Site
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = (AccountDetailTokenOutputVO) request.getSession().getAttribute(SESSION_ACCOUNT_DETAIL_FROM_TOKEN);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {

						UserFamilyDetailsVO familyVo = new UserFamilyDetailsVO();
						familyVo.setFamilyMembers(Integer.parseInt(grantLandRequestCommand.getFamilyMembers()));
						familyVo.setEmployer(grantLandRequestCommand.getEmployer());
						familyVo.setMonthlySalary(Integer.parseInt(grantLandRequestCommand.getMonthlySalary()));
						familyVo.setCurrentAddress(grantLandRequestCommand.getCurrentAddress());
						familyVo.setMaritalStatus(grantLandRequestCommand.getMaritalStatus());
						familyVo.setSpouceEmirateId(grantLandRequestCommand.getSpousesEmiratesId());
						familyVo.setFamilyBookId("1");
						familyVo.setSpouceEmirateId(grantLandRequestCommand.getSpousesEmiratesId());
						familyVo.setPropertyAccountingId("1");
						familyVo.setSalaryCertificateId("1");
						familyVo.setTypeOfUser(accountDetailfromToken.getTypeOfUser());
						familyVo.setUserName(accountDetailfromToken.getUserName());
						familyVo.setNationality((accountDetailfromToken.getNationalityId()) == null ? "" : accountDetailfromToken.getNationalityId().toString());
						familyVo.setTradeLienceNo(StringUtils.isBlank(accountDetailfromToken.getTradeLienceNo()) ? "" : accountDetailfromToken.getTradeLienceNo());

						LandInputVO inputVo = PSDataMapper.grantLandRequestDataToService(grantLandRequestCommand, accountDetailfromToken);
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);

						LandOutputVO output = pSRequestService.grandLandRequest(userDetails, familyVo, inputVo);
						if (output != null) {
							model.addAttribute(ISMOBILE, "false");
							model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
							// Proceed to Application Fee Payment
							model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
							model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
							model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
							model.addAttribute(PAYMENT_URL_EXPRESSON, PORTAL_PAYMENT_URL);
							model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, userDetails.getTypeOfUser());
							// viewname = PORTAL_THANKU_VIEW;
							// model.addAttribute(RESPONCE_KEY,
							// (languageCode.equals(LANG_ENGLISH)) ?
							// output.getStatus_EN() : output.getStatus_AR());
							model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());

							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty( UAQ_URL) +  URL_SEPARATOR + languageCode +  THANKYOU_PAGE;
							logger.info("Success | Render the Thankyou page for DESKTOP");
						}

					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Failure    |    User is Not Loged In ");
				request.getSession().invalidate();

			}
			model.addAttribute( PAGE_LABEL, "label.ps.grantlandreq");

		}

		return viewname;
	}

	/***
	 * Handler Method EXTENTION Grand Land
	 * Request-URL-/uaq/extensiongrandLand.html
	 ***/

	@RequestMapping(value = ViewPath.EXTENSION_GRAND_LAND_REQUEST, method = RequestMethod.GET)
	public String extentionGrandLand(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		super.handleRequest(request, model);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		// For E-Service RHS
		String departmentServicesJSON = super.getDepartmentServicesJSON(languageCode);
		model.addAttribute("departmentServicesJSON", departmentServicesJSON);
		
		Locale locale=new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.exgrandland.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.exgrandland.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.exgrandland.pagekeyword", null, locale));
		
		String viewname = EMPTY_STRING;
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Account Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
					logger.info("mobile    |    Token Validated  | ps.extensionOfGrand page Loaded");
					viewname = MOBILE_EX_GRANDLAND;
					portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
					model.addAttribute("extensionOfGrandCommand", new ExtensionOfGrandCommand());
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
					logger.info("Desktop    |    Token Validated  | ps.extensionOfGrand page loaded");
					viewname = PORTAL_EX_GRANDLAND;
					portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
					model.addAttribute("extensionOfGrandCommand", new ExtensionOfGrandCommand());
					
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop     |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();

			}
			model.addAttribute(PAGE_LABEL, "label.ps.extensiongrantland");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
			model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		}

		return viewname;
	}

	/***
	 * Handler Method EXTENTION Grand Land Request
	 * POST-URL-/uaq/extensiongrandLand.html
	 ***/

	@RequestMapping(value = ViewPath.EXTENSION_GRAND_LAND_REQUEST, method = RequestMethod.POST)
	public String extentionGrandLand(@ModelAttribute("extensionOfGrandCommand") ExtensionOfGrandCommand extensionOfGrandCommand, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		super.handleRequest(request, model);
		logger.info("Extension Grant Land Request | handle POST Request");
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		
		
		Locale locale=new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.exgrandland.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.exgrandland.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.exgrandland.pagekeyword", null, locale));

		String viewname = EMPTY_STRING;
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Login info" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSDataMapper.extensionOfGrandDataToService(extensionOfGrandCommand, accountDetailfromToken);
						LandOutputVO output;
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						try {
							output = pSRequestService.extensionGrandLand(user, landInputVO);
							if (!output.getStatus().equals(SERVICE_FAILED)) {
								logger.info("Status Id | "+output.getSatausId());
								logger.info("request No | "+output.getRequestNo());
								model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
								model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
								model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
								model.addAttribute(PAYMENT_URL_EXPRESSON, MOBILE_PAYMENT_URL);
								model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, user.getTypeOfUser());
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  THANKYOU_PAGE;
								logger.info("Success | Render the Thankyou page for MOBILE");
							}
						} catch (DatatypeConfigurationException e) {
							logger.error("Failure | " + e.getMessage());
						} catch (ParseException e) {
							logger.error("Failure | " + e.getMessage());
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
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = (AccountDetailTokenOutputVO) request.getSession().getAttribute(SESSION_ACCOUNT_DETAIL_FROM_TOKEN);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSDataMapper.extensionOfGrandDataToService(extensionOfGrandCommand, accountDetailfromToken);
						LandOutputVO output;
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						try {
							output = pSRequestService.extensionGrandLand(user, landInputVO);
							if (!output.getStatus().equals(SERVICE_FAILED)) {
								logger.info("Status Id | "+output.getSatausId());
								logger.info("request No | "+output.getRequestNo());
								model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
								model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
								model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
								model.addAttribute(PAYMENT_URL_EXPRESSON, PORTAL_PAYMENT_URL);
								model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, user.getTypeOfUser());
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  THANKYOU_PAGE;
								logger.info("Success | Redirect to Thankyou Controller");
							}
						} catch (DatatypeConfigurationException e) {
							logger.error("Failure | " + e.getMessage());
						} catch (ParseException e) {
							logger.error("Failure | " + e.getMessage());
						}
						

					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Failure    |    User is Not Loged In ");
				request.getSession().invalidate();

			}
			model.addAttribute(PAGE_LABEL, "label.ps.extensiongrantland");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
			model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		}

		return viewname;
	}

	/*** Handler Method Add Land Request -URL-/uaq/addlandrequest.html ***/

	@RequestMapping(value = ViewPath.ADD_LAND_REQUEST, method = RequestMethod.GET)
	public String addLandRequest(HttpServletRequest request, ModelMap model, HttpServletResponse response) {
		logger.enter("In Add Land Request | handle Request");
		super.handleRequest(request, model);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale =new Locale(languageCode);
		// For E-Service RHS
		String departmentServicesJSON = super.getDepartmentServicesJSON(languageCode);
		model.addAttribute("departmentServicesJSON", departmentServicesJSON);
		
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.addLand.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.addLand.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.addLand.pagekeyword", null, locale));
		
		String viewname = EMPTY_STRING;
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Login info" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
					logger.info("mobile    |    Token Validated  | ps.addLandRequest page Loaded");
					viewname = MOBILE_ADDLAND;
					portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
					model.addAttribute("addLandRequestCommand", new AddLandRequestCommand());
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Mobile  | Failure    |  User Not Loged In   ");
			}
		} else {
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | ps.addLandRequest page Loaded");
					portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
					model.addAttribute("addLandRequestCommand", new AddLandRequestCommand());
					viewname = PORTAL_ADDLAND;
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.ps.addlandReq");
			model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		}
		return viewname;
	}

	/*** Handler Method Add Land Request URL-/uaq/addlandrequest.html ***/

	@RequestMapping(value = ViewPath.ADD_LAND_REQUEST, method = RequestMethod.POST)
	public String addLandRequest(@ModelAttribute("addLandRequestCommand") AddLandRequestCommand addLandRequestCommand, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		super.handleRequest(request, model);
		logger.info("Add Land Request | handle POST Request");
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		String viewname = EMPTY_STRING;
		
		Locale locale=new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.addLand.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.addLand.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.addLand.pagekeyword", null, locale));
		
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Login info" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSDataMapper.addLandDataToService(addLandRequestCommand, accountDetailfromToken);
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output = pSRequestService.addLandRequest(user, landInputVO);
						if (!output.getStatus().equals(SERVICE_FAILED)) {
							logger.info("Status Id | "+output.getSatausId());
							logger.info("request No | "+output.getRequestNo());
							model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
							model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
							model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
							model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
							model.addAttribute(PAYMENT_URL_EXPRESSON, MOBILE_PAYMENT_URL);
							model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, user.getTypeOfUser());
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty( UAQ_URL) +  URL_SEPARATOR + languageCode +  THANKYOU_PAGE;
							logger.info("Success | Render the Thankyou page for MOBILE");
						}
					}
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Failure    |  User Not Loged In logininfo return Null  ");

			}

		} else {
			model.addAttribute(ISMOBILE, "false");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSDataMapper.addLandDataToService(addLandRequestCommand, accountDetailfromToken);
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output = pSRequestService.addLandRequest(user, landInputVO);
						if (!output.getStatus().equals(SERVICE_FAILED)) {
							logger.info("Status Id | "+output.getSatausId());
							logger.info("request No | "+output.getRequestNo());
							model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
							model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
							model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
							model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
							model.addAttribute(PAYMENT_URL_EXPRESSON, PORTAL_PAYMENT_URL);
							model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, user.getTypeOfUser());
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty( UAQ_URL) +  URL_SEPARATOR + languageCode +  THANKYOU_PAGE;
							logger.info("Success | Render the Thankyou page for DESKTOP");
						}
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Failure    |    User is Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.ps.addlandReq");
			model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		}
		return viewname;
	}

	/***
	 * Handler Method ISSUE SITE PLAN DOCUMENT REQUEST
	 * URL-/uaq/issuesiteplandoc.html
	 ***/

	@RequestMapping(value = ViewPath.ISSUE_SITEPLAN_DOC_REQUEST, method = RequestMethod.GET)
	public String handleIssueSitePlanDoc(HttpServletRequest request, ModelMap model, HttpServletResponse response) {
		super.handleRequest(request, model);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		// For E-Service RHS
		String departmentServicesJSON = super.getDepartmentServicesJSON(languageCode);
		model.addAttribute("departmentServicesJSON", departmentServicesJSON);

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.issuesiteplan.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.issuesiteplan.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.issuesiteplan.pagekeyword", null, locale));

		String viewname = EMPTY_STRING;
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Login info" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
					logger.info("mobile    |    Token Validated  | ps.issuesiteplan page Loaded");
					viewname = MOBILE_ISSUESITEPLAN;
					portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
					model.addAttribute("issueSitePlanDocumentCommand", new IssueSitePlanDocumentCommand());
					model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Mobile  | Failure    |  User Not Loged In   ");
			}
		} else {
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | ps.issuesiteplan page Loaded");
					viewname = PORTAL_ISSUESITEPLAN;
					portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
					model.addAttribute("issueSitePlanDocumentCommand", new IssueSitePlanDocumentCommand());
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop   |  Failure    |    User is Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.ps.issueSitePlan");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
			model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		}
		return viewname;
	}

	/***
	 * Handler Method ISSUE SITE PLAN DOCUMENT REQUEST POST
	 * URL-/uaq/issuesiteplandoc.html
	 ***/

	@RequestMapping(value = ViewPath.ISSUE_SITEPLAN_DOC_REQUEST, method = RequestMethod.POST)
	public String handlehandleIssueSitePlanDocPost(@ModelAttribute("issueSitePlanDocumentCommand") IssueSitePlanDocumentCommand issueSitePlanDocumentCommand, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		super.handleRequest(request, model);
		logger.info("Issue Site Plan Document Request | handle POST Request");

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.issuesiteplan.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.issuesiteplan.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.issuesiteplan.pagekeyword", null, locale));

		String viewname = EMPTY_STRING;
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.debug("Mobile User Detail- Login info" + logininfo.toString());
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO IssueSitePlanInputVO = PSDataMapper.issueSitePlanDataToService(issueSitePlanDocumentCommand, accountDetailfromToken);
						IssueSitePlanInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output;
						try {
							output = pSRequestService.issueSitePalnDocument(user, IssueSitePlanInputVO);
							if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
								logger.info("Status Id | "+output.getSatausId());
								logger.info("request No | "+output.getRequestNo());
								model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
								model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
								model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
								model.addAttribute(PAYMENT_URL_EXPRESSON, MOBILE_PAYMENT_URL);
								model.addAttribute( REQUEST_PARAM_TYPE_OF_USER, user.getTypeOfUser());
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  THANKYOU_PAGE;
								logger.info("Success | Render the Thankyou page for MOBILE");
							}
						} catch (DatatypeConfigurationException e) {
							logger.error("Failure | " + e.getMessage());
						} catch (ParseException e) {
							logger.error("Failure | " + e.getMessage());
						}
						
					}
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Failure    |  User Not Loged In   ");
			}
		} else {
			model.addAttribute( ISMOBILE, "false");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = (AccountDetailTokenOutputVO) request.getSession().getAttribute(SESSION_ACCOUNT_DETAIL_FROM_TOKEN);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSDataMapper.issueSitePlanDataToService(issueSitePlanDocumentCommand, accountDetailfromToken);
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output;
						try {
							output = pSRequestService.issueSitePalnDocument(user, landInputVO);
							if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
								logger.info("Status Id | "+output.getSatausId());
								logger.info("request No | "+output.getRequestNo());
								model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
								model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
								model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
								model.addAttribute(PAYMENT_URL_EXPRESSON, PORTAL_PAYMENT_URL);
								model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, user.getTypeOfUser());
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  THANKYOU_PAGE;
								logger.info("Success | Render the Thankyou page for DESKTOP");
							}
						} catch (DatatypeConfigurationException e) {
							logger.error("Failure | " + e.getMessage());
						} catch (ParseException e) {
							logger.error("Failure | " + e.getMessage());
						}
						
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Failure    |    User is Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.ps.issueSitePlan");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
			model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		}

		return viewname;

	}

	/***
	 * Handler Method LAND DEMACRATION REQUEST
	 * URL-/uaq/landdemarcationrequest.html
	 **/

	@RequestMapping(value = ViewPath.LAND_DEMARCATION_REQUEST, method = RequestMethod.GET)
	public String handleLandDemacration(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		super.handleRequest(request, model);

		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		// For E-Service RHS
		String departmentServicesJSON = super.getDepartmentServicesJSON(languageCode);
		model.addAttribute("departmentServicesJSON", departmentServicesJSON);
		String viewname = EMPTY_STRING;
		LoginOutputVO logininfo = null;

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.landdemarcation.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.landdemarcation.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.landdemarcation.pagekeyword", null, locale));
		
		if (portalUtil.isMobile(request, response)) {
			// mobile Site
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Login info" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
					logger.info("mobile    |    Token Validated  | ps.issuesiteplan page Loaded");
					viewname = MOBILE_LANDDEMACRATION;
					portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
					model.addAttribute("landDemarcationRequestCommand", new LandDemarcationRequestCommand());
					model.addAttribute( LANGUAGE_TRANSFORMATION_IGNORE, "true");
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
					logger.info("Desktop    |    Token Validated  | ps.issuesiteplan page Loaded");
					viewname = PORTAL_LANDDEMACRATION;
					portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
					model.addAttribute("landDemarcationRequestCommand", new LandDemarcationRequestCommand());
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop   |   Failure    |    User is Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.ps.landDemorcation");
			model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		}

		return viewname;
	}

	/***
	 * Handler Method LAND DEMACRATION REQUEST POST
	 * URL-/uaq/landdemarcationrequest.html
	 **/

	@RequestMapping(value = ViewPath.LAND_DEMARCATION_REQUEST, method = RequestMethod.POST)
	public String handleLandDemacrationPost(@ModelAttribute("landDemarcationRequestCommand") LandDemarcationRequestCommand command, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String viewname = EMPTY_STRING;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		logger.info("LAND DEMARCATION REQUEST | handle POST Request");
		super.handleRequest(request, model);

		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Login info" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landDemacreationVO = PSDataMapper.landDemacrationDataToService(command, accountDetailfromToken);
						landDemacreationVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output;
						try {
							output = pSRequestService.landDemarcationRequest(user, landDemacreationVO);
							if (!output.getStatus().equals(SERVICE_FAILED)) {
								logger.info("Status Id | "+output.getSatausId());
								logger.info("request No | "+output.getRequestNo());
								model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
								model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
								model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
								model.addAttribute(PAYMENT_URL_EXPRESSON, MOBILE_PAYMENT_URL);
								model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, user.getTypeOfUser());
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  THANKYOU_PAGE;
								logger.info("Success | Render the Thankyou page for MOBILE");
							}
						} catch (DatatypeConfigurationException e) {
							logger.error("Failure | " + e.getMessage());
							
						} catch (ParseException e) {
							logger.error("Failure | " + e.getMessage());
						}
						
					}
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Failure    |  User Not Loged In   ");

			}

		} else {
			model.addAttribute( ISMOBILE, "false");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = (AccountDetailTokenOutputVO) request.getSession().getAttribute(SESSION_ACCOUNT_DETAIL_FROM_TOKEN);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landDemacreationVO = PSDataMapper.landDemacrationDataToService(command, accountDetailfromToken);
						landDemacreationVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output;
						try {
							output = pSRequestService.landDemarcationRequest(user, landDemacreationVO);
							if (!output.getStatus().equals(SERVICE_FAILED)) {
								logger.info("Status Id | "+output.getSatausId());
								logger.info("request No | "+output.getRequestNo());
								model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
								model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
								model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
								model.addAttribute(PAYMENT_URL_EXPRESSON, PORTAL_PAYMENT_URL);
								model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, user.getTypeOfUser());
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) +  URL_SEPARATOR + languageCode +  THANKYOU_PAGE;
								logger.info("Success | Render the Thankyou page for MOBILE");
							}
						} catch (DatatypeConfigurationException e) {
							logger.error("Failure | " + e.getMessage());
						} catch (ParseException e) {
							logger.error("Failure | " + e.getMessage());
						}
						
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Failure    |    User is Not Loged In ");
				request.getSession().invalidate();

			}
			model.addAttribute(PAGE_LABEL, "label.ps.landDemorcation");
			model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		}

		return viewname;
	}
}
