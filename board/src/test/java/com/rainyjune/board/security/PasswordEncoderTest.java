package com.rainyjune.board.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PasswordEncoderTest {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Test
    void checkPassword() {
        String orgPassword = "admin";
        String serverPassword = "$2a$10$emZJLxV/H234ESKZ3OIUcuv3y6s1G8v6MsNZGQJASx8nlTvDbnM3G";
        String encodedPassword = passwordEncoder().encode(orgPassword);
        assertEquals(serverPassword, encodedPassword);
        System.out.println("org password : " + serverPassword);
        System.out.println("encoded password : " + encodedPassword);
    }


}
