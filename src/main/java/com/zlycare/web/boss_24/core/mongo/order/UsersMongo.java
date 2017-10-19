package com.zlycare.web.boss_24.core.mongo.order;

import com.zlycare.web.boss_24.core.mongo.po.Users;
import com.zlycare.web.boss_24.mongo.base.MongoBaseDao;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * Author : LinGuoDong
 * Create : 2016/5/10 12:05
 * Update : 2016/5/10 12:05
 * Descriptions :
 */
@Repository
public class UsersMongo extends MongoBaseDao<Users> {
    private static final Logger logger = LoggerFactory.getLogger(UsersMongo.class);
    // 用户表
    private static final String USERS = "users";
    // 主键
    private static final String ID = "_id";

    @Override
    protected Class<Users> getEntityClass() {
        return Users.class;
    }

    @Override
    public void insert(Users bean) {
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
    public void create(Users users) {
        //serveRule.setId(sequenceDao.getServeRuleSeqId());
        super.save(users);
    }

    /**
     * 获取用户
     * 不关乎会员类型和是否删除
     */
    public Users getUser(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("查询用户信息失败，Id为空");
            return null;
        }
        Query query = new Query(Criteria.where(ID).is(id));
        Users users = this.findOne(query, USERS);
        if (users == null) {
            logger.error("查询用户信息失败");
            return null;
        }
        return users;
    }


    /**
     * 查询用户列表
     *
     * @param name       name
     * @param docChatNum docChatNum
     * @param phoneNum   phoneNum
     * @return List<Users>
     */
    public List<Users> getAllUserList(String name, String docChatNum, String phoneNum) {
        Query query = new Query(Criteria.where("isDeleted").is(false));

        // 其中，热线号、手机号精确匹配，用户姓名模糊匹配
        if (StringUtils.isNotEmpty(name)) {
            query.addCriteria(Criteria.where("name").regex(name));
        }
        if (StringUtils.isNotEmpty(docChatNum)) {
            query.addCriteria(Criteria.where("docChatNum").is(docChatNum));
        }
        if (StringUtils.isNotEmpty(phoneNum)) {
            query.addCriteria(Criteria.where("phoneNum").is(phoneNum));
        }

        // query.with(new Sort(Sort.Direction.DESC, "createdAt"));

        List<Users> usersList = this.find(query, USERS);
        if (CollectionUtils.isEmpty(usersList)) {
            logger.error("查询用户信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        return usersList;
    }
}
