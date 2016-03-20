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
							//viewname = MOBILE_THANKU_VIEW;
							logger.info("Success | Render the Thankyou page for DESKTOP");
							
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

	
}
