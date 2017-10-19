package com.zlycare.web.boss_24.controller.user.bean;


import com.zlycare.web.boss_24.beans.annotation.StringTrimProperty;

/**
 * Description:用户登录Bean
 * Author: LiKaiHua
 * Update: 2016/01/7 19:43
 */
public class UserLoginBean {

    /**
     * 工号
     */
    @StringTrimProperty
    private String jobNumber;

    /**
     * 密码
     */
    @StringTrimProperty
    private String password;

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
