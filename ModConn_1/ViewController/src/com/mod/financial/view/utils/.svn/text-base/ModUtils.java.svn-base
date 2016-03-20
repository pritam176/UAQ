package com.mod.financial.view.utils;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ModUtils {
    public ModUtils() {
        super();
    }
    
    final static long SECOND_MILLIS = 1000;
    final static long MINUTE_MILLIS = SECOND_MILLIS * 60;
    final static long HOUR_MILLIS = MINUTE_MILLIS * 60;
    final static long DAY_MILLIS = HOUR_MILLIS * 24;
    final static long YEAR_MILLIS = DAY_MILLIS * 365;

    public static java.util.Date convertJBODateToUtilDate(oracle.jbo.domain.Date jboDate) {
        java.util.Date utilDate = null;
        if (jboDate != null) {
            utilDate = new java.util.Date(jboDate.dateValue().getTime());
        }
        return utilDate;
    }

    public static String convertJBODateToUtilDateFormat(oracle.jbo.domain.Date jboDate, String format) {
        java.util.Date utilDate = null;
        SimpleDateFormat form = new SimpleDateFormat(format);
        if (jboDate != null) {
            utilDate = new java.util.Date(jboDate.dateValue().getTime());
        }
        return form.format(utilDate);
    }

    public static oracle.jbo.domain.Date convertUtilDateToJboDate(java.util.Date date) {
        long javaMilliseconds = date.getTime();
        java.sql.Date javaSqlDate = new java.sql.Date(javaMilliseconds);
        oracle.jbo.domain.Date oracleDate = new oracle.jbo.domain.Date(javaSqlDate);
        return oracleDate;
    }

    /**
     * Get the days difference
     */
    public static int daysDiff(Date earlierDate, Date laterDate) {
        if (earlierDate == null || laterDate == null)
            return 0;
        return (int)((laterDate.getTime() / DAY_MILLIS) - (earlierDate.getTime() / DAY_MILLIS));
    }

    /**
     * Roll the java.sql.Date forward or backward.
     * @param startDate - The start date
     * @period Calendar.YEAR etc
     * @param amount - Negative to rollbackwards.
     */
    public static java.util.Date rollDate(java.util.Date startDate, int period, int amount) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(startDate);
        gc.add(period, amount);
        return new java.util.Date(gc.getTime().getTime());
    }

    /**
     * Roll the days forward or backward.
     * @param startDate - The start date
     * @param months - Negative to rollbackwards.
     */
    public static java.util.Date rollMonths(java.util.Date startDate, int months) {
        return rollDate(startDate, Calendar.MONTH, months);
    }
    
}
