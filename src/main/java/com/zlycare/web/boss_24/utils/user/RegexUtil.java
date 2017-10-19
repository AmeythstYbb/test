package com.zlycare.web.boss_24.utils.user;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 校验工具类
 *
 * @author DaiJian
 * @create 2016/1/22 15:11
 */
public class RegexUtil {

    /**
     * 校验密码MD5串格式
     * 串由大写字母和数字组成
     * 长度为32
     *
     * @param str 校验字符串
     * @return boolean
     */
    public static boolean validPassword(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        String regex = "^[A-Z\\d]{32}$";
        return str.matches(regex);
    }

    public static boolean isNotValidPhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return true;
        }
        if(Pattern.compile("^1\\d{10}$").matcher(phone).matches()){
            return false;
        }else{
            return true;
        }
    }

}
