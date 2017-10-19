package com.zlycare.web.boss_24.controller.bean;/**
 * Created by zhanglw on 2017/6/7.
 */

import java.io.Serializable;

/**
 * Author : zhanglianwei
 * Create : 2017/6/7 23:18
 * Update : 2017/6/7 23:18
 * Descriptions :
 */
public class PageBean implements Serializable {
    private static final long serialVersionUID = -7081470201468480639L;

    private Integer pageNum = 1;

    private Integer numPerPage= 20;

    private Integer startIndex = 0;

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

    public Integer getStartIndex() {
        if(pageNum!=null&&pageNum>0&&numPerPage!=null){
           startIndex =  (pageNum-1)*numPerPage;
        }

        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
}
