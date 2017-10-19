package com.zlycare.web.boss_24.beans.constants;

/**
 * Descriptions : 开发环境
 * Author : kaihua
 * Create : 2016/9/5 13:22
 * Update : 2016/9/5 13:22
 */
public enum  SystemEnvEnum {

    /**
     * 开发
     */
    SYSTEM_DEV("dev"),

    /**
     * 本地
     */
    SYSTEM_LOCAL("local"),

    /**
     * 生产
     */
    SYSTEM_PRODUCTION("production"),

    /**
     * 测试
     */
    SYSTEM_TEST("test"),


    /**
     * 预发布
     */
    SYSTEM_VERIFY("verify");


    private String name;

    public String getName()
    {
        return this.name;
    }

    SystemEnvEnum(String name) {
        this.name = name;
    }

}
