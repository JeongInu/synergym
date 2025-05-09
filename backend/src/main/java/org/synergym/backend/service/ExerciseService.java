package org.synergym.backend.service;

import org.synergym.backend.dto.ExerciseDTO;

import java.util.List;

public interface ExerciseService {
    ExerciseDTO getExerciseById(Long id);
    ExerciseDTO addExercise(ExerciseDTO exerciseDTO);
    ExerciseDTO updateExercise(Long id, ExerciseDTO exerciseDTO);
    void deleteExercise(Long id);
    List<ExerciseDTO> getAllExercises();
}