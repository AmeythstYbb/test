package com.zlycare.web.boss_24.beans.constants;

/**
 * Package Name: com.dongdaodao.beans.constants
 * Author: WuZheng
 * Create Date: 2016/12/5 17:21
 * Description: 申请提现发票开具的默认值 对应XX_withdrawal_invoice表
 */
public enum InvoiceDefaultValueEnum {

    /**
     * 默认发票抬头（中文括号）
     */
    DEFAULT_TITLE("东道道（北京）互联网科技有限公司"),

    /**
     * 默认发票类型：普通发票
     */
    DEFAULT_TYPE("1"),

    /**
     * 默认发票明细
     */
    DEFAULT_CONTENT("设计费");

    InvoiceDefaultValueEnum(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    private String defaultValue;

    public String getDefaultValue() {
        return defaultValue;
    }
}
