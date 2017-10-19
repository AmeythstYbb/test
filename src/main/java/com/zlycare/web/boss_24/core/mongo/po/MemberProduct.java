package com.zlycare.web.boss_24.core.mongo.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/6/19
 * Update : 2017/6/19
 * Descriptions : 会员产品
 */
@Document(collection = "vipMemberProducts")
public class MemberProduct {
    /**
     * id
     */
    @Id
    private String id;
    /**
     * 提交人-当前用户的姓名
     */
    private String creator;
    /**
     * 工号-当前用户工号
     */
    private String jobNumber;
    /**
     * 创建时间-提交日期
     */
    private Double createdAt;
    /**
     * 产品厂家名称
     */
    private String productCompanyName;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品类型
     */
    private String type;
    /**
     * 产品子类型
     */
    private String subType;
    /**
     * 产品一句话描述
     */
    private String productDescription;
    /**
     * 产品说明
     */
    private String productDetail;
    /**
     * 图片列表/产品说明配图,存储的是完整路径集合(七牛完整路径)。
     */
    private List<String> productPics;
    /**
     * 厂家对接人
     */
    private String broker;
    /**
     * 厂家对接人联系方式
     */
    private String brokerPhone;
    /**
     * 产品市场价
     */
    private Double marketingPrice;
    /**
     * 朱李叶渠道价
     */
    private Double zlyChannelPrice;
    /**
     * 审核状态 1 通过 0 未审核 -1 未通过
     */
    private Integer status;
    /**
     * 可报销金额/真实金额
     */
    private Double realPrice;
    /**
     * 建议可报销金额（选填）
     */
    private Double adviseRealPrice;
    /**
     * 产品字号（选填）
     */
    private String productCode;
    /**
     * 咨询联系人
     */
    private String servicePeopleName;
    /**
     * 咨询联系人电话
     */
    private String servicePeopleCall;
    /**
     * 咨询联系人id
     */
    private String servicePeopleId;
    /**
     * 咨询联系人IM账号
     */
    private String servicePeopleImUserName;
    /**
     * 咨询联系人热线号
     */
    private String servicePeopleDocChatNum;
    /**
     * 清单类型
     */
    private String vipType;
    /**
     * 是否上线 1 0
     */
    private Integer online;
    /**
     * 拒绝原因
     */
    private String reason;
    /**
     * 修改时间
     */
    private Double updatedAt;
    /**
     * 同步修改时间
     */
    private Double statisticsUpdatedAt;
    /**
     * 删除标识 默认false;
     */
    private boolean isDeleted;
    /**
     * 第三级目录ID
     */
    private String thirdType;
    /**
     * 可销售区域集合
     */
    private List<String> productSalesArea;
    /**
     * 上/下线备注
     */
    private String onlineRemark;

    public String getOnlineRemark() {
        return onlineRemark;
    }

    public void setOnlineRemark(String onlineRemark) {
        this.onlineRemark = onlineRemark;
    }

    public String getThirdType() {
        return thirdType;
    }

    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
    }

    public List<String> getProductSalesArea() {
        return productSalesArea;
    }

    public void setProductSalesArea(List<String> productSalesArea) {
        this.productSalesArea = productSalesArea;
    }

    public Double getAdviseRealPrice() {
        return adviseRealPrice;
    }

    public void setAdviseRealPrice(Double adviseRealPrice) {
        this.adviseRealPrice = adviseRealPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getServicePeopleDocChatNum() {
        return servicePeopleDocChatNum;
    }

    public void setServicePeopleDocChatNum(String servicePeopleDocChatNum) {
        this.servicePeopleDocChatNum = servicePeopleDocChatNum;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Double getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Double createdAt) {
        this.createdAt = createdAt;
    }

    public String getProductCompanyName() {
        return productCompanyName;
    }

    public void setProductCompanyName(String productCompanyName) {
        this.productCompanyName = productCompanyName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public List<String> getProductPics() {
        return productPics;
    }

    public void setProductPics(List<String> productPics) {
        this.productPics = productPics;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getBrokerPhone() {
        return brokerPhone;
    }

    public void setBrokerPhone(String brokerPhone) {
        this.brokerPhone = brokerPhone;
    }

    public Double getMarketingPrice() {
        return marketingPrice;
    }

    public void setMarketingPrice(Double marketingPrice) {
        this.marketingPrice = marketingPrice;
    }

    public Double getZlyChannelPrice() {
        return zlyChannelPrice;
    }

    public void setZlyChannelPrice(Double zlyChannelPrice) {
        this.zlyChannelPrice = zlyChannelPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

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

    public String getServicePeopleId() {
        return servicePeopleId;
    }

    public void setServicePeopleId(String servicePeopleId) {
        this.servicePeopleId = servicePeopleId;
    }

    public String getServicePeopleImUserName() {
        return servicePeopleImUserName;
    }

    public void setServicePeopleImUserName(String servicePeopleImUserName) {
        this.servicePeopleImUserName = servicePeopleImUserName;
    }

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Double updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Double getStatisticsUpdatedAt() {
        return statisticsUpdatedAt;
    }

    public void setStatisticsUpdatedAt(Double statisticsUpdatedAt) {
        this.statisticsUpdatedAt = statisticsUpdatedAt;
    }


}
