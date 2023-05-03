package com.rainyjune.board.security.service;

import com.rainyjune.board.provider.TokenProvider;
import com.rainyjune.board.security.dto.LoginResponseDto;
import com.rainyjune.board.security.repository.AuthMemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    public LoginResponseDto authenticate(String userId, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String accessToken = tokenProvider.createToken(authentication);

        return LoginResponseDto.builder().accessToken(accessToken).build();
    }
}
