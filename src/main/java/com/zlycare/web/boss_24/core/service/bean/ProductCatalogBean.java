package com.zlycare.web.boss_24.core.service.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Author : linguodong
 * Create : 2017/7/25
 * Update : 2017/7/25
 * Descriptions : 产品三级目录
 */
@Document(collection = "productCatalog")
public class ProductCatalogBean {
    public ProductCatalogBean() {
        Long time = new Date().getTime();
        this.sort = 30;
        this.isDeleted = false;
        this.createdAt = time.doubleValue();
        this.statisticsUpdatedAt = time.doubleValue();
        this.updatedAt = time.doubleValue();
        // this.show = 1; 页面传递
    }

    /**
     * 自增ID
     */
    @Id
    private String id;
    /**
     * 父级目录id
     */
    private String parentId;
    /**
     * 父级目录id 集合
     */
    private String parentIds;
    /**
     * 当前目录名称
     */
    private String name;
    /**
     * 图标路径
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否展示
     */
    private Integer show;
    /**
     * 会员专区类型
     */
    private String vipType;
    /**
     * 创建时间
     */
    private Double createdAt;
    /**
     * 修改时间
     */
    private Double updatedAt;
    /**
     * 同步修改时间
     */
    private Double statisticsUpdatedAt;
    /**
     * 删除标识 默认false;
     */
    private boolean isDeleted;

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
    }

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    public Double getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Double createdAt) {
        this.createdAt = createdAt;
    }

    public Double getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Double updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Double getStatisticsUpdatedAt() {
        return statisticsUpdatedAt;
    }

    public void setStatisticsUpdatedAt(Double statisticsUpdatedAt) {
        this.statisticsUpdatedAt = statisticsUpdatedAt;
    }
}
