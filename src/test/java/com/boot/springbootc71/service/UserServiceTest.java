package com.boot.springbootc71.service;

import com.boot.springbootc71.model.User;
import com.boot.springbootc71.model.dto.UserCreateDto;
import com.boot.springbootc71.repository.UserRepository;
import com.boot.springbootc71.repository.UserRepositoryTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    static User user = new User();

    @BeforeAll
    public static void beforeAll() {
        user.setId(5L);
/*         Authentication authenticationMock = Mockito.mock(Authentication.class);
        SecurityContext securityContextMock = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContextMock.getAuthentication()).thenReturn(authenticationMock);
        SecurityContextHolder.setContext(securityContextMock);*/
    }

    @Test
    void getAllUsers_Success() {
        userService.getAllUsers();

        Mockito.verify(repository, Mockito.times(1)).customGetAllUsers();
    }

    @Test
    void createUser_Success() {
        Mockito.when(repository.save(any())).thenReturn(user);
        userService.createUser(new UserCreateDto());

        Mockito.verify(repository, Mockito.times(1)).save(any());
    }

    @Test
    void deleteUserById_Success() {
        userService.deleteUserById(132L);

        Mockito.verify(repository, Mockito.times(1)).deleteById(anyLong());
    }
}