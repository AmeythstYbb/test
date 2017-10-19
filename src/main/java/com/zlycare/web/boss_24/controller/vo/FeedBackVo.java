package com.zlycare.web.boss_24.controller.vo;

import com.zlycare.web.boss_24.core.mongo.po.Users;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by MRZ on 2017/9/3.
 */
public class FeedBackVo {
    /**
     * id
     */
    @Id
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户手机号
     */
    private  String phoneNum;
    /**
     * 反馈信息
     */
    private  String content;
    /**
     * 状态
     * 处理阶段, 0 - 未处理; 1 - 已阅读/接受; 2 - 已处理
     */
    private  Integer status;
    /**
     *反馈时间
     */
    private Double createdAt;
    /**
     *备注
     */
    private  String remarks;
    /**
     * 姓名
     */
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 申请时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 热线号
     */
    private  String docChatNum;
    /**
     * 修改时间
     */
    private Double updatedAt;

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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Double createdAt) {
        this.createdAt = createdAt;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDocChatNum() {
        return docChatNum;
    }

    public void setDocChatNum(String docChatNum) {
        this.docChatNum = docChatNum;
    }

    public Double getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Double updatedAt) {
        this.updatedAt = updatedAt;
    }
}
