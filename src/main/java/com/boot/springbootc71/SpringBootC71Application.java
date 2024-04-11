package com.boot.springbootc71;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
        title = "Group_71_lesson_project",
        description = "Description :)",
        contact = @Contact(name = "Arnold Sharcineger",
                url = "bat9vzdanii.by",
                email = "maminboss@gmail.com")
))
@SpringBootApplication
public class SpringBootC71Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootC71Application.class, args);
    }
}
