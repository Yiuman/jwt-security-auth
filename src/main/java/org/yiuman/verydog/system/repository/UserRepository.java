package org.yiuman.verydog.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yiuman.verydog.system.domain.User;

import java.util.Optional;

/**
 * @author Yiuman
 * @date 2018/8/28
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);

    Optional<User> findByPhone(String phone);

    @Query("select user from User user where user.username=:login or user.phone=:login")
    Optional<User> findByUsernameOrPhone(@Param("login") String login);
}
