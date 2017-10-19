package com.zlycare.web.boss_24.beans.constants;

/**
 * @author lufanglong
 * @date 2015-12-02 11:24
 * @Description 用户类型枚举
 */
public enum UserTypeEnum {

    /**
     * 用户
     */
    CUSTOMER("customer", 1, "c"),
    /**
     * 创意人
     */
    SUPPLIER("supplier", 2, "cp"),
    /**
     * 顾问
     */
    KEEPER("keeper", 3, "k");

    String type;
    int value;
    String role;

    public String getType() {
        return type;
    }

    public int getValue() {
        return this.value;
    }

    public String getRole() {
        return role;
    }

    UserTypeEnum(String type, int value, String role) {
        this.value = value;
        this.type = type;
        this.role = role;
    }


    public static UserTypeEnum getUserTypeEnum(Integer value)
    {
        if(value == null)
        {
            return null;
        }
        if(value.intValue() == CUSTOMER.getValue())
        {
            return CUSTOMER;
        }else if(value.intValue() == SUPPLIER.getValue())
        {
            return SUPPLIER;
        }else if(value.intValue() == KEEPER.getValue())
        {
            return KEEPER;
        }
        return null;
    }
}
