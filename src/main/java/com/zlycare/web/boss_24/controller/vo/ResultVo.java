package com.zlycare.web.boss_24.controller.vo;

/**
 * Author : linguodong
 * Create : 2017/6/7
 * Update : 2017/6/7
 * Descriptions :
 */
public class ResultVo {
    /**
     * 成功(1:成功;0:失败)
     */
    private Integer success = 0;

    /**
     * 消息
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public ResultVo(boolean flag) {
        this.success = flag ? 1 : 0;
    }


    public ResultVo(boolean flag, String message) {
        this.success = flag ? 1 : 0;
        this.message = message;
    }
}
