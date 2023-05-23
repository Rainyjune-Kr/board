package com.rainyjune.board.security.provider;

import com.rainyjune.board.redis.service.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.stream.Collectors;

public class RefreshTokenProvider extends TokenProvider {
    private static final String WEIGHT_KEY = "token-weight";

    @Autowired
    private RedisService redisService;

    public RefreshTokenProvider (String secret, long tokenValidMinutes) {
        super(secret, tokenValidMinutes);
    }

    public String createToken(Authentication authentication) {
        String userId = authentication.getName();
        if (redisService.getValue("RefreshToken:" + userId) != null) {
            redisService.deleteValue("RefreshToken:" + userId);
        }

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + (this.tokenValidMinutes * 1000L * 60));

        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(validity)
                .compact();

        redisService.setValueWithTimeout("RefreshToken:" + userId, refreshToken, this.tokenValidMinutes);

        return refreshToken;
    }

    // extract token weight from token
    public long getTokenWeight(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return Long.valueOf(String.valueOf(claims.get(WEIGHT_KEY)));
    }

    public boolean validateRefreshToken (String userName, String token) {
        String redisRefreshToken = (String) redisService.getValue("RefreshToken:" + userName);
        if (redisRefreshToken == null) {
            return false;
        }

        if (validateToken(token)
                || !redisRefreshToken.equals(token)) {
            redisService.deleteValue("RefreshToken:" + userName);
            return false;
        }

        return true;
    }
}
