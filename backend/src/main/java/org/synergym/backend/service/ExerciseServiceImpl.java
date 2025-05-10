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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);

        if (exerciseOptional.isPresent()) {
            Exercise exercise = exerciseOptional.get();
            exercise.changeName(exerciseDTO.getName());
            exercise.changeDescription(exerciseDTO.getDescription());
            exercise.changeCategory(exerciseDTO.getCategory());
            exercise.changeMuscles(exerciseDTO.getMuscles());
            exercise.changeEquipment(exerciseDTO.getEquipment());

            exerciseRepository.save(exercise);
        }
=======
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exercise.get().updateFromDTO(exerciseDTO);  // Entity에서 DTO의 정보를 업데이트하는 메서드 호출
        exerciseRepository.save(exercise.get()); // 업데이트된 Entity 저장
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exercise.get().updateFromDTO(exerciseDTO);  // Entity에서 DTO의 정보를 업데이트하는 메서드 호출
        exerciseRepository.save(exercise.get()); // 업데이트된 Entity 저장
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exercise.get().updateFromDTO(exerciseDTO);  // Entity에서 DTO의 정보를 업데이트하는 메서드 호출
        exerciseRepository.save(exercise.get()); // 업데이트된 Entity 저장
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exercise.get().updateFromDTO(exerciseDTO);  // Entity에서 DTO의 정보를 업데이트하는 메서드 호출
        exerciseRepository.save(exercise.get()); // 업데이트된 Entity 저장
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exercise.get().updateFromDTO(exerciseDTO);  // Entity에서 DTO의 정보를 업데이트하는 메서드 호출
        exerciseRepository.save(exercise.get()); // 업데이트된 Entity 저장
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
>>>>>>> 90fa1dc (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> d09733c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 666814f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 9f3ef93 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> ff4e567 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + id));
        
        exercise.changeName(exerciseDTO.getName());
        exercise.changeDescription(exerciseDTO.getDescription());
        exercise.changeCategory(exerciseDTO.getCategory());
        exercise.changeMuscles(exerciseDTO.getMuscles());
        exercise.changeEquipment(exerciseDTO.getEquipment());

        exerciseRepository.save(exercise);
<<<<<<< HEAD
>>>>>>> cc535ab (test : user, exercise, routine test코드 작성)
=======
=======
=======
>>>>>>> 02d523e (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> c20f762 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> e8fc40a (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
>>>>>>> 8bfcd65 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);

        if (exerciseOptional.isPresent()) {
            Exercise exercise = exerciseOptional.get();
            exercise.changeName(exerciseDTO.getName());
            exercise.changeDescription(exerciseDTO.getDescription());
            exercise.changeCategory(exerciseDTO.getCategory());
            exercise.changeMuscles(exerciseDTO.getMuscles());
            exercise.changeEquipment(exerciseDTO.getEquipment());

            exerciseRepository.save(exercise);
        }
=======
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exercise.get().updateFromDTO(exerciseDTO);  // Entity에서 DTO의 정보를 업데이트하는 메서드 호출
        exerciseRepository.save(exercise.get()); // 업데이트된 Entity 저장
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> 8859a4d (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> 90fa1dc (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
=======
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exercise.get().updateFromDTO(exerciseDTO);  // Entity에서 DTO의 정보를 업데이트하는 메서드 호출
        exerciseRepository.save(exercise.get()); // 업데이트된 Entity 저장
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> 02d523e (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> d09733c (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
=======
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exercise.get().updateFromDTO(exerciseDTO);  // Entity에서 DTO의 정보를 업데이트하는 메서드 호출
        exerciseRepository.save(exercise.get()); // 업데이트된 Entity 저장
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> c20f762 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> 666814f (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
=======
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exercise.get().updateFromDTO(exerciseDTO);  // Entity에서 DTO의 정보를 업데이트하는 메서드 호출
        exerciseRepository.save(exercise.get()); // 업데이트된 Entity 저장
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> e8fc40a (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
<<<<<<< HEAD
>>>>>>> 9f3ef93 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
=======
=======
=======
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        exercise.get().updateFromDTO(exerciseDTO);  // Entity에서 DTO의 정보를 업데이트하는 메서드 호출
        exerciseRepository.save(exercise.get()); // 업데이트된 Entity 저장
>>>>>>> 48662b8 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> 8bfcd65 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
>>>>>>> ff4e567 (feat(users) : service코드 수정 및 createdAt 칼럼삭제)
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