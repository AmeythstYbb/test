package com.zlycare.web.boss_24.utils.core.bean;

import com.zlycare.web.boss_24.utils.core.constants.OrderFileTypeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 订单文件参数bean
 * @author lufanglong
 * @date 2016-07-12 15:57
 */
public class OrderFileBean {
    /**
     * 注解文件路径
     */
    private String annotationFilePath;
    /**
     * 文件后缀
     */
    private String suffix;
    /**
     * 订单ID
     */
    private Integer orderId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单创建时间
     */
    private Date orderCreateTime;
    /**
     * 订单名称
     */
    private String orderName;
    /**
     * 方案ID
     */
//    private Integer solutionId;
    /**
     * 方案序号
     */
    private Integer solutionNo;
    /**
     * 源文件序号(文件上传临时表ID)
     */
    private Integer sourceFileNo;
    /**
     * 订单文件类型
     */
    private OrderFileTypeEnum orderFileTypeEnum;
    /**
     * 提交次数
     */
    private Integer submitCount;

    public String getAnnotationFilePath() {
        return annotationFilePath;
    }

    public void setAnnotationFilePath(String annotationFilePath) {
        this.annotationFilePath = annotationFilePath;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

//    public Integer getSolutionId() {
//        return solutionId;
//    }
//
//    public void setSolutionId(Integer solutionId) {
//        this.solutionId = solutionId;
//    }

    public Integer getSolutionNo() {
        return solutionNo;
    }

    public void setSolutionNo(Integer solutionNo) {
        this.solutionNo = solutionNo;
    }

    public Integer getSourceFileNo() {
        return sourceFileNo;
    }

    public void setSourceFileNo(Integer sourceFileNo) {
        this.sourceFileNo = sourceFileNo;
    }

    public OrderFileTypeEnum getOrderFileTypeEnum() {
        return orderFileTypeEnum;
    }

    public void setOrderFileTypeEnum(OrderFileTypeEnum orderFileTypeEnum) {
        this.orderFileTypeEnum = orderFileTypeEnum;
    }

    public Integer getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(Integer submitCount) {
        this.submitCount = submitCount;
    }

    public boolean check() {
        //如果有任何一个为空,返回false
        if (StringUtils.isEmpty(annotationFilePath) || StringUtils.isEmpty(suffix) || orderId == null
                || orderCreateTime == null || StringUtils.isEmpty(orderName) || solutionNo == null
                || orderFileTypeEnum == null || submitCount == null) {
//        if (StringUtils.isEmpty(annotationFilePath) || StringUtils.isEmpty(suffix) || orderId == null || orderCreateTime == null
//                || StringUtils.isEmpty(orderName) || solutionId == null || solutionNo == null
//                || orderFileTypeEnum == null || submitCount == null) {
            return false;
        } else {
            return true;
        }
    }
}
