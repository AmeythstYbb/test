package com.zlycare.web.boss_24.controller.vo;

import org.springframework.data.annotation.Id;

/**
 * Author : linguodong
 * Create : 2017/8/29
 * Update : 2017/8/29
 * Descriptions : 配置表字典，获取红包规则
 */
public class RedPaperConfigVo {
    /**
     * 自增ID
     */
    @Id
    private String id;
    /**
     * 说明
     */
    private String note;
    /**
     * 具体内容
     */
    private RedPaperConfigFieldVo field;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public RedPaperConfigFieldVo getField() {
        return field;
    }

    public void setField(RedPaperConfigFieldVo field) {
        this.field = field;
    }
}
