package com.zlycare.web.boss_24.beans.constants;

/**
 * Descriptions : 审核状态
 * Author : kaihua
 * Create : 2016/1/28 19:55
 * Update : 2016/1/28 19:55
 */
public enum UserAuditStatus {

    /**
     * 未提交资料
     */
    UNCOMMITTED_INFORMATION(1),
    /**
     * 未审核
     */
    NOT_AUDIT(2),
    /**
     * 审核通过
     */
    PASS_AUDIT(3),
    /**
     * 审核未通过
     */
    FAILED_AUDIT(4);

    private int value;
    
    UserAuditStatus(int value) {
        this.value = value;
    }
     /**
     * 校验{@code auditStatus 是否有效}
     *
     * @param auditStatus 要校验的状态参数
     * @return boolean
     */
    public static boolean checkStatus(Integer auditStatus) {
        if (auditStatus == null) {
            return false;
        }
        return !(auditStatus != UNCOMMITTED_INFORMATION.getValue() &&
                auditStatus != NOT_AUDIT.getValue() &&
                auditStatus != PASS_AUDIT.getValue() &&
                auditStatus != FAILED_AUDIT.getValue());
    }
    
    public static UserAuditStatus getAuditStatus(Integer auditStatus) {
        if (auditStatus == null) {
                return null;
            }
        if (auditStatus.intValue() == UNCOMMITTED_INFORMATION.getValue()) {
            return UNCOMMITTED_INFORMATION;
        } else if (auditStatus.intValue() == NOT_AUDIT.getValue()) {
            return NOT_AUDIT;
        } else if (auditStatus.intValue() == FAILED_AUDIT.getValue()) {
            return FAILED_AUDIT;
        } else if (auditStatus.intValue() == PASS_AUDIT.getValue()) {
            return PASS_AUDIT;
        }
        return null;
    }
     public int getValue() {
        return this.value;
    }

    public String getName() {
        if (this.value == PASS_AUDIT.getValue()) {
            return "已认证";
        }
        return "未认证";
    }

}
