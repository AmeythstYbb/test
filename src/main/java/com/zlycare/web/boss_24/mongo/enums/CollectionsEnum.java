package com.zlycare.web.boss_24.mongo.enums;

/**
 * @author lufanglong
 * @date 2015-11-20 21:18
 * @Description Mongo的collection名称枚举
 */
public enum CollectionsEnum {

    /*********以下是规则和字典表相关*************/

    /**
     * 订单分类
     */
    CATEGORY("category"),

    /**
     * 支付规则表
     */
    PAYMENT_RULE("paymentRule"),

    /**
     * 支付渠道表
     */
    PAYMENT_CHANNEL("paymentChannel"),

    /**
     * 支付业务类型
     */
    PAYMENT_BUSINESS_TYPE("paymentBusinessType"),

    /**
     * 充值类型
     */
    RECHARGE_TYPE("rechargeType"),

    /**
     * 创意人报酬规则
     */
    CP_PAY_RULE("cpPayRule"),

    /**
     * 客户等级表
     */
    CUSTOMER_GRADE("customerGrade"),

    /**
     * 创意人等级
     */
    SUPPLIER_GRADE("supplierGrade"),

    /**
     * 创意人级别表
     */
    SUPPLIER_LEVEL("supplierLevel"),

    /**
     * 顾问等级表
     */
    KEEPER_GRADE("keeperGrade"),

    /**
     * 订单评价规则表
     */
    ORDER_EVALUATION_RULE("orderEvaluationRule"),

    /**
     * 订单评价类型表
     */
    ORDER_EVALUATION_TYPE("orderEvaluationType"),
    /**
     * 订单评价类型表
     */
    PC_TIME_LINE_NODE_URI("pcTimeLineNodeUri"),

    /**
     * 订单状态表
     */
    ORDER_STATUS("orderStatus"),

    /**
     * 客户端状态显示名称表
     */
    CLIENT_ORDER_STATUS("clientOrderStatus"),
    /**
     * 创意人客户端状态对应关系
     */
    CP_CLIENT_ORDER_STATUS("cpClientOrderStatus"),

    /**
     * 订单time line 节点信息表
     */
    ORDER_TIME_LINE_TITLE("orderTimeLineTitle"),

    /**
     * 顾问订单time line 节点信息表
     */
    KEEPER_ORDER_TIME_LINE_TITLE("keeperOrderTimeLineTitle"),
    /**
     * 创意人订单time line 节点信息表
     */
    CP_ORDER_TIME_LINE_TITLE("cpOrderTimeLineTitle"),
    /**
     * 客户审核状态表
     */
    USER_AUDIT_STATUS("userAuditStatus"),

    /**
     * 赠送积分规则表
     */
    GIFT_GRANT_RULE("giftGrantRule"),

    /**
     * 赠送积分类型表
     */
    GIFT_TYPE("giftType"),

    /**
     * 地区表
     */
    AREA("area"),

    /**
     * 反馈记录
     */
    FEEDBACK("feedback"),

    /**
     * 企业行业表
     */
    ENTERPRISE_INDUSTRY("enterpriseIndustry"),

    /**
     * 企业年收入表
     */
    ENTERPRISE_INCOME("enterpriseIncome"),

    /**
     * 注册资金
     */
    ENTERPRISE_REGISTERED_CAPITAL("enterpriseRegisteredCapital"),

    /**
     * 企业类型表
     */
    ENTERPRISE_TYPE("enterpriseType"),

    /**
     * 企业员工数量表
     */
    ENTERPRISE_STAFF_TOTAL("enterpriseStaffTotal"),

    /**
     * 企业员工从业年限
     */
    ENTERPRISE_STAFF_YEARS_WORKING("enterpriseStaffYearsWorking"),

    /**
     * 验证码类型表
     */
    CAPTCHA_TYPE("captchaType"),

    /**
     * 交易类型表
     */
    TRADE_TYPE("tradeType"),

    /**
     * 发票类型表
     */
    INVOICE_TYPE("invoiceType"),

    /**
     * 发票内容类型表
     */
    INVOICE_CATEGORY("invoiceCategory"),

    /**
     * 学历表
     */
    DEGREE("degree"),

