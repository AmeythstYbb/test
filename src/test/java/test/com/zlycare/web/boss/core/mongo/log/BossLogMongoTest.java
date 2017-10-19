package test.com.zlycare.web.boss.core.mongo.log;

import com.zlycare.web.boss_24.core.mongo.log.BossLogMongo;
import com.zlycare.web.boss_24.core.mongo.po.BossLog;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.com.zlycare.web.boss.BaseTest;

import java.util.Date;
/**
 * Created by zhanglw on 2017/6/2.
 */

/**
 * Author : zhanglianwei
 * Create : 2017/6/2 15:52
 * Update : 2017/6/2 15:52
 * Descriptions :
 */
@Ignore
public class BossLogMongoTest extends BaseTest {

    @Autowired
    private BossLogMongo bossLogMongo;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void insert() throws Exception {

        BossLog bossLog = new BossLog();
        bossLog.setCreateAt(new Date());
        bossLog.setOperateInfo("Insert");
        bossLog.setUserId("1000Test");
        bossLogMongo.insert(bossLog);
    }

    @Test
    public void create() throws Exception {
    }

}