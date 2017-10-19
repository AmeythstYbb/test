package com.zlycare.web.boss_24.core.dao.mapper;

import com.zlycare.web.boss_24.core.dao.po.BusinessPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/5/30
 * Update : 2017/5/30
 * Descriptions :
 */
public interface BusinessMapper {
    /**
     * 获取地域
     *
     * @param id id
     * @return User
     */
    BusinessPo findOne(@Param("id") long id);

    /**
     * 根据id删除Area, 删除id匹配的数据 or 删除父节点id 模糊匹配的数据。删除父节点的时候同时删除子节点
     *
     * @param id      id
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_DELETE, 修改状态
     * @return Integer
     */
    Integer deleteById(@Param("id") long id, @Param("delFlag") String delFlag);

    /**
     * 查询父节点下的子节点，不包含自己
     *
     * @param delFlag  delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @param parentId parentId
     * @return List<BusinessPo>
     */
    List<BusinessPo> findByParentIdsLike(@Param("parentId") Long parentId, @Param("delFlag") String delFlag);

    /**
     * 查询所有地域
     *
     * @param delFlag delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @return List<BusinessPo>
     */
    List<BusinessPo> findAllList(@Param("delFlag") String delFlag);

    /**
     * 查询当前节点和子节点
     *
     * @param delFlag  delFlag 默认 MenuConstants.DEL_FLAG_NORMAL
     * @param parentId parentId
     * @return List<BusinessPo>
     */
    List<BusinessPo> findAllChild(@Param("parentId") Long parentId, @Param("delFlag") String delFlag);

}
