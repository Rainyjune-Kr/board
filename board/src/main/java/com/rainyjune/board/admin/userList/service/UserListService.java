package com.rainyjune.board.admin.userList.service;

import com.rainyjune.board.admin.userList.dto.UserListDto;
import com.rainyjune.board.admin.userList.repository.UserListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserListService {
    @Autowired
    private UserListRepository userListRepository;

    public List<UserListDto> findAll() {
        return userListRepository.findAll()
                .stream()
                .map(user -> UserListDto.createUserDto(user))
                .collect(Collectors.toList());
    }

    public List<UserListDto> findUserById(String userId) {
        return userListRepository.findByUserId(userId)
                .stream()
                .map(user -> UserListDto.createUserDto(user))
                .collect(Collectors.toList());
    }
}
