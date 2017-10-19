package com.zlycare.web.boss_24.core.service.sys;

import com.zlycare.web.boss_24.core.service.bean.RoleBean;
import com.zlycare.web.boss_24.core.service.bo.RoleBo;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/5/31
 * Update : 2017/5/31
 * Descriptions : 系统管理，安全相关实体的管理类,角色
 */
public interface RoleService {

    /**
     * 获取角色
     *
     * @param id id
     * @return RoleBo
     */
    RoleBo getRole(Long id);

    /**
     * 获取角色
     *
     * @param name name
     * @return RoleBo
     */
    RoleBo findRoleByName(String name);

    /**
     * 获取用户的所有角色
     *
     * @param userId userId
     * @return List<RoleBo>
     */
    List<RoleBo> findAllRole(Long userId);

    /**
     * 只在删除角色时查询用户关联的角色时使用,还有给用户赋权限查询用户拥有的角色时使用(针对1)
     *
     * @param userId userId
     * @return List<RoleBo>
     */
    List<RoleBo> findByUserId(Long userId);

    /**
     * (废)
     * 存储角色底层，外层赋值
     * 角色跟menu关联表，在外层创建，事物操作
     *
     * @param roleBean roleBean
     * @return boolean
     */
    Integer saveRole(RoleBean roleBean);

    /**
     * 创建权限、权限和menu关联表
     *
     * @param name       name
     * @param menu_check menu_check
     * @return boolean
     */
    boolean InsertRoleAndMenu(String name, String menu_check);

    /**
     * 修改权限、权限和menu关联表
     *
     * @param name       name
     * @param menu_check menu_check
     * @return boolean
     */
    boolean updateRoleAndMenu(String name, String menu_check, Long roleId);

    /**
     * 删除角色
     *
     * @param id id
     * @return Integer
     */
    Integer deleteRole(Long id);
}
