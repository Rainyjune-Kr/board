package com.rainyjune.board.security.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="tb_role")
@Getter
@NoArgsConstructor
public class AuthRole {
    @Id
    @Column(name="role_id")
    private String roleId;

    @Builder
    public AuthRole(String roleId) {
        this.roleId = roleId;
    }
}
