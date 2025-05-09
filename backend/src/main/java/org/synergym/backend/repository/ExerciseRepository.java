package org.synergym.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.synergym.backend.entity.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
