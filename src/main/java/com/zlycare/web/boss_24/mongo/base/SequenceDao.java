package com.zlycare.web.boss_24.mongo.base;

import com.zlycare.web.boss_24.mongo.enums.CollectionsEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author lufanglong
 * @author DaiJian
 * @date 2015-11-20 22:03
 * @Description 自增序列Dao
 */
@Repository
public class SequenceDao extends SequenceBaseDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /********以下是字典和规则表相关**********/
    /**
     * 获取订单Time line节点信息id
     *
     * @return Integer
     */
    public Integer getOrderTimeLineTitleSeqId() {
        return super.getSequenceId(CollectionsEnum.ORDER_TIME_LINE_TITLE.getName());
    }

    /**
     * 顾问订单timeline节点信息id
     *
     * @return
     */
    public Integer getKeeperOrderTimeLineTitleSeqId() {
        return super.getSequenceId(CollectionsEnum.KEEPER_ORDER_TIME_LINE_TITLE.getName());
    }

    /**
     * 创意人订单timeline节点信息id
     *
     * @return
     */
    public Integer getCpOrderTimeLineTitleSeqId() {
        return super.getSequenceId(CollectionsEnum.CP_ORDER_TIME_LINE_TITLE.getName());
    }

    /**
     * 获得客户端显示状态名称id
     *
     * @return Integer
     */
    public Integer getClientOrderStatusSeqId() {
        return super.getSequenceId(CollectionsEnum.CLIENT_ORDER_STATUS.getName(), 10);
    }

    /**
     * 获得客户端显示状态名称id
     *
     * @return Integer
     */
    public Integer getCpClientOrderStatusSeqId() {
        return super.getSequenceId(CollectionsEnum.CP_CLIENT_ORDER_STATUS.getName(), 10);
    }

    /**
     * 获取顾问等级序列Id
     *
     * @return Integer
     */
    public Integer getKeeperGradeSeqId() {
        return super.getSequenceId(CollectionsEnum.KEEPER_GRADE.getName());
    }

    /**
     * 获取分类表序列ID
     *
     * @return Integer
     */
    public Integer getCategorySeqId() {
        return super.getSequenceId(CollectionsEnum.CATEGORY.getName());
    }

    /**
     * 获取支付规则表序列ID
     *
     * @return Integer
     */
    public Integer getPaymentRuleSeqId() {
        return super.getSequenceId(CollectionsEnum.PAYMENT_RULE.getName());
    }

    /**
     * 获取客户等级表序列ID
     *
     * @return Integer
     */
    public Integer getCustomerGradeSeqId() {
        return super.getSequenceId(CollectionsEnum.CUSTOMER_GRADE.getName());
    }

    /**
     * 获取地区序列ID
     *
     * @return Integer
     */
    public Integer getAreaSeqId() {
        return super.getSequenceId(CollectionsEnum.AREA.getName());
    }

    /**
     * 反馈记录序列ID
     *
     * @return
     */
    public Integer getFeedbackSeqId() {
        return super.getSequenceId(CollectionsEnum.FEEDBACK.getName());
    }

    /**
     * 获取验证码类型序列ID
     *
     * @return Integer
     */
    public Integer getCaptchaTypeSeqId() {
        return super.getSequenceId(CollectionsEnum.CAPTCHA_TYPE.getName());
    }

    /**
     * 获取企业年营业额序列ID
     *
     * @return Integer
     */
    public Integer getEnterpriseIncomeSeqId() {
        return super.getSequenceId(CollectionsEnum.ENTERPRISE_INCOME.getName());
    }

    /**
     * 企业注册资金
     *
     * @return
     */
    public Integer getEnterpriseRegisteredCapitalSeqId() {
        return super.getSequenceId(CollectionsEnum.ENTERPRISE_REGISTERED_CAPITAL.getName());
    }

    /**
     * 获取企业行业序列ID
     *
     * @return Integer
     */
    public Integer getEnterpriseIndustrySeqId() {
        return super.getSequenceId(CollectionsEnum.ENTERPRISE_INDUSTRY.getName());
    }

    /**
     * 获取企业员工数量序列ID
     *
     * @return Integer
     */
    public Integer getEnterpriseStaffTotalSeqId() {
        return super.getSequenceId(CollectionsEnum.ENTERPRISE_STAFF_TOTAL.getName());
    }

    /**
     * 获取企业类型序列ID
     *
     * @return Integer
     */
    public Integer getEnterpriseTypeSeqId() {
        return super.getSequenceId(CollectionsEnum.ENTERPRISE_TYPE.getName());
    }

    /**
     * 获取企业员工从业年限序列ID
     *
     * @return Integer
     */
    public Integer getEnterpriseStaffYearsWorkingSeqId() {
        return super.getSequenceId(CollectionsEnum.ENTERPRISE_STAFF_YEARS_WORKING.getName());
    }

    /**
     * 获取积分赠送规则序列ID
     *
     * @return Integer
     */
    public Integer getGiftGrantRuleSeqId() {
        return super.getSequenceId(CollectionsEnum.GIFT_GRANT_RULE.getName());
    }

    /**
     * 获取积分赠送类型序列ID
     *
     * @return Integer
     */
    public Integer getGiftTypeSeqId() {
        return super.getSequenceId(CollectionsEnum.GIFT_TYPE.getName());
    }

    /**
     * 获取开票类型序列ID
     *
     * @return Integer
     */
    public Integer getInvoiceCategorySeqId() {
        return super.getSequenceId(CollectionsEnum.INVOICE_CATEGORY.getName());
    }

    /**
     * 获取发票类型序列ID
     *
     * @return Integer
     */
    public Integer getInvoiceTypeSeqId() {
        return super.getSequenceId(CollectionsEnum.INVOICE_TYPE.getName());
    }

    /**
     * 获取订单评价计算规则序列ID
     *
     * @return Integer
     */
    public Integer getOrderEvaluationRuleSeqId() {
        return super.getSequenceId(CollectionsEnum.ORDER_EVALUATION_RULE.getName());

    }

    /**
     * 获取订单评价类型序列ID
     *
     * @return Integer
     */
    public Integer getOrderEvaluationTypeSeqId() {
        return super.getSequenceId(CollectionsEnum.ORDER_EVALUATION_TYPE.getName());
    }

    /**
     * 获取订单状态序列ID
     *
     * @return Integer
     */
    public Integer getOrderStatusSeqId() {
        return super.getSequenceId(CollectionsEnum.ORDER_STATUS.getName(), 10).intValue();
    }

    /**
     * 获取创意人等级序列ID
     *
     * @return Integer
     */
    public Integer getSupplierGradeSeqId() {
        return super.getSequenceId(CollectionsEnum.SUPPLIER_GRADE.getName());
    }

    /**
     * 获取创意人级别序列ID
     *
     * @return Integer
     */
    public Integer getSupplierLevelSeqId() {
        return super.getSequenceId(CollectionsEnum.SUPPLIER_LEVEL.getName());
    }

    /**
     * 获取pc time node uri序列ID
     *
     * @return Integer
     */
    public Integer getPcTimeLineNodeUriSeqId() {
        return super.getSequenceId(CollectionsEnum.PC_TIME_LINE_NODE_URI.getName());
    }

    /**
     * 获取交易类型序列ID
     *
     * @return Integer
     */
    public Integer getTradeTypeSeqId() {
        return super.getSequenceId(CollectionsEnum.TRADE_TYPE.getName());
    }

    /**
     * 获取用户资料审核状态序列ID
     *
     * @return Integer
     */
    public Integer getUserAuditStatusSeqId() {
        return super.getSequenceId(CollectionsEnum.USER_AUDIT_STATUS.getName());
    }

    /**
     * 获取支付渠道序列ID
     *
     * @return Integer
     */
    public Integer getPaymentChannelSeqId() {
        return super.getSequenceId(CollectionsEnum.PAYMENT_CHANNEL.getName());
    }

    /**
     * 支付业务类型
     *
     * @return
     */
    public Integer getPaymentBusinessTypeSeqId() {
        return super.getSequenceId(CollectionsEnum.PAYMENT_BUSINESS_TYPE.getName());
    }

    /**
     * 充值类型
     *
     * @return
     */
    public Integer getRechargeTypeSeqId() {
        return super.getSequenceId(CollectionsEnum.RECHARGE_TYPE.getName());
    }


    /**
     * 获取创意人收款规则seq
     *
     * @return
     */
    public Integer getCpPayRuleSeqId() {
        return super.getSequenceId(CollectionsEnum.CP_PAY_RULE.getName());
    }

    /*********以下是order相关*******/
    /**
     * 方案Id
     *
     * @return id
     */
    public Long getSolutionSeqId() {
        return super.getSequenceLongId(CollectionsEnum.SOLUTION.getName());
    }

    /**
     * 源文件Id
     *
     * @return id
     */
    public Long getFinalFileSeqId() {
        return super.getSequenceLongId(CollectionsEnum.FINAL_FILE.getName());
    }

    /**
     * 调研报告Id(编辑)
     */
    public Integer getReportSeqId() {
        return super.getSequenceId(CollectionsEnum.REPORT.getName());
    }

    /**
     * 已提交调研报告（记录）
     */
    public Integer getReportRecordSeqId() {
        return super.getSequenceId(CollectionsEnum.REPORT_RECORD.getName());
    }

    /**
     * 订单计划时间表获取ID
     *
     * @return Integer
     */
    public Integer getOrderScheduleSeqId() {
        return super.getSequenceId(CollectionsEnum.ORDER_SCHEDULE.getName());
    }

    /**
     * 获取订单计划表序列ID
     *
     * @return Integer
     */
    public Integer getOrderTimeLineSeqId() {
        return super.getSequenceId(CollectionsEnum.ORDER_TIME_LINE.getName());
    }

    /**
     * 获取顾问订单计划表序列ID
     *
     * @return
     */
    public Integer getKeeperOrderTimeLineSedId() {
        return super.getSequenceId(CollectionsEnum.KEEPER_ORDER_TIME_LINE.getName());
    }

    /**
     * 获取创意人订单计划表序列ID
     *
     * @return
     */
    public Integer getCpOrderTimeLineSedId() {
        return super.getSequenceId(CollectionsEnum.KEEPER_ORDER_TIME_LINE.getName());
    }

    /**
     * 获取订单深化设计记录序列ID
     *
     * @return
     */
    public Integer getDeepDesignLogSedId() {
        return super.getSequenceId(CollectionsEnum.DEEP_DESIGN_LOG.getName());
    }

    /**
     * 个人简历序列ID
     *
     * @return
     */
    public Integer getResumeSeqId() {
        return super.getSequenceId(CollectionsEnum.RESUME.getName());
    }

    /**
     * 获取IM帐号
     *
     * @return Long
     */
    public Long getImAccountSeqId() {
        return super.getSequenceLongId(CollectionsEnum.IM_ACCOUNT.getName());
    }

    /**
     * 获得分配顾问seq
     *
     * @return Long
     */
    public Long getKeeperAssignSeqId() {
        return super.getSequenceLongId(CollectionsEnum.KEEPER_ASSIGN.getName());
    }

    /**
     * 获得分配创意人seq
     *
     * @return Long
     */
    public Long getSupplierAssignSeqId() {
        return super.getSequenceLongId(CollectionsEnum.SUPPLIER_ASSIGN.getName());
    }

    /**
     * 获取订单im关系seq
     *
     * @return seq值
     */
    public Integer getOrderImInfoSeqId() {
        return super.getSequenceId(CollectionsEnum.ORDER_IM_INFO.getName());
    }

    /**
     * 获取创意人分配记录缓存的序列id
     *
     * @return id
     */
    public Integer getSupplierAssignCacheSeqId() {
        return super.getSequenceId(CollectionsEnum.SUPPLIER_ASSIGN_CACHE.getName());
    }

    /**
     * 获得创意人取消订单log seq
     *
     * @return Long
     */
    public Long getCpRefuseOrderLogSeq() {
        return super.getSequenceLongId(CollectionsEnum.CP_REFUSE_ORDER_LOG.getName());
    }

    /**
     * 获取顾问取消订单Log seq
     *
     * @return
     */
    public Long getKeeperOffLogSeqId() {
        return super.getSequenceLongId(CollectionsEnum.KEEPER_OFF_LOG.getName());
    }

    /**
     * 获取源文件上传记录表的ID Sequence --- add By WuZheng
     *
     * @return
     */
    public Long getSourceFileUploadRecordId() {
        return super.getSequenceLongId(CollectionsEnum.SOURCE_FILE_UPLOAD_RECORD.getName());
    }


    /**
     * 获取验证码发送上限表的ID Sequence --- add By WuZheng
     *
     * @return
     */
    public Long getCaptchaTimesRuleId() {
        return super.getSequenceLongId(CollectionsEnum.CAPTCHA_TIMES_RULE.getName());
    }
    /*********以下是用户相关********/
    /**
     * 获取用户认证
     *
     * @return Long
     */
    public Long getUserAuthRecordSeqId() {
        return super.getSequenceLongId(CollectionsEnum.USER_AUTH_RECORD.getName());
    }

    /**
     * 获取用户Token
     *
     * @return Long
     */
    public Long getUserTokenSeqId() {
        return super.getSequenceLongId(CollectionsEnum.USER_TOKEN.getName());
    }

    /**
     * 更改密码记录Log
     *
     * @return Long
     */
    public Long getPasswordChangeLogSeqId() {
        return super.getSequenceLongId(CollectionsEnum.PASSWORD_CHANGE_LOG.getName());
    }

    public Long getBossLogId() {
        return super.getSequenceLongId(CollectionsEnum.BOSS_LOG.getName());
    }

    /*********以下是运营内容相关********/
    /**
     * 道道案例
     *
     * @return Integer
     */
    public Integer getDaodaoCaseSeqId() {
        return super.getSequenceId(CollectionsEnum.DAODAO_CASE.getName());
    }

    /**
     * 道道最新案例
     *
     * @return Integer
     */
    public Integer getDaodaoNewCaseSeqId() {
        return super.getSequenceId(CollectionsEnum.DAODAO_NEW_CASE.getName());
    }

    /**
     * 东道合作伙伴
     *
     * @return Integer
     */
    public Integer getPartnerSeqId() {
        return super.getSequenceId(CollectionsEnum.DONGDAO_PARTNER.getName());
    }

    /**
     * 东道荣誉
     *
     * @return Integer
     */
    public Integer getHonorSeqId() {
        return super.getSequenceId(CollectionsEnum.DONGDAO_HONOR.getName());
    }

    /**
     * bannner
     *
     * @return Integer
     */
    public Integer getBannerSeqId() {
        return super.getSequenceId(CollectionsEnum.DAODAO_BANNER.getName());
    }

    /*********以下是log相关********/

    /**
     * 获取用户登录日志
     *
     * @return Long
     */
    public Long getUserLoginLogSeqId() {
        return super.getSequenceLongId(CollectionsEnum.USER_LOGIN_LOG.getName());
    }

    /*********
     * 以下是sms相关
     ********/
    public Long getSMSReusltSeqId() {
        return super.getSequenceLongId(CollectionsEnum.SMS_RESULT.getName());
    }

    /*********
     * 以下是订单编码相关
     ********/

    public Long getOrderCodeSeqId() {
        return super.getSequenceLongId(CollectionsEnum.ORDER_CODE.getName());
    }

    public Long getOrderCodeTempSeqId() {
        return super.getSequenceLongId(CollectionsEnum.ORDER_CODE_TEMP.getName());
    }

    /*********
     * 以下是顾问编码相关
     ********/
    public Long getKeeperCodeSeqId() {
        return super.getSequenceLongId(CollectionsEnum.KEEPER_CODE.getName());
    }


    /*********
     * 以下是APP版本相关
     ********/
    /**
     * app版本
     *
     * @return Integer
     */
    public Integer getAppVersionSeqId() {
        return super.getSequenceId(CollectionsEnum.APP_VERSION.getName());
    }

    /**
     * app数据版本
     *
     * @return Integer
     */
    public Integer getAppDataVersionSeqId() {
        return super.getSequenceId(CollectionsEnum.APP_DATA_VERSION.getName());
    }

    /**
     * 顾问订单需求-草稿
     *
     * @return
     */
    public Integer getKeeperOrderRequirement() {
        return super.getSequenceId(CollectionsEnum.KEEPER_ORDER_REQUIREMENT.getName());
    }

    /**
     * 简历的教育信息ID
     */
    public Integer getResumeEducationSeqId() {
        return super.getSequenceId(CollectionsEnum.RESUME_EDUCATION.getName());
    }

    /**
     * 简历的工作经历ID
     */
    public Integer getResumeJobSeqId() {
        return super.getSequenceId(CollectionsEnum.RESUME_JOB.getName());
    }

    /**
     * 简历的项目经历ID
     */
    public Integer getResumeProjectSeqId() {
        return super.getSequenceId(CollectionsEnum.RESUME_PROJECT.getName());
    }


    /** ********************************************************************** */
    /**
     * 服务标准
     *
     * @return
     */
    public Integer getServeRuleSeqId() {
        return super.getSequenceId(CollectionsEnum.SERVE_RULE.getName());
    }

    /**
     * 创意人levelForPrice
     */
    public Integer getSupplierLevelForPriceSeqId() {
        return super.getSequenceId(CollectionsEnum.SUPPLIER_LEVEL_FOR_PRICE.getName());
    }

    /**
     * 服务标准 和 需求分类关联表
     */
    public Integer getServeRuleCategorySeqId() {
        return super.getSequenceId(CollectionsEnum.SERVE_RULE_CATEGORY.getName());
    }

    /**
     * 获取文件上传临时表ID
     *
     * @return Long
     */
    public Long getFileUploadTempSeqId() {
        return super.getSequenceLongId(CollectionsEnum.FILE_UPLOAD_TEMP.getName());
    }

    /**
     * 提现约束
     */
    public Integer getWidthrawalRestrictSeqId() {
        return super.getSequenceId(CollectionsEnum.WidthrawalRestrict.getName());
    }

    /**
     * 用户账户信息
     *
     * @return
     */
    public Integer getUserAccountSeqId() {
        return super.getSequenceId(CollectionsEnum.USER_ACCOUNT.getName());
    }

    /**
     * 学历
     */
    public Integer getDegreeSeqId() {
        return super.getSequenceId(CollectionsEnum.DEGREE.getName());
    }

    /**
     * 获取客户订单支付记录序列ID
     *
     * @return
     */
    public Long getCustomerOrderPaymentLogSedId() {
        return super.getSequenceLongId(CollectionsEnum.CUSTOMER_ORDER_PAYMENT_LOG.getName());
    }

    /**
     * 获取源文件审核历史源文件记录的ID
     *
     * @return
     */
    public Long getSourceFileHistoryLogSeqId() {
        return super.getSequenceLongId(CollectionsEnum.SOURCE_FILE_HISTORY_LOG.getName());
    }

    /**
     * 订单编号ID
     *
     * @return
     */
    public Long getOrderNumberSeqId() {
        return super.getSequenceLongId(CollectionsEnum.ORDER_NUMBER.getName());
    }

    /**
     * 客户支付流水号ID
     *
     * @return
     */
    public Long getPaySerialNumberSeqId() {
        return super.getSequenceLongId(CollectionsEnum.PAY_SERIAL_NUMBER.getName());
    }

    public Long getFirstSolutionHistoryLogSeqId() {
        return super.getSequenceLongId(CollectionsEnum.FIRST_SOLUTION_LOG.getName());
    }

}