    /********以下是order相关*********/
    /**
     * 方案
     */
    SOLUTION("solution"),
    /**
     * 源文件
     */
    FINAL_FILE("finalFile"),
    /**
     * 调研报告（编辑）
     */
    REPORT("report"),
    /**
     * 已提交调研报告（记录）
     */
    REPORT_RECORD("reportRecord"),
    /**
     * 订单time line
     */
    ORDER_TIME_LINE("orderTimeLine"),
    /**
     * 顾问订单time line
     */
    KEEPER_ORDER_TIME_LINE("keeperOrderTimeLine"),
    /**
     * 创意人订单time line
     */
    CP_ORDER_TIME_LINE("cpOrderTimeLine"),
    /**
     * order schedule
     */
    ORDER_SCHEDULE("orderSchedule"),
    /**
     * 顾问分配规则表
     */
    KEEPER_ASSIGN("keeperAssign"),
    /**
     * 创意人取消订单记录
     */
    CP_REFUSE_ORDER_LOG("cpRefuseOrderLog"),
    /**
     * 创意人分配规则表
     */
    SUPPLIER_ASSIGN("supplierAssign"),
    /**
     * 深化设计操作log
     */
    DEEP_DESIGN_LOG("deepDesignLog"),
    //用户模块
    /**
     * 用户鉴权记录表
     */
    USER_AUTH_RECORD("userAuthRecord"),
    /**
     * 用户token表
     */
    USER_TOKEN("userToken"),

    /**
     * 顾问取消接单原因记录
     */
    KEEPER_OFF_LOG("keeperOffLog"),

    /**
     * 个人简历
     */
    RESUME("resume"),
    /**
     * IM账户
     */
    IM_ACCOUNT("imAccount"),
    /**
     * 创意人分配缓存
     */
    SUPPLIER_ASSIGN_CACHE("supplierAssignCache"),

    //运营内容相关表
    /**
     * 道道案例表
     */
    DAODAO_CASE("daodaoCase"),
    /**
     * 道道最新案例表
     */
    DAODAO_NEW_CASE("daodaoNewCase"),
    /**
     * 首页banner表
     */
    DAODAO_BANNER("banner"),
    /**
     * 东道合作伙伴表
     */
    DONGDAO_PARTNER("partner"),
    /**
     * 东道合作伙伴表
     */
    DONGDAO_HONOR("honor"),

    //log相关表
    /**
     * 用户登录日志表
     */
    USER_LOGIN_LOG("userLoginLog"),
    /**
     * 用户修改密码记录表
     */
    PASSWORD_CHANGE_LOG("passwordChangeLog"),
    //sms相关
    /**
     * SMS result
     */
    SMS_RESULT("smsResult"),
    //订单编码
    /**
     * 订单编码临时
     */
    ORDER_CODE_TEMP("orderCodeTemp"),
    /**
     * 订单编码
     */
    ORDER_CODE("orderCode"),
    /**
     * 顾问编码
     */
    KEEPER_CODE("keeperCode"),
    /**
     * 订单Im关系
     */
    ORDER_IM_INFO("orderImInfo"),

    //APP相关
    /**
     * 订单编码临时
     */
    APP_DATA_VERSION("appDataVersion"),
    /**
     * app版本
     */
    APP_VERSION("appVersion"),

    /**
     * 顾问订单需求-草稿
     */
    KEEPER_ORDER_REQUIREMENT("keeperOrderRequirement"),
    /**
     * 服务标准
     */
    SERVE_RULE("serveRule"),
    /**
     * 创意人等级对应价格区间关系表
     */
    SUPPLIER_LEVEL_FOR_PRICE("supplierLevelForPrice"),
    /**
     * 服务标准和需求分类关联表
     */
    SERVE_RULE_CATEGORY("serveRuleCategory"),

    /**
     * 提现限制
     */
    WidthrawalRestrict("widthrawalRestrict"),

    /**
     * 简历表 中的教育信息
     */

    RESUME_EDUCATION("resumeEducation"),

    /**
     * 简历表 中的职业经历的信息
     */
    RESUME_JOB("resumeJob"),

    /**
     * 简历表中的   项目经历信息
     */

    RESUME_PROJECT("resumeProject"),

    /**
     * 文件上传临时表
     */
    FILE_UPLOAD_TEMP("fileUploadTemp"),

    /**
     * 源文件上传记录表 --- add by WuZheng
     */
    SOURCE_FILE_UPLOAD_RECORD("sourceFileUploadRecord"),
    /**
     * 获取验证码上限表 --- add by WuZheng
     */
    CAPTCHA_TIMES_RULE("captchaTimesRule"),

    /**
     * 上传源文件历史记录
     */
    SOURCE_FILE_HISTORY_LOG("sourceFileHistoryLog"),

    /**
     * 所有用户账户信息
     */
    USER_ACCOUNT("userAccount"),

    /**
     * 客户订单支付log
     */
    CUSTOMER_ORDER_PAYMENT_LOG("customerOrderPaymentLog"),

    /**
     * 订单编号
     */
    ORDER_NUMBER("orderNumber"),


    /**
     * 支付流水号
     */
    PAY_SERIAL_NUMBER("paySerialNumber"),

    /**
     * BossLog
     */
    BOSS_LOG("bossLog"),

    /**
     * 审核初稿不通过记录ID
     */
    FIRST_SOLUTION_LOG("firstSolutionHistoryLog"),;

    String name;

    CollectionsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
