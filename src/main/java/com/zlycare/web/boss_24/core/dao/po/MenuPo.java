/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zlycare.web.boss_24.core.dao.po;

import com.google.common.collect.Lists;
import com.zlycare.web.boss_24.constants.sys.MenuConstants;
import java.util.List;

/**
 * 菜单Entity
 * @author ThinkGem
 * @version 2013-01-15
 */

public class MenuPo {
	private Long id;		// 编号
	private int parentId;	// 父级菜单
	private String parentIds; // 所有父级编号
	private String name; 	// 名称
	private String href; 	// 链接
	private String target; 	// 目标（ mainFrame、_blank、_self、_parent、_top）
	private String icon; 	// 图标
	private Integer sort; 	// 排序
	private String isShow; 	// 是否在菜单中显示（1：显示；0：不显示）
	private String permission; // 权限标识
	private String delFlag; // 删除标记（0：正常；1：删除）
	
	private List<MenuPo> childList = Lists.newArrayList();// 拥有子菜单列表
	private List<RolePo> roleList = Lists.newArrayList(); // 拥有角色列表

	public MenuPo(){
		this.sort = 30;
		this.delFlag = MenuConstants.DEL_FLAG_NORMAL;
	}
	
	public MenuPo(Long id){
		this();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
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

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public List<MenuPo> getChildList() {
		return childList;
	}

	public void setChildList(List<MenuPo> childList) {
		this.childList = childList;
	}

	public List<RolePo> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RolePo> roleList) {
		this.roleList = roleList;
	}

	//原始代码
//	public static void sortList(List<MenuPo> list, List<MenuPo> sourcelist, Long parentId){
//		for (int i=0; i<sourcelist.size(); i++){
//			MenuPo e = sourcelist.get(i);
//			if (e.getParent()!=null && e.getParent().getId()!=null
//					&& e.getParent().getId().equals(parentId)){
//				list.add(e);
//				// 判断是否还有子节点, 有则继续获取子节点
//				for (int j=0; j<sourcelist.size(); j++){
//					MenuPo child = sourcelist.get(j);
//					if (child.getParent()!=null && child.getParent().getId()!=null
//							&& child.getParent().getId().equals(e.getId())){
//						sortList(list, sourcelist, e.getId());
//						break;
//					}
//				}
//			}
//		}
//	}

	public boolean isRoot(){
		return isRoot(this.id);
	}
	
	public static boolean isRoot(Long id){
		return id != null && id.equals(1L);
	}
}