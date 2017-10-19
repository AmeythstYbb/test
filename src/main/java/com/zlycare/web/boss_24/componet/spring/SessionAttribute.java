package com.zlycare.web.boss_24.componet.spring;

import java.lang.annotation.*;

/**
 * 标注用于向方法参数注入SessionAttribute中的对象<br />
 *
 * @author yz.wu
 */
@Target( { ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionAttribute {
    /**
     * 属性名，如果为空，则使用参数名查找
     */
    String value() default "";

    boolean required() default true;
}
