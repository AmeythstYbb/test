package com.zlycare.web.boss_24.beans.constants.error;

/**
 * @author lufanglong
 * @author DaiJian
 * @date 2016-01-14 16:11
 * @Description 服务业务码
 */
public enum ServiceBusinessCode {
	
    /**
     * 2000
     */
    ORDER_ERROR(2000, "订单异常"),
    /**
     * 2001
     */
    ORDER_NOT_FOUND_ERROR(2001, "找不到订单"),
    /**
     * 2002
     */
    ORDER_CREATE_ERROR(2002, "创建订单失败"),
    /**
     * 2003
     */
    ORDER_CANCEL_ERROR(2003, "取消订单异常"),
    /**
     * 2004
     */
    ORDER_CONFIRM_ERROR(2004, "确认订单异常"),
    /**
     * 2005
     */
    ORDER_CANCEL_OUT_OF_STATUS_ERROR(2005, "取消订单失败，订单状态不满足要求"),
    /**
     * 2006
     */
    ORDER_ATTACHMENT_ERROR(2006, "订单附件异常"),
    /**
     * 2007
     */
    ORDER_ITEM_ERROR(2007, "订单子项异常"),
    /**
     * 2008
     */
    ORDER_EVALUATION_ERROR(2008, "订单评价异常"),
    /**
     * 2009
     */
    ORDER_EVALUATION_CREATE_ERROR(2009, "创建订单评价信息异常"),
    /**
     * 订单状态不满足要求
     */
    ORDER_STATUS_ERROR(2010, "订单状态不满足要求"),
    /**
     * 未查询到订单编号异常
     */
    ORDER_CODE_NOT_FOUND(2011, "未查询到订单编号异常"),
    /**
     * 未查询到顾问信息
     */
    KEEPER_NOT_FOUND(2012, "未查询到顾问信息"),
    /**
     * 订单状态不一直
     */
    ORDER_STATUS_DISSIMILARITY(2013, "订单状态不一致"),
    /**
     * 2100
     */
    ORDER_AMOUNT_ERROR(2100, "订单金额异常"),
    /**
     * 2101
     */
    ORDER_AMOUNT_CAN_NOT_GET(2101, "获取订单金额异常"),
    /**
     * 2102
     */
    ORDER_TIME_LINE_ERROR(2102, "订单时间轴异常"),
    /**
     * 2103
     */
    ORDER_TIME_LINE_NOT_FOUNT(2103, "获取订单时间轴异常"),
    /**
     * 2104
     */
    ORDER_TIME_LINE_CREATE_ERROR(2104, "创建订单时间轴异常"),
    /**
     * 2105
     */
    ORDER_TIME_LINE_UPDATE_ERROR(2105, "更新订单时间轴异常"),
    /**
     * 2106
     */
    ORDER_TIME_LINE_UNSET_ERROR(2106, "删除订单时间轴异常"),
    /**
     * 2107
     */
    ORDER_SCHEDULE_ERROR(2107, "订单进度异常"),
    /**
     * 2108
     */
    ORDER_SCHEDULE_FOUND_ERROR(2108, "查询订单进度异常"),
    /**
     * 2109
     */
    ORDER_SCHEDULE_CREATE_ERROR(2109, "创建订单进度异常"),
    /**
     * 2110
     */
    ORDER_STATUS_LOG_ERROR(2110, "订单状态记录异常"),
    /**
     * 订单和用户信息不匹配
     */
    ORDER_CHECKED_ERROR(2111, "订单和用户信息不匹配"),
    /**
     * 创建订单状态记录异常
     */
    CREATE_ORDER_STATUS_LOG_ERROR(2112, "创建订单状态记录异常"),
    /**
     * 客户已经评价
     */
    CUSTOMER_ALREADY_EVALUATION(2113, "客户已经完成评价"),
    /**
     * 订单预期时间无效
     */
    INVALID_EXPECTED_DATE_EXCEPTION(2114, "无效的订单预期时间"),
    /**
     * 没有查询到order time line
     */
    ORDER_TIMELINE_NOT_FOUND(2115, "没有查询到订单时间线"),
    /**
     * 订单和方案信息不匹配
     */
    ORDER_CUSTOMER_NOT_MATCH(2200, "订单和方案信息不匹配"),
    /**
     * 2600
     */
    ORDER_SOLUTION_ERROR(2600, "订单方案异常"),
    /**
     * 2601
     */
    SOLUTION_NOT_FOUND_EXCEPTION(2601, "订单方案没有找到异常"),
    /**
     * 2602
     */
    SOLUTION_CAN_NOT_DEEPIN_STATUS_ERROR(2602, "订单方案无法继续深化，订单状态不满足要求"),
    /**
     * 2603
     */
    ORDER_SOLUTION_DEEPING_ERROR(2603, "订单方案继续深化异常"),
    /**
     * 2604
     */
    ORDER_SOLUTION_CONFIRM_FINAL_SOLUTION_ERROR(2604, "确认订单终稿异常"),
    /**
     * 订单方案类型错误
     */
    SOLUTION_TYPE_EXCEPTION(2605, "订单方案类型错误"),
    /**
     * 2606
     */
    SERVE_RULE_NOT_FOUND(2606, "服务标准异常"),
    /**
     * 2607
     */
    SERVE_RULE_CATEGORY_NOT_FOUND(2607, "服务标准和需求分类关联异常"),

