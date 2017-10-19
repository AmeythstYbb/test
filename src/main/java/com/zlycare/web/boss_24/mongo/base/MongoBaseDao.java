package com.zlycare.web.boss_24.mongo.base;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collection;
import java.util.List;

/**
 * User: lfl
 * Date: 13-12-10
 * ModifyDate: 2016-01-07
 * Description: mongo基础操作
 */
public abstract class MongoBaseDao<T> {// 抽象类

    /**
     * 通过条件查询集合-传入集合名字
     *
     * @param query
     * @param collectionName 集合名字
     * @return List<T>
     */
    public List<T>
    find(Query query, String collectionName) {
        if (query == null || StringUtils.isEmpty(collectionName)) {
            return null;
        }
        return mongoTemplate.find(query, this.getEntityClass(), collectionName);
    }

    /**
     * 通过条件查询集合
     *
     * @param query
     * @return List<T>
     */
    public List<T>
    find(Query query) {
        if (query == null) {
            return null;
        }
        return mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * 通过条件查询对象
     *
     * @param query
     * @return T
     */
    public T findOne(Query query) {
        if (query == null) {
            return null;
        }
        return mongoTemplate.findOne(query, this.getEntityClass());
    }

    /**
     * 去重查询
     */
    @SuppressWarnings("unchecked")
    public List<T> distinct(String distinct, String collectionName) {
        if (StringUtils.isEmpty(distinct) || StringUtils.isEmpty(collectionName)) {
            return null;
        }
        return mongoTemplate.getCollection(collectionName).distinct(distinct);
    }

    /**
     * 去重查询
     */
    @SuppressWarnings("unchecked")
    public List<T> distinct(String distinct, String collectionName, Query query) {
        if (StringUtils.isEmpty(distinct) || StringUtils.isEmpty(collectionName) || query == null) {
            return null;
        }
        return mongoTemplate.getCollection(collectionName).distinct(distinct, query.getQueryObject());
    }

    /**
     * 通过条件查询对象
     *
     * @param query
     * @param query 集合名称
     * @return T
     */
    public T findOne(Query query, String collectionName) {
        if (query == null || StringUtils.isEmpty(collectionName)) {
            return null;
        }
        return mongoTemplate.findOne(query, this.getEntityClass(), collectionName);
    }

    /**
     * 查询出所有数据
     *
     * @return List<T>
     */
    public List<T> findAll() {
        return mongoTemplate.findAll(getEntityClass());
    }

    /**
     * 通过条件查询记录数
     *
     * @param query
     * @return T
     */
    public Long count(Query query) {
        if (query == null) {
            return null;
        }
        return mongoTemplate.count(query, this.getEntityClass());
    }

    /**
     * 通过条件查询记录数
     *
     * @param query
     * @param collectionName 集合名
     * @return T
     */
    public Long count(Query query, String collectionName) {
        if (query == null || StringUtils.isEmpty(collectionName)) {
            return null;
        }
        return mongoTemplate.count(query, collectionName);
    }

    /**
     * 查询并且修改记录
     *
     * @param query
     * @param update
     * @return T
     */
    public T findAndModify(Query query, Update update) {
        if (query == null || update == null) {
            return null;
        }
        return mongoTemplate.findAndModify(query, update, this.getEntityClass());
    }

    /**
     * 查询并且修改记录
     *
     * @param query
     * @param update
     * @return T
     */
    public T findAndModify(Query query, Update update, String collectionName) {
        if (query == null || update == null || StringUtils.isEmpty(collectionName)) {
            return null;
        }
        return mongoTemplate.findAndModify(query, update, this.getEntityClass(), collectionName);
    }

    /**
     * 查询并且修改记录
     *
     * @param id
     * @param update
     * @return T
     */
    public T findAndModifyById(Object id, Update update) {
        if (id == null || update == null) {
            return null;
        }
        return mongoTemplate.findAndModify(new Query(Criteria.where("_id").is(id)), update, this.getEntityClass());
    }

    /**
     * 查询并且修改记录
     *
     * @param query
     * @param update
     */
    public void findAndModify(Query query, Update update, FindAndModifyOptions findAndModifyOptions) {
        if (query == null || update == null || findAndModifyOptions == null) {
            return;
        }
        mongoTemplate.findAndModify(query, update, findAndModifyOptions, this.getEntityClass());
    }

    /**
     * 通过条件查询更新所有查到的数据
     *
     * @param query
     * @param update
     * @return
     */
    public void findAndModifyAll(Query query, Update update) {
        if (query == null || update == null) {
            return;
        }
        mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().upsert(true), this.getEntityClass());
    }

    /**
     * 按条件查询,并且删除记录
     *
     * @param query
     * @return T
     */
    public T findAndRemove(Query query) {
        if (query == null) {
            return null;
        }
        return mongoTemplate.findAndRemove(query, this.getEntityClass());
    }

