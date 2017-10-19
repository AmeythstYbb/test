package com.zlycare.web.boss_24.core.serviceImpl.sys;

import com.zlycare.web.boss_24.constants.sys.MenuConstants;
import com.zlycare.web.boss_24.core.dao.mapper.UserMapper;
import com.zlycare.web.boss_24.core.dao.mapper.UserRoleMapper;
import com.zlycare.web.boss_24.core.dao.po.UserPo;
import com.zlycare.web.boss_24.core.service.bean.UserBean;
import com.zlycare.web.boss_24.core.service.bo.*;
import com.zlycare.web.boss_24.core.service.sys.*;
import com.zlycare.web.boss_24.security.SystemRealm;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.user.Password;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Author : linguodong
 * Create : 2017/5/31
 * Update : 2017/5/31
 * Descriptions : 系统管理，安全相关实体的管理类,用户
 */
@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AreaService areaService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private SystemRealm systemRealm;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private DeptService deptService;

    /**
     * 根据id获取用户信息
     *
     * @param id id
     * @return User
     */
    @Override
    public UserBo getUser(Long id) {
        if (id == null) {
            logger.error("查询用户信息失败，用户id为空");
            return null;
        }
        UserPo user = userMapper.findOne(id);
        if (user == null) {
            logger.debug("查询用户信息失败，用户id为" + id);
            return null;
        }
        UserBo userBo = BeanMapper.map(user, UserBo.class);
        userBo = this.userBoInit(userBo);
        return userBo;
    }

    /**
     * 查询当前用户可以查看到的所有用户列表。地域、业务板块、角色
     *
     * @return User
     */
    @Override
    public List<UserBo> findUser(Integer pageSize, Integer start) {
        if (pageSize == null || start == null) {
            logger.error("查询用户信息失败，用户id为空");
            return null;
        }
        List<UserPo> userPoList = userMapper.findAllListPage(pageSize, start, MenuConstants.DEL_FLAG_NORMAL);
        return CollectionUtils.isEmpty(userPoList) ? new LinkedList<>() : BeanMapper.mapList(userPoList, UserBo.class);
    }

    @Override
    public List<UserBo> findUser() {
        List<UserPo> userPoList = userMapper.findAllList(MenuConstants.DEL_FLAG_NORMAL);
        return CollectionUtils.isEmpty(userPoList) ? new LinkedList<>() : BeanMapper.mapList(userPoList, UserBo.class);
    }

    /**
     * 根据登录名获取账号
     *
     * @param jobNumber jobNumber
     * @return User
     */
    @Override
    public UserBo getUserByJobNumber(String jobNumber) {
        if (jobNumber == null) {
            logger.error("查询用户信息失败，用户jobNumber为空");
            return null;
        }
        UserPo user = userMapper.findByJobNumber(jobNumber, MenuConstants.DEL_FLAG_NORMAL);
        if (user == null) {
            logger.debug("查询用户信息失败，用户jobNumber为" + jobNumber);
            return null;
        }
        UserBo userBo = BeanMapper.map(user, UserBo.class);
        userBo = this.userBoInit(userBo);
        return userBo;
    }

    /**
     * 根据登录名获取账号 不区分是否删除
     *
     * @param jobNumber jobNumber
     * @return User
     */
    @Override
    public List<UserBo> getAllUserByJobNumber(String jobNumber) {
        if (jobNumber == null) {
            logger.error("查询用户信息失败，用户jobNumber为空");
            return null;
        }
        List<UserPo> userPoList = userMapper.getAllUserByJobNumber(jobNumber);
        if (CollectionUtils.isEmpty(userPoList)) {
            logger.debug("查询用户信息失败，用户jobNumber为" + jobNumber);
            return null;
        }
        List<UserBo> userBoList = BeanMapper.mapList(userPoList, UserBo.class);
        List<UserBo> userNewBoList = new ArrayList();
        for (UserBo user : userBoList) {
            user = this.userBoInit(user);
            userNewBoList.add(user);
        }
        return userNewBoList;
    }

    /**
     * 根据id删除用户，修改状态
     *
     * @param id id
     */
    @Transactional
    @Override
    public Integer deleteUser(Long id) {
        return userMapper.deleteById(id, MenuConstants.DEL_FLAG_DELETE);
    }

    /**
     * 修改登录信息
     *
     * @param id id
     */
    @Transactional
    @Override
    public void updateUserLoginInfo(Long id) {
        if (id == null) {
            logger.error("修改用户登录信息失败，参数为空");
            return;
        }
        userMapper.updateLoginInfo(SecurityUtils.getSubject().getSession().getHost(), new Date(), id);
    }

    /**
     * 封装用户对象
     *
     * @param userBo userBo
     * @return UserBo
     */
    private UserBo userBoInit(UserBo userBo) {
        // 需求变更，不再封装用户的地域信息和部门信息。
        // 进一步封装AreaBo 和List<AreaBo> 对象
        /*Integer areaId = userBo.getAreaId();
        if (areaId == null) {
            logger.error("查询用户信息的地域信息失败，用户id为" + userBo.getId());
        } else {
            AreaBo areaBo = areaService.findOne(areaId.longValue());
            if (areaBo == null) {
                logger.debug("查询用户信息的地域信息失败，数据为null,用户id为" + userBo.getId());
            } else {
                userBo.setAreaBo(areaBo);
            }
            List<AreaBo> areaBoList = areaService.findAllChild(areaId.longValue());
            if (CollectionUtils.isEmpty(areaBoList)) {
                logger.debug("查询用户信息的地域信息失败，数据为null,用户id为" + userBo.getId());
            } else {
                userBo.setAreaBoList(areaBoList);
            }
        }*/
        // 封装BusinessBo List<BusinessBo>对象
        /*Integer businessId = userBo.getBusinessId();
        if (businessId == null) {
            logger.error("查询用户信息的业务板块信息失败，用户id为" + userBo.getId());
        } else {
            BusinessBo businessBo = businessService.findOne(businessId.longValue());
            if (businessBo == null) {
                logger.debug("查询用户信息的业务板块信息失败，数据为null,用户id为" + userBo.getId());
            } else {
                userBo.setBusinessBo(businessBo);
            }
            List<BusinessBo> businessBoList = businessService.findAllChild(businessId.longValue());
            if (CollectionUtils.isEmpty(businessBoList)) {
                logger.debug("查询用户信息的业务板块信息失败，数据为null,用户id为" + userBo.getId());
            } else {
                userBo.setBusinessBoList(businessBoList);
            }
        }*/
        // 封装用户部门对象
        Integer deptId = userBo.getDeptId();
        if (deptId == null) {
            logger.error("查询用户信息的部门信息失败，用户id为" + userBo.getId());
        } else {
            DeptBo deptBo = deptService.findOne(deptId.longValue());
            if (deptBo == null) {
                logger.debug("查询用户信息的部门信息失败， 数据为null,用户id为" + userBo.getId());
            } else {
                userBo.setDeptBo(deptBo);
            }
        }
        // 封装用户子部门对象
        Integer deptChildrenId = userBo.getDeptChildrenId();
        if (deptChildrenId == null) {
            logger.error("查询用户信息的子部门信息失败，用户id为" + userBo.getId());
        } else {
            DeptBo deptChildrenBo = deptService.findOne(deptChildrenId.longValue());
            if (deptChildrenBo == null) {
                logger.debug("查询用户信息的子部门信息失败， 数据为null,用户id为" + userBo.getId());
            } else {
                userBo.setDeptChildrenBo(deptChildrenBo);
            }
        }
        return userBo;
    }


    /**
     * 存储用户底层，外层赋值，包括所属区域，所在业务板块，
     * 用户跟角色关联表，在外层创建，事物操作
     *
     * @param userBean userBean
     */
    @Transactional
    @Override
    public boolean saveUser(UserBean userBean) {
        if (userBean == null) {
            throw new NullPointerException("createUserBean failed，UserBean is null");
        }
        userMapper.create(BeanMapper.map(userBean, UserPo.class));
        systemRealm.clearCachedAuthorizationInfo(userBean.getJobNumber());
        return true;
    }

    /**
     * 根据id修改密码
     *
     * @param id          id
     * @param jobNumber   jobNumber
     * @param newPassword newPassword
     */
    @Transactional
    @Override
    public void updatePasswordById(Long id, String jobNumber, String newPassword) {
        if (jobNumber == null || id == null || newPassword == null) {
            logger.error("修改用户密码失败，参数为空");
            return;
        }
        userMapper.updatePasswordById(Password.entryptPassword(newPassword), id);
        systemRealm.clearCachedAuthorizationInfo(jobNumber);
    }

    /**
     * 根据id修改密码 修改权限
     *
     * @param id          id
     * @param jobNumber   jobNumber
     * @param newPassword newPassword
     */
    @Transactional
    @Override
    public void updateUser(Long id, String jobNumber, String newPassword, List<Integer> roleList, String area) {
        if (id == null || StringUtils.isEmpty(jobNumber)) {
            logger.error("修改用户密码失败，参数为空");
            return;
        }

        if (StringUtils.isNotEmpty(newPassword)) {
            // 修改密码
            updatePasswordById(id, jobNumber, newPassword);
        }
        if (StringUtils.isNotEmpty(area) && !area.equals("-1")) {
            // 修改用户基本信息
            userMapper.updateArea(area, id);
        }
        // 删除用户原有关联角色，重新关联
        userRoleMapper.deleteByUserId(id);
        if (CollectionUtils.isNotEmpty(roleList)) {
            for (Integer i : roleList) {
                userRoleMapper.create(i, id);
            }
        }
    }


    @Override
    public List<UserBo> findUserBySearch(String jobNumber, Integer start, Integer pageSize) {
        if (pageSize == null || start == null) {
            logger.error("查询用户信息失败，用户id为空");
            return null;
        }
        List<UserPo> userPoList = userMapper.findUserBySearch(jobNumber, pageSize, start, MenuConstants.DEL_FLAG_NORMAL);
        if (CollectionUtils.isEmpty(userPoList)) {
            logger.error("查询用户信息失败，搜索列表为空");
            return new LinkedList<>();
        }
        List<UserBo> userBoList = BeanMapper.mapList(userPoList, UserBo.class);
        List<UserBo> newBoList = new ArrayList<>();
        for (UserBo userBo : userBoList) {
            userBo = this.userBoInit(userBo);
            newBoList.add(userBo);
        }
        return newBoList;
    }

    @Override
    public Integer countBySearch(String jobNumber, Integer start, Integer pageSize) {
        if (pageSize == null || start == null) {
            logger.error("查询用户信息失败，用户id为空");
            return null;
        }
        return userMapper.countBySearch(jobNumber, pageSize, start, MenuConstants.DEL_FLAG_NORMAL);
    }
}
