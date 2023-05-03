package com.rainyjune.board.security.service;

import com.rainyjune.board.security.entity.AuthMember;
import com.rainyjune.board.security.repository.AuthMemberRepository;
import com.rainyjune.board.security.adapter.AccountAdapter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AuthMemberRepository authMemberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthMember authMember = authMemberRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user ID [" + username + "]"));

        return new AccountAdapter(authMember);
    }
}
