package com.zlycare.web.boss_24.controller.productManage;

import com.zlycare.web.boss_24.constants.productManage.VersionManageEnum;
import com.zlycare.web.boss_24.controller.bean.PageUtilBean;
import com.zlycare.web.boss_24.controller.bean.VersionBean;
import com.zlycare.web.boss_24.controller.vo.DwzVo;
import com.zlycare.web.boss_24.controller.vo.ProductCatalogVo;
import com.zlycare.web.boss_24.controller.vo.VersionVo;
import com.zlycare.web.boss_24.controller.vo.member.MemberVo;
import com.zlycare.web.boss_24.core.mongo.po.Version;
import com.zlycare.web.boss_24.core.service.bo.ProductCatalogBo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.bo.VersionBo;
import com.zlycare.web.boss_24.core.service.productManage.VersionManageService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.core.Page;
import com.zlycare.web.boss_24.utils.core.PageUtil;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;


/**
 * Author : linguodong
 * Create : 2017/8/31
 * Update : 2017/8/31
 * Descriptions : 版本管理
 */
@Controller
@RequestMapping("/version")
public class VersionManageController {
    private static final Logger logger = LoggerFactory.getLogger(VersionManageController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private VersionManageService versionManageService;

    // app标识
    public final static List<String> apps = new ArrayList<>();

    static {
        apps.add(VersionManageEnum.CUSTOMER_ANDROID_24.getValue());
        apps.add(VersionManageEnum.CUSTOMER_IOS_24.getValue());
    }

    /*版本管理 version:manage:all /version/manage/list/get version_manage*/

    /**
     * 获取版本列表
     *
     * @return ModelAndView
     */
    @RequiresUser//验证用户是否被记忆
    @RequiresPermissions("version:manage:all")
    @RequestMapping("/manage/list/get")
    @ResponseBody
    public ModelAndView getVersionList(PageUtilBean pageUtilBean) {
        ModelAndView modelAndView = new ModelAndView("/productManage/versionList");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        // 分页参数设置
        Page page;
        Integer pageSize = pageUtilBean.getNumPerPage() != null ? pageUtilBean.getNumPerPage() : 25;
        Integer start = pageUtilBean.getPageNum() != null ? (pageUtilBean.getPageNum() - 1) * pageSize : 0;

        List<VersionBo> versionList = versionManageService.getVersionList(pageSize, start);
        Integer count = versionManageService.countAllList();

        List<VersionVo> versionVoList = BeanMapper.mapList(versionList, VersionVo.class);
        if (org.springframework.util.CollectionUtils.isEmpty(versionVoList)) {
            page = PageUtil.initPage(start, pageSize, 0, Collections.EMPTY_LIST);
        } else {
            versionVoList.stream().forEach(versionVo ->
                    versionVo.setCreateTime(versionVo.getTime() != null ? new Date(versionVo.getTime().longValue()) : null));
            page = PageUtil.initPage(start, pageSize, count, versionVoList);
        }
        /*返回参数,展示数据&分页参数&搜索参数*/
        modelAndView.addObject("page", page);
        modelAndView.addObject("pageUtilBean", pageUtilBean);
        return modelAndView;
    }


    /**
     * 删除 版本
     */
    @RequiresUser
    @RequiresPermissions("version:manage:all")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public DwzVo deleteVersion(@RequestParam("id") String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("删除版本参数null");
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
        versionManageService.deleteVersion(id);
        return new DwzVo("200", "操作成功", "version_manage", "", "", "/version/manage/list/get");
    }


    /**
     * 返回版本修改、提交页面
     */
    @RequiresUser
    @RequiresPermissions("version:manage:all")
    @RequestMapping(value = "/update/view")
    @ResponseBody
    public ModelAndView updateVersionView(@RequestParam(value = "id", required = false) String id) {
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        ModelAndView modelAndView = new ModelAndView("/productManage/versionUpdate");
        modelAndView.addObject("apps", apps);
        if (id == null) {
            // 提交,直接返回页面
            return modelAndView;
        }
        // 查询对象
        VersionBo versionBo = versionManageService.getVersion(id);
        modelAndView.addObject("version", BeanMapper.map(versionBo, VersionVo.class));
        return modelAndView;
    }


    /**
     * 插入、修改版本信息
     */
    @RequiresUser
    @RequiresPermissions("version:manage:all")
    @RequestMapping(value = "/update")
    @ResponseBody
    public DwzVo updattCommit(VersionBean versionBean) {
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new DwzVo("300", "操作失败");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new DwzVo("300", "操作失败");
        }
        if (versionBean == null) {
            logger.error("修改、插入版本信息失败，参数null");
            return new DwzVo("300", "操作失败");
        }
        versionManageService.saveVersion(BeanMapper.map(versionBean,
                com.zlycare.web.boss_24.core.service.bean.VersionBean.class));
        return new DwzVo("version_manage");
    }
}
