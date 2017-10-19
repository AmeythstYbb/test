package com.zlycare.web.boss_24.core.mongo.order;

import com.zlycare.web.boss_24.beans.exception.ServiceException;
import com.zlycare.web.boss_24.core.mongo.po.MemberProduct;
import com.zlycare.web.boss_24.mongo.base.MongoBaseDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Author : LinGuoDong
 * Create : 2016/5/10 12:05
 * Update : 2016/5/10 12:05
 * Descriptions :
 */
@Repository
public class MemberProductMongo extends MongoBaseDao<MemberProduct> {
    private static Logger logger = LoggerFactory.getLogger(MemberProductMongo.class);
    //主键
    private static final String ID = "_id";
    //会员产品清单列表集合名称
    private static final String VIP_MEMBER_PRODUCT = "vipMemberProducts";

    @Override
    protected Class<MemberProduct> getEntityClass() {
        return MemberProduct.class;
    }

    @Override
    public void insert(MemberProduct bean) {
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
    public void create(MemberProduct memberProduct) {
        //serveRule.setId(sequenceDao.getServeRuleSeqId());
        super.save(memberProduct);
    }

    /**
     * 创建
     */
    public void create(MemberProduct memberProduct, String collectionName) {
        if (memberProduct == null || StringUtils.isEmpty(collectionName)) {
            return;
        }
        //serveRule.setId(sequenceDao.getServeRuleSeqId());
        super.save(memberProduct, collectionName);
    }

    /**
     * 删除目录，不区分类型
     */
    public void deleteProductById(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("删除产品目录信息失败，Id为空");
            return;
        }
        Update update = new Update();
        update.set("isDeleted", true);
        // 修改时间
        Long time = new Date().getTime();
        update.set("updatedAt", time.doubleValue());
        update.set("statisticsUpdatedAt", time.doubleValue());

        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(id));
        MemberProduct memberProduct = this.findAndModify(query, update, VIP_MEMBER_PRODUCT);//findAndModify 返回的是修改之前的对象
        if (memberProduct == null || com.zlycare.web.boss_24.utils.other.StringUtils.isEmpty(memberProduct.getId())) {
            logger.error("删除失败");
            throw new ServiceException();
        }
    }
}
