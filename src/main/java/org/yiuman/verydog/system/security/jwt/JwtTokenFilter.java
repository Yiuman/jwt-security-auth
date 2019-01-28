package org.yiuman.verydog.system.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT拦截器
 *
 * @author Yiuman
 * @date 2018/8/28
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtTokenFilter(JwtProperties jwtProperties, JwtTokenProvider jwtTokenProvider) {
        this.jwtProperties = jwtProperties;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //解析请求头，获取Token
        String jwtToken = resolveToken(httpServletRequest);
        if (StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
            //验证token后将认证信息放入Security容器
            SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(jwtToken));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getTokenHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }


}
