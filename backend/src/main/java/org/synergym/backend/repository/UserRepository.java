package org.synergym.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.synergym.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}