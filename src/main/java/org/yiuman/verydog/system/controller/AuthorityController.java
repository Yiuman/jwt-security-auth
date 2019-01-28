package org.yiuman.verydog.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yiuman.verydog.system.domain.Authority;
import org.yiuman.verydog.system.dto.AuthorityDTO;
import org.yiuman.verydog.system.service.AuthorityService;

import java.util.List;

/**
 * @author Yiuman
 * @date 2018/9/5
 */
@RestController
@RequestMapping("/api/authorities")
public class AuthorityController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @PostMapping
    public ResponseEntity<Authority> createAuthority(AuthorityDTO authorityDTO) {
        log.info("REST request to save Authority : {}", authorityDTO);
        return ResponseEntity.ok(authorityService.createAuthority(authorityDTO));
    }

    @GetMapping
    public ResponseEntity<List<Authority>> getAllAuthority(){
        return ResponseEntity.ok(authorityService.getAllAuthoritys());
    }


}
