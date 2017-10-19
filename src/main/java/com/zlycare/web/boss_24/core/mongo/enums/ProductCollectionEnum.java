package com.zlycare.web.boss_24.core.mongo.enums;

/**
 * Product mongodb collection enum
 *
 * @author DaiJian
 * @since 1.9.8
 */
public enum ProductCollectionEnum {
    /**
     * 官网快速发布需求
     */
    OFFICIAL_WEB_REQUIREMENT("OfficialWebRequirement"),
    /**
     * 订单分配供应商时间
     *
     * @since 1.9.14
     */
    ORDER_CP_ASSIGN_TIME_LOGS("orderCpAssignTimeLogs"),
    /**
     * Order op status seq
     *
     * @since 1.9.14
     */
    ORDER_OP_STATUS("orderOpStatus"),
    /**
     * cpSolutionNoRecord long seq
     *
     * @since 1.10.0
     */
    CP_SOLUTION_NO_RECORD("cpSolutionNoRecord"),
    /**;
     * questionnaire
     */
    QUESTIONNAIRE("questionnaire"),;
    /**
     * 集合名词
     */
    private String name;

    ProductCollectionEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