    /**
     * 2671
     */
    ORDER_QUESTIONNAIRE_ALBUM_ERROR(2671,"画册设计数据填写不完整"),
    /**
     * 2672
     */
    ORDER_QUESTIONNAIRE_LOGO_ERROR(2672,"logo设计数据填写不完整"),
    /**
     * 2673
     */
    ORDER_QUESTIONNAIRE_MASCOT_ERROR(2673,"吉祥物设计数据填写不完整"),
    /**
     * 2674
     */
    ORDER_QUESTIONNAIRE_PACKAGE_ERROR(2674,"包装设计数据填写不完整"),
    /**
     * 2701
     */
    REPORT_LOGO_INTEGRITY_ERROR(2701, "logo设计数据填写不完整"),
    /**
     * 2702
     */
    REPORT_VI_INTEGRITY_ERROR(2702,"vi设计数据填写不完整"),
    /**
     * 2703
     */
    REPORT_ADVERTISE_INTEGRITY_ERROR(2703,"广告设计数据填写不完整"),
    /**
     * 2704
     */
    REPORT_PICTURE_ALBUM_INTEGRITY_ERROR(2704,"画册设计数据填写不完整"),
    /**
     * 2705
     */
    REPORT_PACKAGE_INTEGRITY_ERROR(2705,"包装设计数据填写不完整"),
    /**
     * 2706
     */
    REPORT_MASCOT_INTEGRITY_ERROR(2706,"吉祥物设计数据填写不完整"),
    /**
     * 2707
     */
    REPORT_PRODUCT_DESIGN_INTEGRITY_ERROR(2707,"产品设计数据填写不完整"),
    /**
     * 2708
     */
    REPORT_APPEARANCE_INTEGRITY_ERROR(2708,"外观设计数据填写不完整"),
    /**
     * 2709
     */
    REPORT_NAMING_INTEGRITY_ERROR(2709,"命名设计数据填写不完整"),
    /**
     * 2710
     */
    REPORT_ILLUSTRATION_INTEGRITY_ERROR(2710,"插画设计数据填写不完整"),
    /**
     * 2711
     */
    REPORT_INDUSTRIAL_INTEGRITY_ERROR(2711,"工业设计数据填写不完整"),
    /**
     * 2712
     */
    REPORT_BRANDING_INTEGRITY_ERROR(2712,"品牌传播数据填写不完整"),
    /**
     * 用户异常
     */
    USER_ERROR(3000, "用户异常"),
    /**
     * 每小时注册超过限定次数
     */
    USER_REFRESH_SURPASS_ERROR(3001, "每小时注册超过限定次数"),

    /**
     * 验证码错误
     */
    USER_CAPTCHA_ERROR(3002, "验证码错误"),
    /**
     * 手机已注册
     */
    USER_PHONE_REGISTERED(3003, "该手机号已注册，可直接登录"),
    /**
     * 手机已注册为非入口端账号
     */
    USER_PHONE_REGISTERED_OTHERS(30031, "该帐号无法注册"),
    /**
     * 手机未注册(忘记密码时使用三端均未注册过的账号)
     */
    USER_PHONE_NOT_REGISTER(30032, "该手机号未注册"),
    /**
     * 忘记密码时使用非入口端注册过的账号
     */
    USER_PHONE_TYPE_NOT_MATCH(30033, "您还未注册平台帐号，请先前往注册"),
    
    /**
     * 黑明单用户
     */
    USER_BLACKLIST_PHONE(30021,"您的帐号为无效账号"),
    
    /**
     * 注册新旧密码不一致
     */
    USER_OLD_NEW_PASSWORD(30022,"两次输入的密码不同"),
    
