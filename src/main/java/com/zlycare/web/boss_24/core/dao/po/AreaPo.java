/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zlycare.web.boss_24.core.dao.po;


import com.zlycare.web.boss_24.constants.sys.MenuConstants;


public class AreaPo  {
	private Long id;		// 编号
	private String parentId;	// 父级编号
	private String parentIds; // 所有父级编号
	private String name; 	// 区域名称
	private String code; 	// 区域编码
	private String remarks; // 备注
	private String delFlag; // 删除标记（0：正常；1：删除）

//	private List<Office> officeList = Lists.newArrayList(); // 部门列表
//	private List<Area> childList = Lists.newArrayList();	// 拥有子区域列表

	public AreaPo(){
		this.delFlag = MenuConstants.DEL_FLAG_NORMAL;
	}

	public AreaPo(Long id){
		this();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

//	public static void sortList(List<AreaPo> list, List<AreaPo> sourcelist, Long parentId){
//		for (int i=0; i<sourcelist.size(); i++){
//			AreaPo e = sourcelist.get(i);
//			if (e.getParent()!=null && e.getParent().getId()!=null
//					&& e.getParent().getId().equals(parentId)){
//				list.add(e);
//				// 判断是否还有子节点, 有则继续获取子节点
//				for (int j=0; j<sourcelist.size(); j++){
//					AreaPo childe = sourcelist.get(j);
//					if (childe.getParent()!=null && childe.getParent().getId()!=null
//							&& childe.getParent().getId().equals(e.getId())){
//						sortList(list, sourcelist, e.getId());
//						break;
//					}
//				}
//			}
//		}
//	}

	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	public static boolean isAdmin(Long id){
		return id != null && id.equals(1L);
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}