package com.zlycare.web.boss_24.beans.constants;

/**
 * 创意人客户端订单状态对应关系
 *
 * @author DaiJian
 * @create 2016/6/21 14:18
 */
public enum CpClientOrderStatusEnum {
    /**
     * 待确认订单状态
     */
    CONFIRM_ORDER("confirmOrder", 1),
    /**
     * 待提供初稿状态
     */
    FIRST_SOLUTION("firstSolution", 2),
    /**
     * 待深化设计状态
     */
    DEEP_SOLUTION("deepSolution", 3),
    /**
     * 待提交源文件状态
     */
    FINAL_FILE("finalFile", 4),
    /**
     * 待评价订单状态
     */
    EVALUATION("evaluation", 5),
    /**
     * 已完成订单
     */
    FINISH_ORDER("finishOrder", 6),
    /**
     * 已取消订单
     */
    CANCELED_ORDER("canceledOrder", 7),
    /**
     * 未中标订单
     */
    UNSELECTED_ORDER("unselectedOrder", 8);
    /**
     * 节点key
     */
    private String nodeKey;
    /**
     * 节点id
     */
    private int id;

    CpClientOrderStatusEnum(String nodeKey, int id) {
        this.nodeKey = nodeKey;
        this.id = id;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public int getId() {
        return id;
    }
}
