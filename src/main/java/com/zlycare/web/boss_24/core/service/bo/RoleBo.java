/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zlycare.web.boss_24.core.service.bo;

import com.google.common.collect.Lists;
import com.zlycare.web.boss_24.constants.sys.MenuConstants;
import com.zlycare.web.boss_24.core.dao.po.CategoryPo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;



public class RoleBo {
	private Long id;	 // 编号
	private String name; // 角色名称
	private String delFlag; // 删除标记（0：正常；1：删除）

	private List<UserBo> userList = Lists.newArrayList(); // 拥有用户列表
	private List<MenuBo> menuList = Lists.newArrayList(); // 拥有菜单列表
	private List<CategoryPo> categoryList = Lists.newArrayList(); // 拥有内容分类列表

	public RoleBo() {
		this.delFlag = MenuConstants.DEL_FLAG_NORMAL;
	}

	public RoleBo(Long id, String name) {
		this();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public List<UserBo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserBo> userList) {
		this.userList = userList;
	}

	public List<MenuBo> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuBo> menuList) {
		this.menuList = menuList;
	}

	public List<CategoryPo> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryPo> categoryList) {
		this.categoryList = categoryList;
	}

	//原始代码
	public List<Long> getMenuIdList() {
		List<Long> menuIdList = Lists.newArrayList();
		for (MenuBo menu : menuList) {
			menuIdList.add(menu.getId());
		}
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		menuList = Lists.newArrayList();
		for (Long menuId : menuIdList) {
			MenuBo menu = new MenuBo();
			menu.setId(menuId);
			menuList.add(menu);
		}
	}

	public String getMenuIds() {
		List<Long> nameIdList = Lists.newArrayList();
		for (MenuBo menu : menuList) {
			nameIdList.add(menu.getId());
		}
		return StringUtils.join(nameIdList, ",");
	}

	public void setMenuIds(String menuIds) {
		menuList = Lists.newArrayList();
		if (menuIds != null){
			String[] ids = StringUtils.split(menuIds, ",");
			for (String menuId : ids) {
				MenuBo menu = new MenuBo();
				menu.setId(new Long(menuId));
				menuList.add(menu);
			}
		}
	}
	
	/**
	 * 获取内容分类Id列表
	 */
	public List<Long> getCategoryIdList() {
		List<Long> categoryIdList = Lists.newArrayList();
		for (CategoryPo category : categoryList) {
			categoryIdList.add(category.getId());
		}
		return categoryIdList;
	}

	public void setCategoryIdList(List<Long> categoryIdList) {
		categoryList = Lists.newArrayList();
		for (Long categoryId : categoryIdList) {
			CategoryPo category = new CategoryPo();
			category.setId(categoryId);
			categoryList.add(category);
		}
	}

	public String getCategoryIds() {
		List<Long> nameIdList = Lists.newArrayList();
		for (CategoryPo category : categoryList) {
			nameIdList.add(category.getId());
		}
		return StringUtils.join(nameIdList, ",");
	}
	
	public void setCategoryIds(String categoryIds) {
		categoryList = Lists.newArrayList();
		if (categoryIds != null){
			String[] ids = StringUtils.split(categoryIds, ",");
			for (String categoryId : ids) {
				CategoryPo category = new CategoryPo();
				category.setId(new Long(categoryId));
				categoryList.add(category);
			}
		}
	}
	
	/**
	 * 获取权限字符串列表
	 */
	public List<String> getPermissions() {
		List<String> permissions = Lists.newArrayList();
		for (MenuBo menu : menuList) {
			if (menu.getPermission()!=null && !"".equals(menu.getPermission())){
				permissions.add(menu.getPermission());
			}
		}
		return permissions;
	}
	
	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	public static boolean isAdmin(Long id){
		return id != null && id.equals(1L);
	}
	
//	@Transient
//	public String getMenuNames() {
//		List<String> menuNameList = Lists.newArrayList();
//		for (Menu menu : menuList) {
//			menuNameList.add(menu.getName());
//		}
//		return StringUtils.join(menuNameList, ",");
//	}
}
