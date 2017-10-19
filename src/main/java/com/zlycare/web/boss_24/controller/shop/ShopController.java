package com.zlycare.web.boss_24.controller.shop;

import com.zlycare.web.boss_24.constants.shop.ShopConstants;
import com.zlycare.web.boss_24.controller.vo.DwzVo;
import com.zlycare.web.boss_24.controller.vo.ShopVo;
import com.zlycare.web.boss_24.core.service.bo.ShopBo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.shop.ShopService;
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
 * 商户
 *
 * @author
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;


    /**
     * 全城购-列表、搜索
     *
     * @param pageNum    pageNum
     * @param numPerPage numPerPage
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("shop:other:all")
    @RequestMapping("/all/list/get")
    @ResponseBody
    public ModelAndView getShopList(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "numPerPage", required = false) Integer numPerPage,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "status", required = false) String status
    ) {
        Page page;
        Integer pageSize = numPerPage != null ? numPerPage : 20;
        Integer start = pageNum != null ? (pageNum - 1) * pageSize : 0;

        ModelAndView modelAndView = new ModelAndView("/shop/shopAllList");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        if (StringUtils.isEmpty(userBo.getArea())) {
            page = PageUtil.initPage(start, pageSize, 0, Collections.EMPTY_LIST);
            modelAndView.addObject("page", page);
            modelAndView.addObject("searchParam_startDate", startDate);
            modelAndView.addObject("searchParam_endDate", endDate);
            modelAndView.addObject("searchParam_status", status);
            return modelAndView;
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
        // 拆分用户含有地域
        String[] area_string = userBo.getArea().split(",");
        List<String> areaList = Arrays.asList(area_string);
        Long start_date = null, end_date = null;

        if (StringUtils.isNotEmpty(startDate)) {
            start_date = getTime(startDate);
        }
        if (StringUtils.isNotEmpty(endDate)) {
            end_date = getTime(endDate) + 86400000;// 加一天
        }
        List<ShopBo> shopBoList = shopService.getAllList(ShopConstants.all, areaList, start_date, end_date, status_list, start, pageSize);
        Integer count = shopService.countAllList(ShopConstants.all, areaList, start_date, end_date, status_list, start, pageSize);

        List<ShopVo> shopVoList = BeanMapper.mapList(shopBoList, ShopVo.class);
        for (ShopVo shopVo : shopVoList) {
            if (shopVo.getCreatedAt() != null)
                shopVo.setCreateTime(new Date(shopVo.getCreatedAt().longValue()));
            if (shopVo.getUpdatedAt() != null)
                shopVo.setUpdateTime(new Date(shopVo.getUpdatedAt().longValue()));
            if (shopVo.getStatisticsUpdatedAt() != null)
                shopVo.setStatisticsUpdatedTime(new Date(shopVo.getStatisticsUpdatedAt().longValue()));
            if (shopVo.getOpReviewdAt() != null)
                shopVo.setOpReviewdTime(new Date(shopVo.getOpReviewdAt().longValue()));
        }
        if (CollectionUtils.isEmpty(shopBoList)) {
            page = PageUtil.initPage(start, pageSize, 0, Collections.EMPTY_LIST);
        } else {
            page = PageUtil.initPage(start, pageSize, count, shopVoList);
        }

        modelAndView.addObject("page", page);
        modelAndView.addObject("searchParam_startDate", startDate);
        modelAndView.addObject("searchParam_endDate", endDate);
        modelAndView.addObject("searchParam_status", status);
        return modelAndView;
    }

    @RequiresUser
    @RequiresPermissions("shop:other:all")
    @RequestMapping("/avatar")
    @ResponseBody
    public ModelAndView getShopAvatar(@RequestParam(value = "avatar") String avatar
    ) {
        ModelAndView modelAndView = new ModelAndView("/shop/shopAvatar");
        if (StringUtils.isEmpty(avatar)) {
            return modelAndView;
        }
        modelAndView.addObject("avatar", avatar);
        return modelAndView;
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

    @RequiresPermissions("shop:other:all")
    @RequiresUser
    @RequestMapping("/audit")
    @ResponseBody
    public DwzVo passShopApplication(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "status") Integer status,
            @RequestParam(value = "reason", required = false) String reason
    ) {
        if (StringUtils.isEmpty(id) || status == null ||
                (!(status.equals(ShopConstants.SHOP_AUDIT_PASS)) && (!(status.equals(ShopConstants.SHOP_AUDIT_REFUSE))))) {
            logger.error("审核商户参数null");
            return new DwzVo("300", "操作失败");
        }
        if (status.equals(ShopConstants.SHOP_AUDIT_REFUSE) && StringUtils.isEmpty(reason)) {
            logger.error("审核商户参数null");
            return new DwzVo("300", "操作失败");
        }
        shopService.auditShopApplication(id, status, reason);
        DwzVo dwzVo;
        if (status.equals(ShopConstants.SHOP_AUDIT_PASS)) {
            dwzVo = new DwzVo("200", "操作成功", "shop", "", "", "/shop/all/list/get");
        } else if (status.equals(ShopConstants.SHOP_AUDIT_REFUSE)) {
            // todo 。关闭dialog 并刷新
            dwzVo = new DwzVo("200", "操作成功", "shop", "", "closeCurrent", "/shop/all/list/get");
        } else {
            dwzVo = new DwzVo("300", "操作失败");
        }
        return dwzVo;
    }

    @RequiresPermissions("shop:other:all")
    @RequiresUser
    @RequestMapping("/refuse/view")
    @ResponseBody
    public ModelAndView getShopRefuseView(@RequestParam(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("/shop/shopRefuseView");
        if (StringUtils.isEmpty(id)) {
            logger.error("申请id为空，无法拒绝");
            return modelAndView;
        }
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    // -- 朱李叶健康 业务 --

    /**
     * 朱丽叶-列表、搜索
     *
     * @param pageNum    pageNum
     * @param numPerPage numPerPage
     * @return ModelAndView
     */
    @RequiresPermissions("shop:medical:all")
    @RequiresUser
    @RequestMapping("/medical/list/get")
    @ResponseBody
    public ModelAndView getShopMedicalList(
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "numPerPage", required = false) Integer numPerPage,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "status", required = false) String status
    ) {
        Page page;
        Integer pageSize = numPerPage != null ? numPerPage : 20;
        Integer start = pageNum != null ? (pageNum - 1) * pageSize : 0;

        ModelAndView modelAndView = new ModelAndView("/shop/shopMedicalList");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        if (StringUtils.isEmpty(userBo.getArea())) {
            // 防止
            page = PageUtil.initPage(start, pageSize, 0, Collections.EMPTY_LIST);
            modelAndView.addObject("page", page);
            modelAndView.addObject("searchParam_startDate", startDate);
            modelAndView.addObject("searchParam_endDate", endDate);
            modelAndView.addObject("searchParam_status", status);
            return modelAndView;
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
        // 拆分用户含有地域
        String[] area_string = userBo.getArea().split(",");
        List<String> areaList = Arrays.asList(area_string);
        Long start_date = null, end_date = null;

        if (StringUtils.isNotEmpty(startDate)) {
            start_date = getTime(startDate);
        }
        if (StringUtils.isNotEmpty(endDate)) {
            end_date = getTime(endDate) + 86400000;// 加一天
        }
        List<ShopBo> shopBoList = shopService.getAllList(ShopConstants.medical, areaList, start_date, end_date, status_list, start, pageSize);
        Integer count = shopService.countAllList(ShopConstants.medical, areaList, start_date, end_date, status_list, start, pageSize);

        List<ShopVo> shopVoList = BeanMapper.mapList(shopBoList, ShopVo.class);
        for (ShopVo shopVo : shopVoList) {
            if (shopVo.getCreatedAt() != null)
                shopVo.setCreateTime(new Date(shopVo.getCreatedAt().longValue()));
            if (shopVo.getUpdatedAt() != null)
                shopVo.setUpdateTime(new Date(shopVo.getUpdatedAt().longValue()));
            if (shopVo.getStatisticsUpdatedAt() != null)
                shopVo.setStatisticsUpdatedTime(new Date(shopVo.getStatisticsUpdatedAt().longValue()));
            if (shopVo.getOpReviewdAt() != null)
                shopVo.setOpReviewdTime(new Date(shopVo.getOpReviewdAt().longValue()));
        }
        if (CollectionUtils.isEmpty(shopBoList)) {
            page = PageUtil.initPage(start, pageSize, 0, Collections.EMPTY_LIST);
        } else {
            page = PageUtil.initPage(start, pageSize, count, shopVoList);
        }

        modelAndView.addObject("page", page);
        modelAndView.addObject("searchParam_startDate", startDate);
        modelAndView.addObject("searchParam_endDate", endDate);
        modelAndView.addObject("searchParam_status", status);
        return modelAndView;
    }

    @RequiresUser
    @RequiresPermissions("shop:medical:all")
    @RequestMapping("/medical/audit")
    @ResponseBody
    public DwzVo passShopMedicalApplication(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "status") Integer status,
            @RequestParam(value = "reason", required = false) String reason
    ) {
        if (StringUtils.isEmpty(id) || status == null ||
                (!(status.equals(ShopConstants.SHOP_AUDIT_PASS)) && (!(status.equals(ShopConstants.SHOP_AUDIT_REFUSE))))) {
            logger.error("审核商户参数null");
            return new DwzVo("300", "操作失败");
        }
        if (status.equals(ShopConstants.SHOP_AUDIT_REFUSE) && StringUtils.isEmpty(reason)) {
            logger.error("审核商户参数null");
            return new DwzVo("300", "操作失败");
        }
        shopService.auditShopApplication(id, status, reason);
        DwzVo dwzVo;
        if (status.equals(ShopConstants.SHOP_AUDIT_PASS)) {
            dwzVo = new DwzVo("200", "操作成功", "medical", "", "", "/shop/medical/list/get");
        } else if (status.equals(ShopConstants.SHOP_AUDIT_REFUSE)) {
            // todo 。关闭dialog 并刷新
            dwzVo = new DwzVo("200", "操作成功", "medical", "", "closeCurrent", "/shop/medical/list/get");
        } else {
            dwzVo = new DwzVo("300", "操作失败");
        }
        return dwzVo;
    }

    @RequiresPermissions("shop:medical:all")
    @RequiresUser
    @RequestMapping("/medical/refuse/view")
    @ResponseBody
    public ModelAndView getShopMedicalRefuseView(@RequestParam(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("/shop/shopMedicalRefuseView");
        if (StringUtils.isEmpty(id)) {
            logger.error("申请id为空，无法拒绝");
            return modelAndView;
        }
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @RequiresUser
    @RequiresPermissions("shop:medical:all")
    @RequestMapping("/medical/avatar")
    @ResponseBody
    public ModelAndView getShopMedicalAvatar(@RequestParam(value = "avatar") String avatar
    ) {
        ModelAndView modelAndView = new ModelAndView("/shop/shopAvatar");
        if (StringUtils.isEmpty(avatar)) {
            return modelAndView;
        }
        modelAndView.addObject("avatar", avatar);
        return modelAndView;
    }
}
