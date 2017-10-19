package com.zlycare.web.boss_24.controller.product;

import com.zlycare.web.boss_24.controller.bean.MemberBean;
import com.zlycare.web.boss_24.controller.bean.PageUtilBean;
import com.zlycare.web.boss_24.controller.vo.member.MemberVo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.bo.member.MemberBo;
import com.zlycare.web.boss_24.core.service.memberProduct.MemberService;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/8/4
 * Update : 2017/8/4
 * Descriptions : 会员管理 controller
 */

@Controller
@RequestMapping("/member")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private MemberService memberService;

    // 高级会员专区
    private final static String advanced_type = "zlycare";
    // vip会员专区
    private final static String vip_type = "zlycare_vip";

    // 会员管理 /member/list/get member_manage member:manage:all

    /**
     * 会员管理，列表页面、搜索分页等
     *
     * @param pageUtilBean pageUtilBean
     * @param memberBean   memberBean
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("member:manage:all")
    @RequestMapping("/list/get")
    @ResponseBody
    public ModelAndView getMemberList(PageUtilBean pageUtilBean, MemberBean memberBean) {
        ModelAndView modelAndView = new ModelAndView("/memberProduct/memberList");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }// 不关乎用户包含业务地域信息

        // 分页参数设置
        Page page;
        Integer pageSize = pageUtilBean.getNumPerPage() != null ? pageUtilBean.getNumPerPage() : 20;
        Integer start = pageUtilBean.getPageNum() != null ? (pageUtilBean.getPageNum() - 1) * pageSize : 0;

        com.zlycare.web.boss_24.core.service.bean.MemberBean m =
                BeanMapper.map(memberBean, com.zlycare.web.boss_24.core.service.bean.MemberBean.class);
        m.setStart(start);
        m.setPageSize(pageSize);
        m.setType(new ArrayList<String>() {{
            add(advanced_type);
            //add(vip_type); // 暂不查询是vip类型的数据，
            // 如果查询vip的话，两种类型的数据一起聚合出来，在外层再根据userId将不同类型的数据合并到一条数据的话，总条数就无法获取到。无法知道合并后的条数是多少。
            // userId 高级会员 高级会员总额 + userId vip会员 vip会员总额 = userId 高级会员总额 vip会员总额, count的是userId,不包括type。其他查询条件不变
            // todo add vip类型的话，需要在list中再次做聚合，会让100条的查询丢失部分几条，展示会不准确。
            // 一个折中办法，100个不同用户，两种类型，最多查询200条，每种类型各一百条，然后聚合后正好最少也会合并到一百条，然后根据传入的pageSize截取。不对，下一页就不准确了。
            // 注释vip类型
            // todo 以后如果出现vip或者其他类型，不再做聚合，【去掉聚合的部分service】页面同时出现[同一用户]两条数据，显示type，修改高级总额度为额度，高级剩余额度改为剩余额度。
        }});

        List<MemberBo> memberBoList = memberService.getAllList(m);
        Integer count = memberService.countAllList(m);

        List<MemberVo> memberVoList = BeanMapper.mapList(memberBoList, MemberVo.class);
        if (CollectionUtils.isEmpty(memberVoList)) {
            page = PageUtil.initPage(start, pageSize, 0, Collections.EMPTY_LIST);
        } else {
            page = PageUtil.initPage(start, pageSize, count, memberVoList);
        }
        /*返回参数,展示数据&分页参数&搜索参数*/
        modelAndView.addObject("page", page);
        modelAndView.addObject("pageUtilBean", pageUtilBean);
        modelAndView.addObject("memberBean", memberBean);
        return modelAndView;
    }
}
