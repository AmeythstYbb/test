package com.zlycare.web.boss_24.beans.constants.qrcode;

/**
 * @author lena
 * @date 2016/12/8
 * @Description 二维码下载时，对UA里关键字的枚举
 */
public enum UAKeywordEnum {
    /**
     * UA 表示微信自带浏览器的关键字
     */
    UA_BROWSER_MICROMSG("MicroMessenger"),

    /**
     * UA 表示iphone的关键字
     */
    UA_OS_IPHONE("iPhone"),

    /**
     * UA 表示ipad的关键字
     */
    UA_OS_IPAD("iPad"),

    /**
     * UA 表示安卓系统的关键字
     */
    UA_OS_ANDROID("Android");

    UAKeywordEnum(String keyword) {
        this.keyword = keyword;
    }

    private String keyword;

    public String getKeyword() {
        return keyword;
    }
}
