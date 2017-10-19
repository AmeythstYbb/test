package com.zlycare.web.boss_24.controller.financialManage;

import com.zlycare.web.boss_24.controller.bean.FinancialReviewBean;
import com.zlycare.web.boss_24.controller.vo.DwzVo;
import com.zlycare.web.boss_24.controller.vo.FinancialReviewVo;
import com.zlycare.web.boss_24.core.exception.excel.ExcelColumnException;
import com.zlycare.web.boss_24.core.service.bo.FinancialReviewBo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.financialManage.FinancialReviewService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.core.Page;
import com.zlycare.web.boss_24.utils.core.PageUtil;
import com.zlycare.web.boss_24.utils.financialManage.ExcelUtils;
import com.zlycare.web.boss_24.utils.financialManage.xy;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sulong on 2017/10/12.
 */
@Controller
@RequestMapping("/FinancialReview")
public class FinancialReviewController {
    private static final Logger logger = LoggerFactory.getLogger(FinancialReviewController.class);

    private final static String excel2003L = ".xls"; // 2003- 版本的excel
    private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

    @Autowired
    private FinancialReviewService financialReviewService;

    @Autowired
    private UserService userService;

    /**
     * 接收页面传入参数：审核状态(status),数量分页(pageNum)，页数(numPerPage)
     *
     * @return
     */
    private static Map<String, Integer> status = new HashMap<String, Integer>();

    static {
        status.put("待财务审核", 2);
        status.put("财务拒绝", -3);
        status.put("已完成", 3);
    }

    /**
     * @param pageNum
     * @param numPerPage
     * @param status
     * @return
     */
    @RequiresUser
    @RequiresPermissions("financialReview:other:all")
    @RequestMapping("/findAll")
    @ResponseBody

