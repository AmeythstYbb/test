package com.zlycare.web.boss_24.core.serviceImpl.memberProduct;

import com.zlycare.web.boss_24.beans.exception.ServiceException;
import com.zlycare.web.boss_24.core.mongo.order.ProductCatalogMongo;
import com.zlycare.web.boss_24.core.mongo.po.ProductCatalog;
import com.zlycare.web.boss_24.core.service.bean.ProductCatalogBean;
import com.zlycare.web.boss_24.core.service.bo.ProductCatalogBo;
import com.zlycare.web.boss_24.core.service.memberProduct.ProductCatalogService;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/7/25
 * Update : 2017/7/25
 * Descriptions :
 */
@Service
public class ProductCatalogServiceImpl implements ProductCatalogService {
    private static final Logger logger = LoggerFactory.getLogger(ProductCatalogServiceImpl.class);
    @Autowired
    private ProductCatalogMongo productCatalogMongo;

    /**
     * 查询所有目录,vipType不为空则区分类型查询
     *
     * @param vipType vipType
     * @return List<ProductCatalogBo>
     */
    @Override
    public List<ProductCatalogBo> getAllCatalogList(String vipType) {
        List<ProductCatalog> productCatalogList = productCatalogMongo.getAllCatalogList(vipType);
        if (CollectionUtils.isEmpty(productCatalogList)) {
            logger.debug("查询产品目录信息失败，数据为null");
            return new ArrayList<>();
        }
        return BeanMapper.mapList(productCatalogList, ProductCatalogBo.class);
    }

    /**
     * 创建目录，不区分类型
     *
     * @param productCatalogBean productCatalogBean
     * @return Integer
     */
    @Override
    @Transactional
    public Integer createProductCatalog(ProductCatalogBean productCatalogBean) {
        //productCatalogBean.setDeleted(false);
        if (productCatalogBean.getSort() == null)
            productCatalogBean.setSort(30);
        //如果含有父节点,修改当前bean的父节点集合
        String parentId = productCatalogBean.getParentId();
        if (StringUtils.isNotEmpty(parentId)) {
            // 父级目录是手动添加的顶级目录,不再通过父ID查询对象(也查不到)，直接设置其ParentIds参数
            if (parentId.equals("1")) {
                productCatalogBean.setParentId("1");
                productCatalogBean.setParentIds("1,");
            } else {
                ProductCatalog parentCatalog = productCatalogMongo.getProductCatalog(parentId);
                if (parentCatalog == null || com.alibaba.druid.util.StringUtils.isEmpty(parentCatalog.getParentIds())) {
                    logger.error("找不到父节点");
                    throw new ServiceException();
                }
                productCatalogBean.setParentIds(parentCatalog.getParentIds() + parentCatalog.getId() + ",");
            }
        }
        productCatalogMongo.create(BeanMapper.map(productCatalogBean, ProductCatalog.class));
        return 1;
    }

    /**
     * 删除目录，不区分类型，实则修改
     *
     * @param id id
     * @return Integer
     */
    @Override
    public Integer deleteProductCatalog(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("删除目录信息失败，id为空");
            return null;
        }
        productCatalogMongo.deleteCatalogById(id);
        return 1;
    }

    /**
     * 获取 目录
     *
     * @param id id
     * @return MenuPo
     */
    @Override
    public ProductCatalogBo getProductCatalog(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("获取目录信息失败，id为空");
            return null;
        }
        ProductCatalog productCatalog = productCatalogMongo.getProductCatalog(id);
        if (productCatalog == null) {
            logger.debug("查询目录信息失败，id为" + id);
            return null;
        }
        return BeanMapper.map(productCatalog, ProductCatalogBo.class);
    }

    /**
     * 修改产品目录
     *
     * @param id       id
     * @param parentId parentId
     * @param name     name
     * @param sort     sort
     * @param show     show
     * @return boolean
     */
    @Override
    public boolean updateProductCatalog(String id, String parentId, String name, Integer sort, Integer show) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(parentId) || StringUtils.isEmpty(name) || show == null) {
            logger.error("修改目录信息失败，参数为空");
            return false;
        }
        productCatalogMongo.updateProductCatalog(id, parentId, name, sort, show);
        return true;
    }

    /**
     * 查询所有一级类目,vipType不为空则区分类型查询
     *
     * @param vipType 会员专区类型
     * @return List<ProductCatalogBo>
     */
    @Override
    public List<ProductCatalogBo> getAllRootCatalogList(String vipType) {
        List<ProductCatalog> productCatalogList = productCatalogMongo.getAllRootCatalogList(vipType);
        if (CollectionUtils.isEmpty(productCatalogList)) {
            logger.debug("查询产品目录信息失败，数据为null");
            return new ArrayList<>();
        }
        return BeanMapper.mapList(productCatalogList, ProductCatalogBo.class);
    }

    /**
     * 根据类目Id 查询其 下一级 子目录
     *
     * @param vipType 会员专区类型
     * @return List<ProductCatalogBo>
     */
    @Override
    public List<ProductCatalogBo> getCatalogListWithParentId(String vipType, String parentId) {
        if (StringUtils.isEmpty(parentId)) {
            logger.error("获取目录信息失败，id为空");
            return null;
        }
        List<ProductCatalog> productCatalogList = productCatalogMongo.getCatalogListWithParentId(vipType, parentId);
        if (CollectionUtils.isEmpty(productCatalogList)) {
            logger.debug("查询产品目录信息失败，数据为null");
            return new ArrayList<>();
        }
        return BeanMapper.mapList(productCatalogList, ProductCatalogBo.class);
    }

    /**
     * 查询指定目录ID所有父级目录,包括当前目录
     */
    @Override
    public List<ProductCatalogBo> getAllparentCatalogListWithId(String vipType, String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("获取目录信息失败，id为空");
            return null;
        }
        List<ProductCatalog> productCatalogList = productCatalogMongo.getAllparentCatalogListWithId(vipType, id);
        if (CollectionUtils.isEmpty(productCatalogList)) {
            logger.debug("查询产品目录信息失败，数据为null");
            return new ArrayList<>();
        }
        return BeanMapper.mapList(productCatalogList, ProductCatalogBo.class);
    }
}
