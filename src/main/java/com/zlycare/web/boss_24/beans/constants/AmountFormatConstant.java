package com.zlycare.web.boss_24.beans.constants;

/**
 * AmountProperty 注解使用的枚举
 * 标识对金额进行的操作
 *
 * @author DaiJian
 * @create 2016/2/16 14:09
 */
public enum AmountFormatConstant {
    /**
     * 标识对金额进行乘100操作
     */
    AMOUNT_TRANSFER_MULTIPLY,
    /**
     * 标识对金额进行除100操作
     */
    AMOUNT_TRANSFER_DIVIDE,
    /**
     * 标识对金额进行乘100操作,DOUBLE类型
     *
     * @since 1.9.12
     */
    AMOUNT_TRANSFER_MULTIPLY_DOUBLE,
    /**
     * 标识对金额进行除100操作,DOUBLE类型
     *
     * @since 1.9.12
     */
    AMOUNT_TRANSFER_DIVIDE_DOUBLE,;
}
