package com.zlycare.web.boss_24.beans.constants;

/**
 * Package Name: com.dongdaodao.beans.constants
 * Author: WuZheng
 * Create Date: 2016/7/25 10:59
 * Description: 提现申请表状态对应的枚举 XX_withdrawal
 */
public enum WithdrawalApplyStatusEnum {
    /**
     * 已申请提现（待开具发票）
     */
    APPLIED(1),

    /**
     * 发票已开具
     */
    INVOICED(2),

    /**
     * 发票审核通过
     */
    AUDIT_PASS(3),

    /**
     * 发票审核不通过
     */
    AUDIT_FAIL(4),

    /**
     * 打款成功
     */
    WITHDRAWAL_SUCCESS(5),

    /**
     * 打款失败
     */
    WITHDRAWAL_FAIL(6);


    WithdrawalApplyStatusEnum(int statusCode) {
        this.statusCode = statusCode;
    }

    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }
}
