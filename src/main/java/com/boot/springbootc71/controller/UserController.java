package com.boot.springbootc71.controller;

import com.boot.springbootc71.exception.CustomValidException;
import com.boot.springbootc71.model.User;
import com.boot.springbootc71.model.dto.UserCreateDto;
import com.boot.springbootc71.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getAllUsers(ModelAndView modelAndView) {
        List<User> users = userService.getAllUsers();
        modelAndView.setViewName(users.isEmpty() ? "empty" : "get_users");
        modelAndView.addObject("users", users);
        modelAndView.setStatus(HttpStatusCode.valueOf(200));
        return modelAndView;
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, ModelMap modelMap, HttpServletResponse response) { //@PathVariable если мы хотим достать из пути
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            modelMap.addAttribute("user", user.get());
            response.setStatus(200);
            return "get_user_by_id";
        }
        response.setStatus(404);
        return "empty";
    }

    @PostMapping
    public String createUser(@ModelAttribute @Valid UserCreateDto user, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            throw new CustomValidException(bindingResult.getAllErrors().toString());
        }
        if (userService.createUser(user)) {
            response.setStatus(201);
            return "success";
        }
        response.setStatus(409);
        return "failure";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("id") Long id,
                             @RequestParam("age") Integer age,
                             HttpServletResponse response) {
        if (userService.updateUser(id, username, password, age)) {
            response.setStatus(204);
            return "success";
        }
        response.setStatus(409);
        return "failure";
    }

    @PostMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long id, HttpServletResponse response) {
        if (userService.deleteUserById(id)) {
            response.setStatus(204);
            return "success";
        }
        response.setStatus(409);
        return "failure";
    }
}