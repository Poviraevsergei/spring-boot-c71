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

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.customGetAllUsers();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Boolean deleteUserById(Long id) {
        userRepository.deleteById(id);
        return getUserById(id).isEmpty();
    }

    public Boolean createUser(UserCreateDto userFromDto) {
        User user = new User();
        user.setUserPassword(userFromDto.getUserPassword());
        user.setUsername(userFromDto.getUsername());
        user.setAge(userFromDto.getAge());
        user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        user.setChanged(Timestamp.valueOf(LocalDateTime.now()));
        User createdUser = userRepository.save(user);
        return getUserById(createdUser.getId()).isPresent();
    }

    public Boolean updateUser(Long id, String username, String password, Integer age) {
        Optional<User> userFromDBOptional = userRepository.findById(id);
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
            User updatedUser = userRepository.saveAndFlush(userFromDB);
            return userFromDB.equals(updatedUser);
        }
        return false;
    }
}