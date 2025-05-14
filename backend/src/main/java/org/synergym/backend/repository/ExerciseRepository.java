package org.synergym.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.synergym.backend.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer>, JpaSpecificationExecutor<Exercise> {
    List<Exercise> findByCategory(Integer category);
    List<Exercise> findByLanguage(Integer language);
    List<Exercise> findByCategoryAndLanguage(Integer category, Integer language);
}

