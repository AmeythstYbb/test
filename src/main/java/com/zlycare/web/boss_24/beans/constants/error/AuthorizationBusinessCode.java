package com.zlycare.web.boss_24.beans.constants.error;

/**
 * @author lufanglong
 * @date 2016-01-14 16:11
 * @Description 鉴权业务码
 */
public enum AuthorizationBusinessCode {

    /**
     * 1004
     */
    AuthorizationError(1004, "token认证错误"),
    /**
     * 1005
     */
    AuthorizationIllegalError(1005, "token非法"),
    /**
     * 1006
     */
    AuthorizationExpiredError(1006, "token过期"),
    /**
     * 1007
     */
    AuthorizationRefreshError(1007, "refreshToken错误"),
    /**
     * 1009
     */
    AuthorizationOffsiteLoginError(1009, "异地登录");

    /**
     * 业务码
     */
    private int code;
    /**
     * 错误描述
     */
    private String description;

    AuthorizationBusinessCode(int code, String description) {
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
