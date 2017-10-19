package com.zlycare.web.boss_24.core.mongo.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/9/29
 * Update : 2017/9/29
 * Descriptions : excel数据解析临时表
 */
@Document(collection = "momentsTemporary")
public class MomentsTemporary {
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
     * 热线号
     */
    private String docChatNum;
    /**
     * 动态内容
     */
    private String content;
    /**
     * 图片
     */
    private List<String> pics;
    /**
     * 话题
     */
    private List<String> topics;
    /**
     * 标签
     */
    private List<String> tags;
    /**
     * 经度
     */
    private Double lon;
    /**
     * 纬度
     */
    private Double lat;
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

    public String getDocChatNum() {
        return docChatNum;
    }

    public void setDocChatNum(String docChatNum) {
        this.docChatNum = docChatNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
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
}
