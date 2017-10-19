package com.zlycare.web.boss_24.security;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Author : linguodong
 * Create : 2017/6/1
 * Update : 2017/6/1
 * Descriptions : 重写token认证
 */
public class MyAuthenticationFilter extends FormAuthenticationFilter {

    public static final String DEFAULT_JOB_NUMBER_PARAM = "jobNumber";

    private String jobNumberParam = DEFAULT_JOB_NUMBER_PARAM;

    public MyAuthenticationFilter() {
        setFailureKeyAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String jobNumber = getJobNumber(request);
        String password = getPassword(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);

        return new com.zlycare.web.boss_24.security.MyAuthenticationToken(jobNumber, password, rememberMe, host);
    }

    public String getJobNumberParam() {
        return jobNumberParam;
    }

    public void setJobNumberParam(String jobNumberParam) {
        this.jobNumberParam = jobNumberParam;
    }

    protected String getJobNumber(ServletRequest request) {
        return WebUtils.getCleanParam(request, getJobNumberParam());
    }

}