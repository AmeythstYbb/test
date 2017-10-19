package com.zlycare.web.boss_24.core.service.productManage;

import com.zlycare.web.boss_24.core.mongo.po.FeedBack;
import com.zlycare.web.boss_24.core.service.bo.FeedBackBo;


import java.util.List;

/**
 * Created by MRZ on 2017/8/31.
 */
public interface FeedBackService {
    /**
     * 查询用户
     * @return
     */
    List<FeedBackBo> getSuggestions();


    /**
     * 查询反馈信息-用户列表
     *
     * @param status    状态  待处理 已处理 忽略
     * @param start     开始行数
     * @param pageSize  查询数量
     * @return List<ShopBo>
     */
    List<FeedBackBo> getAllList(
            List<Integer> status,
            Integer start,
            Integer pageSize
    );
    /**
     * 查询反馈信息列表
     *
     * @param status    状态 待处理 已处理 忽略
     * @param start     开始行数
     * @param pageSize  查询数量
     * @return Integer
     */
    Integer countAllList(

            List<Integer> status,
            Integer start,
            Integer pageSize);

    /**
     *获取对象
     * @param id
     * @return
     */
    FeedBackBo findById(String id);

    /**
     * 修改反馈信息状态+备注
     * @param feedBackBo
     * @return
     */

    boolean updateFeedBack(FeedBackBo feedBackBo);



}
