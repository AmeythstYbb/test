package com.zlycare.web.boss_24.controller.moments;

import com.zlycare.web.boss_24.constants.ProjectConfig;
import com.zlycare.web.boss_24.constants.moments.MomentsTemporaryConstants;
import com.zlycare.web.boss_24.controller.bean.MomentsTemporaryBean;
import com.zlycare.web.boss_24.controller.vo.DwzVo;
import com.zlycare.web.boss_24.controller.vo.MomentsTemporaryBasicVo;
import com.zlycare.web.boss_24.core.service.bo.*;
import com.zlycare.web.boss_24.core.service.moments.MomentsTemporaryService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.upload.Upload;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.excel.ImportExcelUtil;
import com.zlycare.web.boss_24.utils.http.HttpPostUrl;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.util.IOUtils;
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
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Author : linguodong
 * Create : 2017/8/4
 * Update : 2017/8/4
 * Descriptions : 动态批量上传
 */

@Controller
@RequestMapping("/moments")
public class MomentsBatchUploadController {
    private static final Logger logger = LoggerFactory.getLogger(MomentsBatchUploadController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private MomentsTemporaryService momentsTemporaryService;
    @Autowired
    private ProjectConfig projectConfig;

    private final static String excel2003L = ".xls"; // 2003- 版本的excel
    private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

    // 动态批量上传 /moments/batch/upload/view moments_batch_upload moments:batch:upload

    /**
     * 批量上传动态页面
     *
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("moments:batch:upload")
    @RequestMapping("/batch/upload/view")
    @ResponseBody
    public ModelAndView momentsBatchUploadView(@RequestParam(value = "batch", required = false) Double batch,
                                               @RequestParam(value = "flag", required = false) String flag) {
        ModelAndView modelAndView = new ModelAndView("/moments/batchUpload");
        /*登录信息校验*/
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        Integer commitFlag = 0;// 默认不可提交
        List<MomentsTemporaryBasicVo> momentsTemporaryVoList = new ArrayList<>();

        if (null != batch && StringUtils.isEmpty(flag)) {// 返回上传解析的列表
            // 根据批次ID查询数据列表,service做数据校验。
            MomentsTemporaryBo momentsTemporarBo = momentsTemporaryService.getAllMomentsTemporaryByBatchId(batch);
            if (momentsTemporarBo == null) {
                logger.error("查询失败,批次ID为:" + batch);
                return new ModelAndView("redirect:/user/login");
            }
            List<MomentsTemporaryBasicBo> momentsTemporaryBoList = momentsTemporarBo.getMomentsTemporaryBasicBoList();
            momentsTemporaryVoList = BeanMapper.mapList(momentsTemporaryBoList, MomentsTemporaryBasicVo.class);
            commitFlag = momentsTemporarBo.getCommitFlag();
            modelAndView.addObject("batch", momentsTemporarBo.getBatch());
        } else if (null != batch && StringUtils.isNotEmpty((flag))) {
            // 查询上传失败列表
            List<MomentsTemporaryBasicBo> failedList =
                    momentsTemporaryService.getMomentsTemporaryListByBatchAndUploadFlag(batch, MomentsTemporaryConstants.COMMIT_FAILED);
            if (CollectionUtils.isEmpty(failedList)) {//成功,则回到上传页面
                momentsTemporaryVoList = initDefaultList(2);
            } else {// 失败,展示失败列表
                momentsTemporaryVoList = BeanMapper.mapList(failedList, MomentsTemporaryBasicVo.class);
            }
        } else {// 返回默认显示列表,不需要ID、批次、上传标识字段
            momentsTemporaryVoList = initDefaultList(2);
        }
        modelAndView.addObject("list", momentsTemporaryVoList);
        modelAndView.addObject("commitFlag", commitFlag);
        return modelAndView;
    }


