package com.zlycare.web.boss_24.interceptor;


import com.alibaba.fastjson.JSON;
import com.zlycare.web.boss_24.constants.SessionConstant;
import com.zlycare.web.boss_24.core.mongo.log.BossLogMongo;
import com.zlycare.web.boss_24.core.mongo.po.BossLog;
import com.zlycare.web.boss_24.security.UserUtils;
import com.zlycare.web.boss_24.utils.other.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author lufanglong
 * @date 2015-11-19 19:18
 * @Description 认证拦截器
 */
public class AuthorizationHandlerInterceptor extends HandlerInterceptorAdapter {

    protected final static Logger logger = LoggerFactory.getLogger(AuthorizationHandlerInterceptor.class);

    @Autowired
    private BossLogMongo bossLogMongo;

    /**
     * 拦截请求做预处理, 校验请求认证
     *
     * @param request
     * @param response
     * @param handler
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        try {
            BossLog bossLog = new BossLog();
            String userId = "";
            if (!StringUtils.isEmpty(UserUtils.getUserJobNumber())) {
                userId = UserUtils.getUserJobNumber();
            }

            String url = "";
            if (request != null && request.getRequestURL() != null) {
                bossLog.setOperateInfo(request.getRequestURL().toString());
            }
            if (request != null && request.getParameterMap() != null) {
                if (request.getParameterMap().size() > 0) {
                    bossLog.setParams(JSON.toJSONString(request.getParameterMap()));
                }
            }
            if (!StringUtils.isEmpty(userId)) {
                bossLog.setUserId(userId);
                bossLog.setCreateAt(new Date());
                bossLogMongo.insert(bossLog);
            }
        } catch (Exception e) {
            logger.info("Boss Log Insert Error!!!", e);
        }

        return true;
    }


    private boolean checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        //没有登录
        if (session == null) {
            return false;
        }
        if (!isLogin(session)) {
            return false;
        }
        //登录过期 //下面的代码没意义，不知道在判断啥直接return False
//        if (isExpiration(session)) {
//            return false;
//        }

        //黑名单
        return !isBlack(session);

    }

//    private void checkAuditStatus(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            Integer keeperId = (Integer) session.getAttribute(SessionConstant.USER_UID);
//            if (keeperId != null) {
//                KeeperUserBo keeperUserBo = keeperUserService.getById(keeperId);
//                if (keeperUserBo.getAuditTimes() > 1) {
//                    session.invalidate();
//                }
//            }
//        }
//    }

    /**
     * 黑名单
     *
     * @param session
     * @return
     */
    private boolean isBlack(HttpSession session) {
        return false;
    }

    /**
     * 是否登录
     *
     * @param session
     * @return
     */
    private boolean isLogin(HttpSession session) {
        return session.getAttribute(SessionConstant.USER_UID) != null && session.getAttribute(SessionConstant.USER_ACC) != null;
    }

    /**
     * 过期
     *
     * @param session
     * @return
     */
    private boolean isExpiration(HttpSession session) {
        return false;
        /*Date expiration = (Date) session.getAttribute(SessionConstant.USER_EXPIRATION);
        if (expiration == null || !expiration.before(DateUtils.getCurrentDate())) {
            session.setAttribute(SessionConstant.USER_EXPIRATION, DateUtil.getAfterMinute(30));
            return false;
        } else {
            session.invalidate();
            return true;
        }*/


    }

}