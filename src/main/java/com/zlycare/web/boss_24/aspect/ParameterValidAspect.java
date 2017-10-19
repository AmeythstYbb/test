package com.zlycare.web.boss_24.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


/**
 * @author lufanglong
 * @date 2015-09-14 16:11
 * @Description 参数验证错误处理拦截器
 * 负责拦截开启Spring @Valid 的请求，自动将错误包装成异常，由异常拦截器统一处理
 */
@Aspect
@Component
public class ParameterValidAspect {
    @Before("@within(org.springframework.web.bind.annotation.RequestMapping) && args(..,errors)")
    public void valid(Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException();
        }
    }
}
