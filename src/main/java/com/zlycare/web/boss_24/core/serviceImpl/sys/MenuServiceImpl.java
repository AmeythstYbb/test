package com.zlycare.web.boss_24.core.serviceImpl.sys;

import com.alibaba.druid.util.StringUtils;
import com.zlycare.web.boss_24.beans.exception.ServiceException;
import com.zlycare.web.boss_24.constants.sys.MenuConstants;
import com.zlycare.web.boss_24.core.dao.mapper.MenuMapper;
import com.zlycare.web.boss_24.core.dao.po.MenuPo;
import com.zlycare.web.boss_24.core.service.bean.MenuBean;
import com.zlycare.web.boss_24.core.service.bo.MenuBo;
import com.zlycare.web.boss_24.core.service.sys.MenuService;
import com.zlycare.web.boss_24.security.SystemRealm;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/5/31
 * Update : 2017/5/31
 * Descriptions : 系统管理，安全相关实体的管理类,菜单
 */
@Service
public class MenuServiceImpl implements MenuService {
    private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private SystemRealm systemRealm;

    /**
     * 获取Menu
     *
     * @param id id
     * @return MenuPo
     */
    @Override
    public MenuBo getMenu(Long id) {
        if (id == null) {
            logger.error("查询菜单信息失败，id为空");
            return null;
        }
        MenuPo menuPo = menuMapper.findOne(id);
        if (menuPo == null) {
            logger.debug("查询菜单信息失败，id为" + id);
            return null;
        }
        MenuBo menuBo = BeanMapper.map(menuPo, MenuBo.class);
        // userBo = this.userBoInit(userBo);
        return menuBo;
    }

    /**
     * 获取用户的所有菜单
     *
     * @param userId userId
     * @return List<MenuBo>
     */
    @Override
    public List<MenuBo> findAllMenu(Long userId) {
        if (userId == null) {
            logger.error("查询菜单信息失败，id为空");
            return null;
        }
        List<MenuPo> menuPoList;
        if (userId == 1) {
            menuPoList = menuMapper.findAllList(MenuConstants.DEL_FLAG_NORMAL);
        } else {
            menuPoList = menuMapper.findByUserId(userId, MenuConstants.DEL_FLAG_NORMAL);
        }
        if (CollectionUtils.isEmpty(menuPoList)) {
            logger.debug("查询菜单信息失败，数据为null,用户id为" + userId);
            return Collections.EMPTY_LIST;
        }
        return BeanMapper.mapList(menuPoList, MenuBo.class);
    }

    /**
     * 获取角色的所有菜单
     *
     * @param roleId roleId
     * @return List<MenuBo>
     */
    @Override
    public List<MenuBo> findByRoleId(Long roleId) {
        if (roleId == null) {
            logger.error("查询菜单信息失败，id为空");
            return null;
        }
        List<MenuPo> menuPoList = menuMapper.findByRoleId(roleId, MenuConstants.DEL_FLAG_NORMAL);

        if (CollectionUtils.isEmpty(menuPoList)) {
            logger.debug("查询菜单信息失败，数据为null,roleId 为" + roleId);
            return Collections.EMPTY_LIST;
        }
        return BeanMapper.mapList(menuPoList, MenuBo.class);
    }

    /**
     * 获取用户的所有一级菜单
     *
     * @param userId userId
     * @return List<MenuBo>
     */
    @Override
    public List<MenuBo> findRootMenu(Long userId) {
        if (userId == null) {
            logger.error("查询菜单信息失败，id为空");
            return null;
        }
        List<MenuPo> menuPoList;
        if (userId == 1) {
            menuPoList = menuMapper.findAllRoot(MenuConstants.DEL_FLAG_NORMAL);
        } else {
            menuPoList = menuMapper.findRootByUserId(userId, MenuConstants.DEL_FLAG_NORMAL);
        }
        if (CollectionUtils.isEmpty(menuPoList)) {
            logger.debug("查询一级菜单信息失败，数据为null,用户id为" + userId);
            return Collections.EMPTY_LIST;
        }
        return BeanMapper.mapList(menuPoList, MenuBo.class);
    }

