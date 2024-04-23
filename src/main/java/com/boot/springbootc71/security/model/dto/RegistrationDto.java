package com.boot.springbootc71.security.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class RegistrationDto {

    private String login;
    private String password;
    private String username;
    private Integer age;
}
