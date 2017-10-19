package com.zlycare.web.boss_24.controller.vo;

import java.util.List;

/**
 * Author : linguodong
 * Create : 2017/10/9
 * Update : 2017/10/9
 * Descriptions : excel数据解析临时表VO,暂未使用
 */
public class MomentsTemporaryVo {
    /**
     * 返回数据列表
     */
    List<MomentsTemporaryBasicVo> momentsTemporaryBasicVoList;
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

    public List<MomentsTemporaryBasicVo> getMomentsTemporaryBasicVoList() {
        return momentsTemporaryBasicVoList;
    }

    public void setMomentsTemporaryBasicVoList(List<MomentsTemporaryBasicVo> momentsTemporaryBasicVoList) {
        this.momentsTemporaryBasicVoList = momentsTemporaryBasicVoList;
    }

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
}
