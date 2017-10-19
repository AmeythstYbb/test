package com.zlycare.web.boss_24.beans.constants;

/**
 * pc端time line uri 类型
 *
 * @author DaiJian
 * @create 2016/6/20 11:03
 */
public enum PcTimeLineNodeUriTypeEnum {
    /**
     * 顾问
     */
    PC_KEEPER(1),
    /**
     * 创意人
     */
    PC_CP(2),
    /**
     * 客户
     */
    PC_CUSTOMER(3);
    private int type;

    PcTimeLineNodeUriTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
