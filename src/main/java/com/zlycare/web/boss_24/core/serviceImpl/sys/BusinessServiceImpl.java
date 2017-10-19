package com.zlycare.web.boss_24.core.serviceImpl.sys;

import com.zlycare.web.boss_24.constants.sys.MenuConstants;

import com.zlycare.web.boss_24.core.dao.mapper.BusinessMapper;
import com.zlycare.web.boss_24.core.dao.po.BusinessPo;
import com.zlycare.web.boss_24.core.service.bo.BusinessBo;
import com.zlycare.web.boss_24.core.service.sys.BusinessService;
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
public class BusinessServiceImpl implements BusinessService {
    private static Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);

    @Autowired
    private BusinessMapper businessMapper;

    /**
     * 查询当前节点和子节点
     *
     * @param parentId parentId
     * @return List<BusinessBo>
     */
    @Override
    public List<BusinessBo> findAllChild(Long parentId) {
        if (parentId == null) {
            logger.error("查询业务板块信息失败，parentId为空");
            throw new IllegalArgumentException("parentId is empty");
        }
        List<BusinessPo> businessBos = businessMapper.findAllChild(parentId, MenuConstants.DEL_FLAG_NORMAL);
        if (CollectionUtils.isEmpty(businessBos)) {
            logger.debug("查询业务板块信息失败，parentId为" + parentId);
            return Collections.EMPTY_LIST;
        }
        return BeanMapper.mapList(businessBos, BusinessBo.class);
    }

    /**
     * 获取业务板块
     *
     * @param id id
     * @return BusinessBo
     */
    @Override
    public BusinessBo findOne(Long id) {
        if (id == null) {
            logger.error("查询业务板块信息失败，id为空");
            throw new IllegalArgumentException("parentId is empty");
        }
        BusinessPo businessPo = businessMapper.findOne(id);
        if (businessPo == null) {
            logger.debug("查询业务板块信息失败，id 为" + id);
            return null;
        }
        return BeanMapper.map(businessPo, BusinessBo.class);
    }
}
