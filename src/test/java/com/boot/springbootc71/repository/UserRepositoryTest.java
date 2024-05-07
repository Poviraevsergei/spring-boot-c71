package com.boot.springbootc71.repository;

import com.boot.springbootc71.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    static User user;

    @BeforeAll
    static void beforeAll() {
        user = new User();
        user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        user.setChanged(Timestamp.valueOf(LocalDateTime.now()));
        user.setUsername("USERNAME");
    }

    @Test
    void findAllTest_Success() {
        List<User> users = userRepository.findAll();

        Assertions.assertNotNull(users);
    }

    @Test
    void findByIdTest_Success() {
        User userFromDB = userRepository.findAll().get(0);
        Optional<User> user = userRepository.findById(userFromDB.getId());

        Assertions.assertTrue(user.isPresent());
    }

    @Test
    void saveTest_Success() {
        User savedUser = userRepository.save(user);
        Optional<User> user = userRepository.findById(savedUser.getId());

        Assertions.assertTrue(user.isPresent());
    }

    @Test
    void updateTest_Success() {
        User userSaved = userRepository.save(user);
        userSaved.setUsername("NEW_VALUE_FROM_TEST");
        User resultUser = userRepository.saveAndFlush(userSaved);

        Assertions.assertEquals(resultUser.getUsername(), "NEW_VALUE_FROM_TEST");
    }

    @Test
    void deleteByIdTest_Success() {
        User userSaved = userRepository.save(user);
        userRepository.deleteById(userSaved.getId());
        Optional<User> resultUser = userRepository.findById(userSaved.getId());
        Assertions.assertFalse(resultUser.isPresent());
    }
}