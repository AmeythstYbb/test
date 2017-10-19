package com.zlycare.web.boss_24.core.service.bean;

/**
 * Author : linguodong
 * Create : 2017/8/29
 * Update : 2017/8/29
 * Descriptions :
 */
public class RedPaperBean {
    /**
     * 每个人每天可以领取该活动红包的次数 8
     */
    private Integer timesPerPeoplePerDay;
    /**
     * 触发的红包的金额 3
     */
    private Integer hongbaoValue;
    /**
     * 触发的红包的总数 20
     */
    private Integer hongbaoCount;

    public Integer getTimesPerPeoplePerDay() {
        return timesPerPeoplePerDay;
    }

    public void setTimesPerPeoplePerDay(Integer timesPerPeoplePerDay) {
        this.timesPerPeoplePerDay = timesPerPeoplePerDay;
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
}
