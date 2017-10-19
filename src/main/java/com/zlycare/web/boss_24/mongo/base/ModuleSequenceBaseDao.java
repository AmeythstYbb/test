package com.zlycare.web.boss_24.mongo.base;

import com.zlycare.web.boss_24.mongo.enums.CollectionsBaseEnum;
import com.zlycare.web.boss_24.mongo.po.ModuleSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * 模块自增序列Dao
 * @author lufanglong
 * @date 2016-07-16 13:35
 */
@Repository
public class ModuleSequenceBaseDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 获取序列ID
     *
     * @param collectionName 表名
     * @return Integer
     */
    protected Integer getModuleSequenceId(String collectionName) {
        ModuleSequence moduleSequence = mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(collectionName)),
                new Update().inc(ModuleSequence.getIncKey(), ModuleSequence.getInc()),
                FindAndModifyOptions.options().upsert(true),
                ModuleSequence.class, CollectionsBaseEnum.SEQUENCE.getName());
        if (moduleSequence != null) {
            return moduleSequence.getSeq();
        }
        return null;
    }

    /**
     * 获取序列ID,并增加inc
     *
     * @param collectionName 表名
     * @param inc            增幅
     * @return Integer
     */
    protected Integer getModuleSequenceId(String collectionName, Integer inc) {
        ModuleSequence moduleSequence = mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(collectionName)),
                new Update().inc(ModuleSequence.getIncKey(), inc),
                FindAndModifyOptions.options().upsert(true),
                ModuleSequence.class, CollectionsBaseEnum.SEQUENCE.getName());
        if (moduleSequence != null) {
            return moduleSequence.getSeq();
        }
        return null;
    }

    /**
     * 获取序列ID
     *
     * @param collectionName 表名
     * @return Integer
     */
    protected Long getModuleSequenceLongId(String collectionName) {
        ModuleSequence moduleSequence = mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(collectionName)),
                new Update().inc(ModuleSequence.getIncLongKey(), ModuleSequence.getInc()),
                FindAndModifyOptions.options().upsert(true),
                ModuleSequence.class, CollectionsBaseEnum.SEQUENCE.getName());
        if (moduleSequence != null) {
            return moduleSequence.getSeqLong();
        }
        return null;
    }

    /**
     * 获取序列ID,并增加inc
     *
     * @param collectionName 表名
     * @param inc            增幅
     * @return Long
     */
    protected Long getModuleSequenceLongId(String collectionName, Integer inc) {
        ModuleSequence moduleSequence = mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(collectionName)),
                new Update().inc(ModuleSequence.getIncLongKey(), inc),
                FindAndModifyOptions.options().upsert(true),
                ModuleSequence.class, CollectionsBaseEnum.SEQUENCE.getName());
        if (moduleSequence != null) {
            return moduleSequence.getSeqLong();
        }
        return null;
    }
}
