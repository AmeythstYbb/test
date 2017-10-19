package com.zlycare.web.boss_24.beans.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageUrlProperty {
    /**
     * 图片所在路径
     * 数据库中不保存图片路径名
     *
     * @return
     */
    String path() default "";
}
