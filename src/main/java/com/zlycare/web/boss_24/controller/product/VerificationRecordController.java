package com.zlycare.web.boss_24.controller.product;

import com.zlycare.web.boss_24.controller.bean.PageUtilBean;
import com.zlycare.web.boss_24.controller.bean.VerificationRecordBean;
import com.zlycare.web.boss_24.controller.vo.VerificationRecordVo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.bo.VerificationRecordBo;
import com.zlycare.web.boss_24.core.service.memberProduct.VerificationRecordService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.core.Page;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author : linguodong
 * Create : 2017/6/28
 * Update : 2017/6/28
 * Descriptions : 朱李叶健康下-核销记录
 */
@Controller
@RequestMapping("/member/product/verification")
public class VerificationRecordController {
    @Autowired
    private UserService userService;
    @Autowired
    private VerificationRecordService verificationRecordService;

    private static final Logger logger = LoggerFactory.getLogger(VerificationRecordController.class);

    public final static Map<String, String> product_type = new HashMap<>();

    static {
        product_type.put("zlycare", "高级会员专区");
        product_type.put("zlycare_vip", "vip会员专区");
    }

    /**
     * 核销记录，列表页面、搜索分页等
     *
     * @param pageUtilBean           pageUtilBean
     * @param verificationRecordBean verificationRecordBean
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("product:verification:all")
    @RequestMapping("/list/get")
    @ResponseBody
    public ModelAndView getVerificationList(PageUtilBean pageUtilBean, VerificationRecordBean verificationRecordBean) {
        ModelAndView modelAndView = new ModelAndView("/memberProduct/verificationList");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }// 不关乎用户包含业务地域信息

        Page page;
        Integer pageSize = pageUtilBean.getNumPerPage() != null ? pageUtilBean.getNumPerPage() : 20;
        Integer start = pageUtilBean.getPageNum() != null ? (pageUtilBean.getPageNum() - 1) * pageSize : 0;
        Long start_date = StringUtils.isNotEmpty(pageUtilBean.getStartDate()) ? getAddTime(pageUtilBean.getStartDate(), 0) : null;
        Long end_date = StringUtils.isNotEmpty(pageUtilBean.getEndDate()) ? getAddTime(pageUtilBean.getEndDate(), 86400000) : null;
        com.zlycare.web.boss_24.core.service.bean.VerificationRecordBean v =
                BeanMapper.map(verificationRecordBean, com.zlycare.web.boss_24.core.service.bean.VerificationRecordBean.class);
        v.setStartDate(start_date);
        v.setEndDate(end_date);
        v.setStart(start);
        v.setPageSize(pageSize);

        List<VerificationRecordBo> verificationRecordBo = verificationRecordService.getAllList(v);
        Integer count = verificationRecordService.countAllList(v);

        List<VerificationRecordVo> verificationRecordListVo = BeanMapper.mapList(verificationRecordBo, VerificationRecordVo.class);
        for (VerificationRecordVo verificationRecordVo : verificationRecordListVo) {
            verificationRecordVo.setCreateTime(verificationRecordVo.getCreatedAt() != null ? new Date(verificationRecordVo.getCreatedAt().longValue()) : null);
            // 映射状态
            if (verificationRecordVo.isChecked()) {
                verificationRecordVo.setChecked_status("1");
            } else {
                verificationRecordVo.setChecked_status("0");
            }
        }
        if (CollectionUtils.isEmpty(verificationRecordListVo)) {
            page = PageUtil.initPage(start, pageSize, 0, Collections.EMPTY_LIST);
        } else {
            page = PageUtil.initPage(start, pageSize, count, verificationRecordListVo);
        }

        modelAndView.addObject("page", page);
        modelAndView.addObject("pageUtilBean", pageUtilBean);
        modelAndView.addObject("verificationRecordBean", verificationRecordBean);
        return modelAndView;
    }

    /**
     * 获取时间戳
     *
     * @param dataString dataString
     * @return Long
     */
    private static Long getAddTime(String dataString, Integer day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dataString);
            long time = date.getTime();
            // System.out.println(time);
            return (Long) time + day;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
