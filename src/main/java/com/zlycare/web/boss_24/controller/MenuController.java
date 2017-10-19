package com.zlycare.web.boss_24.controller;

import com.google.common.collect.Lists;
import com.zlycare.web.boss_24.constants.sys.MenuConstants;
import com.zlycare.web.boss_24.controller.bean.MenuBean;
import com.zlycare.web.boss_24.controller.vo.DwzVo;
import com.zlycare.web.boss_24.controller.vo.JsonVo;
import com.zlycare.web.boss_24.controller.vo.MenuVo;
import com.zlycare.web.boss_24.core.service.bo.MenuBo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.sys.MenuService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页信息
 *
 * @author xuchongya created date 2016/7/25 10:22
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    /**
     * (废)
     * 获取一级菜单
     *
     * @return ModelAndView
     */
    //验证用户是否被记忆
    @RequiresUser
    @RequiresPermissions("sys:menu:view")
    @RequestMapping("/tree/root")
    public ModelAndView getRootTree() {
        ModelAndView modelAndView = new ModelAndView("/sys/menuRoot");
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
        List<MenuBo> menuBoList = menuService.findRootMenu(userBo.getId());
        modelAndView.addObject("menuBoList", BeanMapper.mapList(menuBoList, MenuVo.class));

        return modelAndView;
    }

    /**
     * 获取一级菜单下所有菜单
     *
     * @return ModelAndView
     */
    //验证用户是否被记忆
    @RequiresUser
    @RequiresPermissions("sys:menu:view")
    @RequestMapping("/tree")
    public ModelAndView getTree(@RequestParam("rootId") Integer rootId) {
        if (rootId == null) {
            logger.error("参数为空");
            return new ModelAndView("redirect:/user/login");
        }
        ModelAndView modelAndView = new ModelAndView("/sys/menu");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        List<MenuBo> menuBoList = menuService.findByUserIdAndRootId(userBo.getId(), rootId.longValue());
        modelAndView.addObject("menuBoList", BeanMapper.mapList(menuBoList, MenuVo.class));
        modelAndView.addObject("rootId", rootId);

        return modelAndView;
    }


    /**
     * 获取用户拥有的所有菜单列表
     *
     * @return ModelAndView
     */
    //验证用户是否被记忆
    @RequiresUser
    @RequiresPermissions("sys:menu:view")
    @RequestMapping("/list/get")
    public ModelAndView getMenuList() {
        ModelAndView modelAndView = new ModelAndView("/sys/menuList");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        List<MenuBo> list = Lists.newArrayList();
        List<MenuBo> menuBoList = menuService.findAllMenu(userBo.getId());
        List<MenuBo> newList = sortList(list, menuBoList, 1);
        modelAndView.addObject("menuBoList", BeanMapper.mapList(newList, MenuVo.class));
        return modelAndView;
    }


    /**
     * 获取用户拥有的所有菜单列表
     *
     * @return ModelAndView
     */
    //验证用户是否被记忆
    @RequiresUser
    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "/tree/get")
    @ResponseBody
    public ModelAndView getMenuListView() {
        // 参数加当前ID
        ModelAndView modelAndView = new ModelAndView("/sys/menuTree");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }

        List<MenuBo> list = Lists.newArrayList();
        List<MenuBo> menuBoList = menuService.findAllMenu(userBo.getId());
        List<MenuBo> newList = sortList(list, menuBoList, 1);
        modelAndView.addObject("menuBoList", BeanMapper.mapList(newList, MenuVo.class));

        List<JsonVo> jsonVoList = new ArrayList<>();
        for (MenuBo menuBo : menuBoList) {
            JsonVo jsonVo = new JsonVo();
            jsonVo.setName(((menuBo.getParentId() != null && menuBo.getParentId() != 0) ? menuBo.getParentId() : "-1") + "_" + menuBo.getId());
            //jsonVo.setValue("text: " + menuBo.getName());
            jsonVo.setValue("text: " + StringEscapeUtils.escapeEcmaScript(menuBo.getName()));
            jsonVoList.add(jsonVo);
        }
        modelAndView.addObject("data", jsonVoList);

        modelAndView.addObject("extId", ""); // 排除的编号ID
        modelAndView.addObject("parentIds", "");// 父编号层次，用于默认选中节点
        modelAndView.addObject("checked", ""); // 是否可复选
        modelAndView.addObject("checkedIds", ""); // 如果是可复选，则指定默认选中的Id
        modelAndView.addObject("module", "");    // 过滤栏目模型（仅针对CMS的Category树）
        return modelAndView;
    }


    /**
     * 获取用户拥有的所有菜单列表
     *
     * @return ModelAndView
     */
    //验证用户是否被记忆
    @RequiresUser
    @RequiresPermissions("sys:menu:view")
    @RequestMapping(value = "/tree/copy/get")
    @ResponseBody
    public ModelAndView getMenuListCopyView(@RequestParam("id") Integer id) {
        // copy 为了多接收一个参数，返回parentIds，打开菜单树形图时选中现在的父级菜单对象，but失败，仍选最高级
        // 参数加当前ID
        ModelAndView modelAndView = new ModelAndView("/sys/menuTree");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }

        List<MenuBo> list = Lists.newArrayList();
        List<MenuBo> menuBoList = menuService.findAllMenu(userBo.getId());
        List<MenuBo> newList = sortList(list, menuBoList, 1);
        modelAndView.addObject("menuBoList", BeanMapper.mapList(newList, MenuVo.class));

        List<JsonVo> jsonVos = new ArrayList<>();
        for (MenuBo m : menuBoList) {
            JsonVo jsonVo = new JsonVo();
            jsonVo.setName(((m.getParentId() != null && m.getParentId() != 0) ? m.getParentId() : "-1") + "_" + m.getId());
            //jsonVo.setValue("text: " + m.getName());
            //logger.info("");
            jsonVo.setValue("text: " + StringEscapeUtils.escapeEcmaScript(m.getName()));
            jsonVos.add(jsonVo);
        }
        modelAndView.addObject("data", jsonVos);

        MenuBo menuBo = menuService.getMenu(id.longValue());
        if (menuBo == null || StringUtils.isEmpty(menuBo.getParentIds())) {
            logger.error("对象null");
            return new ModelAndView("/sys/menuTree");
        }

        modelAndView.addObject("extId", ""); // 排除的编号ID
        modelAndView.addObject("parentIds", menuBo.getParentIds().replaceFirst("0", "-1").replaceAll(",", "_"));// 父编号层次，用于默认选中节点
        modelAndView.addObject("checked", ""); // 是否可复选
        modelAndView.addObject("checkedIds", ""); // 如果是可复选，则指定默认选中的Id
        modelAndView.addObject("module", "");    // 过滤栏目模型（仅针对CMS的Category树）
        return modelAndView;
    }

    /**
     * 返回插入菜单页面
     */
    @RequiresUser
    @RequiresPermissions("sys:menu:insert")
    @RequestMapping(value = "/insert/view")
    @ResponseBody
    public ModelAndView insertMenuView() {
        ModelAndView modelAndView = new ModelAndView("/sys/menuInsert");
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
        modelAndView.addObject("menuBoList", BeanMapper.mapList(newList, MenuVo.class));
        return new ModelAndView("/sys/menuInsert");
    }


    /**
     * 插入菜单
     */
    @RequiresUser
    @RequiresPermissions("sys:menu:insert")
    @RequestMapping(value = "/insert")
    @ResponseBody
    public DwzVo insertMenu(MenuBean menuBean) {
        if (menuBean == null) {
            logger.error("插入菜单参数null");
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

        com.zlycare.web.boss_24.core.service.bean.MenuBean menuServiceBean = new com.zlycare.web.boss_24.core.service.bean.MenuBean();
        menuServiceBean.setParentId(menuBean.getParent_name_hidden());
        menuServiceBean.setName(menuBean.getName());
        menuServiceBean.setHref(menuBean.getHref());
        menuServiceBean.setTarget(menuBean.getTarget());
        menuServiceBean.setIcon(menuBean.getIcon());
        menuServiceBean.setSort(menuBean.getSort());
        menuServiceBean.setIsShow(menuBean.getShow());
        menuServiceBean.setPermission(menuBean.getPermission());
        menuServiceBean.setDelFlag(MenuConstants.DEL_FLAG_NORMAL);
        menuService.createMenu(menuServiceBean);

        return new DwzVo("menu");
    }

    /**
     * 删除
     */
    @RequiresUser
    @RequiresPermissions("sys:menu:edit")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public DwzVo deleteMenu(@RequestParam("id") Long id) {
        if (id == null) {
            logger.error("删除菜单参数null");
            return new DwzVo("300", "操作失败");
        }
        if (id == 1) {
            logger.error("删除菜单参数异常");
            return new DwzVo("300", "删除菜单失败, 不允许删除顶级菜单");
        }
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new DwzVo("300", "操作失败");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new DwzVo("300", "操作失败");
        }
        // delete 只是update的好处，不用删除关联数据，但是会造成关联数据中大量无效关联。
        // 不用担心新插入的menu和关联表重复的情形，id是自增的不会重复。数据未删除仍占有原有id
        menuService.deleteMenu(id);

        return new DwzVo("200", "操作成功", "menu", "", "", "/menu/list/get");
    }


    /**
     * 返回修改菜单页面
     */
    @RequiresUser
    @RequiresPermissions("sys:menu:edit")
    @RequestMapping(value = "/update/view")
    @ResponseBody
    public ModelAndView updateMenuView(@RequestParam("id") Long id) {
        if (id == null) {
            logger.error("参数 null");
            return new ModelAndView("/sys/menuUpdate");
        }
        ModelAndView modelAndView = new ModelAndView("/sys/menuUpdate");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        MenuBo menuBo = menuService.getMenu(id);
        if (menuBo == null || menuBo.getParentId() == null) {
            logger.error("参数 null");
            return new ModelAndView("/sys/menuUpdate");
        }
        MenuBo menuParentBo = menuService.getMenu(menuBo.getParentId().longValue());
        if (menuParentBo == null) {
            logger.error("参数 null");
            return new ModelAndView("/sys/menuUpdate");
        }
        modelAndView.addObject("menuBo", BeanMapper.map(menuBo, MenuVo.class));
        modelAndView.addObject("menuParentBo", BeanMapper.map(menuParentBo, MenuVo.class));
        return modelAndView;
    }

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
//                for (MenuBo m : list) {
//                    System.out.print(m.getId() + " ");
//                }
                // 判断是否还有子节点, 有则继续获取子节点
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


    /**
     * 插入菜单
     */
    @RequiresUser
    @RequiresPermissions("sys:menu:edit")
    @RequestMapping(value = "/update")
    @ResponseBody
    public DwzVo updateMenu(MenuBean menuBean) {
        if (menuBean == null || menuBean.getId_name_hidden() == null) {
            logger.error("插入菜单参数null");
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

        com.zlycare.web.boss_24.core.service.bean.MenuBean menuServiceBean = new com.zlycare.web.boss_24.core.service.bean.MenuBean();
        menuServiceBean.setId(menuBean.getId_name_hidden().intValue());
        menuServiceBean.setParentId(menuBean.getParent_name_hidden());
        menuServiceBean.setName(menuBean.getName());
        menuServiceBean.setHref(menuBean.getHref());
        menuServiceBean.setTarget(menuBean.getTarget());
        menuServiceBean.setIcon(menuBean.getIcon());
        menuServiceBean.setSort(menuBean.getSort());
        menuServiceBean.setIsShow(menuBean.getShow());
        menuServiceBean.setPermission(menuBean.getPermission());

        menuService.saveUser(menuServiceBean);

        return new DwzVo("menu");
    }
}
