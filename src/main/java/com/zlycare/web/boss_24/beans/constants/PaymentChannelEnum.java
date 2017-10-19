package com.zlycare.web.boss_24.beans.constants;

/**
 * Descriptions : 支付渠道
 * Author : kaihua
 * Create : 2016/2/6 17:17
 * Update : 2016/2/6 17:17
 */
public enum PaymentChannelEnum {

    /**
     * 支付宝
     */
    PAYMENT_CHANNEL_ALIPAY(1,"支付宝"),

    /**
     * 稻米支付
     */
    PAYMENT_CHANNEL_RICEPAY(2,"稻米支付"),

    /**
     * 易宝
     */
    PAYMENT_CHANNEL_YIBAO(3,"易宝"),

    /**
     * 快钱
     */
    PAYMENT_CHANNEL_KUAIQIAN(4,"快钱"),
	
	/**
     * 快钱
     */
    PAYMENT_CHANNEL_SYSTEM(5,"系统充值");

    private int value;

    private String name;

    public String getName(){return this.name;}

    public int getValue() {
        return this.value;
    }

    PaymentChannelEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static PaymentChannelEnum getPaymentChannelEnum(Integer value) {
        if (value != null) {
            if (value == PAYMENT_CHANNEL_ALIPAY.getValue()) {
                return PAYMENT_CHANNEL_ALIPAY;
            } else if (value == PAYMENT_CHANNEL_RICEPAY.getValue()) {
                return PAYMENT_CHANNEL_RICEPAY;
            } else if (value == PAYMENT_CHANNEL_YIBAO.getValue()) {
                return PAYMENT_CHANNEL_YIBAO;
            } else if( value == PAYMENT_CHANNEL_KUAIQIAN.getValue()){
                return PAYMENT_CHANNEL_KUAIQIAN;
            }
            else if( value == PAYMENT_CHANNEL_SYSTEM.getValue()){
                return PAYMENT_CHANNEL_SYSTEM;
            }
        }
        return null;
    }
}
