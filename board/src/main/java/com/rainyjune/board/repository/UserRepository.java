package com.rainyjune.board.repository;

import com.rainyjune.board.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User, String> {
    @Override
    ArrayList<User> findAll();

    @Query(value = "SELECT * FROM tb_user WHERE user_id = :userId", nativeQuery = true)
    ArrayList<User> findByUserId(String userId);
}
