package com.rainyjune.board.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRefreshRequestDto {
    private String accessToken;

    private String refreshToken;
}