    public ModelAndView getShopList(
            //设定 4 个传入参数，
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "numPerPage", required = false) Integer numPerPage,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "applicantPhone", required = false) String applicantPhone,
            @RequestParam(value = "alipayNum", required = false) String alipayNum


    ) {
        //判断page 默认20
        Page page;
        if(StringUtils.isEmpty(status)){
            status="2,3,-3";
        }
        Integer pageSize = numPerPage != null ? numPerPage : 20;
        Integer start = pageNum != null ? (pageNum - 1) * pageSize : 0;
        //判断用户是否登录,否返回登录页面
        //financialManage
        ModelAndView modelAndView = new ModelAndView("financialManage/FinancialReview");
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
        List<FinancialReviewBo> financialReviewBoList = financialReviewService.getAllList(applicantPhone, alipayNum, status_list, start, pageSize);
        Integer count = financialReviewService.countAllList(applicantPhone, alipayNum, status_list, start, pageSize);
        List<FinancialReviewVo> financialReviewVoList = BeanMapper.mapList(financialReviewBoList, FinancialReviewVo.class);
        for (FinancialReviewVo financialReviewVo : financialReviewVoList) {
            if (financialReviewVo.getCreatedAt() != null)
                financialReviewVo.setCreateTime(new Date(financialReviewVo.getCreatedAt().longValue()));
        }
        //封装页数page
        if (CollectionUtils.isEmpty(financialReviewBoList)) {
            page = PageUtil.initPage(start, pageSize, 0, Collections.EMPTY_LIST);
        } else {
            page = PageUtil.initPage(start, pageSize, count, financialReviewVoList);
        }

        modelAndView.addObject("page", page);
        modelAndView.addObject("searchParam_status", status);
        modelAndView.addObject("applicantPhone", applicantPhone);
        modelAndView.addObject("alipayNum", alipayNum);
        return modelAndView;
    }

    /**
     * 返回页面（点击跳出的弹框的页面）
     */ //FinancialReview/upload/view
    // /FinancialReview/update/view
    @RequiresUser
    @RequiresPermissions("financialReview:other:all")
    @RequestMapping("/upload/view")
    @ResponseBody
    public ModelAndView upDateView(@RequestParam(value = "id") String id) {
//                     viewName 的路径是 页面文件view的路径
        ModelAndView modelAndView = new ModelAndView("financialManage/upload");

        return modelAndView;
    }

    @RequiresUser
    @RequiresPermissions("financialReview:other:all")
    @RequestMapping("/update")
    @ResponseBody
    public DwzVo updateSr(FinancialReviewBean financialReviewBean) throws Exception {
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            if (StringUtils.isEmpty(userJobNumber)) {
                return new DwzVo("300", "操作失败");
            }
            UserBo userBo = userService.getUserByJobNumber(userJobNumber);
            if (userBo == null) {
                return new DwzVo("300", "操作失败");
            }
            if (financialReviewBean == null) {
                logger.error("修改反馈信息失败，参数null");
                return new DwzVo("300", "操作失败");
            }
        }
        financialReviewService.updateFinancialReview(BeanMapper.map(financialReviewBean, FinancialReviewBo.class));
        DwzVo dwzVo;
        dwzVo = new DwzVo("200", "操作成功", "financialReview", "", "closeCurrent", "/FinancialReview/findAll");

        return dwzVo;
    }

    /**
     * 运营通过请求 /FinancialReview/agree/update
     *
     * @param financialReviewBean
     * @return
     */
    @RequiresUser
    @RequiresPermissions("financialReview:other:all")
    @RequestMapping("/agree/update")
    @ResponseBody
    public DwzVo updataAgree(FinancialReviewBean financialReviewBean) throws Exception {

        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            if (StringUtils.isEmpty(userJobNumber)) {
                return new DwzVo("300", "操作失败");
            }
            UserBo userBo = userService.getUserByJobNumber(userJobNumber);
            if (userBo == null) {
                return new DwzVo("300", "操作失败");
            }
            if (financialReviewBean == null) {
                logger.error("修改反馈信息失败，参数null");
                return new DwzVo("300", "操作失败");
            }
        }
        financialReviewService.updateAgree(BeanMapper.map(financialReviewBean, FinancialReviewBo.class));
        DwzVo dwzVo;
        dwzVo = new DwzVo("200", "操作成功", "financialReview", "", "", "FinancialReview/findAll");
        return dwzVo;
    }

    /**
     * 上传EXCEL文件
     * /FinancialReview/financialReview/upload
     *
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("financialReview:other:all")
    @RequestMapping(value = "/financialReview/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DwzVo auditUpload(@RequestParam(value = "excel") CommonsMultipartFile multipartfile) {
        ModelAndView modelAndView = new ModelAndView("/financialManage/upload");
        if (multipartfile.isEmpty()) {
            logger.error("EXCEL文件为空");
            // return modelAndView;
            return new DwzVo("300", "上传文件格式有误");
        }
        String fileName = multipartfile.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        /*判断文件格式后缀*/
        if (StringUtils.isEmpty(fileType) || (!fileType.equals(excel2003L) && !fileType.equals(excel2007U))) {
            logger.error("文件格式错误");
//            return modelAndView;
            return new DwzVo("300", "上传文件格式有误");
        }
        // 同批次时间
        Double time = ((Long) new Date().getTime()).doubleValue();
        try {
            InputStream in = null;
            List<List<Object>> listobali = new ArrayList<List<Object>>();
            List<List<Object>> listobyinhang = new ArrayList<List<Object>>();

            in = multipartfile.getInputStream();

//            listob = new ImportExcelUtil().getBankListByExcel(in, fileName);
            String sheelNameAli = "支付宝";
            String sheelNameYinhang = "银行";
            ExcelUtils excelUtils = new ExcelUtils();
            xy xy = excelUtils.getBankListByExcel(in, fileName);
            if (xy == null) {
                throw new Exception();
            } else {
                listobyinhang = xy.getYhk();
                listobali = xy.getZfb();

            }

            if (CollectionUtils.isEmpty(listobali)) {
                return new DwzVo("300", "上传文件格式有误");
            }
            if (CollectionUtils.isEmpty(listobyinhang)) {
                return new DwzVo("300", "上传文件格式有误");
            }

            List<FinancialReviewBo> financialReviewBozfbList = new ArrayList<>();
            for (int i = 0; i < listobali.size(); i++) {
                //System.out.println(listobali);
                List<Object> lo = listobali.get(i);
                FinancialReviewBo financialReviewBo = new FinancialReviewBo();
                financialReviewBo.setId(String.valueOf(lo.get(0)));
                financialReviewBo.setCash((StringUtils.isNotEmpty(String.valueOf(lo.get(1)))) ? Double.valueOf(String.valueOf(lo.get(1))).doubleValue() : null);
                financialReviewBo.setAlipayName(String.valueOf(lo.get(2)));
                financialReviewBo.setAlipayNum(String.valueOf(lo.get(3)));
                financialReviewBo.setExcelstatus(String.valueOf(lo.get(4)));
                financialReviewBo.setReason(String.valueOf(lo.get(5)));
                /*批次和上传状态在外层设置,减少底层的再次循环,降低时间*/
                // 初始化上传状态为:未上传
                financialReviewBozfbList.add(financialReviewBo);
            }
            financialReviewService.financialUpload(financialReviewBozfbList);
            /**
             * 银行卡
             */
            List<FinancialReviewBo> financialReviewBoyhkList = new ArrayList<>();

            for (int i = 0; i < listobyinhang.size(); i++) {
                //System.out.println(listobyinhang);
                List<Object> lo = listobyinhang.get(i);
                FinancialReviewBo financialReviewBo = new FinancialReviewBo();
//               // ID       id
                financialReviewBo.setId(String.valueOf(lo.get(0)));
                // 设置金额
                financialReviewBo.setCash((StringUtils.isNotEmpty(String.valueOf(lo.get(1)))) ? Double.valueOf(String.valueOf(lo.get(1))).doubleValue() : null);
                // 姓名
                financialReviewBo.setApplicantName(String.valueOf(lo.get(2)));
                // 同意/拒绝  status
                financialReviewBo.setExcelstatus(String.valueOf(lo.get(3)));
                //回填码   reason
                financialReviewBo.setReason(String.valueOf(lo.get(4)));
                financialReviewBoyhkList.add(financialReviewBo);
            }
            // 解析完成 批量存入临时表
            financialReviewService.financialUpload(financialReviewBoyhkList);
        }
        catch (ExcelColumnException e) {
            logger.error(e.getMessage());
            return new DwzVo("300", e.getMessage());
        }
        catch (Exception e) {
            // e.printStackTrace();
            logger.error(e.toString());
            return new DwzVo("300","操作失败");
        }
        return new DwzVo("200", "操作成功", "financialReview", "", "closeCurrent", "/FinancialReview/findAll");
    }

    private static Long getTime(String dataString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dataString);
            long time = date.getTime();
            //System.out.println(time);
            return (Long) time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
