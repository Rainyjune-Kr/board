package com.rainyjune.board.security.service;

import com.rainyjune.board.redis.service.RedisService;
import com.rainyjune.board.security.adapter.AccountAdapter;
import com.rainyjune.board.security.dto.TokenRefreshResponseDto;
import com.rainyjune.board.security.entity.AuthMember;
import com.rainyjune.board.security.provider.RefreshTokenProvider;
import com.rainyjune.board.security.provider.TokenProvider;
import com.rainyjune.board.security.dto.LoginResponseDto;
import com.rainyjune.board.security.repository.AuthMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private RefreshTokenProvider refreshTokenProvider;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    private AuthMemberRepository authMemberRepository;

    @Autowired
    private RedisService redisService;

    public LoginResponseDto authenticate(String userId, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String accessToken = tokenProvider.createToken(authentication);

        Long tokenWeight = ((AccountAdapter)authentication.getPrincipal()).getAuthMember().getTokenWeight();
        String refreshToken = refreshTokenProvider.createToken(authentication);

        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public TokenRefreshResponseDto refreshToken(String token, String refreshToken) {
        String resolvedToken = resolveToken(token);
        Authentication authentication = refreshTokenProvider.getAuthentication(resolvedToken);

        AuthMember authMember = authMemberRepository.findByUserId(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user ID [" + authentication.getName() + "]"));

        String redisRefreshToken = (String) redisService.getValue("RefreshToken:" + authentication.getName());
        if (redisRefreshToken == null) {
            return null;
        }

        if (!refreshTokenProvider.validateToken(refreshToken)
            || !redisRefreshToken.equals(refreshToken)) {
            redisService.deleteValue("RefreshToken:" + authentication.getName());
            return null;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        redisService.deleteValue("RefreshToken:" + authentication.getName());

        String accessToken = tokenProvider.createToken(authentication);
        String newRefreshToken = refreshTokenProvider.createToken(authentication);

        return TokenRefreshResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    public void logOut(String accessToken) {
        String resolvedToken = resolveToken(accessToken);
        String userId = tokenProvider.getAuthentication(resolvedToken).getName();

        String redisRefreshToken = (String) redisService.getValue("RefreshToken:" + userId);
        if (redisRefreshToken != null) {
            redisService.deleteValue("RefreshToken:" + userId);
        }
    }

    private String resolveToken (String token) {
        if (token != null
            && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return null;
    }

    public boolean validateAccessToken (String accessToken) {
        return tokenProvider.validateToken(accessToken);
    }
}
