package com.zlycare.web.boss_24.beans.constants;

/**
 * Package Name: com.dongdaodao.beans.constants
 * Author: WuZheng
 * Create Date: 2016/7/25 11:06
 * Description: 提现订单详情表对应的枚举 XX_withdrawal_detail
 */
public enum WithdrawalStatusEnum {
    NOT_ACCESSIBLE(1), //不可提现
    ACCESSIBLE(2), //可提现
    WITHDRAWAL_DONE(3), //已提现
    WITHDRAWAL_ERROR(4); //提现异常


    WithdrawalStatusEnum(int statusCode) {
        this.statusCode = statusCode;
    }

    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }
}
