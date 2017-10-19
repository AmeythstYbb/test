package com.zlycare.web.boss_24.core.service.bo.member;

import org.springframework.data.annotation.Id;

/**
 * Author : linguodong
 * Create : 2017/8/4
 * Update : 2017/8/4
 * Descriptions : 会员列表封装后Bo,不对应数据库实体表，
 */
public class MemberBo {
    /**
     * id
     */
    @Id
    private String id;
    /**
     * 会员姓名
     */
    private String name;
    /**
     * 会员性别
     */
    private String sex;
    /**
     * 会员热线号
     */
    private String docChatNum;
    /**
     * 会员手机号
     */
    private String phoneNum;
    /**
     * 高级会员总额度
     */
    private Double advancedTotalVal;
    /**
     * 高级会员剩余额度
     */
    private Double advancedBalance;
    /**
     * VIP会员总额度
     */
    private Double vipTotalVal;
    /**
     * VIP会员剩余额度
     */
    private Double vipBalance;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 当前数据可用额度
     */
    private Double balance;
    /**
     * 当前数据总额度
     */
    private Double totalVal;
    /**
     * 会员类型 高级、vip、全城购
     */
    private String type;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getTotalVal() {
        return totalVal;
    }

    public void setTotalVal(Double totalVal) {
        this.totalVal = totalVal;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
