package org.yiuman.verydog.system.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * JPA审计  用于记录创建或修改于哪个用户，记录用户名
 *
 * @author Yiuman
 * @date 2018/8/28
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    public SpringSecurityAuditorAware() {
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityUtils.getCurrentUserLogin().orElse("system"));
    }
}
