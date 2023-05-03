package com.rainyjune.board.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequestDto {
    private String userId;

    private String password;

    private String userName;

    private String userLevel;

    private String createUser;

    private Date createDate;
}
