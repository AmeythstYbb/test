package com.zlycare.web.boss_24.utils.common.dto;

/**
 * Description httpClient请求的结果对象,包括请求结果的状态码和响应信息
 * Created by WangPeitao on 2015/3/13.
 */
public class HttpClientResultDto {
    private int statusCode;
    private String entity;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
