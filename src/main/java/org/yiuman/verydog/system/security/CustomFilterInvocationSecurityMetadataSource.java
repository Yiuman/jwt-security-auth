package org.yiuman.verydog.system.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.yiuman.verydog.system.domain.Authority;
import org.yiuman.verydog.system.repository.AuthorityRepository;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 自定义安全数据源
 * 资源源数据定义，即定义某一资源可以被哪些角色访问
 *
 * @author Yiuman
 * @date 2018/9/4
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final AuthorityRepository authorityRepository;

    private static Map<String, Collection<ConfigAttribute>> resourceMap;

    @Autowired
    public CustomFilterInvocationSecurityMetadataSource(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    /**
     * 初始化资源
     */
    @PostConstruct
    public void init() {
        List<Authority> authorities = authorityRepository.findAll();
        resourceMap = new LinkedHashMap<>();

        authorities.forEach(authority -> {
            Collection<ConfigAttribute> configAttributes = resourceMap.get(authority.getUrl());
            if (CollectionUtils.isEmpty(configAttributes)) {
                configAttributes = new ArrayList<>();
            }

            configAttributes.add(new SecurityConfig(authority.getAuthorityName()));

            resourceMap.put(authority.getUrl(), configAttributes);
        });

    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();

        for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            if (entry.getKey().matches(request.getRequestURI())) {
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<>();

        for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
