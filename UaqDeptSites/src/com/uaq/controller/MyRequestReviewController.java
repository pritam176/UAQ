package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.UAQURLConstant.*;
import static com.uaq.common.TilesViewConstant.*;
import static com.uaq.common.ServiceNameConstant.*;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.uaq.common.PropertiesUtil;

import com.uaq.common.ViewPath;
import com.uaq.controller.mapper.EGDDataMapper;
import com.uaq.controller.mapper.LPDataMapper;
import com.uaq.controller.mapper.PSDataMapper;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;
import com.uaq.service.EGDFindRequestService;
import com.uaq.service.EGDResubmitRequestService;
import com.uaq.service.LPFindRequestService;
import com.uaq.service.LPReSubmissionRequestService;
import com.uaq.service.PSFindRequestService;
import com.uaq.service.PSReSubmissionRequestService;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.EGDResubmissionVO;
import com.uaq.vo.LPtoWhomeConcernVO;
import com.uaq.vo.LandInputVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.NewSupplierRegistrationVO;
import com.uaq.vo.PSGrandLandRequestVO;
import com.uaq.vo.PSResubmissonOutputVO;
import com.uaq.vo.ReSubmiisionInputVO;
import com.uaq.vo.UserDeatilVO;
import com.uaq.vo.UserFamilyDetailsVO;
import com.uaq.vo.WhomItmayConcernVO;

@Controller
public class MyRequestReviewController extends BaseController {

	protected static UAQLogger logger = new UAQLogger(MyRequestReviewController.class);

	@Autowired
	private LPFindRequestService lpFindRequestService;

	@Autowired
	private PSFindRequestService pSFindRequestService;

	@Autowired
	private com.uaq.service.PortalUtil portalUtil;

	@Autowired
	private PSReSubmissionRequestService pSReSubmissionRequestService;

	@Autowired
	private LPReSubmissionRequestService lPReSubmissionRequestService;

	@Autowired
	private EGDFindRequestService eGDFindRequestService;

	@Autowired
	EGDResubmitRequestService eGDResubmitRequestService;

