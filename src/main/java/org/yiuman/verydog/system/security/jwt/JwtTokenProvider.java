package org.yiuman.verydog.system.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Jwt Token提供者，用于处理Jwt Token 逻辑
 *
 * @author Yiuman
 * @date 2018/9/4
 */
@Component
public class JwtTokenProvider {

    private final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final JwtProperties jwtProperties;

    @Autowired
    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 创建Token
     *
     * @param authentication 认证信息
     * @param rememberMe     是否记住我
     * @return token
     */
    public String createToken(Authentication authentication, boolean rememberMe) {
        //获取认证信息中的权限,多个权限名用逗号","隔开
        String auths = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = System.currentTimeMillis();
        Date expiration;
        if (rememberMe) {
            expiration = new Date(now + jwtProperties.getRememberMeValidateInSeconds());
        } else {
            expiration = new Date(now + jwtProperties.getTokenValidateInSeconds());
        }

        return Jwts.builder()
                .claim(jwtProperties.getClaimsKey(), auths)
                .setSubject(authentication.getName())
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .setExpiration(expiration)
                .compact();
    }

    /**
     * 检验token有效性
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            log.info("token无效");
        }

        return false;
    }

    /**
     * 根据token获取认证信息
     *
     * @param token token字符串
     * @return 认证信息
     */
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();

        Collection<GrantedAuthority> authorities =
                Arrays.stream(claims.get(jwtProperties.getClaimsKey()).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(claims.getSubject(), token, authorities);
    }


}
