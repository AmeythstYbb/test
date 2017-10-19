package com.zlycare.web.boss_24.controller.product;

import com.zlycare.web.boss_24.constants.memberProduct.ProductConstants;
import com.zlycare.web.boss_24.constants.memberProduct.ProductEnum;
import com.zlycare.web.boss_24.controller.bean.MemberProductBean;
import com.zlycare.web.boss_24.controller.bean.MemberProductPageBean;
import com.zlycare.web.boss_24.controller.vo.*;
import com.zlycare.web.boss_24.core.service.bo.MemberProductBo;
import com.zlycare.web.boss_24.core.service.bo.ProductCatalogBo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.config.ProductSalesAreaService;
import com.zlycare.web.boss_24.core.service.memberProduct.MemberProductService;
import com.zlycare.web.boss_24.core.service.memberProduct.ProductCatalogService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.upload.Upload;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Author : linguodong
 * Create : 2017/6/28
 * Update : 2017/6/28
 * Descriptions : 朱李叶健康下-会员产品录入
 */
@Controller
@RequestMapping("/member/product")
public class VipMemberProductController {
    @Autowired
    private UserService userService;
    @Autowired
    private MemberProductService memberProductService;
    @Autowired
    private ProductCatalogService productCatalogService;
    @Autowired
    private ProductSalesAreaService productSalesAreaService;

    private static final Logger logger = LoggerFactory.getLogger(VipMemberProductController.class);

    //private static final String qiniu = "http://7j1ztl.com1.z0.glb.clouddn.com/";
    private static final String qiniu = "http://dn-juliye.qbox.me/";

    public final static Map<String, String> product_type = new HashMap<>();

    static {
        product_type.put("zlycare", "高级会员专区");
        product_type.put("zlycare_vip", "vip会员专区");
    }

    // /member/product/commit/view member_product_commit 产品提交

    /**
     * 产品提交 列表页面
     *
     * @param memberProductPageBean memberProductPageBean
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("product:commit:all")
    @RequestMapping("/commit/view")
    @ResponseBody
    public ModelAndView getCommitList(MemberProductPageBean memberProductPageBean) {
        Page page;
        Integer pageSize = memberProductPageBean.getNumPerPage() != null ? memberProductPageBean.getNumPerPage() : 20;
        Integer start = memberProductPageBean.getPageNum() != null ? (memberProductPageBean.getPageNum() - 1) * pageSize : 0;

        ModelAndView modelAndView = new ModelAndView("/memberProduct/commitList");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }// 不关乎用户包含业务地域信息
        /*拆分状态 List<Integer>*/
        List<Integer> status_list = new ArrayList<>();
        if (StringUtils.isNotEmpty(memberProductPageBean.getStatus())) {
            String[] status_string = memberProductPageBean.getStatus().split(",");
            List<String> statusList = Arrays.asList(status_string);
            for (String s : statusList) {
                status_list.add(Integer.parseInt(s));
            }
        }
        // todo null处理
        Long start_date = StringUtils.isNotEmpty(memberProductPageBean.getStartDate()) ? getTime(memberProductPageBean.getStartDate()) : null;
        Long end_date = StringUtils.isNotEmpty(memberProductPageBean.getEndDate()) ? getTime(memberProductPageBean.getEndDate()) + 86400000 : null;
        // 超级管理员最高查询权限。
        if (userBo.getId() == 1) {
            userJobNumber = null;
        }
        List<MemberProductBo> memberProductBoList = memberProductService.getAllList(userJobNumber, memberProductPageBean.getProductName(),
                start_date, end_date, status_list, start, pageSize, null, null, memberProductPageBean.getVipType(), memberProductPageBean.getFirstType(), memberProductPageBean.getSecondType(), memberProductPageBean.getThirdType());
        Integer count = memberProductService.countAllList(userJobNumber, memberProductPageBean.getProductName(),
                start_date, end_date, status_list, start, pageSize, null, null, memberProductPageBean.getVipType(), memberProductPageBean.getFirstType(), memberProductPageBean.getSecondType(), memberProductPageBean.getThirdType());