    /**
     * 注册失败
     */
    USER_REGISTER_ERROR(3004, "注册失败"),
    /**
     * 授权失败
     */
    USER_AUTH_ERROR(3005, "授权失败"),
    /**
     * 创建token失败
     */
    USER_CREATE_TOKEN_ERROR(3006, "创建token失败"),
    /**
     * 手机号码校验错误（只能输入纯数字，最多11位）
     */
    USER_PHONE_FORMAT_ERROR(3007, "手机号码校验错误"),
    /**
     * 密码校验错误（MD5）
     */
    USER_PASSWORD_FORMAT_ERROR(3008, "密码校验错误"),
    /**
     * 3009
     */
    USER_AUDIT_STATUS_ERROR(3009, "您的帐号未认证通过"),
    /**
     * token key 无效
     */
    USER_REFRESH_KEY_INVALID(3020, "token key 无效"),
    /**
     * 刷新失败
     */
    USER_REFRESH_ERROR(3021, "刷新失败"),
    /**
     * 用户不存在
     */
    USER_NOT_FOUND_ERROR(3010, "用户不存在"),
    /**
     * 密码错误
     */
    USER_PASSWORD_ERROR(3011, "密码相关异常"),
    /**
     * 3012
     */
    USER_LOGIN_SURPASS_ERROR(3012, "操作频繁，请稍后再试！"),
    /**
     * 用户类型错误
     */
    USER_TYPE_ERROR(3013, "该帐号无法登录"),
    /**
     * 用户数据异常
     */
    USER_DATA_ERROR(3014, "用户数据异常"),
    
    
    /**
     * 客户不存在
     */
    CUSTOMER_NOT_FOUND_ERROR(3015,"客户不存在"),
    
    /**
     * 用户数据异常
     */
    USER_OR_PASSWORD_ERROR(3016, "用户或密码错误"),

    /**
     * 用户操作频繁
     */
    USER_OR_PASSWORD_SURPASS_ERROR(3017, "密码连续错误5次，请两小时后再进行尝试！"),

    /**
     * app用户类型错误
     */
    USER_FORGET_TYPE_ERROR(3018, "该账号无法忘记密码"),



    /**
     * 用户没有权限修改密码
     */
    USER_UNABLE_CHANGE_PASSWORD(3030, "用户没有权限修改密码"),
    /**
     * 用户资料异常
     */
    USER_PROFILE_ERROR(3070, "用户资料异常"),
    /**
     * 用户资料找不到异常
     */
    USER_PROFILE_NOT_FOUND_ERROR(3071, "用户资料不存在异常"),
    /**
     * 3052
     */
    NEW_PASSWORD_SAME_ERROR(3052, "新旧密码不能相同"),
    /**
     * 3053
     */
    TWO_PASSWORD_DIFFERENT_ERROR(3053, "输入的两遍密码不一样"),
    /**
     * 3054
     */
    PASSWORD_CAPTCHA_ERROR(3054, "验证码不正确"),
    /**
     * 3055
     */
    OLD_PASSWORD_INCORRECT(3055, "旧密码不正确"),
    /**
     * 3056
     */
    GET_PASSWORD_CAPTCHA_OVER_TIMES(3056, "获取修改密码验证码次数过多"),

    /**
     * 3059
     */
    GET_PASSWORD_CAPTCHA_TIMES(3059, "验证码次数已达上限"),

    /**
     * 3060
     */
    GET_PASSWORD_CAPTCHA_ERROR(3060, "验证码获取失败,请稍后再试!"),

    /**
     * 9527
     */
    GET_CREATE_BANKCARD_CAPTCHA_OVER_TIMES(9527, "获取新建银行卡验证码次数过多"),
    /**
     * 9528
     */
    GET_DELETE_BANKCARD_CAPTCHA_OVER_TIMES(9528, "获取解绑银行卡验证码次数过多"),
    /**
     * 9529
     */
    GET_WITHDRAW_CAPTCHA_OVER_TIMES(9529, "获取提现验证码次数过多"),
    /**
     * 忘记密码错误
     */
    FORGET_PASSWORD_ERROR(3057, "忘记密码错误"),
    /**
     * 用户没有权限修改密码
     */
    USER_UNABLE_FORGET_PASSWORD(3058, "用户没有权限修改密码"),
    /**
     * 3100
     */
    CUSTOMER_ERROR(3100, "客户异常"),
    /**
     * 3200
     */
    KEEPER_ERROR(3200, "顾问异常"),
    /**
     * 3300
     */
    CUSTOMER_INVOICE_EXCEPTION(3300, "客户开具发票异常"),
    /**
     * 3304
     */
    CP_INVOICE_EXCEPTION(3304, "客户开具发票异常"),
    /**
     * 3301
     */
    INVOICE_VALUE_OVER_FLOW(3301, "发票金额超过限额"),
    /**
     * 3302
     */
    INVOICE_OUT_OF_TIMES(3302, "开票次数超过限定"),
    /**
     * 3303
     */
    INVOICE_OUT_OF_AMOUNT(3303, "开票金额超过客户可开票金额"),

    /**
     * 3310
     */
    ILLEGAL_FILE_NEAM(3310, "文件名不合法"),

    /**
     * 3320
     */
    NICKNAME_MODIFI_ERROR(3320, "用户名错误"),

