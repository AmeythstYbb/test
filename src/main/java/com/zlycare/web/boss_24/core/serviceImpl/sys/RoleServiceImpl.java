package com.zlycare.web.boss_24.core.serviceImpl.sys;

import com.zlycare.web.boss_24.beans.exception.ServiceException;
import com.zlycare.web.boss_24.constants.sys.MenuConstants;
import com.zlycare.web.boss_24.core.dao.mapper.RoleMapper;
import com.zlycare.web.boss_24.core.dao.mapper.RoleMenuMapper;
import com.zlycare.web.boss_24.core.dao.mapper.UserRoleMapper;
import com.zlycare.web.boss_24.core.dao.po.RoleMenuPo;
import com.zlycare.web.boss_24.core.dao.po.RolePo;
import com.zlycare.web.boss_24.core.service.bean.RoleBean;
import com.zlycare.web.boss_24.core.service.bo.RoleBo;
import com.zlycare.web.boss_24.core.service.sys.RoleService;
import com.zlycare.web.boss_24.security.SystemRealm;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/5/31
 * Update : 2017/5/31
 * Descriptions : 系统管理，安全相关实体的管理类，角色
 */
@Service
public class RoleServiceImpl implements RoleService {
    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private SystemRealm systemRealm;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 获取角色
     *
     * @param id id
     * @return RoleBo
     */
    @Override
    public RoleBo getRole(Long id) {
        if (id == null) {
            logger.error("查询角色信息失败，id为空");
            return null;
        }
        RolePo rolePo = roleMapper.findOne(id);
        if (rolePo == null) {
            logger.debug("查询角色信息失败，用户id为" + id);
            return null;
        }
        RoleBo roleBo = BeanMapper.map(rolePo, RoleBo.class);
        // userBo = this.userBoInit(userBo);
        return roleBo;
    }

    // 其他service

    /**
     * 获取角色
     *
     * @param name name
     * @return RoleBo
     */
    @Override
    public RoleBo findRoleByName(String name) {
        if (com.alibaba.druid.util.StringUtils.isEmpty(name)) {
            logger.error("查询角色信息失败，name为空");
            return null;
        }
        RolePo rolePo = roleMapper.findByName(name, MenuConstants.DEL_FLAG_NORMAL);
        if (rolePo == null) {
            logger.debug("查询角色信息失败，name为" + name);
            return null;
        }
        RoleBo roleBo = BeanMapper.map(rolePo, RoleBo.class);
        // userBo = this.userBoInit(userBo);
        return roleBo;
    }

    /**
     * 获取用户的所有角色
     *
     * @param userId userId
     * @return List<RoleBo>
     */
    @Override
    public List<RoleBo> findAllRole(Long userId) {
        if (userId == null) {
            logger.error("查询角色信息失败，id为空");
            return null;
        }
        List<RolePo> rolePoList;
        if (userId == 1) {
            rolePoList = roleMapper.findAllList(MenuConstants.DEL_FLAG_NORMAL);
        } else {
            rolePoList = roleMapper.findByUserId(userId, MenuConstants.DEL_FLAG_NORMAL);
        }
        if (CollectionUtils.isEmpty(rolePoList)) {
            logger.debug("查询角色信息失败，数据为null,用户id为" + userId);
            return Collections.EMPTY_LIST;
        }
        return BeanMapper.mapList(rolePoList, RoleBo.class);
    }

    /**
     * 只在删除角色时查询用户关联的角色时使用
     *
     * @param userId userId
     * @return List<RoleBo>
     */
    @Override
    public List<RoleBo> findByUserId(Long userId) {
        if (userId == null) {
            logger.error("查询角色信息失败，id为空");
            return null;
        }
        List<RolePo> rolePoList = roleMapper.findByUserId(userId, MenuConstants.DEL_FLAG_NORMAL);

        if (CollectionUtils.isEmpty(rolePoList)) {
            logger.debug("查询角色信息失败，数据为null,用户id为" + userId);
            return Collections.EMPTY_LIST;
        }
        return BeanMapper.mapList(rolePoList, RoleBo.class);
    }

