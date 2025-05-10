package org.synergym.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.dto.ExerciseDTO;
import org.synergym.backend.entity.Exercise;
import org.synergym.backend.repository.ExerciseRepository;

import java.util.List;
import java.util.Optional;
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
    public ExerciseDTO getExerciseById(Integer id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        return entityToDto(exercise.get());  // toDTO 메서드 사용
    }

    @Transactional
    @Override
    public Integer addExercise(ExerciseDTO exerciseDTO) {
        Exercise exercise = dtoToEntity(exerciseDTO);
        exercise = exerciseRepository.save(exercise);
        return exercise.getId();  // 저장 후 DTO로 반환
    }

    @Transactional
    @Override
    public void updateExercise(Integer id, ExerciseDTO exerciseDTO) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exercise.get().updateFromDTO(exerciseDTO);  // Entity에서 DTO의 정보를 업데이트하는 메서드 호출
        exerciseRepository.save(exercise.get()); // 업데이트된 Entity 저장
    }

    @Transactional
    @Override
    public void deleteExercise(Integer id) {
        exerciseRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(this::entityToDto)  // Entity를 DTO로 변환
                .collect(Collectors.toList());
    }
}
