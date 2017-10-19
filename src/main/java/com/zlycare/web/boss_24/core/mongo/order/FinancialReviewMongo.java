package com.zlycare.web.boss_24.core.mongo.order;

import com.zlycare.web.boss_24.core.exception.excel.ExcelColumnException;
import com.zlycare.web.boss_24.core.mongo.po.FinancialReview;
import com.zlycare.web.boss_24.mongo.base.MongoBaseDao;
import org.springframework.stereotype.Repository;
import com.zlycare.web.boss_24.beans.exception.ServiceException;
import com.zlycare.web.boss_24.core.service.bo.FinancialReviewBo;
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


import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sulong on 2017/10/12.
 */
@Repository
public class FinancialReviewMongo extends MongoBaseDao<FinancialReview> {
    private static final Logger logger = LoggerFactory.getLogger(FeedBackMongo.class);
    //主键
    private static final String ID = "_id";
    /**
     * 提现审核表
     */
    private static final String AppLications = "applications";

    //内嵌-固定
    @Autowired
    @Qualifier("mongoTemplate")
    @Override
    protected void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    @Override
    protected Class<FinancialReview> getEntityClass() {// 这边需要自己定义自己的
        return FinancialReview.class;
    }

//     /**
//     * 编写getAllList()方法
//     */
//    public List<FinancialReview> getAllList(){//获取一个List 集合（需要判空等，没加）
//        Query query = new Query().limit(5).skip(6);//取5个结果从第6个开始
//        List<FinancialReview> backList = this.find(query, AppLications);//拼接mongo语法，query是条件语句，AppLications是表名
//        return backList;
//
//    }

    public void updateFinancialReview(FinancialReviewBo financialReviewBo) throws Exception {
        if(financialReviewBo == null){
            logger.error("审核信息失败");
            throw new ServiceException();
        }
        if(financialReviewBo.getId() == null || financialReviewBo.getStatus() == null || financialReviewBo.getApplicantPhone() == null){
            logger.error("修改信息为空,修改失败");
            return;
        }
//        String id = financialReviewBo.getId();
//        String reason = financialReviewBo.getReason();
//        String phone = financialReviewBo.getApplicantPhone();
//        Long time = new Date().getTime();

    }

    public  void updateAgree(FinancialReviewBo financialReviewBo) throws Exception {
        if(financialReviewBo == null||financialReviewBo.getId() == null){
            logger.error("审核信息失败");
            throw new ServiceException();
        }
        String id = financialReviewBo.getId();

    }
    /**
     * 接受页面参数返回数据方法
     * @param status
     * @param start
     * @param pageSize
     * @return
     */
    public List<FinancialReview> getAllList(String applicantPhone,String alipayNum, List<Integer> status,Integer start,Integer pageSize){
        Query query = new Query().limit(pageSize).skip(start);
        if(StringUtils.isNotEmpty(applicantPhone)){
            query.addCriteria(Criteria.where("applicantPhone").is(applicantPhone));
        }
        if(StringUtils.isEmpty(alipayNum) || alipayNum.equals("")){
            //query.addCriteria(Criteria.where("alipayNum").exists(false).in(alipayNum));
        }else if(StringUtils.isNotEmpty(alipayNum) && alipayNum.equals("1")  ){
            //支付寶
            query.addCriteria(Criteria.where("alipayNum").exists(true));
        }else if(StringUtils.isNotEmpty(alipayNum) && alipayNum.equals("2") ){
            query.addCriteria(Criteria.where("alipayNum").exists(false));
        }
        if (CollectionUtils.isNotEmpty(status)){
            query.addCriteria(Criteria.where("status").in(status));

        }
        query.with(new Sort(Sort.Direction.DESC,"createdAt"));

        List<FinancialReview> backList = this.find(query,AppLications);
        if(CollectionUtils.isEmpty(backList)){
            logger.error("搜索列表为空");
            return  new LinkedList<>();
        }
        return backList;
    }

    public Integer countAllList(String applicantPhone,String alipayNum,List<Integer> status,Integer start,Integer pageSize){
        if (pageSize == null || start == null){
            logger.error("查询失败m,paeam is null");
            return null;
        }
        Query query = new Query();
        if(StringUtils.isNotEmpty(applicantPhone)){
            query.addCriteria(Criteria.where("applicantPhone").is(applicantPhone));
        }
        if(StringUtils.isEmpty(alipayNum) || alipayNum.equals("")){
            //query.addCriteria(Criteria.where("alipayNum").exists(false).in(alipayNum));
        }else if(StringUtils.isNotEmpty(alipayNum) && alipayNum.equals("1")  ){
            //支付寶
            query.addCriteria(Criteria.where("alipayNum").exists(true));
        }else if(StringUtils.isNotEmpty(alipayNum) && alipayNum.equals("2") ){
            query.addCriteria(Criteria.where("alipayNum").exists(false));
        }
        if (CollectionUtils.isNotEmpty(status)){
            query.addCriteria(Criteria.where("status").in(status));

        }
        Long count = this.count(query,AppLications);
        return count.intValue();
    }

    public FinancialReview findById(String id){
        if (StringUtils.isEmpty(id)){
            logger.error("查询失败");
            return null;
        }
        Query query = new Query(Criteria.where(ID).is(id));
        FinancialReview financialReview = this.findOne(query,AppLications);
        if(financialReview == null ){
            logger.error("提现申请ID有误" +id);
            throw new ExcelColumnException("提现申请ID有误" + "(" + id + ")");
        }
        return financialReview;
    }

}
