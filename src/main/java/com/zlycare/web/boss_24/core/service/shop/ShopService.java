package com.zlycare.web.boss_24.core.service.shop;

import com.zlycare.web.boss_24.core.service.bo.ShopBo;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/6/19
 * Update : 2017/6/19
 * Descriptions :
 */
public interface ShopService {
    //interface 声明一个借口
    //一个类通过 继承-接口 的方式，从而来 继承接口的抽象方法

    /**
     * 查询商户列表，参数待定，当前登录用户，业务板块。
     *
     * @return List<ShopBo>
     */
    List<ShopBo> getShops();


    /**
     * 查询全城够-商户列表
     *
     * @param userArea  用户包含业务地域
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param status    状态 全部 审核通过 不通过 未审核
     * @param start     开始行数
     * @param pageSize  查询数量
     * @return List<ShopBo>
     */
    List<ShopBo> getAllList(
            List<String> shopType,
            List<String> userArea,
            Long startDate,
            Long endDate,
            List<Integer> status,
            Integer start,
            Integer pageSize);

    /**
     * 查询全城够-商户列表-数量
     *
     * @param userArea  用户包含业务地域
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param status    状态 全部 审核通过 不通过 未审核
     * @param start     开始行数
     * @param pageSize  查询数量
     * @return Integer
     */
    Integer countAllList(
            List<String> shopType,
            List<String> userArea,
            Long startDate,
            Long endDate,
            List<Integer> status,
            Integer start,
            Integer pageSize);

    /**
     * 审核 商户申请 信息
     *
     * @param shopId 申请id
     * @param status 审核状态
     * @param reason 拒绝原因 可空
     * @return 结果
     */
    boolean auditShopApplication(String shopId, Integer status, String reason);
}
