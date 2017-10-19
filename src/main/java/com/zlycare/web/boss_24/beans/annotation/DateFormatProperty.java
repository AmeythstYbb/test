package com.zlycare.web.boss_24.beans.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/**
 * Description: 日期类型格式化
 * Author: lufanglong
 * Update: 2015-12-15 10:08
 */
public @interface DateFormatProperty {
    /**
     * Date数据的数据源字段
     *
     * @return
     */
    String source();

    /**
     * 格式化后的格式
     *
     * @return
     */
    String format() default "MM-dd HH:mm";

    /**
     * Date数据的数据源字段是否使用
     * 默认是false, 转json时该字段不使用(不返回)
     *
     * @return
     */
    boolean sourceEnable() default false;

}
