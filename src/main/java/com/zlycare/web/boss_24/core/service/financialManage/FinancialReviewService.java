package com.zlycare.web.boss_24.core.service.financialManage;



import com.zlycare.web.boss_24.core.service.bo.FinancialReviewBo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sulong on 2017/10/12.
 */
public interface FinancialReviewService {

    /**
     * 查询提现列表
     * //    List<FinancialReviewBo> getAllLications();//定义一个借口，在Impl中重写此接口
     * @param status
     * @param start
     * @param pagerSize
     * @return
     */
    List<FinancialReviewBo> getAllList(
            String applicantPhone,
            String alipayNum,
            List<Integer> status,
            Integer start,
            Integer pagerSize
    );

    /**
     * g根据登录名获取账号 在User中写方法了，这里直接用
     * @param jobNumber
     * @return
     */
//    FinancialReviewBo getUserByJobNumber(String jobNumber);

    /**
     * 获取页面页数
     * @param status
     * @param start
     * @param pageSize
     * @return
     */
    Integer countAllList(
            String applicantPhone,
            String alipayNum,
            List<Integer> status,
            Integer start,
            Integer pageSize
    );

    /**
     * 获取对象（id）
     * @param id
     * @return
     */
    FinancialReviewBo findById(String id);

    boolean updateFinancialReview(FinancialReviewBo financialReviewBo) throws Exception;

    //重写
    @Transactional
    List<FinancialReviewBo> getAllLications();

    /**
     * 运营同意
     * @param financialReviewBo
     * @return
     * @throws Exception
     */
    boolean updateAgree(FinancialReviewBo financialReviewBo) throws Exception;

    void financialUpload(List<FinancialReviewBo> financialReviewBoList) throws Exception;
}
