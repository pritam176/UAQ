/**
 * 
 */
package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.ServiceNameConstant.*;
import static com.uaq.common.TilesViewConstant.*;
import static com.uaq.common.UAQURLConstant.*;
import static com.uaq.common.WebServiceConstant.*;

import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.uaq.common.PropertiesUtil;

import com.uaq.common.ViewPath;
import com.uaq.controller.mapper.PSDataMapper;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;
import com.uaq.service.PSAfterServicePayment;
import com.uaq.service.PSFindRequestService;
import com.uaq.service.PortalUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LandInputVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.PSResubmissonOutputVO;
import com.uaq.vo.PageMetadataVO;
import com.uaq.vo.ReSubmiisionInputVO;
import com.uaq.vo.UserDeatilVO;

/**
 * @author WINDOS
 * 
 */
@Controller
public class PSAfterServicePaymentController extends BaseController {

	protected static UAQLogger logger = new UAQLogger(PSAfterServicePaymentController.class);

	@Autowired
	PSAfterServicePayment pSAfterServicePayment;

	@Autowired
	private PortalUtil portalUtil;

	@Autowired
	private PSFindRequestService pSFindRequestService;

	@RequestMapping(value = ViewPath.TO_ISSUE_SITE_PLAN, method = RequestMethod.GET)
	public String servicePaymentIssueSitePlan(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		String requestNo = portalUtil.decryptParameter(request.getParameter(REQUEST_PARAM_REQUEST_NO));
		String serviceId = portalUtil.decryptParameter(request.getParameter(REQUEST_PARAM_SERVICE_ID));
		ReSubmiisionInputVO resubmossioninputVO = new ReSubmiisionInputVO();
		resubmossioninputVO.setAttributeName(SOAP_REQUESTNO_ARGUMENT);
		resubmossioninputVO.setAttributeValue(requestNo);
		String viewname = EMPTY_STRING;

		if (serviceId.equals(ADD_LAND_REQUEST)) {

			PSResubmissonOutputVO resubmitdata = pSFindRequestService.findAddLandRequest(resubmossioninputVO);
			viewname = servicePaymentAddlandRequest(resubmitdata, request, response, model);
			logger.info("ReSubmission Add Land Page Loaded");

		} else if (serviceId.equals(EXTENTION_OF_GRANT_LAND_REQUEST)) {

			PSResubmissonOutputVO resubmitdata = pSFindRequestService.findIssueSitePlanDocRequest(resubmossioninputVO);
			viewname = servicePaymentExtentionGrandLand(resubmitdata, request, response, model);
			logger.info("ReSubmission Issue Site Paln Document Page Loaded");
		}
		model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return viewname;

	}

	private String servicePaymentAddlandRequest(PSResubmissonOutputVO resubmitdata, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		super.handleRequest(request, model);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		String viewname = EMPTY_STRING;
		
		Locale locale =new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.addLand.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.addLand.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.addLand.pagekeyword", null, locale));

		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo.getAcountId() == null) {
				logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				logger.debug("(From Normal Flow)Mobile User Detail from session- Account Id" + logininfo.getAcountId() + "Token Code-" + logininfo.getTokenCode() + "UserName-"
						+ logininfo.getUsername());
			}
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Mobile    |    Token Validated  | ISSUE_SITE_PLAN_FOR_ADD_LAND");
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						if (resubmitdata != null && resubmitdata.getRequestNo() != null) {
							LandInputVO inputVO = PSDataMapper.setReviewDataToAddLandVO(resubmitdata, accountDetailfromToken);
							inputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
							LandOutputVO outputVo;
							try {
								outputVo = pSAfterServicePayment.servicePaymentAddlandRequest(user, inputVO);
								if (!outputVo.getStatus().equals(SERVICE_FAILED)) {
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
									model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputVo.getStatus_EN() : outputVo.getStatus_AR());
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
				logger.info("Mobile  | Failure    |  User Not Loged In   ");
			}
		} else {
			model.addAttribute(ISMOBILE, "false");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | ISSUE_SITE_PLAN_FOR_ADD_LAND");
					AccountDetailTokenOutputVO accountDetailfromToken = (AccountDetailTokenOutputVO) request.getSession().getAttribute(SESSION_ACCOUNT_DETAIL_FROM_TOKEN);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						LandInputVO inputVO = PSDataMapper.setReviewDataToAddLandVO(resubmitdata, accountDetailfromToken);
						inputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO outputVo;
						try {
							outputVo = pSAfterServicePayment.servicePaymentAddlandRequest(user, inputVO);
							if (!outputVo.getStatus().equals("Failed")) {
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputVo.getStatus_EN() : outputVo.getStatus_AR());
							}
						} catch (DatatypeConfigurationException e) {
							logger.error("Failure | " + e.getMessage());
						} catch (ParseException e) {
							logger.error("Failure | " + e.getMessage());
						}
						// viewname =ApplicationConstants.PORTAL_THANKU_VIEW;

					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop  |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.ps.addlandReq");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		}
		return viewname;

	}

	public String servicePaymentExtentionGrandLand(PSResubmissonOutputVO resubmitdata, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		super.handleRequest(request, model);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		String viewname = EMPTY_STRING;
		
		Locale locale=new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.exgrandland.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.exgrandland.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.exgrandland.pagekeyword", null, locale));

		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "false");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Mobile    |    Token Validated  | ISSUE_SITE_PLAN_FOR_EXTENTION_GRAND_LAND");
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						if (resubmitdata != null && resubmitdata.getRequestNo() != null) {
							LandInputVO inputVO = PSDataMapper.setReviewDataToExtentionGrandLandVO(resubmitdata, accountDetailfromToken);
							LandOutputVO outputVo;
							try {
								outputVo = pSAfterServicePayment.servicePaymentExtensionGrandLandRequest(user, inputVO);
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputVo.getStatus_EN() : outputVo.getStatus_AR());
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
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
				logger.info("Mobile  | Failure    |  User Not Loged In   ");
			}
		} else {
			model.addAttribute(ISMOBILE, "false");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | ISSUE_SITE_PLAN_FOR_EXTENTION_GRAND_LAND");
					AccountDetailTokenOutputVO accountDetailfromToken = (AccountDetailTokenOutputVO) request.getSession().getAttribute(SESSION_ACCOUNT_DETAIL_FROM_TOKEN);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						if (resubmitdata != null && resubmitdata.getRequestNo() != null) {
							LandInputVO inputVO = PSDataMapper.setReviewDataToExtentionGrandLandVO(resubmitdata, accountDetailfromToken);
							LandOutputVO outputVo;
							try {
								outputVo = pSAfterServicePayment.servicePaymentExtensionGrandLandRequest(user, inputVO);
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputVo.getStatus_EN() : outputVo.getStatus_AR());
							} catch (DatatypeConfigurationException e) {
								logger.error("Failure | " + e.getMessage());
							} catch (ParseException e) {
								logger.error("Failure | " + e.getMessage());
							}
							// viewname
							// =ApplicationConstants.PORTAL_THANKU_VIEW;

						}
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop  |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.ps.extensiongrantland");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		}
		return viewname;

	}

}
