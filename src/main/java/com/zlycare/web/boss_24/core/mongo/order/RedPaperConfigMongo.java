package com.zlycare.web.boss_24.core.mongo.order;


import com.zlycare.web.boss_24.beans.exception.ServiceException;
import com.zlycare.web.boss_24.core.mongo.po.RedPaperConfig;
import com.zlycare.web.boss_24.mongo.base.MongoBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Author : LinGuoDong
 * Create : 2016/5/10 12:05
 * Update : 2016/5/10 12:05
 * Descriptions :
 */
@Repository
public class RedPaperConfigMongo extends MongoBaseDao<RedPaperConfig> {
    private static final Logger logger = LoggerFactory.getLogger(RedPaperConfigMongo.class);
    // 配置表
    private static final String CONFIG = "configs";
    // 主键
    private static final String ID = "_id";
    // 红包主键
    private static final String RED_PAPER = "59636d8db1bce56941cdf067";

    @Override
    protected Class<RedPaperConfig> getEntityClass() {
        return RedPaperConfig.class;
    }

    @Override
    public void insert(RedPaperConfig bean) {
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
    public void create(RedPaperConfig redPaperConfig) {
        //serveRule.setId(sequenceDao.getServeRuleSeqId());
        super.save(redPaperConfig);
    }

    /**
     * 查询红包配置
     */
    public RedPaperConfig getRedPaper() {
        Query query = new Query(Criteria.where(ID).is(RED_PAPER));
        RedPaperConfig redPaperConfig = this.findOne(query, CONFIG);
        if (redPaperConfig == null) {
            logger.error("查询红包配置失败");
            return null;
        }
        return redPaperConfig;
    }

    /**
     * 修改目录
     */
    public void update(Integer timesPerPeoplePerDay, Integer hongbaoValue, Integer hongbaoCount) {
        if (timesPerPeoplePerDay == null && hongbaoValue == null && hongbaoCount == null) {
            logger.error("修改红包信息失败，参数为空");
            return;
        }
        Update update = new Update();
        if (timesPerPeoplePerDay != null)
            update.set("field.timesPerPeoplePerDay", timesPerPeoplePerDay);
        if (hongbaoValue != null)
            update.set("field.hongbaoValue", hongbaoValue);
        if (hongbaoCount != null)
            update.set("field.hongbaoCount", hongbaoCount);

        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(RED_PAPER));
        RedPaperConfig redPaperConfig = this.findAndModify(query, update, CONFIG);//findAndModify 返回的是修改之前的对象
        if (redPaperConfig == null) {
            logger.error("修改红包失败");
            throw new ServiceException();
        }
    }
}