        List<MemberProductVo> memberProductVoList = BeanMapper.mapList(memberProductBoList, MemberProductVo.class);
        for (MemberProductVo memberProductVo : memberProductVoList) {
            memberProductVo.setCreateTime(memberProductVo.getCreatedAt() != null ? new Date(memberProductVo.getCreatedAt().longValue()) : null);
            memberProductVo.setUpdateTime(memberProductVo.getUpdatedAt() != null ? new Date(memberProductVo.getUpdatedAt().longValue()) : null);
            memberProductVo.setStatisticsUpdatedTime(memberProductVo.getStatisticsUpdatedAt() != null ? new Date(memberProductVo.getStatisticsUpdatedAt().longValue()) : null);
            memberProductVo.setVipTypeName(memberProductVo.getVipType() != null ? product_type.get(memberProductVo.getVipType()) : null);
        }
        if (CollectionUtils.isEmpty(memberProductBoList)) {
            page = PageUtil.initPage(start, pageSize, 0, Collections.EMPTY_LIST);
        } else {
            page = PageUtil.initPage(start, pageSize, count, memberProductVoList);
        }

        modelAndView.addObject("page", page);
        modelAndView.addObject("searchParam_startDate", memberProductPageBean.getStartDate());
        modelAndView.addObject("searchParam_endDate", memberProductPageBean.getEndDate());
        modelAndView.addObject("searchParam_status", memberProductPageBean.getStatus());
        modelAndView.addObject("searchParam_productName", memberProductPageBean.getProductName());
        return modelAndView;
    }

    /**
     * 返回修改产品信息页面
     */
    @RequiresUser
    @RequiresPermissions("product:commit:all")
    @RequestMapping(value = "/commit/update/view")
    @ResponseBody
    public ModelAndView updateMenuView(@RequestParam(value = "id", required = false) String id) {
        ModelAndView modelAndView = new ModelAndView("/memberProduct/commitUpdate");
        // 产品会员专区
        modelAndView.addObject("vipType", product_type);
        // 产品可销售区域
        List<String> productSalesAreaList = productSalesAreaService.getProductSalesArea();
        modelAndView.addObject("productSalesAreaList", productSalesAreaList);
        // 存放一级类目列表
        //List<String> typeList = memberProductService.findTypeList();
        //modelAndView.addObject("typeList", typeList);
        if (id == null) {
            // 直接返回页面
            return modelAndView;
        }
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        MemberProductBo memberProductBo = memberProductService.getMemberProductById(id);
        if (memberProductBo != null && StringUtils.isNotEmpty(memberProductBo.getThirdType())) {
            // 通过 三级类目ID 反查 二级类目ID 和 一级类目ID 并存放到BO
            List<ProductCatalogBo> parentCatalogList = productCatalogService.getAllparentCatalogListWithId(null, memberProductBo.getThirdType());
            if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(parentCatalogList)) {
                parentCatalogList.stream().forEach((parentCatalog) -> {
                    if (parentCatalog.getId().equals(memberProductBo.getThirdType())) {//三级目录名称
                        //memberProductBo.setThirdTypeName(parentCatalog.getName());
                    } else if (parentCatalog.getParentId().equals("1")) {// 一级目录名称
                        //memberProductBo.setFirstTypeName(parentCatalog.getName());
                        memberProductBo.setFirstType(parentCatalog.getId());
                    } else {
                        //memberProductBo.setSecondTypeName(parentCatalog.getName());
                        memberProductBo.setSecondType(parentCatalog.getId());
                    }
                });
            }
        }
        // 根据会员专区类型 查询一级类目
        if (memberProductBo != null && StringUtils.isNotEmpty(memberProductBo.getVipType())) {
            List<ProductCatalogBo> productCatalogBoList = productCatalogService.getAllRootCatalogList(memberProductBo.getVipType());
            modelAndView.addObject("firstTypeList", productCatalogBoList);
        }
        // 根据一级类目ID查询二级类目集合
        if (memberProductBo != null && StringUtils.isNotEmpty(memberProductBo.getFirstType())) {
            List<ProductCatalogBo> productCatalogBoList = productCatalogService.getCatalogListWithParentId(null, memberProductBo.getFirstType());
            modelAndView.addObject("secondTypeList", productCatalogBoList);
        }
        // 根据二级类目 查询三级类目集合
        if (memberProductBo != null && StringUtils.isNotEmpty(memberProductBo.getSecondType())) {
            List<ProductCatalogBo> productCatalogBoList = productCatalogService.getCatalogListWithParentId(null, memberProductBo.getSecondType());
            modelAndView.addObject("thirdTypeList", productCatalogBoList);
        }
        // 将 产品可销售区域 集合转为字符串 逗号分隔
        if (memberProductBo != null && org.apache.commons.collections4.CollectionUtils.isNotEmpty(memberProductBo.getProductSalesArea())) {
            memberProductBo.setProductSalesAreaText(memberProductBo.getProductSalesArea().stream().collect(Collectors.joining(",")));
        }
        //if (memberProductBo != null && StringUtils.isNotEmpty(memberProductBo.getType())) {
        // 存放二级类目列表
        // List<String> subTypeList = memberProductService.findSubTypeList(memberProductBo.getType());
        // modelAndView.addObject("subTypeList", subTypeList);
        //}
        modelAndView.addObject("memberProductBo", BeanMapper.map(memberProductBo, MemberProductVo.class));
        return modelAndView;
    }


