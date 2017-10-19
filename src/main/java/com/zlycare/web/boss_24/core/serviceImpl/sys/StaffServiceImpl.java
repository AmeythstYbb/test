package com.zlycare.web.boss_24.core.serviceImpl.sys;/**
 * Created by zhanglw on 2017/6/6.
 */

import com.zlycare.web.boss_24.constants.sys.MenuConstants;
import com.zlycare.web.boss_24.core.dao.mapper.StaffMapper;
import com.zlycare.web.boss_24.core.dao.mapper.UserRoleMapper;
import com.zlycare.web.boss_24.core.dao.po.StaffPo;
import com.zlycare.web.boss_24.core.service.bo.DeptBo;
import com.zlycare.web.boss_24.core.service.bo.StaffBo;
import com.zlycare.web.boss_24.core.service.sys.DeptService;
import com.zlycare.web.boss_24.core.service.sys.StaffService;
import com.zlycare.web.boss_24.security.SystemRealm;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author : zhanglianwei
 * Create : 2017/6/6 10:06
 * Update : 2017/6/6 10:06
 * Descriptions :
 */
@Service
public class StaffServiceImpl implements StaffService {
    private static Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);


    // todo 补全注释、参数校验、异常捕获处理
    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private SystemRealm systemRealm;

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private DeptService deptService;

    @Override
    public List<StaffBo> findAll(Map<String, Object> mapParams) {
        int page = (int) mapParams.get("page");
        int pageSize = (int) mapParams.get("pageSize");
        //int startIndex = (page - 1) * pageSize;
        int startIndex = page;
        List<StaffPo> staffPoList = staffMapper.findAllListWithPage(mapParams, startIndex, pageSize);
        List<StaffBo> boList = BeanMapper.mapList(staffPoList, StaffBo.class);
        List<StaffBo> newBoList = new ArrayList<>();
        for (StaffBo staffBo : boList) {
            staffBo = this.userBoInit(staffBo);
            newBoList.add(staffBo);
        }
        return newBoList;
    }

    @Transactional
    @Override
    public int insert(Map<String, Object> mapParams) {
        systemRealm.clearAllCachedAuthorizationInfo();
        return staffMapper.insert(mapParams);
    }

    @Override
    public int count(Map<String, Object> mapParams) {
        return staffMapper.count(mapParams);
    }

    @Transactional
    @Override
    public int update(Map<String, Object> mapParams) {
        int count = staffMapper.update(mapParams);

        // 变为离职，解绑角色。如果解绑对是当前用户的角色呢？
        if (mapParams.get("id") != null && (Integer) mapParams.get("status") == 2) {
            userRoleMapper.deleteByUserId(Long.valueOf((Integer) mapParams.get("id")));
        }
        if (mapParams.get("staffName") != null) {
            systemRealm.clearAllCachedAuthorizationInfo();
        }
        return count;
    }

    @Transactional
    @Override
    public int delete(Integer id) {
        systemRealm.clearAllCachedAuthorizationInfo();
        userRoleMapper.deleteByUserId(Long.valueOf(id));
        return staffMapper.fakeDelete(id);
    }

    @Override
    public StaffBo findOne(Integer staffId) {
        StaffPo staffPo = staffMapper.findOne(staffId);
        return BeanMapper.map(staffPo, StaffBo.class);
    }

    /**
     * 封装用户对象
     *
     * @param staffBo staffBo
     * @return StaffBo
     */
    private StaffBo userBoInit(StaffBo staffBo) {
        // 封装用户部门对象
        Integer deptId = staffBo.getDeptId();
        if (deptId == null) {
            logger.error("查询用户信息的部门信息失败，用户id为" + staffBo.getId());
        } else {
            DeptBo deptBo = deptService.findOne(deptId.longValue());
            if (deptBo == null) {
                logger.debug("查询用户信息的部门信息失败，数据为null,用户id为" + staffBo.getId());
            } else {
                staffBo.setDeptBo(deptBo);
            }
        }
        // 封装用户子部门对象
        Integer deptChildrenId = staffBo.getDeptChildrenId();
        if (deptChildrenId == null) {
            logger.error("查询用户信息的子部门信息失败，用户id为" + staffBo.getId());
        } else {
            DeptBo deptChildrenBo = deptService.findOne(deptChildrenId.longValue());
            if (deptChildrenBo == null) {
                logger.debug("查询用户信息的子部门信息失败，数据为null,用户id为" + staffBo.getId());
            } else {
                staffBo.setDeptChildrenBo(deptChildrenBo);
            }
        }
        return staffBo;
    }

    /**
     * 根据手机号获取用户
     *
     * @param phone phone
     * @return User
     */
    @Override
    public StaffBo getUserByPhone(String phone) {
        if (phone == null) {
            logger.error("查询用户信息失败，用户phone为空");
            return null;
        }
        StaffPo staffPo = staffMapper.getUserByPhone(phone, MenuConstants.DEL_FLAG_NORMAL);
        if (staffPo == null) {
            logger.debug("查询用户信息失败，用户jobNumber为" + phone);
            return null;
        }
        StaffBo staffBo = BeanMapper.map(staffPo, StaffBo.class);
        // userBo = this.userBoInit(userBo);
        return staffBo;
    }
}
