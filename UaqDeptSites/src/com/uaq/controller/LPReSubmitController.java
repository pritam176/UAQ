/**
 * 
 */
package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.EMPTY_STRING;
import static com.uaq.common.ApplicationConstants.ISMOBILE;
import static com.uaq.common.ApplicationConstants.LANG_ENGLISH;
import static com.uaq.common.ApplicationConstants.PAGE_LABEL;
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
import static com.uaq.common.TilesViewConstant.PORTAL_LOGIN_AGAIN;
import static com.uaq.common.UAQURLConstant.MY_REQUEST_URL;
import static com.uaq.common.UAQURLConstant.SERVICES_ERROR_PAGE;
import static com.uaq.common.UAQURLConstant.THANKYOU_PAGE;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.WhomItmayConcernCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.controller.mapper.LPDataMapper;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;
import com.uaq.service.LPReSubmissionRequestService;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LandOutputVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.UserDeatilVO;
import com.uaq.vo.WhomItmayConcernVO;

/**
 * @author TACME
 * 
 */

@Controller
public class LPReSubmitController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(LPReSubmitController.class);

	@Autowired
	private com.uaq.service.PortalUtil portalUtil;

	@Autowired
	private LPReSubmissionRequestService lPReSubmissionRequestService;

	@RequestMapping(value = ViewPath.RESUBMIT_WHOM_IT_MAY_CONCERN, method = RequestMethod.POST)
	public String handleReSubmitRequest(@ModelAttribute("whomItmayConcernCommand") WhomItmayConcernCommand whomItmayConcernCommand, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws RemoteException, ParseException, ServiceException {

		super.handleRequest(request, model);
		String viewname = EMPTY_STRING;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		
		LoginOutputVO logininfo = null;
		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			logger.enter("Mobile App Requested for Grand Land");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					request.getSession().setAttribute(SESSION_LOGIN_INFO_MOBILE, logininfo);
					logger.info("mobile    |    Token Validated  | review resubmitGrantLand");

					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						UserDeatilVO userdetailVO = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.toString());
						WhomItmayConcernVO whomItmayConcernVO = LPDataMapper.getWhomItmayConcernVOForResubmit(whomItmayConcernCommand);
						whomItmayConcernVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST_MOBILE;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), whomItmayConcernCommand.getRequestNo(), whomItmayConcernCommand.getStatusId())) {
							viewname = "service.errorpage.mobile";
							LandOutputVO outputVo = lPReSubmissionRequestService.afterPaymentWhomItMayConcern(userdetailVO, whomItmayConcernVO);
							if (!outputVo.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputVo.getStatus_EN() : outputVo.getStatus_AR());
								// viewname = SPRING_REDIRECT +
								// PropertiesUtil.getProperty(UAQ_URL) +
								// URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								viewname = MOBILE_THANKU_VIEW;
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
			logger.enter("Portal Site requested");
			viewname = PORTAL_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_PORTAL);
			if (logininfo != null) {
				if (portalUtil.validateToken(logininfo)) {
					logger.info("Desktop    |    Token Validated For Grand Land ");

					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						UserDeatilVO userdetailVO = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						// viewname = SPRING_REDIRECT +
						// PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR +
						// languageCode + SERVICES_ERROR_PAGE;
						logger.info("Account Detail From LoginInfo =" + accountDetailfromToken.toString());
						WhomItmayConcernVO whomItmayConcernVO = LPDataMapper.getWhomItmayConcernVOForResubmit(whomItmayConcernCommand);
						whomItmayConcernVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), whomItmayConcernCommand.getRequestNo(), whomItmayConcernCommand.getStatusId())) {
							viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
							LandOutputVO outputVo = lPReSubmissionRequestService.afterPaymentWhomItMayConcern(userdetailVO, whomItmayConcernVO);
							if (outputVo != null && !outputVo.getStatus().equalsIgnoreCase(SERVICE_FAILED)) {
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								model.addAttribute(RESPONCE_KEY, (languageCode.equals(LANG_ENGLISH)) ? outputVo.getStatus_EN() : outputVo.getStatus_AR());
							}
						}
					}
				}
			}
			if (viewname.equals(PORTAL_LOGIN_AGAIN)) {
				logger.info("Desktop   |  Failure    |    User Not Loged In ");
				request.getSession().invalidate();

			}

		}
		model.addAttribute("myRequestUrl", PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL);
		model.addAttribute(PAGE_LABEL, "label.lp.toWhomCert");

		return viewname;

	}

}
