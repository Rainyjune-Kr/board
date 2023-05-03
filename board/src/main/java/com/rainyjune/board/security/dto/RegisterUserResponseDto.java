package com.rainyjune.board.security.dto;

import com.rainyjune.board.security.entity.AuthMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserResponseDto {
    private String userId;
    private String userName;
    private List<String> roles;

    public static RegisterUserResponseDto of (AuthMember authMember) {
        if (authMember == null) return null;

        return RegisterUserResponseDto.builder()
                .userId(authMember.getUserId())
                .userName(authMember.getUserName())
                .roles(authMember.getRoles().stream()
                        .map(authority -> authority.getRoleId())
                        .collect(Collectors.toList()))
                .build();
    }
}
