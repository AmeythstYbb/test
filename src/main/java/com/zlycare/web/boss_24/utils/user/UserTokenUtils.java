package com.zlycare.web.boss_24.utils.user;


import com.zlycare.web.boss_24.constants.user.UserConstants;
import com.zlycare.web.boss_24.utils.common.encrypt.AESUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lufanglong
 * @date 2016-01-05 14:11
 * @Description 用户token工具
 */
public class UserTokenUtils {

    protected final static Logger logger = LoggerFactory.getLogger(UserTokenUtils.class);
    
    /**
     * 生成token的密钥
     */
    private final static String AES_USER_TICKET_SECRET = "N8PmUqf7t7dE3G6b";
    /**
     * 生成token的干扰向量
     */
    private final static String AES_USER_TICKET_IV = "hXkqxZB76RL8M67k";

    /**
     * 生成加密token
     * @param tokenId
     * @param userId
     * @param salt
     * @return String
     */
    public static String encryptToken(long tokenId, int userId, String salt) {
        String source = tokenId + UserConstants.TOKEN_SPLIT + userId + UserConstants.TOKEN_SPLIT + salt;
        try {
            return AESUtils.encrypt(source, AES_USER_TICKET_SECRET, AES_USER_TICKET_IV);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 生成加密refreshToken
     * @param tokenId
     * @param refreshToken
     * @return refreshToken
     */
    public static String encryptRefreshToken(long tokenId, String refreshToken) {
        String source = tokenId + UserConstants.TOKEN_SPLIT + refreshToken;
        try {
            return AESUtils.encrypt(source, AES_USER_TICKET_SECRET, AES_USER_TICKET_IV);
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
    public static String decryptToken(String source) {
        try {
            return AESUtils.decrypt(source, AES_USER_TICKET_SECRET, AES_USER_TICKET_IV);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    /**
     * 生成解密RefreshToken
     * @param source
     * @return String
     */
    public static String decryptRefreshToken(String source) {
        try {
            return AESUtils.decrypt(source, AES_USER_TICKET_SECRET, AES_USER_TICKET_IV);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
