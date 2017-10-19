package com.zlycare.web.boss_24.core.service.bo;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Sulong on 2017/10/12.
 */
public class FinancialReviewBo {
    /**
     * id
     */
    @Id
    private String id;
    /**
     * 申请时间
     */
    private Double createdAt;
    /**
     * 提现人姓名(app)
     */
    private String applicantName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    private String applicantPhone;

    public String getApplicantPhone() {
        return applicantPhone;
    }

    /**
     * 身份证信息
     */
    private String sid;

    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime (Date createTime){

        this.createTime = createTime;
    }

    /**

     * 支付宝姓名
     */
    private String alipayName;
    /**
     * 支付宝账号
     */
    private String alipayNum;
    /**
     * 银行账户名
     */
    private String bankCardName;
    /**
     * 地区
     */
    private String area;
    /**
     * 银行
     */
    private String bankName;
    /**
     * 支行
     */
    private String subBankName;
    /**
     * 银行卡号
     */
    private String bankCardNum;
    /**
     * 金额
     */
    private Double cash;

    public String getExcelstatus() {
        return excelstatus;
    }

    /**
     * 解析exce中的status
     */
    private String excelstatus;

    public void setExcelstatus(String excelstatic) {
        this.excelstatus = excelstatic;
    }

    /**
     * 状态
     *status ：0 新建申请; 1 待处理;   －1 系统拒绝;
     *                     2 运营批准; -2 运营拒绝;
     *                     3 财务确认; -3 财务拒绝;
     */
    private Integer status;
    /**
     * 备注
     */
    private String reason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Double createdAt) {
        this.createdAt = createdAt;
    }


    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getAlipayName() {
        return alipayName;
    }

    public void setAlipayName(String alipayName) {
        this.alipayName = alipayName;
    }

    public String getAlipayNum() {
        return alipayNum;
    }

    public void setAlipayNum(String alipayNum) {
        this.alipayNum = alipayNum;
    }

    public String getBankCardName() {
        return bankCardName;
    }

    public void setBankCardName(String bankCardName) {
        this.bankCardName = bankCardName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSubBankName() {
        return subBankName;
    }

    public void setSubBankName(String subBankName) {
        this.subBankName = subBankName;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
