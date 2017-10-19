package com.zlycare.web.boss_24.core.serviceImpl.productManage;


import com.zlycare.web.boss_24.core.mongo.order.FeedBackMongo;
import com.zlycare.web.boss_24.core.mongo.order.UsersMongo;
import com.zlycare.web.boss_24.core.mongo.po.FeedBack;
import com.zlycare.web.boss_24.core.mongo.po.Users;
import com.zlycare.web.boss_24.core.service.bo.FeedBackBo;
import com.zlycare.web.boss_24.core.service.productManage.FeedBackService;
import com.zlycare.web.boss_24.core.serviceImpl.shop.ShopServiceImpl;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by MRZ on 2017/9/1.
 */
    @Service
    public class FeedBackServiceImpl implements FeedBackService {
    private static Logger logger = LoggerFactory.getLogger(FeedBackServiceImpl.class);
    //用户表名字
    private static final String USERS = "users";
    //反馈信息表
    private static final String SUGGESTIONS = "suggestions";
    //主键
    private static final String ID = "_id";
    private static final String userId = "userId";

    @Autowired
    private FeedBackMongo feedBackMongo; //创建一个FeedBackMongo对象
    @Autowired
    private UsersMongo usersMongo;

    @Override
    public List<FeedBackBo> getSuggestions() {
        return null;
    }

    /**
     * 查询意见反馈列表
     * @param status    状态
     * @param start     开始行数
     * @param pageSize  查询数量
     * @return
     */
    @Transactional//事务管理
    @Override
    public List<FeedBackBo> getAllList(List<Integer> status, Integer start, Integer pageSize) {//方法+参数

        if (pageSize == null || start == null  ) {
            logger.error("查询意见反馈信息失败，param is null");
            return null;
        }

        List<FeedBack> backList = feedBackMongo.getAllList(status, start, pageSize);
        if(CollectionUtils.isEmpty(backList)){
            logger.debug("查询反馈信息失败，数据为null");
            return new ArrayList<>();
        }
        for (FeedBack feedBack:backList){
            if(StringUtils.isEmpty(feedBack.getUserId())){
                //TODO feedBack.getUserId()==NULL userid==null
                logger.error("当FeedBack无uid直接跳过");
                continue;
            }
            String userId = feedBack.getUserId();
            if (StringUtils.isNotEmpty(userId)) {
                // 根据userId查找用户信息
                Users user = usersMongo.findOne(new Query(Criteria.where(ID).is(userId)), USERS);
                if (user != null) {
                    feedBack.setName(user.getName());
                    feedBack.setDocChatNum(user.getDocChatNum());
                }
            }
        }


        List<FeedBackBo> feedBackBoList = BeanMapper.mapList(backList, FeedBackBo.class);
        return feedBackBoList;

    }

    /**
     * 查询意见反馈信息
     *
     * @param status    状态 全部 审核通过 不通过 未审核
     * @param start     开始行数
     * @param pageSize  查询数量
     * @return Integer
     */
    @Override
    public Integer countAllList(List<Integer> status, Integer start, Integer pageSize) {
//        if (pageSize == null || start == null ) {
//            logger.error("查询反馈信息失败，param is null");
//            return null;
//        }
//        Query query = new Query().limit(pageSize).skip(start);
//        Criteria criteria;
//        if (CollectionUtils.isNotEmpty(status)) {
//            query.addCriteria(Criteria.where("status").in(status));
//        }
//        query.with(new Sort(Sort.Direction.DESC, "createdAt"));
//
//        Long count = feedBackMongo.count(query, SUGGESTIONS);
        return feedBackMongo.countAllList(status, start, pageSize);
    }

    /**
     * 获取对象
     * @param id
     * @return
     */
    @Override
    public FeedBackBo findById(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("查询id失败，param is null");
            return null;
        }
//        Query query = new Query(Criteria.where(ID).is(id));
//        FeedBack feedBack =feedBackMongo.findOne(query,SUGGESTIONS);
//        return BeanMapper.map(feedBack,FeedBackBo.class);
        FeedBack feedBack =feedBackMongo.findById(id);
        if(feedBack==null){
            logger.debug("获取反馈信息失败，id为" + id);
            return null;
        }
        return BeanMapper.map(feedBack,FeedBackBo.class);
    }

    @Override
    public boolean updateFeedBack(FeedBackBo feedBackBo) {
        if(feedBackBo ==null){
            throw new NullPointerException("feedBackBo  is null");
        }

//        if (StringUtils.isEmpty(feedBackBo.getId())){
//            logger.error("修改反馈信息失败，param is null");
//            return false;
//        }
        feedBackMongo.updateFeedBack(feedBackBo);
        return true;
    }


}
