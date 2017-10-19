package com.zlycare.web.boss_24.mongo.base;


import com.zlycare.web.boss_24.mongo.enums.CollectionsBaseEnum;
import com.zlycare.web.boss_24.mongo.po.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author lufangInteger
 * @date 2015-11-20 22:03
 * @Description 自增序列Dao
 */
@Repository
public class SequenceBaseDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 获取序列ID
     *
     * @param collectionName 表名
     * @return Integer
     */
    protected Integer getSequenceId(String collectionName) {
        Sequence sequence = mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(collectionName)),
                new Update().inc(Sequence.getIncKey(), Sequence.getInc()),
                FindAndModifyOptions.options().upsert(true),
                Sequence.class, CollectionsBaseEnum.SEQUENCE.getName());
        if (sequence != null) {
            return sequence.getSeq();
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
    protected Integer getSequenceId(String collectionName, Integer inc) {
        Sequence sequence = mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(collectionName)),
                new Update().inc(Sequence.getIncKey(), inc),
                FindAndModifyOptions.options().upsert(true),
                Sequence.class, CollectionsBaseEnum.SEQUENCE.getName());
        if (sequence != null) {
            return sequence.getSeq();
        }
        return null;
    }

    /**
     * 获取序列ID
     *
     * @param collectionName 表名
     * @return Integer
     */
    protected Long getSequenceLongId(String collectionName) {
        Sequence sequence = mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(collectionName)),
                new Update().inc(Sequence.getIncLongKey(), Sequence.getInc()),
                FindAndModifyOptions.options().upsert(true),
                Sequence.class, CollectionsBaseEnum.SEQUENCE.getName());
        if (sequence != null) {
            return sequence.getSeqLong();
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
    protected Long getSequenceLongId(String collectionName, Integer inc) {
        Sequence sequence = mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(collectionName)),
                new Update().inc(Sequence.getIncLongKey(), inc),
                FindAndModifyOptions.options().upsert(true),
                Sequence.class, CollectionsBaseEnum.SEQUENCE.getName());
        if (sequence != null) {
            return sequence.getSeqLong();
        }
        return null;
    }
}
