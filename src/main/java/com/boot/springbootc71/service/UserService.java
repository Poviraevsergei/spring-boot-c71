package com.boot.springbootc71.service;

import com.boot.springbootc71.model.User;
import com.boot.springbootc71.model.dto.UserCreateDto;
import com.boot.springbootc71.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final User user;

    @Autowired
    public UserService(UserRepository userRepository, User user) {
        this.userRepository = userRepository;
        this.user = user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(userRepository.findById(id));
    }

    public Boolean deleteUserById(Long id) {
        return userRepository.deleteUser(id);
    }

    public Boolean createUser(UserCreateDto userFromDto) {
        user.setUserPassword(userFromDto.getUserPassword());
        user.setUsername(userFromDto.getUsername());
        user.setAge(userFromDto.getAge());
        user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        user.setChanged(Timestamp.valueOf(LocalDateTime.now()));
        return userRepository.createUser(user);
    }

    public Boolean updateUser(Long id, String username, String password, Integer age) {
        Optional<User> userFromDBOptional = Optional.ofNullable(userRepository.findById(id));
        if (userFromDBOptional.isPresent()){
            User userFromDB = userFromDBOptional.get();
            if (username != null) {
                userFromDB.setUsername(username);
            }
            if (password != null) {
                userFromDB.setUserPassword(password);
            }
            if (age != null){
                userFromDB.setAge(age);
            }
            userFromDB.setChanged(Timestamp.valueOf(LocalDateTime.now()));
            return userRepository.updateUser(userFromDB);
        }
        return false;
    }
}