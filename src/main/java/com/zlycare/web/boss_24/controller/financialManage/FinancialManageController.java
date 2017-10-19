package com.zlycare.web.boss_24.controller.financialManage;

import com.zlycare.web.boss_24.controller.vo.DwzVo;
import com.zlycare.web.boss_24.controller.vo.FinancialManageVo;
import com.zlycare.web.boss_24.controller.bean.FinancialManageBean;
import com.zlycare.web.boss_24.core.service.bo.FinancialManageBo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.financialManage.FinancialManageService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.core.Page;
import com.zlycare.web.boss_24.utils.core.PageUtil;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Sulong on 2017/9/28.
 */

@Controller
@RequestMapping("/FinancialManage")
public class FinancialManageController {
    private static final Logger logger = LoggerFactory.getLogger(FinancialManageController.class);

    @Autowired
    private FinancialManageService financialManageService;

    @Autowired
    private UserService userService;


    /**
     * 接收页面传入参数：审核状态(status),数量分页(pageNum)，页数(numPerPage)
     *
     * @return
     */
    private static Map<String, Integer> status = new HashMap<String, Integer>();
    static{
        status.put("待处理",1);
        status.put("系统拒绝",-1);
        status.put("运营通过",2);
        status.put("运营拒绝",-2);
        status.put("财务拒绝",-3);
        status.put("财务已处理，提现成功",3);
    }

    /**
     *
     * @param pageNum
     * @param numPerPage
     * @param status
     * @return
     */
    @RequiresUser
    @RequiresPermissions("financialManage:other:all")
    @RequestMapping("/findAll")
    @ResponseBody

    public ModelAndView getShopList(
            //设定 4 个传入参数，
            @RequestParam(value = "pageNum",required = false) Integer pageNum,
            @RequestParam(value = "numPerPage",required = false) Integer numPerPage,
            @RequestParam(value = "status",required = false) String status,
            @RequestParam(value = "applicantPhone",required = false) String applicantPhone,
            @RequestParam(value = "alipayNum",required = false) String alipayNum


    ) {
        //判断page 默认20
        Page page;
        Integer pageSize = numPerPage != null ? numPerPage : 20;
        Integer start = pageNum != null ? (pageNum - 1) * pageSize : 0;
        //判断用户是否登录,否返回登录页面

        ModelAndView modelAndView = new ModelAndView("financialManage/FinancialManage");
        String userJobNumber = UserUtils.getUserJobNumber();
        //电话检索applicantPhone
//        List<Integer> applicationPhone_list = new ArrayList<>();
//        if(StringUtils.isEmpty(applicantPhone)){
//            String[] applicantPhone_string = applicantPhone.split(",");
//            List<String> applicantPhoneList = Arrays.asList(applicantPhone_string);
//            for(String a : applicantPhoneList){
//                applicationPhone_list.add(Integer.parseInt(a));
//            }
//        }
        //返回电话数组，传入参数
        //状态检索
        List<Integer> status_list = new ArrayList<>();
        if (StringUtils.isNotEmpty(status)) {
            String[] status_string = status.split(",");
            List<String> statusList = Arrays.asList(status_string);
            for (String s : statusList) {
                status_list.add(Integer.parseInt(s));
            }
        }
        //返回List,传入参数
        List<FinancialManageBo> financialManageBoList = financialManageService.getAllList(applicantPhone,alipayNum,status_list,start,pageSize);
        Integer count = financialManageService.countAllList(applicantPhone,alipayNum,status_list,start,pageSize);
        List<FinancialManageVo> financialManageVoList = BeanMapper.mapList(financialManageBoList,FinancialManageVo.class);
        for (FinancialManageVo financialManageVo : financialManageVoList){
            if (financialManageVo.getCreatedAt() != null)
                financialManageVo.setCreateTime(new Date(financialManageVo.getCreatedAt().longValue()));
        }
        //封装页数page
        if (CollectionUtils.isEmpty(financialManageBoList)){
            page = PageUtil.initPage(start,pageSize,0, Collections.EMPTY_LIST);
        }else{
            page = PageUtil.initPage(start,pageSize,count,financialManageVoList);
        }

        modelAndView.addObject("page", page);
        modelAndView.addObject("searchParam_status", status);
        modelAndView.addObject("applicantPhone",applicantPhone);
        modelAndView.addObject("alipayNum",alipayNum);
        return modelAndView;
    }
    /**
     * 返回修改页面
     *
     */
    // /FinancialManage/update/view
    @RequiresUser
    @RequiresPermissions("financialManage:other:all")
    @RequestMapping("/update/view")
    @ResponseBody
    public ModelAndView upDateView(@RequestParam(value ="id")String id){
        ModelAndView modelAndView = new ModelAndView("financialManage/update");
        if(StringUtils.isEmpty(id)){
            logger.error("id为空，无法修改");
            return modelAndView;
        }
        FinancialManageBo financialManageBo = financialManageService.findById(id);
        modelAndView.addObject("financialManageBo",BeanMapper.map(financialManageBo,FinancialManageVo.class));
        modelAndView.addObject("s_status",status);
        return modelAndView;
    }

    /**
     * 弹框页面
     * @param financialManageBean
     * @return
     * @throws Exception
     */
    @RequiresUser
    @RequiresPermissions("financialManage:other:all")
    @RequestMapping("/update")
    @ResponseBody
    public DwzVo updateSr(FinancialManageBean financialManageBean) throws Exception {
        String userJobNumber = UserUtils.getUserJobNumber();
        if(StringUtils.isEmpty(userJobNumber)){
            if(StringUtils.isEmpty(userJobNumber)){
                return new DwzVo("300","操作失败");
            }
            UserBo userBo = userService.getUserByJobNumber(userJobNumber);
            if(userBo == null){
                return new DwzVo("300","操作失败");
            }
            if(financialManageBean == null){
                logger.error("修改反馈信息失败，参数null");
                return new DwzVo("300","操作失败");
            }
        }
        financialManageService.updateFinancialManage(BeanMapper.map(financialManageBean,FinancialManageBo.class));
        DwzVo dwzVo;
        dwzVo = new DwzVo("200","操作成功","financialManage","","closeCurrent","/FinancialManage/findAll");

        return dwzVo;
    }

    /**
     * 运营通过请求 /FinancialManage/agree/update
     * @param financialManageBean
     * @return
     */
    @RequiresUser
    @RequiresPermissions("financialManage:other:all")
    @RequestMapping("/agree/update")
    @ResponseBody
    public DwzVo updataAgree(FinancialManageBean financialManageBean) throws Exception {

        String userJobNumber = UserUtils.getUserJobNumber();
        if(StringUtils.isEmpty(userJobNumber)){
            if(StringUtils.isEmpty(userJobNumber)){
                return new DwzVo("300","操作失败");
            }
            UserBo userBo = userService.getUserByJobNumber(userJobNumber);
            if(userBo == null){
                return new DwzVo("300","操作失败");
            }
            if(financialManageBean == null){
                logger.error("修改反馈信息失败，参数null");
                return new DwzVo("300","操作失败");
            }
        }
        financialManageService.updateAgree(BeanMapper.map(financialManageBean,FinancialManageBo.class));
        DwzVo dwzVo;
        dwzVo = new DwzVo("200","操作成功","financialManage","","","FinancialManage/findAll");
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
    /**
     * 工具类测试Demo
     */
//    public static void main(String[] args) throws Exception {
//        String bbb = sendFinancial("59df2903898db9b74269df82",
//                "http://api.dc-test.zlycare.com/1/operation/withdrawals/",
//                "-2", "sulong", "18888888888");
//    }

}



