package com.zlycare.web.boss_24.core.serviceImpl.moments;

import com.zlycare.web.boss_24.core.mongo.moments.MomentsTemporaryMongo;
import com.zlycare.web.boss_24.core.mongo.po.MomentsTemporary;
import com.zlycare.web.boss_24.core.service.bo.MomentsTemporaryBasicBo;
import com.zlycare.web.boss_24.core.service.bo.MomentsTemporaryBasicValidateBo;
import com.zlycare.web.boss_24.core.service.bo.MomentsTemporaryBo;
import com.zlycare.web.boss_24.core.service.moments.MomentsTemporaryService;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Author : linguodong
 * Create : 2017/9/29
 * Update : 2017/9/29
 * Descriptions :
 */
@Service
public class MomentsTemporaryServiceImpl implements MomentsTemporaryService {
    private static Logger logger = LoggerFactory.getLogger(MomentsTemporaryServiceImpl.class);

    @Autowired
    private MomentsTemporaryMongo momentsTemporaryMongo;

    /**
     * 批量插入
     *
     * @param momentsTemporaryBoList momentsTemporaryBoList
     * @return boolean
     */
    @Override
    public boolean createBatch(List<MomentsTemporaryBasicBo> momentsTemporaryBoList) {
        if (CollectionUtils.isEmpty(momentsTemporaryBoList)) {
            logger.error("批次插入失败,列表数据为空");
            return false;
        }
        momentsTemporaryMongo.createBatch(BeanMapper.mapList(momentsTemporaryBoList, MomentsTemporary.class));
        return true;
    }

    /**
     * 根据批次ID查询对应数据列表
     *
     * @param batch batch
     * @return list
     */
    @Override
    public MomentsTemporaryBo getAllMomentsTemporaryByBatchId(Double batch) {
        if (batch == null) {
            logger.error("获取列表信息为空，批次为空");
            return null;
        }
        Integer commitFlag = 1;//默认提交值
        List<MomentsTemporary> momentsTemporaryList = momentsTemporaryMongo.getAllMomentsTemporaryByBatchId(batch);
        if (CollectionUtils.isEmpty(momentsTemporaryList)) {
            logger.debug("获取列表信息为空，数据为null");
            return null;
        }
        List<MomentsTemporaryBasicBo> momentsTemporaryBoList = BeanMapper.mapList(momentsTemporaryList, MomentsTemporaryBasicBo.class);
        List<MomentsTemporaryBasicBo> newMomentsTemporaryBasicBoList = new ArrayList<>();
        // 数据校验
        for (MomentsTemporaryBasicBo momentsTemporaryBo : momentsTemporaryBoList) {
            MomentsTemporaryBasicValidateBo momentsTemporaryBasicValidateBo = validateMomentsTemporaryBasicBo(momentsTemporaryBo);
            if (momentsTemporaryBasicValidateBo == null || momentsTemporaryBasicValidateBo.getMomentsTemporaryBasicBo() == null) {
                logger.error("校验后数据丢失,跳过");
                continue;
            }
            newMomentsTemporaryBasicBoList.add(momentsTemporaryBasicValidateBo.getMomentsTemporaryBasicBo());
            if (momentsTemporaryBasicValidateBo.getCommitFlag() != null && momentsTemporaryBasicValidateBo.getCommitFlag() == 0) {
                commitFlag = 0;// 有一处错误则不可提交, 返回1并不重新赋值
            }
        }
        // 封装数据对象
        MomentsTemporaryBo momentsTemporaryBo = new MomentsTemporaryBo();
        momentsTemporaryBo.setMomentsTemporaryBasicBoList(newMomentsTemporaryBasicBoList);
        momentsTemporaryBo.setBatch(batch);
        momentsTemporaryBo.setCommitFlag(commitFlag);
        // 返回数据对象
        return momentsTemporaryBo;
        // 修改接口返回参数,封装对象,为标识符[是否错误]+列表 ok
    }

