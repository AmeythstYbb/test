package com.zlycare.web.boss_24.core.service.productManage;

import com.zlycare.web.boss_24.core.service.bean.VersionBean;
import com.zlycare.web.boss_24.core.service.bo.MemberProductBo;
import com.zlycare.web.boss_24.core.service.bo.VersionBo;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/8/31
 * Update : 2017/8/31
 * Descriptions : 24热线版本Service
 */
public interface VersionManageService {
    /**
     * 查询所有24热线版本列表,分页
     *
     * @param pageSize pageSize
     * @param start    start
     * @return List<VersionBo>
     */
    List<VersionBo> getVersionList(Integer pageSize, Integer start);

    /**
     * 查询所有24热线版本列表 数量
     *
     * @return Integer
     */
    Integer countAllList();

    /**
     * 删除版本
     *
     * @param id id
     * @return Integer
     */
    Integer deleteVersion(String id);


    /**
     * 获取版本
     *
     * @param id id
     * @return VersionBo
     */
    VersionBo getVersion(String id);

    /**
     * 插入、修改 版本
     *
     * @param versionBean versionBean
     */
    void saveVersion(VersionBean versionBean);
}
