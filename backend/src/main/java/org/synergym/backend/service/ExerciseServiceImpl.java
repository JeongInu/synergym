package org.synergym.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.dto.ExerciseDTO;
import org.synergym.backend.entity.Exercise;
import org.synergym.backend.repository.ExerciseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Transactional
    @Override
    public ExerciseDTO getExerciseById(Long id) {
        // Optional을 사용하여 null 체크 및 예외 처리
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));
        return exercise.toDTO();  // toDTO 메서드 사용
    }

    @Transactional
    @Override
    public ExerciseDTO addExercise(ExerciseDTO exerciseDTO) {
        Exercise exercise = Exercise.builder()
                .name(exerciseDTO.getName())
                .description(exerciseDTO.getDescription())
                .category(exerciseDTO.getCategory())
                .muscles(exerciseDTO.getMuscles())
                .equipment(exerciseDTO.getEquipment())
                .build();

        exercise = exerciseRepository.save(exercise);
        return exercise.toDTO();  // 저장 후 DTO로 반환
    }

    @Transactional
    @Override
    public ExerciseDTO updateExercise(Long id, ExerciseDTO exerciseDTO) {
        // Optional을 사용하여 Exercise 엔티티를 찾고, 없을 경우 예외 처리
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found"));

        // DTO 정보를 Entity에 반영
        exercise.updateFromDTO(exerciseDTO);  // Entity에서 DTO의 정보를 업데이트하는 메서드 호출
        exercise = exerciseRepository.save(exercise); // 업데이트된 Entity 저장

        return exercise.toDTO();  // DTO 반환
    }

    @Transactional
    @Override
    public void deleteExercise(Long id) {
        exerciseRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(Exercise::toDTO)  // Entity를 DTO로 변환
                .collect(Collectors.toList());
    }
}
