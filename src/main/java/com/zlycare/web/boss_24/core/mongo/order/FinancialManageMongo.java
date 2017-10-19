package com.zlycare.web.boss_24.core.mongo.order;

import com.zlycare.web.boss_24.beans.exception.ServiceException;
import com.zlycare.web.boss_24.constants.ProjectConfig;
import com.zlycare.web.boss_24.core.mongo.po.FinancialManage;
import com.zlycare.web.boss_24.core.service.bo.FinancialManageBo;
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
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.zlycare.web.boss_24.utils.financialManage.HttpUrlPUT.sendFinancial;

/**
 * Created by Administrator on 2017/9/28.
 */

@Repository
public class FinancialManageMongo extends MongoBaseDao<FinancialManage> {//继承抽象类·
    @Autowired
    ProjectConfig projectConfig;

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
    protected Class<FinancialManage> getEntityClass() {// 这边需要自己定义自己的
        return FinancialManage.class;
    }

//     /**
//     * 编写getAllList()方法
//     */
//    public List<FinancialManage> getAllList(){//获取一个List 集合（需要判空等，没加）
//        Query query = new Query().limit(5).skip(6);//取5个结果从第6个开始
//        List<FinancialManage> backList = this.find(query, AppLications);//拼接mongo语法，query是条件语句，AppLications是表名
//        return backList;
//
//    }

    /**
     * 运营拒绝
     * @param financialManageBo
     * @throws Exception
     */
    public void updateFinancialManage(FinancialManageBo financialManageBo) throws Exception {
        if(financialManageBo == null){
            logger.error("审核信息失败");
            throw new ServiceException();
        }
        if(financialManageBo.getId() == null || financialManageBo.getStatus() == null || financialManageBo.getApplicantPhone() == null){
            logger.error("修改信息为空,修改失败");
            return;
        }
        String id = financialManageBo.getId();
        String reason = financialManageBo.getReason();
        String phone = financialManageBo.getApplicantPhone();
        Long time = new Date().getTime();
        String status = "-2";
//        String url = "http://api.dc-test.zlycare.com/1/operation/withdrawals/";
        String url = projectConfig.getPutUrl();
        String aa=sendFinancial(id,url, status,reason, phone);
    }

    /**
     * 运营同意
     * @param financialManageBo
     * @throws Exception
     */
    public  void updateAgree(FinancialManageBo financialManageBo) throws Exception {
        if(financialManageBo == null||financialManageBo.getId() == null){
            logger.error("审核信息失败");
            throw new ServiceException();
        }
        String id = financialManageBo.getId();
        Long time = new Date().getTime();
        String reason = "";
        String phone = "";
        String status = "2";
//        String url = "http://api.dc-test.zlycare.com/1/operation/withdrawals/";
        String url = projectConfig.getPutUrl();
        String aa=sendFinancial(id,url, status,reason,phone);
        // 无意义代码
//        Query query = new Query();
//        query.addCriteria(Criteria.where(ID).is(id));
//        this.findById(query,AppLications);
    }
    /**
     * 接受页面参数返回数据方法
     * @param status
     * @param start
     * @param pageSize
     * @return
     */
    public List<FinancialManage> getAllList(String applicantPhone,String alipayNum, List<Integer> status,Integer start,Integer pageSize){
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

        List<FinancialManage> backList = this.find(query,AppLications);
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

    public FinancialManage findById(String id){
        if (StringUtils.isEmpty(id)){
            logger.error("查询失败");
            return null;
        }
        Query query = new Query(Criteria.where(ID).is(id));
        FinancialManage financialManage = this.findOne(query,AppLications);
        if(financialManage == null ){
            logger.error("修改出错");
            return null;
        }
        return financialManage;
    }
    
}
