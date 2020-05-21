package com.cc.api.common.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author teangtang
 */
public class DateUtil {
    public static final String DEFAULT_FORMAT = "YYYY-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIME_ZONE = "GMT+8";
    public static final String DATE_FORMAT = "YYYYMMddHHmmss";
    public static final String DATE_FORMAT_1 = "YYYYMMdd";
    public static final String DATE_FORMAT_2 = "YYYY-MM-dd";

    public DateUtil() {
    }

    public static Date getDateAfterSeconds(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(13, seconds);
        return calendar.getTime();
    }

    public static Date getDateAfterDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, days);
        return calendar.getTime();
    }

    public static Date getDateAfterMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, month);
        return calendar.getTime();
    }

    public static Date getDateAfterYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(1, year);
        return calendar.getTime();
    }

    public static String getDateTimeFormat() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
        return sdf.format(date);
    }

    public static String getDateTimeFormat(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getDateTimeFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date getDateTimeFormat(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        new Date();

        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (Exception var5) {
            var5.printStackTrace();
            date = null;
        }

        return date;
    }

    public static Long getCurrentTimeInMillis(int millisecond) {
        Long l = System.currentTimeMillis();
        return l + (long)millisecond;
    }

    public static Integer computeYears(String startDate, String endDate) {
        LocalDate startLocalDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endLocalDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return endLocalDate.getYear() - startLocalDate.getYear();
    }

    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(6);
        int day2 = cal2.get(6);
        int year1 = cal1.get(1);
        int year2 = cal2.get(1);
        if (year1 == year2) {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        } else {
            int timeDistance = 0;

            for(int i = year1; i < year2; ++i) {
                if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
                    timeDistance += 365;
                } else {
                    timeDistance += 366;
                }
            }

            return timeDistance + (day2 - day1);
        }
    }

    public static String plusByYears(String startDate, Integer years) {
        LocalDate startLocalDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate resultLocalDate = startLocalDate.plusYears((long)years);
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(resultLocalDate);
    }

    public static Date getRequiredDate(Date requiredDate, Integer days) {
        Date today = new Date();
        if (differentDays(today, requiredDate) > days) {
            requiredDate = getDateAfterDays(today, days);
        }

        return requiredDate;
    }

    public static void main(String[] args) {
        Date dateR = getDateTimeFormat("2019-11-18", "yyyy-MM-dd");
        Date requiredDate = getRequiredDate(dateR, 30);
        System.out.println(getDateTimeFormat(requiredDate, "yyyy-MM-dd"));
    }
}
