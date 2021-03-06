package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.EMPTY_STRING;
import static com.uaq.common.ApplicationConstants.ISMOBILE;
import static com.uaq.common.ApplicationConstants.LANGUAGE_TRANSFORMATION_IGNORE;
import static com.uaq.common.ApplicationConstants.LANG_ENGLISH;
import static com.uaq.common.ApplicationConstants.PAGE_LABEL;
import static com.uaq.common.ApplicationConstants.PAGE_META_DATA;
import static com.uaq.common.ApplicationConstants.PARAM_LANGUAGE_CODE;
import static com.uaq.common.ApplicationConstants.RESPONCE_KEY;
import static com.uaq.common.ApplicationConstants.SERVICE_FAILED;
import static com.uaq.common.ApplicationConstants.SESSION_LOGIN_INFO_MOBILE;
import static com.uaq.common.ApplicationConstants.SESSION_LOGIN_INFO_PORTAL;
import static com.uaq.common.ApplicationConstants.SPRING_REDIRECT;
import static com.uaq.common.ApplicationConstants.UAQ_URL;
import static com.uaq.common.ApplicationConstants.URL_SEPARATOR;
import static com.uaq.common.TilesViewConstant.DUPLICATE_REQUEST;
import static com.uaq.common.TilesViewConstant.DUPLICATE_REQUEST_MOBILE;
import static com.uaq.common.TilesViewConstant.MOBILE_LOGIN_AGAIN;
import static com.uaq.common.TilesViewConstant.MOBILE_THANKU_VIEW;
import static com.uaq.common.UAQURLConstant.MY_REQUEST_URL;
import static com.uaq.common.UAQURLConstant.SERVICES_ERROR_PAGE;
import static com.uaq.common.UAQURLConstant.THANKYOU_PAGE;

