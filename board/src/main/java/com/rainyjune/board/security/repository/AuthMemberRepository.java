package com.rainyjune.board.security.repository;

import com.rainyjune.board.security.entity.AuthMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthMemberRepository extends JpaRepository<AuthMember, String> {
        @EntityGraph(attributePaths = "roles")
        Optional<AuthMember> findByUserId(String userId);
}
