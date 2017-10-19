package com.zlycare.web.boss_24.core.service.bo;


import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/10/9
 * Update : 2017/10/9
 * Descriptions : excel数据解析临时表封装后Bo
 */
public class MomentsTemporaryBo {
    /**
     * 返回数据列表
     */
    List<MomentsTemporaryBasicBo> momentsTemporaryBasicBoList;
    /**
     * 批次
     * 使用13位时间戳,一个EXCEL文件中的为一个批次,使用同一个值;
     * 即可区分批次,又可区分上传时间;
     */
    private Double batch;
    /**
     * 当前批次数据列表服务器校验后是否存在错误,存在[确认上传]按钮不可点击
     * 0 存在错误;1 可以上传
     */
    private Integer commitFlag;


    public Double getBatch() {
        return batch;
    }

    public void setBatch(Double batch) {
        this.batch = batch;
    }

    public Integer getCommitFlag() {
        return commitFlag;
    }

    public void setCommitFlag(Integer commitFlag) {
        this.commitFlag = commitFlag;
    }

    public List<MomentsTemporaryBasicBo> getMomentsTemporaryBasicBoList() {
        return momentsTemporaryBasicBoList;
    }

    public void setMomentsTemporaryBasicBoList(List<MomentsTemporaryBasicBo> momentsTemporaryBasicBoList) {
        this.momentsTemporaryBasicBoList = momentsTemporaryBasicBoList;
    }
}
