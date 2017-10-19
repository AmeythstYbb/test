package com.zlycare.web.boss_24.controller.product;

import com.zlycare.web.boss_24.controller.bean.RedPaperBean;
import com.zlycare.web.boss_24.controller.vo.DwzVo;
import com.zlycare.web.boss_24.controller.vo.RedPaperConfigVo;
import com.zlycare.web.boss_24.core.service.bo.RedPaperConfigBo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.config.RedPaperService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author : linguodong
 * Create : 2017/8/29
 * Update : 2017/8/29
 * Descriptions : 红包设置
 */
@Controller
@RequestMapping("/redPaper/")
public class RedPaperController {
    private static final Logger logger = LoggerFactory.getLogger(RedPaperController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RedPaperService redPaperService;

    // 红包管理 /redPaper/get redPaper_manage redPaper:manage:all

    /**
     * 红包页面数据展示
     *
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("redPaper:manage:all")
    @RequestMapping("/get")
    @ResponseBody
    public ModelAndView getRedPaper() {
        ModelAndView modelAndView = new ModelAndView("/redPaper/view");
        return initModelAndView(modelAndView);
    }


    /**
     * 返回修改红包信息页面
     */
    @RequiresUser
    @RequiresPermissions("redPaper:manage:all")
    @RequestMapping(value = "/update/view")
    @ResponseBody
    public ModelAndView updateRedPaperView() {
        ModelAndView modelAndView = new ModelAndView("/redPaper/update");
        return initModelAndView(modelAndView);
    }


    /**
     * 封装 ModelAndView
     *
     * @param modelAndView modelAndView
     * @return ModelAndView
     */
    private ModelAndView initModelAndView(ModelAndView modelAndView) {
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        RedPaperConfigBo redPaperConfigBo = redPaperService.getRedPaper();
        if (redPaperConfigBo == null || redPaperConfigBo.getField() == null) {
            logger.error("红包 null");
            return modelAndView;
        }
        modelAndView.addObject("redPaper", BeanMapper.map(redPaperConfigBo, RedPaperConfigVo.class));
        return modelAndView;
    }

    /**
     * 修改红包信息
     */
    @RequiresUser
    @RequiresPermissions("redPaper:manage:all")
    @RequestMapping(value = "/update")
    @ResponseBody
    public DwzVo updateRedPaper(RedPaperBean redPaperBean) {
        if (redPaperBean == null) {
            logger.error("修改红包信息失败，参数null");
            return new DwzVo("300", "操作失败");
        }
        String userJobNumber = UserUtils.getUserJobNumber();
        if (com.zlycare.web.boss_24.utils.other.StringUtils.isEmpty(userJobNumber)) {
            return new DwzVo("300", "操作失败");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new DwzVo("300", "操作失败");
        }

        redPaperService.update(BeanMapper.map(redPaperBean, com.zlycare.web.boss_24.core.service.bean.RedPaperBean.class));
        return new DwzVo("200", "操作成功", "", "", "forward", "/redPaper/get");
        //return new DwzVo("200", "操作成功", "", "redPaper_manage", "forward", "/redPaper/get");
    }
}
