package org.yiuman.verydog.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yiuman.verydog.system.domain.User;
import org.yiuman.verydog.system.dto.UserDTO;
import org.yiuman.verydog.system.service.UserService;

import javax.validation.Valid;

/**
 * @author Yiuman
 * @date 2018/9/5
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> createUser(@Valid UserDTO userDTO){
        log.info("REST request to save User : {}", userDTO);
        return ResponseEntity.ok(userService.createUser(userDTO)) ;
    }

    @PostMapping("/{id}")
    public ResponseEntity<User>  empowerUser(@Valid @PathVariable Long id){
        return ResponseEntity.ok(userService.empowerUser(id));
    }


}
