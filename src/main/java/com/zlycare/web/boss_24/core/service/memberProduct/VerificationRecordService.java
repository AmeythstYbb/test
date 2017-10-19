package com.zlycare.web.boss_24.core.service.memberProduct;

import com.zlycare.web.boss_24.core.service.bean.VerificationRecordBean;
import com.zlycare.web.boss_24.core.service.bo.VerificationRecordBo;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/7/17
 * Update : 2017/7/17
 * Descriptions :
 */
public interface VerificationRecordService {
    /**
     * 查询核销列表
     *
     * @param verificationRecordBean bean
     * @return List
     */
    List<VerificationRecordBo> getAllList(VerificationRecordBean verificationRecordBean);

    /**
     * 查询核销列表 条数 忽略start和size
     *
     * @param verificationRecordBean bean
     * @return Integer
     */
    Integer countAllList(VerificationRecordBean verificationRecordBean);
}
