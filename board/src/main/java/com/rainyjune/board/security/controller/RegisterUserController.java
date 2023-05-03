package com.rainyjune.board.security.controller;

import com.rainyjune.board.security.dto.RegisterUserRequestDto;
import com.rainyjune.board.security.dto.RegisterUserResponseDto;
import com.rainyjune.board.security.service.RegisterUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class RegisterUserController {
    @Autowired
    private final RegisterUserService service;

    @PostMapping("/signUp")
    public ResponseEntity<RegisterUserResponseDto> signUp(@RequestBody RegisterUserRequestDto dto) {
        RegisterUserResponseDto userInfo = service.signUp(dto);

        return ResponseEntity.ok(userInfo);
    }
}
