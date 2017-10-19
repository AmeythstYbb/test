package com.zlycare.web.boss_24.utils.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

/**
 * Description:
 *
 * @author yz.wu
 */
public class DateUtil {
    public static final String FULL_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FULL_TIME_NOT_SEPARATE_FORMAT = "yyyyMMddHHmmss";
    public static final String FULL_MILLSCOND_FORMAT = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String FULL_MILLSCOND_NOT_SEPARATE_FORMAT = "yyyyMMddHHmmssSSS";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_NOT_SEPARATE_FORMAT = "yyyyMMdd";

    /**
     * 返回指定日期的Date
     * @param day
     * @return Date
     */
    public static final Date addDay2Date(int day) {
        DateTime dateTime = new DateTime();
        return dateTime.plusDays(day).toDate();
    }

    /**
     * 返回指定日期
     * @param day
     * @param date
     * @return
     */
    public static final Date addDay2Date(int day, Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusDays(day).toDate();
    }

    /**
     * 返回当天指定分钟的时间
     * @param minutes
     * @return
     */
	public static final Date addMinute2Date(int minutes) {
		DateTime dateTime = new DateTime();
		return dateTime.plusMinutes(minutes).toDate();
	}

    /**
     *
     * @param sceonds
     * @param date
     * @return
     */
	public static final Date addSecond2Date(int sceonds, Date date) {
		DateTime dateTime = new DateTime(date);
		return dateTime.plusSeconds(sceonds).toDate();
	}

    /**
     *
     * @param Millis
     * @param date
     * @return
     */
    public static final Date addMillis2Date(int Millis, Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.plusMillis(Millis).toDate();
    }

    /**
     *
     * @param minutes
     * @return
     */
	public static final String addMinute2String(int minutes) {
		DateTime dateTime = new DateTime();
		return dateTime.plusMinutes(minutes).toString(FULL_TIME_FORMAT);
	}

    /**
     *
     * @param seconds
     * @return
     */
	public static final String addSecond2String(int seconds) {
		DateTime dateTime = new DateTime();
		return dateTime.plusSeconds(seconds).toString(FULL_TIME_FORMAT);
	}

    /**
     *
     * @param date
     * @return
     */
    public static String getTimeString(Date date) {
        return getDateString(date,FULL_TIME_FORMAT);
    }

    /**
     *
     * @param date
     * @return
     */
    public static String getFullTimeString(Date date) {
        return getDateString(date, FULL_TIME_NOT_SEPARATE_FORMAT);
    }

    /**
     *
     * @param date
     * @return
     */
    public static String getDateString(Date date) {
        return getDateString(date, DATE_NOT_SEPARATE_FORMAT);
    }

    /**
     *
     * @param date
     * @param format
     * @return
     */
    public static String getDateString(Date date, String format) {
        return new DateTime(date).toString(format);
    }

    /**
     *
     * @param timeString
     * @return
     */
	public final static Date string2Date(String timeString) {
		return string2Date(timeString,FULL_TIME_NOT_SEPARATE_FORMAT);
	}

    /**
     *
     * @param timeString
     * @param pattern
     * @return
     */
	public final static Date string2Date(String timeString, String pattern) {
		DateTime dateTime = DateTimeFormat.forPattern(pattern)
				.parseDateTime(timeString);
		return dateTime.toDate();
	}

    /**
     *
     * @param timeString
     * @return
     */
	public final static Date timeString2Date(String timeString) {
		return string2Date(timeString,FULL_TIME_FORMAT);
	}

    /**
     *
     * @param after
     * @param before
     * @return
     */
	public static boolean isAfter(Date after, Date before) {
		DateTime d1 = new DateTime(before);
		DateTime d2 = new DateTime(after);
		return d2.isAfter(d1);
	}

    /**
     * 计算两个时间相差的分钟
     * @param one
     * @param two
     * @return
     */
	public static int secondsBetween(Date one, Date two) {
		return secondsBetween(new DateTime(one),new DateTime(two));
	}

    /**
     * 计算两个时间相差的分钟
     * 日期格式：yyyy-MM-dd HH:mm:ss
     * @param one
     * @param two
     * @return
     */
    public static int secondsBetween(String one, String two){
        return secondsBetween(one,two,FULL_TIME_FORMAT);
    }

    /**
     * 计算两个时间相差的分钟，自定义格式
     * @param one
     * @param two
     * @param pattern 自定义格式
     * @return
     */
    public static int secondsBetween(String one, String two, String pattern){
        if(StringUtils.isNotEmpty(one) && StringUtils.isNotEmpty(two)){
            DateTime d1 = DateTimeFormat.forPattern(pattern).parseDateTime(one);
            DateTime d2 = DateTimeFormat.forPattern(pattern).parseDateTime(two);
            return secondsBetween(d1,d2);
        }
        return 0;
    }

    /**
     *
     * @param one
     * @param two
     * @return
     */
    private static int secondsBetween(DateTime one,DateTime two){
        return Seconds.secondsBetween(one,two).getSeconds();
    }
    /**
     * 计算两个时间相差的小时
     * @param one
     * @param two
     * @return
     */
	public static int hoursBetween(Date one, Date two) {
		return hoursBetween(new DateTime(one),new DateTime(two));
	}

    /**
     *计算两个时间相差的小时,日期格式：yyyy-MM-dd HH:mm:ss
     * @param one
     * @param two
     * @return
     */
    public static int hoursBetween(String one, String two){
        return hoursBetween(one,two,FULL_TIME_FORMAT);
    }

    /**
     *计算两个时间相差的小时，自定义格式
     * @param one
     * @param two
     * @param pattern 自定义格式
     * @return
     */
    public static int hoursBetween(String one, String two, String pattern){
        if(StringUtils.isNotEmpty(one) && StringUtils.isNotEmpty(two)){
            DateTime d1 = DateTimeFormat.forPattern(pattern).parseDateTime(one);
            DateTime d2 = DateTimeFormat.forPattern(pattern).parseDateTime(two);
            return hoursBetween(d1,d2);
        }
        return 0;
    }

    /**
     * 计算两个时间相差的小时
     * @param one
     * @param two
     * @return
     */
    private static int hoursBetween(DateTime one,DateTime two){
        return Hours.hoursBetween(one, two).getHours();
    }

    /**
     * 当明时间的最后一秒
     * @return
     */
    public static Date getCurrentDayLastDate()
    {

        DateTime dateTime = new DateTime().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        return dateTime.toDate();
    }
	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(getTimeString(now));
		System.out.println(getTimeString(addSecond2Date(-30, now)));
		System.out.println(secondsBetween(now, addSecond2Date(30, now)));
		System.out.println(secondsBetween(now, addSecond2Date(-30, now)));
        System.out.println();
        System.out.println(getCurrentDayLastDate().toLocaleString());
    }
}
