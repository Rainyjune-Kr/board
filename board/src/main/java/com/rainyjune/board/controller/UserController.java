package com.rainyjune.board.controller;

import com.rainyjune.board.repository.UserRepository;
import com.rainyjune.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


}
