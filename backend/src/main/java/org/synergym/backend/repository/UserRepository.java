package org.synergym.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.synergym.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}