package com.zlycare.web.boss_24.core.mongo.bean;

import org.springframework.data.annotation.Id;

/**
 * Author : linguodong
 * Create : 2017/7/17
 * Update : 2017/7/17
 * Descriptions : 核销记录实体
 */
public class VerificationRecordBean {
    /**
     * 起始值
     */
    private Integer start;
    /**
     * 条数
     */
    private Integer pageSize;
    /**
     * 开始时间
     */
    private Long startDate;
    /**
     * 结束时间
     */
    private Long endDate;
    /**
     * id
     */
    @Id
    private String id;
    /**
     * 核销发起人id
     */
    private String userId;
    /**
     * 会员类型
     */
    private String vipType;
    /**
     * 记录类型 use 是核销记录，buy 是购买会员额度记录
     */
    private String type;
    /**
     * 产品市场价
     */
    private Double marketingPrice;
    /**
     * 报销价格 or 购买到的会员额度
     */
    private Double value;
    /**
     * 产品id
     */
    private String productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 创建时间 下单时间
     */
    private Double createdAt;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户热线号
     */
    private String docChatNum;
    /**
     * 用户手机号
     */
    private String phoneNum;
    /**
     * 核销状态 true 核销
     */
    private Integer isChecked;
    /**
     * 核销状态 1 true 0 false
     */
    private String checked_status;
    /**
     * 核销人id
     */
    private String checkVenderId;
    /**
     * 核销人姓名
     */
    private String checkVenderName;
    /**
     * 核销人热线号
     */
    private String checkVenderNum;
    /**
     * 核销人手机号
     */
    private String checkVenderPhone;

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

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMarketingPrice() {
        return marketingPrice;
    }

    public void setMarketingPrice(Double marketingPrice) {
        this.marketingPrice = marketingPrice;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Double createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocChatNum() {
        return docChatNum;
    }

    public void setDocChatNum(String docChatNum) {
        this.docChatNum = docChatNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer isChecked() {
        return isChecked;
    }

    public void setChecked(Integer checked) {
        isChecked = checked;
    }

    public String getCheckVenderId() {
        return checkVenderId;
    }

    public void setCheckVenderId(String checkVenderId) {
        this.checkVenderId = checkVenderId;
    }

    public String getCheckVenderName() {
        return checkVenderName;
    }

    public void setCheckVenderName(String checkVenderName) {
        this.checkVenderName = checkVenderName;
    }

    public String getCheckVenderNum() {
        return checkVenderNum;
    }

    public void setCheckVenderNum(String checkVenderNum) {
        this.checkVenderNum = checkVenderNum;
    }

    public String getCheckVenderPhone() {
        return checkVenderPhone;
    }

    public void setCheckVenderPhone(String checkVenderPhone) {
        this.checkVenderPhone = checkVenderPhone;
    }

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

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getChecked_status() {
        return checked_status;
    }

    public void setChecked_status(String checked_status) {
        this.checked_status = checked_status;
    }
}
