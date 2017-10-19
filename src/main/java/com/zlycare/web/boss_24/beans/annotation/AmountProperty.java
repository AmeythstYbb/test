package com.zlycare.web.boss_24.beans.annotation;


import com.zlycare.web.boss_24.beans.constants.AmountFormatConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户进行金额装还
 *
 * @author DaiJian
 * @create 2016/2/15 17:37
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AmountProperty {
    /**
     * 标识操作的类型
     */
    AmountFormatConstant amountFormatType();
}