    /**
     * (废)
     * 存储角色底层，外层赋值
     * 角色跟menu关联表，在外层创建，事物操作
     *
     * @param roleBean roleBean
     * @return boolean
     */
    @Transactional
    @Override
    public Integer saveRole(RoleBean roleBean) {
//        if (roleBean == null) {
//            throw new NullPoint erException("createRoleBean failed，RoleBean is null");
//        }
//        Integer result = roleMapper.create(BeanMapper.map(roleBean, RolePo.class));
//        systemRealm.clearAllCachedAuthorizationInfo();
        return 0;
    }

    /**
     * 创建权限、权限和menu关联表
     *
     * @param name       name
     * @param menu_check menu_check
     * @return boolean
     */
    @Transactional
    @Override
    public boolean InsertRoleAndMenu(String name, String menu_check) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(menu_check)) {
            logger.error("not fount param");
            throw new ServiceException();
        }
        RolePo rolePo = new RolePo();
        rolePo.setDelFlag(MenuConstants.DEL_FLAG_NORMAL);
        rolePo.setName(name);
        roleMapper.create(rolePo);
        Integer roleId = rolePo.getId().intValue();// 返回插入的ID
        // 为空则只插入role表即可
        if (StringUtils.isNotEmpty(menu_check) && !menu_check.equals("-1")) {
            String[] check = menu_check.split(",");
            List<String> checkList = Arrays.asList(check);
            for (String s : check) {
                Integer menuId = Integer.parseInt(s);
                RoleMenuPo roleMenuPo = roleMenuMapper.get(roleId, menuId);
                if (roleMenuPo == null) {
                    roleMenuMapper.create(roleId, menuId);
                }
            }
        }
        systemRealm.clearAllCachedAuthorizationInfo();
        return true;
    }

    /**
     * 修改权限、权限和menu关联表
     *
     * @param name       name
     * @param menu_check menu_check
     * @return boolean
     */
    @Transactional
    @Override
    public boolean updateRoleAndMenu(String name, String menu_check, Long roleId) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(menu_check) || roleId == null) {
            logger.error("not fount param");
            throw new ServiceException();
        }
        RolePo rolePo = new RolePo();
        rolePo.setId(roleId);
        rolePo.setName(name);
        roleMapper.update(rolePo);
        roleMenuMapper.deleteByRoleId(roleId);// 清除关联
        if (StringUtils.isNotEmpty(menu_check) && !menu_check.equals("-1")) {// 重新创建
            String[] check = menu_check.split(",");
            List<String> checkList = Arrays.asList(check);
            for (String s : check) {
                Integer menuId = Integer.parseInt(s);
                RoleMenuPo roleMenuPo = roleMenuMapper.get(roleId, menuId);
                if (roleMenuPo == null) {
                    roleMenuMapper.create(roleId, menuId);
                }
            }
        }
        systemRealm.clearAllCachedAuthorizationInfo();
        return true;
    }

    /**
     * 删除角色
     *
     * @param id id
     * @return Integer
     */
    @Transactional
    @Override
    public Integer deleteRole(Long id) {
        if (id == null) {
            logger.error("not fount param");
            throw new ServiceException();
        }
        // 删除角色的时候是所有用户解除角色后才可删除，还是可以直接删除角色，同时解除所有用户和此角色的关联关系。后者
        userRoleMapper.deleteByRoleId(id);  // TODO: 2017/6/9

        // 先删除关联表,再删除role表
        roleMenuMapper.deleteByRoleId(id);// 清除关联
        Integer result = roleMapper.deleteById(id, MenuConstants.DEL_FLAG_DELETE);
        systemRealm.clearAllCachedAuthorizationInfo();
        return result;
    }
}
