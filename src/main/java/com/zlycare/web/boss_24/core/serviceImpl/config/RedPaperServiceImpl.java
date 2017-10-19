package com.zlycare.web.boss_24.core.serviceImpl.config;

import com.zlycare.web.boss_24.core.mongo.order.RedPaperConfigMongo;
import com.zlycare.web.boss_24.core.mongo.po.RedPaperConfig;
import com.zlycare.web.boss_24.core.service.bean.RedPaperBean;
import com.zlycare.web.boss_24.core.service.bo.RedPaperConfigBo;
import com.zlycare.web.boss_24.core.service.config.RedPaperService;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author : linguodong
 * Create : 2017/8/29
 * Update : 2017/8/29
 * Descriptions :
 */
@Service

public class RedPaperServiceImpl implements RedPaperService {
    private static final Logger logger = LoggerFactory.getLogger(RedPaperServiceImpl.class);
    @Autowired
    private RedPaperConfigMongo redPaperConfigMongo;

    /**
     * 获取红包配置
     *
     * @return RedPaperConfigBo
     */
    @Override
    public RedPaperConfigBo getRedPaper() {
        RedPaperConfig redPaperConfig = redPaperConfigMongo.getRedPaper();
        if (redPaperConfig == null) {
            logger.debug("查询红包设置失败");
            return null;
        }
        return BeanMapper.map(redPaperConfig, RedPaperConfigBo.class);
    }

    /**
     * 设置红包内容
     */
    @Override
    public boolean update(RedPaperBean redPaperBean) {
        if (null == redPaperBean) {
            logger.error("修改红包信息失败，参数为空");
            return false;
        }
        redPaperConfigMongo.update(redPaperBean.getTimesPerPeoplePerDay(), redPaperBean.getHongbaoValue(), redPaperBean.getHongbaoCount());
        return true;
    }
}
