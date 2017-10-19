package com.zlycare.web.boss_24.utils.core;

import java.util.List;

/**
 * 分页参数
 *
 * @author DaiJian
 * @create 2015/12/15 14:02
 */
public class Page<T> {
    /**
     * Constructor
     *
     * @param data
     * @param size
     * @param total
     */
    public Page(int start, int size, int total, List<T> data) {
        this.start = start;
        this.data = data;
        this.size = size;
        this.total = total;
        this.pageTotal = (int) Math.ceil(1D * this.total / this.size);
        this.setPageNo();
    }

    /**
     * 查询开始数
     */
    private int start = 0;
    /**
     * 当前页码数
     */
    private int pageNo;
    /**
     * 结果集
     */
    private List<T> data;
    /**
     * 当前页查询数量
     */
    private int size = 10;
    /**
     * 查询的总页数
     */
    private int pageTotal;
    /**
     * 总记录数
     */
    private int total;

    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页码数
     */
    public void setPageNo() {
        this.pageNo = this.start / this.size + 1;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public int getPageTotal() {
        return pageTotal;
    }
}