	@RequestMapping(value = ViewPath.MY_REQUEST_REVIEW, method = RequestMethod.GET)
	public String handleReviewRequest(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws RemoteException, ParseException, ServiceException {

		super.handleRequest(request, model);

		logger.info("In MyRequest Review Controller");

		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);

		String viewname = "";

		String serviceId = portalUtil.decryptParameter(request.getParameter(REQUEST_PARAM_SERVICE_ID));
		String requestNo = portalUtil.decryptParameter(request.getParameter(REQUEST_PARAM_REQUEST_NO));
		String statusId = portalUtil.decryptParameter(request.getParameter(REQUEST_PARAM_STATUS_ID));

		logger.info("From Request Param  | Request No=" + requestNo + "serviceId=" + serviceId + "StatusId=" + statusId);

		ReSubmiisionInputVO inputVO = new ReSubmiisionInputVO();
		inputVO.setAttributeName("RequestNo");
		inputVO.setAttributeValue(requestNo);

		if (serviceId.equals(ADD_LAND_REQUEST)) {

			PSResubmissonOutputVO resubmitdata = pSFindRequestService.findAddLandRequest(inputVO);
			viewname = resubmitAddLand(resubmitdata, request, response, model);
			logger.info("ReSubmission Add Land Page Loaded");

		} else if (serviceId.equals(ISSUE_SITE_PLAN_DOCUMENT_REQUEST)) {

			PSResubmissonOutputVO resubmitdata = pSFindRequestService.findIssueSitePlanDocRequest(inputVO);
			viewname = reSubmitIssueSitePlanDoc(resubmitdata, request, response, model);
			logger.info("ReSubmission Issue Site Paln Document Page Loaded");
		}

		else if (serviceId.equals(LAND_DEMACRATION_REQUEST)) {

			PSResubmissonOutputVO resubmitdata = pSFindRequestService.findLandDemacration(inputVO);
			viewname = reSubmitLandDemacration(resubmitdata, request, response, model);
			logger.info("ReSubmission Issue Site Paln Document Page Loaded");

		}

		else if (serviceId.equals(EXTENTION_OF_GRANT_LAND_REQUEST)) {

			PSResubmissonOutputVO resubmitdata = pSFindRequestService.findExtentionGrandLandRequest(inputVO);
			viewname = reSubmitExtentionGrandLand(resubmitdata, request, response, model);
			logger.info("ReSubmission Issue Site Paln Document Page Loaded");

		}

		else if (serviceId.equals(GRAND_LAND_REQUEST)) {

			PSGrandLandRequestVO pSGrandLandRequestVO = pSFindRequestService.getGrandLandRequest(inputVO);
			viewname = resubmitGrandLandRequest(pSGrandLandRequestVO, request, response, model);
			logger.info("ReSubmission Issue Site Paln Document Page Loaded");

		}

		else if (serviceId.equals(ISSUE_TO_WHOME_IT_MAY_CERTIFICATE)) {

			LPtoWhomeConcernVO lptoWhomeConcernVO = lpFindRequestService.findLpToWhomConcernView1(inputVO, languageCode);
			viewname = resubmitWhomItMayCertificateRequest(lptoWhomeConcernVO, request, response, model);
			logger.info("ReSubmission Issue Site Paln Document Page Loaded");

		} else if (serviceId.equals(NEW_SUPPLIER_REGISTRATION)) {

			EGDResubmissionVO eGDResubmissionVO = eGDFindRequestService.findNewSupplierRequest(inputVO, languageCode);
			viewname = resubmitToNewSupplier(eGDResubmissionVO, request, response, model);
			logger.info("ReSubmission new Supplier Registration Page Loaded");

		} else if (serviceId.equals(RENEW_SUPPLIER_REGISTRATION)) {
			EGDResubmissionVO eGDResubmissionVO = eGDFindRequestService.findNewSupplierRequest(inputVO, languageCode);
			viewname = resubmitToreNewSupplier(eGDResubmissionVO, request, response, model);
			logger.info("ReSubmission re new Supplier Registration Page Loaded");

		}

		model.addAttribute("ignore", "true");
		return viewname;

	}

	private String resubmitWhomItMayCertificateRequest(LPtoWhomeConcernVO lptoWhomeConcernVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws RemoteException,
			ParseException, ServiceException {
		String viewname = "";
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);

		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			logger.enter("Mobile App Requested WhomItMayCertificateRequest");
			viewname = MOBILE_LOGIN_AGAIN;
			model.addAttribute("isMobile", "true");
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo.getAcountId() == null) {
				logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				logger.info("Login Info From Session (Will set In payment Controller.After Succesful thankyou page)| " + logininfo.toString());
			}
			if (logininfo != null) {
				logger.debug("Login Info=" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
					logger.info("mobile    |    Token Validated  | review resubmitGrantLand");
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO userdetailVO = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.toString());
						if (lptoWhomeConcernVO != null && lptoWhomeConcernVO.getRequestNo() != null) {
							WhomItmayConcernVO whomItmayConcernVO = LPDataMapper.getWhomItmayConcernVOForProceedToOpertor(lptoWhomeConcernVO);
							whomItmayConcernVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
							LandOutputVO output = lPReSubmissionRequestService.reSubmitWhomItMayConcern(userdetailVO, whomItmayConcernVO);
							if (!output.getStatus().equalsIgnoreCase("Failed")) {
								model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
								viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
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
			model.addAttribute("isMobile", "false");
			logger.enter("Portal Site requested for WhomItMayCertificateRequest");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated For Grand Land ");
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO userdetailVO = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.toString());
						if (lptoWhomeConcernVO != null && lptoWhomeConcernVO.getRequestNo() != null) {
							WhomItmayConcernVO whomItmayConcernVO = LPDataMapper.getWhomItmayConcernVOForProceedToOpertor(lptoWhomeConcernVO);
							whomItmayConcernVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
							LandOutputVO output = lPReSubmissionRequestService.reSubmitWhomItMayConcern(userdetailVO, whomItmayConcernVO);
							if (!output.getStatus().equalsIgnoreCase("Failed")) {
								model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
								viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							}
						}
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.lp.toWhomCert");
		}
		return viewname;

	}

