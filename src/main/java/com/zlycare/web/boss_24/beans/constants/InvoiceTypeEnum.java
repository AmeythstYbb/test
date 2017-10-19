package com.zlycare.web.boss_24.beans.constants;

/**
 * Package Name: com.dongdaodao.beans.constants
 * Author: WuZheng
 * Create Date: 2016/12/5 16:57
 * Description: 发票类型，对应XX_withdrawal_invoice表
 */
public enum InvoiceTypeEnum {

    NORMAL(1, "普通发票");

    InvoiceTypeEnum(int value, String description) {
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
