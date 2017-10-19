package com.zlycare.web.boss_24.core.mongo.bean;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/8/4
 * Update : 2017/8/4
 * Descriptions : mongo查询参数封装
 */
public class MemberSearchBean {
    /**
     * 当前时间
     */
    private Double currentTime;
    /**
     * 会员类型列表
     */
    private List<String> typeList;
    /**
     * 用户ID列表
     */
    private List<String> userIds;
    // 分页参数
    /**
     * 起始值
     */
    private Integer start;
    /**
     * 条数
     */
    private Integer pageSize;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Double getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Double currentTime) {
        this.currentTime = currentTime;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
