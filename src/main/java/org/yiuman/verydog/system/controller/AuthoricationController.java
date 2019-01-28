package org.yiuman.verydog.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yiuman.verydog.system.security.jwt.JwtProperties;
import org.yiuman.verydog.system.security.jwt.JwtTokenProvider;
import org.yiuman.verydog.system.vm.UserLoginVM;

import javax.validation.Valid;

/**
 * @author Yiuman
 * @date 2018/9/5
 */
@RestController
@RequestMapping("/api/authenticate")
public class AuthoricationController {

    private final JwtProperties jwtProperties;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthoricationController(JwtProperties jwtProperties, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.jwtProperties = jwtProperties;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping
    public ResponseEntity<JwtToken> authorize(@Valid UserLoginVM userLoginVM) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginVM.getLogin(), userLoginVM.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.createToken(authentication, userLoginVM.isRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(jwtProperties.getTokenHeader(), "Bearer " + token);
        return new ResponseEntity<>(new JwtToken(token), httpHeaders, HttpStatus.OK);
    }

    static class JwtToken {

        private String token;

        JwtToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

    }
}
