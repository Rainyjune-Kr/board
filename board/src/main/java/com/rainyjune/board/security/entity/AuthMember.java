package com.rainyjune.board.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Entity
@Table(name="tb_user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthMember {
    @Id
    @Column(name="user_id")
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(name="user_name", nullable = false)
    private String userName;

    @Column(name="user_level", nullable = false)
    private String userLevel;

    @Column(name="token_weight")
    private Long tokenWeight;

    @ManyToMany
    @JoinTable(
            name = "tb_user_role",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName = "user_id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName = "role_id")})
    private List<AuthRole> roles;

    @Column(name="create_user", nullable = false)
    private String createUser;

    @Column(name="create_date", nullable = false)
    private Date createDate;

    public void increaseTokenWeight() {
        this.tokenWeight++;
    }
}
