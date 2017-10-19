package com.zlycare.web.boss_24.core.serviceImpl.config;

import com.zlycare.web.boss_24.core.mongo.config.ProductSalesAreaConfigMongo;
import com.zlycare.web.boss_24.core.mongo.po.ProductSalesAreaConfig;
import com.zlycare.web.boss_24.core.mongo.po.ProductSalesAreaConfigField;
import com.zlycare.web.boss_24.core.service.config.ProductSalesAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/7/27
 * Update : 2017/7/27
 * Descriptions :
 */
@Service
public class ProductSalesAreaServiceImpl implements ProductSalesAreaService {
    private static final Logger logger = LoggerFactory.getLogger(ProductSalesAreaServiceImpl.class);
    @Autowired
    private ProductSalesAreaConfigMongo productSalesAreaConfigMongo;

    /**
     * 获取可销售地域集合
     *
     * @return List<String>
     */
    @Override
    public List<String> getProductSalesArea() {
        ProductSalesAreaConfig productSalesAreaConfig = productSalesAreaConfigMongo.getProductSalesAreaConfig();
        if (productSalesAreaConfig == null || productSalesAreaConfig.getField() == null) {
            logger.error("可销售地域为空");
            return new ArrayList<>();
        }
        ProductSalesAreaConfigField productSalesAreaConfigField = productSalesAreaConfig.getField();
        return productSalesAreaConfigField.getProductSalesArea();
    }
}
