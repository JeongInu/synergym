package org.synergym.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.synergym.backend.entity.Category;
import org.synergym.backend.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer>, JpaSpecificationExecutor<Exercise> {
    List<Exercise> findByLanguage(Integer language);
    List<Exercise> findByCategory_Name(String category);
    List<Exercise> findByCategory_NameAndLanguage(String categoryName, Integer language);
}

