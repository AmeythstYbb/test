package com.zlycare.web.boss_24.controller;

import com.zlycare.web.boss_24.controller.vo.MenuVo;
import com.zlycare.web.boss_24.core.service.bo.MenuBo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.sys.MenuService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 首页信息
 *
 * @author xuchongya created date 2016/7/25 10:22
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;


    //验证用户是否被记忆
    @RequiresUser
    @RequestMapping("")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/index");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (com.zlycare.web.boss_24.utils.other.StringUtils.isEmpty(userJobNumber)) {
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
}


//    String userJobNumber = UserUtils.getUserJobNumber();
//if (StringUtils.isEmpty(userJobNumber)) {
//        return new ModelAndView("redirect:/user/login");
//        }
//        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
//        if (userBo == null) {
//        return new ModelAndView("redirect:/user/login");
//        }
//        modelAndView = new ModelAndView("/index");
//        /*返回头部菜单导航条菜单信息*/
//        /*数据库存一条顶级菜单信息，所有一级菜单(导航栏)将其作为父级菜单*/
//        List<MenuBo> menuBoList = menuService.findAllMenu(userBo.getId());
//        modelAndView.addObject("menuBoList", menuBoList);

//        UserBo userBo = UserUtils.getUser();
//        if (userBo == null || userBo.getId() == null) {
//            // modelAndView = new ModelAndView("redirect:/user/login");
//            modelAndView = new ModelAndView("/index");
//        } else {
//            StringBuilder sb = new StringBuilder("var data={};");
//            modelAndView = new ModelAndView("/index");
//            /*返回头部菜单导航条菜单信息*/
//            /*数据库存一条顶级菜单信息，所有一级菜单(导航栏)将其作为父级菜单*/
//            List<MenuBo> menuBoList = menuService.findAllMenu(userBo.getId());
//            modelAndView.addObject("menuBoList",menuBoList);


            /*第一个id是menuId，第二个id是父级id列表 字符串*/
//            for (int i=0; i<list.size(); i++){
//                Menu e = list.get(i);
//                if (extId == null || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
//                    sb.append("data['"+(e.getParent()!=null?e.getParent().getId():"-1")+"_"+e.getId()+"']='text: "+e.getName());
//                    if (StringUtils.isNotBlank(checkedIds)){
//                        sb.append("; checked: ").append(checkedIds.indexOf(","+e.getId()+",")==-1?"false":"true");
//                    }
//                    sb.append("';\n");
//                }
//            }
//        }