import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.AddLandRequestCommand;
import com.uaq.command.ExtensionOfGrandCommand;
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
import com.uaq.vo.PageMetadataVO;
import com.uaq.vo.UserDeatilVO;

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
						// viewname = SPRING_REDIRECT +
						// PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR +
						// languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.addLandDataToService(addLandRequestCommand, accountDetailfromToken);
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST_MOBILE;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), addLandRequestCommand.getRequestNo(), addLandRequestCommand.getStausId())) {
							viewname = "service.errorpage.mobile";
							try {
								LandOutputVO output = pSReSubmissionRequestService.reSubmitAddLand(user, landInputVO);
								logger.debug("OutPut=" + output != null ? output.getStatus() : "null");
								if (!SERVICE_FAILED.equals(output.getStatus())) {
									// viewname = SPRING_REDIRECT +
									// PropertiesUtil.getProperty(UAQ_URL) +
									// URL_SEPARATOR + languageCode +
									// THANKYOU_PAGE;
									viewname = MOBILE_THANKU_VIEW;
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
								}
							} catch (Exception e) {
								logger.error(e.getMessage(), e);
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

						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.addLandDataToService(addLandRequestCommand, accountDetailfromToken);
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), addLandRequestCommand.getRequestNo(), addLandRequestCommand.getStausId())) {
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
							try {
								LandOutputVO output = pSReSubmissionRequestService.reSubmitAddLand(user, landInputVO);
								logger.debug("OutPut=" + output != null ? output.getStatus() : "null");
								if (!SERVICE_FAILED.equals(output.getStatus())) {
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
								}
							} catch (Exception e) {
								logger.error(e.getMessage(), e);
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
						// viewname = SPRING_REDIRECT +
						// PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR +
						// languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.landDemarcationDataToService(landDemarcationRequestCommand, accountDetailfromToken);
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output;
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST_MOBILE;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), landDemarcationRequestCommand.getRequestNo(), landDemarcationRequestCommand.getStatusid())) {
							try {
								viewname = "service.errorpage.mobile";
								output = pSReSubmissionRequestService.reSubmitLandDemarcationPalnDocument(user, landInputVO);
								//logger.debug("OutPut=" + output != null ? output.getStatus() : "null");
								if (!SERVICE_FAILED.equals(output.getStatus())) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
									// viewname = SPRING_REDIRECT +
									// PropertiesUtil.getProperty(UAQ_URL) +
									// URL_SEPARATOR + languageCode +
									// THANKYOU_PAGE;
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
			logger.enter("Portal Site Requested");
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				logger.debug("Portal User Detail from session- Login Info Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						// viewname = SPRING_REDIRECT +
						// PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR +
						// languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.landDemarcationDataToService(landDemarcationRequestCommand, accountDetailfromToken);
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output;
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), landDemarcationRequestCommand.getRequestNo(), landDemarcationRequestCommand.getStatusid())) {
							try {
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
								output = pSReSubmissionRequestService.reSubmitLandDemarcationPalnDocument(user, landInputVO);
								//logger.debug("OutPut=" + output != null ? output.getStatus() : "null");
								if (!SERVICE_FAILED.equals(output.getStatus())) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								}
							} catch (Exception e) {
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
						// viewname = SPRING_REDIRECT +
						// PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR +
						// languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.issueSitePlanDataToService(issueSitePlanDocumentCommand, accountDetailfromToken);
						// landInputVO.setStatus("1");
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output;
						// This will over written when success
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST_MOBILE;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), issueSitePlanDocumentCommand.getRequestNo(), issueSitePlanDocumentCommand.getStausId())) {
							try {
								viewname = "service.errorpage.mobile";
								output = pSReSubmissionRequestService.reSubmitissueSitePlanDocument(user, landInputVO);
								logger.debug("OutPut=" + output != null ? output.getStatus() : "null");
								if (!SERVICE_FAILED.equals(output.getStatus())) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
									// viewname = SPRING_REDIRECT +
									// PropertiesUtil.getProperty(UAQ_URL) +
									// URL_SEPARATOR + languageCode +
									// THANKYOU_PAGE;
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
			logger.enter("Portal Site Requeset");
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				logger.debug("Portal User Detail from session- Login Info Id" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						// viewname = SPRING_REDIRECT +
						// PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR +
						// languageCode + SERVICES_ERROR_PAGE;
						LandInputVO landInputVO = PSReSubmitDataMapper.issueSitePlanDataToService(issueSitePlanDocumentCommand, accountDetailfromToken);
						// landInputVO.setStatus("1");
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output;
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), issueSitePlanDocumentCommand.getRequestNo(), issueSitePlanDocumentCommand.getStausId())) {
							try {
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
								output = pSReSubmissionRequestService.reSubmitissueSitePlanDocument(user, landInputVO);
								//logger.debug("OutPut=" + output != null ? output.getStatus() : "null");
								if (!SERVICE_FAILED.equals(output.getStatus())) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								}
							} catch (Exception e) {
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

	@RequestMapping(value = ViewPath.RESUBMIT_EXTENSION_GRAND_LAND_REQUEST, method = RequestMethod.POST)
	public String handleExtentionGrandLAndResubmit(@ModelAttribute("extensionOfGrandCommand") ExtensionOfGrandCommand extensionOfGrandCommand, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {

		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);

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
						// viewname = SPRING_REDIRECT +
						// PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR +
						// languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.extentionGrandLandToService(extensionOfGrandCommand, accountDetailfromToken);
						LandOutputVO output;
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST_MOBILE;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), extensionOfGrandCommand.getRequestNo(), extensionOfGrandCommand.getStausId())) {
							try {
								viewname = "service.errorpage.mobile";
								output = pSReSubmissionRequestService.reSubmitExtentionGrandLand(user, landInputVO);
								if (!SERVICE_FAILED.equals(output.getStatus())) {
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
						// viewname = SPRING_REDIRECT +
						// PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR +
						// languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO landInputVO = PSReSubmitDataMapper.extentionGrandLandToService(extensionOfGrandCommand, accountDetailfromToken);
						LandOutputVO output;
						landInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						// This will over written when success
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), extensionOfGrandCommand.getRequestNo(), extensionOfGrandCommand.getStausId()))
							try {
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
								output = pSReSubmissionRequestService.reSubmitExtentionGrandLand(user, landInputVO);
								if (!SERVICE_FAILED.equals(output.getStatus())) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								}
							} catch (Exception e) {
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
