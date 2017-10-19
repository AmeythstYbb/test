package com.zlycare.web.boss_24.controller.vo;

/**
 * Author : linguodong
 * Create : 2017/6/7
 * Update : 2017/6/7
 * Descriptions :
 */
public class JsonVo {

    public JsonVo() {
    }

    public JsonVo(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public JsonVo(String name, String value, Integer error, String url) {
        this.name = name;
        this.value = value;
        this.error = error;
        this.url = url;
    }

    private String name;
    private String value;
    private Integer error;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
