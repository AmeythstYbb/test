package com.zlycare.web.boss_24.security;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author : linguodong
 * Create : 2017/6/2
 * Update : 2017/6/2
 * Descriptions :
 */
public class UserUtils {
    private static Logger logger = LoggerFactory.getLogger(UserUtils.class);

    public static String getUserJobNumber() {
        SystemRealm.Principal principal = (SystemRealm.Principal) SecurityUtils.getSubject().getPrincipal();
        if (principal != null) {
            return principal.getJobNumber();
        } else {
            logger.error("principal is null");
            //throw new UserNotFoundException();
            return "";
        }
    }

}
