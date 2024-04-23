package com.boot.springbootc71.security.model.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private String login;
    private String password;
    private String username;
    private Integer age;
}
