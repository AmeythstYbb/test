package com.zlycare.web.boss_24.beans.constants;

/**
 * 支付规则相关常量
 *
 * @author DaiJian
 * @create 2015/12/10 18:13
 */
public enum PaymentRuleConstant {
    /**
     * 支付定金标识
     */
    DEPOSIT(1, "deposit"),
    /**
     * 支付首款标识
     */
    FIRST_PAY(2, "firstPay"),
    /**
     * 支付中款标识
     */
    SECOND_PAY(3, "secondPay"),
    /**
     * 支付三次款标识
     */
    THIRD_PAY(4, "thirdPay");
    /**
     * 支付类型代码
     */
    private int payCode;
    /**
     * 支付类型名称
     */
    private String name;

    PaymentRuleConstant(int payCode, String name) {
        this.payCode = payCode;
        this.name = name;
    }

    public int getPayCode() {
        return payCode;
    }

    public String getName() {
        return name;
    }

    public static PaymentRuleConstant getPaymentRuleConstant(Integer payCode)
    {
        if(payCode == null)
        {
            return null;
        }
        if(payCode.intValue() == DEPOSIT.getPayCode())
        {
            return DEPOSIT;
        }else if(payCode.intValue() == FIRST_PAY.getPayCode())
        {
            return FIRST_PAY;
        }else if(payCode.intValue() == SECOND_PAY.getPayCode())
        {
            return SECOND_PAY;
        }else if(payCode.intValue() == THIRD_PAY.getPayCode())
        {
            return THIRD_PAY;
        }
        return null;
    }
}
