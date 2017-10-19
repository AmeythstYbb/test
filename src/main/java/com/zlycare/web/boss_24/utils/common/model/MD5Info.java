package com.zlycare.web.boss_24.utils.common.model;

/**
 * Description: MD5Info
 * Author: lufanglong
 * Update: 2014-09-13 10:54
 */
public class MD5Info {
    /**
     * 小写的 16 进制 MD5 字符串表达，有32个字符
     */
    private String md5String;
    /**
     * MD5共128bit， 高64bit 的 long 表达
     */
    private long high;
    /**
     * MD5共128bit， 低64bit 的 long 表达
     */
    private long low;

    public MD5Info(String md5String, long high, long low) {
        this.md5String = md5String;
        this.high = high;
        this.low = low;
    }

    public String getMd5String() {
        return md5String;
    }

    public long getHigh() {
        return high;
    }

    public long getLow() {
        return low;
    }
}
