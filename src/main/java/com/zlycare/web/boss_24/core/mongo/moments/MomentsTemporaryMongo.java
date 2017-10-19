package com.zlycare.web.boss_24.core.mongo.moments;

import com.zlycare.web.boss_24.constants.moments.MomentsTemporaryConstants;
import com.zlycare.web.boss_24.core.mongo.po.MomentsTemporary;
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
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Author : LinGuoDong
 * Create : 2016/5/10 12:05
 * Update : 2016/5/10 12:05
 * Descriptions : 操作excel_moments临时表底层
 */
@Repository
public class MomentsTemporaryMongo extends MongoBaseDao<MomentsTemporary> {
    private static final Logger logger = LoggerFactory.getLogger(MomentsTemporaryMongo.class);
    //excel_moments临时表
    private static final String MOMENTS_TEMPORARY = "momentsTemporary";
    //主键
    private static final String ID = "_id";

    @Override
    protected Class<MomentsTemporary> getEntityClass() {
        return MomentsTemporary.class;
    }

    @Override
    public void insert(MomentsTemporary bean) {
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
     * 批量插入接口(同一批次)
     *
     * @param momentsTemporaryList momentsTemporaryList
     * @return boolean
     */
    public boolean createBatch(List<MomentsTemporary> momentsTemporaryList) {
        if (CollectionUtils.isEmpty(momentsTemporaryList)) {
            logger.error("批次插入失败,列表数据为空");
            return false;
        }
        /*
        Long time = new Date().getTime();
        for (MomentsTemporary momentsTemporary : momentsTemporaryList) {
            // 初始化上传状态为:未上传
            momentsTemporary.setUploadFlag(MomentsTemporaryConstants.NOT_COMMIT);
            // 同一个时间戳,标识为同批次
            momentsTemporary.setBatch(time.doubleValue());
        }
        */
        this.insertAll(momentsTemporaryList);
        return true;
    }

    /**
     * 根据批次ID查询批次列表
     *
     * @param batch 批次ID
     * @return list
     */
    public List<MomentsTemporary> getAllMomentsTemporaryByBatchId(Double batch) {
        if (batch == null) {
            logger.error("根据批次ID查询批次列表失败,Id为空");
            return null;
        }
        Query query = new Query(Criteria.where("batch").is(batch));
        query.with(new Sort(Sort.Direction.ASC, "excelId"));


        List<MomentsTemporary> momentsTemporaryList = this.find(query, MOMENTS_TEMPORARY);
        if (CollectionUtils.isEmpty(momentsTemporaryList)) {
            logger.error("根据批次ID查询批次列表失败，搜索列表为空");
            return new LinkedList<>();
        }
        return momentsTemporaryList;
    }


    /**
     * 根据ID查询一条数据,修改时回显数据用
     *
     * @param id id
     * @return MomentsTemporary
     */
    public MomentsTemporary getMomentsTemporaryById(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("查询临时动态失败，Id为空");
            return null;
        }
        Query query = new Query(Criteria.where(ID).is(id));
        MomentsTemporary momentsTemporary = this.findOne(query, MOMENTS_TEMPORARY);
        if (momentsTemporary == null) {
            logger.error("查询临时动态失败,数据不存在");
            return null;
        }
        return momentsTemporary;
    }


    /**
     * 修改临时动态表的一条数据,通用。
     *
     * @param momentsTemporary 对象
     * @return 结果
     */
    public boolean updateMomentsTemporary(MomentsTemporary momentsTemporary) {
        if (null == momentsTemporary) {
            logger.error("修改临时动态失败，参数为空");
            return false;
        }
        /*参数校验*/
        if (StringUtils.isEmpty(momentsTemporary.getId()) || momentsTemporary.getExcelId() == null || momentsTemporary.getBatch() == null ||
                StringUtils.isEmpty(momentsTemporary.getContent()) || StringUtils.isEmpty(momentsTemporary.getDocChatNum()) ||
                momentsTemporary.getLat() == null || momentsTemporary.getLon() == null || momentsTemporary.getUploadFlag() == null) {
            logger.error("修改失败,参数缺失");
            return false;
        }
        MomentsTemporary m = getMomentsTemporaryById(momentsTemporary.getId());
        if (m == null) {
            logger.error("修改失败,查询数据为空");
            return false;
        }
        m.setBatch(momentsTemporary.getBatch());
        m.setUploadFlag(momentsTemporary.getUploadFlag());
        m.setContent(momentsTemporary.getContent());
        m.setDocChatNum(momentsTemporary.getDocChatNum());
        m.setExcelId(momentsTemporary.getExcelId());
        m.setLat(momentsTemporary.getLat());
        m.setLon(momentsTemporary.getLon());
        m.setPics(momentsTemporary.getPics());
        m.setTags(momentsTemporary.getTags());
        m.setTopics(momentsTemporary.getTopics());
        this.save(m);
        return true;
    }


    /**
     * 当前批次上传成功个数查询
     *
     * @param batch      批次ID
     * @param uploadFlag 上传标识 MomentsTemporaryConstants
     * @return Integer
     */
    public Integer count(Double batch, Integer uploadFlag) {
        Query query = new Query();
        // 上传标志字段不存在,则查询全部数据中指定批次的数量
        if (uploadFlag != null) {
            query.addCriteria(Criteria.where("uploadFlag").is(uploadFlag));
        }
        // 批次字段不存在,则查询全部数据中指定上传状态的数量
        if (batch != null) {
            query.addCriteria(Criteria.where("batch").is(batch));
        }
        Long count = this.count(query, MOMENTS_TEMPORARY);
        return count.intValue();
    }


    /**
     * 指定批次指定上传状态下列表查询
     */
    public List<MomentsTemporary> getMomentsTemporaryListByBatchAndUploadFlag(Double batch, Integer uploadFlag) {
        Query query = new Query();
        // 上传标志字段不存在,则查询全部数据中指定批次的列表
        if (uploadFlag != null) {
            query.addCriteria(Criteria.where("uploadFlag").is(uploadFlag));
        }
        // 批次字段不存在,则查询全部数据中指定上传状态的列表
        if (batch != null) {
            query.addCriteria(Criteria.where("batch").is(batch));
        }

        List<MomentsTemporary> momentsTemporaryList = this.find(query, MOMENTS_TEMPORARY);
        if (CollectionUtils.isEmpty(momentsTemporaryList)) {
            logger.error("查询动态临时表数据失败，搜索列表为空");
            return new LinkedList<>();
        }
        return momentsTemporaryList;
    }

}
