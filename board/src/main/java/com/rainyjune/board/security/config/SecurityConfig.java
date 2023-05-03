package com.rainyjune.board.security.config;

import com.rainyjune.board.provider.TokenProvider;
import com.rainyjune.board.security.JwtAccessDeniedHandler;
import com.rainyjune.board.security.JwtAuthEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private CorsFilter corsFilter;

    @Autowired
    private JwtAuthEntryPoint entryPoint;

    @Autowired
    private JwtAccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Cross site request forgery. rest api에서는 권한이 필요한 요청을 위해 인증 정보를 포함시켜야 하고 서버에 인증 정보를 저장하지 않기 때문에 필요 없다.
                // usernamepasswordauthenticationfilter 전에 jwtauthenticationfilter먼저 실행하겠음
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt를 사용하기 때문에 세션도 사용하지 않음
                .and()
                .formLogin().disable() // form based authentication 사용하지 않음
                .httpBasic().disable() // http basic authentication 사용하지 않음
                .authorizeRequests()
//                .requestMatchers("/", "/**").permitAll()
                .requestMatchers("/api/auth/*").permitAll() // /api/user 요청에 관해 모두 접근가능하게 함
//                .requestMatchers("/users").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
        return http.build();
    }
}
