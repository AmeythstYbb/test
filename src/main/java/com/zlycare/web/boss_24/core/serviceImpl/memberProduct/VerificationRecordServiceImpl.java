package com.zlycare.web.boss_24.core.serviceImpl.memberProduct;

import com.zlycare.web.boss_24.core.mongo.order.UsersMongo;
import com.zlycare.web.boss_24.core.mongo.order.VerificationRecordMongo;
import com.zlycare.web.boss_24.core.mongo.po.Users;
import com.zlycare.web.boss_24.core.mongo.po.VerificationRecord;
import com.zlycare.web.boss_24.core.service.bean.VerificationRecordBean;
import com.zlycare.web.boss_24.core.service.bo.VerificationRecordBo;
import com.zlycare.web.boss_24.core.service.memberProduct.VerificationRecordService;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/7/17
 * Update : 2017/7/17
 * Descriptions :
 */
@Service
public class VerificationRecordServiceImpl implements VerificationRecordService {
    private static Logger logger = LoggerFactory.getLogger(VerificationRecordServiceImpl.class);
    //用户表名字
    private static final String USERS = "users";
    //主键
    private static final String ID = "_id";
    @Autowired
    VerificationRecordMongo verificationRecordMongo;
    @Autowired
    private UsersMongo usersMongo;

    /**
     * 查询核销列表
     *
     * @param verificationRecordBean bean
     * @return List
     */
    @Override
    public List<VerificationRecordBo> getAllList(VerificationRecordBean verificationRecordBean) {
        Integer pageSize = verificationRecordBean.getPageSize();
        Integer start = verificationRecordBean.getStart();

        if (pageSize == null || start == null) {
            logger.error("查询核销记录信息失败，param is null");
            return null;
        }
        com.zlycare.web.boss_24.core.mongo.bean.VerificationRecordBean v =
                BeanMapper.map(verificationRecordBean, com.zlycare.web.boss_24.core.mongo.bean.VerificationRecordBean.class);
        v = init(v);
        if (v == null) {
            return new LinkedList<>();
        }
        List<VerificationRecord> verificationRecordList = verificationRecordMongo.getAllList(v);

        if (CollectionUtils.isEmpty(verificationRecordList)) {
            logger.error("查询核销信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        for (VerificationRecord verificationRecord : verificationRecordList) {
            String userId = verificationRecord.getUserId();
            String checkVenderId = verificationRecord.getCheckVenderId();
            if (StringUtils.isNotEmpty(userId)) {
                // 根据userId查找用户信息
                Users user = usersMongo.findOne(new Query(Criteria.where(ID).is(userId).
                        andOperator(Criteria.where("isDeleted").is(false))), USERS);
                if (user != null) {
                    verificationRecord.setName(user.getName());
                    verificationRecord.setDocChatNum(user.getDocChatNum());
                    verificationRecord.setPhoneNum(user.getPhoneNum());
                }
            }
            if (StringUtils.isNotEmpty(checkVenderId)) {
                // 根据checkVenderId查找用户信息
                Users userCheck = usersMongo.findOne(new Query(Criteria.where(ID).is(checkVenderId).
                        andOperator(Criteria.where("isDeleted").is(false))), USERS);
                if (userCheck != null) {
                    verificationRecord.setCheckVenderName(userCheck.getShopName());
                    verificationRecord.setCheckVenderNum(userCheck.getDocChatNum());
                    verificationRecord.setCheckVenderPhone(userCheck.getPhoneNum());
                }
            }
        }
        List<VerificationRecordBo> verificationRecordBoList = BeanMapper.mapList(verificationRecordList, VerificationRecordBo.class);
        return verificationRecordBoList;
    }

    /**
     * 查询核销列表 条数 忽略start和size
     *
     * @param verificationRecordBean bean
     * @return Integer
     */
    @Override
    public Integer countAllList(VerificationRecordBean verificationRecordBean) {
        com.zlycare.web.boss_24.core.mongo.bean.VerificationRecordBean v =
                BeanMapper.map(verificationRecordBean, com.zlycare.web.boss_24.core.mongo.bean.VerificationRecordBean.class);
        v = init(v);
        // if  StringUtils.isNotEmpty(docChatNum)) || StringUtils.isNotEmpty(checkVenderNum) but 用户表查不到用户。
        // 如何处理 直接返回空数据,还是热线号模糊匹配 todo 精确匹配。找不到直接返回空数据。
        if (v == null) {
            return 0;
        }
        return verificationRecordMongo.countAllList(v);
    }

    private com.zlycare.web.boss_24.core.mongo.bean.VerificationRecordBean init(
            com.zlycare.web.boss_24.core.mongo.bean.VerificationRecordBean verificationRecordBean) {

        String docChatNum = verificationRecordBean.getDocChatNum();
        String checkVenderNum = verificationRecordBean.getCheckVenderNum();
        // 通过用户热线号补全用户id,有热线号但是找不到用户，直接返回null,不再继续查询
        if (StringUtils.isNotEmpty(docChatNum)) {
            Users user = usersMongo.findOne(new Query(Criteria.where("docChatNum").is(docChatNum).
                    andOperator(Criteria.where("isDeleted").is(false))), USERS);
            if (user == null || user.getId() == null) {
                logger.error("查询核销记录失败");
                //throw new ServiceException();
                return null;
            }
            verificationRecordBean.setUserId(user.getId());
        }
        // 通过审核人热线号补全审核用户id,有热线号但是找不到用户，直接返回null,不再继续查询
        if (StringUtils.isNotEmpty(checkVenderNum)) {
            Users user_check = usersMongo.findOne(new Query(Criteria.where("docChatNum").is(checkVenderNum).
                    andOperator(Criteria.where("isDeleted").is(false))), USERS);
            if (user_check == null || user_check.getId() == null) {
                logger.error("查询核销记录失败");
                //throw new ServiceException();
                return null;
            }
            verificationRecordBean.setCheckVenderId(user_check.getId());
        }
        return verificationRecordBean;
    }
}
