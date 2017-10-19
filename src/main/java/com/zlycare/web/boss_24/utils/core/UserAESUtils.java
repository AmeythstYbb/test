package com.zlycare.web.boss_24.utils.core;

import com.zlycare.web.boss_24.utils.common.encrypt.AESUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户AES加密(适用于c\k\cp)
 * @author lufanglong
 * @date 2016-07-22 16:21
 */
public class UserAESUtils {
    protected final static Logger logger = LoggerFactory.getLogger(UserAESUtils.class);

    /**
     * 密钥
     */
    private final static String AES_USER_ACCOUNT_SECRET = "r87EUq3t5dIID2Kv";
    /**
     * 干扰向量
     */
    private final static String AES_USER_ACCOUNT_IV = "iW4qOsr8d5DKS6wD";

    /**
     * 生成加密token
     * @param source
     * @return String
     */
    public static String encrypt(String source) {
        try {
            return AESUtils.encrypt(source, AES_USER_ACCOUNT_SECRET, AES_USER_ACCOUNT_IV);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 生成解密token
     * @param source
     * @return String
     */
    public static String decrypt(String source) {
        try {
            return AESUtils.decrypt(source, AES_USER_ACCOUNT_SECRET, AES_USER_ACCOUNT_IV);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
