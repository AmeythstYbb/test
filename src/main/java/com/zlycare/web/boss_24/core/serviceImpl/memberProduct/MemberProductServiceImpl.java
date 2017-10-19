package com.zlycare.web.boss_24.core.serviceImpl.memberProduct;

import com.zlycare.web.boss_24.beans.exception.ServiceException;
import com.zlycare.web.boss_24.constants.memberProduct.ProductConstants;
import com.zlycare.web.boss_24.core.mongo.order.MemberProductMongo;
import com.zlycare.web.boss_24.core.mongo.order.ProductCatalogMongo;
import com.zlycare.web.boss_24.core.mongo.order.StringMongo;
import com.zlycare.web.boss_24.core.mongo.po.MemberProduct;
import com.zlycare.web.boss_24.core.mongo.po.ProductCatalog;
import com.zlycare.web.boss_24.core.service.bo.MemberProductBo;
import com.zlycare.web.boss_24.core.service.bo.ProductCatalogBo;
import com.zlycare.web.boss_24.core.service.memberProduct.MemberProductService;
import com.zlycare.web.boss_24.core.service.memberProduct.ProductCatalogService;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author : linguodong
 * Create : 2017/6/29
 * Update : 2017/6/29
 * Descriptions : todo 赶时间，很多service多次实现，后期可合并抽取优化。
 */
@Service
public class MemberProductServiceImpl implements MemberProductService {
    private static Logger logger = LoggerFactory.getLogger(MemberProductServiceImpl.class);

    //会员产品清单列表集合名称
    private static final String VIP_MEMBER_PRODUCT = "vipMemberProducts";
    //主键
    private static final String ID = "_id";
    //一级类目
    private static final String TYPE = "type";


    @Autowired
    private MemberProductMongo memberProductMongo;
    @Autowired
    private StringMongo stringMongo;
    @Autowired
    private ProductCatalogService productCatalogService;
    @Autowired
    private ProductCatalogMongo productCatalogMongo;