    /**
     * 按条件查询,并且删除记录
     *
     * @param query
     * @return T
     */
    public T findAndRemove(Query query, String collectionName) {
        if (query == null || StringUtils.isEmpty(collectionName)) {
            return null;
        }
        return mongoTemplate.findAndRemove(query, this.getEntityClass(), collectionName);
    }

    /**
     * 按条件查询,并且删除记录
     *
     * @param id
     * @return T
     */
    public T findAndRemoveById(Object id) {
        if (id == null) {
            return null;
        }
        return mongoTemplate.findAndRemove(new Query(Criteria.where("_id").is(id)), this.getEntityClass());
    }

    /**
     * 通过条件查询更新数据
     *
     * @param query
     * @param update
     * @return
     */
    public void updateFirst(Query query, Update update) {
        if (query == null || update == null) {
            return;
        }
        mongoTemplate.updateFirst(query, update, this.getEntityClass());
    }

    /**
     * 保存一个对象到mongodb
     *
     * @param bean
     * @return T
     */
    public T save(T bean) {
        if (bean == null) {
            return null;
        }
        mongoTemplate.save(bean);
        return bean;
    }

    /**
     * 保存一个对象到mongodb
     *
     * @param bean
     * @param collectionName
     * @return T
     */
    public T save(T bean, String collectionName) {
        if (bean == null || StringUtils.isEmpty(collectionName)) {
            return null;
        }
        mongoTemplate.save(bean, collectionName);
        return bean;
    }

    /**
     * 批量插入
     *
     * @param collection
     */
    public void insertAll(Collection<T> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            return;
        }
        mongoTemplate.insertAll(collection);
    }

    /**
     * 插入
     *
     * @param bean
     */
    public void insert(T bean) {
        if (bean == null) {
            return;
        }
        mongoTemplate.insert(bean);
    }

    /**
     * 插入
     *
     * @param bean
     * @param collectionName
     */
    public void insert(T bean, String collectionName) {
        if (bean == null || collectionName == null) {
            return;
        }
        mongoTemplate.insert(bean, collectionName);
    }


    /**
     * 批量更新
     *
     * @param query
     * @param update
     */
    public void updateMulti(Query query, Update update) {
        if (query == null || update == null) {
            return;
        }
        mongoTemplate.updateMulti(query, update, this.getEntityClass());
    }

    /**
     * 批量更新
     *
     * @param query
     * @param update
     */
    public void updateMulti(Query query, Update update, String collectionName) {
        if (query == null || update == null || StringUtils.isEmpty(collectionName)) {
            return;
        }
        mongoTemplate.updateMulti(query, update, this.getEntityClass(), collectionName);
    }

    /**
     * 批量更新
     *
     * @param query
     */
    public void remove(Query query) {
        if (query == null) {
            return;
        }
        mongoTemplate.remove(query, this.getEntityClass());
    }

    /**
     * 通过ID获取记录
     *
     * @param id
     * @return T
     */
    public T findById(Object id) {
        if (id == null) {
            return null;
        }
        return mongoTemplate.findById(id, this.getEntityClass());
    }

    /**
     * 通过ID获取记录,并且指定了集合名
     *
     * @param id
     * @param collectionName 集合名
     * @return T
     */
    public T findById(Object id, String collectionName) {
        if (id == null || StringUtils.isEmpty(collectionName)) {
            return null;
        }
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }

//    /**
//     * 通过条件查询,查询分页结果
//     * @param skip
//     * @param limit
//     * @param query
//     * @return Page<T>
//     */
//    public Page<T> getPage(Integer skip, Integer limit, Query query) {
//        if (skip == null || limit == null || query == null) {
//            return null;
//        }
//        Page<T> page = new Page<T>();
//        page.setPageNo(skip / limit + 1);
//        page.setPageSize(limit);
//        page.setTotalItems(count(query));
//        query.skip(skip);
//        query.limit(limit);
//        page.setResult(find(query));
//        return page;
//    }
//
//    /**
//     * 通过条件查询
//     * @param query
//     * @return Page<T>
//     */
//    public Page<T> getPage(Query query) {
//        Page<T> page = new Page<T>();
//        if (query == null) {
//            query = new Query();
//        }
//        Long count = this.count(query);
//        page.setPageNo(1);
//        page.setPageSize(count.intValue());
//        page.setTotalItems(count);
//        page.setResult(find(query));
//        return page;
//    }


    /**
     * 清空当前集合数据并删除集合
     */
    private void dropCollection() {
        mongoTemplate.dropCollection(this.getEntityClass());
    }

    /**
     * 获取需要操作的实体类class
     *
     * @return Class<T>
     */
    protected abstract Class<T> getEntityClass();

    /**
     * spring mongodb　集成操作类
     */
    protected MongoTemplate mongoTemplate;

    /**
     * 注入mongodbTemplate
     *
     * @param mongoTemplate
     */
    protected abstract void setMongoTemplate(MongoTemplate mongoTemplate);

}
