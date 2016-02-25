package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.ServiceNameConstant.*;
import static com.uaq.common.UAQURLConstant.*;
import static com.uaq.common.TilesViewConstant.*;
import static com.uaq.common.StatusNameConstant.*;
import static com.uaq.common.WebServiceConstant.*;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.xmlns.adf.svc.errors.ServiceException;
import com.uaq.command.AddLandRequestCommand;
import com.uaq.command.ExtensionOfGrandCommand;
import com.uaq.command.IssueSitePlanDocumentCommand;
import com.uaq.command.LandDemarcationRequestCommand;

import com.uaq.common.PropertiesUtil;

import com.uaq.common.ViewPath;
import com.uaq.controller.mapper.EGDDataMapper;
import com.uaq.controller.mapper.LPDataMapper;
import com.uaq.controller.mapper.PSDataMapper;
import com.uaq.controller.mapper.PWSDataMapper;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.service.EGDFindRequestService;
import com.uaq.service.LPFindRequestService;
import com.uaq.service.LookupServiceEN_AR;
import com.uaq.service.PSFindRequestService;
import com.uaq.service.PWSFindRequestService;
import com.uaq.util.StringUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.EGDResubmissionVO;
import com.uaq.vo.LPtoWhomeConcernVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.PSGrandLandRequestVO;
import com.uaq.vo.PSResubmissonOutputVO;
import com.uaq.vo.PWSReSubmissonOuputVO;
import com.uaq.vo.PageMetadataVO;
import com.uaq.vo.ReSubmiisionInputVO;

@Controller
public class MyRequestResubmitController extends BaseController {

	protected static UAQLogger logger = new UAQLogger(MyRequestResubmitController.class);

	@Autowired
	private PWSFindRequestService pWSFindRequestService;

	@Autowired
	private PSFindRequestService pSFindRequestService;

	@Autowired
	private com.uaq.service.PortalUtil portalUtil;

	@Autowired
	private EGDFindRequestService eGDFindRequestService;

	@Autowired
	private LPFindRequestService lpFindRequestService;

	@Autowired
	private LookupServiceEN_AR lookupServiceEN_AR;

	String defaultpage = EMPTY_STRING;

	/***
	 * Handler Method for reSubmit Url.This Will parse The Service Name .Get The
	 * Request detail & call the appropriate method for Setting Modal based on
	 * Service Id. URL-/uaq/myrequestresubmit.html
	 * 
	 * @throws javax.xml.rpc.ServiceException
	 * @throws RemoteException
	 **/

