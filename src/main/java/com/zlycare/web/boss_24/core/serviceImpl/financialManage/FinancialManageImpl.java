package com.zlycare.web.boss_24.core.serviceImpl.financialManage;

import com.zlycare.web.boss_24.core.dao.po.FinancliaManagePo;
import com.zlycare.web.boss_24.core.mongo.order.FinancialManageMongo;
import com.zlycare.web.boss_24.core.mongo.po.FinancialManage;
import com.zlycare.web.boss_24.core.service.bo.FinancialManageBo;
import com.zlycare.web.boss_24.core.service.financialManage.FinancialManageService;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/28.
 */

    @Service
    public class FinancialManageImpl implements FinancialManageService {
    private static Logger logger = LoggerFactory.getLogger(FinancialManageImpl.class);
    /**
     * 提现审核表
     */
    //主键
    private static final String ID = "_id";
    //表名
    private static final String AppLications = "applications";

    @Autowired
    private FinancialManageMongo financialManageMongo;


    //重写
    @Transactional//事务标示，对mongo没啥用
    @Override
    public List<FinancialManageBo> getAllList(String applicantPhone,String alipayNum,List<Integer> status,Integer start,Integer pageSize){
        if (pageSize == null ||  start == null){
            logger.error("查询信息失败，param is null");
            return null;
        }
        List<FinancialManage> backList  = financialManageMongo.getAllList(applicantPhone,alipayNum,status,start,pageSize);
        if (CollectionUtils.isEmpty(backList)){
            logger.debug("数据为null");
            return new ArrayList<>();
        }
        for (FinancialManage financialManage:backList){
            if(CollectionUtils.isEmpty(backList)){
                logger.error("无uid直接跳过");
                continue;
            }
        }
        List<FinancialManageBo> financialManageBoList = BeanMapper.mapList(backList,FinancialManageBo.class);
        return financialManageBoList;
    }

    @Override
    public Integer countAllList(String applicantPhone,String alipayNum,List<Integer> status,Integer start,Integer pageSize){
        return financialManageMongo.countAllList(applicantPhone,alipayNum,status,start,pageSize);
    }

    /**
     * 获取对象
     * @param id
     * @return
     */
    @Override
    public FinancialManageBo findById(String id){
        if(StringUtils.isEmpty(id)){
            logger.error("查询ID失败，param is null");
            return null;
        }
        FinancialManage financialManage = financialManageMongo.findById(id);
        if(financialManage == null){
            logger.debug("获取信息失败，id为" + id);
            return null;
        }
        return BeanMapper.map(financialManage,FinancialManageBo.class);
    }

    @Override
    public boolean updateFinancialManage(FinancialManageBo financialManageBo) throws Exception {
        if(financialManageBo == null){
            throw new NullPointerException("financialManage is null");
        }
        financialManageMongo.updateFinancialManage(financialManageBo);
        return true;
    }

    @Override
    public List<FinancialManageBo> getAllLications() {
        return null;
    }

    @Override
    public boolean updateAgree(FinancialManageBo financialManageBo) throws Exception {
        if(financialManageBo == null){
            throw new NullPointerException("financialManage is null");
        }
        financialManageMongo.updateAgree(financialManageBo);

        // 調用接口

        return true;
    }

}