    /**
     * 根据批次ID查询数据,含列表、批次ID、是否可提交信息,不校验,不返回封装对象。查询列表
     *
     * @param batch batch
     * @return list
     */
    @Override
    public List<MomentsTemporaryBasicBo> getAllMomentsTemporaryListByBatchId(Double batch) {
        if (batch == null) {
            logger.error("获取列表信息为空，批次为空");
            return null;
        }
        List<MomentsTemporary> momentsTemporaryList = momentsTemporaryMongo.getAllMomentsTemporaryByBatchId(batch);
        if (CollectionUtils.isEmpty(momentsTemporaryList)) {
            logger.debug("获取列表信息为空，数据为null");
            return null;
        }
        // 返回数据对象
        return BeanMapper.mapList(momentsTemporaryList, MomentsTemporaryBasicBo.class);
    }

    /**
     * 根据ID查询一条动态内容并返回
     *
     * @param id id
     * @return MomentsTemporaryBasicBo
     */
    @Override
    public MomentsTemporaryBasicBo getMomentsTemporaryById(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("获取临时动态信息失败，id为空");
            return null;
        }
        MomentsTemporary momentsTemporary = momentsTemporaryMongo.getMomentsTemporaryById(id);
        if (momentsTemporary == null) {
            logger.debug("获取临时动态信息失败，id为" + id);
            return null;
        }
        return BeanMapper.map(momentsTemporary, MomentsTemporaryBasicBo.class);
    }

    /**
     * 修改一条动态
     *
     * @param momentsTemporaryBasicBo momentsTemporaryBasicBo
     * @return boolean
     */
    @Override
    public boolean updateMomentsTemporary(MomentsTemporaryBasicBo momentsTemporaryBasicBo) {
        if (momentsTemporaryBasicBo == null || StringUtils.isEmpty(momentsTemporaryBasicBo.getId())) {
            logger.error("修改动态信息失败，参数为空");
            return false;
        }
        momentsTemporaryMongo.updateMomentsTemporary(BeanMapper.map(momentsTemporaryBasicBo, MomentsTemporary.class));
        return true;
    }

    /**
     * 当前批次上传成功个数查询
     *
     * @param batch      batch
     * @param uploadFlag uploadFlag
     * @return Integer
     */
    @Override
    public Integer count(Double batch, Integer uploadFlag) {
        return momentsTemporaryMongo.count(batch, uploadFlag);
    }

    /**
     * 指定批次指定上传状态下列表查询
     *
     * @param batch      batch
     * @param uploadFlag uploadFlag
     * @return List<MomentsTemporaryBo>
     */
    @Override
    public List<MomentsTemporaryBasicBo> getMomentsTemporaryListByBatchAndUploadFlag(Double batch, Integer uploadFlag) {
        List<MomentsTemporary> momentsTemporaryList = momentsTemporaryMongo.getMomentsTemporaryListByBatchAndUploadFlag(batch, uploadFlag);
        if (CollectionUtils.isEmpty(momentsTemporaryList)) {
            logger.debug("获取列表信息为空，数据为null");
            return null;
        }
        return BeanMapper.mapList(momentsTemporaryList, MomentsTemporaryBasicBo.class);
    }


    private static MomentsTemporaryBasicValidateBo validateMomentsTemporaryBasicBo(MomentsTemporaryBasicBo momentsTemporaryBo) {
        if (momentsTemporaryBo == null) {
            logger.error("校验失败,对象为空");
            return null;
        }
        Integer commitFlag = 1;//默认提交值
        MomentsTemporaryBasicBo result = BeanMapper.map(momentsTemporaryBo, MomentsTemporaryBasicBo.class);

        // 热线号校验
        String docChatNumValidate;
        if (StringUtils.isEmpty(momentsTemporaryBo.getDocChatNum())) {
            docChatNumValidate = "热线号为空";
            commitFlag = 0;
        } else {// 继续校验
            String docChatNum = momentsTemporaryBo.getDocChatNum();
            if (docChatNum.length() != 9 || !isNumeric(docChatNum) || !docChatNum.startsWith("805")) {
                docChatNumValidate = "805开头9位数字";
                commitFlag = 0;
            } else {
                docChatNumValidate = null;
            }
        }
        result.setDocChatNumValidate(docChatNumValidate);

        // 动态内容校验
        String contentValidate;
        if (StringUtils.isEmpty(momentsTemporaryBo.getContent())) {
            contentValidate = "动态内容为空";
            commitFlag = 0;
        } else {// 继续校验
            String content = momentsTemporaryBo.getContent();
            if (content.length() > 800) {
                contentValidate = "小于800字";
                commitFlag = 0;
            } else {
                contentValidate = null;
            }
        }
        result.setContentValidate(contentValidate);

        // 图片校验
        String picsValue, picsValidate;
        if (CollectionUtils.isEmpty(momentsTemporaryBo.getPics())) {
            picsValue = null;
            picsValidate = null;
        } else {// 继续校验
            List<String> pics = momentsTemporaryBo.getPics();
            String pics_str = pics.stream().collect(Collectors.joining(",")); //list转String 逗号连接;
            String regEx;
            if (pics_str.contains("http://") || pics_str.contains("https://")) {
                regEx = "[a-zA-Z_0-9-\\./:]+";
            } else {
                regEx = "[a-zA-Z_0-9-\\.]+";
            }
            Pattern pattern = Pattern.compile(regEx);
            // 字符串是否与正则表达式相匹配
            boolean rs = pattern.matcher(pics_str.replaceAll(",", "")).matches();// 去除连接符号[逗号]再校验
            if (!rs) {
                picsValue = pics_str;
                picsValidate = "图片格式不正确";
                commitFlag = 0;
            } else {
                picsValue = pics_str;
                picsValidate = null;
            }
        }
        result.setPicsValue(picsValue);
        result.setPicsValidate(picsValidate);

        // 话题校验
        String topicsValue, topicsValidate;
        if (CollectionUtils.isEmpty(momentsTemporaryBo.getTopics())) {
            topicsValue = null;
            topicsValidate = null;
        } else {// 继续校验
            List<String> topics = momentsTemporaryBo.getTopics();
            String topics_str = topics.stream().collect(Collectors.joining(",")); //list转String 逗号连接;
            if (topics_str.trim().length() > 50) {
                topicsValue = topics_str;
                topicsValidate = "字数不超过50字";
                commitFlag = 0;
            } else {
                topicsValue = topics_str;
                topicsValidate = null;
            }
        }
        result.setTopicsValue(topicsValue);
        result.setTopicsValidate(topicsValidate);

        // 标签校验
        String tagsValue, tagsVaidate;
        if (CollectionUtils.isEmpty(momentsTemporaryBo.getTags())) {
            tagsValue = null;
            tagsVaidate = null;
        } else {// 继续校验
            List<String> tags = momentsTemporaryBo.getTags();
            String tags_str = tags.stream().collect(Collectors.joining(",")); //list转String 逗号连接;
            if (tags_str.trim().length() > 50) {
                tagsValue = tags_str;
                tagsVaidate = "字数不超过50字";
                commitFlag = 0;
            } else {
                tagsValue = tags_str;
                tagsVaidate = null;
            }
        }
        result.setTagsValue(tagsValue);
        result.setTagsValidate(tagsVaidate);

        // 经度校验
        String lonValidate;
        if (momentsTemporaryBo.getLon() == null) {
            lonValidate = "经度为空";
        } else {// 继续校验
            Double lon = momentsTemporaryBo.getLon();
            if (lon < -180 || lon > 180) {
                lonValidate = "范围有误";
                commitFlag = 0;
            } else {
                lonValidate = null;
            }
        }
        result.setLonValidate(lonValidate);

        // 纬度校验
        String latValidate;
        if (momentsTemporaryBo.getLat() == null) {
            latValidate = "纬度为空";
        } else {// 继续校验
            Double lat = momentsTemporaryBo.getLat();
            if (lat < -90 || lat > 90) {
                latValidate = "范围有误";
                commitFlag = 0;
            } else {
                latValidate = null;
            }
        }
        result.setLatValidate(latValidate);
        // 封装返回
        MomentsTemporaryBasicValidateBo momentsTemporaryBasicValidateBo = new MomentsTemporaryBasicValidateBo();
        momentsTemporaryBasicValidateBo.setMomentsTemporaryBasicBo(result);
        momentsTemporaryBasicValidateBo.setCommitFlag(commitFlag);

        return momentsTemporaryBasicValidateBo;
    }


    /**
     * 校验是否为数字
     *
     * @param str str
     * @return boolean
     */
    private static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            // System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
