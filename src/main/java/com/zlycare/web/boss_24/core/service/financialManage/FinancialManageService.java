package com.zlycare.web.boss_24.core.service.financialManage;

import com.zlycare.web.boss_24.core.service.bo.FinancialManageBo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/9/28.
 */
public interface FinancialManageService {

    /**
     * 查询提现列表
     * //    List<FinancialManageBo> getAllLications();//定义一个借口，在Impl中重写此接口
     * @param status
     * @param start
     * @param pagerSize
     * @return
     */
    List<FinancialManageBo> getAllList(
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
//    FinancialManageBo getUserByJobNumber(String jobNumber);

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
    FinancialManageBo findById(String id);

    boolean updateFinancialManage(FinancialManageBo financialManageBo) throws Exception;

    //重写
    @Transactional

    List<FinancialManageBo> getAllLications();

    /**
     *
     * @param financialManageBo
     * @return
     * @throws Exception
     */
    boolean updateAgree(FinancialManageBo financialManageBo) throws Exception;
}
