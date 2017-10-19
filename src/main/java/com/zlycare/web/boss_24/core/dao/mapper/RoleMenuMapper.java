package com.zlycare.web.boss_24.core.dao.mapper;

import com.zlycare.web.boss_24.core.dao.po.RoleMenuPo;
import org.apache.ibatis.annotations.Param;

/**
 * Author : linguodong
 * Create : 2017/5/30
 * Update : 2017/5/30
 * Descriptions :
 */
public interface RoleMenuMapper {
    /**
     * 创建关联
     *
     * @return int
     */
    int create(@Param("roleId") long roleId, @Param("menuId") long menuId);

    /**
     * 查询关联
     *
     * @return int
     */
    RoleMenuPo get(@Param("roleId") long roleId, @Param("menuId") long menuId);

    /**
     * 根据roleId删除关联
     *
     * @return Integer
     */
    Integer deleteByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据menuID删除关联
     *
     * @return Integer
     */
    Integer deleteByMenuId(@Param("menuId") Long menuId);

}
