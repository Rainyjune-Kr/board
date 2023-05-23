package com.rainyjune.board.security.util;

import com.rainyjune.board.security.provider.RefreshTokenProvider;
import com.rainyjune.board.security.provider.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;

    private final RefreshTokenProvider refreshTokenProvider;

    public JwtFilter(TokenProvider tokenProvider, RefreshTokenProvider refreshTokenProvider) {
        this.tokenProvider = tokenProvider;
        this.refreshTokenProvider = refreshTokenProvider;
    }

    // DoFilter는 토큰의 인증정보를 Security Context에 저장한다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = getAccessToken(request);
        String refreshToken = getRefreshToken(request);
        String requestURI = request.getRequestURI();

        if (tokenProvider.validateToken(accessToken)) {
            Authentication authentication = tokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.debug("'{}' authentication is saved on Security Context, URI: {}", authentication.getName(), requestURI);
        } else if (!tokenProvider.validateToken(accessToken)
                    && refreshToken != null) {
            Authentication authentication = tokenProvider.getAuthentication(refreshToken);
            if (refreshTokenProvider.validateRefreshToken(authentication.getName(), refreshToken)) {
                String reissuedToken = tokenProvider.createToken(authentication, refreshToken);
                response.setHeader(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + reissuedToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.debug("'{}' authentication is saved on Security Context(Access Token Reissued), URI: {}", authentication.getName(), requestURI);
            }
        } else {
            logger.debug("There is no valid JWT token, URI: {}", requestURI);
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(HttpServletRequest request) {
        if (request.getHeader("authorization") != null) {
            return request.getHeader("authorization").substring(7);
        }

        return null;
    }

    private String getRefreshToken(HttpServletRequest request) {
        CookieUtil cookieUtil = new CookieUtil(request);
        return cookieUtil.getCookieValue("refresh-token");
    }
}
