/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zlycare.web.boss_24.controller.vo;

import com.zlycare.web.boss_24.constants.sys.MenuConstants;
import com.zlycare.web.boss_24.core.service.bo.DeptBo;
import org.dozer.Mapping;

import java.util.Date;

/**
 * 用户Entity
 *
 * @author ThinkGem
 * @version 2013-01-15
 */

public class UserVo {

    private Long id;        // 编号
    private Integer areaId;        // 归属区域
    private Integer businessId;    // 业务板块
    private String jobNumber;// 工号
    private String password;// 密码
    @Mapping("userName")
    private String name;    // 姓名
    private Date createDate;// 创建日期
    @Mapping("deleteFlag")
    private String delFlag;    // 删除标记（0：正常；1：删除）
    private String roleNames;    // 角色集合
    private String loginIp;    // 最后登陆IP
    private Date loginDate;    // 最后登陆日期
    /**
     * 在职 离职
     */
    private Integer status;
    /**
     * 一级部门id
     */
    private Integer deptId;
    /**
     * 二级部门id
     */
    private Integer deptChildrenId;
    /**
     * 一级部门对象
     */
    private DeptBo deptBo;
    /**
     * 二级部门对象
     */
    private DeptBo deptChildrenBo;
    /**
     * 用户业务区域
     */
    private String area;
    /**
     * 手机号
     */
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getDeptChildrenId() {
        return deptChildrenId;
    }

    public void setDeptChildrenId(Integer deptChildrenId) {
        this.deptChildrenId = deptChildrenId;
    }

    public DeptBo getDeptBo() {
        return deptBo;
    }

    public void setDeptBo(DeptBo deptBo) {
        this.deptBo = deptBo;
    }

    public DeptBo getDeptChildrenBo() {
        return deptChildrenBo;
    }

    public void setDeptChildrenBo(DeptBo deptChildrenBo) {
        this.deptChildrenBo = deptChildrenBo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserVo() {
        this.createDate = new Date();
        this.delFlag = MenuConstants.DEL_FLAG_NORMAL;
    }

    public UserVo(Long id) {
        this();
        this.id = id;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public boolean isAdmin() {
        return isAdmin(this.id);
    }

    public static boolean isAdmin(Long id) {
        return id != null && id.equals(1L);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}