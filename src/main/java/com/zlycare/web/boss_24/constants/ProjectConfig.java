package com.zlycare.web.boss_24.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lufanglong
 * @date 2015-09-14 09:50
 * @Description 工程配置
 */
@Component
public class ProjectConfig {

    @Value("${resource.image.domain}")
    private String resourceImageDomain;

    /**
     * 过期时间
     */
    @Value("${user.expiration}")
    private Integer userExpiration;

    /**
     * 身份证
     */
    @Value("${user.identity.image}")
    private String identityImage;

    /**
     * im 外链
     */
    @Value("${im.url}")
    private String imUrl;
    /**
     * 财务提现调用接口put
     */
    @Value("${put.url}")
    private String putUrl;

    public String getPutUrl() {
        return putUrl;
    }

    public void setPutUrl(String putUrl) {
        this.putUrl = putUrl;
    }

    /**
     * node 动态接口
     */
    @Value("${node.moments.url}")
    private String nodeMomentsUrl;

    public String getImUrl() {
        return imUrl;
    }

    public void setImUrl(String imUrl) {
        this.imUrl = imUrl;
    }

    public Integer getUserExpiration() {
        return userExpiration;
    }

    public void setUserExpiration(Integer userExpiration) {
        this.userExpiration = userExpiration;
    }

    public String getResourceImageDomain() {
        return resourceImageDomain;
    }

    public void setResourceImageDomain(String resourceImageDomain) {
        this.resourceImageDomain = resourceImageDomain;
    }

    public String getIdentityImage() {
        return identityImage;
    }

    public void setIdentityImage(String identityImage) {
        this.identityImage = identityImage;
    }


    public String getNodeMomentsUrl() {
        return nodeMomentsUrl;
    }

    public void setNodeMomentsUrl(String nodeMomentsUrl) {
        this.nodeMomentsUrl = nodeMomentsUrl;
    }
}