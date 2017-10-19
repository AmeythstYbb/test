package com.zlycare.web.boss_24.core.serviceImpl.sys;

import com.zlycare.web.boss_24.constants.sys.MenuConstants;
import com.zlycare.web.boss_24.core.dao.mapper.DeptMapper;
import com.zlycare.web.boss_24.core.dao.po.DeptPo;
import com.zlycare.web.boss_24.core.service.bo.DeptBo;
import com.zlycare.web.boss_24.core.service.sys.DeptService;
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
public class DeptServiceImpl implements DeptService {
    private static Logger logger = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询当前节点和子节点
     *
     * @param parentId parentId
     * @return List<AreaPo>
     */
    @Override
    public List<DeptBo> findAllChild(Long parentId) {
        if (parentId == null) {
            logger.error("查询部门信息失败，parentId为空");
            throw new IllegalArgumentException("parentId is empty");
        }
        List<DeptPo> deptPoList = deptMapper.findAllChild(parentId, MenuConstants.DEL_FLAG_NORMAL);
        if (CollectionUtils.isEmpty(deptPoList)) {
            logger.debug("查询部门信息失败，parentId为" + parentId);
            return Collections.EMPTY_LIST;
        }
        return BeanMapper.mapList(deptPoList, DeptBo.class);
    }

    /**
     * 查询父级部门是param的子级部门,只查询一级
     */
    @Override
    public List<DeptBo> findByParentId(Long parentId) {
        if (parentId == null) {
            logger.error("查询部门信息失败，parentId为空");
            throw new IllegalArgumentException("parentId is empty");
        }
        List<DeptPo> deptPoList = deptMapper.findByParentId(parentId, MenuConstants.DEL_FLAG_NORMAL);
        if (CollectionUtils.isEmpty(deptPoList)) {
            logger.debug("查询部门信息失败，parentId为" + parentId);
            return Collections.EMPTY_LIST;
        }
        return BeanMapper.mapList(deptPoList, DeptBo.class);
    }

    /**
     * 获取地域
     *
     * @param id id
     * @return AreaBo
     */
    @Override
    public DeptBo findOne(Long id) {
        if (id == null) {
            logger.error("查询部门信息失败，id为空");
            throw new IllegalArgumentException("parentId is empty");
        }
        DeptPo deptPo = deptMapper.findOne(id);
        if (deptPo == null) {
            logger.debug("查询部门信息失败，id 为" + id);
            return null;
        }
        return BeanMapper.map(deptPo, DeptBo.class);
    }
}
