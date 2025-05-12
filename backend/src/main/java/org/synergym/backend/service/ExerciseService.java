package org.synergym.backend.service;

import org.synergym.backend.dto.ExerciseDTO;
import org.synergym.backend.entity.Exercise;

import java.util.List;

public interface ExerciseService {
    ExerciseDTO getExerciseById(Integer id);
    Integer addExercise(ExerciseDTO exerciseDTO);
    void updateExercise(Integer id, ExerciseDTO exerciseDTO);
    void deleteExercise(Integer id);
    List<ExerciseDTO> getAllExercises();

    // DTO로 변환하는 메서드
    default ExerciseDTO entityToDto(Exercise exercise) {
        return ExerciseDTO.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .description(exercise.getDescription())
                .category(exercise.getCategory())
                .muscles(exercise.getMuscles())
                .equipment(exercise.getEquipment())
                .build();
    }

    default Exercise dtoToEntity(ExerciseDTO dto){
        return Exercise.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .muscles(dto.getMuscles())
                .equipment(dto.getEquipment())
                .build();
    }
}