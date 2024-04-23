package com.boot.springbootc71.service;

import com.boot.springbootc71.aop.TimeAop;
import com.boot.springbootc71.model.User;
import com.boot.springbootc71.model.dto.UserCreateDto;
import com.boot.springbootc71.repository.UserRepository;
import com.boot.springbootc71.security.model.UserSecurity;
import com.boot.springbootc71.security.repository.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserSecurityRepository userSecurityRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserSecurityRepository userSecurityRepository) {
        this.userRepository = userRepository;
        this.userSecurityRepository = userSecurityRepository;
    }

    @TimeAop
    public List<User> getAllUsers() {
        return userRepository.customGetAllUsers();
    }

    public Optional<User> getInfoAboutCurrentUser(String username){
       Optional<UserSecurity> userSecurity = userSecurityRepository.findByUserLogin(username);
       if (userSecurity.isEmpty()){
           return Optional.empty();
       }
       return userRepository.findById(userSecurity.get().getUserId());
    }

    @TimeAop
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Boolean deleteUserById(Long id) {
        Optional<User> userCheck = getUserById(id);
        if (userCheck.isEmpty()){
            return false;
        }
        userRepository.deleteById(id);
        return userRepository.findById(id).isEmpty();
    }

    public Boolean createUser(UserCreateDto userFromDto) {
        User user = new User();
        user.setUsername(userFromDto.getUsername());
        user.setAge(userFromDto.getAge());
        user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        user.setChanged(Timestamp.valueOf(LocalDateTime.now()));
        User createdUser = userRepository.save(user);
        return getUserById(createdUser.getId()).isPresent();
    }

    public Boolean updateUser(User user) {
        Optional<User> userFromDBOptional = userRepository.findById(user.getId());
        if (userFromDBOptional.isPresent()){
            User userFromDB = userFromDBOptional.get();
            if (user.getUsername() != null) {
                userFromDB.setUsername(user.getUsername());
            }
            if (user.getAge() != null){
                userFromDB.setAge(user.getAge());
            }
            userFromDB.setChanged(Timestamp.valueOf(LocalDateTime.now()));
            User updatedUser = userRepository.saveAndFlush(userFromDB);
            return userFromDB.equals(updatedUser);
        }
        return false;
    }
}