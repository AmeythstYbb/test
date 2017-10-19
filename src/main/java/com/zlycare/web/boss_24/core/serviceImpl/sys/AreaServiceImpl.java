package com.zlycare.web.boss_24.core.serviceImpl.sys;

import com.zlycare.web.boss_24.constants.sys.MenuConstants;
import com.zlycare.web.boss_24.core.dao.mapper.AreaMapper;
import com.zlycare.web.boss_24.core.dao.po.AreaPo;
import com.zlycare.web.boss_24.core.service.bo.AreaBo;
import com.zlycare.web.boss_24.core.service.sys.AreaService;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/5/31
 * Update : 2017/5/31
 * Descriptions :
 */
@Service
public class AreaServiceImpl implements AreaService {
    private static Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Autowired
    private AreaMapper areaMapper;

    /**
     * 查询当前节点和子节点
     *
     * @param parentId parentId
     * @return List<AreaPo>
     */
    @Override
    public List<AreaBo> findAllChild(Long parentId) {
        if (parentId == null) {
            logger.error("查询地域信息失败，parentId为空");
            throw new IllegalArgumentException("parentId is empty");
        }
        List<AreaPo> areaPoList = areaMapper.findAllChild(parentId, MenuConstants.DEL_FLAG_NORMAL);
        if (CollectionUtils.isEmpty(areaPoList)) {
            logger.debug("查询地域信息失败，parentId为" + parentId);
            return Collections.EMPTY_LIST;
        }
        return BeanMapper.mapList(areaPoList, AreaBo.class);
    }

    /**
     * 查询所有地域，一级平铺
     *
     * @return List<AreaPo>
     */
    @Override
    public List<AreaBo> findAll() {
        List<AreaPo> areaPoList = areaMapper.findAll(MenuConstants.DEL_FLAG_NORMAL);
        if (CollectionUtils.isEmpty(areaPoList)) {
            logger.debug("查询地域信息失败");
            return Collections.EMPTY_LIST;
        }
        return BeanMapper.mapList(areaPoList, AreaBo.class);
    }

    /**
     * 获取地域
     *
     * @param id id
     * @return AreaBo
     */
    @Override
    public AreaBo findOne(Long id) {
        if (id == null) {
            logger.error("查询地域信息失败，id为空");
            throw new IllegalArgumentException("parentId is empty");
        }
        AreaPo areaPo = areaMapper.findOne(id);
        if (areaPo == null) {
            logger.debug("查询地域信息失败，id 为" + id);
            return null;
        }
        return BeanMapper.map(areaPo, AreaBo.class);
    }
}
