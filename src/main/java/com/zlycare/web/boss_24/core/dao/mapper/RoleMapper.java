package com.zlycare.web.boss_24.core.dao.mapper;

import com.zlycare.web.boss_24.core.dao.po.RolePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/5/30
 * Update : 2017/5/30
 * Descriptions :
 */
public interface RoleMapper {
    /**
     * 创建角色
     * @param rolePo rolePo
     * @return int
     */
    int create(RolePo rolePo);

    /**
     * 修改角色
     * @param rolePo po
     */
    void update(RolePo rolePo);
    /**
     * 获取角色
     *
     * @param id id
     * @return User
     */
    RolePo findOne(@Param("id") long id);
    /**
     * 根据姓名获取角色
     *
     * @param name name
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return Role
     */
    RolePo findByName(@Param("name") String name, @Param("delFlag") String delFlag);

    /**
     * 删除角色
     *
     * @param id id
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_DELETE
     * @return Integer
     */
    Integer deleteById(@Param("id") Long id, @Param("delFlag") String delFlag);

    /**
     * 查询所有角色
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<Role>
     */
    List<RolePo> findAllList(@Param("delFlag") String delFlag);

    /**
     * 查询用户拥有的所有角色
     * @param userId userId
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<Role>
     */
    List<RolePo> findByUserId(@Param("userId") Long userId, @Param("delFlag") String delFlag);
}
