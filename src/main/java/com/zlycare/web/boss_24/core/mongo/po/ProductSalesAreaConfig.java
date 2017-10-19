package com.zlycare.web.boss_24.core.mongo.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Author : linguodong
 * Create : 2017/7/27
 * Update : 2017/7/27
 * Descriptions : 配置表字典，获取可销售区域
 */
@Document(collection = "configs")
public class ProductSalesAreaConfig {
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
    private ProductSalesAreaConfigField field;

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

    public ProductSalesAreaConfigField getField() {
        return field;
    }

    public void setField(ProductSalesAreaConfigField field) {
        this.field = field;
    }
}
