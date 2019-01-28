package org.yiuman.verydog.system.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT属性配置
 *
 * @author Yiuman
 * @date 2018/8/28
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String tokenHeader = JwtConstants.TOKEN_HEADER;

    private String secret = JwtConstants.DEFAULT_JWT_SECRET;

    private String claimsKey = JwtConstants.CLAIMS_KEY;

    private long tokenValidateInSeconds = 604800L;

    private long rememberMeValidateInSeconds = 2592000L;

    public JwtProperties() {
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getClaimsKey() {
        return claimsKey;
    }

    public void setClaimsKey(String claimsKey) {
        this.claimsKey = claimsKey;
    }

    public long getTokenValidateInSeconds() {
        return tokenValidateInSeconds;
    }

    public void setTokenValidateInSeconds(long tokenValidateInSeconds) {
        this.tokenValidateInSeconds = tokenValidateInSeconds;
    }

    public long getRememberMeValidateInSeconds() {
        return rememberMeValidateInSeconds;
    }

    public void setRememberMeValidateInSeconds(long rememberMeValidateInSeconds) {
        this.rememberMeValidateInSeconds = rememberMeValidateInSeconds;
    }
}
