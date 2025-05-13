package org.synergym.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.synergym.backend.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);  // 이 메서드 추가
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}