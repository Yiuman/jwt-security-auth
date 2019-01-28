package org.yiuman.verydog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.context.junit4.SpringRunner;
import org.yiuman.verydog.system.domain.User;
import org.yiuman.verydog.system.repository.UserRepository;
import org.yiuman.verydog.system.security.jwt.JwtTokenProvider;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VeryDogApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testAddUsers() {
        User user = new User();
        user.setPassword("123456");
        user.setUsername("system");
        userRepository.save(user);

    }

    @Test
    public void testJwtToken() {
        System.out.println(createToken());
    }

    @Test
    public void testJwtTokenValid() {
        String token = createToken();
        System.out.println(jwtTokenProvider.validateToken(token));
    }

    private String createToken() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return jwtTokenProvider.createToken(new UsernamePasswordAuthenticationToken("system", "system", authorities), false);
    }

}
