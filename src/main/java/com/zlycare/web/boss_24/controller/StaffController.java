package com.zlycare.web.boss_24.controller;

import com.zlycare.web.boss_24.constants.user.DictConstants;
import com.zlycare.web.boss_24.controller.bean.StaffBean;
import com.zlycare.web.boss_24.controller.vo.DeptVo;
import com.zlycare.web.boss_24.controller.vo.DwzVo;
import com.zlycare.web.boss_24.controller.vo.StaffVo;
import com.zlycare.web.boss_24.core.service.bo.DeptBo;
import com.zlycare.web.boss_24.core.service.bo.StaffBo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.sys.DeptService;
import com.zlycare.web.boss_24.core.service.sys.StaffService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.core.PageUtil;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : zhanglianwei
 * Create : 2017/6/5 16:01
 * Update : 2017/6/5 16:01
 * Descriptions :
 */
@Controller
@RequestMapping("/staff")
public class StaffController {

    private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    private StaffService staffService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;

    @RequiresPermissions("sys:staff:view")
    @RequiresUser
    @RequestMapping("/list")
    public ModelAndView getStaffList() {
        Map<String, Object> params = new HashMap<>();
        params.put("page", 0);
        params.put("pageSize", 20);

        List<StaffBo> staffBoList = staffService.findAll(params);
        int count = staffService.count(params);
        ModelAndView modelAndView = new ModelAndView("/staff/staffDeptList");

        // 查询所有部门。
        List<DeptBo> deptBoList = deptService.findAllChild(1L);
        modelAndView.addObject("deptBoList", BeanMapper.mapList(deptBoList, DeptVo.class));

        modelAndView.addObject("result", PageUtil.initPage(0, 20, count, BeanMapper.mapList(staffBoList, StaffVo.class)));
        getConstantMap(modelAndView);
        return modelAndView;
    }

    @RequiresPermissions("sys:staff:view")
    @RequiresUser
    @RequestMapping("/list/new")
    public ModelAndView getStaffListNew(StaffBean staffBean) {
        Map<String, Object> params = BeanMapper.mapToHashMap(staffBean);
        Integer pageSize = staffBean.getNumPerPage() == null ? 20 : staffBean.getNumPerPage();
        Integer start = staffBean.getPageNum() == null ? 0 : (staffBean.getPageNum() - 1) * pageSize;

        params.put("page", start);
        params.put("pageSize", pageSize);

        List<StaffBo> staffBoList = staffService.findAll(params);
        int count = staffService.count(params);
        ModelAndView modelAndView = new ModelAndView("/staff/staffNewList");

        modelAndView.addObject("result", PageUtil.initPage(start, pageSize, count, BeanMapper.mapList(staffBoList, StaffVo.class)));
        getConstantMap(modelAndView);

        modelAndView.addObject("userName", staffBean.getUserName());
        modelAndView.addObject("kind", staffBean.getKind());
        modelAndView.addObject("deptId", staffBean.getDeptId());
        modelAndView.addObject("deptChildrenId", staffBean.getDeptChildrenId());
        return modelAndView;
    }

    @RequiresPermissions("sys:staff:view")
    @RequiresUser
    @RequestMapping("/search")
    public ModelAndView search(StaffBean staffBean) {
        Map<String, Object> params = BeanMapper.mapToHashMap(staffBean);
        params.put("page", staffBean.getPageNum());
        params.put("pageSize", staffBean.getNumPerPage());
        List<StaffBo> staffBoList = staffService.findAll(params);
        int count = staffService.count(params);
        ModelAndView modelAndView = new ModelAndView("/staff/staffList");
        modelAndView.addObject("result", PageUtil.initPage(staffBean.getStartIndex(), staffBean.getNumPerPage(), count, BeanMapper.mapList(staffBoList, StaffVo.class)));
        getConstantMap(modelAndView);
        return modelAndView;
    }

    @RequiresPermissions("sys:staff:edit")
    @RequiresUser
    @RequestMapping("/detail")
    public ModelAndView add(Integer staffId) {
        ModelAndView modelAndView = new ModelAndView("/staff/staffDetail");
        // 存放一级部门列表
        List<DeptBo> deptBoList = deptService.findByParentId(1L);
        modelAndView.addObject("deptBoList", BeanMapper.mapList(deptBoList, DeptVo.class));

        StaffVo vo = new StaffVo();
        if (staffId != null) {
            StaffBo staffBo = staffService.findOne(staffId);
            if (staffBo == null) {
                logger.error("staffBo is null, id is " + staffId);
                return modelAndView;// 存在id，找不到对象，可能是前端id错误，可能是后端数据错误，未直接抛出异常。
            }
            vo = BeanMapper.map(staffBo, StaffVo.class);
            modelAndView.addObject("staff", vo);
            // 补充二级菜单
            if (staffBo.getDeptChildrenId() != null) {
                List<DeptBo> deptChildrenBoList = deptService.findByParentId(staffBo.getDeptId().longValue());
                modelAndView.addObject("deptChildrenBoList", BeanMapper.mapList(deptChildrenBoList, DeptVo.class));
            }
        }
        getConstantMap(modelAndView);
        return modelAndView;
    }

