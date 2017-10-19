package com.zlycare.web.boss_24.beans.constants.order;

/**
 * 订单服务标准
 *
 * @author DaiJian
 * @since 1.5.0
 */
public enum ServiceLevelEnum {
    /**
     * 初级
     */
    FIRST_LEVEL(1, "初级"),
    /**
     * 中级
     */
    SECOND_LEVEL(2, "中级"),
    /**
     * 高级
     */
    THIRD_LEVEL(3, "高级"),;
    /**
     * 服务等级
     */
    private int level;
    /**
     * 服务等级名称
     */
    private String levelName;

    ServiceLevelEnum(int level, String levelName) {
        this.level = level;
        this.levelName = levelName;
    }

    public int getLevel() {
        return level;
    }

    public String getLevelName() {
        return levelName;
    }
}
