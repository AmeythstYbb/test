package com.zlycare.web.boss_24.utils.core;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间处理
 *
 * @author DaiJian
 * @create 2015/11/17 10:32
 */
public class DateUtil {
    private static final String MONTH_DAY = "MM/dd";
    private static final String MONTH_DAY_TIME = "MM-dd HH:mm";
    private static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
    private static final String YEAR_MONTH ="yyyy-MM";
    private static final String DATE_FORMART="yyyyMMdd";
    private DateUtil() {
    }

    /**
     * 返回月-日格式 e.g. 10-01
     *
     * @param date
     * @return
     */
    public static String toDateStringMD(Date date) {
        return date == null ? null : new SimpleDateFormat(MONTH_DAY).format(date);
    }

    /**
     * 返回月-日 时：分格式 e.g. 10-01 12:00
     *
     * @param date
     * @return
     */
    public static String toDateStringMDT(Date date) {
        return date == null ? null : new SimpleDateFormat(MONTH_DAY_TIME).format(date);
    }

    /**
     * 根据提供的格式字符串，格式化date
     *
     * @param date
     * @param format
     * @return String
     */
    public static String toDateString(Date date, String format) {
        return date == null ? null : new SimpleDateFormat(format).format(date);
    }

    /**
     * 将年-月-日 格式字符串转换为Date对象
     *
     * @param dateStr 日期字符串
     * @return Date对象
     * @throws ParseException
     */
    public static Date toDateYMD(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.isEmpty())
            return null;
        return new SimpleDateFormat(YEAR_MONTH).parse(dateStr);
    }

    /**
     * 将年-月 格式字符串转换为Date对象
     *
     * @param dateStr 日期字符串
     * @return Date对象
     * @throws ParseException
     */
    public static Date YMtoDateYMD(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.isEmpty())
            return null;
        return new SimpleDateFormat(YEAR_MONTH).parse(dateStr);
    }


    public static Date getNextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定afterMinute分钟后的时间
     * 2016-4-9 19:18:48 获取30分钟后的时间为 2016-4-9 19:48:48
     * @param afterMinute 分钟
     * @return
     */
    public static Date getAfterMinute(Integer afterMinute)
    {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.MINUTE, afterMinute);
        return currentDate.getTime();
    }
    
    /**
     * 获得当前时间 时分秒补6个0
     * @return
     */
    public static Long getCurrentDate()
    {
    	Date date = new Date();
    	SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMART);
    	return Long.parseLong(sf.format(date)+"000000");
    }
}
