package com.zlycare.web.boss_24.controller.vo;

/**
 * Author : linguodong
 * Create : 2017/6/7
 * Update : 2017/6/7
 * Descriptions :
 */
public class DwzVo {
    private String statusCode;
    private String message;
    private String navTabId;
    private String rel;
    private String callbackType;
    private String forwardUrl;

    public DwzVo(String statusCode, String message, String navTabId, String rel, String callbackType, String forwardUrl) {
        this.statusCode = statusCode;
        this.message = message;
        this.navTabId = navTabId;
        this.rel = rel;
        this.callbackType = callbackType;
        this.forwardUrl = forwardUrl;
    }

    public DwzVo(String navTabId) {
        this.statusCode = "200";
        this.message = "操作成功";
        this.navTabId = navTabId;
        this.rel = "";
        this.callbackType = "closeCurrent";
        this.forwardUrl = "";
    }

    public DwzVo(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.navTabId = "";
        this.rel = "";
        this.callbackType = "";
        this.forwardUrl = "";
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNavTabId() {
        return navTabId;
    }

    public void setNavTabId(String navTabId) {
        this.navTabId = navTabId;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(String callbackType) {
        this.callbackType = callbackType;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
