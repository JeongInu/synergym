package org.synergym.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.synergym.backend.entity.LikedExercise;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikedExerciseRepository extends JpaRepository<LikedExercise, Long> {
    List<LikedExercise> findByUserId(Integer userId);
    Optional<LikedExercise> findByUserIdAndExerciseId(Integer userId, Long exerciseId);

}

