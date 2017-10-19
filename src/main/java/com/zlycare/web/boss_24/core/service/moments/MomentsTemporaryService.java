package com.zlycare.web.boss_24.core.service.moments;

import com.zlycare.web.boss_24.core.mongo.po.MomentsTemporary;
import com.zlycare.web.boss_24.core.service.bo.MomentsTemporaryBasicBo;
import com.zlycare.web.boss_24.core.service.bo.MomentsTemporaryBo;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/6/19
 * Update : 2017/6/19
 * Descriptions : 操作excel_moments service
 */
public interface MomentsTemporaryService {

    /**
     * 批量插入
     *
     * @param momentsTemporaryBoList momentsTemporaryBoList
     * @return boolean
     */
    boolean createBatch(List<MomentsTemporaryBasicBo> momentsTemporaryBoList);


    /**
     * 根据批次ID查询数据,含列表、批次ID、是否可提交信息,包含校验
     *
     * @param batch batch
     * @return list
     */
    MomentsTemporaryBo getAllMomentsTemporaryByBatchId(Double batch);

    /**
     * 根据批次ID查询数据,含列表、批次ID、是否可提交信息,不校验,不返回封装对象。查询列表
     *
     * @param batch batch
     * @return list
     */
    List<MomentsTemporaryBasicBo> getAllMomentsTemporaryListByBatchId(Double batch);


    /**
     * 根据ID查询一条动态内容并返回
     *
     * @param id id
     * @return MomentsTemporaryBasicBo
     */
    MomentsTemporaryBasicBo getMomentsTemporaryById(String id);

    /**
     * 修改一条动态
     *
     * @param momentsTemporaryBasicBo momentsTemporaryBasicBo
     * @return boolean
     */
    boolean updateMomentsTemporary(MomentsTemporaryBasicBo momentsTemporaryBasicBo);


    /**
     * 当前批次上传成功个数查询
     *
     * @param batch      batch
     * @param uploadFlag uploadFlag
     * @return Integer
     */
    Integer count(Double batch, Integer uploadFlag);

    /**
     * 指定批次指定上传状态下列表查询
     *
     * @param batch      batch
     * @param uploadFlag uploadFlag
     * @return List<MomentsTemporaryBo>
     */
    List<MomentsTemporaryBasicBo> getMomentsTemporaryListByBatchAndUploadFlag(Double batch, Integer uploadFlag);

//    /**
//     * 查询商户列表，参数待定，当前登录用户，业务板块。
//     *
//     * @return List<ShopBo>
//     */
//    List<ShopBo> getShops();
//
//
//    /**
//     * 查询全城够-商户列表
//     *
//     * @param userArea  用户包含业务地域
//     * @param startDate 开始日期
//     * @param endDate   结束日期
//     * @param status    状态 全部 审核通过 不通过 未审核
//     * @param start     开始行数
//     * @param pageSize  查询数量
//     * @return List<ShopBo>
//     */
//    List<ShopBo> getAllList(
//            List<String> shopType,
//            List<String> userArea,
//            Long startDate,
//            Long endDate,
//            List<Integer> status,
//            Integer start,
//            Integer pageSize);
//
//    /**
//     * 查询全城够-商户列表-数量
//     *
//     * @param userArea  用户包含业务地域
//     * @param startDate 开始日期
//     * @param endDate   结束日期
//     * @param status    状态 全部 审核通过 不通过 未审核
//     * @param start     开始行数
//     * @param pageSize  查询数量
//     * @return Integer
//     */
//    Integer countAllList(
//            List<String> shopType,
//            List<String> userArea,
//            Long startDate,
//            Long endDate,
//            List<Integer> status,
//            Integer start,
//            Integer pageSize);
//
//    /**
//     * 审核 商户申请 信息
//     *
//     * @param shopId 申请id
//     * @param status 审核状态
//     * @param reason 拒绝原因 可空
//     * @return 结果
//     */
//    boolean auditShopApplication(String shopId, Integer status, String reason);
}
