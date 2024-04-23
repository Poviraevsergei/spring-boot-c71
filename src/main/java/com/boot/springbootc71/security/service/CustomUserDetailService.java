package com.boot.springbootc71.security.service;

import com.boot.springbootc71.security.model.UserSecurity;
import com.boot.springbootc71.security.repository.UserSecurityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserSecurityRepository userSecurityRepository;

    @Autowired
    public CustomUserDetailService(UserSecurityRepository userSecurityRepository) {
        this.userSecurityRepository = userSecurityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserSecurity> securityInfoOptional = userSecurityRepository.findByUserLogin(username);
        if (securityInfoOptional.isEmpty()){
            throw new UsernameNotFoundException("Username not found: " + username);
        }
        UserSecurity security = securityInfoOptional.get();
        return User.builder()
                .username(security.getUserLogin())
                .password(security.getUserPassword())
                .roles(security.getRole().toString())
                .build();
    }
}
