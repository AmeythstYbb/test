package com.zlycare.web.boss_24.controller.product;

import com.google.common.collect.Lists;
import com.zlycare.web.boss_24.controller.bean.ProductCatalogBean;
import com.zlycare.web.boss_24.controller.vo.DwzVo;
import com.zlycare.web.boss_24.controller.vo.JsonVo;
import com.zlycare.web.boss_24.controller.vo.ProductCatalogVo;
import com.zlycare.web.boss_24.core.service.bo.ProductCatalogBo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.memberProduct.ProductCatalogService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : linguodong
 * Create : 2017/7/25
 * Update : 2017/7/25
 * Descriptions : 目录管理controller
 * 问题:
 * mongo库的目录表未在库中创建顶级目录的一条数据，需要自己在查询的list中自动添加。
 * 同时mzTree插件不允许出现-1_0的节点，修改数据库中根目录的父节点为1。
 * 同时treeTable插件中第一级目录的pid必须为0，将所有pid为1的数据替换为0
 * 查询一级目录，即为查询父id为1的数据
 */
@Controller
@RequestMapping("/product/catalog")
public class ProductCatalogController {
    private static final Logger logger = LoggerFactory.getLogger(ProductCatalogController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ProductCatalogService productCatalogService;

    public final static Map<String, String> product_type = new HashMap<>();
    // 高级会员专区
    private final static String advanced_type = "zlycare";
    // vip会员专区
    private final static String vip_type = "zlycare_vip";

    // 高级会员专区 顶级目录ID
    private final static String advanced_root_id = "593f5af5b275c9382c19b599";
    // vip会员专区 顶级目录ID
    private final static String vip_root_id = "593f5af5b275c9382c19b59c";

    static {
        product_type.put("zlycare", "高级会员专区");
        product_type.put("zlycare_vip", "vip会员专区");
    }

    // 高级会员专区 列表请求 /advanced/list/get product_catalog_advanced  product:catalog:advanced:all

    /**
     * 获取 高级会员专区 下 产品目录
     *
     * @return ModelAndView
     */
    //验证用户是否被记忆
    @RequiresUser
    @RequiresPermissions("product:catalog:advanced:all")
    @RequestMapping("/advanced/list/get")
    public ModelAndView getAdvancedProductCatalogList() {
        ModelAndView modelAndView = new ModelAndView("/productCatalog/advancedList");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        List<ProductCatalogBo> list = Lists.newArrayList();
        List<ProductCatalogBo> productCatalogBoList = productCatalogService.getAllCatalogList(advanced_type);
        List<ProductCatalogBo> newList = sortList(list, productCatalogBoList, "1");
        //newList.stream().forEach((ProductCatalogBo)->{
        //    ProductCatalogBo.setParentId("0");
        //});
        modelAndView.addObject("productCatalogAdvancedList", BeanMapper.mapList(newList, ProductCatalogVo.class));
        return modelAndView;
    }

    /**
     * 返回 高级会员专区 插入目录页面
     */
    @RequiresUser
    @RequiresPermissions("product:catalog:advanced:all")
    @RequestMapping(value = "/advanced/insert/view")
    @ResponseBody
    public ModelAndView insertAdvancedView() {
        ModelAndView modelAndView = new ModelAndView("/productCatalog/advancedInsert");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        return modelAndView;
    }

    /**
     * 高级会员专区 点击选择时-获取用户拥有的所有菜单列表
     *
     * @return ModelAndView
     */
    //验证用户是否被记忆
    @RequiresUser
    @RequiresPermissions("product:catalog:advanced:all")
    @RequestMapping(value = "/advanced/tree/get")
    @ResponseBody
    public ModelAndView getAdvancedCatalogListView(@RequestParam(value = "id", required = false) String id) {
        // 参数加当前ID
        ModelAndView modelAndView = new ModelAndView("/productCatalog/advancedTree");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        modelAndView = getCatalogListView(advanced_type, advanced_root_id, modelAndView, id);
        return modelAndView;
    }

    /**
     * 插入高级会员专区目录
     */
    @RequiresUser
    @RequiresPermissions("product:catalog:advanced:all")
    @RequestMapping(value = "/advanced/insert")
    @ResponseBody
    public DwzVo insertAdvancedCatalog(ProductCatalogBean productCatalogBean) {
        if (productCatalogBean == null) {
            logger.error("插入目录参数null");
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
        com.zlycare.web.boss_24.core.service.bean.ProductCatalogBean productCatalogServiceBean = new com.zlycare.web.boss_24.core.service.bean.ProductCatalogBean();
        productCatalogServiceBean.setParentId(productCatalogBean.getParent_name_hidden());
        productCatalogServiceBean.setName(productCatalogBean.getName());
        productCatalogServiceBean.setVipType(advanced_type);
        // menuServiceBean.setIcon(menuBean.getIcon());// 冗余字段
        productCatalogServiceBean.setSort(productCatalogBean.getSort());
        productCatalogServiceBean.setShow(productCatalogBean.getShow());
        productCatalogService.createProductCatalog(productCatalogServiceBean);
        // 返回刷新页面
        return new DwzVo("product_catalog_advanced");
    }

    /**
     * 删除 高级会员专区 目录
     */
    @RequiresUser
    @RequiresPermissions("product:catalog:advanced:all")
    @RequestMapping(value = "/advanced/delete")
    @ResponseBody
    public DwzVo deleteAdvancedCatalog(@RequestParam("id") String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("删除高级会员产品目录参数null");
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
        productCatalogService.deleteProductCatalog(id);
        return new DwzVo("200", "操作成功", "product_catalog_advanced", "", "", "/product/catalog/advanced/list/get");
    }

    /**
     * 返回 修改 高级会员专区 目录 页面
     */
    @RequiresUser
    @RequiresPermissions("product:catalog:advanced:all")
    @RequestMapping(value = "/advanced/update/view")
    @ResponseBody
    public ModelAndView updateAdvamcedCatalogView(@RequestParam("id") String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("参数 null");// 跳转到这里?
            return new ModelAndView("/productCatalog/advancedUpdate");
        }
        ModelAndView modelAndView = new ModelAndView("/productCatalog/advancedUpdate");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        ProductCatalogBo productCatalogBo = productCatalogService.getProductCatalog(id);
        if (productCatalogBo == null || productCatalogBo.getParentId() == null) {
            logger.error("参数 null");
            return new ModelAndView("/productCatalog/advancedUpdate");
        }
        String parentAdvancedId, parentAdvancedName;
        if (StringUtils.isEmpty(productCatalogBo.getParentId()) || productCatalogBo.getParentId().equals("1")) {
            parentAdvancedId = "1";
            parentAdvancedName = "顶级目录";
        } else {
            ProductCatalogBo productParentCatalogBo = productCatalogService.getProductCatalog(productCatalogBo.getParentId());
            if (productParentCatalogBo == null) {
                logger.error("参数 null");
                return new ModelAndView("/productCatalog/advancedUpdate");
            }
            parentAdvancedId = productParentCatalogBo.getId();
            parentAdvancedName = productParentCatalogBo.getName();
        }
        modelAndView.addObject("productCatalogBo", BeanMapper.map(productCatalogBo, ProductCatalogVo.class));
        modelAndView.addObject("parentAdvancedId", parentAdvancedId);
        modelAndView.addObject("parentAdvancedName", parentAdvancedName);
        return modelAndView;
    }

    /**
     * 修改 高级会员专区 目录
     *
     * @return DwzVo
     */
    @RequiresUser
    @RequiresPermissions("product:catalog:advanced:all")
    @RequestMapping(value = "/advanced/update")
    @ResponseBody
    public DwzVo updateAdvancedCatalog(
            @RequestParam(value = "id_name_hidden") String id,
            @RequestParam(value = "parent_name_hidden") String parentId,
            @RequestParam("name") String name,
            @RequestParam(value = "sort", required = false) Integer sort,
            @RequestParam("show") Integer show
    ) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(parentId) || StringUtils.isEmpty(name) || show == null) {
            logger.error("修改 高级会员专区 目录 参数null");
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
        productCatalogService.updateProductCatalog(id, parentId, name, sort, show);
        return new DwzVo("product_catalog_advanced");
    }

