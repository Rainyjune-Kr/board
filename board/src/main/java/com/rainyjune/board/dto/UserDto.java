package com.rainyjune.board.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rainyjune.board.entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UserDto {
    @JsonProperty("user_id")
    private String userId;
    private String password;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("user_level")
    private String userLevel;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;
    @JsonProperty("last_login_date")
    private Date lastLoginDate;
    @JsonProperty("pw_chg_date")
    private Date pwChgDate;
    @JsonProperty("login_fail_cnt")
    private int loginFailCnt;
    @JsonProperty("expire_chk")
    private String expireChk;
    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("create_user")
    private String createUser;
    @JsonProperty("update_date")
    private Date updateDate;
    @JsonProperty("update_user")
    private String updateUser;

    public static UserDto createUserDto(User user) {
        UserDto resultObj = new UserDto(
                user.getUserId(),
                user.getPassword(),
                user.getUserName(),
                user.getUserLevel(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getLastLoginDate(),
                user.getPwChgDate(),
                user.getLoginFailCnt(),
                user.getExpireChk(),
                user.getCreateDate(),
                user.getCreateUser(),
                user.getUpdateDate(),
                user.getUpdateUser()
        );

        return resultObj;
    }
}