//    @RequestMapping("/subType/get")
//    @ResponseBody
//    public List<String> getTypeList(@RequestParam(value = "type", required = true) String type) {
//        if (StringUtils.isEmpty(type)) {
//            logger.error("get type error,param is null");
//            return null;
//        }
//        // 查询二级类目
//        List<String> subTypeList = memberProductService.findSubTypeList(type);
//        return subTypeList;
//    }


    /**
     * 根据 会员专区类型 获取产品一级类目 列表
     *
     * @param type 会员专区类型
     * @return List<String>
     */
    @RequestMapping("/type/get")
    @ResponseBody
    public List<ProductCatalogVo> getTypeList(@RequestParam(value = "type", required = true) String type) {
        if (StringUtils.isEmpty(type)) {
            logger.error("get type error,param is null");
            return null;
        }
        // 根据会员专区类型 查询一级类目
        List<ProductCatalogBo> productCatalogBoList = productCatalogService.getAllRootCatalogList(type);
        return BeanMapper.mapList(productCatalogBoList, ProductCatalogVo.class);
    }

    /**
     * 根据 产品一级类目ID 获取产品二级类目列表,因ID是唯一，此处不需要 产品专区类型
     *
     * @param id id 一节类目id
     * @return List<String>
     */
    @RequestMapping("/subType/get")
    @ResponseBody
    public List<ProductCatalogVo> getSubTypeList(@RequestParam(value = "id", required = true) String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("get type error,param is null");
            return null;
        }
        // 根据一级类目ID,查询二级类目
        List<ProductCatalogBo> productCatalogBoList = productCatalogService.getCatalogListWithParentId(null, id);
        return BeanMapper.mapList(productCatalogBoList, ProductCatalogVo.class);
    }

    /**
     * 根据 产品二级类目ID 获取产品三级类目列表,因ID是唯一，此处不需要 产品专区类型
     *
     * @param id id 一节类目id
     * @return List<String>
     */
    @RequestMapping("/thirdType/get")
    @ResponseBody
    public List<ProductCatalogVo> getThirdTypeList(@RequestParam(value = "id", required = true) String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("get type error,param is null");
            return null;
        }
        // 根据二级类目ID,查询三级类目
        List<ProductCatalogBo> productCatalogBoList = productCatalogService.getCatalogListWithParentId(null, id);
        return BeanMapper.mapList(productCatalogBoList, ProductCatalogVo.class);
    }

    /**
     * 插入、修改产品信息
     */
    @RequiresUser
    @RequiresPermissions("product:commit:all")
    @RequestMapping(value = "/commit/update")
    @ResponseBody
    public DwzVo updattCommit(MemberProductBean memberProductBean) {
        if (memberProductBean == null) {
            logger.error("修改 插入 产品信息 null 参数null");
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
        if (!CollectionUtils.isEmpty(memberProductBean.getProductDeletePics())) {
            try {
                // 七牛删除
                new Upload().delete(memberProductBean.getProductDeletePics());
            } catch (IOException e) {
                logger.error("文件删除失败");
                e.printStackTrace();
            }
        }
        if (!CollectionUtils.isEmpty(memberProductBean.getProductPics())) {
            // 补加前缀
            List<String> pic = new ArrayList<>();
            for (String s : memberProductBean.getProductPics()) {
                pic.add(qiniu + s);
            }
            memberProductBean.setProductPics(pic);
        }
        memberProductBean.setCreator(userBo.getUserName());
        memberProductBean.setJobNumber(userBo.getJobNumber());
        // 初始化pics
        memberProductService.saveMemberProduct(BeanMapper.map(memberProductBean, MemberProductBo.class));
        return new DwzVo("member_product_commit");
    }

    /**
     * 查看图片列表
     *
     * @param id id
     * @return ModelAndView
     */
    @RequiresUser
    @RequestMapping("/pic")
    @ResponseBody
    public ModelAndView getShopAvatar(@RequestParam(value = "id") String id
    ) {
        ModelAndView modelAndView = new ModelAndView("/memberProduct/pic");
        if (id == null) {
            // 直接返回页面
            return modelAndView;
        }
        MemberProductBo memberProductBo = memberProductService.getMemberProductById(id);
        modelAndView.addObject("memberProductBo", BeanMapper.map(memberProductBo, MemberProductVo.class));
        return modelAndView;
    }

    /**
     * 提交 上传文件
     *
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("product:commit:all")
    @RequestMapping(value = "/commit/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonVo upload(@RequestParam(value = "file") CommonsMultipartFile multipartfile) {
        // 七牛
        try {
            String url = new Upload().upload(multipartfile.getBytes());
            // 七牛前缀路径
            // System.out.println(url);
            //String url = "H7sdfasj*gasgfda" + System.currentTimeMillis();
            return new JsonVo("name", url, 0, url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonVo("name", "", 0, "");
    }

    /**
     * 审核员 修改 上传文件
     *
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("product:audit:all")
    @RequestMapping(value = "/audit/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonVo auditUpload(@RequestParam(value = "file") CommonsMultipartFile multipartfile) {
        // 七牛
        try {
            String url = new Upload().upload(multipartfile.getBytes());
            // 七牛前缀路径
            // System.out.println(url);
            //String url = "H7sdfasj*gasgfda" + System.currentTimeMillis();
            return new JsonVo("name", url, 0, url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonVo("name", "", 0, "");
    }

    // 删除文件list 七牛删除

    // /member/product/audit/view  member_product_audit  产品审核

    /**
     * 产品审核 列表页面
     *
     * @param memberProductPageBean memberProductPageBean
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("product:audit:all")
    @RequestMapping("/audit/view")
    @ResponseBody
    public ModelAndView getAuditList(MemberProductPageBean memberProductPageBean) {
        ModelAndView modelAndView = new ModelAndView("/memberProduct/auditList");
        // 存放 会员所属专区
        modelAndView.addObject("vipType", product_type);
        // 存放一级类目列表
        // List<String> typeList = memberProductService.findTypeList();
        // modelAndView.addObject("typeList", typeList);
        Page page;
        Integer pageSize = memberProductPageBean.getNumPerPage() != null ? memberProductPageBean.getNumPerPage() : 20;
        Integer start = memberProductPageBean.getPageNum() != null ? (memberProductPageBean.getPageNum() - 1) * pageSize : 0;

        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }// 不关乎用户包含业务地域信息

        /*拆分状态 List<Integer>*/
        List<Integer> status_list = new ArrayList<>();
        if (StringUtils.isNotEmpty(memberProductPageBean.getStatus())) {
            String[] status_string = memberProductPageBean.getStatus().split(",");
            List<String> statusList = Arrays.asList(status_string);
            for (String s : statusList) {
                status_list.add(Integer.parseInt(s));
            }
        }
        // todo null处理
        Long start_date = StringUtils.isNotEmpty(memberProductPageBean.getStartDate()) ? getTime(memberProductPageBean.getStartDate()) : null;
        Long end_date = StringUtils.isNotEmpty(memberProductPageBean.getEndDate()) ? getTime(memberProductPageBean.getEndDate()) + 86400000 : null;

        List<MemberProductBo> memberProductBoList = memberProductService.getAllList(null, memberProductPageBean.getProductName(),
                start_date, end_date, status_list, start, pageSize, null, null, memberProductPageBean.getVipType(), memberProductPageBean.getFirstType(), memberProductPageBean.getSecondType(), memberProductPageBean.getThirdType());
        Integer count = memberProductService.countAllList(null, memberProductPageBean.getProductName(),
                start_date, end_date, status_list, start, pageSize, null, null, memberProductPageBean.getVipType(), memberProductPageBean.getFirstType(), memberProductPageBean.getSecondType(), memberProductPageBean.getThirdType());

        List<MemberProductVo> memberProductVoList = BeanMapper.mapList(memberProductBoList, MemberProductVo.class);
        for (MemberProductVo memberProductVo : memberProductVoList) {
            memberProductVo.setVipTypeName(memberProductVo.getVipType() != null ? product_type.get(memberProductVo.getVipType()) : null);
            memberProductVo.setCreateTime(memberProductVo.getCreatedAt() != null ? new Date(memberProductVo.getCreatedAt().longValue()) : null);
            memberProductVo.setUpdateTime(memberProductVo.getUpdatedAt() != null ? new Date(memberProductVo.getUpdatedAt().longValue()) : null);
            memberProductVo.setStatisticsUpdatedTime(memberProductVo.getStatisticsUpdatedAt() != null ? new Date(memberProductVo.getStatisticsUpdatedAt().longValue()) : null);
        }
        if (CollectionUtils.isEmpty(memberProductBoList)) {
            page = PageUtil.initPage(start, pageSize, 0, Collections.EMPTY_LIST);
        } else {
            page = PageUtil.initPage(start, pageSize, count, memberProductVoList);
        }

        //if (StringUtils.isNotEmpty(memberProductPageBean.getType())) {
        // 存放二级类目列表
        //List<String> subTypeList = memberProductService.findSubTypeList(memberProductPageBean.getType());
        //modelAndView.addObject("subTypeList", subTypeList);
        //}
        // 根据会员专区类型 查询一级类目
        if (StringUtils.isNotEmpty(memberProductPageBean.getVipType())) {
            List<ProductCatalogBo> productCatalogBoList = productCatalogService.getAllRootCatalogList(memberProductPageBean.getVipType());
            modelAndView.addObject("firstTypeList", productCatalogBoList);
        }
        // 根据一级类目ID查询二级类目集合
        if (StringUtils.isNotEmpty(memberProductPageBean.getFirstType())) {
            List<ProductCatalogBo> productCatalogBoList = productCatalogService.getCatalogListWithParentId(null, memberProductPageBean.getFirstType());
            modelAndView.addObject("secondTypeList", productCatalogBoList);
        }
        // 根据二级类目 查询三级类目集合
        if (StringUtils.isNotEmpty(memberProductPageBean.getSecondType())) {
            List<ProductCatalogBo> productCatalogBoList = productCatalogService.getCatalogListWithParentId(null, memberProductPageBean.getSecondType());
            modelAndView.addObject("thirdTypeList", productCatalogBoList);
        }

        modelAndView.addObject("page", page);
        modelAndView.addObject("searchParam_startDate", memberProductPageBean.getStartDate());
        modelAndView.addObject("searchParam_endDate", memberProductPageBean.getEndDate());
        modelAndView.addObject("searchParam_status", memberProductPageBean.getStatus());
        modelAndView.addObject("searchParam_productName", memberProductPageBean.getProductName());
        //modelAndView.addObject("searchParam_type", memberProductPageBean.getType());
        //modelAndView.addObject("searchParam_subType", memberProductPageBean.getSubType());
        modelAndView.addObject("searchParam_vipType", memberProductPageBean.getVipType());
        modelAndView.addObject("searchParam_firstType", memberProductPageBean.getFirstType());
        modelAndView.addObject("searchParam_secondType", memberProductPageBean.getSecondType());
        modelAndView.addObject("searchParam_thirdType", memberProductPageBean.getThirdType());

        return modelAndView;
    }

    @RequiresUser
    @RequiresPermissions("product:audit:all")
    @RequestMapping("/refuse/dialog/view")
    @ResponseBody
    public ModelAndView productRefuseView(@RequestParam(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("/memberProduct/auditRefuseView");
        if (StringUtils.isEmpty(id)) {
            logger.error("申请id为空，无法拒绝");
            return modelAndView;
        }
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @RequiresUser
    @RequiresPermissions("product:audit:all")
    @RequestMapping("/audit")
    @ResponseBody
    public DwzVo passShopApplication(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "status") Integer status,
            @RequestParam(value = "reason", required = false) String reason,
            //@RequestParam(value = "type", required = false) String type,
            //@RequestParam(value = "subType", required = false) String subType,
            //@RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "realPrice", required = false) Double realPrice,
            @RequestParam(value = "servicePeopleName", required = false) String servicePeopleName,
            @RequestParam(value = "servicePeopleCall", required = false) String servicePeopleCall,
            @RequestParam(value = "servicePeopleImUserName", required = false) String servicePeopleImUserName,
            @RequestParam(value = "servicePeopleId", required = false) String servicePeopleId,
            @RequestParam(value = "servicePeopleDocChatNum", required = false) String servicePeopleDocChatNum
            //,@RequestParam(value = "vipType", required = false) String vipType
    ) {
        if (StringUtils.isEmpty(id) || status == null ||
                (!(status.equals(ProductConstants.PRODUCT_AUDIT_PASS)) && (!(status.equals(ProductConstants.PRODUCT_AUDIT_REFUSE))))) {
            logger.error("审核产品参数null");
            return new DwzVo("300", "操作失败");
        }
        if (status.equals(ProductConstants.PRODUCT_AUDIT_REFUSE) && StringUtils.isEmpty(reason)) {
            logger.error("审核产品参数null");
            return new DwzVo("300", "操作失败");
        }
        if (status.equals(ProductConstants.PRODUCT_AUDIT_PASS) &&
                (( //StringUtils.isEmpty(vipType) ||
                        StringUtils.isEmpty(servicePeopleDocChatNum) || StringUtils.isEmpty(servicePeopleId) ||
                                StringUtils.isEmpty(servicePeopleImUserName) || StringUtils.isEmpty(servicePeopleCall)
                                || StringUtils.isEmpty(servicePeopleName) || realPrice == null)
                        //|| StringUtils.isEmpty(type) || StringUtils.isEmpty(subType) || StringUtils.isEmpty(productName)
                )) {
            logger.error("审核产品参数null");
            return new DwzVo("300", "操作失败");
        }
        DwzVo dwzVo;
        if (status.equals(ProductConstants.PRODUCT_AUDIT_PASS)) {
            memberProductService.auditProductPass(id, status, null,
                    servicePeopleDocChatNum, servicePeopleId, servicePeopleImUserName, servicePeopleCall, servicePeopleName, realPrice,
                    null, null, null);
            dwzVo = new DwzVo("200", "操作成功", "member_product_audit", "", "closeCurrent", "/member/product/audit/view");
        } else if (status.equals(ProductConstants.PRODUCT_AUDIT_REFUSE)) {
            memberProductService.auditProduct(id, status, reason);
            // todo 。关闭dialog 并刷新
            dwzVo = new DwzVo("200", "操作成功", "member_product_audit", "", "closeCurrent", "/member/product/audit/view");
        } else {
            dwzVo = new DwzVo("300", "操作失败");
        }
        return dwzVo;
    }

    /**
     * 返回审核页面
     *
     * @param id id
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("product:audit:all")
    @RequestMapping("/audit/dialog/view")
    @ResponseBody
    public ModelAndView productAuditView(@RequestParam(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("/memberProduct/auditView");
        if (StringUtils.isEmpty(id)) {
            logger.error("申请id为空，无法拒绝");
            return modelAndView;
        }
        modelAndView.addObject("id", id);
        ProductPeopleVo productPeopleVo = new ProductPeopleVo();
        productPeopleVo.setServicePeopleCall(ProductEnum.DEFAULT.getServicePeopleCall());
        productPeopleVo.setServicePeopleDocChatNum(ProductEnum.DEFAULT.getServicePeopleDocChatNum());
        productPeopleVo.setServicePeopleId(ProductEnum.DEFAULT.getServicePeopleId());
        productPeopleVo.setServicePeopleImUserName(ProductEnum.DEFAULT.getServicePeopleImUserName());
        productPeopleVo.setServicePeopleName(ProductEnum.DEFAULT.getServicePeopleName());

        modelAndView.addObject("deafult", productPeopleVo);
        // modelAndView.addObject("vipType", product_type);

        MemberProductBo memberProductBo = memberProductService.getMemberProductById(id);
        modelAndView.addObject("memberProductBo", BeanMapper.map(memberProductBo, MemberProductVo.class));

        // 存放一级类目列表
        // List<String> typeList = memberProductService.findTypeList();
        // modelAndView.addObject("typeList", typeList);
        //if (StringUtils.isNotEmpty(memberProductBo.getType())) {// 理论上不会为空
        //    // 存放二级类目列表
        //    List<String> subTypeList = memberProductService.findSubTypeList(memberProductBo.getType());
        //    modelAndView.addObject("subTypeList", subTypeList);
        //}
        return modelAndView;
    }

    /**
     * 返回 审核列表中点击修改后的页面
     *
     * @param id id
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("product:audit:all")
    @RequestMapping("/audit/update/view")
    @ResponseBody
    public ModelAndView productUpdateAuditView(@RequestParam(value = "id") String id) {
        //ModelAndView modelAndView = new ModelAndView("/memberProduct/auditUpdateView");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        ModelAndView modelAndView = new ModelAndView("/memberProduct/auditUpdate");
        if (StringUtils.isEmpty(id)) {
            logger.error("id为空，无法修改");
            return modelAndView;
        }
        // 产品会员专区
        modelAndView.addObject("vipType", product_type);
        // 产品可销售区域
        List<String> productSalesAreaList = productSalesAreaService.getProductSalesArea();
        modelAndView.addObject("productSalesAreaList", productSalesAreaList);
        // 咨询人员信息
        ProductPeopleVo productPeopleVo = new ProductPeopleVo();
        productPeopleVo.setServicePeopleCall(ProductEnum.DEFAULT.getServicePeopleCall());
        productPeopleVo.setServicePeopleDocChatNum(ProductEnum.DEFAULT.getServicePeopleDocChatNum());
        productPeopleVo.setServicePeopleId(ProductEnum.DEFAULT.getServicePeopleId());
        productPeopleVo.setServicePeopleImUserName(ProductEnum.DEFAULT.getServicePeopleImUserName());
        productPeopleVo.setServicePeopleName(ProductEnum.DEFAULT.getServicePeopleName());
        modelAndView.addObject("default", productPeopleVo);

        MemberProductBo memberProductBo = memberProductService.getMemberProductById(id);
        if (memberProductBo != null && StringUtils.isNotEmpty(memberProductBo.getThirdType())) {
            // 通过 三级类目ID 反查 二级类目ID 和 一级类目ID 并存放到BO
            List<ProductCatalogBo> parentCatalogList = productCatalogService.getAllparentCatalogListWithId(null, memberProductBo.getThirdType());
            if (CollectionUtils.isNotEmpty(parentCatalogList)) {
                parentCatalogList.stream().forEach((parentCatalog) -> {
                    if (memberProductBo.getThirdType().equals(parentCatalog.getId())) {//三级目录
                    } else if (parentCatalog.getParentId().equals("1")) {// 一级目录
                        memberProductBo.setFirstType(parentCatalog.getId());
                    } else {// 二级目录
                        memberProductBo.setSecondType(parentCatalog.getId());
                    }
                });
            }
        }
        // 根据会员专区类型 查询一级类目
        if (memberProductBo != null && StringUtils.isNotEmpty(memberProductBo.getVipType())) {
            List<ProductCatalogBo> productCatalogBoList = productCatalogService.getAllRootCatalogList(memberProductBo.getVipType());
            modelAndView.addObject("firstTypeList", productCatalogBoList);
        }
        // 根据一级类目ID查询二级类目集合
        if (memberProductBo != null && StringUtils.isNotEmpty(memberProductBo.getFirstType())) {
            List<ProductCatalogBo> productCatalogBoList = productCatalogService.getCatalogListWithParentId(null, memberProductBo.getFirstType());
            modelAndView.addObject("secondTypeList", productCatalogBoList);
        }
        // 根据二级类目 查询三级类目集合
        if (memberProductBo != null && StringUtils.isNotEmpty(memberProductBo.getSecondType())) {
            List<ProductCatalogBo> productCatalogBoList = productCatalogService.getCatalogListWithParentId(null, memberProductBo.getSecondType());
            modelAndView.addObject("thirdTypeList", productCatalogBoList);
        }
        // 将 产品可销售区域 集合转为字符串 逗号分隔
        if (memberProductBo != null && org.apache.commons.collections4.CollectionUtils.isNotEmpty(memberProductBo.getProductSalesArea())) {
            memberProductBo.setProductSalesAreaText(memberProductBo.getProductSalesArea().stream().collect(Collectors.joining(",")));
        }
        modelAndView.addObject("memberProductBo", BeanMapper.map(memberProductBo, MemberProductVo.class));
        return modelAndView;
    }

    /**
     * 审核员 修改数据
     *
     * @return DwzVo
     */
    @RequiresUser
    @RequiresPermissions("product:audit:all")
    @RequestMapping("/audit/update")
    @ResponseBody
    public DwzVo passUpdate(MemberProductBean memberProductBean) {
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new DwzVo("300", "操作失败");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new DwzVo("300", "操作失败");
        }
        if (memberProductBean == null) {
            logger.error("修改产品信息失败，参数null");
            return new DwzVo("300", "操作失败");
        }
        if (!CollectionUtils.isEmpty(memberProductBean.getProductDeletePics())) {
            try {
                // 七牛删除
                new Upload().delete(memberProductBean.getProductDeletePics());
            } catch (IOException e) {
                logger.error("七牛文件 删除失败");
                e.printStackTrace();
            }
        }
        if (!CollectionUtils.isEmpty(memberProductBean.getProductPics())) {
            // 补加前缀
            List<String> pic = new ArrayList<>();
            for (String s : memberProductBean.getProductPics()) {
                pic.add(qiniu + s);
            }
            memberProductBean.setProductPics(pic);
        }
        // 初始化pics
        //memberProductBean.setCreator(userBo.getUserName());
        //memberProductBean.setJobNumber(userBo.getJobNumber());
        //memberProductService.updateProduct(id, vipType,
        //        servicePeopleDocChatNum, servicePeopleId, servicePeopleImUserName, servicePeopleCall, servicePeopleName, realPrice, type, subType, productName);
        // memberProductService.saveMemberProduct(BeanMapper.map(memberProductBean, MemberProductBo.class));
        memberProductService.updateProduct(BeanMapper.map(memberProductBean, MemberProductBo.class));
        return new DwzVo("member_product_audit");
    }


    /**
     * 上下线操作
     *
     * @param id     id
     * @param online 标识
     * @return DwzVo
     */
    @RequiresUser
    @RequiresPermissions("product:audit:all")
    @RequestMapping("/online")
    @ResponseBody
    public DwzVo online(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "online") Integer online,
            @RequestParam(value = "onlineRemark", required = false) String onlineRemark // 允许为空
    ) {
        if (StringUtils.isEmpty(id) || online == null) {
            logger.error("上下线参数null");
            return new DwzVo("300", "操作失败");
        }
        DwzVo dwzVo;
        memberProductService.online(id, online, onlineRemark);
        dwzVo = new DwzVo("200", "操作成功", "member_product_audit", "", "closeCurrent", "/member/product/audit/view");
        return dwzVo;
    }


    /**
     * 删除产品
     *
     * @param id id
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("product:audit:all")
    @RequestMapping("/delete")
    @ResponseBody
    public DwzVo delete(
            @RequestParam(value = "id") String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("删除产品参数null");
            return new DwzVo("300", "操作失败");
        }
        DwzVo dwzVo;
        memberProductService.delete(id);
        dwzVo = new DwzVo("200", "操作成功", "member_product_audit", "", "", "/member/product/audit/view");
        return dwzVo;
    }

    /**
     * 上下线 弹窗
     *
     * @param id
     * @return
     */
    @RequiresUser
    @RequiresPermissions("product:audit:all")
    @RequestMapping("/online/dialog/view")
    @ResponseBody
    public ModelAndView productOnlineView(@RequestParam(value = "id") String id
            , @RequestParam(value = "online") Integer online) {
        ModelAndView modelAndView = new ModelAndView("/memberProduct/auditOnlineView");
        if (StringUtils.isEmpty(id) || online == null) {
            logger.error("申请id为空，无法上下线");
            return modelAndView;
        }
        modelAndView.addObject("id", id);
        modelAndView.addObject("online", online);
        return modelAndView;
    }

    /**
     * 获取时间戳
     *
     * @param dataString dataString
     * @return Long
     */
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
}
