package com.zlycare.web.boss_24.core.service.bo;

/**
 * Author : linguodong
 * Create : 2017/10/9
 * Update : 2017/10/9
 * Descriptions :
 */
public class MomentsTemporaryBasicValidateBo {
    /**
     * MomentsTemporaryBasicBo对象
     */
    MomentsTemporaryBasicBo momentsTemporaryBasicBo;
    /**
     * 当前批次数据列表服务器校验后是否存在错误,存在[确认上传]按钮不可点击
     * 0 存在错误;1 可以上传
     */
    private Integer commitFlag;

    public MomentsTemporaryBasicBo getMomentsTemporaryBasicBo() {
        return momentsTemporaryBasicBo;
    }

    public void setMomentsTemporaryBasicBo(MomentsTemporaryBasicBo momentsTemporaryBasicBo) {
        this.momentsTemporaryBasicBo = momentsTemporaryBasicBo;
    }

    public Integer getCommitFlag() {
        return commitFlag;
    }

    public void setCommitFlag(Integer commitFlag) {
        this.commitFlag = commitFlag;
    }
}
