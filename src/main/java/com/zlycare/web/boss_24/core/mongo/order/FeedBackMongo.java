package com.zlycare.web.boss_24.core.mongo.order;

import com.zlycare.web.boss_24.beans.exception.ServiceException;
import com.zlycare.web.boss_24.core.mongo.po.FeedBack;

import com.zlycare.web.boss_24.core.mongo.po.Users;
import com.zlycare.web.boss_24.core.service.bo.FeedBackBo;
import com.zlycare.web.boss_24.mongo.base.MongoBaseDao;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by MRZ on 2017/9/3.
 */
@Repository
public class FeedBackMongo  extends MongoBaseDao<FeedBack> {//继承抽象类
    private static final Logger logger = LoggerFactory.getLogger(FeedBackMongo.class);
    //主键
    private static final String ID = "_id";
    //反馈信息表
    private static final String SUGGESTIONS = "suggestions";
    //用户表名字
    private static final String USERS = "users";
    @Override//重写
    protected Class<FeedBack> getEntityClass() {
        return FeedBack.class;
    }

    @Override
    public void insert(FeedBack bean) {
        super.insert(bean);
    }

    @Autowired
    @Qualifier("mongoTemplate")
    @Override
    protected void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public void create(FeedBack feedBack){
        super.save(feedBack);
    }

    /**
     * 修改
     * @param feedBackBo
     */

    public void updateFeedBack(FeedBackBo feedBackBo){
        if(feedBackBo ==null){
            logger.error("修过信息失败");
            throw new ServiceException();
        }
        if(feedBackBo.getId()==null||feedBackBo.getStatus()==null){
            logger.error("修改信息为空,修改失败");
            return;
        }
        String id = feedBackBo.getId();
        Integer status =feedBackBo.getStatus();
        String remarks =feedBackBo.getRemarks();

        Update update =new Update();
        if (remarks!=null){
            update.set("remarks",remarks);
        }
        Long time = new Date().getTime();
        //update.set("remarks",remarks);
        update.set("status",status);
        update.set("updatedAt",time);
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(id));
        this.findAndModify(query,update,SUGGESTIONS);

    }
    public List<FeedBack> getAllList(List<Integer> status, Integer start, Integer pageSize){
        Query query = new Query().limit(pageSize).skip(start);
        Criteria criteria;
        if (CollectionUtils.isNotEmpty(status)) {
            query.addCriteria(Criteria.where("status").in(status));
        }
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        List<FeedBack> backList = this.find(query, SUGGESTIONS);

        if (CollectionUtils.isEmpty(backList)) {
            logger.error("查询意见反馈信息失败，搜索列表为空");
            return new LinkedList<>();
        }


        return backList;
    }
    public Integer countAllList(List<Integer> status, Integer start, Integer pageSize){
        if (pageSize == null || start == null ) {
            logger.error("查询反馈信息失败，param is null");
            return null;
        }
        Query query = new Query().limit(pageSize).skip(start);
        Criteria criteria;
        if (CollectionUtils.isNotEmpty(status)) {
            query.addCriteria(Criteria.where("status").in(status));
        }
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        Long count = this.count(query, SUGGESTIONS);
        return  count.intValue();
    }
    public FeedBack findById(String id){
        if (StringUtils.isEmpty(id)) {
            logger.error("查询id失败，param is null");
            return null;
        }
        Query query = new Query(Criteria.where(ID).is(id));
        FeedBack feedBack =this.findOne(query,SUGGESTIONS);
        if(feedBack==null){
            logger.error("修改反馈信息失败");
            // throw new ServiceException();
            return null;
        }
        return feedBack;
    }
}
