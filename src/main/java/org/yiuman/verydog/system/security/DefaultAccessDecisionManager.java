package org.yiuman.verydog.system.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 默认访问决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源
 *
 * @author Yiuman
 * @date 2018/9/4
 */
@Component
public class DefaultAccessDecisionManager implements AccessDecisionManager {

    public DefaultAccessDecisionManager() {
    }

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : collection) {
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if (configAttribute.getAttribute().equals(grantedAuthority.getAuthority())) {
                    return;
                }

            }
        }

        throw new AccessDeniedException("Without authority!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
