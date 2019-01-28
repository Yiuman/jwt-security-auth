package org.yiuman.verydog.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yiuman.verydog.system.domain.Role;

import java.util.Optional;

/**
 * @author Yiuman
 * @date 2018/9/5
 */
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleName(String name);
}
