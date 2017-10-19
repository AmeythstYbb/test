package com.zlycare.web.boss_24.security;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Author : linguodong
 * Create : 2017/6/1
 * Update : 2017/6/1
 * Descriptions : 重新token，对用户名的认证改为工号
 */
public class MyAuthenticationToken  extends UsernamePasswordToken {

    private static final long serialVersionUID = 1425855488092920451L;

    /** 手机号 */
    private String jobNumber;

    public MyAuthenticationToken(String jobNumber, String password, boolean rememberMe, String host) {
        super(null, password, rememberMe, host);
        this.jobNumber = jobNumber;
    }

    /** 默认是返回用户名，改写返回工号 */
    @Override
    public Object getPrincipal() {
        return jobNumber;
    }

    @Override
    public void clear() {
        super.clear();
        jobNumber = null;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }
}
