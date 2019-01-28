package org.yiuman.verydog.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yiuman.verydog.system.domain.Role;
import org.yiuman.verydog.system.dto.RoleDTO;
import org.yiuman.verydog.system.repository.AuthorityRepository;
import org.yiuman.verydog.system.repository.RoleRepository;

import java.util.HashSet;
import java.util.Optional;

/**
 * @author Yiuman
 * @date 2018/9/5
 */
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    private final AuthorityRepository authorityRepository;

    @Autowired
    public RoleService(AuthorityRepository authorityRepository, RoleRepository roleRepository) {
        this.authorityRepository = authorityRepository;
        this.roleRepository = roleRepository;
    }

    public Role createRole(RoleDTO roleDTO) {
        Optional<Role> byName = roleRepository.findByRoleName(roleDTO.getRoleName());
        if (byName.isPresent()) {
            throw new RuntimeException("角色已存在");
        }
        Role role = new Role();
        role.setAuthorities(new HashSet<>(authorityRepository.findAll()));
        role.setRoleName(roleDTO.getRoleName());
        return roleRepository.save(role);
    }
}
