package com.zlycare.web.boss_24.core.service.sys;

import com.zlycare.web.boss_24.core.service.bean.MenuBean;
import com.zlycare.web.boss_24.core.service.bo.MenuBo;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/5/31
 * Update : 2017/5/31
 * Descriptions : 系统管理，安全相关实体的管理类,菜单
 */
public interface MenuService {

    /**
     * 获取Menu
     *
     * @param id id
     * @return MenuPo
     */
    MenuBo getMenu(Long id);

    /**
     * 获取用户的所有菜单
     *
     * @param userId userId
     * @return List<MenuBo>
     */
    List<MenuBo> findAllMenu(Long userId);

    /**
     * 获取角色的所有菜单
     *
     * @param roleId roleId
     * @return List<MenuBo>
     */
    List<MenuBo> findByRoleId(Long roleId);

    /**
     * 获取用户的所有的一级菜单
     *
     * @param userId userId
     * @return List<MenuBo>
     */
    List<MenuBo> findRootMenu(Long userId);

    /**
     * 根据ID获取用户的菜单列表，不包括当前id对于的menu
     *
     * @param userId userId
     * @return List<MenuBo>
     */
    List<MenuBo> findByUserIdAndRootId(Long userId,Long rootId);

    /**
     * 存储Menu底层，外层赋值
     * 角色跟menu关联表，在外层创建，事物操作
     * 先创建2对象，再创建关联表
     *
     * @param menubean menubean
     * @return boolean
     */
    boolean saveUser(MenuBean menubean);

    /**
     * 插入Menu底层，外层赋值
     * 角色跟menu关联表，在外层创建，事物操作
     * 先创建2对象，再创建关联表
     *
     * @param menubean menubean
     * @return boolean
     */
    Integer createMenu(MenuBean menubean);

    /**
     * 删除菜单
     *
     * @param id id
     * @return Integer
     */
    Integer deleteMenu(Long id);
}
