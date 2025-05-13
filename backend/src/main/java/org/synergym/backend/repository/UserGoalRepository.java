package org.synergym.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.synergym.backend.entity.UserGoal;


@Repository
public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {
}