    /**
     * 根据ID获取用户的菜单列表，不包括当前id对于的menu
     *
     * @param userId userId
     * @return List<MenuBo>
     */
    @Override
    public List<MenuBo> findByUserIdAndRootId(Long userId, Long rootId) {
        if (userId == null || rootId == null) {
            logger.error("查询菜单信息失败，id为空");
            return null;
        }
        List<MenuPo> menuPoList;
        if (userId == 1) {
            menuPoList = menuMapper.findByRootId(rootId, MenuConstants.DEL_FLAG_NORMAL);
        } else {
            menuPoList = menuMapper.findByUserIdAndRootId(userId, rootId, MenuConstants.DEL_FLAG_NORMAL);
        }
        if (CollectionUtils.isEmpty(menuPoList)) {
            logger.debug("查询菜单信息失败，数据为null,用户id为" + userId);
            return Collections.EMPTY_LIST;
        }
        return BeanMapper.mapList(menuPoList, MenuBo.class);
    }

    /**
     * 存储Menu底层，外层赋值
     * 角色跟menu关联表，在外层创建，事物操作
     * 先创建2对象，再创建关联表
     *
     * @param menuBean menuBean
     * @return boolean
     */
    @Transactional
    @Override
    public boolean saveUser(MenuBean menuBean) {
        MenuBo menuBo = getMenu(menuBean.getId().longValue());
        if (menuBo == null || StringUtils.isEmpty(menuBo.getParentIds())) {
            logger.error("not fount menuBo");
            throw new ServiceException();
        }
        String oldParentIds = menuBo.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds

        menuBean.setDelFlag(MenuConstants.DEL_FLAG_NORMAL);
        if (menuBean.getSort() == null)
            menuBean.setSort(30);
        //修改当前bean的父节点集合
        MenuBo parentMenu = getMenu(menuBean.getParentId().longValue());
        if (parentMenu == null || StringUtils.isEmpty(parentMenu.getParentIds())) {
            logger.error("找不到父节点");
            throw new ServiceException();
        }
        menuBean.setParentIds(parentMenu.getParentIds() + parentMenu.getId() + ",");
        menuMapper.update(BeanMapper.map(menuBean, MenuPo.class));

        //修改当前bean的子节点的父节点和父节点集合
        List<MenuPo> list = menuMapper.findByParentIdsLike(menuBean.getId().longValue(), MenuConstants.DEL_FLAG_NORMAL);
        for (MenuPo e : list) {
            // 把A子节点们之前的parentsId中关于A的部分替换成新的，不涉及对A的id的替换
            e.setParentIds(e.getParentIds().replace(oldParentIds, menuBean.getParentIds()));
            menuMapper.update(e);
        }
        systemRealm.clearAllCachedAuthorizationInfo();
        return true;
    }

    /**
     * 存储Menu底层，外层赋值
     * 角色跟menu关联表，在外层创建，事物操作
     * 先创建2对象，再创建关联表
     *
     * @param menuBean menuBean
     * @return boolean
     */
    @Transactional
    @Override
    @ResponseBody
    public Integer createMenu(MenuBean menuBean) {
        menuBean.setDelFlag(MenuConstants.DEL_FLAG_NORMAL);
        if (menuBean.getSort() == null)
            menuBean.setSort(30);
        //修改当前bean的父节点集合
        MenuBo parentMenu = getMenu(menuBean.getParentId().longValue());
        if (parentMenu == null || StringUtils.isEmpty(parentMenu.getParentIds())) {
            logger.error("找不到父节点");
            throw new ServiceException();
        }
        menuBean.setParentIds(parentMenu.getParentIds() + parentMenu.getId() + ",");
        Integer result = menuMapper.create(BeanMapper.map(menuBean, MenuPo.class));

        //修改当前bean的子节点的父节点和父节点集合
        // 更新子节点 parentIds update时
//        String oldParentIds = menuBean.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
//        List<MenuBo> list = menuMapper.findByParentIdsLike(menuBean.getId(), MenuConstants.DEL_FLAG_NORMAL);
//        for (MenuBo e : list) {
//            e.setParentIds(e.getParentIds().replace(oldParentIds, menuBean.getParentIds()));// 把A子节点们之前的parentsId中关于A的部分替换成新的，不涉及对A的id的替换
//            menuMapper.update();
//        }
        systemRealm.clearAllCachedAuthorizationInfo();
        return result;
    }

    /**
     * 删除菜单
     *
     * @param id id
     * @return Integer
     */
    @Transactional
    @Override
    public Integer deleteMenu(Long id) {
        return menuMapper.deleteById(id, MenuConstants.DEL_FLAG_DELETE);
    }
}
