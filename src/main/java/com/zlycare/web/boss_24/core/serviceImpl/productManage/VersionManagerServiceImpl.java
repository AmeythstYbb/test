package com.zlycare.web.boss_24.core.serviceImpl.productManage;

import com.zlycare.web.boss_24.beans.exception.ServiceException;
import com.zlycare.web.boss_24.core.mongo.order.VersionMongo;
import com.zlycare.web.boss_24.core.mongo.po.ProductCatalog;
import com.zlycare.web.boss_24.core.mongo.po.Version;
import com.zlycare.web.boss_24.core.service.bean.VersionBean;
import com.zlycare.web.boss_24.core.service.bo.ProductCatalogBo;
import com.zlycare.web.boss_24.core.service.bo.VersionBo;
import com.zlycare.web.boss_24.core.service.productManage.VersionManageService;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/8/31
 * Update : 2017/8/31
 * Descriptions :
 */
@Service
public class VersionManagerServiceImpl implements VersionManageService {
    private static final Logger logger = LoggerFactory.getLogger(VersionManagerServiceImpl.class);
    @Autowired
    private VersionMongo versionMongo;
    // 版本表
    private static final String VERSION = "versions";

    /**
     * 查询所有24热线版本列表
     *
     * @param pageSize pageSize
     * @param start    start
     * @return List<VersionBo>
     */
    @Override
    public List<VersionBo> getVersionList(Integer pageSize, Integer start) {
        if (pageSize == null || start == null) {
            logger.error("查询版本信息失败，param is null");
            return new ArrayList<>();
        }
        List<Version> versionList = versionMongo.getVersionList(pageSize, start);
        if (CollectionUtils.isEmpty(versionList)) {
            logger.debug("查询版本信息失败，数据为null");
            return new ArrayList<>();
        }
        return BeanMapper.mapList(versionList, VersionBo.class);
    }

    /**
     * 查询所有24热线版本列表 数量
     *
     * @return Integer
     */
    @Override
    public Integer countAllList() {
        return versionMongo.countAllList();
    }

    /**
     * 删除版本
     *
     * @param id id
     * @return Integer
     */
    @Override
    public Integer deleteVersion(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("删除版本失败，id为空");
            return null;
        }
        versionMongo.deleteVersion(id);
        return 1;
    }

    /**
     * 获取版本
     *
     * @param id id
     * @return VersionBo
     */
    @Override
    public VersionBo getVersion(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("获取版本失败，id为空");
            return null;
        }
        Version version = versionMongo.getVersion(id);
        if (version == null) {
            logger.debug("获取版本失败，id为" + id);
            return null;
        }
        return BeanMapper.map(version, VersionBo.class);
    }

    /**
     * 插入、修改 版本
     *
     * @param versionBean versionBean
     */
    @Override
    public void saveVersion(VersionBean versionBean) {
        //创建版本信息，初始化各种状态，time
        if (versionBean == null) {
            throw new NullPointerException("create or update version failed，versionBean is null");
        }
        Version version = BeanMapper.map(versionBean, Version.class);
        Long time = new Date().getTime();

        if (StringUtils.isEmpty(version.getId())) {/*插入版本*/
            version.setId(null);//""会被存储到mongo
            // 创建时间
            version.setTime(time.doubleValue());
            try {
                versionMongo.create(version, VERSION);
            } catch (Exception e) {
                logger.error("Failed insert version file to mongodb");
                throw new ServiceException();
            }
        } else {/*修改版本*/
            // 查询原始对象后修改字段，再次保存
            VersionBo versionBo = getVersion(version.getId());
            // 修改参数
            versionBo.setDesc(versionBean.getDesc());
            versionBo.setName(versionBean.getName());
            versionBo.setCode(versionBean.getCode());
            versionBo.setUrl(versionBean.getUrl());
            versionBo.setBadCode(versionBean.getBadCode());
            versionBo.setMinCode(versionBean.getMinCode());
            versionBo.setType(versionBean.getType());
            versionMongo.save(BeanMapper.map(versionBo, Version.class), VERSION);
        }
    }
}
