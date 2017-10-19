package com.zlycare.web.boss_24.core.mongo.po;

import org.springframework.data.annotation.Id;

/**
 * Author : linguodong
 * Create : 2017/6/20
 * Update : 2017/6/20
 * Descriptions : 用户拥有商铺表
 */
public class Shops {
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
     * 用户商户所在城市
     */
    private String shopCity;
    /**
     * 用户商户名称
     */
    private String shopName;
    /**
     * 用户商户类型
     */
    private String shopType;
    /**
     * 用户子类型
     */
    private String shopSubType;

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

    public String getShopCity() {
        return shopCity;
    }

    public void setShopCity(String shopCity) {
        this.shopCity = shopCity;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopSubType() {
        return shopSubType;
    }

    public void setShopSubType(String shopSubType) {
        this.shopSubType = shopSubType;
    }
}
