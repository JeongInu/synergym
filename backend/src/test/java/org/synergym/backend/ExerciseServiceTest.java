package org.synergym.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.dto.ExerciseDTO;
import org.synergym.backend.repository.ExerciseRepository;
import org.synergym.backend.service.ExerciseService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
public class ExerciseServiceTest {


    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseRepository exerciseRepository;

    private ExerciseDTO testExerciseDTO;

    @BeforeEach
    void setUp() {
        testExerciseDTO = ExerciseDTO.builder()
                .name("벤치 프레스")
                .description("가슴 운동의 대표적인 운동")
                .category(1)
                .muscles(Arrays.asList(1, 2))
                .equipment(Arrays.asList(1))
                .build();
    }

    @Test
    @Rollback(value = false)
    void createExercise() {
        Integer exerciseId = exerciseService.addExercise(testExerciseDTO);
        ExerciseDTO savedExercise = exerciseService.getExerciseById(exerciseId);
        System.out.println("생성된 운동: " + savedExercise);
    }

    @Test
    void updateExercise() {
        Integer exerciseId = exerciseService.addExercise(testExerciseDTO);

        ExerciseDTO updateDTO = ExerciseDTO.builder()
                .name("인클라인 벤치프레스")
                .description("상부 가슴 운동")
                .category(1)
                .muscles(Arrays.asList(1, 2, 3))
                .equipment(Arrays.asList(1, 2))
                .build();

        exerciseService.updateExercise(exerciseId, updateDTO);
        ExerciseDTO updatedExercise = exerciseService.getExerciseById(exerciseId);
        System.out.println("수정된 운동: " + updatedExercise);
    }

    @Test
    void getAllExercises() {
        exerciseService.addExercise(testExerciseDTO);

        ExerciseDTO secondExercise = ExerciseDTO.builder()
                .name("스쿼트")
                .description("하체 운동의 왕")
                .category(2)
                .muscles(Arrays.asList(4, 5))
                .equipment(Arrays.asList(2))
                .build();
        exerciseService.addExercise(secondExercise);

        List<ExerciseDTO> exercises = exerciseService.getAllExercises();
        System.out.println("전체 운동 목록:");
        exercises.forEach(System.out::println);
    }

    @Test
    void deleteExercise() {
        Integer exerciseId = exerciseService.addExercise(testExerciseDTO);
        exerciseService.deleteExercise(exerciseId);

        try {
            ExerciseDTO deletedExercise = exerciseService.getExerciseById(exerciseId);
            System.out.println("삭제된 운동이 여전히 존재함: " + deletedExercise);
        } catch (RuntimeException e) {
            System.out.println("운동이 성공적으로 삭제됨: " + e.getMessage());
        }
    }
}