    // vip会员专区 列表请求 /vip/list/get    product_catalog_vip    product:catalog:vip:all

    /**
     * 获取 VIP会员专区 下 产品目录
     *
     * @return ModelAndView
     */
    //验证用户是否被记忆
    @RequiresUser
    @RequiresPermissions("product:catalog:vip:all")
    @RequestMapping("/vip/list/get")
    public ModelAndView getVipProductCatalogList() {
        ModelAndView modelAndView = new ModelAndView("/productCatalog/vipList");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        List<ProductCatalogBo> list = Lists.newArrayList();
        List<ProductCatalogBo> productCatalogBoList = productCatalogService.getAllCatalogList(vip_type);
        List<ProductCatalogBo> newList = sortList(list, productCatalogBoList, "1");
        modelAndView.addObject("productCatalogVipList", BeanMapper.mapList(newList, ProductCatalogVo.class));
        return modelAndView;
    }

    /**
     * 返回 VIP会员专区 插入目录页面
     */
    @RequiresUser
    @RequiresPermissions("product:catalog:vip:all")
    @RequestMapping(value = "/vip/insert/view")
    @ResponseBody
    public ModelAndView insertVipView() {
        ModelAndView modelAndView = new ModelAndView("/productCatalog/vipInsert");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        return modelAndView;
    }

