package com.zlycare.web.boss_24.beans.constants;

/**
 * Package Name: com.dongdaodao.beans.constants
 * Author: WuZheng
 * Create Date: 2016/12/6 9:21
 * Description:提现步骤枚举，用于提现操作的log，对应cmsWithdrawalOperateLog, 因为涉及到两个User模块而抽出来在daodao-beans
 */
public enum WithdrawalStepsEnum {
    /**
     * 第1步：开具发票
     */
    STEP_ONE_INVOICE(1),

    /**
     * 第2步：审核发票
     */
    STEP_TWO_AUDIT_INVOICE(2),

    /**
     * 第3步：打款
     */
    STEP_THREE_PAY(3);

    WithdrawalStepsEnum(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }
}
