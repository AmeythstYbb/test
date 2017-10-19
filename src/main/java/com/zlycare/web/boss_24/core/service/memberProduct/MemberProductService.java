package com.zlycare.web.boss_24.core.service.memberProduct;


import com.zlycare.web.boss_24.core.service.bo.MemberProductBo;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/6/29
 * Update : 2017/6/29
 * Descriptions :
 */
public interface MemberProductService {
    /**
     * 获取当前对象
     *
     * @param id id
     * @return MemberProductBo
     */
    MemberProductBo getMemberProductById(String id);

    /**
     * 查询产品清单列表
     *
     * @param productName productName
     * @param startDate   开始日期
     * @param endDate     结束日期
     * @param status      状态 全部 审核通过 不通过 未审核
     * @param start       开始行数
     * @param pageSize    查询数量
     * @param type        类目
     * @param subType     子类目
     * @return List<MemberProductBo>
     */
    List<MemberProductBo> getAllList(
            String jobNumber,
            String productName,
            Long startDate,
            Long endDate,
            List<Integer> status,
            Integer start,
            Integer pageSize,
            String type,
            String subType,
            String vipType,
            String firstType,
            String secondType,
            String thirdType
    );

    /**
     * 查询产品清单数量
     *
     * @param productName productName
     * @param startDate   开始日期
     * @param endDate     结束日期
     * @param status      状态 全部 审核通过 不通过 未审核
     * @param start       开始行数
     * @param pageSize    查询数量
     * @param type        类目
     * @param subType     子类目
     * @return Integer
     */
    Integer countAllList(
            String jobNumber,
            String productName,
            Long startDate,
            Long endDate,
            List<Integer> status,
            Integer start,
            Integer pageSize,
            String type,
            String subType,
            String vipType,
            String firstType,
            String secondType,
            String thirdType
    );

    /**
     * 掺入 and 修改
     *
     * @param memberProductBo memberProductBo
     */
    void saveMemberProduct(MemberProductBo memberProductBo);

    /**
     * 审核 产品申请 信息
     *
     * @param productId 申请id
     * @param status    审核状态
     * @param reason    拒绝原因 可空
     * @return 结果
     */
    boolean auditProduct(String productId, Integer status, String reason);

    /**
     * 审核 产品申请 信息 通过
     *
     * @param productId 申请id
     * @param status    审核状态
     * @return 结果
     */
    boolean auditProductPass(String productId,
                             Integer status,
                             String vipType,
                             String servicePeopleDocChatNum,
                             String servicePeopleId,
                             String servicePeopleImUserName,
                             String servicePeopleCall,
                             String servicePeopleName,
                             Double realPrice,
                             String type,
                             String subType,
                             String productName);

    /**
     * 修改 产品
     *
     * @param productId 申请id
     * @return 结果
     */
    boolean updateProduct(String productId,
                          String vipType,
                          String servicePeopleDocChatNum,
                          String servicePeopleId,
                          String servicePeopleImUserName,
                          String servicePeopleCall,
                          String servicePeopleName,
                          Double realPrice,
                          String type,
                          String subType,
                          String productName);

    /**
     * 审核员 修改 产品
     *
     * @param memberProductBo memberProductBo
     * @return 结果
     */
    boolean updateProduct(MemberProductBo memberProductBo);


    /**
     * 上下线
     *
     * @param productId productId
     * @param online    online
     * @param onlineRemark    onlineRemark
     * @return boolean
     */
    boolean online(String productId, Integer online,String onlineRemark);

    /**
     * 删除
     *
     * @param productId productId
     * @return boolean
     */
    boolean delete(String productId);

    /**
     * 查询一级类目
     *
     * @return List<String>
     */
    List<String> findTypeList();

    /**
     * 查询二级类目
     *
     * @param type 一级类目名称
     * @return List<String>
     * todo 代码可能有性能问题
     */
    List<String> findSubTypeList(String type);
}
