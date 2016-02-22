package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.TilesViewConstant.*;
import static com.uaq.common.UAQURLConstant.*;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uaq.command.WasteContainerCommand;

import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.controller.mapper.PWSDataMapper;
import com.uaq.controller.mapper.PortalDataMapper;
import com.uaq.logger.UAQLogger;
import com.uaq.service.PWSResubmitRequestService;
import com.uaq.service.PortalUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.PageMetadataVO;
import com.uaq.vo.UserDeatilVO;
import com.uaq.vo.WasteContainerRequestInputVO;
import com.uaq.vo.WasteContainerRequestOutputVO;

@Controller
public class PWSResubmitController extends BaseController {

	public static transient UAQLogger logger = new UAQLogger(PWSResubmitController.class);

	@Autowired
	private PortalUtil portalUtil;

	@Autowired
	private PWSResubmitRequestService pWSResubmitRequestService;

	@RequestMapping(value = ViewPath.RESUBMIT_WASTECONTAINER, method = RequestMethod.POST)
	public String handleWasteContainorResubmit(@ModelAttribute("wasteContainerCommand") WasteContainerCommand wasteContainerCommand, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		super.handleRequest(request, model);
		logger.info("Handle WasteContainer Resubmit POST ");
		String viewname = "";
		LoginOutputVO logininfo = null;
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);

		Locale locale = new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.pws.resubmitwastecontainer.pagetitle", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.pws.resubmitwastecontainer.pagedescription", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.pws.resubmitwastecontainer.pagekeyword", null, locale));

		viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL;

		if (portalUtil.isMobile(request, response)) {
			model.addAttribute(ISMOBILE, "true");
			viewname = MOBILE_LOGIN_AGAIN;
			logininfo = (LoginOutputVO) request.getSession().getAttribute(SESSION_LOGIN_INFO_MOBILE);
			if (logininfo != null) {
				logger.debug("Mobile My Request URl.Login info Should from URL=" + logininfo.toString());
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						WasteContainerRequestInputVO wasteContainerRequestInputVO = PWSDataMapper.setcommandToVO(wasteContainerCommand);
						wasteContainerRequestInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						// This will over written when success
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST_MOBILE;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), wasteContainerCommand.getRequestNo(), wasteContainerCommand.getStatus())) {
							
							WasteContainerRequestOutputVO output = pWSResubmitRequestService.reSubmitWasteContainor(user, wasteContainerRequestInputVO, wasteContainerCommand);
							if (!output.getStatus().equals(SERVICE_FAILED)) {
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								model.addAttribute("message", (languageCode.equals(LANG_ENGLISH)) ? output.getMessage_EN() : output.getMessage_AR());
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
				if (portalUtil.validateToken(logininfo)) {
					AccountDetailTokenOutputVO accountDetailfromToken = portalUtil.getAccountDetailForMobile(logininfo);
					if (accountDetailfromToken != null && accountDetailfromToken.getAccountId() != null) {
						viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + SERVICES_ERROR_PAGE;
						UserDeatilVO user = PortalDataMapper.getUserDetailFrom(accountDetailfromToken);
						WasteContainerRequestInputVO wasteContainerRequestInputVO = PWSDataMapper.setcommandToVO(wasteContainerCommand);
						wasteContainerRequestInputVO.setLanguageId(PortalDataMapper.getLanguageId(languageCode));
						// This will over written when success
						model.addAttribute(RESPONCE_KEY, "request.invalid.data");
						viewname = DUPLICATE_REQUEST;
						if (portalUtil.validateRequestForSubmission(logininfo.getUsername(), wasteContainerCommand.getRequestNo(), wasteContainerCommand.getStatus())) {
							
							WasteContainerRequestOutputVO output = pWSResubmitRequestService.reSubmitWasteContainor(user, wasteContainerRequestInputVO, wasteContainerCommand);
							if (!output.getStatus().equals(SERVICE_FAILED)) {
								viewname = SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + THANKYOU_PAGE;
								model.addAttribute("message", (languageCode.equals(LANG_ENGLISH)) ? output.getMessage_EN() : output.getMessage_AR());
							}

						}
					}
				}
			}
			
		}

		if (viewname.equals(SPRING_REDIRECT + PropertiesUtil.getProperty(UAQ_URL) + "/" + languageCode + "/myrequest.html")) {
			request.getSession().invalidate();
			logger.info("Failure    |  User Not Loged In   ");
		}
		model.addAttribute(LANGUAGE_TRANSFORMATION_IGNORE, "true");
		model.addAttribute(PAGE_META_DATA, pageMetadataVO);
		model.addAttribute(PAGE_LABEL, "dept.lbl.pws.wastcont");
		model.addAttribute("myRequestUrl", PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + languageCode + URL_SEPARATOR + MY_REQUEST_URL);
		return viewname;
	}

}
