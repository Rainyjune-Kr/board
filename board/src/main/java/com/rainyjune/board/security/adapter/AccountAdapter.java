package com.rainyjune.board.security.adapter;

import com.rainyjune.board.security.entity.AuthMember;
import com.rainyjune.board.security.entity.AuthRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;

public class AccountAdapter extends User {
    private AuthMember authMember;

    public AccountAdapter(AuthMember authMember) {
        super(authMember.getUserId(), authMember.getPassword(), authorities(authMember.getRoles()));
        this.authMember = authMember;
    }

    public AuthMember getAuthMember() {
        return this.authMember;
    }

    private static List<GrantedAuthority> authorities(List<AuthRole> roles) {
        return roles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRoleId()))
                .collect(Collectors.toList());
    }
}
