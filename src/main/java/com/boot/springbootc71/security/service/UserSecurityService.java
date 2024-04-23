package com.boot.springbootc71.security.service;

import com.boot.springbootc71.security.model.UserSecurity;
import com.boot.springbootc71.security.repository.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService {

    private final UserSecurityRepository userSecurityRepository;

    @Autowired
    public UserSecurityService(UserSecurityRepository userSecurityRepository) {
        this.userSecurityRepository = userSecurityRepository;
    }
}
