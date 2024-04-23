package com.boot.springbootc71.security.service;

import com.boot.springbootc71.exception.SameUserInDatabase;
import com.boot.springbootc71.model.User;
import com.boot.springbootc71.repository.UserRepository;
import com.boot.springbootc71.security.model.Roles;
import com.boot.springbootc71.security.model.UserSecurity;
import com.boot.springbootc71.security.model.dto.RegistrationDto;
import com.boot.springbootc71.security.repository.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserSecurityService {

    private final PasswordEncoder passwordEncoder;

    private final UserSecurityRepository userSecurityRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(PasswordEncoder passwordEncoder, UserSecurityRepository userSecurityRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userSecurityRepository = userSecurityRepository;
        this.userRepository = userRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public void registration(RegistrationDto registrationDto) {
        Optional<UserSecurity> security = userSecurityRepository.findByUserLogin(registrationDto.getLogin());
        if (security.isPresent()) {
            throw new SameUserInDatabase(registrationDto.getLogin());
        }
        User user = new User();
        user.setAge(registrationDto.getAge());
        user.setUsername(registrationDto.getUsername());
        user.setChanged(Timestamp.valueOf(LocalDateTime.now()));
        user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        User savedUser = userRepository.save(user);

        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setUserLogin(registrationDto.getLogin());
        userSecurity.setUserPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userSecurity.setRole(Roles.USER);
        userSecurity.setUserId(savedUser.getId());
        userSecurity.setIsBlocked(false);
        userSecurityRepository.save(userSecurity);
    }
}
