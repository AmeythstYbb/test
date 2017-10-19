package com.zlycare.web.boss_24.beans.constants;

/**
 * Package Name: com.dongdaodao.beans.constants
 * Author: WuZheng
 * Create Date: 2016/8/24 17:21
 * Description:
 */
public enum SerialGenerateRuleEnum {
//    WD+1位默认代码+2位渠道代码+2位业务类型代码+10位无序数字
//    DD+1位收款代码+2位渠道代码+2位业务类型代码+10位无序数字
//    DD+1位收款代码+2位渠道代码+2位业务类型代码+10位无序数字

    //1.前缀
    WITHDRAW_PREFIX(1, "WD"),
    PAY_PREFIX(2, "DD"),

    //2.收款代码 1位
    PAY_CODE_INCOME(1, "1"), //收款（以公司为主体，客户为客体，即客户付给我们的钱）
    PAY_CODE_PAY(2, "2"), //付款 （以公司为主体，客户为客体，即我们给别人的钱）

    //2.提现默认代码 1位
    WITHDRAW_DEFAULT(3, "3"),

    //3.渠道代码 2位
    CHANNEL_WITHDRAW(0, "00"), //提现
    CHANNEL_ALI_PAY(1, "01"), //支付宝
    CHANNEL_RICE_PAY(2, "02"), //稻米
    CHANNEL_YEE_PAY(3, "03"), //易宝
    CHANNEL_LIAN_LIAN_PAY(4, "04"), //连连
    CHANNEL_CMS_RECHARGE(5, "05"), //CMS系统充值

    //4.业务类型代码 2位
    FUND_PRE(2, "00"), //定金 客户支付
    FUND_FIRST(3, "01"), //首款 客户支付
    FUND_MEDIUM(4, "02"), //中款 客户支付
    FUND_TAIL(5, "03"), //尾款 客户支付
    FUND_KEEPER_WITHDRAW(5, "04"), //顾问提现
    FUND_CP_WITHDRAW(6, "05"), //创意人提现
    FUND_TOP_UP(1, "06"), //充值

    //5.业务类型代码
//    SERVICE_BRAND_STRATEGY("01"),//品牌策略
//    SERVICE_BRAND_DESIGN("02"),//品牌设计
//    SERVICE_BRAND_SPREAD("03"),//品牌传播
//    SERVICE_BRAND_DIGITIZE("04"),//品牌数字化
//    SERVICE_PACKAGE_DESIGN("05"),//包装设计
//    SERVICE_SPACE_DESIGN("06"),// 商业空间设计
//    SERVICE_WORKSHOP("07"),// 制作工厂
//    SERVICE_ADVERTISEMENT("08"),// 广告
    ;

    SerialGenerateRuleEnum(Integer code, String codeString) {
        this.code = code;
        this.codeString = codeString;
    }

    private Integer code;
    private String codeString;

    public String getCodeString() {
        return codeString;
    }

    public Integer getCode() {
        return code;
    }
}
