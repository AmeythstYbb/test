package com.zlycare.web.boss_24.beans.constants;

/**
 * Descriptions : 交易类型
 * Author : kaihua
 * Create : 2016/1/20 20:18
 * Update : 2016/1/20 20:18
 */
public enum PaymentBusinessEnum {

    /**
     * 稻米充值
     */
    DAOMI_CHARGE(1, "稻米充值"),
    /**
     * 定金支付
     */
    DEPOSIT_PAY(2, "支付定金"),
    /**
     * 首款支付
     */
    FIRST_MONEY(3, "支付首款"),
    /**
     * 二次支付
     */
    SECOND_MONEY(4, "支付中款"),
    /**
     * 尾款支付
     */
    LAST_MONEY(5, "支付尾款"),
    /**
     * 稻米奖励
     */
    DAOMI_AWARD(6,"稻米奖励"),
	
	/**
     * 系统充值 CMS
     */
    DAOMI_SYSTEM(7,"系统充值");

    private int value;

    private String name;

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    PaymentBusinessEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static PaymentBusinessEnum getPaymentBusinessEnum(Integer value) {
        if (value != null) {
            if (value.intValue() == DAOMI_CHARGE.getValue()) {
                return DAOMI_CHARGE;
            } else if (value.intValue() == DEPOSIT_PAY.getValue()) {
                return DEPOSIT_PAY;
            } else if (value.intValue() == FIRST_MONEY.getValue()) {
                return FIRST_MONEY;
            } else if (value.intValue() == SECOND_MONEY.getValue()) {
                return SECOND_MONEY;
            } else if (value.intValue() == LAST_MONEY.getValue()) {
                return LAST_MONEY;
            }
            else if (value.intValue() == DAOMI_SYSTEM.getValue()) {
                return DAOMI_SYSTEM;
            }
        }
        return null;
    }
}
