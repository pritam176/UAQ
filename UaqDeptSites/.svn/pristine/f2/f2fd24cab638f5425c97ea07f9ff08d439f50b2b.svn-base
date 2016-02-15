package com.uaq.controller;



import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.TilesViewConstant.*;
import static com.uaq.common.UAQURLConstant.*;


import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.xmlns.adf.svc.errors.ServiceException;
import com.uaq.command.WhomItmayConcernCommand;

import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.controller.mapper.LPDataMapper;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;
import com.uaq.service.LPRequestService;
import com.uaq.service.LookupServiceEN_AR;
import com.uaq.service.LpLookupService;
import com.uaq.service.PortalUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.PageMetadataVO;
import com.uaq.vo.UserDeatilVO;
import com.uaq.vo.WhomItmayConcernVO;

/**
 * 
 * Controller for Land and Property Valuation
 * 
 * @author Gsekhar
 * 
 **/

@Controller
public class LPTOWhomeEverController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(LPTOWhomeEverController.class);

	@Autowired
	private PortalUtil portalUtil;

	@Autowired
	@Qualifier("lPRequestService")
	LPRequestService lprequestService;

	@Autowired
	@Qualifier("lplookup")
	LpLookupService lplookupservice;

	@Autowired
	private LookupServiceEN_AR lookupServiceEN_AR;

	@RequestMapping(value = ViewPath.WHOM_IT_MAY_CONCERN, method = RequestMethod.GET)
	public String handlewhomItMayConcern(HttpServletRequest request, HttpServletResponse responce, ModelMap model) {
		logger.enter("whomitmayconcern");
		super.handleRequest(request, model);
		String viewname = "";
		LoginOutputVO logininfo = null;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.lp.whomitmayconcern.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.lp.whomitmayconcern.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.lp.whomitmayconcern.pagekeyword", null, locale));

		try {
			Map<String, String> addressedTOMap = lookupServiceEN_AR.getAddressedTOValues(languageCode);
			model.addAttribute("addressedTOMap", addressedTOMap);
		} catch (ServiceException e) {
			logger.debug(e.getMessage());

		}
		if (portalUtil.isMobile(request, responce)) {
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = portalUtil.getLoginDetailFromMobileRequest(request);
			if (portalUtil.validateToken(logininfo)) {
				request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
				logger.info("Success    |    Token Validated");
				WhomItmayConcernCommand whomItmayConcernCommand = new WhomItmayConcernCommand();
				viewname = "lp.whomitmayconcern.mobile";
				model.addAttribute("whomItmayConcernCommand", whomItmayConcernCommand);
			}

			if (viewname.equals(MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("Failure    |  User Not Loged In   ");

			}

		} else {
			// Desktop site handle
			logininfo = (LoginOutputVO) request.getSession().getAttribute( SESSION_LOGIN_INFO_PORTAL);
			viewname =  PORTAL_LOGIN_AGAIN;
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Success    |    Token Validated");
					WhomItmayConcernCommand whomItmayConcernCommand = new WhomItmayConcernCommand();
					viewname = "lp.whomitmayconcern";
					model.addAttribute("whomItmayConcernCommand", whomItmayConcernCommand);
				}

			}
			if (viewname.equals( PORTAL_LOGIN_AGAIN)) {
				logger.info("Failure    |    User Not Loged In ");
				request.getSession().invalidate();

			}
			model.addAttribute( PAGE_LABEL, "label.lp.toWhomCert");
			model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		}
		model.addAttribute( LANGUAGE_TRANSFORMATION_IGNORE, "true");
		return viewname;

	}

	@RequestMapping(value = ViewPath.WHOM_IT_MAY_CONCERN, method = RequestMethod.POST)
	public String handlewhomItMayConcern(@ModelAttribute("whomItmayConcernCommand") WhomItmayConcernCommand whomItmayConcernCommand, ModelMap model, HttpServletRequest request,
			HttpServletResponse responce) throws RemoteException, ParseException {
		logger.enter("To whome ever it my concern | handle POST Request");
		super.handleRequest(request, model);
		String viewname = "";
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		LoginOutputVO logininfo = null;
		logininfo = (LoginOutputVO) request.getSession().getAttribute( SESSION_LOGIN_INFO_MOBILE);
		if (portalUtil.isMobile(request, responce)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty( UAQ_URL) +  URL_SEPARATOR + languageCode +  SERVICES_ERROR_PAGE;
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						WhomItmayConcernVO whomItmayConcern = LPDataMapper.getAddressToDetails(accountDetailfromToken, whomItmayConcernCommand);
						whomItmayConcern.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output = lprequestService.submitWhomItMayConcern(userDetails, whomItmayConcern);
						if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
							logger.info("Status Id | "+output.getSatausId());
							logger.info("request No | "+output.getRequestNo());
							/* if (output.getSatausId().equals("33")) { */
							model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
							model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
							model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
							model.addAttribute( REQUEST_PARAM_LP_LETTER, whomItmayConcern.getAddressTo());
							model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
							model.addAttribute(PAYMENT_URL_EXPRESSON, PORTAL_PAYMENT_URL);
							model.addAttribute( REQUEST_PARAM_TYPE_OF_USER, userDetails.getTypeOfUser());
							model.addAttribute( ISMOBILE, "true");
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty( UAQ_URL) +  URL_SEPARATOR + languageCode +  THANKYOU_PAGE;
							logger.info("Success | Render the Thankyou page for DESKTOP");

							/* } */
							/*
							 * if (output.getSatausId().equals("21")) {
							 * model.addAttribute(REQUEST_PARAM_STATUS_ID,
							 * output.getSatausId());
							 * model.addAttribute(REQUEST_PARAM_SERVICE_ID,
							 * output.getServiceId());
							 * model.addAttribute(REQUEST_PARAM_REQUEST_NO,
							 * output.getRequestNo());
							 * model.addAttribute( ISMOBILE, "true"); viewname
							 * = SPRING_REDIRECT +
							 * PropertiesUtil.getProperty( UAQ_URL) +
							 *  URL_SEPARATOR + languageCode
							 * + "/myrequestreview.html"; }
							 */

						}

					}
				}

			}
			if (viewname.equals( MOBILE_LOGIN_AGAIN)) {
				request.getSession().invalidate();
				logger.info("mobile | Failure    |  User Not Loged In   ");

			}
		} else {
			model.addAttribute( ISMOBILE, "false");
			viewname =  PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute( SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						UserDeatilVO userDetails = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty( UAQ_URL) +  URL_SEPARATOR + languageCode +  SERVICES_ERROR_PAGE;
						WhomItmayConcernVO whomItmayConcern = LPDataMapper.getAddressToDetails(accountDetailfromToken, whomItmayConcernCommand);
						whomItmayConcern.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						LandOutputVO output = lprequestService.submitWhomItMayConcern(userDetails, whomItmayConcern);
						if (output != null && !output.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
							logger.info("Status Id | "+output.getSatausId());
							logger.info("request No | "+output.getRequestNo());
							// Proceed to Application Fee Payment
							/* if (output.getSatausId().equals("33")) { */
							model.addAttribute(REQUEST_PARAM_STATUS_ID, output.getSatausId());
							model.addAttribute(REQUEST_PARAM_SERVICE_ID, output.getServiceId());
							model.addAttribute(REQUEST_PARAM_REQUEST_NO, output.getRequestNo());
							model.addAttribute( REQUEST_PARAM_LP_LETTER, whomItmayConcern.getAddressTo());
							model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? output.getStatus_EN() : output.getStatus_AR());
							model.addAttribute(PAYMENT_URL_EXPRESSON, PORTAL_PAYMENT_URL);
							model.addAttribute( REQUEST_PARAM_TYPE_OF_USER, userDetails.getTypeOfUser());
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty( UAQ_URL) +  URL_SEPARATOR + languageCode +  THANKYOU_PAGE;
							logger.info("Success | Render the Thankyou page for DESKTOP");

							/* } */
							/*
							 * if (output.getSatausId().equals("21")) {
							 * model.addAttribute(REQUEST_PARAM_STATUS_ID,
							 * output.getSatausId());
							 * model.addAttribute(REQUEST_PARAM_SERVICE_ID,
							 * output.getServiceId());
							 * model.addAttribute(REQUEST_PARAM_REQUEST_NO,
							 * output.getRequestNo());
							 * model.addAttribute( ISMOBILE, "false"); viewname
							 * = SPRING_REDIRECT +
							 * PropertiesUtil.getProperty( UAQ_URL) +
							 *  URL_SEPARATOR + languageCode
							 * + "/myrequestreview.html"; }
							 */
						}

					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Failure    |    User is Not Loged In ");
				request.getSession().invalidate();

			}
		}
		model.addAttribute( PAGE_LABEL, "label.lp.toWhomCert");
		return viewname;
	}

	/*
	 * @RequestMapping(value = ViewPath.LAND_PROPERTY_VALUTION, method =
	 * RequestMethod.GET) public String handleLandandProperty(ModelMap model,
	 * HttpServletRequest request) { super.handleRequest(request, model); String
	 * languageCode = request.getParameter("languageCode");
	 * logger.info("Land and Property  Valuation | handle Request");
	 * 
	 * String viewname = ""; LoginOutputVO logininfo = (LoginOutputVO)
	 * request.getSession().getAttribute("logininfo"); if (logininfo != null) {
	 * if (portalUtil.validateToken(logininfo)) {
	 * lookUpDataDropDownLandandProperty(model, languageCode);
	 * model.addAttribute("landandPropertyValuationCommand", new
	 * LandandPropertyValuationCommand());
	 * 
	 * viewname = getViewName(request, "land.property");
	 * 
	 * } else { logger.info("Failure    |   Token Invalided");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } } else {
	 * logger.info("Failure    |    User Not Loged In ");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } model.addAttribute("pagelable",
	 * "label.lp.landProperty"); return viewname; }
	 * 
	 * @RequestMapping(value = ViewPath.LAND_PROPERTY_VALUTION, method =
	 * RequestMethod.POST) public String
	 * handleLandandProperty(@ModelAttribute("landandPropertyValuationCommand")
	 * LandandPropertyValuationCommand landandPropertyValuationCommand, ModelMap
	 * model, HttpServletRequest request) { String languageCode =
	 * request.getParameter("languageCode");
	 * logger.info("Land and Property  Valuation | Submission Request");
	 * super.handleRequest(request, model);
	 * 
	 * String viewname = ""; LoginOutputVO logininfo = (LoginOutputVO)
	 * request.getSession().getAttribute("logininfo"); if (logininfo != null) {
	 * if (portalUtil.validateToken(logininfo)) { AccountDetailTokenOutputVO
	 * accountDetailfromToken = (AccountDetailTokenOutputVO)
	 * request.getSession().getAttribute("AccountDetailfromToken");
	 * LandandPropertyVO landandPropertyVO = new LandandPropertyVO();
	 * landandPropertyVO
	 * .setIndicatePosition(landandPropertyValuationCommand.getIndicatePosition
	 * ());
	 * landandPropertyVO.setLandStatus(landandPropertyValuationCommand.getLandStatus
	 * ());
	 * landandPropertyVO.setLandType(landandPropertyValuationCommand.getLandType
	 * ()); landandPropertyVO.setOwnershipStatus("1");
	 * landandPropertyVO.setSitePlanNumber
	 * (landandPropertyValuationCommand.getSitePlanNumber());
	 * landandPropertyVO.setSector(landandPropertyValuationCommand.getSector());
	 * landandPropertyVO
	 * .setSectorBlock(landandPropertyValuationCommand.getSectorBlock());
	 * landandPropertyVO
	 * .setSubSector(landandPropertyValuationCommand.getSubSector());
	 * landandPropertyVO
	 * .setSectorPlotNumber(landandPropertyValuationCommand.getSectorPlotNumber
	 * ());
	 * landandPropertyVO.setArea(landandPropertyValuationCommand.getArea());
	 * landandPropertyVO
	 * .setAreaBlock(landandPropertyValuationCommand.getAreaBlock());
	 * landandPropertyVO
	 * .setSubArea(landandPropertyValuationCommand.getSubArea());
	 * landandPropertyVO
	 * .setAreaPlotNumber(landandPropertyValuationCommand.getAreaPlotNumber());
	 * landandPropertyVO.setRequestStatus("1");
	 * landandPropertyVO.setRequestType("1");
	 * landandPropertyVO.setOwnerName("1");
	 * landandPropertyVO.setOwnerCertificate
	 * (landandPropertyValuationCommand.getOwnerCertificate());
	 * landandPropertyVO
	 * .setSitePlanDocument(landandPropertyValuationCommand.getSitePlanDocument
	 * ());
	 * 
	 * UserDeatilVO user =
	 * PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
	 * 
	 * GeneralRequestInputVO generlaRequetVO = new GeneralRequestInputVO();
	 * generlaRequetVO.setServiceid("401");
	 * generlaRequetVO.setServiceType("NEW");
	 * generlaRequetVO.setUserComment("Test User");
	 * generlaRequetVO.setSourceType("1");
	 * generlaRequetVO.setSubmittedByUserId(accountDetailfromToken
	 * .getAccountId());
	 * generlaRequetVO.setUserAccountid(accountDetailfromToken.getAccountId());
	 * generlaRequetVO.setStatus("NEW");
	 * generlaRequetVO.setServiceName("Land and Propert Valuation Request");
	 * generlaRequetVO.setRequestID("1"); generlaRequetVO.setWorkflowId("1");
	 * generlaRequetVO.setRequestNo("11"); generlaRequetVO.setLanguageID("1");
	 * 
	 * BigDecimal familyId = new
	 * BigDecimal(StringUtils.isBlank(accountDetailfromToken.getFamilyBookNum())
	 * ? "0" : accountDetailfromToken.getFamilyBookNum());
	 * landandPropertyVO.setFamilyBookID(familyId);
	 * landandPropertyVO.setOwnerNationality
	 * ((accountDetailfromToken.getNationalityId()) == null ? "" :
	 * accountDetailfromToken.getNationalityId().toString());
	 * 
	 * // LandandPropertyOutputVO output = //
	 * lprequestService.executeLandandPropertyRequest(user, // generlaRequetVO,
	 * landandPropertyVO); viewname = getViewName(request, "dept.tahkyou");
	 * 
	 * // model.addAttribute("message", (languageCode.equals(LANG_ENGLISH)) ? //
	 * output.getStatus_EN() : output.getStatus_AR()); } else {
	 * logger.info("Failure    |    Token Invalided ");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } } else {
	 * logger.info("Failure    |    User is Not Loged In ");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } model.addAttribute("pagelable",
	 * "label.lp.landProperty"); return viewname;
	 * 
	 * }
	 * 
	 * // Load new pro Request Page
	 * 
	 * @RequestMapping(value = ViewPath.ISSUE_NEW_PROCARD_SERVICE, method =
	 * RequestMethod.GET) public String loadnewProcardRequest(ModelMap modelMap,
	 * HttpServletRequest request) { super.handleRequest(request, modelMap);
	 * 
	 * String viewname = ""; String languageCode =
	 * request.getParameter("languageCode"); LoginOutputVO logininfo =
	 * (LoginOutputVO) request.getSession().getAttribute("logininfo"); if
	 * (logininfo != null) { if (portalUtil.validateToken(logininfo)) {
	 * modelMap.addAttribute("procardRequestCommand", new
	 * ProcardRequestCommand()); lookUpDataDropDown(modelMap, languageCode);
	 * viewname = getViewName(request, "newprocard.service");
	 * 
	 * } else { logger.info("Failure    |    Token Invalided ");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } } else {
	 * logger.info("Failure    |    User is Not Loged In ");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } modelMap.addAttribute("pagelable",
	 * "label.lp.newProCard"); return viewname;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = ViewPath.ISSUE_NEW_PROCARD_SERVICE, method =
	 * RequestMethod.POST) public String
	 * handlenewProcardRequest(@ModelAttribute("procardRequestCommand")
	 * ProcardRequestCommand procardRequestCommand, ModelMap modelMap,
	 * HttpServletRequest request) { super.handleRequest(request, modelMap);
	 * String languageCode = request.getParameter("languageCode");
	 * 
	 * String viewname = ""; LoginOutputVO logininfo = (LoginOutputVO)
	 * request.getSession().getAttribute("logininfo"); if (logininfo != null) {
	 * if (portalUtil.validateToken(logininfo)) { AccountDetailTokenOutputVO
	 * accountDetailfromToken = (AccountDetailTokenOutputVO)
	 * request.getSession().getAttribute("AccountDetailfromToken");
	 * GeneralRequestInputVO generlaRequetVO = new GeneralRequestInputVO();
	 * generlaRequetVO.setServiceid("403");
	 * generlaRequetVO.setServiceType("NEW");
	 * generlaRequetVO.setUserComment("Test User");
	 * generlaRequetVO.setSourceType("1");
	 * generlaRequetVO.setSubmittedByUserId(accountDetailfromToken
	 * .getAccountId());
	 * generlaRequetVO.setUserAccountid(accountDetailfromToken.getAccountId());
	 * generlaRequetVO.setStatus("NEW");
	 * generlaRequetVO.setServiceName("Lp Card PRO");
	 * generlaRequetVO.setRequestID("1"); generlaRequetVO.setWorkflowId("1");
	 * generlaRequetVO.setRequestNo("11"); generlaRequetVO.setLanguageID("1");
	 * 
	 * NewProCardVO newProCardVO = new NewProCardVO();
	 * newProCardVO.setProName(procardRequestCommand.getProName());
	 * newProCardVO.setProIdNumber(procardRequestCommand.getProIdNumber());
	 * newProCardVO
	 * .setProNationality(procardRequestCommand.getProNationality());
	 * 
	 * newProCardVO.setProIdExpiryDate(procardRequestCommand.getProIdExpiryDate()
	 * ); newProCardVO.setProIdProof(procardRequestCommand.getProIdProof());
	 * newProCardVO.setProPhotograph(procardRequestCommand.getProPhotograph());
	 * 
	 * UserDeatilVO user =
	 * PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
	 * 
	 * // NewProCardOutputVO output = //
	 * lprequestService.executeNewProCard(user, generlaRequetVO, //
	 * newProCardVO); viewname = getViewName(request, "dept.tahkyou");
	 * 
	 * // modelMap.addAttribute("message", (languageCode.equals(LANG_ENGLISH)) // ?
	 * output.getStatus_EN() : output.getStatus_AR()); } else {
	 * logger.info("Failure    |    Token Invalided ");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } } else {
	 * logger.info("Failure    |    User is Not Loged In ");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } modelMap.addAttribute("pagelable",
	 * "label.lp.newProCard"); return viewname; }
	 * 
	 * @RequestMapping(value = ViewPath.RENEW_PROCARD_SERVICE, method =
	 * RequestMethod.GET) public String loadreNewProcardRequest(ModelMap
	 * modelMap, HttpServletRequest request) { super.handleRequest(request,
	 * modelMap);
	 * 
	 * String viewname = ""; String languageCode =
	 * request.getParameter("languageCode"); LoginOutputVO logininfo =
	 * (LoginOutputVO) request.getSession().getAttribute("logininfo"); if
	 * (logininfo != null) { if (portalUtil.validateToken(logininfo)) {
	 * modelMap.addAttribute("procardRequestCommand", new
	 * ProcardRequestCommand()); viewname = getViewName(request,
	 * "renewprocard.service");
	 * 
	 * } else { logger.info("Failure    |    Token Invalided ");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } } else {
	 * logger.info("Failure    |    User is Not Loged In ");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } modelMap.addAttribute("pagelable",
	 * "label.lp.renewProCard"); return viewname;
	 * 
	 * }
	 *//**
	 * 
	 * @param realEstateOfficeCommand
	 * @param modelMap
	 * @param request
	 * @return
	 */
	/*
	 * 
	 * @RequestMapping(value = ViewPath.RENEW_REALESTATE_SERVICE, method =
	 * RequestMethod.GET) public String loadReNewRealEstateRequest(ModelMap
	 * modelMap, HttpServletRequest request) { super.handleRequest(request,
	 * modelMap);
	 * 
	 * String viewname = ""; String languageCode =
	 * request.getParameter("languageCode"); LoginOutputVO logininfo =
	 * (LoginOutputVO) request.getSession().getAttribute("logininfo"); if
	 * (logininfo != null) { if (portalUtil.validateToken(logininfo)) {
	 * modelMap.addAttribute("renewRealEstateOfficeCommand", new
	 * RenewRealEstateOfficeCommand()); viewname = getViewName(request,
	 * "renewrealestate.service");
	 * 
	 * } else { logger.info("Failure    |    Token Invalided ");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } } else {
	 * logger.info("Failure    |    User is Not Loged In ");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } modelMap.addAttribute("pagelable",
	 * "label.lp.renewRealEstate"); return viewname;
	 * 
	 * }
	 *//**
	 * 
	 * @param newRealEstateOfficeCommand
	 * @param modelMap
	 * @param request
	 * @return
	 */
	/*
	 * @RequestMapping(value = ViewPath.NEW_REALESTATE_SERVICE, method =
	 * RequestMethod.GET) public String loadNewRealEstateRequest(@ModelAttribute
	 * NewRealEstateOfficeCommand newRealEstateOfficeCommand, ModelMap modelMap,
	 * HttpServletRequest request) { super.handleRequest(request, modelMap);
	 * 
	 * String viewname = ""; String languageCode =
	 * request.getParameter("languageCode"); LoginOutputVO logininfo =
	 * (LoginOutputVO) request.getSession().getAttribute("logininfo"); if
	 * (logininfo != null) { if (portalUtil.validateToken(logininfo)) { viewname
	 * = getViewName(request, "newrealestate.service");
	 * 
	 * } else { logger.info("Failure    |    Token Invalided ");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } } else {
	 * logger.info("Failure    |    User is Not Loged In ");
	 * request.getSession().invalidate(); viewname = getViewName(request,
	 * "login.again"); } modelMap.addAttribute("pagelable",
	 * "label.lp.newRealEstate"); return viewname;
	 * 
	 * }
	 */

	/*
	 * protected void lookUpDataDropDown(ModelMap model, String languageCode) {
	 * 
	 * Map<String, Map<String, String>> nationalList;
	 * 
	 * try { nationalList = lookupServiceEN_AR.getNationalityListAR_EN();
	 * Map<String, String> nationalList_EN = nationalList.get(LANG_ENGLISH);
	 * model.addAttribute("nationalList", nationalList_EN);
	 * 
	 * } catch (ServiceException e) {
	 * logger.error("Error getting Value from Look Up EService " +
	 * e.getMessage()); e.printStackTrace(); } catch (UAQException e) {
	 * logger.error("Error getting Value from Look Up EService " +
	 * e.getMessage()); e.printStackTrace(); } }
	 * 
	 * protected void lookUpDataDropDownLandandProperty(ModelMap model, String
	 * languageCode) {
	 * 
	 * Map<String, Map<String, String>> landStatusList; Map<String, Map<String,
	 * String>> landTypeList; Map<String, Map<String, String>> sectorList;
	 * Map<String, Map<String, String>> areaList; try { landStatusList =
	 * lplookupservice.landStatusLookupAR_EN(); landTypeList =
	 * lplookupservice.landTypeLookupAR_EN(); sectorList =
	 * lplookupservice.sectorLookupAR_EN(); areaList =
	 * lplookupservice.areaLookupAR_EN();
	 * 
	 * Map<String, String> landStatus_EN = landStatusList.get(LANG_ENGLISH); Map<String,
	 * String> landTypeList_EN = landTypeList.get(LANG_ENGLISH); Map<String, String>
	 * sectorList_EN = sectorList.get(LANG_ENGLISH); Map<String, String> areaList_EN =
	 * areaList.get(LANG_ENGLISH);
	 * 
	 * Map<String, String> landStatus_AR = landStatusList.get("AR"); Map<String,
	 * String> landTypeList_AR = landTypeList.get("AR"); Map<String, String>
	 * sectorList_AR = sectorList.get("AR"); Map<String, String> areaList_AR =
	 * sectorList.get("AR");
	 * 
	 * if (languageCode.equals("ar")) { model.addAttribute("landStatus",
	 * landStatus_AR); model.addAttribute("landTypeList", landTypeList_AR);
	 * model.addAttribute("sectorList", sectorList_AR);
	 * model.addAttribute("areaList", areaList_AR); } else {
	 * model.addAttribute("landStatus", landStatus_EN);
	 * model.addAttribute("landTypeList", landTypeList_EN);
	 * model.addAttribute("sectorList", sectorList_EN);
	 * model.addAttribute("areaList", areaList_EN); }
	 * 
	 * } catch (ServiceException e) {
	 * logger.error("Error getting Value from Look Up EService " +
	 * e.getMessage());
	 * 
	 * } }
	 */

	/*	*//**
	 * Validate The Login Agnaist Token Return From Login
	 * 
	 * @param LoginOutputVO
	 * @return boolean
	 */
	/*
	 * private boolean validateToken(LoginOutputVO logininfo) {
	 * AccountDetailTokenInput accountDetailTokenInput = new
	 * AccountDetailTokenInput();
	 * accountDetailTokenInput.setAcountId(logininfo.getAcountId());
	 * accountDetailTokenInput.setCreatedDate(logininfo.getCreatedDate());
	 * accountDetailTokenInput
	 * .setLastUpdatedDate(logininfo.getLastUpdatedDate());
	 * accountDetailTokenInput.setStatus(logininfo.getStatus());
	 * accountDetailTokenInput.setTokenCode(logininfo.getTokenCode());
	 * accountDetailTokenInput.setTypeOfUser(logininfo.getTypeOfUser());
	 * accountDetailTokenInput.setUsername(logininfo.getUsername()); return
	 * loginService.validateToken(accountDetailTokenInput); }
	 *//**
	 * Set the User Data
	 * 
	 * @param AccountDetailTokenOutputVO
	 * @return UserDeatilVO
	 */
	/*
	 * private UserDeatilVO getUserDetailFrom(AccountDetailTokenOutputVO
	 * accountDetailfromToken) { UserDeatilVO user = new UserDeatilVO();
	 * user.setAccountid(accountDetailfromToken.getAccountId());
	 * user.setTypeOfUser(accountDetailfromToken.getTypeOfUser());
	 * user.setUsername(accountDetailfromToken.getUserName());
	 * user.setNationality((accountDetailfromToken.getNationalityId()) == null ?
	 * "" : accountDetailfromToken.getNationalityId().toString());
	 * user.setAddress1(accountDetailfromToken.getAddressline1());
	 * user.setAddress2(accountDetailfromToken.getAddressline2());
	 * user.setAddress3(""); user.setDOB((accountDetailfromToken.getDob()) ==
	 * null ? new Date() : accountDetailfromToken.getDob().getTime());
	 * user.setEmailID(accountDetailfromToken.getEmailAddress());
	 * user.setEmirate(StringUtils.isBlank(accountDetailfromToken.getEmirate())
	 * ? "" : accountDetailfromToken.getEmirate());
	 * user.setEmiratesId(StringUtils
	 * .isBlank(accountDetailfromToken.getEmiratesId()) ? "" :
	 * accountDetailfromToken.getEmiratesId());
	 * user.setFamilyBookNo(StringUtils.
	 * isBlank(accountDetailfromToken.getFamilyBookNum()) ? "" :
	 * accountDetailfromToken.getFamilyBookNum());
	 * user.setFirstName(accountDetailfromToken.getFirstName());
	 * user.setLastName
	 * (StringUtils.isBlank(accountDetailfromToken.getLastName()) ? "" :
	 * accountDetailfromToken.getLastName());
	 * user.setMiddleName(StringUtils.isBlank
	 * (accountDetailfromToken.getMiddleName()) ? "" :
	 * accountDetailfromToken.getMiddleName());
	 * user.setMobileNo(accountDetailfromToken.getMobileNo());
	 * user.setNationality((accountDetailfromToken.getNationalityId()) == null ?
	 * "" : accountDetailfromToken.getNationalityId().toString());
	 * user.setPOBOX(accountDetailfromToken.getPostbox().toString());
	 * user.setTradeLienceNo
	 * (StringUtils.isBlank(accountDetailfromToken.getTradeLienceNo()) ? "" :
	 * accountDetailfromToken.getTradeLienceNo());
	 * user.setTypeOfUser(accountDetailfromToken.getTypeOfUser());
	 * user.setTradeLienceNo
	 * (StringUtils.isBlank(accountDetailfromToken.getTradeLienceNo()) ? "" :
	 * accountDetailfromToken.getTradeLienceNo());
	 * user.setLanguageId((accountDetailfromToken.getLanguageId()) == null ? ""
	 * : accountDetailfromToken.getLanguageId().toString());
	 * user.setTradeLicenceExpDate
	 * (StringUtils.isBlank(accountDetailfromToken.getTradeLienceExpiryDate()) ?
	 * "1990-01-02" : accountDetailfromToken.getTradeLienceExpiryDate()); return
	 * user; }
	 *//**
	 * This function will get the view name for mobile or desktop as per the
	 * request
	 * 
	 * @param HttpServletRequest
	 * @param String
	 * @return String
	 */
	/*
	 * private String getViewName(HttpServletRequest request, String viewName) {
	 * if (request.getHeader("User-Agent").indexOf("Mobile") != -1) { viewName =
	 * viewName + ".mobile"; logger.info("Mobile Thank you Site Loaded" +
	 * viewName); } else { logger.info("Desktop Thank you Site Loaded" +
	 * viewName); }
	 * 
	 * return viewName;
	 * 
	 * }
	 * 
	 * // user detail info for whom it may concern private UserDeatilVO
	 * getUserDetailsInfo(AccountDetailTokenOutputVO accountDetailfromToken,
	 * WhomItmayConcernCommand whomItmayConcernCommand) { UserDeatilVO
	 * userDetails = new UserDeatilVO();
	 * 
	 * userDetails.setUsername(accountDetailfromToken.getUserName());
	 * userDetails.setTypeOfUser(accountDetailfromToken.getTypeOfUser());
	 * userDetails.setAccountid(accountDetailfromToken.getAccountId());
	 * userDetails.setMobileNo(accountDetailfromToken.getMobileNo());
	 * userDetails.setEmailID(accountDetailfromToken.getEmailAddress());
	 * userDetails.setEmiratesId(accountDetailfromToken.getEmiratesId());
	 * userDetails.setTradeLienceNo(accountDetailfromToken.getTradeLienceNo());
	 * userDetails.setFamilyBookNo(accountDetailfromToken.getFamilyBookNum());
	 * userDetails
	 * .setNationality(String.valueOf(accountDetailfromToken.getNationalityId
	 * ())); userDetails.setEmirate(accountDetailfromToken.getEmirate());
	 * userDetails.setFirstName(accountDetailfromToken.getFirstName());
	 * userDetails.setLastName(accountDetailfromToken.getLastName());
	 * userDetails.setAddress1(accountDetailfromToken.getAddressline1());
	 * userDetails.setAddress2(accountDetailfromToken.getAddressline2());
	 * userDetails
	 * .setPOBOX(String.valueOf(accountDetailfromToken.getPostbox())); //
	 * userDetails.setDOB(accountDetailfromToken.getDob().getTime());
	 * userDetails.setHomePhone(accountDetailfromToken.getHomePhone());
	 * userDetails
	 * .setLanguageId(String.valueOf(accountDetailfromToken.getLanguageId()));
	 * userDetails.setEmiratesCode(accountDetailfromToken.getEmiratesCode() !=
	 * null ? new BigDecimal(accountDetailfromToken.getEmiratesCode()) : new
	 * BigDecimal("0"));
	 * userDetails.setEdiaExpirtyDate(accountDetailfromToken.getEidaExpiryDate
	 * ());
	 * 
	 * return userDetails; }
	 * 
	 * private WhomItmayConcernVO getAddressToDetails(AccountDetailTokenOutputVO
	 * accountDetailfromToken, WhomItmayConcernCommand whomItmayConcernCommand)
	 * {
	 * 
	 * WhomItmayConcernVO whomItmayConcernVO = new WhomItmayConcernVO();
	 * 
	 * whomItmayConcernVO.setAddressTo(whomItmayConcernCommand.getAddressTo());
	 * whomItmayConcernVO
	 * .setFamilyBookNo(whomItmayConcernCommand.getFamilyBookNo());
	 * whomItmayConcernVO
	 * .setSpouseIdNo(whomItmayConcernCommand.getSpouseIdNo());
	 * whomItmayConcernVO.setSource("1");
	 * whomItmayConcernVO.setServiceId("405");
	 * whomItmayConcernVO.setUserCommnets("");
	 * whomItmayConcernVO.setOther(whomItmayConcernCommand.getOther());
	 * 
	 * whomItmayConcernVO.setRequestId(1); whomItmayConcernVO.setRequestNo("");
	 * whomItmayConcernVO.setWorkListId(1); whomItmayConcernVO.setStateId(1);
	 * whomItmayConcernVO.setToWhomItmayId(1);
	 * whomItmayConcernVO.setTempToWhomItmayId(1);
	 * whomItmayConcernVO.setServiceName_En("to whom it may concern");
	 * whomItmayConcernVO.setServiceName_Ar("to whom it may concern");
	 * 
	 * whomItmayConcernVO.setPaymentId(1);
	 * whomItmayConcernVO.setPaymentStatus("");
	 * whomItmayConcernVO.setServiceFee("");
	 * whomItmayConcernVO.setApplicationFee("");
	 * whomItmayConcernVO.setDeptID(""); whomItmayConcernVO.setAppFeeDisc("");
	 * whomItmayConcernVO.setServiceFeeDisc("");
	 * whomItmayConcernVO.setEdirhamServCode("");
	 * whomItmayConcernVO.setIsPaymentRequired("");
	 * 
	 * whomItmayConcernVO.setScanFamilyBook(whomItmayConcernCommand.
	 * getScanFamilyBook_name());
	 * whomItmayConcernVO.setSposeEmiratesId(whomItmayConcernCommand
	 * .getSposeEmiratesId_name());
	 * 
	 * return whomItmayConcernVO; }
	 */
}
