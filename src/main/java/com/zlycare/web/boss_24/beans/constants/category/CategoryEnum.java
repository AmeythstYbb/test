package com.zlycare.web.boss_24.beans.constants.category;

/**
 * Package Name: com.dongdaodao.beans.constants.category
 * Author: WuZheng
 * Create Date: 2017/1/24 10:57
 * Description: 对应Mongo字典表Category里的业务领域分类
 */
public enum CategoryEnum {

    BRAND_STRATEGY(1, "品牌策略"), //大类
    ENTITLE(51, "命名"),

    BRAND_DESIGN(2, "品牌设计"), //大类
    LOGO(9, "标志"),
    VI(10, "VI"),
    MASCOT(14, "吉祥物"),

    BRAND_COMMUNICATION(3, "品牌传播"), //大类
    BRAND_COMMUNICATION_SUB(57, "品牌传播"),

    PRODUCT_AND_PACKAGE(5, "产品和包装设计"), //大类
    PACAKGE_DESIGN(13, "包装"),
    PRODUCT_DESIGN(15, "产品设计"),
    APPEARANCE_DESIGN(46, "外观设计"),

    ADVERTISEMENT(8, "广告"), //大类
    IMAGE_AND_MATERIAL(11, "形象及物料"),
    ALBUM(12, "画册"),

    ILLUSTRATION(52, "插画"), //大类
    ILLUSTRATION_SUB(53, "插画"),

    INDUSTRIAL_DESIGN(54, "工业设计"), //大类
    INDUSTRIAL_DESIGN_SUB(55, "工业设计");

    CategoryEnum(int id, String depiction) {
        this.id = id;
        this.depiction = depiction;
    }

    private int id;
    private String depiction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepiction() {
        return depiction;
    }

    public void setDepiction(String depiction) {
        this.depiction = depiction;
    }
}