	private String resubmitGrandLandRequest(PSGrandLandRequestVO pSGrandLandRequestVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String viewname = "";
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			logger.enter("Mobile App Requested for Grand Land");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo.getAcountId() == null) {
				logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				logger.info("Login Info From Session (Will set In payment Controller.After Succesful thankyou page)| " + logininfo.toString());
			}
			if (logininfo != null) {
				logger.debug("Login Info=" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
					logger.info("mobile    |    Token Validated  | review resubmitGrantLand");

					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.toString());

						UserFamilyDetailsVO familyVo = new UserFamilyDetailsVO();

						familyVo.setFamilyMembers(Integer.parseInt(pSGrandLandRequestVO.getFamilyMembersNo()));
						familyVo.setEmployer(pSGrandLandRequestVO.getEmployer());
						familyVo.setMonthlySalary(Integer.parseInt(pSGrandLandRequestVO.getMonthlySalary()));
						familyVo.setCurrentAddress(pSGrandLandRequestVO.getCurrentaddress());
						familyVo.setMaritalStatus(pSGrandLandRequestVO.getMaritalStatus());
						familyVo.setSpouceEmirateId(pSGrandLandRequestVO.getSpousesEmiratesId());
						familyVo.setFamilyBookId("1");
						familyVo.setSpouceEmirateId(pSGrandLandRequestVO.getSpousesEmiratesId());
						familyVo.setPropertyAccountingId("1");
						familyVo.setSalaryCertificateId("1");
						familyVo.setTypeOfUser(accountDetailfromToken.getTypeOfUser());
						familyVo.setUserName(accountDetailfromToken.getUserName());
						familyVo.setNationality((accountDetailfromToken.getNationalityId()) == null ? "" : accountDetailfromToken.getNationalityId().toString());
						familyVo.setTradeLienceNo(StringUtils.isBlank(accountDetailfromToken.getTradeLienceNo()) ? "" : accountDetailfromToken.getTradeLienceNo());

						LandInputVO inputVO = PSDataMapper.setReviewDataToGrandLand(pSGrandLandRequestVO, accountDetailfromToken);

						LandOutputVO outputVo = pSReSubmissionRequestService.reSubmitGrantLand(familyVo, inputVO);

						// viewname = MOBILE_THANKU_VIEW;
						model.addAttribute("isMobile", "true");
						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;

						model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputVo.getStatus_EN() : outputVo.getStatus_AR());
					}
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Mobile  | Failure    |  User Not Loged In   ");

			}
		} else {
			logger.enter("Portal Site requested");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated For Grand Land ");

					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.toString());

						UserFamilyDetailsVO familyVo = new UserFamilyDetailsVO();

						familyVo.setFamilyMembers(Integer.parseInt(pSGrandLandRequestVO.getFamilyMembersNo()));
						familyVo.setEmployer(pSGrandLandRequestVO.getEmployer());
						familyVo.setMonthlySalary(Integer.parseInt(pSGrandLandRequestVO.getMonthlySalary()));
						familyVo.setCurrentAddress(pSGrandLandRequestVO.getCurrentaddress());
						familyVo.setMaritalStatus(pSGrandLandRequestVO.getMaritalStatus());
						familyVo.setSpouceEmirateId(pSGrandLandRequestVO.getSpousesEmiratesId());
						familyVo.setFamilyBookId("1");
						familyVo.setSpouceEmirateId(pSGrandLandRequestVO.getSpousesEmiratesId());
						familyVo.setPropertyAccountingId("1");
						familyVo.setSalaryCertificateId("1");
						familyVo.setTypeOfUser(accountDetailfromToken.getTypeOfUser());
						familyVo.setUserName(accountDetailfromToken.getUserName());
						familyVo.setNationality((accountDetailfromToken.getNationalityId()) == null ? "" : accountDetailfromToken.getNationalityId().toString());
						familyVo.setTradeLienceNo(StringUtils.isBlank(accountDetailfromToken.getTradeLienceNo()) ? "" : accountDetailfromToken.getTradeLienceNo());

						LandInputVO inputVO = PSDataMapper.setReviewDataToGrandLand(pSGrandLandRequestVO, accountDetailfromToken);

						LandOutputVO outputVo = pSReSubmissionRequestService.reSubmitGrantLand(familyVo, inputVO);
						// viewname = PORTAL_THANKU_VIEW;
						model.addAttribute("isMobile", "false");

						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
						model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputVo.getStatus_EN() : outputVo.getStatus_AR());
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();

			}
			model.addAttribute(PAGE_LABEL, "label.ps.addlandReq");
		}

		return viewname;

	}

	private String resubmitAddLand(PSResubmissonOutputVO resubmissonVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String viewname = "";
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			logger.info("Mobile App Reuested AddLand");
			model.addAttribute("isMobile", "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo.getAcountId() == null) {
				logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				logger.info("Login Info From Session (Will set In payment Controller.After Succesful thankyou page)| ");
			}
			if (logininfo != null) {
				logger.debug("Login Info=" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("mobile    |    Token Validated  | review Add Land");
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.getAccountId());
						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
							LandInputVO inputVO = PSDataMapper.setReviewDataToAddLandVO(resubmissonVO, accountDetailfromToken);
							inputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
							LandOutputVO output = pSReSubmissionRequestService.reSubmitAddLand(user, inputVO);
							if (output.getStatus().equals("Failed")) {
								viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
							} else {
								viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
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
			logger.enter("Portal SIte Requested");
			model.addAttribute("isMobile", "false");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | For Add Land");
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.toString());
						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
							LandInputVO inputVO = PSDataMapper.setReviewDataToAddLandVO(resubmissonVO, accountDetailfromToken);
							inputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
							LandOutputVO output = pSReSubmissionRequestService.reSubmitAddLand(user, inputVO);
							if (!output.getStatus().equals("Failed")) {
								viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
							}
						}
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.ps.addlandReq");
		}
		return viewname;

	}

	private String reSubmitLandDemacration(PSResubmissonOutputVO resubmissonVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String viewname = "";
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;
		// Locale locale = new Locale(languageCode);
		if (portalUtil.isMobile(request, response)) {
			model.addAttribute("isMobile", "true");
			logger.enter("Mobile App requested");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo.getAcountId() == null) {
				logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				logger.info("Login Info From Session (Will set In payment Controller.After Succesful thankyou page)| " + logininfo.toString());
			}
			if (logininfo != null) {
				logger.debug("Login Info=" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("mobile    |    Token Validated  | Land demacration");
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.toString());
						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
							LandInputVO inputVo = PSDataMapper.settingServiceToDemarcationVO(resubmissonVO, accountDetailfromToken);
							inputVo.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
							LandOutputVO output;
							try {
								output = pSReSubmissionRequestService.reSubmitLandDemarcationPalnDocument(user, inputVo);
								if (!output.getStatus().equals("Failed")) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
									viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
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
			model.addAttribute("isMobile", "false");
			logger.enter("Portal Site Requested");
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			viewname = PORTAL_LOGIN_AGAIN;
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | review Land demacration");
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.toString());
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
							LandInputVO inputVo = PSDataMapper.settingServiceToDemarcationVO(resubmissonVO, accountDetailfromToken);
							inputVo.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
							LandOutputVO output;
							try {
								output = pSReSubmissionRequestService.reSubmitLandDemarcationPalnDocument(user, inputVo);
								if (!output.getStatus().equals("Failed")) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
									viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
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
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.ps.landDemorcation");
		}
		return viewname;
	}

	private String reSubmitIssueSitePlanDoc(PSResubmissonOutputVO resubmissonVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String viewname = "";
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;

		if (portalUtil.isMobile(request, response)) {
			model.addAttribute("isMobile", "true");
			logger.enter("Mobile App Requested");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo.getAcountId() == null) {
				logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				logger.info("Login Info From Session (Will set In payment Controller.After Succesful thankyou page)| " + logininfo.toString());
			}
			if (logininfo != null) {
				logger.debug("Login Info=" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("mobile    |    Token Validated  | Issue SIte Plan");
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.toString());
						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
							LandInputVO inputVo = PSDataMapper.settingServiceToToIssueSitePlanDocVO(resubmissonVO, accountDetailfromToken);
							inputVo.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
							inputVo.setStatus("2");
							LandOutputVO output;
							try {
								output = pSReSubmissionRequestService.reSubmitissueSitePlanDocument(user, inputVo);
								if (output != null && !output.getStatus().equalsIgnoreCase("Failed")) {
									viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
									model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
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
			logger.enter("Portal site requested");
			model.addAttribute("isMobile", "false");
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			logger.debug("Portal Login info Should from URL=" + logininfo.toString());
			viewname = PORTAL_LOGIN_AGAIN;
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | Issue Site Plan");
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.toString());
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
							LandInputVO inputVo = PSDataMapper.settingServiceToToIssueSitePlanDocVO(resubmissonVO, accountDetailfromToken);
							inputVo.setStatus("2");
							inputVo.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
							LandOutputVO output;
							try {
								output = pSReSubmissionRequestService.reSubmitissueSitePlanDocument(user, inputVo);
								if (output != null && !output.getStatus().equalsIgnoreCase("Failed")) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
									viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
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
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.ps.issueSitePlan");
		}
		return viewname;
	}

	private String reSubmitExtentionGrandLand(PSResubmissonOutputVO resubmissonVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String viewname = "";
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			logger.enter("Mobile App Requested");
			model.addAttribute("isMobile", "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo.getAcountId() == null) {
				logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				logger.info("Login Info From Session (Will set In payment Controller.After Succesful thankyou page)| " + logininfo.toString());
			}
			if (logininfo != null) {
				logger.debug("Login Info=" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("mobile    |    Token Validated  | ExtentionGrandLand");
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.toString());
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
							viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
							LandInputVO inputVO = PSDataMapper.setReviewDataToExtentionGrandLandVO(resubmissonVO, accountDetailfromToken);
							LandOutputVO output;
							try {
								output = pSReSubmissionRequestService.reSubmitExtentionGrandLand(user, inputVO);
								viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								if (output.getStatus().equals("upadated")) {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
								}
							} catch (DatatypeConfigurationException e) {
								logger.error("Failure | " + e.getMessage());
							} catch (ParseException e) {
								logger.error("Failure | " + e.getMessage());
							}

						} else {

						}
					}
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Mobile  | Failure    |  User Not Loged In   ");
			}
		} else {
			model.addAttribute("isMobile", "false");
			logger.enter("Portal site Requested ");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | ExtentionGrandLand");
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.toString());
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
							viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
							LandInputVO inputVO = PSDataMapper.setReviewDataToExtentionGrandLandVO(resubmissonVO, accountDetailfromToken);
							LandOutputVO output;
							try {
								output = pSReSubmissionRequestService.reSubmitExtentionGrandLand(user, inputVO);
								viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								if (output.getStatus().equals("upadated")) {
									model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
								} else {
									model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
								}
							} catch (DatatypeConfigurationException e) {
								logger.error("Failure | " + e.getMessage());
							} catch (ParseException e) {
								logger.error("Failure | " + e.getMessage());
							}
							
							
						} else {
							
						}
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.ps.extensiongrantland");
		}
		return viewname;
	}

	private String resubmitToNewSupplier(EGDResubmissionVO eGDResubmissionVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String viewname = "";
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;
		Locale locale = new Locale(languageCode);
		if (portalUtil.isMobile(request, response)) {
			logger.enter("Mobile site Requested ");
			model.addAttribute("isMobile", "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo.getAcountId() == null) {
				logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				logger.info("Login Info From Session (Will set In payment Controller.After Succesful thankyou page)| " + logininfo.toString());
			}
			if (logininfo != null) {
				logger.debug("Login Info=" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("mobile    |    Token Validated  | new Supplier Review");
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						if (eGDResubmissionVO != null && eGDResubmissionVO.getRequestNo() != null) {
							NewSupplierRegistrationVO supplierDetails = EGDDataMapper.setSupplierDetailToReviewService(eGDResubmissionVO, accountDetailfromToken);
							supplierDetails.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
							LandOutputVO output = eGDResubmitRequestService.reSubmitSupplierRequest(userDetails, supplierDetails);
							if (output.getStatus().equals("upadated")) {
								model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
							} else {
								model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
							}
							viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
						}
					}
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Mobile  | Failure    |  User Not Loged In   ");
			}
		} else {
			model.addAttribute("isMobile", "false");
			logger.enter("Portal site Requested ");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						if (eGDResubmissionVO != null && eGDResubmissionVO.getRequestNo() != null) {
							NewSupplierRegistrationVO supplierDetails = EGDDataMapper.setSupplierDetailToReviewService(eGDResubmissionVO, accountDetailfromToken);
							supplierDetails.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
							LandOutputVO output = eGDResubmitRequestService.reSubmitSupplierRequest(userDetails, supplierDetails);
							if (output.getStatus().equals("upadated")) {
								model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
							} else {
								model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
							}
							viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
						}
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();
			}
			model.addAttribute(PAGE_LABEL, "label.egd.newSupplier");
		}
		return viewname;
	}

	private String resubmitToreNewSupplier(EGDResubmissionVO eGDResubmissionVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String viewname = "";
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;
		Locale locale = new Locale(languageCode);
		if (portalUtil.isMobile(request, response)) {
			logger.enter("Mobile site Requested ");
			model.addAttribute("isMobile", "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo.getAcountId() == null) {
				logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				logger.info("Login Info From Session (Will set In payment Controller.After Succesful thankyou page)| " + logininfo.toString());
			}
			if (logininfo != null) {
				logger.debug("Mobile My Request URl.Login info Should from URL=" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("mobile    |    Token Validated  | new Supplier Review");
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + ViewPath.SERVICES_ERROR_PAGE;
						if (eGDResubmissionVO != null && eGDResubmissionVO.getRequestNo() != null) {
							NewSupplierRegistrationVO supplierDetails = EGDDataMapper.setSupplierDetailToReviewService(eGDResubmissionVO, accountDetailfromToken);
							supplierDetails.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
							LandOutputVO output = eGDResubmitRequestService.reSubmitSupplierRequest(userDetails, supplierDetails);
							if (output.getStatus().equals("upadated")) {
								model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
							} else {
								model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
							}
							viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
						}
					}
				}
			}
			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Mobile  | Failure    |  User Not Loged In   ");
			}
		} else {
			logger.enter("Portal site Requested ");
			model.addAttribute("isMobile", "false");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						if (eGDResubmissionVO != null && eGDResubmissionVO.getRequestNo() != null) {
							NewSupplierRegistrationVO supplierDetails = EGDDataMapper.setSupplierDetailToReviewService(eGDResubmissionVO, accountDetailfromToken);
							supplierDetails.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
							LandOutputVO output = eGDResubmitRequestService.reSubmitSupplierRequest(userDetails, supplierDetails);

							if (output.getStatus().equals("upadated")) {
								model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
							} else {
								model.addAttribute(RESPONCE_KEY, (languageCode.equals("en")) ? output.getStatus_EN() : output.getStatus_AR());
							}
							viewname = "redirect:" + PropertiesUtil.getProperty("uaq.url") + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
						}
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();

			}
			model.addAttribute(PAGE_LABEL, "label.egd.newSupplier");
		}

		return viewname;

	}

}
