package com.zlycare.web.boss_24.core.mongo.order;

import com.zlycare.web.boss_24.beans.exception.ServiceException;
import com.zlycare.web.boss_24.core.mongo.po.ProductCatalog;
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
public class ProductCatalogMongo extends MongoBaseDao<ProductCatalog> {
    private static final Logger logger = LoggerFactory.getLogger(ProductCatalogMongo.class);
    //会员产品清单列表集合名称
    private static final String PRODUCT_CATALOG = "productCatalog";
    //主键
    private static final String ID = "_id";
    // 根目录父ID
    private static final String root_parent_id = "1";

    @Override
    protected Class<ProductCatalog> getEntityClass() {
        return ProductCatalog.class;
    }

    @Override
    public void insert(ProductCatalog bean) {
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
     * 获取当前目录
     * 不关乎会员类型和是否删除
     */
    public ProductCatalog getProductCatalog(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("查询产品目录信息失败，Id为空");
            return null;
        }
        Query query = new Query(Criteria.where(ID).is(id));
        ProductCatalog productCatalog = this.findOne(query, PRODUCT_CATALOG);
        if (productCatalog == null) {
            logger.error("查询产品目录失败");
            // throw new ServiceException();
            return null;
        }
        return productCatalog;
    }

    /**
     * 创建
     */
    public void create(ProductCatalog productCatalog) {
        //serveRule.setId(sequenceDao.getServeRuleSeqId());
        if (productCatalog == null) {
            logger.error("创建目录失败，参数为null");
            return;
        }
        if (StringUtils.isEmpty(productCatalog.getParentId())) {
            productCatalog.setParentId("1");
            productCatalog.setParentIds("1,");
        }
        super.save(productCatalog);
    }

