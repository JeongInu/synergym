package org.synergym.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.synergym.backend.dto.ExerciseDTO;
import org.synergym.backend.dto.ExerciseResponseDTO;
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


    @Test
    void getAllExercises() {
        List<ExerciseResponseDTO> exercises = exerciseService.getAllExercises();
        System.out.println("===== getAllExercises() 결과 =====");
        for (ExerciseResponseDTO dto : exercises) {
            System.out.println("ID: " + dto.getId() +
                    ", Name: " + dto.getName() +
                    ", Category: " + dto.getCategory() +
                    ", Language: " + dto.getLanguage());
        }
    }

    @Test
    void testGetExercisesByCategoryAndLanguage() {
        Integer category = 8;
        Integer language = 2;

        List<ExerciseResponseDTO> result = exerciseService.getExercisesByCategoryAndLanguage(category, language);

        System.out.println("===== getExercisesByCategoryAndLanguage(" + category + ", " + language + ") 결과 =====");
        for (ExerciseResponseDTO dto : result) {
            System.out.println("ID: " + dto.getId() +
                    ", Name: " + dto.getName() +
                    ", Category: " + dto.getCategory() +
                    ", Language: " + dto.getLanguage());
        }
    }
}
