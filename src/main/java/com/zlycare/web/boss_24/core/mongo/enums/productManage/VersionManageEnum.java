package com.zlycare.web.boss_24.core.mongo.enums.productManage;

/**
 * Author : linguodong
 * Create : 2017/8/31
 * Update : 2017/8/31
 * Descriptions : 版本对应标识枚举类
 */
public enum VersionManageEnum {
    /**
     * 24热线-安卓
     */
    CUSTOMER_ANDROID_24("24customer-android", "24热线-安卓"),
    /**
     * 24热线-iOS
     */
    CUSTOMER_IOS_24("24customer-ios", "24热线-iOS");
    /**
     *
     */
    private String value;
    /**
     *
     */
    private String name;

    VersionManageEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
