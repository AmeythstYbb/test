package com.zlycare.web.boss_24.init;/**
 * Created by zhanglw on 2017/6/12.
 */

import com.zlycare.web.boss_24.core.mongo.log.BossLogMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Author : zhanglianwei
 * Create : 2017/6/12 18:42
 * Update : 2017/6/12 18:42
 * Descriptions :
 */
@Component
public class AppInit {

    @Autowired
    private BossLogMongo bossLogMongo;

    @PostConstruct
    public void initMongoBase() {
        bossLogMongo.create();
    }

}
