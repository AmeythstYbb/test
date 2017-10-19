package com.zlycare.web.boss_24.controller.productManage;
import com.zlycare.web.boss_24.controller.bean.FeedBackBean;
import com.zlycare.web.boss_24.controller.vo.DwzVo;
import com.zlycare.web.boss_24.controller.vo.FeedBackVo;
import com.zlycare.web.boss_24.core.service.bo.FeedBackBo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.productManage.FeedBackService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by MRZ on 2017/8/31.
 */

/**
 * BOSS意见反馈 /feedBack/findAll feedBackManage feedBack:other:all
 */
@Controller
@RequestMapping("/feedBack")
public class FeedBackController {

    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(FeedBackController.class);

    private static Map<String, Integer> status = new HashMap<String, Integer>();
    static{
        status.put("待处理",0);
        status.put("已处理",2);
        status.put("忽略",1);
    }

    /**
     * 反馈信息列表
     *
     * @param pageNum
     * @param numPerPage
     * @param status
     * @return
     */
    @RequiresUser
    @RequiresPermissions("feedBack:other:all")
    @RequestMapping("/findAll")
    @ResponseBody

    public ModelAndView getShopList(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "numPerPage", required = false) Integer numPerPage,
            @RequestParam(value = "status", required = false) String status
    ) {
        Page page;
        Integer pageSize = numPerPage != null ? numPerPage : 20;
        Integer start = pageNum != null ? (pageNum - 1) * pageSize : 0;

        ModelAndView modelAndView = new ModelAndView("/productManage/FeedBackList");
        String userJobNumber = UserUtils.getUserJobNumber();
        //判断用户登录信息？  返回登录页面
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        //判断用户登录信息？  返回登录页面
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        // 拆分状态 List<Integer>
        List<Integer> status_list = new ArrayList<>();
        if (StringUtils.isNotEmpty(status)) {
            String[] status_string = status.split(",");
            List<String> statusList = Arrays.asList(status_string);
            for (String s : statusList) {
                status_list.add(Integer.parseInt(s));
            }
        }

        //获取list和count
        List<FeedBackBo> fbList = feedBackService.getAllList(status_list, start, pageSize);
        Integer count = feedBackService.countAllList(status_list, start, pageSize);
        List<FeedBackVo> feedBackVoList = BeanMapper.mapList(fbList, FeedBackVo.class);
        for (FeedBackVo feedBackVo : feedBackVoList) {
            if (feedBackVo.getCreatedAt() != null)
                feedBackVo.setCreateTime(new Date(feedBackVo.getCreatedAt().longValue()));
        }

        //封装page
        if (CollectionUtils.isEmpty(fbList)) {
            page = PageUtil.initPage(start, pageSize, 0, Collections.EMPTY_LIST);
        } else {
            page = PageUtil.initPage(start, pageSize, count, feedBackVoList);
        }
        modelAndView.addObject("page", page);
        modelAndView.addObject("searchParam_status", status);
        return modelAndView;
    }

    /**
     * 返回修改页面
     *
     * @param id
     * @return
     */
    @RequiresUser
    @RequiresPermissions("feedBack:other:all")
    @RequestMapping("/update/view")
    @ResponseBody
    public ModelAndView upDateView(@RequestParam(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("/productManage/update");
        if (StringUtils.isEmpty(id)) {
            logger.error("id为空，无法修改");
            return modelAndView;
        }
//       modelAndView.addObject("id", id);
        FeedBackBo feedBackBo = feedBackService.findById(id);
        modelAndView.addObject("feedBackBo", BeanMapper.map(feedBackBo, FeedBackVo.class));
        modelAndView.addObject("s_status",status);
        return modelAndView;
    }

    @RequiresUser
    @RequiresPermissions("feedBack:other:all")
    @RequestMapping("/update")
    @ResponseBody
    public DwzVo updataSr(FeedBackBean feedBackBean) {
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            if (StringUtils.isEmpty(userJobNumber)) {
                return new DwzVo("300", "操作失败");
            }
            UserBo userBo = userService.getUserByJobNumber(userJobNumber);
            if (userBo == null) {
                return new DwzVo("300", "操作失败");
            }
            if (feedBackBean == null) {
                logger.error("修改反馈信息失败，参数null");
                return new DwzVo("300", "操作失败");
            }
        }
        feedBackService.updateFeedBack(BeanMapper.map(feedBackBean, FeedBackBo.class));
        DwzVo dwzVo;
        dwzVo =new DwzVo("200", "操作成功", "feedBackManage", "", "closeCurrent", "/feedBack/findAll");
        return dwzVo;
    }


    private static Long getTime(String dataString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dataString);
            long time = date.getTime();
            System.out.println(time);
            return (Long) time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        getTime("2016-07-07");
//    }


}
