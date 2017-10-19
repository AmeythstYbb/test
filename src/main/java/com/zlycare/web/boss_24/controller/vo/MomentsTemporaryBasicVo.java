package com.zlycare.web.boss_24.controller.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/9/29
 * Update : 2017/9/29
 * Descriptions : excel数据解析临时表 basicVo
 */
public class MomentsTemporaryBasicVo {
    /**
     * 自增ID
     */
    @Id
    private String id;
    /**
     * 序号,Excel带入
     */
    private Integer excelId;
    /**
     * 序号 校验
     * key:excelId 的值,value:异常信息[校验通过为空]
     * 前端逻辑：展示key，value为空则正常，否则显示红色，并在title展示异常信息
     */
    // private HashMap<String, String> excelIdValidate;
    private String excelIdValidate;
    /**
     * 热线号
     */
    private String docChatNum;
    /**
     * 热线号 校验
     * key:docChatNum 的值,value:异常信息[校验通过为空]
     * 前端逻辑：展示key，value为空则正常，否则显示红色，并在title展示异常信息
     */
    //private HashMap<String, String> docChatNumValidate;
    private String docChatNumValidate;
    /**
     * 动态内容
     */
    private String content;
    /**
     * 动态内容 校验
     * key:content 的值,value:异常信息[校验通过为空]
     * 前端逻辑：展示key，value为空则正常，否则显示红色，并在title展示异常信息
     */
    //private HashMap<String, String> contentValidate;
    private String contentValidate;
    /**
     * 图片
     */
    private List<String> pics;
    /**
     * 图片 校验
     * key:pics 的值(list转string),value:异常信息[校验通过为空]
     * 前端逻辑：展示key，value为空则正常，否则显示红色，并在title展示异常信息
     */
    //private HashMap<String, String> picsValidate;
    private String picsValidate;
    /**
     * pics转换后的值
     */
    private String picsValue;
    /**
     * tags转换后的值
     */
    private String tagsValue;
    /**
     * topics转换后的值
     */
    private String topicsValue;
    /**
     * 话题
     */
    private List<String> topics;
    /**
     * 话题 校验
     * key:topics 的值(list转string),value:异常信息[校验通过为空]
     * 前端逻辑：展示key，value为空则正常，否则显示红色，并在title展示异常信息
     */
    //private HashMap<String, String> topicsValidate;
    private String topicsValidate;
    /**
     * 标签
     */
    private List<String> tags;
    /**
     * 标签 校验
     * key:tags 的值(list转string),value:异常信息[校验通过为空]
     * 前端逻辑：展示key，value为空则正常，否则显示红色，并在title展示异常信息
     */
    //private HashMap<String, String> tagsValidate;
    private String tagsValidate;
    /**
     * 经度
     */
    private Double lon;
    /**
     * 经度 校验
     * key:lon 的值,value:异常信息[校验通过为空]
     * 前端逻辑：展示key，value为空则正常，否则显示红色，并在title展示异常信息
     */
    //private HashMap<String, String> lonValidate;
    private String lonValidate;
    /**
     * 纬度
     */
    private Double lat;
    /**
     * 纬度 校验
     * key:lat 的值,value:异常信息[校验通过为空]
     * 前端逻辑：展示key，value为空则正常，否则显示红色，并在title展示异常信息
     */
    //private HashMap<String, String> latValidate;
    private String latValidate;
    /**
     * 批次
     * 使用13位时间戳,一个EXCEL文件中的为一个批次,使用同一个值;
     * 即可区分批次,又可区分上传时间;
     */
    private Double batch;
    /**
     * 是否上传字段[传递到七牛并调用node接口,当前数据是否已进入业务数据库]
     * 0 未上传; 1 上传成功; 2 上传失败
     */
    private Integer uploadFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getExcelId() {
        return excelId;
    }

    public void setExcelId(Integer excelId) {
        this.excelId = excelId;
    }

    public String getExcelIdValidate() {
        return excelIdValidate;
    }

    public void setExcelIdValidate(String excelIdValidate) {
        this.excelIdValidate = excelIdValidate;
    }

    public String getDocChatNum() {
        return docChatNum;
    }

    public void setDocChatNum(String docChatNum) {
        this.docChatNum = docChatNum;
    }

    public String getDocChatNumValidate() {
        return docChatNumValidate;
    }

    public void setDocChatNumValidate(String docChatNumValidate) {
        this.docChatNumValidate = docChatNumValidate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentValidate() {
        return contentValidate;
    }

    public void setContentValidate(String contentValidate) {
        this.contentValidate = contentValidate;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getPicsValidate() {
        return picsValidate;
    }

    public void setPicsValidate(String picsValidate) {
        this.picsValidate = picsValidate;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public String getTopicsValidate() {
        return topicsValidate;
    }

    public void setTopicsValidate(String topicsValidate) {
        this.topicsValidate = topicsValidate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTagsValidate() {
        return tagsValidate;
    }

    public void setTagsValidate(String tagsValidate) {
        this.tagsValidate = tagsValidate;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getLonValidate() {
        return lonValidate;
    }

    public void setLonValidate(String lonValidate) {
        this.lonValidate = lonValidate;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getLatValidate() {
        return latValidate;
    }

    public void setLatValidate(String latValidate) {
        this.latValidate = latValidate;
    }

    public Double getBatch() {
        return batch;
    }

    public void setBatch(Double batch) {
        this.batch = batch;
    }

    public Integer getUploadFlag() {
        return uploadFlag;
    }

    public void setUploadFlag(Integer uploadFlag) {
        this.uploadFlag = uploadFlag;
    }

    public String getPicsValue() {
        return picsValue;
    }

    public void setPicsValue(String picsValue) {
        this.picsValue = picsValue;
    }

    public String getTagsValue() {
        return tagsValue;
    }

    public void setTagsValue(String tagsValue) {
        this.tagsValue = tagsValue;
    }

    public String getTopicsValue() {
        return topicsValue;
    }

    public void setTopicsValue(String topicsValue) {
        this.topicsValue = topicsValue;
    }
}