    /**
     * 4000
     */
    CONTENT_ERROR(4000, "运营数据异常"),
    /**
     * 4001
     */
    CASE_ERROR(4001, "案例异常"),
    /**
     * 4002
     */
    CASE_NOT_FOUND_ERROR(4002, "案例不存在异常"),
    /**
     * 4003
     */
    HONOR_ERROR(4003, "东道荣誉异常"),
    /**
     * 4004
     */
    HONOR_NOT_FOUND_ERROR(4004, "东道荣誉不存在异常"),
    /**
     * 4005
     */
    CASE_USER_LIKED_ERROR(4005, "用户已like过该案例"),
    /**
     * 公共模块异常
     */
    COMMON_ERROR(5000, "公共模块异常"),
    /**
     * 未查询到地区信息
     */
    FIND_AREA_ERROR(5001, "未查询到地区信息"),
    /**
     * 查询发票类型异常
     */
    FIND_INVOICE_TYPE_ERROR(5002, "查询发票类型异常"),
    /**
     * 查询发票内容类型异常
     */
    FIND_INVOICE_CATEGORY_ERROR(5003, "查询发票内容类型异常"),
    /**
     * 未查询到支付规则
     */
    PAYMENT_RULE_NOT_FOUND_ERROR(5004, "未找到支付规则"),
    /**
     * 未找到可用支付渠道
     */
    PAYMENT_CHANNEL_NOT_FOUND_ERROR(5005, "未找到可用支付渠道"),
    /**
     * 未找到订单评价规则
     */
    EVALUATION_RULE_NOT_FOUND(5006, "未找到订单评价规则"),
    /**
     * 数字格式异常
     */
    ILLEGAL_NUMBER_FORMAT_ERROR(5100, "数字格式异常"),
    /**
     * 文件列表为空
     */
    FILE_ARRAY_IS_EMPTY(5498, "文件列表为空"),
    /**
     * 文件上传异常
     */
    FILE_UPLOAD_ERROR(5499, "文件上传异常"),
    /**
     * 6000
     */
    WORKORDER_ERROR(6000, "派单异常"),
    /**
     * 6001
     */
    ASSIGN_KEEPER_ERROR(6001, "分配顾问异常"),
    /**
     * 6002
     */
    ASSING_SUPPLIER_ERROR(6002, "分配创意人异常"),
    /**
     * 7000
     */
    THIRD_PARTY_ERROR(7000, "第三方异常"),
    /**
     * 7100
     */
    EASEMOBO_RESPONSE_ERROR(7100, "环信响应异常"),
    /**
     * 支付异常
     */
    PAY_ERROR(7200, "支付异常"),
    /**
     * 稻米支付异常
     */
    PAY_DAOMI_PAY_ERROR(7201, "稻米支付异常"),
    /**
     * 稻米充值异常
     */
    PAY_DAOMI_CHARGE_ERROR(7202, "稻米充值异常"),
    /**
     * 稻米余额不足
     */
    PAY_DAOMI_BALANCE_ERROR(7203, "稻米余额不足"),
    /**
     * 支付密码未设置
     */
    PAY_DAOMI_PASSWORD_UNSET(7204, "支付密码未设置"),

    /**
     * 支付密码错误
     */
    PAY_DAOMI_PASSWORD_ERROR(7205, "支付密码错误"),
    /**
     * 稻米支付频繁错误 超过5次
     */
    PAY_DAOMI_PASSWORD_ERROR_REPEATEDLY(7206, "支付频繁错误 "),
    /**
     * 新老密码不能相同
     */
    PAY_DAOMI_PASSWORD_SAME(7207, "新老密码不能相同"),

    /**
     * 充值未完成
     */
    PAY_RECHARGE_UNFINISHED(7208, "充值未完成"),

    /**
     * 充值失败
     */
    PAY_RECHARGE_ERROR(7209, "充值失败"),

    /**
     * 稻米支付密码不能为空
     */
    PAY_RICIPAY_PASSWORD_EMPTY_ERROR(7210,"稻米支付密码不能为空"),

    /**
     * 稻米支付密码格式错误
     */
    PAY_RICIPAY_PASSWORD_FORMAT_ERROR(7211,"请输入正确的密码"),

    /**
     * 分配创意人异常
     */
    ASSIGN_SUPPLIER_ERROR(6002, "分配创意人异常"),
    /**
     * 8000
     */
    SMS_ERROR(8000, "短信通道异常"),
    /**
     * 8001
     */
    SMS_INTERVAL_TIME(8001, "连续发送短信时间间隔过短"),
    /**
     * 8002
     */
    SMS_OVER_FLOW(8002, "发送次数过多"),
    /**
     * 9998
     */
    SERVICE_ERROR(9998, "服务异常");
    /**
     * 业务码
     */
    private int code;
    /**
     * 错误描述
     */
    private String description;

    ServiceBusinessCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
