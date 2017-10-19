package com.zlycare.web.boss_24.core.mongo.order;

import com.zlycare.web.boss_24.core.mongo.bean.VerificationRecordBean;
import com.zlycare.web.boss_24.core.mongo.po.VerificationRecord;
import com.zlycare.web.boss_24.mongo.base.MongoBaseDao;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author : LinGuoDong
 * Create : 2016/5/10 12:05
 * Update : 2016/5/10 12:05
 * Descriptions :
 */
@Repository
public class VerificationRecordMongo extends MongoBaseDao<VerificationRecord> {
    //会员产品清单列表集合名称
    private static final String VIP_MEMBER_TRADES = "vipMemberTrades";
    //主键
    private static final String ID = "_id";
    // 类型
    private static final String type = "use";

    @Override
    protected Class<VerificationRecord> getEntityClass() {
        return VerificationRecord.class;
    }

    @Override
    public void insert(VerificationRecord bean) {
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
    public void create(VerificationRecord verificationRecord) {
        //serveRule.setId(sequenceDao.getServeRuleSeqId());
        super.save(verificationRecord);
    }

    /**
     * 查询
     */
    public List<VerificationRecord> getAllList(VerificationRecordBean verificationRecordBean) {
        Integer pageSize = verificationRecordBean.getPageSize();
        Integer start = verificationRecordBean.getStart();
        Long startDate = verificationRecordBean.getStartDate();
        Long endDate = verificationRecordBean.getEndDate();
        String userId = verificationRecordBean.getUserId();
        String checkVenderId = verificationRecordBean.getCheckVenderId();
        String productName = verificationRecordBean.getProductName();
        String isChecked = verificationRecordBean.getChecked_status();
        if (pageSize == null || start == null) {
            return new ArrayList<>();
        }
        Query query = new Query(Criteria.where("type").is(type)).limit(pageSize).skip(start);
        Criteria criteria;
        if (StringUtils.isNotEmpty(userId)) {
            query.addCriteria(Criteria.where("userId").is(userId));
        }
        if (StringUtils.isNotEmpty(checkVenderId)) {
            query.addCriteria(Criteria.where("checkVenderId").is(checkVenderId));
        }
        if (startDate != null && endDate != null) {
            criteria = Criteria.where("createdAt").gte(startDate).lte(endDate);
            query.addCriteria(criteria);
        }
        if (startDate != null && endDate == null) {
            criteria = Criteria.where("createdAt").gte(startDate);
            query.addCriteria(criteria);
        }
        if (startDate == null && endDate != null) {
            criteria = Criteria.where("createdAt").lte(endDate);
            query.addCriteria(criteria);
        }
        if (StringUtils.isNotEmpty(isChecked)) {
            if (isChecked.equals("1")) {
                query.addCriteria(Criteria.where("isChecked").is(true));
            } else if (isChecked.equals("0")) {
                query.addCriteria(Criteria.where("isChecked").is(false));
            }
        }
        if (StringUtils.isNotEmpty(productName)) {
            query.addCriteria(Criteria.where("productName").regex(productName));
        }
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        List<VerificationRecord> verificationRecordList = this.find(query, VIP_MEMBER_TRADES);
        if (CollectionUtils.isEmpty(verificationRecordList)) {
            //logger.error("查询产品清单信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        return verificationRecordList;
    }

    /**
     * 查询
     */
    public Integer countAllList(VerificationRecordBean verificationRecordBean) {
        Long startDate = verificationRecordBean.getStartDate();
        Long endDate = verificationRecordBean.getEndDate();
        String userId = verificationRecordBean.getUserId();
        String checkVenderId = verificationRecordBean.getCheckVenderId();
        String productName = verificationRecordBean.getProductName();
        String isChecked = verificationRecordBean.getChecked_status();
        Query query = new Query(Criteria.where("type").is(type));
        Criteria criteria;
        if (StringUtils.isNotEmpty(userId)) {
            query.addCriteria(Criteria.where("userId").is(userId));
        }
        if (StringUtils.isNotEmpty(checkVenderId)) {
            query.addCriteria(Criteria.where("checkVenderId").is(checkVenderId));
        }
        if (startDate != null && endDate != null) {
            criteria = Criteria.where("createdAt").gte(startDate).lte(endDate);
            query.addCriteria(criteria);
        }
        if (startDate != null && endDate == null) {
            criteria = Criteria.where("createdAt").gte(startDate);
            query.addCriteria(criteria);
        }
        if (startDate == null && endDate != null) {
            criteria = Criteria.where("createdAt").lte(endDate);
            query.addCriteria(criteria);
        }
        if (StringUtils.isNotEmpty(isChecked)) {
            if (isChecked.equals("1")) {
                query.addCriteria(Criteria.where("isChecked").is(true));
            } else if (isChecked.equals("0")) {
                query.addCriteria(Criteria.where("isChecked").is(false));
            }
        }
        if (StringUtils.isNotEmpty(productName)) {
            query.addCriteria(Criteria.where("productName").regex(productName));
        }
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        Long count = this.count(query, VIP_MEMBER_TRADES);

        return count.intValue();
    }
}