    /**
     * VIP会员专区 点击选择时-获取用户拥有的所有菜单列表
     *
     * @return ModelAndView
     */
    //验证用户是否被记忆
    @RequiresUser
    @RequiresPermissions("product:catalog:vip:all")
    @RequestMapping(value = "/vip/tree/get")
    @ResponseBody
    public ModelAndView getVipCatalogListView(@RequestParam(value = "id", required = false) String id) {
        // 参数加当前ID
        ModelAndView modelAndView = new ModelAndView("/productCatalog/vipTree");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        modelAndView = getCatalogListView(vip_type, vip_root_id, modelAndView, id);
        return modelAndView;
    }

    /**
     * 插入VIP会员专区目录
     */
    @RequiresUser
    @RequiresPermissions("product:catalog:vip:all")
    @RequestMapping(value = "/vip/insert")
    @ResponseBody
    public DwzVo insertVipCatalog(ProductCatalogBean productCatalogBean) {
        if (productCatalogBean == null) {
            logger.error("插入目录参数null");
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
        com.zlycare.web.boss_24.core.service.bean.ProductCatalogBean productCatalogServiceBean = new com.zlycare.web.boss_24.core.service.bean.ProductCatalogBean();
        productCatalogServiceBean.setParentId(productCatalogBean.getParent_name_hidden());
        productCatalogServiceBean.setName(productCatalogBean.getName());
        productCatalogServiceBean.setVipType(vip_type);
        // menuServiceBean.setIcon(menuBean.getIcon());// 冗余字段
        productCatalogServiceBean.setSort(productCatalogBean.getSort());
        productCatalogServiceBean.setShow(productCatalogBean.getShow());
        productCatalogService.createProductCatalog(productCatalogServiceBean);
        // 返回刷新页面
        return new DwzVo("product_catalog_vip");
    }

    /**
     * 删除 VIP会员专区 目录
     */
    @RequiresUser
    @RequiresPermissions("product:catalog:vip:all")
    @RequestMapping(value = "/vip/delete")
    @ResponseBody
    public DwzVo deleteVipCatalog(@RequestParam("id") String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("删除产品目录参数null");
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
        productCatalogService.deleteProductCatalog(id);
        return new DwzVo("200", "操作成功", "product_catalog_vip", "", "", "/product/catalog/vip/list/get");
    }

    /**
     * 返回修改 VIP会员专区 目录 页面
     */
    @RequiresUser
    @RequiresPermissions("product:catalog:vip:all")
    @RequestMapping(value = "/vip/update/view")
    @ResponseBody
    public ModelAndView updateVipCatalogView(@RequestParam("id") String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("参数 null");// 跳转到这里?
            return new ModelAndView("/productCatalog/vipUpdate");
        }
        ModelAndView modelAndView = new ModelAndView("/productCatalog/vipUpdate");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        ProductCatalogBo productCatalogBo = productCatalogService.getProductCatalog(id);
        if (productCatalogBo == null || productCatalogBo.getParentId() == null) {
            logger.error("参数 null");
            return new ModelAndView("/productCatalog/vipUpdate");
        }
        String parentVipId, parentVipName;
        if (StringUtils.isEmpty(productCatalogBo.getParentId()) || productCatalogBo.getParentId().equals("1")) {
            parentVipId = "1";
            parentVipName = "顶级目录";
        } else {
            ProductCatalogBo productParentCatalogBo = productCatalogService.getProductCatalog(productCatalogBo.getParentId());
            if (productParentCatalogBo == null) {
                logger.error("参数 null");
                return new ModelAndView("/productCatalog/vipUpdate");
            }
            parentVipId = productParentCatalogBo.getId();
            parentVipName = productParentCatalogBo.getName();
        }
        modelAndView.addObject("productCatalogBo", BeanMapper.map(productCatalogBo, ProductCatalogVo.class));
        modelAndView.addObject("parentVipId", parentVipId);
        modelAndView.addObject("parentVipName", parentVipName);
        return modelAndView;
    }

