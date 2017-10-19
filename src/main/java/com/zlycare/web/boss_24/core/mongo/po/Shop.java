package com.zlycare.web.boss_24.core.mongo.po;

import org.springframework.data.annotation.Id;

/**
 * Author : linguodong
 * Create : 2017/6/19
 * Update : 2017/6/19
 * Descriptions : 商户实体
 */
public class Shop {
    /**
     * id
     */
    @Id
    private String id;
    /**
     * 所在城市
     */
    private String shopCity;
    /**
     * 商户名称
     */
    private String shopName;
    /**
     * 商户地址
     */
    private String shopAddress;
    /**
     * 商户类型
     */
    private String shopType;
    /**
     * 商户电话
     */
    private String shopPhoneNum;
    /**
     * 商户头像
     */
    private String shopAvatar;
    /**
     * 营业执照
     */
    private String shopLicense;
    /**
     * 子类型
     */
    private String shopSubType;
    /**
     * 经度
     */
    private Double shopAddressMapLon;
    /**
     * 纬度
     */
    private Double shopAddressMapLat;
    /**
     * 申请人id
     * applicantRef?
     */
    private String applicantId;
    /**
     * 申请人名称
     */
    private String applicantName;
    /**
     * 申请人电话
     */
    private String applicantPhone;
    /**
     * 类型 17为商户
     */
    private Integer type;
    /**
     * 状态
     * -1 拒绝 0 未审核 1 通过
     */
    private Integer status;
    /**
     * 来源
     */
    private String source;
    /**
     * 拒绝原因
     */
    private String reason;
    /**
     * 申请创建时间
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
     * 运营操作时间
     */
    private Double opReviewdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopPhoneNum() {
        return shopPhoneNum;
    }

    public void setShopPhoneNum(String shopPhoneNum) {
        this.shopPhoneNum = shopPhoneNum;
    }

    public String getShopAvatar() {
        return shopAvatar;
    }

    public void setShopAvatar(String shopAvatar) {
        this.shopAvatar = shopAvatar;
    }

    public String getShopLicense() {
        return shopLicense;
    }

    public void setShopLicense(String shopLicense) {
        this.shopLicense = shopLicense;
    }

    public String getShopSubType() {
        return shopSubType;
    }

    public void setShopSubType(String shopSubType) {
        this.shopSubType = shopSubType;
    }

    public Double getShopAddressMapLon() {
        return shopAddressMapLon;
    }

    public void setShopAddressMapLon(Double shopAddressMapLon) {
        this.shopAddressMapLon = shopAddressMapLon;
    }

    public Double getShopAddressMapLat() {
        return shopAddressMapLat;
    }

    public void setShopAddressMapLat(Double shopAddressMapLat) {
        this.shopAddressMapLat = shopAddressMapLat;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public Double getOpReviewdAt() {
        return opReviewdAt;
    }

    public void setOpReviewdAt(Double opReviewdAt) {
        this.opReviewdAt = opReviewdAt;
    }
}
