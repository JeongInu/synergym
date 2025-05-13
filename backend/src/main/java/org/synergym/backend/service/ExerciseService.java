package org.synergym.backend.service;

import org.synergym.backend.dto.ExerciseDTO;
import org.synergym.backend.dto.ExerciseResponseDTO;
import org.synergym.backend.entity.Exercise;

import java.util.List;

public interface ExerciseService {
    ExerciseResponseDTO getExerciseById(Integer id);
    Integer addExercise(ExerciseDTO exerciseDTO);
    void deleteExercise(Integer id);
    List<ExerciseResponseDTO> getAllExercises();

    Exercise dtoToEntity(ExerciseDTO dto);
    ExerciseResponseDTO entityToResponseDto(Exercise exercise);
}