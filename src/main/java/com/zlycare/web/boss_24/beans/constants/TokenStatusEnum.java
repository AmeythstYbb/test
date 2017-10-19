package com.zlycare.web.boss_24.beans.constants;

/**
 * Descriptions :
 * Author : kaihua
 * Create : 2016/2/23 17:44
 * Update : 2016/2/23 17:44
 */
public enum TokenStatusEnum {

    /**
     * 正常
     */
    VALID_STATUS(1),

    /**
     * 过期（到有效期）Invalid
     */
    EXCEED_INVALID_STATUS(0),

    /**
     * 异地登录
     */
    OFFSITE_INVALID_STATUS(2);

    private int value;

    public int getValue() {
        return value;
    }

    TokenStatusEnum(int value) {
        this.value = value;
    }

    public TokenStatusEnum getTokenStatusEnum(Integer value) {
        if (value != null) {
            if (value.intValue() == VALID_STATUS.getValue()) {
                return VALID_STATUS;
            } else if (value.intValue() == EXCEED_INVALID_STATUS.getValue()) {
                return EXCEED_INVALID_STATUS;
            } else if (value.intValue() == OFFSITE_INVALID_STATUS.getValue()) {
                return OFFSITE_INVALID_STATUS;
            }
        }
        return null;
    }

}
