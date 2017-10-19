package com.zlycare.web.boss_24.utils.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 * Description:
 * 支持SHA-1/MD5消息摘要的工具类.
 * 支持Hex与Base64两种编码方式.
 * 
 * @author yz.wu
 */
public class DigestUtils {
	private static Logger logger = LoggerFactory.getLogger(DigestUtils.class) ;

	private static final String SHA1 = "SHA-1";
	private static final String MD5 = "MD5";

	//-- String Hash function --//
	/**
	 * 对输入字符串进行sha1散列, 返回Hex编码的结果.
	 */
	public static String sha1ToHex(String input) {
		if (StringUtils.isNotEmpty(input)) {
			byte[] digestResult = digest(input, SHA1);
			return com.zlycare.web.boss_24.utils.common.utils.EncodeUtils.hexEncode(digestResult);
		} else {
			if (logger.isErrorEnabled()) {
				logger.error("sha1ToHex, input is null");
			}
			
			return null;
		}
	}

	/**
	 * 对输入字符串进行sha1散列, 返回Base64编码的结果.
	 */
	public static String sha1ToBase64(String input) {
		if (StringUtils.isNotEmpty(input)) {
			byte[] digestResult = digest(input, SHA1);
			return com.zlycare.web.boss_24.utils.common.utils.EncodeUtils.base64Encode(digestResult);
		} else {
			if (logger.isErrorEnabled()) {
				logger.error("sha1ToBase64, input is null");
			}
			
			return null;
		}
	}

	/**
	 * 对输入字符串进行sha1散列, 返回Base64编码的URL安全的结果.
	 */
	public static String sha1ToBase64UrlSafe(String input) {
		byte[] digestResult = digest(input, SHA1);
		return com.zlycare.web.boss_24.utils.common.utils.EncodeUtils.base64UrlSafeEncode(digestResult);
		
//		if (StringUtils.isNotEmpty(input)) {
//			byte[] digestResult = digest(input, SHA1);
//			return EncodeUtils.base64UrlSafeEncode(digestResult);
//		} else {
//			if (logger.isErrorEnabled()) {
//				logger.error("sha1ToBase64UrlSafe, input is null");
//			}
//			
//			return null;
//		}
	}

    /**
     * 对输入字符串进行md5散列, 返回Base64编码的URL安全的结果.
     */
    public static String md5ToHex(String input) {
        if (StringUtils.isNotEmpty(input)) {
            byte[] digestResult = digest(input, MD5);
            return com.zlycare.web.boss_24.utils.common.utils.EncodeUtils.hexEncode(digestResult);
        } else {
            if (logger.isErrorEnabled()) {
                logger.error("md5ToBase64UrlSafe, input is null");
            }

            return null;
        }
    }
	/**
	 * 对输入字符串进行md5散列, 返回Base64编码的URL安全的结果.
	 */
	public static String md5ToBase64UrlSafe(String input) {
		if (StringUtils.isNotEmpty(input)) {
			byte[] digestResult = digest(input, MD5);
			return com.zlycare.web.boss_24.utils.common.utils.EncodeUtils.base64UrlSafeEncode(digestResult);
		} else {
			if (logger.isErrorEnabled()) {
				logger.error("md5ToBase64UrlSafe, input is null");
			}
			
			return null;
		}
	}

	/**
	 * 对字符串进行散列, 支持md5与sha1算法.
	 */
	private static byte[] digest(String input, String algorithm) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			return messageDigest.digest(input.getBytes());
		} catch (GeneralSecurityException e) {
			throw new IllegalStateException("Security exception", e);
		}
	}

	//-- File Hash function --//
	/**
	 * 对文件进行md5散列,返回Hex编码结果.
	 */
	public static String md5ToHex(InputStream input) throws IOException {
		return digest(input, MD5);
	}

	/**
	 * 对文件进行sha1散列,返回Hex编码结果.
	 */
	public static String sha1ToHex(InputStream input) throws IOException {
		return digest(input, SHA1);
	}

	/**
	 * 对文件进行散列, 支持md5与sha1算法.
	 */
	private static String digest(InputStream input, String algorithm) throws IOException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			int bufferLength = 1024;
			byte[] buffer = new byte[bufferLength];
			int read = input.read(buffer, 0, bufferLength);

			while (read > -1) {
				messageDigest.update(buffer, 0, read);
				read = input.read(buffer, 0, bufferLength);
			}

			return com.zlycare.web.boss_24.utils.common.utils.EncodeUtils.hexEncode(messageDigest.digest());

		} catch (GeneralSecurityException e) {
			throw new IllegalStateException("Security exception", e);
		}
	}
	
	public static void main(String[] args) {
		 System.out.println(sha1ToBase64UrlSafe("123456"));
	}
}
