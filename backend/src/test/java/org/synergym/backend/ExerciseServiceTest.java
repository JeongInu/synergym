package org.synergym.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
                    ", Description: " + dto.getDescription() +
                    ", Category: " + dto.getCategoryName());
        }
    }

    @Test
    void testGetExercisesByCategoryNameAndLanguageName() {
        // given
        String categoryName = "Back"; // 예시: Chest
        String languageName = "EN";  // 영어 → ID 2

        // when
        List<ExerciseResponseDTO> result = exerciseService.getExercisesByCategoryAndLanguage(categoryName, languageName);

        // then
        System.out.println("===== getExercisesByCategoryAndLanguage(\"" + categoryName + "\", \"" + languageName + "\") 결과 =====");
        for (ExerciseResponseDTO dto : result) {
            System.out.println("ID: " + dto.getId()
                    + ", 이름: " + dto.getName()
                    + ", 카테고리: " + dto.getCategoryName()
                    + ", 언어: " + dto.getLanguage());
        }
    }

    @Test
    public void testSearchExercises() {
        // 테스트할 파라미터 설정
        String keyword = "High Row";  // 예시로 운동 키워드 설정

        // Pageable 설정 (페이지 크기 10, 정렬 기준 id 내림차순)
        PageRequest pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("id")));

        // search 메서드 호출
        var result = exerciseService.searchExercises(keyword, pageable);

        // 결과 출력
        List<ExerciseResponseDTO> exercises = result.getContent();  // getBody() -> getContent()로 수정
        exercises.forEach(exercise -> {
            System.out.println("Exercise ID: " + exercise.getId());
            System.out.println("Exercise Name: " + exercise.getName());
            System.out.println("Exercise Description: " + exercise.getDescription());
            System.out.println("Exercise Category: " + exercise.getCategoryName());
            System.out.println("Exercise Language: " + exercise.getLanguageName());
            System.out.println("--------");
        });
    }
}
