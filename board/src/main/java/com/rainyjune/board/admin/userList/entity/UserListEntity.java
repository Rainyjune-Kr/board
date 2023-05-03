package com.rainyjune.board.admin.userList.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name="TB_USER")
public class UserListEntity {
    @Id
    @Column(name="user_id")
    private String userId;

    @Column
    private String password;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_level")
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
    private Integer loginFailCnt;

    @Column
    private String expireChk;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="create_user")
    private String createUser;

    @Column
    private Date updateDate;

    @Column
    private String updateUser;
}
