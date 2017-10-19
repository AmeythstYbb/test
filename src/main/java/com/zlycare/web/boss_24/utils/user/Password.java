package com.zlycare.web.boss_24.utils.user;

import com.zlycare.web.boss_24.constants.sys.Shiro;
import com.zlycare.web.boss_24.utils.other.Encodes;
import com.zlycare.web.boss_24.utils.security.Digests;

/**
 * Author : linguodong
 * Create : 2017/5/31
 * Update : 2017/5/31
 * Descriptions :
 */
public class Password {



    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword) {
        byte[] salt = Digests.generateSalt(Shiro.SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, Shiro.HASH_INTERATIONS);
        return Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        byte[] salt = Encodes.decodeHex(password.substring(0,16));
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, Shiro.HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword));
    }

}
