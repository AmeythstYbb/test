package com.zlycare.web.boss_24.core.dao.mapper;

import com.zlycare.web.boss_24.core.dao.po.MenuPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/5/30
 * Update : 2017/5/30
 * Descriptions :
 */
public interface MenuMapper {
    /**
     * 创建角色
     *
     * @param menuPo menuPo
     * @return int
     */
    int create(MenuPo menuPo);

    /**
     * 修改角色
     *
     * @param menuPo menuPo
     * @return int
     */
    void update(MenuPo menuPo);

    /**
     * 获取菜单
     *
     * @param id id
     * @return MenuPo
     */
    MenuPo findOne(@Param("id") long id);

    /**
     * 查询父节点下的子节点
     *
     * @param parentId parentId
     * @param delFlag  delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<Menu>
     */
    List<MenuPo> findByParentIdsLike(@Param("parentId") Long parentId, @Param("delFlag") String delFlag);

    /**
     * 查询所有菜单
     *
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<Menu>
     */
    List<MenuPo> findAllList(@Param("delFlag") String delFlag);

    /**
     * 根据id删除menu, 删除id匹配的数据 or 删除父节点id 模糊匹配的数据。删除父节点的时候同时删除子节点
     *
     * @param id      id
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_DELETE
     * @return Integer
     */
    Integer deleteById(@Param("id") long id, @Param("delFlag") String delFlag);

    /**
     * 查询用户拥有的所有菜单
     *
     * @param userId  userId
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<Role>
     */
    List<MenuPo> findByUserId(@Param("userId") Long userId, @Param("delFlag") String delFlag);

    /**
     * 查询角色拥有的所有菜单
     *
     * @param roleId  roleId
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<Role>
     */
    List<MenuPo> findByRoleId(@Param("roleId") Long roleId, @Param("delFlag") String delFlag);

    /**
     * 查询用户拥有的id下的所有菜单，不含当前id对应menu
     *
     * @param userId  userId
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<Role>
     */
    List<MenuPo> findByUserIdAndRootId(@Param("userId") Long userId, @Param("rootId") Long rootId, @Param("delFlag") String delFlag);

    /**
     * 查询id下的所有菜单，不含当前id对应menu
     *
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<Role>
     */
    List<MenuPo> findByRootId(@Param("rootId") Long rootId, @Param("delFlag") String delFlag);


    /**
     * 查询用户拥有的所有一级菜单
     *
     * @param userId  userId
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<Role>
     */
    List<MenuPo> findRootByUserId(@Param("userId") Long userId, @Param("delFlag") String delFlag);

    /**
     * 查询所有一级菜单
     *
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<Role>
     */
    List<MenuPo> findAllRoot(@Param("delFlag") String delFlag);
}