    /**
     * 上传EXCEL文件
     *
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("moments:batch:upload")
    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public DwzVo auditUpload(@RequestParam(value = "excel") CommonsMultipartFile multipartfile) {
        ModelAndView modelAndView = new ModelAndView("/moments/batchUpload");
        if (multipartfile.isEmpty()) {
            logger.error("EXCEL文件为空");
            // return modelAndView;
            return new DwzVo("300", "操作失败");
        }
        String fileName = multipartfile.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        /*判断文件格式后缀*/
        if (StringUtils.isEmpty(fileType) || (!fileType.equals(excel2003L) && !fileType.equals(excel2007U))) {
            logger.error("文件格式错误");
//            return modelAndView;
            return new DwzVo("300", "操作失败");
        }
        // 同批次时间
        Double time = ((Long) new Date().getTime()).doubleValue();
        try {
            InputStream in = null;
            List<List<Object>> listob = null;
            in = multipartfile.getInputStream();
            listob = new ImportExcelUtil().getBankListByExcel(in, fileName);
            if (CollectionUtils.isEmpty(listob)) {
                return new DwzVo("300", "格式错误");// 数据为空 or 数据大于100
            }
            // 该处可调用service相应方法进行数据保存到数据库中，现只对数据输出;已保存
            List<MomentsTemporaryBasicBo> momentsTemporaryBoList = new ArrayList<>();
            for (int i = 0; i < listob.size(); i++) {
                List<Object> lo = listob.get(i);
                /*
                InfoVo vo = new InfoVo();
                vo.setCode(String.valueOf(lo.get(0)));
                vo.setName(String.valueOf(lo.get(1)));
                vo.setDate(String.valueOf(lo.get(2)));
                vo.setMoney(String.valueOf(lo.get(3)));
                */
                /*
                System.out.println("打印信息-->" +
                        " 序号：" + String.valueOf(lo.get(0)) +
                        " 热线号: " + String.valueOf(lo.get(1)) +
                        " 动态内容: " + String.valueOf(lo.get(2)) +
                        " 图片: " + String.valueOf(lo.get(3)) +
                        " 话题: " + String.valueOf(lo.get(4)) +
                        " 标签: " + String.valueOf(lo.get(5)) +
                        " 经度: " + String.valueOf(lo.get(6)) +
                        " 维度: " + String.valueOf(lo.get(7)));
                */
                MomentsTemporaryBasicBo momentsTemporaryBo = new MomentsTemporaryBasicBo();
                // 设置序号
                momentsTemporaryBo.setExcelId(Integer.parseInt(String.valueOf((lo.get(0)))));
                // 设置热线号
                momentsTemporaryBo.setDocChatNum(String.valueOf(lo.get(1)));
                // 设置动态内容
                momentsTemporaryBo.setContent(String.valueOf(lo.get(2)));
                // 解析图片字符串为列表
                List<String> pic_list = initList(String.valueOf(lo.get(3)));
                momentsTemporaryBo.setPics(pic_list);
                // 解析话题字符串为列表
                List<String> topic_list = initList(String.valueOf(lo.get(4)));
                momentsTemporaryBo.setTopics(topic_list);
                // 解析标签字符串为列表
                List<String> tag_list = initList(String.valueOf(lo.get(5)));
                momentsTemporaryBo.setTags(tag_list);
                // 设置经纬度
                //Object o = lo.get(6);
                momentsTemporaryBo.setLon(StringUtils.isNotEmpty(String.valueOf(lo.get(6))) ? (Double) (lo.get(6)) : null);
                momentsTemporaryBo.setLat(StringUtils.isNotEmpty(String.valueOf(lo.get(7))) ? (Double) (lo.get(7)) : null);
                /*批次和上传状态在外层设置,减少底层的再次循环,降低时间*/
                // 初始化上传状态为:未上传
                momentsTemporaryBo.setUploadFlag(MomentsTemporaryConstants.NOT_COMMIT);
                // 同一个时间戳,标识为同批次
                momentsTemporaryBo.setBatch(time);

                momentsTemporaryBoList.add(momentsTemporaryBo);
            }
            // 解析完成 批量存入临时表
            momentsTemporaryService.createBatch(momentsTemporaryBoList);
        } catch (Exception e) {
            // e.printStackTrace();
            logger.error(e.getMessage());
            return new DwzVo("300", "格式错误");
        }
        // redirect 重定向页面数据获取接口,传递批次ID,将临时表中此批次的数据校验封装后展示到页面
        // 页面无法再次返回刷新 ok ,页面判断全部字段(ok),图片校验问题(ok),返回列表时返回是否有错状态(ok),
        // 修改单条(ok)、页面展示单条数据返回(ok),字段校验(ok),提交(ok),保存(ok),刷新(ok),提交上传(ok)
        //
        // return modelAndView;
        // 重定向跳转,带参数
        // return new ModelAndView("redirect:/moments/batch/upload/view?batch=" + time);
        // 重定向无法再次刷新页面,
        return new DwzVo("200", "操作成功", "", "", "forward", "/moments/batch/upload/view?batch=" + time);
    }


    /**
     * [返回]临时列表中点击修改后的页面
     *
     * @param id id
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("moments:batch:upload")
    @RequestMapping("/batch/upload/update/view")
    @ResponseBody
    public ModelAndView momentsBatchUploadUpdateView(@RequestParam(value = "id") String id) {
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        ModelAndView modelAndView = new ModelAndView("/moments/batchUpdate");
        if (StringUtils.isEmpty(id)) {
            logger.error("id为空，无法修改");
            return modelAndView;
        }
        // 根据id查询数据对象
        MomentsTemporaryBasicBo momentsTemporaryBasicBo = momentsTemporaryService.getMomentsTemporaryById(id);
        // 将 图片、标签、话题 集合转为字符串,逗号分隔;提交时字符串转换为列表存储
        if (momentsTemporaryBasicBo != null && CollectionUtils.isNotEmpty(momentsTemporaryBasicBo.getPics())) {
            momentsTemporaryBasicBo.setPicsValue(momentsTemporaryBasicBo.getPics().stream().collect(Collectors.joining(",")));
        }
        if (momentsTemporaryBasicBo != null && CollectionUtils.isNotEmpty(momentsTemporaryBasicBo.getTopics())) {
            momentsTemporaryBasicBo.setTopicsValue(momentsTemporaryBasicBo.getTopics().stream().collect(Collectors.joining(",")));
        }
        if (momentsTemporaryBasicBo != null && CollectionUtils.isNotEmpty(momentsTemporaryBasicBo.getTags())) {
            momentsTemporaryBasicBo.setTagsValue(momentsTemporaryBasicBo.getTags().stream().collect(Collectors.joining(",")));
        }
        // 返回数据对象
        modelAndView.addObject("momentsTemporaryBasicBo", BeanMapper.map(momentsTemporaryBasicBo, MomentsTemporaryBasicVo.class));
        return modelAndView;
    }


    /**
     * 修改单条动态数据
     *
     * @return DwzVo
     */
    @RequiresUser
    @RequiresPermissions("moments:batch:upload")
    @RequestMapping("/batch/upload/update")
    @ResponseBody
    public DwzVo momentsBatchUploadUpdate(MomentsTemporaryBean momentsTemporaryBean) {
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new DwzVo("300", "操作失败");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new DwzVo("300", "操作失败");
        }
        if (momentsTemporaryBean == null || StringUtils.isEmpty(momentsTemporaryBean.getId()) || momentsTemporaryBean.getExcelId() == null ||
                momentsTemporaryBean.getBatch() == null || StringUtils.isEmpty(momentsTemporaryBean.getContent()) ||
                StringUtils.isEmpty(momentsTemporaryBean.getDocChatNum()) || momentsTemporaryBean.getLat() == null ||
                momentsTemporaryBean.getLon() == null || momentsTemporaryBean.getUploadFlag() == null) {
            logger.error("修改动态信息失败，参数null");
            return new DwzVo("300", "操作失败");
        }

        momentsTemporaryService.updateMomentsTemporary(BeanMapper.map(momentsTemporaryBean, MomentsTemporaryBasicBo.class));

        return new DwzVo("200", "操作成功", "moments_batch_upload", "", "closeCurrent", "/moments/batch/upload/view?batch=" + momentsTemporaryBean.getBatch());

        //return new DwzVo("200", "操作成功", "", "", "forward", "/moments/batch/upload/view?batch=" + momentsTemporaryBean.getBatch());
    }


    /**
     * 接收确认提交请求,对临时表数据进行图片下载、七牛上传、node调用处理
     *
     * @param batch 批次ID
     * @return ModelAndView
     */
    @RequiresUser
    @RequiresPermissions("moments:batch:upload")
    @RequestMapping("/batch/upload/commit")
    @ResponseBody
    public ModelAndView commitBatchMoments(@RequestParam(value = "batch") Double batch) {
        ModelAndView modelAndView = new ModelAndView("/moments/commitResult");
        String userJobNumber = UserUtils.getUserJobNumber();
        if (StringUtils.isEmpty(userJobNumber)) {
            return new ModelAndView("redirect:/user/login");
        }
        UserBo userBo = userService.getUserByJobNumber(userJobNumber);
        if (userBo == null) {
            return new ModelAndView("redirect:/user/login");
        }
        if (batch == null) {
            logger.error("batch为空，无法上传");
            return modelAndView;
        }
        // 根据batch查询当前数据列表
        List<MomentsTemporaryBasicBo> momentsTemporaryBasicBoList = momentsTemporaryService.getAllMomentsTemporaryListByBatchId(batch);
        if (CollectionUtils.isEmpty(momentsTemporaryBasicBoList)) {
            logger.error("查询失败,批次ID为:" + batch);
            return new ModelAndView("redirect:/user/login");
        }
        for (MomentsTemporaryBasicBo momentsTemporaryBasicBo : momentsTemporaryBasicBoList) {
            if (momentsTemporaryBasicBo == null || StringUtils.isEmpty(momentsTemporaryBasicBo.getId())) {
                // 数据为空 或者 ID不存在,跳过。
                continue;
            }
            if (StringUtils.isEmpty(momentsTemporaryBasicBo.getDocChatNum()) || StringUtils.isEmpty(momentsTemporaryBasicBo.getContent()) ||
                    momentsTemporaryBasicBo.getUploadFlag() == null) {
                // 热线号、上传状态或者内容有问题,将其状态置为失败
                momentsTemporaryBasicBo.setUploadFlag(MomentsTemporaryConstants.COMMIT_FAILED);
                momentsTemporaryService.updateMomentsTemporary(momentsTemporaryBasicBo);
                continue;
            }
            Integer uploadFlag = momentsTemporaryBasicBo.getUploadFlag();
            // 处理当前批次中未上传的数据
            if (MomentsTemporaryConstants.NOT_COMMIT.intValue() == uploadFlag.intValue()) {
                logger.error(batch + "_" + momentsTemporaryBasicBo.getId() + " 开始上传...");
                try {
                    // 图片
                    List<String> node_pics = new ArrayList<>();
                    List<String> pics = momentsTemporaryBasicBo.getPics();
                    if (CollectionUtils.isNotEmpty(pics)) {
                        for (String pic : pics) {
                            // 链接,下载图片并上传到七牛,获取返回的文件名
                            if (pic.contains("http://") || pic.contains("https://")) {
                                URL url = new URL(pic);
                                InputStream ism = url.openStream();
                                String name = new Upload().upload(IOUtils.toByteArray(ism));
                                node_pics.add(name);
                            } else {// 直接添加
                                node_pics.add(pic);
                            }
                        }
                    }
                    // 热线号
                    String docChatNum = momentsTemporaryBasicBo.getDocChatNum();
                    // 内容
                    String content = momentsTemporaryBasicBo.getContent();
                    // 经纬度
                    String location = momentsTemporaryBasicBo.getLat() + "," + momentsTemporaryBasicBo.getLon().toString();
                    // 话题
                    List<String> topics = momentsTemporaryBasicBo.getTopics();
                    // 标签
                    List<String> tags = momentsTemporaryBasicBo.getTags();
                    // 调用node接口,正常返回,上传状态修改为1,失败修改为2;
                    toNode(projectConfig.getNodeMomentsUrl(), docChatNum, content, node_pics, topics, location, tags);
                    // 修改当前数据的上传状态为正常
                    momentsTemporaryBasicBo.setUploadFlag(MomentsTemporaryConstants.COMMIT_SUCCESS);
                    momentsTemporaryService.updateMomentsTemporary(momentsTemporaryBasicBo);
                } catch (IOException e) {
                    logger.error("调用七牛接口异常,批次ID为:" + momentsTemporaryBasicBo.getId());
                    // 修改上传状态为失败
                    momentsTemporaryBasicBo.setUploadFlag(MomentsTemporaryConstants.COMMIT_FAILED);
                    momentsTemporaryService.updateMomentsTemporary(momentsTemporaryBasicBo);
                }
            }
        }
        // 查询成功个数;
        Integer successCount = momentsTemporaryService.count(batch, MomentsTemporaryConstants.COMMIT_SUCCESS);
        // 查询未上传个数
        Integer notCount = momentsTemporaryService.count(batch, MomentsTemporaryConstants.NOT_COMMIT);
        // 查询上传失败列表
        List<MomentsTemporaryBasicBo> failedList =
                momentsTemporaryService.getMomentsTemporaryListByBatchAndUploadFlag(batch, MomentsTemporaryConstants.COMMIT_FAILED);
        if ((notCount != null && notCount == 0) && CollectionUtils.isEmpty(failedList)) {
            // 或者 失败个数和列表,返回到页面
            modelAndView.addObject("flag", "1");
        } else {
            modelAndView.addObject("flag", "0");
            modelAndView.addObject("successCount", successCount);
            modelAndView.addObject("notCount", notCount);
            modelAndView.addObject("filedCount", CollectionUtils.isEmpty(failedList) ? 0 : failedList.size());
            modelAndView.addObject("failedList", failedList);
        }
        return modelAndView;
    }

    /**
     * 调用node接口
     *
     * @param dcoChatNum dcoChatNum
     * @param content    content
     * @param pics       pics
     * @param topics     topics
     * @param location   location
     * @param tags       tags
     */
    private static void toNode(String url, String dcoChatNum, String content, List<String> pics, List<String> topics,
                               String location, List<String> tags) {
        Map<String, String> mapParam = new HashMap<String, String>();
        // 封装参数
        mapParam.put("account", dcoChatNum);
        mapParam.put("content", content);
        if (CollectionUtils.isNotEmpty(pics)) {
            mapParam.put("pics", pics.stream().collect(Collectors.joining("&pics=")));
        }
        if (StringUtils.isNotEmpty(location)) {
            mapParam.put("location", location);
        }
        if (CollectionUtils.isNotEmpty(tags)) {
            mapParam.put("tags", tags.stream().collect(Collectors.joining("&tags=")));
        }
        if (CollectionUtils.isNotEmpty(topics)) {
            mapParam.put("topics", topics.stream().collect(Collectors.joining("&topics=")));
        }
        // 调用接口
        HttpPostUrl.sendPost(url, mapParam);
    }


    /**
     * 字符串根据逗号解析为列表
     *
     * @param s 字符串
     * @return 列表
     */
    private static List<String> initList(String s) {
        if (StringUtils.isEmpty(s)) {
            logger.error("字符串转列表失败");
            return null;
        }
        List<String> init_list = new ArrayList<>();
        if (StringUtils.isNotEmpty(s)) {
            String[] init_string = s.split(",");
            List<String> initList = Arrays.asList(init_string);
            for (String str : initList) {
                init_list.add(str);
            }
        }
        return init_list;
    }


    /**
     * 返回默认展示对象列表,不需要ID、批次、上传标识字段
     *
     * @return 列表
     */
    private static List<MomentsTemporaryBasicVo> initDefaultList(Integer row) {
        if (row == null) {
            row = 10;
        }
        List<MomentsTemporaryBasicVo> momentsTemporaryVoList = new ArrayList<>();
        for (int i = 1; i <= row; i++) {
            MomentsTemporaryBasicVo momentsTemporaryVo = new MomentsTemporaryBasicVo();
            momentsTemporaryVo.setContent("动态内容动态内容动态内容动态内容");
            momentsTemporaryVo.setDocChatNum("805001245");
            momentsTemporaryVo.setExcelId(i);
            momentsTemporaryVo.setLat(27.689721D);
            momentsTemporaryVo.setLon(111.435623D);
            momentsTemporaryVo.setPicsValue("805001409_20170801_1.jpg");
            momentsTemporaryVo.setTagsValue("美食");
            momentsTemporaryVo.setTopicsValue("美食活动");
            momentsTemporaryVoList.add(momentsTemporaryVo);
        }
        return momentsTemporaryVoList;
    }
}
