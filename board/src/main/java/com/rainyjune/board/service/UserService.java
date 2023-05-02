package com.rainyjune.board.service;

import com.rainyjune.board.dto.UserDto;
import com.rainyjune.board.entity.User;
import com.rainyjune.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserDto.createUserDto(user))
                .collect(Collectors.toList());
    }

    public List<UserDto> findUserById(String userId) {
        return userRepository.findByUserId(userId)
                .stream()
                .map(user -> UserDto.createUserDto(user))
                .collect(Collectors.toList());
    }
}
