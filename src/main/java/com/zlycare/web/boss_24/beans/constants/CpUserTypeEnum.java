package com.zlycare.web.boss_24.beans.constants;

/**
 * Descriptions :
 * Author : kaihua
 * Create : 2016/5/29 17:08
 * Update : 2016/5/29 17:08
 */
public enum  CpUserTypeEnum {

    /**
     * 个人
     */
    PERSON(1),

    /**
     * 企业
     */
    BUSINESS(2);


    int value;

    public int getValue() {
        return this.value;
    }

    CpUserTypeEnum( int value) {
        this.value = value;
    }

    public static CpUserTypeEnum getCpUserTypeEnum(Integer value)
    {
        if(value == null)
        {
            return null;
        }
        if(value.intValue() == PERSON.getValue())
        {
            return PERSON;
        }
        return BUSINESS;
    }
}
