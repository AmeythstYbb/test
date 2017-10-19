package com.zlycare.web.boss_24.core.mongo.config;

import com.zlycare.web.boss_24.core.mongo.po.ProductSalesAreaConfig;
import com.zlycare.web.boss_24.mongo.base.MongoBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Author : LinGuoDong
 * Create : 2016/5/10 12:05
 * Update : 2016/5/10 12:05
 * Descriptions :
 */
@Repository
public class ProductSalesAreaConfigMongo extends MongoBaseDao<ProductSalesAreaConfig> {
    //配置表_各种
    private static final String CONFIGS = "configs";
    //主键
    private static final String ID = "_id";
    //可销售区域对应数据ID
    private static final String PRODUCT_SALES_AREA_CONFIG_ID = "5976ae2fb1bce56941cdf676";

    @Override
    protected Class<ProductSalesAreaConfig> getEntityClass() {
        return ProductSalesAreaConfig.class;
    }

    @Override
    public void insert(ProductSalesAreaConfig bean) {
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
    public void create(ProductSalesAreaConfig productSalesAreaConfig) {
        //serveRule.setId(sequenceDao.getServeRuleSeqId());
        super.save(productSalesAreaConfig);
    }

    /**
     * 查询
     */
    public ProductSalesAreaConfig getProductSalesAreaConfig() {
        Query query = new Query(Criteria.where(ID).is(PRODUCT_SALES_AREA_CONFIG_ID));
        return this.findOne(query, CONFIGS);
    }
}
