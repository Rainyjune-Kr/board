package com.rainyjune.board.admin.userList.repository;

import com.rainyjune.board.admin.userList.entity.UserListEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserListRepository extends CrudRepository<UserListEntity, String> {
    @Override
    ArrayList<UserListEntity> findAll();

    @Query(value = "SELECT * FROM tb_user WHERE user_id = :userId", nativeQuery = true)
    ArrayList<UserListEntity> findByUserId(String userId);
}
