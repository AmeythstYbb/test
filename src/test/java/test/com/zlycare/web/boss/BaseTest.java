package test.com.zlycare.web.boss;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Author : zhanglianwei
 * Create : 2017/6/2 10:05
 * Update : 2017/6/2 10:05
 * Descriptions :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
@Profile("local")
@Ignore
public class BaseTest {


    @Test
    public  void test(){
        System.out.println("Hello");
    }
}
