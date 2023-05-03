package com.rainyjune.board.admin.userList.controller;

import com.rainyjune.board.admin.userList.dto.UserListDto;
import com.rainyjune.board.admin.userList.repository.UserListRepository;
import com.rainyjune.board.admin.userList.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserListController {
    @Autowired
    private UserListRepository userListRepository;

    @Autowired
    private UserListService userListService;

    @RequestMapping("/users/allUsers")
    public ArrayList<UserListDto> inquireAllUsers() {
        return new ArrayList<UserListDto>(userListService.findAll());
    }
}
