package com.zlycare.web.boss_24.core.service.sys;

import com.zlycare.web.boss_24.core.service.bo.AreaBo;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/5/31
 * Update : 2017/5/31
 * Descriptions :
 */
public interface AreaService {
    // 封装bo对象 bo对象里封存 list<area> 当前area 拥有的所有子区域对象列表
    // bo对象里封存 parentArrea, 当前area对象的父级area
    // 获取基本对象service、获取完整对象service

    // xml 数据库
    // 抽取 底层po的 工具方法

    /**
     * 查询当前节点和子节点
     *
     * @param parentId parentId
     * @return List<AreaPo>
     */
    List<AreaBo> findAllChild(Long parentId);

    /**
     * 获取地域
     *
     * @param id id
     * @return AreaBo
     */
    AreaBo findOne(Long id);

    /**
     * 查询所有地域，一级平铺
     *
     * @return List<AreaPo>
     */
    List<AreaBo> findAll();
}
