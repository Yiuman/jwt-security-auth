package org.yiuman.verydog.system.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;


/**
 * 自定义安全拦截过滤器
 *
 * @author Yiuman
 * @date 2018/9/4
 */
@Component
public class CustomFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private final CustomFilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    public CustomFilterSecurityInterceptor(CustomFilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return securityMetadataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Autowired
    public void setAccessDecisionManager(DefaultAccessDecisionManager accessDecisionManager) {
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(fi);
    }

    @Override
    public void destroy() {

    }

    /**
     * filterInvocation里面有一个被拦截的url
     * 里面调用CustomFilterInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取filterInvocation对应的所有权限
     * 再调用DefaultAccessDecisionManager的decide方法来校验用户的权限是否足够
     *
     * @param filterInvocation
     * @throws IOException
     * @throws ServletException
     */
    public void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {

        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try {
            // 执行下一个拦截器
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

}
