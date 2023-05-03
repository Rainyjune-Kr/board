package com.rainyjune.board.security.service;

import com.rainyjune.board.security.dto.RegisterUserRequestDto;
import com.rainyjune.board.security.dto.RegisterUserResponseDto;
import com.rainyjune.board.security.entity.AuthMember;
import com.rainyjune.board.security.entity.AuthRole;
import com.rainyjune.board.security.exception.DuplicateUserException;
import com.rainyjune.board.security.repository.AuthMemberRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class RegisterUserService {
    @Autowired
    private AuthMemberRepository authMemberRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public RegisterUserResponseDto signUp(RegisterUserRequestDto registerUserRequestDto) {
        if (authMemberRepository.findByUserId(registerUserRequestDto.getUserId()).orElseGet(()->null) != null) {
            throw new DuplicateUserException("ID [" + registerUserRequestDto.getUserId() + "] already exists in system");
        }

        AuthRole role = AuthRole.builder().roleId("admin").build();
        List<AuthRole> authorities = new ArrayList<>();
        authorities.add(role);

        AuthMember member = AuthMember.builder()
                .userId(registerUserRequestDto.getUserId())
                .password(passwordEncoder.encode(registerUserRequestDto.getPassword()))
                .userName(registerUserRequestDto.getUserName())
                .userLevel(registerUserRequestDto.getUserLevel())
                .roles(authorities)
                .createUser(registerUserRequestDto.getCreateUser())
                .createDate(new Date())
                .build();

        return RegisterUserResponseDto.of(authMemberRepository.save(member));
    }
}
