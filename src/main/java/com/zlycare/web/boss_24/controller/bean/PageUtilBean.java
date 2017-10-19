package com.zlycare.web.boss_24.controller.bean;

/**
 * Author : linguodong
 * Create : 2017/6/29
 * Update : 2017/6/29
 * Descriptions :
 */
public class PageUtilBean {
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
}
