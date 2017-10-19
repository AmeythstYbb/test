package com.zlycare.web.boss_24.utils.common.utils;

import java.util.UUID;

/**
 * Description: UUIDUtils
 * Author: lufanglong
 * Update: 2014-08-22 13:58
 */
public class UUIDUtils {

    /**
     * 获取UUID（去除-）
     * @return String
     */
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
