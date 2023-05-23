package com.rainyjune.board.security.controller;

import com.rainyjune.board.security.dto.LoginRequestDto;
import com.rainyjune.board.security.dto.LoginResponseDto;
import com.rainyjune.board.security.dto.TokenRefreshRequestDto;
import com.rainyjune.board.security.dto.TokenRefreshResponseDto;
import com.rainyjune.board.security.service.AuthService;
import com.rainyjune.board.security.util.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    private final long COOKIE_EXPIRATION_TIME = 86400L; // 1 day

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto token = authService.authenticate(loginRequestDto.getUserId(), loginRequestDto.getPassword());

        HttpCookie httpCookie = ResponseCookie.from("refresh-token", token.getRefreshToken())
                .maxAge(COOKIE_EXPIRATION_TIME)
                .httpOnly(true)
                .secure(true)
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, httpCookie.toString());
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());

        return new ResponseEntity<>(token, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<TokenRefreshResponseDto> refreshToken(@CookieValue(name="refresh-token") String refreshToken,
                                                                @RequestHeader("Authorization") String accessToken) {
        TokenRefreshResponseDto dto = authService.refreshToken(accessToken, refreshToken);

        if (dto != null) {
            ResponseCookie responseCookie = ResponseCookie.from("refresh-token", dto.getRefreshToken())
                    .maxAge(COOKIE_EXPIRATION_TIME)
                    .httpOnly(true)
                    .secure(true)
                    .build();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + dto.getAccessToken())
                    .build();
        } else {
            ResponseCookie responseCookie = ResponseCookie.from("refresh-token", "")
                    .maxAge(0)
                    .path("/")
                    .build();

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                    .build();
        }
    }

    @PostMapping("/validateAccessToken")
    public ResponseEntity<?> validateAccessToken(@RequestHeader("Authorization") String accessToken) {
        if (!authService.validateAccessToken(accessToken)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logOut(@RequestHeader("Authorization") String accessToken) {
        authService.logOut(accessToken);
        ResponseCookie responseCookie = ResponseCookie.from("refresh-token", "")
                .maxAge(0)
                .path("/")
                .build();

        return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.SET_COOKIE, responseCookie.toString()).build();
    }
}
