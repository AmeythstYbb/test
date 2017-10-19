package com.zlycare.web.boss_24.core.dao.mapper;

import com.zlycare.web.boss_24.core.dao.po.UserRolePo;
import org.apache.ibatis.annotations.Param;

/**
 * Author : linguodong
 * Create : 2017/5/30
 * Update : 2017/5/30
 * Descriptions :
 */
public interface UserRoleMapper {
    /**
     * 创建关联
     *
     * @return int
     */
    int create(@Param("roleId") long roleId, @Param("userId") long userId);

    /**
     * 查询关联
     *
     * @return int
     */
    UserRolePo get(@Param("roleId") long roleId, @Param("userId") long userId);

    /**
     * 根据roleId删除关联
     *
     * @return Integer
     */
    Integer deleteByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据userId删除关联
     *
     * @return Integer
     */
    Integer deleteByUserId(@Param("userId") Long userId);

}
