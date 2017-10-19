package com.zlycare.web.boss_24.controller.user;

import com.zlycare.web.boss_24.constants.user.DictConstants;
import com.zlycare.web.boss_24.controller.vo.*;
import com.zlycare.web.boss_24.core.service.bo.*;
import com.zlycare.web.boss_24.core.service.sys.AreaService;
import com.zlycare.web.boss_24.core.service.sys.MenuService;
import com.zlycare.web.boss_24.core.service.sys.RoleService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.core.Page;
import com.zlycare.web.boss_24.utils.core.PageUtil;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import com.zlycare.web.boss_24.utils.user.Collections3;
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

import java.util.Collections;
import java.util.List;

/**
 * 首页信息
 *
 * @author xuchongya created date 2016/7/25 10:22
 */
@Controller
@RequestMapping("/user")//设置外部请求访问调用UsersController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);//设置固定名称，打日志

    @Autowired
    private UserService userService;//声明接口userService
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AreaService areaService;

    /**
     * 获取用户拥有的所有用户列表
     *
     * @return ModelAndView
     */
    //验证用户是否被记忆
    @RequiresUser
    @RequiresPermissions("sys:user:view")
    @RequestMapping("/list/get")
    public ModelAndView getMenuList(
            @RequestParam(value = "number", required = false) String jobNumber,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "numPerPage", required = false) Integer numPerPage
    ) {
        ModelAndView modelAndView = new ModelAndView("/sys/userList");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        // 查询出所有用户,不以当前用户的地域、角色区分
        // 查询出所有用户,不以当前用户的地域、角色区分
        Integer pageSize = numPerPage != null ? numPerPage : 20;
        Integer start = pageNum != null ? (pageNum - 1) * pageSize : 0;

        List<UserBo> userBoList = userService.findUserBySearch(jobNumber, start, pageSize);
        int count = userService.countBySearch(jobNumber, start, pageSize);
        List<UserVo> userVoList = BeanMapper.mapList(userBoList, UserVo.class);

        for (UserVo userVo : userVoList) {
            // 用户拥有的角色信息
            List<RoleBo> userRoleBoList = roleService.findByUserId(userVo.getId());
            userVo.setRoleNames(Collections3.extractToString(userRoleBoList, "name", ", "));
        }

        Page page;
        if (CollectionUtils.isEmpty(userBoList)) {
            page = PageUtil.initPage(start, pageSize, 0, Collections.EMPTY_LIST);
        } else {
            page = PageUtil.initPage(start, pageSize, count, userVoList);
        }

        modelAndView.addObject("page", page);
        modelAndView.addObject("statusMap", DictConstants.statusMap);

        return modelAndView;
    }

    @RequiresUser
    @RequestMapping("/search")
    @RequiresPermissions("sys:user:view")
    @ResponseBody
    public ModelAndView searchUser(
            @RequestParam(value = "number", required = false) String jobNumber,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "numPerPage", required = false) Integer numPerPage
    ) {
        ModelAndView modelAndView = new ModelAndView("/sys/userList");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }

        // 查询出所有用户,不以当前用户的地域、角色区分
        Integer pageSize = numPerPage != null ? numPerPage : 20;
        Integer start = pageNum != null ? (pageNum - 1) * pageSize : 0;

        List<UserBo> userBoList = userService.findUserBySearch(jobNumber, start, pageSize);
        int count = userService.countBySearch(jobNumber, start, pageSize);

        List<UserVo> userVoList = BeanMapper.mapList(userBoList, UserVo.class);
        for (UserVo userVo : userVoList) {
            // 用户拥有的角色信息
            List<RoleBo> userRoleBoList = roleService.findByUserId(userVo.getId());
            userVo.setRoleNames(Collections3.extractToString(userRoleBoList, "name", ", "));
        }

        Page page;
        if (CollectionUtils.isEmpty(userBoList)) {
            page = PageUtil.initPage(start, pageSize, 0, Collections.EMPTY_LIST);
        } else {
            page = PageUtil.initPage(start, pageSize, count, userVoList);
        }
        //page.setPageNo(pageNum);
        modelAndView.addObject("page", page);
        modelAndView.addObject("searchParam", jobNumber);
        modelAndView.addObject("statusMap", DictConstants.statusMap);

        return modelAndView;
    }

    /**
     * 返回修改用户页面
     * 修改密码并修改权限
     */
    @RequiresUser
    @RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "/update/view")
    @ResponseBody
    public ModelAndView updateUserView(@RequestParam("id") Long id) {
        if (id == null) {
            logger.error("参数 null");
            return new ModelAndView("/sys/userUpdate");
        }
        ModelAndView modelAndView = new ModelAndView("/sys/userUpdate");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        // 修改用户信息
        UserBo userBo = userService.getUser(id);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }

        // 当前用户信息
        UserBo userNowBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }

        // 当前用户能看到的所有角色信息
        //List<RoleBo> roleBoList = roleService.findAllRole(userNowBo.getId());
        // 此方法只有进入到用户管理界面-修改用户才可调用，说明用户拥有用户管理的权限，不能只返回当前用户关联的角色，而是所有角色。
        List<RoleBo> roleBoList = roleService.findAllRole(1L);

        // 修改用户拥有的角色信息
        List<RoleBo> userRoleBoList = roleService.findByUserId(userBo.getId());

        // 返回用户信息、用户拥有的角色信息、所有角色信息
        modelAndView.addObject("userBo", BeanMapper.map(userBo, UserVo.class));
        modelAndView.addObject("roleBoList", BeanMapper.mapList(roleBoList, RoleVo.class));
        modelAndView.addObject("userRoleBoList", BeanMapper.mapList(userRoleBoList, RoleVo.class));
        modelAndView.addObject("userRole", Collections3.extractToString(BeanMapper.mapList(userRoleBoList, RoleVo.class), "id", ","));
        modelAndView.addObject("dimission", userBo.getStatus());

        // 返回所有业务区域列表,用户含有的业务员区域在自己的属性字段中，以逗号分隔
        List<AreaBo> areaBoList = areaService.findAll();
        modelAndView.addObject("areaBoList", BeanMapper.mapList(areaBoList, AreaVo.class));
        return modelAndView;
    }


    /**
     * 修改用户
     */
    @RequiresUser
    @RequiresPermissions("sys:user:edit")
    @RequestMapping(value = "/update")
    @ResponseBody
    public DwzVo updateMenu(
            @RequestParam("id") Long id,
            @RequestParam("jobNumber") String jobNumber,
            @RequestParam("password") String password,
            @RequestParam("repassword") String repassword,
            @RequestParam(value = "roleList", required = false) List<Integer> roleList,
            @RequestParam(value = "areaId", required = false) String area) {
        if (id == null || StringUtils.isEmpty(jobNumber)) {
            logger.error("插入用户参数null");
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
        if ((StringUtils.isEmpty(repassword) && StringUtils.isNotEmpty(password)) ||
                StringUtils.isNotEmpty(repassword) && StringUtils.isEmpty(password)) {
            return new DwzVo("300", "操作失败");
        }
        if (StringUtils.isNotEmpty(repassword) && StringUtils.isNotEmpty(password)) {
            if (!repassword.equals(password)) {
                return new DwzVo("300", "密码不一致");
            }
        }
        userService.updateUser(id, jobNumber, password, roleList, area);

        return new DwzVo("user");
    }
}
