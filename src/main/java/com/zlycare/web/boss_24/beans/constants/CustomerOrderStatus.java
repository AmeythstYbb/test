package com.zlycare.web.boss_24.beans.constants;

public enum CustomerOrderStatus {
	
	C_ASSIGN_KEEPER(0,"待分配顾问"),
	C_ORDER_COMMIT(5,"待确认订单"),
	C_PAY_EARNEST(10,"待支付定金"),
	C_SURVEY(15,"待深化调研"),
	C_FIST_AMOUNT(25,"待支付首款"),
	C_FIRST_DRAFT(30,"待确定初稿"),
	C_PAY_TWO_AMOUNT(40,"待支付中款"),
	C_DETAILED_DESIGN_ONE(45,"深化设计"),
	C_DETAILED_DESIGN_TWO(75,"深化设计"),
	C_DETAILED_DESIGN_THREE(80,"深化设计"),
	C_FOR_PAYMENT(90,"待评价结款"),
	C_DELIVER(95,"待交付源文件"),
	C_END(100,"已完成"),
	C_CANCEL(0,"已取消");
	 /**
     * 状态代码
     */
    private int code;
    /**
     * 状态名称
     */
    private String name;

    CustomerOrderStatus(int code, String name) {
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
