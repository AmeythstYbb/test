package com.zlycare.web.boss_24.core.service.bo;

/**
 * Author : linguodong
 * Create : 2017/8/29
 * Update : 2017/8/29
 * Descriptions :
 */
public class RedPaperConfigFieldBo {
    /**
     * 发动态触发红包活动的有效期
     */
    private Double expiredAt;
    /**
     * 邀请奖励活动有效期
     */
    private Double inviteExpiredAt;
    /**
     * 每天限制触发发红包的最大次数 1000
     */
    private Integer limitPerDay;
    /**
     * 每个人每天可以领取该活动红包的次数 8
     */
    private Integer timesPerPeoplePerDay;
    /**
     * 满设定次数后,邀请者无奖励, 被邀请者有奖励 10
     */
    private Integer inviterRewardLimit;
    /**
     * 触发的红包的金额 3
     */
    private Integer hongbaoValue;
    /**
     * 触发的红包的总数 20
     */
    private Integer hongbaoCount;
    /**
     * 邀请用户成功时奖励的金额 2
     */
    private Integer inviteValue;

    public Double getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Double expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Double getInviteExpiredAt() {
        return inviteExpiredAt;
    }

    public void setInviteExpiredAt(Double inviteExpiredAt) {
        this.inviteExpiredAt = inviteExpiredAt;
    }

    public Integer getLimitPerDay() {
        return limitPerDay;
    }

    public void setLimitPerDay(Integer limitPerDay) {
        this.limitPerDay = limitPerDay;
    }

    public Integer getTimesPerPeoplePerDay() {
        return timesPerPeoplePerDay;
    }

    public void setTimesPerPeoplePerDay(Integer timesPerPeoplePerDay) {
        this.timesPerPeoplePerDay = timesPerPeoplePerDay;
    }

    public Integer getInviterRewardLimit() {
        return inviterRewardLimit;
    }

    public void setInviterRewardLimit(Integer inviterRewardLimit) {
        this.inviterRewardLimit = inviterRewardLimit;
    }

    public Integer getHongbaoValue() {
        return hongbaoValue;
    }

    public void setHongbaoValue(Integer hongbaoValue) {
        this.hongbaoValue = hongbaoValue;
    }

    public Integer getHongbaoCount() {
        return hongbaoCount;
    }

    public void setHongbaoCount(Integer hongbaoCount) {
        this.hongbaoCount = hongbaoCount;
    }

    public Integer getInviteValue() {
        return inviteValue;
    }

    public void setInviteValue(Integer inviteValue) {
        this.inviteValue = inviteValue;
    }
}
