package com.zlycare.web.boss_24.core.mongo.order;


import com.zlycare.web.boss_24.beans.exception.ServiceException;
import com.zlycare.web.boss_24.constants.productManage.VersionManageEnum;
import com.zlycare.web.boss_24.core.mongo.po.MemberProduct;
import com.zlycare.web.boss_24.core.mongo.po.ProductCatalog;
import com.zlycare.web.boss_24.core.mongo.po.Version;
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

import java.util.*;

/**
 * Author : LinGuoDong
 * Create : 2016/5/10 12:05
 * Update : 2016/5/10 12:05
 * Descriptions :
 */
@Repository
public class VersionMongo extends MongoBaseDao<Version> {
    private static final Logger logger = LoggerFactory.getLogger(VersionMongo.class);
    // 版本表
    private static final String VERSION = "versions";
    // 主键
    private static final String ID = "_id";
    // app标识
    public final static List<String> apps = new ArrayList<>();

    static {
        apps.add(VersionManageEnum.CUSTOMER_ANDROID_24.getValue());
        apps.add(VersionManageEnum.CUSTOMER_IOS_24.getValue());
    }

    @Override
    protected Class<Version> getEntityClass() {
        return Version.class;
    }

    @Override
    public void insert(Version bean) {
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
    public void create(Version version) {
        //serveRule.setId(sequenceDao.getServeRuleSeqId());
        super.save(version);
    }

    /**
     * 创建
     */
    public void create(Version version, String collectionName) {
        if (version == null || StringUtils.isEmpty(collectionName)) {
            return;
        }
        //serveRule.setId(sequenceDao.getServeRuleSeqId());
        super.save(version, collectionName);
    }

    /**
     * 查询24热线所有版本
     *
     * @return List<Version>
     */
    public List<Version> getVersionList(Integer pageSize, Integer start) {
        if (pageSize == null || start == null) {
            logger.error("查询版本信息失败，param is null");
            return new ArrayList<>();
        }
        if (CollectionUtils.isEmpty(apps)) {
            return new ArrayList<>();
        }
        Query query = new Query().limit(pageSize).skip(start);
        query.addCriteria(Criteria.where("type").in(apps));
        query.with(new Sort(Sort.Direction.DESC, "time"));
        List<Version> versionList = this.find(query, VERSION);
        if (CollectionUtils.isEmpty(versionList)) {
            logger.error("查询版本信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        return versionList;
    }

    /**
     * 查询数量
     */
    public Integer countAllList() {
        if (CollectionUtils.isEmpty(apps)) {
            return 0;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("type").in(apps));
        //query.with(new Sort(Sort.Direction.DESC, "time"));
        Long count = this.count(query, VERSION);
        return count.intValue();
    }


    /**
     * 删除版本, 直接删除
     */
    public void deleteVersion(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("删除版本失败，Id为空");
            return;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(id));
        Version version = this.findAndRemove(query, VERSION);//findAndRemove 返回删除的对象
        if (version == null) {
            logger.error("删除版本失败");
            throw new ServiceException();
        }
    }


    /**
     * 获取版本
     *
     * @param id id
     * @return
     */
    public Version getVersion(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("查询版本信息失败，Id为空");
            return null;
        }
        Query query = new Query(Criteria.where(ID).is(id));
        Version version = this.findOne(query, VERSION);
        if (version == null) {
            logger.error("查询版本信息失败");
            // throw new ServiceException();
            return null;
        }
        return version;
    }

}
