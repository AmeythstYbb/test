package com.zlycare.web.boss_24.beans.constants;

/**
 * Descriptions : 系统类型
 * Author : kaihua
 * Create : 2016/1/26 18:15
 * Update : 2016/1/26 18:15
 */
public enum ClientSystemEnum {

    /**
     * IPHONE OS 系统
     */
    IOS_SYSTEM("iOS", 1),
    /**
     * Android 系统
     */
    ANDROID_SYSTEM("Android", 2);

    private int value;

    private String name;

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    ClientSystemEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
