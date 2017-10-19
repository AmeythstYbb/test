package com.zlycare.web.boss_24.core.mongo.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author : linguodong
 * Create : 2017/8/4
 * Update : 2017/8/4
 * Descriptions : 会员数据实体表
 */
@Document(collection = "memberships")
public class MemberShips {
    /**
     * 自增ID
     */
    @Id
    private String id;
    /**
     * 会员类型 高级、vip、全城购
     */
    private String type;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 数据来源
     */
    private String source;
    /**
     * 过期时间
     */
    private Double expiredAt;
    /**
     * 生效时间
     */
    private Double validAt;
    /**
     * 会员卡No
     */
    private String cardNo;
    /**
     * 当前数据可用额度
     */
    private Double balance;
    /**
     * 当前数据消耗额度
     */
    private Double cost;
    /**
     * 当前数据总额度
     */
    private Double totalVal;
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
    // 聚合后字段
    /**
     * 高级会员总额度
     */
    private Double advancedTotalVal;
    /**
     * 高级会员剩余额度
     */
    private Double advancedBalance;
    /**
     * VIP会员总额度，暂不使用
     */
    private Double vipTotalVal;
    /**
     * VIP会员剩余额度，暂不使用
     */
    private Double vipBalance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Double getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Double expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Double getValidAt() {
        return validAt;
    }

    public void setValidAt(Double validAt) {
        this.validAt = validAt;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getTotalVal() {
        return totalVal;
    }

    public void setTotalVal(Double totalVal) {
        this.totalVal = totalVal;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Double getAdvancedTotalVal() {
        return advancedTotalVal;
    }

    public void setAdvancedTotalVal(Double advancedTotalVal) {
        this.advancedTotalVal = advancedTotalVal;
    }

    public Double getAdvancedBalance() {
        return advancedBalance;
    }

    public void setAdvancedBalance(Double advancedBalance) {
        this.advancedBalance = advancedBalance;
    }

    public Double getVipTotalVal() {
        return vipTotalVal;
    }

    public void setVipTotalVal(Double vipTotalVal) {
        this.vipTotalVal = vipTotalVal;
    }

    public Double getVipBalance() {
        return vipBalance;
    }

    public void setVipBalance(Double vipBalance) {
        this.vipBalance = vipBalance;
    }
}
