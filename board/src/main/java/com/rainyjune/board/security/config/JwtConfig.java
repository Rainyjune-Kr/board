package com.rainyjune.board.security.config;

import com.rainyjune.board.security.provider.RefreshTokenProvider;
import com.rainyjune.board.security.provider.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String accessTokenSecret;

    @Value("${jwt.access-token-valid-minutes}")
    private Long accessTokenValidMinutes;

    @Value("${jwt.refresh-token-valid-minutes}")
    private Long refreshTokenValidMinutes;

    @Bean(name="tokenProvider")
    public TokenProvider tokenProvider() {
        return new TokenProvider(accessTokenSecret, accessTokenValidMinutes);
    }

    @Bean(name="refreshTokenProvider")
    public RefreshTokenProvider refreshTokenProvider() {
        return new RefreshTokenProvider(accessTokenSecret, refreshTokenValidMinutes);
    }
}
