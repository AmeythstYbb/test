package com.zlycare.web.boss_24.controller.vo;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Author : linguodong
 * Create : 2017/8/31
 * Update : 2017/8/31
 * Descriptions : 版本管理实体(强升)
 */
public class VersionVo {
    /**
     * id
     */
    @Id
    private String id;
    /**
     * 创建时间
     * app端取最新的数据用作版本管理
     */
    private Double time;
    /**
     * 版本说明
     */
    private String desc;
    /**
     * 版本名称
     */
    private String name;
    /**
     * 当前最新版本
     */
    private Integer code;
    /**
     * 当前最新版链接
     */
    private String url;
    /**
     * 禁止的版本号
     */
    private Integer badCode;
    /**
     * 无须强升的最低版本
     */
    private Integer minCode;
    /**
     * 所属APP
     */
    private String type;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getBadCode() {
        return badCode;
    }

    public void setBadCode(Integer badCode) {
        this.badCode = badCode;
    }

    public Integer getMinCode() {
        return minCode;
    }

    public void setMinCode(Integer minCode) {
        this.minCode = minCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
