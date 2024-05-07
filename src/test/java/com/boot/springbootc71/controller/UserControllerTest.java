package com.boot.springbootc71.controller;

import com.boot.springbootc71.model.User;
import com.boot.springbootc71.model.dto.UserCreateDto;
import com.boot.springbootc71.security.filter.JwtFilter;
import com.boot.springbootc71.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private JwtFilter jwtAuthenticationFilter;

    static List<User> users = new ArrayList<>();
    static User user = new User();
    static UserCreateDto userCreateDto = new UserCreateDto();

    @BeforeAll
    public static void beforeAll() {
        user.setId(5L);
        users.add(user);
        userCreateDto.setUsername("user_test");
        userCreateDto.setUserPassword("qwerty123");
        userCreateDto.setAge(28);
    }

    @Test
    void getAllUsersTest_Success() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.is(5)));
    }

    @Test
    void createUserTest_ReturnIsCreated() throws Exception {
        Mockito.when(userService.createUser(any())).thenReturn(true);

        mockMvc.perform(post("/user")
                        .content(objectMapper.writeValueAsString(userCreateDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createUserTest_IsConflict() throws Exception {
        Mockito.when(userService.createUser(any())).thenReturn(false);

        mockMvc.perform(post("/user")
                        .content(objectMapper.writeValueAsString(userCreateDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void updateUserTest_IsNoContent() throws Exception {
        Mockito.when(userService.updateUser(any())).thenReturn(true);

        mockMvc.perform(put("/user")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteUserById_IsNoContent() throws Exception {
        Mockito.when(userService.deleteUserById(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/user/10"))
                .andExpect(status().isNoContent());
    }
}