    /**
     * 修改 VIP会员专区 目录
     *
     * @return DwzVo
     */
    @RequiresUser
    @RequiresPermissions("product:catalog:vip:all")
    @RequestMapping(value = "/vip/update")
    @ResponseBody
    public DwzVo updateVipCatalog(
            @RequestParam(value = "id_name_hidden") String id,
            @RequestParam(value = "parent_name_hidden") String parentId,
            @RequestParam("name") String name,
            @RequestParam(value = "sort", required = false) Integer sort,
            @RequestParam("show") Integer show
    ) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(parentId) || StringUtils.isEmpty(name) || show == null) {
            logger.error("修改VIP会员专区目录 参数null");
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
        productCatalogService.updateProductCatalog(id, parentId, name, sort, show);
        return new DwzVo("product_catalog_vip");
    }

    // ******************************************************* 内部工具 *******************************************************

    /**
     * 工具 封装list
     *
     * @param list       list
     * @param sourceList sourceList
     * @param parentId   parentId
     * @return List<ProductCatalogBo>
     */
    public static List<ProductCatalogBo> sortList(List<ProductCatalogBo> list, List<ProductCatalogBo> sourceList, String parentId) {
        for (int i = 0; i < sourceList.size(); i++) {
            ProductCatalogBo e = sourceList.get(i);
            if (e.getParentId() != null
                    && e.getParentId().equals(parentId)) {
                list.add(e);
                // 判断是否还有子节点, 有则继续获取子节点
                for (int j = 0; j < sourceList.size(); j++) {
                    ProductCatalogBo child = sourceList.get(j);
                    if (child.getParentId() != null
                            && child.getParentId().equals(e.getId())) {
                        list = sortList(list, sourceList, e.getId());
                        break;
                    }
                }
            }

        }
        return list;
    }

    /**
     * @param type         会员类型
     * @param typeRootId   会员根节点默认id
     * @param modelAndView 页面模版
     * @param id           父级id
     * @return ModelAndView
     */
    ModelAndView getCatalogListView(String type, String typeRootId, ModelAndView modelAndView, String id) {
        if (StringUtils.isEmpty(type) || modelAndView == null || StringUtils.isEmpty(typeRootId)) {
            logger.error("参数为null");
            return new ModelAndView("redirect:/user/login");
        }

        List<ProductCatalogBo> productCatalogBoList = productCatalogService.getAllCatalogList(type);
        List<JsonVo> jsonVoList = new ArrayList<>();
        // 插入根目录数据
        ProductCatalogBo productCatalogRootBo = new ProductCatalogBo();
        productCatalogRootBo.setParentId("1");
        productCatalogRootBo.setParentIds("1,");
        productCatalogRootBo.setName("顶级目录");
        productCatalogRootBo.setId(typeRootId);// 此处Id仅为判断是否是根节点,若是，真正返回的Id值为1，并被下级目录当作父ID
        productCatalogRootBo.setVipType(type);
        productCatalogBoList.add(0, productCatalogRootBo);//根目录节点必须在最前
        for (ProductCatalogBo productCatalogBo : productCatalogBoList) {
            JsonVo jsonVo = new JsonVo();
            //jsonVo.setName(((productCatalogBo.getParentId() != null && !productCatalogBo.getParentId().equals("0")) ? productCatalogBo.getParentId() : "-1") + "_" + productCatalogBo.getId());
            String name;//点击获取节点值时，获取的值是x_y中的y值，所以根节点的值获取的是1，并不是advanced_root_id。
            if (productCatalogBo.getParentId() != null && !productCatalogBo.getId().equals(typeRootId)) {// 非根目录
                name = productCatalogBo.getParentId() + "_" + productCatalogBo.getId();
            } else {
                name = "-1_1";
            }
            jsonVo.setName(name);
            //jsonVo.setValue("text: " + productCatalogBo.getName());
            jsonVo.setValue("text: " + StringEscapeUtils.escapeEcmaScript(productCatalogBo.getName()));
            jsonVoList.add(jsonVo);
        }
        modelAndView.addObject("data", jsonVoList);
        String parentIds = "";
        if (StringUtils.isNotEmpty(id)) {
            ProductCatalogBo productCatalogBo = productCatalogService.getProductCatalog(id);
            if (productCatalogBo == null || StringUtils.isEmpty(productCatalogBo.getParentIds())) {
                logger.error("目录对象null,id is:" + id);
                return modelAndView;
            }
            if (productCatalogBo.getParentId().equals("1")) {
                parentIds = "-1_1";
            } else {
                parentIds = productCatalogBo.getParentIds().replaceFirst("1", "-1_1").replaceAll(",", "_");// 父编号层次，用于默认选中节点
            }
        }
        // mzTree所需其他参数
        modelAndView.addObject("extId", ""); // 排除的编号ID
        modelAndView.addObject("parentIds", parentIds);// 父编号层次，用于默认选中节点
        modelAndView.addObject("checked", ""); // 是否可复选
        modelAndView.addObject("checkedIds", ""); // 如果是可复选，则指定默认选中的Id
        modelAndView.addObject("module", "");    // 过滤栏目模型（仅针对CMS的Category树）
        return modelAndView;
    }
}
