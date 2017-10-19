package com.zlycare.web.boss_24.core.service.memberProduct;

import com.zlycare.web.boss_24.core.service.bean.ProductCatalogBean;
import com.zlycare.web.boss_24.core.service.bo.ProductCatalogBo;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/7/25
 * Update : 2017/7/25
 * Descriptions :
 */
public interface ProductCatalogService {

    /**
     * 查询所有目录,vipType不为空则区分类型查询
     *
     * @param vipType vipType
     * @return List<ProductCatalogBo>
     */
    List<ProductCatalogBo> getAllCatalogList(String vipType);


    /**
     * 创建目录，不区分类型
     *
     * @param productCatalogBean productCatalogBean
     * @return Integer
     */
    Integer createProductCatalog(ProductCatalogBean productCatalogBean);

    /**
     * 删除目录，不区分类型
     *
     * @param id id
     * @return Integer
     */
    Integer deleteProductCatalog(String id);

    /**
     * 获取目录
     *
     * @param id id
     * @return ProductCatalogBo
     */
    ProductCatalogBo getProductCatalog(String id);

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
    boolean updateProductCatalog(String id, String parentId, String name, Integer sort, Integer show);

    /**
     * 查询所有一级类目
     *
     * @param vipType 会员专区类型
     * @return List<ProductCatalogBo>
     */
    List<ProductCatalogBo> getAllRootCatalogList(String vipType);

    /**
     * 根据类目Id 查询其 下一级 子目录
     *
     * @param vipType 会员专区类型
     * @return List<ProductCatalogBo>
     */
    List<ProductCatalogBo> getCatalogListWithParentId(String vipType, String parentId);

    /**
     * 查询指定目录ID所有父级目录,包括当前目录
     */
    List<ProductCatalogBo> getAllparentCatalogListWithId(String vipType, String id);
}
