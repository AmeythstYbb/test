package com.zlycare.web.boss_24.core.mongo.log;

import com.zlycare.web.boss_24.core.mongo.po.BossLog;
import com.zlycare.web.boss_24.mongo.base.MongoBaseDao;
import com.zlycare.web.boss_24.mongo.base.SequenceDao;
import com.zlycare.web.boss_24.mongo.enums.CollectionsEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Author : zhanglianwei
 * Create : 2017/6/1 18:05
 * Update : 2017/6/1 18:05
 * Descriptions :
 */
@Repository
public class BossLogMongo extends MongoBaseDao<BossLog> {
    @Autowired
    private SequenceDao sequenceDao;

    @Override
    protected Class<BossLog> getEntityClass() {
        return BossLog.class;
    }

    @Override
    public void insert(BossLog bean) {
        super.insert(bean, CollectionsEnum.BOSS_LOG.getName());
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
    public void create() {
        if (this.mongoTemplate != null) {
            if (!this.mongoTemplate.collectionExists(CollectionsEnum.BOSS_LOG.getName())) {
                this.mongoTemplate.createCollection(CollectionsEnum.BOSS_LOG.getName());
            }
        }
    }
}
