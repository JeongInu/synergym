package org.synergym.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.synergym.backend.dto.ExerciseDTO;
import org.synergym.backend.dto.ExerciseResponseDTO;
import org.synergym.backend.entity.Exercise;

import java.util.List;

public interface ExerciseService {
    ExerciseResponseDTO getExerciseById(Integer id);
    Integer addExercise(ExerciseDTO exerciseDTO);
    void deleteExercise(Integer id);
    List<ExerciseResponseDTO> getAllExercises();
    List<ExerciseResponseDTO> getExercisesByCategoryAndLanguage(Integer category, Integer language);
    Page<ExerciseResponseDTO> searchExercises(Integer category,
                                              Integer language,
                                              List<Integer> muscles,
                                              List<Integer> equipment,
                                              String keyword,
                                              Pageable pageable);
    Exercise dtoToEntity(ExerciseDTO dto);
    ExerciseResponseDTO entityToResponseDto(Exercise exercise);
}