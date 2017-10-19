package com.zlycare.web.boss_24.core.service.sys;/**
 * Created by zhanglw on 2017/6/5.
 */

import com.zlycare.web.boss_24.core.service.bo.StaffBo;

import java.util.List;
import java.util.Map;

/**
 * Author : zhanglianwei
 * Create : 2017/6/5 13:34
 * Update : 2017/6/5 13:34
 * Descriptions : 获得人员信息
 */
public interface StaffService {
    // todo 补全注释

    List<StaffBo> findAll(Map<String, Object> mapParams);

    int insert(Map<String, Object> mapParams);

    int count(Map<String, Object> mapParams);

    int update(Map<String, Object> mapParams);

    int delete(Integer id);

    StaffBo findOne(Integer staffId);
    /**
     * 根据手机号获取用户
     *
     * @param phone phone
     * @return User
     */
    StaffBo getUserByPhone(String phone);
}