	@RequestMapping(value = ViewPath.MY_REQUEST_RESUBMIT, method = RequestMethod.GET)
	public String handleResubmit(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws javax.xml.rpc.ServiceException, RemoteException {
		super.handleRequest(request, model);
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		String serviceId = portalUtil.decryptParameter(request.getParameter(REQUEST_PARAM_SERVICE_ID));
		String requestNo = portalUtil.decryptParameter(request.getParameter(REQUEST_PARAM_REQUEST_NO));
		String statusId = request.getParameter(REQUEST_PARAM_STATUS_ID);

		ReSubmiisionInputVO inputVO = new ReSubmiisionInputVO();
		inputVO.setAttributeName(SOAP_REQUESTNO_ARGUMENT);
		inputVO.setAttributeValue(requestNo);
		// default page for myRequest when Login fail.
		defaultpage = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL;
		String viewname = defaultpage;

		if (serviceId.equals(NEW_WASTE_CONTAINER)) {

			PWSReSubmissonOuputVO resubmitdata = pWSFindRequestService.findWasteContainerRequestView(inputVO);
			resubmitdata.setStatus(statusId);
			viewname = addingModelDataToWasteContainor(resubmitdata, request, response, model);
			logger.info("ReSubmission WasteCOntainor Page Loaded");

		} else if (serviceId.equals(EXTENTION_OF_GRANT_LAND_REQUEST)) {

			PSResubmissonOutputVO resubmitVO = pSFindRequestService.findExtentionGrandLandRequest(inputVO);
			viewname = addingModelToExtentonGrandLand(resubmitVO, request, response, model);
			logger.info("ReSubmission ExtentionGrand Land Page Loaded");

		} else if (serviceId.equals(LAND_DEMACRATION_REQUEST)) {

			PSResubmissonOutputVO resubmitdata = pSFindRequestService.findLandDemacration(inputVO);
			resubmitdata.setStatus(statusId);
			viewname = addingModelToLandDemarcaion(resubmitdata, request, response, model);
			logger.info("ReSubmission Land Demarcation Page Loaded");

		} else if (serviceId.equals(ADD_LAND_REQUEST)) {

			PSResubmissonOutputVO resubmitdata = pSFindRequestService.findAddLandRequest(inputVO);
			viewname = addingModelToAddLand(resubmitdata, request, response, model);
			logger.info("ReSubmission Add Land Page Loaded");

		} else if (serviceId.equals(ISSUE_SITE_PLAN_DOCUMENT_REQUEST)) {

			PSResubmissonOutputVO resubmitdata = pSFindRequestService.findIssueSitePlanDocRequest(inputVO);
			viewname = addingModelToIssueSitePlanDoc(resubmitdata, request, response, model);
			logger.info("ReSubmission Issue Site Paln Document Page Loaded");
		}  else if (serviceId.equals(ISSUE_TO_WHOME_IT_MAY_CERTIFICATE)) {
			LPtoWhomeConcernVO lptoWhomeConcernVO = lpFindRequestService.findLpToWhomConcernView1(inputVO, languageCode);
			lptoWhomeConcernVO.setStatus(statusId);
			viewname = addingModelToWhomItMayConcern(lptoWhomeConcernVO, request, response, model);
			logger.info("ReSubmission Issue Site Paln Document Page Loaded");

		} else if (serviceId.equals(NEW_SUPPLIER_REGISTRATION)) {

			EGDResubmissionVO eGDResubmissionVO = eGDFindRequestService.findNewSupplierRequest(inputVO, languageCode);
			eGDResubmissionVO.setStatus(statusId);
			viewname = addingModelToNewSupplier(eGDResubmissionVO, request, response, model);
			logger.info("ReSubmission new Supplier Registration Page Loaded");

		} else if (serviceId.equals(RENEW_SUPPLIER_REGISTRATION)) {
			EGDResubmissionVO eGDResubmissionVO = eGDFindRequestService.findNewSupplierRequest(inputVO, languageCode);
			eGDResubmissionVO.setStatus(statusId);
			viewname = addingModelreToNewSupplier(eGDResubmissionVO, request, response, model);
			logger.info("ReSubmission re new Supplier Registration Page Loaded");

		} else if (serviceId.equals(ISSUE_NEW_PRO_REQUEST)) {

			if (portalUtil.isMobile(request, response)) {
				model.addAttribute(ISMOBILE, "true");
				viewname = MOBILE_LOGIN_AGAIN;
				LoginOutputVO logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				//LoginOutputVO logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
				if (logininfo != null) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
				}
			}

			viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + "/showServicePhase.html?serviceId=" + ISSUE_NEW_PRO_REQUEST + "&requestNumber="
					+ inputVO.getAttributeValue() + "&servicePhase=Resubmit"+ "&statusId="+statusId;

		} else if (serviceId.equals(RENEW_PRO_REQUEST)) {

			if (portalUtil.isMobile(request, response)) {
				model.addAttribute(ISMOBILE, "true");
				viewname = MOBILE_LOGIN_AGAIN;
				LoginOutputVO logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				//LoginOutputVO logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
				if (logininfo != null) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
				}
			}

			viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + "/showServicePhase.html?serviceId=" + RENEW_PRO_REQUEST + "&requestNumber="
					+ inputVO.getAttributeValue() + "&servicePhase=Resubmit"+ "&statusId="+statusId;

		}
		else if (serviceId.equals(LAND_PROPERTY_VALUTION_REQUEST)) {

		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			LoginOutputVO logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);//
			//LoginOutputVO logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (logininfo != null) {
				request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
			}
		}

		viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + "/showServicePhase.html?serviceId=" + LAND_PROPERTY_VALUTION_REQUEST + "&requestNumber="
				+ inputVO.getAttributeValue() + "&servicePhase=Resubmit"+ "&statusId="+statusId;

	}
		else if (serviceId.equals(NEW_REAL_ESTATE)) {

			if (portalUtil.isMobile(request, response)) {
				model.addAttribute(ISMOBILE, "true");
				viewname = MOBILE_LOGIN_AGAIN;
				LoginOutputVO logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				//LoginOutputVO logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
				if (logininfo != null) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
				}
			}

			viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + "/showServicePhase.html?serviceId=" + NEW_REAL_ESTATE + "&requestNumber="
					+ inputVO.getAttributeValue() + "&servicePhase="+("31".equals(statusId)?"Step1":"Resubmit")+ "&statusId="+statusId;

		}
		else if (serviceId.equals(RENEW_REAL_ESTATE)) {

			if (portalUtil.isMobile(request, response)) {
				model.addAttribute(ISMOBILE, "true");
				viewname = MOBILE_LOGIN_AGAIN;
				LoginOutputVO logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				//LoginOutputVO logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
				if (logininfo != null) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
				}
			}

			viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + "/showServicePhase.html?serviceId=" + RENEW_REAL_ESTATE + "&requestNumber="
					+ inputVO.getAttributeValue() + "&servicePhase="+("31".equals(statusId)?"Step1":"Resubmit")+ "&statusId="+statusId;

		}
		else if (serviceId.equals(LOST_DOCUMENT)) {

			if (portalUtil.isMobile(request, response)) {
				model.addAttribute(ISMOBILE, "true");
				viewname = MOBILE_LOGIN_AGAIN;
				LoginOutputVO logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				//LoginOutputVO logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
				if (logininfo != null) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
				}
			}

			viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + "/showServicePhase.html?serviceId=" + LOST_DOCUMENT + "&requestNumber="
					+ inputVO.getAttributeValue() + "&servicePhase="+("44".equals(statusId)?"Step1":"Resubmit")+ "&statusId="+statusId;

		}
		else if (serviceId.equals(GRAND_LAND_REQUEST)) {

			if (portalUtil.isMobile(request, response)) {
				model.addAttribute(ISMOBILE, "true");
				viewname = MOBILE_LOGIN_AGAIN;
				LoginOutputVO logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
				//LoginOutputVO logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
				if (logininfo != null) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
				}
			}

			viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + "/showServicePhase.html?serviceId=" + GRAND_LAND_REQUEST + "&requestNumber="
					+ inputVO.getAttributeValue() + "&servicePhase=Resubmit"+ "&statusId="+statusId;

		}
		model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return viewname;

	}

	// TODO: Handle Mobile
	private String addingModelToWhomItMayConcern(LPtoWhomeConcernVO resubmitVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		logger.enter("addingModelToWhomItMayConcern");

		String viewname = defaultpage;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.lp.whomitmayconcern.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.lp.whomitmayconcern.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.lp.whomitmayconcern.pagekeyword", null, locale));

		try {
			Map<String, String> addressedTOMap = lookupServiceEN_AR.getAddressedTOValues(languageCode);
			model.addAttribute("addressedTOMap", addressedTOMap);
		} catch (ServiceException e) {
			logger.error("Failed-" + e.getMessage());
		}

		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			//logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("mobile    |    Token Validated  | pws.addingModelToWhomItMayConcern.mobile");
					viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
					if (resubmitVO != null && resubmitVO.getRequestNo() != null) {
						request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
						model.addAttribute("whomItmayConcernCommand", LPDataMapper.setWhomItmayConcernCommand(resubmitVO));
						model.addAttribute(SESSION_REQUEST_ATTACHMENT, resubmitVO.getAttachmentRecPayload());
						model.addAttribute(SESSION_REVIEWER_COMMENTS, resubmitVO.getComments());
						model.addAttribute(SESSION_REQUEST_STATUS, resubmitVO.getStatus());

						if (PROCEED_TO_REJECT.equals(resubmitVO.getStatus())) {
							viewname = MOBILE_TO_WHOM_CERTIFICATE_REJECT;
						} else if (PROCEED_TO_RESUBMIT.equals(resubmitVO.getStatus())) {
							viewname = MOBILE_TO_WHOM_CERTIFICATE_RESUBMIT;
						} else if (ALREADY_RESUBMIT_STATUS.equals(resubmitVO.getStatus())) {
							model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
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
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);

			if (logininfo != null) {
				logger.debug("Portal User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | pws.addingModelToWhomItMayConcern");
					viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
					if (resubmitVO != null && resubmitVO.getRequestNo() != null) {
						model.addAttribute("whomItmayConcernCommand", LPDataMapper.setWhomItmayConcernCommand(resubmitVO));
						model.addAttribute(SESSION_REQUEST_ATTACHMENT, resubmitVO.getAttachmentRecPayload());
						model.addAttribute(SESSION_REVIEWER_COMMENTS, resubmitVO.getComments());
						model.addAttribute(SESSION_REQUEST_STATUS, resubmitVO.getStatus());

						if (PROCEED_TO_REJECT.equals(resubmitVO.getStatus())) {
							viewname = PORTAL_TO_WHOM_CERTIFICATE_REJECT;
						} else if (PROCEED_TO_RESUBMIT.equals(resubmitVO.getStatus())) {
							viewname = PORTAL_TO_WHOM_CERTIFICATE_RESUBMIT;
						} else if (ALREADY_RESUBMIT_STATUS.equals(resubmitVO.getStatus())) {
							model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
						}
					}
				}
			}
			if (viewname.equals(defaultpage)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();

			}
		}
		model.addAttribute(PAGE_LABEL, "label.lp.toWhomCert");
		model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		logger.exit("addingModelToWhomItMayConcern");
		return viewname;
	}

	/***
	 * Method for Setting the Request Data to Model For Resubmit purpose.
	 **/

	private String addingModelToExtentonGrandLand(PSResubmissonOutputVO resubmitVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.enter("addingModelToExtentonGrandLand");
		String viewname = defaultpage;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;
		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.exgrandland.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.exgrandland.pagetitle", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.exgrandland.pagetitle", null, locale));

		BigDecimal resubmitStatus = new BigDecimal(RESUBMIT);
		BigDecimal rejectStatus = new BigDecimal(REJECT);
		BigDecimal alreadySubmited = new BigDecimal(ALREADY_RESUBMIT);

		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			//logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("mobile    |    Token Validated  | pws.addingModelToExtentonGrandLand.mobile");
					viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
					if (resubmitVO != null && resubmitVO.getRequestNo() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						try {
							ExtensionOfGrandCommand  extGrandLandCommand= PSDataMapper.settingExtentionGrandLandCommand(resubmitVO);
							model.addAttribute("extensionOfGrandCommand", extGrandLandCommand);
							model.addAttribute(SESSION_LAND_LOCATION, extGrandLandCommand.getLocations());
							request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
							model.addAttribute(SESSION_REVIEWER_COMMENTS, resubmitVO.getOptionalComments());
							model.addAttribute(SESSION_REQUEST_STATUS, resubmitVO.getStatus());
							model.addAttribute(SESSION_REQUEST_ATTACHMENT, resubmitVO.getExtentionAttachmentList());

							portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);

							if (resubmitVO.getStatusid().compareTo(rejectStatus) == 0) {
								viewname = MOBILE_EX_GRANDLAND_REJECT;
							} else if (resubmitVO.getStatusid().compareTo(resubmitStatus) == 0) {
								viewname = MOBILE_EX_GRANDLAND_RESUBMIT;
							} else if (resubmitVO.getStatusid().compareTo(alreadySubmited) == 0) {
								model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							}
						} catch (Exception e) {
							logger.debug(e.getMessage());
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
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			// viewname = PORTAL_LOGIN_AGAIN;
			if (logininfo != null) {
				logger.debug("Portal User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | pws.addingModelToExtentonGrandLand");
					viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
					portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
					
					try{
						ExtensionOfGrandCommand extGrandLandCommand = PSDataMapper.settingExtentionGrandLandCommand(resubmitVO);
						model.addAttribute("extensionOfGrandCommand", extGrandLandCommand);
						model.addAttribute(SESSION_REVIEWER_COMMENTS, resubmitVO.getOptionalComments());
						model.addAttribute(SESSION_REQUEST_STATUS, resubmitVO.getStatus());
						model.addAttribute(SESSION_REQUEST_ATTACHMENT, resubmitVO.getExtentionAttachmentList());
						model.addAttribute(SESSION_LAND_LOCATION, extGrandLandCommand.getLocations());
					if (resubmitVO.getStatusid().compareTo(rejectStatus) == 0) {
						 viewname = PORTAL_EX_GRANDLAND_REJECT;
					} else if (resubmitVO.getStatusid().compareTo(resubmitStatus) == 0) {
						viewname = PORTAL_EX_GRANDLAND_RESUBMIT;
					} else if (resubmitVO.getStatusid().compareTo(alreadySubmited) == 0) {
						model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
					}
				} catch (Exception e) {
					logger.debug(e.getMessage());
				}
					
				}
			}
			if (viewname.equals(defaultpage)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();

			}
		}
		model.addAttribute(PAGE_LABEL, "label.ps.extensiongrantland");
		model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		logger.info("addingModelToExtentonGrandLand");
		return viewname;
	}

	/***
	 * Method for Setting the Request Data to Model For Resubmit purpose.
	 **/

	private String addingModelDataToWasteContainor(PWSReSubmissonOuputVO resubmissonVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		logger.enter("addingModelDataToWasteContainor");
		String viewname = defaultpage;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.pws.wastecontainer.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.pws.wastecontainer.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.pws.wastecontainer.pagekeyword", null, locale));
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			//logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("mobile    |    Token Validated  | pws.wasteContainer.mobile");
					viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
					if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
						model.addAttribute("wasteContainerCommand", PWSDataMapper.settingWasteContinorCommand(resubmissonVO));
						model.addAttribute(SESSION_REQUEST_ATTACHMENT, resubmissonVO.getWasteContainerattachmentRecPayload());
						model.addAttribute(SESSION_REVIEWER_COMMENTS, resubmissonVO.getUserComments());
						model.addAttribute(SESSION_REQUEST_STATUS, resubmissonVO.getStatus());
						//request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
						model.addAttribute("replacment", resubmissonVO.getReplacementReason());
						// resubmissonVO);

						if (PROCEED_TO_REJECT.equals(resubmissonVO.getStatus())) {
							viewname = MOBILE_WASTECONTAINER_REJECT;
						} else if (PROCEED_TO_RESUBMIT.equals(resubmissonVO.getStatus())) {
							viewname = MOBILE_WASTECONTAINER_RESUBMIT;
						} else if (ALREADY_RESUBMIT_STATUS.equals(resubmissonVO.getStatus())) {
							model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
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
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			// viewname = "login.again";
			if (logininfo != null) {
				logger.debug("Portal User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | pws.wasteContainer");
					viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
					if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
						model.addAttribute("wasteContainerCommand", PWSDataMapper.settingWasteContinorCommand(resubmissonVO));
						model.addAttribute(SESSION_REQUEST_ATTACHMENT, resubmissonVO.getWasteContainerattachmentRecPayload());
						model.addAttribute(SESSION_REVIEWER_COMMENTS, resubmissonVO.getUserComments());
						model.addAttribute(SESSION_REQUEST_STATUS, resubmissonVO.getStatus());
						model.addAttribute("replacment", resubmissonVO.getReplacementReason());
						// request.getSession().setAttribute(SESSION_RESUBMIT_PWS,
						// resubmissonVO);

						if (PROCEED_TO_REJECT.equals(resubmissonVO.getStatus())) {
							viewname = PORTAL_WASTECONTAINER_REJECT;
						} else if (PROCEED_TO_RESUBMIT.equals(resubmissonVO.getStatus())) {
							viewname = PORTAL_WASTECONTAINER_RESUBMIT;
						} else if (ALREADY_RESUBMIT_STATUS.equals(resubmissonVO.getStatus())) {
							model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
						}
					}
				}
			}
			if (viewname.equals(defaultpage)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();

			}
		}
		model.addAttribute(PAGE_LABEL, "dept.lbl.pws.wastcont");
		model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		logger.exit("addingModelDataToWasteContainor");
		return viewname;
	}

	/***
	 * Method for Setting the Request Data to Model For Resubmit purpose.
	 **/
	private String addingModelToAddLand(PSResubmissonOutputVO resubmissonVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		logger.enter("addingModelToAddLand");
		String viewname = defaultpage;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.addLand.resubmit.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.addLand.resubmit.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.addLand.resubmit.pagekeyword", null, locale));

		BigDecimal resubmitStatus = new BigDecimal(RESUBMIT);
		BigDecimal rejectStatus = new BigDecimal(REJECT);
		BigDecimal alreadySubmited = new BigDecimal(ALREADY_RESUBMIT);

		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			//logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("mobile    |    Token Validated  | ps.addLandRequest ");
					viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
					if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
						AddLandRequestCommand addLandRequestCommand = PSDataMapper.settingCommandAddLandCommand(resubmissonVO);
						model.addAttribute("addLandRequestCommand", addLandRequestCommand);
						request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
						model.addAttribute(SESSION_REQUEST_ATTACHMENT, resubmissonVO.getServiceAttachments());
						model.addAttribute(SESSION_REVIEWER_COMMENTS, resubmissonVO.getOptionalComments());
						model.addAttribute(SESSION_REQUEST_STATUS, String.valueOf(resubmissonVO.getStatusid()));
						model.addAttribute(SESSION_LAND_LOCATION, addLandRequestCommand.getLocations());
						portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
						// For Rejected & Resubmit Request
						try {
							if (resubmissonVO.getStatusid().compareTo(rejectStatus) == 0) {
								viewname = MOBILE_ADD_LAND_PLAN_REJECT;
							} else if (resubmissonVO.getStatusid().compareTo(resubmitStatus) == 0) {
								viewname = MOBILE_ADD_LAND_PLAN_RESUBMIT;
							} else if (resubmissonVO.getStatusid().compareTo(alreadySubmited) == 0) {
								model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							}
						} catch (Exception e) {
							logger.error("Request Status comare Failed");
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
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				logger.debug("Portal User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | ps.addLandRequest loading");
					viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
					if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
						portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
						AddLandRequestCommand addLandRequestCommand = PSDataMapper.settingCommandAddLandCommand(resubmissonVO);
						model.addAttribute("addLandRequestCommand", addLandRequestCommand);
						model.addAttribute(SESSION_REQUEST_ATTACHMENT, resubmissonVO.getServiceAttachments());
						model.addAttribute(SESSION_REVIEWER_COMMENTS, resubmissonVO.getOptionalComments());
						model.addAttribute(SESSION_REQUEST_STATUS, String.valueOf(resubmissonVO.getStatusid()));
						model.addAttribute(SESSION_LAND_LOCATION, addLandRequestCommand.getLocations());
						// For Rejected & Resubmit Request
						try {
							if (resubmissonVO.getStatusid().compareTo(rejectStatus) == 0) {
								viewname = PORTAL_ADD_LAND_PLAN_REJECT;
							} else if (resubmissonVO.getStatusid().compareTo(resubmitStatus) == 0) {
								viewname = PORTAL_ADD_LAND_RESUBMIT;
							} else if (resubmissonVO.getStatusid().compareTo(alreadySubmited) == 0) {
								model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							}
						} catch (Exception e) {
							logger.error("Request Status comare Failed");
						}
					}
				}
			}
			if (viewname.equals(defaultpage)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();

			}
		}
		model.addAttribute(PAGE_LABEL, "label.ps.addlandReq");
		model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		logger.exit("addingModelToAddLand");
		return viewname;
	}

	/***
	 * Method for Setting the Request Data to Model For Resubmit purpose.
	 **/

	private String addingModelToIssueSitePlanDoc(PSResubmissonOutputVO resubmissonVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.enter("addingModelToIssueSitePlanDoc");
		String viewname = defaultpage;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.issuesiteplan.resubmit.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.issuesiteplan.resubmit.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.issuesiteplan.resubmit.pagekeyword", null, locale));

		BigDecimal resubmitStatus = new BigDecimal(RESUBMIT);
		BigDecimal rejectStatus = new BigDecimal(REJECT);
		BigDecimal alreadySubmited = new BigDecimal(ALREADY_RESUBMIT);

		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			//logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("mobile    |    Token Validated  | resubmit.ps.issueSitePlanDocument");
					viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
					if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
						portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);

						IssueSitePlanDocumentCommand issueSitePlanDocumentCommand = PSDataMapper.settingIssueSitePlanDocCommand(resubmissonVO);
						model.addAttribute("issueSitePlanDocumentCommand", issueSitePlanDocumentCommand);
						model.addAttribute(SESSION_REQUEST_ATTACHMENT, resubmissonVO.getIssueSitePlnAttachments());
						model.addAttribute(SESSION_REVIEWER_COMMENTS, resubmissonVO.getOptionalComments());
						model.addAttribute(SESSION_REQUEST_STATUS, String.valueOf(resubmissonVO.getStatusid()));
						model.addAttribute(SESSION_LAND_LOCATION, issueSitePlanDocumentCommand.getLocations());
						request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
						try {
							if (resubmissonVO.getStatusid().compareTo(rejectStatus) == 0) {
								viewname = MOBILE_ISSUE_SITE_PLAN_REJECT;
							} else if (resubmissonVO.getStatusid().compareTo(resubmitStatus) == 0) {
								viewname = MOBILE_ISSUE_SITE_PLAN_RESUBMIT;
							} else if (resubmissonVO.getStatusid().compareTo(alreadySubmited) == 0) {
								model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							}
						} catch (Exception e) {
							logger.error("Request Status comare Failed");
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
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				logger.debug("Portal User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | resubmit.ps.issueSitePlanDocument");
					viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
					if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
						portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
						IssueSitePlanDocumentCommand issueSitePlanDocumentCommand = PSDataMapper.settingIssueSitePlanDocCommand(resubmissonVO);
						model.addAttribute("issueSitePlanDocumentCommand", issueSitePlanDocumentCommand);
						model.addAttribute(SESSION_REQUEST_ATTACHMENT, resubmissonVO.getIssueSitePlnAttachments());
						model.addAttribute(SESSION_REVIEWER_COMMENTS, resubmissonVO.getOptionalComments());
						model.addAttribute(SESSION_REQUEST_STATUS, String.valueOf(resubmissonVO.getStatusid()));
						model.addAttribute(SESSION_LAND_LOCATION, issueSitePlanDocumentCommand.getLocations());
						try {
							if (resubmissonVO.getStatusid().compareTo(rejectStatus) == 0) {
								viewname = PORTAL_ISSUE_SITE_PLAN_REJECT;
							} else if (resubmissonVO.getStatusid().compareTo(resubmitStatus) == 0) {
								viewname = PORTAL_ISSUE_SITE_PLAN_RESUBMIT;
							} else if (resubmissonVO.getStatusid().compareTo(alreadySubmited) == 0) {
								model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							}
						} catch (Exception e) {
							logger.error("Request Status comare Failed");
						}

					}
				}
			}
			if (viewname.equals(defaultpage)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();

			}
		}
		model.addAttribute(PAGE_LABEL, "label.ps.issueSitePlan");
		model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		logger.exit("addingModelToIssueSitePlanDoc");
		return viewname;

	}

	/***
	 * Method for Setting the Request Data to Model For Resubmit purpose.
	 **/
	private String addingModelToGrantLand(PSGrandLandRequestVO pSGrandLandRequestVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String viewname = defaultpage;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			//logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("mobile    |    Token Validated  | resubmit.ps.grantlandrequest");
					portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
					model.addAttribute("grantLandRequestCommand", PSDataMapper.settingGrantLandCommand(pSGrandLandRequestVO));
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
					request.getSession().setAttribute(SESSION_RESUBMIT_PS_GRANDLAND, pSGrandLandRequestVO);
					viewname = "resubmit.ps.grantlandrequest";
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
				logger.debug("Portal User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | resubmit.ps.grantlandrequest");
					portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
					model.addAttribute("grantLandRequestCommand", PSDataMapper.settingGrantLandCommand(pSGrandLandRequestVO));
					request.getSession().setAttribute(SESSION_RESUBMIT_PS_GRANDLAND, pSGrandLandRequestVO);
					viewname = "resubmit.ps.grantlandrequest";
				}
			}
			if (viewname.equals(defaultpage)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();

			}

		}

		return viewname;

	}

	/***
	 * Method for Setting the Request Data to Model For Resubmit purpose.
	 **/

	private String addingModelToLandDemarcaion(PSResubmissonOutputVO resubmissonVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.enter("addingModelToLandDemarcaion");
		String viewname = defaultpage;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.ps.landdemarcation.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.ps.landdemarcation.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.ps.landdemarcation.pagekeyword", null, locale));

		BigDecimal resubmitStatus = new BigDecimal(RESUBMIT);
		BigDecimal rejectStatus = new BigDecimal(REJECT);
		BigDecimal alreadySubmited = new BigDecimal(ALREADY_RESUBMIT);

		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			//logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("mobile    |    Token Validated  | ps.LandDemarcaion");
					viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
					if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
						portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
						LandDemarcationRequestCommand ldc = PSDataMapper.settingCommanLandDemarcationCommand(resubmissonVO);
						model.addAttribute("landDemarcationRequestCommand", ldc);
						model.addAttribute(SESSION_REQUEST_ATTACHMENT, resubmissonVO.getLandDemarcationAttachments());
						model.addAttribute(SESSION_REVIEWER_COMMENTS, resubmissonVO.getOptionalComments());
						model.addAttribute(SESSION_REQUEST_STATUS, String.valueOf(resubmissonVO.getStatusid()));
						request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
						model.addAttribute(SESSION_LAND_LOCATION, ldc.getLocations());

						// For Rejected & Resubmit Request
						try {
							if (resubmissonVO.getStatusid().compareTo(rejectStatus) == 0) {
								viewname = MOBILE_LAND_DEMACRATION_REJECT;
							} else if (resubmissonVO.getStatusid().compareTo(resubmitStatus) == 0) {
								viewname = MOBILE_LAND_DEMACRATION_RESUBMIT;
							} else if (resubmissonVO.getStatusid().compareTo(alreadySubmited) == 0) {
								model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							}
						} catch (Exception e) {
							logger.error("Request Status comare Failed");
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
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				logger.debug("Portal User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated  | ps.LandDemarcaion");
					viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
					if (resubmissonVO != null && resubmissonVO.getRequestNo() != null) {
						portalUtil.lookUpDataDropDownPlanningandSurvey(model, languageCode);
						LandDemarcationRequestCommand ldc = PSDataMapper.settingCommanLandDemarcationCommand(resubmissonVO);
						model.addAttribute("landDemarcationRequestCommand", ldc);
						model.addAttribute(SESSION_REQUEST_ATTACHMENT, resubmissonVO.getLandDemarcationAttachments());
						model.addAttribute(SESSION_REVIEWER_COMMENTS, resubmissonVO.getOptionalComments());
						model.addAttribute(SESSION_REQUEST_STATUS, String.valueOf(resubmissonVO.getStatusid()));
						model.addAttribute(SESSION_LAND_LOCATION, ldc.getLocations());
						// For Rejected & Resubmit Request
						try {
							if (resubmissonVO.getStatusid().compareTo(rejectStatus) == 0) {
								viewname = PORTAL_LAND_DEMACRATION_REJECT;
							} else if (resubmissonVO.getStatusid().compareTo(resubmitStatus) == 0) {
								viewname = PORTAL_LAND_DEMACRATION_RESUBMIT;
							} else if (resubmissonVO.getStatusid().compareTo(alreadySubmited) == 0) {
								model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
							}
						} catch (Exception e) {
							logger.error("Request Status comare Failed");
						}
					}
				}
			}
			if (viewname.equals(defaultpage)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();
			}
		}
		model.addAttribute(PAGE_LABEL, "label.ps.landDemorcation");
		model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		logger.exit("addingModelToLandDemarcaion");
		return viewname;

	}

	private String addingModelToNewSupplier(EGDResubmissionVO eGDResubmissionVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.enter("addingModelToNewSupplier");
		String viewname = defaultpage;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;
		String typeOfUser = EMPTY_STRING;

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.egd.resubmitnewSupplierRegistrationPage.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.egd.resubmitnewSupplierRegistrationPage.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.egd.resubmitnewSupplierRegistrationPage.pagekeyword", null, locale));

		try {
			portalUtil.emiratesDropDown(model, languageCode);
		} catch (ServiceException e) {
			logger.error("emiratesDropDown service error" + e.getMessage());

		} catch (UAQException e) {
			logger.error("emiratesDropDown service error" + e.getMessage());
		}

		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			//logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					typeOfUser = logininfo.getTypeOfUser();
					model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, typeOfUser);
					if (typeOfUser.equals(ESTABLISMENT_USER)) {
						AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
						logger.info("mobile    |    Token Validated  | resubmit.egd.newSupplier.mobile");
						if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
							if (eGDResubmissionVO != null && eGDResubmissionVO.getRequestNo() != null) {
								portalUtil.lookUpDataDropDownforEGDSuppCategoryEN_AR(model, languageCode);
								portalUtil.lookUpDataDropDownforEgdSuppRegTypesEN_AR(model, languageCode);
								model.addAttribute("newSupplierRegistrationCommand", EGDDataMapper.addResubmissionDataToModal(eGDResubmissionVO, accountDetailfromToken));
								request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
								
								model.addAttribute(SESSION_REQUEST_ATTACHMENT, eGDResubmissionVO.getAttachmentRecPayload());
								model.addAttribute(SESSION_REVIEWER_COMMENTS, eGDResubmissionVO.getReviewerComments());
								model.addAttribute(SESSION_REQUEST_STATUS, String.valueOf(eGDResubmissionVO.getStatus()));
								model.addAttribute(SESSION_USER_ATTACHMENT, accountDetailfromToken.getUserAttachmentsList());
								if (eGDResubmissionVO.getStatus().equals(PROCEED_TO_REJECT)) {
									viewname = MOBILE_NEW_SUPPLIER_REJECT;
								} else if (eGDResubmissionVO.getStatus().equals(PROCEED_TO_RESUBMIT)) {
									viewname = MOBILE_NEW_SUPPLIER_RESUBMIT;
								} else if (eGDResubmissionVO.getStatus().equals(ALREADY_RESUBMIT_REQUEST)) {
									model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								}
							}
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
			model.addAttribute(ISMOBILE, "false");
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				logger.debug("Portal User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					typeOfUser = logininfo.getTypeOfUser();
					model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, typeOfUser);
					if (typeOfUser.equals(ESTABLISMENT_USER)) {
						AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
						if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
							if (eGDResubmissionVO != null && eGDResubmissionVO.getRequestNo() != null) {
								logger.info("Desktop    |    Token Validated  | resubmit.egd.newSupplier");
								portalUtil.lookUpDataDropDownforEGDSuppCategoryEN_AR(model, languageCode);
								portalUtil.lookUpDataDropDownforEgdSuppRegTypesEN_AR(model, languageCode);
								model.addAttribute("newSupplierRegistrationCommand", EGDDataMapper.addResubmissionDataToModal(eGDResubmissionVO, accountDetailfromToken));
								// request.getSession().setAttribute(SESSION_RESUBMIT_EGD,
								// eGDResubmissionVO);
								model.addAttribute(SESSION_REQUEST_ATTACHMENT, eGDResubmissionVO.getAttachmentRecPayload());
								model.addAttribute(SESSION_REVIEWER_COMMENTS, eGDResubmissionVO.getReviewerComments());
								model.addAttribute(SESSION_REQUEST_STATUS, String.valueOf(eGDResubmissionVO.getStatus()));
								model.addAttribute(SESSION_USER_ATTACHMENT, accountDetailfromToken.getUserAttachmentsList());

								if (eGDResubmissionVO.getStatus().equals(PROCEED_TO_REJECT)) {
									viewname = PORTAL_NEW_SUPPLIER_REJECT;
								} else if (eGDResubmissionVO.getStatus().equals(PROCEED_TO_RESUBMIT)) {
									viewname = PORTAL_NEW_SUPPLIER_RESUBMIT;
								} else if (eGDResubmissionVO.getStatus().equals(ALREADY_RESUBMIT_REQUEST)) {
									model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								}
							}
						}
					}
				}
			}
			if (viewname.equals(defaultpage)) {
				if (StringUtil.isEmpty(typeOfUser)) {
					logger.info("Desktop   |  Failure    |    User Not Loged In ");
					request.getSession().invalidate();
				}
			}
			model.addAttribute(PAGE_LABEL, "label.egd.newSupplier");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
			logger.exit("addingModelToRENewSupplier");
		}

		return viewname;

	}

	private String addingModelreToNewSupplier(EGDResubmissionVO eGDResubmissionVO, HttpServletRequest request, HttpServletResponse responce, ModelMap model) {
		logger.enter("RENewSupplier");
		String viewname = defaultpage;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;
		String typeOfUser = EMPTY_STRING;

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.egd.resubmitreNewSupplierRegistrationPage.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.egd.resubmitreNewSupplierRegistrationPage.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.egd.resubmitreNewSupplierRegistrationPage.pagekeyword", null, locale));

		try {
			portalUtil.emiratesDropDown(model, languageCode);
		} catch (ServiceException e) {
			logger.error("emiratesDropDown service error" + e.getMessage());

		} catch (UAQException e) {
			logger.error("emiratesDropDown service error" + e.getMessage());
		}

		if (portalUtil.isMobile(request, responce)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			//logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					typeOfUser = logininfo.getTypeOfUser();
					model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, typeOfUser);
					if (typeOfUser.equals(ESTABLISMENT_USER)) {
						logger.info("mobile    |    Token Validated  | resubmit.egd.RenewSupplier.mobile");
						AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
						if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
							if (eGDResubmissionVO != null && eGDResubmissionVO.getRequestNo() != null) {
								portalUtil.lookUpDataDropDownforEGDSuppCategoryEN_AR(model, languageCode);
								portalUtil.lookUpDataDropDownforEgdSuppRegTypesEN_AR(model, languageCode);
								model.addAttribute("newSupplierRegistrationCommand", EGDDataMapper.addResubmissionDataToModal(eGDResubmissionVO, accountDetailfromToken));
								request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
								// request.getSession().setAttribute(SESSION_RESUBMIT_EGD,
								// eGDResubmissionVO);
								model.addAttribute(SESSION_REQUEST_ATTACHMENT, eGDResubmissionVO.getAttachmentRecPayload());
								model.addAttribute(SESSION_REVIEWER_COMMENTS, eGDResubmissionVO.getReviewerComments());
								model.addAttribute(SESSION_REQUEST_STATUS, String.valueOf(eGDResubmissionVO.getStatus()));
								model.addAttribute(SESSION_USER_ATTACHMENT, accountDetailfromToken.getUserAttachmentsList());

								if (eGDResubmissionVO.getStatus().equals(PROCEED_TO_REJECT)) {
									viewname = MOBILE_RENEW_SUPPLIER_REJECT;
								} else if (eGDResubmissionVO.getStatus().equals(PROCEED_TO_RESUBMIT)) {
									viewname = MOBILE_RENEW_SUPPLIER_RESUBMIT;
								} else if (eGDResubmissionVO.getStatus().equals(ALREADY_RESUBMIT_REQUEST)) {
									model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								}
							}
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
			model.addAttribute(ISMOBILE, "false");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				logger.debug("Portal User Detail- Logininfo = " + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					typeOfUser = logininfo.getTypeOfUser();
					model.addAttribute(REQUEST_PARAM_TYPE_OF_USER, typeOfUser);
					if (typeOfUser.equals(ESTABLISMENT_USER)) {
						logger.info("Desktop    |    Token Validated  | resubmit.egd.RenewSupplier");
						AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
						if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
							if (eGDResubmissionVO != null && eGDResubmissionVO.getRequestNo() != null) {
								portalUtil.lookUpDataDropDownforEGDSuppCategoryEN_AR(model, languageCode);
								portalUtil.lookUpDataDropDownforEgdSuppRegTypesEN_AR(model, languageCode);
								model.addAttribute("newSupplierRegistrationCommand", EGDDataMapper.addResubmissionDataToModal(eGDResubmissionVO, accountDetailfromToken));
								// request.getSession().setAttribute(SESSION_RESUBMIT_EGD,
								// eGDResubmissionVO);
								model.addAttribute(SESSION_REQUEST_ATTACHMENT, eGDResubmissionVO.getAttachmentRecPayload());
								model.addAttribute(SESSION_REVIEWER_COMMENTS, eGDResubmissionVO.getReviewerComments());
								model.addAttribute(SESSION_REQUEST_STATUS, String.valueOf(eGDResubmissionVO.getStatus()));
								model.addAttribute(SESSION_USER_ATTACHMENT, accountDetailfromToken.getUserAttachmentsList());
								if (eGDResubmissionVO.getStatus().equals(PROCEED_TO_REJECT)) {
									viewname = PORTAL_RENEW_SUPPLIER_REJECT;
								} else if (eGDResubmissionVO.getStatus().equals(PROCEED_TO_RESUBMIT)) {
									viewname = PORTAL_RENEW_SUPPLIER_RESUBMIT;
								} else if (eGDResubmissionVO.getStatus().equals(ALREADY_RESUBMIT_REQUEST)) {
									model.addAttribute(RESPONCE_KEY, messageSource.getMessage("duplicate.request", null, locale));
									viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								}
							}
						}
					}
				}
			}
			if (viewname.equals(defaultpage)) {
				if (StringUtil.isEmpty(typeOfUser)) {
					logger.info("Desktop   |  Failure    |    User Not Loged In ");
					request.getSession().invalidate();
				}

			}
			model.addAttribute(PAGE_LABEL, "label.egd.newSupplier");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
			logger.exit("NewSupplier");

		}

		return viewname;

	}

}