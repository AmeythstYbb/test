/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zlycare.web.boss_24.security;

import com.zlycare.web.boss_24.constants.sys.Shiro;
import com.zlycare.web.boss_24.core.dao.po.UserPo;
import com.zlycare.web.boss_24.core.service.bo.MenuBo;
import com.zlycare.web.boss_24.core.service.bo.UserBo;
import com.zlycare.web.boss_24.core.service.sys.MenuService;
import com.zlycare.web.boss_24.core.service.sys.RoleService;
import com.zlycare.web.boss_24.core.service.sys.UserService;
import com.zlycare.web.boss_24.utils.common.utils.BeanMapper;
import com.zlycare.web.boss_24.utils.other.Encodes;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;

/**
 * 系统安全认证实现类
 * @author ThinkGem
 * @version 2013-01-15
 */
@Service
public class SystemRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        MyAuthenticationToken token = (MyAuthenticationToken) authcToken;
        UserBo userBo = null;
        try {
            userBo = userService.getUserByJobNumber(token.getJobNumber());
        } catch (AuthenticationException e) {
            throw new IncorrectCredentialsException(e);
        }
        if (userBo != null) {
            byte[] salt = Encodes.decodeHex(userBo.getPassword().substring(0, 16));
            return new SimpleAuthenticationInfo(new Principal(BeanMapper.map(userBo, UserPo.class)),
                    userBo.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
        } else {
//			throw new IncorrectCredentialsException("账号或密码错误！");
            return null;
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) getAvailablePrincipal(principals);
        UserBo userBo = userService.getUserByJobNumber(principal.getJobNumber());
        if (userBo != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<MenuBo> menuBoList = menuService.findAllMenu(userBo.getId());
            for (MenuBo menuBo : menuBoList) {
                if (StringUtils.isNotBlank(menuBo.getPermission())) {
                    // 添加基于Permission的权限信息
                    info.addStringPermission(menuBo.getPermission());
                }
            }
            // 更新登录IP和时间
            userService.updateUserLoginInfo(userBo.getId());
            return info;
        } else {
            return null;
        }
    }

    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Shiro.HASH_ALGORITHM);
        matcher.setHashIterations(Shiro.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    /**
     * 清空用户关联权限认证，待下次使用时重新加载
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清空所有关联认证
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    /**
     * 授权用户信息
     */
    public static class Principal implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long id;
        private String jobNumber;
        private String name;
        private Map<String, Object> cacheMap;

        public Principal(UserPo userPo) {
            this.id = userPo.getId();
            this.jobNumber = userPo.getJobNumber();
            this.name = userPo.getUserName();
        }

        public Long getId() {
            return id;
        }

        public String getJobNumber() {
            return jobNumber;
        }

        public String getName() {
            return name;
        }

        public Map<String, Object> getCacheMap() {
            if (cacheMap == null) {
                cacheMap = new HashMap<String, Object>();
            }
            return cacheMap;
        }

    }
}
