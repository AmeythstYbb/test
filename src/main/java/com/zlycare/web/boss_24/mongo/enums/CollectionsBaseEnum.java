package com.zlycare.web.boss_24.mongo.enums;

/**
 * @author lufanglong
 * @date 2015-11-20 21:18
 * @Description Mongo的collection名称枚举
 */
public enum CollectionsBaseEnum {
    SEQUENCE("sequence");

    String name;

    CollectionsBaseEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
