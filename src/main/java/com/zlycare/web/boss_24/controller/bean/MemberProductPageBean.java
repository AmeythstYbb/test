package com.zlycare.web.boss_24.controller.bean;

/**
 * Author : linguodong
 * Create : 2017/6/29
 * Update : 2017/6/29
 * Descriptions :
 */
public class MemberProductPageBean {
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 每页行数
     */
    private Integer numPerPage;
    /**
     * 开始时间
     */
    private String startDate;
    /**
     * 结束时间
     */
    private String endDate;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 审核状态
     */
    private String status;
    /**
     * 一级类目
     */
    private String type;
    /**
     * 二级类目
     */
    private String subType;
    /**
     * 产品所属专区
     */
    private String vipType;
    /**
     * 三级目录
     */
    private String thirdType;
    /**
     * 二级目录
     */
    private String secondType;
    /**
     * 一级目录
     */
    private String firstType;

    public String getSecondType() {
        return secondType;
    }

    public void setSecondType(String secondType) {
        this.secondType = secondType;
    }

    public String getFirstType() {
        return firstType;
    }

    public void setFirstType(String firstType) {
        this.firstType = firstType;
    }

    public String getThirdType() {
        return thirdType;
    }

    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
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

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(Integer numPerPage) {
        this.numPerPage = numPerPage;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
