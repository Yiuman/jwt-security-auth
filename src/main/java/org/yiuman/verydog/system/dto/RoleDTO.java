package org.yiuman.verydog.system.dto;

import org.yiuman.verydog.system.domain.Authority;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yiuman
 * @date 2018/9/5
 */
public class RoleDTO {

    private String roleName;

    private Set<Authority> authorities;

    public RoleDTO() {

    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
