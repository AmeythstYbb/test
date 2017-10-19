package com.zlycare.web.boss_24.beans.constants.error;

/**
 * @author lufanglong
 * @date 2016-01-14 16:11
 * @Description API业务码
 */
public enum ApiBusinessCode {

    SUCCESS(1001, "成功"),
    FAILURE(1002, "失败"),
    /**
     * 1003
     */
    PARAMETER_ERROR(1003, "参数错误"),
    /**
     * 1004
     */
    TOKEN_ERROR(1004, "token认证错误"),
    /**
     * 1005
     */
    ILLEGAL_TOKEN_ERROR(1005, "token非法"),
    /**
     * 1006
     */
    EXPIRED_TOKEN_ERROR(1006, "token过期"),
    /**
     * 1007
     */
    REFRESH_TOKEN_ERROR(1007, "refreshToken错误"),
    /**
     * 1008
     */
    SIGN_ERROR(1008, "URL签名验证错误"),
    /**
     * 9999
     */
    SYSTEM_ERROR(9999, "系统错误");

    /**
     * 业务码
     */
    private int code;
    /**
     * 错误描述
     */
    private String description;

    ApiBusinessCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
