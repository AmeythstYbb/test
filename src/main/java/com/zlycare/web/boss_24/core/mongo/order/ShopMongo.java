package com.zlycare.web.boss_24.core.mongo.order;

import com.zlycare.web.boss_24.core.mongo.po.Shop;
import com.zlycare.web.boss_24.mongo.base.MongoBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Author : LinGuoDong
 * Create : 2016/5/10 12:05
 * Update : 2016/5/10 12:05
 * Descriptions :
 */
@Repository
public class ShopMongo extends MongoBaseDao<Shop> {

    @Override
    protected Class<Shop> getEntityClass() {
        return Shop.class;
    }

    @Override
    public void insert(Shop bean) {
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
    public void create(Shop shop) {
        //serveRule.setId(sequenceDao.getServeRuleSeqId());
        super.save(shop);
    }
}
