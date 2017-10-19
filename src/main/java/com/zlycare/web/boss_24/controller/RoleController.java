package com.zlycare.web.boss_24.controller;

import com.google.common.collect.Lists;
import com.zlycare.web.boss_24.controller.vo.DwzVo;
import com.zlycare.web.boss_24.controller.vo.MenuVo;
import com.zlycare.web.boss_24.controller.vo.RoleVo;
import com.zlycare.web.boss_24.core.service.bo.MenuBo;
import com.zlycare.web.boss_24.core.service.bo.RoleBo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.sys.MenuService;
import com.zlycare.web.boss_24.core.service.sys.RoleService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 首页信息
 *
 * @author xuchongya created date 2016/7/25 10:22
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    /**
     * 获取当前用户能看到的所有权限
     *
     * @return
     */
    //验证用户是否被记忆
    @RequiresUser
    @RequiresPermissions("sys:role:view")
    @RequestMapping("/list/get")
    public ModelAndView getRoleList() {
        ModelAndView modelAndView = new ModelAndView("/sys/roleList");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        /*返回头部菜单导航条菜单信息*/
        /*数据库存一条顶级菜单信息，所有一级菜单(导航栏)将其作为父级菜单*/
        //List<RoleBo> roleBoList = roleService.findAllRole(userBo.getId());
        // 此方法只有进入到角色管理界面才可调用，说明用户拥有角色管理的权限，不能只返回当前用户关联的角色，而是所有角色。
        List<RoleBo> roleBoList = roleService.findAllRole(1L);
        modelAndView.addObject("roleList", BeanMapper.mapList(roleBoList, RoleVo.class));

        return modelAndView;
    }

    /**
     * 返回插入角色页面
     */
    @RequiresUser
    @RequiresPermissions("sys:role:insert")
    @RequestMapping(value = "/insert/view")
    @ResponseBody
    public ModelAndView insertRoleView() {
        ModelAndView modelAndView = new ModelAndView("/sys/roleInsert");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        /*返回menu集合*/
        List<MenuBo> list = Lists.newArrayList();
        List<MenuBo> menuBoList = menuService.findAllMenu(userBo.getId());
        List<MenuBo> newList = sortList(list, menuBoList, 1);
//        modelAndView.addObject("menuList", BeanMapper.mapList(newList, MenuVo.class));
        modelAndView.addObject("menuList", BeanMapper.mapList(menuBoList, MenuVo.class));
        return modelAndView;
    }

    /**
     * 插入角色
     */
    @RequiresUser
    @RequiresPermissions("sys:role:insert")
    @RequestMapping(value = "/insert")
    @ResponseBody
    public DwzVo insertRole(@RequestParam("name") String name, @RequestParam("menu_check") String menu_check) {

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(menu_check)) {
            logger.error("插入角色参数null");
            return new DwzVo("300", "操作失败");
        }
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new DwzVo("300", "操作失败");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new DwzVo("300", "操作失败");
        }

        // 验证是否存在重复权限
        RoleBo roleBo = roleService.findRoleByName(name);
        if (roleBo != null) {
            return new DwzVo("300", "角色名已存在");
        }

        //
        if (menu_check.equals("-1")) {
            //  权限归零或者未选择
        } else {
            // 权限插入或者修改
        }
        roleService.InsertRoleAndMenu(name, menu_check);

        return new DwzVo("role");
    }

    /**
     * 返回修改角色页面
     */
    @RequiresUser
    @RequiresPermissions("sys:role:edit")
    @RequestMapping(value = "/update/view")
    @ResponseBody
    public ModelAndView updateRoleView(@RequestParam("id") Long id) {
        if (id == null) {
            logger.error("参数 null");
            return new ModelAndView("/sys/roleUpdate");
        }
        ModelAndView modelAndView = new ModelAndView("/sys/roleUpdate");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        RoleBo roleBo = roleService.getRole(id);
        if (roleBo == null) {
            logger.error("参数 null");
            return new ModelAndView("/sys/roleUpdate");
        }
                /*返回menu集合*/
        List<MenuBo> list = Lists.newArrayList();
        List<MenuBo> menuBoList = menuService.findAllMenu(userBo.getId());
        List<MenuBo> newList = sortList(list, menuBoList, 1);
//        modelAndView.addObject("menuList", BeanMapper.mapList(newList, MenuVo.class));
        List<MenuVo> menuVoList = BeanMapper.mapList(menuBoList, MenuVo.class);
        for (MenuVo menuVo : menuVoList) {
            menuVo.setIdString(menuVo.getId().toString());//引号
        }
        modelAndView.addObject("menuList", menuVoList);

        // 返回角色拥有的菜单列表
        List<MenuBo> roleMenuBoList = menuService.findByRoleId(id);
        if (roleMenuBoList != null) {
            StringBuilder s = new StringBuilder();
            for (MenuBo menuBo : roleMenuBoList) {
                s.append(menuBo.getId());
                s.append(",");
            }
            modelAndView.addObject("select", s.toString());
        }

        modelAndView.addObject("roleBo", BeanMapper.map(roleBo, RoleVo.class));
        return modelAndView;
    }

    /**
     * 修改角色
     */
    @RequiresUser
    @RequiresPermissions("sys:role:edit")
    @RequestMapping(value = "/update")
    @ResponseBody
    public DwzVo insertRole(
            @RequestParam("name") String name,
            @RequestParam("menu_check") String menu_check,
            @RequestParam("old_name") String old_name,
            @RequestParam("roleId") Long roleId) {

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(menu_check) || StringUtils.isEmpty(old_name) || roleId == null) {
            logger.error("修改角色参数null");
            return new DwzVo("300", "操作失败");
        }
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new DwzVo("300", "操作失败");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new DwzVo("300", "操作失败");
        }

        // 验证是否存在重复权限
        RoleBo roleBo = roleService.findRoleByName(name);
        if (roleBo != null && (!name.equals(old_name))) {
            return new DwzVo("300", "角色名已存在");
        }

        //
        if (menu_check.equals("-1")) {
            //  权限归零或者未选择
        } else {
            // 权限插入或者修改
        }
        roleService.updateRoleAndMenu(name, menu_check, roleId);

        return new DwzVo("role");
    }


    /**
     * 删除
     */
    @RequiresUser
    @RequiresPermissions("sys:role:delete")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public DwzVo deleteMenu(@RequestParam("id") Long id) {
        if (id == null) {
            logger.error("删除角色参数null");
            return new DwzVo("300", "操作失败");
        }
        if (id == 1) {
            logger.error("删除角色参数异常");
            return new DwzVo("300", "删除角色失败, 不允许删除内部角色");
        }

        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new DwzVo("300", "操作失败");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null || userBo.getId() == null) {
            return new DwzVo("300", "操作失败");
        }

        List<RoleBo> roleBoList = roleService.findByUserId(userBo.getId());//当前用户含有权限,不能删除当前用户现有权限
        if (!CollectionUtils.isEmpty(roleBoList)) {
            for (RoleBo roleBo : roleBoList) {
                if (roleBo.getId().equals(id)) {
                    logger.error("删除角色参数异常");
                    return new DwzVo("300", "删除角色失败, 不能删除当前用户所在角色");
                }
            }
        }
        // delete 只是update的好处，不用删除关联数据，但是会造成关联数据中大量无效关联。
        // 不用担心新插入的menu和关联表重复的情形，id是自增的不会重复。数据未删除仍占有原有id
        roleService.deleteRole(id);

        return new DwzVo("200", "操作成功", "role", "", "", "/role/list/get");
    }


    // 插入并绑定菜单、列表、分页、修改、删除
    // 菜单
    // 用户
    // 其他页面绑定权限
    // 录入数据
    // 修改密码
    // im
    // session过期后登录跳转问题
    // 点击一级菜单问题
    // 合并数据库
    // 修改姓名的时候重置steamReal

    /**
     * 工具 封装list
     *
     * @param list       list
     * @param sourcelist sourcelist
     * @param parentId   parentId
     * @return List<MenuBo>
     */
    public static List<MenuBo> sortList(List<MenuBo> list, List<MenuBo> sourcelist, Integer parentId) {
        for (int i = 0; i < sourcelist.size(); i++) {
            MenuBo e = sourcelist.get(i);
            if (e.getParentId() != null
                    && e.getParentId().equals(parentId)) {
                list.add(e);
                for (int j = 0; j < sourcelist.size(); j++) {
                    MenuBo child = sourcelist.get(j);
                    if (child.getParentId() != null
                            && child.getParentId().longValue() == (e.getId())) {
                        list = sortList(list, sourcelist, e.getId().intValue());
                        break;
                    }
                }
            }

        }
        return list;
    }

}
