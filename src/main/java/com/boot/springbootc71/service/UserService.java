package com.boot.springbootc71.service;

import com.boot.springbootc71.exception.CustomValidException;
import com.boot.springbootc71.model.User;
import com.boot.springbootc71.model.dto.UserCreateDto;
import com.boot.springbootc71.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
        if (userFromDBOptional.isPresent()) {
            User userFromDB = userFromDBOptional.get();
            if (username != null) {
                userFromDB.setUsername(username);
            }
            if (password != null) {
                userFromDB.setUserPassword(password);
            }
            if (age != null) {
                userFromDB.setAge(age);
            }
            userFromDB.setChanged(Timestamp.valueOf(LocalDateTime.now()));
            User updatedUser = userRepository.saveAndFlush(userFromDB);
            return userFromDB.equals(updatedUser);
        }
        return false;
    }

    public List<User> getUsersAndSortByField(String field) {
        return userRepository.findAll(Sort.by(field).descending());
    }

    public List<User> getUsersWithPagination(int size, int page) {
        return userRepository.findAll(Pageable.ofSize(size).withPage(page)).getContent();
    }

    public void second() throws Exception {
        testTransactional();
    }

    @Transactional(rollbackFor = Exception.class)
    public void testTransactional() throws Exception {
        User u1 = new User();
        u1.setAge(50);
        u1.setUsername("user1");
        u1.setChanged(Timestamp.valueOf(LocalDateTime.now()));
        u1.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        u1.setUserPassword("userpassword1");

        User u2 = new User();
        u2.setAge(50);
        u2.setUsername("user2");
        u2.setChanged(Timestamp.valueOf(LocalDateTime.now()));
        u2.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        u2.setUserPassword("userpassword2");

        userRepository.save(u1);
        if (true)throw new Exception();
        userRepository.save(u2);
    }
}