package com.zlycare.web.boss_24.constants.sys;

/**
 * Author : linguodong
 * Create : 2017/5/31
 * Update : 2017/5/31
 * Descriptions :
 */
public class Shiro {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    /**
     * 设置管理端访问路径（ADMIN_PATH或FRONT_PATH可允许一个为空）
     * 1. 修改本类 ADMIN_PATH 常量
     * 2. 修改 applicationContext-shiro.xml 中的 shiroFilter
     * 3. 修改 decorators.xml 中的 default
     * 4. 修改 spring-mvc.xml 中的 mvc:view-controller
     */
    public static final String ADMIN_PATH = "/sys";
}
