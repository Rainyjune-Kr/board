package com.rainyjune.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Table(name="TB_USER")
public class User {
    @Id
    private String userId;

    @Column
    private String password;

    @Column
    private String userName;

    @Column
    private String userLevel;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column
    private Date lastLoginDate;

    @Column
    private Date pwChgDate;

    @Column
    private int loginFailCnt;

    @Column
    private String expireChk;

    @Column
    private Date createDate;

    @Column
    private String createUser;

    @Column
    private Date updateDate;

    @Column
    private String updateUser;
}
