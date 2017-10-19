package com.zlycare.web.boss_24.beans.constants;

/**
 * 订单状态枚举
 *
 * @author DaiJian
 * @create 2015/11/20 11:03
 */
public enum OrderStatusConstant {
    /**
     * 取消订单
     */
    CANCEL_ORDER(-1),
    /**
     * 客户发布需求
     */
    CUSTOMER_PUBLISH_REQUIREMENT(0),
    /**
     * 匹配顾问
     */
    ASSIGN_KEEPER(10),
    /**
     * 顾问完成需求详单
     */
    KEEPER_PROVIDE_REQUIREMENT(20),
    /**
     * 客户确认订单
     */
    CUSTOMER_CONFIRM_ORDER(30),
    /**
     * 客户支付定金
     */
    CUSTOMER_PAY_DEPOSIT(40),
    /**
     * 顾问向客户发送调研问卷
     */
    KEEPER_SEND_QUESTIONNAIRE_TO_CUSTOMER(41),
    /**
     * 客户提交调研问卷(给管家)
     * (没有调研问卷的订单在客户支付完定金[30->40]之后直接变更状态[40->45])
     */
    CUSTOMER_PROVIFR_QUESTIONNAIRE_TO_KEEPER(45),
    /**
     * 顾问提供深化调研报告
     */
    KEEPER_PROVIDE_DEEP_RESEARCH(50),

    /**
     * 客户确认深化调研报告
     */
    CUSTOMER_CONFIRM_DEEP_RESEARCH(60),

    /**
     * 客户支付首款
     */
    CUSTOMER_PAY_FIRST(70),

    /**
     * 匹配创意人
     */
    ASSIGN_SUPPLIER(80),

    /**
     * 创意人接单
     */
    SUPPLIER_ACCEPT_ORDER(90),

    /**
     * 创意人提供初稿
     */
    // todo 6.28 被注释
    SUPPLIER_PROVIDE_FIRST_SOLUTION(110),

    /**
     * 顾问审核初稿
     */
    KEEPER_AUDIT_FIRST_SOLUTION(120),

    /**
     * 客户确认初稿
     */
    CUSTOMER_CONFIRM_FIRST_SOLUTION(130),

    /**
     * 客户支付中款
     */
    CUSTOMER_PAY_SECOND(140),

    /**
     * 创意人确认中款
     */
    SUPPLIER_CONFIRM_SECOND_PAY(150),

    /**
     * 创意人提供深化设计一稿、 深化设计一稿顾问审核中(不通过、重提、重审、待审)
     */
    SUPPLIER_PROVIDE_DEEP_SOLUTION_FIRST(160),

    /**
     * 顾问审核深化设计一稿通过
     */
    KEEPER_AUDIT_FIRST_DEEP_SOLUTION_PASS(170),
    /**
     * 顾问审核深化设计一稿不通过 ：废除
     */
    /*KEEPER_AUDIT_FIRST_DEEP_SOLUTION_NOT_PASS(180),*/

    /**
     * 客户审核深化设计一稿未通过
     */
    CUSTOMER_AUDIT_FIRST_DEEP_SOLUTION_NOT_PASS(190),

    /**
     * 创意人提供深化设计二稿、 深化设计二稿顾问审核中(不通过、重提、重审、待审)
     */
    SUPPLIER_PROVIDE_DEEP_SOLUTION_SECOND(200),

    /**
     * 顾问审核深化设计二稿通过
     */
    KEEPER_AUDIT_SECOND_DEEP_SOLUTION_PASS(210),
    /**
     * 顾问审核深化设计二稿不通过 : 废除
     */
    /*KEEPER_AUDIT_SECOND_DEEP_SOLUTION_NOT_PASS(220),*/

    /**
     * 客户审核深化设计二稿未通过
     */
    CUSTOMER_AUDIT_SECOND_DEEP_SOLUTION_NOT_PASS(230),

    /**
     * 创意人提供深化设计三稿、 深化设计三稿顾问审核中(不通过、重提、重审、待审)
     */
    SUPPLIER_PROVIDE_DEEP_SOLUTION_THIRD(240),

    /**
     * 顾问审核深化设计三稿通过
     */
    KEEPER_AUDIT_THIRD_DEEP_SOLUTION_PASS(250),
    /**
     * 顾问审核深化设计三稿不通过 : 废除
     */
    /*KEEPER_AUDIT_THIRD_DEEP_SOLUTION_NOT_PASS(260),*/

    /**
     * 客户确认终稿
     */
    CUSTOMER_CONFIRM_FINAL_SOLUTION(270),

    /**
     * 客户完成评价
     */
    CUSTOMER_FINISH_EVALUATION(280),

    /**
     * 客户支付尾款
     */
    CUSTOMER_PAY_THIRD(290),

    /**
     * 创意人完成评价
     */
    CP_FINISH_EVALUATION(350),

    /**
     * 创意人确认收到尾款
     */
    SUPPLIER_CONFIRM_LAST_PAY(295),

    /**
     * 创意人提供源文件、 源文件顾问审核中(不通过、重提、重审、待审)
     */
    SUPPLIER_PROVIDE_FILE(300),

    /**
     * 顾问审核源文件通过
     */
    KEEPER_AUDIT_FINAL_FILE_PASS(310),
    /**
     * 顾问审核源文件不通过 : 废除
     */
    /*KEEPER_AUDIT_FINAL_FILE_NOT_PASS(320),*/

    /**
     * 客户确认源文件
     */
    CUSTOMER_CONFIRM_FINAL_FILE(330),

    /**
     * 顾问完成评价
     */
    KEEPER_FINISH_EVALUATION(340),

    /**
     * 顾问收款审核通过
     */
    KEEPER_CONFIRM_PAY(360),

    /**
     * 向客户赠送稻米
     */
    PRESENT_DAOMI(380),

    /**
     * 项目完成
     */
    FINISH_PROJECT(390),

    // todo 消息推送模版使用状态码, 不在订单流程中使用, 仅用来替代订单流程中不存在的状态从消息模版库中取信息模版。

    /**
     * 客户驳回调研报告
     */
    CUSTOMER_AUDIT_REPORT_UN_PASS(1000),

    /**
     * 创意人拒单
     */
    CP_REFUSE_ORDER(1010),

    /**
     * 创意人提交深化设计
     */
    CP_PROVIDE_DEEP_RESIGN(1020),

    /**
     * 顾问审核初稿通过
     */
    KEEPER_AUDIT_FIRST_SOLUTION_ONE_PASS(1030),

    /**
     * 顾问审核初稿不通过
     */
    KEEPER_AUDIT_FIRST_SOLUTION_ONE_UN_PASS(1040),

    /**
     * 客户选择初稿未中标
     */
    CUSTOMER_AUDIT_FIRST_SOLUTION_UN_PASS(1050),

    /**
     * 顾问审核深化设计不通过
     */
    KEEPER_AUDIT_DEEP_RESIGN_UN_PASS(1060),

    /**
     * 顾问审核源文件不通过
     */
    KEEPER_AUDIT_FINAL_FILE_UN_PASS(1070),;

    /**
     * 状态码
     */
    private int statusCode;

    OrderStatusConstant(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
