package com.zlycare.web.boss_24.beans.constants;

public enum CustomerOrderUrl {
	
	C_ORDER_LIST_URL(10,"/order/index/get");
	 /**
     * 状态代码
     */
    private int code;
    /**
     * 状态名称
     */
    private String name;

    CustomerOrderUrl(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public String getCode(int code) {
        return name;
    }
    
    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
