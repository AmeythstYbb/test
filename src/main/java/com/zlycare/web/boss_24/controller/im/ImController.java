package com.zlycare.web.boss_24.controller.im;

import com.zlycare.web.boss_24.constants.ProjectConfig;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.utils.common.utils.MD5Util;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * im 聊天界面,嵌入外部页面
 */
@Controller
@RequestMapping("/im")
public class ImController {

    private static final Logger logger = LoggerFactory.getLogger(ImController.class);

    //private static final String IM_HREF = "http://192.168.252.10:3000/im/page/index";

    @Autowired
    private UserService userService;
    @Autowired
    ProjectConfig projectConfig;

    @RequiresUser
    @RequiresPermissions("sys:im:view")
    @RequestMapping("/view")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/im/imView");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (com.zlycare.web.boss_24.utils.other.StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }

        modelAndView.addObject("phone", userBo.getMobile());
        modelAndView.addObject("href", projectConfig.getImUrl());
        modelAndView.addObject("auth", passwdMd5(userBo.getMobile()));
        return modelAndView;
    }

    /*
    1、手机号md5加密，转为大写
    2、第一次加密结果截取(9,16)位做盐值
    3、手机号+盐值再次md5加密，并转大写。
    */

    /**
     * 密码加密
     *
     * @param password password
     * @return String
     */
    private static String passwdMd5(String password) {
        if (StringUtils.isEmpty(password)) {
            return "";
        }
        String tempPassword = MD5Util.md5(password).toUpperCase();
        //System.out.println(tempPassword);
        return MD5Util.md5(password + tempPassword.substring(9, 16)).toUpperCase();
    }

    /*public static void main(String[] args) {
        System.out.println(passwdMd5("15210321234"));
    }*/
}
