package com.zlycare.web.boss_24.core.mongo.po;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/6/20
 * Update : 2017/6/20
 * Descriptions : mongo 业务表用户实体
 */
public class Users {
    /**
     * id
     */
    @Id
    private String id;
    /**
     * 申请商户审核状态
     */
    private Integer shopVenderApplyStatus;
    /**
     * 用户商户所在城市
     */
    private String shopCity;
    /**
     * 用户商户名称
     */
    private String shopName;
    /**
     * 用户商户地址
     */
    private String shopAddress;
    /**
     * 用户商户类型
     */
    private String shopType;
    /**
     * 用户商户电话
     */
    private String shopPhoneNum;
    /**
     * 用户商户头像
     */
    private String shopAvatar;
    /**
     * 用户营业执照
     */
    private String shopLicense;
    /**
     * 用户子类型
     */
    private String shopSubType;
    /**
     * 是否时商户?
     */
    private boolean isVender;
    /**
     * 经度
     */
    private Double shopAddressMapLon;
    /**
     * 纬度
     */
    private Double shopAddressMapLat;
    /**
     * 经纬度
     */
    private List<Double> shopLocation;
    /**
     * 姓名
     */
    private String name;
    /**
     * 热线号
     */
    private String docChatNum;
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 性别
     */
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDocChatNum() {
        return docChatNum;
    }

    public void setDocChatNum(String docChatNum) {
        this.docChatNum = docChatNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getShopVenderApplyStatus() {
        return shopVenderApplyStatus;
    }

    public void setShopVenderApplyStatus(Integer shopVenderApplyStatus) {
        this.shopVenderApplyStatus = shopVenderApplyStatus;
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

    public boolean isVender() {
        return isVender;
    }

    public void setVender(boolean vender) {
        isVender = vender;
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

    public List<Double> getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(List<Double> shopLocation) {
        this.shopLocation = shopLocation;
    }
    //    public static void main(String[] args) {
//         Double[] shopLocation = new Double[]{1D,2D};
//        System.out.println(shopLocation.toString());
//    }

}
