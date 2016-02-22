package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.TilesViewConstant.*;
import static com.uaq.common.UAQURLConstant.MY_REQUEST_URL;
import static com.uaq.common.UAQURLConstant.SERVICES_ERROR_PAGE;
import static com.uaq.common.UAQURLConstant.THANKYOU_PAGE;

import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import com.uaq.controller.mapper.PSReSubmitDataMapper;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;

import com.uaq.service.PSReSubmissionRequestService;
import com.uaq.service.PortalUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LandInputVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.LoginOutputVO;

import com.uaq.vo.PSGrandLandRequestVO;

import com.uaq.vo.PageMetadataVO;
import com.uaq.vo.UserDeatilVO;
import com.uaq.vo.UserFamilyDetailsVO;

@Controller
public class PSResubmitController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(PSResubmitController.class);

	@Autowired
	private PortalUtil portalUtil;

	@Autowired
	private PSReSubmissionRequestService pSReSubmissionRequestService;

	@Autowired
	private MessageSource messageSource;

	// for returning MyRequest when Login Fail
	String defaultpage = EMPTY_STRING;

	@RequestMapping(value = ViewPath.RESUBMIT_GRAND_LAND_REQUEST, method = RequestMethod.POST)
	public String handleGrandLandResubmit(@ModelAttribute("grantLandRequestCommand") GrantLandRequestCommand grantLandRequestCommand, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		super.handleRequest(request, model);
		logger.info("Handle Add Land Resubmit POST ");
		String viewname = EMPTY_STRING;
		LoginOutputVO logininfo = null;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);

		PSGrandLandRequestVO pSGrandLandRequestVO = (PSGrandLandRequestVO) request.getSession().getAttribute(SESSION_RESUBMIT_PS_GRANDLAND);
		defaultpage = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL;
		viewname = defaultpage;

		if (pSGrandLandRequestVO != null) {
			if (portalUtil.isMobile(request, response)) {
				logger.enter("Mobile App Requested");
				viewname = MOBILE_LOGIN_AGAIN;
				logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				if (logininfo != null) {
					logger.debug("Mobile My Request URl.Login info Should from URL=" + logininfo.toString());
					if (portalUtil.validateToken(logininfo)) {
						AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);

						if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
							// UserDeatilVO user =
							// portalUtil.getUserDetailFrom(accountDetailfromToken);
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

							LandInputVO landInputVO = PSReSubmitDataMapper.addGrandLandDateToService(grantLandRequestCommand, accountDetailfromToken, pSGrandLandRequestVO);

							LandOutputVO output = pSReSubmissionRequestService.reSubmitGrantLand(familyVo, landInputVO);

							// viewname =
							// MOBILE_THANKU_VIEW;

							model.addAttribute(ISMOBILE, "true");
							model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
						}
					}

				}
				if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
					request.getSession().invalidate();
					logger.info("Failure    |  User Not Loged In   ");

				}

			} else {
				logger.enter("Portal Site Requested");
				logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
				if (logininfo != null) {
					logger.debug("Portal User Detail from session- Login Info Id" + logininfo.toString());
					if (portalUtil.validateToken(logininfo)) {
						AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
						if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
							// UserDeatilVO user =
							// portalUtil.getUserDetailFrom(accountDetailfromToken);
							LandInputVO landInputVO = PSReSubmitDataMapper.addGrandLandDateToService(grantLandRequestCommand, accountDetailfromToken, pSGrandLandRequestVO);
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

							LandOutputVO output = pSReSubmissionRequestService.reSubmitGrantLand(familyVo, landInputVO);

							// viewname =
							// PORTAL_THANKU_VIEW;
							model.addAttribute(ISMOBILE, "false");
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
						}
					}
				}
			}
			model.addAttribute(PAGE_LABEL, "label.ps.grantlandreq");
			model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");

		}
		if (viewname.equals(defaultpage)) {
			request.getSession().invalidate();
			logger.info("Failure    |  User Not Loged In   ");
		}
		model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return viewname;

	}

	@RequestMapping(value = ViewPath.RESUBMIT_ADD_LAND, method = RequestMethod.POST)
	public String handleAddLandResubmit(@ModelAttribute("addLandRequestCommand") AddLandRequestCommand addLandRequestCommand, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		super.handleRequest(request, model);
		logger.info("Handle Add Land Resubmit POST ");
		String viewname = EMPTY_STRING;
		LoginOutputVO logininfo = null;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.addLand.resubmit.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.addLand.resubmit.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.addLand.resubmit.pagekeyword", null, locale));

		defaultpage = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL;
		viewname = defaultpage;

		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			logger.enter("Mobile Site Requested");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail from session- Login Info Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.addLandDataToService(addLandRequestCommand, accountDetailfromToken);
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST_MOBILE;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), addLandRequestCommand.getRequestNo(), addLandRequestCommand.getStausId())) {
							LandOutputVO output = pSReSubmissionRequestService.reSubmitAddLand(user, landInputVO);
							logger.debug("OutPut=" + output != null ? output.getStatus() : "null");
							if (!SERVICE_FAILED.equals(output.getStatus())) {
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
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
			logger.debug("Portal site Requested");
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				logger.debug("Portal User Detail from session- Login Info Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.addLandDataToService(addLandRequestCommand, accountDetailfromToken);
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), addLandRequestCommand.getRequestNo(), addLandRequestCommand.getStausId())) {
							LandOutputVO output = pSReSubmissionRequestService.reSubmitAddLand(user, landInputVO);
							logger.debug("OutPut=" + output != null ? output.getStatus() : "null");
							if (!SERVICE_FAILED.equals(output.getStatus())) {
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
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
		model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		model.addAttribute(PAGE_LABEL, "label.ps.addlandReq");
		model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		model.addAttribute("myRequestUrl", PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL);
		return viewname;

	}

	@RequestMapping(value = ViewPath.RESUBMIT_LAND_DEMARCATION_REQUEST, method = RequestMethod.POST)
	public String handleLandDemarcationrequestResubmit(@ModelAttribute("landDemarcationRequestCommand") LandDemarcationRequestCommand landDemarcationRequestCommand, HttpServletResponse response,
			HttpServletRequest request, ModelMap model) {
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		super.handleRequest(request, model);
		logger.info("Land Demarcation Request | handle POST Request");
		String viewname = EMPTY_STRING;
		LoginOutputVO logininfo = null;

		defaultpage = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL;
		viewname = defaultpage;
		// if (submitedUserVO != null) {
		if (portalUtil.isMobile(request, response)) {
			logger.enter("Mobile App Requested");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				model.addAttribute(ISMOBILE, "true");
				logger.debug("Mobile User Detail from session- Login Info Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.landDemarcationDataToService(landDemarcationRequestCommand, accountDetailfromToken);
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output;
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST_MOBILE;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), landDemarcationRequestCommand.getRequestNo(), landDemarcationRequestCommand.getStatusid())) {
							try {

								output = pSReSubmissionRequestService.reSubmitLandDemarcationPalnDocument(user, landInputVO);
								logger.debug("OutPut=" + output != null ? output.getStatus() : "null");
								if (!SERVICE_FAILED.equals(output.getStatus())) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								}
							} catch (DatatypeConfigurationException e) {
								logger.error("Failure | " + e.getMessage());
							} catch (ParseException e) {
								logger.error("Failure | " + e.getMessage());
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
			logger.enter("Portal Site Requested");
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				logger.debug("Portal User Detail from session- Login Info Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.landDemarcationDataToService(landDemarcationRequestCommand, accountDetailfromToken);
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output;
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), landDemarcationRequestCommand.getRequestNo(), landDemarcationRequestCommand.getStatusid())) {
							try {

								output = pSReSubmissionRequestService.reSubmitLandDemarcationPalnDocument(user, landInputVO);
								logger.debug("OutPut=" + output != null ? output.getStatus() : "null");
								if (!SERVICE_FAILED.equals(output.getStatus())) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								}
							} catch (DatatypeConfigurationException e) {
								logger.error("Failure | " + e.getMessage());
							} catch (ParseException e) {
								logger.error("Failure | " + e.getMessage());
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
		model.addAttribute(PAGE_LABEL, "label.ps.landDemorcation");
		model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		model.addAttribute("myRequestUrl", PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL);
		return viewname;

	}

	@RequestMapping(value = ViewPath.RESUBMIT_ISSUE_SITE_PLAN_DOC, method = RequestMethod.POST)
	public String handleIssueSitePlanDocResubmit(@ModelAttribute("issueSitePlanDocumentCommand") IssueSitePlanDocumentCommand issueSitePlanDocumentCommand, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.issuesiteplan.resubmit.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.issuesiteplan.resubmit.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.issuesiteplan.resubmit.pagekeyword", null, locale));

		super.handleRequest(request, model);
		logger.info("Issue Site Plan Document Request | handle POST Request");

		String viewname = EMPTY_STRING;
		LoginOutputVO logininfo = null;

		defaultpage = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL;
		viewname = defaultpage;
		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			logger.enter("Mobile App Requested");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail from session- Login Info Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.issueSitePlanDataToService(issueSitePlanDocumentCommand, accountDetailfromToken);
						landInputVO.setStatus("1");
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output;
						// This will over written when success
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST_MOBILE;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), issueSitePlanDocumentCommand.getRequestNo(), issueSitePlanDocumentCommand.getStausId())) {
							try {
								output = pSReSubmissionRequestService.reSubmitissueSitePlanDocument(user, landInputVO);
								logger.debug("OutPut=" + output != null ? output.getStatus() : "null");
								if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								}
							} catch (DatatypeConfigurationException e) {
								logger.error("Failure | " + e.getMessage());
							} catch (ParseException e) {
								logger.error("Failure | " + e.getMessage());
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
			logger.enter("Portal Site Requeset");
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				logger.debug("Portal User Detail from session- Login Info Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						LandInputVO landInputVO = PSReSubmitDataMapper.issueSitePlanDataToService(issueSitePlanDocumentCommand, accountDetailfromToken);
						landInputVO.setStatus("1");
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output;
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), issueSitePlanDocumentCommand.getRequestNo(), issueSitePlanDocumentCommand.getStausId())) {
							try {

								output = pSReSubmissionRequestService.reSubmitissueSitePlanDocument(user, landInputVO);
								logger.debug("OutPut=" + output != null ? output.getStatus() : "null");
								if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								}
							} catch (DatatypeConfigurationException e) {
								logger.error("Failure | " + e.getMessage());
							} catch (ParseException e) {
								logger.error("Failure | " + e.getMessage());
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
		model.addAttribute(PAGE_LABEL, "label.ps.issueSitePlan");
		model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		model.addAttribute("myRequestUrl", PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL);
		return viewname;

	}

	// Need to change the seesion variable to form hiiden
	@RequestMapping(value = ViewPath.RESUBMIT_EXTENSION_GRAND_LAND_REQUEST, method = RequestMethod.POST)
	public String handleExtentionGrandLAndResubmit(@ModelAttribute("extensionOfGrandCommand") ExtensionOfGrandCommand extensionOfGrandCommand, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		super.handleRequest(request, model);
		logger.enter("Issue Site Plan Document Request | handle POST Request");

		String viewname = EMPTY_STRING;
		LoginOutputVO logininfo = null;

		defaultpage = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL;
		viewname = defaultpage;
		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail from session- Login Info Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.extentionGrandLandToService(extensionOfGrandCommand, accountDetailfromToken);
						LandOutputVO output;
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST_MOBILE;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), extensionOfGrandCommand.getRequestNo(), extensionOfGrandCommand.getStausId())) {
							try {
								output = pSReSubmissionRequestService.reSubmitExtentionGrandLand(user, landInputVO);
								if (!output.getStatus().equals(SERVICE_FAILED)) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
									viewname = MOBILE_THANKU_VIEW;
								}
							} catch (Exception e) {
								logger.error("Failure | " + e.getMessage());
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
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.extentionGrandLandToService(extensionOfGrandCommand, accountDetailfromToken);
						LandOutputVO output;
						// This will over written when success
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), extensionOfGrandCommand.getRequestNo(), extensionOfGrandCommand.getStausId()))
							try {

								output = pSReSubmissionRequestService.reSubmitExtentionGrandLand(user, landInputVO);
								if (!output.getStatus().equals(SERVICE_FAILED)) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								}
							} catch (DatatypeConfigurationException e) {
								logger.error("Failure | " + e.getMessage());
							} catch (ParseException e) {
								logger.error("Failure | " + e.getMessage());
							}
					}

				}
			}
			if (viewname.equals(defaultpage)) {
				request.getSession().invalidate();
				logger.info("Failure    |  User Not Loged In   ");
			}
		}

		logger.debug("viewReturn-" + viewname);
		model.addAttribute(PAGE_LABEL, "label.ps.extensiongrantland");
		model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		model.addAttribute("myRequestUrl", PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL);
		logger.info("reSubmitExtentionGrandLand");
		return viewname;

	}

}
