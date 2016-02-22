package com.uaq.ws.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtil {
	
	public static final String DATE_TIME_FORMAT = "dd-MMM-yyyy hh:mm a";
	private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
	private static final SimpleDateFormat sdfDate = new SimpleDateFormat(POSConstants.DD_MM_YY);
	private static final SimpleDateFormat sdfTime = new SimpleDateFormat(POSConstants.HH_MM);

	public static String getDateXML() { // current, xml dateTime equivalent
		String strDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		strDate = formatter.format(new Date());
		strDate = strDate.substring(0, strDate.indexOf('T') + 1).concat("00:00:00"); // required for prayer times webservice
		return strDate;
	}

	public static String getTimeFromXML(String dateTime) {
		String strDate = null;
		strDate = dateTime.substring(dateTime.indexOf('T') + 1, dateTime.indexOf('T') + 6);
		return strDate;
	}

	public static Date getSqlDateFromStringDate(String strDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date parsed = format.parse(strDate);
		return parsed;
	}
	
	public static String getUAQFormattedDate(Date date,String dateFormat,String locale){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat,new Locale(locale));
		String formatedDate = sdf.format(date);
		return formatedDate;
	}
	
	public static Date getDateFromString(String strDate) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = df.parse(strDate);        
        return parsed; 
	}
	public static String gregorianCaltoString(XMLGregorianCalendar xmlGregorianCalendar) {
		Calendar cal = xmlGregorianCalendar.toGregorianCalendar();
		formatter.setTimeZone(cal.getTimeZone());
		String date = formatter.format(cal.getTime());
		return date;

	}
		
	public static Date getDateFromDate(String strDate) throws ParseException{
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm:a");
        Date parsed = df.parse(strDate);        
        return parsed; 
	}
	
}
