package com.zlycare.web.boss_24.core.dao.mapper;/**
 * Created by zhanglw on 2017/6/5.
 */

import com.zlycare.web.boss_24.core.dao.po.StaffPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Author : zhanglianwei
 * Create : 2017/6/5 11:55
 * Update : 2017/6/5 11:55
 * Descriptions :
 */
public interface StaffMapper {

    /**
     * 查找List
     *
     * @return
     */
    List<StaffPo> findAllListWithPage(@Param("paramsMap") Map<String, Object> paramsMap, @Param("startIndex") int startIndex, @Param("limit") int limit);

    /**
     * 修改
     *
     * @return
     */
    int update(@Param("paramsMap") Map<String, Object> paramsMap);

    /**
     * 查找一个
     *
     * @return
     */
    StaffPo findOne(@Param("id") Integer id);

    int insert(@Param("staffMap") Map<String, Object> staffMap);

    int count(@Param("paramsMap") Map<String, Object> mapParams);

    int fakeDelete(@Param("staffId") Integer id);

    /**
     * 获取用户
     *
     * @param phone      phone
     * @param deleteFlag deleteFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return User
     */
    StaffPo getUserByPhone(@Param("phone") String phone, @Param("deleteFlag") String deleteFlag);

}
