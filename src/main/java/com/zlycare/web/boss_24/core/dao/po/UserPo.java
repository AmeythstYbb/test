/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zlycare.web.boss_24.core.dao.po;

import com.google.common.collect.Lists;

import com.zlycare.web.boss_24.constants.sys.MenuConstants;
import com.zlycare.web.boss_24.core.service.bo.DeptBo;
import com.zlycare.web.boss_24.utils.user.Collections3;

import java.util.Date;
import java.util.List;

/**
 * 用户Entity
 * @author ThinkGem
 * @version 2013-01-15
 */

public class UserPo {

	private Long id;		// 编号
	private Integer areaId;		// 归属区域
	private Integer businessId;	// 业务板块
	private String jobNumber;// 工号
	private String password;// 密码
	private String userName;	// 姓名
	private Date createDate;// 创建日期
	private String deleteFlag;	// 删除标记（0：正常；1：删除）
	private String loginIp;	// 最后登陆IP
	private Date loginDate;	// 最后登陆日期
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 用户业务区域
	 */
	private String area;
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

	private List<RolePo> roleList = Lists.newArrayList(); // 拥有角色列表

	public UserPo() {
		this.createDate = new Date();
		this.deleteFlag = MenuConstants.DEL_FLAG_NORMAL;
	}
	
	public UserPo(Long id) {
		this();
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRoleNames() {
		return Collections3.extractToString(roleList, "name", ", ");
	}

	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	public static boolean isAdmin(Long id){
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


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
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