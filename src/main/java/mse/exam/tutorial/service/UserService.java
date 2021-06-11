package mse.exam.tutorial.service;

import lombok.extern.slf4j.Slf4j;
import mse.exam.tutorial.dto.UserDto;
import mse.exam.tutorial.entity.Authority;
import mse.exam.tutorial.entity.Chito;
import mse.exam.tutorial.entity.User;
import mse.exam.tutorial.repository.UserRepository;
import mse.exam.tutorial.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User signup(UserDto userDto) {
        Authority authority;
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        //빌더 패턴의 장점
        if (!(userDto.getUsername().equals("admin"))) {
            authority = Authority.builder()
                    .authorityName("ROLE_USER")
                    .build();
        }
        else {
//            Set<Authority> authorities = userRepository.findOneWithUserByUsername(userDto.getUsername()).getAuthorities();
            authority = Authority.builder()
                    .authorityName("ROLE_ADMIN")
                    .build();
        }

        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        if (userDto.getUsername().equals("media"))
        {

            Chito chito = new Chito(127,4.5,60,60,60);
            user = User.builder()
                    .username(userDto.getUsername())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .nickname(userDto.getNickname())
                    .authorities(Collections.singleton(authority))
                    .activated(true)
                    .chito(chito)
                    .build();
        }

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }

    public User findOneWithUserByUsername(String username)
    {
        return userRepository.findOneWithUserByUsername(username);
    }
}
