package com.zlycare.web.boss_24.beans.constants;

public enum OrderAllStatus {
	
	RELEASE_DEMAND(0,"发布需求"),
	BUTLER_ASSIGNED(10,"分配顾问"),
	BUTLE_FINISH_DEMAND_ORDER(20,"顾问完成需求订单"),
	CUSTOMER_PAY_EARNEST(40,"客户支付定金"),
	BUTLER_FINISH_RESEARCH_REPORT(50,"顾问完成调研报告"),
	CUSTOMER_COMMIT_RESEARCH_REPORT(60,"客户确认调研报告"),
	BUTLER_EXAMINE_PASS_FIRST_DRAFT(120,"顾问审核初稿通过"),
	CUSTOMER_CHECK_FIRST_DRAFT(130,"客户选中初稿"),
	CUSTOMER_EXAMINE_DESIGN_ONE_FAIL(190,"客户审核深化设计1未通过"),
	CUSTOMER_EXAMINE_DESIGN_TWO_FAIL(230,"客户审核深化设计2未通过"),
	BUTLER_EXAMINE_DESIGN_THREE_FAIL(260,"顾问审核深化设计三未通过"),
	CUSTOMER_FINISH_EVALUATE(280,"客户完成评价"),
	BUTLE_EXAMINE_PASS_SOURCE_FILE(310,"顾问审核源文件通过"),
	PROJECT_COMPLETION(390,"项目完成");
	 /**
     * 状态代码
     */
    private int code;
    /**
     * 状态名称
     */
    private String name;

    OrderAllStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