    /**
     * 获取当前对象
     *
     * @param id id
     * @return MemberProductBo
     */
    @Override
    public MemberProductBo getMemberProductById(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("查询产品信息信息失败，param is null");
            return null;
        }
        Query query = new Query(Criteria.where(ID).is(id));
        MemberProduct memberProduct = memberProductMongo.findOne(query, VIP_MEMBER_PRODUCT);
        return BeanMapper.map(memberProduct, MemberProductBo.class);
    }

    /**
     * 查询产品清单列表
     *
     * @param productName productName
     * @param startDate   开始日期
     * @param endDate     结束日期
     * @param status      状态 全部 审核通过 不通过 未审核
     * @param start       开始行数
     * @param pageSize    查询数量
     * @return List<MemberProductBo>
     */
    @Override
    @Transactional
    public List<MemberProductBo> getAllList(String jobNumber, String productName, Long startDate, Long
            endDate, List<Integer> status, Integer start, Integer pageSize, String type, String subType,
                                            String vipType, String firstType, String secondType, String thirdType) {
        // todo 解析四参数
        // vipType 直接加上
        // if thirdType ！= null 一二级目录忽略 直接加上
        // else second 不等于null 一级目录 忽略 查询 二级目录的下一级 数据  所有ID放到集合 参数的parentIds in 集合
        // else first 不等于 null 查询 一级目录的所有 子集合 所有ID放到集合  参数的parentIds in 集合

        // jobNumber 字段不判空，当作搜索条件使用
        if (pageSize == null || start == null) {
            logger.error("查询产品信息信息失败，param is null");
            return null;
        }
        Query query = new Query(Criteria.where("isDeleted").is(false)).limit(pageSize).skip(start);
        Criteria criteria;
        if (StringUtils.isNotEmpty(jobNumber)) {
            query.addCriteria(Criteria.where("jobNumber").is(jobNumber));
        }
        if (StringUtils.isNotEmpty(type)) {
            query.addCriteria(Criteria.where("type").is(type));
        }
        if (StringUtils.isNotEmpty(subType)) {
            query.addCriteria(Criteria.where("subType").is(subType));
        }
        if (StringUtils.isNotEmpty(vipType)) {
            query.addCriteria(Criteria.where("vipType").is(vipType));
        }
        if (StringUtils.isNotEmpty(thirdType)) {
            query.addCriteria(Criteria.where("thirdType").is(thirdType));
        } else if (StringUtils.isNotEmpty(secondType)) {
            List<ProductCatalog> productCatalogList = productCatalogMongo.getCatalogListWithParentId(null, secondType);
            if (CollectionUtils.isNotEmpty(productCatalogList)) {
                List<String> ids = new ArrayList<>();
                productCatalogList.stream().forEach(productCatalog -> {
                    ids.add(productCatalog.getId());
                });
                if (CollectionUtils.isNotEmpty(ids)) {
                    query.addCriteria(Criteria.where("thirdType").in(ids));
                }
            }
        } else if (StringUtils.isNotEmpty(firstType)) {
            List<ProductCatalog> productCatalogList = productCatalogMongo.getAllCatalogListWithParentId(null, firstType);
            if (CollectionUtils.isNotEmpty(productCatalogList)) {
                List<String> ids = new ArrayList<>();
                productCatalogList.stream().forEach(productCatalog -> {
                    ids.add(productCatalog.getId());
                });
                if (CollectionUtils.isNotEmpty(ids)) {
                    query.addCriteria(Criteria.where("thirdType").in(ids));
                }
            }
        }
        if (startDate != null && endDate != null) {
            criteria = Criteria.where("createdAt").gte(startDate).lte(endDate);
            query.addCriteria(criteria);
        }
        if (startDate != null && endDate == null) {
            criteria = Criteria.where("createdAt").gte(startDate);
            query.addCriteria(criteria);
        }
        if (startDate == null && endDate != null) {
            criteria = Criteria.where("createdAt").lte(endDate);
            query.addCriteria(criteria);
        }
        if (CollectionUtils.isNotEmpty(status)) {
            query.addCriteria(Criteria.where("status").in(status));
        }
        if (StringUtils.isNotEmpty(productName)) {
            query.addCriteria(Criteria.where("productName").regex(productName));
        }
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        List<MemberProduct> memberProductList = memberProductMongo.find(query, VIP_MEMBER_PRODUCT);
        if (CollectionUtils.isEmpty(memberProductList)) {
            logger.error("查询产品清单信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        List<MemberProductBo> memberProductBoList = BeanMapper.mapList(memberProductList, MemberProductBo.class);
        if (CollectionUtils.isNotEmpty(memberProductBoList)) {
            memberProductBoList.stream().forEach((memberProductBo) -> {
                // 封装一二三级目录名称
                List<ProductCatalogBo> parentCatalogList = productCatalogService.getAllparentCatalogListWithId(null, memberProductBo.getThirdType());
                if (CollectionUtils.isNotEmpty(parentCatalogList)) {
                    parentCatalogList.stream().forEach((parentCatalog) -> {
                        if (parentCatalog.getId().equals(memberProductBo.getThirdType())) {//三级目录名称
                            memberProductBo.setThirdTypeName(parentCatalog.getName());
                        } else if (parentCatalog.getParentId().equals("1")) {// 一级目录名称
                            memberProductBo.setFirstTypeName(parentCatalog.getName());
                        } else {
                            memberProductBo.setSecondTypeName(parentCatalog.getName());
                        }
                    });
                }
                // 地域集合转换为文本字符串，逗号分隔
                if (CollectionUtils.isNotEmpty(memberProductBo.getProductSalesArea())) {
                    memberProductBo.setProductSalesAreaText(memberProductBo.getProductSalesArea().stream().collect(Collectors.joining(",")));
                }
            });
        }
        return memberProductBoList;
    }

    /**
     * 查询产品清单数量
     *
     * @param productName productName
     * @param startDate   开始日期
     * @param endDate     结束日期
     * @param status      状态 全部 审核通过 不通过 未审核
     * @param start       开始行数
     * @param pageSize    查询数量
     * @return Integer
     */
    @Override
    @Transactional
    public Integer countAllList(String jobNumber, String productName, Long startDate, Long endDate, List<Integer> status, Integer
            start, Integer pageSize, String type, String subType, String vipType, String firstType, String secondType, String thirdType) {
        // todo 解析四参数
        // vipType 直接加上
        // if thirdType ！= null 一二级目录忽略 直接加上
        // else second 不等于null 一级目录 忽略 查询 二级目录的下一级 数据  所有ID放到集合 参数的parentIds in 集合
        // else first 不等于 null 查询 一级目录的所有 子集合 所有ID放到集合  参数的parentIds in 集合¬

        // jobNumber 字段不判空，当作搜索条件使用
        if (pageSize == null || start == null) {
            logger.error("查询产品信息信息失败，param is null");
            return null;
        }
        Query query = new Query(Criteria.where("isDeleted").is(false)).limit(pageSize).skip(start);
        Criteria criteria;
        if (StringUtils.isNotEmpty(jobNumber)) {
            query.addCriteria(Criteria.where("jobNumber").is(jobNumber));
        }
        if (StringUtils.isNotEmpty(type)) {
            query.addCriteria(Criteria.where("type").is(type));
        }
        if (StringUtils.isNotEmpty(subType)) {
            query.addCriteria(Criteria.where("subType").is(subType));
        }
        if (StringUtils.isNotEmpty(vipType)) {
            query.addCriteria(Criteria.where("vipType").is(vipType));
        }
        if (StringUtils.isNotEmpty(thirdType)) {
            query.addCriteria(Criteria.where("thirdType").is(thirdType));
        } else if (StringUtils.isNotEmpty(secondType)) {
            List<ProductCatalog> productCatalogList = productCatalogMongo.getCatalogListWithParentId(null, secondType);
            if (CollectionUtils.isNotEmpty(productCatalogList)) {
                List<String> ids = new ArrayList<>();
                productCatalogList.stream().forEach(productCatalog -> {
                    ids.add(productCatalog.getId());
                });
                if (CollectionUtils.isNotEmpty(ids)) {
                    query.addCriteria(Criteria.where("thirdType").in(ids));
                }
            }
        } else if (StringUtils.isNotEmpty(firstType)) {
            List<ProductCatalog> productCatalogList = productCatalogMongo.getAllCatalogListWithParentId(null, firstType);
            if (CollectionUtils.isNotEmpty(productCatalogList)) {
                List<String> ids = new ArrayList<>();
                productCatalogList.stream().forEach(productCatalog -> {
                    ids.add(productCatalog.getId());
                });
                if (CollectionUtils.isNotEmpty(ids)) {
                    query.addCriteria(Criteria.where("thirdType").in(ids));
                }
            }
        }
        if (startDate != null && endDate != null) {
            criteria = Criteria.where("createdAt").gte(startDate).lte(endDate);
            query.addCriteria(criteria);
        }
        if (startDate != null && endDate == null) {
            criteria = Criteria.where("createdAt").gte(startDate);
            query.addCriteria(criteria);
        }
        if (startDate == null && endDate != null) {
            criteria = Criteria.where("createdAt").lte(endDate);
            query.addCriteria(criteria);
        }
        if (CollectionUtils.isNotEmpty(status)) {
            query.addCriteria(Criteria.where("status").in(status));
        }
        if (StringUtils.isNotEmpty(productName)) {
            query.addCriteria(Criteria.where("productName").regex(productName));
        }
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        Long count = memberProductMongo.count(query, VIP_MEMBER_PRODUCT);
        return count.intValue();
    }

    /**
     * 掺入 and 修改
     *
     * @param memberProductBo memberProductBo
     */
    @Override
    public void saveMemberProduct(MemberProductBo memberProductBo) {
        //创建产品信息 初始化各种状态 时间等 删除标识
        if (memberProductBo == null) {
            throw new NullPointerException("memberProductBo  is null");
        }
        MemberProduct memberProduct = BeanMapper.map(memberProductBo, MemberProduct.class);

        Long time = new Date().getTime();

        if (StringUtils.isEmpty(memberProduct.getId())) {
            memberProduct.setId(null);//""会被存储
            // 插入 初始化参数
            memberProduct.setCreatedAt(time.doubleValue());
            memberProduct.setUpdatedAt(time.doubleValue());
            memberProduct.setStatisticsUpdatedAt(time.doubleValue());
            memberProduct.setStatus(0);
            memberProduct.setOnline(0);
            memberProduct.setDeleted(false);
            try {
                memberProductMongo.create(memberProduct, VIP_MEMBER_PRODUCT);
            } catch (Exception e) {
                logger.error("Failed insert memberProduct file to mongodb");
                throw new ServiceException();
            }
        } else { // 修改 修改时间
            // 查询原值 赋值 修改save -- 不可重置 提交人和提交人工号字段
            MemberProductBo memberProductBoz = getMemberProductById(memberProduct.getId());
            // 修改时间
            memberProductBoz.setUpdatedAt(time.doubleValue());
            memberProductBoz.setStatisticsUpdatedAt(time.doubleValue());
            // 修改参数
            memberProductBoz.setProductCompanyName(memberProductBo.getProductCompanyName());
            memberProductBoz.setProductName(memberProductBo.getProductName());
            //memberProductBoz.setType(memberProductBo.getType());
            //memberProductBoz.setSubType(memberProductBo.getSubType());
            memberProductBoz.setProductDescription(memberProductBo.getProductDescription());
            memberProductBoz.setProductDetail(memberProductBo.getProductDetail());
            memberProductBoz.setMarketingPrice(memberProductBo.getMarketingPrice());
            memberProductBoz.setZlyChannelPrice(memberProductBo.getZlyChannelPrice());
            memberProductBoz.setAdviseRealPrice(memberProductBo.getAdviseRealPrice());
            memberProductBoz.setProductCode(memberProductBo.getProductCode());
            memberProductBoz.setBroker(memberProductBo.getBroker());
            memberProductBoz.setBrokerPhone(memberProductBo.getBrokerPhone());
            memberProductBoz.setProductPics(memberProductBo.getProductPics());
            memberProductBoz.setProductSalesArea(memberProductBo.getProductSalesArea());
            memberProductBoz.setThirdType(memberProductBo.getThirdType());
            memberProductBoz.setVipType(memberProductBo.getVipType());
            // 修改状态为未审核
            memberProductBoz.setStatus(0);
            memberProductMongo.save(BeanMapper.map(memberProductBoz, MemberProduct.class), VIP_MEMBER_PRODUCT);
        }
    }

    /**
     * 审核 产品申请 信息
     *
     * @param productId productId
     * @param status    审核状态
     * @param reason    拒绝原因 可空
     * @return 结果
     */
    @Override
    public boolean auditProduct(String productId, Integer status, String reason) {
        if (StringUtils.isEmpty(productId) || status == null) {
            logger.error("审核产品信息失败，param is null");
            return false;
        }
        if (status.equals(ProductConstants.PRODUCT_AUDIT_REFUSE) && StringUtils.isEmpty(reason)) {
            logger.error("审核产品信息失败");
            return false;
        }
        /*mongo*/
        Update update = new Update();
        update.set("status", status);
        if (StringUtils.isNotEmpty(reason)) {
            update.set("reason", reason);
        }
        // 修改时间
        Long time = new Date().getTime();
        update.set("updatedAt", time);
        update.set("statisticsUpdatedAt", time);

        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(productId));
        MemberProduct memberProduct = memberProductMongo.findAndModify(query, update, VIP_MEMBER_PRODUCT);//findAndModify 返回的是修改之前的对象
        if (memberProduct == null || StringUtils.isEmpty(memberProduct.getId())) {
            logger.error("审核产品申请失败");
            throw new ServiceException();
        }
        return true;
    }

    /**
     * 审核 产品申请 信息 通过
     *
     * @param productId 申请id
     * @param status    审核状态
     * @return 结果
     */
    @Override
    public boolean auditProductPass(String productId, Integer status, String vipType, String servicePeopleDocChatNum,
                                    String servicePeopleId, String servicePeopleImUserName,
                                    String servicePeopleCall, String servicePeopleName, Double realPrice,
                                    String type, String subType, String productName) {
        if (StringUtils.isEmpty(productId) || status == null || (StringUtils.isEmpty(servicePeopleDocChatNum)
                || StringUtils.isEmpty(servicePeopleId) ||
                StringUtils.isEmpty(servicePeopleImUserName) || StringUtils.isEmpty(servicePeopleCall) || StringUtils.isEmpty(servicePeopleName)
                || realPrice == null)) {
            logger.error("审核产品信息失败，param is null");
            return false;
        }
        /*mongo*/
        Update update = new Update();
        update.set("status", status);
        if (StringUtils.isNotEmpty(vipType)) {
            update.set("vipType", vipType);
        }
        if (StringUtils.isNotEmpty(type)) {
            update.set("type", type);
        }
        if (StringUtils.isNotEmpty(subType)) {
            update.set("subType", subType);
        }
        if (StringUtils.isNotEmpty(productName)) {
            update.set("productName", productName);
        }
        if (StringUtils.isNotEmpty(servicePeopleDocChatNum)) {
            update.set("servicePeopleDocChatNum", servicePeopleDocChatNum);
        }
        if (StringUtils.isNotEmpty(servicePeopleId)) {
            update.set("servicePeopleId", servicePeopleId);
        }
        if (StringUtils.isNotEmpty(servicePeopleImUserName)) {
            update.set("servicePeopleImUserName", servicePeopleImUserName);
        }
        if (StringUtils.isNotEmpty(servicePeopleCall)) {
            update.set("servicePeopleCall", servicePeopleCall);
        }
        if (StringUtils.isNotEmpty(servicePeopleName)) {
            update.set("servicePeopleName", servicePeopleName);
        }
        if (realPrice != null) {
            update.set("realPrice", realPrice);
        }
        // 修改时间
        Long time = new Date().getTime();
        update.set("updatedAt", time);
        update.set("statisticsUpdatedAt", time);

        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(productId));
        MemberProduct memberProduct = memberProductMongo.findAndModify(query, update, VIP_MEMBER_PRODUCT);//findAndModify 返回的是修改之前的对象
        if (memberProduct == null || StringUtils.isEmpty(memberProduct.getId())) {
            logger.error("审核产品申请失败");
            throw new ServiceException();
        }
        return true;
    }

    /**
     * 修改 产品
     *
     * @param productId 申请id
     * @return 结果
     */
    @Override
    public boolean updateProduct(String productId, String vipType, String servicePeopleDocChatNum, String servicePeopleId,
                                 String servicePeopleImUserName, String servicePeopleCall, String servicePeopleName, Double realPrice,
                                 String type, String subType, String productName) {
        if (StringUtils.isEmpty(productId) || (StringUtils.isEmpty(vipType) || StringUtils.isEmpty(servicePeopleDocChatNum)
                || StringUtils.isEmpty(servicePeopleId) ||
                StringUtils.isEmpty(servicePeopleImUserName) || StringUtils.isEmpty(servicePeopleCall) || StringUtils.isEmpty(servicePeopleName)
                || realPrice == null)
                || StringUtils.isEmpty(type) || StringUtils.isEmpty(subType) || StringUtils.isEmpty(productName)) {
            logger.error("修改产品信息失败，param is null");
            return false;
        }
        /*mongo*/
        Update update = new Update();
        if (StringUtils.isNotEmpty(vipType)) {
            update.set("vipType", vipType);
        }

        if (StringUtils.isNotEmpty(type)) {
            update.set("type", type);
        }
        if (StringUtils.isNotEmpty(subType)) {
            update.set("subType", subType);
        }
        if (StringUtils.isNotEmpty(productName)) {
            update.set("productName", productName);
        }

        if (StringUtils.isNotEmpty(servicePeopleDocChatNum)) {
            update.set("servicePeopleDocChatNum", servicePeopleDocChatNum);
        }
        if (StringUtils.isNotEmpty(servicePeopleId)) {
            update.set("servicePeopleId", servicePeopleId);
        }
        if (StringUtils.isNotEmpty(servicePeopleImUserName)) {
            update.set("servicePeopleImUserName", servicePeopleImUserName);
        }
        if (StringUtils.isNotEmpty(servicePeopleCall)) {
            update.set("servicePeopleCall", servicePeopleCall);
        }
        if (StringUtils.isNotEmpty(servicePeopleName)) {
            update.set("servicePeopleName", servicePeopleName);
        }
        if (realPrice != null) {
            update.set("realPrice", realPrice);
        }
        // 修改时间
        Long time = new Date().getTime();
        update.set("updatedAt", time);
        update.set("statisticsUpdatedAt", time);

        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(productId));
        MemberProduct memberProduct = memberProductMongo.findAndModify(query, update, VIP_MEMBER_PRODUCT);//findAndModify 返回的是修改之前的对象
        if (memberProduct == null || StringUtils.isEmpty(memberProduct.getId())) {
            logger.error("审核产品申请失败");
            throw new ServiceException();
        }
        return true;
    }

    /**
     * 审核员 修改产品
     *
     * @param memberProductBo memberProductBo
     * @return 结果
     */
    @Override
    public boolean updateProduct(MemberProductBo memberProductBo) {
        if (memberProductBo == null) {
            throw new NullPointerException("memberProductBo  is null");
        }
        if (StringUtils.isEmpty(memberProductBo.getId()) || (StringUtils.isEmpty(memberProductBo.getVipType()) || StringUtils.isEmpty(memberProductBo.getServicePeopleDocChatNum())
                || StringUtils.isEmpty(memberProductBo.getServicePeopleId()) || StringUtils.isEmpty(memberProductBo.getServicePeopleImUserName())
                || StringUtils.isEmpty(memberProductBo.getServicePeopleCall()) || StringUtils.isEmpty(memberProductBo.getServicePeopleName())
                || memberProductBo.getRealPrice() == null) || StringUtils.isEmpty(memberProductBo.getProductName())) {
            logger.error("修改产品信息失败，param is null");
            return false;
        }
        Long time = new Date().getTime();
        // 查询原值 赋值 修改save -- 不可重置 提交人和提交人工号字段 审核状态 是否删除等
        MemberProductBo memberProductBoz = getMemberProductById(memberProductBo.getId());
        // 修改时间
        memberProductBoz.setUpdatedAt(time.doubleValue());
        memberProductBoz.setStatisticsUpdatedAt(time.doubleValue());
        // 修改参数
        memberProductBoz.setProductCompanyName(memberProductBo.getProductCompanyName());
        memberProductBoz.setProductName(memberProductBo.getProductName());
        memberProductBoz.setProductDescription(memberProductBo.getProductDescription());
        memberProductBoz.setProductDetail(memberProductBo.getProductDetail());
        memberProductBoz.setMarketingPrice(memberProductBo.getMarketingPrice());
        memberProductBoz.setZlyChannelPrice(memberProductBo.getZlyChannelPrice());
        memberProductBoz.setServicePeopleDocChatNum(memberProductBo.getServicePeopleDocChatNum());
        memberProductBoz.setServicePeopleId(memberProductBo.getServicePeopleId());
        memberProductBoz.setServicePeopleImUserName(memberProductBo.getServicePeopleImUserName());
        memberProductBoz.setServicePeopleCall(memberProductBo.getServicePeopleCall());
        memberProductBoz.setServicePeopleName(memberProductBo.getServicePeopleName());
        //memberProductBoz.setAdviseRealPrice(memberProductBo.getAdviseRealPrice());
        memberProductBoz.setRealPrice(memberProductBo.getRealPrice());
        memberProductBoz.setProductCode(memberProductBo.getProductCode());
        memberProductBoz.setBroker(memberProductBo.getBroker());
        memberProductBoz.setBrokerPhone(memberProductBo.getBrokerPhone());
        memberProductBoz.setProductPics(memberProductBo.getProductPics());
        memberProductBoz.setProductSalesArea(memberProductBo.getProductSalesArea());
        memberProductBoz.setThirdType(memberProductBo.getThirdType());
        memberProductBoz.setVipType(memberProductBo.getVipType());
        memberProductMongo.save(BeanMapper.map(memberProductBoz, MemberProduct.class), VIP_MEMBER_PRODUCT);
        return true;
    }

    /**
     * 上下线
     *
     * @param productId    申请id
     * @param online       online
     * @param onlineRemark onlineRemark
     * @return 结果
     */
    @Override
    public boolean online(String productId, Integer online, String onlineRemark) {
        if (StringUtils.isEmpty(productId) || online == null) {
            logger.error("上下线失败，param is null");
            return false;
        }
        if (online != 1 && online != 0) {
            logger.error("上下线失败，param is null");
            return false;
        }
        /*mongo*/
        Update update = new Update();
        update.set("online", online);
        update.set("onlineRemark", StringUtils.isNotEmpty(onlineRemark) ? onlineRemark : "");
        // 修改时间
        Long time = new Date().getTime();
        update.set("updatedAt", time);
        update.set("statisticsUpdatedAt", time);

        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(productId));
        MemberProduct memberProduct = memberProductMongo.findAndModify(query, update, VIP_MEMBER_PRODUCT);//findAndModify 返回的是修改之前的对象
        if (memberProduct == null || StringUtils.isEmpty(memberProduct.getId())) {
            logger.error("上下线失败");
            throw new ServiceException();
        }
        return true;
    }

    /**
     * 删除
     *
     * @param productId productId
     * @return boolean
     */
    @Override
    public boolean delete(String productId) {
        if (StringUtils.isEmpty(productId)) {
            logger.error("删除失败，param is null");
            return false;
        }
        memberProductMongo.deleteProductById(productId);
        return true;
    }

    /**
     * 查询一级类目
     *
     * @return List<String>
     */
    @Override
    public List<String> findTypeList() {
        List<String> typeList = stringMongo.distinct(TYPE, VIP_MEMBER_PRODUCT);
        if (CollectionUtils.isEmpty(typeList)) {
            logger.error("查询产品一级类目列表为空");
            return new LinkedList<>();
        }
        //List<MemberProduct> memberProductList = memberProductMongo.find(new Query(), VIP_MEMBER_PRODUCT);
        //if (CollectionUtils.isEmpty(memberProductList)) {
        //    logger.error("查询产品一级类目列表为空");
        //    return new LinkedList<>();
        //}
        //HashSet set = new HashSet<String>();
        //for (MemberProduct memberProduct : memberProductList) {
        //    if (StringUtils.isNotEmpty(memberProduct.getType())) {
        //        set.add(memberProduct.getType());
        //    }
        //}
        //return new ArrayList(set);
        if (!typeList.contains("其他"))
            typeList.add("其他");
        if (CollectionUtils.isEmpty(typeList)) {
            typeList.add("其他");
        }
        return typeList;
    }

    /**
     * 查询二级类目
     *
     * @param type 二级类目名称
     * @return List<String>
     */
    @Override
    public List<String> findSubTypeList(String type) {
        if (StringUtils.isEmpty(type)) {
            logger.error("find product subtype list failed ，param is null");
            return null;
        }
        List<MemberProduct> memberProductList = memberProductMongo.find(new Query(Criteria.where(TYPE).is(type)), VIP_MEMBER_PRODUCT);
        if (CollectionUtils.isEmpty(memberProductList)) {
            logger.error("查询产品二级类目列表为空");
            return new ArrayList<String>() {{
                add("其他");
            }};
        }
        HashSet set = new HashSet<String>();
        for (MemberProduct memberProduct : memberProductList) {
            if (StringUtils.isNotEmpty(memberProduct.getSubType())) {
                set.add(memberProduct.getSubType());
            }
        }
        set.add("其他");// 防止其他重复
        return new ArrayList(set);
    }
}
