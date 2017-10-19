package com.zlycare.web.boss_24.beans.constants;

/**
 * Descriptions :
 * Author : kaihua
 * Create : 2016/2/18 17:30
 * Update : 2016/2/18 17:30
 */
public enum AmountTypeEnum {

    DAOMI_TYPE(1, "粒"),

    CASH_TYPE(2, "元");


    AmountTypeEnum(int value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    private int value;

    private String unit;

    public int getValue() {
        return this.value;
    }

    public String getUnit() {
        return this.unit;
    }

    public static AmountTypeEnum getAmountTypeEnum(Integer value) {
        if (value != null) {
            if (value.intValue() == DAOMI_TYPE.getValue()) {
                return DAOMI_TYPE;
            } else if (value.intValue() == CASH_TYPE.getValue()) {
                return CASH_TYPE;
            }
        }
        return null;
    }

}
