package com.boot.springbootc71.security.service;

import com.boot.springbootc71.exception.SameUserInDatabase;
import com.boot.springbootc71.security.model.UserSecurity;
import com.boot.springbootc71.security.model.dto.RegistrationDto;
import com.boot.springbootc71.security.repository.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSecurityService {

    private final UserSecurityRepository userSecurityRepository;

    @Autowired
    public UserSecurityService(UserSecurityRepository userSecurityRepository) {
        this.userSecurityRepository = userSecurityRepository;
    }

    public void registration(RegistrationDto registrationDto){
        Optional<UserSecurity> security = userSecurityRepository.findByUserLogin(registrationDto.getLogin());
        if (security.isPresent()){
            throw new SameUserInDatabase(registrationDto.getLogin());
        }
    }
}
