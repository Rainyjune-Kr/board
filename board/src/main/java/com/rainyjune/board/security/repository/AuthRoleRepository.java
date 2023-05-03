package com.rainyjune.board.security.repository;

import com.rainyjune.board.security.entity.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRoleRepository extends JpaRepository<AuthRole, String> {
}
