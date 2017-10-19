package com.zlycare.web.boss_24.core.mongo.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by zhanglw on 2017/6/1.
 * Create : 2017/6/1
 * Update : 2017/6/1
 * Descriptions: Boss日志信息Bean
 */
@Document(collection = "bossLog")
public class BossLog {

    /**
     * 自增ID
     */
    @Id
    private String id;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 操作信息,可能是url等
     */
    private String operateInfo;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 日志创建时间
     */
    private Date createAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOperateInfo() {
        return operateInfo;
    }

    public void setOperateInfo(String operateInfo) {
        this.operateInfo = operateInfo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
