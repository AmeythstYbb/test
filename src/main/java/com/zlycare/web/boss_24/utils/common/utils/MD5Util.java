package com.zlycare.web.boss_24.utils.common.utils;


import com.zlycare.web.boss_24.utils.common.model.MD5Info;

import java.io.InputStream;
import java.security.MessageDigest;

/**
 * Created by IntelliJ IDEA.
 * User: Mr.Wu
 * Date: 2008-4-28
 * Time: 22:54:35
 * To change this template use File | Settings | File Templates.
 */
public class MD5Util {
    // 用来将字节转换成 16 进制表示的字符
    private static final char HEX_DIGITS[] =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 进行md5加密
     * <p/>
     * 执行 1百万次 耗时大约 6~20秒
     *
     * @param text 源字符
     * @return 加密后字符
     */
    public static String md5(String text) {
        String result = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(text.getBytes("UTF-8"));
            byte[] b = md5.digest();
            result = byte2Hex(b);
        } catch (Exception e) {
            // do nothing
        }
        return result;
    }

    public static String md5(String text, String encode) {
        String result = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(text.getBytes(encode));
            byte[] b = md5.digest();
            result = byte2Hex(b);
        } catch (Exception e) {
            // do nothing
        }
        return result;
    }

    public static MD5Info md5Info(String text) {
        String result = "";
        long high = 0;
        long low = 0;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(text.getBytes("UTF-8"));
            byte[] b = md5.digest();
            result = byte2Hex(b);
            high = byte16High2Long(b);
            low = byte16Low2Long(b);
        } catch (Exception e) {
            // do nothing
        }
        return new MD5Info(result, high, low);
    }

    /**
     * 进行md5加密，取结果的前8个字符和后8个字符
     * <p/>
     * 执行 1百万次 耗时大约 6~20秒
     *
     * @param text 源字符
     * @return 加密后字符
     */
    public static String md5Half(String text) {
        String result = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(text.getBytes("UTF-8"));
            byte[] b = md5.digest();
            result = byte16to16Half(b);
        } catch (Exception e) {
            // do nothing
        }
        return result;
    }

    /**
     * 16位byte转换为32位字符串
     *
     * @param byte16
     * @return
     */
    protected static String byte2Hex(byte[] byte16) {
        int byteLength = byte16.length;
        // 用字节表示就是 byteLength 个字节
        char str[] = new char[byteLength * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
        // 所以表示成 16 进制需要 byteLength * 2 个字符
        int k = 0; // 表示转换结果中对应的字符位置
        for (int i = 0; i < byteLength; i++) { // 从第一个字节开始，对 MD5 的每一个字节
            // 转换成 16 进制字符的转换
            byte byte0 = byte16[i]; // 取第 i 个字节
            str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
            // >>> 为逻辑右移，将符号位一起右移
            str[k++] = HEX_DIGITS[byte0 & 0xf]; // 取字节中低 4 位的数字转换
        }
        return new String(str); // 换后的结果转换为字符串
    }

    /**
     * 16位byte转换为32位字符串
     *
     * @param byte16
     * @return
     */
    protected static String byte16to16Half(byte[] byte16) {
        // 用字节表示就是 16 个字节
        char str[] = new char[16]; // 每个字节用 16 进制表示的话，使用两个字符，
        // 所以表示成 16 进制需要 32 个字符
        int k = 0; // 表示转换结果中对应的字符位置
        for (int i = 0; i < 4; i++) {
            byte byte0 = byte16[i];
            str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
            // >>> 为逻辑右移，将符号位一起右移
            str[k++] = HEX_DIGITS[byte0 & 0xf]; // 取字节中低 4 位的数字转换
        }
        for (int i = 12; i < 16; i++) {
            byte byte0 = byte16[i];
            str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
            // >>> 为逻辑右移，将符号位一起右移
            str[k++] = HEX_DIGITS[byte0 & 0xf]; // 取字节中低 4 位的数字转换
        }
        return new String(str); // 换后的结果转换为字符串
    }

    protected static long byte16High2Long(byte[] byte16) {
        long result = 0;
        for (int i = 0; i < 8; i++) {
            long byte0 = (long) byte16[i];
            if (byte0 < 0) {
                byte0 = 256 + byte0;
            }
            result |= byte0 << ((7 - i) * 8);
        }
        return result;
    }

    protected static long byte16Low2Long(byte[] byte16) {
        long result = 0;
        for (int i = 8; i < 16; i++) {
            long byte0 = (long) byte16[i];
            if (byte0 < 0) {
                byte0 = 256 + byte0;
            }
            result |= byte0 << ((15 - i) * 8);
        }
        return result;
    }

//
//	/**
//	 * 进行md4加密
//	 * 执行 1百万次 耗时大约 5~20秒
//	 *
//	 * @param text 源字符串
//	 * @return 加密后字符串
//	 */
//	public static String md4(String text) {
//		String result = null;
//		try {
//			MessageDigest md4 = MD4.getInstance();
//			md4.update(text.getBytes("UTF-8"));
//			byte[] b = md4.digest();
//			result = byte16to32(b);
//		} catch (Exception e) {
//			// do nothing
//		}
//		return result;
//	}

    public static String streamToMD5(InputStream inputStream) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int numRead;
            while ((numRead = inputStream.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            return byte2Hex(md5.digest());
        } catch (Exception e) {
            return "";
        }
    }

//    public static void main(String[] args) {
//        System.out.println(md5("1521A0336").toUpperCase());
//    }
}
