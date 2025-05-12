package org.synergym.backend.service;

import org.synergym.backend.dto.ExerciseDTO;
import org.synergym.backend.dto.ExerciseResponseDTO;
import org.synergym.backend.entity.Exercise;

import java.util.List;

public interface ExerciseService {
    ExerciseResponseDTO getExerciseById(Integer id);
    Integer addExercise(ExerciseDTO exerciseDTO);
    void updateExercise(Integer id, ExerciseDTO exerciseDTO);
    void deleteExercise(Integer id);
    List<ExerciseResponseDTO> getAllExercises();

    default ExerciseDTO entityToDto(Exercise exercise) {
        return ExerciseDTO.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .description(exercise.getDescription())
                .creationDate(exercise.getCreationDate())
                .license(exercise.getLicense())
                .category(exercise.getCategory())
                .language(exercise.getLanguage())
                .muscles(exercise.getMuscles())
                .musclesSecondary(exercise.getMusclesSecondary())
                .equipment(exercise.getEquipment())
                .translations(exercise.getTranslations())
                .build();
    }

    default ExerciseResponseDTO entityToResponseDto(Exercise exercise) {
        return ExerciseResponseDTO.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .description(exercise.getDescription())
                .category(exercise.getCategory())
                .language(exercise.getLanguage())
                .build();
    }

    default Exercise dtoToEntity(ExerciseDTO dto) {
        return Exercise.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .creationDate(dto.getCreationDate())
                .license(dto.getLicense())
                .category(dto.getCategory())
                .language(dto.getLanguage())
                .muscles(dto.getMuscles())
                .musclesSecondary(dto.getMusclesSecondary())
                .equipment(dto.getEquipment())
                .translations(dto.getTranslations())
                .build();
    }
}