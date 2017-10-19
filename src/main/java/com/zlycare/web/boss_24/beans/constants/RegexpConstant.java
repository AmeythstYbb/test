package com.zlycare.web.boss_24.beans.constants;

/**
 * 正则表达式
 * @author lufanglong
 * @date 2016-08-02 12:08
 */
public class RegexpConstant {

    /**
     * 手机号
     */
    public static final String PHONE = "^1\\d{10}$";
    /**
     * 手机号错误提示
     */
    public static final String PHONE_MSG = "手机号码校验错误";
    /**
     * 密码
     */
    public static final String PASSWORD = "^[A-Z\\d]{32}$";
    /**
     * 密码错误提示
     */
    public static final String PASSWORD_MSG = "密码校验错误";
    /**
     * 验证码
     */
    public static final String CAPTCHA = "^[A-Z\\d]{6}$";
    /**
     * 验证码错误提示
     */
    public static final String CAPTCHA_MSG = "验证码校验错误";
    /**
     * 短信验证码
     */
    public static final String SMS_CAPTCHA = "^^\\d{4}$";
    /**
     * 短信验证码错误提示
     */
    public static final String SMS_CAPTCHA_MSG = "验证码校验错误";
}
