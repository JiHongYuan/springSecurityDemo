package com.security;

import com.model.Resource;
import com.model.Role;
import com.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author jiHongYuan
 * @Title: MyFilterSecurityMetadataSource
 * @ProjectName TestSpring
 * @date 2019/3/2122:38
 * <p>
 * 加载资源与权限的对应关系
 */
@Component("myFilterSecurityMetadataSource")
public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ResourceService resourceService;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @PostConstruct
    private void loadResourceDefine() {
        if (resourceMap == null) {
            resourceMap = new HashMap<>();
            List<Resource> list = resourceService.selectResourceRole();
            for (Resource resources : list) {
                Collection<ConfigAttribute> configAttributes = null;
                for (Role role : resources.getRoles()) {
                    configAttributes = new ArrayList<>();
                    ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + role.getRoleKey());
                    configAttributes.add(configAttribute);
                }
                resourceMap.put(resources.getPath(), configAttributes);
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        if (resourceMap == null) {
            loadResourceDefine();
        }
        String s = ((FilterInvocation) o).getRequestUrl();
        System.out.println(s);
        RequestMatcher matcher;
        String resUrl = null;
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            resUrl = ite.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if (matcher.matches(request)) {
                return resourceMap.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
