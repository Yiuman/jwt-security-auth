package org.yiuman.verydog.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yiuman.verydog.system.domain.Role;
import org.yiuman.verydog.system.dto.RoleDTO;
import org.yiuman.verydog.system.service.RoleService;

import javax.validation.Valid;

/**
 * @author Yiuman
 * @date 2018/9/5
 */
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@Valid RoleDTO roleDTO) {
        log.info("REST request to save Role : {}", roleDTO);
        return ResponseEntity.ok(roleService.createRole(roleDTO));
    }
}
