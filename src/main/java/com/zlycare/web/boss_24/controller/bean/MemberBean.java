package com.zlycare.web.boss_24.controller.bean;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/8/4
 * Update : 2017/8/4
 * Descriptions :
 */
public class MemberBean {
    /**
     * 会员姓名
     */
    private String name;
    /**
     * 会员热线号
     */
    private String docChatNum;
    /**
     * 会员手机号
     */
    private String phoneNum;
    /**
     * 会员类型列表
     */
    private List<String> type;

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocChatNum() {
        return docChatNum;
    }

    public void setDocChatNum(String docChatNum) {
        this.docChatNum = docChatNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
