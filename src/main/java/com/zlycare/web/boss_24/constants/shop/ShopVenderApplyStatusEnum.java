package com.zlycare.web.boss_24.constants.shop;

/**
 * Author : linguodong
 * Create : 2017/6/20
 * Update : 2017/6/20
 * Descriptions :
 */
public enum ShopVenderApplyStatusEnum {

    // shopVenderApplyStatus : {type : Number, default: 0 },//0-未申请 1-申请中 2-拒绝 3-通过 4-再次申请 5-再次拒绝

    /**
     * 未申请
     */
    NOT_APPLICATION(0, "未申请"),
    /**
     * 申请中
     */
    APPLICATION_ING(1, "申请中"),
    /**
     * 拒绝
     */
    APPLICATION_REFUSE(2, "拒绝"),
    /**
     * 通过
     */
    APPLICATION_PASS(3, "通过"),
    /**
     * 再次申请
     */
    APPLICATION_AGAIN_ING(4, "再次申请"),
    /**
     * 再次拒绝
     */
    APPLICATION_AGAIN_REFUSE(5, "再次拒绝"),;
    /**
     * 状态码
     */
    private int key;
    /**
     * 状态描述
     */
    private String value;

    ShopVenderApplyStatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
