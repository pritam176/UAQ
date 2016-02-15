package com.uaq.controller.mapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.uaq.common.ApplicationConstants;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.UserDeatilVO;

public class PortalDataMapper {

	

	private final static DateFormat df = new SimpleDateFormat(ApplicationConstants.DATE_FORMAT);
	
	private final static DateFormat df1 = new SimpleDateFormat(ApplicationConstants.DD_MM_YYYY);
	
	private final static DateFormat df2 = new SimpleDateFormat(ApplicationConstants.MM_DD_YYYY);

	/**
	 * This will map data from .AccountDetailTokenOutputVO to UserDeatilVO
	 * 
	 * 
	 * @param AccountDetailTokenOutputVO
	 * @return UserDeatilVO
	 */
	public static UserDeatilVO getUserDetailFrom(AccountDetailTokenOutputVO accountDetailfromToken) {
		UserDeatilVO user = new UserDeatilVO();
		user.setAccountid(accountDetailfromToken.getAccountId());
		user.setTypeOfUser(accountDetailfromToken.getTypeOfUser());
		user.setUsername(accountDetailfromToken.getUserName());
		user.setNationality(accountDetailfromToken.getNationalityId());
		user.setAddress1(accountDetailfromToken.getAddressline1());
		user.setAddress2(accountDetailfromToken.getAddressline2());
		user.setAddress3("");
		user.setEmiratesCode(accountDetailfromToken.getEmiratesCode());
		user.setDOB(new Date());
		user.setEmailID(accountDetailfromToken.getEmailAddress());
		user.setEmirate("");
		user.setEmiratesId(accountDetailfromToken.getEmiratesId());
		user.setFamilyBookNo("0".equals(accountDetailfromToken.getFamilyBookNum()) ? null : accountDetailfromToken.getFamilyBookNum());
		user.setFirstName(accountDetailfromToken.getFirstName());
		user.setLastName("");
		user.setMiddleName("");
		user.setMobileNo(accountDetailfromToken.getMobileNo());
		user.setPOBOX(accountDetailfromToken.getPostbox());
		
		user.setTradeLienceNo(accountDetailfromToken.getTradeLienceNo() == null?"":accountDetailfromToken.getTradeLienceNo());
		user.setLanguageId(accountDetailfromToken.getLanguageId());
		user.setHomePhone("");
		// TODO remove hardcoded date
		user.setEdiaExpirtyDate((accountDetailfromToken.getEidaExpiryDate()) == null ? "2015-10-05" : accountDetailfromToken.getEidaExpiryDate());
		user.setLoginUserName(accountDetailfromToken.getLoginusername());
		user.setApplicantTypeId(accountDetailfromToken.getApplicanttypeid());
		return user;
	}

	public static String getLanguageId(String languageCode) {
		String langid = "2";
		if (languageCode.equals("en")) {
			langid = "1";
		}
		return langid;
	}

	public static Calendar toCalendar(String dateString) throws DatatypeConfigurationException, ParseException {
		Date date = null;
		date = df.parse(dateString);
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(date);
		XMLGregorianCalendar xmlCalendar = null;
		xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(xmlCalendar.toGregorianCalendar().getTimeInMillis());
		return c;
	}
	
	public static Calendar fromDDMMYYtoCalendar(String dateString) throws ParseException {

		// Date date = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(df1.parse(dateString));
		return cal;
	}
	public static Calendar fromMMDDYYtoCalendar(String dateString) throws ParseException {

		// Date date = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(df2.parse(dateString));
		return cal;
	}

}
