package com.zlycare.web.boss_24.core.service.memberProduct;

import com.zlycare.web.boss_24.core.service.bean.MemberBean;
import com.zlycare.web.boss_24.core.service.bo.member.MemberBo;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/8/4
 * Update : 2017/8/4
 * Descriptions : 会员管理service
 */
public interface MemberService {

    /**
     * 查询会员列表
     *
     * @param memberBean bean
     * @return List
     */
    List<MemberBo> getAllList(MemberBean memberBean);

    /**
     * 查询会员列表 条数(忽略start和size,其余参数和上一方法一致)
     *
     * @param memberBean bean
     * @return Integer
     */
    Integer countAllList(MemberBean memberBean);
}
