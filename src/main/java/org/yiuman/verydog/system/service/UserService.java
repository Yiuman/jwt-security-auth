package org.yiuman.verydog.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yiuman.verydog.system.domain.Role;
import org.yiuman.verydog.system.domain.User;
import org.yiuman.verydog.system.dto.UserDTO;
import org.yiuman.verydog.system.repository.RoleRepository;
import org.yiuman.verydog.system.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yiuman
 * @date 2018/9/5
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User createUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("用户名已存在");
        }

        if(userRepository.findByPhone(userDTO.getPhone()).isPresent()){
            throw new RuntimeException("手机已存在");
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhone(userDTO.getPhone());
        return userRepository.save(user);
    }

    public User empowerUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("找不到用户"));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.findById(1L).get());
        user.setRoles(roleSet);
        return userRepository.save(user);
    }
}
