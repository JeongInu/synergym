package org.synergym.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.synergym.backend.dto.ExerciseDTO;
import org.synergym.backend.dto.ExerciseResponseDTO;
import org.synergym.backend.entity.Exercise;

import java.util.List;

public interface ExerciseService {
    ExerciseResponseDTO getExerciseById(Integer id);
    List<ExerciseResponseDTO> getAllExercises();
    Page<ExerciseResponseDTO> searchExercises(String keyword,
                                              Pageable pageable);
    List<ExerciseResponseDTO> getExercisesByCategoryAndLanguage(String categoryName, String languageName);
    Exercise dtoToEntity(ExerciseDTO dto);
    ExerciseResponseDTO entityToResponseDto(Exercise exercise);
}