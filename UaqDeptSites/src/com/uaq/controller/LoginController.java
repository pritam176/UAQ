package com.uaq.controller;

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.TilesViewConstant.*;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uaq.command.Logincommand;

import com.uaq.common.PropertiesUtil;
import com.uaq.common.ViewPath;
import com.uaq.service.LoginService;
import com.uaq.vo.AccountDetailTokenInput;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LoginInputVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.LogoutInputVO;
import com.uaq.vo.LogoutOutputVO;
import com.uaq.vo.MyProfileVO;
import com.uaq.vo.PageMetadataVO;

/**
 * Controller Class For Login & Logout
 * 
 * @author Pritam
 * 
 */
@Controller
public class LoginController extends BaseController {

	@Autowired
	private LoginService loginService;
	
	DateFormat df = new SimpleDateFormat(DATE_FORMAT);
	/**Ajax Submit*/
	@RequestMapping(value = ViewPath.USER_LOGIN, method = RequestMethod.POST, produces={PRODUCE_JAVASCRIPT})
	@ResponseBody
	public String handleLoginRequest(@ModelAttribute("logincommand") Logincommand logincommand, HttpServletRequest request,
			ModelMap modelMap) {

		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		super.handleRequest(request, modelMap);
		String returnmsg = "";
		JSONObject jsonObject=new JSONObject();
		HttpSession session = request.getSession(true);
		LoginInputVO input = new LoginInputVO();
		/*input.setLoginUserName(logincommand.getUsername());
		input.setLoginPassword(logincommand.getPassword());*/
		
		input.setLoginUserName(request.getParameter(LOGIN_USERNAME));
		input.setLoginPassword(request.getParameter(LOGIN_PASSWORD));
		
		LoginOutputVO logininfo = loginService.loginServiceRequest(input);
		if (logininfo != null) {
			if (logininfo.getExecutionStatus().equals(SERVICE_SUCCESS)) {
				
				session.setAttribute(SESSION_LOGIN_INFO_PORTAL, logininfo);
				
				AccountDetailTokenInput accountDetailTokenInput = new AccountDetailTokenInput();
				accountDetailTokenInput.setAcountId(logininfo.getAcountId());
				accountDetailTokenInput.setCreatedDate(logininfo.getCreatedDate());
				accountDetailTokenInput.setLastUpdatedDate(logininfo.getLastUpdatedDate());
				accountDetailTokenInput.setStatus(logininfo.getStatus());
				accountDetailTokenInput.setTokenCode(logininfo.getTokenCode());
				accountDetailTokenInput.setTypeOfUser(logininfo.getTypeOfUser());
				accountDetailTokenInput.setUsername(logininfo.getUsername());
				AccountDetailTokenOutputVO ouputAccountDetailfromToken = loginService.getDetailFromToken(accountDetailTokenInput);
				if (ouputAccountDetailfromToken.getExecutionStatus().equals(SERVICE_SUCCESS)) {
					session.setAttribute(SESSION_ACCOUNT_DETAIL_FROM_TOKEN, ouputAccountDetailfromToken);
					logger.info("Success    | Forworded to Home page");
					jsonObject.put("status", "Success");
					jsonObject.put("name", ouputAccountDetailfromToken.getFirstName().trim().split(" ")[0]);
				} else {
					//modelMap.addAttribute("message", );
					
					jsonObject.put("status", "failure");
					jsonObject.put("msg", (languageCode.equals(LANG_ENGLISH)) ? ouputAccountDetailfromToken.getMessage_EN(): ouputAccountDetailfromToken.getMessage_AR());
					
					logger.info("Failure    |" + ouputAccountDetailfromToken.getExecutionStatus());
				}
			} else {
				//modelMap.addAttribute("message", (languageCode.equals("en")) ? logininfo.getMessage_EN() : logininfo.getMessage_AR());
				jsonObject.put("status", "failure");
				jsonObject.put("msg", (languageCode.equals(LANG_ENGLISH)) ? logininfo.getMessage_EN(): logininfo.getMessage_AR());
				logger.info("Failure    | " + logininfo.getMessage_EN());
			}
		} else {
			//modelMap.addAttribute("message", "Some Internal Issue");
			jsonObject.put("status", "failure");
			logger.info("Failure    | loginService.loginServiceRequest(input); return null");
		}
		returnmsg = jsonObject.toString();
		return returnmsg;

	}
	@RequestMapping(value=ViewPath.USER_LOGOUT,method=RequestMethod.GET)
	public String handleLogoutRequest(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		
		logger.debug("Received request to show login page");
		
		String view = "redirect:" + PropertiesUtil.getProperty(UAQ_URL) + URL_SEPARATOR + request.getParameter(PARAM_LANGUAGE_CODE) + URL_SEPARATOR + FROM_HOME_PAGE +URL_HTML_EXTENSION;
//		String view = "redirect:http://PropertiesUtil.getProperty("uaq.url")/"+ request.getParameter("languageCode") + "/home.html";
		
		   Map<String, String> resultMap = new HashMap<String, String>();
		   
           try {
                           super.handleRequest(request, model);
           } catch (Exception wamException) {
                           logger.debug("Error processing the request" + wamException);
                           resultMap.put("message", "error");
                           resultMap.put("error", wamException.getLocalizedMessage());
           }
		
		HttpSession session = request.getSession();
		LoginOutputVO logininfo = (LoginOutputVO) session.getAttribute(SESSION_LOGIN_INFO_PORTAL);
		
		LogoutInputVO logoutInputVO=new LogoutInputVO();
		LogoutOutputVO logoutOP =null;
		
		if(logininfo!=null){
			logoutInputVO.setAcountId(logininfo.getAcountId());
			logoutInputVO.setCreatedDate(logininfo.getCreatedDate());
			logoutInputVO.setLastUpdatedDate(logininfo.getLastUpdatedDate());
			logoutInputVO.setStatus(logininfo.getStatus());
			logoutInputVO.setTokenCode(logininfo.getTokenCode());
			logoutInputVO.setTypeOfUser(logininfo.getTypeOfUser());
			logoutInputVO.setUsername(logininfo.getUsername());
			logoutOP=loginService.logoutServiceRequest(logoutInputVO);
			
				 session.invalidate();
				 
		}
		  request.getSession().removeAttribute("logStatus");
          request.getSession().removeAttribute("userName");
          response.setHeader("Cache-Control", "no-cache, no-store");
          response.setHeader("Pragma", "no-cache");
          request.getSession().invalidate();

          Logincommand loginCommand = new Logincommand();
          model.addAttribute(loginCommand);

          return view;
	
	}
	
	
	@RequestMapping(value = ViewPath.USER_PROFILE, method = RequestMethod.GET)
	public String handleprofile(HttpServletRequest request, ModelMap model) throws ParseException {
		super.handleRequest(request, model);
		MyProfileVO myprofilevo=new MyProfileVO();
		logger.enter("User Profile | handle Request");
		String languageCode = request.getParameter(PARAM_LANGUAGE_CODE);
		Locale locale =new Locale(languageCode);
		PageMetadataVO pageMetadataVO = new PageMetadataVO();
		pageMetadataVO.setPageTitle(messageSource.getMessage("label.profile", null, locale));
		pageMetadataVO.setPageDescription(messageSource.getMessage("label.profile", null, locale));
		pageMetadataVO.setPageKeywords(messageSource.getMessage("label.profile", null, locale));
	
		HttpSession session = request.getSession();
		AccountDetailTokenOutputVO accountDetailfromToken = (AccountDetailTokenOutputVO) session.getAttribute(SESSION_ACCOUNT_DETAIL_FROM_TOKEN);
		String view=EMPTY_STRING;
		if (accountDetailfromToken != null && accountDetailfromToken.getAccountId()!= null) {
		
		myprofilevo.setFirstName(accountDetailfromToken.getFirstName());
		
		myprofilevo.setMobileNo(accountDetailfromToken.getMobileNo());
		myprofilevo.setEmailAddress(accountDetailfromToken.getEmailAddress());
		myprofilevo.setPassportNo(accountDetailfromToken.getPassportNo());
		myprofilevo.setEmiratesId((accountDetailfromToken.getEmiratesId())==null?EMPTY_STRING:accountDetailfromToken.getEmiratesId());
		myprofilevo.setTradeLienceNo((accountDetailfromToken.getTradeLienceNo())==null?EMPTY_STRING:accountDetailfromToken.getTradeLienceNo());
		myprofilevo.setTypeOfUser(accountDetailfromToken.getTypeOfUser());
		String defaultDate=DEFAULT_DATE;
		java.util.Date dob = (accountDetailfromToken.getDob())==null? df.parse(defaultDate): accountDetailfromToken.getDob().getTime();
		   String dateofBirth = df.format(dob);
		myprofilevo.setDob(dateofBirth);
	
		
		if (languageCode.equals(LANG_ARABIC)) {
			myprofilevo.setCountryidofresidency(accountDetailfromToken.getCountryidofresidency_AR());
			myprofilevo.setCountryidofcitizenship(accountDetailfromToken.getCountryidofcitizenship_AR());
		} else {
			myprofilevo.setCountryidofresidency(accountDetailfromToken.getCountryidofresidency_EN());
			myprofilevo.setCountryidofcitizenship(accountDetailfromToken.getCountryidofcitizenship_EN());
		}
		
		model.addAttribute("accountDetailfromToken",myprofilevo);
		view = "myprofile.view";
		}else {
			view = PORTAL_LOGIN_AGAIN;
		}
		model.addAttribute(PAGE_LABEL, "label.myprofile");
		model.addAttribute("pageMetadata", pageMetadataVO);
		return view;
	}
	

}
