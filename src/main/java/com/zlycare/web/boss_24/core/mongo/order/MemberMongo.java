package com.zlycare.web.boss_24.core.mongo.order;

import com.zlycare.web.boss_24.core.mongo.bean.MemberSearchBean;
import com.zlycare.web.boss_24.core.mongo.po.MemberShips;
import com.zlycare.web.boss_24.mongo.base.MongoBaseDao;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Author : LinGuoDong
 * Create : 2016/5/10 12:05
 * Update : 2016/5/10 12:05
 * Descriptions :
 */
@Repository
public class MemberMongo extends MongoBaseDao<MemberShips> {
    private static final Logger logger = LoggerFactory.getLogger(MemberMongo.class);
    // 会员表
    private static final String MEMBER_SHIPS = "memberships";
    // 主键
    private static final String ID = "_id";
    // 管道字段
    private static final String USER_ID = "userId";
    // 管道字段
    private static final String TYPE = "type";
    // 默认来源
    private static final String SOURCE = "docChat";

    @Override
    protected Class<MemberShips> getEntityClass() {
        return MemberShips.class;
    }

    @Override
    public void insert(MemberShips bean) {
        //bean.setId(sequenceDao.getServeRuleSeqId());
        super.insert(bean);
    }

    @Autowired
    @Qualifier("mongoTemplate")
    @Override
    protected void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 创建
     */
    public void create(MemberShips memberShips) {
        //serveRule.setId(sequenceDao.getServeRuleSeqId());
        super.save(memberShips);
    }

    /**
     * 查询
     */
    public List<MemberShips> getAllList(MemberSearchBean memberSearchBean) {
        Integer pageSize = memberSearchBean.getPageSize();
        Integer start = memberSearchBean.getStart();
        Double currentTime = memberSearchBean.getCurrentTime();
        List<String> typeList = memberSearchBean.getTypeList();
        List<String> userIds = memberSearchBean.getUserIds();

        if (pageSize == null || start == null || currentTime == null || CollectionUtils.isEmpty(typeList)) {
            logger.error("查询会员失败，参数为空");
            return new ArrayList<>();
        }
        Criteria criteria;
        if (CollectionUtils.isNotEmpty(userIds)) {
            criteria = Criteria.where(USER_ID).in(userIds);
        } else {
            criteria = new Criteria();
        }
        criteria.andOperator(
                Criteria.where("isDeleted").is(false),
                Criteria.where("source").is(SOURCE),
                Criteria.where(TYPE).in(typeList),
                Criteria.where(TYPE).exists(true),
                Criteria.where("expiredAt").gt(currentTime));

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group(USER_ID, TYPE).sum("totalVal").as("totalVal").sum("balance").as("balance"),
                Aggregation.skip(start),// fuck,skip必须放在limit之前
                Aggregation.limit(pageSize));
        AggregationResults<MemberShips> memberShipses = mongoTemplate.aggregate(agg, MEMBER_SHIPS, MemberShips.class);
        List<MemberShips> memberShipsList = new ArrayList<>();
        Iterator iter = memberShipses.iterator();
        while (iter.hasNext()) {
            memberShipsList.add((MemberShips) iter.next());
        }

//        // todo 开始写聚合查询函数
//        修改接收参数 接收管道字段值
//        db.getCollection('memberships').aggregate([{$match:{
//            "isDeleted":false, expiredAt:{
//                $gt:
//                1501847975000
//            },"source":"docChat", userId:{
//                $in:["57d77e24f52e142136bd8573"]},type:
//            {
//                $in:["zlycare", "zlycare_vip"]}
//        }},{
//            $group:
//            {
//                _id:
//                {
//                    userId:
//                    "$userId", type:"$type"
//                },advancedTotalVal:
//            {
//                $sum:
//                "$totalVal"
//            },advancedBalance:
//            {
//                $sum:
//                "$balance"
//            }
//            }
//        }])
        if (CollectionUtils.isEmpty(memberShipsList)) {
            //logger.error("查询会员信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        return memberShipsList;
    }

    /**
     * 查询
     */
    public Integer countAllList(MemberSearchBean memberSearchBean) {
        Double currentTime = memberSearchBean.getCurrentTime();
        List<String> typeList = memberSearchBean.getTypeList();
        List<String> userIds = memberSearchBean.getUserIds();

        if (currentTime == null || CollectionUtils.isEmpty(typeList)) {
            logger.error("查询会员失败，参数为空");
            return null;
        }
        Criteria criteria;
        if (CollectionUtils.isNotEmpty(userIds)) {
            criteria = Criteria.where(USER_ID).in(userIds);
        } else {
            criteria = new Criteria();
        }

        //GroupBy groupBy = GroupBy.key(USER_ID).initialDocument("{count:0}")
        //        .reduceFunction("function(doc, prev){prev.count+=1}");
        criteria.andOperator(
                Criteria.where("isDeleted").is(false),
                Criteria.where("source").is(SOURCE),
                Criteria.where(TYPE).in(typeList),
                Criteria.where(TYPE).exists(true),
                Criteria.where("expiredAt").gt(currentTime));

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group(USER_ID).count().as("count"));
        List list = this.distinct(USER_ID, MEMBER_SHIPS, new Query(criteria));
        //AggregationResults<MemberShips> memberShipses = mongoTemplate.aggregate(agg, MEMBER_SHIPS, MemberShips.class);

        //GroupByResults g = mongoTemplate.group(criteria, MEMBER_SHIPS, groupBy, Map.class);

        //Long count = this.count(new Query(criteria), MEMBER_SHIPS);
        //return g.getRawResults().k;
        return CollectionUtils.isNotEmpty(list) ? list.size() : 0;
    }

}