    @RequiresPermissions("sys:staff:edit")
    @RequiresUser
    @RequestMapping("/dept/get")
    @ResponseBody
    public List<DeptVo> getDeptList(@RequestParam(value = "id", required = true) Integer id) {
        if (id == null) {
            logger.error("get dept error,param is null");
            return null;
        }
        // 查询子部门
        List<DeptBo> deptBoList = deptService.findByParentId(id.longValue());
        return BeanMapper.mapList(deptBoList, DeptVo.class);
    }

    @RequiresPermissions("sys:staff:edit")
    @RequiresUser
    @RequestMapping("/update")
    @ResponseBody
    public DwzVo update(StaffBean staffBean) {
        if (StringUtils.isEmpty(staffBean.getMobile()) || staffBean.getId() == null) {
            return new DwzVo("300", "更新失败,缺少参数");
        }
        StaffBo staffBo = staffService.getUserByPhone(staffBean.getMobile());
        if (staffBo != null && !(staffBean.getId().equals(staffBo.getId()))) {//通过手机号查询到存在用户且不是自己
            logger.error("手机号已经存在");
            return new DwzVo("300", "手机号已经存在");
        }

        DwzVo dwzVo = null;
        int count = staffService.update(BeanMapper.mapToHashMap(staffBean));
        if (count > 0) {
            dwzVo = new DwzVo("200", "操作成功", "staff", "", "closeCurrent", "/staff/list/new");
        } else {
            dwzVo = new DwzVo("300", "更新失败!!");
        }
        return dwzVo;
    }

    @RequiresPermissions("sys:staff:insert")
    @RequiresUser
    @RequestMapping("/insert")
    @ResponseBody
    public DwzVo insert(StaffBean staffBean) {
        if (staffBean == null || StringUtils.isEmpty(staffBean.getJobNumber()) || StringUtils.isEmpty(staffBean.getMobile())) {
            logger.error("参数为空");
            return new DwzVo("300", "操作失败");
        }
        // 根据工号查询用户信息。 只查询未删除的使用前者，查询所有使用后者
        // UserBo userBo = userService.getUserByJobNumber(staffBean.getJobNumber());
        // 当前使用的工号只会有一个人，已经被删除的同一工号可能有多人。
        List<UserBo> userBoList = userService.getAllUserByJobNumber(staffBean.getJobNumber());
        if (!CollectionUtils.isEmpty(userBoList)) {
            logger.error("工号已经存在");
            return new DwzVo("300", "工号已经存在");
        }

        if (StringUtils.isEmpty(staffBean.getMobile())) {
            return new DwzVo("300", "更新失败,缺少参数");
        }
        StaffBo staffBo = staffService.getUserByPhone(staffBean.getMobile());
        if (staffBo != null) {//通过手机号查询到存在用户
            logger.error("手机号已经存在");
            return new DwzVo("300", "手机号已经存在");
        }

        DwzVo dwzVo = null;
        int count = staffService.insert(BeanMapper.mapToHashMap(staffBean));
        if (count > 0) {
            dwzVo = new DwzVo("200", "操作成功", "staff", "", "closeCurrent", "/staff/list/new");
        } else {
            dwzVo = new DwzVo("300", "操作失败");
        }
        return dwzVo;
    }

    @RequiresPermissions("sys:staff:delete")
    @RequiresUser
    @RequestMapping("/delete")
    @ResponseBody
    public DwzVo delete(Integer staffId) {
        // 添加参数校验
        if (staffId == null) {
            logger.error("删除用户参数null");
            return new DwzVo("300", "操作失败");
        }
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new DwzVo("300", "操作失败");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null || userBo.getId() == null) {
            return new DwzVo("300", "操作失败");
        }
        if (userBo.getId().intValue() == staffId) {
            return new DwzVo("300", "不可删除当前用户");
        }

        DwzVo dwzVo = null;
        int count = staffService.delete(staffId);
        if (count > 0) {
            dwzVo = new DwzVo("200", "删除成功!", "staff", "", "", "/staff/list/new");
        } else {
            dwzVo = new DwzVo("300", "操作失败");
        }
        return dwzVo;
    }


    private void getConstantMap(ModelAndView modelAndView) {
        modelAndView.addObject("ageMap", DictConstants.ageMap);

        modelAndView.addObject("maritalMap", DictConstants.maritalMap);

        modelAndView.addObject("politicalMap", DictConstants.politicalMap);

        modelAndView.addObject("certificateMap", DictConstants.certificateMap);

        modelAndView.addObject("educationMap", DictConstants.educationMap);

        modelAndView.addObject("kindMap", DictConstants.kindMap);

        modelAndView.addObject("statusMap", DictConstants.statusMap);

        modelAndView.addObject("contractTypeMap", DictConstants.contractTypeMap);

        modelAndView.addObject("recruitChannelMap", DictConstants.recruitChannelMap);
    }

//    @InitBinder
//    protected void initBinder(HttpServletRequest request,
//                              ServletRequestDataBinder binder) throws Exception {
//        //对于需要转换为Date类型的属性，使用DateEditor进行处理
//        binder.registerCustomEditor(Date.class, new DateEditor());
//    }
}

