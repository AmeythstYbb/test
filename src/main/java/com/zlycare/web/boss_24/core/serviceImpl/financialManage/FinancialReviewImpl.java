package com.zlycare.web.boss_24.core.serviceImpl.financialManage;

import com.google.common.collect.Lists;
import com.zlycare.web.boss_24.constants.ProjectConfig;
import com.zlycare.web.boss_24.core.exception.excel.ExcelColumnException;
import com.zlycare.web.boss_24.core.mongo.po.FinancialReview;
import com.zlycare.web.boss_24.core.service.financialManage.FinancialReviewService;
import com.zlycare.web.boss_24.core.service.bo.FinancialReviewBo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zlycare.web.boss_24.core.mongo.order.FinancialReviewMongo;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import java.util.List;
import static com.zlycare.web.boss_24.utils.financialManage.HttpUrlPUT.sendFinancial;


/**
 * Created by Sulong on 2017/9/28.
 */

@Service
public class FinancialReviewImpl implements FinancialReviewService {
    private static Logger logger = LoggerFactory.getLogger(FinancialReviewImpl.class);
    /**
     * 提现审核表
     */
    //主键
    private static final String ID = "_id";
    //表名
    private static final String AppLications = "applications";

    @Autowired
    private FinancialReviewMongo financialReviewMongo;
    @Autowired
    ProjectConfig projectConfig;


    //重写
    @Transactional//事务标示，对mongo没啥用
    @Override
    public List<FinancialReviewBo> getAllList(String applicantPhone, String alipayNum, List<Integer> status, Integer start, Integer pageSize) {
        if (pageSize == null || start == null) {
            logger.error("获取信息失败，param is null");
            throw new ExcelColumnException("获取信息失败(param is null)");

        }
        List<FinancialReview> backList = financialReviewMongo.getAllList(applicantPhone, alipayNum, status, start, pageSize);
        if (CollectionUtils.isEmpty(backList)) {
            logger.debug("数据为null");
            throw new ExcelColumnException("获取信息失败(数据为null)");
        }
        for (FinancialReview financialReview : backList) {
            if (CollectionUtils.isEmpty(backList)) {
                logger.error("获取信息失败");
                throw new ExcelColumnException("获取信息失败");
            }
        }
        List<FinancialReviewBo> financialReviewBoList = BeanMapper.mapList(backList, FinancialReviewBo.class);
        return financialReviewBoList;
    }

    @Override
    public Integer countAllList(String applicantPhone, String alipayNum, List<Integer> status, Integer start, Integer pageSize) {
        return financialReviewMongo.countAllList(applicantPhone, alipayNum, status, start, pageSize);
    }

    /**
     * 获取对象
     *
     * @param id
     * @return
     */
    @Override
    public FinancialReviewBo findById(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("查询ID失败，param is null");
            throw new ExcelColumnException("查询ID失败，param is null");

        }
        FinancialReview financialReview = financialReviewMongo.findById(id);
        if (financialReview == null) {
            logger.debug("获取信息失败，id为" + id);
            throw new ExcelColumnException("获取信息失败");
        }
        return BeanMapper.map(financialReview, FinancialReviewBo.class);
    }

    @Override
    public boolean updateFinancialReview(FinancialReviewBo financialReviewBo) throws Exception {
        if (financialReviewBo == null) {
            throw new NullPointerException("financialReview is null");
        }
        financialReviewMongo.updateFinancialReview(financialReviewBo);
        return true;
    }

    @Override
    public List<FinancialReviewBo> getAllLications() {
        return null;
    }

    @Override
    public boolean updateAgree(FinancialReviewBo financialReviewBo) throws Exception {
        if (financialReviewBo == null) {
            throw new NullPointerException("financialReview is null");
        }
        financialReviewMongo.updateAgree(financialReviewBo);



        return true;
    }

    @Override
    public void financialUpload(List<FinancialReviewBo> financialReviewBoList) {
        //业务逻辑
        List<String> excelStatusList = Lists.newArrayList("同意", "拒绝");
        financialReviewBoList.stream().forEach(f -> {
//             System.out.println(f.getId());
             String uuid = f.getId();
            FinancialReviewBo financialReviewBouuid = findById(uuid);
            if (financialReviewBouuid == null || financialReviewBouuid.getStatus() !=2) {
                logger.error("不能操作已处理数据" + uuid);
                throw new ExcelColumnException("文件格式有误(不能操作已处理数据)" + uuid);
            }

            String status = null;

            String excel = f.getExcelstatus();
            System.out.print(excel);
            if (!excelStatusList.contains(f.getExcelstatus())) {
                logger.error("文件格式有误(获取信息失败)");
                throw new ExcelColumnException("文件格式有误(获取信息失败)");
            }
            //调用工具类准备参数
            if (excel .equals("同意")) {
                status = "3";
            } else {
                status = "-3";
            }
            //String url = "http://api.dc-test.zlycare.com/1/operation/withdrawals/";
            String url = projectConfig.getPutUrl();
            String id = f.getId();
            // 判断iD是否为空
            //application 通过id查询实体，判断实体为空、手机号为空 ApplicantPhone
//            不为空把手机号给phobne，查不到实体，log，continue
            if (StringUtils.isEmpty(id)) {
                logger.error("文件格式有误(获取信息失败)");
                throw new ExcelColumnException("文件格式有误(获取信息失败)");
            }
            FinancialReviewBo financialReviewBo = findById(id);
            if (financialReviewBo == null || StringUtils.isEmpty(financialReviewBo.getApplicantPhone())) {
                logger.error("提现申请ID有误" + id);
                throw new ExcelColumnException("提现申请ID有误" + id);
            }
            String phone = financialReviewBo.getApplicantPhone();
            String reason = f.getReason();
//        System.out.print(status);
            String aa = null;
            try {

                aa = sendFinancial(id, url, status, reason,phone);
                System.out.print(aa);
            } catch (Exception e) {
                logger.error("调用接口传入参数抛出异常" + id + ",error:" + e.getMessage());//抛出异常了这里
//                e.printStackTrace();
            }
        });
    }

}
