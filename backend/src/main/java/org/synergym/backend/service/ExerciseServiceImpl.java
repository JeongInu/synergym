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

    @Transactional(readOnly = true)  // 읽기 전용 트랜잭션으로 변경
    @Override
    public ExerciseDTO getExerciseById(Integer id) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + id));
        return entityToDto(exercise);
    }

    @Transactional
    @Override
    public Integer addExercise(ExerciseDTO exerciseDTO) {
        Exercise exercise = dtoToEntity(exerciseDTO);
        exercise = exerciseRepository.save(exercise);
        return exercise.getId();
    }

    @Transactional
    @Override
    public void updateExercise(Integer id, ExerciseDTO exerciseDTO) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + id));
        
        exercise.changeName(exerciseDTO.getName());
        exercise.changeDescription(exerciseDTO.getDescription());
        exercise.changeCategory(exerciseDTO.getCategory());
        exercise.changeMuscles(exerciseDTO.getMuscles());
        exercise.changeEquipment(exerciseDTO.getEquipment());

        exerciseRepository.save(exercise);
    }

    @Transactional
    @Override
    public void deleteExercise(Integer id) {
        if (!exerciseRepository.existsById(id)) {
            throw new RuntimeException("Exercise not found with id: " + id);
        }
        exerciseRepository.deleteById(id);
    }

    @Transactional(readOnly = true)  // 읽기 전용 트랜잭션으로 변경
    @Override
    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}