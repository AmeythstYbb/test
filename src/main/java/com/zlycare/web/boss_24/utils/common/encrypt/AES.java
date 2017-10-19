package com.zlycare.web.boss_24.utils.common.encrypt;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;

/**
 * AES算法加密，解密
 *
 * @author tonydeng
 */
public final class AES {
    private static final Logger log = LoggerFactory.getLogger(AES.class);
    private static BufferedBlockCipher cipher;

    static {
        cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher((new AESFastEngine())));
    }


    /**
     * 加密
     *
     * @param content
     * @param keyword
     * @return
     */
    synchronized public static String encrypt(@Nullable String content, @Nullable String keyword) {
        return encrypt(content.getBytes(), keyword);
    }

    /**
     * 加密
     *
     * @param content
     * @param keyword
     * @return
     */
    synchronized public static String encrypt(@Nullable byte[] content, @Nullable String keyword) {
        if (content != null && content.length > 0 && StringUtils.isNotEmpty(keyword)) {
            try {
                cipher.init(true, new KeyParameter(keyword.getBytes()));
                byte[] enc = new byte[cipher.getOutputSize(content.length)];
                int size1 = cipher.processBytes(content, 0, content.length, enc, 0);
                int size2 = cipher.doFinal(enc, size1);
                byte[] encryptedContent = new byte[size1 + size2];
                System.arraycopy(enc, 0, encryptedContent, 0, encryptedContent.length);
                return new String(Base64.encode(encryptedContent));
            } catch (InvalidCipherTextException e) {
                if (log.isErrorEnabled())
                    log.error("AES encrypt content:'{}' password '{}' error {}", content, keyword, e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 解密
     *
     * @param message 密文
     * @param keyword
     * @return 明文
     * @throws Exception
     */
    synchronized public static String decrypt(@Nullable String message, @Nullable String keyword) {
        return decrypt(message.getBytes(), keyword);
    }

    /**
     * 解密
     *
     * @param message 密文
     * @param keyword
     * @return 明文
     * @throws Exception
     */
    synchronized public static String decrypt(@Nullable byte[] message, @Nullable String keyword) {
        return new String(decrypt(message, keyword.getBytes()));
    }

    /**
     * 解密
     *
     * @param message 密文
     * @param keyword
     * @return 明文
     * @throws Exception
     */
    synchronized public static byte[] decrypt(@Nullable byte[] message, @Nullable byte[] keyword) {

        try {
            byte[] encryptedContent = Base64.decode(message);
            cipher.init(false, new KeyParameter(keyword));
            byte[] dec = new byte[cipher.getOutputSize(encryptedContent.length)];
            int size1 = cipher.processBytes(encryptedContent, 0, encryptedContent.length, dec, 0);
            int size2 = cipher.doFinal(dec, size1);
            byte[] decryptedContent = new byte[size1 + size2];
            System.arraycopy(dec, 0, decryptedContent, 0, decryptedContent.length);
            return decryptedContent;
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
        }
        return null;
    }
}
