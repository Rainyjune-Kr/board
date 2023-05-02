package com.rainyjune.board.controller;

import com.rainyjune.board.dto.UserDto;
import com.rainyjune.board.repository.UserRepository;
import com.rainyjune.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("/users/allUsers")
    public ArrayList<UserDto> inquireAllUsers() {
        return new ArrayList<UserDto>(userService.findAll());
    }
}
