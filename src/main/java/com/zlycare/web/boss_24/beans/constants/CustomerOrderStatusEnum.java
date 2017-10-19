package com.zlycare.web.boss_24.beans.constants;

/**
 * 客户订单状态对应关系
 * @author : xiaoyouyuan
 * @date : 2016-07-27 
 */
public enum CustomerOrderStatusEnum {
	/**
	 * 发布需求
	 */
	ISSUE_REQUIREMENTS("issueRequirements", 1),

	/**
	* 待确认订单状态
	*/
	CONFIRM_ORDER("confirmOrder", 2),
	/**
	 * 支付定金
	 */
	PAY_DEPOSIT("payDeposit", 3),
	/**
	 * 深化调研
	 */
	DEEP_RESEARCH("deepResearch", 4),
	/**
	 * 支付首款
	 */
	PAY_FIRST("payFirst", 5),
	/**
	 * 确认初稿
	 */
	CONFIRM_DRAFT("confirmDraft", 6),
	/**
	 * 支付中款
	 */
	PAY_SECOND("paySecond", 7),
	/**
	 * 深化设计
	 */
	DEEP_SOLUTION("deepSolution", 8),
	/**
	 * 评价订单
	 */
	PAY_LAST("payLast", 9),
	/**
	 * 交付源文件
	 */
	FINAL_FILE("finalFile", 10);
	/**
	 * 节点key
	 */
	private String nodeKey;
	/**
	 * 节点id
	 */
	private int id;

	CustomerOrderStatusEnum(String nodeKey, int id) {
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
