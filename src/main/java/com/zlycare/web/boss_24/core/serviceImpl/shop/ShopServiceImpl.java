package com.zlycare.web.boss_24.core.serviceImpl.shop;

import com.zlycare.web.boss_24.beans.exception.ServiceException;
import com.zlycare.web.boss_24.constants.shop.ShopConstants;
import com.zlycare.web.boss_24.constants.shop.ShopVenderApplyStatusEnum;
import com.zlycare.web.boss_24.core.mongo.order.ShopMongo;
import com.zlycare.web.boss_24.core.mongo.order.ShopsMongo;
import com.zlycare.web.boss_24.core.mongo.order.UsersMongo;
import com.zlycare.web.boss_24.core.mongo.po.Shop;
import com.zlycare.web.boss_24.core.mongo.po.Shops;
import com.zlycare.web.boss_24.core.mongo.po.Users;
import com.zlycare.web.boss_24.core.service.bo.ShopBo;
import com.zlycare.web.boss_24.core.service.shop.ShopService;
import com.zlycare.web.boss_24.sms.util.SmsService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/6/19
 * Update : 2017/6/19
 * Descriptions :
 */
@Service
public class ShopServiceImpl implements ShopService {
    private static Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);
    // 17类型为商户审核申请
    private static final Integer shop = 17;
    //申请表名字
    private static final String applications = "applications";
    //用户表名字
    private static final String USERS = "users";
    //商户表名字
    private static final String SHOPS = "shops";
    //主键
    private static final String ID = "_id";
    private static final String userId = "userId";

    @Autowired
    private ShopMongo shopMongo;
    @Autowired
    private UsersMongo usersMongo;
    @Autowired
    private ShopsMongo shopsMongo;

    @Override
    public List<ShopBo> getShops() {
        return null;
    }

    /**
     * 查询全城够-商户列表
     *
     * @param userArea  用户包含业务地域
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param status    状态 全部 审核通过 不通过 未审核
     * @param start     开始行数
     * @param pageSize  查询数量
     * @return List<ShopBo>
     */
    @Transactional
    @Override
    public List<ShopBo> getAllList(List<String> shopType, List<String> userArea, Long startDate, Long endDate, List<Integer> status, Integer start, Integer pageSize) {
        if (pageSize == null || start == null || CollectionUtils.isEmpty(shopType) || CollectionUtils.isEmpty(userArea)) {
            logger.error("查询商户信息失败，param is null");
            return null;
        }
        Query query = new Query().limit(pageSize).skip(start);
        query.addCriteria(Criteria.where("type").is(shop));
        query.addCriteria(Criteria.where("shopType").in(shopType));
        query.addCriteria(Criteria.where("shopCity").in(userArea));
        Criteria criteria;
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
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        List<Shop> shopList = shopMongo.find(query, applications);
        if (CollectionUtils.isEmpty(shopList)) {
            logger.error("查询商户信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        List<ShopBo> shopBoList = BeanMapper.mapList(shopList, ShopBo.class);
        return shopBoList;
    }

    /**
     * 查询全城够-商户列表-数量
     *
     * @param userArea  用户包含业务地域
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param status    状态 全部 审核通过 不通过 未审核
     * @param start     开始行数
     * @param pageSize  查询数量
     * @return Integer
     */
    @Override
    public Integer countAllList(List<String> shopType, List<String> userArea, Long startDate, Long endDate, List<Integer> status, Integer start, Integer pageSize) {
        if (pageSize == null || start == null || CollectionUtils.isEmpty(shopType) || CollectionUtils.isEmpty(userArea)) {
            logger.error("查询商户信息失败，param is null");
            return null;
        }
        Query query = new Query().limit(pageSize).skip(start);
        query.addCriteria(Criteria.where("type").is(shop));
        query.addCriteria(Criteria.where("shopType").in(shopType));
        query.addCriteria(Criteria.where("shopCity").in(userArea));
        Criteria criteria;
        if (startDate != null && endDate != null) {
            criteria = Criteria.where("createdAt").gte(startDate).lt(endDate);
            query.addCriteria(criteria);
        }
        if (startDate != null && endDate == null) {
            criteria = Criteria.where("createdAt").gte(startDate);
            query.addCriteria(criteria);
        }
        if (startDate == null && endDate != null) {
            criteria = Criteria.where("createdAt").lt(endDate);
            query.addCriteria(criteria);
        }
        if (CollectionUtils.isNotEmpty(status)) {
            query.addCriteria(Criteria.where("status").in(status));
        }
        query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        Long count = shopMongo.count(query, applications);
        return count.intValue();
    }


    /**
     * 审核 商户申请 信息
     *
     * @param shopId 申请id
     * @param status 审核状态
     * @param reason 拒绝原因 可空
     * @return 结果
     */
    @Transactional
    @Override
    public boolean auditShopApplication(String shopId, Integer status, String reason) {
        if (StringUtils.isEmpty(shopId) || status == null) {
            logger.error("审核商户信息失败，param is null");
            return false;
        }
        if (status.equals(ShopConstants.SHOP_AUDIT_REFUSE) && StringUtils.isEmpty(reason)) {
            logger.error("审核商户参数null");
            return false;
        }

        /*mongo 修改Application信息并返回修改对象*/
        Update update = new Update();
        //update.addToSet("status", status);
        update.set("status", status);
        if (StringUtils.isNotEmpty(reason)) {
            update.set("reason", reason);
        }
        // 修改时间
        Long time = new Date().getTime();
        update.set("opReviewdAt", time);
        update.set("updatedAt", time);
        update.set("statisticsUpdatedAt", time);

        Query query = new Query();
        query.addCriteria(Criteria.where(ID).is(shopId));
        Shop shop = shopMongo.findAndModify(query, update, applications);//findAndModify 返回的是修改之前的对象
        //List<Shop> shop = shopMongo.find(new Query(Criteria.where(ID).is(shopId)),applications);
        if (shop == null || StringUtils.isEmpty(shop.getApplicantId())) {
            logger.error("审核商户申请失败");
            throw new ServiceException();
        }
        //shopVenderApplyStatus : {type : Number, default: 0 },//0-未申请 1-申请中 2-拒绝 3-通过 4-再次申请 5-再次拒绝
        /*根据申请表中的申请人id获取申请用户对象*/
        Users users = usersMongo.findOne(new Query(Criteria.where(ID).is(shop.getApplicantId())), USERS);
        if (users == null || users.getShopVenderApplyStatus() == null) {
            logger.error("审核商户申请失败");
            throw new ServiceException();
        }

        /*根据审核状态的不同，修改用户不同的对象信息，成功修改用户表中的商铺信息经纬度等 失败则修改shopVenderApplyStatus*/
        Query queryUsers = new Query();
        queryUsers.addCriteria(Criteria.where(ID).is(shop.getApplicantId()));
        Update updateUsers = new Update();
        if (status.equals(ShopConstants.SHOP_AUDIT_REFUSE)) {
            if (users.getShopVenderApplyStatus() == ShopVenderApplyStatusEnum.APPLICATION_ING.getKey()) {
                updateUsers.set("shopVenderApplyStatus", ShopVenderApplyStatusEnum.APPLICATION_REFUSE.getKey());
            } else if (users.getShopVenderApplyStatus() == ShopVenderApplyStatusEnum.APPLICATION_AGAIN_ING.getKey()) {
                updateUsers.set("shopVenderApplyStatus", ShopVenderApplyStatusEnum.APPLICATION_AGAIN_REFUSE.getKey());
            } else {
                logger.error("审核商户申请失败");
                throw new ServiceException();
            }
        } else if (status.equals(ShopConstants.SHOP_AUDIT_PASS)) {
            updateUsers.set("shopCity", shop.getShopCity());
            updateUsers.set("shopName", shop.getShopName());
            updateUsers.set("shopAddress", shop.getShopAddress());
            updateUsers.set("shopType", shop.getShopType());
            updateUsers.set("shopPhoneNum", shop.getShopPhoneNum());
            updateUsers.set("shopAvatar", shop.getShopAvatar());
            updateUsers.set("shopLicense", shop.getShopLicense());
            updateUsers.set("shopSubType", shop.getShopSubType());
            updateUsers.set("shopVenderApplyStatus", ShopVenderApplyStatusEnum.APPLICATION_PASS.getKey());
            updateUsers.set("isVender", true);
            if (shop.getShopAddressMapLat() != null && shop.getShopAddressMapLon() != null) {
                updateUsers.set("shopAddressMapLon", shop.getShopAddressMapLon());
                updateUsers.set("shopAddressMapLat", shop.getShopAddressMapLat());
                updateUsers.set("shopLocation", new ArrayList<Double>() {{
                    add(shop.getShopAddressMapLon());
                    add(shop.getShopAddressMapLat());
                }});
            }
        }
        // updateUsers.set("opReviewdAt", time);
        updateUsers.set("updatedAt", time);
        updateUsers.set("statisticsUpdatedAt", time);
        Users users_update = usersMongo.findAndModify(queryUsers, updateUsers, USERS);
        if (users_update == null) {
            logger.error("审核商户申请失败");
            throw new ServiceException();
        }
        /*如果审核通过 将申请表中的店铺信息同步修改到shop表中用户已经拥有的 商铺信息*/
        if (status.equals(ShopConstants.SHOP_AUDIT_PASS)) {
            // TODO: 2017/6/20
            //shops 运营商户表 
            //目前并不能从用户表中查询到其是否是运营商户，so，需要先去shops表中根据userId 查询是否能找到此条数据
            //最后根据是否查询到数据进行对应操作。存在，根据userId修改审核表商户信息到shop表，否则，无操作
            Shops shops = shopsMongo.findOne(new Query(Criteria.where(userId).is(users_update.getId())), SHOPS);
            if (shops != null) {// 存在运营商户表，修改
                Query queryShops = new Query();
                queryShops.addCriteria(Criteria.where(userId).is(users_update.getId()));
                Update updateShops = new Update();
                updateShops.set("shopName", shop.getShopName());
                updateShops.set("shopCity", shop.getShopCity());
                updateShops.set("shopType", shop.getShopType());
                updateShops.set("shopSubType", shop.getShopSubType());
                // updateShops.set("opReviewdAt", time);
                updateShops.set("updatedAt", time);
                updateShops.set("statisticsUpdatedAt", time);
                Shops shops_update = shopsMongo.findAndModify(queryShops, updateShops, SHOPS);
                if (shops_update == null) {
                    logger.error("审核商户申请失败");
                    throw new ServiceException();
                }
            }
        }
        /*最后，根据审核状态不同。发送不同模版的短信给applicantPhone*/
        if (status.equals(ShopConstants.SHOP_AUDIT_PASS)) {
            SmsService.sendShopApplicationsAuditPass(shop.getApplicantPhone());
        } else if (status.equals(ShopConstants.SHOP_AUDIT_REFUSE)) {
            SmsService.sendShopApplicationsAuditRefuse(shop.getApplicantPhone(), reason);
        }
        return true;
    }

//    public static void main(String[] args) {
//        String time = DateUtils.get(new Date());
//        System.out.println(new Date().getTime());
//    }
}
