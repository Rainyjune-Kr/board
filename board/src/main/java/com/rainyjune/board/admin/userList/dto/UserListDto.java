package com.rainyjune.board.admin.userList.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rainyjune.board.admin.userList.entity.UserListEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UserListDto {
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
    private Integer loginFailCnt;
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

    public static UserListDto createUserDto(UserListEntity userListEntity) {
        UserListDto resultObj = new UserListDto(
                userListEntity.getUserId(),
                userListEntity.getPassword(),
                userListEntity.getUserName(),
                userListEntity.getUserLevel(),
                userListEntity.getPhoneNumber(),
                userListEntity.getEmail(),
                userListEntity.getLastLoginDate(),
                userListEntity.getPwChgDate(),
                userListEntity.getLoginFailCnt(),
                userListEntity.getExpireChk(),
                userListEntity.getCreateDate(),
                userListEntity.getCreateUser(),
                userListEntity.getUpdateDate(),
                userListEntity.getUpdateUser()
        );

        return resultObj;
    }
}
