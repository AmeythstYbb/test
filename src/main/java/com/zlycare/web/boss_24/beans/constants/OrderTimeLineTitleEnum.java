package com.zlycare.web.boss_24.beans.constants;

/**
 * 订单timeline 节点枚举
 *
 * @author DaiJian
 * @create 2016/3/8 17:56
 */
public enum OrderTimeLineTitleEnum {
    /**
     * 顾问1.0.0版本
     */
    KEEPER_VERSION_1(1, "1.0.0"),
    /**
     * 顾问1.0.0版本
     */
    CP_VERSION_1(2, "1.0.0"),;
    /**
     * 版本号
     */
    private String version;
    /**
     * 订单timeline 类型
     */
    private Integer type;

    OrderTimeLineTitleEnum(Integer type, String version) {
        this.version = version;
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public Integer getType() {
        return type;
    }
}
