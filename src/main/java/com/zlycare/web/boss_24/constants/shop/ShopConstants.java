package com.zlycare.web.boss_24.constants.shop;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/6/19
 * Update : 2017/6/19
 * Descriptions :
 */
public class ShopConstants {
    public final static List<String> medical = new ArrayList<String>() {{
        add("医疗");
    }};
    public final static List<String> all = new ArrayList<String>() {{
        add("餐饮");
        add("酒店住宿");
        add("商超");
        add("果蔬生鲜");
        add("休闲娱乐");
        add("生活服务");
        add("美体");
        add("其他");
    }};


    public final static Integer SHOP_AUDIT_PASS = 1;
    public final static Integer SHOP_AUDIT_REFUSE = -1;

}
