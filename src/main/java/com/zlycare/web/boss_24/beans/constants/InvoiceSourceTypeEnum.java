package com.zlycare.web.boss_24.beans.constants;

/**
 * Package Name: com.dongdaodao.beans.constants
 * Author: WuZheng
 * Create Date: 2016/12/5 16:52
 * Description: 提现发票的来源（1.代开 2.邮寄） 对应XX_withdrawal_invoice表
 */
public enum InvoiceSourceTypeEnum {
    /**
     * 代开发票
     */
    AGENCY(1, "代缴"),

    /**
     * 邮寄发票
     */
    EXPRESS(2, "邮寄");

    InvoiceSourceTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    private int value;

    private String description;

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
