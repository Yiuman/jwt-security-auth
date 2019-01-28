package org.yiuman.verydog.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yiuman.verydog.system.domain.Authority;
import org.yiuman.verydog.system.dto.AuthorityDTO;
import org.yiuman.verydog.system.repository.AuthorityRepository;

import java.util.HashSet;
import java.util.List;

/**
 * @author Yiuman
 * @date 2018/9/5
 */
@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority createAuthority(AuthorityDTO authorityDTO) {
        Authority authority = new Authority();
        authority.setAuthorityName(authorityDTO.getAuthorityName());
        authority.setUrl(authorityDTO.getUrl());
        return authorityRepository.save(authority);
    }

    public List<Authority> getAllAuthoritys(){
        return authorityRepository.findAll();
    }
}
