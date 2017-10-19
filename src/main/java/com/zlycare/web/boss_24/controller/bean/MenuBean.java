/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zlycare.web.boss_24.controller.bean;

import com.zlycare.web.boss_24.constants.sys.MenuConstants;

/**
 * 菜单Entity
 * @author ThinkGem
 * @version 2013-01-15
 */

public class MenuBean {
    private Long id_name_hidden;// 修改对象id
    private int parent_name_hidden;    // 父级菜单id
    private String parentIds; // 所有父级编号
    private String name;    // 名称
    private String href;    // 链接
    private String target;    // 目标（ mainFrame、_blank、_self、_parent、_top）
    private String icon;    // 图标
    private Integer sort;    // 排序
    private String show;    // 是否在菜单中显示（1：显示；0：不显示）
    private String permission; // 权限标识
    private String delFlag; // 删除标记（0：正常；1：删除）

    public MenuBean() {
        this.sort = 30;
        this.delFlag = MenuConstants.DEL_FLAG_NORMAL;
    }

    public Long getId_name_hidden() {
        return id_name_hidden;
    }

    public void setId_name_hidden(Long id_name_hidden) {
        this.id_name_hidden = id_name_hidden;
    }

    public int getParent_name_hidden() {
        return parent_name_hidden;
    }

    public void setParent_name_hidden(int parent_name_hidden) {
        this.parent_name_hidden = parent_name_hidden;
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

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}