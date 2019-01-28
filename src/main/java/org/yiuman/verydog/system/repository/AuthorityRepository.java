package org.yiuman.verydog.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yiuman.verydog.system.domain.Authority;

/**
 * @author Yiuman
 * @date 2018/9/4
 */
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

}
