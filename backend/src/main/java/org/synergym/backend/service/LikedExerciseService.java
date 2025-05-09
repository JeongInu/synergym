package org.synergym.backend.service;

import org.synergym.backend.dto.LikedExerciseDTO;

import java.util.List;

public interface LikedExerciseService {
    List<LikedExerciseDTO> getLikedExercisesByUserId(Long userId);
    void addLikedExercise(Long userId, Long exerciseId);
    void removeLikedExercise(Long userId, Long exerciseId);
}