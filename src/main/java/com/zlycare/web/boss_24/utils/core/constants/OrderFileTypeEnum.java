package com.zlycare.web.boss_24.utils.core.constants;

/**
 * 订单文件类型
 *
 * @author lufanglong
 * @date 2016-07-12 14:26
 */
public enum OrderFileTypeEnum {

    /**
     * 初稿
     */
    PROJECT("P"),
    /**
     * 深化
     */
    DEEPON("D"),
    /**
     * 源文件
     */
    SOURCE("S");

    private String value;

    public String getValue() {
        return this.value;
    }

    OrderFileTypeEnum(String value) {
        this.value = value;
    }
}
