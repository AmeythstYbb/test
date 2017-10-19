package com.zlycare.web.boss_24.beans.annotation;


import com.zlycare.web.boss_24.beans.constants.StringTrimEnum;

import java.lang.annotation.*;

/**
 * Descriptions :
 * Author : kaihua
 * Create : 2016/8/29 20:50
 * Update : 2016/8/29 20:50
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringTrimProperty {

    StringTrimEnum stringTrimEnum() default StringTrimEnum.CLEAR_PREFIX_SUFFIX_TRIM;
}
