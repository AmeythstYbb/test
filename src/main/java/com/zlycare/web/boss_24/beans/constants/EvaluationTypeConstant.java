package com.zlycare.web.boss_24.beans.constants;

/**
 * Description:订单评价类型常量
 * Author: DaiJian
 * Update: 2015/12/16 11:41
 */
public class EvaluationTypeConstant {
    private EvaluationTypeConstant() {
    }

    /**
     * 创意人对顾问评价
     */
    public static final int CP_2_KEEPER = 1;
    /**
     * 顾问对客户评价
     */
    public static final int KEEPER2_2_CUSTOMER = 2;
    /**
     * 顾问对创意人评价
     */
    public static final int KEEPER_2_CP = 3;
    /**
     * 客户对顾问评价
     */
    public static final int CUSTOMER_2_KEEPER = 4;
    /**
     * 客户对创意人评价
     */
    public static final int CUSTOMER_2_CP = 5;
}