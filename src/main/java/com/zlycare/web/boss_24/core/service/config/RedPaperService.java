package com.zlycare.web.boss_24.core.service.config;

import com.zlycare.web.boss_24.core.service.bean.RedPaperBean;
import com.zlycare.web.boss_24.core.service.bo.RedPaperConfigBo;

/**
 * Author : linguodong
 * Create : 2017/7/27
 * Update : 2017/7/27
 * Descriptions :
 */
public interface RedPaperService {
    /**
     * 获取红包配置
     *
     * @return RedPaperConfigBo
     */
    RedPaperConfigBo getRedPaper();

    /**
     * 设置红包内容
     */
    boolean update(RedPaperBean redPaperBean);
}