    /**
     * 查询所有目录,vipType不为空则区分类型查询
     */
    public List<ProductCatalog> getAllCatalogList(String vipType) {

        Query query = new Query(Criteria.where("isDeleted").is(false));
        if (StringUtils.isNotEmpty(vipType)) {
            query.addCriteria(Criteria.where("vipType").is(vipType));
        }
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        List<ProductCatalog> productCatalogList = this.find(query, PRODUCT_CATALOG);
        if (CollectionUtils.isEmpty(productCatalogList)) {
            logger.error("查询产品目录信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        return productCatalogList;
    }

    /**
     * 查询指定父级目录下一级目录
     */
    public List<ProductCatalog> getCatalogListWithParentId(String vipType, String parentId) {
        if (StringUtils.isEmpty(parentId)) {
            logger.error("查询产品目录信息失败，parentId为空");
            return new LinkedList<>();
        }
        Query query = new Query(Criteria.where("isDeleted").is(false));
        if (StringUtils.isNotEmpty(vipType)) {
            query.addCriteria(Criteria.where("vipType").is(vipType));
        }
        query.addCriteria(Criteria.where("parentId").is(parentId));
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        List<ProductCatalog> productCatalogList = this.find(query, PRODUCT_CATALOG);
        if (CollectionUtils.isEmpty(productCatalogList)) {
            logger.error("查询产品目录信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        return productCatalogList;
    }

    /**
     * 查询指定父级目录下所有子级目录,父级目录不包括顶级目录0
     */
    public List<ProductCatalog> getAllCatalogListWithParentId(String vipType, String parentId) {
        if (StringUtils.isEmpty(parentId)) {
            logger.error("查询产品目录信息失败，parentId为空");
            return new LinkedList<>();
        }
        Query query = new Query(Criteria.where("isDeleted").is(false));
        if (StringUtils.isNotEmpty(vipType)) {
            query.addCriteria(Criteria.where("vipType").is(vipType));
        }
        query.addCriteria(Criteria.where("parentIds").regex("," + parentId + ","));
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        List<ProductCatalog> productCatalogList = this.find(query, PRODUCT_CATALOG);
        if (CollectionUtils.isEmpty(productCatalogList)) {
            logger.error("查询产品目录信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        return productCatalogList;
    }

    /**
     * 查询指定目录ID查询所有父级目录,包括当前目录
     */
    public List<ProductCatalog> getAllparentCatalogListWithId(String vipType, String Id) {
        if (StringUtils.isEmpty(Id)) {
            logger.error("查询产品目录信息失败，Id为空");
            return new LinkedList<>();
        }
        // 查询当前ID对应的目录对象。
        Query queryOne = new Query();
        queryOne.addCriteria(Criteria.where(ID).is(Id).andOperator(Criteria.where("isDeleted").is(false)));
        ProductCatalog productCatalog = this.findOne(queryOne, PRODUCT_CATALOG);
        if (productCatalog == null || StringUtils.isEmpty(productCatalog.getParentIds())) {
            logger.error("查询产品目录失败");
            // throw new ServiceException();
            return new ArrayList<>();
        }

        Query query = new Query(Criteria.where("isDeleted").is(false));
        if (StringUtils.isNotEmpty(vipType)) {
            query.addCriteria(Criteria.where("vipType").is(vipType));
        }
        // 将 productCatalog.getParentIds() 变为list,去除1,然后where(ID).in(list)
        List<String> parentIds = new ArrayList<>();
        if (StringUtils.isNotEmpty(productCatalog.getParentIds())) {
            String[] parentIds_String = productCatalog.getParentIds().split(",");
            parentIds = Arrays.asList(parentIds_String);
            //parentIds.remove("1"); //去不去无所谓，id为1的数据不存在
        }
        query.addCriteria(Criteria.where(ID).in(parentIds));
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        List<ProductCatalog> productCatalogList = this.find(query, PRODUCT_CATALOG);
        if (CollectionUtils.isEmpty(productCatalogList)) {
            logger.error("查询产品目录信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        // 添加当前目录
        productCatalogList.add(productCatalog);
        return productCatalogList;
    }

    /**
     * 查询所有一级目录
     */
    public List<ProductCatalog> getAllRootCatalogList(String vipType) {
        Query query = new Query(Criteria.where("isDeleted").is(false));
        if (StringUtils.isNotEmpty(vipType)) {
            query.addCriteria(Criteria.where("vipType").is(vipType));
        }
        // 默认parentId == 1 的目录为一级目录
        query.addCriteria(Criteria.where("parentId").is(root_parent_id));
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        List<ProductCatalog> productCatalogList = this.find(query, PRODUCT_CATALOG);
        if (CollectionUtils.isEmpty(productCatalogList)) {
            logger.error("查询产品目录信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        return productCatalogList;
    }

    /**
     * 删除目录，不区分类型
     */
    public void deleteCatalogById(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("删除产品目录信息失败，Id为空");
            return;
        }
        Long now_time = new Date().getTime();
        Double time = now_time.doubleValue();
        /*修改当前目录*/
        Update update = new Update();
        // 修改删除字段
        update.set("isDeleted", true);
        // 修改时间
        update.set("updatedAt", time);
        update.set("statisticsUpdatedAt", time);
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(id));
        ProductCatalog productCatalog = this.findAndModify(query, update, PRODUCT_CATALOG);//findAndModify 返回的是修改之前的对象
        if (productCatalog == null) {
            logger.error("删除目录失败");
            throw new ServiceException();
        }
        /*批量修改多条_子目录,目录的parentIds含有id的数据*/
        Update updateMulti = new Update();
        // 修改删除字段
        updateMulti.set("isDeleted", true);
        // 修改时间
        updateMulti.set("updatedAt", time);
        updateMulti.set("statisticsUpdatedAt", time);
        Query queryMulti = new Query();
        queryMulti.addCriteria(Criteria.where("parentIds").regex("," + id + ","));
        this.updateMulti(queryMulti, updateMulti, PRODUCT_CATALOG);
    }

    /**
     * 修改目录
     */
    public void updateProductCatalog(String id, String parentId, String name, Integer sort, Integer show) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(parentId) || StringUtils.isEmpty(name) || show == null) {
            logger.error("修改目录信息失败，参数为空");
            return;
        }
        Long now_time = new Date().getTime();
        Double time = now_time.doubleValue();
        if (sort == null)
            sort = 30;
        ProductCatalog productCatalog = getProductCatalog(id);
        if (productCatalog == null || StringUtils.isEmpty(productCatalog.getParentIds())) {
            logger.error("not fount menuBo");
            throw new ServiceException();
        }
        String oldParentIds = productCatalog.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
        /*修改当前bean的父节点集合*/
        String parentIds;
        // 父级目录是手动添加的顶级目录,不再通过父ID查询对象(也查不到)，直接设置其ParentIds参数
        if (parentId.equals("1")) {
            parentIds = "1,";
        } else {
            ProductCatalog parentCatalog = this.getProductCatalog(parentId);
            if (parentCatalog == null || org.apache.commons.lang3.StringUtils.isEmpty(parentCatalog.getParentIds())) {
                logger.error("找不到父节点");
                throw new ServiceException();
            }
            parentIds = parentCatalog.getParentIds() + parentCatalog.getId() + ",";
        }
        Update update = new Update();
        // 修改时间
        update.set("updatedAt", time);
        update.set("statisticsUpdatedAt", time);
        // 修改其他参数
        update.set("name", name);
        update.set("sort", sort);
        update.set("show", show);
        update.set("parentId", parentId);
        update.set("parentIds", parentIds);
        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(id));
        ProductCatalog productCatalogOne = this.findAndModify(query, update, PRODUCT_CATALOG);//findAndModify 返回的是修改之前的对象
        if (productCatalogOne == null) {
            logger.error("修改目录失败");
            throw new ServiceException();
        }
        /*修改当前bean的子节点的父节点和父节点集合*/
        Query queryMulti = new Query();
        queryMulti.addCriteria(Criteria.where("parentIds").regex("," + id + ","));
        List<ProductCatalog> productCatalogList = this.find(queryMulti);
        for (ProductCatalog productBaseCatalog : productCatalogList) {
            // 修改时间
            productBaseCatalog.setUpdatedAt(time);
            productBaseCatalog.setStatisticsUpdatedAt(time);
            productBaseCatalog.setParentIds(productBaseCatalog.getParentIds().replace(oldParentIds, parentIds));
            this.save(productBaseCatalog);
        }
    }
}
