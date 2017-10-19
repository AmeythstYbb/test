package com.zlycare.web.boss_24.utils.core;

import org.apache.commons.lang3.RandomUtils;

/**
 * 生成验证码
 *
 * @author DaiJian
 * @create 2016/1/20 17:46
 */
public class GenerateCaptchaUtil {
    private GenerateCaptchaUtil() {
    }

    /**
     * 随机生成4位数字
     *
     * @return
     */
    public static String generateCaptcha() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            builder.append(RandomUtils.nextInt(0, 9));
        }
        return builder.toString();
    }
}
