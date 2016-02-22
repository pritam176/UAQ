package com.uaq.service;

import static com.uaq.common.ApplicationConstants.*;
import static com.uaq.common.WebServiceConstant.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import com.oracle.xmlns.adf.svc.errors.ServiceException;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.StringUtil;
import com.uaq.vo.AccountDetailTokenInput;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.LoginOutputVO;
import com.uaq.vo.MyRequestOutputVO;

/**
 * This Class will use for UTILITy function Like getUser Detail from Token
 * Validate Taken etc
 * 
 * 
 */
@Service("portalUtil")
public class PortalUtil {

	public static transient UAQLogger logger = new UAQLogger(PortalUtil.class);

	private final SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);

	@Autowired
	@Qualifier("lplookup")
	LpLookupService lplookupservice;

	@Autowired
	private LoginService loginService;

	@Autowired
	private LookupServiceEN_AR lookupServiceEN_AR;

	@Autowired
	private GenericManageableCaptchaService captchaService;

	@Autowired
	private MyRequestService myRequestService;

	/**
	 * This will dycrypt the string from Request parameter
	 * 
	 * @param String
	 * @return String
	 */
	public String decryptParameter(String token) {
		return token;

	}

	/**
	 * This will check for mobile request or Desktop as per the request
	 * User-agent
	 * 
	 * @param HttpServletRequest
	 * @return boolean
	 */
	public boolean isMobile(HttpServletRequest request, HttpServletResponse responce) {

		boolean mobile = false;
		String nativeapp = "false";

		Cookie[] requestCookies = request.getCookies();

		Map<String, String> cookies = new HashMap<String, String>();

		if (requestCookies != null) {
			for (Cookie c : requestCookies) {
				cookies.put(c.getName(), c.getValue());
				logger.info("cookies-" + c.getName() + "value-" + c.getValue());
			}
		}

		if (request.getHeader("User-Agent").indexOf("Mobile") != -1) {
			nativeapp = (String) ((request.getParameter("isNative")) != null ? request.getParameter("isNative") : cookies.get("isMobile"));
		}

		if (!StringUtil.isEmpty(nativeapp)) {
			if (nativeapp.equalsIgnoreCase("true")) {
				// request.getSession().setAttribute("isNative", "true");
				Cookie isMobile = new Cookie("isMobile", "true");
				responce.addCookie(isMobile);
				request.setAttribute("isNative", "1");
				mobile = true;
			}

		}
		return mobile;
	}

	/**
	 * Set the Sector Data Based on the Language code for ENglish & Arabic
	 * 
	 * @param ModelMap
	 * @param String
	 */
	public void lookUpDataDropDownPlanningandSurvey(ModelMap model, String languageCode) {
		Map<String, Map<String, String>> sectorList;
		Map<String, Map<String, String>> areaList;
		Map<String, Map<String, String>> landType;
		try {
			sectorList = lplookupservice.sectorLookupAR_EN();
			areaList = lplookupservice.areaLookupAR_EN();
			landType = lplookupservice.landUsageLookupAR_EN();

			Map<String, String> sectorList_EN = sectorList.get(LANG_ENGLISH);
			Map<String, String> areaList_EN = areaList.get(LANG_ENGLISH);
			Map<String, String> landType_EN = landType.get(LANG_ENGLISH);

			Map<String, String> sectorList_AR = sectorList.get(LANG_ARABIC);
			Map<String, String> areaList_AR = areaList.get(LANG_ARABIC);
			Map<String, String> landType_AR = landType.get(LANG_ARABIC);
			if (languageCode.equals(LANG_ARABIC)) {
				model.addAttribute(SECTOR_LIST, sectorList_AR);
				model.addAttribute(AREA_LIST, areaList_AR);
				model.addAttribute(LAND_TYPE_LIST, landType_AR);
			} else {
				model.addAttribute(SECTOR_LIST, sectorList_EN);
				model.addAttribute(AREA_LIST, areaList_EN);
				model.addAttribute(LAND_TYPE_LIST, landType_EN);
			}
		} catch (ServiceException e) {
			logger.error("Error getting Value from Look Up EService " + e.getMessage());
		}
	}

	/**
	 * This will validate the Token Against the User Login On Desktop Request.
	 * Note:-token from the session .
	 * 
	 * @param LoginOutputVO
	 * @return boolean
	 */
	public boolean validateToken(LoginOutputVO logininfo) {
		AccountDetailTokenInput accountDetailTokenInput = new AccountDetailTokenInput();
		accountDetailTokenInput.setAcountId(logininfo.getAcountId());
		accountDetailTokenInput.setCreatedDate(logininfo.getCreatedDate());
		accountDetailTokenInput.setLastUpdatedDate(logininfo.getLastUpdatedDate());
		accountDetailTokenInput.setStatus(logininfo.getStatus());
		accountDetailTokenInput.setTokenCode(logininfo.getTokenCode());
		accountDetailTokenInput.setTypeOfUser(logininfo.getTypeOfUser());
		accountDetailTokenInput.setUsername(logininfo.getUsername());
		return loginService.validateToken(accountDetailTokenInput);
	}

	/**
	 * This will get The Login User Data From Request.For Mobile User
	 * 
	 * 
	 * @param HttpServletRequest
	 * @return LoginOutputVO
	 */

	public LoginOutputVO getLoginDetailFromMobileRequest(HttpServletRequest request) {
		LoginOutputVO logininfo = new LoginOutputVO();
		logininfo.setAcountId(decryptParameter(request.getParameter(REQUEST_PARAM_ACCOUNT_ID)));
		logininfo.setTypeOfUser(decryptParameter(request.getParameter(REQUEST_PARAM_TYPE_OF_USER)));
		logininfo.setStatus(decryptParameter(request.getParameter(REQUEST_PARAM_STATUS)));
		logininfo.setUsername(decryptParameter(request.getParameter(REQUEST_PARAM_USERNAME)));
		logininfo.setTokenCode(decryptParameter(request.getParameter(REQUEST_PARAM_TOKEN)));
		logininfo.setCreatedDate(Calendar.getInstance());
		logininfo.setLastUpdatedDate(Calendar.getInstance());

		return logininfo;
	}

	/**
	 * This will get Account detail from LoginOutputVO.For Mobile User
	 * 
	 * 
	 * @param LoginOutputVO
	 * @return AccountDetailTokenOutputVO
	 */

	public AccountDetailTokenOutputVO getAccountDetailForMobile(LoginOutputVO logininfo) {
		AccountDetailTokenInput accountDetailTokenInput = new AccountDetailTokenInput();
		accountDetailTokenInput.setAcountId(logininfo.getAcountId());
		accountDetailTokenInput.setCreatedDate(logininfo.getCreatedDate());
		accountDetailTokenInput.setLastUpdatedDate(logininfo.getLastUpdatedDate());
		accountDetailTokenInput.setStatus(logininfo.getStatus());
		accountDetailTokenInput.setTokenCode(logininfo.getTokenCode());
		accountDetailTokenInput.setTypeOfUser(logininfo.getTypeOfUser());
		accountDetailTokenInput.setUsername(logininfo.getUsername());
		return loginService.getDetailFromToken(accountDetailTokenInput);

	}

	public String gregorianCaltoDate(XMLGregorianCalendar xmlGregorianCalendar) {
		// logger.enter("gregorianCal to date conversion | ");

		Calendar cal = xmlGregorianCalendar.toGregorianCalendar();
		formatter.setTimeZone(cal.getTimeZone());
		String date = formatter.format(cal.getTime());
		return date;

	}

	public Calendar gregorianCalender(XMLGregorianCalendar xmlGregorianCalendar) {
		// logger.enter("gregorianCal to date conversion | ");

		Calendar cal = xmlGregorianCalendar.toGregorianCalendar();
		return cal;

	}

	/** method for render droup down FOR EGD Supplier Category **/
	public void lookUpDataDropDownforEGDSuppCategoryEN_AR(ModelMap model, String languageCode) {

		Map<String, Map<String, String>> eGDSuppCategory;
		try {
			eGDSuppCategory = lplookupservice.findEgdSuppCategoryEN_AR();
			Map<String, String> eGDSuppCategory_EN = eGDSuppCategory.get(LANG_ENGLISH);
			Map<String, String> eGDSuppCategoryt_AR = eGDSuppCategory.get(LANG_ARABIC);
			if (languageCode.equals(LANG_ARABIC)) {
				model.addAttribute("eGDSuppCategory", eGDSuppCategoryt_AR);
				logger.info("eGDSuppCategory  is  ::" + eGDSuppCategoryt_AR);
			} else {
				model.addAttribute("eGDSuppCategory", eGDSuppCategory_EN);
				logger.info("eGDSuppCategory  is  ::" + eGDSuppCategory_EN);
			}
		} catch (ServiceException e) {
			logger.error("Error getting Value from Look Up EService " + e.getMessage());
		}
	}

	/** method for render droup down FOR EGD Supplier Registration Type **/
	public void lookUpDataDropDownforEgdSuppRegTypesEN_AR(ModelMap model, String languageCode) {
		Map<String, Map<String, String>> egdSuppRegTypes;
		try {
			egdSuppRegTypes = lplookupservice.findEgdSuppRegTypesEN_AR();
			Map<String, String> EgdSuppRegTypes_EN = egdSuppRegTypes.get(LANG_ENGLISH);
			Map<String, String> EgdSuppRegTypes_AR = egdSuppRegTypes.get(LANG_ARABIC);
			if (!languageCode.equals(LANG_ARABIC)) {
				model.addAttribute("egdSuppRegTypes", EgdSuppRegTypes_EN);
				logger.info("egdSuppRegTypes " + EgdSuppRegTypes_EN);
			} else {
				model.addAttribute("egdSuppRegTypes", EgdSuppRegTypes_AR);
				logger.info("egdSuppRegTypes " + EgdSuppRegTypes_AR);
			}
		} catch (ServiceException e) {
			logger.error("Error getting Value from Look Up EService " + e.getMessage());
		}
	}

	public XMLGregorianCalendar toXMLGregorianCalendar(Calendar c) throws DatatypeConfigurationException {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(c.getTimeInMillis());
		XMLGregorianCalendar xc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		return xc;
	}

	public boolean validateCaptcha(String sessionId, String captchaText, Errors errors) {

		boolean isResponseCorrect = false;

		try {
			isResponseCorrect = captchaService.validateResponseForID(sessionId, captchaText);
			if (!isResponseCorrect) {
				errors.rejectValue("captchaText", "captchaText.incorrect", "Invalid Captcha Text");
			}
		} catch (CaptchaServiceException e) {
			logger.error("Invalid Session Id");
		}

		return isResponseCorrect;
	}

	public void emiratesDropDown(ModelMap model, String languageCode) throws ServiceException, UAQException {

		String language = (languageCode == null) ? LANG_ARABIC : languageCode;

		Map<String, Map<String, String>> emiratesList;

		emiratesList = lookupServiceEN_AR.getEmiratesListAR_EN();

		Map<String, String> emirateList_EN = emiratesList.get(LANG_ENGLISH);

		Map<String, String> emirateList_AR = emiratesList.get(LANG_ARABIC);

		if (language.equals(LANG_ARABIC)) {
			model.addAttribute(EMIRATE_LIST, emirateList_AR);
		} else {
			model.addAttribute(EMIRATE_LIST, emirateList_EN);
		}

		// model.addAttribute("familybook_emirates", emirateList_EN);

	}

	public void nationalDropDown(ModelMap model, String languageCode) throws ServiceException, UAQException {

		String language = (languageCode == null) ? "en" : languageCode;
		// ValueComparator bvc = new ValueComparator(map);
		Map<String, Map<String, String>> nationalList;

		nationalList = lookupServiceEN_AR.getNationalityListAR_EN();

		Map<String, String> nationalList_EN = sortByComparator(nationalList.get(LANG_ENGLISH));
		Map<String, String> nationalList_AR = nationalList.get(LANG_ARABIC);
		if (language.equals(LANG_ARABIC)) {
			model.addAttribute(COUNTRY_LIST, nationalList_AR);
		} else {
			model.addAttribute(COUNTRY_LIST, nationalList_EN);
		}

	}

	public void gccDropDown(ModelMap model, String languageCode) throws ServiceException, UAQException {
		String language = (languageCode == null) ? "en" : languageCode;
		// ValueComparator bvc = new ValueComparator(map);
		Map<String, Map<String, String>> nationalList;
		nationalList = lookupServiceEN_AR.getGCCListAR_EN();
		Map<String, String> nationalList_EN = sortByComparator(nationalList.get(LANG_ENGLISH));
		Map<String, String> nationalList_AR = nationalList.get(LANG_ARABIC);
		if (language.equals(LANG_ARABIC)) {
			model.addAttribute(GCC_COUNTRY_LIST, nationalList_AR);
		} else {
			model.addAttribute(GCC_COUNTRY_LIST, nationalList_EN);
		}
	}

	public void tradeLicenseDropDown(ModelMap model, String languageCode) throws ServiceException, UAQException {

		String language = (languageCode == null) ? LANG_ENGLISH : languageCode;

		Map<String, Map<String, String>> tradeLicList;

		tradeLicList = lookupServiceEN_AR.getTradeLicenceAR_EN();

		Map<String, String> tradeLicList_EN = tradeLicList.get(LANG_ENGLISH);

		Map<String, String> tradeLicList_AR = tradeLicList.get(LANG_ARABIC);

		if (language.equals(LANG_ARABIC)) {
			model.addAttribute(TRADELICENCE_LIST, tradeLicList_AR);
		} else {
			model.addAttribute(TRADELICENCE_LIST, tradeLicList_EN);
		}

	}

	/*
	 * For Shorting The Contry List
	 */
	private static Map<String, String> sortByComparator(Map<String, String> unsortMap) {

		// Convert Map to List
		List<Map.Entry<String, String>> list = new LinkedList<Map.Entry<String, String>>(unsortMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
			@Override
			public int compare(Entry<String, String> o1, Entry<String, String> o2) {
				// TODO Auto-generated method stub
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// Convert sorted map back to a Map
		Map<String, String> sortedMap = new LinkedHashMap<String, String>();
		for (Iterator<Map.Entry<String, String>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	public boolean validateRequestForSubmission(String requestedUsername, String requestNo, String action) {
		boolean isVaid = false;
		logger.debug("requestNo-"+requestNo+"requestedUsername-"+requestedUsername+"action-"+action);
		if(StringUtil.isEmpty(requestedUsername) && StringUtil.isEmpty(requestNo) && StringUtil.isEmpty(action)  )
			return isVaid;
		
		MyRequestOutputVO requestOutpur = myRequestService.getUserNameFromRequestNo(requestNo);
		logger.debug("status from Applicant Request -"+requestOutpur.getStatusId().toString()+"| userName-"+requestOutpur.getUserName());
		if (!SERVICE_FAILED.equals(requestOutpur.getServiceExecution())) {
			if (requestedUsername.equals(requestOutpur.getUserName())) {
				logger.debug("request no from this user");
				if(action.equals(requestOutpur.getStatusId().toString())){
					logger.debug("request has valid status for operation");
					isVaid =true;
				}
			}
		}

		return isVaid;
	}
}
