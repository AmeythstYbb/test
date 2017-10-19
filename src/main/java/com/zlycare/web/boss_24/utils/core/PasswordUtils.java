package com.zlycare.web.boss_24.utils.core;

import com.zlycare.web.boss_24.utils.common.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;

/**
 * 密码工具类
 *
 * @author lufanglong
 * @date 2016-08-02 18:22
 */
public class PasswordUtils {

    /**
     * md5摘要,规则算法详见wiki
     * @param password 明文密码
     * @return String
     */
    public static String md5(String password) {
        if (StringUtils.isEmpty(password)) {
            return null;
        }
        String salt = password.substring(9, 15);
        return MD5Util.md5(password + salt).toUpperCase();
    }
}
