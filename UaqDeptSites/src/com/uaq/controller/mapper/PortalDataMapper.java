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
import com.uaq.util.StringUtil;
import com.uaq.vo.AccountDetailTokenOutputVO;
import com.uaq.vo.UserDeatilVO;

public class PortalDataMapper {

	

	

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
		user.setTradeLicenceExpDate(accountDetailfromToken.getTradeLienceExpiryDate());
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

	

}
