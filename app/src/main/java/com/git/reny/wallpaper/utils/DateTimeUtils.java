package com.git.reny.wallpaper.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by reny on 2016/7/27.
 */

public class DateTimeUtils {

    private static Locale locale = Locale.CHINA;

    public static String appYMD = "yyyy-MM-dd";
    public static String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static String YMDHMS_FILE = "yyyyMMddHHmmss";
    public static String HM = "HH:mm";


    //return "yyyy-MM-dd"
    public static String getDefaultCurDateStr() {
        return getCurDateStr(getFormatYMDHMS(appYMD));
    }
    public static String getDefaultCurDateStr(Date date) {
        return getFormatYMDHMS(appYMD).format(date);
    }

    public static int getCurYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getCurMonth(){
        return Calendar.getInstance().get(Calendar.MONTH) + 1;// 获取当前月份
    }


    public static String getTimeFileName(){
        return getCurDateStr(getFormatYMDHMS(YMDHMS_FILE));
    }

    public static String getCurHM(){
        return getCurDateStr(getFormatYMDHMS(HM));
    }

    //"yyyy-MM-dd HH:mm:ss"
    public static String getCurDateStr(SimpleDateFormat formater) {
        return formater.format(new Date(System.currentTimeMillis()));
    }

    public static SimpleDateFormat getFormatYMDHMS(String YMDHMS){
        return new SimpleDateFormat(YMDHMS, locale);
    }


    public static Calendar date2Calendar(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 描述：String类型的日期时间转化为Date类型.
     *
     * @param strDate String形式的日期时间
     * @param format  格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return Date Date类型日期时间
     */
    public static Date getDateByFormat(String strDate, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, locale);
        Date date = null;
        try {
            date = mSimpleDateFormat.parse(strDate.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /***
     * 将日期客串 转为 Calendar
     * @param dateStr 必须是格式 appYMD = "yyyy-MM-dd";
     * @return Calendar
     */
    public static Calendar getCalendarByDateStr(String dateStr){
        return date2Calendar(getDateByFormat(dateStr, appYMD));
    }

    public static String timeStamp2Date(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd", locale);
        Long i = Long.parseLong(time.trim());
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }


    /***
     * 某一年距今多少天
     * @param year 如2007
     * @return 2007-01-01 00:00:00 到今天的天数
     */
    public static int daysFromDate(int year){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
        try{
            Date d1 = df.parse(year + "-01-01 00:00:00");
            Date d2 = new Date(System.currentTimeMillis());
            long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
            return (int)(diff / (1000 * 60 * 60 * 24));
        }catch (Exception e){
            return (getCurYear() - year) * 365;
        }
    }

    /***
     * 某一年距今多少月
     * @param year 如2007
     * @return 2007-01-01 00:00:00 到今天的月数
     */
    public static int monthFromDate(int year){
        return (getCurYear() - year) * 12 + getCurMonth();
    }

    /***
     * 通过类似两个日期：2015-01-05、2018-01-01  获取期间的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int daysByDates(String date1, String date2){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", locale);
        try{
            Date d1 = df.parse(date1.trim());
            Date d2 = df.parse(date2.trim());
            long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
            return (int)(diff / (1000 * 60 * 60 * 24));
        }catch (Exception e){
            return 0;
        }
    }

    /***
     * 通过类似两个日期：2015-01-05、2018-01-01  获取期间的月数
     * @param date1
     * @param date2
     * @return
     */
    public static int monthByDates(String date1, String date2){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", locale);
        try{
            Date d1 = df.parse(date1.trim());
            Date d2 = df.parse(date2.trim());
            Calendar calendar1 = new GregorianCalendar();
            calendar1.setTime(d1);
            Calendar calendar2 = new GregorianCalendar();
            calendar2.setTime(d2);
            return (calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR)) * 12 + calendar2.get(Calendar.MONTH) + 1;
        }catch (Exception e){
            return 0;
        }
    }

    /***
     * 通过类似两个日期：2015-01-05、2018-01-01  获取期间的年数
     * @param date1
     * @param date2
     * @return
     */
    public static int yearsByDates(String date1, String date2){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", locale);
        try{
            Date d1 = df.parse(date1.trim());
            Date d2 = df.parse(date2.trim());
            Calendar calendar1 = new GregorianCalendar();
            calendar1.setTime(d1);
            Calendar calendar2 = new GregorianCalendar();
            calendar2.setTime(d2);
            return calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR);
        }catch (Exception e){
            return 0;
        }
    }

}
