package com.zlycare.web.boss_24.constants.memberProduct;


/**
 * Author : linguodong
 * Create : 2017/6/19
 * Update : 2017/6/19
 * Descriptions : 咨询人信息枚举，之后使用字典。
 */
public enum ProductEnum {

    /**
     * 未申请
     */
    DEFAULT("朱李叶健康会员服务助理", "17301199731", "5941cc0cc94d071410661857", "587595ac9fa133923a59d8e7", "801010866"),;
    /**
     * 咨询联系人
     */
    private String servicePeopleName;
    /**
     * 联系电话
     */
    private String servicePeopleCall;
    /**
     * 咨询联系人 im
     */
    private String servicePeopleImUserName;
    /**
     * 咨询人id
     */
    private String servicePeopleId;
    /**
     * 咨询人热线号
     */
    private String servicePeopleDocChatNum;

    public String getServicePeopleName() {
        return servicePeopleName;
    }

    public void setServicePeopleName(String servicePeopleName) {
        this.servicePeopleName = servicePeopleName;
    }

    public String getServicePeopleCall() {
        return servicePeopleCall;
    }

    public void setServicePeopleCall(String servicePeopleCall) {
        this.servicePeopleCall = servicePeopleCall;
    }

    public String getServicePeopleImUserName() {
        return servicePeopleImUserName;
    }

    public void setServicePeopleImUserName(String servicePeopleImUserName) {
        this.servicePeopleImUserName = servicePeopleImUserName;
    }

    public String getServicePeopleId() {
        return servicePeopleId;
    }

    public void setServicePeopleId(String servicePeopleId) {
        this.servicePeopleId = servicePeopleId;
    }

    public String getServicePeopleDocChatNum() {
        return servicePeopleDocChatNum;
    }

    public void setServicePeopleDocChatNum(String servicePeopleDocChatNum) {
        this.servicePeopleDocChatNum = servicePeopleDocChatNum;
    }

    ProductEnum(String servicePeopleName, String servicePeopleCall, String servicePeopleImUserName, String servicePeopleId, String servicePeopleDocChatNum) {
        this.servicePeopleName = servicePeopleName;
        this.servicePeopleCall = servicePeopleCall;
        this.servicePeopleImUserName = servicePeopleImUserName;
        this.servicePeopleId = servicePeopleId;
        this.servicePeopleDocChatNum = servicePeopleDocChatNum;
    }
}
