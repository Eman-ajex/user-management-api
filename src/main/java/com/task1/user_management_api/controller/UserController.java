package com.task1.user_management_api.controller;

import com.task1.user_management_api.dto.UserRequest;

import com.task1.user_management_api.entity.User;

import com.task1.user_management_api.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/users")

@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping

    public User createUser(@RequestBody UserRequest request) {

        User user = User.builder()

                .name(request.getName())

                .employeeType(request.getEmployeeType())

                .build();

        return userService.save(user);

    }

    @GetMapping("/{id}")

    public User getUser(@PathVariable Long id) {

        return userService.getUser(id);

    }

    @PutMapping("/{id}")

    public User updateUser(

            @PathVariable Long id,

            @RequestBody UserRequest request) {

        User user = User.builder()

                .name(request.getName())

                .employeeType(request.getEmployeeType())

                .build();

        return userService.updateUser(id, user);

    }

    @DeleteMapping("/{id}")

    public String deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return "User deleted successfully";

    }

}
