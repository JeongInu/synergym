package org.synergym.backend.service;

import org.synergym.backend.dto.LikedExerciseDTO;
import org.synergym.backend.entity.Exercise;
import org.synergym.backend.entity.LikedExercise;
import org.synergym.backend.entity.User;

import java.util.List;

public interface LikedExerciseService {
    List<LikedExerciseDTO> getLikedExercisesByUserId(Integer userId);
    Integer addLikedExercise(Integer userId, Integer exerciseId);
    void removeLikedExercise(Integer userId, Integer exerciseId);

    // DTO로 변환하는 메서드
    default LikedExerciseDTO entityToDto(LikedExercise likedExercise) {
        return LikedExerciseDTO.builder()
                .id(likedExercise.getId())
                .userId(likedExercise.getUser().getId())  // User의 ID만 반환
                .exerciseId(likedExercise.getExercise().getId())  // Exercise의 ID만 반환
                .build();
    }

    default LikedExercise dtoToEntity(User user, Exercise exercise) {
        return LikedExercise.builder()
                .user(user)
                .exercise(exercise)
                .build();
    }
}